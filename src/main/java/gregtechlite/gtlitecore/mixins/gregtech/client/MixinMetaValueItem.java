package gregtechlite.gtlitecore.mixins.gregtech.client;

import com.llamalad7.mixinextras.sugar.Local;
import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.items.metaitem.stats.IItemComponent;
import gregtechlite.gtlitecore.client.renderer.CustomItemRenderer;
import gregtechlite.gtlitecore.client.renderer.ItemRendererManager;
import org.jetbrains.annotations.UnknownNullability;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = MetaItem.MetaValueItem.class, remap = false)
public abstract class MixinMetaValueItem implements CustomItemRenderer
{
    @Unique
    private @UnknownNullability ItemRendererManager gtlitecore$rendererManager;

    @SuppressWarnings("AddedMixinMembersNamePattern")
    @Unique
    @Override
    public ItemRendererManager getRendererManager()
    {
        return gtlitecore$rendererManager;
    }

    @Inject(method = "addItemComponentsInternal([Lgregtech/api/items/metaitem/stats/IItemComponent;)V",
            at = @At(value = "FIELD",
                     target = "Lgregtech/api/items/metaitem/MetaItem$MetaValueItem;allStats:Ljava/util/List;"))
    private void gtlitecore$captureRendererManager(IItemComponent[] stats, CallbackInfo callbackInfo,
                                                   @Local IItemComponent itemComponent)
    {
        if (itemComponent instanceof ItemRendererManager)
        {
            gtlitecore$rendererManager = (ItemRendererManager) itemComponent;
        }
    }
}
