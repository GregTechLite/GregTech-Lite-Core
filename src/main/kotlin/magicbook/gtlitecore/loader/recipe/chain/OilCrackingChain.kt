package magicbook.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.LV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.V
import gregtech.api.GTValues.VA
import gregtech.api.recipes.GTRecipeHandler
import gregtech.api.recipes.RecipeMaps.COMBUSTION_GENERATOR_FUELS
import gregtech.api.recipes.RecipeMaps.DISTILLATION_RECIPES
import gregtech.api.recipes.RecipeMaps.DISTILLERY_RECIPES
import gregtech.api.recipes.RecipeMaps.FLUID_HEATER_RECIPES
import gregtech.api.recipes.ingredients.IntCircuitIngredient
import gregtech.api.unification.material.Materials.Butane
import gregtech.api.unification.material.Materials.Carbon
import gregtech.api.unification.material.Materials.Ethane
import gregtech.api.unification.material.Materials.Ethylene
import gregtech.api.unification.material.Materials.HydroCrackedButadiene
import gregtech.api.unification.material.Materials.HydroCrackedButane
import gregtech.api.unification.material.Materials.HydroCrackedEthane
import gregtech.api.unification.material.Materials.HydroCrackedEthylene
import gregtech.api.unification.material.Materials.HydroCrackedPropane
import gregtech.api.unification.material.Materials.HydroCrackedPropene
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.Isoprene
import gregtech.api.unification.material.Materials.Methane
import gregtech.api.unification.material.Materials.Propane
import gregtech.api.unification.material.Materials.Propene
import gregtech.api.unification.material.Materials.SteamCrackedButadiene
import gregtech.api.unification.material.Materials.SteamCrackedButane
import gregtech.api.unification.material.Materials.SteamCrackedEthane
import gregtech.api.unification.material.Materials.SteamCrackedEthylene
import gregtech.api.unification.material.Materials.SteamCrackedPropane
import gregtech.api.unification.material.Materials.SteamCrackedPropene
import gregtech.api.unification.ore.OrePrefix.dust
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Carbon5Fraction
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Dicyclopentadiene
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.DimerizedCarbon5Fraction
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Pentane
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK
import magicbook.gtlitecore.api.utils.GTRecipeUtility

@Suppress("MISSING_DEPENDENCY_CLASS")
class OilCrackingChain
{

    companion object
    {

        fun init()
        {
            ethaneCrackProcessing()
            ethyleneCrackProcessing()
            propaneCrackProcessing()
            propeneCrackProcessing()
            butaneCrackProcessing()
            butadieneCrackProcessing()
            carbon5FractionProcessing()
            oilFuelRecipes()
        }

        private fun ethaneCrackProcessing()
        {
            // Deleted vanilla hydrogen cracked ethane recipes.
            GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
                arrayOf(IntCircuitIngredient.getIntegratedCircuit(1)),
                arrayOf(HydroCrackedEthane.getFluid(40)))
            GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
                arrayOf(IntCircuitIngredient.getIntegratedCircuit(2)),
                arrayOf(HydroCrackedEthane.getFluid(40)))

            GTRecipeHandler.removeRecipesByInputs(DISTILLATION_RECIPES,
                HydroCrackedEthane.getFluid(1000))

            DISTILLATION_RECIPES.recipeBuilder()
                .fluidInputs(HydroCrackedEthane.getFluid(1000))
                .fluidOutputs(Carbon5Fraction.getFluid(250))
                .fluidOutputs(Methane.getFluid(1750)) // CH4
                .fluidOutputs(Hydrogen.getFluid(2000)) // H
                .EUt(VA[MV].toLong())
                .duration(6 * SECOND)
                .buildAndRegister()

