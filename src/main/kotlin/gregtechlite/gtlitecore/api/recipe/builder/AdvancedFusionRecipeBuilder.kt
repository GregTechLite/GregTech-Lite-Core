package gregtechlite.gtlitecore.api.recipe.builder

import gregtech.api.recipes.Recipe
import gregtech.api.recipes.RecipeBuilder
import gregtech.api.recipes.RecipeMap
import gregtech.api.util.EnumValidationResult
import gregtechlite.gtlitecore.api.GTLiteLog
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeProperties
import org.apache.commons.lang3.builder.ToStringBuilder

class AdvancedFusionRecipeBuilder : RecipeBuilder<AdvancedFusionRecipeBuilder>
{

    val tier: Int
        get() = (if (this.recipePropertyStorage == null) 0
            else this.recipePropertyStorage.get(GTLiteRecipeProperties.ADVANCED_FUSION_TIER, 0))!!

    constructor()

    @Suppress("unused")
    constructor(recipe: Recipe,
                recipeMap: RecipeMap<AdvancedFusionRecipeBuilder>) : super(recipe, recipeMap)

    constructor(recipeBuilder: RecipeBuilder<AdvancedFusionRecipeBuilder>) : super(recipeBuilder)

    override fun copy(): AdvancedFusionRecipeBuilder
    {
        return AdvancedFusionRecipeBuilder(this)
    }

    override fun applyPropertyCT(key: String, value: Any): Boolean
    {
        if (key == GTLiteRecipeProperties.ADVANCED_FUSION_TIER.key)
        {
            this.tier((value as Number).toInt())
            return true
        }
        return super.applyPropertyCT(key, value)
    }

    fun tier(tier: Int): AdvancedFusionRecipeBuilder
    {
        if (tier <= 0)
        {
            GTLiteLog.logger.error("Tier cannot be less than or equal to 0", IllegalArgumentException())
            this.recipeStatus = EnumValidationResult.INVALID
        }
        this.applyProperty(GTLiteRecipeProperties.ADVANCED_FUSION_TIER, tier)
        return this
    }

    override fun toString(): String
    {
        return ToStringBuilder(this)
            .appendSuper(super.toString())
            .append(GTLiteRecipeProperties.ADVANCED_FUSION_TIER.key, this.tier)
            .toString()
    }

}
