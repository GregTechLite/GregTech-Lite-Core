package gregtechlite.gtlitecore.api.gui.widget

import com.cleanroommc.modularui.api.ITheme
import com.cleanroommc.modularui.api.drawable.IDrawable
import com.cleanroommc.modularui.drawable.DynamicDrawable
import com.cleanroommc.modularui.drawable.GuiDraw
import com.cleanroommc.modularui.drawable.ItemDrawable
import com.cleanroommc.modularui.integration.jei.JeiIngredientProvider
import com.cleanroommc.modularui.screen.viewport.ModularGuiContext
import com.cleanroommc.modularui.theme.WidgetSlotTheme
import com.cleanroommc.modularui.theme.WidgetTheme
import com.cleanroommc.modularui.widgets.ButtonWidget
import gregtech.api.items.metaitem.MetaItem
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.item.ItemStack

/**
 * Button widget that looks like and partially behaves like item slot.
 * Draws supplied [ItemStack], draws white overlay when hovered and allows interaction with JEI.
 */
class SlotLikeButtonWidget : ButtonWidget<SlotLikeButtonWidget>, JeiIngredientProvider
{

    private val itemSupplier: () -> ItemStack
    private val itemDrawable: IDrawable

    /**
     * @param itemStack The [ItemStack] to "put into the slot".
     */
    constructor(itemStack: ItemStack)
    {
        this.itemSupplier = { itemStack }
        this.itemDrawable = ItemDrawable(itemStack).asIcon()
        this.disableHoverBackground()
    }

    /**
     * @param metaItem The [MetaItem] to specify the correspondenced [ItemStack] to put into the slot.
     */
    constructor(metaItem: MetaItem<*>.MetaValueItem)
    {
        this.itemSupplier = { metaItem.stackForm }
        this.itemDrawable = ItemDrawable(metaItem.stackForm).asIcon()
        this.disableHoverBackground()
    }

    /**
     * @param itemSupplier Supplier that specifies `ItemStack` to be present in the slot.
     */
    constructor(itemSupplier: () -> ItemStack)
    {
        this.itemSupplier = itemSupplier
        this.itemDrawable = DynamicDrawable { ItemDrawable(itemSupplier.invoke()) }.asIcon()
        this.disableHoverBackground()
    }

    override fun getWidgetThemeInternal(theme: ITheme): WidgetTheme?
    {
        return theme.itemSlotTheme
    }

    override fun draw(context: ModularGuiContext?, widgetTheme: WidgetTheme?)
    {
        super.draw(context, widgetTheme)
        this.itemDrawable.drawAtZero(context, area, widgetTheme)
        if (this.isHovering)
        {
            GlStateManager.disableLighting()
            GlStateManager.enableBlend()
            GlStateManager.colorMask(true, true, true, false)
            GuiDraw.drawRect(1f, 1f, 16f, 16f, getHoverColor(widgetTheme))
            GlStateManager.colorMask(true, true, true, true)
            GlStateManager.disableBlend()
        }
    }

    private fun getHoverColor(widgetTheme: WidgetTheme?): Int
    {
        if (widgetTheme is WidgetSlotTheme)
        {
            val slotTheme = widgetTheme
            return slotTheme.slotHoverColor
        }
        return ITheme.getDefault().itemSlotTheme.slotHoverColor
    }

    override fun getIngredient(): Any? = itemSupplier.invoke()

}
