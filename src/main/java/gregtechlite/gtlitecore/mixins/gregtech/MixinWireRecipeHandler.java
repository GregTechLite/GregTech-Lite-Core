package gregtechlite.gtlitecore.mixins.gregtech;

import gregtech.loaders.recipe.handlers.WireRecipeHandler;
import gregtechlite.gtlitecore.mixins.hooks.RecipeHandlerHooks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = WireRecipeHandler.class, remap = false)
public abstract class MixinWireRecipeHandler
{
    /**
     * @author Magic_Sweepy
     * @reason Disabled {@code generateCableCovering} for all {@code wireGt} orePrefixes. Only generate
     *         {@code wireGtSingle} recipes, this method used to generate wiremill recipes.
     */
    @Inject(method = "register",
            at = @At("HEAD"),
            cancellable = true)
    private static void stopInit(CallbackInfo callbackInfo)
    {
        RecipeHandlerHooks.registerWireHandlers();
        callbackInfo.cancel();
    }
}
