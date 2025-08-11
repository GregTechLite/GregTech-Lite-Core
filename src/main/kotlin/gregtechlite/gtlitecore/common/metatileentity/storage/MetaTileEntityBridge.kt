package gregtechlite.gtlitecore.common.metatileentity.storage

import codechicken.lib.render.CCRenderState
import codechicken.lib.render.pipeline.IVertexOperation
import codechicken.lib.vec.Matrix4
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.unification.material.Material
import gregtech.client.renderer.ICubeRenderer
import gregtechlite.gtlitecore.api.metatileentity.MetaTileEntityDelegator
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation
import net.minecraftforge.common.capabilities.Capability

class MetaTileEntityBridge(metaTileEntityId: ResourceLocation,
                           capabilityFilter: (Capability<*>) -> Boolean,
                           private val renderer: ICubeRenderer,
                           baseColor: Int) : MetaTileEntityDelegator(metaTileEntityId, capabilityFilter, baseColor)
{

    constructor(metaTileEntityId: ResourceLocation,
                capabilityFilter: (Capability<*>) -> Boolean,
                renderer: ICubeRenderer,
                baseMaterial: Material) : this(metaTileEntityId, capabilityFilter, renderer, baseMaterial.materialRGB)

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MetaTileEntityBridge(metaTileEntityId, capabilityFilter, renderer, baseColor)

    override fun getDelegatingFacing(facing: EnumFacing?) = facing?.opposite

    override fun hasFrontFacing() = false

    override fun renderMetaTileEntity(renderState: CCRenderState?,
                                      translation: Matrix4?,
                                      pipeline: Array<IVertexOperation?>?)
    {
        super.renderMetaTileEntity(renderState, translation, pipeline)
        renderer.render(renderState, translation, pipeline)
    }

}
