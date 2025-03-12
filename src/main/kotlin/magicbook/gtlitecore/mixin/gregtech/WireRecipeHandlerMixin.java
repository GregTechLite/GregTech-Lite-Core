package magicbook.gtlitecore.mixin.gregtech;

import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.loaders.recipe.handlers.WireRecipeHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = WireRecipeHandler.class, remap = false)
public abstract class WireRecipeHandlerMixin
{

    @Inject(method = "register",
            at = @At("HEAD"),
            cancellable = true)
    private static void callbackRegistrate(CallbackInfo ci)
    {
        OrePrefix.wireGtSingle.addProcessingHandler(PropertyKey.WIRE, WireRecipeHandler::processWireSingle);
        // Deleted all generateCableCovering() including.
        ci.cancel();
    }

}
