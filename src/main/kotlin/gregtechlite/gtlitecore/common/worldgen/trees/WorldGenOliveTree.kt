package gregtechlite.gtlitecore.common.worldgen.trees

import gregtech.api.GTValues
import gregtechlite.gtlitecore.api.extension.copy
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems
import gregtechlite.gtlitecore.common.worldgen.features.BiomeCondition
import gregtechlite.gtlitecore.common.worldgen.features.TemperatureRainfallCondition
import net.minecraft.block.BlockLog
import net.minecraft.block.state.IBlockState
import net.minecraft.init.Biomes
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import java.util.*
import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.sqrt

class WorldGenOliveTree : AbstractTree("olive", 6)
{
    init
    {
        addCondition(BiomeCondition(Biomes.BIRCH_FOREST, 5, 0.55))
        addCondition(BiomeCondition(Biomes.FOREST, 2, 0.65))
        addCondition(BiomeCondition(Biomes.PLAINS, 1, 0.88))
        addCondition(TemperatureRainfallCondition(3, 1.5, 0.6, 0.6, 0.3))
    }

    companion object
    {
        var LEAVES_COLOR = 0x828E5A
    }

    override fun generateLeaves(worldIn: World,
                                blockPos: BlockPos.MutableBlockPos,
                                height: Int,
                                random: Random,
                                notifier: (World?, BlockPos?, IBlockState?) -> Unit)
    {
        val currentBlockPos = blockPos.copy()
        currentBlockPos.move(EnumFacing.UP, height)
        var i = 25
        while (i > 0)
        {
            val layerSize = (ceil(sqrt(i.toDouble()))).toInt()
            val iterator = BlockPos.getAllInBox(
                currentBlockPos.offset(EnumFacing.NORTH, layerSize)
                    .offset(EnumFacing.WEST, layerSize),
                currentBlockPos.offset(EnumFacing.SOUTH, layerSize)
                    .offset(EnumFacing.EAST, layerSize))
            val j = i
            iterator.forEach { leavesPos ->
                if (abs(leavesPos!!.x - currentBlockPos.getX())
                        + abs(leavesPos.z - currentBlockPos.getZ()) <= sqrt(j.toDouble()))
                    notifier(worldIn, leavesPos, naturalLeaveState)
            }
            currentBlockPos.move(EnumFacing.UP)
            i -= (random.nextInt(8) + 13)
        }
    }

    override fun generateTrunk(worldIn: World,
                               blockPos: BlockPos.MutableBlockPos,
                               maxHeight: Int,
                               random: Random?,
                               notifier: (World?, BlockPos?, IBlockState?) -> Unit)
    {
        val upNBlockPos = blockPos.copy()
        val upNSplitBlockPos = upNBlockPos.copy()

        val splitDirection = EnumFacing.byHorizontalIndex(random!!.nextInt(4))
        val splittingHeight = maxHeight - 1 - random.nextInt(3)

        for (height in 0..<maxHeight)
        {
            val state = worldIn.getBlockState(upNBlockPos)
            if (state.getBlock().isAir(state, worldIn, upNBlockPos)
                || state.getBlock().isLeaves(state, worldIn, upNBlockPos))
            {
                notifier(worldIn, upNBlockPos,
                    logState?.withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.Y))
            }

            if (height == splittingHeight)
            {
                upNSplitBlockPos.move(splitDirection)
            }
            if (height >= splittingHeight)
            {
                notifier(worldIn, upNSplitBlockPos,
                    logState?.withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.Y)
                )
                if (random.nextInt(2) == 0)
                    upNSplitBlockPos.move(splitDirection)
            }

            upNBlockPos.move(EnumFacing.UP)
            upNSplitBlockPos.move(EnumFacing.UP)
        }
    }

    override fun getMooreRadiusAtHeight(height: Int, trunkHeight: Int): Int
    {
        if (height < trunkHeight - 3) return 0
        if (height < trunkHeight) return 4 - (trunkHeight - height)
        return 0
    }

    override fun getBlockColor(blockState: IBlockState?,
                               worldIn: IBlockAccess?,
                               blockPos: BlockPos?,
                               tintIndex: Int): Int = LEAVES_COLOR

    override fun getItemColor(stack: ItemStack?, tintIndex: Int) = LEAVES_COLOR

    override fun getAppleDrop(chance: Int): ItemStack?
    {
        if (GTValues.RNG.nextInt(chance / 15) == 0)
            return GTLiteMetaItems.OLIVE.getStackForm(GTValues.RNG.nextInt(4) + 1)
        return ItemStack.EMPTY
    }

    override val apple: ItemStack?
        get() = GTLiteMetaItems.OLIVE.stackForm

}
