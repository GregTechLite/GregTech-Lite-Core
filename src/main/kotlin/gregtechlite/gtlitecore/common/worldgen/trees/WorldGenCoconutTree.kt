package gregtechlite.gtlitecore.common.worldgen.trees

import gregtech.api.GTValues
import gregtechlite.gtlitecore.api.extension.copy
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems
import gregtechlite.gtlitecore.common.worldgen.features.TemperatureRainfallCondition
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import java.util.*

class WorldGenCoconutTree : AbstractTree("coconut", 8)
{

    init
    {
        addCondition(TemperatureRainfallCondition(5, 1.5, 0.8, 0.9, 0.5))
    }

    companion object
    {
        var LEAVES_COLOR = 0x657F1C
    }

    override fun generateLeaves(worldIn: World,
                                blockPos: BlockPos.MutableBlockPos,
                                height: Int,
                                random: Random,
                                notifier: (World?, BlockPos?, IBlockState?) -> Unit)
    {
        // Generate top.
        val tBlockPos = blockPos.up(height - 1).copy()
        val tSideVariance = random.nextInt(4)
        for (i in 0 .. 2)
        {
            tBlockPos.move(EnumFacing.UP)
            notifier(worldIn, tBlockPos, naturalLeaveState)
            if (i == 1 || i == 2)
            {
                tBlockPos.move(EnumFacing.byHorizontalIndex(tSideVariance))
                notifier(worldIn, tBlockPos, naturalLeaveState)
            }
        }
        tBlockPos.move(EnumFacing.byHorizontalIndex(tSideVariance))
        notifier(worldIn, tBlockPos, naturalLeaveState)
        // Generate sideways leaves.
        for (i in 0 .. 3)
        {
            val leafOffset = random.nextInt(2)
            val sBlockPos = blockPos.up(height - 2 + leafOffset).copy()

            val sSideVariance = random.nextInt(2)
            for (j in 0 .. 2)
            {
                sBlockPos.move(EnumFacing.byHorizontalIndex(i))
                notifier(worldIn, sBlockPos, naturalLeaveState)
                if (j == 0)
                {
                    sBlockPos.move(EnumFacing.UP)
                    notifier(worldIn, sBlockPos, naturalLeaveState)
                }
                if (random.nextInt(3) == 0)
                {
                    if (sSideVariance == 0)
                    {
                        sBlockPos.move(EnumFacing.byHorizontalIndex(i).rotateY())
                        notifier(worldIn, sBlockPos, naturalLeaveState)
                    }
                    else
                    {
                        sBlockPos.move(EnumFacing.byHorizontalIndex(i).rotateYCCW())
                        notifier(worldIn, sBlockPos, naturalLeaveState)
                    }
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

    override fun getBlockColor(blockState: IBlockState?,
                               worldIn: IBlockAccess?,
                               blockPos: BlockPos?,
                               tintIndex: Int) = LEAVES_COLOR

    override fun getItemColor(stack: ItemStack?, tintIndex: Int) = LEAVES_COLOR

    override fun getAppleDrop(chance: Int): ItemStack?
    {
        if (GTValues.RNG.nextInt(chance / 7) == 0)
            return GTLiteMetaItems.COCONUT.getStackForm(GTValues.RNG.nextInt(2))
        return ItemStack.EMPTY
    }

    override val apple: ItemStack?
        get() = GTLiteMetaItems.COCONUT.stackForm

}
