package gregtechlite.gtlitecore.common.worldgen.generator.plant

import gregtechlite.gtlitecore.api.worldgen.condition.GenerateCondition
import gregtechlite.gtlitecore.api.worldgen.generator.AbstractWorldGenerator
import gregtechlite.gtlitecore.api.worldgen.generator.CustomWorldGeneratorImpl
import gregtechlite.gtlitecore.common.block.GTLiteBerryBushBlock
import gregtechlite.gtlitecore.common.worldgen.generator.custom.CustomWorldGeneratorBerry
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.init.Blocks
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import java.util.*

open class WorldGeneratorBerryBase(seed: Int, val bush: GTLiteBerryBushBlock) : AbstractWorldGenerator(seed)
{

    override var innerGenerator: CustomWorldGeneratorImpl?
        get() = CustomWorldGeneratorBerry(this)
        set(value)
        {
            innerGenerator = value
        }

    override fun generate(worldIn: World?,
                          blockPos: BlockPos.MutableBlockPos?,
                          rand: Random?,
                          notifier: (World?, BlockPos?, IBlockState?) -> Unit): Boolean
    {
        if (canGrowAt(worldIn, blockPos))
        {
            notifier(worldIn, blockPos, bush.withAge(2))
            repeat(rand!!.nextInt(3))
            {
                val other = blockPos!!.add(rand.nextInt(5) - 2,
                                           rand.nextInt(5) - 2, 0)

                if (canGrowAt(worldIn, other))
                    notifier(worldIn, other, bush.withAge(2))
            }
            return true
        }
        return false
    }

    fun addCondition(condition: GenerateCondition): WorldGeneratorBerryBase
    {
        this.conditions.add(condition)
        return this
    }

    private fun canGrowAt(world: World?, pos: BlockPos?): Boolean
    {
        if (pos!!.y >= 1 && pos.y < world!!.height)
        {
            val soilState = world.getBlockState(pos.down())
            val currentState = world.getBlockState(pos)
            return canGrowInto(currentState.getBlock()) && soilState.getBlock()
                .canSustainPlant(soilState, world, pos.down(), EnumFacing.UP, bush)
        }
        return false
    }

    private fun canGrowInto(block: Block): Boolean
    {
        val material = block.defaultState.material
        return material === Material.AIR || block === Blocks.VINE || material === Material.SNOW
    }

}