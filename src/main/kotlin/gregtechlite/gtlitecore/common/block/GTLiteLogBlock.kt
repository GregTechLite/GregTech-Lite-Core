package gregtechlite.gtlitecore.common.block

import gregtechlite.gtlitecore.api.GTLiteLog
import gregtechlite.gtlitecore.api.MOD_ID
import gregtechlite.gtlitecore.api.block.TranslatableBlock
import gregtechlite.gtlitecore.common.creativetabs.GTLiteCreativeTabs
import gregtechlite.gtlitecore.common.worldgen.generator.tree.WorldGeneratorTreeBase
import gregtechlite.gtlitecore.common.worldgen.generator.tree.WorldGeneratorTreeRegistry
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
        setTranslationKey("gtlitecore.log_$offset")
        setHarvestLevel("axe", 0)
        defaultState = blockState.baseState
            .withProperty(LOG_AXIS, EnumAxis.Y)
        setCreativeTab(GTLiteCreativeTabs.TAB_DECORATION)

        GTLiteBlocks.LOGS.add(this)
    }

    fun getTreeFromState(blockState: IBlockState): WorldGeneratorTreeBase
        = WorldGeneratorTreeRegistry.generators[blockState.getValue(VARIANT) + (offset * 4)]

    override fun getMetaFromState(blockState: IBlockState): Int
    {
        var meta = 0
        meta = meta or blockState.getValue(LOG_AXIS).ordinal
        meta = meta or (blockState.getValue(VARIANT) shl 2)
        return meta
    }

    @Deprecated("Deprecated in Java")
    override fun getStateFromMeta(meta: Int): IBlockState = defaultState
        .withProperty(LOG_AXIS, EnumAxis.entries[meta and 3])
        .withProperty(VARIANT, (meta and 12) shr 2)

    override fun createBlockState(): BlockStateContainer = BlockStateContainer(this, LOG_AXIS, VARIANT)

    override fun getSubBlocks(itemIn: CreativeTabs, items: NonNullList<ItemStack>)
    {
        for (i in 0..3)
        {
            if (WorldGeneratorTreeRegistry.generators.size <= i + offset * 4)
                break
            items.add(ItemStack(this, 1, i shl 2))
        }
    }

    override fun damageDropped(blockState: IBlockState): Int = blockState.getValue(VARIANT) shl 2

    override fun getTranslation(blockState: IBlockState): String = runCatching {
        "${MOD_ID}.log.${getTreeFromState(blockState).name}"
    }.getOrElse {
        GTLiteLog.logger.warn("Found some incorrect log block state '$blockState'")
        "${MOD_ID}.log.error"
    }
}