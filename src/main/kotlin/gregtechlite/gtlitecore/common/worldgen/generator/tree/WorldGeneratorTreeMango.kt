package gregtechlite.gtlitecore.common.worldgen.generator.tree

import gregtech.api.GTValues
import gregtechlite.gtlitecore.api.extension.copy
import gregtechlite.gtlitecore.api.extension.moveUp
import gregtechlite.gtlitecore.api.worldgen.condition.BiomeCondition
import gregtechlite.gtlitecore.api.worldgen.condition.ClimateCondition
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems
import net.minecraft.block.state.IBlockState
import net.minecraft.init.Biomes
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import java.util.*
import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.sqrt

class WorldGeneratorTreeMango : WorldGeneratorTreeBase("mango", 2)
{

    init
    {
        conditions.add(BiomeCondition(arrayOf(Biomes.MUTATED_JUNGLE_EDGE, Biomes.JUNGLE_EDGE), 4, 0.2))
        conditions.add(ClimateCondition(2, 1.5, 0.9, 0.9, 0.3))
    }

    override fun generateLeaves(worldIn: World,
                                blockPos: BlockPos.MutableBlockPos,
                                height: Int,
                                rand: Random,
                                notifier: (World?, BlockPos?, IBlockState?) -> Unit)
    {
        val currentYBlockPos = blockPos.copy()
        currentYBlockPos.moveUp(height - 2)

        var size = 13
        while (size > 0)
        {
            val layerSize = (ceil(sqrt(size.toDouble()))).toInt()
            val iterator = BlockPos.getAllInBox(
                currentYBlockPos.north(layerSize).west(layerSize),
                currentYBlockPos.south(layerSize).east(layerSize))

            val sideSize = size
            iterator.forEach {
                if (abs(it.x - currentYBlockPos.x) + abs(it.z - currentYBlockPos.z) <= sqrt(sideSize.toDouble()))
                    notifier(worldIn, it, placedLeaveState)
            }

            currentYBlockPos.moveUp()
            size -= (rand.nextInt(4) + 4)
        }
        notifier(worldIn, blockPos.copy().moveUp(height), placedLeaveState)
    }

    override fun getFruitDrop(chance: Int): ItemStack?
    {
        if (GTValues.RNG.nextInt(chance / 10) == 0)
            return GTLiteMetaItems.MANGO.getStackForm(GTValues.RNG.nextInt(3))
        return ItemStack.EMPTY
    }

    override fun getItemColor(stack: ItemStack?,
                              tintIndex: Int) = 0x7D921E

    override fun getBlockColor(blockState: IBlockState?,
                               worldIn: IBlockAccess?,
                               blockPos: BlockPos?,
                               tintIndex: Int) = 0x7D921E

}