            // Deleted vanilla steam cracked ethane recipes.
            GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
                arrayOf(IntCircuitIngredient.getIntegratedCircuit(1)),
                arrayOf(SteamCrackedEthane.getFluid(100)))
            GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
                arrayOf(IntCircuitIngredient.getIntegratedCircuit(2)),
                arrayOf(SteamCrackedEthane.getFluid(40)))

            GTRecipeHandler.removeRecipesByInputs(DISTILLATION_RECIPES,
                SteamCrackedEthane.getFluid(1000))

            DISTILLATION_RECIPES.recipeBuilder()
                .fluidInputs(SteamCrackedEthane.getFluid(1000))
                .chancedOutput(dust, Carbon, 2500, 0)
                .fluidOutputs(Carbon5Fraction.getFluid(125))
                .fluidOutputs(Ethylene.getFluid(250)) // C2H4
                .fluidOutputs(Methane.getFluid(1125)) // CH4
                .EUt(VA[MV].toLong())
                .duration(6 * SECOND)
                .buildAndRegister()
        }

        private fun ethyleneCrackProcessing()
        {
            // Deleted vanilla hydrogen cracked ethylene recipes.
            GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
                arrayOf(IntCircuitIngredient.getIntegratedCircuit(1)),
                arrayOf(HydroCrackedEthylene.getFluid(40)))

            GTRecipeHandler.removeRecipesByInputs(DISTILLATION_RECIPES,
                HydroCrackedEthylene.getFluid(1000))

            DISTILLATION_RECIPES.recipeBuilder()
                .fluidInputs(HydroCrackedEthylene.getFluid(1000))
                .fluidOutputs(Carbon5Fraction.getFluid(200))
                .fluidOutputs(Ethane.getFluid(8000)) // C2H6
                .EUt(VA[MV].toLong())
                .duration(6 * SECOND)
                .buildAndRegister()

            // Deleted vanilla steam cracked ethylene recipes.
            GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
                arrayOf(IntCircuitIngredient.getIntegratedCircuit(1)),
                arrayOf(SteamCrackedEthylene.getFluid(1000)))

            GTRecipeHandler.removeRecipesByInputs(DISTILLATION_RECIPES,
                SteamCrackedEthylene.getFluid(1000))

            DISTILLATION_RECIPES.recipeBuilder()
                .fluidInputs(SteamCrackedEthylene.getFluid(1000))
                .output(dust, Carbon)
                .fluidOutputs(Carbon5Fraction.getFluid(200))
                .fluidOutputs(Methane.getFluid(800))
                .EUt(VA[MV].toLong())
                .duration(6 * SECOND)
                .buildAndRegister()

        }

        private fun propaneCrackProcessing()
        {
            // Deleted vanilla hydrogen cracked propane recipes.
            GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
                arrayOf(IntCircuitIngredient.getIntegratedCircuit(1)),
                arrayOf(HydroCrackedPropane.getFluid(40)))
            GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
                arrayOf(IntCircuitIngredient.getIntegratedCircuit(2)),
                arrayOf(HydroCrackedPropane.getFluid(40)))

            GTRecipeHandler.removeRecipesByInputs(DISTILLATION_RECIPES,
                HydroCrackedPropane.getFluid(1000))

            DISTILLATION_RECIPES.recipeBuilder()
                .fluidInputs(HydroCrackedPropane.getFluid(1000))
                .fluidOutputs(Carbon5Fraction.getFluid(250))
                .fluidOutputs(Ethane.getFluid(875)) // C2H6
                .fluidOutputs(Methane.getFluid(875)) // CH4
                .EUt(VA[MV].toLong())
                .duration(6 * SECOND)
                .buildAndRegister()

            // Deleted vanilla steam cracked propane recipes.
            GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
                arrayOf(IntCircuitIngredient.getIntegratedCircuit(1)),
                arrayOf(SteamCrackedPropane.getFluid(40)))
            GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
                arrayOf(IntCircuitIngredient.getIntegratedCircuit(2)),
                arrayOf(SteamCrackedPropane.getFluid(40)))

            GTRecipeHandler.removeRecipesByInputs(DISTILLATION_RECIPES,
                SteamCrackedPropane.getFluid(1000))

            DISTILLATION_RECIPES.recipeBuilder()
                .fluidInputs(SteamCrackedPropane.getFluid(1000))
                .chancedOutput(dust, Carbon, 2500, 0)
                .fluidOutputs(Carbon5Fraction.getFluid(250))
                .fluidOutputs(Ethylene.getFluid(750)) // C2H4
                .fluidOutputs(Methane.getFluid(1000)) // CH4
                .EUt(VA[MV].toLong())
                .duration(6 * SECOND)
                .buildAndRegister()
        }

        private fun propeneCrackProcessing()
        {
            // Deleted vanilla hydrogen cracked propene recipes.
            GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
                arrayOf(IntCircuitIngredient.getIntegratedCircuit(1)),
                arrayOf(HydroCrackedPropene.getFluid(100)))
            GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
                arrayOf(IntCircuitIngredient.getIntegratedCircuit(2)),
                arrayOf(HydroCrackedPropene.getFluid(100)))
            GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
                arrayOf(IntCircuitIngredient.getIntegratedCircuit(3)),
                arrayOf(HydroCrackedPropene.getFluid(100)))

            GTRecipeHandler.removeRecipesByInputs(DISTILLATION_RECIPES,
                HydroCrackedPropene.getFluid(1000))

            DISTILLATION_RECIPES.recipeBuilder()
                .fluidInputs(HydroCrackedPropene.getFluid(1000))
                .fluidOutputs(Carbon5Fraction.getFluid(250))
                .fluidOutputs(Propane.getFluid(250)) // C3H8
                .fluidOutputs(Ethylene.getFluid(250)) // C2H4
                .fluidOutputs(Methane.getFluid(250)) // CH4
                .EUt(VA[MV].toLong())
                .duration(6 * SECOND)
                .buildAndRegister()

            // Deleted vanilla steam cracked ethylene recipes.
            GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
                arrayOf(IntCircuitIngredient.getIntegratedCircuit(1)),
                arrayOf(SteamCrackedPropene.getFluid(40)))
            GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
                arrayOf(IntCircuitIngredient.getIntegratedCircuit(2)),
                arrayOf(SteamCrackedPropene.getFluid(100)))

            GTRecipeHandler.removeRecipesByInputs(DISTILLATION_RECIPES,
                SteamCrackedPropene.getFluid(1000))

            DISTILLATION_RECIPES.recipeBuilder()
                .fluidInputs(SteamCrackedPropene.getFluid(1000))
                .chancedOutput(dust, Carbon, 5000, 0)
                .fluidOutputs(Carbon5Fraction.getFluid(250))
                .fluidOutputs(Ethylene.getFluid(1000))
                .fluidOutputs(Methane.getFluid(250))
                .EUt(VA[MV].toLong())
                .duration(6 * SECOND)
                .buildAndRegister()
        }

        private fun butaneCrackProcessing()
        {
            // Deleted vanilla hydrogen cracked butane recipes.
            GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
                arrayOf(IntCircuitIngredient.getIntegratedCircuit(1)),
                arrayOf(HydroCrackedButane.getFluid(40)))
            GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
                arrayOf(IntCircuitIngredient.getIntegratedCircuit(2)),
                arrayOf(HydroCrackedButane.getFluid(40)))
            GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
                arrayOf(IntCircuitIngredient.getIntegratedCircuit(3)),
                arrayOf(HydroCrackedButane.getFluid(100)))

            GTRecipeHandler.removeRecipesByInputs(DISTILLATION_RECIPES,
                HydroCrackedButane.getFluid(1000))

            DISTILLATION_RECIPES.recipeBuilder()
                .fluidInputs(HydroCrackedButane.getFluid(1000))
                .fluidOutputs(Carbon5Fraction.getFluid(250))
                .fluidOutputs(Propane.getFluid(750)) // C3H8
                .fluidOutputs(Ethane.getFluid(750)) // C2H6
                .fluidOutputs(Methane.getFluid(250)) // CH4
                .EUt(VA[MV].toLong())
                .duration(6 * SECOND)
                .buildAndRegister()
            
            // Deleted vanilla steam cracked butane recipes.
            GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
                arrayOf(IntCircuitIngredient.getIntegratedCircuit(1)),
                arrayOf(SteamCrackedButane.getFluid(200)))
            GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
                arrayOf(IntCircuitIngredient.getIntegratedCircuit(2)),
                arrayOf(SteamCrackedButane.getFluid(40)))
            GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
                arrayOf(IntCircuitIngredient.getIntegratedCircuit(3)),
                arrayOf(SteamCrackedButane.getFluid(40)))
            GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
                arrayOf(IntCircuitIngredient.getIntegratedCircuit(4)),
                arrayOf(SteamCrackedButane.getFluid(100)))

            GTRecipeHandler.removeRecipesByInputs(DISTILLATION_RECIPES,
                SteamCrackedButane.getFluid(1000))

            DISTILLATION_RECIPES.recipeBuilder()
                .fluidInputs(SteamCrackedButane.getFluid(1000))
                .chancedOutput(dust, Carbon, 2500, 0)
                .fluidOutputs(Carbon5Fraction.getFluid(125))
                .fluidOutputs(Propane.getFluid(250)) // C3H8
                .fluidOutputs(Ethane.getFluid(750)) // C2H6
                .fluidOutputs(Ethylene.getFluid(750)) // C2H4
                .fluidOutputs(Methane.getFluid(250)) // CH4
                .EUt(VA[MV].toLong())
                .duration(6 * SECOND)
                .buildAndRegister()

        }

        private fun butadieneCrackProcessing()
        {
            // Deleted vanilla hydrogen cracked butadiene recipes.
            GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
                arrayOf(IntCircuitIngredient.getIntegratedCircuit(1)),
                arrayOf(HydroCrackedButadiene.getFluid(40)))
            GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
                arrayOf(IntCircuitIngredient.getIntegratedCircuit(2)),
                arrayOf(HydroCrackedButadiene.getFluid(100)))

            GTRecipeHandler.removeRecipesByInputs(DISTILLATION_RECIPES,
                HydroCrackedButadiene.getFluid(1000))

            DISTILLATION_RECIPES.recipeBuilder()
                .fluidInputs(HydroCrackedButadiene.getFluid(1000))
                .fluidOutputs(Carbon5Fraction.getFluid(250))
                .fluidOutputs(Butane.getFluid(500)) // C4H8
                .fluidOutputs(Ethylene.getFluid(500)) // C2H4
                .EUt(VA[MV].toLong())
                .duration(6 * SECOND)
                .buildAndRegister()

            // Deleted vanilla steam cracked butadiene recipes.
            GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
                arrayOf(IntCircuitIngredient.getIntegratedCircuit(1)),
                arrayOf(SteamCrackedButadiene.getFluid(200)))
            GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
                arrayOf(IntCircuitIngredient.getIntegratedCircuit(2)),
                arrayOf(SteamCrackedButadiene.getFluid(100)))
            GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
                arrayOf(IntCircuitIngredient.getIntegratedCircuit(3)),
                arrayOf(SteamCrackedButadiene.getFluid(40)))

            GTRecipeHandler.removeRecipesByInputs(DISTILLATION_RECIPES,
                SteamCrackedButadiene.getFluid(1000))

            DISTILLATION_RECIPES.recipeBuilder()
                .fluidInputs(SteamCrackedButadiene.getFluid(1000))
                .chancedOutput(dust, Carbon, 5000, 0)
                .fluidOutputs(Carbon5Fraction.getFluid(125))
                .fluidOutputs(Propene.getFluid(125))
                .fluidOutputs(Ethylene.getFluid(250))
                .fluidOutputs(Methane.getFluid(1000))
                .EUt(VA[MV].toLong())
                .duration(6 * SECOND)
                .buildAndRegister()
        }

        // C5 Fraction is a petroleum fractionation products in reality world oil
        // cracking processes. We add it to add several recipes for C5H8 recipes.
        private fun carbon5FractionProcessing()
        {
            // Deleted vanilla C5H8 (Isoprene) recipes because we add C5 Fraction recipes
            // for several distillation recipes.
            GTRecipeUtility.removeChemicalRecipes(
                arrayOf(IntCircuitIngredient.getIntegratedCircuit(3)),
                arrayOf(Propene.getFluid(2000)))

            GTRecipeUtility.removeChemicalRecipes(
                arrayOf(Ethylene.getFluid(1000),
                        Propene.getFluid(1000)))

            // C5 Fraction -> Dimerized C5 Fraction.
            FLUID_HEATER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .fluidInputs(Carbon5Fraction.getFluid(1000))
                .fluidOutputs(DimerizedCarbon5Fraction.getFluid(870))
                .EUt(VA[LV].toLong())
                .duration(3 * SECOND)
                .buildAndRegister()

            // Dimerized C5 Fraction -> C10H12 + C5H12 + C5H8
            DISTILLATION_RECIPES.recipeBuilder()
                .fluidInputs(DimerizedCarbon5Fraction.getFluid(870))
                .fluidOutputs(Dicyclopentadiene.getFluid(130))
                .fluidOutputs(Pentane.getFluid(380))
                .fluidOutputs(Isoprene.getFluid(360))
                .EUt(VA[LV].toLong())
                .duration(3 * SECOND)
                .buildAndRegister()

            // C10H12 -> 2C5H8
            FLUID_HEATER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .fluidInputs(Dicyclopentadiene.getFluid(1000))
                .fluidOutputs(Isoprene.getFluid(2000))
                .EUt(VA[LV].toLong())
                .duration(3 * SECOND)
                .buildAndRegister()

        }

        private fun oilFuelRecipes()
        {

            // Pentane
            COMBUSTION_GENERATOR_FUELS.recipeBuilder()
                .fluidInputs(Pentane.getFluid(1))
                .EUt(V[LV])
                .duration(2 * TICK)
                .buildAndRegister()

        }

    }

}