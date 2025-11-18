package gregtechlite.gtlitecore.client.model

import com.google.common.collect.Table
import com.morphismmc.morphismlib.client.Games
import com.morphismmc.morphismlib.integration.Mods
import gregtech.api.unification.material.info.MaterialIconSet
import gregtech.api.unification.material.info.MaterialIconType
import gregtech.api.util.GTUtility
import gregtechlite.gtlitecore.api.GTLiteLog
import gregtechlite.gtlitecore.api.MOD_ID
import gregtechlite.gtlitecore.api.collection.openHashMapOf
import gregtechlite.gtlitecore.api.collection.hashTableOf
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
import net.minecraftforge.fml.common.ObfuscationReflectionHelper
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.relauncher.Side

/**
 * Material BlockState Loader.
 *
 * This is a custom model loader for GTCEu format material blocks that handles registration and loading of material
 * block models with additional parameters. This implementation provides compatibility with CTM mod state models while
 * maintaining functionality for standard material block rendering.
 *
 * @see gregtech.client.model.modelfactories.MaterialBlockModelLoader
 */
@Mod.EventBusSubscriber(modid = MOD_ID, value = [Side.CLIENT])
object MaterialBlockStateLoader {

    private val BLOCKSTATES_CACHE: Table<MaterialIconType, MaterialIconSet, ResourceLocation> = hashTableOf()
    private val ENTRIES: MutableMap<ModelEntry, ModelResourceLocation> = openHashMapOf()

    /**
     * Loads or retrieves a cached block model for the specified [iconType] and [iconSet].
     *
     * @param iconType The icon type of the material.
     * @param iconSet  The icon set of the material.
     * @param variant  The block state variant identifier, e.g. "normal", "inventory".
     * @return         A [ModelResourceLocation] pointing to the baked model resource.
     */
    @JvmOverloads
    fun loadBlockModel(iconType: MaterialIconType,
                       iconSet: MaterialIconSet,
                       variant: String? = null): ModelResourceLocation
    {
        return ENTRIES.getOrPut(ModelEntry(iconType, iconSet, variant.orEmpty())) {
            createModelId(iconType, iconSet, variant, "normal")
        }
    }

    /**
     * Loads or retrieves a cached item model for the specified material [iconType] and [iconSet].
     *
     * @param iconType The icon type of the material.
     * @param iconSet  The icon set of the material.
     * @return         A [ModelResourceLocation] pointing to the baked model resource.
     */
    fun loadItemModel(iconType: MaterialIconType,
                      iconSet: MaterialIconSet): ModelResourceLocation
    {
        return ENTRIES.getOrPut(ModelEntry(iconType, iconSet, null)) {
            createModelId(iconType, iconSet, null, "inventory")
        }
    }

    /**
     * Pre-loads and caches all registered material block models during texture stitching, ensuring textures are
     * properly registered in the texture atlas.
     *
     * @param event The texture stitching pre-event containing textureMap information.
     */
    @SubscribeEvent
    fun onTextureStitch(event: TextureStitchEvent.Pre)
    {
        ENTRIES.keys.forEach { it.modelCache = loadModel(event, it) }
    }

    /**
     * Bakes all pre-loaded material block models and registers them in the model registry.
     * Supported connected textures when the CTM mod is active.
     *
     * @param event The model bake event containing model registry and loader references.
     */
    @SubscribeEvent
    fun onModelBake(event: ModelBakeEvent)
    {
        ENTRIES.entries.forEach { (modelEntry, modelResourceLocation) ->
            if (Mods.CTM.isActive)
            {
                val stateModels = ObfuscationReflectionHelper.getPrivateValue<MutableMap<ModelResourceLocation, IModel>, ModelLoader>(
                    ModelLoader::class.java, event.modelLoader, "stateModels")
                bakeAndRegister(event.modelRegistry, modelEntry.modelCache, modelResourceLocation, stateModels)
            }
            else
            {
                bakeAndRegister(event.modelRegistry, modelEntry.modelCache, modelResourceLocation, null)
            }
        }
    }

    private fun createModelId(iconType: MaterialIconType,
                              iconSet: MaterialIconSet,
                              variant: String?,
                              modelVariant: String): ModelResourceLocation
    {
        val basePath = listOf("material", iconType.name, iconSet.name).joinToString("_")
        val variantSuffix = variant
            ?.takeIf { it.isNotBlank() && it != "normal" }
            ?.replace(Regex("[=,]"), "_")

        val fullPath = variantSuffix?.let { "${basePath}_$it" } ?: basePath
        return ModelResourceLocation(GTUtility.gregtechId(fullPath), modelVariant)
    }

    private fun loadModel(event: TextureStitchEvent.Pre, modelEntry: ModelEntry): IModel? = runCatching {
        ModelLoaderRegistry.getModel(modelEntry.modelLocation)
            .also {
                it.textures.forEach { texture -> event.map.registerSprite(texture) }
            }
        }
        .onFailure { GTLiteLog.logger.error("Failed to load material model {}:", modelEntry, it) }
        .getOrNull()

    private fun bakeAndRegister(registry: IRegistry<ModelResourceLocation?, IBakedModel?>,
                                model: IModel?,
                                modelId: ModelResourceLocation,
                                stateModels: MutableMap<ModelResourceLocation, IModel>?)
    {
        val bakedModel = bake(model ?: ModelLoaderRegistry.getMissingModel())
        registry.putObject(modelId, bakedModel)
        stateModels?.put(modelId, model ?: ModelLoaderRegistry.getMissingModel())
    }

    private fun bake(model: IModel): IBakedModel = model.bake(model.defaultState, DefaultVertexFormats.ITEM) {
        Games.mc().textureMapBlocks.getAtlasSprite(it.toString())
    }

    /**
     * The record representing a material model entry in the cache.
     *
     * @property iconType The icon type of the material.
     * @property iconSet  The icon set of the material.
     * @property variant  The block state variant identifier, e.g. "normal", "inventory".
     *
     * @constructor       Creates a new model entry with specified [iconType] and [iconSet].
     */
    private data class ModelEntry(val iconType: MaterialIconType,
                                  val iconSet: MaterialIconSet,
                                  val variant: String?)
    {

        /**
         * Cached model instance to avoid repeated loading.
         *
         * This property stores the loaded IModel instance after texture stitching for use during model baking phase.
         */
        var modelCache: IModel? = null

        /**
         * Generates the appropriate resource location for this model entry.
         *
         * For block states with variants, generates a blockstate resource location. For item models, generates an item
         * model resource location.
         *
         * @return A [ResourceLocation] pointing to the model definition file
         */
        val modelLocation: ResourceLocation
            get() = if (variant != null)
            {
                ModelResourceLocation(iconType.recurseIconsetPath(iconSet, BLOCKSTATES_CACHE,
                    "blockstates/material_sets/%s/%s.json",
                    "material_sets/%s/%s"), variant)
            }
            else
            {
                val itemModelPath = iconType.getItemModelPath(iconSet)
                ResourceLocation(itemModelPath.namespace, "item/${itemModelPath.path}")
            }
    }

}