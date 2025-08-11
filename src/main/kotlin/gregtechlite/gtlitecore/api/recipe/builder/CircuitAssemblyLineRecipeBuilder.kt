package gregtechlite.gtlitecore.api.recipe.builder

import gregtech.api.recipes.Recipe
import gregtech.api.recipes.RecipeBuilder
import gregtech.api.recipes.RecipeMap
import gregtechlite.gtlitecore.api.recipe.property.CircuitPatternProperty
import net.minecraft.item.ItemStack
import org.apache.commons.lang3.builder.ToStringBuilder

class CircuitAssemblyLineRecipeBuilder : RecipeBuilder<CircuitAssemblyLineRecipeBuilder>
{

    constructor()

    @Suppress("unused")
    constructor(recipe: Recipe,
                recipeMap: RecipeMap<CircuitAssemblyLineRecipeBuilder>) : super(recipe, recipeMap)

    constructor(recipeBuilder: RecipeBuilder<CircuitAssemblyLineRecipeBuilder>) : super(recipeBuilder)

    override fun copy() = CircuitAssemblyLineRecipeBuilder(this)

    fun getCircuit() = if (this.recipePropertyStorage == null) null
        else this.recipePropertyStorage.get(CircuitPatternProperty, null)

    override fun applyPropertyCT(key: String, value: Any): Boolean
    {
        if (key == CircuitPatternProperty.key)
        {
            this.circuit(value as ItemStack)
            return true
        }
        return super.applyPropertyCT(key, value)
    }

    fun circuit(stack: ItemStack): CircuitAssemblyLineRecipeBuilder
    {
        this.applyProperty(CircuitPatternProperty, stack)
        return this
    }

    override fun toString() = ToStringBuilder(this)
        .appendSuper(super.toString())
        .append(CircuitPatternProperty.key, this.getCircuit())
        .toString()

}