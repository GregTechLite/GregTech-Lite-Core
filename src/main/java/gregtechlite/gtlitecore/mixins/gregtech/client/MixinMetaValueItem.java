package gregtechlite.gtlitecore.mixins.gregtech.client;

import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.items.metaitem.stats.IItemComponent;
import gregtechlite.gtlitecore.client.renderer.CustomItemRenderer;
import gregtechlite.gtlitecore.client.renderer.ItemRendererManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value = MetaItem.MetaValueItem.class, remap = false)
public abstract class MixinMetaValueItem implements CustomItemRenderer
{

    @Unique
    private ItemRendererManager gtlitecore$rendererManager;

    @Unique
    @Override
    public ItemRendererManager getRendererManager()
    {
        return gtlitecore$rendererManager;
    }

    @Inject(
            method = "addItemComponentsInternal([Lgregtech/api/items/metaitem/stats/IItemComponent;)V",
            at = @At(value = "FIELD",
                     target = "Lgregtech/api/items/metaitem/MetaItem$MetaValueItem;allStats:Ljava/util/List;"),
            locals = LocalCapture.CAPTURE_FAILHARD)
    private void addItemComponentsInternal(IItemComponent[] stats,
                                           CallbackInfo ci,
                                           IItemComponent[] var2,
                                           int var3, int var4,
                                           IItemComponent itemComponent)
    {
        if (itemComponent instanceof ItemRendererManager)
        {
            gtlitecore$rendererManager = (ItemRendererManager) itemComponent;
        }
    }

}