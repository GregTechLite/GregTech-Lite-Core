package gregtechlite.gtlitecore.client.renderer.texture.custom

import codechicken.lib.render.CCRenderState
import codechicken.lib.render.pipeline.IVertexOperation
import codechicken.lib.vec.Cuboid6
import codechicken.lib.vec.Matrix4
import gregtech.client.renderer.texture.Textures
import gregtech.client.texture.IconRegistrar
import gregtechlite.gtlitecore.api.MOD_ID
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.client.renderer.texture.TextureMap
import net.minecraft.util.BlockRenderLayer
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class ExtenderRenderer(private val basePath: String) : IconRegistrar
{

    @SideOnly(Side.CLIENT)
    private lateinit var textures: Array<TextureAtlasSprite>

    init
    {
        Textures.iconRegisters.add(this)
    }

    @SideOnly(Side.CLIENT)
    override fun registerIcons(textureMap: TextureMap)
    {
        val formattedBase = "${MOD_ID}:blocks/$basePath"
        this.textures = arrayOf(
            textureMap.registerSprite(ResourceLocation("$formattedBase/in")),
            textureMap.registerSprite(ResourceLocation("$formattedBase/side")),
            textureMap.registerSprite(ResourceLocation("$formattedBase/out"))
        )
    }

    @SideOnly(Side.CLIENT)
    fun render(renderState: CCRenderState?,
               translation: Matrix4?,
               pipeline: Array<IVertexOperation?>?,
               outFace: EnumFacing?, inFace: EnumFacing?)
    {
        for (renderSide in EnumFacing.VALUES)
        {
            val baseSprite = when (renderSide)
            {
                inFace -> textures[0]
                outFace -> textures[2]
                else -> textures[1]
            }
            Textures.renderFace(renderState, translation, pipeline, renderSide, Cuboid6.full,
                                baseSprite, BlockRenderLayer.CUTOUT_MIPPED)
        }
    }

    @SideOnly(Side.CLIENT)
    fun getParticleTexture(): TextureAtlasSprite? = textures[0]

}