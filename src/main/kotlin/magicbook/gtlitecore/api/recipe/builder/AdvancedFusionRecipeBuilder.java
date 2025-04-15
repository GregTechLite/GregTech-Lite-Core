package magicbook.gtlitecore.api.recipe.builder;

import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.util.EnumValidationResult;
import magicbook.gtlitecore.api.recipe.property.AdvancedFusionTieredProperty;
import magicbook.gtlitecore.api.utils.GTLiteLog;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.jetbrains.annotations.NotNull;

public class AdvancedFusionRecipeBuilder extends RecipeBuilder<AdvancedFusionRecipeBuilder>
{

    public AdvancedFusionRecipeBuilder()
    {
        super();
    }

    public AdvancedFusionRecipeBuilder(Recipe recipe,
                                       RecipeMap<AdvancedFusionRecipeBuilder> recipeMap)
    {
        super(recipe, recipeMap);
    }

    public AdvancedFusionRecipeBuilder(RecipeBuilder<AdvancedFusionRecipeBuilder> recipeBuilder)
    {
        super(recipeBuilder);
    }

    @Override
    public AdvancedFusionRecipeBuilder copy()
    {
        return new AdvancedFusionRecipeBuilder(this);
    }

    public int getTier()
    {
        return this.recipePropertyStorage == null ? 0 : this.recipePropertyStorage.get(AdvancedFusionTieredProperty.getInstance(), 0);
    }

    @Override
    public boolean applyPropertyCT(@NotNull String key, @NotNull Object value)
    {
        if (key.equals(AdvancedFusionTieredProperty.KEY))
        {
            this.tier(((Number) value).intValue());
            return true;
        }
        return super.applyPropertyCT(key, value);
    }

    public AdvancedFusionRecipeBuilder tier(int tier)
    {
        if (tier <= 0)
        {
            GTLiteLog.logger.error("Tier cannot be less than or equal to 0", new IllegalArgumentException());
            this.recipeStatus = EnumValidationResult.INVALID;
        }
        this.applyProperty(AdvancedFusionTieredProperty.getInstance(), tier);
        return this;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append(AdvancedFusionTieredProperty.getInstance().getKey(), this.getTier())
                .toString();
    }

}
