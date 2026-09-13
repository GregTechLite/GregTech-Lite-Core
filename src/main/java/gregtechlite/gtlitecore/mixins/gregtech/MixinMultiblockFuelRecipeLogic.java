package gregtechlite.gtlitecore.mixins.gregtech;

import gregtech.api.capability.impl.MultiblockFuelRecipeLogic;
import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(value = MultiblockFuelRecipeLogic.class, remap = false)
public abstract class MixinMultiblockFuelRecipeLogic extends MultiblockRecipeLogic
{
    public MixinMultiblockFuelRecipeLogic(RecipeMapMultiblockController mte)
    {
        super(mte);
    }

    /**
     * Prevent multiblock generators from decreasing progress when dynamo hatch is full.
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
