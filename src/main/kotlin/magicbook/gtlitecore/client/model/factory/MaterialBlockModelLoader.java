package magicbook.gtlitecore.client.model.factory;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import gregtech.api.unification.material.info.MaterialIconSet;
import gregtech.api.unification.material.info.MaterialIconType;
import gregtech.api.util.GTUtility;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import magicbook.gtlitecore.api.utils.GTLiteLog;
import magicbook.gtlitecore.api.utils.GTLiteValues;
import magicbook.gtlitecore.api.utils.Mods;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.IRegistry;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Objects;

/**
 * Transformed from {@link gregtech.client.model.modelfactories.MaterialBlockModelLoader}.
 */
@Mod.EventBusSubscriber(modid = GTLiteValues.MODID, value = Side.CLIENT)
public class MaterialBlockModelLoader
{

    private static final Table<MaterialIconType, MaterialIconSet, ResourceLocation> BLOCKSTATES_CACHE = HashBasedTable.create();

    private static final Object2ObjectOpenHashMap<ModelEntry, ModelResourceLocation> ENTRIES = new Object2ObjectOpenHashMap<>();

    @NotNull
    public static ModelResourceLocation loadBlockModel(@NotNull MaterialIconType iconType,
                                                       @NotNull MaterialIconSet iconSet)
    {
        return loadBlockModel(iconType, iconSet, null);
    }

    @NotNull
    public static ModelResourceLocation loadBlockModel(@NotNull MaterialIconType iconType,
                                                       @NotNull MaterialIconSet iconSet,
                                                       @Nullable String variant)
    {
        return ENTRIES.computeIfAbsent(new ModelEntry(iconType, iconSet,
                variant == null ? "" : variant),
                modelEntry -> createModelId(modelEntry, "normal"));
    }

    @NotNull
    public static ModelResourceLocation loadItemModel(@NotNull MaterialIconType iconType,
                                                      @NotNull MaterialIconSet iconSet)
    {
        return ENTRIES.computeIfAbsent(new ModelEntry(iconType, iconSet, null),
                modelEntry -> createModelId(modelEntry, "inventory"));
    }

    private static ModelResourceLocation createModelId(@NotNull ModelEntry modelEntry,
                                                       @NotNull String variant)
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("material_")
                .append(modelEntry.iconType.name)
                .append("_")
                .append(modelEntry.iconSet.name);
        if (modelEntry.variant != null && !modelEntry.variant.isEmpty()
                && !modelEntry.variant.equals("normal"))
        {
            stringBuilder.append("_")
                    .append(modelEntry.variant.replace('=', '_')
                            .replace(',', '_'));
        }
        return new ModelResourceLocation(GTUtility.gregtechId(
                stringBuilder.toString()), variant);
    }

    @SubscribeEvent
    public static void onTextureStitch(TextureStitchEvent.Pre event)
    {
        for (ModelEntry entry : ENTRIES.keySet())
            entry.modelCache = loadModel(event, entry);
    }

    @SuppressWarnings("deprecation")
    @SubscribeEvent
    public static void onModelBake(ModelBakeEvent event)
    {
        Map<ModelResourceLocation, IModel> stateModels = Mods.CTM.isModLoaded()
                ? ReflectionHelper.getPrivateValue(ModelLoader.class, event.getModelLoader(),
                    "stateModels", null) : null;

        ENTRIES.entrySet().forEach(e -> bakeAndRegister(event.getModelRegistry(),
                e.getKey().modelCache, e.getValue(), stateModels));

    }

    @Nullable
    private static IModel loadModel(TextureStitchEvent.Pre event, ModelEntry modelEntry)
    {
        IModel model;
        try
        {
            model = ModelLoaderRegistry.getModel(modelEntry.getModelLocation());
        }
        catch (Exception exception)
        {
            GTLiteLog.logger.error("Failed to load material model {}:", modelEntry, exception);
            return null;
        }
        for (ResourceLocation texture : model.getTextures())
            event.getMap().registerSprite(texture);
        return model;
    }

    private static void bakeAndRegister(@NotNull IRegistry<ModelResourceLocation, IBakedModel> registry,
                                        @Nullable IModel model,
                                        @NotNull ModelResourceLocation modelId,
                                        @Nullable Map<ModelResourceLocation, IModel> stateModels)
    {
        if (model == null)
        {
            // Insert missing model to prevent cluttering logs with useless model loading error messages.
            registry.putObject(modelId, bake(ModelLoaderRegistry.getMissingModel()));
            return;
        }
        registry.putObject(modelId, bake(model));
        if (stateModels != null) // CTM needs the model to be present on this field to properly replace the model.
            stateModels.put(modelId, model);
    }

    private static IBakedModel bake(IModel model)
    {
        return model.bake(model.getDefaultState(), DefaultVertexFormats.ITEM,
                t -> Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(t.toString()));
    }

    private static final class ModelEntry
    {

        private final MaterialIconType iconType;
        private final MaterialIconSet iconSet;
        private final String variant;

        @Nullable
        private IModel modelCache;

        private ModelEntry(@NotNull MaterialIconType iconType,
                           @NotNull MaterialIconSet iconSet,
                           @Nullable String variant)
        {
            this.iconType = iconType;
            this.iconSet = iconSet;
            this.variant = variant;
        }

        public ResourceLocation getModelLocation()
        {
            if (this.variant != null)
            {
                return new ModelResourceLocation(this.iconType
                        .recurseIconsetPath(this.iconSet, BLOCKSTATES_CACHE,
                                "blockstates/material_sets/%s/%s.json",
                                "material_sets/%s/%s"), variant);
            }
            else
            {
                ResourceLocation itemModelPath = this.iconType.getItemModelPath(this.iconSet);
                return new ResourceLocation(itemModelPath.getNamespace(), "item/" + itemModelPath.getPath());
            }
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o)
                return true;
            if (o == null || this.getClass() != o.getClass())
                return false;
            ModelEntry modelEntry = (ModelEntry) o;
            return Objects.equals(this.variant, modelEntry.variant)
                    && this.iconType.equals(modelEntry.iconType)
                    && this.iconSet.equals(modelEntry.iconSet);
        }

        @Override
        public int hashCode()
        {
            return Objects.hash(this.iconType, this.iconSet, this.variant);
        }

        @Override
        public String toString()
        {
            return "ModelEntry{iconType=" + this.iconType + ", iconSet=" + this.iconSet
                    + ", variant=" + this.variant + "}";
        }

    }

}
