package gregtechlite.gtlitecore.common.block

import gregtechlite.gtlitecore.api.GTLiteLog
import gregtechlite.gtlitecore.api.MOD_ID
import gregtechlite.gtlitecore.api.block.TranslatableBlock
import gregtechlite.gtlitecore.common.creativetabs.GTLiteCreativeTabs
import gregtechlite.gtlitecore.common.worldgen.trees.AbstractTree
import net.minecraft.block.BlockLog
import net.minecraft.block.properties.PropertyInteger
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList

class GTLiteLogBlock(private val offset: Int) : BlockLog(), TranslatableBlock
{

    companion object
    {

        val VARIANT: PropertyInteger = PropertyInteger.create("variant", 0, 3)

    }

    init
    {
        this.setTranslationKey("gtlitecore.log_$offset")
        this.setHarvestLevel("axe", 0)
        this.defaultState = blockState.baseState
            .withProperty(LOG_AXIS, EnumAxis.Y)
        this.setCreativeTab(GTLiteCreativeTabs.TAB_DECORATION)
        // Add to LOGS pool.
        GTLiteBlocks.LOGS.add(this)
    }

    fun getTreeFromState(blockState: IBlockState): AbstractTree =
        AbstractTree.trees[blockState.getValue(VARIANT) + (this.offset * 4)]!!

    override fun getMetaFromState(blockState: IBlockState): Int
    {
        var meta = 0
        meta = meta or blockState.getValue(LOG_AXIS).ordinal
        meta = meta or (blockState.getValue(VARIANT) shl 2)
        return meta
    }

    @OptIn(ExperimentalStdlibApi::class)
    @Deprecated("Deprecated in Java")
    override fun getStateFromMeta(meta: Int): IBlockState = this.defaultState
        .withProperty(LOG_AXIS, EnumAxis.entries[meta and 3])
        .withProperty(VARIANT, (meta and 12) shr 2)

    override fun createBlockState(): BlockStateContainer = BlockStateContainer(this, LOG_AXIS, VARIANT)

    override fun getSubBlocks(
        itemIn: CreativeTabs,
        items: NonNullList<ItemStack>
    )
    {
        for (i in 0..3)
        {
            if (AbstractTree.trees.size <= i + this.offset * 4)
                break
            items.add(ItemStack(this, 1, i shl 2))
        }
    }

    override fun damageDropped(blockState: IBlockState): Int = blockState.getValue(VARIANT) shl 2

    override fun getTranslation(blockState: IBlockState): String
    {
        try
        {
            return "${MOD_ID}.log." + getTreeFromState(blockState).name
        } catch (exception: IndexOutOfBoundsException)
        {
            GTLiteLog.logger.debug(exception)
            return "${MOD_ID}.log.error"
        }
    }

}