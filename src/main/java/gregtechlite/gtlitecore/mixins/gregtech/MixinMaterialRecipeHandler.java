package gregtechlite.gtlitecore.mixins.gregtech;

import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.loaders.recipe.handlers.MaterialRecipeHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = MaterialRecipeHandler.class, remap = false)
public abstract class MixinMaterialRecipeHandler
{
    /**
     * Delete {@code processIngot}, {@code processBlock} and all {@code processGemConversion}.
     */
    @Inject(method = "register",
            at = @At("HEAD"),
            cancellable = true)
    private static void callbackRegistrate(CallbackInfo ci)
    {
        OrePrefix.nugget.addProcessingHandler(PropertyKey.DUST, MaterialRecipeHandler::processNugget);
        OrePrefix.frameGt.addProcessingHandler(PropertyKey.DUST, MaterialRecipeHandler::processFrame);
        OrePrefix.dust.addProcessingHandler(PropertyKey.DUST, MaterialRecipeHandler::processDust);
        OrePrefix.dustSmall.addProcessingHandler(PropertyKey.DUST, MaterialRecipeHandler::processSmallDust);
        OrePrefix.dustTiny.addProcessingHandler(PropertyKey.DUST, MaterialRecipeHandler::processTinyDust);
        ci.cancel();
    }
}
