package gregtechlite.gtlitecore.api.gui.template

import com.cleanroommc.modularui.api.IPanelHandler
import com.cleanroommc.modularui.api.drawable.IKey
import com.cleanroommc.modularui.api.widget.IWidget
import com.cleanroommc.modularui.drawable.ItemDrawable
import com.cleanroommc.modularui.widgets.ButtonWidget
import com.cleanroommc.modularui.widgets.layout.Flow
import gregtech.api.mui.GTGuiTextures
import net.minecraft.item.ItemStack

@Suppress("UnstableApiUsage")
object ComposableUITemplate
{

    /**
     * Create a title row for configuration screen contexts.
     *
     * @param iconStack The [ItemStack] which will be the icon of the configuration screen.
     */
    fun configScreenTitle(iconStack: ItemStack): Flow = Flow.row()
        .pos(4, 4)
        .height(16)
        .coverChildrenWidth()
        .child(ItemDrawable(iconStack).asWidget()
                   .size(16)
                   .marginRight(4))
        .child(IKey.lang("gtlitecore.ui.configuration_screen.title").asWidget()
                   .heightRel(1.0f))

    /**
     * Create a button for common MTE which required add configuration screen.
     *
     * As `flexButton` part for default situations in UI contexts.
     *
     * @param syncHandler The sync handler of the configuration screen panel.
     */
    fun configScreenButton(syncHandler: IPanelHandler): IWidget = ButtonWidget()
        .size(18)
        .overlay(GTGuiTextures.FILTER_SETTINGS_OVERLAY.asIcon().size(16))
        .addTooltipLine(IKey.lang("gtlitecore.ui.configuration_screen.button"))
        .onMousePressed { i ->
            if (syncHandler.isPanelOpen)
                syncHandler.closePanel()
            else
                syncHandler.openPanel()
            return@onMousePressed true
        }

}