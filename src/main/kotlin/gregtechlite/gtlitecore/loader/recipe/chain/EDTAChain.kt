package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.unification.material.Materials.Ammonia
import gregtech.api.unification.material.Materials.Chlorine
import gregtech.api.unification.material.Materials.Copper
import gregtech.api.unification.material.Materials.Ethylene
import gregtech.api.unification.material.Materials.HydrochloricAcid
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.Methanol
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Salt
import gregtech.api.unification.material.Materials.Silver
import gregtech.api.unification.material.Materials.SodiumHydroxide
import gregtech.api.unification.material.Materials.VinylChloride
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.dustTiny
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.BURNER_REACTOR_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CopperDichloride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Dichloroethane
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.EDTA
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Ethylenediamine
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Formaldehyde
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HydrogenCyanide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SodiumCyanide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TetrasodiumEDTA

internal object EDTAChain
{

    // @formatter:off

    fun init()
    {

        // Cu + 2Cl -> CuCl2
        CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, Copper)
            .fluidInputs(Chlorine.getFluid(2000))
            .output(dust, CopperDichloride, 3)
            .EUt(VA[LV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()

        // C2H4 + 2HCl -> C2H4Cl2 + 2H
        CHEMICAL_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .notConsumable(dust, CopperDichloride)
            .fluidInputs(Ethylene.getFluid(1000))
            .fluidInputs(HydrochloricAcid.getFluid(2000))
            .fluidOutputs(Dichloroethane.getFluid(1000))
            .fluidOutputs(Hydrogen.getFluid(2000))
            .EUt(VA[LV])
            .duration(4 * SECOND)
            .buildAndRegister()

        // Another usage of 1,2-Dichloroethane (C2H4Cl2).
        // C2H4Cl2 -> C2H3Cl + HCl
        BURNER_REACTOR_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .fluidInputs(Dichloroethane.getFluid(1000))
            .fluidOutputs(VinylChloride.getFluid(1000))
            .fluidOutputs(HydrochloricAcid.getFluid(1000))
            .EUt(VA[MV])
            .duration(2 * SECOND)
            .buildAndRegister()

        // C2H4Cl2 + 2NH3 -> C2H4(NH2)2 + 2HCl
        CHEMICAL_RECIPES.recipeBuilder()
            .fluidInputs(Dichloroethane.getFluid(1000))
            .fluidInputs(Ammonia.getFluid(2000))
            .fluidOutputs(Ethylenediamine.getFluid(1000))
            .fluidOutputs(HydrochloricAcid.getFluid(2000))
            .EUt(VA[HV])
            .duration(4 * SECOND)
            .buildAndRegister()

        // NaOH + HCN -> NaCN + H2O
        CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, SodiumHydroxide, 3)
            .fluidInputs(HydrogenCyanide.getFluid(1000))
            .output(dust, SodiumCyanide, 3)
            .fluidOutputs(Water.getFluid(1000))
            .EUt(VA[LV])
            .duration(6 * SECOND)
            .buildAndRegister()

        // CH3OH -> CH2O + 2H
        BURNER_REACTOR_RECIPES.recipeBuilder()
            .input(dustTiny, Silver)
            .fluidInputs(Methanol.getFluid(1000))
            .fluidOutputs(Formaldehyde.getFluid(1000))
            .fluidOutputs(Hydrogen.getFluid(2000))
            .EUt(VA[HV])
            .duration(9 * SECOND)
            .buildAndRegister()

        // C2H4(NH2)2 + 4CH2O + 4NaCN + 6H2O -> C10H12Na4N2O8 + 4NH3 + 2O
        CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, SodiumCyanide, 12)
            .fluidInputs(Ethylenediamine.getFluid(1000))
            .fluidInputs(Formaldehyde.getFluid(4000))
            .fluidInputs(Water.getFluid(4000))
            .output(dust, TetrasodiumEDTA, 36)
            .fluidOutputs(Ammonia.getFluid(4000))
            .fluidOutputs(Oxygen.getFluid(2000))
            .EUt(VA[HV])
            .duration(6 * SECOND)
            .buildAndRegister()

        //  C10H12Na4N2O8 + 4HCl -> C10H16N2O8 + 4NaCl
        CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, TetrasodiumEDTA, 36)
            .fluidInputs(HydrochloricAcid.getFluid(4000))
            .output(dust, EDTA, 32)
            .output(dust, Salt, 8)
            .EUt(VA[IV])
            .duration(5 * SECOND)
            .buildAndRegister()
    }

    // @formatter:on

}