package magicbook.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.VA
import gregtech.api.metatileentity.multiblock.CleanroomType
import gregtech.api.recipes.RecipeMaps.BREWING_RECIPES
import gregtech.api.recipes.RecipeMaps.CENTRIFUGE_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_BATH_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.CRACKING_RECIPES
import gregtech.api.recipes.RecipeMaps.DISTILLATION_RECIPES
import gregtech.api.recipes.RecipeMaps.MIXER_RECIPES
import gregtech.api.recipes.RecipeMaps.PYROLYSE_RECIPES
import gregtech.api.unification.material.Materials.AceticAcid
import gregtech.api.unification.material.Materials.Bromine
import gregtech.api.unification.material.Materials.Butane
import gregtech.api.unification.material.Materials.Caesium
import gregtech.api.unification.material.Materials.CarbonDioxide
import gregtech.api.unification.material.Materials.Chlorine
import gregtech.api.unification.material.Materials.CoalTar
import gregtech.api.unification.material.Materials.Dimethylamine
import gregtech.api.unification.material.Materials.HydrochloricAcid
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.Methane
import gregtech.api.unification.material.Materials.Methanol
import gregtech.api.unification.material.Materials.Naphthalene
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Platinum
import gregtech.api.unification.material.Materials.Salt
import gregtech.api.unification.material.Materials.Sodium
import gregtech.api.unification.material.Materials.SodiumHydroxide
import gregtech.api.unification.material.Materials.Steam
import gregtech.api.unification.material.Materials.SulfurDioxide
import gregtech.api.unification.material.Materials.Toluene
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.foil
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.BURNER_REACTOR_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.CHEMICAL_DEHYDRATOR_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.CHEMICAL_PLANT_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.AcetylChloride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BenzylBromide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BenzyltrimethylammoniumBromide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BromoBromomethylNaphthalene
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Bromomethane
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Butyllithium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CaesiumCarbonate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CaesiumHydroxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Chlorobutane
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Dimethylacetamide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Fullerene
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.GeodesicPolyarene
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HydrobromicAcid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HydrogenPeroxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Indanone
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Indene
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PalladiumAcetate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PotassiumHydroxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PotassiumTertbutoxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SodioIndene
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SteamCrackedSodioIndene
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TertbutylAlcohol
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ThionylChloride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Trimethylamine
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Truxene
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK

@Suppress("MISSING_DEPENDENCY_CLASS")
class FullereneChain
{

