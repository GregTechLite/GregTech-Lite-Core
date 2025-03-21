package magicbook.gtlitecore.common.block.blocks

import com.google.common.collect.Lists
import gregtech.core.CoreModule
import magicbook.gtlitecore.api.GTLiteAPI
import magicbook.gtlitecore.api.block.IBlockTranslatable
import magicbook.gtlitecore.api.utils.GTLiteLog
import magicbook.gtlitecore.api.utils.GTLiteValues
import magicbook.gtlitecore.api.utils.LeafDecayHelper
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks
import magicbook.gtlitecore.common.worldgen.trees.AbstractTree
import net.minecraft.block.BlockLeaves
import net.minecraft.block.BlockPlanks
import net.minecraft.block.properties.PropertyInteger
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.client.Minecraft
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.BlockRenderLayer
import net.minecraft.util.EnumFacing
import net.minecraft.util.NonNullList
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import java.util.*

class GTLiteLeaveVariantBlock(private val offset: Int) : BlockLeaves(), IBlockTranslatable
{

    companion object
    {

        val VARIANT: PropertyInteger = PropertyInteger.create("variant", 0, 3)

    }

    init
    {
        this.setTranslationKey("gtlite_leaves_$offset")
        this.setHardness(0.2f)
        this.setLightOpacity(1)
        this.defaultState = blockState.baseState
            .withProperty(CHECK_DECAY, true)
            .withProperty(DECAYABLE, true)
            .withProperty(VARIANT, 0)
        this.setCreativeTab(GTLiteAPI.TAB_GTLITE_DECORATION)
        // Add to LEAVES pool.
        GTLiteMetaBlocks.LEAVES.add(this)
    }

    fun getTreeFromState(blockState: IBlockState): AbstractTree
            = AbstractTree.trees[blockState.getValue(VARIANT) + (this.offset * 4)]

    @SideOnly(Side.CLIENT)
    fun registerColors()
    {
        Minecraft.getMinecraft().blockColors.registerBlockColorHandler(
            { blockState: IBlockState, worldIn: IBlockAccess?, blockPos: BlockPos?, tintIndex: Int ->
                getTreeFromState(blockState)
                    .getBlockColor(blockState, worldIn, blockPos, tintIndex)
            }, this
        )
        Minecraft.getMinecraft().itemColors.registerItemColorHandler(
            { stack: ItemStack, tintIndex: Int ->
                getTreeFromState(this.getStateFromMeta(stack.itemDamage))
                    .getItemColor(stack, tintIndex)
            }, this
        )
    }

    @Deprecated("Deprecated in Java")
    override fun getStateFromMeta(meta: Int): IBlockState = this.defaultState
        .withProperty(DECAYABLE, (meta and 1) == 1)
        .withProperty(CHECK_DECAY, (meta and 2) == 2)
        .withProperty(VARIANT, (meta and 12) shr 2)

    override fun getMetaFromState(blockState: IBlockState): Int
    {
        var meta = 0
        if (blockState.getValue(DECAYABLE))
            meta = meta or 1
        if (blockState.getValue(CHECK_DECAY))
            meta = meta or 2
        meta = meta or (blockState.getValue(VARIANT) shl 2)
        return meta
    }

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

    override fun getWoodType(meta: Int): BlockPlanks.EnumType? = null

    override fun getItemDropped(blockState: IBlockState, random: Random, fortune: Int): Item
        = Item.getItemFromBlock(getTreeFromState(blockState).saplingState.block)

    override fun onSheared(stack: ItemStack, worldIn: IBlockAccess,
                           blockPos: BlockPos, fortune: Int): List<ItemStack>
        = Lists.newArrayList(ItemStack(this, 1, this.getMetaFromState(worldIn.getBlockState(blockPos))))

    override fun createBlockState(): BlockStateContainer
        = BlockStateContainer(this, DECAYABLE, CHECK_DECAY, VARIANT)

    override fun getTranslation(blockState: IBlockState): String
    {
        try
        {
            return "${GTLiteValues.MODID}.leaves." + getTreeFromState(blockState).name
        }
        catch (exception: IndexOutOfBoundsException)
        {
            GTLiteLog.logger.debug(exception)
            return "${GTLiteValues.MODID}.leaves.error"
        }
    }

    override fun getRenderLayer(): BlockRenderLayer
    {
        if (!isFancyGraphics())
            return super.getRenderLayer()
        return BlockRenderLayer.CUTOUT_MIPPED
    }

    @Deprecated("Deprecated in Java")
    override fun isOpaqueCube(blockState: IBlockState): Boolean
    {
        if (!isFancyGraphics())
            return super.isOpaqueCube(blockState)
        return false
    }

    @Deprecated("Deprecated in Java")
    override fun shouldSideBeRendered(blockState: IBlockState, blockAccess: IBlockAccess,
                                      blockPos: BlockPos, side: EnumFacing): Boolean
    {
        if (!isFancyGraphics())
            return super.shouldSideBeRendered(blockState, blockAccess, blockPos, side)
        return true
    }

    override fun dropApple(worldIn: World, blockPos: BlockPos, blockState: IBlockState, chance: Int)
        = spawnAsEntity(worldIn, blockPos, (blockState.block as GTLiteLeaveVariantBlock)
            .getTreeFromState(blockState).getAppleDrop(chance))

    override fun damageDropped(blockState: IBlockState): Int
            = (blockState.getValue(VARIANT) shl 1) + ((this.offset % 2) * 8)

    @Deprecated("Deprecated in Java")
    override fun getItem(worldIn: World, blockPos: BlockPos, blockState: IBlockState): ItemStack
        = ItemStack(Item.getItemFromBlock(this), 1,
        blockState.getValue(VARIANT) shl 2)

    override fun updateTick(worldIn: World, blockPos: BlockPos,
                            blockState: IBlockState, random: Random)
        = LeafDecayHelper.leafDecay(this, worldIn, blockPos)

    private fun isFancyGraphics(): Boolean = CoreModule.proxy.isFancyGraphics

}