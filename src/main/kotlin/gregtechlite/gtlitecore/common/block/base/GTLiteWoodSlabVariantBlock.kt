package gregtechlite.gtlitecore.common.block.base

import gregtechlite.gtlitecore.common.block.variant.WoodType
import gregtechlite.gtlitecore.common.block.GTLiteMetaBlocks
import gregtechlite.gtlitecore.common.creativetabs.GTLiteCreativeTabs
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

        val VARIANT: PropertyEnum<WoodType> = PropertyEnum.create("variant", WoodType::class.java)

    }

    init
    {
        this.setTranslationKey("gtlite_wood_slab")
        this.setHardness(2.0F)
        this.setResistance(5.0F)
        this.setSoundType(SoundType.WOOD)
        this.setHarvestLevel("axe", 0)
        this.setCreativeTab(GTLiteCreativeTabs.TAB_DECORATION)
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

    fun getTypeFromMeta(meta: Int): WoodType
        = WoodType.entries.toTypedArray()
            .getOrElse(meta and 0xF) { WoodType.BANANA }

    override fun getSubBlocks(itemIn: CreativeTabs, items: NonNullList<ItemStack>)
        = WoodType.entries.forEach { t ->
            items.add(ItemStack(this, 1, t.ordinal)) }

    class Double : GTLiteWoodSlabVariantBlock()
    {

        init
        {
            this.defaultState = blockState.baseState
                .withProperty(VARIANT, WoodType.BANANA)
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
                .withProperty(VARIANT, WoodType.BANANA)
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