package magicbook.gtlitecore.common.block.blocks

import magicbook.gtlitecore.api.GTLiteAPI
import net.minecraft.block.BlockStairs
import net.minecraft.block.state.IBlockState
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess

class GTLiteWoodStairVariantBlock(state: IBlockState) : BlockStairs(state)
{

    init
    {
        this.setHarvestLevel("axe", 0)
        this.setCreativeTab(GTLiteAPI.TAB_GTLITE)
        this.useNeighborBrightness = true
    }

    override fun doesSideBlockChestOpening(blockState: IBlockState, world: IBlockAccess,
                                           pos: BlockPos, side: EnumFacing): Boolean = false

}