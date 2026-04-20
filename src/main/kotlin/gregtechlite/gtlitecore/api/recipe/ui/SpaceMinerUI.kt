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
internal class SpaceMinerUI<R: RecipeMap<*>>(recipeMap: R) : RecipeMapUI<R>(recipeMap, true, true, true, true, false)
{

    init
    {
        setItemSlotOverlay(GuiTextures.INT_CIRCUIT_OVERLAY, false, true)
        setProgressBarTexture(GTLiteGuiTextures.PROGRESS_BAR_SPACE_MINING)
    }

    override fun createJeiUITemplate(importItems: IItemHandlerModifiable, exportItems: IItemHandlerModifiable,
                                     importFluids: FluidTankList, exportFluids: FluidTankList, yOffset: Int): ModularUI.Builder
    {
        val builder = ModularUI.Builder(GuiTextures.BACKGROUND, 176, 156)
            .widget(RecipeProgressWidget(200, 45, 4, 34, 63, progressBarTexture(), progressBarMoveType(), recipeMap()))
            .widget(ImageWidget(9 + 18, 54, 18, 18, logoTexture()))
        addInventorySlotGroup(builder, importItems, importFluids, false, yOffset)
        addInventorySlotGroup(builder, exportItems, exportFluids, true, yOffset)
        return builder
    }

    override fun addInventorySlotGroup(builder: ModularUI.Builder,
                                       itemHandler: IItemHandlerModifiable, fluidHandler: FluidTankList,
                                       isOutputs: Boolean, yOffset: Int)
    {
        val startInputsX = 9
        val startOutputsX = startInputsX + 70
        if (!isOutputs)
        {
            // Item input slots.
            addSlot(builder, startInputsX + 18, 0, 0, itemHandler, fluidHandler, false, false)
            addSlot(builder, startInputsX + 18, 18, 1, itemHandler, fluidHandler, false, false)
        }
        else
        {
            // Item output slots.
            for (h in 0 .. 3)
            {
                for (w in 0 .. 3)
                {
                    val slotIdx = h * 4 + w
                    addSlot(builder, startOutputsX + w * 18, h * 18, slotIdx, itemHandler, fluidHandler, false, true)
                }
            }
        }
    }

    private fun logoTexture(): TextureArea = GTLiteGuiTextures.SPACE_ELEVATOR_LOGO

}