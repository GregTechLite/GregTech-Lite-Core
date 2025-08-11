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

class WorldGenBananaTree : AbstractTree("banana", 0)
{

    init
    {
        addCondition(BiomeCondition(Biomes.JUNGLE, 5, 0.35))
        addCondition(BiomeCondition(Biomes.JUNGLE_EDGE, 5, 0.3))
        addCondition(BiomeCondition(Biomes.JUNGLE_HILLS, 5, 0.35))
        addCondition(BiomeCondition(Biomes.MUTATED_JUNGLE, 5, 0.3))
        addCondition(BiomeCondition(Biomes.MUTATED_JUNGLE_EDGE, 5, 0.15))
        addCondition(TemperatureRainfallCondition(5, 1.5, 0.8, 0.8, 0.4))
    }

    companion object
    {
        var LEAVES_COLOR: Int = 0x396A2E
    }

    override fun generateLeaves(worldIn: World,
                                blockPos: BlockPos.MutableBlockPos,
                                height: Int,
                                random: Random,
                                notifier: (World?, BlockPos?, IBlockState?) -> Unit)
    {
        // Generate top.
        val tBlockPos = blockPos.up(height - 1).copy()
        for (i in 0 .. 2)
        {
            tBlockPos.move(EnumFacing.UP)
            notifier(worldIn, tBlockPos, naturalLeaveState)
            if (i == 1)
            {
                tBlockPos.move(EnumFacing.byHorizontalIndex(random.nextInt(4)))
                notifier(worldIn, tBlockPos, naturalLeaveState)
            }
        }
        // Generate sideways leaves.
        for (i in 0 .. 3)
        {
            val leafOffset = random.nextInt(2)
            val sBlockPos = blockPos.up(height - 2 + leafOffset).copy()

            for (j in 0 .. 2)
            {
                sBlockPos.move(EnumFacing.byHorizontalIndex(i))
                notifier(worldIn, sBlockPos, naturalLeaveState)
                if (j == 0)
                {
                    sBlockPos.move(EnumFacing.UP)
                    notifier(worldIn, sBlockPos, naturalLeaveState)
                }
            }
        }
        // Generate ring at height - 3 for extra fullness.
        for (i in 0 .. 3)
        {
            notifier(
                worldIn, blockPos.up(height - 1)
                    .offset(EnumFacing.byHorizontalIndex(i)), naturalLeaveState
            )
            notifier(
                worldIn, blockPos.up(height - 1)
                    .offset(EnumFacing.byHorizontalIndex(i))
                    .offset(EnumFacing.byHorizontalIndex(i).rotateY()), naturalLeaveState
            )
        }
    }

    override fun generateTrunk(worldIn: World,
                               blockPos: BlockPos.MutableBlockPos,
                               maxHeight: Int, random: Random?,
                               notifier: (World?, BlockPos?, IBlockState?) -> Unit)
    {
        val upNBlockPos = blockPos.copy()
        for (height in 0 ..< maxHeight)
        {
            val blockState = worldIn.getBlockState(upNBlockPos)
            if (blockState.getBlock().isAir(blockState, worldIn, upNBlockPos)
                || blockState.getBlock().isLeaves(blockState, worldIn, upNBlockPos))
            {
                notifier(worldIn, blockPos.up(height),
                    logState?.withProperty(BlockLog.LOG_AXIS,
                        if (height == maxHeight - 1) BlockLog.EnumAxis.NONE else BlockLog.EnumAxis.Y))
            }
            upNBlockPos.move(EnumFacing.UP)
        }
    }

    override fun getMinTrunkHeight(random: Random) = 3 + random.nextInt(2)

    override fun getAppleDrop(chance: Int): ItemStack?
    {
        if (GTValues.RNG.nextInt(chance / 8) == 0)
            return GTLiteMetaItems.BANANA.getStackForm(GTValues.RNG.nextInt(4) + 3)
        return ItemStack.EMPTY
    }

    override val apple: ItemStack?
        get() = GTLiteMetaItems.BANANA.stackForm

    override fun getBlockColor(blockState: IBlockState?,
                               worldIn: IBlockAccess?,
                               blockPos: BlockPos?,
                               tintIndex: Int, ) = LEAVES_COLOR

    override fun getItemColor(stack: ItemStack?, tintIndex: Int) = LEAVES_COLOR

}
