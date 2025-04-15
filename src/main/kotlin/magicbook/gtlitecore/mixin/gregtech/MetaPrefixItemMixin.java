package magicbook.gtlitecore.mixin.gregtech;

import gregtech.api.items.materialitem.MetaPrefixItem;
import gregtech.api.items.metaitem.StandardMetaItem;
import gregtech.api.unification.material.info.MaterialIconSet;
import it.unimi.dsi.fastutil.shorts.ShortIterator;
import magicbook.gtlitecore.client.renderer.IItemRenderer;
import magicbook.gtlitecore.client.renderer.IItemRendererManager;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Map;

@Mixin(value = MetaPrefixItem.class, remap = false)
public abstract class MetaPrefixItemMixin extends StandardMetaItem
{

    @SuppressWarnings("rawtypes")
    @Inject(
            method = "registerModels()V",
            at = @At(value = "INVOKE",
                     target = "Lnet/minecraft/client/renderer/block/model/ModelBakery;registerItemVariants(Lnet/minecraft/item/Item;[Lnet/minecraft/util/ResourceLocation;)V",
                     ordinal = 0),
            locals = LocalCapture.CAPTURE_FAILHARD)
    private void registerModels(CallbackInfo ci,
                                Map alreadyRegistered,
                                ShortIterator var2,
                                short metaItem,
                                MaterialIconSet materialIconSet,
                                short registrationKey,
                                ResourceLocation resourceLocation)
    {
        if (materialIconSet instanceof IItemRendererManager)
        {
            IItemRendererManager rendererManager = (IItemRendererManager) materialIconSet;
            rendererManager.onRendererRegistry(resourceLocation);
            metaItems.get(metaItem).addComponents(((IItemRenderer) materialIconSet).getRendererManager());
        }
    }

}
