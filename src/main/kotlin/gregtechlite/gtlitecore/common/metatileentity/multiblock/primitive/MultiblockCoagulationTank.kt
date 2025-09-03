package gregtechlite.gtlitecore.common.metatileentity.multiblock.primitive

import codechicken.lib.render.CCRenderState
import codechicken.lib.render.pipeline.IVertexOperation
import codechicken.lib.vec.Matrix4
import com.cleanroommc.modularui.value.sync.DoubleSyncValue
import com.cleanroommc.modularui.value.sync.SyncHandlers
import com.cleanroommc.modularui.widgets.ItemSlot
import com.cleanroommc.modularui.widgets.ProgressWidget
import com.cleanroommc.modularui.widgets.SlotGroupWidget
import gregtech.api.capability.IGhostSlotConfigurable
import gregtech.api.capability.impl.GhostCircuitItemStackHandler
import gregtech.api.capability.impl.ItemHandlerList
import gregtech.api.capability.impl.PrimitiveRecipeLogic
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiblockAbility.EXPORT_ITEMS
import gregtech.api.metatileentity.multiblock.MultiblockAbility.IMPORT_FLUIDS
import gregtech.api.metatileentity.multiblock.ParallelLogicType
import gregtech.api.metatileentity.multiblock.RecipeMapPrimitiveMultiblockController
import gregtech.api.metatileentity.multiblock.ui.MultiblockUIFactory
import gregtech.api.mui.GTGuiTextures
import gregtech.api.mui.GTGuiTheme
import gregtech.api.mui.widget.GhostCircuitSlotWidget
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.recipes.RecipeMap
import gregtech.api.util.GTTransferUtils.addFluidsToFluidHandler
import gregtech.api.util.KeyUtil
import gregtech.client.renderer.ICubeRenderer
import gregtech.common.mui.widget.GTFluidSlot
import gregtechlite.gtlitecore.api.gui.GTLiteMuiTextures
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.COAGULATION_RECIPES
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.SNOW_LAYER
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteTextures
import gregtechlite.gtlitecore.common.block.variant.PrimitiveCasing
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.NonNullList
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.common.util.Constants
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.items.IItemHandlerModifiable
import net.minecraftforge.items.ItemHandlerHelper.insertItem
import net.minecraftforge.items.ItemStackHandler
import kotlin.math.min

