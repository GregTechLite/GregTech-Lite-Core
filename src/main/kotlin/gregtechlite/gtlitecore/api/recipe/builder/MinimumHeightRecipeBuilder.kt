package gregtechlite.gtlitecore.api.recipe.builder

import gregtech.api.recipes.Recipe
import gregtech.api.recipes.RecipeBuilder
import gregtech.api.recipes.RecipeMap
import gregtech.api.util.EnumValidationResult
import gregtechlite.gtlitecore.api.LOGGER
import gregtechlite.gtlitecore.api.extension.buildToString
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeProperties

class MinimumHeightRecipeBuilder : RecipeBuilder<MinimumHeightRecipeBuilder>
{
    val minHeight
        get() = recipePropertyStorage?.let { recipePropertyStorage.get(GTLiteRecipeProperties.MINIMUM_HEIGHT, 0) } ?: 0

    constructor()

    @Suppress("unused")
    constructor(recipe: Recipe, recipeMap: RecipeMap<MinimumHeightRecipeBuilder>) : super(recipe, recipeMap)

    constructor(recipeBuilder: MinimumHeightRecipeBuilder) : super(recipeBuilder)

    override fun copy(): MinimumHeightRecipeBuilder = MinimumHeightRecipeBuilder(this)

    override fun applyPropertyCT(key: String, value: Any): Boolean
    {
        if (key == GTLiteRecipeProperties.MINIMUM_HEIGHT.key)
        {
            minHeight((value as Number).toInt())
            return true
        }
        return super.applyPropertyCT(key, value)
    }

    fun minHeight(height: Int): MinimumHeightRecipeBuilder = apply {
        if (height <= -64 || height >= 256)
        {
            LOGGER.error("Minimum Height cannot be less than -64 or greater than 256", IllegalArgumentException())
            recipeStatus = EnumValidationResult.INVALID
        }
        applyProperty(GTLiteRecipeProperties.MINIMUM_HEIGHT, height)
    }

    override fun toString(): String = buildToString {
        appendSuper(super.toString())
        append(GTLiteRecipeProperties.MINIMUM_HEIGHT.key, minHeight)
    }
}
