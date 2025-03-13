package magicbook.gtlitecore.common.metatileentity.multiblock

import codechicken.lib.render.CCRenderState
import codechicken.lib.render.pipeline.IVertexOperation
import codechicken.lib.vec.Matrix4
import gregtech.api.capability.IGhostSlotConfigurable
import gregtech.api.capability.impl.GhostCircuitItemStackHandler
import gregtech.api.capability.impl.ItemHandlerList
import gregtech.api.capability.impl.PrimitiveRecipeLogic
import gregtech.api.gui.GuiTextures
import gregtech.api.gui.ModularUI
import gregtech.api.gui.widgets.ClickButtonWidget
import gregtech.api.gui.widgets.GhostCircuitSlotWidget
import gregtech.api.gui.widgets.ProgressWidget
import gregtech.api.gui.widgets.RecipeProgressWidget
import gregtech.api.gui.widgets.SlotWidget
import gregtech.api.gui.widgets.TankWidget
import gregtech.api.metatileentity.MetaTileEntity
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiblockAbility
import gregtech.api.metatileentity.multiblock.ParallelLogicType
import gregtech.api.metatileentity.multiblock.RecipeMapPrimitiveMultiblockController
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.PatternMatchContext
import gregtech.api.recipes.RecipeMap
import gregtech.api.recipes.ingredients.IntCircuitIngredient
import gregtech.api.util.GTTransferUtils
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.renderer.texture.Textures
import magicbook.gtlitecore.api.gui.GTLiteGuiTextures
import magicbook.gtlitecore.api.pattern.GTLiteTraceabilityPredicate
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK
import magicbook.gtlitecore.client.renderer.texture.GTLiteTextures
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks
import magicbook.gtlitecore.common.block.blocks.BlockPrimitiveCasing
import net.minecraft.client.resources.I18n
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraft.util.NonNullList
import net.minecraft.util.ResourceLocation
import net.minecraft.util.text.TextComponentTranslation
import net.minecraft.world.World
import net.minecraftforge.common.util.Constants
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fluids.IFluidTank
import net.minecraftforge.items.IItemHandler
import net.minecraftforge.items.IItemHandlerModifiable
import net.minecraftforge.items.ItemStackHandler
import java.lang.String.valueOf
import java.util.*
import kotlin.math.min

@Suppress("MISSING_DEPENDENCY_CLASS")
class MetaTileEntityCoagulationTank : RecipeMapPrimitiveMultiblockController, IGhostSlotConfigurable
{

    private var circuitInventory: GhostCircuitItemStackHandler? = null
    private var actualImportItems: IItemHandlerModifiable? = null

    var size: Int = 0

    constructor(metaTileEntityId: ResourceLocation) : super(metaTileEntityId, GTLiteRecipeMaps.COAGULATION_RECIPES)
    {
        this.recipeMapWorkable = CoagulationTankRecipeLogic(this, GTLiteRecipeMaps.COAGULATION_RECIPES)
        this.circuitInventory = GhostCircuitItemStackHandler(this)
        this.circuitInventory!!.addNotifiableMetaTileEntity(this)
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity?): MetaTileEntity
        = MetaTileEntityCoagulationTank(this.metaTileEntityId)

    override fun initializeInventory()
    {
        super.initializeInventory()
        this.actualImportItems = null
    }

