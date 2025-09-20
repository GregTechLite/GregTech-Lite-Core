package gregtechlite.gtlitecore.common.metatileentity.part

import codechicken.lib.render.CCRenderState
import codechicken.lib.render.pipeline.IVertexOperation
import codechicken.lib.vec.Matrix4
import com.cleanroommc.modularui.api.drawable.IKey
import com.cleanroommc.modularui.factory.PosGuiData
import com.cleanroommc.modularui.screen.ModularPanel
import com.cleanroommc.modularui.utils.Alignment
import com.cleanroommc.modularui.utils.Color
import com.cleanroommc.modularui.value.sync.PanelSyncManager
import com.cleanroommc.modularui.value.sync.SyncHandlers
import com.cleanroommc.modularui.widgets.ItemSlot
import com.cleanroommc.modularui.widgets.RichTextWidget
import com.cleanroommc.modularui.widgets.SlotGroupWidget
import gregtech.api.capability.GregtechDataCodes
import gregtech.api.capability.impl.FilteredItemHandler
import gregtech.api.capability.impl.FluidTankList
import gregtech.api.capability.impl.NotifiableFluidTank
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.AbilityInstances
import gregtech.api.metatileentity.multiblock.IMultiblockAbilityPart
import gregtech.api.metatileentity.multiblock.MultiblockAbility
import gregtech.api.mui.GTGuiTextures
import gregtech.api.mui.GTGuis
import gregtech.api.recipes.RecipeMaps
import gregtech.api.recipes.properties.impl.DimensionProperty
import gregtech.api.unification.material.Materials
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityMultiblockNotifiablePart
import gregtech.common.mui.widget.GTFluidSlot
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteOverlays
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.network.PacketBuffer
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumParticleTypes
import net.minecraft.util.ResourceLocation
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.fluids.Fluid
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fluids.FluidTank
import net.minecraftforge.fluids.FluidUtil
import net.minecraftforge.fluids.IFluidTank
import net.minecraftforge.fluids.capability.CapabilityFluidHandler
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.items.IItemHandlerModifiable
import net.minecraftforge.items.ItemStackHandler
import java.util.*
import kotlin.math.cos
import kotlin.math.sin

