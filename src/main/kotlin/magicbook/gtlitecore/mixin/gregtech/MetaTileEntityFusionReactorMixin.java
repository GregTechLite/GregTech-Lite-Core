package magicbook.gtlitecore.mixin.gregtech;

import gregtech.api.GTValues;
import gregtech.api.metatileentity.IFastRenderMetaTileEntity;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.recipes.RecipeMaps;
import gregtech.client.utils.IBloomEffect;
import gregtech.common.metatileentities.multi.electric.MetaTileEntityFusionReactor;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Mixin(value = MetaTileEntityFusionReactor.class, remap = false)
public abstract class MetaTileEntityFusionReactorMixin extends RecipeMapMultiblockController implements IFastRenderMetaTileEntity, IBloomEffect
{

    @Mutable
    @Final
    @Shadow
    private final int tier;

    public MetaTileEntityFusionReactorMixin(ResourceLocation metaTileEntityId, int tier)
    {
        super(metaTileEntityId, RecipeMaps.FUSION_RECIPES);
        this.tier = tier;
    }

    @Override
    public void addInformation(ItemStack stack,
                               @Nullable World player,
                               @NotNull List<String> tooltip,
                               boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        long energyCostEach = calculateEnergyStorageFactor(16) / 1000000L;
        tooltip.add(I18n.format("gtlitecore.machine.fusion_reactor.energy_cost",
                GTValues.V[tier] / 16, energyCostEach / 16));
        tooltip.add(I18n.format("gtlitecore.machine.fusion_reactor.recipe_request"));
        tooltip.add(I18n.format("gregtech.machine.fusion_reactor.overclocking"));
    }

    @Shadow
    private long calculateEnergyStorageFactor(int energyInputAmount)
    {
        return (long) energyInputAmount * (long) Math.pow(2.0, this.tier - 6) * 10000000L;
    }

}
