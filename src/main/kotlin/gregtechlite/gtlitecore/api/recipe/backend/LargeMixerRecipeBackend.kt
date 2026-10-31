package gregtechlite.gtlitecore.api.recipe.backend

import gregtech.api.recipes.RecipeMaps
import gregtechlite.gtlitecore.GTLiteMod
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps

internal object LargeMixerRecipeBackend
{
    fun init()
    {
        RecipeMaps.MIXER_RECIPES.onRecipeBuild(GTLiteMod.id("mixer_copy")) {
            GTLiteRecipeMaps.LARGE_MIXER_RECIPES.recipeBuilder()
                .inputs(*it.inputs.toTypedArray())
                .fluidInputs(it.fluidInputs)
                .outputs(it.outputs)
                .chancedOutputs(it.chancedOutputs)
                .fluidOutputs(it.fluidOutputs)
                .chancedFluidOutputs(it.chancedFluidOutputs)
                .cleanroom(it.cleanroom)
                .duration(it.duration)
                .EUt(it.eUt)
                .buildAndRegister()
        }
    }
}