package gregtechlite.gtlitecore.common.metatileentity.part

import codechicken.lib.render.CCRenderState
import codechicken.lib.render.pipeline.ColourMultiplier
import codechicken.lib.render.pipeline.IVertexOperation
import codechicken.lib.vec.Matrix4
import com.cleanroommc.modularui.api.GuiAxis
import com.cleanroommc.modularui.api.IPanelHandler
import com.cleanroommc.modularui.api.drawable.IKey
import com.cleanroommc.modularui.drawable.ItemDrawable
import com.cleanroommc.modularui.drawable.Rectangle
import com.cleanroommc.modularui.factory.PosGuiData
import com.cleanroommc.modularui.screen.ModularPanel
import com.cleanroommc.modularui.screen.UISettings
import com.cleanroommc.modularui.utils.Color
import com.cleanroommc.modularui.value.sync.BooleanSyncValue
import com.cleanroommc.modularui.value.sync.PanelSyncManager
import com.cleanroommc.modularui.value.sync.SyncHandlers
import com.cleanroommc.modularui.widget.Widget
import com.cleanroommc.modularui.widgets.ButtonWidget
import com.cleanroommc.modularui.widgets.SliderWidget
import com.cleanroommc.modularui.widgets.SlotGroupWidget
import com.cleanroommc.modularui.widgets.ToggleButton
import com.cleanroommc.modularui.widgets.layout.Flow
import com.cleanroommc.modularui.widgets.layout.Grid
import com.cleanroommc.modularui.widgets.slot.ItemSlot
import com.cleanroommc.modularui.widgets.textfield.TextFieldWidget
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.OpV
import gregtech.api.capability.GregtechDataCodes.TOGGLE_COLLAPSE_ITEMS
import gregtech.api.capability.GregtechDataCodes.WORKING_ENABLED
import gregtech.api.capability.IControllable
import gregtech.api.capability.IGhostSlotConfigurable
import gregtech.api.capability.INotifiableHandler
import gregtech.api.capability.impl.GhostCircuitItemStackHandler
import gregtech.api.capability.impl.ItemHandlerList
import gregtech.api.items.itemhandlers.GTItemStackHandler
import gregtech.api.metatileentity.MetaTileEntity
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.AbilityInstances
import gregtech.api.metatileentity.multiblock.IMultiblockAbilityPart
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase
import gregtech.api.mui.GTGuiTextures
import gregtech.api.mui.GTGuis
import gregtech.api.mui.widget.GhostCircuitSlotWidget
import gregtech.api.util.GTUtility.convertRGBtoOpaqueRGBA_CL
import gregtech.client.renderer.texture.cube.SimpleOrientedCubeRenderer
import gregtech.client.renderer.texture.custom.FireboxActiveRenderer
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityItemBus
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.capability.GTLiteDataCodes.STACK_SIZE_PER_SLOT
import gregtechlite.gtlitecore.api.capability.handler.ConfigurableItemStackHandler
import gregtechlite.gtlitecore.api.extension.add
import gregtechlite.gtlitecore.api.extension.collapseInventorySlotContents
import gregtechlite.gtlitecore.api.extension.square
import gregtechlite.gtlitecore.api.gui.sync.SafeIntSyncValue
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteOverlays
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.network.PacketBuffer
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.items.IItemHandlerModifiable
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sqrt

