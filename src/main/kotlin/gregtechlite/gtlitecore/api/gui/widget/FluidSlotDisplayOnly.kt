package gregtechlite.gtlitecore.api.gui.widget

import com.cleanroommc.modularui.api.ITheme
import com.cleanroommc.modularui.api.widget.Interactable
import com.cleanroommc.modularui.drawable.GuiDraw
import com.cleanroommc.modularui.drawable.text.TextRenderer
import com.cleanroommc.modularui.integration.recipeviewer.RecipeViewerGhostIngredientSlot
import com.cleanroommc.modularui.integration.recipeviewer.RecipeViewerIngredientProvider
import com.cleanroommc.modularui.screen.viewport.ModularGuiContext
import com.cleanroommc.modularui.theme.WidgetThemeEntry
import com.cleanroommc.modularui.utils.Color
import com.cleanroommc.modularui.widget.Widget
import gregtech.client.utils.RenderUtil
import gregtechlite.gtlitecore.api.mui.sync.FluidDisplaySyncHandler
import net.minecraftforge.fluids.FluidStack

class FluidSlotDisplayOnly : Widget<FluidSlotDisplayOnly>(),
    Interactable, RecipeViewerGhostIngredientSlot<FluidStack>, RecipeViewerIngredientProvider
{
    private var syncHandler: FluidDisplaySyncHandler? = null
    private var textRenderer: TextRenderer = TextRenderer()

    override fun onInit()
    {
        this.textRenderer.setShadow(true)
        this.textRenderer.setScale(.5F)
        this.textRenderer.setColor(Color.WHITE.main)
    }

    fun getFluidStack(): FluidStack?
    {
        return if (this.syncHandler == null) null else this.syncHandler!!.value
    }

    override fun draw(context: ModularGuiContext, widgetTheme: WidgetThemeEntry<*>)
    {
        val content = getFluidStack()
        if (content != null)
        {
            GuiDraw.drawFluidTexture(
                content,
                1.0F,
                1.0F,
                area.width - 2F,
                area.height - 2F,
                0F
            )
        }

        RenderUtil.handleJEIGhostSlotOverlay(this, widgetTheme)
    }

    fun syncHandler(handler: FluidDisplaySyncHandler?): FluidSlotDisplayOnly
    {
        this.syncHandler = handler
        return this
    }

    override fun getWidgetThemeInternal(theme: ITheme): WidgetThemeEntry<*>
    {
        return theme.fluidSlotTheme
    }

    override fun setGhostIngredient(ingredient: FluidStack)
    {
    }

    override fun castGhostIngredientIfValid(ingredient: Any): FluidStack?
    {
        return null
    }

    override fun getIngredient(): Any?
    {
        return this.getFluidStack()
    }
}