package gregtechlite.gtlitecore.api.recipe.backend

import gregtech.api.recipes.RecipeMaps
import gregtechlite.gtlitecore.GTLiteMod
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps

internal object MatterReshapingFrameworkBackend
{
    fun init()
    {
        RecipeMaps.FORGE_HAMMER_RECIPES.onRecipeBuild(GTLiteMod.id("forge_hammer_copy")) {
            GTLiteRecipeMaps.MATTER_RESHAPING_RECIPES.recipeBuilder()
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