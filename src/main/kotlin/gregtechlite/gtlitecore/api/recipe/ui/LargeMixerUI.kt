package gregtechlite.gtlitecore.api.recipe.ui

import gregtech.api.capability.impl.FluidTankList
import gregtech.api.gui.GuiTextures
import gregtech.api.gui.ModularUI
import gregtech.api.gui.widgets.RecipeProgressWidget
import gregtech.api.recipes.RecipeMap
import gregtech.api.recipes.ui.RecipeMapUI
import net.minecraftforge.items.IItemHandlerModifiable

@Suppress("UnstableApiUsage")
internal class LargeMixerUI<R : RecipeMap<*>>(recipeMap: R) : RecipeMapUI<R>(recipeMap, true, true, true, true, false)
{

    init
    {
        setItemSlotOverlay(GuiTextures.DUST_OVERLAY, false)
        setItemSlotOverlay(GuiTextures.DUST_OVERLAY, true)
    }

    override fun createJeiUITemplate(importItems: IItemHandlerModifiable, exportItems: IItemHandlerModifiable,
                                     importFluids: FluidTankList, exportFluids: FluidTankList, yOffset: Int): ModularUI.Builder
    {
        val builder = ModularUI.builder(GuiTextures.BACKGROUND, 176, 203)
            .widget(RecipeProgressWidget(200, 74, 45, 22, 22, progressBarTexture(), progressBarMoveType(), recipeMap()))
        addInventorySlotGroup(builder, importItems, importFluids, false, yOffset)
        addInventorySlotGroup(builder, exportItems, exportFluids, true, yOffset)
        return builder
    }

    override fun addInventorySlotGroup(builder: ModularUI.Builder,
                                       itemHandler: IItemHandlerModifiable, fluidHandler: FluidTankList,
                                       isOutputs: Boolean, yOffset: Int)
    {
        val startInputsX = 14
        val startInputsY1 = 9
        val startInputsY2 = startInputsY1 + 54
        val startOutputsX = startInputsX + 88
        val startOutputsY = startInputsY2 - 18
        if (!isOutputs)
        {
            // Item input slots.
            for (h in 0..2)
            {
                for (w in 0..2)
                {
                    val slotIdx = h * 3 + w
                    addSlot(builder, startInputsX + 18 * w, startInputsY1 + 18 * h, slotIdx, itemHandler, fluidHandler, false, false)
                }
            }
            // Fluid input slots.
            for (h in 0..1)
            {
                for (w in 0..2)
                {
                    val slotIdx = h * 3 + w
                    addSlot(builder, startInputsX + 18 * w, startInputsY2 + 18 * h, slotIdx, itemHandler, fluidHandler, true, false)
                }
            }
        }
        else
        {
            // Item output slot.
            addSlot(builder, startOutputsX, startOutputsY, 0, itemHandler, fluidHandler, false, true)
            // Fluid output slot.
            addSlot(builder, startOutputsX + 18, startOutputsY, 0, itemHandler, fluidHandler, true, true)
        }
    }


}