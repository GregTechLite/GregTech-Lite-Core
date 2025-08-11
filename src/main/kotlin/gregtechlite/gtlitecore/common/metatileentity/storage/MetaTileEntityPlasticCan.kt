package gregtechlite.gtlitecore.common.metatileentity.storage

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
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteTextures
import gregtechlite.gtlitecore.mixins.gregtech.AccessorMetaTileEntityDrum
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation
import org.apache.commons.lang3.ArrayUtils

class MetaTileEntityPlasticCan : MetaTileEntityDrum
{

    private val inner: AccessorMetaTileEntityDrum

    constructor(metaTileEntityId: ResourceLocation?, material: Material,
                tankSize: Int) : super(metaTileEntityId, material, tankSize)
    {
        inner = this as AccessorMetaTileEntityDrum
    }

    constructor(
        metaTileEntityId: ResourceLocation?, fluidFilter: IPropertyFluidFilter<*>?,
        color: Int, tankSize: Int
    ) : super(metaTileEntityId, fluidFilter!!, false, color, tankSize)
    {
        inner = this as AccessorMetaTileEntityDrum
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity): MetaTileEntity
        = MetaTileEntityPlasticCan(metaTileEntityId, inner.fluidFilter,
                inner.color, inner.tankSize)

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
