package gregtechlite.gtlitecore.common.metatileentity.part

import com.cleanroommc.modularui.api.drawable.IKey
import com.cleanroommc.modularui.factory.PosGuiData
import com.cleanroommc.modularui.screen.ModularPanel
import com.cleanroommc.modularui.value.sync.BooleanSyncValue
import com.cleanroommc.modularui.value.sync.PanelSyncManager
import com.cleanroommc.modularui.value.sync.SyncHandlers
import com.cleanroommc.modularui.widget.Widget
import com.cleanroommc.modularui.widgets.ItemSlot
import com.cleanroommc.modularui.widgets.SlotGroupWidget
import com.cleanroommc.modularui.widgets.ToggleButton
import com.cleanroommc.modularui.widgets.layout.Flow
import com.cleanroommc.modularui.widgets.layout.Grid
import com.cleanroommc.modularui.widgets.slot.ModularSlot
import gregtech.api.capability.GregtechDataCodes
import gregtech.api.capability.IControllable
import gregtech.api.capability.IGhostSlotConfigurable
import gregtech.api.capability.INotifiableHandler
import gregtech.api.capability.impl.GhostCircuitItemStackHandler
import gregtech.api.capability.impl.ItemHandlerList
import gregtech.api.capability.impl.NotifiableItemStackHandler
import gregtech.api.items.itemhandlers.GTItemStackHandler
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.AbilityInstances
import gregtech.api.metatileentity.multiblock.IMultiblockAbilityPart
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase
import gregtech.api.mui.GTGuiTextures
import gregtech.api.mui.GTGuis
import gregtech.api.mui.widget.GhostCircuitSlotWidget
import gregtech.api.util.GTHashMaps
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityItemBus
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.capability.handler.ConfigurableItemStackHandler
import gregtechlite.gtlitecore.api.extension.copy
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities
import net.minecraft.client.resources.I18n
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.network.PacketBuffer
import net.minecraft.util.NonNullList
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.items.IItemHandlerModifiable
import kotlin.math.max
import kotlin.math.sqrt

