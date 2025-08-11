package gregtechlite.gtlitecore.api.recipe.builder

import gregtech.api.recipes.Recipe
import gregtech.api.recipes.RecipeBuilder
import gregtech.api.recipes.RecipeMap
import gregtech.api.util.EnumValidationResult
import gregtechlite.gtlitecore.api.GTLiteLog
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeProperties
import org.apache.commons.lang3.builder.ToStringBuilder

class QuantumForceTransformerRecipeBuilder : RecipeBuilder<QuantumForceTransformerRecipeBuilder>
{

    val tier: Int
        get() = (if (this.recipePropertyStorage == null) 0
            else this.recipePropertyStorage.get(GTLiteRecipeProperties.QUANTUM_FORCE_TRANSFORMER_TIER, 0))!!

    @Suppress("unused")
    constructor()

    @Suppress("unused")
    constructor(recipe: Recipe,
                recipeMap: RecipeMap<QuantumForceTransformerRecipeBuilder>) : super(recipe, recipeMap)

    constructor(recipeBuilder: RecipeBuilder<QuantumForceTransformerRecipeBuilder>) : super(recipeBuilder)

    override fun copy(): QuantumForceTransformerRecipeBuilder
    {
        return QuantumForceTransformerRecipeBuilder(this)
    }

    override fun applyPropertyCT(key: String, value: Any): Boolean
    {
        if (key == GTLiteRecipeProperties.QUANTUM_FORCE_TRANSFORMER_TIER.key)
        {
            this.tier((value as Number).toInt())
            return true
        }
        return super.applyPropertyCT(key, value)
    }

    fun tier(tier: Int): QuantumForceTransformerRecipeBuilder
    {
        if (tier <= 0)
        {
            GTLiteLog.logger.error("Tier cannot be less than or equal to 0", IllegalArgumentException())
            this.recipeStatus = EnumValidationResult.INVALID
        }
        this.applyProperty(GTLiteRecipeProperties.QUANTUM_FORCE_TRANSFORMER_TIER, tier)
        return this
    }

    override fun toString(): String
    {
        return ToStringBuilder(this)
            .appendSuper(super.toString())
            .append(GTLiteRecipeProperties.QUANTUM_FORCE_TRANSFORMER_TIER.key, this.tier)
            .toString()
    }

}
