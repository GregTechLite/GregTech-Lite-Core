package gregtechlite.gtlitecore.api.recipe.ui

import gregtech.api.capability.impl.FluidTankList
import gregtech.api.gui.GuiTextures
import gregtech.api.gui.ModularUI
import gregtech.api.gui.resources.TextureArea
import gregtech.api.gui.widgets.ImageWidget
import gregtech.api.gui.widgets.RecipeProgressWidget
import gregtech.api.recipes.RecipeMap
import gregtech.api.recipes.ui.RecipeMapUI
import gregtechlite.gtlitecore.api.gui.GTLiteGuiTextures
import net.minecraftforge.items.IItemHandlerModifiable

@Suppress("UnstableApiUsage")
internal class SpaceAssemblerUI<R: RecipeMap<*>>(recipeMap: R) : RecipeMapUI<R>(recipeMap, true, true, true, true, false)
{

    init
    {
        setProgressBarTexture(GuiTextures.PROGRESS_BAR_ASSEMBLY_LINE)
    }

    override fun createJeiUITemplate(importItems: IItemHandlerModifiable, exportItems: IItemHandlerModifiable,
                                     importFluids: FluidTankList, exportFluids: FluidTankList, yOffset: Int): ModularUI.Builder
    {
        val builder = ModularUI.builder(GuiTextures.BACKGROUND, 176, 176)
            .widget(RecipeProgressWidget(200, 80, 1, 54, 72, progressBarTexture(), progressBarMoveType(), recipeMap()))
            .widget(ImageWidget(8 + 18 * 7, 1 + 18 * 3, 18, 18, logoTexture()))
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

        val startInputsX1 = 80 - itemSlotsToLeft * 18
        val startInputsX2 = startInputsX1 + 18 * 5
        val startInputsY = 37 - (itemSlotsToDown / 2.0 * 18).toInt()
        val startOutputsX = startInputsX1 + 18 * 4
        val startOutputsY = 1

        if (!isOutputs)
        {
            // Item input slots.
            for (h in 0 until itemSlotsToDown)
            {
                for (w in 0 until itemSlotsToLeft)
                {
                    val slotIdx = h * itemSlotsToLeft + w
                    addSlot(builder, startInputsX1 + 18 * w, startInputsY + 18 * h, slotIdx, itemHandler, fluidHandler, invertFluids, false)
                }
            }

            // Fluid input slots.
            if (fluidInputsCount > 0 || invertFluids)
            {
                if (itemSlotsToDown >= fluidInputsCount)
                {
                    for (h in 0 until fluidInputsCount)
                    {
                        addSlot(builder, startInputsX2, startInputsY + 18 * h, h, itemHandler, fluidHandler, true, false)
                    }
                }
            }
        }
        else
        {
            // Item output slot.
            addSlot(builder, startOutputsX, startOutputsY, 0, itemHandler, fluidHandler, invertFluids, true)
        }
    }

    private fun logoTexture(): TextureArea = GTLiteGuiTextures.SPACE_ELEVATOR_LOGO

}