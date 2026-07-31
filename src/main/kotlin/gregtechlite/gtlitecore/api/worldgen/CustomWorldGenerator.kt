package gregtechlite.gtlitecore.api.worldgen

import net.minecraft.block.state.IBlockState
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import org.jetbrains.annotations.MustBeInvokedByOverriders
import java.util.*

interface CustomWorldGenerator
{
    @MustBeInvokedByOverriders
    fun generate(worldIn: World?,
                 blockPos: BlockPos.MutableBlockPos?,
                 rand: Random?,
                 notifier: (World?, BlockPos?, IBlockState?) -> Unit): Boolean
}