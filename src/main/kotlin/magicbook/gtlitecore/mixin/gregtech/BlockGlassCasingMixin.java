package magicbook.gtlitecore.mixin.gregtech;

import gregtech.api.block.VariantActiveBlock;
import gregtech.api.block.VariantItemBlock;
import gregtech.common.blocks.BlockGlassCasing;
import magicbook.gtlitecore.api.block.IGlassTier;
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
public abstract class BlockGlassCasingMixin extends VariantActiveBlock<BlockGlassCasing.CasingType>
{

    public BlockGlassCasingMixin(Material materialIn)
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
        tooltip.add(I18n.format("gtlitecore.tooltip.glass_tier", ((IGlassTier) casingType).getColoredVoltageNameByTier()));
    }

}
