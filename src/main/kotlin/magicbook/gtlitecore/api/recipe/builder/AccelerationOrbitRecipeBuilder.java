package magicbook.gtlitecore.api.recipe.builder;

import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.util.EnumValidationResult;
import magicbook.gtlitecore.api.recipe.property.AccelerationOrbitTieredProperty;
import magicbook.gtlitecore.api.utils.GTLiteLog;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.jetbrains.annotations.NotNull;

public class AccelerationOrbitRecipeBuilder extends RecipeBuilder<AccelerationOrbitRecipeBuilder>
{

    public AccelerationOrbitRecipeBuilder() {}

    public AccelerationOrbitRecipeBuilder(Recipe recipe,
                                          RecipeMap<AccelerationOrbitRecipeBuilder> recipeMap)
    {
        super(recipe, recipeMap);
    }

    public AccelerationOrbitRecipeBuilder(RecipeBuilder<AccelerationOrbitRecipeBuilder> recipeBuilder)
    {
        super(recipeBuilder);
    }

    @Override
    public AccelerationOrbitRecipeBuilder copy()
    {
        return new AccelerationOrbitRecipeBuilder(this);
    }

    public int getTier()
    {
        return this.recipePropertyStorage == null ? 0 : this.recipePropertyStorage.get(AccelerationOrbitTieredProperty.getInstance(), 0);
    }

    @Override
    public boolean applyPropertyCT(@NotNull String key, @NotNull Object value)
    {
        if (key.equals(AccelerationOrbitTieredProperty.KEY))
        {
            this.tier(((Number) value).intValue());
            return true;
        }
        return super.applyPropertyCT(key, value);
    }

    public AccelerationOrbitRecipeBuilder tier(int tier)
    {
        if (tier <= 0)
        {
            GTLiteLog.logger.error("Tier cannot be less than or equal to 0", new IllegalArgumentException());
            this.recipeStatus = EnumValidationResult.INVALID;
        }
        this.applyProperty(AccelerationOrbitTieredProperty.getInstance(), tier);
        return this;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append(AccelerationOrbitTieredProperty.getInstance().getKey(), this.getTier())
                .toString();
    }

}
