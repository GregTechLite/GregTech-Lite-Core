package gregtechlite.gtlitecore.api.recipe.builder

import gregtech.api.recipes.Recipe
import gregtech.api.recipes.RecipeBuilder
import gregtech.api.recipes.RecipeMap
import gregtech.api.util.EnumValidationResult
import gregtechlite.gtlitecore.api.LOGGER
import gregtechlite.gtlitecore.api.extension.buildToString
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeProperties

class QuantumForceTransformerRecipeBuilder : RecipeBuilder<QuantumForceTransformerRecipeBuilder>
{
    val tier
        get() = recipePropertyStorage?.let { recipePropertyStorage.get(GTLiteRecipeProperties.QUANTUM_FORCE_TRANSFORMER_TIER, 0) } ?: 0

    constructor()

    @Suppress("unused")
    constructor(recipe: Recipe, recipeMap: RecipeMap<QuantumForceTransformerRecipeBuilder>) : super(recipe, recipeMap)

    constructor(recipeBuilder: RecipeBuilder<QuantumForceTransformerRecipeBuilder>) : super(recipeBuilder)

    override fun copy(): QuantumForceTransformerRecipeBuilder = QuantumForceTransformerRecipeBuilder(this)

    override fun applyPropertyCT(key: String, value: Any): Boolean
    {
        if (key == GTLiteRecipeProperties.QUANTUM_FORCE_TRANSFORMER_TIER.key)
        {
            tier((value as Number).toInt())
            return true
        }
        return super.applyPropertyCT(key, value)
    }

    fun tier(tier: Int): QuantumForceTransformerRecipeBuilder = apply {
        if (tier <= 0)
        {
            LOGGER.error("Tier cannot be less than or equal to 0", IllegalArgumentException())
            recipeStatus = EnumValidationResult.INVALID
        }
        applyProperty(GTLiteRecipeProperties.QUANTUM_FORCE_TRANSFORMER_TIER, tier)
    }

    override fun toString(): String = buildToString {
        appendSuper(super.toString())
        append(GTLiteRecipeProperties.QUANTUM_FORCE_TRANSFORMER_TIER.key, tier)
    }
}
