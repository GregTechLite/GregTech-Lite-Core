package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.ZPM
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.unification.material.Materials.Ammonia
import gregtech.api.unification.material.Materials.AmmoniumChloride
import gregtech.api.unification.material.Materials.Chloromethane
import gregtech.api.unification.material.Materials.HydrochloricAcid
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.Methane
import gregtech.api.unification.material.Materials.Nitrobenzene
import gregtech.api.unification.material.Materials.Nitrochlorobenzene
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Palladium
import gregtech.api.unification.material.Materials.Phenol
import gregtech.api.unification.material.Materials.Tin
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.material.Materials.Zinc
import gregtech.api.unification.ore.OrePrefix.dust
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Aminophenol
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Aniline
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BiphenylTetracarboxylicAcidDianhydride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Durene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.KaptonE
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.KaptonK
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Nitroaniline
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Oxydianiline
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ParaPhenylenediamine
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ParaXylene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PhthalicAnhydride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PyromelliticDianhydride

internal object KaptonChain
{

    // @formatter:off

    fun init()
    {
        pyromelliticDianhydrideProcess()
        oxydianilineProcess()
        biphenylTetracarboxylicAcidDianhydrideProcess()
        paraPhenylenediamineProcess()
        kaptonProcess()
    }

    private fun pyromelliticDianhydrideProcess()
    {
        // C6H4(CH3)2 + 2CH3Cl -> C6H2(CH3)4 + 2HCl
        CHEMICAL_RECIPES.recipeBuilder()
            .fluidInputs(ParaXylene.getFluid(1000))
            .fluidInputs(Chloromethane.getFluid(2000))
            .output(dust, Durene, 24)
            .fluidOutputs(HydrochloricAcid.getFluid(2000))
            .EUt(VA[MV])
            .duration(6 * SECOND)
            .buildAndRegister()

        // C6H2(CH3)4 + 12O -> C6H2(C2O3)2 + 6H2O
        CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, Durene, 4)
            .fluidInputs(Oxygen.getFluid(2000))
            .output(dust, PyromelliticDianhydride, 3)
            .fluidOutputs(Water.getFluid(1000))
            .EUt(VA[HV])
            .duration(1 * SECOND + 10 * TICK)
            .buildAndRegister()
    }

    private fun oxydianilineProcess()
    {
        // C6H5NO2 + 4H -> HOC6H4NH2 + H2O
        CHEMICAL_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .fluidInputs(Nitrobenzene.getFluid(1000))
            .fluidInputs(Hydrogen.getFluid(4000))
            .output(dust, Aminophenol, 15)
            .fluidOutputs(Water.getFluid(1000))
            .EUt(VA[LV])
            .duration(15 * SECOND)
            .buildAndRegister()

        // 6H + C6H5NO2 -> C6H5NH2 + 2H2O
        CHEMICAL_RECIPES.recipeBuilder()
            .notConsumable(dust, Zinc)
            .fluidInputs(Hydrogen.getFluid(6000))
            .fluidInputs(Nitrobenzene.getFluid(1000))
            .fluidOutputs(Aniline.getFluid(1000))
            .fluidOutputs(Water.getFluid(2000))
            .EUt(VA[MV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // 2C6H5NH2 + C2H5OH -> C12H12N2O + 2CH4
        CHEMICAL_RECIPES.recipeBuilder()
            .notConsumable(dust, Tin)
            .fluidInputs(Aniline.getFluid(2000))
            .fluidInputs(Phenol.getFluid(1000))
            .notConsumable(HydrochloricAcid.getFluid(1))
            .output(dust, Oxydianiline, 27)
            .fluidOutputs(Methane.getFluid(2000))
            .EUt(VA[ZPM])
            .duration(5 * SECOND)
            .buildAndRegister()

        // HOC6H4NH2 + ClC6H4NO2 + H2O -> C12H12N2O + 3O + HCl
        // LARGE_CHEMICAL_RECIPES.recipeBuilder()
        //     .notConsumable(dust, Saltpeter)
        //     .input(dust, Aminophenol, 15)
        //     .fluidInputs(Nitrochlorobenzene.getFluid(1000))
        //     .fluidInputs(Water.getFluid(1000))
        //     .notConsumable(Dimethylformamide.getFluid(1))
        //     .output(dust, Oxydianiline, 27)
        //     .fluidOutputs(Oxygen.getFluid(3000))
        //     .fluidOutputs(HydrochloricAcid.getFluid(1000))
        //     .EUt(VA[UV])
        //     .duration(10 * SECOND)
        //     .buildAndRegister()
    }

    private fun biphenylTetracarboxylicAcidDianhydrideProcess()
    {
        // C8H4O3 -> C16H6O6 + 2H
        CHEMICAL_RECIPES.recipeBuilder()
            .notConsumable(dust, Palladium)
            .input(dust, PhthalicAnhydride, 15)
            .output(dust, BiphenylTetracarboxylicAcidDianhydride, 28)
            .fluidOutputs(Hydrogen.getFluid(2000))
            .EUt(VA[HV])
            .duration(10 * SECOND)
            .buildAndRegister()
    }

    private fun paraPhenylenediamineProcess()
    {
        // ClC6H4NO2 + 2NH3 -> H2NC6H4NO2 + NH4Cl
        CHEMICAL_RECIPES.recipeBuilder()
            .fluidInputs(Nitrochlorobenzene.getFluid(1000))
            .fluidInputs(Ammonia.getFluid(2000))
            .output(dust, AmmoniumChloride, 2)
            .fluidOutputs(Nitroaniline.getFluid(1000))
            .EUt(VA[HV])
            .duration(5 * SECOND)
            .buildAndRegister()

        //  H2NC6H4NO2 + 6H -> H2NC6H4NH2 + 2H2O
        CHEMICAL_RECIPES.recipeBuilder()
            .fluidInputs(Nitroaniline.getFluid(1000))
            .fluidInputs(Hydrogen.getFluid(6000))
            .output(dust, ParaPhenylenediamine, 16)
            .fluidOutputs(Water.getFluid(2000))
            .EUt(VA[IV])
            .duration(10 * SECOND)
            .buildAndRegister()
    }

    private fun kaptonProcess()
    {
        // 2C6H2(C2O3)2 + C12H12N2O -> C22H10N2O5 + 10C + 6H + 2O (loss)
        CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, PyromelliticDianhydride, 2)
            .input(dust, Oxydianiline, 3)
            .fluidOutputs(KaptonK.getFluid(L))
            .EUt(VA[IV])
            .duration(1 * SECOND + 10 * TICK)
            .buildAndRegister()

        // 2C6H2(C2O3)2 + C12H12N2O + C16H6O6 + H2NC6H4NH2 -> C24H18N2O5 + 30C + 12H + 14O + 2N (loss)
        CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, BiphenylTetracarboxylicAcidDianhydride, 2)
            .input(dust, ParaPhenylenediamine)
            .fluidInputs(KaptonK.getFluid(L))
            .fluidOutputs(KaptonE.getFluid(L))
            .EUt(VA[ZPM])
            .duration(1 * SECOND + 10 * TICK)
            .buildAndRegister()
    }

    // @formatter:on

}