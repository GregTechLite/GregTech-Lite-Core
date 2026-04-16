package gregtechlite.gtlitecore.common.block

import gregtechlite.gtlitecore.api.GTLiteLog
import gregtechlite.gtlitecore.api.MOD_ID
import gregtechlite.gtlitecore.api.block.TranslatableBlock
import gregtechlite.gtlitecore.common.creativetabs.GTLiteCreativeTabs
import gregtechlite.gtlitecore.common.worldgen.generator.tree.WorldGeneratorTreeBase
import gregtechlite.gtlitecore.common.worldgen.generator.tree.WorldGeneratorTreeRegistry
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.block.properties.PropertyInteger
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList

class GTLitePlankBlock(private val offset: Int) : Block(Material.WOOD), TranslatableBlock
{

    companion object
    {
        val VARIANT: PropertyInteger = PropertyInteger.create("variant", 0, 15)
    }

    init
    {
        setTranslationKey("gtlitecore.planks_$offset")
        setHardness(2.0f)
        setResistance(5.0f)
        setHarvestLevel("axe", 0)
        setCreativeTab(GTLiteCreativeTabs.TAB_DECORATION)

        GTLiteBlocks.PLANKS.add(this)
    }

    fun getTreeFromState(blockState: IBlockState): WorldGeneratorTreeBase
        = WorldGeneratorTreeRegistry.generators[blockState.getValue(VARIANT) + (offset * 16)]

    override fun getMetaFromState(blockState: IBlockState): Int = blockState.getValue(VARIANT)

    @Deprecated("Deprecated in Java")
    override fun getStateFromMeta(meta: Int): IBlockState = defaultState.withProperty(VARIANT, meta)

    override fun createBlockState(): BlockStateContainer = BlockStateContainer(this, VARIANT)

    override fun getSubBlocks(itemIn: CreativeTabs, items: NonNullList<ItemStack>)
    {
        for (i in 0..15)
        {
            if (WorldGeneratorTreeRegistry.generators.size <= i + offset * 16)
                break
            items.add(ItemStack(this, 1, i))
        }
    }

    override fun damageDropped(blockState: IBlockState): Int = getMetaFromState(blockState)

    override fun getTranslation(blockState: IBlockState): String = runCatching {
        "${MOD_ID}.planks.${getTreeFromState(blockState).name}"
    }.getOrElse {
        GTLiteLog.logger.warn("Found some incorrect plank block state '$blockState'")
        "${MOD_ID}.planks.error"
    }
}