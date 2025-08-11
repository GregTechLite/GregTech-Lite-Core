package gregtechlite.gtlitecore.api.recipe.builder

import gregtech.api.recipes.Recipe
import gregtech.api.recipes.RecipeBuilder
import gregtech.api.recipes.RecipeMap
import gregtech.api.util.EnumValidationResult
import gregtechlite.gtlitecore.api.GTLiteLog
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeProperties
import org.apache.commons.lang3.builder.ToStringBuilder

class ComponentAssemblyLineRecipeBuilder : RecipeBuilder<ComponentAssemblyLineRecipeBuilder>
{

    val tier: Int
        get() = (if (this.recipePropertyStorage == null) 0
            else this.recipePropertyStorage.get(GTLiteRecipeProperties.COMPONENT_ASSEMBLY_LINE_TIER, 0))!!

    constructor()

    @Suppress("unused")
    constructor(recipe: Recipe,
                recipeMap: RecipeMap<ComponentAssemblyLineRecipeBuilder>) : super(recipe, recipeMap)

    constructor(recipeBuilder: RecipeBuilder<ComponentAssemblyLineRecipeBuilder>) : super(recipeBuilder)

    override fun copy(): ComponentAssemblyLineRecipeBuilder
    {
        return ComponentAssemblyLineRecipeBuilder(this)
    }

    override fun applyPropertyCT(key: String, value: Any): Boolean
    {
        if (key == GTLiteRecipeProperties.COMPONENT_ASSEMBLY_LINE_TIER.key)
        {
            this.tier((value as Number).toInt())
            return true
        }
        return super.applyPropertyCT(key, value)
    }

    fun tier(tier: Int): ComponentAssemblyLineRecipeBuilder
    {
        if (tier <= 0)
        {
            GTLiteLog.logger.error("Tier cannot be less than or equal to 0", IllegalArgumentException())
            this.recipeStatus = EnumValidationResult.INVALID
        }
        this.applyProperty(GTLiteRecipeProperties.COMPONENT_ASSEMBLY_LINE_TIER, tier)
        return this
    }

    override fun toString(): String
    {
        return ToStringBuilder(this)
            .appendSuper(super.toString())
            .append(GTLiteRecipeProperties.COMPONENT_ASSEMBLY_LINE_TIER.key, this.tier)
            .toString()
    }

}
