package gregtechlite.gtlitecore.api.recipe.builder

import gregtech.api.recipes.Recipe
import gregtech.api.recipes.RecipeBuilder
import gregtech.api.recipes.RecipeMap
import gregtech.api.util.EnumValidationResult
import gregtechlite.gtlitecore.api.GTLiteLog
import gregtechlite.gtlitecore.api.extension.buildToString
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeProperties

class NoCoilTemperatureRecipeBuilder : RecipeBuilder<NoCoilTemperatureRecipeBuilder>
{
    val temperature
        get() = recipePropertyStorage?.let { recipePropertyStorage.get(GTLiteRecipeProperties.NO_COIL_TEMPERATURE, 0) } ?: 0

    constructor()

    @Suppress("unused")
    constructor(recipe: Recipe, recipeMap: RecipeMap<NoCoilTemperatureRecipeBuilder>) : super(recipe, recipeMap)

    constructor(recipeBuilder: NoCoilTemperatureRecipeBuilder) : super(recipeBuilder)

    override fun copy(): NoCoilTemperatureRecipeBuilder = NoCoilTemperatureRecipeBuilder(this)

    override fun applyPropertyCT(key: String, value: Any): Boolean
    {
        if (key == GTLiteRecipeProperties.NO_COIL_TEMPERATURE.key)
        {
            temperature(value as Int)
            return true
        }
        return super.applyPropertyCT(key, value)
    }

    fun temperature(temp: Int): NoCoilTemperatureRecipeBuilder = apply {
        if (temp <= 0)
        {
            GTLiteLog.logger.error("Temperature cannot be less than or equal to 0", IllegalArgumentException())
            recipeStatus = EnumValidationResult.INVALID
        }
        applyProperty(GTLiteRecipeProperties.NO_COIL_TEMPERATURE, temp)
    }

    override fun toString(): String = buildToString {
        appendToString(super.toString())
        append(GTLiteRecipeProperties.NO_COIL_TEMPERATURE.key, temperature)
    }
}