package magicbook.gtlitecore.loader.recipe.oreprocessing

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.GTRecipeHandler
import gregtech.api.recipes.RecipeMaps.BLAST_RECIPES
import gregtech.api.recipes.RecipeMaps.CENTRIFUGE_RECIPES
import gregtech.api.recipes.RecipeMaps.ELECTROLYZER_RECIPES
import gregtech.api.unification.material.Materials.Carbon
import gregtech.api.unification.material.Materials.CarbonDioxide
import gregtech.api.unification.material.Materials.Magnesia
import gregtech.api.unification.material.Materials.Magnesium
import gregtech.api.unification.material.Materials.Plutonium239
import gregtech.api.unification.material.Materials.Plutonium241
import gregtech.api.unification.material.Materials.Uraninite
import gregtech.api.unification.material.Materials.Uranium
import gregtech.api.unification.material.Materials.UraniumHexafluoride
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.ingot
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.ROASTER_RECIPES
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK

@Suppress("MISSING_DEPENDENCY_CLASS")
class UraniumPlutoniumProcessing
{

    companion object
    {

        fun init()
        {
            // Add several simplified recipes for U, U235, and U238.
            GTRecipeHandler.removeRecipesByInputs(ELECTROLYZER_RECIPES,
                UraniumHexafluoride.getFluid(1000))

            // UO2 + 2Mg -> U + 2MgO
            ROASTER_RECIPES.recipeBuilder()
                .input(dust, Uraninite, 3)
                .input(dust, Magnesium, 2)
                .output(dust, Uranium)
                .output(dust, Magnesia, 4)
                .EUt(VA[HV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // UO2 + C -> U + CO2
            BLAST_RECIPES.recipeBuilder()
                .input(dust, Uraninite, 3)
                .input(dust, Carbon)
                .output(ingot, Uranium)
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .EUt(VA[MV].toLong())
                .duration(15 * SECOND)
                .blastFurnaceTemp(600)
                .buildAndRegister()

            // Pu239 -> Pu241
            CENTRIFUGE_RECIPES.recipeBuilder()
                .input(dust, Plutonium239)
                .output(dust, Plutonium241)
                .EUt(VA[MV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

        }

    }

}