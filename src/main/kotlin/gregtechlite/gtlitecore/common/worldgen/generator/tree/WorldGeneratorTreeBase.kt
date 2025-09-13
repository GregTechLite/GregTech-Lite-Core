package gregtechlite.gtlitecore.common.worldgen.generator.tree

import gregtechlite.gtlitecore.api.extension.copy
import gregtechlite.gtlitecore.api.worldgen.generator.AbstractWorldGenerator
import gregtechlite.gtlitecore.api.worldgen.generator.CustomWorldGeneratorImpl
import gregtechlite.gtlitecore.common.block.GTLiteBlocks
import gregtechlite.gtlitecore.common.worldgen.generator.custom.CustomWorldGeneratorTree
import net.minecraft.block.Block
import net.minecraft.block.BlockLeaves
import net.minecraft.block.BlockLog
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraftforge.common.IPlantable
import java.util.Random
import kotlin.math.abs

abstract class WorldGeneratorTreeBase(val name: String, private val seed: Int) : AbstractWorldGenerator(seed)
{

    // region Construct and Generation contents

    var logState: IBlockState? = null
    var leaveState: IBlockState? = null
    var saplingState: IBlockState? = null

    /**
     * The natural generating leave block state, the value is based on [leaveState] but be decayable.
     * Updated with [gregtechlite.magicbook.util.LeafDecayUpdate].
     */
    open val placedLeaveState: IBlockState
        get() = this.leaveState!!
            .withProperty(BlockLeaves.DECAYABLE, true)
            .withProperty(BlockLeaves.CHECK_DECAY, true)

    /**
     * The natural generating sapling block, this block is used for placed a tree for [net.minecraftforge.common.IPlantable] interface.
     */
    open val placedSaplingBlock: IPlantable
        get() = this.saplingState!!.block as IPlantable

    // endregion

    // region Correspondenced Generators

    override var innerGenerator: CustomWorldGeneratorImpl?
        get() = CustomWorldGeneratorTree(false, this)
        set(value)
        {
            innerGenerator = value
        }

    var outerGenerator: CustomWorldGeneratorImpl?
        get() = CustomWorldGeneratorTree(true, this)
        set(value)
        {
            outerGenerator = value
        }

    // endregion

    init
    {
        WorldGeneratorTreeRegistry.generators.add(this)
    }

    abstract fun getItemColor(stack: ItemStack?, tintIndex: Int): Int

    abstract fun getBlockColor(blockState: IBlockState?,
                               worldIn: IBlockAccess?,
                               blockPos: BlockPos?,
                               tintIndex: Int): Int

    override fun generate(worldIn: World?,
                          blockPos: BlockPos.MutableBlockPos?,
                          rand: Random?,
                          notifier: (World?, BlockPos?, IBlockState?) -> Unit): Boolean
    {
        val minHeight = rand?.let { getMinTrunkHeight(it) }
        if (blockPos!!.y >= 1 && blockPos.y + minHeight!! + 1 <= worldIn!!.height)
        {
            if (isSuitableLocation(worldIn, blockPos, minHeight))
            {
                val blockState = worldIn.getBlockState(blockPos.down())
                val block = blockState.block
                if (block.canSustainPlant(blockState, worldIn, blockPos.down(),
                       EnumFacing.UP, this.placedSaplingBlock)
                    && blockPos.y < worldIn.height - minHeight - 1)
                {
                    block.onPlantGrow(blockState, worldIn, blockPos.down(), blockPos)
                    generateLeaves(worldIn, blockPos, minHeight, rand, notifier)
                    generateTrunk(worldIn, blockPos, minHeight, rand, notifier)
                    return true
                }
            }
        }
        return false
    }

