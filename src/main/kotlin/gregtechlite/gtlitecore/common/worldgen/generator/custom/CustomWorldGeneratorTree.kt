package gregtechlite.gtlitecore.common.worldgen.generator.custom

import gregtechlite.gtlitecore.api.worldgen.generator.CustomWorldGeneratorImpl
import gregtechlite.gtlitecore.common.worldgen.generator.tree.WorldGeneratorTreeBase
import gregtechlite.gtlitecore.core.GTLiteConfigHolder
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.event.terraingen.SaplingGrowTreeEvent
import net.minecraftforge.fml.common.eventhandler.Event
import java.util.*

class CustomWorldGeneratorTree(isNotified: Boolean, generator: WorldGeneratorTreeBase) : CustomWorldGeneratorImpl(isNotified, generator)
{

    override fun scatter(worldIn: World?, blockPos: BlockPos.MutableBlockPos?, rand: Random?): Boolean
    {
        val event = SaplingGrowTreeEvent(worldIn, rand, blockPos)
        MinecraftForge.TERRAIN_GEN_BUS.post(event)

        if (event.result == Event.Result.DENY)
            return false

        return generator.generate(worldIn, blockPos, rand) { world, pos, rand ->
            setBlockAndUpdate(world, pos, rand)
        }
    }

    override fun configure(): Boolean = !GTLiteConfigHolder.worldgen.disableAdditionTreesGeneration // TODO clarify

}