package gregtechlite.gtlitecore.common.worldgen.generator.tree

import gregtech.api.GTValues
import gregtechlite.gtlitecore.api.extension.copy
import gregtechlite.gtlitecore.api.worldgen.condition.ClimateCondition
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import java.util.*

class WorldGeneratorTreeCoconut : WorldGeneratorTreeBase("coconut", 8)
{

    init
    {
        conditions.add(ClimateCondition(5, 1.5, 0.8, 0.9, 0.5))
    }

    override fun generateLeaves(worldIn: World,
                                blockPos: BlockPos.MutableBlockPos,
                                height: Int,
                                rand: Random,
                                notifier: (World?, BlockPos?, IBlockState?) -> Unit)
    {
        // region Top leaves
        val tBlockPos = blockPos.up(height - 1).copy()
        val tSideVariance = rand.nextInt(4)
        for (i in 0 .. 2)
        {
            tBlockPos.move(EnumFacing.UP)
            notifier(worldIn, tBlockPos, placedLeaveState)
            if (i == 1 || i == 2)
            {
                tBlockPos.move(EnumFacing.byHorizontalIndex(tSideVariance))
                notifier(worldIn, tBlockPos, placedLeaveState)
            }
        }
        tBlockPos.move(EnumFacing.byHorizontalIndex(tSideVariance))
        notifier(worldIn, tBlockPos, placedLeaveState)
        // endregion

        // region Sideway leaves
        for (i in 0 .. 3)
        {
            val leafOffset = rand.nextInt(2)
            val sBlockPos = blockPos.up(height - 2 + leafOffset).copy()

            val sSideVariance = rand.nextInt(2)
            for (j in 0 .. 2)
            {
                sBlockPos.move(EnumFacing.byHorizontalIndex(i))
                notifier(worldIn, sBlockPos, placedLeaveState)
                if (j == 0)
                {
                    sBlockPos.move(EnumFacing.UP)
                    notifier(worldIn, sBlockPos, placedLeaveState)
                }
                if (rand.nextInt(3) == 0)
                {
                    if (sSideVariance == 0)
                    {
                        sBlockPos.move(EnumFacing.byHorizontalIndex(i).rotateY())
                        notifier(worldIn, sBlockPos, placedLeaveState)
                    }
                    else
                    {
                        sBlockPos.move(EnumFacing.byHorizontalIndex(i).rotateYCCW())
                        notifier(worldIn, sBlockPos, placedLeaveState)
                    }
                }
            }
        }
        // endregion

        // region Bottom leaves
        for (i in 0 .. 3)
        {
            notifier(worldIn, blockPos.up(height - 1)
                .offset(EnumFacing.byHorizontalIndex(i)), placedLeaveState)
            notifier(worldIn, blockPos.up(height - 1)
                .offset(EnumFacing.byHorizontalIndex(i))
                .offset(EnumFacing.byHorizontalIndex(i).rotateY()), placedLeaveState)
        }
        // endregion

    }

    override fun getFruitDrop(chance: Int): ItemStack?
    {
        if (GTValues.RNG.nextInt(chance / 7) == 0)
            return GTLiteMetaItems.COCONUT.getStackForm(GTValues.RNG.nextInt(2))
        return ItemStack.EMPTY
    }

    override fun getItemColor(stack: ItemStack?, tintIndex: Int) = 0x657F1C

    override fun getBlockColor(blockState: IBlockState?,
                               worldIn: IBlockAccess?,
                               blockPos: BlockPos?,
                               tintIndex: Int) = 0x657F1C

}
