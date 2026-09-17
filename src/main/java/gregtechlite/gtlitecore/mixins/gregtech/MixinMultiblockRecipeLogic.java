package gregtechlite.gtlitecore.mixins.gregtech;

import gregtech.api.capability.IMultipleRecipeMaps;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.capability.INotifiableHandler;
import gregtech.api.capability.impl.AbstractRecipeLogic;
import gregtech.api.capability.impl.FluidTankList;
import gregtech.api.capability.impl.ItemHandlerList;
import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.multiblock.AbilityInstances;
import gregtech.api.metatileentity.multiblock.IMultiblockAbilityPart;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.api.metatileentity.multiblock.MultiblockWithDisplayBase;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMap;
import gregtech.common.ConfigHolder;
import gregtechlite.gtlitecore.api.GTLiteValues;
import gregtechlite.gtlitecore.api.capability.MultipleNotifiableHandler;
import gregtechlite.gtlitecore.api.capability.PatternedSingletonDualInputInventory;
import gregtechlite.gtlitecore.api.capability.PatternedSingletonDualInputProxy;
import gregtechlite.gtlitecore.api.capability.SingletonDualInputAdapter;
import gregtechlite.gtlitecore.api.capability.SingletonDualInputProxy;
import gregtechlite.gtlitecore.api.capability.handler.SingletonDualInputHandler;
import gregtechlite.gtlitecore.api.metatileentity.multiblock.ColorChannel;
import gregtechlite.gtlitecore.mixins.hooks.Implemented;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;
import it.unimi.dsi.fastutil.objects.Reference2ObjectMap;
import it.unimi.dsi.fastutil.objects.Reference2ObjectOpenHashMap;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;
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
    private final Reference2ObjectMap<PatternedSingletonDualInputInventory, ObjectList<Recipe>>
            gtlitecore$dualInputRecipeCache = new Reference2ObjectOpenHashMap<>();

    @Unique
    private @Nullable RecipeMap<?> gtlitecore$lastRecipeMap = null;

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

    // region Color-Based Distinct Impl

    @Unique
    private int gtlitecore$lastLoggedChannelMask = 0;

    @Unique
    private int gtlitecore$lastChannel = -2;

    @Unique
    private @Nullable IItemHandlerModifiable gtlitecore$lastBus = null;

    @Inject(method = "trySearchNewRecipe",
            at = @At("HEAD"),
            cancellable = true)
    private void gtlitecore$trySearchNewRecipeChannels(CallbackInfo callbackInfo)
    {
        if (!(metaTileEntity instanceof RecipeMapMultiblockController))
            return;

        RecipeMapMultiblockController controller = (RecipeMapMultiblockController) metaTileEntity;

        if (ConfigHolder.machines.enableMaintenance && controller.hasMaintenanceMechanics()
                && controller.getNumMaintenanceProblems() > 5)
        {
            callbackInfo.cancel();
            return;
        }

        if (gtlitecore$trySearchNewRecipeByChannel(controller))
            callbackInfo.cancel();
    }

    @SuppressWarnings("unchecked")
    @Unique
    private boolean gtlitecore$trySearchNewRecipeByChannel(RecipeMapMultiblockController controller)
    {
        ObjectList<IItemHandlerModifiable>[] itemCache = new ObjectList[ColorChannel.COUNT + 1];
        ObjectList<IFluidTank>[] fluidCache = new ObjectList[ColorChannel.COUNT + 1];

        for (IMultiblockPart part : controller.getMultiblockParts())
        {
            if (!(part instanceof MetaTileEntity) || !(part instanceof IMultiblockAbilityPart))
                continue;

            IMultiblockAbilityPart<?> abilityPart = (IMultiblockAbilityPart<?>) part;
            int channel = ColorChannel.ofPaintingColor(((MetaTileEntity) part).getPaintingColor());
            int cacheIdx = channel == ColorChannel.NONE ? 0 : channel + 1;

            if (abilityPart.getAbilities().contains(MultiblockAbility.IMPORT_ITEMS))
            {
                AbilityInstances instances = new AbilityInstances(MultiblockAbility.IMPORT_ITEMS);
                abilityPart.registerAbilities(instances);
                gtlitecore$addUniqueCache(gtlitecore$buildItemCache(itemCache, cacheIdx),
                        instances.cast());
            }
            if (abilityPart.getAbilities().contains(MultiblockAbility.IMPORT_FLUIDS))
            {
                AbilityInstances instances = new AbilityInstances(MultiblockAbility.IMPORT_FLUIDS);
                abilityPart.registerAbilities(instances);
                gtlitecore$addUniqueCache(gtlitecore$buildFluidCache(fluidCache, cacheIdx),
                        instances.cast());
            }
        }

        int presentMask = 0;
        for (int channel = 0; channel < ColorChannel.COUNT; channel++)
        {
            if (itemCache[channel + 1] != null || fluidCache[channel + 1] != null)
                presentMask |= 1 << channel;
        }

        if (presentMask != gtlitecore$lastLoggedChannelMask)
        {
            gtlitecore$lastLoggedChannelMask = presentMask;
            GTLiteValues.LOGGER.info("[ColorChannel] controller at {} channel set changed, channels in use: {}",
                    controller.getPos(), gtlitecore$channelList(presentMask));
        }

        if (presentMask == 0)
            return false;

        if (gtlitecore$trySearchNewRecipeDualInput())
            return true;

        long maxVoltage = getMaxVoltage();
        boolean distinct = controller.canBeDistinct() && controller.isDistinct()
                && getInputInventory().getSlots() > 0;
        boolean allowSameFluidFill = getInputTank().allowSameFluidFill();
        boolean anyRecipeFound = false;

        int[] groupCache = new int[ColorChannel.COUNT + 1];
        int groupCount = 0;
        for (int channel = 0; channel < ColorChannel.COUNT; channel++)
        {
            if ((presentMask & (1 << channel)) != 0)
                groupCache[groupCount++] = channel + 1;
        }
        if (itemCache[0] != null || fluidCache[0] != null)
            groupCache[groupCount++] = 0;

        int startGroup = 0; // do robin rotation
        int lastCache = gtlitecore$lastChannel == ColorChannel.NONE ? 0 : gtlitecore$lastChannel + 1;
        for (int i = 0; i < groupCount; i++)
        {
            if (groupCache[i] == lastCache)
            {
                startGroup = (i + 1) % groupCount;
                break;
            }
        }

        for (int groupIndex = 0; groupIndex < groupCount; groupIndex++)
        {
            int cache = groupCache[(startGroup + groupIndex) % groupCount];
            int channel = cache == 0 ? ColorChannel.NONE : cache - 1;

            ObjectList<IItemHandlerModifiable> itemGroup = new ObjectArrayList<>();
            gtlitecore$addUniqueCache(itemGroup, itemCache[cache]);

            ObjectList<IFluidTank> fluidGroup = new ObjectArrayList<>();
            gtlitecore$addUniqueCache(fluidGroup, fluidCache[cache]);

            if (distinct)
            {
                if (itemGroup.isEmpty())
                    continue;

                int startBus = 0; // do intra rotation
                if (gtlitecore$lastBus != null)
                {
                    int found = itemGroup.indexOf(gtlitecore$lastBus);
                    if (found >= 0)
                        startBus = (found + 1) % itemGroup.size();
                }

                for (int i = 0; i < itemGroup.size(); i++)
                {
                    IItemHandlerModifiable bus = itemGroup.get((startBus + i) % itemGroup.size());

                    if (invalidatedInputList.contains(bus))
                        continue;

                    ObjectList<IFluidTank> tanks = new ObjectArrayList<>(fluidGroup);
                    gtlitecore$addHandlerFluidTanks(tanks, bus);
                    IMultipleTankHandler fluids = new FluidTankList(allowSameFluidFill, tanks);

                    Recipe currentRecipe = previousRecipe != null && previousRecipe.matches(false, bus, fluids)
                            ? previousRecipe : findRecipe(maxVoltage, bus, fluids);

                    if (currentRecipe == null)
                    {
                        invalidatedInputList.add(bus);
                        continue;
                    }
                    anyRecipeFound = true;
                    if (!checkRecipe(currentRecipe))
                        continue;

                    if (gtlitecore$prepareRecipeDistinctByChannel(currentRecipe, bus, fluids))
                    {
                        previousRecipe = currentRecipe;
                        currentDistinctInputBus = bus;
                        lastRecipeIndex = gtlitecore$busIndex(bus);
                        gtlitecore$lastChannel = channel;
                        gtlitecore$lastBus = bus;
                        return true;
                    }
                }
            }
            else
            {
                ItemHandlerList items = new ItemHandlerList(itemGroup);
                ObjectList<IFluidTank> tanks = new ObjectArrayList<>(fluidGroup);
                for (IItemHandlerModifiable handler : itemGroup)
                    gtlitecore$addHandlerFluidTanks(tanks, handler);
                IMultipleTankHandler fluids = new FluidTankList(allowSameFluidFill, tanks);

                Recipe currentRecipe = previousRecipe != null && previousRecipe.getEUt() <= maxVoltage
                        && previousRecipe.matches(false, items, fluids)
                        ? previousRecipe : findRecipe(maxVoltage, items, fluids);

                if (currentRecipe != null)
                    anyRecipeFound = true;

                if (currentRecipe != null && checkRecipe(currentRecipe) && prepareRecipe(currentRecipe, items, fluids))
                {
                    previousRecipe = currentRecipe;
                    gtlitecore$lastChannel = channel;
                    gtlitecore$lastBus = null;
                    return true;
                }
            }
        }

        invalidInputsForRecipes = !anyRecipeFound;
        return true;
    }

    @Unique
    private ObjectList<IItemHandlerModifiable> gtlitecore$buildItemCache(ObjectList<IItemHandlerModifiable>[] caches, int index)
    {
        ObjectList<IItemHandlerModifiable> cache = caches[index];
        if (cache == null)
        {
            cache = new ObjectArrayList<>();
            caches[index] = cache;
        }
        return cache;
    }

    @Unique
    private ObjectList<IFluidTank> gtlitecore$buildFluidCache(ObjectList<IFluidTank>[] caches, int index)
    {
        ObjectList<IFluidTank> cache = caches[index];
        if (cache == null)
        {
            cache = new ObjectArrayList<>();
            caches[index] = cache;
        }
        return cache;
    }

    @Unique
    private <T> void gtlitecore$addUniqueCache(ObjectList<T> target, @Nullable List<? extends T> source)
    {
        if (source == null)
            return;
        for (T element : source)
            if (!target.contains(element))
                target.add(element);
    }

    @Unique
    private void gtlitecore$addHandlerFluidTanks(ObjectList<IFluidTank> tanks, IItemHandler handler)
    {
        if (handler instanceof IFluidTank)
        {
            IFluidTank tank = (IFluidTank) handler;
            if (!tanks.contains(tank))
                tanks.add(tank);
        }
        else if (handler instanceof IMultipleTankHandler)
        {
            for (IFluidTank tank : ((IMultipleTankHandler) handler).getFluidTanks())
                if (!tanks.contains(tank))
                    tanks.add(tank);
        }
    }

    @Unique
    private boolean gtlitecore$prepareRecipeDistinctByChannel(Recipe recipe, IItemHandlerModifiable bus,
                                                              IMultipleTankHandler fluids)
    {
        recipe = Recipe.trimRecipeOutputs(recipe, getRecipeMap(), metaTileEntity.getItemOutputLimit(),
                metaTileEntity.getFluidOutputLimit());

        recipe = findParallelRecipe(recipe, bus, fluids, getOutputInventory(), getOutputTank(),
                getMaxParallelVoltage(), getParallelLimit());

        if (recipe != null)
        {
            recipe = setupAndConsumeRecipeInputs(recipe, bus, fluids);
            if (recipe != null)
            {
                setupRecipe(recipe);
                return true;
            }
        }
        return false;
    }

    @Unique
    private int gtlitecore$busIndex(IItemHandlerModifiable bus)
    {
        List<IItemHandlerModifiable> buses = getInputBuses();
        for (int i = 0; i < buses.size(); i++)
            if (buses.get(i) == bus)
                return i;
        return 0;
    }

    @Unique
    private String gtlitecore$channelList(int mask)
    {
        StringBuilder builder = new StringBuilder();
        for (int channel = 0; channel < ColorChannel.COUNT; channel++)
        {
            if ((mask & (1 << channel)) == 0)
                continue;
            if (builder.length() > 0)
                builder.append(',');
            builder.append(channel);
        }
        return builder.length() == 0 ? "none" : builder.toString();
    }

    // endregion

    @Shadow
    public abstract long getMaxVoltage();

    @Shadow
    public abstract boolean checkRecipe(@NotNull Recipe recipe);

    @Shadow
    protected abstract List<IItemHandlerModifiable> getInputBuses();

    @Shadow
    protected abstract IItemHandlerModifiable getInputInventory();

    @Shadow
    protected abstract IItemHandlerModifiable getOutputInventory();

    @Shadow
    protected abstract IMultipleTankHandler getInputTank();

    @Shadow
    protected abstract IMultipleTankHandler getOutputTank();

    @Shadow
    protected IItemHandlerModifiable currentDistinctInputBus;
}
