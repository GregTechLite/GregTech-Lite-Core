package gregtechlite.gtlitecore.client.model.retexture

import com.google.common.collect.ImmutableMap
import gregtechlite.gtlitecore.api.LOGGER
import net.minecraft.client.renderer.block.model.IBakedModel
import net.minecraft.client.renderer.block.model.ModelBlock
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.client.renderer.vertex.VertexFormat
import net.minecraft.util.ResourceLocation
import net.minecraftforge.client.model.IModel
import net.minecraftforge.client.model.ModelLoaderRegistry
import net.minecraftforge.common.model.IModelState
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import java.util.Optional
import java.util.function.Function

@SideOnly(Side.CLIENT)
class ReTexturedModel(val resources: Array<ResourceLocation>) : IModel
{
    var provider: ReTexturedModelProvider? = null
    var variant: ResourceLocation? = null

    private var loadedModels: Array<IModel>? = null

    fun getModels(): Array<IModel> = loadedModels ?: loadModels()

    fun reset()
    {
        loadedModels = null
    }

    private fun loadModels(): Array<IModel>
    {
        val models = resources.map { resource ->
            val model = runCatching { ModelLoaderRegistry.getModel(resource) }
                .onFailure { LOGGER.error("Failed to load the retextured model resource $resource:", it) }
                .getOrElse { ModelLoaderRegistry.getMissingModel() }
            provider?.loadModel(variant ?: resource, resource, model) ?: model
        }.toTypedArray()
        loadedModels = models
        return models
    }

    override fun getDependencies(): Collection<ResourceLocation>
        = getModels().flatMap { it.dependencies }.distinct()

    override fun getTextures(): Collection<ResourceLocation>
        = getModels().flatMap { it.textures }.distinct()

    override fun bake(state: IModelState, format: VertexFormat,
                      bakedTextureGetter: Function<ResourceLocation, TextureAtlasSprite>): IBakedModel
        = ReTexturedBakedModel(this)

    override fun getDefaultState(): IModelState = getModels()[0].defaultState

    override fun process(customData: ImmutableMap<String, String>): ReTexturedModel
        = withModels { it.process(customData) }

    override fun smoothLighting(value: Boolean): ReTexturedModel = withModels { it.smoothLighting(value) }

    override fun gui3d(value: Boolean): ReTexturedModel = withModels { it.gui3d(value) }

    override fun uvlock(value: Boolean): ReTexturedModel = withModels { it.uvlock(value) }

    override fun retexture(textures: ImmutableMap<String, String>): ReTexturedModel
    {
        if (textures.isEmpty())
            return this
        return withModels { it.retexture(textures) }
    }

    override fun asVanillaModel(): Optional<ModelBlock> = getModels()[0].asVanillaModel()

    private fun withModels(transform: (IModel) -> IModel): ReTexturedModel
    {
        val models = getModels().map(transform).toTypedArray()
        return ReTexturedModel(resources).also {
            it.provider = provider
            it.variant = variant
            it.loadedModels = models
        }
    }
}
