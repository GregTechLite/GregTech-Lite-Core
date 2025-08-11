package gregtechlite.gtlitecore.api.metatileentity

import gregtech.api.capability.IGhostSlotConfigurable
import gregtech.api.capability.impl.FluidHandlerProxy
import gregtech.api.capability.impl.FluidTankList
import gregtech.api.capability.impl.GhostCircuitItemStackHandler
import gregtech.api.capability.impl.ItemHandlerList
import gregtech.api.capability.impl.ItemHandlerProxy
import gregtech.api.capability.impl.NotifiableFluidTank
import gregtech.api.capability.impl.NotifiableItemStackHandler
import gregtech.api.capability.impl.RecipeLogicSteam
import gregtech.api.gui.GuiTextures
import gregtech.api.gui.ModularUI
import gregtech.api.gui.resources.TextureArea
import gregtech.api.gui.widgets.ClickButtonWidget
import gregtech.api.gui.widgets.GhostCircuitSlotWidget
import gregtech.api.gui.widgets.RecipeProgressWidget
import gregtech.api.gui.widgets.SlotWidget
import gregtech.api.gui.widgets.TankWidget
import gregtech.api.metatileentity.MetaTileEntity
import gregtech.api.metatileentity.SteamMetaTileEntity
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.recipes.RecipeMap
import gregtech.api.recipes.ingredients.IntCircuitIngredient
import gregtech.api.util.GTTransferUtils
import gregtech.client.renderer.ICubeRenderer
import gregtechlite.gtlitecore.api.gui.GTLiteGuiTextures
import gregtechlite.gtlitecore.api.gui.indicator.SteamProgressBarIndicator
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.ResourceLocation
import net.minecraft.util.text.TextComponentTranslation
import net.minecraftforge.common.util.Constants
import net.minecraftforge.fluids.FluidTank
import net.minecraftforge.fluids.IFluidTank
import net.minecraftforge.fluids.capability.IFluidHandler
import net.minecraftforge.items.IItemHandler
import net.minecraftforge.items.IItemHandlerModifiable
import net.minecraftforge.items.ItemStackHandler
import kotlin.math.ceil
import kotlin.math.sqrt

