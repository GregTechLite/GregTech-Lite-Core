package gregtechlite.gtlitecore.common.metatileentity.part

import appeng.api.AEApi
import appeng.api.implementations.ICraftingPatternItem
import appeng.api.networking.crafting.ICraftingPatternDetails
import appeng.api.networking.crafting.ICraftingProvider
import appeng.api.networking.crafting.ICraftingProviderHelper
import appeng.api.networking.events.MENetworkCraftingPatternChange
import appeng.api.networking.security.IActionSource
import appeng.api.storage.channels.IFluidStorageChannel
import appeng.api.storage.channels.IItemStorageChannel
import appeng.api.storage.data.IAEItemStack
import appeng.me.GridAccessException
import appeng.me.helpers.AENetworkProxy
import appeng.util.Platform
import codechicken.lib.render.CCRenderState
import codechicken.lib.render.pipeline.IVertexOperation
import codechicken.lib.vec.Matrix4
import com.cleanroommc.modularui.api.drawable.IKey
import com.cleanroommc.modularui.api.widget.IWidget
import com.cleanroommc.modularui.factory.PosGuiData
import com.cleanroommc.modularui.screen.ModularPanel
import com.cleanroommc.modularui.screen.UISettings
import com.cleanroommc.modularui.value.sync.BooleanSyncValue
import com.cleanroommc.modularui.value.sync.PanelSyncManager
import com.cleanroommc.modularui.value.sync.SyncHandlers
import com.cleanroommc.modularui.widgets.SlotGroupWidget
import com.cleanroommc.modularui.widgets.ToggleButton
import com.cleanroommc.modularui.widgets.layout.Flow
import com.cleanroommc.modularui.widgets.layout.Grid
import com.cleanroommc.modularui.widgets.slot.ItemSlot
import com.glodblock.github.common.item.fake.FakeFluids
import com.glodblock.github.common.item.fake.FakeItemRegister
import gregtech.api.capability.GregtechDataCodes.WORKING_ENABLED
import gregtech.api.capability.IControllable
import gregtech.api.capability.IDataStickIntractable
import gregtech.api.capability.IGhostSlotConfigurable
import gregtech.api.capability.IMultipleTankHandler
import gregtech.api.capability.impl.FluidTankList
import gregtech.api.capability.impl.GhostCircuitItemStackHandler
import gregtech.api.capability.impl.NotifiableItemStackHandler
import gregtech.api.items.itemhandlers.GTItemStackHandler
import gregtech.api.metatileentity.MetaTileEntity
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.AbilityInstances
import gregtech.api.metatileentity.multiblock.IMultiblockAbilityPart
import gregtech.api.metatileentity.multiblock.MultiblockAbility
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.mui.GTGuiTextures
import gregtech.api.mui.GTGuis
import gregtech.api.mui.widget.GhostCircuitSlotWidget
import gregtech.api.util.GTUtility
import gregtech.common.metatileentities.multi.multiblockpart.appeng.MetaTileEntityAEHostablePart
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.capability.PatternedSingletonDualInputProxy
import gregtechlite.gtlitecore.api.capability.SingletonDualInputInventory
import gregtechlite.gtlitecore.api.capability.PatternedSingletonDualInputInventory
import gregtechlite.gtlitecore.api.capability.handler.DynamicItemHandlerList
import gregtechlite.gtlitecore.api.capability.handler.DynamicFluidTank
import gregtechlite.gtlitecore.api.capability.handler.DynamicNotifiableItemStackHandler
import gregtechlite.gtlitecore.api.capability.SingletonDualInputAdapter
import gregtechlite.gtlitecore.api.capability.handler.SingletonDualInputHandler
import gregtechlite.gtlitecore.api.extension.copy
import gregtechlite.gtlitecore.api.gui.GTLiteMuiTextures
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteOverlays
import net.minecraft.client.resources.I18n
import net.minecraft.entity.item.EntityItem
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.InventoryCrafting
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.nbt.NBTTagList
import net.minecraft.util.ResourceLocation
import net.minecraft.util.text.TextComponentTranslation
import net.minecraft.world.World
import net.minecraftforge.common.util.Constants
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.items.IItemHandlerModifiable
import java.util.Optional

