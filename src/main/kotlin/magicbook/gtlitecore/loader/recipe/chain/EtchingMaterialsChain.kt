package magicbook.gtlitecore.loader.recipe.chain

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
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.CHEMICAL_DEHYDRATOR_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Ethylenediamine
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.EthylenediaminePyrocatechol
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HydrogenPeroxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PotassiumHydroxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Pyrocatechol
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TetramethylammoniumChloride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TetramethylammoniumHydroxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Trimethylamine
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND

@Suppress("MISSING_DEPENDENCY_CLASS")
class EtchingMaterialsChain
{

    companion object
    {

        fun init()
        {
            tmahProcess()
            edpProcess()
        }

        private fun tmahProcess()
        {
            //  N(CH3)3 + CH3Cl -> N(CH3)4Cl
            CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Trimethylamine.getFluid(1000))
                .fluidInputs(Chloromethane.getFluid(1000))
                .notConsumable(Ethanol.getFluid(4000))
                .output(dust, TetramethylammoniumChloride, 18)
                .EUt(VA[IV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // N(CH3)4Cl + KOH -> N(CH3)4OH + KCl
            CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, TetramethylammoniumChloride, 18)
                .input(dust, PotassiumHydroxide, 3)
                .fluidInputs(Water.getFluid(5000))
                .output(dust, RockSalt, 2)
                .fluidOutputs(TetramethylammoniumHydroxide.getFluid(5000))
                .EUt(VA[IV].toLong())
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
                .EUt(VA[HV].toLong())
                .duration(20 * SECOND)
                .buildAndRegister()

            // C6H4(OH)2 + 0.5H2O + C6H6O2 -> (C6H4(OH)2)(C6H6O2)
            MIXER_RECIPES.recipeBuilder()
                .input(dust, Pyrocatechol, 3)
                .fluidInputs(DistilledWater.getFluid(500))
                .fluidInputs(Ethylenediamine.getFluid(500))
                .fluidOutputs(EthylenediaminePyrocatechol.getFluid(1000))
                .EUt(VA[IV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()
        }

    }

}