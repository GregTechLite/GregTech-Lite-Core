package magicbook.gtlitecore.common.block.itemblocks;

import gregtech.api.unification.material.Material;
import gregtech.common.ConfigHolder;
import magicbook.gtlitecore.api.unification.ore.GTLiteOrePrefix;
import magicbook.gtlitecore.common.block.blocks.BlockSheetedFrame;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SheetedFrameItemBlock extends ItemBlock
{

    private final BlockSheetedFrame frameBlock;

    public SheetedFrameItemBlock(BlockSheetedFrame block)
    {
        super(block);
        this.frameBlock = block;
        this.setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int damage)
    {
        return damage;
    }

    public IBlockState getBlockState(ItemStack stack)
    {
        return this.frameBlock.getStateFromMeta(this.getMetadata(stack.getItemDamage()));
    }

    @NotNull
    @Override
    public String getItemStackDisplayName(@NotNull ItemStack stack)
    {
        Material material = this.getBlockState(stack).getValue(frameBlock.variantProperty);
        return GTLiteOrePrefix.sheetedFrame.getLocalNameForItem(material);
    }

    @Override
    public void addInformation(@NotNull ItemStack stack,
                               @Nullable World worldIn,
                               @NotNull List<String> tooltip,
                               @NotNull ITooltipFlag flagIn)
    {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        if (ConfigHolder.misc.debug)
            tooltip.add("MetaItem Id: sheetedFrame" + this.frameBlock.getGTMaterial(stack.getMetadata()).toCamelCaseString());
    }

}
