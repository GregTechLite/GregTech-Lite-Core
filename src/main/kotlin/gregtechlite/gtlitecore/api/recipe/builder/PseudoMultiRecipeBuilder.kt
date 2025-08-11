package gregtechlite.gtlitecore.api.recipe.builder

import gregtech.api.recipes.Recipe
import gregtech.api.recipes.RecipeBuilder
import gregtech.api.recipes.RecipeMap
import gregtechlite.gtlitecore.api.recipe.property.PseudoMultiProperty
import gregtechlite.gtlitecore.api.recipe.property.value.PseudoMultiPropertyValues
import net.minecraft.block.state.IBlockState

class PseudoMultiRecipeBuilder : RecipeBuilder<PseudoMultiRecipeBuilder>
{

    constructor()

    @Suppress("unused")
    constructor(recipe: Recipe,
                recipeMap: RecipeMap<PseudoMultiRecipeBuilder>) : super(recipe, recipeMap)

    constructor(recipeBuilder: RecipeBuilder<PseudoMultiRecipeBuilder>) : super(recipeBuilder)

    override fun copy(): PseudoMultiRecipeBuilder = PseudoMultiRecipeBuilder(this)

    // Registrate via RecipeBuilder. e.g. AnyThisBuilder.recipeBuilder().blockStates("name", blockStateArrayList)
    // will register correspondenced translation text and values.
    fun blockStates(blockGroupName: String, blockStates: ArrayList<IBlockState>): PseudoMultiRecipeBuilder
    {
        this.applyProperty(PseudoMultiProperty, PseudoMultiPropertyValues(blockGroupName, blockStates))
        return this
    }

}