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

class WorldGenLimeTree : AbstractTree("lime", 5)
{

    init
    {
        addCondition(BiomeCondition(Biomes.JUNGLE, 3, 0.3))
        addCondition(BiomeCondition(Biomes.MUTATED_JUNGLE_EDGE, 4, 0.3))
        addCondition(TemperatureRainfallCondition(5, 1.2, 0.8, 0.85, 0.3))
    }

    companion object
    {
        var LEAVES_COLOR = 0x426801
    }

    override fun generateLeaves(worldIn: World,
                                blockPos: BlockPos.MutableBlockPos,
                                height: Int,
                                random: Random,
                                notifier: (World?, BlockPos?, IBlockState?) -> Unit)
    {
        val currentYBlockPos = blockPos.copy()
        currentYBlockPos.move(EnumFacing.UP, height - 2)
        var i = 9
        while (i > 0)
        {
            val layerSize = (ceil(sqrt(i.toDouble()))).toInt()
            val iterator = BlockPos.getAllInBox(
                currentYBlockPos.offset(EnumFacing.NORTH, layerSize)
                    .offset(EnumFacing.WEST, layerSize),
                currentYBlockPos.offset(EnumFacing.SOUTH, layerSize)
                    .offset(EnumFacing.EAST, layerSize))
            val j = i
            iterator.forEach { leavesPos ->
                if (abs(leavesPos!!.x - currentYBlockPos.getX())
                        + abs(leavesPos.z - currentYBlockPos.getZ()) <= sqrt(j.toDouble())
                    || (abs(leavesPos.x - currentYBlockPos.getX()) + abs(leavesPos.z - currentYBlockPos.getZ())
                            <= (sqrt(j.toDouble()) + 0.5) && random.nextInt(2) == 0))
                {
                    notifier(worldIn, leavesPos, naturalLeaveState)
                }
            }
            currentYBlockPos.move(EnumFacing.UP)
            i -= (random.nextInt(2) + 2)
        }
        notifier(worldIn, blockPos.copy()
                .move(EnumFacing.UP, height), naturalLeaveState)
    }

    override fun getBlockColor(blockState: IBlockState?,
                               worldIn: IBlockAccess?,
                               blockPos: BlockPos?,
                               tintIndex: Int) = LEAVES_COLOR


    override fun getItemColor(stack: ItemStack?, tintIndex: Int) = LEAVES_COLOR

    override fun getAppleDrop(chance: Int): ItemStack?
    {
        if (GTValues.RNG.nextInt(chance / 10) == 0)
            return GTLiteMetaItems.LIME.getStackForm(GTValues.RNG.nextInt(2) + 1)
        return ItemStack.EMPTY
    }

    override val apple: ItemStack?
        get() = GTLiteMetaItems.LIME.stackForm

}
