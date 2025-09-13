package gregtechlite.gtlitecore.common.worldgen.generator.tree

import gregtech.api.GTValues
import gregtechlite.gtlitecore.api.extension.copy
import gregtechlite.gtlitecore.api.worldgen.condition.BiomeCondition
import gregtechlite.gtlitecore.api.worldgen.condition.ClimateCondition
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems
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

class WorldGeneratorTreeLemon : WorldGeneratorTreeBase("lemon", 4)
{

    init
    {
        conditions.add(BiomeCondition(Biomes.JUNGLE_EDGE, 3, 0.4))
        conditions.add(BiomeCondition(Biomes.FOREST, 1, 0.65))
        conditions.add(ClimateCondition(5, 1.2, 0.7, 0.7, 0.3))
    }

    override fun generateLeaves(worldIn: World,
                                blockPos: BlockPos.MutableBlockPos,
                                height: Int,
                                rand: Random,
                                notifier: (World?, BlockPos?, IBlockState?) -> Unit)
    {
        val currentYBlockPos = blockPos.copy()
        currentYBlockPos.move(EnumFacing.UP, height - 4)

        var size = 1
        while (size < 12)
        {
            val layerSize = (ceil(sqrt(size.toDouble()))).toInt()
            val iterator = BlockPos.getAllInBox(
                currentYBlockPos.offset(EnumFacing.NORTH, layerSize)
                    .offset(EnumFacing.WEST, layerSize),
                currentYBlockPos.offset(EnumFacing.SOUTH, layerSize)
                    .offset(EnumFacing.EAST, layerSize))

            val sideSize = size
            iterator.forEach { leavesPos  ->
                if (abs(leavesPos!!.x - currentYBlockPos.getX())
                    + abs(leavesPos.z - currentYBlockPos.getZ()) <= sqrt(sideSize.toDouble())
                    && rand.nextInt(16 - sideSize) != 0)
                {
                    notifier(worldIn, leavesPos, placedLeaveState)
                }
            }
            currentYBlockPos.move(EnumFacing.UP)
            size += (rand.nextInt(3) + 2)
        }
        notifier(worldIn, blockPos.copy().move(EnumFacing.UP, height), placedLeaveState)
    }

    override fun getMinTrunkHeight(random: Random) = 6 + random.nextInt(3)

    override fun getFruitDrop(chance: Int): ItemStack?
    {
        if (GTValues.RNG.nextInt(chance / 10) == 0)
            return GTLiteMetaItems.LEMON.getStackForm(GTValues.RNG.nextInt(2) + 1)
        return ItemStack.EMPTY
    }

    override fun getItemColor(stack: ItemStack?, tintIndex: Int) = 0x87A92C

    override fun getBlockColor(blockState: IBlockState?,
                               worldIn: IBlockAccess?,
                               blockPos: BlockPos?,
                               tintIndex: Int) = 0x87A92C

}
