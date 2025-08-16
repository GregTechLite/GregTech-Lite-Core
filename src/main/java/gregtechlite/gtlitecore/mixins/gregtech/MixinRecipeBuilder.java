package gregtechlite.gtlitecore.mixins.gregtech;

import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.util.ValidationResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = RecipeBuilder.class, remap = false)
public abstract class MixinRecipeBuilder
{

    @Shadow
    protected long EUt;

    /**
     * Minimum {@code EUt} request bound is not be implemented in CEu but only throw loggers. We added a minimum
     * bound of the {@code EUt} properties in {@link RecipeBuilder}.
     * <p>
     * If a recipe requested {@code EUt} value is <tt>0</tt>, then added it with <tt>1</tt> to resolve bound
     * problem of {@link RecipeBuilder}.
     *
     * @author Magic_Sweepy
     */
    @Inject(method = "build",
            at = @At("HEAD"))
    private void requestEUtRange(CallbackInfoReturnable<ValidationResult<Recipe>> cir)
    {
        if (EUt == 0) EUt += 1;
    }

}
