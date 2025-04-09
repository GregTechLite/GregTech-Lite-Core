package magicbook.gtlitecore.mixin.gregtech;

import gregtech.api.capability.IEnergyContainer;
import gregtech.api.capability.impl.FuelRecipeLogic;
import gregtech.api.capability.impl.RecipeLogicEnergy;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.recipes.RecipeMap;
import org.jetbrains.annotations.ApiStatus;
import org.spongepowered.asm.mixin.Mixin;

import java.util.function.Supplier;

/**
 * Bug fixes of {@code FuelRecipeLogic}.
 *
 * @apiNote In {@code FuelRecipeLogic}, recipe progress used incorrect logic with all generators,
 *          because this is same as common recipe logic, this bug will cause all generators running
 *          not-consumed it fuels. For this fix, we add overriden of {@code updateRecipeProgress},
 *          and used back version progress logic to override it.
 *
 * @deprecated When GTCEu update and fix this problem.
 */
@ApiStatus.ScheduledForRemoval(inVersion = "Next GTCEu Update")
@Deprecated
@Mixin(value = FuelRecipeLogic.class, remap = false)
public abstract class FuelRecipeLogicMixin extends RecipeLogicEnergy
{

    public FuelRecipeLogicMixin(MetaTileEntity tileEntity, RecipeMap<?> recipeMap,
                                Supplier<IEnergyContainer> energyContainer)
    {
        super(tileEntity, recipeMap, energyContainer);
    }

    /**
     * @deprecated When GTCEu update and fix this problem.
     */
    @ApiStatus.ScheduledForRemoval(inVersion = "Next GTCEu Update")
    @Deprecated
    @Override
    protected void updateRecipeProgress() // Generators always run recipes
    {
        if (canRecipeProgress && drawEnergy(recipeEUt, true))
        {
            drawEnergy(recipeEUt, false);
            // As recipe starts with progress on 1 this has to be > only not => to compensate for it.
            if (++progressTime > maxProgressTime)
                completeRecipe();
        }
    }

}
