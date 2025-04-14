package magicbook.gtlitecore.loader.recipe.oreprocessing

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.V
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.ZPM
import gregtech.api.recipes.GTRecipeHandler
import gregtech.api.recipes.RecipeMaps.AUTOCLAVE_RECIPES
import gregtech.api.recipes.RecipeMaps.CENTRIFUGE_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_BATH_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.DISTILLATION_RECIPES
import gregtech.api.recipes.RecipeMaps.DISTILLERY_RECIPES
import gregtech.api.recipes.RecipeMaps.GAS_TURBINE_FUELS
import gregtech.api.recipes.RecipeMaps.LARGE_CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.MIXER_RECIPES
import gregtech.api.recipes.RecipeMaps.SIFTER_RECIPES
import gregtech.api.recipes.ingredients.IntCircuitIngredient
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Materials.AcidicEnrichedNaquadahSolution
import gregtech.api.unification.material.Materials.AcidicNaquadriaSolution
import gregtech.api.unification.material.Materials.Ammonia
import gregtech.api.unification.material.Materials.AntimonyTrifluoride
import gregtech.api.unification.material.Materials.BariumSulfide
import gregtech.api.unification.material.Materials.Calcium
import gregtech.api.unification.material.Materials.DistilledWater
import gregtech.api.unification.material.Materials.EnrichedNaquadahSolution
import gregtech.api.unification.material.Materials.EnrichedNaquadahSulfate
import gregtech.api.unification.material.Materials.EnrichedNaquadahWaste
import gregtech.api.unification.material.Materials.FluoroantimonicAcid
import gregtech.api.unification.material.Materials.GalliumSulfide
import gregtech.api.unification.material.Materials.HydrofluoricAcid
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.HydrogenSulfide
import gregtech.api.unification.material.Materials.ImpureEnrichedNaquadahSolution
import gregtech.api.unification.material.Materials.ImpureNaquadriaSolution
import gregtech.api.unification.material.Materials.IndiumPhosphide
import gregtech.api.unification.material.Materials.Lutetium
import gregtech.api.unification.material.Materials.Naquadah
import gregtech.api.unification.material.Materials.NaquadahEnriched
import gregtech.api.unification.material.Materials.Naquadria
import gregtech.api.unification.material.Materials.NaquadriaSolution
import gregtech.api.unification.material.Materials.NaquadriaSulfate
import gregtech.api.unification.material.Materials.NaquadriaWaste
import gregtech.api.unification.material.Materials.NitricAcid
import gregtech.api.unification.material.Materials.Nitrogen
import gregtech.api.unification.material.Materials.NitrogenDioxide
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.PhosphoricAcid
import gregtech.api.unification.material.Materials.Plutonium239
import gregtech.api.unification.material.Materials.Plutonium241
import gregtech.api.unification.material.Materials.Quicklime
import gregtech.api.unification.material.Materials.SodiumHydroxide
import gregtech.api.unification.material.Materials.SodiumSulfide
import gregtech.api.unification.material.Materials.Steam
import gregtech.api.unification.material.Materials.Sulfur
import gregtech.api.unification.material.Materials.SulfurDioxide
import gregtech.api.unification.material.Materials.SulfuricAcid
import gregtech.api.unification.material.Materials.TitaniumTrifluoride
import gregtech.api.unification.material.Materials.Trinium
import gregtech.api.unification.material.Materials.TriniumSulfide
import gregtech.api.unification.material.Materials.Uranium235
import gregtech.api.unification.material.Materials.Uranium238
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.dustSmall
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.BURNER_REACTOR_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.CHEMICAL_PLANT_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.CRYOGENIC_REACTOR_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.ROASTER_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.AmmoniumNitrate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BariumHydroxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CalciumSulfide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CrudeNaquadahFuel
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.GalliumTrioxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HeavyNaquadahFuel
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.IndiumPhosphate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LightNaquadahFuel
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LowPurityEnrichedNaquadahEmulsion
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LowPurityNaquadriaEmulsion
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MediumNaquadahFuel
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.NaquadahGas
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.NaquadriaEnergetic
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TechnetiumHeptaoxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TriniumTrioxide
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.MINUTE
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK
import magicbook.gtlitecore.api.utils.GTRecipeUtility

/**
 * Produces Enriched Naquadah and Naquadria from Naquadah.
 * - Main Products: Enriched Naquadah, Naquadria, Trinium.
 * - Side Products: Indium, Gallium, Titanium, Technetium and Naquadah Fuels.
 */
@Suppress("MISSING_DEPENDENCY_CLASS")
class NaquadahProcessing
{

