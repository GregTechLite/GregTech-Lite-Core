package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.MIXER_RECIPES
import gregtech.api.unification.material.Materials.Chloromethane
import gregtech.api.unification.material.Materials.DistilledWater
import gregtech.api.unification.material.Materials.Ethanol
import gregtech.api.unification.material.Materials.Phenol
import gregtech.api.unification.material.Materials.RockSalt
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.ore.OrePrefix.dust
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Ethylenediamine
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.EthylenediaminePyrocatechol
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HydrogenPeroxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PotassiumHydroxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Pyrocatechol
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TetramethylammoniumChloride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TetramethylammoniumHydroxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Trimethylamine
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt

internal object EtchingMaterialsChain
{

    // @formatter:off

    fun init()
    {
        tmahProcess()
        edpProcess()
    }

    private fun tmahProcess()
    {
        // N(CH3)3 + CH3Cl -> N(CH3)4Cl
        CHEMICAL_RECIPES.recipeBuilder()
            .fluidInputs(Trimethylamine.getFluid(1000))
            .fluidInputs(Chloromethane.getFluid(1000))
            .notConsumable(Ethanol.getFluid(4000))
            .output(dust, TetramethylammoniumChloride, 18)
            .EUt(VA[IV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // N(CH3)4Cl + KOH -> N(CH3)4OH + KCl
        CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, TetramethylammoniumChloride, 18)
            .input(dust, PotassiumHydroxide, 3)
            .fluidInputs(Water.getFluid(5000))
            .output(dust, RockSalt, 2)
            .fluidOutputs(TetramethylammoniumHydroxide.getFluid(5000))
            .EUt(VA[IV])
            .duration(10 * SECOND)
            .buildAndRegister()
    }

    private fun edpProcess()
    {
        // C6H5OH + H2O2 -> C6H4(OH)2 + H2O
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
            .fluidInputs(Phenol.getFluid(1000))
            .fluidInputs(HydrogenPeroxide.getFluid(1000))
            .output(dust, Pyrocatechol, 12)
            .fluidOutputs(Water.getFluid(1000))
            .EUt(VA[HV])
            .duration(20 * SECOND)
            .buildAndRegister()

        // C6H4(OH)2 + 0.5H2O + C6H6O2 -> (C6H4(OH)2)(C6H6O2)
        MIXER_RECIPES.recipeBuilder()
            .input(dust, Pyrocatechol, 3)
            .fluidInputs(DistilledWater.getFluid(500))
            .fluidInputs(Ethylenediamine.getFluid(500))
            .fluidOutputs(EthylenediaminePyrocatechol.getFluid(1000))
            .EUt(VA[IV])
            .duration(10 * SECOND)
            .buildAndRegister()
    }

    // @formatter:on

}