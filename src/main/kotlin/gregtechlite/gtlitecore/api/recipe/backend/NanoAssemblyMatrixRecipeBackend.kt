package gregtechlite.gtlitecore.api.recipe.backend

import gregtech.api.recipes.RecipeMaps
import gregtechlite.gtlitecore.GTLiteMod
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps

object NanoAssemblyMatrixRecipeBackend
{

    fun init()
    {
        // Ass Line
        RecipeMaps.ASSEMBLY_LINE_RECIPES.onRecipeBuild(GTLiteMod.id("ass_line_copy")) { builder ->
            GTLiteRecipeMaps.NANO_ASSEMBLY_MATRIX_RECIPES.recipeBuilder()
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

        // Space Ass
        GTLiteRecipeMaps.SPACE_ASSEMBLER_RECIPES.onRecipeBuild(GTLiteMod.id("space_ass_copy")) { builder ->
            GTLiteRecipeMaps.NANO_ASSEMBLY_MATRIX_RECIPES.recipeBuilder()
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