package gregtechlite.gtlitecore.mixins.gregtech.client;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import gregtech.api.pipenet.block.material.TileEntityMaterialPipeBase;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;
import gregtech.api.util.GTUtility;
import gregtech.client.renderer.pipe.FluidPipeRenderer;
import gregtech.common.pipelike.fluidpipe.FluidPipeType;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.EnumMap;

/**
 * Add {@link MaterialIconSet} supported for {@link FluidPipeRenderer}.
 * <p>
 * This class implement a feature that make material pipes rendered its iconSet textures
 * but not default textures.
 * <p>
 * WARNING: This change will cause the original pipes textures be broken, should add
 * correspondenced iconSet textures to all iconSets.
 *
 * @author Magic_Sweepy
 */
@Mixin(value = FluidPipeRenderer.class, remap = false)
public abstract class MixinFluidPipeRenderer
{
    @Unique
    private final Table<FluidPipeType, MaterialIconSet, TextureAtlasSprite> gtlitecore$pipeTextures
            = HashBasedTable.create();

    @Unique
    private final Object2ObjectMap<MaterialIconSet, TextureAtlasSprite> gtlitecore$pipeSideTextures
            = new Object2ObjectOpenHashMap<>();

    @Inject(method = "registerIcons",
            at = @At(value = "TAIL"))
    private void gtlitecore$registerIcons(TextureMap map, CallbackInfo callbackInfo)
    {
        for (MaterialIconSet iconSet : MaterialIconSet.ICON_SETS.values())
        {
            ResourceLocation iconSetPipeTinyLocation = GTUtility.gregtechId("blocks/material_sets/"
                    + iconSet.getName().toLowerCase() + "/pipe_tiny_in");
            ResourceLocation iconSetPipeSmallLocation = GTUtility.gregtechId("blocks/material_sets/"
                    + iconSet.getName().toLowerCase() + "/pipe_small_in");
            ResourceLocation iconSetPipeNormalLocation = GTUtility.gregtechId("blocks/material_sets/"
                    + iconSet.getName().toLowerCase() + "/pipe_normal_in");
            ResourceLocation iconSetPipeLargeLocation = GTUtility.gregtechId("blocks/material_sets/"
                    + iconSet.getName().toLowerCase() + "/pipe_large_in");
            ResourceLocation iconSetPipeHugeLocation = GTUtility.gregtechId("blocks/material_sets/"
                    + iconSet.getName().toLowerCase() + "/pipe_huge_in");
            ResourceLocation iconSetPipeQuadrupleLocation = GTUtility.gregtechId("blocks/material_sets/"
                    + iconSet.getName().toLowerCase() + "/pipe_quadruple_in");
            ResourceLocation iconSetPipeNonupleLocation = GTUtility.gregtechId("blocks/material_sets/"
                    + iconSet.getName().toLowerCase() + "/pipe_nonuple_in");
            ResourceLocation iconSetPipeSideLocation = GTUtility.gregtechId("blocks/material_sets/"
                    + iconSet.getName().toLowerCase() + "/pipe_side");

            gtlitecore$pipeTextures.put(FluidPipeType.TINY, iconSet, map.registerSprite(iconSetPipeTinyLocation));
            gtlitecore$pipeTextures.put(FluidPipeType.SMALL, iconSet, map.registerSprite(iconSetPipeSmallLocation));
            gtlitecore$pipeTextures.put(FluidPipeType.NORMAL, iconSet, map.registerSprite(iconSetPipeNormalLocation));
            gtlitecore$pipeTextures.put(FluidPipeType.LARGE, iconSet, map.registerSprite(iconSetPipeLargeLocation));
            gtlitecore$pipeTextures.put(FluidPipeType.HUGE, iconSet, map.registerSprite(iconSetPipeHugeLocation));
            gtlitecore$pipeTextures.put(FluidPipeType.QUADRUPLE, iconSet, map.registerSprite(iconSetPipeQuadrupleLocation));
            gtlitecore$pipeTextures.put(FluidPipeType.NONUPLE, iconSet, map.registerSprite(iconSetPipeNonupleLocation));
            gtlitecore$pipeSideTextures.put(iconSet, map.registerSprite(iconSetPipeSideLocation));
        }
    }

    /**
     * @author Magic_Sweepy
     * @reason Replace the per-pipe-type texture lookup ({@code EnumMap.get}) of {@code buildRenderer}.
     */
    @WrapOperation(method = "buildRenderer",
                   at = @At(value = "INVOKE",
                            target = "Ljava/util/EnumMap;get(Ljava/lang/Object;)Ljava/lang/Object;"))
    private Object gtlitecore$getPipeTexture(EnumMap<FluidPipeType, TextureAtlasSprite> enumMap, Object key,
                                             Operation<Object> original,
                                             @Local(index = 5, argsOnly = true) Material material)
    {
        return gtlitecore$pipeTextures.get((FluidPipeType) key, material.getMaterialIconSet());
    }

    /**
     * @author Magic_Sweepy
     * @reason Replace the static {@code Textures.PIPE_SIDE} field read of {@code buildRenderer}.
     */
    @WrapOperation(method = "buildRenderer",
                   at = @At(value = "FIELD",
                            target = "Lgregtech/client/renderer/texture/Textures;PIPE_SIDE:Lnet/minecraft/client/renderer/texture/TextureAtlasSprite;"))
    private TextureAtlasSprite gtlitecore$getPipeSideTexture(Operation<TextureAtlasSprite> original,
                                                             @Local(index = 5, argsOnly = true) Material material)
    {
        return gtlitecore$pipeSideTextures.get(material.getMaterialIconSet());
    }

    /**
     * @author Magic_Sweepy
     * @reason Replace the static {@code Textures.PIPE_SIDE_WOOD} field read of {@code buildRenderer}.
     */
    @WrapOperation(method = "buildRenderer",
                   at = @At(value = "FIELD",
                            target = "Lgregtech/client/renderer/texture/Textures;PIPE_SIDE_WOOD:Lnet/minecraft/client/renderer/texture/TextureAtlasSprite;"))
    private TextureAtlasSprite gtlitecore$getPipeSideWoodTexture(Operation<TextureAtlasSprite> original,
                                                                 @Local(index = 5, argsOnly = true) Material material)
    {
        return gtlitecore$pipeSideTextures.get(material.getMaterialIconSet());
    }
}
