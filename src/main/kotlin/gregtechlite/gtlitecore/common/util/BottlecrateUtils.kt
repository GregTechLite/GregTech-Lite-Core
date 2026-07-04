package gregtechlite.gtlitecore.common.util

import gregtechlite.gtlitecore.common.block.BlockBottlecrate
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumFacing.*
import net.minecraft.util.math.AxisAlignedBB

object BottlecrateUtils {
    const val SLOT_WIDTH = 4.0/16.0
    const val SLOT_LENGTH = 4.0/16.0
    const val DEPTH_OFFSET = 0.001
    const val BOTTLE_WIDTH  = SLOT_WIDTH - 2*DEPTH_OFFSET
    const val BOTTLE_LENGTH = SLOT_LENGTH - 2*DEPTH_OFFSET
    const val BOTTLE_HEIGHT = 11.0/16.0
    const val NECK_WIDTH  = 2.0/16.0
    const val NECK_LENGTH = 2.0/16.0
    const val NECK_HEIGHT = 2.0/16.0
    const val CAP_WIDTH  = 2.0/16.0
    const val CAP_LENGTH = 2.0/16.0
    const val CAP_HEIGHT = 1.0/16.0
    const val CRATE_MARGINX = 1.0/16.0
    const val CRATE_MARGINZ = 1.0/16.0
    const val CRATE_BOTTOM = 1.0/16.0
    const val FLUID_MARGIN = 0.5/16.0
    const val FLUID_WIDTH = SLOT_WIDTH-2*FLUID_MARGIN
    const val FLUID_LENGTH = SLOT_LENGTH-2*FLUID_MARGIN
    const val FLUID_HEIGHT = 10.0/16.0
    const val SLOT_STEPX = 5.0/16.0
    const val SLOT_STEPZ = 5.0/16.0

    val bottleBoxes = run{
        Array(9) {  i ->
            val baseBox = AxisAlignedBB(.0, .0, .0, SLOT_WIDTH, BOTTLE_HEIGHT + NECK_HEIGHT, SLOT_LENGTH)
            val (bx, by, bz) = getStartPoint(i)
            baseBox.offset(bx, by, bz)
        }
    }

    /**
     * Get the row and column used for calculations based on the visual position of the slot
     * @param visualIndex The visual position of the slot. When facing bottlecrate, the top left is 0, increasing by one from left to right, top to bottom.
     * @param facing The front facing of bottlecrate
     * @return Pair(physRow, physCol)，the row and column used for calculations
     */
    fun indexToRowCol(visualIndex: Int, facing: EnumFacing): Pair<Int, Int> {
        val visualRow = visualIndex/3
        val visualCol = visualIndex%3
        return when(facing){
            NORTH -> Pair(2-visualCol,2-visualRow)
            EAST -> Pair(visualRow,2-visualCol)
            SOUTH -> Pair(visualCol,visualRow)
            WEST -> Pair(2-visualRow,visualCol)
            else -> Pair(visualCol,visualRow)
        }
    }

    /**
     * Get the index of a slot in bottlecrate's direction based on the index when it is facing south
     * @param visual The visual position of the slot
     * @return physIndex The physical position of the slot
     */
    fun getPhysIndex(visual: Int, facing: EnumFacing):Int{
        val visualRow = visual/3
        val visualCol = visual%3

        val (physCol,physRow) = when(facing){
            NORTH -> Pair(2-visualCol,2-visualRow)
            EAST -> Pair(visualRow,2-visualCol)
            SOUTH -> Pair(visualCol,visualRow)
            WEST -> Pair(2-visualRow,visualCol)
            else-> Pair(visualCol,visualRow)
        }
        return 3*physRow+physCol
    }

    /**
     * Get the index of a slot in bottlecrate's direction based on the index when it is facing south
     * @param physIndex The visual position of the slot
     * @return visual The physical position of the slot
     */

    fun getVisualIndex(physIndex:Int,facing: EnumFacing):Int{
        val physCol = physIndex%3
        val physRow = physIndex/3

        val (visualCol,visualRow) =  when(facing){
            NORTH -> Pair(2-physCol,2-physRow)
            EAST -> Pair(2-physRow,physCol)
            SOUTH -> Pair(physCol,physRow)
            WEST -> Pair(physRow,2-physCol)
            else -> Pair(physCol,physRow)
        }
        return 3*visualCol+visualRow
    }



    /**
     * Get the starting point of bottle based on its visual position
     * @param physIndex The physical position of the slot. When facing bottlecrate, the southeast is 0, increasing by one from east to west, south to north.
     * @return Triple(xOffset,yOffset,zOffset) Offset of the starting point relative to the block (x,y,z)
     */

    fun getStartPoint(physIndex: Int): Triple<Double,Double,Double>{
        val (col,row) = Pair(physIndex%3,physIndex/3)
        return Triple(CRATE_MARGINX+col*SLOT_STEPX,CRATE_BOTTOM,CRATE_MARGINZ+row*SLOT_STEPZ)
    }
}