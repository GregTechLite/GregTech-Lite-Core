package gregtechlite.gtlitecore.mixins.gregtech;

import gregtech.api.capability.IEnergyContainer;
import gregtech.api.capability.impl.FuelRecipeLogic;
import gregtech.api.capability.impl.RecipeLogicEnergy;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.recipes.RecipeMap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Unique;

import java.util.function.Supplier;

@Mixin(value = FuelRecipeLogic.class, remap = false)
public abstract class MixinFuelRecipeLogic extends RecipeLogicEnergy
{
    public MixinFuelRecipeLogic(MetaTileEntity mte,
                                RecipeMap<?> recipeMap,
                                Supplier<IEnergyContainer> energyContainer)
    {
        super(mte, recipeMap, energyContainer);
    }

    /**
     * Fix prevent single generators from decreasing progress when energy buffer is full.
     */
    @Unique
    @Override
    protected void updateRecipeProgress()
    {
        if (canRecipeProgress && drawEnergy(recipeEUt, true))
        {
            drawEnergy(recipeEUt, false);
            if (++progressTime > maxProgressTime)
                completeRecipe();
        }
    }
}
