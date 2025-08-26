package gregtechlite.gtlitecore.loader.recipe.oreprocessing

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.CHEMICAL_BATH_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.ELECTROLYZER_RECIPES
import gregtech.api.recipes.RecipeMaps.MIXER_RECIPES
import gregtech.api.unification.material.Materials.Actinium
import gregtech.api.unification.material.Materials.Chlorine
import gregtech.api.unification.material.Materials.DinitrogenTetroxide
import gregtech.api.unification.material.Materials.HydrochloricAcid
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.NitricAcid
import gregtech.api.unification.material.Materials.NitrogenDioxide
import gregtech.api.unification.material.Materials.Pitchblende
import gregtech.api.unification.material.Materials.Radium
import gregtech.api.unification.material.Materials.Salt
import gregtech.api.unification.material.Materials.Sodium
import gregtech.api.unification.material.Materials.SulfuricAcid
import gregtech.api.unification.material.Materials.Uraninite
import gregtech.api.unification.material.Materials.Uranium
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.ore.OrePrefix.dust
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LeadDichloride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LeadSulfate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PoloniumDioxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PoloniumNitrate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.RadiumDichloride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.RadiumSulfate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SodiumNitrate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SodiumPolonate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ThoriumDioxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ThoriumNitrate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.UranylChlorideSolution
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.UranylNitrate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.UranylNitrateSolution
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt

/**
 * Produces Polonium and Radium from Pitchblende and Uraninite.
 * - Main Products: Polonium, Radium.
 * - Side Products: Lead, Uranium, Thorium, Actinium.
 */
internal object PoloniumRadiumProcessing
{

    // @formatter:off

    fun init()
    {
        poloniumProcess()
        radiumProcess()
    }

    private fun poloniumProcess()
    {
        // (UO2)3ThPb + 4HNO3 -> UO2 + Po(NO3)4
        CHEMICAL_BATH_RECIPES.recipeBuilder()
            .input(dust, Pitchblende, 5)
            .fluidInputs(NitricAcid.getFluid(4000))
            .output(dust, Uraninite, 3)
            .output(dust, PoloniumNitrate, 17)
            .EUt(VA[IV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // Po(NO3)4 + 2Na -> Na2PoO4 + 4NO2
        CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, PoloniumNitrate, 17)
            .input(dust, Sodium, 2)
            .output(dust, SodiumPolonate, 7)
            .fluidOutputs(NitrogenDioxide.getFluid(4000))
            .EUt(VA[EV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // Na2PoO4 + 2HCl -> PoO2 + 2NaCl + H2O + O (drop)
        CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, SodiumPolonate, 7)
            .fluidInputs(HydrochloricAcid.getFluid(2000))
            .output(dust, PoloniumDioxide, 3)
            .output(dust, Salt, 4)
            .fluidOutputs(Water.getFluid(1000))
            .EUt(VA[HV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()
    }

    private fun radiumProcess()
    {
        // UO2 + 2Cl + H2O -> UO2Cl2·H2O
        MIXER_RECIPES.recipeBuilder()
            .circuitMeta(3)
            .input(dust, Uraninite, 3)
            .fluidInputs(Chlorine.getFluid(2000))
            .fluidInputs(Water.getFluid(1000))
            .fluidOutputs(UranylChlorideSolution.getFluid(1000))
            .EUt(VA[IV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()

        // UO2Cl2·H2O + 2HNO3 -> UO2(NO3)2·H2O + 2HCl (pseudo cycle)
        CHEMICAL_RECIPES.recipeBuilder()
            .fluidInputs(UranylChlorideSolution.getFluid(1000))
            .fluidInputs(NitricAcid.getFluid(2000))
            .fluidOutputs(UranylNitrateSolution.getFluid(1000))
            .fluidOutputs(HydrochloricAcid.getFluid(2000))
            .EUt(VA[HV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // UO2(NO3)2·H2O -> UO2(NO3)2 + H2O
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .fluidInputs(UranylNitrateSolution.getFluid(1000))
            .output(dust, UranylNitrate, 10)
            .fluidOutputs(Water.getFluid(1000))
            .EUt(VA[MV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // 2UO2(NO3)2 + 4H2SO4 -> 2RaSO4 + 2PbSO4 + Th(NO3)4 + U + 2H2O
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .fluidInputs(UranylNitrateSolution.getFluid(2000))
            .fluidInputs(SulfuricAcid.getFluid(4000))
            .output(dust, RadiumSulfate, 12)
            .output(dust, LeadSulfate, 12)
            .output(dust, ThoriumNitrate, 17)
            .output(dust, Uranium)
            .fluidOutputs(Water.getFluid(2000))
            .EUt(VA[EV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // RaSO4 + 2HCl -> RaCl2 + H2SO4
        CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, RadiumSulfate, 6)
            .fluidInputs(HydrochloricAcid.getFluid(2000))
            .output(dust, RadiumDichloride, 3)
            .fluidOutputs(SulfuricAcid.getFluid(1000))
            .EUt(VA[MV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // Also allowed use PbSO4 to cycle H2SO4 in this chain.
        // PbSO4 + 2HCl -> PbCl2 + H2SO4
        CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, LeadSulfate, 6)
            .fluidInputs(HydrochloricAcid.getFluid(2000))
            .output(dust, LeadDichloride, 3)
            .fluidOutputs(SulfuricAcid.getFluid(1000))
            .EUt(VA[MV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // Th(NO3)4 + 2Na -> ThO2 + 2NaNO3 + (Ac) + N2O4 + NO2 + O (drop)
        ELECTROLYZER_RECIPES.recipeBuilder()
            .input(dust, ThoriumNitrate, 17)
            .input(dust, Sodium, 2)
            .output(dust, ThoriumDioxide, 3)
            .output(dust, SodiumNitrate, 10)
            .chancedOutput(dust, Actinium, 2500, 500)
            .fluidOutputs(NitrogenDioxide.getFluid(1000))
            .fluidOutputs(DinitrogenTetroxide.getFluid(1000))
            .EUt(VA[EV])
            .duration(2 * SECOND + 15 * TICK)
            .buildAndRegister()

        // RaCl2 + 2H -> Ra + 2HCl (cycle)
        CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, RadiumDichloride, 3)
            .fluidInputs(Hydrogen.getFluid(2000))
            .output(dust, Radium)
            .fluidOutputs(HydrochloricAcid.getFluid(2000))
            .EUt(VA[HV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()

    }

    // @formatter:on

}