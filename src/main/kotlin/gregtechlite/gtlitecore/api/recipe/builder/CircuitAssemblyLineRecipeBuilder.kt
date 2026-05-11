package gregtechlite.gtlitecore.api.recipe.builder

import gregtech.api.recipes.Recipe
import gregtech.api.recipes.RecipeBuilder
import gregtech.api.recipes.RecipeMap
import gregtechlite.gtlitecore.api.recipe.property.CircuitPatternProperty
import gregtechlite.gtlitecore.api.util.buildToString
import net.minecraft.item.ItemStack

class CircuitAssemblyLineRecipeBuilder : RecipeBuilder<CircuitAssemblyLineRecipeBuilder>
{
    val circuit = recipePropertyStorage?.let { recipePropertyStorage.get(CircuitPatternProperty, null) }

    constructor()

    @Suppress("unused")
    constructor(recipe: Recipe, recipeMap: RecipeMap<CircuitAssemblyLineRecipeBuilder>) : super(recipe, recipeMap)

    constructor(recipeBuilder: RecipeBuilder<CircuitAssemblyLineRecipeBuilder>) : super(recipeBuilder)

    override fun copy(): CircuitAssemblyLineRecipeBuilder = CircuitAssemblyLineRecipeBuilder(this)

    override fun applyPropertyCT(key: String, value: Any): Boolean
    {
        if (key == CircuitPatternProperty.key)
        {
            circuit(value as ItemStack)
            return true
        }
        return super.applyPropertyCT(key, value)
    }

    fun circuit(stack: ItemStack): CircuitAssemblyLineRecipeBuilder = apply {
        applyProperty(CircuitPatternProperty, stack)
    }

    override fun toString() = buildToString(this) {
        appendSuper(super.toString())
        append(CircuitPatternProperty.key, circuit)
    }
}