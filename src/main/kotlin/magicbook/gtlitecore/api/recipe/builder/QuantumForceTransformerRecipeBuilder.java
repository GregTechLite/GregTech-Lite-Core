package magicbook.gtlitecore.api.recipe.builder;

import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.util.EnumValidationResult;
import magicbook.gtlitecore.api.recipe.property.QuantumForceTransformerTieredProperty;
import magicbook.gtlitecore.api.utils.GTLiteLog;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.jetbrains.annotations.NotNull;

public class QuantumForceTransformerRecipeBuilder extends RecipeBuilder<QuantumForceTransformerRecipeBuilder>
{

    public QuantumForceTransformerRecipeBuilder() {}

    public QuantumForceTransformerRecipeBuilder(Recipe recipe,
                                                RecipeMap<QuantumForceTransformerRecipeBuilder> recipeMap)
    {
        super(recipe, recipeMap);
    }

    public QuantumForceTransformerRecipeBuilder(RecipeBuilder<QuantumForceTransformerRecipeBuilder> recipeBuilder)
    {
        super(recipeBuilder);
    }

    @Override
    public QuantumForceTransformerRecipeBuilder copy()
    {
        return new QuantumForceTransformerRecipeBuilder(this);
    }

    public int getTier()
    {
        return this.recipePropertyStorage == null ? 0 : this.recipePropertyStorage.get(QuantumForceTransformerTieredProperty.getInstance(), 0);
    }

    @Override
    public boolean applyPropertyCT(@NotNull String key, @NotNull Object value)
    {
        if (key.equals(QuantumForceTransformerTieredProperty.KEY))
        {
            this.tier(((Number) value).intValue());
            return true;
        }
        return super.applyPropertyCT(key, value);
    }

    public QuantumForceTransformerRecipeBuilder tier(int tier)
    {
        if (tier <= 0)
        {
            GTLiteLog.logger.error("Tier cannot be less than or equal to 0", new IllegalArgumentException());
            this.recipeStatus = EnumValidationResult.INVALID;
        }
        this.applyProperty(QuantumForceTransformerTieredProperty.getInstance(), tier);
        return this;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append(QuantumForceTransformerTieredProperty.getInstance().getKey(), this.getTier())
                .toString();
    }

}
