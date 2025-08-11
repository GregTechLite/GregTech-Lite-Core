package gregtechlite.gtlitecore.api.recipe.builder

import gregtech.api.recipes.Recipe
import gregtech.api.recipes.RecipeBuilder
import gregtech.api.recipes.RecipeMap
import gregtech.api.util.EnumValidationResult
import gregtechlite.gtlitecore.api.GTLiteLog
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeProperties
import org.apache.commons.lang3.builder.ToStringBuilder

class MinimumHeightRecipeBuilder : RecipeBuilder<MinimumHeightRecipeBuilder>
{

    val minHeight: Int
        get() = (if (this.recipePropertyStorage == null) 0
            else this.recipePropertyStorage.get(GTLiteRecipeProperties.MINIMUM_HEIGHT, 0))!!

    constructor()

    @Suppress("unused")
    constructor(recipe: Recipe,
                recipeMap: RecipeMap<MinimumHeightRecipeBuilder>) : super(recipe, recipeMap)

    constructor(recipeBuilder: MinimumHeightRecipeBuilder) : super(recipeBuilder)

    override fun copy(): MinimumHeightRecipeBuilder
    {
        return MinimumHeightRecipeBuilder(this)
    }

    override fun applyPropertyCT(key: String, value: Any): Boolean
    {
        if (key == GTLiteRecipeProperties.MINIMUM_HEIGHT.key)
        {
            this.minHeight((value as Number).toInt())
            return true
        }
        return super.applyPropertyCT(key, value)
    }

    fun minHeight(height: Int): MinimumHeightRecipeBuilder
    {
        if (height <= -64 || height >= 256)
        {
            GTLiteLog.logger.error(
                "Minimum Height cannot be less than -64 or greater than 256",
                IllegalArgumentException()
            )
            this.recipeStatus = EnumValidationResult.INVALID
        }
        this.applyProperty(GTLiteRecipeProperties.MINIMUM_HEIGHT, height)
        return this
    }

    override fun toString(): String
    {
        return ToStringBuilder(this)
            .appendSuper(super.toString())
            .append(GTLiteRecipeProperties.MINIMUM_HEIGHT.key, this.minHeight)
            .toString()
    }

}
