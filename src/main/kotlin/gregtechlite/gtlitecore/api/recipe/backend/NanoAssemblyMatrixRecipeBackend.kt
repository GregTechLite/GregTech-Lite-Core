package gregtechlite.gtlitecore.api.recipe.backend

import gregtech.api.recipes.RecipeMaps
import gregtechlite.gtlitecore.GTLiteMod
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps

internal object NanoAssemblyMatrixRecipeBackend
{
    fun init()
    {
        RecipeMaps.ASSEMBLY_LINE_RECIPES.onRecipeBuild(GTLiteMod.id("ass_line_copy")) {
            GTLiteRecipeMaps.NANO_ASSEMBLY_MATRIX_RECIPES.recipeBuilder()
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

        GTLiteRecipeMaps.SPACE_ASSEMBLER_RECIPES.onRecipeBuild(GTLiteMod.id("space_ass_copy")) {
            GTLiteRecipeMaps.NANO_ASSEMBLY_MATRIX_RECIPES.recipeBuilder()
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