class PartMachineQuantumItemBus(id: ResourceLocation, tier: Int)
    : MetaTileEntityItemBus(id, tier, false), IMultiblockAbilityPart<IItemHandlerModifiable>, IControllable,
      IGhostSlotConfigurable
{
    private var actualImportItems: IItemHandlerModifiable? = null

    private var workingEnabled: Boolean = true
    private var autoCollapse: Boolean = false

    private val maxStackSizePerSlot: Int
        get()
        {
            require(tier in IV..OpV)
            val actualTier = tier - IV
            val exp = intArrayOf(11, 14, 17, 20, 23, 26, 28, 30, 31)
            val n = exp[actualTier]
            return if (actualTier == OpV - IV) (1 shl n) - 1 else 1 shl n
        }

    private var stackSizePerSlot: Int = maxStackSizePerSlot

    private val itemSize: Int // QIB start at IV stage, and it will nerf with LV tier level inventory limit as default.
        get() = (1 + tier - 5).square()

    override fun createMetaTileEntity(te: IGregTechTileEntity): MetaTileEntity
        = PartMachineQuantumItemBus(metaTileEntityId, tier)

    override fun initializeInventory()
    {
        super.initializeInventory()
        if (hasGhostCircuitInventory())
        {
            circuitInventory = GhostCircuitItemStackHandler(this)
            circuitInventory!!.addNotifiableMetaTileEntity(this)
            actualImportItems = ItemHandlerList(listOf(importItems!!, circuitInventory!!))
        }
        else
        {
            actualImportItems = importItems
        }
    }

    override fun getImportItems(): IItemHandlerModifiable?
        = if (actualImportItems == null) super<MetaTileEntityItemBus>.importItems else actualImportItems

    override fun createImportItemHandler(): IItemHandlerModifiable?
        = ConfigurableItemStackHandler(this, itemSize, controller, false) { min(stackSizePerSlot, maxStackSizePerSlot) }

     override fun update()
     {
         super.update()
         if (!world.isRemote && offsetTimer % (5 * TICK) == 0L)
         {
             if (workingEnabled)
             {
                pullItemsFromNearbyHandlers(frontFacing)
             }
         }
         // Only attempt to auto collapse the inventory contents once the bus has been notified
         if (isAutoCollapse())
         {
             // Exclude the ghost circuit inventory from the auto collapse, so it does not extract any ghost circuits
             // from the slot.
             val inventory = super<MetaTileEntityItemBus>.importItems
             if (!isAttachedToMultiBlock || notifiedItemInputList.contains(inventory))
             {
                 inventory.collapseInventorySlotContents()
             }
         }
    }

    override fun isAutoCollapse(): Boolean = autoCollapse

    override fun setWorkingEnabled(workingEnabled: Boolean)
    {
        this.workingEnabled = workingEnabled
        if (world != null && !world.isRemote)
        {
            writeCustomData(WORKING_ENABLED) { it.writeBoolean(this.workingEnabled) }
        }
    }

    override fun isWorkingEnabled(): Boolean = workingEnabled

    override fun writeInitialSyncData(buf: PacketBuffer)
    {
        super.writeInitialSyncData(buf)
        buf.writeBoolean(workingEnabled)
        buf.writeBoolean(autoCollapse)
        buf.writeInt(min(stackSizePerSlot, maxStackSizePerSlot))
    }

    override fun receiveInitialSyncData(buf: PacketBuffer)
    {
        super.receiveInitialSyncData(buf)
        workingEnabled = buf.readBoolean()
        autoCollapse = buf.readBoolean()
        stackSizePerSlot = buf.readInt()
    }

    override fun writeToNBT(data: NBTTagCompound): NBTTagCompound
    {
        super.writeToNBT(data)
        data.setBoolean("workingEnabled", workingEnabled)
        data.setBoolean("autoCollapse", autoCollapse)
        data.setInteger("stackSizePerSlot", stackSizePerSlot)
        circuitInventory?.write(data)
        return data
    }

    override fun readFromNBT(data: NBTTagCompound)
    {
        super.readFromNBT(data)
        if (data.hasKey("workingEnabled"))
            workingEnabled = data.getBoolean("workingEnabled")
        if (data.hasKey("autoCollapse"))
            autoCollapse = data.getBoolean("autoCollapse")
        if (data.hasKey("stackSizePerSlot"))
            stackSizePerSlot = min(data.getInteger("stackSizePerSlot"), maxStackSizePerSlot)
        circuitInventory?.read(data)
    }

    override fun receiveCustomData(dataId: Int, buf: PacketBuffer)
    {
        super.receiveCustomData(dataId, buf)
        when (dataId)
        {
            TOGGLE_COLLAPSE_ITEMS -> autoCollapse     = buf.readBoolean()
            WORKING_ENABLED       -> workingEnabled   = buf.readBoolean()
            STACK_SIZE_PER_SLOT   -> stackSizePerSlot = min(buf.readInt(), maxStackSizePerSlot)
        }
    }

    override fun setAutoCollapse(inverted: Boolean)
    {
        autoCollapse = inverted
        if (!world.isRemote)
        {
            if (autoCollapse) addNotifiedInput(importItems)
            writeCustomData(TOGGLE_COLLAPSE_ITEMS) { it.writeBoolean(autoCollapse) }
            notifyBlockUpdate()
            markDirty()
        }
    }

    fun setStackSizePerSlot(newStackSize: Int)
    {
        stackSizePerSlot = min(newStackSize, maxStackSizePerSlot)
        if (!world.isRemote)
        {
            writeCustomData(STACK_SIZE_PER_SLOT) { it.writeInt(stackSizePerSlot) }
            notifyBlockUpdate()
            markDirty()
        }
    }

    override fun addToMultiBlock(controller: MultiblockControllerBase?)
    {
        super.addToMultiBlock(controller)
        if (hasGhostCircuitInventory() && actualImportItems is ItemHandlerList)
        {
            for (handler in (actualImportItems as ItemHandlerList).backingHandlers)
            {
                if (handler is INotifiableHandler)
                {
                    handler.addNotifiableMetaTileEntity(controller)
                    handler.addToNotifiedList(this, handler, false)
                }
            }
        }
    }

    override fun removeFromMultiBlock(controller: MultiblockControllerBase?)
    {
        super.removeFromMultiBlock(controller)
        if (hasGhostCircuitInventory() && actualImportItems is ItemHandlerList)
        {
            for (handler in (actualImportItems as ItemHandlerList).backingHandlers)
            {
                if (handler is INotifiableHandler)
                {
                    handler.removeNotifiableMetaTileEntity(controller)
                }
            }
        }
    }

    override fun registerAbilities(abilityInstances: AbilityInstances)
    {
        abilityInstances.add(importItems)
    }

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, player: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        tooltip.add(I18n.format("gregtech.machine.item_bus.import.tooltip"))
        tooltip.add(I18n.format("gtlitecore.machine.quantum_item_bus.import.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.quantum_item_bus.import.tooltip.2"))
        tooltip.add(I18n.format("gtlitecore.machine.quantum_item_bus.import.tooltip.3", maxStackSizePerSlot))
        tooltip.add(I18n.format("gregtech.universal.tooltip.item_storage_capacity", itemSize))
        tooltip.add(I18n.format("gregtech.universal.enabled"))
    }

    @Suppress("UnstableApiUsage")
    override fun buildUI(guiData: PosGuiData, panelSyncManager: PanelSyncManager, settings: UISettings): ModularPanel
    {
        val rowSize = sqrt(itemSize.toDouble()).toInt()
        panelSyncManager.registerSlotGroup("item_inv", rowSize)

        val backgroundWidth = max(199, rowSize * 18 + 14) // (Player Inv Width, Bus Inv Width)
        val backgroundHeight = 112 + 18 * rowSize

        val workingStateValue = BooleanSyncValue(
            { workingEnabled },
            { workingStatus -> workingEnabled = workingStatus })

        val collapseStateValue = BooleanSyncValue(
            { autoCollapse },
            { collapseMode -> autoCollapse = collapseMode })

        val handler = importItems
        val hasGhostCircuit = hasGhostCircuitInventory() && circuitInventory != null

        val stackSizePanel = panelSyncManager.syncedPanel("stack_size_panel", true, ::makeStackSizePanel)

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
                       .mapTo(rowSize, rowSize.square()) { slotIdx ->
                           ItemSlot()
                               .slot(SyncHandlers.itemSlot(handler, slotIdx)
                                         .slotGroup("item_inv")
                                         .changeListener { newItem, onlyAmountChanged, client, init ->
                                             if (onlyAmountChanged && handler is GTItemStackHandler)
                                                 handler.onContentsChanged(slotIdx)
                                         }
                                         .ignoreMaxStackSize(true)
                                         .accessibility(true, true))
                       })
            .child(Flow.column()
                       .pos(backgroundWidth - 25, backgroundHeight - 60)
                       .width(18)
                       .height(77)
                       .child(ButtonWidget()
                                  .size(17)
                                  .top(59)
                                  .overlay(GTGuiTextures.FILTER_SETTINGS_OVERLAY.asIcon()
                                               .size(16))
                                  .addTooltipLine(IKey.lang("gtlitecore.machine.quantum_item_bus.import.configuration"))
                                  .onMousePressed {
                                      if (stackSizePanel.isPanelOpen)
                                          stackSizePanel.closePanel()
                                      else
                                          stackSizePanel.openPanel()
                                      return@onMousePressed true
                                  })
                       .child(ToggleButton()
                                  .top(36)
                                  .value(workingStateValue)
                                  .overlay(GTGuiTextures.BUTTON_ITEM_OUTPUT)
                                  .tooltipAutoUpdate(true)
                                  .tooltipBuilder {
                                      if (workingStateValue.boolValue)
                                          it.addLine(IKey.lang("gregtech.gui.item_auto_input.tooltip.enabled"))
                                      else
                                          it.addLine(IKey.lang("gregtech.gui.item_auto_input.tooltip.disabled"))
                                  })
            .child(ToggleButton()
                       .top(18)
                       .value(collapseStateValue)
                       .overlay(GTGuiTextures.BUTTON_AUTO_COLLAPSE)
                       .tooltipAutoUpdate(true)
                       .tooltipBuilder {
                           if (collapseStateValue.boolValue)
                               it.addLine( IKey.lang("gregtech.gui.item_auto_collapse.tooltip.enabled"))
                           else
                               it.addLine(IKey.lang("gregtech.gui.item_auto_collapse.tooltip.disabled"))
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

    @Suppress("UnstableApiUsage", "unused")
    private fun makeStackSizePanel(syncManager: PanelSyncManager, syncHandler: IPanelHandler): ModularPanel
    {
        val stackSizeSync = SafeIntSyncValue(::stackSizePerSlot, ::setStackSizePerSlot)
        return GTGuis.createPopupPanel("stack_size", 116, 53)
            .child(Flow.row()
                       .pos(4, 4)
                       .height(16)
                       .coverChildrenWidth()
                       .child(ItemDrawable(stackForm).asWidget()
                                  .size(16)
                                  .marginRight(4))
                       .child(IKey.lang("gtlitecore.machine.quantum_item_bus.import.configuration").asWidget()
                                  .heightRel(1.0f)))
            .child(Flow.row()
                       .top(20)
                       .margin(4, 0)
                       .coverChildrenHeight()
                       .child(SliderWidget()
                                  .background(Rectangle()
                                                  .color(Color.BLACK.brighter(2)).asIcon()
                                                  .height(8))
                                  .bounds(0.0, maxStackSizePerSlot.toDouble())
                                  .setAxis(GuiAxis.X)
                                  .value(stackSizeSync)
                                  .widthRel(0.7f)
                                  .height(20))
                       .child(TextFieldWidget()
                                  .widthRel(0.3f)
                                  .height(20)
                                  .setTextColor(Color.WHITE.darker(1))
                                  .background(GTGuiTextures.DISPLAY)
                                  .value(stackSizeSync)
                                  .setValidator {
                                      var value = it.toInt()
                                      if (value < 0)
                                          value = 0
                                      else if (value > maxStackSizePerSlot)
                                          value = maxStackSizePerSlot
                                      return@setValidator value.toString()
                                  }))
    }

    @SideOnly(Side.CLIENT)
    override fun renderMetaTileEntity(renderState: CCRenderState, translation: Matrix4,
                                      pipeline: Array<IVertexOperation>)
    {
        val coloredPipeline = pipeline.add(ColourMultiplier(convertRGBtoOpaqueRGBA_CL(paintingColorForRendering)))
        if (baseTexture is FireboxActiveRenderer || baseTexture is SimpleOrientedCubeRenderer)
            baseTexture.renderOriented(renderState, translation, coloredPipeline, frontFacing)
        else
            baseTexture.render(renderState, translation, coloredPipeline)
        if (shouldRenderOverlay())
            GTLiteOverlays.QUANTUM_ITEM_BUS_OVERLAY.renderSided(frontFacing, renderState, translation, pipeline)
    }
}