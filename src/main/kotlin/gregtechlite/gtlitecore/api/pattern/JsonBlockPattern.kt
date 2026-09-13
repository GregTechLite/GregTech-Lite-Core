package gregtechlite.gtlitecore.api.pattern

import gregtech.api.util.RelativeDirection
import gregtechlite.gtlitecore.api.collection.charHashMapOf
import net.minecraft.block.Block
import net.minecraft.block.state.IBlockState
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class JsonBlockPattern
{
    var structureDirection: Array<RelativeDirection>

    lateinit var blockPattern: Array<Array<String>>
    lateinit var aisleRepetitions: Array<IntArray>

    val symbols = charHashMapOf<MutableSet<String>>()

    init
    {
        structureDirection = arrayOf(RelativeDirection.LEFT, RelativeDirection.UP, RelativeDirection.FRONT)
    }

    constructor()
    {
        symbols.getOrPut(' ') { HashSet() }.add("any")
        symbols.getOrPut('#') { HashSet() }.add("air")
        symbols.getOrPut('@') { HashSet() }.add("controller")
    }

    constructor(world: World,
                minX: Int, minY: Int, minZ: Int,
                maxX: Int, maxY: Int, maxZ: Int)
    {
        blockPattern = Array(1 + maxX - minX) { Array(1 + maxY - minY) { "" } }
        aisleRepetitions = Array(blockPattern.size) {
            IntArray(2).apply {
                this[0] = 1
                this[1] = 1
            }
        }

        val states = mutableMapOf<IBlockState, Char>().apply {
            put(Block.getBlockById(0).defaultState, ' ') // Blocks#AIR state.
        }

        var currentChar = 'A'

        for (x in minX..maxX)
        {
            for (y in minY..maxY)
            {
                blockPattern[x - minX][y - minY] = String(CharArray(maxZ - minZ + 1) { i ->
                    val pos = BlockPos(x, y, minZ + i)
                    val state = world.getBlockState(pos)
                    states.getOrPut(state)
                    {
                        val newChar = currentChar++
                        symbols.getOrPut(newChar) { HashSet() }.add(newChar.toString())
                        newChar
                    }
                })
            }
        }
    }

    fun getActualPosOffset(x: Int, y: Int, z: Int, facing: EnumFacing): BlockPos
    {
        val c0 = intArrayOf(x, y, z)
        val c1 = IntArray(3)
        remapping(c0, c1, facing)
        return BlockPos(c1[0], c1[1], c1[2])
    }

    fun getActualPatternOffset(pos: BlockPos, facing: EnumFacing): IntArray
    {
        val c0 = intArrayOf(pos.x, pos.y, pos.z)
        val c1 = IntArray(3)
        remapping(c0, c1, facing)
        return c1
    }

    fun remapping(c0: IntArray, c1: IntArray, facing: EnumFacing)
    {
        for (i in 0..2)
        {
            val realFacing = structureDirection[i].getActualFacing(facing)
            when (realFacing)
            {
                EnumFacing.UP -> c1[1] = c0[i]
                EnumFacing.DOWN -> c1[1] = -c0[i]
                EnumFacing.WEST -> c1[0] = -c0[i]
                EnumFacing.EAST -> c1[0] = c0[i]
                EnumFacing.NORTH -> c1[2] = -c0[i]
                EnumFacing.SOUTH -> c1[2] = c0[i]
                else -> throw IllegalArgumentException("Invalid facing direction")
            }
        }
    }

    fun clean()
    {
        val usedChars = blockPattern.flatMap { row ->
            row.flatMap { str ->
                str.toCharArray().toList()
            }
        }.toSet()

        symbols.keys.removeAll { it !in usedChars }
        val usedPredicates = symbols.values.flatten().toSet()
        symbols.values.forEach { it.retainAll(usedPredicates) }
    }

    fun getCenterOffset(): IntArray
    {
        for (i in blockPattern.indices)
        {
            for (j in blockPattern[0].indices)
            {
                val row = blockPattern[i][j]
                for (k in row.indices)
                {
                    if (row[k] == '@')
                    {
                        return intArrayOf(i, j, k)
                    }
                }
            }
        }
        return intArrayOf(0, 0, 0)
    }

    fun copy(): JsonBlockPattern = JsonBlockPattern().apply {
        structureDirection = this@JsonBlockPattern.structureDirection.copyOf()
        blockPattern = Array(this@JsonBlockPattern.blockPattern.size) { i ->
            this@JsonBlockPattern.blockPattern[i].copyOf()
        }
        aisleRepetitions = Array(this@JsonBlockPattern.aisleRepetitions.size) { i ->
            this@JsonBlockPattern.aisleRepetitions[i].copyOf()
        }
        symbols.putAll(this@JsonBlockPattern.symbols.mapValues { HashSet(it.value) })
    }
}
