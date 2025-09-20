@file:Suppress("Deprecation")
package gregtechlite.gtlitecore.client.model

import com.google.common.collect.HashBasedTable
import gregtech.api.unification.material.info.MaterialIconSet
import gregtech.api.unification.material.info.MaterialIconType
import gregtech.api.util.GTUtility
import gregtechlite.gtlitecore.api.GTLiteLog
import gregtechlite.gtlitecore.api.MOD_ID
import com.morphismmc.morphismlib.integration.Mods
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.block.model.IBakedModel
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.client.renderer.vertex.DefaultVertexFormats
import net.minecraft.util.ResourceLocation
import net.minecraft.util.registry.IRegistry
import net.minecraftforge.client.event.ModelBakeEvent
import net.minecraftforge.client.event.TextureStitchEvent
import net.minecraftforge.client.model.IModel
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.client.model.ModelLoaderRegistry
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.relauncher.ReflectionHelper
import net.minecraftforge.fml.relauncher.Side

/**
 * Material BlockState Loader.
 *
 * This model loader is a custom `BlockModelLoader` for GT material blocks,
 * used to register material block with extra params and load correspondenced
 * item block model. Another choice is vanilla GTCEu `MaterialBlockModelLoader`,
 * this class is compatible with CTM stateModels.
 *
 * @see gregtech.client.model.modelfactories.MaterialBlockModelLoader
 */
@Mod.EventBusSubscriber(modid = MOD_ID, value = [Side.CLIENT])
object MaterialBlockStateLoader
{

    private val BLOCKSTATES_CACHE = HashBasedTable.create<MaterialIconType?, MaterialIconSet?, ResourceLocation?>()
    private val ENTRIES = Object2ObjectOpenHashMap<ModelEntry, ModelResourceLocation>()

    @JvmOverloads
    fun loadBlockModel(iconType: MaterialIconType,
                       iconSet: MaterialIconSet,
                       variant: String? = null): ModelResourceLocation
    {
        return ENTRIES.computeIfAbsent(ModelEntry(iconType, iconSet, variant ?: "")) { modelEntry ->
            createModelId(modelEntry, "normal")
        }
    }

    fun loadItemModel(iconType: MaterialIconType,
                      iconSet: MaterialIconSet): ModelResourceLocation
    {
        return ENTRIES.computeIfAbsent(ModelEntry(iconType, iconSet, null)) { modelEntry ->
            createModelId(modelEntry, "inventory")
        }
    }

    private fun createModelId(modelEntry: ModelEntry,
                              variant: String): ModelResourceLocation
    {
        val basePath = listOf("material", modelEntry.iconType.name, modelEntry.iconSet.name).joinToString("_")
        val variantSuffix = modelEntry.variant?.takeIf { it.isNotBlank() && it != "normal" }
            ?.replace(Regex("[=,]"), "_")
        val fullPath = variantSuffix?.let { "${basePath}_$it" } ?: basePath
        return ModelResourceLocation(GTUtility.gregtechId(fullPath), variant)
    }

    @SubscribeEvent
    fun onTextureStitch(event: TextureStitchEvent.Pre)
    {
        for (entry in ENTRIES.keys)
            entry.modelCache = loadModel(event, entry)
    }

    @SubscribeEvent
    fun onModelBake(event: ModelBakeEvent)
    {
        ENTRIES.entries.forEach { modelEntry ->
            if (Mods.CTM.isActive)
            {
                val stateModels = ReflectionHelper.getPrivateValue<MutableMap<ModelResourceLocation, IModel>, ModelLoader>(
                    ModelLoader::class.java, event.modelLoader, "stateModels", null)
                bakeAndRegister(event.modelRegistry, modelEntry.key?.modelCache, modelEntry.value, stateModels)
            }
            else
            {
                bakeAndRegister(event.modelRegistry, modelEntry.key?.modelCache, modelEntry.value, null)
            }
        }
    }

    private fun loadModel(event: TextureStitchEvent.Pre, modelEntry: ModelEntry): IModel?
    {
        val model: IModel?
        try
        {
            model = ModelLoaderRegistry.getModel(modelEntry.modelLocation)
        }
        catch (exception: Exception)
        {
            GTLiteLog.logger.error("Failed to load material model {}:", modelEntry, exception)
            return null
        }
        for (texture in model.textures)
            event.map.registerSprite(texture)
        return model
    }

    private fun bakeAndRegister(registry: IRegistry<ModelResourceLocation?, IBakedModel?>,
                                model: IModel?,
                                modelId: ModelResourceLocation,
                                stateModels: MutableMap<ModelResourceLocation, IModel>?)
    {
        if (model == null)
        {
            // Insert missing model to prevent cluttering logs with useless model loading error messages.
            registry.putObject(modelId, bake(ModelLoaderRegistry.getMissingModel()))
            return
        }
        registry.putObject(modelId, bake(model))
        stateModels?.put(modelId, model)
    }

    private fun bake(model: IModel): IBakedModel
    {
        return model.bake(model.defaultState, DefaultVertexFormats.ITEM) { spriteLocation ->
            Minecraft.getMinecraft().textureMapBlocks.getAtlasSprite(spriteLocation.toString())
        }
    }

    private data class ModelEntry(val iconType: MaterialIconType,
                                  val iconSet: MaterialIconSet,
                                  val variant: String?)
    {

        var modelCache: IModel? = null

        val modelLocation: ResourceLocation
            get()
            {
                if (this.variant != null)
                {
                    return ModelResourceLocation(this.iconType.recurseIconsetPath(this.iconSet,
                                                                                  BLOCKSTATES_CACHE,
                                                                                  "blockstates/material_sets/%s/%s.json",
                                                                                  "material_sets/%s/%s"), variant)
                }
                else
                {
                    val itemModelPath = this.iconType.getItemModelPath(this.iconSet)
                    return ResourceLocation(itemModelPath.getNamespace(), "item/" + itemModelPath.getPath())
                }
            }

    }

}