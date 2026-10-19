package gregtechlite.gtlitecore.mixins.jei;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import gregtechlite.gtlitecore.core.GTLiteConfigHolder;
import gregtechlite.gtlitecore.mixins.hooks.Compat;
import mezz.jei.plugins.vanilla.ingredients.item.ItemStackRenderer;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

/**
 * Let the item count font renderer be scalable when the stack number is too big, and format the
 * stack count to a compact form (like "m", "g", "k", e.t.c).
 *
 * @author Magic_Sweepy
 */
@Compat(modId = { "jei", "gregtech" })
@Mixin(value = ItemStackRenderer.class, remap = false)
public class MixinItemStackRenderer
{
    /**
     * @author Magic_Sweepy
     * @reason Replace JEI's {@code CountUtil.renderCountString} effect with scaleable string.
     */
    @WrapOperation(method = "render*",
                   at = @At(value = "INVOKE",
                            target = "Lmezz/jei/util/CountUtil;renderCountString(Lnet/minecraft/client/gui/FontRenderer;JIIZ)V"))
    private void gtlitecore$renderScaledCount(FontRenderer fontRenderer, long count, int x, int y, boolean shadow,
                                              Operation<Void> original,
                                              @Local(index = 4, argsOnly = true) ItemStack ingredient)
    {
        if (GTLiteConfigHolder.compat.jeiCompat.scaleableFontRenderer)
        {
            gtlitecore$renderItemAndEffectIntoGUI(fontRenderer, ingredient, x, y);
        }
    }

    /**
     * @author Magic_Sweepy
     * @reason Skips the vanilla count overlay when the scaled count was already drawn.
     */
    @WrapOperation(method = "render*",
                   at = @At(value = "INVOKE",
                            target = "Lnet/minecraft/client/renderer/RenderItem;renderItemOverlayIntoGUI(Lnet/minecraft/client/gui/FontRenderer;Lnet/minecraft/item/ItemStack;IILjava/lang/String;)V"))
    private void gtlitecore$skipVanillaCountOverlay(RenderItem renderItem, FontRenderer fontRenderer,
                                                    ItemStack stack, int x, int y, String textOverride,
                                                    Operation<Void> original)
    {
        if (!GTLiteConfigHolder.compat.jeiCompat.scaleableFontRenderer)
        {
            original.call(renderItem, fontRenderer, stack, x, y, textOverride);
        }
    }

    /**
     * @see net.minecraft.client.renderer.RenderItem#renderItemAndEffectIntoGUI
     */
    @Unique
    private void gtlitecore$renderItemAndEffectIntoGUI(FontRenderer fontRenderer, ItemStack stack,
                                                       int x, int y)
    {
        if (stack.getCount() != 1)
        {
            String count = gtlitecore$formatCount(stack.getCount());

            GlStateManager.disableLighting();
            GlStateManager.disableDepth();
            GlStateManager.disableBlend();

            boolean shouldScale = stack.getCount() > 99;
            if (shouldScale)
            {
                GlStateManager.pushMatrix();
                GlStateManager.scale(0.5F, 0.5F, 1.0F);
            }

            int posX = shouldScale ? (x + 16) * 2 - fontRenderer.getStringWidth(count)
                    : x + 16 - fontRenderer.getStringWidth(count);
            int posY = shouldScale ? (y + 16) * 2 - 8 : y + 16 - 8;
            fontRenderer.drawStringWithShadow(count, posX, posY, 0xFFFFFF);

            if (shouldScale) GlStateManager.popMatrix();
            GlStateManager.enableLighting();
            GlStateManager.enableDepth();
            GlStateManager.enableBlend();
        }
    }

    @Unique
    private String gtlitecore$formatCount(int count)
    {
        if (count <= 99)
            return String.valueOf(count);
        if (count <= 9_999)
            return String.valueOf(count);
        if (count <= 999_999)
        {
            float k = count / 1000f;
            return String.format(k % 1 == 0 ? "%.0fk" : "%.1fk", k);
        }
        if (count <= 999_999_999)
        {
            float m = count / 1_000_000f;
            return String.format(m % 1 == 0 ? "%.0fm" : "%.1fm", m);
        }
        float g = count / 1_000_000_000f;
        return String.format(g % 1 == 0 ? "%.0fg" : "%.1fg", g);
    }
}
