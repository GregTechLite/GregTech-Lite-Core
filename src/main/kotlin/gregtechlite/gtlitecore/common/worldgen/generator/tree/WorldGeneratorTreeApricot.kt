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

class WorldGeneratorTreeApricot : WorldGeneratorTreeBase("apricot", 3)
{

    init
    {
        conditions.add(BiomeCondition(Biomes.MUTATED_SAVANNA, 4, 0.40))
        conditions.add(BiomeCondition(Biomes.SAVANNA, 2, 0.55))
        conditions.add(ClimateCondition(2, 1.20, 1.2, 0.05, 0.2))
    }

    override fun generateLeaves(worldIn: World,
                                blockPos: BlockPos.MutableBlockPos,
                                height: Int,
                                rand: Random,
                                notifier: (World?, BlockPos?, IBlockState?) -> Unit)
    {
        val currentYBlockPos = blockPos.copy()
        currentYBlockPos.moveUp(height - 4)

        var atBottom = true

        var size = 8
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
            if (atBottom)
            {
                size += 7
                atBottom = false
            }
            currentYBlockPos.moveUp()
            size -= (rand.nextInt(3) + 3)
        }
        notifier(worldIn, blockPos.copy().moveUp(height), placedLeaveState)
    }

    override fun getFruitDrop(chance: Int): ItemStack?
    {
        if (GTValues.RNG.nextInt(chance / 15) == 0)
            return GTLiteMetaItems.APRICOT.stackForm
        return ItemStack.EMPTY
    }

    override fun getItemColor(stack: ItemStack?, tintIndex: Int) = 0x87A92C

    override fun getBlockColor(blockState: IBlockState?,
                               worldIn: IBlockAccess?,
                               blockPos: BlockPos?,
                               tintIndex: Int) = 0x87A92C

}
