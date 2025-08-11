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
        val startInputsX1 = 14
        val startInputsY1 = 9
        if (!isOutputs)
        {
            // Item input slots.
            for (i in 0..2) // Height.
            {
                for (j in 0..2) // Width.
                {
                    val slotIndex = i * 3 + j
                    this.addSlot(builder, startInputsX1 + 18 * j, startInputsY1 + 18 * i,
                        slotIndex, itemHandler, fluidHandler, false, false)
                }
            }
            // Fluid input slots.
            val startInputsY2 = startInputsY1 + 18 * 3
            for (i in 0..1) // Height.
            {
                for (j in 0..2) // Width.
                {
                    val slotIndex = i * 3 + j
                    this.addSlot(builder, startInputsX1 + 18 * j, startInputsY2 + 18 * i,
                        slotIndex, itemHandler, fluidHandler, true, false)
                }
            }
        }
        else
        {
            val startInputsX2 = startInputsX1 + 18 * 3 + 34
            val startInputsY3 = startInputsY1 + 18 * 2
            this.addSlot(builder, startInputsX2, startInputsY3, 0,
                itemHandler, fluidHandler, false, true)
            this.addSlot(builder, startInputsX2 + 18, startInputsY3, 0,
                itemHandler, fluidHandler, true, true)
        }
    }


}