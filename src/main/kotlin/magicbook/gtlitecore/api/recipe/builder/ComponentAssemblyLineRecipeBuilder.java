package magicbook.gtlitecore.api.recipe.builder;

import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.util.EnumValidationResult;
import magicbook.gtlitecore.api.recipe.property.ComponentAssemblyLineTieredProperty;
import magicbook.gtlitecore.api.utils.GTLiteLog;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.jetbrains.annotations.NotNull;

public class ComponentAssemblyLineRecipeBuilder extends RecipeBuilder<ComponentAssemblyLineRecipeBuilder>
{

    public ComponentAssemblyLineRecipeBuilder() {}

    public ComponentAssemblyLineRecipeBuilder(Recipe recipe,
                                              RecipeMap<ComponentAssemblyLineRecipeBuilder> recipeMap)
    {
        super(recipe, recipeMap);
    }

    public ComponentAssemblyLineRecipeBuilder(RecipeBuilder<ComponentAssemblyLineRecipeBuilder> recipeBuilder)
    {
        super(recipeBuilder);
    }

    @Override
    public ComponentAssemblyLineRecipeBuilder copy()
    {
        return new ComponentAssemblyLineRecipeBuilder(this);
    }

    public int getTier()
    {
        return this.recipePropertyStorage == null ? 0 : this.recipePropertyStorage.get(ComponentAssemblyLineTieredProperty.getInstance(), 0);
    }

    @Override
    public boolean applyPropertyCT(@NotNull String key, @NotNull Object value)
    {
        if (key.equals(ComponentAssemblyLineTieredProperty.KEY))
        {
            this.tier(((Number) value).intValue());
            return true;
        }
        return super.applyPropertyCT(key, value);
    }

    public ComponentAssemblyLineRecipeBuilder tier(int tier)
    {
        if (tier <= 0)
        {
            GTLiteLog.logger.error("Tier cannot be less than or equal to 0", new IllegalArgumentException());
            this.recipeStatus = EnumValidationResult.INVALID;
        }
        this.applyProperty(ComponentAssemblyLineTieredProperty.getInstance(), tier);
        return this;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append(ComponentAssemblyLineTieredProperty.getInstance().getKey(), this.getTier())
                .toString();
    }

}
