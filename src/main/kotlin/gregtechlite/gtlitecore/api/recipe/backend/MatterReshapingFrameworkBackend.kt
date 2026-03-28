package gregtechlite.gtlitecore.api.recipe.backend

import gregtech.api.recipes.RecipeMaps
import gregtechlite.gtlitecore.GTLiteMod
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps

object MatterReshapingFrameworkBackend
{

    fun init()
    {
        RecipeMaps.FORGE_HAMMER_RECIPES.onRecipeBuild(GTLiteMod.id("forge_hammer_copy")) { builder ->
            GTLiteRecipeMaps.MATTER_RESHAPING_RECIPES.recipeBuilder()
                .inputs(*builder.inputs.toTypedArray())
                .fluidInputs(builder.fluidInputs)
                .outputs(builder.outputs)
                .chancedOutputs(builder.chancedOutputs)
                .fluidOutputs(builder.fluidOutputs)
                .chancedFluidOutputs(builder.chancedFluidOutputs)
                .cleanroom(builder.cleanroom)
                .duration(builder.duration)
                .EUt(builder.eUt)
                .buildAndRegister()
        }
    }

}