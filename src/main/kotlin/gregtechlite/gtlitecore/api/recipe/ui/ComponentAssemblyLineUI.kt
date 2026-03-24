package gregtechlite.gtlitecore.api.recipe.ui

import gregtech.api.capability.impl.FluidTankList
import gregtech.api.gui.GuiTextures
import gregtech.api.gui.ModularUI
import gregtech.api.gui.resources.TextureArea
import gregtech.api.gui.widgets.ProgressWidget
import gregtech.api.recipes.RecipeMap
import gregtech.api.recipes.ui.RecipeMapUI
import gregtechlite.gtlitecore.api.gui.GTLiteGuiTextures
import net.minecraftforge.items.IItemHandlerModifiable

@Suppress("UnstableApiUsage")
internal class ComponentAssemblyLineUI<R: RecipeMap<*>>(recipeMap: R) : RecipeMapUI<R>(recipeMap, true, true, true, true, false)
{

    override fun createJeiUITemplate(importItems: IItemHandlerModifiable, exportItems: IItemHandlerModifiable,
                                     importFluids: FluidTankList, exportFluids: FluidTankList, yOffset: Int): ModularUI.Builder
    {
        val builder = ModularUI.builder(GuiTextures.BACKGROUND, 176, 176)
            .widget(ProgressWidget(200, 70, 12, 72, 40, progressBarTexture1(), progressBarMoveType1()))
            .widget(ProgressWidget(200, 131, 15, 3, 12, progressBarTexture2(), progressBarMoveType2()))
        addInventorySlotGroup(builder, importItems, importFluids, false, yOffset)
        addInventorySlotGroup(builder, exportItems, exportFluids, true, yOffset)
        return builder
    }

    override fun addInventorySlotGroup(builder: ModularUI.Builder,
                                       itemHandler: IItemHandlerModifiable, fluidHandler: FluidTankList,
                                       isOutputs: Boolean, yOffset: Int)
    {
        val startInputsX1 = 16
        val startInputsX2 = startInputsX1 + 72
        val startInputsY1 = 9
        val startInputsY2 = startInputsY1 + 18
        val startOutputsX = startInputsX2 + 54
        if (!isOutputs)
        {
            // Item input slots.
            for (h in 0..3)
            {
                for (w in 0..2)
                {
                    val slotIdx = h * 3 + w
                    addSlot(builder, startInputsX1 + 18 * w, startInputsY1 + 18 * h, slotIdx, itemHandler, fluidHandler, false, false)
                }
            }
            // Fluid input slots.
            for (h in 0..2)
            {
                for (w in 0..3)
                {
                    val slotIdx = h * 4 + w
                    addSlot(builder, startInputsX2 + 18 * w, startInputsY2 + 18 * h, slotIdx, itemHandler, fluidHandler, true, false)
                }
            }
        }
        else
        {
            // Item output slot.
            addSlot(builder, startOutputsX, startInputsY1, 0, itemHandler, fluidHandler, false, true)
        }
    }

    private fun progressBarTexture1(): TextureArea = GTLiteGuiTextures.PROGRESS_BAR_COMPONENT_ASSEMBLY_LINE_1

    private fun progressBarMoveType1(): ProgressWidget.MoveType = ProgressWidget.MoveType.HORIZONTAL

    private fun progressBarTexture2(): TextureArea = GTLiteGuiTextures.PROGRESS_BAR_COMPONENT_ASSEMBLY_LINE_2

    private fun progressBarMoveType2(): ProgressWidget.MoveType = ProgressWidget.MoveType.VERTICAL

}