    companion object
    {

        fun init()
        {
            truxeneProcess()
            bromoBromomethylNaphthaleneProcess()
            btabProcess()
            potassiumTertbutoxideProcess()
            caesiumCarbonateProcess()
            dimethylacetamideProcess()
            fullereneProcess()
        }

        private fun truxeneProcess()
        {
            // Na + ? -> Na(C6H4C3H4)?
            BREWING_RECIPES.recipeBuilder()
                .input(dust, Sodium)
                .fluidInputs(CoalTar.getFluid(1000))
                .fluidOutputs(SodioIndene.getFluid(1000))
                .EUt(VA[IV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // Na(C6H4C3H4)? + H2O -> Na(C6H4C3H4)·H2O
            CRACKING_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .fluidInputs(SodioIndene.getFluid(1000))
                .fluidInputs(Steam.getFluid(1000))
                .fluidOutputs(SteamCrackedSodioIndene.getFluid(1000))
                .EUt(VA[IV].toLong())
                .duration(8 * SECOND)
                .buildAndRegister()

            // Distilled Na(C6H4C3H4)·H2O to split Na and C6H4C3H4
            DISTILLATION_RECIPES.recipeBuilder()
                .fluidInputs(SteamCrackedSodioIndene.getFluid(1000))
                .output(dust, Sodium)
                .fluidOutputs(Indene.getFluid(1000))
                .EUt(VA[IV].toLong())
                .duration(12 * SECOND)
                .buildAndRegister()

            // C6H4C3H4 + O -> C6H4C3H4O
            CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Indene.getFluid(1000))
                .fluidInputs(Oxygen.getFluid(1000))
                .output(dust, Indanone, 18)
                .EUt(VA[IV].toLong())
                .duration(4 * SECOND)
                .buildAndRegister()

            // 3C9H8O -> C27H18 + 3H2O
            CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .input(dust, Indanone, 54)
                .fluidOutputs(Truxene.getFluid(1000))
                .fluidOutputs(Water.getFluid(3000))
                .EUt(VA[EV].toLong())
                .duration(24 * SECOND)
                .buildAndRegister()
        }

        private fun bromoBromomethylNaphthaleneProcess()
        {
            // CH3OH + HBr -> CH3Br + H2O
            CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Methanol.getFluid(1000))
                .fluidInputs(HydrobromicAcid.getFluid(1000))
                .fluidOutputs(Bromomethane.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .EUt(VA[LV].toLong())
                .duration(8 * SECOND)
                .buildAndRegister()

            // C10H8 + 2CH3Br -> C11H8Br2 + CH4 + 2H (lost)
            CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Naphthalene.getFluid(1000))
                .fluidInputs(Bromomethane.getFluid(2000))
                .fluidOutputs(BromoBromomethylNaphthalene.getFluid(1000))
                .fluidOutputs(Methane.getFluid(1000))
                .EUt(VA[IV].toLong())
                .duration(16 * SECOND)
                .buildAndRegister()
        }

        private fun btabProcess()
        {
            // C7H8 (C6H5CH3) + Br -> C6H5CH2Br
            BURNER_REACTOR_RECIPES.recipeBuilder()
                .fluidInputs(Toluene.getFluid(1000))
                .fluidInputs(Bromine.getFluid(1000))
                .fluidOutputs(BenzylBromide.getFluid(1000))
                .EUt(VA[HV].toLong())
                .duration(2 * SECOND)
                .buildAndRegister()

            // C6H5CH2Br + (CH3)3N -> C6H5CH2N(CH3)3Br
            MIXER_RECIPES.recipeBuilder()
                .fluidInputs(BenzylBromide.getFluid(1000))
                .fluidInputs(Trimethylamine.getFluid(1000))
                .output(dust, BenzyltrimethylammoniumBromide, 28)
                .EUt(VA[LuV].toLong())
                .duration(4 * SECOND)
                .buildAndRegister()

            // C6H5CH2N(CH3)3Br decomposition.
            CENTRIFUGE_RECIPES.recipeBuilder()
                .input(dust, BenzyltrimethylammoniumBromide, 28)
                .fluidOutputs(BenzylBromide.getFluid(1000))
                .fluidOutputs(Trimethylamine.getFluid(1000))
                .EUt(VA[MV].toLong())
                .duration(8 * SECOND)
                .buildAndRegister()
        }

        private fun potassiumTertbutoxideProcess()
        {
            // C4H10 + 2Cl -> C4H9Cl + HCl
            CHEMICAL_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .fluidInputs(Butane.getFluid(1000))
                .fluidInputs(Chlorine.getFluid(2000))
                .fluidOutputs(Chlorobutane.getFluid(1000))
                .fluidOutputs(HydrochloricAcid.getFluid(1000))
                .EUt(VA[HV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // C4H9Cl + H2O -> C4H10O + HCl
            CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Chlorobutane.getFluid(1000))
                .fluidInputs(Water.getFluid(1000))
                .fluidOutputs(TertbutylAlcohol.getFluid(1000))
                .fluidOutputs(HydrochloricAcid.getFluid(1000))
                .EUt(VA[EV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // KOH + C4H10O -> C4H9OK + H2O
            CHEMICAL_BATH_RECIPES.recipeBuilder()
                .input(dust, PotassiumHydroxide, 3)
                .fluidInputs(TertbutylAlcohol.getFluid(1000))
                .output(dust, PotassiumTertbutoxide, 15)
                .fluidOutputs(Water.getFluid(1000))
                .EUt(VA[IV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()
        }

        private fun caesiumCarbonateProcess()
        {
            // 2Cs + H2O2 -> 2CsOH
            CHEMICAL_BATH_RECIPES.recipeBuilder()
                .input(dust, Caesium, 2)
                .fluidInputs(HydrogenPeroxide.getFluid(1000))
                .output(dust, CaesiumHydroxide, 6)
                .EUt(VA[HV].toLong())
                .duration(9 * SECOND)
                .buildAndRegister()

            // CsOH + CO2 -> Cs2CO3
            CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, CaesiumHydroxide, 3)
                .fluidInputs(CarbonDioxide.getFluid(1000))
                .output(dust, CaesiumCarbonate, 6)
                .EUt(VA[EV].toLong())
                .duration(4 * SECOND + 10 * TICK)
                .buildAndRegister()
        }

        private fun dimethylacetamideProcess()
        {
            // SOCl2 + CH3COOH -> CH3COCl + SO2 + HCl
            BURNER_REACTOR_RECIPES.recipeBuilder()
                .fluidInputs(ThionylChloride.getFluid(1000))
                .fluidInputs(AceticAcid.getFluid(1000))
                .fluidOutputs(AcetylChloride.getFluid(1000))
                .fluidOutputs(SulfurDioxide.getFluid(1000))
                .fluidOutputs(HydrochloricAcid.getFluid(1000))
                .EUt(VA[MV].toLong())
                .duration(15 * SECOND)
                .buildAndRegister()

            // NaOH + CH3COCl + (CH3)2NH -> (CH3)2NC(O)CH3 + NaCl + H2O
            CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, SodiumHydroxide, 3)
                .fluidInputs(AcetylChloride.getFluid(1000))
                .fluidInputs(Dimethylamine.getFluid(1000))
                .output(dust, Salt, 2)
                .fluidOutputs(Dimethylacetamide.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .EUt(VA[IV].toLong())
                .duration(6 * SECOND)
                .buildAndRegister()
        }

        /**
         * Total catalyst list of Geodesic Polyarene:
         * - Benzyltrimethylammonium Bromide (C6H5CH2N(CH3)3Br)
         * - Potassium Tertbutoxide (C4H9OK)
         * - Caesium Carbonate (Cs2CO3)
         * - Palladium Acetate (Pd(CH3COOH)2)
         * - Butyllithium (C4H9Li)
         * - Tertbutyl Alcohol (C4H10O)
         * - Dimethylacetamide ((CH3)2NC(O)CH3)
         */
        private fun fullereneProcess()
        {
            // C27H18 + 3C11H8Br2 -> C60H30 + 2HBr + 10H
            CHEMICAL_PLANT_RECIPES.recipeBuilder()
                .notConsumable(dust, BenzyltrimethylammoniumBromide)
                .notConsumable(dust, PotassiumTertbutoxide)
                .notConsumable(dust, CaesiumCarbonate)
                .input(dust, PalladiumAcetate)
                .fluidInputs(Truxene.getFluid(1000))
                .fluidInputs(BromoBromomethylNaphthalene.getFluid(3000))
                .fluidInputs(Butyllithium.getFluid(10))
                .notConsumable(TertbutylAlcohol.getFluid(1))
                .notConsumable(Dimethylacetamide.getFluid(1))
                .output(dust, GeodesicPolyarene, 60)
                .fluidOutputs(HydrobromicAcid.getFluid(2000))
                .fluidOutputs(Hydrogen.getFluid(10000))
                .EUt(VA[UHV].toLong())
                .duration(30 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // C60H30 -> C60 + 30H
            PYROLYSE_RECIPES.recipeBuilder()
                .input(dust, GeodesicPolyarene)
                .input(foil, Platinum)
                .output(dust, Fullerene)
                .fluidOutputs(Hydrogen.getFluid(500))
                .EUt(VA[UHV].toLong())
                .duration(10 * TICK)
                .buildAndRegister()
        }

    }

}