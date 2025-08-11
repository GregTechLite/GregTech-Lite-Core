package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.VA
import gregtech.api.fluids.store.FluidStorageKeys
import gregtech.api.recipes.RecipeMaps.CENTRIFUGE_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_BATH_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.MIXER_RECIPES
import gregtech.api.unification.material.Materials.Aluminium
import gregtech.api.unification.material.Materials.Bromine
import gregtech.api.unification.material.Materials.Butane
import gregtech.api.unification.material.Materials.Butene
import gregtech.api.unification.material.Materials.Cadmium
import gregtech.api.unification.material.Materials.Chlorine
import gregtech.api.unification.material.Materials.DilutedSulfuricAcid
import gregtech.api.unification.material.Materials.DistilledWater
import gregtech.api.unification.material.Materials.Ethane
import gregtech.api.unification.material.Materials.Ethylene
import gregtech.api.unification.material.Materials.Helium
import gregtech.api.unification.material.Materials.HydrochloricAcid
import gregtech.api.unification.material.Materials.HydrogenSulfide
import gregtech.api.unification.material.Materials.HypochlorousAcid
import gregtech.api.unification.material.Materials.Ice
import gregtech.api.unification.material.Materials.Magnesium
import gregtech.api.unification.material.Materials.MagnesiumChloride
import gregtech.api.unification.material.Materials.Methane
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Potassium
import gregtech.api.unification.material.Materials.RockSalt
import gregtech.api.unification.material.Materials.Selenium
import gregtech.api.unification.material.Materials.SulfuricAcid
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.ore.OrePrefix.dust
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.BATH_CONDENSER_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CVD_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.AluminiumHydroxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.AluminiumSelenide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Butanediol
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CadmiumBromide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CadmiumSelenide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CadmiumSulfide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.DiethylSulfide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Dimethylcadmium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.EthyleneDibromide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.GrignardReagent
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HRAMagnesium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HydrobromicAcid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HydrogenSelenide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MagnesiumBromide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Tetrahydrofuran
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt

internal object QuantumDotsChain
{

    // @formatter:off

    fun init()
    {
        grignardReagentProcess()
        dimethylcadmiumProcess()
        cadmiumSelenideProcess()
        cadmiumSulfideProcess()
    }