class MultiblockCoagulationTank(id: ResourceLocation)
    : RecipeMapPrimitiveMultiblockController(id, COAGULATION_RECIPES), IGhostSlotConfigurable
{

    private var circuitInventory: GhostCircuitItemStackHandler? = null
    private var actualImportItems: IItemHandlerModifiable? = null

    init
    {
        recipeMapWorkable = CoagulationTankRecipeLogic(this, COAGULATION_RECIPES)
        circuitInventory = GhostCircuitItemStackHandler(this)
        circuitInventory?.addNotifiableMetaTileEntity(this)
    }

    companion object
    {
        private val casingState
            get() = PrimitiveCasing.REINFORCED_TREATED_WOOD_WALL.state
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MultiblockCoagulationTank(metaTileEntityId)

    override fun initializeInventory()
    {
        super.initializeInventory()
        actualImportItems = null
    }

    override fun getImportItems(): IItemHandlerModifiable
    {
        if (actualImportItems == null)
            actualImportItems = if (circuitInventory == null) super.getImportItems()
                else ItemHandlerList((listOf(super.getImportItems(), circuitInventory)))
        return actualImportItems!!
    }

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("CCC", "CCC", "CCC")
        .aisle("CCC", "C*C", "C#C")
        .aisle("CSC", "CCC", "CCC")
        .where('S', selfPredicate())
        .where('C', states(casingState)
            .or(abilities(IMPORT_FLUIDS, EXPORT_ITEMS)))
        .where('#', air())
        .where('*', air().or(SNOW_LAYER))
        .build()

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteTextures.REINFORCED_TREATED_WOOD_WALL

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = GTLiteTextures.COAGULATION_TANK_OVERLAY

    override fun renderMetaTileEntity(renderState: CCRenderState?,
                                      translation: Matrix4?,
                                      pipeline: Array<out IVertexOperation>?)
    {
        super.renderMetaTileEntity(renderState, translation, pipeline)
        frontOverlay.renderOrientedState(renderState, translation, pipeline, frontFacing,
            recipeMapWorkable.isActive, recipeMapWorkable.isWorkingEnabled)
    }

    override fun update()
    {
        super.update()
        if (offsetTimer % 5 * TICK == 0L && isStructureFormed)
        {
            // Extracted all fluids which in import hatches to fluid container.
            for (tank in getAbilities(IMPORT_FLUIDS))
            {
                if (tank.fluid != null)
                {
                    val fluidStacks = NonNullList.create<FluidStack>()
                    val toFill = (importFluids.getTankAt(0).capacity - importFluids.getTankAt(0).fluidAmount)
                    val amount = min(tank.fluidAmount, toFill)
                    fluidStacks.add(FluidStack(tank.fluid, amount))
                    if (addFluidsToFluidHandler(importFluids, true, fluidStacks))
                    {
                        addFluidsToFluidHandler(importFluids, false, fluidStacks)
                        tank.drain(amount, true)
                    }
                }
            }
            // Do same thing for export items.
            for (i in 0 until exportItems.slots)
            {
                val stack = exportItems.getStackInSlot(i)
                exportItems.setStackInSlot(i, insertItem(ItemHandlerList(getAbilities(EXPORT_ITEMS)), stack, false))
            }
            fillInternalTankFromFluidContainer()
        }
    }

    override fun hasMaintenanceMechanics() = false

    override fun hasGhostCircuitInventory() = true

    override fun setGhostCircuitConfig(config: Int)
    {
        if (circuitInventory == null || circuitInventory!!.circuitValue == config)
            return

        circuitInventory!!.circuitValue = config
        if (!world.isRemote) markDirty()
    }

    override fun writeToNBT(data: NBTTagCompound): NBTTagCompound
    {
        super.writeToNBT(data)
        if (circuitInventory != null)
            circuitInventory!!.write(data)
        return data
    }

    override fun readFromNBT(data: NBTTagCompound)
    {
        super.readFromNBT(data)
        if (circuitInventory != null)
        {
            if (data.hasKey("CircuitInventory", Constants.NBT.TAG_COMPOUND))
            {
                val currentCircuitInventory = ItemStackHandler()
                for (i in 0 until currentCircuitInventory.slots)
                {
                    var stack = currentCircuitInventory.getStackInSlot(i)
                    if (stack.isEmpty) continue
                    stack = insertItem(importItems, stack, false)
                    circuitInventory!!.setCircuitValueFromStack(stack)
                }
            }
            else
            {
                circuitInventory!!.read(data)
            }
        }
    }

    override fun usesMui2() = true

    override fun getUITheme(): GTGuiTheme = GTGuiTheme.PRIMITIVE

    @Suppress("UnstableApiUsage")
    override fun createUIFactory(): MultiblockUIFactory?
    {
        return MultiblockUIFactory(this)
            .setSize(176, 166)
            .disableDisplay()
            .disableButtons()
            .addScreenChildren { parent, guiSyncManager ->
                guiSyncManager.registerSlotGroup("item_inv", 3, true)

                val tankSyncManager1 = GTFluidSlot.sync(importFluids.getTankAt(0))
                    .accessibility(true, true)
                    .showAmountOnSlot { true }

                val tankSyncManager2 = GTFluidSlot.sync(importFluids.getTankAt(1))
                    .accessibility(true, true)
                    .showAmountOnSlot { true }

                parent.child(KeyUtil.lang(metaFullName).asWidget()
                                 .pos(6, 6))
                    .child(SlotGroupWidget.playerInventory()
                               .left(7).bottom(7))
                    .child(ItemSlot()
                               .slot(SyncHandlers.itemSlot(importItems, 0)
                                         .slotGroup("item_inv"))
                               .background(GTGuiTextures.SLOT_PRIMITIVE)
                               .pos(30, 30))
                    .child(ItemSlot()
                               .slot(SyncHandlers.itemSlot(importItems, 1)
                                         .slotGroup("item_inv"))
                               .background(GTGuiTextures.SLOT_PRIMITIVE)
                               .pos(30 + 18, 30))
                    .child(ItemSlot()
                               .slot(SyncHandlers.itemSlot(exportItems, 0)
                                         .slotGroup("item_inv")
                                         .accessibility(false, true))
                               .background(GTGuiTextures.SLOT_PRIMITIVE)
                               .pos(106, 39))

                    .child(GTFluidSlot()
                               .syncHandler(tankSyncManager1)
                               .pos(30, 30 + 18)
                               .background(GTLiteMuiTextures.PRIMITIVE_FLUID_SLOT))
                    .child(GTFluidSlot()
                               .syncHandler(tankSyncManager2)
                               .pos(30 + 18, 30 + 18)
                               .background(GTLiteMuiTextures.PRIMITIVE_FLUID_SLOT))

                    .child(GhostCircuitSlotWidget()
                               .slot(circuitInventory, 0)
                               .background(GTGuiTextures.SLOT_PRIMITIVE, GTLiteMuiTextures.PRIMITIVE_INT_CIRCUIT_OVERLAY)
                               .pos(124, 62))

                    .child(ProgressWidget()
                               .pos(76, 41)
                               .size(20, 15)
                               .texture(GTGuiTextures.PRIMITIVE_BLAST_FURNACE_PROGRESS_BAR, 100)
                               .value(DoubleSyncValue(recipeMapWorkable::getProgressPercent))
                               .direction(ProgressWidget.Direction.RIGHT))
            }
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
        tooltip.add(I18n.format("gregtech.universal.tooltip.parallel", 8))
    }

    private inner class CoagulationTankRecipeLogic(metaTileEntity: RecipeMapPrimitiveMultiblockController, recipeMap: RecipeMap<*>) : PrimitiveRecipeLogic(metaTileEntity, recipeMap)
    {

        override fun getParallelLimit(): Int = 8

        override fun getMaxParallelVoltage(): Long = 2_147_432_767L // Long.MAX_VALUE - 50800 EU

        override fun getParallelLogicType(): ParallelLogicType = ParallelLogicType.MULTIPLY

    }

}