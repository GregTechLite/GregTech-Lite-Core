package gregtechlite.gtlitecore.mixins.gregtech.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;
import gregtech.client.renderer.pipe.ItemPipeRenderer;
import gregtech.common.pipelike.itempipe.ItemPipeType;
import gregtechlite.gtlitecore.mixins.hooks.PipeTextureHooks;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.EnumMap;

/**
 * Add {@link MaterialIconSet} supported for {@link ItemPipeRenderer}.
 * <p>
 * This class implement a feature that make material pipes rendered its iconSet textures
 * but not default textures.
 * <p>
 * WARNING: This change will cause the original pipes textures be broken, should add
 * correspondenced iconSet textures to all iconSets.
 *
 * @author Magic_Sweepy
 */
@Mixin(value = ItemPipeRenderer.class, remap = false)
public abstract class MixinItemPipeRenderer
{
    @Inject(method = "registerIcons",
            at = @At(value = "TAIL"))
    private void gtlitecore$registerIcons(TextureMap map, CallbackInfo callbackInfo)
    {
        PipeTextureHooks.registerItemPipeIcons(map);
    }

    @WrapOperation(method = "buildRenderer",
                   at = @At(value = "INVOKE",
                            target = "Ljava/util/EnumMap;get(Ljava/lang/Object;)Ljava/lang/Object;"))
    private @Nullable Object gtlitecore$getPipeTexture(EnumMap<ItemPipeType, TextureAtlasSprite> enumMap, Object key,
                                                       Operation<Object> original,
                                                       @Local(index = 5, argsOnly = true) Material material)
    {
        return PipeTextureHooks.itemPipeTexture((ItemPipeType) key, material.getMaterialIconSet());
    }

    @WrapOperation(method = "buildRenderer",
                   at = @At(value = "FIELD",
                            target = "Lgregtech/client/renderer/texture/Textures;PIPE_SIDE:Lnet/minecraft/client/renderer/texture/TextureAtlasSprite;"))
    private @Nullable TextureAtlasSprite gtlitecore$getPipeSideTexture(Operation<TextureAtlasSprite> original,
                                                                       @Local(index = 5, argsOnly = true) Material material)
    {
        return PipeTextureHooks.itemPipeSideTexture(material.getMaterialIconSet());
    }
}
