package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.ZPM
import gregtech.api.fluids.store.FluidStorageKeys
import gregtech.api.metatileentity.multiblock.CleanroomType
import gregtech.api.recipes.RecipeMaps.CENTRIFUGE_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.MIXER_RECIPES
import gregtech.api.unification.material.Materials.AceticAcid
import gregtech.api.unification.material.Materials.Ammonia
import gregtech.api.unification.material.Materials.Benzene
import gregtech.api.unification.material.Materials.Calcite
import gregtech.api.unification.material.Materials.CalciumChloride
import gregtech.api.unification.material.Materials.CarbonDioxide
import gregtech.api.unification.material.Materials.CarbonMonoxide
import gregtech.api.unification.material.Materials.Chlorine
import gregtech.api.unification.material.Materials.DinitrogenTetroxide
import gregtech.api.unification.material.Materials.DistilledWater
import gregtech.api.unification.material.Materials.Ethylbenzene
import gregtech.api.unification.material.Materials.Gypsum
import gregtech.api.unification.material.Materials.Helium
import gregtech.api.unification.material.Materials.HydrochloricAcid
import gregtech.api.unification.material.Materials.HydrofluoricAcid
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.Ice
import gregtech.api.unification.material.Materials.NitricAcid
import gregtech.api.unification.material.Materials.NitricOxide
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Steam
import gregtech.api.unification.material.Materials.SulfuricAcid
import gregtech.api.unification.material.Materials.Toluene
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.ore.OrePrefix.dust
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.BATH_CONDENSER_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.BURNER_REACTOR_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CHEMICAL_PLANT_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CRYOGENIC_REACTOR_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.ROASTER_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Acetaldehyde
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Acetamide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.AceticAnhydride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Acetonitrile
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.AmmoniumAcetate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.AmmoniumCarbonate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.AmmoniumSulfate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Benzaldehyde
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BenzylChloride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Benzylamine
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BoricAcid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BoronTrifluoride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CrudeHexanitrohexaaxaisowurtzitane
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Dibenzyltetraacetylhexaazaisowurtzitane
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Dimethylformamide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Ethylenediamine
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Formaldehyde
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Glyoxal
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Hexabenzylhexaazaisowurtzitane
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Hexanitrohexaaxaisowurtzitane
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HydrobromicAcid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.NHydroxysuccinimide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.NitroniumTetrafluoroborate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.NitrosoniumTetrafluoroborate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SilicaGel
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SuccinicAcid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SuccinicAnhydride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Succinimide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SuccinimidylAcetate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Tetraacetyldinitrosohexaazaisowurtzitane
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TetrafluoroboricAcid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Tetrahydrofuran
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Trimethylamine
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt

internal object HNIWChain
{

    // @formatter:off

    fun init()
    {
        hbhiwProcess()
        dbtahiwProcess()
        tadnhiwProcess()
        crudeHniwProcess()
        hniwProcess()
    }