class PartMachineAirIntakeHatch(id: ResourceLocation,
                                tier: Int,
                                private val capacity: Int,
                                private val transferRate: Int, )
    : MetaTileEntityMultiblockNotifiablePart(id, tier, false), IMultiblockAbilityPart<IFluidTank>
{

    private val rand: Random = Random()

    private var fluidTank: FluidTank = NotifiableFluidTank(capacity, this, false)
    private var airType: Fluid? = null

    private var isWorkingEnabled: Boolean = false

    init
    {
        initializeInventory()
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = PartMachineAirIntakeHatch(metaTileEntityId, tier, capacity, transferRate)

    override fun update()
    {
        super.update()
        if (isFirstTick && !world.isRemote)
        {
            RecipeMaps.GAS_COLLECTOR_RECIPES.recipeList.forEach { recipe ->
                if (!recipe.hasProperty(DimensionProperty.getInstance())) return

                val dimensions = recipe.getProperty(DimensionProperty.getInstance(), null)
                if (dimensions?.whiteListDimensions[0] == world.provider.dimension)
                {
                    airType = recipe.fluidOutputs[0].fluid
                }

                if (airType == null)
                    airType = Materials.Air.fluid
            }
        }

        val blockFacingPos = BlockPos(pos.x + frontFacing.xOffset,
                                      pos.y + frontFacing.yOffset,
                                      pos.z + frontFacing.zOffset)

        if (offsetTimer % (5 * TICK) == 0L && world.isAirBlock(blockFacingPos))
        {
            if (!world.isRemote)
            {
                val fillAmount = fluidTank.fill(FluidStack(airType, transferRate), true)
                if (fillAmount == 0 && isWorkingEnabled)
                {
                    isWorkingEnabled = false
                    writeCustomData(GregtechDataCodes.WORKING_ENABLED) { it.writeBoolean(isWorkingEnabled) }
                }
                else if (fillAmount > 0 && !isWorkingEnabled)
                {
                    isWorkingEnabled = true
                    writeCustomData(GregtechDataCodes.WORKING_ENABLED) { it.writeBoolean(isWorkingEnabled) }
                }
            }

            /**
             * Port from [GT++ in GT5u](https://github.com/GTNewHorizons/GT5-Unofficial)).
             */
            if (world.isRemote && isWorkingEnabled)
            {
                val particle = EnumParticleTypes.CLOUD

                val randX = rand.nextFloat()
                val randY = rand.nextFloat()
                val randZ = rand.nextFloat()

                val posX = pos.x + 0.25f + frontFacing.xOffset * 0.76f
                var posY = pos.y + 0.65f + frontFacing.yOffset * 0.76f
                val posZ = pos.z + 0.25f + frontFacing.zOffset * 0.76f

                var spdX: Float = frontFacing.yOffset * 0.1f + 0.2f + 0.1f * rand.nextFloat()
                val spdY: Float
                val spdZ: Float

                if (frontFacing.yOffset == -1)
                {
                    val pi2 = rand.nextFloat() * 2.0f * Math.PI
                    spdY = sin(pi2).toFloat() * 0.1f
                    spdZ = cos(pi2).toFloat() * 0.1f
                    spdX = -spdX
                    posY = posY - 0.8f
                }
                else
                {
                    spdY = -(frontFacing.xOffset * (0.1f + 0.2f * rand.nextFloat()))
                    spdZ = -(frontFacing.zOffset * (0.1f + 0.2f * rand.nextFloat()))
                }

                world.spawnParticle(particle,
                                    (posX + randX * 0.5f).toDouble(),
                                    (posY + rand.nextFloat() * 0.5f).toDouble(),
                                    (posZ + rand.nextFloat() * 0.5f).toDouble(),
                                    spdY.toDouble(),
                                    -spdX.toDouble(),
                                    spdZ.toDouble())

                world.spawnParticle(particle,
                                    (posX + randY * 0.5f).toDouble(),
                                    (posY + rand.nextFloat() * 0.5f).toDouble(),
                                    (posZ + rand.nextFloat() * 0.5f).toDouble(),
                                    spdY.toDouble(),
                                    -spdX.toDouble(),
                                    spdZ.toDouble())
                world.spawnParticle(particle,
                                    (posX + randZ * 0.5f).toDouble(),
                                    (posY + rand.nextFloat() * 0.5f).toDouble(),
                                    (posZ + rand.nextFloat() * 0.5f).toDouble(),
                                    spdY.toDouble(),
                                    -spdX.toDouble(),
                                    spdZ.toDouble())
            }

        }

        fillContainerFromInternalTank(fluidTank)
    }

    override fun receiveCustomData(dataId: Int, buf: PacketBuffer)
    {
        super.receiveCustomData(dataId, buf)
        if (dataId == GregtechDataCodes.WORKING_ENABLED)
        {
            this.isWorkingEnabled = buf.readBoolean()
        }
    }

    override fun writeInitialSyncData(buf: PacketBuffer)
    {
        super.writeInitialSyncData(buf)
        buf.writeBoolean(isWorkingEnabled)
    }

    override fun receiveInitialSyncData(buf: PacketBuffer)
    {
        super.receiveInitialSyncData(buf)
        this.isWorkingEnabled = buf.readBoolean()
    }

    override fun getAbility(): MultiblockAbility<IFluidTank?>? = MultiblockAbility.IMPORT_FLUIDS

    override fun registerAbilities(abilities: AbilityInstances)
    {
        abilities.add(fluidTank)
    }

    @SideOnly(Side.CLIENT)
    override fun renderMetaTileEntity(renderState: CCRenderState?,
                                      translation: Matrix4?,
                                      pipeline: Array<IVertexOperation?>?)
    {
        super.renderMetaTileEntity(renderState, translation, pipeline)
        GTLiteOverlays.AIR_INTAKE_HATCH_OVERLAY.renderSided(frontFacing, renderState, translation, pipeline)
    }

    override fun <T : Any?> getCapability(capability: Capability<T>, side: EnumFacing?): T?
    {
        if (capability === CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
        {
            return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(fluidTank)
        }
        return super.getCapability<T?>(capability, side)
    }

    override fun createImportFluidHandler(): FluidTankList
    {
        return FluidTankList(false, fluidTank)
    }

    override fun createImportItemHandler(): IItemHandlerModifiable?
    {
        return FilteredItemHandler(this).setFillPredicate(
            FilteredItemHandler.getCapabilityFilter(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY))
    }

    override fun createExportItemHandler(): IItemHandlerModifiable = ItemStackHandler(1)

    @Suppress("UnstableApiUsage")
    override fun buildUI(guiData: PosGuiData, syncManager: PanelSyncManager): ModularPanel
    {
        syncManager.registerSlotGroup("item_inv", 2)

        val tankSyncHandler = GTFluidSlot.sync(this.fluidTank)
            .showAmountOnSlot(false)
            .accessibility(true, false)

        return GTGuis.createPanel(this, 176, 166)
            .child(IKey.lang(metaFullName).asWidget()
                       .pos(5, 5))
            .child(SlotGroupWidget.playerInventory()
                       .left(7).bottom(7))
            .child(GTGuiTextures.DISPLAY.asWidget()
                       .left(7).top(16)
                       .size(81, 55))
            .child(GTGuiTextures.TANK_ICON.asWidget()
                       .left(92).top(36)
                       .size(14, 15))
            .child(RichTextWidget()
                       .size(75, 47)
                       .pos(10, 20)
                       .textColor(Color.WHITE.main)
                       .alignment(Alignment.TopLeft)
                       .autoUpdate(true)
                       .textBuilder {
                           it.addLine(IKey.lang("gregtech.gui.fluid_amount"))
                           val name = tankSyncHandler.fluidLocalizedName
                           if (name == null) return@textBuilder

                           it.addLine(IKey.str(name))
                           it.addLine(IKey.str(tankSyncHandler.formattedFluidAmount))
                       })
            .child(GTFluidSlot()
                       .syncHandler(tankSyncHandler)
                       .pos(69, 52)
                       .disableBackground())
            .child(ItemSlot()
                       .slot(SyncHandlers.itemSlot(this.importItems, 0)
                                 .slotGroup("item_inv")
                                 .filter { FluidUtil.getFluidHandler(it) != null })
                       .background(GTGuiTextures.SLOT, GTGuiTextures.IN_SLOT_OVERLAY)
                       .pos(90, 16))
            .child(ItemSlot()
                       .slot(SyncHandlers.itemSlot(this.exportItems, 0)
                                 .slotGroup("item_inv")
                                 .accessibility(false, true))
                       .background(GTGuiTextures.SLOT, GTGuiTextures.OUT_SLOT_OVERLAY)
                       .pos(90, 53))
    }

    @Suppress("UnstableApiUsage")
    override fun usesMui2(): Boolean = true

    override fun addInformation(stack: ItemStack?,
                                player: World?,
                                tooltip: MutableList<String?>,
                                advanced: Boolean)
    {
        tooltip.add(I18n.format("gtlitecore.machine.air_intake_hatch.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.air_intake_hatch.tooltip.2", transferRate))
        tooltip.add(I18n.format("gregtech.universal.tooltip.fluid_storage_capacity", capacity))
        tooltip.add(I18n.format("gregtech.universal.enabled"))
    }

    override fun addToolUsages(stack: ItemStack?,
                               world: World?,
                               tooltip: MutableList<String?>,
                               advanced: Boolean)
    {
        tooltip.add(I18n.format("gregtech.tool_action.screwdriver.access_covers"))
        super.addToolUsages(stack, world, tooltip, advanced)
    }

}