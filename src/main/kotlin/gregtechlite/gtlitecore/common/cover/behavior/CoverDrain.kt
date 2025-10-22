package gregtechlite.gtlitecore.common.cover.behavior

import codechicken.lib.render.CCRenderState
import codechicken.lib.render.pipeline.IVertexOperation
import codechicken.lib.vec.Cuboid6
import codechicken.lib.vec.Matrix4
import gregtech.api.cover.CoverBase
import gregtech.api.cover.CoverDefinition
import gregtech.api.cover.CoverableView
import gregtech.api.unification.material.Materials
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteOverlays
import net.minecraft.init.Blocks
import net.minecraft.util.BlockRenderLayer
import net.minecraft.util.EnumFacing
import net.minecraft.util.ITickable
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fluids.capability.CapabilityFluidHandler

class CoverDrain(definition: CoverDefinition,
                 coverableView: CoverableView,
                 attachedSide: EnumFacing,
                 private var transferRate: Int) : CoverBase(definition, coverableView, attachedSide), ITickable
{

    private var waterStack: FluidStack? = null

    override fun canAttach(coverableView: CoverableView, attachedSide: EnumFacing): Boolean
    {
        return coverableView.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, attachedSide)
    }

    override fun renderCover(renderState: CCRenderState,
                             translation: Matrix4,
                             pipeline: Array<out IVertexOperation>?,
                             plateBox: Cuboid6,
                             renderLayer: BlockRenderLayer)
    {
        GTLiteOverlays.COVER_DRAIN.renderSided(attachedSide, plateBox, renderState, pipeline, translation)
    }

    override fun update()
    {
        if (world.isRemote || offsetTimer % SECOND != 0L) return

        val neighborBlock = world.getBlockState(pos.offset(attachedSide))

        if (tileEntityHere == null) return

        val fluidHandler = tileEntityHere?.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, attachedSide)
        if (fluidHandler == null)
            return

        if (waterStack == null)
        {
            if (neighborBlock == Blocks.WATER.defaultState || neighborBlock == Blocks.FLOWING_WATER.defaultState)
            {
                this.waterStack = FluidStack(Materials.Water.fluid, this.transferRate)
            }
        }

        if (waterStack != null)
        {
            fluidHandler.fill(this.waterStack!!.copy(), true)
        }
    }

}