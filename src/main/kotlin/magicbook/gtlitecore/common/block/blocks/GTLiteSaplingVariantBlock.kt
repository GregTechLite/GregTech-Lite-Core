package magicbook.gtlitecore.common.block.blocks

import magicbook.gtlitecore.api.GTLiteAPI
import magicbook.gtlitecore.api.block.IBlockTranslatable
import magicbook.gtlitecore.api.utils.GTLiteLog
import magicbook.gtlitecore.api.utils.GTLiteValues
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks
import magicbook.gtlitecore.common.worldgen.trees.AbstractTree
import net.minecraft.block.BlockBush
import net.minecraft.block.BlockSapling
import net.minecraft.block.IGrowable
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.properties.PropertyInteger
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraftforge.common.EnumPlantType
import java.util.*

class GTLiteSaplingVariantBlock(private val offset: Int) : BlockBush(Material.LEAVES), IGrowable, IBlockTranslatable
{
    companion object
    {

        private val SAPLING_AABB = AxisAlignedBB(0.1, 0.0, 0.1, 0.9, 0.8, 0.9)

        val VARIANT: PropertyInteger = PropertyInteger.create("variant", 0, 7)

    }

    init
    {
        this.setTranslationKey("gtlite_sapling_$offset")
        this.setTickRandomly(true)
        this.setHardness(0.0f)
        this.setLightOpacity(1)
        this.setSoundType(SoundType.PLANT)
        this.setCreativeTab(GTLiteAPI.TAB_GTLITE_DECORATION)
        // Add to SAPLINGS pool.
        GTLiteMetaBlocks.SAPLINGS.add(this)
    }

    fun getTreeFromState(blockState: IBlockState): AbstractTree
        = AbstractTree.trees[blockState.getValue(VARIANT) + (this.offset * 8)]

    override fun createBlockState(): BlockStateContainer = BlockStateContainer(this, BlockSapling.STAGE, VARIANT)

    @Deprecated("Deprecated in Java")
    override fun getStateFromMeta(meta: Int): IBlockState = this.defaultState.withProperty(BlockSapling.STAGE, (meta and 1))
        .withProperty(VARIANT, (meta and 14) shr 1)

    override fun getMetaFromState(blockState: IBlockState): Int
    {
        var meta = 0
        meta = meta or blockState.getValue(BlockSapling.STAGE)
        meta = meta or (blockState.getValue(VARIANT) shl 1)
        return meta
    }

    override fun getSubBlocks(itemIn: CreativeTabs,
                              items: NonNullList<ItemStack>)
    {
        for (i in 0..7)
        {
            if (AbstractTree.trees.size <= i + this.offset * 8)
                break
            items.add(ItemStack(this, 1, i * 2))
        }
    }

    override fun getTranslation(blockState: IBlockState): String
    {
        try
        {
            return "${GTLiteValues.MODID}.sapling." + getTreeFromState(blockState).name
        }
        catch (exception: IndexOutOfBoundsException)
        {
            GTLiteLog.logger.debug(exception)
            return "${GTLiteValues.MODID}.sapling.error"
        }
    }

    @Deprecated("Deprecated in Java")
    override fun getBoundingBox(blockState: IBlockState, blockSource: IBlockAccess,
                                blockPos: BlockPos): AxisAlignedBB = SAPLING_AABB

    override fun canGrow(worldIn: World, blockPos: BlockPos, blockState: IBlockState,
                         isClient: Boolean): Boolean = true

    override fun canUseBonemeal(worldIn: World, random: Random, blockPos: BlockPos,
                                blockState: IBlockState): Boolean = true

    override fun canBeReplacedByLeaves(blockState: IBlockState, worldIn: IBlockAccess,
                                       blockPos: BlockPos): Boolean = true

    override fun grow(worldIn: World, random: Random, blockPos: BlockPos,
                      blockState: IBlockState)
    {
        getTreeFromState(blockState).treeGrowInstance
            .generate(worldIn, random, blockPos)
    }

    override fun getPlantType(worldIn: IBlockAccess,
                              blockPos: BlockPos): EnumPlantType = EnumPlantType.Plains

    override fun damageDropped(blockState: IBlockState): Int = blockState.getValue(VARIANT) shl 1

    override fun updateTick(worldIn: World, blockPos: BlockPos, blockState: IBlockState,
                            random: Random)
    {
        if (!worldIn.isRemote)
        {
            super.updateTick(worldIn, blockPos, blockState, random)
            // Short-circuit the rest of this (BlockSapling).
            if (random.nextInt(7) != 0)
                return
            // Forge: prevent loading unloaded chunks when checking neighbor's light.
            if (!worldIn.isAreaLoaded(blockPos, 1))
                return
            if (worldIn.getLightFromNeighbors(blockPos.up()) >= 9)
            {
                this.grow(worldIn, random, blockPos, blockState)
            }
        }
    }

}