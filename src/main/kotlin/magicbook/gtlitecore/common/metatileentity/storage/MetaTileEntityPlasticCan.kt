package magicbook.gtlitecore.common.metatileentity.storage

import codechicken.lib.colour.ColourRGBA
import codechicken.lib.render.CCRenderState
import codechicken.lib.render.pipeline.ColourMultiplier
import codechicken.lib.render.pipeline.IVertexOperation
import codechicken.lib.vec.Matrix4
import gregtech.api.capability.IPropertyFluidFilter
import gregtech.api.metatileentity.MetaTileEntity
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.unification.material.Material
import gregtech.api.util.GTUtility
import gregtech.client.renderer.texture.Textures
import gregtech.common.metatileentities.storage.MetaTileEntityDrum
import magicbook.gtlitecore.client.GTLiteTextures
import magicbook.gtlitecore.mixin.gregtech.MetaTileEntityDrumAccessor
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation
import org.apache.commons.lang3.ArrayUtils

@Suppress("MISSING_DEPENDENCY_CLASS")
class MetaTileEntityPlasticCan : MetaTileEntityDrum
{

    private val inner: MetaTileEntityDrumAccessor

    constructor(metaTileEntityId: ResourceLocation?, material: Material,
                tankSize: Int) : super(metaTileEntityId, material, tankSize)
    {
        this.inner = this as MetaTileEntityDrumAccessor
    }

    constructor(metaTileEntityId: ResourceLocation?, fluidFilter: IPropertyFluidFilter,
                color: Int, tankSize: Int) : super(metaTileEntityId, fluidFilter, false, color, tankSize)
    {
        this.inner = this as MetaTileEntityDrumAccessor
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity): MetaTileEntity
        = MetaTileEntityPlasticCan(this.metaTileEntityId, this.inner.fluidFilter,
                this.inner.color, this.inner.tankSize)


    override fun renderMetaTileEntity(renderState: CCRenderState,
                                      translation: Matrix4,
                                      pipeline: Array<IVertexOperation>)
    {
        val multiplier = ColourMultiplier(ColourRGBA.multiply(
                GTUtility.convertRGBtoOpaqueRGBA_CL(inner.color),
                GTUtility.convertRGBtoOpaqueRGBA_CL(this.paintingColorForRendering)))
        GTLiteTextures.PLASTIC_CAN.render(renderState, translation,
            ArrayUtils.add(pipeline, multiplier), this.getFrontFacing())
        GTLiteTextures.PLASTIC_CAN_OVERLAY.render(renderState, translation, pipeline)
        if (inner.isAutoOutput)
            Textures.STEAM_VENT_OVERLAY.renderSided(EnumFacing.DOWN, renderState, translation, pipeline)
    }

}
