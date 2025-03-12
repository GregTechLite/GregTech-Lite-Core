package magicbook.gtlitecore.mixin.gregtech;

import gregtech.loaders.recipe.handlers.WireCombiningHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = WireCombiningHandler.class, remap = false)
public abstract class WireCombiningHandlerMixin
{

    @Inject(method = "register",
            at = @At("HEAD"),
            cancellable = true)
    private static void callbackRegistrate(CallbackInfo ci)
    {
        // OrePrefix.wireGtSingle.addProcessingHandler(PropertyKey.WIRE, WireCombiningHandler::processWireCompression);
        // for (OrePrefix wirePrefix : WIRE_DOUBLING_ORDER) {
        //     wirePrefix.addProcessingHandler(PropertyKey.WIRE, WireCombiningHandler::generateWireCombiningRecipe);
        // }
        // for (OrePrefix cablePrefix : cableToWireMap.keySet()) {
        //     cablePrefix.addProcessingHandler(PropertyKey.WIRE, WireCombiningHandler::processCableStripping);
        // }
        ci.cancel();
    }

}