    @Suppress("UNCHECKED_CAST")
    override fun getImportItems(): IItemHandlerModifiable
    {
        if (this.actualImportItems == null)
            this.actualImportItems = if (this.circuitInventory == null) super.getImportItems()
                else ItemHandlerList((listOf(super.getImportItems() as IItemHandlerModifiable, circuitInventory)) as MutableList<IItemHandler>)
        return this.actualImportItems!!
    }

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        this.size = context.getOrDefault("length", 1)
    }

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("CCC", "CCC", "CCC")
        .aisle("CCC", "CIC", "C#C").setRepeatable(1, 16)
        .aisle("CCC", "CSC", "CCC")
        .where('S', selfPredicate())
        .where('C', states(getCasingState())
            .or(abilities(MultiblockAbility.IMPORT_FLUIDS, MultiblockAbility.EXPORT_ITEMS)))
        .where('I', GTLiteTraceabilityPredicate.scaleIndicatorPredicate())
        .where('#', air())
        .build()

    private fun getCasingState() = GTLiteMetaBlocks.PRIMITIVE_CASING.getState(BlockPrimitiveCasing.PrimitiveCasingType.REINFORCED_TREATED_WOOD_WALL)

    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteTextures.REINFORCED_TREATED_WOOD_WALL

    override fun getFrontOverlay(): ICubeRenderer = Textures.PRIMITIVE_PUMP_OVERLAY

    override fun renderMetaTileEntity(renderState: CCRenderState?, translation: Matrix4?,
                                      pipeline: Array<out IVertexOperation>?)
    {
        super.renderMetaTileEntity(renderState, translation, pipeline)
        this.frontOverlay.renderOrientedState(renderState, translation, pipeline, this.frontFacing,
            this.recipeMapWorkable.isActive, this.recipeMapWorkable.isWorkingEnabled)
    }

    @Suppress("UNCHECKED_CAST")
    override fun update()
    {
        super.update()
        if (this.offsetTimer % 5 * TICK == 0L && this.isStructureFormed)
        {
            // Extracted all fluids which in import hatches to fluid container.
            for (tank: IFluidTank in this.getAbilities(MultiblockAbility.IMPORT_FLUIDS))
            {
                if (tank.fluid != null)
                {
                    val fluidStacks = NonNullList.create<FluidStack>()
                    val toFill = (this.importFluids.getTankAt(0).capacity - this.importFluids.getTankAt(0).fluidAmount)
                    val amount = min(tank.fluidAmount, toFill)
                    fluidStacks.add(FluidStack(tank.fluid, amount))
                    if (GTTransferUtils.addFluidsToFluidHandler(this.importFluids, true, fluidStacks))
                    {
                        GTTransferUtils.addFluidsToFluidHandler(this.importFluids, false, fluidStacks)
                        tank.drain(amount, true)
                    }
                }
            }
            // Do same thing for export items.
            for (i in 0 until (this.exportItems as IItemHandlerModifiable).slots)
            {
                val stack = (this.exportItems as IItemHandlerModifiable).getStackInSlot(i)
                this.exportItems.setStackInSlot(i, GTTransferUtils.insertItem(
                    ItemHandlerList(this.getAbilities(MultiblockAbility.EXPORT_ITEMS)
                            as MutableList<out IItemHandler>) as IItemHandler, stack, false))
            }
            this.fillInternalTankFromFluidContainer()
        }
    }

    override fun hasMaintenanceMechanics(): Boolean = false

    override fun hasGhostCircuitInventory(): Boolean = true

    override fun setGhostCircuitConfig(config: Int)
    {
        if (this.circuitInventory == null || this.circuitInventory!!.circuitValue == config)
            return
        this.circuitInventory!!.circuitValue = config
        if (!(this.world as World).isRemote)
            markDirty()
    }

    private fun getCircuitSlotTooltip(widget: SlotWidget): SlotWidget
    {
        val configString: String = if (this.circuitInventory == null || this.circuitInventory!!.circuitValue == GhostCircuitItemStackHandler.NO_CONFIG)
            TextComponentTranslation("gregtech.gui.configurator_slot.no_value").formattedText
        else
            valueOf(this.circuitInventory!!.circuitValue)
        return widget.setTooltipText("gregtech.gui.configurator_slot.tooltip", configString)
    }

    override fun writeToNBT(data: NBTTagCompound): NBTTagCompound
    {
        super.writeToNBT(data)
        data.setInteger("size", this.size)
        if (this.circuitInventory != null)
            this.circuitInventory!!.write(data)
        return data
    }

    override fun readFromNBT(data: NBTTagCompound)
    {
        super.readFromNBT(data)
        this.size = data.getInteger("size")
        if (this.circuitInventory != null)
        {
            if (data.hasKey("CircuitInventory", Constants.NBT.TAG_COMPOUND))
            {
                val tCircuitInventory = ItemStackHandler()
                for (i in 0 until tCircuitInventory.slots)
                {
                    var stack = tCircuitInventory.getStackInSlot(i)
                    if (stack.isEmpty)
                        continue
                    stack = GTTransferUtils.insertItem(this.importItems as IItemHandler, stack, false)
                    this.circuitInventory!!.setCircuitValueFromStack(stack)
                }
            }
            else
            {
                this.circuitInventory!!.read(data)
            }
        }
    }

    override fun createUITemplate(player: EntityPlayer?): ModularUI.Builder
    {
        val builder = ModularUI.builder(GuiTextures.PRIMITIVE_BACKGROUND, 176, 166)
            .label(6, 6,  this.metaFullName)
            .widget(RecipeProgressWidget(this.recipeMapWorkable::getProgressPercent, 76, 41,
                20, 15, GuiTextures.PRIMITIVE_BLAST_FURNACE_PROGRESS_BAR,
                ProgressWidget.MoveType.HORIZONTAL, GTLiteRecipeMaps.COAGULATION_RECIPES))
            .widget(SlotWidget(this.importItems as IItemHandler, 0,
                30, 30, true, true)
                .setBackgroundTexture(GuiTextures.PRIMITIVE_SLOT))
            .widget(SlotWidget(this.importItems as IItemHandler, 1,
                48, 30, true, true)
                .setBackgroundTexture(GuiTextures.PRIMITIVE_SLOT))
            .widget(TankWidget(this.importFluids.getTankAt(1) as IFluidTank,
                30, 48, 18, 18)
                .setAlwaysShowFull(true)
                .setBackgroundTexture(GTLiteGuiTextures.PRIMITIVE_FLUID_SLOT)
                .setContainerClicking(true, true))
            .widget(TankWidget(this.importFluids.getTankAt(0) as IFluidTank,
                48, 48, 18, 18)
                .setAlwaysShowFull(true)
                .setBackgroundTexture(GTLiteGuiTextures.PRIMITIVE_FLUID_SLOT)
                .setContainerClicking(true, true))
            .widget(SlotWidget(this.exportItems as IItemHandler, 0,
                106, 39, true, false)
                .setBackgroundTexture(GuiTextures.PRIMITIVE_SLOT))
        val circuitSlot = GhostCircuitSlotWidget(this.circuitInventory, 0,
            124, 62)
            .setBackgroundTexture(GuiTextures.PRIMITIVE_SLOT, GTLiteGuiTextures.INT_CIRCUIT_OVERLAY_STEAM.get(true))
        builder.widget(getCircuitSlotTooltip(circuitSlot))
            .widget(ClickButtonWidget(115, 62, 9, 9, "") { click ->
                circuitInventory!!.addCircuitValue(if (click.isShiftClick) 5 else 1) }
                .setShouldClientCallback(true)
                .setButtonTexture(GTLiteGuiTextures.BUTTON_INT_CIRCUIT_PLUS_PRIMITIVE)
                .setDisplayFunction { circuitInventory!!.hasCircuitValue()
                        && circuitInventory!!.circuitValue < IntCircuitIngredient.CIRCUIT_MAX })
            .widget(ClickButtonWidget(115, 71, 9, 9, "") { click ->
                circuitInventory!!.addCircuitValue(if (click.isShiftClick) -5 else -1) }
                .setShouldClientCallback(true)
                .setButtonTexture(GTLiteGuiTextures.BUTTON_INT_CIRCUIT_MINUS_PRIMITIVE)
                .setDisplayFunction { circuitInventory!!.hasCircuitValue()
                        && circuitInventory!!.circuitValue > IntCircuitIngredient.CIRCUIT_MIN})
        return builder.bindPlayerInventory(player!!.inventory, GuiTextures.PRIMITIVE_SLOT, 0)
    }

    override fun addInformation(stack: ItemStack?,
                                world: World?,
                                tooltip: MutableList<String>,
                                advanced: Boolean)
    {
        super.addInformation(stack, world, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.machine.coagulation_tank.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.coagulation_tank.tooltip.2"))
        tooltip.add(I18n.format("gtlitecore.machine.coagulation_tank.tooltip.3"))
        tooltip.add(I18n.format("gtlitecore.machine.coagulation_tank.tooltip.4"))
    }

    class CoagulationTankRecipeLogic(metaTileEntity: RecipeMapPrimitiveMultiblockController, recipeMap: RecipeMap<*>) : PrimitiveRecipeLogic(metaTileEntity, recipeMap)
    {

        override fun getParallelLimit(): Int = (this.getMetaTileEntity() as MetaTileEntityCoagulationTank).size

        override fun getMaxParallelVoltage(): Long = 2147432767L // Long.MAX_VALUE - 50800 EU

        override fun getParallelLogicType(): ParallelLogicType = ParallelLogicType.MULTIPLY

    }

}