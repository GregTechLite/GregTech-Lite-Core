package magicbook.gtlitecore.common.block.blocks

import magicbook.gtlitecore.api.GTLiteAPI
import magicbook.gtlitecore.api.block.GTLiteWoodType
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks
import net.minecraft.block.BlockSlab
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.properties.IProperty
import net.minecraft.block.properties.PropertyEnum
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraft.util.NonNullList
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import java.util.*

abstract class GTLiteWoodSlabVariantBlock : BlockSlab(Material.WOOD)
{

    companion object
    {

        val VARIANT: PropertyEnum<GTLiteWoodType> = PropertyEnum.create("variant", GTLiteWoodType::class.java)

    }

    init
    {
        this.setTranslationKey("gtlite_wood_slab")
        this.setHardness(2.0F)
        this.setResistance(5.0F)
        this.setSoundType(SoundType.WOOD)
        this.setHarvestLevel("axe", 0)
        this.setCreativeTab(GTLiteAPI.TAB_GTLITE_DECORATION)
        this.useNeighborBrightness = true
    }

    override fun getVariantProperty(): IProperty<*> = VARIANT

    override fun damageDropped(state: IBlockState): Int = state.getValue(VARIANT).ordinal

    override fun getItemDropped(state: IBlockState, random: Random, fortune: Int): Item
        = Item.getItemFromBlock(GTLiteMetaBlocks.WOOD_SLABS)

    override fun getTranslationKey(meta: Int): String
        = "${super.getTranslationKey()}.${getTypeFromMeta(meta).name}".lowercase(Locale.getDefault())

    override fun getTypeForItem(stack: ItemStack): Comparable<*>
        = getTypeFromMeta(stack.metadata)

    fun getTypeFromMeta(meta: Int): GTLiteWoodType
        = GTLiteWoodType.entries.toTypedArray()
            .getOrElse(meta and 0xF) { GTLiteWoodType.BANANA }

    override fun getSubBlocks(itemIn: CreativeTabs, items: NonNullList<ItemStack>)
        = GTLiteWoodType.entries.forEach { t ->
            items.add(ItemStack(this, 1, t.ordinal)) }

    class Double : GTLiteWoodSlabVariantBlock()
    {

        init
        {
            this.defaultState = blockState.baseState
                .withProperty(VARIANT, GTLiteWoodType.BANANA)
        }

        override fun isDouble(): Boolean = true

        @Deprecated("Deprecated in Java")
        override fun getStateFromMeta(meta: Int): IBlockState
            = this.defaultState.withProperty(VARIANT, getTypeFromMeta(meta))

        override fun getMetaFromState(state: IBlockState)
            = state.getValue(VARIANT).ordinal

        override fun createBlockState(): BlockStateContainer
            = BlockStateContainer(this, VARIANT)

    }

    class Half : GTLiteWoodSlabVariantBlock()
    {

        init
        {
            this.defaultState = blockState.baseState
                .withProperty(HALF, EnumBlockHalf.BOTTOM)
                .withProperty(VARIANT, GTLiteWoodType.BANANA)
        }

        override fun isDouble(): Boolean = false

        @Deprecated("Deprecated in Java")
        override fun getStateFromMeta(meta: Int): IBlockState
            = this.defaultState.withProperty(VARIANT, getTypeFromMeta(meta))
                    .withProperty(HALF, if (meta and 8 == 0) EnumBlockHalf.BOTTOM else EnumBlockHalf.TOP)

        override fun getMetaFromState(state: IBlockState): Int
        {
            var meta = state.getValue(VARIANT).ordinal
            if (state.getValue(HALF) == EnumBlockHalf.TOP)
                meta = meta or 8
            return meta
        }

        override fun createBlockState(): BlockStateContainer
            = BlockStateContainer(this, HALF, VARIANT)

        override fun doesSideBlockChestOpening(blockState: IBlockState, world: IBlockAccess,
                                               pos: BlockPos, side: EnumFacing): Boolean = false

    }

}