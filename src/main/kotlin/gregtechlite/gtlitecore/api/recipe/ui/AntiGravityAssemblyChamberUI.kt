package gregtechlite.gtlitecore.api.recipe.ui

import gregtech.api.capability.impl.FluidTankList
import gregtech.api.gui.GuiTextures
import gregtech.api.gui.ModularUI
import gregtech.api.gui.widgets.ProgressWidget
import gregtech.api.recipes.RecipeMap
import gregtech.api.recipes.ui.RecipeMapUI
import net.minecraftforge.items.IItemHandlerModifiable

@Suppress("UnstableApiUsage")
internal class AntiGravityAssemblyChamberUI<R : RecipeMap<*>>(recipeMap: R) : RecipeMapUI<R>(recipeMap, true, true, true, true, false)
{

    override fun createJeiUITemplate(importItems: IItemHandlerModifiable, exportItems: IItemHandlerModifiable,
                                     importFluids: FluidTankList, exportFluids: FluidTankList, yOffset: Int): ModularUI.Builder
    {
        val builder = ModularUI.builder(GuiTextures.BACKGROUND, 176, 176)
            .widget(ProgressWidget(200, 80, 1, 54, 72, GuiTextures.PROGRESS_BAR_ASSEMBLY_LINE, ProgressWidget.MoveType.HORIZONTAL))
        addInventorySlotGroup(builder, importItems, importFluids, false, yOffset)
        addInventorySlotGroup(builder, exportItems, exportFluids, true, yOffset)
        return builder
    }

    override fun addInventorySlotGroup(builder: ModularUI.Builder,
                                       itemHandler: IItemHandlerModifiable, fluidHandler: FluidTankList,
                                       isOutputs: Boolean, yOffset: Int)
    {
        var itemInputsCount = itemHandler.slots
        var fluidInputsCount = fluidHandler.tanks
        var invertFluids = false
        if (itemInputsCount == 0)
        {
            val tmp = itemInputsCount
            itemInputsCount = fluidInputsCount
            fluidInputsCount = tmp
            invertFluids = true
        }
        val inputSlotGrid = determineSlotsGrid(itemInputsCount)
        val itemSlotsToLeft = inputSlotGrid[0]
        val itemSlotsToDown = inputSlotGrid[1]
        val startInputsX = 80 - itemSlotsToLeft * 18
        val startInputsY = 37 - (itemSlotsToDown / 2.0 * 18).toInt()

        if (!isOutputs)
        {
            for (i in 0 until itemSlotsToDown)
            {
                for (j in 0 until itemSlotsToLeft)
                {
                    val slotIndex = i * itemSlotsToLeft + j /* + 1*/ // needed for data slot
                    addSlot(builder, startInputsX + 18 * j, startInputsY + 18 * i,
                            slotIndex, itemHandler, fluidHandler, invertFluids, false)
                }
            }
            if (fluidInputsCount > 0 || invertFluids)
            {
                if (itemSlotsToDown >= fluidInputsCount)
                {
                    val startSpecX = startInputsX + 18 * 5
                    for (i in 0 until fluidInputsCount)
                    {
                        addSlot(builder, startSpecX, startInputsY + 18 * i, i, itemHandler, fluidHandler, true, false)
                    }
                }
            }
        }
        else
        {
            for (i in 0 until 4)
            {
                addSlot(builder, startInputsX + 18 * 4, 1 + 18 * i, i, itemHandler, fluidHandler, invertFluids, true)
            }
        }
    }

}