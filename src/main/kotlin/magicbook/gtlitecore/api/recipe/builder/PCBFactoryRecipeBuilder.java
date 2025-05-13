package magicbook.gtlitecore.api.recipe.builder;

import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.util.EnumValidationResult;
import magicbook.gtlitecore.api.recipe.property.PCBFactoryAuxiliaryTieredProperty;
import magicbook.gtlitecore.api.recipe.property.PCBFactoryTieredProperty;
import magicbook.gtlitecore.api.utils.GTLiteLog;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.jetbrains.annotations.NotNull;

public class PCBFactoryRecipeBuilder extends RecipeBuilder<PCBFactoryRecipeBuilder>
{

    public PCBFactoryRecipeBuilder() {}

    public PCBFactoryRecipeBuilder(Recipe recipe,
                                   RecipeMap<PCBFactoryRecipeBuilder> recipeMap)
    {
        super(recipe, recipeMap);
    }

    public PCBFactoryRecipeBuilder(PCBFactoryRecipeBuilder recipeBuilder)
    {
        super(recipeBuilder);
    }

    @Override
    public PCBFactoryRecipeBuilder copy()
    {
        return new PCBFactoryRecipeBuilder(this);
    }

    @Override
    public boolean applyPropertyCT(@NotNull String key, @NotNull Object value)
    {
        if (key.equals(PCBFactoryTieredProperty.KEY))
        {
            this.tier(((Number) value).intValue());
            return true;
        }
        if (key.equals(PCBFactoryAuxiliaryTieredProperty.KEY))
        {
            this.upgradeTier(((Number) value).intValue());
            return true;
        }
        return super.applyPropertyCT(key, value);
    }

    public int getTier()
    {
        return this.recipePropertyStorage == null ? 0 : this.recipePropertyStorage.get(PCBFactoryTieredProperty.getInstance(), 0);
    }

    public PCBFactoryRecipeBuilder tier(int tier)
    {
        if (tier <= 0)
        {
            GTLiteLog.logger.error("PCB Factory Tier cannot be less than 0", new IllegalArgumentException());
            this.recipeStatus = EnumValidationResult.INVALID;
        }
        this.applyProperty(PCBFactoryTieredProperty.getInstance(), tier);
        return this;
    }

    public int getUpgradeTier()
    {
        return this.recipePropertyStorage == null ? 0 : this.recipePropertyStorage.get(PCBFactoryAuxiliaryTieredProperty.getInstance(), 0);
    }

    public PCBFactoryRecipeBuilder upgradeTier(int tier)
    {
        if (tier <= 0)
        {
            GTLiteLog.logger.error("PCB Factory Auxiliary Tier cannot be less than 0", new IllegalArgumentException());
            this.recipeStatus = EnumValidationResult.INVALID;
        }
        this.applyProperty(PCBFactoryAuxiliaryTieredProperty.getInstance(), tier);
        return this;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append(PCBFactoryTieredProperty.getInstance().getKey(), getTier())
                .append(PCBFactoryAuxiliaryTieredProperty.getInstance().getKey(), getUpgradeTier())
                .toString();
    }

}
