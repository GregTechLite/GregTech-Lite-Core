package magicbook.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.fluids.store.FluidStorageKeys
import gregtech.api.recipes.RecipeMaps.CHEMICAL_BATH_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.MIXER_RECIPES
import gregtech.api.unification.material.Materials.Aluminium
import gregtech.api.unification.material.Materials.Bromine
import gregtech.api.unification.material.Materials.Butene
import gregtech.api.unification.material.Materials.Cadmium
import gregtech.api.unification.material.Materials.Chlorine
import gregtech.api.unification.material.Materials.DilutedSulfuricAcid
import gregtech.api.unification.material.Materials.DistilledWater
import gregtech.api.unification.material.Materials.Ethylene
import gregtech.api.unification.material.Materials.Helium
import gregtech.api.unification.material.Materials.HydrochloricAcid
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
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.BATH_CONDENSER_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.CVD_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.AluminiumHydroxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.AluminiumSelenide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Butanediol
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CadmiumBromide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CadmiumSelenide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Dimethylcadmium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.EthyleneDibromide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.GrignardReagent
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HRAMagnesium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HydrobromicAcid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HydrogenSelenide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MagnesiumBromide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Tetrahydrofuran
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK

@Suppress("MISSING_DEPENDENCY_CLASS")
class QuantumDotsChain
{

    companion object
    {

        fun init()
        {
            grignardReagentProcess()
            dimethylcadmiumProcess()
            cadmiumSelenideProcess()
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
                .EUt(VA[HV].toLong())
                .duration(3 * SECOND)
                .buildAndRegister()

            // C4H10O2 -> C4H8O + H2O
            CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Butanediol.getFluid(1000))
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(Tetrahydrofuran.getFluid(1000))
                .fluidOutputs(DilutedSulfuricAcid.getFluid(1500))
                .EUt(VA[HV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // Add another reaction to produce MgCl2.
            // Mg + 2Cl -> MgCl2
            CHEMICAL_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(dust, Magnesium)
                .fluidInputs(Chlorine.getFluid(2000))
                .output(dust, MagnesiumChloride, 3)
                .EUt(VA[LV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // MgCl2 + 2K (molten) + C4H8O (semicatalyst) -> *Mg* + 2KCl
            CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, MagnesiumChloride, 3)
                .fluidInputs(Potassium.getFluid(L * 2))
                .fluidInputs(Tetrahydrofuran.getFluid(10))
                .output(dust, HRAMagnesium)
                .output(dust, RockSalt, 4)
                .EUt(VA[IV].toLong())
                .duration(7 * SECOND)
                .buildAndRegister()

            // C2H4 + 2Br -> C2H4Br2
            MIXER_RECIPES.recipeBuilder()
                .fluidInputs(Ethylene.getFluid(1000))
                .fluidInputs(Bromine.getFluid(2000))
                .fluidOutputs(EthyleneDibromide.getFluid(3000))
                .EUt(VA[MV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // Mg + C2H4Br2 -> CH3MgBr + HBr + C (lost)
            CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, HRAMagnesium)
                .fluidInputs(EthyleneDibromide.getFluid(3000))
                .fluidOutputs(GrignardReagent.getFluid(1000))
                .fluidOutputs(HydrobromicAcid.getFluid(1000))
                .EUt(VA[LV].toLong())
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
                    .EUt(VA[LV].toLong())
                    .duration(5 * SECOND)
                    .buildAndRegister()
            }

            // CdBr2 + 2CH3MgBr -> (CH3)2Cd + 2MgBr2
            CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, CadmiumBromide, 3)
                .fluidInputs(GrignardReagent.getFluid(2000))
                .output(dust, MagnesiumBromide, 6)
                .fluidOutputs(Dimethylcadmium.getFluid(1000))
                .EUt(VA[IV].toLong())
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
                .EUt(VA[LV].toLong())
                .duration(20 * SECOND)
                .buildAndRegister()

            // Al2Se3 + 6H2O -> 2Al(OH)3 + 3H2Se
            CHEMICAL_BATH_RECIPES.recipeBuilder()
                .input(dust, AluminiumSelenide, 5)
                .fluidInputs(Water.getFluid(6000))
                .output(dust, AluminiumHydroxide, 14)
                .fluidOutputs(HydrogenSelenide.getFluid(3000))
                .EUt(VA[HV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // (CH3)2Cd + H2Se -> CdSe + 2CH4
            CVD_RECIPES.recipeBuilder()
                .fluidInputs(Dimethylcadmium.getFluid(1000))
                .fluidInputs(HydrogenSelenide.getFluid(1000))
                .output(dust, CadmiumSelenide, 2)
                .fluidOutputs(Methane.getFluid(2000))
                .EUt(VA[IV].toLong())
                .duration(4 * SECOND)
                .temperature(665)
                .buildAndRegister()
        }

    }

}