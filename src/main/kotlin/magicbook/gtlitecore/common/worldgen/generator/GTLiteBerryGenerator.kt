package magicbook.gtlitecore.common.worldgen.generator

import gregtech.common.ConfigHolder
import magicbook.gtlitecore.api.worldgen.AbstractWorldGenerator
import magicbook.gtlitecore.common.worldgen.crops.WorldGenBerry
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import java.util.*

class GTLiteBerryGenerator(berry: WorldGenBerry) : AbstractWorldGenerator(true, berry)
{

    override fun generate(worldIn: World, random: Random, blockPos: BlockPos): Boolean
        = feature.generate(worldIn, BlockPos.MutableBlockPos(blockPos), random, this::setBlock)

    override fun generateMutable(worldIn: World?, random: Random?, blockPos: BlockPos.MutableBlockPos?): Boolean
        = feature.generate(worldIn, blockPos, random, this::setBlock)

    override fun enabledGenerator(): Boolean
        = !ConfigHolder.worldgen.disableRubberTreeGeneration

}