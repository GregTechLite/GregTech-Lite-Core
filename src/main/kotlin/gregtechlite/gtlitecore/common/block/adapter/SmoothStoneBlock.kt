package gregtechlite.gtlitecore.common.block.adapter

import gregtechlite.gtlitecore.api.block.variant.BlockVariant
import gregtechlite.gtlitecore.common.block.GTLiteMetaBlocks
import gregtechlite.gtlitecore.common.block.base.GTLiteStoneVariantBlock
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack

enum class SmoothStoneBlock : BlockVariant
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
        get() = GTLiteMetaBlocks.STONES[GTLiteStoneVariantBlock.StoneVariant.SMOOTH]!!
            .getState(GTLiteStoneVariantBlock.StoneType.valueOf(this.name))

    override fun getStack(count: Int): ItemStack
    {
        return GTLiteMetaBlocks.STONES[GTLiteStoneVariantBlock.StoneVariant.SMOOTH]!!
            .getItemVariant(GTLiteStoneVariantBlock.StoneType.valueOf(this.name))
    }

}