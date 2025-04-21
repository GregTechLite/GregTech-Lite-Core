package magicbook.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.ZPM
import gregtech.api.recipes.RecipeMaps.CHEMICAL_BATH_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.LARGE_CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.MIXER_RECIPES
import gregtech.api.unification.material.Materials.Ammonia
import gregtech.api.unification.material.Materials.Butyraldehyde
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.NitricAcid
import gregtech.api.unification.material.Materials.NitrogenDioxide
import gregtech.api.unification.material.Materials.SulfuricAcid
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.material.Materials.Zeolite
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.gem
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.CHEMICAL_DEHYDRATOR_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.CVD_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.ROASTER_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Alumina
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.AluminaSolution
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.AluminiumNitrate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.AmmoniumCyanate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Butanol
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Carbamide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Dichloromethane
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HydrogenCyanide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HydrogenPeroxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ManganeseSulfate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.NdYAG
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.NeodymiumDopedYttriumOxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.NeodymiumOxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PotassiumPermanganate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PotassiumSulfate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Tributylamine
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.UnprocessedNdYAGSolution
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.YttriumOxide
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK

@Suppress("MISSING_DEPENDENCY_CLASS")
class NdYAGChain
{

    companion object
    {

        fun init()
        {
            carbamideProcess()
            tributylamineProcess()
            aluminaSolutionProcess()
            ndYAGProcess()
        }

        private fun carbamideProcess()
        {
            // 2KMnO4 + 5NH3 + 5HCN + 3H2SO4 -> 2MnSO4 + K2SO4 + 5NH4CNO + 3H2O
            LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, PotassiumPermanganate, 12)
                .fluidInputs(Ammonia.getFluid(5000))
                .fluidInputs(HydrogenCyanide.getFluid(5000))
                .fluidInputs(SulfuricAcid.getFluid(3000))
                .output(dust, ManganeseSulfate, 12)
                .output(dust, PotassiumSulfate, 7)
                .fluidOutputs(AmmoniumCyanate.getFluid(5000))
                .fluidOutputs(Water.getFluid(3000))
                .EUt(VA[EV].toLong())
                .duration(40 * SECOND)
                .buildAndRegister()

            // NH4CNO -> CH4N2O
            CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .fluidInputs(AmmoniumCyanate.getFluid(1000))
                .output(dust, Carbamide, 8)
                .EUt(VA[HV].toLong())
                .duration(16 * SECOND)
                .buildAndRegister()
        }

        private fun tributylamineProcess()
        {
            // C4H8O + 2H -> C4H10O
            CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Butyraldehyde.getFluid(1000))
                .fluidInputs(Hydrogen.getFluid(2000))
                .fluidOutputs(Butanol.getFluid(1000))
                .EUt(VA[HV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // 3C4H10O + NH3 -> (C4H9)3N + 3H2O
            CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .notConsumable(dust, Zeolite)
                .fluidInputs(Butanol.getFluid(3000))
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidOutputs(Tributylamine.getFluid(1000))
                .fluidOutputs(Water.getFluid(3000))
                .EUt(VA[HV].toLong())
                .duration(7 * SECOND)
                .buildAndRegister()
        }

        private fun aluminaSolutionProcess()
        {
            // Al2O3 + HNO3 -> 2Al(NO3)3 + 3H2O
            CHEMICAL_BATH_RECIPES.recipeBuilder()
                .input(dust, Alumina, 5)
                .fluidInputs(NitricAcid.getFluid(6000))
                .output(dust, AluminiumNitrate, 26)
                .fluidOutputs(Water.getFluid(3000))
                .EUt(VA[LV].toLong())
                .duration(9 * SECOND + 10 * TICK)
                .buildAndRegister()

            // 2Al(NO3)3 + CH2Cl2 + 2(C4H9)3N -> (Al2O3)(CH2Cl2)(C12H27N)2 + 2HNO3 (cycle) + NO2
            LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, AluminiumNitrate, 26)
                .fluidInputs(Dichloromethane.getFluid(1000))
                .fluidInputs(Tributylamine.getFluid(2000))
                .fluidInputs(HydrogenPeroxide.getFluid(1000))
                .fluidOutputs(AluminaSolution.getFluid(1000))
                .fluidOutputs(NitricAcid.getFluid(2000))
                .fluidOutputs(NitrogenDioxide.getFluid(1000))
                .EUt(VA[HV].toLong())
                .duration(14 * SECOND)
                .buildAndRegister()
        }

        private fun ndYAGProcess()
        {
            // Y2O3 + Nd2O3 -> (Y2O3)(Nd2O3)
            ROASTER_RECIPES.recipeBuilder()
                .input(dust, YttriumOxide, 5)
                .input(dust, NeodymiumOxide, 5)
                .output(dust, NeodymiumDopedYttriumOxide, 10)
                .EUt(VA[IV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // CH4N2O + (Y2O3)(Nd2O3) + (Al2O3)(CH2Cl2)(C12H27N)2 -> 2Nd:YAG? + 2(C4H9)3N (cycle)
            CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Carbamide, 8)
                .input(dust, NeodymiumDopedYttriumOxide, 10)
                .fluidInputs(AluminaSolution.getFluid(1000))
                .fluidOutputs(UnprocessedNdYAGSolution.getFluid(2000))
                .fluidOutputs(Tributylamine.getFluid(2000))
                .EUt(VA[IV].toLong())
                .duration(16 * SECOND)
                .buildAndRegister()

            // Nd:YAG? -> Nd:YAG + CH2Cl2 (cycle)
            CVD_RECIPES.recipeBuilder()
                .fluidInputs(UnprocessedNdYAGSolution.getFluid(1000))
                .output(gem, NdYAG)
                .fluidOutputs(Dichloromethane.getFluid(1000))
                .EUt(VA[ZPM].toLong())
                .duration(27 * SECOND + 10 * TICK)
                .temperature(1884)
                .buildAndRegister()

        }

    }

}