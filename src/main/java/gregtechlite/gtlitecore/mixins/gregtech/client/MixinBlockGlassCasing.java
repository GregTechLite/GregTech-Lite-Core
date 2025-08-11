package gregtechlite.gtlitecore.mixins.gregtech.client;

import gregtech.api.GTValues;
import gregtech.api.block.VariantActiveBlock;
import gregtech.api.block.VariantItemBlock;
import gregtech.common.blocks.BlockGlassCasing;
import gregtechlite.gtlitecore.api.block.attribute.StateTier;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;

import java.util.List;

@Mixin(BlockGlassCasing.class)
public abstract class MixinBlockGlassCasing extends VariantActiveBlock<BlockGlassCasing.CasingType>
{

    public MixinBlockGlassCasing(Material materialIn)
    {
        super(materialIn);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public void addInformation(@NotNull ItemStack stack,
                               @Nullable World player,
                               @NotNull List<String> tooltip,
                               @NotNull ITooltipFlag advanced)
    {
        super.addInformation(stack, player, tooltip, advanced);
        VariantItemBlock itemBlock = (VariantItemBlock<BlockGlassCasing.CasingType, BlockGlassCasing>) stack.getItem();
        IBlockState stackState = itemBlock.getBlockState(stack);
        Object casingType = this.getState(stackState);
        tooltip.add(I18n.format("gtlitecore.tooltip.glass_tier", GTValues.VOCNF[((StateTier) casingType).getTier()]));
    }

}
