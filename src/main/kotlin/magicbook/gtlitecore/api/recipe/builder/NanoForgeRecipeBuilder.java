package magicbook.gtlitecore.api.recipe.builder;

import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.util.EnumValidationResult;
import magicbook.gtlitecore.api.recipe.property.NanoForgeTieredProperty;
import magicbook.gtlitecore.api.utils.GTLiteLog;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.jetbrains.annotations.NotNull;

public class NanoForgeRecipeBuilder extends RecipeBuilder<NanoForgeRecipeBuilder>
{

    public NanoForgeRecipeBuilder() {}

    public NanoForgeRecipeBuilder(Recipe recipe,
                                  RecipeMap<NanoForgeRecipeBuilder> recipeMap)
    {
        super(recipe, recipeMap);
    }

    public NanoForgeRecipeBuilder(RecipeBuilder<NanoForgeRecipeBuilder> recipeBuilder)
    {
        super(recipeBuilder);
    }

    @Override
    public NanoForgeRecipeBuilder copy()
    {
        return new NanoForgeRecipeBuilder(this);
    }

    public int getTier()
    {
        return this.recipePropertyStorage == null ? 0 : this.recipePropertyStorage.get(NanoForgeTieredProperty.getInstance(), 0);
    }

    @Override
    public boolean applyPropertyCT(@NotNull String key, @NotNull Object value)
    {
        if (key.equals(NanoForgeTieredProperty.KEY))
        {
            this.tier(((Number) value).intValue());
            return true;
        }
        return super.applyPropertyCT(key, value);
    }

    public NanoForgeRecipeBuilder tier(int tier)
    {
        if (tier <= 0)
        {
            GTLiteLog.logger.error("Tier cannot be less than or equal to 0", new IllegalArgumentException());
            this.recipeStatus = EnumValidationResult.INVALID;
        }
        this.applyProperty(NanoForgeTieredProperty.getInstance(), tier);
        return this;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append(NanoForgeTieredProperty.getInstance().getKey(), this.getTier())
                .toString();
    }

}
