package gregtechlite.gtlitecore.common.worldgen.generator

import gregtech.common.ConfigHolder
import gregtechlite.gtlitecore.api.worldgen.AbstractWorldGenerator
import gregtechlite.gtlitecore.common.worldgen.crops.WorldGenBerry
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import java.util.*

class GTLiteBerryGenerator(berry: WorldGenBerry) : AbstractWorldGenerator(true, berry)
{

    override fun generate(worldIn: World,
                          random: Random,
                          blockPos: BlockPos): Boolean
    {
        return feature.generate(worldIn, BlockPos.MutableBlockPos(blockPos), random, ::setBlock)
    }

    override fun generateMutable(worldIn: World?,
                                 random: Random?,
                                 blockPos: BlockPos.MutableBlockPos?): Boolean
    {
        return feature.generate(worldIn, blockPos, random, ::setBlock)
    }

    override fun enabledGenerator() = !ConfigHolder.worldgen.disableRubberTreeGeneration

}