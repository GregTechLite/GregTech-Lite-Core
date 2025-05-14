package magicbook.gtlitecore.api.recipe.ui

import gregtech.api.capability.impl.FluidTankList
import gregtech.api.gui.GuiTextures
import gregtech.api.gui.ModularUI
import gregtech.api.gui.widgets.ProgressWidget
import gregtech.api.gui.widgets.RecipeProgressWidget
import gregtech.api.recipes.RecipeMap
import gregtech.api.recipes.ui.RecipeMapUI
import magicbook.gtlitecore.api.gui.GTLiteGuiTextures
import net.minecraftforge.items.IItemHandlerModifiable
import org.jetbrains.annotations.ApiStatus

@ApiStatus.Internal
@Suppress("UnstableApiUsage", "MISSING_DEPENDENCY_CLASS")
class StellarForgeUI<R: RecipeMap<*>>(recipeMap: R) : RecipeMapUI<R>(recipeMap, true, true, true, true, false)
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
            .widget(RecipeProgressWidget(200, 176 / 2 - 18 + 4, 18 * 3 - 4, 21, 21, progressBarTexture(), progressBarMoveType(), recipeMap()))
        addInventorySlotGroup(builder, importItems, importFluids, false, yOffset)
        addInventorySlotGroup(builder, exportItems, exportFluids, true, yOffset)
        return builder
    }

    override fun addInventorySlotGroup(builder: ModularUI.Builder,
                                       itemHandler: IItemHandlerModifiable, fluidHandler: FluidTankList,
                                       isOutputs: Boolean, yOffset: Int)
    {
        val startInputsX1 = 14
        val startInputsY1 = 9
        if (!isOutputs)
        {
            // Item inputs slots
            for (i in 0..2) // Height
            {
                for (j in 0..2) // Width
                {
                    val slotIndex = i * 3 + j
                    addSlot(builder, startInputsX1 + 18 * j, startInputsY1 + 18 * i, slotIndex, itemHandler, fluidHandler, false, false)
                }
            }
            // Fluid inputs slots
            val startInputsY2 = startInputsY1 + 18 * 3
            for (i in 0..2) // Height
            {
                for (j in 0..2) // Width
                {
                    val slotIndex = i * 3 + j
                    addSlot(builder, startInputsX1 + 18 * j, startInputsY2 + 18 * i, slotIndex, itemHandler, fluidHandler, true, false)
                }
            }
        }
        else
        {
            // Item outputs slots
            val startInputsX2 = startInputsX1 + 3 * 18 + 34
            for (i in 0..2) // Height
            {
                for (j in 0..2) // Width
                {
                    val slotIndex = i * 3 + j
                    addSlot(builder, startInputsX2 + 18 * j, startInputsY1 + 18 * i, slotIndex, itemHandler, fluidHandler, false, true)
                }
            }
            // Fluid outputs slots
            val startInputsY3 = startInputsY1 + 3 * 18
            for (i in 0..2) // Height
            {
                for (j in 0..2) // Width
                {
                    val slotIndex = i * 3 + j
                    addSlot(builder, startInputsX2 + 18 * j, startInputsY3 + 18 * i, slotIndex, itemHandler, fluidHandler, true, true)
                }
            }
        }
    }

}