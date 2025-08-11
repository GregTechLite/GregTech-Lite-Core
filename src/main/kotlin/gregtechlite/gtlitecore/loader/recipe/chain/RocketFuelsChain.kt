package gregtechlite.gtlitecore.loader.recipe.chain

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
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.ROCKET_ENGINE_FUELS
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.DenseHydrazineRocketFuel
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Methylhydrazine
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MethylhydrazineNitrateRocketFuel
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.RP1RocketFuel
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt

internal object RocketFuelsChain
{

    // @formatter:off

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
            .EUt(VA[MV])
            .duration(6 * SECOND)
            .buildAndRegister()

        // Dense Hydrazine Rocket Fuel
        MIXER_RECIPES.recipeBuilder()
            .fluidInputs(Dimethylhydrazine.getFluid(1000))
            .fluidInputs(Methanol.getFluid(1000))
            .fluidOutputs(DenseHydrazineRocketFuel.getFluid(1000))
            .EUt(VA[EV])
            .duration(6 * SECOND)
            .buildAndRegister()

        // Methylhydrazine Nitrate Rocket Fuel
        MIXER_RECIPES.recipeBuilder()
            .fluidInputs(Methylhydrazine.getFluid(1000))
            .fluidInputs(Tetranitromethane.getFluid(1000))
            .fluidOutputs(MethylhydrazineNitrateRocketFuel.getFluid(1000))
            .EUt(VA[EV])
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

    // @formatter:on

}