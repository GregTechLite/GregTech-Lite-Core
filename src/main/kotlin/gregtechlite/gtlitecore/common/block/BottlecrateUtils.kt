package gregtechlite.gtlitecore.common.block

import net.minecraft.util.EnumFacing
import net.minecraft.util.math.AxisAlignedBB

object BottlecrateUtils
{
    const val SLOT_WIDTH   = 4.0 / 16.0
    const val SLOT_LENGTH  = 4.0 / 16.0
    const val DEPTH_OFFSET = 0.001

    const val BOTTLE_WIDTH  = SLOT_WIDTH - 2 * DEPTH_OFFSET
    const val BOTTLE_LENGTH = SLOT_LENGTH - 2 * DEPTH_OFFSET
    const val BOTTLE_HEIGHT = 11.0 / 16.0

    const val NECK_WIDTH  = 2.0 / 16.0
    const val NECK_LENGTH = 2.0 / 16.0
    const val NECK_HEIGHT = 2.0 / 16.0

    const val CAP_WIDTH  = 2.0 / 16.0
    const val CAP_LENGTH = 2.0 / 16.0
    const val CAP_HEIGHT = 1.0 / 16.0

    const val CRATE_MARGIN_X = 1.0 / 16.0
    const val CRATE_MARGIN_Z = 1.0 / 16.0
    const val CRATE_BOTTOM  = 1.0 / 16.0

    const val FLUID_MARGIN = 0.5 / 16.0
    const val FLUID_WIDTH = SLOT_WIDTH - 2 * FLUID_MARGIN
    const val FLUID_LENGTH = SLOT_LENGTH - 2 * FLUID_MARGIN
    const val FLUID_HEIGHT = 10.0 / 16.0

    const val SLOT_STEP_X = 5.0 / 16.0
    const val SLOT_STEP_Z = 5.0 / 16.0

    val bottleBoxes = run {
        Array(9) { i ->
            val baseBox = AxisAlignedBB(.0, .0, .0, SLOT_WIDTH, BOTTLE_HEIGHT + NECK_HEIGHT, SLOT_LENGTH)
            val (bx, by, bz) = getStartPoint(i)
            baseBox.offset(bx, by, bz)
        }
    }

    /**
     * Gets the row and column used for calculations based on the visual position of the slot.
     *
     * @param visualIdx The visual position of the slot. When facing bottlecrate, the top left is 0, increasing by one
     *                  from left to right, top to bottom.
     * @param facing    The front facing of bottlecrate.
     * @return          The row and column used for calculations.
     */
    fun indexToRowCol(visualIdx: Int, facing: EnumFacing): Pair<Int, Int>
    {
        val visualRow = visualIdx / 3
        val visualCol = visualIdx % 3
        return when (facing)
        {
            EnumFacing.NORTH -> Pair(2 - visualCol, 2 - visualRow)
            EnumFacing.EAST  -> Pair(visualRow, 2 - visualCol)
            EnumFacing.SOUTH -> Pair(visualCol, visualRow)
            EnumFacing.WEST  -> Pair(2 - visualRow, visualCol)
            else             -> Pair(visualCol, visualRow)
        }
    }

    /**
     * Gets the index of a slot in bottlecrate's direction based on the index when it is facing south.
     *
     * @param visualIdx The visual position of the slot.
     * @return          The physical position of the slot.
     */
    fun getPhysicalIndex(visualIdx: Int, facing: EnumFacing): Int
    {
        val visualRow = visualIdx / 3
        val visualCol = visualIdx % 3
        val (physCol, physRow) = when (facing)
        {
            EnumFacing.NORTH -> Pair(2 - visualCol,2 - visualRow)
            EnumFacing.EAST  -> Pair(visualRow, 2 - visualCol)
            EnumFacing.SOUTH -> Pair(visualCol, visualRow)
            EnumFacing.WEST  -> Pair(2 - visualRow, visualCol)
            else             -> Pair(visualCol, visualRow)
        }
        return 3 * physRow+physCol
    }

    /**
     * Gets the index of a slot in bottlecrate's direction based on the index when it is facing south.
     *
     * @param physIdx The visual position of the slot.
     * @return        The physical position of the slot.
     */
    fun getVisualIndex(physIdx: Int, facing: EnumFacing): Int
    {
        val physCol = physIdx % 3
        val physRow = physIdx / 3
        val (visualCol, visualRow) = when (facing)
        {
            EnumFacing.NORTH -> Pair(2 - physCol, 2 - physRow)
            EnumFacing.EAST  -> Pair(2 - physRow, physCol)
            EnumFacing.SOUTH -> Pair(physCol, physRow)
            EnumFacing.WEST  -> Pair(physRow, 2 - physCol)
            else             -> Pair(physCol, physRow)
        }
        return 3 * visualCol+visualRow
    }

    /**
     * Gets the starting point of bottle based on its visual position.
     *
     * @param physIdx The physical position of the slot. When facing bottlecrate, the southeast is 0, increasing by one
     *                from east to west, south to north.
     * @return        The offset of the starting point relative to the block (x, y, z).
     */
    fun getStartPoint(physIdx: Int): Triple<Double,Double,Double>
    {
        val (col, row) = Pair(physIdx % 3, physIdx / 3)
        return Triple(CRATE_MARGIN_X + col * SLOT_STEP_X, CRATE_BOTTOM, CRATE_MARGIN_Z + row * SLOT_STEP_Z)
    }
}