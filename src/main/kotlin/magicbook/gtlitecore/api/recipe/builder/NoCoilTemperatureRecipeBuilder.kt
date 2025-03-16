package magicbook.gtlitecore.api.recipe.builder

import gregtech.api.recipes.Recipe
import gregtech.api.recipes.RecipeBuilder
import gregtech.api.recipes.RecipeMap
import gregtech.api.util.EnumValidationResult
import magicbook.gtlitecore.api.recipe.property.NoCoilTemperatureProperty
import magicbook.gtlitecore.api.utils.GTLiteLog
import org.apache.commons.lang3.builder.ToStringBuilder

class NoCoilTemperatureRecipeBuilder : RecipeBuilder<NoCoilTemperatureRecipeBuilder>
{

    constructor()

    constructor(recipe: Recipe,
                recipeMap: RecipeMap<NoCoilTemperatureRecipeBuilder>) : super(recipe, recipeMap)

    constructor(recipeBuilder: NoCoilTemperatureRecipeBuilder) : super(recipeBuilder)

    override fun copy(): NoCoilTemperatureRecipeBuilder = NoCoilTemperatureRecipeBuilder(this)

    override fun applyPropertyCT(key: String, value: Any): Boolean
    {
        if (key == NoCoilTemperatureProperty.KEY)
        {
            temperature(value as Int)
            return true
        }
        return super.applyPropertyCT(key, value)
    }

    fun temperature(temp: Int): NoCoilTemperatureRecipeBuilder
    {
        if (temp <= 0)
        {
            GTLiteLog.logger.error("Temperature cannot be less than or equal to 0", IllegalArgumentException())
            this.recipeStatus = EnumValidationResult.INVALID
        }
        this.applyProperty(NoCoilTemperatureProperty.INSTANCE, temp)
        return this
    }

    fun getTemperature() = this.recipePropertyStorage.get(NoCoilTemperatureProperty.INSTANCE, 0)

    override fun toString(): String = ToStringBuilder(this)
        .appendToString(super.toString())
        .append(NoCoilTemperatureProperty.INSTANCE.key, getTemperature())
        .toString()

}