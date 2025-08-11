package gregtechlite.gtlitecore.loader.recipe

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.LARGE_CHEMICAL_RECIPES
import gregtech.api.recipes.ingredients.IntCircuitIngredient
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Materials.Butyraldehyde
import gregtech.api.unification.material.Materials.CarbonMonoxide
import gregtech.api.unification.material.Materials.Chlorine
import gregtech.api.unification.material.Materials.Chloromethane
import gregtech.api.unification.material.Materials.DilutedHydrochloricAcid
import gregtech.api.unification.material.Materials.Dimethyldichlorosilane
import gregtech.api.unification.material.Materials.HydrochloricAcid
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.Methane
import gregtech.api.unification.material.Materials.Polydimethylsiloxane
import gregtech.api.unification.material.Materials.Propene
import gregtech.api.unification.material.Materials.Silicon
import gregtech.api.unification.material.Materials.SodiumBisulfate
import gregtech.api.unification.material.Materials.SodiumHydroxide
import gregtech.api.unification.material.Materials.SulfuricAcid
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.ore.OrePrefix.dust
import gregtechlite.gtlitecore.api.MINUTE
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeHandler

internal object RecipeConflicts
{

    // @formatter:off


    fun init()
    {
        // Conflict between Polydimethylsiloxane (C2H6OSi) and Carbon Tetrachloride (CCl4).
        GTLiteRecipeHandler.removeChemicalRecipes(
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

        // Conflict between Methyltrichlorosilane (Si(CH3)Cl3) and Dimethyldichlorosilane (Si(CH3)2Cl2)
        GTLiteRecipeHandler.removeChemicalRecipes(
            arrayOf(OreDictUnifier.get(dust, Silicon)),
            arrayOf(Chloromethane.getFluid(2000)))

        // Si + 2CH3Cl -> Si(CH3)2Cl2
        CHEMICAL_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(dust, Silicon)
            .fluidInputs(Chloromethane.getFluid(2000))
            .fluidOutputs(Dimethyldichlorosilane.getFluid(1000))
            .EUt(96) // MV
            .duration(12 * SECOND)
            .buildAndRegister()

        // Add an int circuit to C4H8O recipe.
        GTLiteRecipeHandler.removeChemicalRecipes(
            arrayOf(Propene.getFluid(1000),
                Hydrogen.getFluid(2000),
                CarbonMonoxide.getFluid(1000)))

        CHEMICAL_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .fluidInputs(Propene.getFluid(1000))
            .fluidInputs(Hydrogen.getFluid(2000))
            .fluidInputs(CarbonMonoxide.getFluid(1000))
            .fluidOutputs(Butyraldehyde.getFluid(1000))
            .EUt(VA[HV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // Conflicts between NaHSO4 and C6H6NNaO3S.
        GTLiteRecipeHandler.removeChemicalRecipes(
            arrayOf(OreDictUnifier.get(dust, SodiumHydroxide, 3)),
            arrayOf(SulfuricAcid.getFluid(1000)))

        CHEMICAL_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(dust, SodiumHydroxide, 3)
            .fluidInputs(SulfuricAcid.getFluid(1000))
            .output(dust, SodiumBisulfate, 7)
            .fluidOutputs(Water.getFluid(1000))
            .EUt(VA[LV])
            .duration(5 * SECOND)
            .buildAndRegister()

    }

    // @formatter:on

}