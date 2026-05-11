package gregtechlite.gtlitecore.api.recipe.builder

import gregtech.api.recipes.Recipe
import gregtech.api.recipes.RecipeBuilder
import gregtech.api.recipes.RecipeMap
import gregtech.api.util.EnumValidationResult
import gregtechlite.gtlitecore.api.GTLiteLog
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeProperties
import gregtechlite.gtlitecore.api.util.buildToString

class ComponentAssemblyLineRecipeBuilder : RecipeBuilder<ComponentAssemblyLineRecipeBuilder>
{
    val tier = recipePropertyStorage?.let { recipePropertyStorage.get(GTLiteRecipeProperties.COMPONENT_ASSEMBLY_LINE_TIER, 0) } ?: 0

    constructor()

    @Suppress("unused")
    constructor(recipe: Recipe, recipeMap: RecipeMap<ComponentAssemblyLineRecipeBuilder>) : super(recipe, recipeMap)

    constructor(recipeBuilder: RecipeBuilder<ComponentAssemblyLineRecipeBuilder>) : super(recipeBuilder)

    override fun copy(): ComponentAssemblyLineRecipeBuilder = ComponentAssemblyLineRecipeBuilder(this)

    override fun applyPropertyCT(key: String, value: Any): Boolean
    {
        if (key == GTLiteRecipeProperties.COMPONENT_ASSEMBLY_LINE_TIER.key)
        {
            tier((value as Number).toInt())
            return true
        }
        return super.applyPropertyCT(key, value)
    }

    fun tier(tier: Int): ComponentAssemblyLineRecipeBuilder = apply {
        if (tier <= 0)
        {
            GTLiteLog.logger.error("Tier cannot be less than or equal to 0", IllegalArgumentException())
            recipeStatus = EnumValidationResult.INVALID
        }
        applyProperty(GTLiteRecipeProperties.COMPONENT_ASSEMBLY_LINE_TIER, tier)
    }

    override fun toString(): String = buildToString(this) {
        appendSuper(super.toString())
        append(GTLiteRecipeProperties.COMPONENT_ASSEMBLY_LINE_TIER.key, tier)
    }
}
