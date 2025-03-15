package magicbook.gtlitecore.api.block.impl;

import magicbook.gtlitecore.api.block.IBlockTranslatable;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class TranslatableVariantItemBlock<T extends Block & IBlockTranslatable> extends ItemBlock
{

    private final T block;

    public TranslatableVariantItemBlock(T block)
    {
        super(block);
        this.block = block;
        this.setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int damage)
    {
        return damage;
    }

    @NotNull
    @Override
    public String getTranslationKey(@NotNull ItemStack stack)
    {
        return this.block.getTranslation(getBlockState(stack));
    }

    @SuppressWarnings("deprecation")
    public IBlockState getBlockState(ItemStack stack)
    {
        return this.block.getStateFromMeta(stack.getItemDamage());
    }

}