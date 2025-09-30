package gregtechlite.gtlitecore.mixins.gregtech;

import gregtech.loaders.recipe.handlers.WireCombiningHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = WireCombiningHandler.class, remap = false)
public abstract class MixinWireCombiningHandler
{

    /**
     * Disabled all wire combining recipes initialization.
     *
     * @see gregtechlite.gtlitecore.loader.recipe.handler.WireCombinationHandler
     */
    @Inject(method = "register",
            at = @At("HEAD"),
            cancellable = true)
    private static void stopInit(CallbackInfo ci)
    {
        ci.cancel();
    }

}
