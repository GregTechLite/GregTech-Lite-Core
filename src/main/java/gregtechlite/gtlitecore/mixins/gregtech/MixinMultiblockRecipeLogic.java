package gregtechlite.gtlitecore.mixins.gregtech;

import gregtech.api.capability.IMultipleRecipeMaps;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.capability.INotifiableHandler;
import gregtech.api.capability.impl.AbstractRecipeLogic;
import gregtech.api.capability.impl.ItemHandlerList;
import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.api.metatileentity.multiblock.MultiblockWithDisplayBase;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.util.GTLog;
import gregtechlite.gtlitecore.api.capability.MultipleNotifiableHandler;
import gregtechlite.gtlitecore.api.capability.PatternedSingletonDualInputInventory;
import gregtechlite.gtlitecore.api.capability.PatternedSingletonDualInputProxy;
import gregtechlite.gtlitecore.api.capability.SingletonDualInputAdapter;
import gregtechlite.gtlitecore.api.capability.SingletonDualInputProxy;
import gregtechlite.gtlitecore.api.capability.handler.SingletonDualInputHandler;
import gregtechlite.gtlitecore.mixins.hooks.Implemented;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;
import it.unimi.dsi.fastutil.objects.Reference2ObjectOpenHashMap;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import org.jetbrains.annotations.ApiStatus.ScheduledForRemoval;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Mixin(value = MultiblockRecipeLogic.class, remap = false)
public abstract class MixinMultiblockRecipeLogic extends AbstractRecipeLogic implements SingletonDualInputAdapter
{
    @Shadow
    protected List<IItemHandlerModifiable> invalidatedInputList;

    @Shadow
    protected int lastRecipeIndex;

    @Unique
    private final Reference2ObjectOpenHashMap<PatternedSingletonDualInputInventory, ObjectList<Recipe>>
            gtlitecore$dualInputRecipeCache = new Reference2ObjectOpenHashMap<>();

    @Unique
    private @Nullable RecipeMap<?> gtlitecore$lastRecipeMap = null;

    @Unique
    private final boolean gtlitecore$debug = true; // TODO: Remove it when test finished.

    public MixinMultiblockRecipeLogic(MetaTileEntity tileEntity, RecipeMap<?> recipeMap)
    {
        super(tileEntity, recipeMap);
    }

    // region Dual Buffer Impl

    /**
     * @reason (1) Allowed to check notified inputs list when check working for inputs in recipe logic.
     *         (2) Logging test infos for singleton dual api (TODO: Remove it when test finished).
     * @author Magic_Sweepy
     */
    @ScheduledForRemoval(inVersion = "Change gregtech to our forked version")
    @Deprecated
    @Implemented(at = "https://github.com/GregTechCEu/GregTech/pull/2769")
    @Overwrite
    @Override
    protected boolean canWorkWithInputs()
    {
        if (gtlitecore$consumeDualInputUpdates())
            return true;

        MultiblockWithDisplayBase controller = (MultiblockWithDisplayBase) metaTileEntity;
        if (controller instanceof RecipeMapMultiblockController)
        {
            RecipeMapMultiblockController distinctController = (RecipeMapMultiblockController) controller;

            if (distinctController.canBeDistinct() && distinctController.isDistinct()
                    && getInputInventory().getSlots() > 0)
            {
                boolean canWork = false;
                if (invalidatedInputList.isEmpty()) return true;

                if (!metaTileEntity.getNotifiedFluidInputList().isEmpty())
                {
                    canWork = true;
                    invalidatedInputList.clear();
                    metaTileEntity.getNotifiedFluidInputList().clear();
                    metaTileEntity.getNotifiedItemInputList().clear();
                }
                else
                {
                    Iterator<IItemHandlerModifiable> notifiedIter = metaTileEntity.getNotifiedItemInputList().iterator();
                    while (notifiedIter.hasNext())
                    {
                        IItemHandlerModifiable bus = notifiedIter.next();
                        Iterator<IItemHandlerModifiable> invalidatedIter = invalidatedInputList.iterator();
                        while (invalidatedIter.hasNext()) {
                            IItemHandler invalidatedHandler = invalidatedIter.next();
                            if (invalidatedHandler instanceof MultipleNotifiableHandler)
                            {
                                MultipleNotifiableHandler multipleNotifiableHandler = (MultipleNotifiableHandler) invalidatedHandler;
                                for (INotifiableHandler notifiableHandler : multipleNotifiableHandler.getBackingNotifiers())
                                {
                                    if (notifiableHandler == bus)
                                    {
                                        canWork = true;
                                        invalidatedIter.remove();
                                        break;
                                    }
                                }
                            }
                            else if (invalidatedHandler == bus)
                            {
                                canWork = true;
                                invalidatedIter.remove();
                            }
                        }
                        notifiedIter.remove();
                    }
                }
                ArrayList<IItemHandler> flattenedHandlers = new ArrayList<>();
                for (IItemHandler itemHandler : getInputBuses())
                {
                    if (itemHandler instanceof ItemHandlerList)
                    {
                        flattenedHandlers.addAll(((ItemHandlerList) itemHandler).getBackingHandlers());
                    }
                    flattenedHandlers.add(itemHandler);
                }

                // noinspection SlowListContainsAll, SuspiciousMethodCalls
                if (!invalidatedInputList.containsAll(flattenedHandlers))
                {
                    canWork = true;
                }

                return canWork;
            }
        }
        return super.canWorkWithInputs();
    }

    // endregion

    // region Pattern Buffer Impl

    /**
     * @author Magic_Sweepy
     * @reason Clean {@link #gtlitecore$dualInputRecipeCache} when the logic is invalidated.
     */
    @Overwrite
    @Override
    public void invalidate()
    {
        super.invalidate();
        lastRecipeIndex = 0;
        invalidatedInputList.clear();
        gtlitecore$dualInputRecipeCache.clear();
    }

