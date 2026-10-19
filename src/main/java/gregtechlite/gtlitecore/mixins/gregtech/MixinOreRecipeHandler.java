package gregtechlite.gtlitecore.mixins.gregtech;

import gregtech.loaders.recipe.handlers.OreRecipeHandler;
import gregtechlite.gtlitecore.mixins.hooks.RecipeHandlerHooks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = OreRecipeHandler.class, remap = false)
public abstract class MixinOreRecipeHandler
{
    /**
     * @author Magic_Sweepy
     * @reason Disabled {@code crushedPurified} recipes initialization.
     */
    @Inject(method = "register",
            at = @At("HEAD"),
            cancellable = true)
    private static void stopInit(CallbackInfo callbackInfo)
    {
        RecipeHandlerHooks.registerOreHandlers();
        callbackInfo.cancel();
    }
}
