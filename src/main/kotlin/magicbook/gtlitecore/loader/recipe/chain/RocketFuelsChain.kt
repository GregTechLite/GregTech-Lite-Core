package magicbook.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.V
import gregtech.api.GTValues.VA
import gregtech.api.fluids.store.FluidStorageKeys
import gregtech.api.recipes.GTRecipeHandler
import gregtech.api.recipes.RecipeMaps.COMBUSTION_GENERATOR_FUELS
import gregtech.api.recipes.RecipeMaps.MIXER_RECIPES
import gregtech.api.unification.material.Materials.CoalTar
import gregtech.api.unification.material.Materials.Dimethylhydrazine
import gregtech.api.unification.material.Materials.Methanol
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.RocketFuel
import gregtech.api.unification.material.Materials.Tetranitromethane
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.ROCKET_ENGINE_FUELS
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.DenseHydrazineRocketFuel
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Methylhydrazine
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MethylhydrazineNitrateRocketFuel
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.RP1RocketFuel
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND

@Suppress("MISSING_DEPENDENCY_CLASS")
class RocketFuelsChain
{

    companion object
    {

        fun init()
        {
            // Deleted rocket fuel combustion generator recipe.
            GTRecipeHandler.removeRecipesByInputs(COMBUSTION_GENERATOR_FUELS,
                RocketFuel.getFluid(16))

            // RP-1 Rocket Fuel
            MIXER_RECIPES.recipeBuilder()
                .fluidInputs(CoalTar.getFluid(1000))
                .fluidInputs(Oxygen.getFluid(FluidStorageKeys.LIQUID, 1000))
                .fluidOutputs(RP1RocketFuel.getFluid(1000))
                .EUt(VA[MV].toLong())
                .duration(6 * SECOND)
                .buildAndRegister()

            // Dense Hydrazine Rocket Fuel
            MIXER_RECIPES.recipeBuilder()
                .fluidInputs(Dimethylhydrazine.getFluid(1000))
                .fluidInputs(Methanol.getFluid(1000))
                .fluidOutputs(DenseHydrazineRocketFuel.getFluid(1000))
                .EUt(VA[EV].toLong())
                .duration(6 * SECOND)
                .buildAndRegister()

            // Methylhydrazine Nitrate Rocket Fuel
            MIXER_RECIPES.recipeBuilder()
                .fluidInputs(Methylhydrazine.getFluid(1000))
                .fluidInputs(Tetranitromethane.getFluid(1000))
                .fluidOutputs(MethylhydrazineNitrateRocketFuel.getFluid(1000))
                .EUt(VA[EV].toLong())
                .duration(2 * SECOND)
                .buildAndRegister()

            // Rocket Fuels
            ROCKET_ENGINE_FUELS.recipeBuilder()
                .fluidInputs(RocketFuel.getFluid(16))
                .EUt(V[HV])
                .duration(2 * SECOND)
                .buildAndRegister()

            ROCKET_ENGINE_FUELS.recipeBuilder()
                .fluidInputs(RP1RocketFuel.getFluid(12))
                .EUt(V[HV])
                .duration(1 * SECOND)
                .buildAndRegister()

            ROCKET_ENGINE_FUELS.recipeBuilder()
                .fluidInputs(DenseHydrazineRocketFuel.getFluid(9))
                .EUt(V[EV])
                .duration(4 * SECOND)
                .buildAndRegister()

            ROCKET_ENGINE_FUELS.recipeBuilder()
                .fluidInputs(MethylhydrazineNitrateRocketFuel.getFluid(6))
                .EUt(V[EV])
                .duration(6 * SECOND)
                .buildAndRegister()

        }

    }

}