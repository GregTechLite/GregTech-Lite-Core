package gregtechlite.gtlitecore.loader.recipe.handler

import gregtech.api.GTValues.LV
import gregtech.api.GTValues.VH
import gregtech.api.recipes.ModHandler
import gregtech.api.recipes.RecipeMaps.FORGE_HAMMER_RECIPES
import gregtech.api.recipes.RecipeMaps.MACERATOR_RECIPES
import gregtech.api.recipes.RecipeMaps.ORE_WASHER_RECIPES
import gregtech.api.recipes.RecipeMaps.SIFTER_RECIPES
import gregtech.api.recipes.RecipeMaps.THERMAL_CENTRIFUGE_RECIPES
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Material
import gregtech.api.unification.material.Materials.Stone
import gregtech.api.unification.material.info.MaterialFlags.HIGH_SIFTER_OUTPUT
import gregtech.api.unification.material.properties.OreProperty
import gregtech.api.unification.material.properties.PropertyKey
import gregtech.api.unification.ore.OrePrefix
import gregtech.api.unification.ore.OrePrefix.crushed
import gregtech.api.unification.ore.OrePrefix.crushedCentrifuged
import gregtech.api.unification.ore.OrePrefix.crushedPurified
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.dustPure
import gregtech.api.unification.ore.OrePrefix.gem
import gregtech.api.unification.ore.OrePrefix.gemChipped
import gregtech.api.unification.ore.OrePrefix.gemExquisite
import gregtech.api.unification.ore.OrePrefix.gemFlawed
import gregtech.api.unification.ore.OrePrefix.gemFlawless
import gregtech.api.unification.ore.OrePrefix.ingot
import gregtech.api.unification.stack.UnificationEntry
import gregtech.api.util.GTUtility.copyFirst
import gregtech.common.ConfigHolder
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TectonicPetrotheum
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ZephyreanAerotheum
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix.gemSolitary
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix.oreBlueSchist
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix.oreGreenSchist
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix.oreKimberlite
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix.oreKomatiite
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix.oreLimestone
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix.oreQuartzite
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix.oreShale
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix.oreSlate
import gregtech.loaders.recipe.handlers.OreRecipeHandler as GTOreRecipeHandler

object OreRecipeHandler
{

    // @formatter:off

    fun init()
    {
        crushedPurified.addProcessingHandler(PropertyKey.ORE, ::processCrushedPurified)

        if (ConfigHolder.worldgen.allUniqueStoneTypes)
        {
            oreLimestone.addProcessingHandler(PropertyKey.ORE, GTOreRecipeHandler::processOre)
            oreKomatiite.addProcessingHandler(PropertyKey.ORE, GTOreRecipeHandler::processOre)
            oreGreenSchist.addProcessingHandler(PropertyKey.ORE, GTOreRecipeHandler::processOre)
            oreBlueSchist.addProcessingHandler(PropertyKey.ORE, GTOreRecipeHandler::processOre)
            oreKimberlite.addProcessingHandler(PropertyKey.ORE, GTOreRecipeHandler::processOre)
            oreQuartzite.addProcessingHandler(PropertyKey.ORE, GTOreRecipeHandler::processOre)
            oreSlate.addProcessingHandler(PropertyKey.ORE, GTOreRecipeHandler::processOre)
            oreShale.addProcessingHandler(PropertyKey.ORE, GTOreRecipeHandler::processOre)
        }

        crushed.addProcessingHandler(PropertyKey.ORE, ::processCrushedOre)
    }

