package gregtechlite.gtlitecore.api.recipe.backend

import gregtech.api.unification.material.Materials
import gregtechlite.gtlitecore.GTLiteMod
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CVD_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.LASER_CVD_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.PLASMA_CVD_RECIPES

object CVDRecipeBackend
{

    fun init()
    {
        CVD_RECIPES.onRecipeBuild(GTLiteMod.id("advanced_cvd_copy")) { recipeBuilder ->

            // Laser-Induced CVD recipes required laser-induced gas for protective and ensure transmissive correct.
            for (laserInducedGas in arrayOf(
                Materials.Helium.getFluid(16000),
                Materials.Neon.getFluid(16000),
                Materials.Argon.getFluid(16000),
                Materials.Krypton.getFluid(16000),
                Materials.Xenon.getFluid(16000)))
            {
                LASER_CVD_RECIPES.recipeBuilder()
                    .inputs(*recipeBuilder.inputs.toTypedArray())
                    .fluidInputs(recipeBuilder.fluidInputs)
                    .notConsumable(laserInducedGas)
                    .outputs(recipeBuilder.outputs)
                    .chancedOutputs(recipeBuilder.chancedOutputs)
                    .fluidOutputs(recipeBuilder.fluidOutputs)
                    .chancedFluidOutputs(recipeBuilder.chancedFluidOutputs)
                    .cleanroom(recipeBuilder.cleanroom)
                    .EUt(recipeBuilder.eUt)
                    .duration(recipeBuilder.duration)
                    .temperature(recipeBuilder.getTemperature())
                    .hidden()
                    .buildAndRegister()
            }

            // Plasma-enhanced CVD recipes.
            PLASMA_CVD_RECIPES.recipeBuilder()
                .inputs(*recipeBuilder.inputs.toTypedArray())
                .fluidInputs(recipeBuilder.fluidInputs)
                .outputs(recipeBuilder.outputs)
                .chancedOutputs(recipeBuilder.chancedOutputs)
                .fluidOutputs(recipeBuilder.fluidOutputs)
                .chancedFluidOutputs(recipeBuilder.chancedFluidOutputs)
                .cleanroom(recipeBuilder.cleanroom)
                .EUt(recipeBuilder.eUt)
                .duration(recipeBuilder.duration)
                .temperature(recipeBuilder.getTemperature())
                .hidden()
                .buildAndRegister()
        }

    }

}