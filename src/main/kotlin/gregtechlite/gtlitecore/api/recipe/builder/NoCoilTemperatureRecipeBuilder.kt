package gregtechlite.gtlitecore.api.recipe.builder

import gregtech.api.recipes.Recipe
import gregtech.api.recipes.RecipeBuilder
import gregtech.api.recipes.RecipeMap
import gregtech.api.util.EnumValidationResult
import gregtechlite.gtlitecore.api.GTLiteLog
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeProperties
import org.apache.commons.lang3.builder.ToStringBuilder

class NoCoilTemperatureRecipeBuilder : RecipeBuilder<NoCoilTemperatureRecipeBuilder>
{

    constructor()

    @Suppress("unused")
    constructor(recipe: Recipe,
                recipeMap: RecipeMap<NoCoilTemperatureRecipeBuilder>) : super(recipe, recipeMap)

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

    fun temperature(temp: Int): NoCoilTemperatureRecipeBuilder
    {
        if (temp <= 0)
        {
            GTLiteLog.logger.error("Temperature cannot be less than or equal to 0", IllegalArgumentException())
            this.recipeStatus = EnumValidationResult.INVALID
        }
        this.applyProperty(GTLiteRecipeProperties.NO_COIL_TEMPERATURE, temp)
        return this
    }

    fun getTemperature() : Int = this.recipePropertyStorage.get(GTLiteRecipeProperties.NO_COIL_TEMPERATURE, 0)!!

    override fun toString(): String = ToStringBuilder(this)
        .appendToString(super.toString())
        .append(GTLiteRecipeProperties.NO_COIL_TEMPERATURE.key, getTemperature())
        .toString()

}