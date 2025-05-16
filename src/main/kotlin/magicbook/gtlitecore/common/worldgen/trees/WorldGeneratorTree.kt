package magicbook.gtlitecore.common.worldgen.trees

import magicbook.gtlitecore.api.worldgen.AbstractWorldGenerator
import magicbook.gtlitecore.core.GTLiteConfigHolder
import net.minecraft.block.state.IBlockState
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.event.terraingen.SaplingGrowTreeEvent
import net.minecraftforge.fml.common.eventhandler.Event
import java.util.*

class WorldGeneratorTree(notify: Boolean, tree: AbstractTree?) : AbstractWorldGenerator(notify, tree)
{

    override fun generateMutable(worldIn: World, random: Random,
                                 blockPos: BlockPos.MutableBlockPos): Boolean
    {
        val event = SaplingGrowTreeEvent(worldIn, random, blockPos)
        MinecraftForge.TERRAIN_GEN_BUS.post(event)
        if (event.result == Event.Result.DENY)
            return false
        return feature.generate(worldIn, blockPos, random) { tWorldIn: World?, tBlockPos: BlockPos?, tBlockState: IBlockState? ->
            this.setBlock(tWorldIn, tBlockPos, tBlockState)
        }
    }

    override fun enabledGenerator(): Boolean = !GTLiteConfigHolder.worldgen.disableAdditionTreesGeneration

}