    private fun hbhiwProcess()
    {
        // 4NH3 + H2SO4 + 3O -> (NH4)2SO4 + 3H2O
        CRYOGENIC_REACTOR_RECIPES.recipeBuilder()
            .fluidInputs(Ammonia.getFluid(4000))
            .fluidInputs(SulfuricAcid.getFluid(1000))
            .fluidInputs(Oxygen.getFluid(3000))
            .output(dust, AmmoniumSulfate, 17)
            .fluidOutputs(Ice.getFluid(3000))
            .EUt(VA[EV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // CaCO3 + (NH4)2SO4 -> 0.5CaSO4·(H2O)2 + (NH4)2CO3
        ROASTER_RECIPES.recipeBuilder()
            .input(dust, Calcite, 5)
            .input(dust, AmmoniumSulfate, 17)
            .output(dust, Gypsum, 6)
            .output(dust, AmmoniumCarbonate, 14)
            .EUt(VA[HV])
            .duration(15 * SECOND)
            .buildAndRegister()

        // (NH4)2CO3 + 2C2H4O2 -> 2NH4CH3CO2 + CO2 + H2O
        CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, AmmoniumCarbonate, 14)
            .fluidInputs(AceticAcid.getFluid(2000))
            .output(dust, AmmoniumAcetate, 24)
            .fluidOutputs(CarbonDioxide.getFluid(1000))
            .fluidOutputs(Water.getFluid(1000))
            .EUt(VA[EV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // NH4CH3CO2 -> CH3CONH2 + H2O
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
            .input(dust, AmmoniumAcetate, 12)
            .output(dust, Acetamide, 9)
            .fluidOutputs(Water.getFluid(1000))
            .EUt(VA[MV])
            .duration(1 * SECOND)
            .buildAndRegister()

        // CH3CONH2 -> CH3CN + H2O
        CENTRIFUGE_RECIPES.recipeBuilder()
            .input(dust, Acetamide, 9)
            .fluidOutputs(Acetonitrile.getFluid(1000))
            .fluidOutputs(Water.getFluid(1000))
            .EUt(VA[HV])
            .duration(1 * SECOND)
            .buildAndRegister()

        // 2C2H4O + 2HNO3 -> 2C2H2O2 + N2O + 3H2O
        BURNER_REACTOR_RECIPES.recipeBuilder()
            .fluidInputs(Acetaldehyde.getFluid(2000))
            .fluidInputs(NitricAcid.getFluid(2000))
            .fluidOutputs(Glyoxal.getFluid(2000))
            .fluidOutputs(NitricOxide.getFluid(1000))
            .fluidOutputs(Steam.getFluid(3000))
            .EUt(VA[HV])
            .duration(2 * SECOND)
            .buildAndRegister()

        // C7H8 (C6H5CH3) + Cl -> C6H5CH2Cl
        BURNER_REACTOR_RECIPES.recipeBuilder()
            .fluidInputs(Toluene.getFluid(1000))
            .fluidInputs(Chlorine.getFluid(1000))
            .fluidOutputs(BenzylChloride.getFluid(1000))
            .EUt(VA[HV])
            .duration(2 * SECOND)
            .buildAndRegister()

        // C7H7Cl + NH3 -> C7H9N + HCl
        CRYOGENIC_REACTOR_RECIPES.recipeBuilder()
            .fluidInputs(BenzylChloride.getFluid(1000))
            .fluidInputs(Ammonia.getFluid(1000))
            .fluidOutputs(Benzylamine.getFluid(1000))
            .fluidOutputs(HydrochloricAcid.getFluid(1000))
            .EUt(VA[MV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()

        // CH3CN + 3C2H2O2 + 6C7H9N -> C48H48N6
        CHEMICAL_RECIPES.recipeBuilder()
            .fluidInputs(Acetonitrile.getFluid(1000))
            .fluidInputs(Glyoxal.getFluid(3000))
            .fluidInputs(Benzylamine.getFluid(6000))
            .output(dust, Hexabenzylhexaazaisowurtzitane, 64)
            .output(dust, Hexabenzylhexaazaisowurtzitane, 38)
            .EUt(VA[IV])
            .duration(5 * SECOND)
            .buildAndRegister()
    }

    private fun dbtahiwProcess()
    {
        // C4H6O4 + C4H6O3 -> (CH2CO)2O + 2C2H4O2
        CHEMICAL_RECIPES.recipeBuilder()
            .notConsumable(dust, CalciumChloride)
            .input(dust, SuccinicAcid, 14)
            .fluidInputs(AceticAnhydride.getFluid(1000))
            .output(dust, SuccinicAnhydride, 11)
            .fluidOutputs(AceticAcid.getFluid(2000))
            .EUt(VA[IV])
            .duration(2 * SECOND)
            .buildAndRegister()

        // (CH2CO)2O + NH3 -> (CH2CO)2NOH + 2H
        for (fluidStack in arrayOf(
            Water.getFluid(100),
            DistilledWater.getFluid(100),
            Ice.getFluid(50),
            Oxygen.getFluid(FluidStorageKeys.LIQUID, 25),
            Helium.getFluid(FluidStorageKeys.LIQUID, 10)))
        {
            BATH_CONDENSER_RECIPES.recipeBuilder()
                .input(dust, SuccinicAnhydride, 11)
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidInputs(fluidStack)
                .output(dust, NHydroxysuccinimide, 13)
                .fluidOutputs(Hydrogen.getFluid(2000))
                .EUt(VA[EV])
                .duration(4 * SECOND)
                .buildAndRegister()
        }

        // (CH2CO)2NOH + C4H6O3 -> C6H7NO4 + C2H4O2
        CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, NHydroxysuccinimide, 13)
            .notConsumable(Trimethylamine.getFluid(1))
            .notConsumable(Tetrahydrofuran.getFluid(1))
            .fluidInputs(AceticAnhydride.getFluid(1000))
            .output(dust, SuccinimidylAcetate, 18)
            .fluidOutputs(AceticAcid.getFluid(1000))
            .EUt(VA[IV])
            .duration(2 * SECOND)
            .buildAndRegister()

        // C48H48N6 + 4C6H7NO4 + 8H -> C28H32N6O4 + 4C4H5NO2 + 4C7H8 + 4O
        CHEMICAL_PLANT_RECIPES.recipeBuilder()
            .input(dust, Hexabenzylhexaazaisowurtzitane, 64)
            .input(dust, Hexabenzylhexaazaisowurtzitane, 38)
            .input(dust, SuccinimidylAcetate, 64)
            .input(dust, SuccinimidylAcetate, 8)
            .fluidInputs(Hydrogen.getFluid(8000))
            .notConsumable(Ethylbenzene.getFluid(1))
            .notConsumable(Dimethylformamide.getFluid(1))
            .notConsumable(HydrobromicAcid.getFluid(1))
            .output(dust, Dibenzyltetraacetylhexaazaisowurtzitane, 64)
            .output(dust, Dibenzyltetraacetylhexaazaisowurtzitane, 6)
            .output(dust, Succinimide, 48)
            .fluidOutputs(Toluene.getFluid(4000))
            .fluidOutputs(Oxygen.getFluid(4000))
            .EUt(VA[ZPM])
            .duration(6 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // C4H5NO2 + H2O -> C4H6O4 + N (lost)
        MIXER_RECIPES.recipeBuilder()
            .input(dust, Succinimide, 12)
            .fluidInputs(Water.getFluid(1000))
            .output(dust, SuccinicAcid, 14)
            .EUt(VA[HV])
            .duration(4 * SECOND + 10 * TICK)
            .buildAndRegister()
    }

    private fun tadnhiwProcess()
    {
        // C28H32N6O4 + 6NaOBF4 + 2H2O -> C14H18N8O6 + 4NO + 2C7H6O
        CHEMICAL_PLANT_RECIPES.recipeBuilder()
            .input(dust, Dibenzyltetraacetylhexaazaisowurtzitane, 64)
            .input(dust, Dibenzyltetraacetylhexaazaisowurtzitane, 6)
            .input(dust, NitrosoniumTetrafluoroborate, 42)
            .fluidInputs(Water.getFluid(2000))
            .output(dust, Tetraacetyldinitrosohexaazaisowurtzitane, 46)
            .fluidOutputs(TetrafluoroboricAcid.getFluid(6000))
            .fluidOutputs(NitricOxide.getFluid(4000))
            .fluidOutputs(Benzaldehyde.getFluid(2000))
            .EUt(VA[IV])
            .duration(6 * SECOND)
            .buildAndRegister()

        // C6H6 + CH2O -> C7H6O + 2H
        CRYOGENIC_REACTOR_RECIPES.recipeBuilder()
            .fluidInputs(Benzene.getFluid(1000))
            .fluidInputs(Formaldehyde.getFluid(1000))
            .fluidOutputs(Benzaldehyde.getFluid(1000))
            .fluidOutputs(Hydrogen.getFluid(2000))
            .EUt(VA[HV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // C7H6O -> C6H6 + CO
        CENTRIFUGE_RECIPES.recipeBuilder()
            .fluidInputs(Benzaldehyde.getFluid(1000))
            .fluidOutputs(Benzene.getFluid(1000))
            .fluidOutputs(CarbonMonoxide.getFluid(1000))
            .EUt(VA[LV])
            .duration(3 * SECOND)
            .buildAndRegister()
    }

    private fun crudeHniwProcess()
    {
        // BF3 + HF + HNO3 -> NaO2BF4 + H2O
        CHEMICAL_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .fluidInputs(BoronTrifluoride.getFluid(1000))
            .fluidInputs(HydrofluoricAcid.getFluid(1000))
            .fluidInputs(NitricAcid.getFluid(1000))
            .output(dust, NitroniumTetrafluoroborate, 8)
            .fluidOutputs(Water.getFluid(1000))
            .EUt(VA[EV])
            .duration(2 * SECOND)
            .buildAndRegister()

        // 2BF3 + 2HF + 2N2O4 -> NaOBF4 + HNO3
        CHEMICAL_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .fluidInputs(BoronTrifluoride.getFluid(2000))
            .fluidInputs(HydrofluoricAcid.getFluid(2000))
            .fluidInputs(DinitrogenTetroxide.getFluid(2000))
            .output(dust, NitrosoniumTetrafluoroborate, 7)
            .fluidOutputs(NitricAcid.getFluid(1000))
            .EUt(VA[EV])
            .duration(2 * SECOND)
            .buildAndRegister()

        // C14H18N8O6 + 6NaO2BF4 + 4H2O -> C6H6N12O12 + 2NaOBF4 + 4HBF4 + 4C2H4O2
        CHEMICAL_PLANT_RECIPES.recipeBuilder()
            .input(dust, Tetraacetyldinitrosohexaazaisowurtzitane, 46)
            .input(dust, NitroniumTetrafluoroborate, 48)
            .fluidInputs(Water.getFluid(4000))
            .output(dust, CrudeHexanitrohexaaxaisowurtzitane, 36)
            .output(dust, NitrosoniumTetrafluoroborate, 14)
            .fluidOutputs(TetrafluoroboricAcid.getFluid(4000))
            .fluidOutputs(AceticAcid.getFluid(4000))
            .EUt(VA[UV])
            .duration(10 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        //  HBF4 + 3H2O -> 4HF + H3BO3 (cycle)
        CHEMICAL_RECIPES.recipeBuilder()
            .fluidInputs(TetrafluoroboricAcid.getFluid(1000))
            .fluidInputs(Water.getFluid(3000))
            .output(dust, BoricAcid, 7)
            .fluidOutputs(HydrofluoricAcid.getFluid(4000))
            .EUt(VA[MV])
            .duration(5 * SECOND)
            .buildAndRegister()
    }

    private fun hniwProcess()
    {
        // C6H6N12O12 + C2H4(NH2)2 -> C6H6N12O12
        CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, CrudeHexanitrohexaaxaisowurtzitane, 36)
            .input(dust, SilicaGel, 3)
            .fluidInputs(Ethylenediamine.getFluid(1000))
            .output(dust, Hexanitrohexaaxaisowurtzitane, 36)
            .EUt(VA[UV])
            .duration(10 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()
    }

    // @formatter:on

}