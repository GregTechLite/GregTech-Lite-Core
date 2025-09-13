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

class WorldGeneratorTreeBanana : WorldGeneratorTreeBase("banana", 0)
{

    init
    {
        conditions.add(BiomeCondition(Biomes.JUNGLE, 5, 0.35))
        conditions.add(BiomeCondition(Biomes.JUNGLE_EDGE, 5, 0.3))
        conditions.add(BiomeCondition(Biomes.JUNGLE_HILLS, 5, 0.35))
        conditions.add(BiomeCondition(Biomes.MUTATED_JUNGLE, 5, 0.3))
        conditions.add(BiomeCondition(Biomes.MUTATED_JUNGLE_EDGE, 5, 0.15))
        conditions.add(ClimateCondition(5, 1.5, 0.8, 0.8, 0.4))
    }

    override fun generateLeaves(worldIn: World,
                                blockPos: BlockPos.MutableBlockPos,
                                height: Int,
                                rand: Random,
                                notifier: (World?, BlockPos?, IBlockState?) -> Unit)
    {
        // region Top leaves
        val pos = blockPos.up(height - 1).copy()
        for (facingIndex in 0 .. 2)
        {
            pos.move(EnumFacing.UP)
            notifier(worldIn, pos, placedLeaveState)
            if (facingIndex == 1)
            {
                pos.move(EnumFacing.byHorizontalIndex(rand.nextInt(4)))
                notifier(worldIn, pos, placedLeaveState)
            }
        }
        // endregion

        // region Sideways leaves
        for (facingIndex in 0 .. 3)
        {
            val leafOffset = rand.nextInt(2)
            val sidePos = blockPos.up(height - 2 + leafOffset).copy()
            for (sideFacingIndex in 0 .. 2)
            {
                sidePos.move(EnumFacing.byHorizontalIndex(facingIndex))
                notifier(worldIn, sidePos, placedLeaveState)
                if (sideFacingIndex == 0)
                {
                    sidePos.move(EnumFacing.UP)
                    notifier(worldIn, sidePos, placedLeaveState)
                }
            }
        }
        // endregion

        // region Bottom leaves
        for (facingIndex in 0 .. 3)
        {
            notifier(worldIn, blockPos.up(height - 1)
                .offset(EnumFacing.byHorizontalIndex(facingIndex)), placedLeaveState)
            notifier(
                worldIn, blockPos.up(height - 1)
                    .offset(EnumFacing.byHorizontalIndex(facingIndex))
                    .offset(EnumFacing.byHorizontalIndex(facingIndex).rotateY()), placedLeaveState)
        }
        // endregion
    }

    override fun generateTrunk(worldIn: World,
                               blockPos: BlockPos.MutableBlockPos,
                               maxHeight: Int, rand: Random?,
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
                notifier(worldIn, blockPos.up(height), logState
                    ?.withProperty(BlockLog.LOG_AXIS,
                                   if (height == maxHeight - 1) BlockLog.EnumAxis.NONE else BlockLog.EnumAxis.Y))
            }
            upNBlockPos.move(EnumFacing.UP)
        }
    }

    override fun getMinTrunkHeight(random: Random) = 3 + random.nextInt(2)

    override fun getFruitDrop(chance: Int): ItemStack?
    {
        if (GTValues.RNG.nextInt(chance / 8) == 0)
            return GTLiteMetaItems.BANANA.getStackForm(GTValues.RNG.nextInt(4) + 3)
        return ItemStack.EMPTY
    }

    override fun getItemColor(stack: ItemStack?, tintIndex: Int) = 0x396A2E

    override fun getBlockColor(blockState: IBlockState?,
                               worldIn: IBlockAccess?,
                               blockPos: BlockPos?,
                               tintIndex: Int) = 0x396A2E

}