    /**
     * @author Magic_Sweepy
     * @reason Auto clean cache and last recipe map check.
     */
    @Overwrite
    @Override
    public @Nullable RecipeMap<?> getRecipeMap()
    {
        RecipeMap<?> recipeMap;
        if (metaTileEntity instanceof IMultipleRecipeMaps)
            recipeMap = ((IMultipleRecipeMaps) metaTileEntity).getCurrentRecipeMap();
        else
            recipeMap = super.getRecipeMap();

        if (recipeMap != gtlitecore$lastRecipeMap)
        {
            if (gtlitecore$lastRecipeMap != null)
            {
                gtlitecore$dualInputRecipeCache.clear();
            }
            gtlitecore$lastRecipeMap = recipeMap;
        }
        return recipeMap;
    }

    @Inject(method = "trySearchNewRecipeCombined",
            at = @At("HEAD"),
            cancellable = true)
    private void gtlitecore$trySearchNewRecipeCombined(CallbackInfo callbackInfo)
    {
        if (gtlitecore$trySearchNewRecipeDualInput())
        {
            callbackInfo.cancel();
        }
    }

    @Unique
    public boolean gtlitecore$consumeDualInputUpdates()
    {
        if (metaTileEntity instanceof MultiblockControllerBase)
        {
            MultiblockControllerBase controller = ((MultiblockControllerBase) metaTileEntity);
            boolean isUpdated = false;

            for (IMultiblockPart multiblockPart : controller.getMultiblockParts())
            {
                if (multiblockPart instanceof SingletonDualInputProxy
                        && ((SingletonDualInputProxy) multiblockPart).shouldUpdate())
                {
                    isUpdated = true;
                }
            }
            return isUpdated;
        }
        else
        {
            return false;
        }
    }

    @Unique
    private boolean gtlitecore$trySearchNewRecipeDualInput()
    {
        if (!(metaTileEntity instanceof MultiblockControllerBase))
            return false;

        MultiblockControllerBase controller = (MultiblockControllerBase) metaTileEntity;
        long maxVoltage = getMaxVoltage();

        for (IMultiblockPart part : controller.getMultiblockParts())
        {
            if (!(part instanceof PatternedSingletonDualInputProxy)) continue;

            Iterator<? extends PatternedSingletonDualInputInventory> inventories =
                    ((PatternedSingletonDualInputProxy) part).inventories();
            while (inventories.hasNext())
            {
                PatternedSingletonDualInputInventory inventory = inventories.next();
                if (inventory.isEmpty()) continue;

                for (Recipe recipe : gtlitecore$candidateRecipes(inventory, maxVoltage))
                {
                    if (recipe.getEUt() > maxVoltage) continue;
                    if (!checkRecipe(recipe)) continue;

                    IItemHandlerModifiable itemInputs = inventory.getConsumableItemHandler();
                    IMultipleTankHandler fluidInputs = inventory.getConsumableFluidHandler();
                    if (prepareRecipe(recipe, itemInputs, fluidInputs))
                    {
                        previousRecipe = recipe;
                        invalidInputsForRecipes = false;
                        if (gtlitecore$debug)
                        {
                            GTLog.logger.info("{} picked a recipe (EUt={}, duration={}) for inventory {}",
                                    metaTileEntity.getMetaFullName(),
                                    recipe.getEUt(), recipe.getDuration(),
                                    System.identityHashCode(inventory));
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Unique
    private ObjectList<Recipe> gtlitecore$candidateRecipes(PatternedSingletonDualInputInventory inventory,
                                                           long maxVoltage)
    {
        ObjectList<Recipe> cached = gtlitecore$dualInputRecipeCache.get(inventory);
        if (cached != null) return cached;

        boolean cacheable = inventory.shouldBeCached();
        ObjectList<Recipe> computed = new ObjectArrayList<>();

        RecipeMap<?> recipeMap = getRecipeMap();
        if (recipeMap != null)
        {
            Recipe recipe;
            if (cacheable)
            {
                SingletonDualInputHandler pattern = inventory.getPatternInputs();
                ObjectList<ItemStack> items = new ObjectArrayList<>();
                for (ItemStack stack : pattern.inputItems)
                {
                    if (!stack.isEmpty())
                        items.add(stack);
                }
                ObjectList<FluidStack> fluids = new ObjectArrayList<>();
                for (FluidStack stack : pattern.inputFluid)
                {
                    if (stack.amount > 0)
                        fluids.add(stack);
                }
                recipe = recipeMap.findRecipe(maxVoltage, items, fluids);
            }
            else
            {
                recipe = recipeMap.findRecipe(maxVoltage, inventory.getConsumableItemHandler(),
                        inventory.getConsumableFluidHandler());
            }

            if (recipe != null)
                computed.add(recipe);
        }

        if (cacheable && !computed.isEmpty())
        {
            gtlitecore$dualInputRecipeCache.put(inventory, computed);
        }
        return computed;
    }

    @SuppressWarnings("AddedMixinMembersNamePattern")
    @Unique
    @Override
    public void removeInventoryRecipeCache(PatternedSingletonDualInputInventory inventory)
    {
        gtlitecore$dualInputRecipeCache.remove(inventory);
    }

    @SuppressWarnings("AddedMixinMembersNamePattern")
    @Unique
    @Override
    public void clearInventoryRecipeCache()
    {
        gtlitecore$dualInputRecipeCache.clear();
    }

    // endregion

    @Shadow
    public abstract long getMaxVoltage();

    @Shadow
    public abstract boolean checkRecipe(@NotNull Recipe recipe);

    @Shadow
    protected abstract List<IItemHandlerModifiable> getInputBuses();
}
