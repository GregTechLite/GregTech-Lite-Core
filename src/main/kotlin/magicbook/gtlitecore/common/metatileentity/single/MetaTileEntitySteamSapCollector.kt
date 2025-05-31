package magicbook.gtlitecore.common.metatileentity.single

import codechicken.lib.raytracer.CuboidRayTraceResult
import codechicken.lib.render.CCRenderState
import codechicken.lib.render.pipeline.IVertexOperation
import codechicken.lib.vec.Matrix4
import gregtech.api.capability.impl.FluidTankList
import gregtech.api.capability.impl.NotifiableItemStackHandler
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import magicbook.gtlitecore.api.gui.indicator.SteamProgressBarIndicators
import magicbook.gtlitecore.api.metatileentity.PseudoMultiSteamMachineMetaTileEntity
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.SAP_COLLECTOR_RECIPES
import magicbook.gtlitecore.client.renderer.texture.GTLiteTextures
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

class MetaTileEntitySteamSapCollector(metaTileEntityId: ResourceLocation?,
                                      isHighPressure: Boolean) : PseudoMultiSteamMachineMetaTileEntity(metaTileEntityId, SAP_COLLECTOR_RECIPES, SteamProgressBarIndicators.EXTRACTION, GTLiteTextures.SAP_COLLECTOR_OVERLAY, false, isHighPressure)
{
    private val sapCollectionAmount: Long = if (isHighPressure) 6L else 3L

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity): PseudoMultiSteamMachineMetaTileEntity = MetaTileEntitySteamSapCollector(metaTileEntityId, isHighPressure)

    override fun createImportItemHandler(): IItemHandlerModifiable = NotifiableItemStackHandler(this, 1, this, false)

    override fun createExportItemHandler(): IItemHandlerModifiable = NotifiableItemStackHandler(this, 1, this, true)

    override fun createExportFluidHandler(): FluidTankList = FluidTankList(false, FluidTank(16_000))

    override fun addNotifiedInput(input: Any?)
    {
        super.addNotifiedInput(input)
        onNeighborChanged()
    }

    @SideOnly(Side.CLIENT)
    override fun renderMetaTileEntity(renderState: CCRenderState?, translation: Matrix4?, pipeline: Array<IVertexOperation?>?)
    {
        super.renderMetaTileEntity(renderState, translation, pipeline)
        GTLiteTextures.SAP_COLLECTOR_OVERLAY.renderOrientedState(renderState, translation, pipeline,
            getFrontFacing(), isActive(), true)
    }

    override fun isValidFrontFacing(facing: EnumFacing?): Boolean
    {
        return super.isValidFrontFacing(facing)
                && facing != workableHandler.ventingSide
                && facing != workableHandler.ventingSide.opposite
    }

    override fun setFrontFacing(frontFacing: EnumFacing)
    {
        super.setFrontFacing(frontFacing)
        if (workableHandler.ventingSide == frontFacing
            || workableHandler.ventingSide == frontFacing.opposite)
        {
            workableHandler.ventingSide = frontFacing.rotateY()
        }
    }

    override fun onWrenchClick(playerIn: EntityPlayer, hand: EnumHand, facing: EnumFacing, hitResult: CuboidRayTraceResult): Boolean
    {
        if (!playerIn.isSneaking)
        {
            if (workableHandler.ventingSide == facing)
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
                    workableHandler.ventingSide = facing
                return true
            }
        }
        else
        {
            return super.onWrenchClick(playerIn, hand, facing, hitResult)
        }
    }

    override fun addInformation(stack: ItemStack?, world: World?, tooltip: MutableList<String?>, advanced: Boolean)
    {
        super.addInformation(stack, world, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.machine.sap_collector.sap_collection"))
        tooltip.add(I18n.format("gtlitecore.machine.sap_collector.sap_collection_amount", sapCollectionAmount))
    }

}