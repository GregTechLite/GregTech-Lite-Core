package gregtechlite.gtlitecore.mixins.jei;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import gregtechlite.gtlitecore.core.GTLiteConfigHolder;
import gregtechlite.gtlitecore.mixins.hooks.Compat;
import gregtechlite.gtlitecore.mixins.hooks.ItemCountRenderer;
import mezz.jei.plugins.vanilla.ingredients.item.ItemStackRenderer;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
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
     * @reason Draws the scalable count for stacks above 64, the branch JEI delegates to {@code CountUtil}.
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
            ItemCountRenderer.renderCount(fontRenderer, ingredient.getCount(), x, y);
        }
        else
        {
            original.call(fontRenderer, count, x, y, shadow);
        }
    }

    /**
     * @author Magic_Sweepy
     * @reason Draws the scalable count for stacks up to 64, which JEI renders through the vanilla overlay.
     */
    @WrapOperation(method = "render*",
                   at = @At(value = "INVOKE",
                            target = "Lnet/minecraft/client/renderer/RenderItem;renderItemOverlayIntoGUI(Lnet/minecraft/client/gui/FontRenderer;Lnet/minecraft/item/ItemStack;IILjava/lang/String;)V"))
    private void gtlitecore$renderCountOrOverlay(RenderItem renderItem, FontRenderer fontRenderer,
                                                 ItemStack stack, int x, int y, String textOverride,
                                                 Operation<Void> original)
    {
        if (GTLiteConfigHolder.compat.jeiCompat.scaleableFontRenderer)
        {
            ItemCountRenderer.renderCount(fontRenderer, stack.getCount(), x, y);
        }
        else
        {
            original.call(renderItem, fontRenderer, stack, x, y, textOverride);
        }
    }
}
