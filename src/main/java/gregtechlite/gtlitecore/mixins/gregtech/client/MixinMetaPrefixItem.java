package gregtechlite.gtlitecore.mixins.gregtech.client;

import com.llamalad7.mixinextras.sugar.Local;
import gregtech.api.items.materialitem.MetaPrefixItem;
import gregtech.api.items.metaitem.StandardMetaItem;
import gregtech.api.unification.material.info.MaterialIconSet;
import gregtechlite.gtlitecore.client.renderer.CustomItemRenderer;
import gregtechlite.gtlitecore.client.renderer.ItemRendererManager;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = MetaPrefixItem.class, remap = false)
public abstract class MixinMetaPrefixItem extends StandardMetaItem
{
    @Inject(method = "registerModels()V",
            at = @At(value = "INVOKE",
                     target = "Lnet/minecraft/client/renderer/block/model/ModelBakery;registerItemVariants(Lnet/minecraft/item/Item;[Lnet/minecraft/util/ResourceLocation;)V",
                     ordinal = 0))
    private void gtlitecore$registerCustomRenderer(CallbackInfo callbackInfo,
                                                   @Local MaterialIconSet materialIconSet,
                                                   @Local ResourceLocation resourceLocation,
                                                   @Local(ordinal = 0) short metaItem)
    {
        if (materialIconSet instanceof ItemRendererManager)
        {
            ItemRendererManager rendererManager = (ItemRendererManager) materialIconSet;
            rendererManager.onRendererRegistry(resourceLocation);
            metaItems.get(metaItem).addComponents(((CustomItemRenderer) materialIconSet).getRendererManager());
        }
    }
}
