package gregtechlite.gtlitecore.common.block.adapter

import gregtechlite.gtlitecore.api.block.variant.BlockVariant
import gregtechlite.gtlitecore.common.block.GTLiteBlocks
import net.minecraft.block.state.IBlockState
import net.minecraft.item.Item
import net.minecraft.item.ItemStack

@Suppress("Deprecation")
enum class PlankBlock : BlockVariant
{

    BANANA,
    ORANGE,
    MANGO,
    APRICOT,
    LEMON,
    LIME,
    OLIVE,
    NUTMEG,
    COCONUT,
    RAINBOW;

    override val state: IBlockState
        get() = GTLiteBlocks.PLANKS[0].getStateFromMeta(this.ordinal)

    override fun getStack(count: Int): ItemStack
    {
        return ItemStack(Item.getItemFromBlock(GTLiteBlocks.PLANKS[0]), count, this.ordinal, null)
    }

}