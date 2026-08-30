package gregtechlite.gtlitecore.mixins.gregtech.client;

import gregtech.api.GTValues;
import gregtech.api.metatileentity.multiblock.FuelMultiblockController;
import gregtech.api.recipes.RecipeMap;
import gregtech.common.metatileentities.multi.electric.generator.MetaTileEntityLargeTurbine;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Mixin(value = MetaTileEntityLargeTurbine.class, remap = false)
public abstract class MixinMetaTileEntityLargeTurbine extends FuelMultiblockController
{
    @Shadow
    public @Final int tier;

    public MixinMetaTileEntityLargeTurbine(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap, int tier)
    {
        super(metaTileEntityId, recipeMap, tier);
    }

    /**
     * @author Magic_Sweepy
     * @reason Add autofill support hint for player.
     */
    @Overwrite
    @Override
    public void addInformation(ItemStack stack, @Nullable World world, @NotNull List<String> tooltip, boolean advanced)
    {
        super.addInformation(stack, world, tooltip, advanced);
        tooltip.add(I18n.format("gregtech.universal.tooltip.base_production_eut", GTValues.V[tier] * 2));
        tooltip.add(I18n.format("gregtech.multiblock.turbine.efficiency_tooltip", GTValues.VNF[tier]));
        tooltip.add(I18n.format("gtlitecore.machine.large_turbine.autofill"));
    }
}
