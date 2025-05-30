package magicbook.gtlitecore.api.recipe.builder

import gregtech.api.recipes.Recipe
import gregtech.api.recipes.RecipeBuilder
import gregtech.api.recipes.RecipeMap
import gregtech.api.recipes.properties.impl.PrimitiveProperty
import gregtech.api.util.ValidationResult

class NoEnergyRecipeBuilder : RecipeBuilder<NoEnergyRecipeBuilder>
{

    constructor()

    constructor(recipe: Recipe,
                recipeMap: RecipeMap<NoEnergyRecipeBuilder>) : super(recipe, recipeMap)


    constructor(recipeBuilder: NoEnergyRecipeBuilder) : super(recipeBuilder)

    override fun copy(): NoEnergyRecipeBuilder = NoEnergyRecipeBuilder(this)

    override fun build(): ValidationResult<Recipe>
    {
        EUt(1)
        applyProperty(PrimitiveProperty.getInstance(), true)
        return super.build()
    }

}