    companion object
    {

        fun init()
        {
            removeVanillaRecipes()
            naquadahProcess()
            naquadahEnrichedProcess()
            naquadriaProcess()
            naquadahFuelProcess()
        }

        private fun removeVanillaRecipes()
        {
            // Remove Nq + H2SbF7 -> TiF3 + Impurified Nq+/Nq* Solutions.
            GTRecipeHandler.removeRecipesByInputs(LARGE_CHEMICAL_RECIPES,
                arrayOf(OreDictUnifier.get(dust, Naquadah, 6)),
                arrayOf(FluoroantimonicAcid.getFluid(1000)))

            // Remove Impurified Nq+ Solution -> Nq+ Solution Centrifuging.
            GTRecipeHandler.removeRecipesByInputs(CENTRIFUGE_RECIPES, ImpureEnrichedNaquadahSolution.getFluid(2000))
            // Remove Impurified Nq* Solution -> Nq* Solution Centrifuging.
            GTRecipeHandler.removeRecipesByInputs(CENTRIFUGE_RECIPES, ImpureNaquadriaSolution.getFluid(2000))

            // Remove Nq+ Solution -> Acidic Nq+ Solution Mixing.
            GTRecipeUtility.removeMixerRecipes(
                arrayOf(EnrichedNaquadahSolution.getFluid(1000),
                    SulfuricAcid.getFluid(2000)))
            // Remove Nq* Solution -> Acidic Nq* Solution Mixing.
            GTRecipeUtility.removeMixerRecipes(
                arrayOf(NaquadriaSolution.getFluid(1000),
                    SulfuricAcid.getFluid(2000)))

            // Remove Acidic Nq+ Solution -> Nq+SO4 Centrifuging.
            GTRecipeHandler.removeRecipesByInputs(CENTRIFUGE_RECIPES,
                AcidicEnrichedNaquadahSolution.getFluid(3000))
            // Remove Acidic Nq* Solution -> Nq*SO4 Centrifuging.
            GTRecipeHandler.removeRecipesByInputs(CENTRIFUGE_RECIPES,
                AcidicNaquadriaSolution.getFluid(3000))

            // Remove Nq+ Waste distillation recipes.
            GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
                arrayOf(IntCircuitIngredient.getIntegratedCircuit(1)),
                arrayOf(EnrichedNaquadahWaste.getFluid(200)))
            GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
                arrayOf(IntCircuitIngredient.getIntegratedCircuit(2)),
                arrayOf(EnrichedNaquadahWaste.getFluid(200)))
            GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
                arrayOf(IntCircuitIngredient.getIntegratedCircuit(3)),
                arrayOf(EnrichedNaquadahWaste.getFluid(400)))
            GTRecipeHandler.removeRecipesByInputs(DISTILLATION_RECIPES,
                EnrichedNaquadahWaste.getFluid(2000))

            // Remove Nq* Waste distillation recipes.
            GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
                arrayOf(IntCircuitIngredient.getIntegratedCircuit(1)),
                arrayOf(NaquadriaWaste.getFluid(200)))
            GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
                arrayOf(IntCircuitIngredient.getIntegratedCircuit(2)),
                arrayOf(NaquadriaWaste.getFluid(200)))
            GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
                arrayOf(IntCircuitIngredient.getIntegratedCircuit(3)),
                arrayOf(NaquadriaWaste.getFluid(400)))
            GTRecipeHandler.removeRecipesByInputs(DISTILLATION_RECIPES,
                NaquadriaWaste.getFluid(2000))

        }

        private fun naquadahProcess()
        {
            // Nq + H2SbF7 -> TiF3 + Low Purity Nq+/Nq* Emulsion
            BURNER_REACTOR_RECIPES.recipeBuilder()
                .input(dust, Naquadah, 6)
                .fluidInputs(FluoroantimonicAcid.getFluid(1000))
                .output(dust, TitaniumTrifluoride, 4)
                .fluidOutputs(LowPurityEnrichedNaquadahEmulsion.getFluid(1000))
                .fluidOutputs(LowPurityNaquadriaEmulsion.getFluid(1000))
                .EUt(VA[LuV].toLong())
                .duration(20 * SECOND)
                .buildAndRegister()
        }

        private fun naquadahEnrichedProcess()
        {
            // NaOH + Low Purity Nq+ Emulsion -> Ba(OH)2 + SbF3 + Impure Enriched Nq+ Solution
            CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, SodiumHydroxide, 3)
                .fluidInputs(LowPurityEnrichedNaquadahEmulsion.getFluid(2000))
                .output(dust, BariumHydroxide, 5)
                .output(dust, AntimonyTrifluoride, 2)
                .fluidOutputs(ImpureEnrichedNaquadahSolution.getFluid(1000))
                .EUt(VA[EV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Impure Nq+ Solution -> (Tc2O7) + Nq+ Solution + Nq+ Waste
            CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(ImpureEnrichedNaquadahSolution.getFluid(2000))
                .chancedOutput(dust, TechnetiumHeptaoxide, 3, 2000, 500)
                .fluidOutputs(EnrichedNaquadahSolution.getFluid(1000))
                .fluidOutputs(EnrichedNaquadahWaste.getFluid(1000))
                .EUt(VA[HV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // Nq+ Solution + 2H2SO4 -> Ke2O3 + Acidic Nq+ Solution
            CRYOGENIC_REACTOR_RECIPES.recipeBuilder()
                .fluidInputs(EnrichedNaquadahSolution.getFluid(1000))
                .fluidInputs(SulfuricAcid.getFluid(2000))
                .output(dust, TriniumTrioxide, 5)
                .fluidOutputs(AcidicEnrichedNaquadahSolution.getFluid(1000))
                .EUt(VA[LuV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Ke2O3 + 2H2S -> 2KeS + 2H2O + O (drop)
            ROASTER_RECIPES.recipeBuilder()
                .input(dust, TriniumTrioxide, 5)
                .fluidInputs(HydrogenSulfide.getFluid(2000))
                .output(dust, TriniumSulfide, 2)
                .fluidOutputs(Steam.getFluid(2000))
                .EUt(VA[HV].toLong())
                .duration(2 * SECOND + 15 * TICK)
                .buildAndRegister()

            // KeS + Zn -> Ke + ZnS (EBF, 7200K) from original Gregtech Naquadah processing.

            // 2x Acidic Nq+ Solution -> Nq+SO4 + Na2S
            AUTOCLAVE_RECIPES.recipeBuilder()
                .input(dust, SodiumHydroxide, 6)
                .fluidInputs(AcidicEnrichedNaquadahSolution.getFluid(2000))
                .output(dust, EnrichedNaquadahSulfate, 6)
                .output(dust, SodiumSulfide, 3)
                .EUt(VA[IV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Nq+SO4 + H -> Nq+ + H2SO4 from original Gregtech Naquadah processing.

            // Nq+ Waste -> (Nq) + 0.5HF + 0.3Nq+ Solution (raw cycling)
            DISTILLATION_RECIPES.recipeBuilder()
                .fluidInputs(EnrichedNaquadahWaste.getFluid(2000))
                .chancedOutput(dustSmall, Naquadah, 4000, 500)
                .fluidOutputs(HydrofluoricAcid.getFluid(500))
                .fluidOutputs(EnrichedNaquadahSolution.getFluid(350))
                .EUt(VA[IV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Addition recipe to Ba(OH)2 -> BaS (BaS is vanilla produce of Enriched Naquadah chain,
            // but in our chain, BaS is not a produce, but consider to usage of future, we add
            // a recipe for Ba(OH)2 -> BaS). Hint: Ba(OH)2 also can do Electrolyzer Decomposition.

            // Ba(OH)2 + Na2S -> BaS + 2NaOH
            CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, BariumHydroxide, 5)
                .input(dust, SodiumSulfide, 3)
                .output(dust, BariumSulfide, 2)
                .output(dust, SodiumHydroxide, 6)
                .EUt(VA[MV].toLong())
                .duration(3 * SECOND + 4 * TICK)
                .buildAndRegister()
        }

        private fun naquadriaProcess()
        {
            // Low Purity Nq* Emulsion + 2H3PO4 -> Impure Nq* Solution + InPO4 + 0.5SbF3
            CRYOGENIC_REACTOR_RECIPES.recipeBuilder()
                .fluidInputs(LowPurityNaquadriaEmulsion.getFluid(1000))
                .fluidInputs(PhosphoricAcid.getFluid(2000))
                .output(dust, IndiumPhosphate, 6)
                .output(dust, AntimonyTrifluoride, 2)
                .fluidOutputs(ImpureNaquadriaSolution.getFluid(1000))
                .EUt(VA[IV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // InPO4 + 8H -> InP + 4H2O
            CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, IndiumPhosphate, 6)
                .fluidInputs(Hydrogen.getFluid(8000))
                .output(dust, IndiumPhosphide, 2)
                .fluidOutputs(Water.getFluid(4000))
                .EUt(VA[HV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // Impure Nq* Solution -> (Tc2O7) + Nq* Solution + Nq* Waste
            CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(ImpureNaquadriaSolution.getFluid(2000))
                .chancedOutput(dust, TechnetiumHeptaoxide, 6, 2000, 500)
                .fluidOutputs(NaquadriaSolution.getFluid(1000))
                .fluidOutputs(NaquadriaWaste.getFluid(1000))
                .EUt(VA[EV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // Nq* Solution + H2SO4 -> Ga2O3 + Acidic Nq* Solution
            SIFTER_RECIPES.recipeBuilder()
                .fluidInputs(NaquadriaSolution.getFluid(1000))
                .fluidInputs(SulfuricAcid.getFluid(2000))
                .output(dust, GalliumTrioxide, 5)
                .fluidOutputs(AcidicNaquadriaSolution.getFluid(1000))
                .EUt(VA[ZPM].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Addition recipes for Ga2O3, GaS convert.
            // Ga2O3 + 2S -> 2GaS + 3O
            ROASTER_RECIPES.recipeBuilder()
                .input(dust, GalliumTrioxide, 5)
                .input(dust, Sulfur, 2)
                .output(dust, GalliumSulfide, 4)
                .fluidOutputs(Oxygen.getFluid(3000))
                .EUt(VA[EV].toLong())
                .duration(2 * SECOND)
                .buildAndRegister()

            // Acidic Nq* Solution + CaO -> Nq*SO4 + CaS
            CHEMICAL_BATH_RECIPES.recipeBuilder()
                .input(dust, Quicklime, 2)
                .fluidInputs(AcidicNaquadriaSolution.getFluid(2000))
                .output(dust, NaquadriaSulfate, 6)
                .output(dust, CalciumSulfide, 2)
                .EUt(VA[LuV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Nq*SO4 + 2H -> Nq* + H2SO4 by original processing.

            // Nq* Waste -> (Nq+) + 0.5HF + 0.35Nq* Solution
            DISTILLATION_RECIPES.recipeBuilder()
                .fluidInputs(NaquadriaWaste.getFluid(2000))
                .chancedOutput(dustSmall, NaquadahEnriched, 4000, 500)
                .fluidOutputs(HydrofluoricAcid.getFluid(500))
                .fluidOutputs(NaquadriaSolution.getFluid(350))
                .EUt(VA[LuV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Addition recipes for CaS-CaO recycling.

            // Ca + S -> CaS
            CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Calcium)
                .input(dust, Sulfur)
                .output(dust, CalciumSulfide, 2)
                .EUt(VA[LV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // CaS + 2O -> CaO + SO
            ROASTER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(dust, CalciumSulfide, 2)
                .fluidInputs(Oxygen.getFluid(2000))
                .output(dust, Quicklime, 2)
                .fluidOutputs(SulfurDioxide.getFluid(1000))
                .EUt(VA[LV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()
        }

        private fun naquadahFuelProcess()
        {
            // Nq + NH4NO3 -> Nq(NH4NO3)?
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .input(dust, Naquadah)
                .input(dust, AmmoniumNitrate, 2)
                .fluidInputs(Water.getFluid(1000))
                .fluidOutputs(CrudeNaquadahFuel.getFluid(1000))
                .EUt(VA[IV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .input(dust, Naquadah)
                .input(dust, AmmoniumNitrate, 2)
                .fluidInputs(DistilledWater.getFluid(1000))
                .fluidOutputs(CrudeNaquadahFuel.getFluid(1000))
                .EUt(VA[IV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Nq+ + NH4NO3 -> 3Nq(NH4NO3)?
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .input(dust, NaquadahEnriched)
                .input(dust, AmmoniumNitrate, 2)
                .fluidInputs(Water.getFluid(1000))
                .fluidOutputs(CrudeNaquadahFuel.getFluid(3000))
                .EUt(VA[LuV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .input(dust, NaquadahEnriched)
                .input(dust, AmmoniumNitrate, 2)
                .fluidInputs(DistilledWater.getFluid(1000))
                .fluidOutputs(CrudeNaquadahFuel.getFluid(3000))
                .EUt(VA[LuV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Nq* + NH4NO3 -> 6Nq(NH4NO3)?
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .input(dust, Naquadria)
                .input(dust, AmmoniumNitrate, 2)
                .fluidInputs(Water.getFluid(1000))
                .fluidOutputs(CrudeNaquadahFuel.getFluid(6000))
                .EUt(VA[ZPM].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .input(dust, Naquadria)
                .input(dust, AmmoniumNitrate, 2)
                .fluidInputs(DistilledWater.getFluid(1000))
                .fluidOutputs(CrudeNaquadahFuel.getFluid(6000))
                .EUt(VA[ZPM].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Crude Naquadah Fuel fraction.
            DISTILLATION_RECIPES.recipeBuilder()
                .fluidInputs(CrudeNaquadahFuel.getFluid(9000))
                .chancedOutput(dust, Naquadah, 1000, 500)
                .fluidOutputs(HeavyNaquadahFuel.getFluid(1000))
                .fluidOutputs(MediumNaquadahFuel.getFluid(2000))
                .fluidOutputs(LightNaquadahFuel.getFluid(3000))
                .fluidOutputs(NaquadahGas.getFluid(1000))
                .fluidOutputs(NitricAcid.getFluid(800))
                .fluidOutputs(Ammonia.getFluid(400))
                .fluidOutputs(EnrichedNaquadahWaste.getFluid(400))
                .fluidOutputs(NaquadriaWaste.getFluid(200))
                .EUt(VA[IV].toLong())
                .duration(1 * MINUTE)
                .buildAndRegister()

            // TODO Naquadah fuels as Naquadah Reactor fuels.

            // Naquadah gas as gas turbine fuels.
            GAS_TURBINE_FUELS.recipeBuilder()
                .fluidInputs(NaquadahGas.getFluid(24))
                .EUt(VA[MV].toLong())
                .duration(12 * SECOND)
                .buildAndRegister()

            // Advanced recipes for Naquadah Fuels.

            // Energetic Naquadria
            CHEMICAL_PLANT_RECIPES.recipeBuilder()
                .circuitMeta(24)
                .input(dust, Naquadria)
                .fluidInputs(NitrogenDioxide.getFluid(500))
                .fluidInputs(SulfuricAcid.getFluid(500))
                .output(dust, Lutetium)
                .output(dust, Uranium238)
                .output(dust, Plutonium241)
                .output(dust, NaquadahEnriched)
                .fluidOutputs(NaquadriaEnergetic.getFluid(1000))
                .EUt(V[ZPM] / 2)
                .duration(2 * SECOND)
                .buildAndRegister()

            // Light Naquadah Fuel
            CHEMICAL_PLANT_RECIPES.recipeBuilder()
                .circuitMeta(24)
                .input(dust, Naquadah)
                .fluidInputs(Uranium235.getFluid(500))
                .fluidInputs(Nitrogen.getFluid(500))
                .fluidOutputs(LightNaquadahFuel.getFluid(6000))
                .EUt(VA[UV].toLong())
                .duration(15 * SECOND)
                .buildAndRegister()

            CHEMICAL_PLANT_RECIPES.recipeBuilder()
                .circuitMeta(24)
                .input(dust, GalliumSulfide)
                .fluidInputs(NaquadriaEnergetic.getFluid(1000))
                .fluidInputs(Nitrogen.getPlasma(1000))
                .fluidOutputs(LightNaquadahFuel.getFluid(12000))
                .EUt(VA[UHV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // Medium Naquadah Fuel
            CHEMICAL_PLANT_RECIPES.recipeBuilder()
                .circuitMeta(24)
                .input(dust, NaquadahEnriched)
                .fluidInputs(Uranium235.getFluid(500))
                .fluidInputs(Plutonium241.getFluid(500))
                .output(dust, Plutonium239)
                .fluidOutputs(MediumNaquadahFuel.getFluid(6000))
                .EUt(VA[UV].toLong())
                .duration(15 * SECOND)
                .buildAndRegister()

            CHEMICAL_PLANT_RECIPES.recipeBuilder()
                .circuitMeta(24)
                .input(dust, IndiumPhosphide)
                .fluidInputs(NaquadriaEnergetic.getFluid(1000))
                .fluidInputs(Nitrogen.getPlasma(1000))
                .fluidOutputs(MediumNaquadahFuel.getFluid(12000))
                .EUt(VA[UHV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // Heavy Naquadah Fuel
            CHEMICAL_PLANT_RECIPES.recipeBuilder()
                .circuitMeta(24)
                .input(dust, Naquadria)
                .input(dust, Plutonium239)
                .fluidInputs(Nitrogen.getPlasma(500))
                .output(dust, NaquadahEnriched)
                .fluidOutputs(HeavyNaquadahFuel.getFluid(6000))
                .EUt(VA[UV].toLong())
                .duration(15 * SECOND)
                .buildAndRegister()

            CHEMICAL_PLANT_RECIPES.recipeBuilder()
                .circuitMeta(24)
                .input(dust, Trinium)
                .fluidInputs(NaquadriaEnergetic.getFluid(1000))
                .fluidInputs(Nitrogen.getPlasma(1000))
                .fluidOutputs(HeavyNaquadahFuel.getFluid(12000))
                .EUt(VA[UHV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

        }

    }

}