class PartMachineHugeItemBus(metaTileEntityId: ResourceLocation, tier: Int)
    : MetaTileEntityItemBus(metaTileEntityId, tier, false), IMultiblockAbilityPart<IItemHandlerModifiable>,
                                                                             IControllable, IGhostSlotConfigurable
{

    private var slotItemInventory: ConfigurableItemStackHandler? = null
    private var actualImportItems: IItemHandlerModifiable? = null

    private var workingEnabled: Boolean = true
    private var autoCollapse: Boolean = false

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = PartMachineHugeItemBus(metaTileEntityId, tier)

    override fun initializeInventory()
    {
        super.initializeInventory()
        if (hasGhostCircuitInventory())
        {
            circuitInventory = GhostCircuitItemStackHandler(this)
            circuitInventory!!.addNotifiableMetaTileEntity(this)
            slotItemInventory = ConfigurableItemStackHandler(this, getInventorySize(), null, false) { Int.MAX_VALUE }
            actualImportItems = ItemHandlerList(listOf(slotItemInventory, circuitInventory))
        }
        else
        {
            actualImportItems = null
        }
    }

    private fun getInventorySize(): Int
    {
        val slotRoot = 1 + tier
        return slotRoot * slotRoot
    }

    override fun getImportItems(): IItemHandlerModifiable?
    {
        return if (actualImportItems == null) super.getImportItems() else actualImportItems
    }

    override fun createImportItemHandler(): IItemHandlerModifiable?
    {
        return NotifiableItemStackHandler(this, getInventorySize(), controller, false)
    }

     override fun update()
     {
         super.update()
         if (!world.isRemote && offsetTimer % (5 * TICK) == 0L)
         {
             if (workingEnabled)
             {
                pullItemsFromNearbyHandlers(getFrontFacing())
             }
         }
         // Only attempt to auto collapse the inventory contents once the bus has been notified
         if (isAutoCollapse())
         {
             // Exclude the ghost circuit inventory from the auto collapse, so it does not extract any ghost circuits
             // from the slot
             val inventory = super.getImportItems()
             if (!isAttachedToMultiBlock || this.getNotifiedItemInputList().contains(inventory))
             {
                 collapseInventorySlotContents(inventory)
             }
         }
    }

    override fun isAutoCollapse(): Boolean = this.autoCollapse

    override fun setWorkingEnabled(workingEnabled: Boolean)
    {
        this.workingEnabled = workingEnabled
        if (world != null && !world.isRemote)
        {
            writeCustomData(GregtechDataCodes.WORKING_ENABLED) { it.writeBoolean(this.workingEnabled) }
        }
    }

    override fun isWorkingEnabled(): Boolean = this.workingEnabled

    override fun writeInitialSyncData(buf: PacketBuffer)
    {
        super.writeInitialSyncData(buf)
        buf.writeBoolean(this.workingEnabled)
        buf.writeBoolean(this.autoCollapse)
    }

    override fun receiveInitialSyncData(buf: PacketBuffer)
    {
        super.receiveInitialSyncData(buf)
        this.workingEnabled = buf.readBoolean()
        this.autoCollapse = buf.readBoolean()
    }

    override fun writeToNBT(data: NBTTagCompound): NBTTagCompound
    {
        super.writeToNBT(data)
        data.setBoolean("workingEnabled", this.workingEnabled)
        data.setBoolean("autoCollapse", this.autoCollapse)
        if (this.circuitInventory != null)
        {
            this.circuitInventory!!.write(data)
        }
        return data
    }

    override fun readFromNBT(data: NBTTagCompound)
    {
        super.readFromNBT(data)
        if (data.hasKey("workingEnabled"))
        {
            this.workingEnabled = data.getBoolean("workingEnabled")
        }
        if (data.hasKey("autoCollapse"))
        {
            this.autoCollapse = data.getBoolean("autoCollapse")
        }
        if (this.circuitInventory != null)
        {
            this.circuitInventory!!.read(data)
        }
    }

    override fun receiveCustomData(dataId: Int, buf: PacketBuffer)
    {
        super.receiveCustomData(dataId, buf)
        if (dataId == GregtechDataCodes.TOGGLE_COLLAPSE_ITEMS)
        {
            this.autoCollapse = buf.readBoolean()
        }
        else if (dataId == GregtechDataCodes.WORKING_ENABLED)
        {
            this.workingEnabled = buf.readBoolean()
        }
    }

    private fun collapseInventorySlotContents(inventory: IItemHandlerModifiable)
    {
        // Gather a snapshot of the provided inventory
        val inventoryContents = GTHashMaps.fromItemHandler(inventory, true)
        val inventoryItemContents: MutableList<ItemStack> = ArrayList()

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

    override fun setAutoCollapse(inverted: Boolean)
    {
        this.autoCollapse = inverted
        if (!world.isRemote)
        {
            if (this.autoCollapse)
            {
                addNotifiedInput(super.getImportItems())
            }
            writeCustomData(GregtechDataCodes.TOGGLE_COLLAPSE_ITEMS) { it.writeBoolean(this.autoCollapse) }
            notifyBlockUpdate()
            markDirty()
        }
    }

    override fun addToMultiBlock(controllerBase: MultiblockControllerBase?)
    {
        super.addToMultiBlock(controllerBase)
        if (hasGhostCircuitInventory() && actualImportItems is ItemHandlerList)
        {
            for (handler in (actualImportItems as ItemHandlerList).backingHandlers)
            {
                if (handler is INotifiableHandler)
                {
                    handler.addNotifiableMetaTileEntity(controllerBase)
                    handler.addToNotifiedList(this, handler, false)
                }
            }
        }
    }

    override fun removeFromMultiBlock(controllerBase: MultiblockControllerBase?)
    {
        super.removeFromMultiBlock(controllerBase)
        if (hasGhostCircuitInventory() && actualImportItems is ItemHandlerList)
        {
            for (handler in (actualImportItems as ItemHandlerList).backingHandlers)
            {
                if (handler is INotifiableHandler)
                {
                    handler.removeNotifiableMetaTileEntity(controllerBase)
                }
            }
        }
    }

    override fun registerAbilities(abilityInstances: AbilityInstances)
    {
        if (hasGhostCircuitInventory() && actualImportItems != null)
        {
            abilityInstances.add(actualImportItems)
        }
        else
        {
            abilityInstances.add(importItems)
        }
    }

    override fun addInformation(stack: ItemStack?, player: World?, tooltip: MutableList<String?>, advanced: Boolean)
    {
        tooltip.add(I18n.format("gregtech.machine.item_bus.import.tooltip"))
        tooltip.add(I18n.format("gtlitecore.machine.huge_item_bus.import.tooltip"))
        tooltip.add(I18n.format("gregtech.universal.tooltip.item_storage_capacity", getInventorySize()))
        tooltip.add(I18n.format("gregtech.universal.enabled"))
        tooltip.add(I18n.format("gtlitecore.machine.huge_item_bus.tooltip"))
    }

    @Suppress("UnstableApiUsage")
    override fun buildUI(guiData: PosGuiData, panelSyncManager: PanelSyncManager): ModularPanel
    {
        val rowSize = sqrt(getInventorySize().toDouble()).toInt()
        panelSyncManager.registerSlotGroup("item_inv", rowSize)

        val backgroundWidth = max(9 * 18 + 18 + 14 + 5,  // Player Inv width
                                  rowSize * 18 + 14) // Bus Inv width
        val backgroundHeight = 18 + 18 * rowSize + 94

        val workingStateValue = BooleanSyncValue({ workingEnabled },
                                                 { workingStatus -> workingEnabled = workingStatus })

        val collapseStateValue = BooleanSyncValue({ autoCollapse },
                                                  { collapseMode -> autoCollapse = collapseMode })

        val handler = importItems
        val hasGhostCircuit = hasGhostCircuitInventory() && this.circuitInventory != null

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
                       .mapTo(rowSize, rowSize * rowSize) { index ->
                           ItemSlot()
                               .slot(object : ModularSlot(handler, index) { override fun getSlotStackLimit(): Int = Int.MAX_VALUE }
                                         .ignoreMaxStackSize(true)
                                         .slotGroup("item_inv")
                                         .changeListener { newItem, onlyAmountChanged, client, init ->
                                             if (onlyAmountChanged && handler is GTItemStackHandler)
                                             {
                                                 handler.onContentsChanged(index)
                                             }
                                         }
                                         .accessibility(!this.isExportHatch, true))
                       })
            .child(Flow.column()
                       .pos(backgroundWidth - 7 - 18, backgroundHeight - 18 * 4 - 7 - 5)
                       .width(18)
                       .height(18 * 4 + 5)
                       .child(GTGuiTextures.getLogo(uiTheme).asWidget()
                                  .size(17)
                                  .top(18 * 3 + 5))
                       .child(ToggleButton()
                                  .top(18 * 2)
                                  .value(workingStateValue)
                                  .overlay(GTGuiTextures.BUTTON_ITEM_OUTPUT)
                                  .tooltipAutoUpdate(true)
                                  .tooltipBuilder { tooltip ->
                                      tooltip.addLine(if (workingStateValue.boolValue)
                                          IKey.lang("gregtech.gui.item_auto_input.tooltip.enabled")
                                      else
                                          IKey.lang("gregtech.gui.item_auto_input.tooltip.disabled"))
                                  })
                       .child(ToggleButton()
                                  .top(18)
                                  .value(collapseStateValue)
                                  .overlay(GTGuiTextures.BUTTON_AUTO_COLLAPSE)
                                  .tooltipAutoUpdate(true)
                                  .tooltipBuilder { tooltip ->
                                      tooltip.addLine(if (collapseStateValue.boolValue)
                                          IKey.lang("gregtech.gui.item_auto_collapse.tooltip.enabled")
                                      else
                                          IKey.lang("gregtech.gui.item_auto_collapse.tooltip.disabled"))
                                  })
                       .childIf(hasGhostCircuit, GhostCircuitSlotWidget()
                           .slot(SyncHandlers.itemSlot(circuitInventory, 0))
                           .background(GTGuiTextures.SLOT, GTGuiTextures.INT_CIRCUIT_OVERLAY))
                              .childIf(!hasGhostCircuit, Widget()
                                  .background(GTGuiTextures.SLOT, GTGuiTextures.BUTTON_X)
                                  .tooltip { tooltip ->
                                      tooltip.addLine(IKey.lang("gregtech.gui.configurator_slot.unavailable.tooltip"))
                                  }))
    }

    override fun getSubItems(creativeTab: CreativeTabs,
                             subItems: NonNullList<ItemStack>)
    {
        for (bus in GTLiteMetaTileEntities.HUGE_ITEM_IMPORT_BUS)
        {
            subItems.add(bus.stackForm)
        }
    }

}