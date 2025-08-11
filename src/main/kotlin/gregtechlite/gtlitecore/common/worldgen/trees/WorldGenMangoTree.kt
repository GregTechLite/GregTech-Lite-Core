package gregtechlite.gtlitecore.common.worldgen.trees

import gregtech.api.GTValues
import gregtechlite.gtlitecore.api.extension.copy
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems
import gregtechlite.gtlitecore.common.worldgen.features.BiomeCondition
import gregtechlite.gtlitecore.common.worldgen.features.TemperatureRainfallCondition
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

class WorldGenMangoTree : AbstractTree("mango", 2)
{
    init
    {
        addCondition(BiomeCondition(arrayOf(Biomes.MUTATED_JUNGLE_EDGE, Biomes.JUNGLE_EDGE), 4, 0.2))
        addCondition(TemperatureRainfallCondition(2, 1.5, 0.9, 0.9, 0.3))
    }

    companion object
    {
        var LEAVES_COLOR = 0x7D921E
    }

    override fun generateLeaves(worldIn: World,
                                blockPos: BlockPos.MutableBlockPos,
                                height: Int,
                                random: Random,
                                notifier: (World?, BlockPos?, IBlockState?) -> Unit)
    {
        val currentYBlockPos = blockPos.copy()
        currentYBlockPos.move(EnumFacing.UP, height - 2)
        var i = 13
        while (i > 0)
        {
            val layerSize = (ceil(sqrt(i.toDouble()))).toInt()
            val iterator = BlockPos.getAllInBox(
                currentYBlockPos.offset(EnumFacing.NORTH, layerSize)
                    .offset(EnumFacing.WEST, layerSize),
                currentYBlockPos.offset(EnumFacing.SOUTH, layerSize)
                    .offset(EnumFacing.EAST, layerSize)
            )
            val j = i
            iterator.forEach { leavesPos ->
                if (abs(leavesPos!!.x - currentYBlockPos.getX())
                    + abs(leavesPos.z - currentYBlockPos.getZ()) <= sqrt(j.toDouble())
                ) notifier(worldIn, leavesPos, naturalLeaveState)
            }
            currentYBlockPos.move(EnumFacing.UP)
            i -= (random.nextInt(4) + 4)
        }
        notifier(
            worldIn, blockPos.copy()
                .move(EnumFacing.UP, height), naturalLeaveState
        )
    }

    override fun getBlockColor(
        blockState: IBlockState?, worldIn: IBlockAccess?,
        blockPos: BlockPos?, tintIndex: Int) = LEAVES_COLOR

    override fun getItemColor(stack: ItemStack?, tintIndex: Int) = LEAVES_COLOR

    override fun getAppleDrop(chance: Int): ItemStack?
    {
        if (GTValues.RNG.nextInt(chance / 10) == 0)
            return GTLiteMetaItems.MANGO.getStackForm(GTValues.RNG.nextInt(3))
        return ItemStack.EMPTY
    }

    override val apple: ItemStack?
        get() = GTLiteMetaItems.MANGO.stackForm

}
