package gregtechlite.gtlitecore.common.block.adapter

import gregtechlite.gtlitecore.api.block.variant.BlockVariant
import gregtechlite.gtlitecore.common.block.GTLiteBlocks
import gregtechlite.gtlitecore.common.block.GTLiteStoneVariantBlock
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack

enum class PolishedStoneBlock : BlockVariant
{

    LIMESTONE,
    KOMATIITE,
    GREEN_SCHIST,
    BLUE_SCHIST,
    KIMBERLITE,
    QUARTZITE,
    SLATE,
    SHALE;

    override val state: IBlockState
        get() = GTLiteBlocks.STONES[GTLiteStoneVariantBlock.StoneVariant.POLISHED]!!
            .getState(GTLiteStoneVariantBlock.StoneType.valueOf(this.name))

    override fun getStack(count: Int): ItemStack
    {
        return GTLiteBlocks.STONES[GTLiteStoneVariantBlock.StoneVariant.POLISHED]!!
            .getItemVariant(GTLiteStoneVariantBlock.StoneType.valueOf(this.name))
    }

}