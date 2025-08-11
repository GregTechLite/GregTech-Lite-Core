package gregtechlite.gtlitecore.api.extension

import net.minecraft.util.math.BlockPos

/**
 * Copies the [BlockPos] to [BlockPos.MutableBlockPos].
 *
 * @return A MutableBlockPos copy of BlockPos.
 */
fun BlockPos.copy(): BlockPos.MutableBlockPos
{
    return BlockPos.MutableBlockPos(this)
}