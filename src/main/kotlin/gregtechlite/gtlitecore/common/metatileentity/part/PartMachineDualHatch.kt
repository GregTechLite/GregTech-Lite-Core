package gregtechlite.gtlitecore.common.metatileentity.part

import codechicken.lib.raytracer.CuboidRayTraceResult
import codechicken.lib.render.CCRenderState
import codechicken.lib.render.pipeline.IVertexOperation
import codechicken.lib.vec.Matrix4
import com.cleanroommc.modularui.api.drawable.IKey
import com.cleanroommc.modularui.api.widget.IWidget
import com.cleanroommc.modularui.factory.PosGuiData
import com.cleanroommc.modularui.screen.ModularPanel
import com.cleanroommc.modularui.value.BoolValue
import com.cleanroommc.modularui.value.sync.BooleanSyncValue
import com.cleanroommc.modularui.value.sync.PanelSyncManager
import com.cleanroommc.modularui.value.sync.SyncHandlers
import com.cleanroommc.modularui.widget.Widget
import com.cleanroommc.modularui.widgets.ItemSlot
import com.cleanroommc.modularui.widgets.SlotGroupWidget
import com.cleanroommc.modularui.widgets.ToggleButton
import com.cleanroommc.modularui.widgets.layout.Flow
import com.cleanroommc.modularui.widgets.layout.Grid
import gregtech.api.GTValues
import gregtech.api.capability.DualHandler
import gregtech.api.capability.GregtechDataCodes
import gregtech.api.capability.IControllable
import gregtech.api.capability.IGhostSlotConfigurable
import gregtech.api.capability.impl.FluidTankList
import gregtech.api.capability.impl.GhostCircuitItemStackHandler
import gregtech.api.capability.impl.ItemHandlerList
import gregtech.api.capability.impl.NotifiableFluidTank
import gregtech.api.capability.impl.NotifiableItemStackHandler
import gregtech.api.items.itemhandlers.GTItemStackHandler
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.AbilityInstances
import gregtech.api.metatileentity.multiblock.IMultiblockAbilityPart
import gregtech.api.metatileentity.multiblock.MultiblockAbility
import gregtech.api.mui.GTGuiTextures
import gregtech.api.mui.GTGuis
import gregtech.api.mui.widget.GhostCircuitSlotWidget
import gregtech.api.util.GTHashMaps
import gregtech.client.renderer.texture.Textures
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityMultiblockNotifiablePart
import gregtech.common.mui.widget.GTFluidSlot
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.copy
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteTextures
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
import net.minecraftforge.items.IItemHandlerModifiable
import java.util.*
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sqrt

