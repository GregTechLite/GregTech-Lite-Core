package gregtechlite.gtlitecore.mixins.gregtech;

import gregtech.loaders.recipe.handlers.MaterialRecipeHandler;
import gregtechlite.gtlitecore.mixins.hooks.RecipeHandlerHooks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = MaterialRecipeHandler.class, remap = false)
public abstract class MixinMaterialRecipeHandler
{
    /**
     * @author Magic_Sweepy
     * @reason Delete {@code processIngot}, {@code processBlock} and all {@code processGemConversion}.
     */
    @Inject(method = "register",
            at = @At("HEAD"),
            cancellable = true)
    private static void callbackRegistrate(CallbackInfo callbackInfo)
    {
        RecipeHandlerHooks.registerMaterialHandlers();
        callbackInfo.cancel();
    }
}
