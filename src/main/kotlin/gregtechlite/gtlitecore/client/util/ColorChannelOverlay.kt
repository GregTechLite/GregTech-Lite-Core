package gregtechlite.gtlitecore.client.util

import codechicken.lib.render.CCRenderState
import codechicken.lib.render.pipeline.ColourMultiplier
import codechicken.lib.render.pipeline.IVertexOperation
import codechicken.lib.vec.Matrix4
import gregtech.api.metatileentity.MetaTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockAbilityPart
import gregtech.api.metatileentity.multiblock.MultiblockAbility
import gregtech.api.util.GTUtility
import gregtech.client.renderer.texture.Textures
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

@SideOnly(Side.CLIENT)
fun MetaTileEntity.isColorChannelPart(): Boolean
    = this is IMultiblockAbilityPart<*> && (abilities.contains(MultiblockAbility.IMPORT_ITEMS) || abilities.contains(MultiblockAbility.IMPORT_FLUIDS))

@SideOnly(Side.CLIENT)
fun MetaTileEntity.renderColorChannelOverlay(renderState: CCRenderState?, translation: Matrix4?,
                                             pipeline: Array<out IVertexOperation?>?)
{
    if (!isPainted || !isColorChannelPart()) return

    val source = pipeline
    val tinted = arrayOfNulls<IVertexOperation>(if (source == null) 1 else source.size + 1)
    if (source != null)
    {
        for (index in source.indices)
            tinted[index] = source[index]
    }
    tinted[tinted.size - 1] = ColourMultiplier(GTUtility.convertRGBtoOpaqueRGBA_CL(paintingColorForRendering))

    Textures.PIPE_IN_OVERLAY.renderSided(frontFacing, renderState, translation, tinted)
}
