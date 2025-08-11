package gregtechlite.gtlitecore.common.worldgen.crops

import gregtechlite.gtlitecore.api.worldgen.feature.AbstractFeature
import gregtechlite.gtlitecore.api.worldgen.feature.FeatureCondition
import gregtechlite.gtlitecore.common.block.base.GTLiteBerryBushVariantBlock
import gregtechlite.gtlitecore.common.worldgen.generator.GTLiteBerryGenerator
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.init.Blocks
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import java.util.*

open class WorldGenBerry(seed: Int, val berryBush: GTLiteBerryBushVariantBlock) : AbstractFeature(seed)
{

    init
    {
        worldGenInstance = GTLiteBerryGenerator(this)
    }

    override fun generate(worldIn: World?,
                          blockPos: BlockPos.MutableBlockPos?,
                          random: Random?,
                          notifier: (World?, BlockPos?, IBlockState?) -> Unit): Boolean
    {
        if (canGrowAt(worldIn, blockPos))
        {
            notifier(worldIn, blockPos, berryBush.withAge(2))
            repeat(random!!.nextInt(3))
            {
                val other = blockPos!!.add(random.nextInt(5) - 2,
                    random.nextInt(5) - 2, 0)

                if (canGrowAt(worldIn, other))
                    notifier(worldIn, other, berryBush.withAge(2))
            }
            return true
        }
        return false
    }

    protected fun canGrowAt(world: World?, pos: BlockPos?): Boolean
    {
        if (pos!!.y >= 1 && pos.y < world!!.height)
        {
            val soilState = world.getBlockState(pos.down())
            val currentState = world.getBlockState(pos)
            return canGrowInto(currentState.getBlock()) && soilState.getBlock()
                .canSustainPlant(soilState, world, pos.down(), EnumFacing.UP, berryBush)
        }
        return false
    }

    protected fun canGrowInto(block: Block): Boolean
    {
        val material = block.defaultState.getMaterial()
        return material === Material.AIR || block === Blocks.VINE || material === Material.SNOW
    }

    override fun addCondition(condition: FeatureCondition): WorldGenBerry
    {
        return super.addCondition(condition) as WorldGenBerry
    }

}
