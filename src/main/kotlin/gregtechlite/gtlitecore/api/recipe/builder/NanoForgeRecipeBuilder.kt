package gregtechlite.gtlitecore.api.recipe.builder

import gregtech.api.recipes.Recipe
import gregtech.api.recipes.RecipeBuilder
import gregtech.api.recipes.RecipeMap
import gregtech.api.util.EnumValidationResult
import gregtechlite.gtlitecore.api.GTLiteLog
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeProperties
import org.apache.commons.lang3.builder.ToStringBuilder

class NanoForgeRecipeBuilder : RecipeBuilder<NanoForgeRecipeBuilder>
{

    val tier: Int
        get() = (if (this.recipePropertyStorage == null) 0
            else this.recipePropertyStorage.get(GTLiteRecipeProperties.NANO_FORGE_TIER, 0))!!

    constructor()

    @Suppress("unused")
    constructor(recipe: Recipe,
                recipeMap: RecipeMap<NanoForgeRecipeBuilder>) : super(recipe, recipeMap)

    constructor(recipeBuilder: RecipeBuilder<NanoForgeRecipeBuilder>) : super(recipeBuilder)

    override fun copy(): NanoForgeRecipeBuilder
    {
        return NanoForgeRecipeBuilder(this)
    }

    override fun applyPropertyCT(key: String, value: Any): Boolean
    {
        if (key == GTLiteRecipeProperties.NANO_FORGE_TIER.key)
        {
            this.tier((value as Number).toInt())
            return true
        }
        return super.applyPropertyCT(key, value)
    }

    fun tier(tier: Int): NanoForgeRecipeBuilder
    {
        if (tier <= 0)
        {
            GTLiteLog.logger.error("Tier cannot be less than or equal to 0", IllegalArgumentException())
            this.recipeStatus = EnumValidationResult.INVALID
        }
        this.applyProperty(GTLiteRecipeProperties.NANO_FORGE_TIER, tier)
        return this
    }

    override fun toString(): String
    {
        return ToStringBuilder(this)
            .appendSuper(super.toString())
            .append(GTLiteRecipeProperties.NANO_FORGE_TIER.key, this.tier)
            .toString()
    }

}