    private fun grignardReagentProcess()
    {
        // C4H8 + HClO + H2O -> C4H10O2 + HCl
        CHEMICAL_RECIPES.recipeBuilder()
            .fluidInputs(Butene.getFluid(1000))
            .fluidInputs(HypochlorousAcid.getFluid(1000))
            .fluidInputs(Water.getFluid(1000))
            .fluidOutputs(Butanediol.getFluid(1000))
            .fluidOutputs(HydrochloricAcid.getFluid(1000))
            .EUt(VA[HV])
            .duration(3 * SECOND)
            .buildAndRegister()

        // C4H10O2 -> C4H8O + H2O
        CHEMICAL_RECIPES.recipeBuilder()
            .fluidInputs(Butanediol.getFluid(1000))
            .fluidInputs(SulfuricAcid.getFluid(1000))
            .fluidOutputs(Tetrahydrofuran.getFluid(1000))
            .fluidOutputs(DilutedSulfuricAcid.getFluid(1500))
            .EUt(VA[HV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // Add another reaction to produce MgCl2.
        // Mg + 2Cl -> MgCl2
        CHEMICAL_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(dust, Magnesium)
            .fluidInputs(Chlorine.getFluid(2000))
            .output(dust, MagnesiumChloride, 3)
            .EUt(VA[LV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()

        // MgCl2 + 2K (molten) + C4H8O (semicatalyst) -> *Mg* + 2KCl
        CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, MagnesiumChloride, 3)
            .fluidInputs(Potassium.getFluid(L * 2))
            .fluidInputs(Tetrahydrofuran.getFluid(10))
            .output(dust, HRAMagnesium)
            .output(dust, RockSalt, 4)
            .EUt(VA[IV])
            .duration(7 * SECOND)
            .buildAndRegister()

        // C2H4 + 2Br -> C2H4Br2
        MIXER_RECIPES.recipeBuilder()
            .fluidInputs(Ethylene.getFluid(1000))
            .fluidInputs(Bromine.getFluid(2000))
            .fluidOutputs(EthyleneDibromide.getFluid(3000))
            .EUt(VA[MV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // Mg + C2H4Br2 -> CH3MgBr + HBr + C (lost)
        CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, HRAMagnesium)
            .fluidInputs(EthyleneDibromide.getFluid(3000))
            .fluidOutputs(GrignardReagent.getFluid(1000))
            .fluidOutputs(HydrobromicAcid.getFluid(1000))
            .EUt(VA[LV])
            .duration(5 * SECOND)
            .buildAndRegister()

    }

    private fun dimethylcadmiumProcess()
    {
        // Cd + 2Br -> CdBr2
        for (fluidStack in arrayOf(
            Water.getFluid(100),
            DistilledWater.getFluid(100),
            Ice.getFluid(50),
            Oxygen.getFluid(FluidStorageKeys.LIQUID, 25),
            Helium.getFluid(FluidStorageKeys.LIQUID, 10)))
        {
            BATH_CONDENSER_RECIPES.recipeBuilder()
                .input(dust, Cadmium)
                .fluidInputs(Bromine.getFluid(2000))
                .fluidInputs(fluidStack)
                .output(dust, CadmiumBromide, 3)
                .EUt(VA[LV])
                .duration(5 * SECOND)
                .buildAndRegister()
        }

        // CdBr2 + 2CH3MgBr -> (CH3)2Cd + 2MgBr2
        CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, CadmiumBromide, 3)
            .fluidInputs(GrignardReagent.getFluid(2000))
            .output(dust, MagnesiumBromide, 6)
            .fluidOutputs(Dimethylcadmium.getFluid(1000))
            .EUt(VA[IV])
            .duration(5 * SECOND)
            .buildAndRegister()
    }

    private fun cadmiumSelenideProcess()
    {
        // 2Al + 3Se -> Al2Se3
        MIXER_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(dust, Aluminium, 2)
            .input(dust, Selenium, 3)
            .output(dust, AluminiumSelenide, 5)
            .EUt(VA[LV])
            .duration(20 * SECOND)
            .buildAndRegister()

        // Al2Se3 + 6H2O -> 2Al(OH)3 + 3H2Se
        CHEMICAL_BATH_RECIPES.recipeBuilder()
            .input(dust, AluminiumSelenide, 5)
            .fluidInputs(Water.getFluid(6000))
            .output(dust, AluminiumHydroxide, 14)
            .fluidOutputs(HydrogenSelenide.getFluid(3000))
            .EUt(VA[HV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // (CH3)2Cd + H2Se -> CdSe + 2CH4
        CVD_RECIPES.recipeBuilder()
            .fluidInputs(Dimethylcadmium.getFluid(1000))
            .fluidInputs(HydrogenSelenide.getFluid(1000))
            .output(dust, CadmiumSelenide, 2)
            .fluidOutputs(Methane.getFluid(2000))
            .EUt(VA[IV])
            .duration(4 * SECOND)
            .temperature(665)
            .buildAndRegister()
    }

    private fun cadmiumSulfideProcess()
    {
        // 2C2H4 + H2S -> C4H10S
        MIXER_RECIPES.recipeBuilder()
            .fluidInputs(Ethylene.getFluid(2000))
            .fluidInputs(HydrogenSulfide.getFluid(1000))
            .fluidOutputs(DiethylSulfide.getFluid(1000))
            .EUt(VA[LV])
            .duration(7 * SECOND + 10 * TICK)
            .buildAndRegister()

        // C4H10S decomposition.
        CENTRIFUGE_RECIPES.recipeBuilder()
            .fluidInputs(DiethylSulfide.getFluid(1000))
            .fluidOutputs(Ethylene.getFluid(2000))
            .fluidOutputs(HydrogenSulfide.getFluid(1000))
            .EUt(VA[LV])
            .duration(15 * SECOND)
            .buildAndRegister()

        // Cd(CH3)2 + C4H10S -> CdS + C2H6 + C4H10
        CVD_RECIPES.recipeBuilder()
            .fluidInputs(Dimethylcadmium.getFluid(1000))
            .fluidInputs(DiethylSulfide.getFluid(1000))
            .output(dust, CadmiumSulfide, 2)
            .fluidOutputs(Ethane.getFluid(1000))
            .fluidOutputs(Butane.getFluid(1000))
            .EUt(VA[UV])
            .duration(4 * SECOND)
            .temperature(742)
            .buildAndRegister()
    }

    // @formatter:on

}