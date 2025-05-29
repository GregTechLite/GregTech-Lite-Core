package magicbook.gtlitecore.mixin.gregtech;

import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.capability.IWorkable;
import gregtech.api.capability.impl.AbstractRecipeLogic;
import gregtech.api.metatileentity.MTETrait;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.logic.IParallelableRecipeLogic;
import net.minecraftforge.items.IItemHandlerModifiable;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

/**
 * Bug Fix: Deleted invalid cast of eutDiscount in AbstractRecipeLogic.
 *
 * @deprecated When GTCEu update and fix this problem.
 */
@ApiStatus.ScheduledForRemoval(inVersion = "Next GTCEu Update")
@Deprecated
@Mixin(value = AbstractRecipeLogic.class, remap = false)
public abstract class AbstractRecipeLogicMixin extends MTETrait implements IWorkable, IParallelableRecipeLogic
{

    @Shadow
    @Final
    private RecipeMap<?> recipeMap;

    @Shadow
    private double speedBonus;
    @Shadow
    private double euDiscount;

    public AbstractRecipeLogicMixin(@NotNull MetaTileEntity metaTileEntity)
    {
        super(metaTileEntity);
    }

    /**
     * @deprecated When GTCEu update and fix this problem.
     *
     * @author Magic_Sweepy
     * @reason Fix invalid cast with int and long in eutDiscount.
     */
    @ApiStatus.ScheduledForRemoval(inVersion = "Next GTCEu Update")
    @Deprecated
    @Overwrite
    public boolean prepareRecipe(Recipe recipe, IItemHandlerModifiable inputInventory,
                                 IMultipleTankHandler inputFluidInventory)
    {
        recipe = Recipe.trimRecipeOutputs(recipe, getRecipeMap(), metaTileEntity.getItemOutputLimit(),
                metaTileEntity.getFluidOutputLimit());
        // apply EU/speed discount (if any) before parallel, if-statement to avoid unnecessarily creating
        // RecipeBuilder object
        if (euDiscount > 0 || speedBonus > 0)
        {
            RecipeBuilder<?> builder = new RecipeBuilder<>(recipe, recipeMap);
            if (euDiscount > 0)
            {
                long newEUt = Math.round(recipe.getEUt() * euDiscount); // Do not cast int in there.
                if (newEUt <= 0)
                    newEUt = 1;
                builder.EUt(newEUt);
            }
            if (speedBonus > 0)
            {
                int duration = recipe.getDuration();
                int newDuration = (int) Math.round(duration * speedBonus);
                if (newDuration <= 0) newDuration = 1;
                builder.duration(newDuration);
            }
            recipe = builder.build().getResult();
        }

        // Pass in the trimmed recipe to the parallel logic
        recipe = findParallelRecipe(recipe, inputInventory, inputFluidInventory,
                getOutputInventory(), getOutputTank(), getMaxParallelVoltage(), getParallelLimit());

        if (recipe != null)
        {
            recipe = setupAndConsumeRecipeInputs(recipe, inputInventory, inputFluidInventory);
            if (recipe != null)
            {
                setupRecipe(recipe);
                return true;
            }
        }
        return false;
    }

    @Shadow
    protected abstract void setupRecipe(@NotNull Recipe recipe);

    @Shadow
    @Nullable
    protected abstract Recipe setupAndConsumeRecipeInputs(@NotNull Recipe recipe,
                                                          @NotNull IItemHandlerModifiable importInventory,
                                                          @NotNull IMultipleTankHandler importFluids);

    @Shadow
    @Nullable
    public abstract RecipeMap<?> getRecipeMap();

    @Shadow
    public abstract int getParallelLimit();

    @Shadow
    protected abstract long getMaxParallelVoltage();

    @Shadow
    protected abstract IMultipleTankHandler getOutputTank();

    @Shadow
    protected abstract IItemHandlerModifiable getOutputInventory();

}