// TODO Remove it when GTCEu PR#2769 merged.
class PartMachineDualHatch(id: ResourceLocation,
                           tier: Int,
                           isExportHatch: Boolean)
    : MetaTileEntityMultiblockNotifiablePart(id, tier, isExportHatch),
      IMultiblockAbilityPart<IItemHandlerModifiable>, IControllable, IGhostSlotConfigurable
{

    private var circuitInventory: GhostCircuitItemStackHandler? = null
    private var actualImportItems: IItemHandlerModifiable? = null
    private var dualHandler: DualHandler? = null
    
    private var workingEnabled = true
    private var autoCollapse = false
    
    init
    {
        initializeInventory()
    }
    
    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity?) = PartMachineDualHatch(metaTileEntityId, tier, isExportHatch)

    override fun initializeInventory()
    {
        super.initializeInventory()
        if (hasGhostCircuitInventory())
        {
            circuitInventory = GhostCircuitItemStackHandler(this)
            actualImportItems = ItemHandlerList(listOf(this.importItems, circuitInventory))
        }
        else
        {
            actualImportItems = this.importItems
        }
        dualHandler = DualHandler(if (isExportHatch) this.exportItems!! else this.actualImportItems!!,
                                  if (isExportHatch) getExportFluids() else getImportFluids(), isExportHatch)
    }
    
    override fun getImportItems(): IItemHandlerModifiable = dualHandler!!

    // TODO Should max value of all sizeRoots should be MAX or still UHV?

    private fun getItemSize(): Int
    {
        val sizeRoot = 1 + min(GTValues.UHV, tier)
        return sizeRoot * sizeRoot
    }

    private fun getTankSize(): Int
    {
        return 8000 * min(Int.MAX_VALUE, 1 shl tier)
    }

    private fun createTanks(): Array<IFluidTank?>
    {
        val size = 1 + min(GTValues.UHV, tier)
        val tanks = arrayOfNulls<IFluidTank>(size)
        for (index in tanks.indices)
        {
            tanks[index] = NotifiableFluidTank(getTankSize(), null, isExportHatch)
        }
        return tanks
    }

     override fun createImportItemHandler(): IItemHandlerModifiable
     {
        return if (isExportHatch)
            GTItemStackHandler(this, 0)
        else
            NotifiableItemStackHandler(this, getItemSize(), null, false)
    }
    
    override fun createExportItemHandler(): IItemHandlerModifiable
    {
        return if (isExportHatch)
            NotifiableItemStackHandler(this, getItemSize(), null, true)
        else
            GTItemStackHandler(this, 0)
    }
    
    override fun createImportFluidHandler(): FluidTankList
    {
        return if (isExportHatch)
            FluidTankList(false)
        else
            FluidTankList(false, *createTanks())
    }
    
    override fun createExportFluidHandler(): FluidTankList
    {
        return if (isExportHatch)
            FluidTankList(false, *createTanks())
        else
            FluidTankList(false)
    }
    
    override fun update()
    {
        super.update()
        
        if (!world.isRemote && offsetTimer % (5 * TICK) == 0L)
        {
            if (workingEnabled)
            {
                if (isExportHatch)
                {
                    pushItemsIntoNearbyHandlers(getFrontFacing())
                    pushFluidsIntoNearbyHandlers(getFrontFacing())
                }
                else
                {
                    pullItemsFromNearbyHandlers(getFrontFacing())
                    pullFluidsFromNearbyHandlers(getFrontFacing())
                }
            }
            
            if (autoCollapse())
            {
                val itemHandler = if (isExportHatch) getExportItems() else super.getImportItems()
                if (!isAttachedToMultiBlock
                    || (if (isExportHatch) getNotifiedItemOutputList().contains(itemHandler)
                        else getNotifiedItemInputList().contains(itemHandler)))
                {
                    collapseInventorySlotContents(itemHandler)
                }
            }
        }
    }
    
    override fun hasGhostCircuitInventory(): Boolean = !this.isExportHatch
    
    override fun setGhostCircuitConfig(config: Int)
    {
        if (this.circuitInventory == null
            || this.circuitInventory!!.circuitValue == config)
            return 

        this.circuitInventory!!.circuitValue = config
        if (!world.isRemote)
            markDirty()
    }
    
    override fun getAbility(): MultiblockAbility<IItemHandlerModifiable?>?
    {
        return if (isExportHatch) MultiblockAbility.EXPORT_ITEMS else MultiblockAbility.IMPORT_ITEMS
    }
    
    override fun registerAbilities(abilityInstances: AbilityInstances)
    {
        abilityInstances.add(dualHandler)
    }

    @Suppress("UnstableApiUsage")
    override fun usesMui2(): Boolean = true

    @Suppress("UnstableApiUsage")
    override fun buildUI(guiData: PosGuiData, syncManager: PanelSyncManager): ModularPanel
    {
        val rowSize = sqrt(getItemSize().toDouble()).toInt()
        syncManager.registerSlotGroup("item_inv", rowSize)
        
        val backgroundWidth = max(9 * 18 + 18 + 14 + 5,  // Player Inv width
                                         rowSize * 18 + 14 + 18) // Bus Inv width
        val backgroundHeight = 18 + 18 * rowSize + 94
        
        val widgets: MutableList<MutableList<IWidget>> = ArrayList<MutableList<IWidget>>()
        for (i in 0 ..< rowSize)
        {
            widgets.add(ArrayList<IWidget>())
            for (j in 0 ..< rowSize)
            {
                val index = i * rowSize + j
                val handler = if (isExportHatch) getExportItems() else getImportItems()
                widgets[i].add(ItemSlot()
                        .slot(SyncHandlers.itemSlot(handler, index)
                                  .slotGroup("item_inv")
                                  .changeListener { newItem: ItemStack?, onlyAmountChanged: Boolean, client: Boolean, init: Boolean ->
                                      if (onlyAmountChanged && handler is GTItemStackHandler)
                                      {
                                          handler.onContentsChanged(index)
                                      }
                                  }
                                  .accessibility(!isExportHatch, true)))
            }
            
            val tankHandler: IFluidTank = dualHandler!!.getTankAt(i)
            widgets[i].add(GTFluidSlot()
                .syncHandler(GTFluidSlot.sync(tankHandler)
                        .accessibility(true, !isExportHatch)))
        }
        
        val workingStateValue = BooleanSyncValue({ workingEnabled },
                                                 { workingStatus: Boolean -> workingEnabled = workingStatus })

        val collapseStateValue = BooleanSyncValue({ autoCollapse },
                                                  { collapse: Boolean -> autoCollapse = collapse })

        syncManager.syncValue("working_state", workingStateValue)
        syncManager.syncValue("collapse_state", collapseStateValue)
        
        val hasGhostCircuit = hasGhostCircuitInventory() && circuitInventory != null
        
        return GTGuis.createPanel(this, backgroundWidth, backgroundHeight)
            .child(IKey.lang(metaFullName).asWidget()
                       .pos(5, 5))
            .child(SlotGroupWidget.playerInventory()
                       .left(7).bottom(7))
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
                                            { workingStatus: Boolean -> workingStateValue.boolValue = workingStatus }))
                   .overlay(GTGuiTextures.BUTTON_ITEM_OUTPUT)
                   .tooltipBuilder {
                       it.setAutoUpdate(true)
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
                                            { collapseStatus: Boolean -> collapseStateValue.boolValue = collapseStatus }))
                   .overlay(GTGuiTextures.BUTTON_AUTO_COLLAPSE)
                   .tooltipBuilder {
                       it.setAutoUpdate(true)
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
            .slot(SyncHandlers.itemSlot(circuitInventory, 0)
                      .changeListener { newItem: ItemStack?, onlyAmountChanged: Boolean, client: Boolean, init: Boolean ->
                          // Add the dual handler to the notified list.
                          dualHandler!!.onContentsChanged()
                      })
        .background(GTGuiTextures.SLOT, GTGuiTextures.INT_CIRCUIT_OVERLAY))
        .childIf(!hasGhostCircuit, Widget()
            .background(GTGuiTextures.SLOT, GTGuiTextures.BUTTON_X)
            .tooltip { it.addLine(IKey.lang("gregtech.gui.configurator_slot.unavailable.tooltip")) }))
    }
    
    override fun renderMetaTileEntity(renderState: CCRenderState?,
                                      translation: Matrix4?,
                                      pipeline: Array<IVertexOperation?>?)
    {
        super.renderMetaTileEntity(renderState, translation, pipeline)
        val renderer = if (isExportHatch) Textures.PIPE_OUT_OVERLAY else Textures.PIPE_IN_OVERLAY
        renderer.renderSided(getFrontFacing(), renderState, translation, pipeline)

        val overlay = if (isExportHatch) GTLiteTextures.DUAL_HATCH_OUTPUT_OVERLAY else GTLiteTextures.DUAL_HATCH_INPUT_OVERLAY
        overlay.renderSided(getFrontFacing(), renderState, translation, pipeline)
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
            writeCustomData(GregtechDataCodes.WORKING_ENABLED) { it.writeBoolean(workingEnabled) }
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
                    addNotifiedOutput(getExportItems())
                }
                else
                {
                    addNotifiedInput(getImportItems())
                }
            }
            writeCustomData(GregtechDataCodes.TOGGLE_COLLAPSE_ITEMS) { it.writeBoolean(autoCollapse) }
            notifyBlockUpdate()
            markDirty()
        }
    }
    
    fun autoCollapse(): Boolean = autoCollapse
    
    override fun receiveCustomData(dataId: Int, buf: PacketBuffer)
    {
        super.receiveCustomData(dataId, buf)
        if (dataId == GregtechDataCodes.WORKING_ENABLED)
        {
            workingEnabled = buf.readBoolean()
        }
        else if (dataId == GregtechDataCodes.TOGGLE_COLLAPSE_ITEMS)
        {
            autoCollapse = buf.readBoolean()
        }
    }
    
    override fun onScrewdriverClick(playerIn: EntityPlayer,
                                    hand: EnumHand?,
                                    facing: EnumFacing?,
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
        
        if (circuitInventory != null)
        {
            circuitInventory!!.write(data)
        }
        
        return data
    }
    
    override fun readFromNBT(data: NBTTagCompound)
    {
        super.readFromNBT(data)
        
        this.workingEnabled = data.getBoolean("workingEnabled")
        this.autoCollapse = data.getBoolean("autoCollapse")
        
        if (circuitInventory != null)
        {
            circuitInventory!!.read(data)
        }
    }
    
    override fun addInformation(stack: ItemStack?,
                                player: World?,
                                tooltip: MutableList<String?>,
                                advanced: Boolean)
    {
        if (isExportHatch)
        {
            tooltip.add(I18n.format("gregtech.machine.item_bus.export.tooltip"))
        }
        else
        {
            tooltip.add(I18n.format("gregtech.machine.item_bus.import.tooltip"))
        }
        tooltip.add(I18n.format("gregtech.universal.tooltip.item_storage_capacity", getItemSize()))
        tooltip.add(I18n.format("gregtech.universal.tooltip.fluid_storage_capacity", getTankSize()))
        tooltip.add(I18n.format("gregtech.universal.enabled"))
    }
    
    override fun addToolUsages(stack: ItemStack?,
                               world: World?,
                               tooltip: MutableList<String?>,
                               advanced: Boolean)
    {
        tooltip.add(I18n.format("gregtech.tool_action.screwdriver.access_covers"))
        tooltip.add(I18n.format("gregtech.tool_action.screwdriver.auto_collapse"))
        tooltip.add(I18n.format("gregtech.tool_action.wrench.set_facing"))
        super.addToolUsages(stack, world, tooltip, advanced)
    }

    fun collapseInventorySlotContents(inventory: IItemHandlerModifiable)
    {
        // Gather a snapshot of the provided inventory
        val inventoryContents = GTHashMaps.fromItemHandler(inventory, true)
            
        val inventoryItemContents: MutableList<ItemStack> = ArrayList<ItemStack>()
            
        // Populate the list of item stacks in the inventory with apportioned item stacks, for easy replacement
        for (e in inventoryContents.object2IntEntrySet())
        {
            val stack: ItemStack = e.key!!
            var count = e.intValue
            val maxStackSize = stack.maxStackSize
            while (count >= maxStackSize)
            {
                val copy = stack.copy(maxStackSize)
                inventoryItemContents.add(copy)
                count -= maxStackSize
            }
            if (count > 0)
            {
                val copy = stack.copy(count)
                inventoryItemContents.add(copy)
            }
        }
            
        for (i in 0 ..< inventory.getSlots())
        {
            val stackToMove: ItemStack
            // Ensure that we are not exceeding the List size when attempting to populate items
            if (i >= inventoryItemContents.size)
            {
                stackToMove = ItemStack.EMPTY
            }
            else
            {
                stackToMove = inventoryItemContents[i]
            }
                
            // Populate the slots
            inventory.setStackInSlot(i, stackToMove)
        }
    }

}