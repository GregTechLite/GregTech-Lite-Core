package gregtechlite.gtlitecore.api.recipe.ui

import gregtech.api.capability.impl.FluidTankList
import gregtech.api.gui.GuiTextures
import gregtech.api.gui.ModularUI
import gregtech.api.gui.widgets.RecipeProgressWidget
import gregtech.api.recipes.RecipeMap
import gregtech.api.recipes.ui.RecipeMapUI
import net.minecraftforge.items.IItemHandlerModifiable

@Suppress("UnstableApiUsage")
internal class IntegratedOreProcessorUI<R: RecipeMap<*>>(recipeMap: R) : RecipeMapUI<R>(recipeMap, true, true, true, true, false)
{

    init
    {
        setItemSlotOverlay(GuiTextures.DUST_OVERLAY, true)
        setProgressBarTexture(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE)
    }

    override fun createJeiUITemplate(importItems: IItemHandlerModifiable, exportItems: IItemHandlerModifiable,
                                     importFluids: FluidTankList, exportFluids: FluidTankList, yOffset: Int): ModularUI.Builder
    {
        val builder = ModularUI.Builder(GuiTextures.BACKGROUND, 176, 156)
            .widget(RecipeProgressWidget(200, 70, 25, 22, 22, progressBarTexture(), progressBarMoveType(), recipeMap()))
        addInventorySlotGroup(builder, importItems, importFluids, false, yOffset)
        addInventorySlotGroup(builder, exportItems, exportFluids, true, yOffset)
        return builder
    }

    override fun addInventorySlotGroup(builder: ModularUI.Builder,
                                       itemHandler: IItemHandlerModifiable, fluidHandler: FluidTankList,
                                       isOutputs: Boolean, yOffset: Int)
    {
        val startInputsX = 27
        val startInputsY = 36
        val startOutputsX = startInputsX + 70
        if (!isOutputs)
        {
            // Item input slots.
            for (h in 0..1)
            {
                for (w in 0..1)
                {
                    val slotIdx = h * 2 + w
                    addSlot(builder, startInputsX + w * 18, h * 18, slotIdx, itemHandler, fluidHandler, false, false)
                }
            }
            // Fluid input slots.
            for (h in 0..1)
            {
                for (w in 0..1)
                {
                    val slotIdx = h * 2 + w
                    addSlot(builder, startInputsX + w * 18, startInputsY + h * 18, slotIdx, itemHandler, fluidHandler, true, false)
                }
            }
        }
        else
        {
            // Item output slots.
            for (h in 0..3)
            {
                for (w in 0..2)
                {
                    val slotIdx = h * 3 + w
                    addSlot(builder, startOutputsX + 18 * w, h * 18, slotIdx, itemHandler, fluidHandler, false, true)
                }
            }
        }
    }

}