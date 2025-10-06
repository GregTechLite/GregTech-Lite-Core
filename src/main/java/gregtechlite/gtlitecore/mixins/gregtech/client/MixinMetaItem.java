package gregtechlite.gtlitecore.mixins.gregtech.client;

import gregtech.api.items.metaitem.MetaItem;
import gregtechlite.gtlitecore.client.renderer.CustomItemRenderer;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = MetaItem.class, remap = false)
public abstract class MixinMetaItem<T extends MetaItem<?>.MetaValueItem> extends Item
{

    @Shadow
    public abstract ResourceLocation createItemModelPath(T metaValueItem, String postfix);

    @SuppressWarnings("unchecked")
    @Redirect(
            method = "registerModels()V",
            at = @At(value = "INVOKE",
                     target = "Lgregtech/api/items/metaitem/MetaItem;createItemModelPath(Lgregtech/api/items/metaitem/MetaItem$MetaValueItem;Ljava/lang/String;)Lnet/minecraft/util/ResourceLocation;"))
    private ResourceLocation registerModels(MetaItem<?> metaItem,
                                            MetaItem<?>.MetaValueItem metaValueItem,
                                            String postfix)
    {
        ResourceLocation location = createItemModelPath((T) metaValueItem, postfix);
        CustomItemRenderer itemRenderer = (CustomItemRenderer) metaValueItem;
        if (itemRenderer.getRendererManager() != null)
        {
            itemRenderer.getRendererManager().onRendererRegistry(location);
        }
        return location;
    }

}
