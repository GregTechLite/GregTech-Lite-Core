package gregtechlite.gtlitecore.api.recipe.builder

import gregtech.api.recipes.Recipe
import gregtech.api.recipes.RecipeBuilder
import gregtech.api.recipes.RecipeMap
import gregtech.api.util.EnumValidationResult
import gregtechlite.gtlitecore.api.GTLiteLog
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeProperties
import gregtechlite.gtlitecore.api.util.buildToString

class PCBFactoryRecipeBuilder : RecipeBuilder<PCBFactoryRecipeBuilder>
{
    val tier = recipePropertyStorage?.let { recipePropertyStorage.get(GTLiteRecipeProperties.PCB_FACTORY_TIER, 0) } ?: 0
    val upgradeTier = recipePropertyStorage?.let { recipePropertyStorage.get(GTLiteRecipeProperties.PCB_FACTORY_BIO_CHAMBER_UPGRADE, 0) } ?: 0

    constructor()

    @Suppress("unused")
    constructor(recipe: Recipe, recipeMap: RecipeMap<PCBFactoryRecipeBuilder>) : super(recipe, recipeMap)

    constructor(recipeBuilder: PCBFactoryRecipeBuilder) : super(recipeBuilder)

    override fun copy(): PCBFactoryRecipeBuilder = PCBFactoryRecipeBuilder(this)

    override fun applyPropertyCT(key: String, value: Any): Boolean
    {
        if (key == GTLiteRecipeProperties.PCB_FACTORY_TIER.key)
        {
            tier((value as Number).toInt())
            return true
        }
        if (key == GTLiteRecipeProperties.PCB_FACTORY_BIO_CHAMBER_UPGRADE.key)
        {
            upgradeTier((value as Number).toInt())
            return true
        }
        return super.applyPropertyCT(key, value)
    }

    fun tier(tier: Int): PCBFactoryRecipeBuilder = apply {
        if (tier <= 0)
        {
            GTLiteLog.logger.error("PCB Factory Tier cannot be less than 0", IllegalArgumentException())
            recipeStatus = EnumValidationResult.INVALID
        }
        applyProperty(GTLiteRecipeProperties.PCB_FACTORY_TIER, tier)
    }

    fun upgradeTier(tier: Int): PCBFactoryRecipeBuilder = apply {
        if (tier <= 0)
        {
            GTLiteLog.logger.error("PCB Factory Auxiliary Tier cannot be less than 0", IllegalArgumentException())
            recipeStatus = EnumValidationResult.INVALID
        }
        applyProperty(GTLiteRecipeProperties.PCB_FACTORY_BIO_CHAMBER_UPGRADE, tier)
    }

    override fun toString(): String = buildToString(this) {
        appendSuper(super.toString())
        append(GTLiteRecipeProperties.PCB_FACTORY_TIER.key, tier)
        append(GTLiteRecipeProperties.PCB_FACTORY_BIO_CHAMBER_UPGRADE.key, upgradeTier)
    }
}
