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
import kotlin.math.pow

class WorldGeneratorTreeNutmeg : WorldGeneratorTreeBase("nutmeg", 7)
{

    init
    {
        conditions.add(BiomeCondition(Biomes.JUNGLE, 3, 0.3))
        conditions.add(ClimateCondition(3, 1.2, 0.85, 1.0, 0.3))
    }

    override fun generateLeaves(worldIn: World,
                                blockPos: BlockPos.MutableBlockPos,
                                height: Int,
                                rand: Random,
                                notifier: (World?, BlockPos?, IBlockState?) -> Unit)
    {
        val currentYBlockPos = blockPos.copy()
        currentYBlockPos.moveUp(height - 2)
        var size = 3.0
        while (size > 0)
        {
            val layerSize = (ceil(size)).toInt()
            val iterator = BlockPos.getAllInBox(
                currentYBlockPos.north(layerSize).west(layerSize),
                currentYBlockPos.south(layerSize).east(layerSize))

            val sideSize = size
            iterator.forEach {
                if ((abs(it.x - currentYBlockPos.x).toDouble().pow(2.0)
                            + abs(it.z - currentYBlockPos.z).toDouble().pow(2.0)).pow(0.5) <= sideSize)
                    notifier(worldIn, it, placedLeaveState)
            }
            currentYBlockPos.moveUp()
            size -= (rand.nextDouble() / 2 + 0.5)
        }
        notifier(worldIn, blockPos.copy().moveUp(height), placedLeaveState)
    }

    override fun getItemColor(stack: ItemStack?, tintIndex: Int) = 0x6DB626

    override fun getBlockColor(blockState: IBlockState?,
                               worldIn: IBlockAccess?,
                               blockPos: BlockPos?,
                               tintIndex: Int) = 0x6DB626

    override fun getFruitDrop(chance: Int): ItemStack?
    {
        if (GTValues.RNG.nextInt(chance / 10) == 0)
            return GTLiteMetaItems.NUTMEG.getStackForm(GTValues.RNG.nextInt(2) + 1)
        return ItemStack.EMPTY
    }

}
