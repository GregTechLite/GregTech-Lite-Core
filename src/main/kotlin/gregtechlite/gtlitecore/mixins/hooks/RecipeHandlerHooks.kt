package gregtechlite.gtlitecore.mixins.hooks

import gregtech.api.unification.material.properties.PropertyKey
import gregtech.api.unification.ore.OrePrefix
import gregtech.common.ConfigHolder
import gregtech.loaders.recipe.handlers.MaterialRecipeHandler
import gregtech.loaders.recipe.handlers.OreRecipeHandler
import gregtech.loaders.recipe.handlers.PartsRecipeHandler
import gregtech.loaders.recipe.handlers.WireRecipeHandler

/**
 * Re-registers the subset of GregTech processing handlers which our loaders still need.
 *
 * The mixins cancel GregTech's own `register()` methods (see
 * [gregtechlite.gtlitecore.loader.recipe.handler.RecipeHandlers] for the recipes we generate instead), so the
 * handlers listed here have to be attached manually.
 *
 * @author Magic_Sweepy
 */
@Hook
object RecipeHandlerHooks
{
    @JvmStatic
    fun registerMaterialHandlers()
    {
        OrePrefix.nugget.addProcessingHandler(PropertyKey.DUST, MaterialRecipeHandler::processNugget)
        OrePrefix.frameGt.addProcessingHandler(PropertyKey.DUST, MaterialRecipeHandler::processFrame)
        OrePrefix.dust.addProcessingHandler(PropertyKey.DUST, MaterialRecipeHandler::processDust)
        OrePrefix.dustSmall.addProcessingHandler(PropertyKey.DUST, MaterialRecipeHandler::processSmallDust)
        OrePrefix.dustTiny.addProcessingHandler(PropertyKey.DUST, MaterialRecipeHandler::processTinyDust)
    }

    @JvmStatic
    fun registerOreHandlers()
    {
        OrePrefix.ore.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre)
        OrePrefix.oreEndstone.addProcessingHandler(PropertyKey.ORE) { prefix, material, property ->
            OreRecipeHandler.processOre(prefix, material, property, 4)
        }
        OrePrefix.oreNetherrack.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOreDouble)

        if (ConfigHolder.worldgen.allUniqueStoneTypes)
        {
            OrePrefix.oreGranite.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre)
            OrePrefix.oreDiorite.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre)
            OrePrefix.oreAndesite.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre)
            OrePrefix.oreBasalt.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre)
            OrePrefix.oreBlackgranite.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre)
            OrePrefix.oreMarble.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre)
            OrePrefix.oreRedgranite.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre)
            OrePrefix.oreSand.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre)
            OrePrefix.oreRedSand.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre)
        }

        OrePrefix.crushed.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processCrushedOre)
        OrePrefix.crushedCentrifuged.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processCrushedCentrifuged)
        OrePrefix.dustImpure.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processDirtyDust)
        OrePrefix.dustPure.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processPureDust)
    }

    @JvmStatic
    fun registerPartsHandlers()
    {
        OrePrefix.stickLong.addProcessingHandler(PropertyKey.DUST, PartsRecipeHandler::processLongStick)
        OrePrefix.plate.addProcessingHandler(PropertyKey.DUST, PartsRecipeHandler::processPlate)
        OrePrefix.plateDouble.addProcessingHandler(PropertyKey.INGOT, PartsRecipeHandler::processPlateDouble)
        OrePrefix.plateDense.addProcessingHandler(PropertyKey.DUST, PartsRecipeHandler::processPlateDense)

        OrePrefix.turbineBlade.addProcessingHandler(PropertyKey.INGOT, PartsRecipeHandler::processTurbine)
        OrePrefix.rotor.addProcessingHandler(PropertyKey.INGOT, PartsRecipeHandler::processRotor)
        OrePrefix.bolt.addProcessingHandler(PropertyKey.DUST, PartsRecipeHandler::processBolt)
        OrePrefix.screw.addProcessingHandler(PropertyKey.DUST, PartsRecipeHandler::processScrew)
        OrePrefix.wireFine.addProcessingHandler(PropertyKey.INGOT, PartsRecipeHandler::processFineWire)
        OrePrefix.foil.addProcessingHandler(PropertyKey.INGOT, PartsRecipeHandler::processFoil)

        OrePrefix.gear.addProcessingHandler(PropertyKey.DUST, PartsRecipeHandler::processGear)
        OrePrefix.gearSmall.addProcessingHandler(PropertyKey.DUST, PartsRecipeHandler::processGear)
        OrePrefix.ring.addProcessingHandler(PropertyKey.INGOT, PartsRecipeHandler::processRing)
        OrePrefix.springSmall.addProcessingHandler(PropertyKey.INGOT, PartsRecipeHandler::processSpringSmall)
        OrePrefix.spring.addProcessingHandler(PropertyKey.INGOT, PartsRecipeHandler::processSpring)
        OrePrefix.round.addProcessingHandler(PropertyKey.INGOT, PartsRecipeHandler::processRound)
    }

    @JvmStatic
    fun registerWireHandlers()
    {
        OrePrefix.wireGtSingle.addProcessingHandler(PropertyKey.WIRE, WireRecipeHandler::processWireSingle)
    }
}
