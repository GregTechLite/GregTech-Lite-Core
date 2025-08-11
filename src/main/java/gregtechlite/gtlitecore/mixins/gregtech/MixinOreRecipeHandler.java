package gregtechlite.gtlitecore.mixins.gregtech;

import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.common.ConfigHolder;
import gregtech.loaders.recipe.handlers.OreRecipeHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = OreRecipeHandler.class, remap = false)
public abstract class MixinOreRecipeHandler
{

    @Inject(method = "register",
            at = @At("HEAD"),
            cancellable = true)
    private static void callbackRegistrate(CallbackInfo ci)
    {
        OrePrefix.ore.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
        OrePrefix.oreEndstone.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
        OrePrefix.oreNetherrack.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
        if (ConfigHolder.worldgen.allUniqueStoneTypes)
        {
            OrePrefix.oreGranite.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
            OrePrefix.oreDiorite.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
            OrePrefix.oreAndesite.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
            OrePrefix.oreBasalt.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
            OrePrefix.oreBlackgranite.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
            OrePrefix.oreMarble.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
            OrePrefix.oreRedgranite.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
            OrePrefix.oreSand.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
            OrePrefix.oreRedSand.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
        }

        OrePrefix.crushed.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processCrushedOre);
        // OrePrefix.crushedPurified.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processCrushedPurified);
        OrePrefix.crushedCentrifuged.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processCrushedCentrifuged);
        OrePrefix.dustImpure.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processDirtyDust);
        OrePrefix.dustPure.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processPureDust);

        ci.cancel();
    }

}
