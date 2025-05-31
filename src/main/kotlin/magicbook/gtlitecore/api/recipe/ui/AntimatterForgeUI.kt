package magicbook.gtlitecore.api.recipe.ui

import gregtech.api.capability.impl.FluidTankList
import gregtech.api.gui.GuiTextures
import gregtech.api.gui.ModularUI
import gregtech.api.gui.widgets.RecipeProgressWidget
import gregtech.api.recipes.RecipeMap
import gregtech.api.recipes.ui.RecipeMapUI
import net.minecraftforge.items.IItemHandlerModifiable
import org.jetbrains.annotations.ApiStatus

@ApiStatus.Internal
@Suppress("UnstableApiUsage")
class AntimatterForgeUI<R : RecipeMap<*>>(recipeMap: R) : RecipeMapUI<R>(recipeMap, true, true, true, true, false)
{

    init
    {
        setItemSlotOverlay(GuiTextures.INT_CIRCUIT_OVERLAY, false)
    }

    override fun createJeiUITemplate(importItems: IItemHandlerModifiable, exportItems: IItemHandlerModifiable,
                                     importFluids: FluidTankList, exportFluids: FluidTankList, yOffset: Int): ModularUI.Builder
    {
        val builder = ModularUI.builder(GuiTextures.BACKGROUND, 176, 176 + 18 * 3)
            .widget(RecipeProgressWidget(200, 176 / 2 - 14 + 18, 18 * 2 + 7 , 22, 22, progressBarTexture(), progressBarMoveType(), recipeMap()))
        addInventorySlotGroup(builder, importItems, importFluids, false, yOffset)
        addInventorySlotGroup(builder, exportItems, exportFluids, true, yOffset)
        return builder
    }

    override fun addInventorySlotGroup(builder: ModularUI.Builder,
                                       itemHandler: IItemHandlerModifiable, fluidHandler: FluidTankList,
                                       isOutputs: Boolean, yOffset: Int)
    {
        if (!isOutputs)
        {
            addSlot(builder, 14 + 18 * 3, 9, 0, itemHandler, fluidHandler, false, false)
            addSlot(builder, 14 + 18 * 2, 9, 0, itemHandler, fluidHandler, true, false)

            val startInputsX1 = 14
            val startInputsY1 = 9 + 18
            for (i in 0..4) // Height
            {
                for (j in 0..3) // Width
                {
                    val slotIndex = i * 4 + j + 1 // for antimatter slots.
                    addSlot(builder, startInputsX1 + 18 * j, startInputsY1 + 18 * i, slotIndex, itemHandler, fluidHandler, true, false)
                }
            }
        }
        else
        {
            val startInputsX2 = 14 + 18 * 4 + 35
            val startInputsY2 = 9 + 18 * 2
            addSlot(builder, startInputsX2, startInputsY2, 0, itemHandler, fluidHandler, true, true)
        }
    }

}