package gregtechlite.gtlitecore.api.recipe.ui

import gregtech.api.capability.impl.FluidTankList
import gregtech.api.gui.GuiTextures
import gregtech.api.gui.ModularUI
import gregtech.api.gui.widgets.ProgressWidget
import gregtech.api.gui.widgets.RecipeProgressWidget
import gregtech.api.recipes.RecipeMap
import gregtech.api.recipes.ui.RecipeMapUI
import gregtechlite.gtlitecore.api.gui.GTLiteGuiTextures
import net.minecraftforge.items.IItemHandlerModifiable

@Suppress("UnstableApiUsage")
internal class StellarForgeUI<R: RecipeMap<*>>(recipeMap: R) : RecipeMapUI<R>(recipeMap, true, true, true, true, false)
{

    init
    {
        setItemSlotOverlay(GuiTextures.FURNACE_OVERLAY_1, false)
        setItemSlotOverlay(GuiTextures.FURNACE_OVERLAY_1, true)
        setFluidSlotOverlay(GuiTextures.FURNACE_OVERLAY_2, false)
        setFluidSlotOverlay(GuiTextures.FURNACE_OVERLAY_2, true)
        setProgressBar(GTLiteGuiTextures.PROGRESS_BAR_NOVA_EXPLOSION, ProgressWidget.MoveType.HORIZONTAL)
    }

    override fun createJeiUITemplate(importItems: IItemHandlerModifiable, exportItems: IItemHandlerModifiable,
                                     importFluids: FluidTankList, exportFluids: FluidTankList, yOffset: Int): ModularUI.Builder
    {
        val builder = ModularUI.Builder(GuiTextures.BACKGROUND, 176, 176 + 18 * 2 + 18)
            .widget(RecipeProgressWidget(200, 74, 50, 21, 21, progressBarTexture(), progressBarMoveType(), recipeMap()))
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
        val startInputsY2 = startInputsY1 + 18 * 3
        val startOutputsX = startInputsX + 3 * 18 + 34
        val startOutputsY = startInputsY1 + 3 * 18
        if (!isOutputs)
        {
            // Item inputs slots.
            for (h in 0..2)
            {
                for (w in 0..2)
                {
                    val slotIdx = h * 3 + w
                    addSlot(builder, startInputsX + 18 * w, startInputsY1 + 18 * h, slotIdx, itemHandler, fluidHandler, false, false)
                }
            }

            // Fluid inputs slots.
            for (h in 0..2)
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
            // Item outputs slots.
            for (h in 0..2)
            {
                for (w in 0..2)
                {
                    val slotIdx = h * 3 + w
                    addSlot(builder, startOutputsX + 18 * w, startInputsY1 + 18 * h, slotIdx, itemHandler, fluidHandler, false, true)
                }
            }
            // Fluid outputs slots.
            for (h in 0..2)
            {
                for (w in 0..2)
                {
                    val slotIdx = h * 3 + w
                    addSlot(builder, startOutputsX + 18 * w, startOutputsY + 18 * h, slotIdx, itemHandler, fluidHandler, true, true)
                }
            }
        }
    }

}