package gregtechlite.gtlitecore.api.recipe.backend

import gregtech.api.recipes.RecipeMaps
import gregtechlite.gtlitecore.GTLiteMod
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.LARGE_MIXER_RECIPES

object LargeMixerRecipeBackend
{

    fun init()
    {

        RecipeMaps.MIXER_RECIPES.onRecipeBuild(GTLiteMod.id("mixer_copy")) { recipeBuilder ->
            LARGE_MIXER_RECIPES.recipeBuilder()
                .inputs(*recipeBuilder.inputs.toTypedArray())
                .fluidInputs(recipeBuilder.fluidInputs)
                .outputs(recipeBuilder.outputs)
                .chancedOutputs(recipeBuilder.chancedOutputs)
                .fluidOutputs(recipeBuilder.fluidOutputs)
                .chancedFluidOutputs(recipeBuilder.chancedFluidOutputs)
                .cleanroom(recipeBuilder.cleanroom)
                .duration(recipeBuilder.duration)
                .EUt(recipeBuilder.eUt)
                .buildAndRegister()
        }

    }

}