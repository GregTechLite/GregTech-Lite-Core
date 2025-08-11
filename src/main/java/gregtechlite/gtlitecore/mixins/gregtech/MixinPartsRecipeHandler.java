package gregtechlite.gtlitecore.mixins.gregtech;

import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.loaders.recipe.handlers.PartsRecipeHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = PartsRecipeHandler.class, remap = false)
public abstract class MixinPartsRecipeHandler
{

    @Inject(method = "register",
            at = @At("HEAD"),
            cancellable = true)
    private static void callbackRegistrate(CallbackInfo ci)
    {
        // OrePrefix.stick.addProcessingHandler(PropertyKey.DUST, PartsRecipeHandler::processStick);
        OrePrefix.stickLong.addProcessingHandler(PropertyKey.DUST, PartsRecipeHandler::processLongStick);
        OrePrefix.plate.addProcessingHandler(PropertyKey.DUST, PartsRecipeHandler::processPlate);
        OrePrefix.plateDouble.addProcessingHandler(PropertyKey.INGOT, PartsRecipeHandler::processPlateDouble);
        OrePrefix.plateDense.addProcessingHandler(PropertyKey.DUST, PartsRecipeHandler::processPlateDense);

        OrePrefix.turbineBlade.addProcessingHandler(PropertyKey.INGOT, PartsRecipeHandler::processTurbine);
        OrePrefix.rotor.addProcessingHandler(PropertyKey.INGOT, PartsRecipeHandler::processRotor);
        OrePrefix.bolt.addProcessingHandler(PropertyKey.DUST, PartsRecipeHandler::processBolt);
        OrePrefix.screw.addProcessingHandler(PropertyKey.DUST, PartsRecipeHandler::processScrew);
        OrePrefix.wireFine.addProcessingHandler(PropertyKey.INGOT, PartsRecipeHandler::processFineWire);
        OrePrefix.foil.addProcessingHandler(PropertyKey.INGOT, PartsRecipeHandler::processFoil);
        // OrePrefix.lens.addProcessingHandler(PropertyKey.GEM, PartsRecipeHandler::processLens);

        OrePrefix.gear.addProcessingHandler(PropertyKey.DUST, PartsRecipeHandler::processGear);
        OrePrefix.gearSmall.addProcessingHandler(PropertyKey.DUST, PartsRecipeHandler::processGear);
        OrePrefix.ring.addProcessingHandler(PropertyKey.INGOT, PartsRecipeHandler::processRing);
        OrePrefix.springSmall.addProcessingHandler(PropertyKey.INGOT, PartsRecipeHandler::processSpringSmall);
        OrePrefix.spring.addProcessingHandler(PropertyKey.INGOT, PartsRecipeHandler::processSpring);
        OrePrefix.round.addProcessingHandler(PropertyKey.INGOT, PartsRecipeHandler::processRound);

        ci.cancel();
    }


}
