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
import java.util.*
import kotlin.math.abs

abstract class WorldGeneratorTreeBase(val name: String, private val seed: Int) : AbstractWorldGenerator(seed)
{
    lateinit var logState: IBlockState
    lateinit var leaveState: IBlockState
    lateinit var saplingState: IBlockState

    open val placedLeaveState: IBlockState
        get() = leaveState.withProperty(BlockLeaves.DECAYABLE, true).withProperty(BlockLeaves.CHECK_DECAY, true)

    open val placedSaplingBlock: IPlantable
        get() = saplingState.block as IPlantable

    override var innerGenerator: CustomWorldGeneratorImpl?
        get() = CustomWorldGeneratorTree(false, this)
        set(newGenerator)
        {
            innerGenerator = newGenerator
        }

    var outerGenerator: CustomWorldGeneratorImpl?
        get() = CustomWorldGeneratorTree(true, this)
        set(newGenerator)
        {
            outerGenerator = newGenerator
        }

    init
    {
        WorldGeneratorTreeRegistry.add(this)
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
                if (block.canSustainPlant(blockState, worldIn, blockPos.down(), EnumFacing.UP, placedSaplingBlock)
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
                            notifier(worldIn, leavesBlockPos, leaveState)
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
        for (height in 0 until maxHeight)
        {
            val blockState = worldIn.getBlockState(upNBlockPos)
            val block = blockState.block
            if (block.isAir(blockState, worldIn, upNBlockPos) || block.isLeaves(blockState, worldIn, upNBlockPos))
            {
                notifier(worldIn, blockPos.up(height), logState.withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.Y))
            }
            upNBlockPos.move(EnumFacing.UP)
        }
    }

    @Suppress("Deprecation")
    internal fun setupBlocks()
    {
        val leaves = GTLiteBlocks.LEAVES[seed / 4]
        leaveState = leaves.getStateFromMeta(seed % 4 shl 2)

        val logs = GTLiteBlocks.LOGS[seed / 4]
        logState = logs.getStateFromMeta(seed % 4 shl 2)

        val saplings = GTLiteBlocks.SAPLINGS[seed / 8]
        saplingState = saplings.getStateFromMeta(seed % 8 shl 1)
    }

    private fun isReplaceable(worldIn: World, blockPos: BlockPos): Boolean
        = canGrowInto(worldIn.getBlockState(blockPos).block)

    protected fun canGrowInto(block: Block): Boolean
    {
        val material = block.defaultState.material
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

    open fun getMinTrunkHeight(rand: Random): Int = rand.nextInt(3) + 5

    /**
     * Sets the Moore radius at the height value which the tree can take up at.
     *
     * @param height      The block height at which this radius is being taken (starting from 0).
     * @param trunkHeight The height of the trunk.
     * @return            The maximum radius outside the center block that the tree can take up at this height value.
     */
    protected open fun getMooreRadiusAtHeight(height: Int, trunkHeight: Int): Int = 0

    /**
     * Sets the chanced drop items for the tree.
     *
     * @param chance The base chance of the drop items dependent.
     * @return       The item with stack format, or [ItemStack.EMPTY] by default.
     */
    open fun getFruitDrop(chance: Int): ItemStack = ItemStack.EMPTY
}