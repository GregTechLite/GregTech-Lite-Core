package gregtechlite.gtlitecore.api.worldgen

import net.minecraft.block.state.IBlockState
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import java.util.Random

interface CustomWorldGenerator
{

    fun generate(worldIn: World?,
                 blockPos: BlockPos.MutableBlockPos?,
                 rand: Random?,
                 notifier: (World?, BlockPos?, IBlockState?) -> Unit): Boolean

}