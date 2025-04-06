package magicbook.gtlitecore.loader.recipe

import gregtech.api.recipes.RecipeMaps.LARGE_CHEMICAL_RECIPES
import gregtech.api.recipes.ingredients.IntCircuitIngredient
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Materials.Chlorine
import gregtech.api.unification.material.Materials.DilutedHydrochloricAcid
import gregtech.api.unification.material.Materials.HydrochloricAcid
import gregtech.api.unification.material.Materials.Methane
import gregtech.api.unification.material.Materials.Polydimethylsiloxane
import gregtech.api.unification.material.Materials.Silicon
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.ore.OrePrefix.dust
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.MINUTE
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTRecipeUtility

@Suppress("MISSING_DEPENDENCY_CLASS")
class RecipeConflicts
{

    companion object
    {

        fun init()
        {
            // Conflict between Polydimethylsiloxane (C2H6OSi) and Carbon Tetrachloride (CCl4).
            GTRecipeUtility.removeChemicalRecipes(
                arrayOf(OreDictUnifier.get(dust, Silicon),
                    IntCircuitIngredient.getIntegratedCircuit(2)),
                arrayOf(Water.getFluid(1000),
                    Chlorine.getFluid(4000),
                    Methane.getFluid(2000)))

            LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .circuitMeta(24)
                .input(dust, Silicon, 3)
                .fluidInputs(Water.getFluid(3000))
                .fluidInputs(Methane.getFluid(6000))
                .fluidInputs(Chlorine.getFluid(12000))
                .output(dust, Polydimethylsiloxane, 9)
                .fluidOutputs(HydrochloricAcid.getFluid(6000))
                .fluidOutputs(DilutedHydrochloricAcid.getFluid(6000))
                .EUt(96) // MV
                .duration(2 * MINUTE + 24 * SECOND)
                .buildAndRegister()
        }

    }

}