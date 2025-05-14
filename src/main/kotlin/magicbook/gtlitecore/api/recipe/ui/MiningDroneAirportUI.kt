package magicbook.gtlitecore.api.recipe.ui

import gregtech.api.capability.impl.FluidTankList
import gregtech.api.gui.GuiTextures
import gregtech.api.gui.ModularUI
import gregtech.api.gui.widgets.RecipeProgressWidget
import gregtech.api.recipes.RecipeMap
import gregtech.api.recipes.ui.RecipeMapUI
import magicbook.gtlitecore.api.gui.GTLiteGuiTextures
import net.minecraftforge.items.IItemHandlerModifiable
import org.jetbrains.annotations.ApiStatus

@ApiStatus.Internal
@Suppress("UnstableApiUsage", "MISSING_DEPENDENCY_CLASS")
class MiningDroneAirportUI<R: RecipeMap<*>>(recipeMap: R) : RecipeMapUI<R>(recipeMap, true, true, true, true, false)
{

    init
    {
        setFluidSlotOverlay(GuiTextures.FURNACE_OVERLAY_2, false)
    }

    override fun createJeiUITemplate(importItems: IItemHandlerModifiable, exportItems: IItemHandlerModifiable,
                                     importFluids: FluidTankList, exportFluids: FluidTankList, yOffset: Int): ModularUI.Builder
    {
        val builder = ModularUI.Builder(GuiTextures.BACKGROUND, 176, 156)
            .widget(RecipeProgressWidget(200, 18 + 27, 0 + 4, 34, 63, GTLiteGuiTextures.PROGRESS_BAR_MINING_DRONE, progressBarMoveType(), recipeMap()))
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
            // Item input slots (2x2) for mining drones, int circuits and other items
            // if needed (these slots are not needed often).
            for (i in 0 .. 1) // Height
            {
                for (j in 0 .. 1) // Weight
                {
                    val slotIndex = i * 2 + j
                    this.addSlot(builder, j * 18 + 9, i * 18, slotIndex, itemHandler, fluidHandler, false, false)
                }
            }
            // Fluid input slots for rocket fuels import (and some booster).
            // this.addSlot(builder, 9, 45 + 9, 0, itemHandler, fluidHandler, true, false)
            this.addSlot(builder, 9 + 18, 45 + 9, 0, itemHandler, fluidHandler, true, false)

        }
        else
        {
            // Item output slots (4x4) for all ores which will be exports.
            for (i in 0 .. 3) // Height
            {
                for (j in 0 .. 3) // Weight
                {
                    val slotIndex = i * 4 + j
                    this.addSlot(builder, 79 + j * 18, i * 18, slotIndex, itemHandler, fluidHandler, false, true)
                }
            }

        }

    }

}