package gregtechlite.gtlitecore.mixins.gregtech.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import gregtech.api.pipenet.tile.IPipeTile;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;
import gregtech.client.renderer.pipe.CableRenderer;
import gregtechlite.gtlitecore.mixins.hooks.PipeTextureHooks;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Let wireGtX/cableGtX renderer {@link MaterialIconSet} textures.
 * <p>
 * This class implement a feature that make material wireGtX/cableGtX can render its
 * textures from its {@link MaterialIconSet}. This change will cause the original
 * wireGtX/cableGtX source texture at "gregtech/blocks/cable/wire.png" be invalid in
 * its renderer. The solution of this problem is created a new texture at its folder.
 * <p>
 * The sprite table itself lives in {@link PipeTextureHooks}, this class only redirects the field reads.
 *
 * @author Magic_Sweepy
 */
@Mixin(value = CableRenderer.class, remap = false)
public abstract class MixinCableRenderer
{
    @Inject(method = "registerIcons",
            at = @At(value = "TAIL"))
    private void gtlitecore$registerIcons(TextureMap map, CallbackInfo callbackInfo)
    {
        PipeTextureHooks.registerCableIcons(map);
    }

    @WrapOperation(method = "buildRenderer",
                   at = @At(value = "FIELD",
                            target = "Lgregtech/client/renderer/pipe/CableRenderer;wireTexture:Lnet/minecraft/client/renderer/texture/TextureAtlasSprite;"))
    private @Nullable TextureAtlasSprite gtlitecore$getWireTexture(CableRenderer instance,
                                                                   Operation<TextureAtlasSprite> original,
                                                                   @Local(index = 5, argsOnly = true) Material material)
    {
        return PipeTextureHooks.cableWireTexture(material.getMaterialIconSet());
    }

    @WrapOperation(method = "getParticleTexture(Lgregtech/api/pipenet/tile/IPipeTile;)Lorg/apache/commons/lang3/tuple/Pair;",
                   at = @At(value = "FIELD",
                            target = "Lgregtech/client/renderer/pipe/CableRenderer;wireTexture:Lnet/minecraft/client/renderer/texture/TextureAtlasSprite;"))
    private @Nullable TextureAtlasSprite gtlitecore$getParticleWireTexture(CableRenderer instance,
                                                                 Operation<TextureAtlasSprite> original,
                                                                 @Local(index = 1, argsOnly = true) IPipeTile<?, ?> pipeTile)
    {
        return PipeTextureHooks.cableWireTextureForPipe(pipeTile);
    }
}
