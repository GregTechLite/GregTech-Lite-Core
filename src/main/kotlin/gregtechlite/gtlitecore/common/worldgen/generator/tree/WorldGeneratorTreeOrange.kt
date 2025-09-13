package gregtechlite.gtlitecore.common.worldgen.generator.tree

import gregtech.api.GTValues
import gregtechlite.gtlitecore.api.extension.copy
import gregtechlite.gtlitecore.api.worldgen.condition.BiomeCondition
import gregtechlite.gtlitecore.api.worldgen.condition.ClimateCondition
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems
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

class WorldGeneratorTreeOrange : WorldGeneratorTreeBase("orange", 1)
{

    init
    {
        conditions.add(BiomeCondition(Biomes.SAVANNA, 3, 0.45))
        conditions.add(BiomeCondition(Biomes.SAVANNA_PLATEAU, 6, 0.25))
        conditions.add(ClimateCondition(3, 1.2, 1.15, 0.0, 0.2))
    }

    override fun getMinTrunkHeight(random: Random): Int
    {
        return 2 + random.nextInt(3)
    }

    override fun generateLeaves(worldIn: World,
                                blockPos: BlockPos.MutableBlockPos,
                                height: Int,
                                rand: Random,
                                notifier: (World?, BlockPos?, IBlockState?) -> Unit)
    {
        val currentYBlockPos = blockPos.copy()
        currentYBlockPos.move(EnumFacing.UP)
        for (facingIndex in 0 ..< height + 1)
        {
            val iterator = BlockPos.getAllInBox(
                currentYBlockPos.offset(EnumFacing.NORTH, 2)
                    .offset(EnumFacing.WEST, 2),
                currentYBlockPos.offset(EnumFacing.SOUTH, 2)
                    .offset(EnumFacing.EAST, 2))

            val sideFacingIndex = facingIndex
            iterator.forEach { leavesPos ->
                if (abs(leavesPos!!.x - currentYBlockPos.getX())
                        + abs(leavesPos.z - currentYBlockPos.getZ()) < 3
                    && (sideFacingIndex < height || ((abs(leavesPos.x - currentYBlockPos.getX())
                        + abs(leavesPos.z - currentYBlockPos.getZ()) == 1
                            || rand.nextInt(2) == 0))))
                {
                    notifier(worldIn, leavesPos, placedLeaveState)
                }
            }
            currentYBlockPos.move(EnumFacing.UP)
        }
        notifier(worldIn, currentYBlockPos, placedLeaveState)
    }

    override fun generateTrunk(worldIn: World,
                               blockPos: BlockPos.MutableBlockPos,
                               maxHeight: Int,
                               rand: Random?,
                               notifier: (World?, BlockPos?, IBlockState?) -> Unit)
    {
        val upNBlockPos = blockPos.copy()
        for (height in 0..<maxHeight)
        {
            notifier(worldIn, upNBlockPos, logState?.withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.Y))
            if (height > 0)
            {
                val randomFacing = EnumFacing.byHorizontalIndex(rand!!.nextInt(4))
                notifier(worldIn, upNBlockPos.offset(randomFacing), logState
                    ?.withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.fromFacingAxis(randomFacing.axis)))
            }
            upNBlockPos.move(EnumFacing.UP)
        }
    }

    override fun getMooreRadiusAtHeight(height: Int, trunkHeight: Int): Int
    {
        if (height == 0) return 0
        if (height < trunkHeight + 1) return 1
        return 0
    }

    override fun getFruitDrop(chance: Int): ItemStack?
    {
        if (GTValues.RNG.nextInt(chance / 10) == 0)
            return GTLiteMetaItems.ORANGE.getStackForm(GTValues.RNG.nextInt(2) + 1)
        return ItemStack.EMPTY
    }

    override fun getItemColor(stack: ItemStack?, tintIndex: Int) = 0x76C92C

    override fun getBlockColor(blockState: IBlockState?,
                               worldIn: IBlockAccess?,
                               blockPos: BlockPos?,
                               tintIndex: Int) = 0x76C92C

}
