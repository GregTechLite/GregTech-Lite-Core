package gregtechlite.gtlitecore.api.recipe.ui

import gregtech.api.capability.impl.FluidTankList
import gregtech.api.gui.GuiTextures
import gregtech.api.gui.ModularUI
import gregtech.api.gui.widgets.RecipeProgressWidget
import gregtech.api.recipes.RecipeMap
import gregtech.api.recipes.ui.RecipeMapUI
import net.minecraftforge.items.IItemHandlerModifiable

@Suppress("UnstableApiUsage")
internal class AntimatterForgeUI<R : RecipeMap<*>>(recipeMap: R) : RecipeMapUI<R>(recipeMap, true, true, true, true, false)
{

    init
    {
        setItemSlotOverlay(GuiTextures.INT_CIRCUIT_OVERLAY, false)
    }

    override fun createJeiUITemplate(importItems: IItemHandlerModifiable, exportItems: IItemHandlerModifiable,
                                     importFluids: FluidTankList, exportFluids: FluidTankList, yOffset: Int): ModularUI.Builder
    {
        val builder = ModularUI.builder(GuiTextures.BACKGROUND, 176, 230)
            .widget(RecipeProgressWidget(200, 92, 43, 22, 22, progressBarTexture(), progressBarMoveType(), recipeMap()))
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
        val startInputsY2 = startInputsY1 + 18
        val startOutputsX = startInputsX + 107
        val startOutputsY = startInputsY2 + 18
        if (!isOutputs)
        {
            // Circuit (item) input slot.
            addSlot(builder, startInputsX + 18 * 3, startInputsY1, 0, itemHandler, fluidHandler, false, false)

            // Antimatter (fluid) input slot.
            addSlot(builder, startInputsX + 18 * 2, startInputsY1, 0, itemHandler, fluidHandler, true, false)

            // Fluid input slots.
            for (h in 0..4)
            {
                for (w in 0..3)
                {
                    val slotIdx = h * 4 + w + 1
                    addSlot(builder, startInputsX + 18 * w, startInputsY2 + 18 * h, slotIdx, itemHandler, fluidHandler, true, false)
                }
            }
        }
        else
        {
            // Fluid output slot.
            addSlot(builder, startOutputsX, startOutputsY, 0, itemHandler, fluidHandler, true, true)
        }
    }

}