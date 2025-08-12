package gregtechlite.gtlitecore.api.pattern

import gregtech.api.util.RelativeDirection
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

    val symbols: MutableMap<Char, MutableSet<String>> = HashMap()

    init
    {
        this.structureDirection = arrayOf(RelativeDirection.LEFT, RelativeDirection.UP, RelativeDirection.FRONT)
    }

    constructor()
    {
        this.symbols.getOrPut(' ') { HashSet() }.add("any")
        this.symbols.getOrPut('#') { HashSet() }.add("air")
        this.symbols.getOrPut('@') { HashSet() }.add("controller")
    }

    constructor(world: World,
                minX: Int, minY: Int, minZ: Int,
                maxX: Int, maxY: Int, maxZ: Int)
    {
        this.blockPattern = Array(1 + maxX - minX) { Array(1 + maxY - minY) { "" } }
        this.aisleRepetitions = Array(this.blockPattern.size) {
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
                val builder = StringBuilder()
                for (z in minZ..maxZ)
                {
                    val pos = BlockPos(x, y, z)
                    val state = world.getBlockState(pos)
                    val char = states.getOrPut(state)
                    {
                        val newChar = currentChar++
                        this.symbols.getOrPut(newChar) { HashSet() }.add(newChar.toString())
                        newChar
                    }
                    builder.append(char)
                }
                this.blockPattern[x - minX][y - minY] = builder.toString()
            }
        }
    }

    fun getActualPosOffset(x: Int, y: Int, z: Int, facing: EnumFacing): BlockPos
    {
        val c0 = intArrayOf(x, y, z)
        val c1 = IntArray(3)
        this.remapping(c0, c1, facing)
        return BlockPos(c1[0], c1[1], c1[2])
    }

    fun getActualPatternOffset(pos: BlockPos, facing: EnumFacing): IntArray
    {
        val c0 = intArrayOf(pos.x, pos.y, pos.z)
        val c1 = IntArray(3)
        this.remapping(c0, c1, facing)
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
        val usedChars = this.blockPattern.flatMap { row ->
            row.flatMap { str ->
                str.toCharArray().toList()
            }
        }.toSet()

        this.symbols.keys.removeAll { it !in usedChars }
        val usedPredicates = this.symbols.values.flatten().toSet()
        this.symbols.values.forEach { it.retainAll(usedPredicates) }
    }

    fun getCenterOffset(): IntArray
    {
        for (i in this.blockPattern.indices)
        {
            for (j in this.blockPattern[0].indices)
            {
                val row = this.blockPattern[i][j]
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

        this.structureDirection = this@JsonBlockPattern.structureDirection.copyOf()
        this.blockPattern = Array(this@JsonBlockPattern.blockPattern.size) { i ->
            this@JsonBlockPattern.blockPattern[i].copyOf()
        }
        this.aisleRepetitions = Array(this@JsonBlockPattern.aisleRepetitions.size) { i ->
            this@JsonBlockPattern.aisleRepetitions[i].copyOf()
        }
        this.symbols.putAll(this@JsonBlockPattern.symbols.mapValues { HashSet(it.value) })
    }

}