open class SimpleSteamMachineMetaTileEntity(metaTileEntityId: ResourceLocation,
                                            recipeMap: RecipeMap<*>?,
                                            protected var progressBarIndicator: SteamProgressBarIndicator,
                                            renderer: ICubeRenderer?,
                                            @JvmField protected var isBrickedCasing: Boolean,
                                            isHighPressure: Boolean)
    : SteamMetaTileEntity(metaTileEntityId, recipeMap, renderer, isHighPressure), IGhostSlotConfigurable
{
    protected var circuitInventory: GhostCircuitItemStackHandler? = null
    protected var outputItemInventory: IItemHandler? = null
    protected var outputFluidInventory: IFluidHandler? = null
    private var actualImportItems: IItemHandlerModifiable? = null

    init
    {
        // If steam machine has ghost circuit settings, then initialized ghost circuit stack handler.
        if (this.hasGhostCircuitInventory())
        {
            this.circuitInventory = GhostCircuitItemStackHandler(this)
            this.circuitInventory!!.addNotifiableMetaTileEntity(this)
        }
        this.initializeInventory()
        this.workableHandler = RecipeLogicSteam(
            this, recipeMap, isHighPressure,
            this.steamFluidTank, 1.0
        )
    }

    companion object
    {

        protected fun determineSlotsGrid(itemInputCount: Int): IntArray
        {
            if (itemInputCount == 3) return intArrayOf(3, 1)
            val slotsLeft = ceil(sqrt(itemInputCount.toDouble())).toInt()
            val slotsDown = ceil(itemInputCount / slotsLeft.toDouble()).toInt()
            return intArrayOf(slotsLeft, slotsDown)
        }

    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity?): MetaTileEntity
    {
        return SimpleSteamMachineMetaTileEntity(this.metaTileEntityId, this.workableHandler.recipeMap,
            this.progressBarIndicator, this.renderer, this.isBrickedCasing,
            this.isHighPressure)
    }

    override fun initializeInventory()
    {
        super.initializeInventory()
        this.outputItemInventory = ItemHandlerProxy(ItemStackHandler(0), this.exportItems)
        this.outputFluidInventory = FluidHandlerProxy(FluidTankList(false), this.exportFluids)
        this.actualImportItems = null
    }

    override fun getImportItems(): IItemHandlerModifiable?
    {
        if (this.actualImportItems == null) this.actualImportItems = if (this.circuitInventory == null)
            super.getImportItems()
        else
            ItemHandlerList(listOf(super.getImportItems(), this.circuitInventory))
        return this.actualImportItems
    }

    override fun createImportItemHandler(): IItemHandlerModifiable
    {
        if (this.workableHandler == null) return ItemStackHandler(0)
        return NotifiableItemStackHandler(this,
            this.workableHandler.recipeMap!!.maxInputs, this, false)
    }

    override fun createExportItemHandler(): IItemHandlerModifiable
    {
        if (this.workableHandler == null) return ItemStackHandler(0)
        return NotifiableItemStackHandler(this,
            this.workableHandler.recipeMap!!.maxOutputs, this, true)
    }

    override fun createImportFluidHandler(): FluidTankList
    {
        super.createImportFluidHandler()
        if (this.workableHandler == null) return FluidTankList(false, this.steamFluidTank)
        val importFluids = arrayOfNulls<IFluidTank>(this.workableHandler.recipeMap!!.maxFluidInputs + 1)
        importFluids[0] = this.steamFluidTank
        for (i in 1..<importFluids.size)
        {
            importFluids[i] = NotifiableFluidTank(8000, this, false)
        }
        return FluidTankList(false, *importFluids)
    }

    override fun createExportFluidHandler(): FluidTankList
    {
        if (this.workableHandler == null) return FluidTankList(false)
        val exportFluids = arrayOfNulls<FluidTank>(this.workableHandler.recipeMap!!.maxFluidOutputs)
        for (i in exportFluids.indices)
        {
            exportFluids[i] = NotifiableFluidTank(8000, this, true)
        }
        return FluidTankList(false, *exportFluids)
    }

    public override fun isBrickedCasing(): Boolean
    {
        return this.isBrickedCasing
    }

    @Deprecated("Deprecated in Java")
    override fun createUI(player: EntityPlayer?): ModularUI?
    {
        return createGuiTemplate(player).build(getHolder(), player)
    }

    protected fun createGuiTemplate(player: EntityPlayer?): ModularUI.Builder
    {
        val recipeMap = this.workableHandler.recipeMap

        // Original Gui from SteamMetaTileEntity.
        val builder = super.createUITemplate(player)

        // Progress Bar and Ghost Circuit Inventory components.
        this.addRecipeProgressBar(builder, recipeMap)
        this.addInventorySlotGroup(builder, this.importItems, this.importFluids, false)
        this.addInventorySlotGroup(builder, this.exportItems, this.exportFluids, true)
        this.addGhostCircuitSlot(builder)
        return builder
    }

    protected fun addRecipeProgressBar(builder: ModularUI.Builder, recipeMap: RecipeMap<*>?)
    {
        val x = 89 - this.progressBarIndicator.width / 2
        val y = 42 - this.progressBarIndicator.height / 2
        builder.widget(RecipeProgressWidget(
            { this.workableHandler.progressPercent },
            x, y, this.progressBarIndicator.width, this.progressBarIndicator.height,
            this.progressBarIndicator.progressBarTexture.get(isHighPressure),
            this.progressBarIndicator.progressBarMoveType, recipeMap))
    }

    protected fun addInventorySlotGroup(builder: ModularUI.Builder,
                                        itemHandler: IItemHandlerModifiable,
                                        fluidHandler: FluidTankList,
                                        isOutputs: Boolean)
    {
        var offsetY = 0
        var itemSlotCount = itemHandler.getSlots()
        var fluidSlotCount = fluidHandler.tanks - (if (isOutputs) 0 else 1) // Remove input steam tank.
        // Redundant to store item slots count if you know it's going to be 0.
        var invertFluids = false
        if (itemSlotCount == 0)
        {
            val tmp = 0
            itemSlotCount = fluidSlotCount
            fluidSlotCount = tmp
            invertFluids = true
        }

        val inputSlotGrid = determineSlotsGrid(itemSlotCount)
        val itemSlotLeft = inputSlotGrid[0]
        val itemSlotDown = inputSlotGrid[1]

        // If height of item slots > fluid slots AND primary[item] slot (can be item or fluid) don't take full length of 3.
        val isVerticalFluid = itemSlotDown >= fluidSlotCount && itemSlotLeft < 3
        val fluidGridHeight =
            (if (fluidSlotCount / 3 == 0) 1 else fluidSlotCount / 3) // Fit into at most 3 wide by x tall.

        val fullGridHeight = itemSlotDown + (if (isVerticalFluid) 0 else fluidGridHeight)
        if (fullGridHeight >= 3) offsetY += 4

        val startInputsX = if (isOutputs)
            89 + this.progressBarIndicator.width / 2 + 9
        else
            89 - (this.progressBarIndicator.width / 2 + 9 + itemSlotLeft * 18)
        var startInputsY = offsetY + (if (isVerticalFluid)
            42 - ((itemSlotDown * 18) / 2)
        else
            42 - (((fluidSlotCount - 1) / 3 + 1) * 18))

        val wasGroup = itemHandler.getSlots() + fluidHandler.tanks == 12
        if (wasGroup) startInputsY -= 9
        else if (itemHandler.getSlots() >= 6 && fluidHandler.tanks >= 2 && !isOutputs) startInputsY -= 9

        for (i in 0..<itemSlotDown)
        {
            for (j in 0..<itemSlotLeft)
            {
                val slotIndex = i * itemSlotLeft + j
                if (slotIndex >= itemSlotCount) break
                val x = startInputsX + 18 * j
                val y = startInputsY + 18 * i
                addSlot(builder, x, y, slotIndex, itemHandler, fluidHandler, invertFluids, isOutputs)
            }
        }

        if (wasGroup) startInputsY += 2
        if (fluidSlotCount > 0 || invertFluids)
        {
            if (isVerticalFluid)
            {
                val startSpecX = if (isOutputs) startInputsX + itemSlotLeft * 18 else startInputsX - 18
                for (i in 0..<fluidSlotCount)
                {
                    addSlot(builder, startSpecX, startInputsY + 18 * i, i,
                            itemHandler, fluidHandler, !invertFluids, isOutputs)
                }
            }
            else
            {
                val startSpecY = startInputsY + itemSlotDown * 18
                for (i in 0..<fluidSlotCount)
                {
                    val x = if (isOutputs)
                        startInputsX + 18 * (i % 3)
                    else
                        startInputsX + itemSlotLeft * 18 - 18 - 18 * (i % 3)
                    val y = startSpecY + (i / 3) * 18
                    addSlot(builder, x, y, i, itemHandler, fluidHandler, !invertFluids, isOutputs)
                }
            }
        }
    }

    protected fun addGhostCircuitSlot(builder: ModularUI.Builder)
    {
        if (this.exportItems.getSlots() + this.exportFluids.tanks <= 9)
        {
            if (this.circuitInventory != null)
            {
                val circuitSlot = GhostCircuitSlotWidget(circuitInventory, 0, 124, 62)
                    .setBackgroundTexture(GuiTextures.SLOT_STEAM.get(isHighPressure), this.circuitSlotOverlay)
                builder.widget(circuitSlot.setConsumer { widget -> getCircuitSlotTooltip(widget) })
                    .widget(ClickButtonWidget(115, 62, 9, 9, "") { click ->
                        this.circuitInventory?.addCircuitValue(if (click.isShiftClick) 5 else 1) }
                            .setShouldClientCallback(true)
                            .setButtonTexture(GTLiteGuiTextures.BUTTON_INT_CIRCUIT_PLUS_STEAM.get(isHighPressure))
                            .setDisplayFunction {
                                this.circuitInventory!!.hasCircuitValue()
                                        && this.circuitInventory!!.circuitValue < IntCircuitIngredient.CIRCUIT_MAX
                            })
                    .widget(
                        ClickButtonWidget(115, 71, 9, 9, "") { click ->
                            this.circuitInventory?.addCircuitValue(if (click!!.isShiftClick) -5 else -1) }.setShouldClientCallback(true)
                            .setButtonTexture(GTLiteGuiTextures.BUTTON_INT_CIRCUIT_MINUS_STEAM.get(isHighPressure))
                            .setDisplayFunction {
                                this.circuitInventory!!.hasCircuitValue()
                                        && this.circuitInventory!!.circuitValue > IntCircuitIngredient.CIRCUIT_MIN
                            })
            }
        }
    }

    protected val circuitSlotOverlay: TextureArea?
        get() = GTLiteGuiTextures.INT_CIRCUIT_OVERLAY_STEAM.get(isHighPressure)

    protected fun getCircuitSlotTooltip(widget: SlotWidget)
    {
        val configString = if (circuitInventory == null
            || circuitInventory!!.circuitValue == GhostCircuitItemStackHandler.NO_CONFIG)
            TextComponentTranslation("gregtech.gui.configurator_slot.no_value").getFormattedText()
        else
            circuitInventory!!.circuitValue.toString()
        widget.setTooltipText("gregtech.gui.configurator_slot.tooltip", configString)
    }

    protected fun addSlot(builder: ModularUI.Builder,
                          x: Int, y: Int, slotIndex: Int,
                          itemHandler: IItemHandlerModifiable?,
                          fluidHandler: FluidTankList,
                          isFluid: Boolean, isOutputs: Boolean)
    {
        var slotIndex = slotIndex
        if (!isOutputs && isFluid) slotIndex++ // Skip steam slot.

        if (!isFluid)
        {
            builder.widget(SlotWidget(itemHandler, slotIndex, x, y, true, !isOutputs)
                    .setBackgroundTexture(*getOverlaysForSlot(false)))
        }
        else
        {
            builder.widget(TankWidget(fluidHandler.getTankAt(slotIndex), x, y, 18, 18)
                    .setAlwaysShowFull(true)
                    .setBackgroundTexture(*getOverlaysForSlot(true))
                    .setContainerClicking(true, !isOutputs))
        }
    }

    protected fun getOverlaysForSlot(isFluid: Boolean): Array<TextureArea?>
    {
        return arrayOf(
            if (isFluid)
                GTLiteGuiTextures.FLUID_SLOT_STEAM.get(isHighPressure)
            else
                GuiTextures.SLOT_STEAM.get(isHighPressure)
        )
    }

    override fun writeToNBT(data: NBTTagCompound): NBTTagCompound
    {
        super.writeToNBT(data)
        if (this.circuitInventory != null) this.circuitInventory!!.write(data)
        return data
    }

    override fun readFromNBT(data: NBTTagCompound)
    {
        super.readFromNBT(data)
        if (this.circuitInventory != null)
        {
            if (data.hasKey("CircuitInventory", Constants.NBT.TAG_COMPOUND))
            {
                val circuitStackHandler = ItemStackHandler()
                for (i in 0 ..< circuitStackHandler.slots)
                {
                    var stack = circuitStackHandler.getStackInSlot(i)
                    if (stack.isEmpty) continue
                    stack = GTTransferUtils.insertItem(this.importItems, stack, false)
                    this.circuitInventory!!.setCircuitValueFromStack(stack)
                }
            }
            else
            {
                this.circuitInventory!!.read(data)
            }
        }
    }

    override fun hasGhostCircuitInventory(): Boolean = true

    override fun setGhostCircuitConfig(config: Int)
    {
        if (this.circuitInventory == null
            || this.circuitInventory!!.circuitValue == config) return
        this.circuitInventory!!.circuitValue = config
        if (!world.isRemote) markDirty()
    }

}
