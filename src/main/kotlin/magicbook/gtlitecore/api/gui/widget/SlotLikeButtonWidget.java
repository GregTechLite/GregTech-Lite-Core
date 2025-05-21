package magicbook.gtlitecore.api.gui.widget;

import com.cleanroommc.modularui.api.ITheme;
import com.cleanroommc.modularui.api.drawable.IDrawable;
import com.cleanroommc.modularui.drawable.DynamicDrawable;
import com.cleanroommc.modularui.drawable.GuiDraw;
import com.cleanroommc.modularui.drawable.ItemDrawable;
import com.cleanroommc.modularui.integration.jei.JeiIngredientProvider;
import com.cleanroommc.modularui.screen.viewport.ModularGuiContext;
import com.cleanroommc.modularui.theme.WidgetSlotTheme;
import com.cleanroommc.modularui.theme.WidgetTheme;
import com.cleanroommc.modularui.widgets.ButtonWidget;
import gregtech.api.items.metaitem.MetaItem;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

/**
 * Button widget that looks like and partially behaves like item slot. Draws supplied {@code ItemStack},
 * draws white overlay when hovered and allows interaction with JEI.
 */
public class SlotLikeButtonWidget extends ButtonWidget<SlotLikeButtonWidget> implements JeiIngredientProvider
{

    private final Supplier<ItemStack> itemSupplier;
    private final IDrawable itemDrawable;

    /**
     * @param itemStack {@code ItemStack} to "put into the slot".
     */
    public SlotLikeButtonWidget(ItemStack itemStack)
    {
        this.itemSupplier = () -> itemStack;
        this.itemDrawable = new ItemDrawable(itemStack).asIcon();
        disableHoverBackground();
    }

    public SlotLikeButtonWidget(MetaItem<?>.MetaValueItem metaItem)
    {
        this.itemSupplier = metaItem::getStackForm;
        this.itemDrawable = new ItemDrawable(metaItem.getStackForm()).asIcon();
        disableHoverBackground();
    }

    /**
     * @param itemSupplier  Supplier that specifies {@code ItemStack} to be present in the slot.
     */
    public SlotLikeButtonWidget(Supplier<ItemStack> itemSupplier)
    {
        this.itemSupplier = itemSupplier;
        this.itemDrawable = new DynamicDrawable(() -> new ItemDrawable(itemSupplier.get())).asIcon();
        disableHoverBackground();
    }

    @Override
    public WidgetTheme getWidgetThemeInternal(ITheme theme)
    {
        return theme.getItemSlotTheme();
    }

    @Override
    public void draw(ModularGuiContext context, WidgetTheme widgetTheme)
    {
        super.draw(context, widgetTheme);
        this.itemDrawable.drawAtZero(context, getArea(), widgetTheme);
        if (isHovering())
        {
            GlStateManager.disableLighting();
            GlStateManager.enableBlend();
            GlStateManager.colorMask(true, true, true, false);
            GuiDraw.drawRect(1, 1, 16, 16, getHoverColor(widgetTheme));
            GlStateManager.colorMask(true, true, true, true);
            GlStateManager.disableBlend();
        }
    }

    private int getHoverColor(WidgetTheme widgetTheme)
    {
        if (widgetTheme instanceof WidgetSlotTheme)
        {
            WidgetSlotTheme slotTheme = (WidgetSlotTheme) widgetTheme;
            return slotTheme.getSlotHoverColor();
        }
        return ITheme.getDefault()
                .getItemSlotTheme()
                .getSlotHoverColor();
    }

    @Nullable
    @Override
    public Object getIngredient()
    {
        return itemSupplier.get();
    }

}
