package gregtechlite.gtlitecore.common.metatileentity.part

import codechicken.lib.raytracer.CuboidRayTraceResult
import codechicken.lib.render.CCRenderState
import codechicken.lib.render.pipeline.IVertexOperation
import codechicken.lib.vec.Matrix4
import com.cleanroommc.modularui.api.drawable.IKey
import com.cleanroommc.modularui.api.widget.IWidget
import com.cleanroommc.modularui.factory.PosGuiData
import com.cleanroommc.modularui.screen.ModularPanel
import com.cleanroommc.modularui.screen.UISettings
import com.cleanroommc.modularui.value.BoolValue
import com.cleanroommc.modularui.value.sync.BooleanSyncValue
import com.cleanroommc.modularui.value.sync.PanelSyncManager
import com.cleanroommc.modularui.value.sync.SyncHandlers
import com.cleanroommc.modularui.widget.Widget
import com.cleanroommc.modularui.widgets.SlotGroupWidget
import com.cleanroommc.modularui.widgets.ToggleButton
import com.cleanroommc.modularui.widgets.layout.Flow
import com.cleanroommc.modularui.widgets.layout.Grid
import com.cleanroommc.modularui.widgets.slot.ItemSlot
import gregtech.api.GTValues
import gregtech.api.capability.DualHandler
import gregtech.api.capability.GregtechDataCodes.TOGGLE_COLLAPSE_ITEMS
import gregtech.api.capability.GregtechDataCodes.WORKING_ENABLED
import gregtech.api.capability.IControllable
import gregtech.api.capability.IGhostSlotConfigurable
import gregtech.api.capability.impl.FluidTankList
import gregtech.api.capability.impl.GhostCircuitItemStackHandler
import gregtech.api.capability.impl.ItemHandlerList
import gregtech.api.capability.impl.NotifiableFluidTank
import gregtech.api.capability.impl.NotifiableItemStackHandler
import gregtech.api.items.itemhandlers.GTItemStackHandler
import gregtech.api.metatileentity.MetaTileEntity
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.AbilityInstances
import gregtech.api.metatileentity.multiblock.IMultiblockAbilityPart
import gregtech.api.metatileentity.multiblock.MultiblockAbility
import gregtech.api.mui.GTGuiTextures
import gregtech.api.mui.GTGuis
import gregtech.api.mui.widget.GhostCircuitSlotWidget
import gregtech.client.renderer.texture.Textures
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityMultiblockNotifiablePart
import gregtech.common.mui.widget.GTFluidSlot
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.collapseInventorySlotContents
import gregtechlite.gtlitecore.api.extension.square
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteOverlays
import gregtechlite.gtlitecore.mixins.hooks.Implemented
import net.minecraft.client.resources.I18n
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.network.PacketBuffer
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.ResourceLocation
import net.minecraft.util.text.TextComponentTranslation
import net.minecraft.world.World
import net.minecraftforge.fluids.IFluidTank
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.items.IItemHandlerModifiable
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sqrt

