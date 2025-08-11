package gregtechlite.gtlitecore.common.metatileentity.single

import codechicken.lib.raytracer.CuboidRayTraceResult
import codechicken.lib.render.CCRenderState
import codechicken.lib.render.pipeline.IVertexOperation
import codechicken.lib.vec.Matrix4
import gregtech.api.capability.impl.FluidTankList
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtechlite.gtlitecore.api.metatileentity.PseudoMultiMachineMetaTileEntity
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.SAP_COLLECTOR_RECIPES
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteTextures
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.collectorTankSizeFunction
import net.minecraft.client.resources.I18n
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.fluids.FluidTank
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.items.IItemHandlerModifiable
import net.minecraftforge.items.ItemStackHandler

class MachineSapCollector(metaTileEntityId: ResourceLocation?, tier: Int) : PseudoMultiMachineMetaTileEntity(metaTileEntityId, SAP_COLLECTOR_RECIPES, GTLiteTextures.SAP_COLLECTOR_OVERLAY, tier, true, collectorTankSizeFunction)
{

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity): PseudoMultiMachineMetaTileEntity = MachineSapCollector(metaTileEntityId, tier)

    override fun createImportItemHandler(): IItemHandlerModifiable = ItemStackHandler(1)

    override fun createExportItemHandler(): IItemHandlerModifiable = ItemStackHandler(1)

    override fun createExportFluidHandler(): FluidTankList = FluidTankList(false, FluidTank(16000))

    override fun addNotifiedInput(input: Any?)
    {
        super.addNotifiedInput(input)
        onNeighborChanged()
    }

    override fun isValidFrontFacing(facing: EnumFacing?): Boolean
    {
        return super.isValidFrontFacing(facing)
                && facing != outputFacingFluids.opposite && facing != outputFacingItems.opposite
    }

    @Suppress("Deprecation")
    override fun setFrontFacing(frontFacing: EnumFacing)
    {
        super.setFrontFacing(frontFacing)
        if (outputFacingFluids == frontFacing.opposite
            || outputFacingItems == frontFacing.opposite)
            outputFacing = frontFacing.rotateY()
    }

    @Suppress("deprecation")
    override fun onWrenchClick(playerIn: EntityPlayer, hand: EnumHand, facing: EnumFacing, hitResult: CuboidRayTraceResult): Boolean
    {
        if (!playerIn.isSneaking)
        {
            if (outputFacing == facing)
            {
                return false
            }
            else if (hasFrontFacing() && facing == getFrontFacing()
                || facing == getFrontFacing().opposite)
            {
                return false
            }
            else
            {
                if (!world.isRemote)
                    outputFacing = facing
                return true
            }
        }
        else
        {
            return super.onWrenchClick(playerIn, hand, facing, hitResult)
        }
    }

    @SideOnly(Side.CLIENT)
    override fun renderMetaTileEntity(renderState: CCRenderState?, translation: Matrix4?, pipeline: Array<IVertexOperation?>?)
    {
        super.renderMetaTileEntity(renderState, translation, pipeline)
        GTLiteTextures.SAP_COLLECTOR_OVERLAY.renderOrientedState(renderState, translation, pipeline,
            getFrontFacing(), isActive(), true)
    }

    override fun addInformation(stack: ItemStack?, player: World?, tooltip: MutableList<String?>, advanced: Boolean)
    {
        super.addInformation(stack, player, tooltip, advanced)
        tooltip[1] = I18n.format("gtlitecore.machine.sap_collector.sap_collection")
    }

    override fun getIsWeatherOrTerrainResistant() = true

}