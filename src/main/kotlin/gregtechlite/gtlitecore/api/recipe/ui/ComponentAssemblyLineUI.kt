package gregtechlite.gtlitecore.api.recipe.ui

import gregtech.api.capability.impl.FluidTankList
import gregtech.api.gui.GuiTextures
import gregtech.api.gui.ModularUI
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
            .widget(ProgressWidget(200, 70, 12, 72, 40, GTLiteGuiTextures.PROGRESS_BAR_COMPONENT_ASSEMBLY_LINE_1, ProgressWidget.MoveType.HORIZONTAL))
            .widget(ProgressWidget(200, 131, 15, 3, 12, GTLiteGuiTextures.PROGRESS_BAR_COMPONENT_ASSEMBLY_LINE_2, ProgressWidget.MoveType.VERTICAL))
        this.addInventorySlotGroup(builder, importItems, importFluids, false, yOffset)
        this.addInventorySlotGroup(builder, exportItems, exportFluids, true, yOffset)
        return builder
    }

    override fun addInventorySlotGroup(builder: ModularUI.Builder,
                                       itemHandler: IItemHandlerModifiable, fluidHandler: FluidTankList,
                                       isOutputs: Boolean, yOffset: Int)
    {
        val startInputsX1 = 70 - 3 * 18
        val startInputsY = 45 - 2 * 18
        if (!isOutputs)
        {
            // Item input slots.
            for (i in 0..3)
            {
                for (j in 0..2)
                {
                    val slotIndex = i * 3 + j
                    addSlot(builder, startInputsX1 + 18 * j, startInputsY + 18 * i, slotIndex, itemHandler, fluidHandler, false, false)
                }
            }
            // fluid input slots
            val startInputsX2 = startInputsX1 + 18 * 4
            for (i in 0..2)
            {
                for (j in 0..3)
                {
                    val slotIndex = i * 4 + j
                    addSlot(builder, startInputsX2 + 18 * j, startInputsY + 18 + 18 * i, slotIndex, itemHandler, fluidHandler, true, false)
                }
            }
        }
        else
        {
            // Output slots.
            addSlot(builder, startInputsX1 + 18 * 7, 9, 0, itemHandler, fluidHandler, false, true)
        }
    }

}