package magicbook.gtlitecore.api.recipe.builder

import gregtech.api.recipes.Recipe
import gregtech.api.recipes.RecipeBuilder
import gregtech.api.recipes.RecipeMap
import magicbook.gtlitecore.api.recipe.property.CircuitPatternProperty
import net.minecraft.item.ItemStack
import org.apache.commons.lang3.builder.ToStringBuilder

class CircuitAssemblyLineRecipeBuilder : RecipeBuilder<CircuitAssemblyLineRecipeBuilder>
{

    constructor()

    constructor(recipe: Recipe,
                recipeMap: RecipeMap<CircuitAssemblyLineRecipeBuilder>) : super(recipe, recipeMap)

    constructor(recipeBuilder: RecipeBuilder<CircuitAssemblyLineRecipeBuilder>) : super(recipeBuilder)

    override fun copy() = CircuitAssemblyLineRecipeBuilder(this)

    fun getCircuit() = if (this.recipePropertyStorage == null) null else this.recipePropertyStorage.get(CircuitPatternProperty.INSTANCE, null)

    override fun applyPropertyCT(key: String, value: Any): Boolean
    {
        if (key == CircuitPatternProperty.KEY)
        {
            this.circuit(value as ItemStack)
            return true
        }
        return super.applyPropertyCT(key, value)
    }

    fun circuit(stack: ItemStack): CircuitAssemblyLineRecipeBuilder
    {
        this.applyProperty(CircuitPatternProperty.INSTANCE, stack)
        return this
    }

    override fun toString() = ToStringBuilder(this)
        .appendSuper(super.toString())
        .append(CircuitPatternProperty.INSTANCE.getKey(), this.getCircuit())
        .toString()

}