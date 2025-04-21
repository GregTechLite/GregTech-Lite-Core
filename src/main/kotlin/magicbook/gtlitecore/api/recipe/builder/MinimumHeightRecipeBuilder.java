package magicbook.gtlitecore.api.recipe.builder;

import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.util.EnumValidationResult;
import magicbook.gtlitecore.api.recipe.property.MinimumHeightProperty;
import magicbook.gtlitecore.api.utils.GTLiteLog;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.jetbrains.annotations.NotNull;

public class MinimumHeightRecipeBuilder extends RecipeBuilder<MinimumHeightRecipeBuilder>
{

    public MinimumHeightRecipeBuilder() {}

    public MinimumHeightRecipeBuilder(Recipe recipe,
                                      RecipeMap<MinimumHeightRecipeBuilder> recipeMap)
    {
        super(recipe, recipeMap);
    }

    public MinimumHeightRecipeBuilder(MinimumHeightRecipeBuilder recipeBuilder)
    {
        super(recipeBuilder);
    }

    @Override
    public MinimumHeightRecipeBuilder copy()
    {
        return new MinimumHeightRecipeBuilder(this);
    }

    @Override
    public boolean applyPropertyCT(@NotNull String key, @NotNull Object value)
    {
        if (key.equals(MinimumHeightProperty.KEY))
        {
            this.minHeight(((Number) value).intValue());
            return true;
        }
        return super.applyPropertyCT(key, value);
    }

    public MinimumHeightRecipeBuilder minHeight(int height)
    {
        if (height <= -64 || height >= 256) // TODO Should we consider the special situation when player install a height-extended mod?
        {
            GTLiteLog.logger.error("Minimum Height cannot be less than -64 or greater than 256", new IllegalArgumentException());
            this.recipeStatus = EnumValidationResult.INVALID;
        }
        this.applyProperty(MinimumHeightProperty.getInstance(), height);
        return this;
    }

    public int getMinHeight()
    {
        return this.recipePropertyStorage == null ? 0 : this.recipePropertyStorage.get(MinimumHeightProperty.getInstance(), 0);
    }

    public String toString()
    {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append(MinimumHeightProperty.getInstance().getKey(), getMinHeight())
                .toString();
    }

}
