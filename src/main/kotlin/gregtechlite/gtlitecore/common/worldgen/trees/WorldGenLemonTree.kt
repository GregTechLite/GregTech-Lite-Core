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

class WorldGenLemonTree : AbstractTree("lemon", 4)
{

    companion object
    {
        var LEAVES_COLOR = 0x87A92C
    }

    init
    {
        addCondition(BiomeCondition(Biomes.JUNGLE_EDGE, 3, 0.4))
        addCondition(BiomeCondition(Biomes.FOREST, 1, 0.65))
        addCondition(TemperatureRainfallCondition(5, 1.2, 0.7, 0.7, 0.3))
    }

    override fun generateLeaves(worldIn: World,
                                blockPos: BlockPos.MutableBlockPos,
                                height: Int,
                                random: Random,
                                notifier: (World?, BlockPos?, IBlockState?) -> Unit)
    {
        val currentYBlockPos = blockPos.copy()
        currentYBlockPos.move(EnumFacing.UP, height - 4)
        var i = 1
        while (i < 12)
        {
            val layerSize = (ceil(sqrt(i.toDouble()))).toInt()
            val iterator = BlockPos.getAllInBox(
                currentYBlockPos.offset(EnumFacing.NORTH, layerSize)
                    .offset(EnumFacing.WEST, layerSize),
                currentYBlockPos.offset(EnumFacing.SOUTH, layerSize)
                    .offset(EnumFacing.EAST, layerSize))
            val j = i
            iterator.forEach { leavesPos  ->
                if (abs(leavesPos!!.x - currentYBlockPos.getX())
                    + abs(leavesPos.z - currentYBlockPos.getZ()) <= sqrt(j.toDouble())
                    && random.nextInt(16 - j) != 0)
                {
                    notifier(worldIn, leavesPos, naturalLeaveState)
                }
            }
            currentYBlockPos.move(EnumFacing.UP)
            i += (random.nextInt(3) + 2)
        }
        notifier(worldIn, blockPos.copy()
                .move(EnumFacing.UP, height), naturalLeaveState)
    }

    override fun getMinTrunkHeight(random: Random) = 6 + random.nextInt(3)

    override fun getBlockColor(blockState: IBlockState?,
                               worldIn: IBlockAccess?,
                               blockPos: BlockPos?,
                               tintIndex: Int) = LEAVES_COLOR

    override fun getItemColor(stack: ItemStack?, tintIndex: Int) = LEAVES_COLOR

    override fun getAppleDrop(chance: Int): ItemStack?
    {
        if (GTValues.RNG.nextInt(chance / 10) == 0)
            return GTLiteMetaItems.LEMON.getStackForm(GTValues.RNG.nextInt(2) + 1)
        return ItemStack.EMPTY
    }

    override val apple: ItemStack?
        get() = GTLiteMetaItems.LEMON.stackForm

}
