package magicbook.gtlitecore.common.block.blocks

import magicbook.gtlitecore.api.GTLiteAPI
import magicbook.gtlitecore.api.block.IBlockTranslatable
import magicbook.gtlitecore.api.utils.GTLiteLog
import magicbook.gtlitecore.api.utils.GTLiteValues
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks
import magicbook.gtlitecore.common.worldgen.trees.AbstractTree
import net.minecraft.block.BlockLog
import net.minecraft.block.properties.PropertyInteger
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList

class GTLiteLogVariantBlock(private val offset: Int) : BlockLog(), IBlockTranslatable
{

    companion object
    {

        val VARIANT: PropertyInteger = PropertyInteger.create("variant", 0, 3)

    }

    init
    {
        this.setTranslationKey("gtlite_log_$offset")
        this.setHarvestLevel("axe", 0)
        this.defaultState = blockState.baseState
            .withProperty(LOG_AXIS, EnumAxis.Y)
        this.setCreativeTab(GTLiteAPI.TAB_GTLITE)
        // Add to LOGS pool.
        GTLiteMetaBlocks.LOGS.add(this)
    }

    fun getTreeFromState(blockState: IBlockState): AbstractTree
            = AbstractTree.trees[blockState.getValue(VARIANT) + (this.offset * 4)]

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

    override fun getSubBlocks(itemIn: CreativeTabs,
                              items: NonNullList<ItemStack>)
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
            return "${GTLiteValues.MODID}.log." + getTreeFromState(blockState).name
        }
        catch (exception: IndexOutOfBoundsException)
        {
            GTLiteLog.logger.debug(exception)
            return "${GTLiteValues.MODID}.log.error"
        }
    }

}