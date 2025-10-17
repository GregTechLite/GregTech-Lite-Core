package gregtechlite.gtlitecore.api.recipe.map

import gregtech.api.recipes.Recipe
import gregtech.api.recipes.RecipeBuilder
import gregtech.api.recipes.RecipeMap
import gregtech.api.recipes.ingredients.IntCircuitIngredient
import gregtech.api.recipes.ui.RecipeMapUIFunction
import net.minecraft.item.ItemStack
import net.minecraftforge.fluids.FluidStack

abstract class PseudoGroupRecipeMapBase<R : RecipeBuilder<R>>(
    unlocalizedName: String,
    defaultRecipeBuilder: R,
    recipeMapUI: RecipeMapUIFunction,
    maxInputs: Int,
    maxOutputs: Int,
    maxFluidInputs: Int,
    maxFluidOutputs: Int
) : RecipeMap<R>(unlocalizedName, defaultRecipeBuilder, recipeMapUI, maxInputs, maxOutputs, maxFluidInputs, maxFluidOutputs)
{

    companion object
    {

        /**
         * The minimum configuration value of the integrated circuit.
         */
        const val MIN_CIRCUIT_VALUE = 20
    }

    protected fun getRecipe(voltage: Long, exactVoltage: Boolean,
                            inputs: List<ItemStack?>, fluidInputs: List<FluidStack?>,
                            inputItems: List<ItemStack>, inputFluids: List<FluidStack>,
                            recipeMap: RecipeMap<R>): Recipe?
    {
        return recipeMap.find(inputItems, inputFluids) { recipe ->
            if (exactVoltage && recipe.eUt != voltage) return@find false
            if (recipe.eUt > voltage) return@find false
            return@find recipe.matches(false, inputs, fluidInputs)
        }
    }

    fun getCircuitValue(inputs: List<ItemStack?>, size: Int): Int
    {
        for (input in inputs)
        {
            if (IntCircuitIngredient.isIntegratedCircuit(input))
            {
                // Only circuits with correct configuration will be considered.
                val num  = IntCircuitIngredient.getCircuitConfiguration(input)
                if (num in MIN_CIRCUIT_VALUE..(MIN_CIRCUIT_VALUE + size))
                    return num
            }
        }
        return 0
    }

}