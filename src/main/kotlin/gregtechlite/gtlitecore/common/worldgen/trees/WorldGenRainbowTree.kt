package gregtechlite.gtlitecore.common.worldgen.trees

import gregtechlite.gtlitecore.api.extension.copy
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials
import gregtechlite.gtlitecore.common.worldgen.features.BiomeCondition
import gregtechlite.gtlitecore.common.worldgen.features.TemperatureRainfallCondition
import net.minecraft.block.state.IBlockState
import net.minecraft.init.Biomes
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraftforge.fluids.Fluid
import java.util.*
import kotlin.math.abs

class WorldGenRainbowTree : AbstractTree("rainbow", 9)
{
    init
    {
        addCondition(BiomeCondition(Biomes.PLAINS, 5, 0.89))
        addCondition(BiomeCondition(Biomes.MUTATED_PLAINS, 5, 0.89))
        addCondition(TemperatureRainfallCondition(5, 0.5, 0.8, 0.4, 0.1))
    }

    companion object
    {
        val LEAVES_COLOR = intArrayOf(0xFF0000, 0xFF4000, 0xFF8000, 0xFFC000, 0xFFFF00, 0xC0FF00, 0x80FF00, 0x40FF00,
                                      0x00FF00, 0x00FF40, 0x00FF80, 0x00FFC0, 0x00FFFF, 0x00C0FF, 0x0080FF, 0x0040FF,
                                      0x0000FF, 0x4000FF, 0x8000FF, 0xC000FF, 0xFF00FF, 0xFF00C0, 0xFF0080, 0xFF0040)
    }

    override fun generateLeaves(worldIn: World,
                                blockPos: BlockPos.MutableBlockPos,
                                height: Int,
                                random: Random,
                                notifier: (World?, BlockPos?, IBlockState?) -> Unit)
    {
        val currentYBlockPos = blockPos.copy()
        currentYBlockPos.move(EnumFacing.UP, height - 3)
        for (i in 0..6)
        {
            val layerSize = getMooreRadiusAtHeight(i + height - 3, height)
            val iterator = BlockPos.getAllInBox(
                currentYBlockPos.offset(EnumFacing.NORTH, layerSize)
                    .offset(EnumFacing.WEST, layerSize),
                currentYBlockPos.offset(EnumFacing.SOUTH, layerSize)
                    .offset(EnumFacing.EAST, layerSize))
            iterator.forEach { leavesPos: BlockPos? ->
                if (abs(leavesPos!!.x - currentYBlockPos.getX())
                    + abs(leavesPos.z - currentYBlockPos.getZ()) < 6)
                {
                    notifier(worldIn, leavesPos, naturalLeaveState)
                }
            }
            currentYBlockPos.move(EnumFacing.UP)
        }
    }

    override fun getMinTrunkHeight(random: Random) = random.nextInt(3) + 6

    override fun getMooreRadiusAtHeight(height: Int, trunkHeight: Int): Int
    {
        if (height < trunkHeight - 3) return 0
        if (height == trunkHeight - 3) return 2
        if (height < trunkHeight + 3) return 3
        return 2
    }

    override val perlinScale: Double
        get() = 0.05

    override fun getBlockColor(blockState: IBlockState?,
                               worldIn: IBlockAccess?,
                               blockPos: BlockPos?,
                               tintIndex: Int): Int
    {
        return LEAVES_COLOR[((abs(blockPos!!.x) + abs(blockPos.y) + abs(blockPos.z))) % LEAVES_COLOR.size]
    }

    override fun getItemColor(stack: ItemStack?, tintIndex: Int) = 0x8F00FF

    override val treeSap: Fluid?
        get() = GTLiteMaterials.RainbowSap.fluid

}