// TODO: Remove it when we replace CEu to our port version.
@Implemented(at = ["https://github.com/GregTechCEu/GregTech/pull/2769"])
class PartMachineDualHatch(id: ResourceLocation, tier: Int, isExportHatch: Boolean)
    : MetaTileEntityMultiblockNotifiablePart(id, tier, isExportHatch), IMultiblockAbilityPart<IItemHandlerModifiable>,
      IControllable, IGhostSlotConfigurable
{
    private var circuitInventory: GhostCircuitItemStackHandler? = null
    private var actualImportItems: IItemHandlerModifiable? = null
    private var dualHandler: DualHandler? = null
    
    private var workingEnabled = true
    private var autoCollapse = false

    private val itemSize: Int
        get() = (1 + min(GTValues.UHV, tier)).square()

    private val tankSize: Int
        get() = 8000 * min(Int.MAX_VALUE, 1 shl tier)

    init
    {
        initializeInventory()
    }
    
    override fun createMetaTileEntity(te: IGregTechTileEntity): MetaTileEntity
        = PartMachineDualHatch(metaTileEntityId, tier, isExportHatch)

    override fun initializeInventory()
    {
        super.initializeInventory()
        if (hasGhostCircuitInventory())
        {
            circuitInventory = GhostCircuitItemStackHandler(this)
            actualImportItems = ItemHandlerList(listOf(importItems, circuitInventory))
        }
        else
        {
            actualImportItems = importItems
        }
        dualHandler = DualHandler(if (isExportHatch) exportItems!! else actualImportItems!!,
                                  if (isExportHatch) exportFluids else importFluids, isExportHatch)
    }
    
    override fun getImportItems(): IItemHandlerModifiable = dualHandler!!

    private fun createTanks(): Array<IFluidTank?>
    {
        val size = 1 + min(GTValues.UHV, tier)
        val tanks = arrayOfNulls<IFluidTank>(size)
        for (index in tanks.indices)
        {
            tanks[index] = NotifiableFluidTank(tankSize, null, isExportHatch)
        }
        return tanks
    }

    override fun createImportItemHandler(): IItemHandlerModifiable
        = if (isExportHatch) GTItemStackHandler(this, 0) else NotifiableItemStackHandler(this, itemSize, null, false)
    
    override fun createExportItemHandler(): IItemHandlerModifiable
        = if (isExportHatch) NotifiableItemStackHandler(this, itemSize, null, true) else GTItemStackHandler(this, 0)
    
    override fun createImportFluidHandler(): FluidTankList
        = if (isExportHatch) FluidTankList(false) else FluidTankList(false, *createTanks())
    
    override fun createExportFluidHandler(): FluidTankList
        = if (isExportHatch) FluidTankList(false, *createTanks()) else FluidTankList(false)
    
    override fun update()
    {
        super.update()

        if (!world.isRemote && offsetTimer % (5 * TICK) == 0L)
        {
            if (workingEnabled)
            {
                if (isExportHatch)
                {
                    pushItemsIntoNearbyHandlers(frontFacing)
                    pushFluidsIntoNearbyHandlers(frontFacing)
                }
                else
                {
                    pullItemsFromNearbyHandlers(frontFacing)
                    pullFluidsFromNearbyHandlers(frontFacing)
                }
            }
            
            if (autoCollapse())
            {
                val itemHandler = if (isExportHatch) exportItems else super<MetaTileEntityMultiblockNotifiablePart>.importItems
                if (!isAttachedToMultiBlock || (if (isExportHatch) notifiedItemInputList.contains(itemHandler)
                    else notifiedItemInputList.contains(itemHandler)))
                {
                    itemHandler.collapseInventorySlotContents()
                }
            }
        }
    }
    
    override fun hasGhostCircuitInventory(): Boolean = !isExportHatch
    
    override fun setGhostCircuitConfig(config: Int)
    {
        if (circuitInventory == null || circuitInventory!!.circuitValue == config)
            return
        circuitInventory!!.circuitValue = config

        if (!world.isRemote) markDirty()
    }

    override fun getAbilities(): List<MultiblockAbility<*>>
        = if (isExportHatch) listOf(MultiblockAbility.EXPORT_ITEMS, MultiblockAbility.EXPORT_FLUIDS)
          else listOf(MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.IMPORT_FLUIDS)
    
    override fun registerAbilities(abilityInstances: AbilityInstances)
    {
        abilityInstances.add(dualHandler)
    }

    @Suppress("UnstableApiUsage")
    override fun usesMui2(): Boolean = true

    @Suppress("UnstableApiUsage")
    override fun buildUI(guiData: PosGuiData, syncManager: PanelSyncManager, settings: UISettings): ModularPanel
    {
        val rowSize = sqrt(itemSize.toDouble()).toInt()
        syncManager.registerSlotGroup("item_inv", rowSize)
        
        val backgroundWidth = max(199, rowSize * 18 + 32) // (Player Inv Width, Bus Inv Width)
        val backgroundHeight = 112 + 18 * rowSize
        
        val widgets = arrayListOf<MutableList<IWidget>>()
        for (i in 0 ..< rowSize)
        {
            widgets.add(arrayListOf())
            for (j in 0 ..< rowSize)
            {
                val idx = i * rowSize + j
                val handler = if (isExportHatch) exportItems else importItems
                widgets[i].add(ItemSlot()
                        .slot(SyncHandlers.itemSlot(handler, idx)
                                  .slotGroup("item_inv")
                                  .changeListener { newItem, onlyAmountChanged, client, init ->
                                      if (onlyAmountChanged && handler is GTItemStackHandler)
                                          handler.onContentsChanged(idx)
                                  }
                                  .accessibility(!isExportHatch, true)))
            }
            
            val tankHandler = dualHandler!!.getTankAt(i)
            widgets[i].add(GTFluidSlot()
                .syncHandler(GTFluidSlot.sync(tankHandler)
                        .accessibility(true, !isExportHatch)))
        }
        
        val workingStateValue = BooleanSyncValue({ workingEnabled },
                                                 { workingStatus -> workingEnabled = workingStatus })

        val collapseStateValue = BooleanSyncValue({ autoCollapse },
                                                  { collapse -> autoCollapse = collapse })

        syncManager.syncValue("working_state", workingStateValue)
        syncManager.syncValue("collapse_state", collapseStateValue)
        
        val hasGhostCircuit = hasGhostCircuitInventory() && circuitInventory != null
        
        return GTGuis.createPanel(this, backgroundWidth, backgroundHeight)
            .child(IKey.lang(metaFullName).asWidget()
                       .pos(5, 5))
            .child(SlotGroupWidget.playerInventory(false)
                       .left(7)
                       .bottom(7))
            .child(Grid()
                       .top(18)
                       .height(rowSize * 18)
                       .minElementMargin(0, 0)
                       .minColWidth(18)
                       .minRowHeight(18)
                       .alignX(0.5f)
                       .matrix(widgets))
            .child(Flow.column()
                       .pos(backgroundWidth - 7 - 18, backgroundHeight - 18 * 4 - 7 - 5)
                       .width(18)
                       .height(18 * 4 + 5)
                       .child(GTGuiTextures.getLogo(uiTheme).asWidget().size(17).top(18 * 3 + 5))
        .child(ToggleButton()
                   .top(18 * 2)
                   .value(BoolValue.Dynamic({ workingStateValue.boolValue },
                                            { workingStatus -> workingStateValue.boolValue = workingStatus }))
                   .overlay(GTGuiTextures.BUTTON_ITEM_OUTPUT)
                   .tooltipBuilder {
                       it.isAutoUpdate = true
                       if (isExportHatch)
                       {
                           if (workingStateValue.boolValue)
                               it.addLine(IKey.lang("gregtech.gui.item_auto_output.tooltip.enabled"))
                           else
                               it.addLine(IKey.lang("gregtech.gui.item_auto_output.tooltip.disabled"))
                       }
                       else
                       {
                           if (workingStateValue.boolValue)
                           {
                               it.addLine(IKey.lang("gregtech.gui.item_auto_input.tooltip.enabled"))
                           }
                           else
                           {
                               it.addLine(IKey.lang("gregtech.gui.item_auto_input.tooltip.disabled"))
                           }
                       }
                   })
        .child(ToggleButton()
                   .top(18)
                   .value(BoolValue.Dynamic({ collapseStateValue.boolValue },
                                            { collapseStatus -> collapseStateValue.boolValue = collapseStatus }))
                   .overlay(GTGuiTextures.BUTTON_AUTO_COLLAPSE)
                   .tooltipBuilder {
                       it.isAutoUpdate = true
                       if (collapseStateValue.boolValue)
                       {
                           it.addLine(IKey.lang("gregtech.gui.item_auto_collapse.tooltip.enabled"))
                       }
                       else
                       {
                           it.addLine(IKey.lang("gregtech.gui.item_auto_collapse.tooltip.disabled"))
                       }
                   })

        .childIf(hasGhostCircuit, GhostCircuitSlotWidget()
            .slot(circuitInventory, 0)
            .background(GTGuiTextures.SLOT, GTGuiTextures.INT_CIRCUIT_OVERLAY))
        .childIf(!hasGhostCircuit, Widget()
            .background(GTGuiTextures.SLOT, GTGuiTextures.BUTTON_X)
            .tooltip {
                it.addLine(IKey.lang("gregtech.gui.configurator_slot.unavailable.tooltip"))
            }))
    }

    @SideOnly(Side.CLIENT)
    override fun renderMetaTileEntity(renderState: CCRenderState?, translation: Matrix4?,
                                      pipeline: Array<IVertexOperation?>?)
    {
        super.renderMetaTileEntity(renderState, translation, pipeline)
        val renderer = if (isExportHatch) Textures.PIPE_OUT_OVERLAY else Textures.PIPE_IN_OVERLAY
        renderer.renderSided(frontFacing, renderState, translation, pipeline)
        val overlay = if (isExportHatch) GTLiteOverlays.DUAL_HATCH_OUTPUT_OVERLAY else GTLiteOverlays.DUAL_HATCH_INPUT_OVERLAY
        overlay.renderSided(frontFacing, renderState, translation, pipeline)
    }
    
    override fun writeInitialSyncData(buf: PacketBuffer)
    {
        super.writeInitialSyncData(buf)
        buf.writeBoolean(workingEnabled)
        buf.writeBoolean(autoCollapse)
    }
    
    override fun receiveInitialSyncData(buf: PacketBuffer)
    {
        super.receiveInitialSyncData(buf)
        workingEnabled = buf.readBoolean()
        autoCollapse = buf.readBoolean()
    }
    
    override fun setWorkingEnabled(workingEnabled: Boolean)
    {
        this.workingEnabled = workingEnabled
        if (world != null && !world.isRemote)
        {
            writeCustomData(WORKING_ENABLED) { it.writeBoolean(this.workingEnabled) }
        }
    }
    
    override fun isWorkingEnabled(): Boolean = workingEnabled
    
    fun setAutoCollapse(inverted: Boolean)
    {
        autoCollapse = inverted
        if (!world.isRemote)
        {
            if (autoCollapse)
            {
                if (isExportHatch)
                {
                    addNotifiedOutput(exportItems)
                }
                else
                {
                    addNotifiedInput(importItems)
                }
            }
            writeCustomData(TOGGLE_COLLAPSE_ITEMS) { it.writeBoolean(autoCollapse) }
            notifyBlockUpdate()
            markDirty()
        }
    }
    
    fun autoCollapse(): Boolean = autoCollapse
    
    override fun receiveCustomData(dataId: Int, buf: PacketBuffer)
    {
        super.receiveCustomData(dataId, buf)
        when (dataId)
        {
            WORKING_ENABLED       -> workingEnabled = buf.readBoolean()
            TOGGLE_COLLAPSE_ITEMS -> autoCollapse = buf.readBoolean()
        }
    }
    
    override fun onScrewdriverClick(playerIn: EntityPlayer, hand: EnumHand?, facing: EnumFacing?,
                                    hitResult: CuboidRayTraceResult?): Boolean
    {
        setAutoCollapse(!autoCollapse)
        if (!world.isRemote)
        {
            if (autoCollapse)
            {
                playerIn.sendStatusMessage(TextComponentTranslation("gregtech.bus.collapse_true"), true)
            }
            else
            {
                playerIn.sendStatusMessage(TextComponentTranslation("gregtech.bus.collapse_false"), true)
            }
        }
        return true
    }
    
    override fun writeToNBT(data: NBTTagCompound): NBTTagCompound
    {
        super.writeToNBT(data)
        data.setBoolean("workingEnabled", workingEnabled)
        data.setBoolean("autoCollapse", autoCollapse)
        circuitInventory?.write(data)
        return data
    }
    
    override fun readFromNBT(data: NBTTagCompound)
    {
        super.readFromNBT(data)
        workingEnabled = data.getBoolean("workingEnabled")
        autoCollapse = data.getBoolean("autoCollapse")
        circuitInventory?.read(data)
    }

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, player: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        if (isExportHatch)
            tooltip.add(I18n.format("gregtech.machine.item_bus.export.tooltip"))
        else
            tooltip.add(I18n.format("gregtech.machine.item_bus.import.tooltip"))
        tooltip.add(I18n.format("gregtech.universal.tooltip.item_storage_capacity", itemSize))
        tooltip.add(I18n.format("gregtech.universal.tooltip.fluid_storage_capacity", tankSize))
        tooltip.add(I18n.format("gregtech.universal.enabled"))
    }

    @SideOnly(Side.CLIENT)
    override fun addToolUsages(stack: ItemStack, world: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        tooltip.add(I18n.format("gregtech.tool_action.screwdriver.access_covers"))
        tooltip.add(I18n.format("gregtech.tool_action.screwdriver.auto_collapse"))
        tooltip.add(I18n.format("gregtech.tool_action.wrench.set_facing"))
        super.addToolUsages(stack, world, tooltip, advanced)
    }
}