package gregtechlite.gtlitecore.api.recipe.builder

import gregtech.api.recipes.Recipe
import gregtech.api.recipes.RecipeBuilder
import gregtech.api.recipes.RecipeMap
import gregtech.api.util.EnumValidationResult
import gregtechlite.gtlitecore.api.GTLiteLog
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeProperties
import org.apache.commons.lang3.builder.ToStringBuilder

class PCBFactoryRecipeBuilder : RecipeBuilder<PCBFactoryRecipeBuilder>
{

    val tier: Int
        get() = (if (this.recipePropertyStorage == null) 0 else
            this.recipePropertyStorage.get(GTLiteRecipeProperties.PCB_FACTORY_TIER, 0))!!

    val upgradeTier: Int
        get() = (if (this.recipePropertyStorage == null) 0
            else this.recipePropertyStorage.get(GTLiteRecipeProperties.PCB_FACTORY_BIO_CHAMBER_UPGRADE, 0))!!

    constructor()

    @Suppress("unused")
    constructor(recipe: Recipe,
                recipeMap: RecipeMap<PCBFactoryRecipeBuilder>) : super(recipe, recipeMap)

    constructor(recipeBuilder: PCBFactoryRecipeBuilder) : super(recipeBuilder)

    override fun copy(): PCBFactoryRecipeBuilder
    {
        return PCBFactoryRecipeBuilder(this)
    }

    override fun applyPropertyCT(key: String, value: Any): Boolean
    {
        if (key == GTLiteRecipeProperties.PCB_FACTORY_TIER.key)
        {
            this.tier((value as Number).toInt())
            return true
        }
        if (key == GTLiteRecipeProperties.PCB_FACTORY_BIO_CHAMBER_UPGRADE.key)
        {
            this.upgradeTier((value as Number).toInt())
            return true
        }
        return super.applyPropertyCT(key, value)
    }



    fun tier(tier: Int): PCBFactoryRecipeBuilder
    {
        if (tier <= 0)
        {
            GTLiteLog.logger.error("PCB Factory Tier cannot be less than 0", IllegalArgumentException())
            this.recipeStatus = EnumValidationResult.INVALID
        }
        this.applyProperty(GTLiteRecipeProperties.PCB_FACTORY_TIER, tier)
        return this
    }

    fun upgradeTier(tier: Int): PCBFactoryRecipeBuilder
    {
        if (tier <= 0)
        {
            GTLiteLog.logger.error("PCB Factory Auxiliary Tier cannot be less than 0", IllegalArgumentException())
            this.recipeStatus = EnumValidationResult.INVALID
        }
        this.applyProperty(GTLiteRecipeProperties.PCB_FACTORY_BIO_CHAMBER_UPGRADE, tier)
        return this
    }

    override fun toString(): String
    {
        return ToStringBuilder(this)
            .appendSuper(super.toString())
            .append(GTLiteRecipeProperties.PCB_FACTORY_TIER.key, this.tier)
            .append(GTLiteRecipeProperties.PCB_FACTORY_BIO_CHAMBER_UPGRADE.key, this.upgradeTier)
            .toString()
    }

}
