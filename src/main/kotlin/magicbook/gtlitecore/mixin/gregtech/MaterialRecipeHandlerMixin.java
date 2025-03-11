package magicbook.gtlitecore.mixin.gregtech;

import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.loaders.recipe.handlers.MaterialRecipeHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = MaterialRecipeHandler.class, remap = false)
public abstract class MaterialRecipeHandlerMixin
{

    @Inject(method = "register",
            at = @At("HEAD"),
            cancellable = true)
    private static void callbackRegistrate(CallbackInfo ci)
    {
        // OrePrefix.ingot.addProcessingHandler(PropertyKey.INGOT, MaterialRecipeHandler::processIngot);
        OrePrefix.nugget.addProcessingHandler(PropertyKey.DUST, MaterialRecipeHandler::processNugget);

        // OrePrefix.block.addProcessingHandler(PropertyKey.DUST, MaterialRecipeHandler::processBlock);
        OrePrefix.frameGt.addProcessingHandler(PropertyKey.DUST, MaterialRecipeHandler::processFrame);

        OrePrefix.dust.addProcessingHandler(PropertyKey.DUST, MaterialRecipeHandler::processDust);
        OrePrefix.dustSmall.addProcessingHandler(PropertyKey.DUST, MaterialRecipeHandler::processSmallDust);
        OrePrefix.dustTiny.addProcessingHandler(PropertyKey.DUST, MaterialRecipeHandler::processTinyDust);

        // for (int i = 0; i < GEM_ORDER.size(); i++)
        // {
        //     OrePrefix gemPrefix = GEM_ORDER.get(i);
        //     OrePrefix prevGemPrefix = i == 0 ? null : GEM_ORDER.get(i - 1);
        //     gemPrefix.addProcessingHandler(PropertyKey.GEM,
        //             (p, material, property) -> processGemConversion(p, prevGemPrefix, material));
        // }

        ci.cancel();
    }

}
