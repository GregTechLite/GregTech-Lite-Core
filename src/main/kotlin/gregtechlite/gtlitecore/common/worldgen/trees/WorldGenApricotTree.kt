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

class WorldGenApricotTree : AbstractTree("apricot", 3)
{

    init
    {
        addCondition(BiomeCondition(Biomes.MUTATED_SAVANNA, 4, 0.40))
        addCondition(BiomeCondition(Biomes.SAVANNA, 2, 0.55))
        addCondition(TemperatureRainfallCondition(2, 1.20, 1.2, 0.05, 0.2))
    }

    companion object
    {
        var LEAVES_COLOR = 0x87A92C
    }

    override fun generateLeaves(worldIn: World,
                                blockPos: BlockPos.MutableBlockPos,
                                height: Int,
                                random: Random,
                                notifier: (World?, BlockPos?, IBlockState?) -> Unit)
    {
        val currentYBlockPos = blockPos.copy()
        currentYBlockPos.move(EnumFacing.UP, height - 4)
        var atBottom = true
        var i = 8
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
                    + abs(leavesPos.z - currentYBlockPos.getZ()) <= sqrt(j.toDouble()))
                    notifier(worldIn, leavesPos, naturalLeaveState)
            }
            if (atBottom)
            {
                i += 7
                atBottom = false
            }
            currentYBlockPos.move(EnumFacing.UP)
            i -= (random.nextInt(3) + 3)
        }
        notifier(worldIn, blockPos.copy().move(EnumFacing.UP, height), naturalLeaveState)
    }

    override fun getBlockColor(blockState: IBlockState?,
                               worldIn: IBlockAccess?,
                               blockPos: BlockPos?,
                               tintIndex: Int) = LEAVES_COLOR

    override fun getItemColor(stack: ItemStack?, tintIndex: Int) = LEAVES_COLOR

    override fun getAppleDrop(chance: Int): ItemStack?
    {
        if (GTValues.RNG.nextInt(chance / 15) == 0)
            return GTLiteMetaItems.APRICOT.stackForm
        return ItemStack.EMPTY
    }

    override val apple: ItemStack?
        get() = GTLiteMetaItems.APRICOT.stackForm

}
