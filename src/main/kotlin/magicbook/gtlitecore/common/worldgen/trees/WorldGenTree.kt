package magicbook.gtlitecore.common.worldgen.trees

import gregtech.common.ConfigHolder
import magicbook.gtlitecore.api.worldgen.AbstractWorldGen
import net.minecraft.block.state.IBlockState
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.event.terraingen.SaplingGrowTreeEvent
import net.minecraftforge.fml.common.eventhandler.Event
import java.util.*

class WorldGenTree(notify: Boolean, tree: AbstractTree?) : AbstractWorldGen(notify, tree)
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

    override fun enabledGenerator(): Boolean // TODO Owner ConfigHolders?
    {
        return !ConfigHolder.worldgen.disableRubberTreeGeneration
    }

}