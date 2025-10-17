package gregtechlite.gtlitecore.api.recipe.map

import gregtech.api.recipes.Recipe
import gregtech.api.recipes.RecipeBuilder
import gregtech.api.recipes.RecipeMap
import gregtech.api.recipes.ui.RecipeMapUIFunction
import net.minecraft.item.ItemStack
import net.minecraftforge.fluids.FluidStack

class PseudoQuadrupleRecipeMap<R : RecipeBuilder<R>>(
    unlocalizedName: String,
    defaultRecipeBuilder: R,
    recipeMapUI: RecipeMapUIFunction,
    maxInputs: Int,
    maxOutputs: Int,
    maxFluidInputs: Int,
    maxFluidOutputs: Int,
    internal val firstRecipeMap: RecipeMap<R>,
    internal val secondRecipeMap: RecipeMap<R>,
    internal val thirdRecipeMap: RecipeMap<R>,
    internal val fourthRecipeMap: RecipeMap<R>
) : PseudoGroupRecipeMapBase<R>(unlocalizedName, defaultRecipeBuilder, recipeMapUI, maxInputs, maxOutputs, maxFluidInputs, maxFluidOutputs)
{

    override fun findRecipe(voltage: Long,
                            inputs: List<ItemStack?>,
                            fluidInputs: List<FluidStack?>,
                            exactVoltage: Boolean): Recipe?
    {
        val inputItems = inputs.filterNotNull().toList()
        val inputFluids = fluidInputs.filterNotNull().filter { it.amount != 0 }.toList()
        return when (getCircuitValue(inputs, 3))
        {
            MIN_CIRCUIT_VALUE -> getRecipe(voltage, exactVoltage, inputs, fluidInputs, inputItems, inputFluids, firstRecipeMap)
            MIN_CIRCUIT_VALUE + 1 -> getRecipe(voltage, exactVoltage, inputs, fluidInputs, inputItems, inputFluids, secondRecipeMap)
            MIN_CIRCUIT_VALUE + 2 -> getRecipe(voltage, exactVoltage, inputs, fluidInputs, inputItems, inputFluids, thirdRecipeMap)
            MIN_CIRCUIT_VALUE + 3 -> getRecipe(voltage, exactVoltage, inputs, fluidInputs, inputItems, inputFluids, fourthRecipeMap)
            else -> null
        }
    }

}