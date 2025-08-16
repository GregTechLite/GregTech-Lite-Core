package gregtechlite.gtlitecore.common.worldgen.trees

import gregtechlite.gtlitecore.api.extension.copy
import gregtechlite.gtlitecore.api.worldgen.AbstractWorldGenerator
import gregtechlite.gtlitecore.api.worldgen.feature.AbstractFeature
import gregtechlite.gtlitecore.common.block.GTLiteBlocks
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
import net.minecraftforge.fluids.Fluid
import java.util.*
import kotlin.math.abs

abstract class AbstractTree(val name: String?, private val seed: Int) : AbstractFeature(seed)
{

    var logState: IBlockState? = null
    var leaveState: IBlockState? = null
    var saplingState: IBlockState? = null

    open val apple: ItemStack?
        // Drop item stack.
        get() = ItemStack.EMPTY

    open val treeSap: Fluid?
        // Get tree sap like maple sugar in maple tree!
        get() = null

    val sapling: ItemStack
        // Get sapling item stack.
        get() = ItemStack(GTLiteBlocks.SAPLINGS[this.seed / 8], 1, (this.seed % 8) shl 1)

    protected val naturalLeaveState: IBlockState
        get() = this.leaveState!!.withProperty(BlockLeaves.DECAYABLE, true)
            .withProperty(BlockLeaves.CHECK_DECAY, true)

    val treeGrowInstance: AbstractWorldGenerator?
        get() = featureGrowInstance

    val plantableSapling: IPlantable
        get() = this.saplingState!!.getBlock() as IPlantable

    companion object
    {

        val trees = ArrayList<AbstractTree?>()
    }

    init
    {
        featureGrowInstance = WorldGeneratorTree(true, this)
        worldGenInstance = WorldGeneratorTree(false, this)

        trees.add(this)
    }

    abstract fun getItemColor(stack: ItemStack?, tintIndex: Int): Int

    abstract fun getBlockColor(blockState: IBlockState?,
                               worldIn: IBlockAccess?,
                               blockPos: BlockPos?,
                               tintIndex: Int): Int

    override fun generate(worldIn: World?,
                          blockPos: BlockPos.MutableBlockPos?,
                          random: Random?,
                          notifier: (World?, BlockPos?, IBlockState?) -> Unit): Boolean
    {
        val minHeight = random?.let { getMinTrunkHeight(it) }
        if (blockPos!!.getY() >= 1 && blockPos.getY() + minHeight!! + 1 <= worldIn!!.height)
        {
            if (isSuitableLocation(worldIn, blockPos, minHeight))
            {
                val blockState = worldIn.getBlockState(blockPos.down())
                if (blockState.getBlock().canSustainPlant(blockState, worldIn, blockPos.down(),
                        EnumFacing.UP, this.plantableSapling)
                    && blockPos.getY() < worldIn.height - minHeight - 1)
                {
                    blockState.getBlock().onPlantGrow(blockState, worldIn,
                        blockPos.down(), blockPos)
                    generateLeaves(worldIn, blockPos, minHeight, random, notifier)
                    generateTrunk(worldIn, blockPos, minHeight, random, notifier)
                    return true
                }
            }
        }
        return false
    }

    protected open fun generateLeaves(worldIn: World,
                                      blockPos: BlockPos.MutableBlockPos,
                                      height: Int,
                                      random: Random,
                                      notifier: (World?, BlockPos?, IBlockState?) -> Unit, )
    {
        for (foliageY in blockPos.getY() - 3 + height .. blockPos.getY() + height)
        {
            val foliageLayer = foliageY - (blockPos.getY() + height)
            val foliageLayerRadius = 1 - foliageLayer / 2
            for (foliageX in blockPos.getX() - foliageLayerRadius .. blockPos.getX() + foliageLayerRadius)
            {
                val foliageRelativeX = foliageX - blockPos.getX()
                for (foliageZ in blockPos.getZ() - foliageLayerRadius .. blockPos.getZ() + foliageLayerRadius)
                {
                    val foliageRelativeZ = foliageZ - blockPos.getZ()
                    // Fill in layer with some randomness.
                    if (abs(foliageRelativeX) != foliageLayerRadius
                        || abs(foliageRelativeZ) != foliageLayerRadius
                        || random.nextInt(2) != 0
                        && foliageLayer != 0)
                    {
                        val leavesBlockPos = BlockPos(foliageX, foliageY, foliageZ)
                        val leavesBlockState = worldIn.getBlockState(leavesBlockPos)

                        if (leavesBlockState.getBlock().isReplaceable(worldIn, blockPos)
                            || leavesBlockState.getBlock().canBeReplacedByLeaves(leavesBlockState, worldIn, blockPos)
                        )
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
                                     random: Random?,
                                     notifier: (World?, BlockPos?, IBlockState?) -> Unit)
    {
        val upNBlockPos = blockPos.copy()
        for (height in 0 ..< maxHeight)
        {
            val blockState = worldIn.getBlockState(upNBlockPos)
            if (blockState.getBlock().isAir(blockState, worldIn, upNBlockPos)
                || blockState.getBlock().isLeaves(blockState, worldIn, upNBlockPos))
            {
                notifier(worldIn, blockPos.up(height), this.logState!!.withProperty(
                    BlockLog.LOG_AXIS, BlockLog.EnumAxis.Y))
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
            // Handles increasing space towards top of tree.
            val extraSpaceNeeded = getMooreRadiusAtHeight(height, minHeight)
            val tBlockPos = BlockPos.MutableBlockPos()
            for (checkX in blockPos.x - extraSpaceNeeded .. blockPos.x + extraSpaceNeeded)
            {
                for (checkZ in blockPos.z - extraSpaceNeeded .. blockPos.z + extraSpaceNeeded)
                {
                    if (!isReplaceable(worldIn, tBlockPos.setPos(checkX, height + blockPos.y, checkZ)))
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
     * @return The maximum radius outside the center block that the
     * tree can take up at this height value.
     */
    protected open fun getMooreRadiusAtHeight(height: Int, trunkHeight: Int): Int = 0

    // Drop items like apple of a tree with chance.
    open fun getAppleDrop(chance: Int): ItemStack? = ItemStack.EMPTY

}