    protected open fun generateLeaves(worldIn: World,
                                      blockPos: BlockPos.MutableBlockPos,
                                      height: Int,
                                      rand: Random,
                                      notifier: (World?, BlockPos?, IBlockState?) -> Unit, )
    {
        for (foliageY in blockPos.y - 3 + height .. blockPos.y + height)
        {
            val foliageLayer = foliageY - (blockPos.y + height)
            val foliageLayerRadius = 1 - foliageLayer / 2
            for (foliageX in blockPos.x - foliageLayerRadius .. blockPos.x + foliageLayerRadius)
            {
                val foliageRelativeX = foliageX - blockPos.x
                for (foliageZ in blockPos.z - foliageLayerRadius .. blockPos.z + foliageLayerRadius)
                {
                    val foliageRelativeZ = foliageZ - blockPos.z
                    if (abs(foliageRelativeX) != foliageLayerRadius
                        || abs(foliageRelativeZ) != foliageLayerRadius
                        || rand.nextInt(2) != 0
                        && foliageLayer != 0)
                    {
                        val leavesBlockPos = BlockPos(foliageX, foliageY, foliageZ)
                        val leavesBlockState = worldIn.getBlockState(leavesBlockPos)
                        val leavesBlock = leavesBlockState.block

                        if (leavesBlock.isReplaceable(worldIn, blockPos)
                            || leavesBlock.canBeReplacedByLeaves(leavesBlockState, worldIn, blockPos))
                        {
                            notifier(worldIn, leavesBlockPos, this.leaveState)
                        }
                    }
                }
            }
        }
    }

    protected open fun generateTrunk(worldIn: World,
                                     blockPos: BlockPos.MutableBlockPos,
                                     maxHeight: Int,
                                     rand: Random?,
                                     notifier: (World?, BlockPos?, IBlockState?) -> Unit)
    {
        val upNBlockPos = blockPos.copy()
        for (height in 0 ..< maxHeight)
        {
            val blockState = worldIn.getBlockState(upNBlockPos)
            val block = blockState.block
            if (block.isAir(blockState, worldIn, upNBlockPos)
                || block.isLeaves(blockState, worldIn, upNBlockPos))
            {
                notifier(worldIn, blockPos.up(height), this.logState!!
                    .withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.Y))
            }
            upNBlockPos.move(EnumFacing.UP)
        }
    }

    @Suppress("Deprecation")
    fun setupBlocks()
    {
        val leaves = GTLiteBlocks.LEAVES[this.seed / 4]
        this.leaveState = leaves.getStateFromMeta(this.seed % 4 shl 2)

        val logs = GTLiteBlocks.LOGS[this.seed / 4]
        this.logState = logs.getStateFromMeta(this.seed % 4 shl 2)

        val saplings = GTLiteBlocks.SAPLINGS[this.seed / 8]
        this.saplingState = saplings.getStateFromMeta(this.seed % 8 shl 1)
    }

    fun isReplaceable(worldIn: World, blockPos: BlockPos): Boolean
    {
        return canGrowInto(worldIn.getBlockState(blockPos).getBlock())
    }

    protected fun canGrowInto(block: Block): Boolean
    {
        val material = block.defaultState.getMaterial()
        return material === Material.AIR || material === Material.LEAVES
                || block === Blocks.GRASS || block === Blocks.DIRT
                || block === Blocks.LOG || block === Blocks.LOG2
                || block === Blocks.SAPLING || block === Blocks.VINE
    }

    protected fun isSuitableLocation(worldIn: World, blockPos: BlockPos, minHeight: Int): Boolean
    {
        for (height in 0 .. 1 + minHeight)
        {
            val extraSpaceNeeded = getMooreRadiusAtHeight(height, minHeight)
            val pos = BlockPos.MutableBlockPos()
            for (checkX in blockPos.x - extraSpaceNeeded .. blockPos.x + extraSpaceNeeded)
            {
                for (checkZ in blockPos.z - extraSpaceNeeded .. blockPos.z + extraSpaceNeeded)
                {
                    if (!isReplaceable(worldIn, pos.setPos(checkX, height + blockPos.y, checkZ)))
                        return false
                }
            }
        }
        return true
    }

    open fun getMinTrunkHeight(random: Random): Int = random.nextInt(3) + 5

    /**
     * @param height      The block height at which this radius is being taken (starting from 0).
     * @param trunkHeight The height of the trunk.
     * @return            The maximum radius outside the center block that the tree can take up at this height value.
     */
    protected open fun getMooreRadiusAtHeight(height: Int, trunkHeight: Int): Int = 0

    open fun getFruitDrop(chance: Int): ItemStack? = ItemStack.EMPTY

}