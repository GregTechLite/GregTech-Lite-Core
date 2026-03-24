package gregtechlite.gtlitecore.api.recipe.util

import gregtech.api.GTValues.MAX
import gregtech.api.GTValues.V
import gregtech.api.recipes.Recipe
import gregtech.api.recipes.RecipeBuilder
import gregtech.api.recipes.RecipeMap
import gregtech.api.unification.OreDictUnifier
import net.minecraft.item.ItemStack
import net.minecraftforge.fluids.FluidStack
import kotlin.math.ceil

object OreProcessorRecipeWrapper
{

    val orePrefixes : Array<String> = arrayOf(
        "crushedPurified", "crushedCentrifuged", "crushed", "dustImpure", "dustPure", "ore")

    fun isOre(itemStack: ItemStack) : Boolean
    {
        if (itemStack.isEmpty)
            return false

        val ids = OreDictUnifier.getOreDictionaryNames(itemStack)
        for (prefix in orePrefixes)
        {
            for (id in ids)
            {
                if (id.startsWith(prefix))
                    return true
            }
        }
        return false
    }

    fun getOutputStack(recipe: Recipe, factor: Int) : List<ItemStack>
    {
        val output = mutableListOf<ItemStack>()
        for (recipeItemStack in recipe.outputs)
        {
            val outputItemStack = recipeItemStack.copy()
            outputItemStack.setCount(outputItemStack.count * factor)
            output.add(outputItemStack)
        }
        for (chancedItemOutput in recipe.chancedOutputs.chancedEntries)
        {
            val itemStack = chancedItemOutput.ingredient.copy()
            itemStack.count = (ceil(itemStack.count.toLong() * factor * chancedItemOutput.chance * 1e-4)).toInt()
            output.add(itemStack)
        }
        return output
    }

    fun compressOutput(input: List<ItemStack>) : List<ItemStack>
    {
        val compressedOutput = mutableListOf<ItemStack>()
        for (item in input)
        {
            var found = false
            for (outputItemStack in compressedOutput)
            {
                if (outputItemStack.isItemEqual(item))
                {
                    outputItemStack.count += item.count
                    found = true
                    break
                }
            }
            if (!found)
            {
                compressedOutput.add(item.copy())
            }
        }
        return compressedOutput
    }

    fun <T : RecipeBuilder<T>> doRecipe(input: List<ItemStack>, recipeMap : RecipeMap<T>,
                                        extraFluid : List<FluidStack>?, extraItem : List<ItemStack>?) : List<ItemStack>?
    {
        val output = mutableListOf<ItemStack>()
        var hasChanged = false
        for (itemStack in input)
        {
            if (isOre(itemStack))
            {
                val itemList = mutableListOf(itemStack)
                if (extraItem != null)
                {
                    itemList.addAll(extraItem)
                }
                val recipe: Recipe? = recipeMap.findRecipe(V[MAX], itemList, extraFluid ?: emptyList<FluidStack>())
                if (recipe != null)
                {
                    hasChanged = true
                    output.addAll(getOutputStack(recipe, itemStack.count))
                }
                else
                {
                    output.add(itemStack)
                }
            }
            else
            {
                output.add(itemStack)
            }
        }
        if (!hasChanged)
        {
            return null
        }
        return compressOutput(output)
    }

}