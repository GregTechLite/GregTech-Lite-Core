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
        this.setItemSlotOverlay(GuiTextures.INT_CIRCUIT_OVERLAY, false, true)
        this.setItemSlotOverlay(GTLiteGuiTextures.RAW_ORE_OVERLAY, true)
        this.setFluidSlotOverlay(GuiTextures.HEATING_OVERLAY_2, false)
    }

    override fun createJeiUITemplate(importItems: IItemHandlerModifiable, exportItems: IItemHandlerModifiable,
                                     importFluids: FluidTankList, exportFluids: FluidTankList, yOffset: Int): ModularUI.Builder
    {
        val builder = ModularUI.Builder(GuiTextures.BACKGROUND, 176, 203)
            .widget(RecipeProgressWidget(200, 74 - 18 * 3 + 9, 45 - 18 - 6 - 2 - 2, 66, 66 + 10, GTLiteGuiTextures.PROGRESS_BAR_MINING_DRONE, progressBarMoveType(), recipeMap()))
        this.addInventorySlotGroup(builder, importItems, importFluids, false, yOffset)
        this.addInventorySlotGroup(builder, exportItems, exportFluids, true, yOffset)
        return builder
    }

    override fun addInventorySlotGroup(builder: ModularUI.Builder,
                                       itemHandler: IItemHandlerModifiable, fluidHandler: FluidTankList,
                                       isOutputs: Boolean, yOffset: Int)
    {

        val startOutputsX = 176 / 2 - 18
        val startOutputsY = 9
        if (isOutputs)
        {
            for (i in 0 .. 4) // Height.
            {
                for (j in 0 .. 4) // Width.
                {
                    val slotIndex = i * 5 + j
                    this.addSlot(builder, startOutputsX + 18 * j, startOutputsY + 18 * i,
                        slotIndex, itemHandler, fluidHandler, false, true)
                }
            }
        }
        else
        {
            val startInputsX = 14 - 8
            val startInputsY = startOutputsY + 18 * 2

            this.addSlot(builder, startInputsX, startInputsY, 0, itemHandler, fluidHandler, false, false)
            this.addSlot(builder, startInputsX + 18, startInputsY, 1, itemHandler, fluidHandler, false, false)
            this.addSlot(builder, startInputsX, startInputsY + 18, 0, itemHandler, fluidHandler, true, false)
        }

    }

}