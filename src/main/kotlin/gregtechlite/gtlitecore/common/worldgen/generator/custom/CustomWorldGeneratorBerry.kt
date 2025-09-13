package gregtechlite.gtlitecore.common.worldgen.generator.custom

import gregtechlite.gtlitecore.api.worldgen.generator.CustomWorldGeneratorImpl
import gregtechlite.gtlitecore.common.worldgen.generator.plant.WorldGeneratorBerryBase
import gregtechlite.gtlitecore.core.GTLiteConfigHolder
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import java.util.Random

class CustomWorldGeneratorBerry(berry: WorldGeneratorBerryBase) : CustomWorldGeneratorImpl(true, berry)
{

    override fun generate(worldIn: World, rand: Random, pos: BlockPos): Boolean
    {
        return generator.generate(worldIn, BlockPos.MutableBlockPos(pos), rand, ::setBlockAndUpdate)
    }

    override fun scatter(worldIn: World?, blockPos: BlockPos.MutableBlockPos?, rand: Random?): Boolean
    {
        return generator.generate(worldIn, blockPos, rand, ::setBlockAndUpdate)
    }

    override fun configure(): Boolean = !GTLiteConfigHolder.worldgen.disableAdditionTreesGeneration // TODO clarify

}