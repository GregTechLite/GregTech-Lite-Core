package gregtechlite.gtlitecore.mixins.gregtech.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import gregtech.api.items.metaitem.MetaItem;
import gregtechlite.gtlitecore.client.renderer.CustomItemRenderer;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = MetaItem.class, remap = false)
public abstract class MixinMetaItem<T extends MetaItem<?>.MetaValueItem> extends Item
{
    @WrapOperation(method = "registerModels()V",
                   at = @At(value = "INVOKE",
                            target = "Lgregtech/api/items/metaitem/MetaItem;createItemModelPath(Lgregtech/api/items/metaitem/MetaItem$MetaValueItem;Ljava/lang/String;)Lnet/minecraft/util/ResourceLocation;"))
    private ResourceLocation gtlitecore$registerModels(MetaItem<?> metaItem,
                                                       MetaItem<?>.MetaValueItem metaValueItem,
                                                       String postfix,
                                                       Operation<ResourceLocation> original)
    {
        ResourceLocation location = original.call(metaItem, metaValueItem, postfix);
        CustomItemRenderer itemRenderer = (CustomItemRenderer) metaValueItem;
        if (itemRenderer.getRendererManager() != null)
        {
            itemRenderer.getRendererManager().onRendererRegistry(location);
        }
        return location;
    }
}
