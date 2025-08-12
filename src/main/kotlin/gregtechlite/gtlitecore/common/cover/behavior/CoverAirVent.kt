package gregtechlite.gtlitecore.common.cover.behavior

import codechicken.lib.render.CCRenderState
import codechicken.lib.render.pipeline.IVertexOperation
import codechicken.lib.vec.Cuboid6
import codechicken.lib.vec.Matrix4
import gregtech.api.cover.CoverBase
import gregtech.api.cover.CoverDefinition
import gregtech.api.cover.CoverableView
import gregtech.api.recipes.RecipeMaps
import gregtech.api.recipes.properties.impl.DimensionProperty
import gregtech.client.renderer.texture.Textures
import gregtechlite.gtlitecore.api.SECOND
import net.minecraft.util.BlockRenderLayer
import net.minecraft.util.EnumFacing
import net.minecraft.util.ITickable
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fluids.capability.CapabilityFluidHandler

class CoverAirVent(definition: CoverDefinition,
                   coverableView: CoverableView,
                   attachedSide: EnumFacing,
                   private var transferRate: Int) : CoverBase(definition, coverableView, attachedSide), ITickable
{

    private var airType: FluidStack? = null

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
        Textures.AIR_VENT_OVERLAY.renderSided(attachedSide, plateBox, renderState, pipeline, translation)
    }

    override fun update()
    {
        if (world.isRemote || offsetTimer % SECOND != 0L) return

        // Obstructed block in neighbor is not allowed, otherwise stop updating.
        if (world.getBlockState(pos.offset(attachedSide)).isFullBlock) return

        if (tileEntityHere == null) return

        val fluidHandler = tileEntityHere?.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, attachedSide)
        if (fluidHandler == null)
            return

        if (airType == null)
        {
            RecipeMaps.GAS_COLLECTOR_RECIPES.recipeList
                .firstOrNull { recipe ->
                    // We check whitelist dimensions only, so if player in blacklisted dimension, then skipped.
                    val dimensions = recipe.getProperty(DimensionProperty.getInstance(), null)
                    if (dimensions == null) return@firstOrNull false

                    dimensions.whiteListDimensions?.any { world.provider.dimension == it } ?: false
                }
                ?.fluidOutputs
                ?.firstOrNull()
                ?.let {
                    this.airType = FluidStack(it.fluid, this.transferRate)
                }
        }

        fluidHandler.fill(this.airType!!.copy(), true)

    }

}