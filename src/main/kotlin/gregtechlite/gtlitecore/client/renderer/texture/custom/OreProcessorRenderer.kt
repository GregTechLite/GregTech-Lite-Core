package gregtechlite.gtlitecore.client.renderer.texture.custom

import codechicken.lib.render.CCRenderState
import codechicken.lib.render.pipeline.IVertexOperation
import codechicken.lib.vec.Cuboid6
import codechicken.lib.vec.Matrix4
import gregtech.client.renderer.texture.Textures
import gregtech.client.texture.IconRegistrar
import gregtechlite.gtlitecore.api.extension.registerSprite
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.client.renderer.texture.TextureMap
import net.minecraft.util.BlockRenderLayer
import net.minecraft.util.EnumFacing
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class OreProcessorRenderer : IconRegistrar
{

    @SideOnly(Side.CLIENT)
    private var baseBackgroundSprite: TextureAtlasSprite? = null

    @SideOnly(Side.CLIENT)
    private var activeBladeSprite: TextureAtlasSprite? = null

    init
    {
        Textures.iconRegisters.add(this)
    }

    @SideOnly(Side.CLIENT)
    override fun registerIcons(textureMap: TextureMap)
    {
        baseBackgroundSprite = textureMap.registerSprite("blocks/machines/multiblock/integrated_ore_processor/base_background")
        activeBladeSprite = textureMap.registerSprite("blocks/machines/multiblock/integrated_ore_processor/rotor_spinning")
    }

    @SideOnly(Side.CLIENT)
    fun renderSided(renderState: CCRenderState?, translation: Matrix4?, pipeline: Array<IVertexOperation?>?,
                    side: EnumFacing, hasBase: Boolean, isActive: Boolean, )
    {
        var cornerOffset: Matrix4
        when (side.axis)
        {
            EnumFacing.Axis.X ->
            {
                cornerOffset = translation!!.copy().translate(0.01 * side.xOffset, -1.0, -1.0)
                cornerOffset.scale(1.0, 3.0, 3.0)
            }

            EnumFacing.Axis.Z ->
            {
                cornerOffset = translation!!.copy().translate(-1.0, -1.0, 0.01 * side.zOffset)
                cornerOffset.scale(3.0, 3.0, 1.0)
            }

            EnumFacing.Axis.Y ->
            {
                cornerOffset = translation!!.copy().translate(-1.0, 0.01 * side.yOffset, -1.0)
                cornerOffset.scale(3.0, 1.0, 3.0)
            }
        }
        if (hasBase && !isActive)
        {
            Textures.renderFace(renderState, cornerOffset, pipeline, side, Cuboid6.full,
                                baseBackgroundSprite, BlockRenderLayer.CUTOUT_MIPPED)
        }
        if (isActive)
        {
            Textures.renderFace(renderState, cornerOffset, pipeline, side, Cuboid6.full,
                                activeBladeSprite, BlockRenderLayer.CUTOUT_MIPPED)
        }
    }

}