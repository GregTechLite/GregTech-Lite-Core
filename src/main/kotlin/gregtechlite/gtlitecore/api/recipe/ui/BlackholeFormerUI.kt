package gregtechlite.gtlitecore.api.recipe.ui

import gregtech.api.capability.impl.FluidTankList
import gregtech.api.gui.GuiTextures
import gregtech.api.gui.ModularUI
import gregtech.api.gui.widgets.ProgressWidget.MoveType
import gregtech.api.gui.widgets.SlotWidget
import gregtech.api.recipes.RecipeMap
import gregtech.api.recipes.ui.RecipeMapUI
import gregtechlite.gtlitecore.api.gui.GTLiteGuiTextures
import net.minecraftforge.items.IItemHandlerModifiable

@Suppress("UnstableApiUsage")
internal class BlackholeFormerUI<R : RecipeMap<*>>(recipeMap: R) : RecipeMapUI<R>(recipeMap, true, true, true, true, false)
{
    init
    {
        setProgressBar(GTLiteGuiTextures.PROGRESS_BAR_BLACKHOLE_FORMING, MoveType.HORIZONTAL)
    }

    override fun addSlot(builder: ModularUI.Builder, x: Int, y: Int, slotIndex: Int, itemHandler: IItemHandlerModifiable?,
                         fluidHandler: FluidTankList?, isFluid: Boolean, isOutputs: Boolean, )
    {
        val slotWidget = SlotWidget(itemHandler, slotIndex, x, y, true, !isOutputs)
        if (isOutputs)
        {
            slotWidget.setBackgroundTexture(*arrayOf(GuiTextures.SLOT, GuiTextures.PRESS_OVERLAY_3))
        }
        else if (slotIndex != 0 && slotIndex != 3)
        {
            if (slotIndex != 1 && slotIndex != 4)
            {
                if (slotIndex == 2 || slotIndex == 5)
                {
                    slotWidget.setBackgroundTexture(*arrayOf(GuiTextures.SLOT, GuiTextures.PRESS_OVERLAY_1))
                }
            }
            else
            {
                slotWidget.setBackgroundTexture(*arrayOf(GuiTextures.SLOT, GuiTextures.PRESS_OVERLAY_4))
            }
        }
        else
        {
            slotWidget.setBackgroundTexture(*arrayOf(GuiTextures.SLOT, GuiTextures.PRESS_OVERLAY_2))
        }
        builder.widget(slotWidget)
    }
}