class PartMachineMECraftingPatternInputHatch(id: ResourceLocation, tier: Int, supportFluids: Boolean)
    : MetaTileEntityAEHostablePart<IAEItemStack>(id, tier, false, IItemStorageChannel::class.java),
      IMultiblockAbilityPart<IItemHandlerModifiable>, PatternedSingletonDualInputProxy,
      ICraftingProvider, IGhostSlotConfigurable, IControllable, IDataStickIntractable
{
    private val _supportFluids = supportFluids // Skip java super constructor invokes.

    private val itemChannel: IItemStorageChannel
        get() = AEApi.instance().storage().getStorageChannel(IItemStorageChannel::class.java)

    private val fluidChannel: IFluidStorageChannel
        get() = AEApi.instance().storage().getStorageChannel(IFluidStorageChannel::class.java)

    private var patternInventory: PatternInventory? = null
    private var manualInventory: ManualInventory? = null
    private var circuitInventory: GhostCircuitItemStackHandler? = null
    private var internalInventory: Array<PatternSlot?> = arrayOfNulls(PATTERN_SLOT_COUNT)

    private var workingEnabled = true
    private var justHadNewItems = false
    private var needPatternSync = true

    private var sharedItemHandler: DynamicItemHandlerList? = null

    private val mirrors = mutableListOf<PartMachineMECraftingPatternInputMirror>()
    private var lastMirrorValidationTick = -1L

    init
    {
        initializeInventory()
    }

    companion object
    {
        const val PATTERN_SLOT_COUNT = 36
        const val MANUAL_SLOT_COUNT = 9

        const val SHARED_SLOT_COUNT = MANUAL_SLOT_COUNT + 1

        private const val PATTERN_COLUMNS = 9

        private const val WORKING_TAG = "WorkingEnabled"
        private const val DATA_STICK_TAG = "MECraftingPatternInputBus"
        const val MIRROR_LINK_TAG = "MECraftingPatternInputMirrorLink"

        private const val PATTERN_SYNC_DURATION = 10 * TICK
        private const val ME_STATUS_SYNC_DURATION = 20 * TICK
    }

    override fun createMetaTileEntity(te: IGregTechTileEntity): MetaTileEntity
        = PartMachineMECraftingPatternInputHatch(metaTileEntityId, tier, _supportFluids)

    override fun initializeInventory()
    {
        super.initializeInventory()
        patternInventory = PatternInventory()
        manualInventory = ManualInventory()
        circuitInventory = GhostCircuitItemStackHandler(this)
        internalInventory = arrayOfNulls(PATTERN_SLOT_COUNT)
        sharedItemHandler = DynamicItemHandlerList(circuitInventory!!, manualInventory!!)
    }

    override fun createImportItemHandler(): IItemHandlerModifiable = GTItemStackHandler(this, 0)

    override fun createExportItemHandler(): IItemHandlerModifiable = GTItemStackHandler(this, 0)

    // region Pattern Operations

    private fun onPatternChange(index: Int, newItem: ItemStack?)
    {
        if (index !in internalInventory.indices) return
        if (world?.isRemote == true) return

        val originalSlot = internalInventory[index]
        originalSlot?.let {
            if (!it.hasChanged(newItem)) return
            proxy?.let { proxy -> it.refund(proxy, actionSource, true) }

            invalidateRecipeCache(it)
            internalInventory[index] = null
            needPatternSync = true
        }

        if (newItem == null || newItem.isEmpty || newItem.item !is ICraftingPatternItem) return

        internalInventory[index] = PatternSlot(newItem.copy())
        needPatternSync = true
        justHadNewItems = true
    }

    private fun invalidateRecipeCache(inventory: PatternedSingletonDualInputInventory? = null)
    {
        val logic = (controller as? RecipeMapMultiblockController)?.recipeMapWorkable as? SingletonDualInputAdapter ?: return
        inventory?.let { logic.removeInventoryRecipeCache(inventory) } ?: logic.clearInventoryRecipeCache()
    }

    private fun dropStack(stack: ItemStack)
    {
        val worldIn = world ?: return
        if (stack.isEmpty) return
        val entity = EntityItem(worldIn, pos.x + 0.5, pos.y + 0.5, pos.z + 0.5, stack)
        entity.setDefaultPickupDelay()
        worldIn.spawnEntity(entity)
    }

    private fun postMEPatternChange(): Boolean
    {
        val proxy = proxy ?: return false
        if (!proxy.isActive) return false
        val node = proxy.node ?: return false
        return try
        {
            proxy.grid.postEvent(MENetworkCraftingPatternChange(this, node))
            true
        }
        catch (_: GridAccessException) { false }
    }

    override fun gridChanged()
    {
        needPatternSync = true
    }

    // endregion

    // region Mirror Operations

    fun addMirror(mirror: PartMachineMECraftingPatternInputMirror)
    {
        if (mirrors.none { it === mirror })
            mirrors.add(mirror)
    }

    fun removeMirror(mirror: PartMachineMECraftingPatternInputMirror)
    {
        mirrors.remove(mirror)
    }

    fun getMirrors(): List<PartMachineMECraftingPatternInputMirror>
    {
        if (offsetTimer != lastMirrorValidationTick)
        {
            mirrors.removeAll { it.getMaster() !== this }
            lastMirrorValidationTick = offsetTimer
        }
        return mirrors
    }

    fun getSharedItemHandler(): IItemHandlerModifiable = sharedItemHandler!!

    fun writeMirrorLink(root: NBTTagCompound)
    {
        NBTTagCompound().also {
            it.setInteger("x", pos.x)
            it.setInteger("y", pos.y)
            it.setInteger("z", pos.z)
            root.setTag(MIRROR_LINK_TAG, it)
        }
    }

    private fun clearMirrors()
    {
        mirrors.forEach { it.onMasterRemoved() }
        mirrors.clear()
    }

    // endregion

    override fun inventories(): Iterator<PatternedSingletonDualInputInventory> = internalInventory.filterNotNull().iterator()

    override fun getFirstNonEmptyInventory(): Optional<SingletonDualInputInventory>
    {
        for (slot in internalInventory)
        {
            if (slot != null && !slot.isEmpty())
                return Optional.of(slot)
        }
        return Optional.empty()
    }

    override fun supportFluids(): Boolean = _supportFluids

    override fun update()
    {
        super.update()
        if (world == null || world.isRemote) return

        if (needPatternSync && offsetTimer % PATTERN_SYNC_DURATION == 0L)
        {
            needPatternSync = !postMEPatternChange()
        }
        if (offsetTimer % ME_STATUS_SYNC_DURATION == 0L)
        {
            updateMEStatus()
        }
    }

    override fun shouldUpdate(): Boolean
    {
        val updated = justHadNewItems
        justHadNewItems = false
        return updated
    }

    override fun getSharedItems(): Array<ItemStack>
    {
        val result = ArrayList<ItemStack>(MANUAL_SLOT_COUNT + 1)

        val circuit = circuitInventory?.getStackInSlot(0)
        if (circuit != null && !circuit.isEmpty)
            result.add(circuit)

        val manual = manualInventory
        manual?.let {
            for (i in 0 until MANUAL_SLOT_COUNT)
            {
                val stack = it.getStackInSlot(i)
                if (!stack.isEmpty)
                    result.add(stack)
            }
        }
        return result.toTypedArray()
    }

    override fun provideCrafting(craftingTracker: ICraftingProviderHelper)
    {
        if (proxy?.isActive != true) return
        for (slot in internalInventory)
        {
            val details = slot?.patternDetails ?: continue
            craftingTracker.addCraftingOption(this, details)
        }
    }

    override fun pushPattern(patternDetails: ICraftingPatternDetails, table: InventoryCrafting): Boolean
    {
        if (proxy?.isActive != true) return false
        if (!workingEnabled) return false

        if (!_supportFluids)
        {
            for (i in 0 until table.sizeInventory)
            {
                val stack = table.getStackInSlot(i)
                if (!stack.isEmpty && FakeFluids.isFluidFakeItem(stack))
                    return false
            }
        }

        val slot = internalInventory.firstOrNull { it?.patternDetails == patternDetails } ?: return false
        if (!slot.insertItemsAndFluids(table))
            return false

        justHadNewItems = true
        return true
    }

    override fun isBusy(): Boolean = false

    override fun hasGhostCircuitInventory(): Boolean = true

    override fun setGhostCircuitConfig(config: Int)
    {
        val inventory = circuitInventory ?: return
        if (inventory.circuitValue == config) return
        inventory.circuitValue = config
        invalidateRecipeCache()
        justHadNewItems = true
        if (world?.isRemote == false) markDirty()
    }

    override fun isWorkingEnabled(): Boolean = workingEnabled

    override fun setWorkingEnabled(workingEnabled: Boolean)
    {
        this.workingEnabled = workingEnabled
        if (world != null && !world.isRemote)
        {
            writeCustomData(WORKING_ENABLED) { it.writeBoolean(this.workingEnabled) }
        }
    }

    override fun onDataStickLeftClick(player: EntityPlayer, dataStick: ItemStack)
    {
        NBTTagCompound().also {
            it.setTag(DATA_STICK_TAG, writeConfigToTag())
            writeMirrorLink(it)
            dataStick.tagCompound = it
        }

        dataStick.setTranslatableName("gtlitecore.machine.me_crafting_pattern_input_hatch.data_stick")
        player.sendStatusMessage(TextComponentTranslation("gtlitecore.machine.me_crafting_pattern_input_hatch.config_saved"), true)
    }

    override fun onDataStickRightClick(player: EntityPlayer, dataStick: ItemStack): Boolean
    {
        val tag = dataStick.tagCompound ?: return false
        if (!tag.hasKey(DATA_STICK_TAG))
            return false
        readConfigFromTag(tag.getCompoundTag(DATA_STICK_TAG))

        player.sendStatusMessage(TextComponentTranslation("gtlitecore.machine.me_crafting_pattern_input_hatch.config_loaded"), true)
        return true
    }

    private fun writeConfigToTag(): NBTTagCompound
    {
        val tag = NBTTagCompound()
        patternInventory?.let { GTUtility.writeItems(it, "PatternInventory", tag) }
        manualInventory?.let { GTUtility.writeItems(it, "ManualInventory", tag) }
        circuitInventory?.write(tag)
        tag.setTag("InternalInventory", writeInternalInventory())
        return tag
    }

    private fun readConfigFromTag(tag: NBTTagCompound)
    {
        patternInventory?.let { GTUtility.readItems(it, "PatternInventory", tag) }
        manualInventory?.let { GTUtility.readItems(it, "ManualInventory", tag) }
        circuitInventory?.read(tag)
        readInternalInventory(tag)
        invalidateRecipeCache()
        justHadNewItems = true
        needPatternSync = true
    }

    override fun writeToNBT(data: NBTTagCompound): NBTTagCompound
    {
        super.writeToNBT(data)
        data.setBoolean(WORKING_TAG, workingEnabled)
        patternInventory?.let { GTUtility.writeItems(it, "PatternInventory", data) }
        manualInventory?.let { GTUtility.writeItems(it, "ManualInventory", data) }
        circuitInventory?.write(data)
        data.setTag("InternalInventory", writeInternalInventory())
        return data
    }

    override fun readFromNBT(data: NBTTagCompound)
    {
        super.readFromNBT(data)
        if (data.hasKey(WORKING_TAG))
            workingEnabled = data.getBoolean(WORKING_TAG)

        patternInventory?.let { GTUtility.readItems(it, "PatternInventory", data) }
        manualInventory?.let { GTUtility.readItems(it, "ManualInventory", data) }
        circuitInventory?.read(data)
        readInternalInventory(data)

        needPatternSync = true
    }

    private fun writeInternalInventory(): NBTTagList
    {
        val slots = NBTTagList()
        internalInventory.forEachIndexed { idx, slot ->
            slot?.let {
                val slotTag = NBTTagCompound()
                slotTag.setInteger("slot", idx)
                slotTag.setTag("contents", it.writeToNBT(NBTTagCompound()))
                slots.appendTag(slotTag)
            }
        }
        return slots
    }

    private fun readInternalInventory(data: NBTTagCompound)
    {
        val slots = data.getTagList("InternalInventory", Constants.NBT.TAG_COMPOUND)
        for (i in 0 until slots.tagCount())
        {
            val slotTag = slots.getCompoundTagAt(i)
            val index = slotTag.getInteger("slot")
            if (index !in internalInventory.indices) continue
            internalInventory[index]?.readFromNBT(slotTag.getCompoundTag("contents"))
        }
    }

    override fun onRemoval()
    {
        super.onRemoval()
        clearMirrors()
    }

    override fun invalidate()
    {
        super.invalidate()
        if (world?.isRemote == false)
            clearMirrors()
    }

    override fun getAbilities(): List<MultiblockAbility<*>> = listOf(MultiblockAbility.IMPORT_ITEMS)

    override fun registerAbilities(abilityInstances: AbilityInstances)
    {
        if (abilityInstances.isKey(MultiblockAbility.IMPORT_ITEMS))
        {
            abilityInstances.add(getSharedItemHandler())
        }
    }

    @SideOnly(Side.CLIENT)
    override fun renderMetaTileEntity(renderState: CCRenderState?, translation: Matrix4?,
                                      pipeline: Array<out IVertexOperation?>?)
    {
        super.renderMetaTileEntity(renderState, translation, pipeline)
        if (shouldRenderOverlay())
        {
            val overlay = if (_supportFluids)
                GTLiteOverlays.ME_CRAFTING_INPUT_BUFFER_OVERLAY
            else
                GTLiteOverlays.ME_CRAFTING_INPUT_BUS_OVERLAY
            overlay.renderSided(frontFacing, renderState, translation, pipeline)
        }
    }

    @Suppress("UnstableApiUsage")
    override fun usesMui2(): Boolean = true

    @Suppress("UnstableApiUsage")
    override fun buildUI(guiData: PosGuiData, syncManager: PanelSyncManager, settings: UISettings): ModularPanel
    {
        val patternRows = PATTERN_SLOT_COUNT / PATTERN_COLUMNS
        syncManager.registerSlotGroup("pattern_inv", PATTERN_COLUMNS)
        syncManager.registerSlotGroup("manual_inv", PATTERN_COLUMNS)

        val patternGrid = ArrayList<MutableList<IWidget>>(patternRows)
        val patterns = patternInventory!!
        for (row in 0 until patternRows)
        {
            val rowWidgets = ArrayList<IWidget>(PATTERN_COLUMNS)
            for (column in 0 until PATTERN_COLUMNS)
            {
                val index = row * PATTERN_COLUMNS + column
                rowWidgets.add(ItemSlot()
                                   .background(GTGuiTextures.SLOT, GTLiteMuiTextures.PATTERN_OVERLAY)
                                   .slot(SyncHandlers.itemSlot(patterns, index)
                                             .slotGroup("pattern_inv")
                                             .filter { it.isEmpty || it.item is ICraftingPatternItem }
                                             .changeListener { _, onlyAmountChanged, _, _ ->
                                                 if (onlyAmountChanged) patterns.onContentsChanged(index)
                                             }
                                             .accessibility(true, true)))
            }
            patternGrid.add(rowWidgets)
        }

        val manualRow = ArrayList<IWidget>(PATTERN_COLUMNS)
        val manual = manualInventory!!
        for (index in 0 until MANUAL_SLOT_COUNT)
        {
            manualRow.add(ItemSlot()
                              .slot(SyncHandlers.itemSlot(manual, index)
                                        .slotGroup("manual_inv")
                                        .accessibility(true, true)))
        }
        val manualGrid = ArrayList<MutableList<IWidget>>(1)
        manualGrid.add(manualRow)

        val workingStateSync = BooleanSyncValue(
            { workingEnabled },
            { workingStatus -> workingEnabled = workingStatus })

        val backgroundWidth = 199
        val patternTop = 26
        val manualTop = patternTop + patternRows * 18 + 4
        val backgroundHeight = 208

        return GTGuis.createPanel(this, backgroundWidth, backgroundHeight)
            .child(IKey.lang(metaFullName).asWidget()
                       .pos(5, 5))
            .child(IKey.lang {
                if (isOnline()) "gregtech.gui.me_network.online" else "gregtech.gui.me_network.offline"
            }.asWidget().pos(5, 16))
            .child(SlotGroupWidget.playerInventory(false)
                       .left(7)
                       .bottom(7))
            .child(Grid()
                       .left(8)
                       .top(patternTop)
                       .height(patternRows * 18)
                       .minElementMargin(0, 0)
                       .minColWidth(18)
                       .minRowHeight(18)
                       .matrix(patternGrid))
            .child(Grid()
                       .left(8)
                       .top(manualTop)
                       .height(18)
                       .minElementMargin(0, 0)
                       .minColWidth(18)
                       .minRowHeight(18)
                       .matrix(manualGrid))
            .child(Flow.column()
                       .pos(backgroundWidth - 23, patternTop)
                       .width(18)
                       .height(patternRows * 18)
                       .child(ToggleButton()
                                  .top(0)
                                  .value(workingStateSync)
                                  .overlay(GTGuiTextures.BUTTON_ITEM_OUTPUT)
                                  .tooltipAutoUpdate(true)
                                  .tooltipBuilder {
                                      if (workingStateSync.boolValue)
                                          it.addLine(IKey.lang("gregtech.gui.item_auto_input.tooltip.enabled"))
                                      else
                                          it.addLine(IKey.lang("gregtech.gui.item_auto_input.tooltip.disabled"))
                                  })
                       .child(GhostCircuitSlotWidget()
                                  .top(22)
                                  .slot(circuitInventory, 0)
                                  .background(GTGuiTextures.SLOT, GTGuiTextures.INT_CIRCUIT_OVERLAY)))
    }

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, player: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        super.addInformation(stack, player, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.machine.me_crafting_pattern_input_hatch.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.me_crafting_pattern_input_hatch.tooltip.2"))
        tooltip.add(I18n.format("gtlitecore.machine.me_crafting_pattern_input_hatch.tooltip.3"))
        if (_supportFluids)
            tooltip.add(I18n.format("gtlitecore.machine.me_crafting_pattern_input_hatch.tooltip.fluid"))
        else
            tooltip.add(I18n.format("gtlitecore.machine.me_crafting_pattern_input_hatch.tooltip.item"))
        tooltip.add(I18n.format("gregtech.machine.me.extra_connections.tooltip"))
        tooltip.add(I18n.format("gregtech.universal.enabled"))
    }

    @SideOnly(Side.CLIENT)
    override fun addToolUsages(stack: ItemStack, world: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        tooltip.add(I18n.format("gregtech.tool_action.wire_cutter.connect"))
        tooltip.add(I18n.format("gregtech.tool_action.wrench.set_facing"))
        tooltip.add(I18n.format("gregtech.tool_action.screwdriver.access_covers"))
        super.addToolUsages(stack, world, tooltip, advanced)
    }

    private inner class PatternInventory
        : NotifiableItemStackHandler(this@PartMachineMECraftingPatternInputHatch, PATTERN_SLOT_COUNT, null, false)
    {
        override fun onContentsChanged(slot: Int)
        {
            super.onContentsChanged(slot)
            onPatternChange(slot, getStackInSlot(slot))
        }
    }

    private inner class ManualInventory
        : NotifiableItemStackHandler(this@PartMachineMECraftingPatternInputHatch, MANUAL_SLOT_COUNT, null, false)
    {
        override fun onContentsChanged(slot: Int)
        {
            super.onContentsChanged(slot)
            invalidateRecipeCache()
            justHadNewItems = true
        }
    }

    inner class PatternSlot(val pattern: ItemStack) : PatternedSingletonDualInputInventory
    {
        val patternDetails: ICraftingPatternDetails? by lazy(LazyThreadSafetyMode.NONE) {
            (pattern.item as? ICraftingPatternItem)?.getPatternForItem(pattern, world)
        }

        val itemInventory = arrayListOf<ItemStack>()
        val fluidInventory = arrayListOf<FluidStack?>()

        fun hasChanged(newPattern: ItemStack?): Boolean
        {
            if (newPattern == null || newPattern.isEmpty)
                return true
            if (ItemStack.areItemStacksEqual(pattern, newPattern))
                return false

            val newDetails = (newPattern.item as? ICraftingPatternItem)
                ?.getPatternForItem(newPattern, world) ?: return true
            return patternDetails != newDetails
        }

        override fun isEmpty(): Boolean
        {
            removeInvalidateContents()
            return itemInventory.isEmpty() && fluidInventory.isEmpty()
        }

        override fun getItemInputs(): Array<ItemStack>
        {
            removeInvalidateContents()
            return itemInventory.toTypedArray()
        }

        override fun getFluidInputs(): Array<FluidStack>
        {
            removeInvalidateContents()
            return fluidInventory.filterNotNull().toTypedArray()
        }

        override fun getConsumableItemHandler(): IItemHandlerModifiable
            = DynamicItemHandlerList(circuitInventory!!, manualInventory!!, DynamicNotifiableItemStackHandler(itemInventory))

        override fun getConsumableFluidHandler(): IMultipleTankHandler
        {
            val tanks = fluidInventory.indices.map { DynamicFluidTank(fluidInventory, it, Int.MAX_VALUE) }
            return FluidTankList(false, tanks)
        }

        override fun getPatternInputs(): SingletonDualInputHandler
        {
            val details = patternDetails ?: return SingletonDualInputHandler()

            val items = arrayListOf<ItemStack>()
            getSharedItems().forEach { if (!it.isEmpty) items.add(it) }

            val fluids = arrayListOf<FluidStack>()
            for (input in details.inputs)
            {
                if (input == null) continue
                val stack = input.createItemStack() ?: continue
                if (stack.isEmpty) continue
                if (FakeFluids.isFluidFakeItem(stack))
                {
                    val fluid = FakeItemRegister.getStack<FluidStack>(stack)
                    if (fluid != null && fluid.amount > 0)
                        fluids.add(fluid)
                }
                else
                {
                    items.add(stack)
                }
            }
            return SingletonDualInputHandler(items.toTypedArray(), fluids.toTypedArray())
        }

        override fun shouldBeCached(): Boolean = true

        fun insertItemsAndFluids(table: InventoryCrafting): Boolean
        {
            for (i in 0 until table.sizeInventory)
            {
                val stack = table.getStackInSlot(i)
                if (stack.isEmpty) continue
                if (FakeFluids.isFluidFakeItem(stack))
                {
                    val fluid = FakeItemRegister.getStack<FluidStack>(stack) ?: continue
                    if (fluid.amount > 0)
                        insertFluid(fluid)
                }
                else
                {
                    insertItem(stack)
                }
            }
            return true
        }

        private fun insertItem(inserted: ItemStack)
        {
            for (existing in itemInventory)
            {
                if (!ItemStack.areItemsEqual(existing, inserted)) continue
                if (!ItemStack.areItemStackTagsEqual(existing, inserted)) continue
                existing.grow(inserted.count)
                return
            }
            itemInventory.add(inserted.copy())
        }

        private fun insertFluid(inserted: FluidStack)
        {
            for (existing in fluidInventory)
            {
                if (existing == null || !existing.isFluidEqual(inserted)) continue
                existing.amount += inserted.amount
                return
            }
            fluidInventory.add(inserted.copy())
        }

        fun refund(proxy: AENetworkProxy, src: IActionSource, shouldDrop: Boolean)
        {
            try
            {
                val storage = proxy.storage
                val itemMonitor = storage.getInventory(itemChannel)
                val fluidMonitor = storage.getInventory(fluidChannel)
                val energy = proxy.energy

                for (i in itemInventory.indices)
                {
                    val stack = itemInventory[i]
                    if (stack.isEmpty) continue
                    val aeStack = itemChannel.createStack(stack) ?: continue
                    val rest = Platform.poweredInsert(energy, itemMonitor, aeStack, src)
                    val leftover = rest?.stackSize?.toInt() ?: 0
                    when
                    {
                        leftover <= 0 -> itemInventory[i] = ItemStack.EMPTY
                        shouldDrop    ->
                        {
                            dropStack(stack.copy(leftover))
                            itemInventory[i] = ItemStack.EMPTY
                        }
                        else          -> itemInventory[i] = stack.copy(leftover)
                    }
                }

                for (i in fluidInventory.indices)
                {
                    val fluid = fluidInventory[i] ?: continue
                    if (fluid.amount <= 0) continue
                    val aeStack = fluidChannel.createStack(fluid) ?: continue
                    val rest = Platform.poweredInsert(energy, fluidMonitor, aeStack, src)
                    val leftover = rest?.stackSize?.toInt() ?: 0
                    when
                    {
                        leftover <= 0 -> fluidInventory[i] = null
                        shouldDrop    ->
                        {
                            val packet = FakeFluids.packFluid2Packet(fluid.copy(leftover))
                            if (!packet.isEmpty) dropStack(packet)
                            fluidInventory[i] = null
                        }
                        else          -> fluidInventory[i] = fluid.copy(leftover)
                    }
                }
            }
            catch (_: GridAccessException) {}
            removeInvalidateContents()
        }

        fun writeToNBT(tag: NBTTagCompound): NBTTagCompound
        {
            tag.setTag("pattern", pattern.writeToNBT(NBTTagCompound()))

            val items = NBTTagList()
            itemInventory.forEach { if (!it.isEmpty) items.appendTag(it.writeToNBT(NBTTagCompound())) }
            tag.setTag("inventory", items)

            val fluids = NBTTagList()
            fluidInventory.forEach {
                if (it != null && it.amount > 0)
                    fluids.appendTag(it.writeToNBT(NBTTagCompound()))
            }
            tag.setTag("fluidInventory", fluids)
            return tag
        }

        fun readFromNBT(tag: NBTTagCompound)
        {
            itemInventory.clear()
            fluidInventory.clear()

            val items = tag.getTagList("inventory", Constants.NBT.TAG_COMPOUND)
            for (i in 0 until items.tagCount())
            {
                val stack = ItemStack(items.getCompoundTagAt(i))
                if (!stack.isEmpty)
                    itemInventory.add(stack)
            }

            val fluids = tag.getTagList("fluidInventory", Constants.NBT.TAG_COMPOUND)
            for (i in 0 until fluids.tagCount())
            {
                val fluid = FluidStack.loadFluidStackFromNBT(fluids.getCompoundTagAt(i))
                if (fluid != null && fluid.amount > 0)
                    fluidInventory.add(fluid)
            }
        }

        private fun removeInvalidateContents()
        {
            itemInventory.removeAll { it.isEmpty }
            fluidInventory.removeAll { it == null || it.amount <= 0 }
        }
    }
}