    private fun processCrushedPurified(purifiedPrefix: OrePrefix, material: Material, property: OreProperty)
    {
        val crushedCentrifugedStack = OreDictUnifier.get(crushedCentrifuged, material)
        val dustStack = OreDictUnifier.get(dustPure, material)
        val byproductMaterial = property.getOreByProduct(1, material)
        val byproductStack = OreDictUnifier.get(dust, byproductMaterial)

        // Hamming crushedPurifiedX -> dustPureX.
        FORGE_HAMMER_RECIPES.recipeBuilder()
            .input(purifiedPrefix, material)
            .outputs(dustStack)
            .EUt(VH[LV])
            .duration(10 * TICK)
            .buildAndRegister()

        // Macerating crushedPurifiedX -> dustPureX + (dustX: byproduct).
        MACERATOR_RECIPES.recipeBuilder()
            .input(purifiedPrefix, material)
            .outputs(dustStack)
            .chancedOutput(byproductStack, 1400, 850)
            .duration(20 * SECOND)
            .buildAndRegister()

        // Hand-hamming crushedPurifiedX -> dustPureX.
        ModHandler.addShapelessRecipe(String.format("purified_ore_to_dust_%s", material), dustStack,
            'h', UnificationEntry(purifiedPrefix, material))

        // Thermal centrifuging crushedPurifiedX -> crushedCentrifugedX + (dustX: byproduct).
        if (!crushedCentrifugedStack.isEmpty)
        {
            THERMAL_CENTRIFUGE_RECIPES.recipeBuilder()
                .input(purifiedPrefix, material)
                .outputs(crushedCentrifugedStack)
                .chancedOutput(dust, byproductMaterial, 3333, 0)
                .buildAndRegister()
        }

        // Gem sifting, we merged gemSolitary and sort it at this stage.
        if (material.hasProperty(PropertyKey.GEM))
        {
            val solitaryStack = OreDictUnifier.get(gemSolitary, material)
            val exquisiteStack = OreDictUnifier.get(gemExquisite, material)
            val flawlessStack = OreDictUnifier.get(gemFlawless, material)
            val gemStack = OreDictUnifier.get(gem, material)
            val flawedStack = OreDictUnifier.get(gemFlawed, material)
            val chippedStack = OreDictUnifier.get(gemChipped, material)

            if (material.hasFlag(HIGH_SIFTER_OUTPUT))
            {
                SIFTER_RECIPES.recipeBuilder()
                    .circuitMeta(1)
                    .input(purifiedPrefix, material)
                    .chancedOutput(solitaryStack, 100, 50)
                    .chancedOutput(exquisiteStack, 500, 150)
                    .chancedOutput(flawlessStack, 1500, 200)
                    .chancedOutput(gemStack, 5000, 1000)
                    .chancedOutput(flawedStack, 2000, 500)
                    .chancedOutput(chippedStack, 3000, 350)
                    .chancedOutput(dustStack, 2500, 500)
                    .EUt(VH[LV])
                    .duration(20 * SECOND)
                    .buildAndRegister()

                SIFTER_RECIPES.recipeBuilder()
                    .circuitMeta(2)
                    .input(purifiedPrefix, material)
                    .fluidInputs(ZephyreanAerotheum.getFluid(250))
                    .chancedOutput(solitaryStack, 200, 100)
                    .chancedOutput(exquisiteStack, 1000, 300)
                    .chancedOutput(flawlessStack, 3000, 400)
                    .outputs(gemStack)
                    .chancedOutput(flawedStack, 4000, 1000)
                    .chancedOutput(chippedStack, 6000, 700)
                    .chancedOutput(dustStack, 5000, 1000)
                    .EUt(VH[LV])
                    .duration(10 * SECOND)
                    .buildAndRegister()
            }
            else
            {
                SIFTER_RECIPES.recipeBuilder()
                    .circuitMeta(1)
                    .input(purifiedPrefix, material)
                    .chancedOutput(solitaryStack, 50, 25)
                    .chancedOutput(exquisiteStack, 300, 100)
                    .chancedOutput(flawlessStack, 1000, 150)
                    .chancedOutput(gemStack, 3500, 500)
                    .chancedOutput(flawedStack, 2500, 300)
                    .chancedOutput(chippedStack, 3500, 400)
                    .chancedOutput(dustStack, 5000, 750)
                    .EUt(VH[LV])
                    .duration(20 * SECOND)
                    .buildAndRegister()

                SIFTER_RECIPES.recipeBuilder()
                    .circuitMeta(2)
                    .input(purifiedPrefix, material)
                    .fluidInputs(ZephyreanAerotheum.getFluid(250))
                    .chancedOutput(solitaryStack, 100, 50)
                    .chancedOutput(exquisiteStack, 600, 200)
                    .chancedOutput(flawlessStack, 2000, 300)
                    .chancedOutput(gemStack, 7000, 1000)
                    .chancedOutput(flawedStack, 5000, 600)
                    .chancedOutput(chippedStack, 7000, 800)
                    .outputs(dustStack)
                    .EUt(VH[LV])
                    .duration(10 * SECOND)
                    .buildAndRegister()
            }
        }

        processMetalSmelting(purifiedPrefix, material, property)
    }

    private fun processCrushedOre(crushedPrefix: OrePrefix, material: Material, property: OreProperty)
    {
        val byproductMaterial = property.getOreByProduct(0, material)
        val crushedPurifiedOre = copyFirst(OreDictUnifier.get(crushedPurified, material),
                                                        OreDictUnifier.get(dust, material))

        ORE_WASHER_RECIPES.recipeBuilder()
            .circuitMeta(3)
            .input(crushedPrefix, material)
            .fluidInputs(TectonicPetrotheum.getFluid(100))
            .outputs(crushedPurifiedOre)
            .output(dust, Stone)
            .chancedOutput(dust, byproductMaterial, 6666, 0)
            .duration(5 * SECOND)
            .buildAndRegister()
    }

    /**
     * @see gregtech.loaders.recipe.handlers.OreRecipeHandler.processMetalSmelting
     */
    private fun processMetalSmelting(crushedPrefix: OrePrefix, material: Material, property: OreProperty)
    {
        val smeltingResult = property.directSmeltResult ?: material
        if (smeltingResult.hasProperty(PropertyKey.INGOT))
        {
            val ingotStack = OreDictUnifier.get(ingot, smeltingResult)
            if (!ingotStack.isEmpty && doesMaterialUseNormalFurnace(smeltingResult))
            {
                ModHandler.addSmeltingRecipe(UnificationEntry(crushedPrefix, material), ingotStack, 0.5f)
            }
        }
    }

    /**
     * @see gregtech.loaders.recipe.handlers.OreRecipeHandler.doesMaterialUseNormalFurnace
     */
    private fun doesMaterialUseNormalFurnace(material: Material) = !material.hasProperty(PropertyKey.BLAST)

    // @formatter:on

}