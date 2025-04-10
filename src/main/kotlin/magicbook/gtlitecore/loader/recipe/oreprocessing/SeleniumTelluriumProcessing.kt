package magicbook.gtlitecore.loader.recipe.oreprocessing

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.VHA
import gregtech.api.recipes.RecipeMaps.CENTRIFUGE_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.ELECTROLYZER_RECIPES
import gregtech.api.recipes.RecipeMaps.MIXER_RECIPES
import gregtech.api.unification.material.Materials.CarbonDioxide
import gregtech.api.unification.material.Materials.Copper
import gregtech.api.unification.material.Materials.Gold
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Selenium
import gregtech.api.unification.material.Materials.Silver
import gregtech.api.unification.material.Materials.SodaAsh
import gregtech.api.unification.material.Materials.SodiumHydroxide
import gregtech.api.unification.material.Materials.Sulfur
import gregtech.api.unification.material.Materials.SulfurDioxide
import gregtech.api.unification.material.Materials.SulfurTrioxide
import gregtech.api.unification.material.Materials.SulfuricAcid
import gregtech.api.unification.material.Materials.SulfuricCopperSolution
import gregtech.api.unification.material.Materials.Tellurium
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.ingot
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.ROASTER_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BlueVitriol
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ChalcogenAnodeMud
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Cuprite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SeleniumDioxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SelenousAcid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SodiumTellurite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TelluriumDioxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Tenorite
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK

@Suppress("MISSING_DEPENDENCY_CLASS")
class SeleniumTelluriumProcessing
{

    companion object
    {

        fun init()
        {
            // Cu + H2SO4 -> CuSO4 + 2H
            CHEMICAL_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(dust, Copper)
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(BlueVitriol.getFluid(1000))
                .fluidOutputs(Hydrogen.getFluid(2000))
                .EUt(VA[MV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // CuO + H2SO4 -> CuSO4 + H2O
            CHEMICAL_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(dust, Tenorite, 2)
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(BlueVitriol.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .EUt(VA[MV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // Cu2O + 2H2SO4 -> 2CuSO4 + H2O + 2H (lost)
            CHEMICAL_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .input(dust, Cuprite, 3)
                .fluidInputs(SulfuricAcid.getFluid(2000))
                .fluidOutputs(BlueVitriol.getFluid(2000))
                .fluidOutputs(Water.getFluid(1000))
                .EUt(VA[MV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // CuSO4 -> Cu + S + 4O
            ELECTROLYZER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .fluidInputs(BlueVitriol.getFluid(1000))
                .output(dust, Copper)
                .output(dust, Sulfur)
                .fluidOutputs(Oxygen.getFluid(4000))
                .EUt(VHA[MV].toLong())
                .duration(7 * SECOND + 4 * TICK)
                .buildAndRegister()

            // CuSO4 + H2O -> CuSO4·H2O
            ELECTROLYZER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .fluidInputs(BlueVitriol.getFluid(1000))
                .fluidInputs(Water.getFluid(1000))
                .fluidOutputs(SulfuricCopperSolution.getFluid(1000))
                .EUt(VHA[MV].toLong())
                .duration(7 * SECOND + 4 * TICK)
                .buildAndRegister()

            // CuSO4 + H2O -> Ag2TeSe + Cu + O + H2SO4
            ELECTROLYZER_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .fluidInputs(BlueVitriol.getFluid(1000))
                .fluidInputs(Water.getFluid(1000))
                .output(dust, Copper)
                .chancedOutput(dust, ChalcogenAnodeMud, 4, 500, 0)
                .fluidOutputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(Oxygen.getFluid(1000))
                .EUt(VHA[MV].toLong())
                .duration(3 * SECOND + 12 * TICK)
                .buildAndRegister()

            // Optional recovery of metals to provide some nice bonus.
            CENTRIFUGE_RECIPES.recipeBuilder()
                .input(dust, ChalcogenAnodeMud, 4)
                .output(dust, Silver)
                .chancedOutput(dust, Copper, 1000, 1000)
                .chancedOutput(dust, Gold, 750, 750)
                .EUt(VHA[MV].toLong())
                .duration(15 * SECOND)
                .buildAndRegister()

            telluriumProcess()
            seleniumProcess()
        }

        private fun telluriumProcess()
        {
            // Ag2TeSe + 4O + Na2CO3 -> Na2TeO3 + SeO2 + 2Ag + CO2
            ROASTER_RECIPES.recipeBuilder()
                .input(dust, ChalcogenAnodeMud, 4)
                .input(dust, SodaAsh, 6)
                .fluidInputs(Oxygen.getFluid(4000))
                .output(dust, SodiumTellurite, 6)
                .output(dust, SeleniumDioxide, 3)
                .output(ingot, Silver, 2)
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .EUt(VA[HV].toLong())
                .duration(30 * SECOND)
                .buildAndRegister() // Because this reaction is above 1900K, so silver will output by ingot form.

            // Na2TeO3 + H2O -> TeO2 + 2NaOH
            ELECTROLYZER_RECIPES.recipeBuilder()
                .input(dust, SodiumTellurite, 6)
                .fluidInputs(Water.getFluid(1000))
                .output(dust, TelluriumDioxide, 3)
                .output(dust, SodiumHydroxide, 6)
                .EUt(VA[MV].toLong())
                .duration(20 * SECOND)
                .buildAndRegister()

            // TeO2 + 2SO2 + H2O -> Te + H2SO4 + SO3
            CHEMICAL_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(dust, TelluriumDioxide, 3)
                .fluidInputs(SulfurDioxide.getFluid(2000))
                .fluidInputs(Water.getFluid(1000))
                .output(dust, Tellurium)
                .fluidOutputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(SulfurTrioxide.getFluid(1000))
                .EUt(VA[EV].toLong())
                .duration(15 * SECOND)
                .buildAndRegister()
        }

        private fun seleniumProcess()
        {
            // SeO2 + H2O -> H2SeO3
            MIXER_RECIPES.recipeBuilder()
                .input(dust, SeleniumDioxide, 3)
                .fluidInputs(Water.getFluid(1000))
                .output(dust, SelenousAcid, 6)
                .EUt(VA[MV].toLong())
                .duration(20 * SECOND)
                .buildAndRegister()

            // H2SeO3 + 2SO2 -> Se + H2SO4 + SO3
            CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, SelenousAcid, 6)
                .fluidInputs(SulfurDioxide.getFluid(2000))
                .output(dust, Selenium)
                .fluidOutputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(SulfurTrioxide.getFluid(1000))
                .EUt(VA[EV].toLong())
                .duration(15 * SECOND)
                .buildAndRegister()
        }

    }

}