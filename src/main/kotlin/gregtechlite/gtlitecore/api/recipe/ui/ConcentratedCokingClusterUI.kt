package gregtechlite.gtlitecore.api.recipe.ui

import gregtech.api.capability.impl.FluidTankList
import gregtech.api.gui.GuiTextures
import gregtech.api.gui.ModularUI
import gregtech.api.gui.widgets.ProgressWidget
import gregtech.api.gui.widgets.ProgressWidget.MoveType
import gregtech.api.recipes.RecipeMap
import gregtech.api.recipes.ui.RecipeMapUI
import gregtechlite.gtlitecore.api.gui.GTLiteGuiTextures
import net.minecraftforge.items.IItemHandlerModifiable

@Suppress("UnstableApiUsage")
internal class ConcentratedCokingClusterUI<R : RecipeMap<*>>(recipeMap: R) : RecipeMapUI<R>(recipeMap, true, true, true, true, false)
{
    init
    {
        setItemSlotOverlay(GuiTextures.FURNACE_OVERLAY_1, false)
        setItemSlotOverlay(GuiTextures.FURNACE_OVERLAY_1, true)
        setFluidSlotOverlay(GuiTextures.FURNACE_OVERLAY_2, false)
        setFluidSlotOverlay(GuiTextures.FURNACE_OVERLAY_2, true)
    }

    override fun createJeiUITemplate(importItems: IItemHandlerModifiable, exportItems: IItemHandlerModifiable,
                                     importFluids: FluidTankList, exportFluids: FluidTankList, yOffset: Int): ModularUI.Builder
    {
        val builder = ModularUI.defaultBuilder(yOffset)
        builder.widget(ProgressWidget(200, 48 + 18, yOffset + 32, 20, 20, GTLiteGuiTextures.PROGRESS_BAR_COMPLEX_PYROLYSIS, MoveType.HORIZONTAL))
        addInventorySlotGroup(builder, importItems, importFluids, false, 9)
        addInventorySlotGroup(builder, exportItems, exportFluids, true, 9)
        return builder
    }

    override fun addInventorySlotGroup(builder: ModularUI.Builder, itemHandler: IItemHandlerModifiable,
                                       fluidHandler: FluidTankList, isOutputs: Boolean, yOffset: Int)
    {
        val startInputsX = 22
        val startInputsY = yOffset + 18
        if (!isOutputs)
        {
            for (h in 0..1)
            {
                addSlot(builder, startInputsX, startInputsY + h * 18, h, itemHandler, fluidHandler, false, false)
                addSlot(builder, startInputsX + 18, startInputsY + h * 18, h, itemHandler, fluidHandler, true, false)
            }
        }
        else
        {
            val startOutputsX = 94
            for (h in 0..1)
            {
                addSlot(builder, startOutputsX, startInputsY + h * 18, h, itemHandler, fluidHandler, false, true)
            }

            for (w in 0..2)
            {
                for (h in 0..1)
                {
                    val slotIdx = h * 3 + w
                    addSlot(builder, startOutputsX + 18 + 18 * w, startInputsY + h * 18, slotIdx, itemHandler, fluidHandler, true, false)
                }
            }
        }
    }
}