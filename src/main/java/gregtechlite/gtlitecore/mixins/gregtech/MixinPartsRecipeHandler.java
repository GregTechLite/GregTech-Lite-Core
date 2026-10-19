package gregtechlite.gtlitecore.mixins.gregtech;

import gregtech.loaders.recipe.handlers.PartsRecipeHandler;
import gregtechlite.gtlitecore.mixins.hooks.RecipeHandlerHooks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = PartsRecipeHandler.class, remap = false)
public abstract class MixinPartsRecipeHandler
{
    /**
     * @author Magic_Sweepy
     * @reason Disabled {@code stick} and {@code craftingLens} recipes initialization.
     */
    @Inject(method = "register",
            at = @At("HEAD"),
            cancellable = true)
    private static void stopInit(CallbackInfo callbackInfo)
    {
        RecipeHandlerHooks.registerPartsHandlers();
        callbackInfo.cancel();
    }
}
