package gregtechlite.gtlitecore.loader.recipe.oreprocessing

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.VH
import gregtech.api.recipes.RecipeMaps.CENTRIFUGE_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_BATH_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.unification.material.Materials.Ammonia
import gregtech.api.unification.material.Materials.AmmoniumChloride
import gregtech.api.unification.material.Materials.CalciumChloride
import gregtech.api.unification.material.Materials.CarbonDioxide
import gregtech.api.unification.material.Materials.Chlorine
import gregtech.api.unification.material.Materials.HydrochloricAcid
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.HydrogenSulfide
import gregtech.api.unification.material.Materials.Lead
import gregtech.api.unification.material.Materials.Massicot
import gregtech.api.unification.material.Materials.Molybdenite
import gregtech.api.unification.material.Materials.Molybdenum
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Powellite
import gregtech.api.unification.material.Materials.Rhenium
import gregtech.api.unification.material.Materials.Salt
import gregtech.api.unification.material.Materials.SodaAsh
import gregtech.api.unification.material.Materials.SodiumHydroxide
import gregtech.api.unification.material.Materials.SulfurDioxide
import gregtech.api.unification.material.Materials.SulfuricAcid
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.material.Materials.Wulfenite
import gregtech.api.unification.ore.OrePrefix.dust
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.ROASTER_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.AmmoniumPerrhenate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LeadDichloride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MolybdenumFlue
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MolybdenumTrioxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PerrhenicAcid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TraceRheniumFlue
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt

/**
 * Produces Molybdenum and Rhenium from Molybdenite/Powellite/Wulfenite.
 * - Main Products: Molybdenum, Rhenium.
 * - Side Products: None.
 *
 * A raw product estimations for these Ore Processing:
 * - 3 Molybdenite -> 1 Molybdenum
 * - 6 Powellite/Wulfenite -> 1 Molybdenum
 * - 3 Molybdenite -> 1 Rhenium
 */
internal object MolybdenumRheniumProcessing
{

    // @formatter:off

    fun init()
    {
        // Remove vanilla arc furnace smelting recipes of Molybdenite.
        // ...
        molybdenumProcess()
        rheniumProcess()
    }

    private fun molybdenumProcess()
    {
        // MoS2 + 9O -> MoO3 + 2SO2 + ReO2
        ROASTER_RECIPES.recipeBuilder()
            .input(dust, Molybdenite, 3)
            .fluidInputs(Oxygen.getFluid(9000))
            .output(dust, MolybdenumTrioxide, 4)
            .fluidOutputs(SulfurDioxide.getFluid(2000))
            .fluidOutputs(MolybdenumFlue.getFluid(1000))
            .EUt(VA[MV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // MoO3 + 6H -> Mo + 3H2O
        CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, MolybdenumTrioxide, 4)
            .fluidInputs(Hydrogen.getFluid(6000))
            .output(dust, Molybdenum)
            .fluidOutputs(Water.getFluid(3000))
            .EUt(VA[HV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // CaMoO4 + 2HCl -> MoO3 + CaCl2 + H2O
        CHEMICAL_BATH_RECIPES.recipeBuilder()
            .input(dust, Powellite, 6)
            .fluidInputs(HydrochloricAcid.getFluid(2000))
            .output(dust, MolybdenumTrioxide, 4)
            .output(dust, CalciumChloride, 3)
            .fluidOutputs(Water.getFluid(1000))
            .EUt(VA[HV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // PbMoO4 + 2HCl -> MoO3 + H2O + PbCl2
        CHEMICAL_BATH_RECIPES.recipeBuilder()
            .input(dust, Wulfenite, 6)
            .fluidInputs(HydrochloricAcid.getFluid(2000))
            .output(dust, MolybdenumTrioxide, 4)
            .output(dust, LeadDichloride, 3)
            .fluidOutputs(Water.getFluid(1000))
            .EUt(VA[HV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // Another recipes for PbCl2.

        // Pb + 2Cl -> PbCl2
        CHEMICAL_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(dust, Lead)
            .fluidInputs(Chlorine.getFluid(2000))
            .output(dust, LeadDichloride, 3)
            .EUt(VA[LV])
            .duration(4 * SECOND)
            .buildAndRegister()

        // PbO + 2HCl -> PbCl2 + H2O
        CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, Massicot, 2)
            .fluidInputs(HydrochloricAcid.getFluid(2000))
            .output(dust, LeadDichloride, 3)
            .fluidOutputs(Water.getFluid(1000))
            .EUt(VA[LV])
            .duration(2 * SECOND)
            .buildAndRegister()
    }

    private fun rheniumProcess()
    {
        // ReO2? -> MoO3 + ReO2
        CENTRIFUGE_RECIPES.recipeBuilder()
            .fluidInputs(MolybdenumFlue.getFluid(1000))
            .output(dust, MolybdenumTrioxide)
            .fluidOutputs(TraceRheniumFlue.getFluid(500))
            .EUt(VH[MV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // ReO2 + Na2CO3 + NaOH + 3Cl -> HReO4 + 3NaCl + CO2
        CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, SodaAsh, 6)
            .input(dust, SodiumHydroxide, 3)
            .fluidInputs(TraceRheniumFlue.getFluid(1000))
            .fluidInputs(Chlorine.getFluid(3000))
            .output(dust, PerrhenicAcid, 6)
            .output(dust, Salt, 6)
            .fluidOutputs(CarbonDioxide.getFluid(1000))
            .EUt(VA[IV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // HReO4 + NH3 -> NH4ReO4
        CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, PerrhenicAcid, 6)
            .fluidInputs(Ammonia.getFluid(1000))
            .output(dust, AmmoniumPerrhenate, 10)
            .EUt(VA[EV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // NH4ReO4 + H2S + Cl -> Re + NH4Cl + H2SO4
        CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, AmmoniumPerrhenate, 10)
            .fluidInputs(HydrogenSulfide.getFluid(1000))
            .fluidInputs(Chlorine.getFluid(1000))
            .output(dust, Rhenium, 1)
            .output(dust, AmmoniumChloride, 2)
            .fluidOutputs(SulfuricAcid.getFluid(1000))
            .EUt(VA[EV])
            .duration(5 * SECOND)
            .buildAndRegister()

    }

    // @formatter:on

}