package gregtechlite.gtlitecore.api.extension

import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos

fun BlockPos.copy(): BlockPos.MutableBlockPos = BlockPos.MutableBlockPos(this)

fun BlockPos.horizontal(idx: Int): BlockPos = offset(EnumFacing.byHorizontalIndex(idx))

fun BlockPos.horizontalYRotate(idx: Int): BlockPos = offset(EnumFacing.byHorizontalIndex(idx).rotateY())

fun BlockPos.MutableBlockPos.moveUp(n: Int = 1): BlockPos.MutableBlockPos = move(EnumFacing.UP, n)

fun BlockPos.MutableBlockPos.moveHorizontal(idx: Int): BlockPos.MutableBlockPos
    = move(EnumFacing.byHorizontalIndex(idx))

fun BlockPos.MutableBlockPos.moveHorizontalRotateY(idx: Int): BlockPos.MutableBlockPos
    = move(EnumFacing.byHorizontalIndex(idx).rotateY())

fun BlockPos.MutableBlockPos.moveHorizontalRotateYCCW(idx: Int): BlockPos.MutableBlockPos
    = move(EnumFacing.byHorizontalIndex(idx).rotateYCCW())