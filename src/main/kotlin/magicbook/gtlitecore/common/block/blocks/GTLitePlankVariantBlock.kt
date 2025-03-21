package magicbook.gtlitecore.common.block.blocks

import magicbook.gtlitecore.api.GTLiteAPI
import magicbook.gtlitecore.api.block.IBlockTranslatable
import magicbook.gtlitecore.api.utils.GTLiteValues
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks
import magicbook.gtlitecore.common.worldgen.trees.AbstractTree
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.block.properties.PropertyInteger
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList

class GTLitePlankVariantBlock(private val offset: Int) : Block(Material.WOOD), IBlockTranslatable
{

    companion object
    {

        val VARIANT: PropertyInteger = PropertyInteger.create("variant", 0, 15)

    }

    init
    {
        this.setTranslationKey("gtlite_planks_$offset")
        this.setHardness(2.0f)
        this.setResistance(5.0f)
        this.setHarvestLevel("axe", 0)
        this.setCreativeTab(GTLiteAPI.TAB_GTLITE_DECORATION)
        // Add to PLANKS pool.
        GTLiteMetaBlocks.PLANKS.add(this)
    }

    fun getTreeFromState(blockState: IBlockState): AbstractTree
        = AbstractTree.trees[blockState.getValue(VARIANT) + (this.offset * 16)]

    override fun getMetaFromState(blockState: IBlockState): Int = blockState.getValue(VARIANT)

    @Deprecated("Deprecated in Java")
    override fun getStateFromMeta(meta: Int): IBlockState = this.defaultState.withProperty(VARIANT, meta)

    override fun createBlockState(): BlockStateContainer = BlockStateContainer(this, VARIANT)

    override fun getSubBlocks(itemIn: CreativeTabs,
                              items: NonNullList<ItemStack>)
    {
        for (i in 0..15)
        {
            if (AbstractTree.trees.size <= i + this.offset * 16)
                break
            items.add(ItemStack(this, 1, i))
        }
    }

    override fun damageDropped(blockState: IBlockState): Int = this.getMetaFromState(blockState)

    override fun getTranslation(blockState: IBlockState): String
    {
        try
        {
            return "${GTLiteValues.MODID}.planks." + getTreeFromState(blockState).name
        }
        catch (exception: IndexOutOfBoundsException)
        {
            return "${GTLiteValues.MODID}.planks.error"
        }
    }

}