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

class WorldGeneratorTreeLime : WorldGeneratorTreeBase("lime", 5)
{

    init
    {
        conditions.add(BiomeCondition(Biomes.JUNGLE, 3, 0.3))
        conditions.add(BiomeCondition(Biomes.MUTATED_JUNGLE_EDGE, 4, 0.3))
        conditions.add(ClimateCondition(5, 1.2, 0.8, 0.85, 0.3))
    }

    override fun generateLeaves(worldIn: World,
                                blockPos: BlockPos.MutableBlockPos,
                                height: Int,
                                rand: Random,
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
                            <= (sqrt(j.toDouble()) + 0.5) && rand.nextInt(2) == 0))
                {
                    notifier(worldIn, leavesPos, placedLeaveState)
                }
            }
            currentYBlockPos.move(EnumFacing.UP)
            i -= (rand.nextInt(2) + 2)
        }
        notifier(worldIn, blockPos.copy()
                .move(EnumFacing.UP, height), placedLeaveState)
    }

    override fun getFruitDrop(chance: Int): ItemStack?
    {
        if (GTValues.RNG.nextInt(chance / 10) == 0)
            return GTLiteMetaItems.LIME.getStackForm(GTValues.RNG.nextInt(2) + 1)
        return ItemStack.EMPTY
    }

    override fun getItemColor(stack: ItemStack?, tintIndex: Int) = 0x426801

    override fun getBlockColor(blockState: IBlockState?,
                               worldIn: IBlockAccess?,
                               blockPos: BlockPos?,
                               tintIndex: Int) = 0x426801

}
