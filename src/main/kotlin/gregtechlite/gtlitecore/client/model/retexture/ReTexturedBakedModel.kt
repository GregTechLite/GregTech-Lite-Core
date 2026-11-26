package gregtechlite.gtlitecore.client.model.retexture

import com.google.common.collect.ImmutableMap
import com.morphismmc.morphismlib.client.Games
import gregtechlite.gtlitecore.api.collection.openHashMapOf
import net.minecraft.block.state.IBlockState
import net.minecraft.client.renderer.block.model.BakedQuad
import net.minecraft.client.renderer.block.model.IBakedModel
import net.minecraft.client.renderer.block.model.ItemCameraTransforms
import net.minecraft.client.renderer.block.model.ItemOverrideList
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.client.renderer.vertex.DefaultVertexFormats
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation
import net.minecraftforge.client.MinecraftForgeClient
import net.minecraftforge.client.model.IModel
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import org.apache.commons.lang3.tuple.Pair
import javax.vecmath.Matrix4f

@SideOnly(Side.CLIENT)
class ReTexturedBakedModel(private val baseModel: ReTexturedModel) : IBakedModel
{
    private val bakedModels = openHashMapOf<ResourceLocation, IBakedModel>()
    private val retexturedModels = openHashMapOf<TexturedModelKey, IBakedModel>()

    override fun getQuads(state: IBlockState?, side: EnumFacing?, rand: Long): List<BakedQuad>
    {
        val provider = baseModel.provider ?: return emptyList()

        val layer = MinecraftForgeClient.getRenderLayer()
        val models = baseModel.getModels()
        val quads = arrayListOf<BakedQuad>()

        for (idx in models.indices)
        {
            val resource = baseModel.resources[idx]
            if (layer != null && !provider.shouldRenderInLayer(state, side, resource, layer)) continue
            val model = getBakedModel(models[idx], resource, provider.reTextured(state, side, resource))
            quads.addAll(provider.reBakedQuad(state, side, resource, model.getQuads(state, side, rand)))
        }
        return quads
    }

    private fun getBakedModel(model: IModel, resource: ResourceLocation,
                              textures: Map<String, String>?): IBakedModel
    {
        if (textures.isNullOrEmpty())
            return bakedModels.getOrPut(resource) { bake(model) }

        return retexturedModels.getOrPut(TexturedModelKey(resource, textures)) {
            bake(model.retexture(ImmutableMap.copyOf(textures)))
        }
    }

    private fun bake(model: IModel): IBakedModel
        = model.bake(model.defaultState, DefaultVertexFormats.ITEM) {
            Games.mc().textureMapBlocks.getAtlasSprite(it.toString())
        }

    override fun isAmbientOcclusion(): Boolean = getDefaultBakedModel().isAmbientOcclusion

    override fun isAmbientOcclusion(state: IBlockState): Boolean = getDefaultBakedModel().isAmbientOcclusion(state)

    override fun isGui3d(): Boolean = getDefaultBakedModel().isGui3d

    override fun isBuiltInRenderer(): Boolean = getDefaultBakedModel().isBuiltInRenderer

    override fun getParticleTexture(): TextureAtlasSprite = getDefaultBakedModel().particleTexture

    @Suppress("Deprecation")
    @Deprecated("Deprecated in Java")
    override fun getItemCameraTransforms(): ItemCameraTransforms = getDefaultBakedModel().itemCameraTransforms

    override fun handlePerspective(transformType: ItemCameraTransforms.TransformType): Pair<out IBakedModel, Matrix4f>
        = getDefaultBakedModel().handlePerspective(transformType)

    override fun getOverrides(): ItemOverrideList = getDefaultBakedModel().overrides

    private fun getDefaultBakedModel(): IBakedModel
        = bakedModels.getOrPut(baseModel.resources[0]) { bake(baseModel.getModels()[0]) }

    private data class TexturedModelKey(val resource: ResourceLocation, val textures: Map<String, String>)
}
