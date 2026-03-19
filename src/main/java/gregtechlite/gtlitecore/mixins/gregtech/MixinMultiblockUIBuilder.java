package gregtechlite.gtlitecore.mixins.gregtech;

import com.cleanroommc.modularui.api.drawable.IDrawable;
import com.cleanroommc.modularui.utils.serialization.ByteBufAdapters;
import gregtech.api.capability.impl.AbstractRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.multiblock.ui.MultiblockUIBuilder;
import gregtech.api.metatileentity.multiblock.ui.Operation;
import gregtech.api.mui.GTByteBufAdapters;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.chance.output.impl.ChancedFluidOutput;
import gregtech.api.recipes.chance.output.impl.ChancedItemOutput;
import gregtech.api.util.GTHashMaps;
import gregtech.api.util.GTUtility;
import gregtech.api.util.KeyUtil;
import gregtechlite.gtlitecore.mixins.Implemented;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Implemented(at = "https://github.com/GregTechCEu/GregTech/pull/2913")
@Mixin(value = MultiblockUIBuilder.class, remap = false)
public abstract class MixinMultiblockUIBuilder {

    /**
     * @author Magic_Sweepy
     * @reason Fix incorrect parallel fluid output for the multiblock ui.
     */
    @Overwrite
    public MultiblockUIBuilder addRecipeOutputLine(AbstractRecipeLogic recipeLogic, int maxLines)
    {
        Recipe recipe = recipeLogic.getPreviousRecipe();

        if (getSyncer().syncBoolean(recipe == null))
            return (MultiblockUIBuilder) (Object) this;

        RecipeMap<?> recipeMap = recipeLogic.getRecipeMap();

        if (getSyncer().syncBoolean(recipeMap == null))
            return (MultiblockUIBuilder) (Object) this;

        Recipe trimmedRecipe = null;
        if (isServer())
        {
            MetaTileEntity mte = recipeLogic.getMetaTileEntity();
            trimmedRecipe = Recipe.trimRecipeOutputs(recipe, recipeMap,
                    mte.getItemOutputLimit(), mte.getFluidOutputLimit());
        }

        int p = getSyncer().syncInt(recipeLogic.getParallelRecipesPerformed());
        if (p == 0) p = 1;

        long eut = getSyncer().syncLong(trimmedRecipe == null ? 0 : trimmedRecipe.getEUt());
        long maxVoltage = getSyncer().syncLong(recipeLogic.getMaximumOverclockVoltage());
        int maxProgress = getSyncer().syncInt(recipeLogic.getMaxProgress());

        if (maxProgress == 0) return (MultiblockUIBuilder) (Object) this;

        List<ItemStack> itemOutputs = new ArrayList<>();
        List<ChancedItemOutput> chancedItemOutputs = new ArrayList<>();
        List<FluidStack> fluidOutputs = new ArrayList<>();
        List<ChancedFluidOutput> chancedFluidOutputs = new ArrayList<>();

        if (isServer())
        {
            itemOutputs.addAll(trimmedRecipe.getOutputs());
            chancedItemOutputs.addAll(trimmedRecipe.getChancedOutputs().getChancedEntries());
            fluidOutputs.addAll(trimmedRecipe.getFluidOutputs());
            chancedFluidOutputs.addAll(trimmedRecipe.getChancedFluidOutputs().getChancedEntries());
        }

        itemOutputs = getSyncer().syncCollection(itemOutputs, ByteBufAdapters.ITEM_STACK);
        fluidOutputs = getSyncer().syncCollection(fluidOutputs, ByteBufAdapters.FLUID_STACK);
        chancedItemOutputs = getSyncer().syncCollection(chancedItemOutputs, GTByteBufAdapters.CHANCED_ITEM_OUTPUT);
        chancedFluidOutputs = getSyncer().syncCollection(chancedFluidOutputs, GTByteBufAdapters.CHANCED_FLUID_OUTPUT);

        addKey(KeyUtil.lang(TextFormatting.GRAY, "gregtech.gui.multiblock.recipe_producing"), Operation::addLine);

        int recipeTier = GTUtility.getTierByVoltage(eut);
        int machineTier = GTUtility.getOCTierByVoltage(maxVoltage);

        // items
        Object2IntMap<ItemStack> itemMap = GTHashMaps.fromItemStackCollection(itemOutputs);

        for (ItemStack stack : itemMap.keySet())
        {
            addItemOutputLine(stack, (long) itemMap.getInt(stack) * p, maxProgress);
        }

        for (ChancedItemOutput chancedItemOutput : chancedItemOutputs)
        {
            // noinspection DataFlowIssue
            int chance = getSyncer()
                    .syncInt(() -> recipeMap.chanceFunction.getBoostedChance(chancedItemOutput, recipeTier, machineTier));
            int count = chancedItemOutput.getIngredient().getCount() * p;
            addChancedItemOutputLine(chancedItemOutput, count, chance, maxProgress);
        }

        // fluids

        Object2IntMap<FluidStack> fluidMap = GTHashMaps.fromFluidCollection(fluidOutputs);

        for (FluidStack stack : fluidMap.keySet())
        {
            addFluidOutputLine(stack, (long) fluidMap.getInt(stack) * p, maxProgress);
        }

        for (ChancedFluidOutput chancedFluidOutput : chancedFluidOutputs)
        {
            // noinspection DataFlowIssue
            int chance = getSyncer()
                    .syncInt(() -> recipeMap.chanceFunction.getBoostedChance(chancedFluidOutput, recipeTier, machineTier));
            int count = chancedFluidOutput.getIngredient().amount * p;
            addChancedFluidOutputLine(chancedFluidOutput, count, chance, maxProgress);
        }
        return (MultiblockUIBuilder) (Object) this;
    }

    @Shadow
    @NotNull
    abstract MultiblockUIBuilder.@NotNull InternalSyncer getSyncer();

    @Shadow
    protected abstract boolean isServer();

    @Shadow
    protected abstract void addKey(IDrawable key, Function<IDrawable, Operation> function);

    @Shadow
    protected abstract void addItemOutputLine(@NotNull ItemStack stack, long count, int recipeLength);

    @Shadow
    protected abstract void addChancedItemOutputLine(@NotNull ChancedItemOutput output, int count, int chance, int recipeLength);

    @Shadow
    protected abstract void addFluidOutputLine(@NotNull FluidStack stack, long count, int recipeLength);

    @Shadow
    protected abstract void addChancedFluidOutputLine(ChancedFluidOutput output, int count, int chance, int recipeLength);

}
