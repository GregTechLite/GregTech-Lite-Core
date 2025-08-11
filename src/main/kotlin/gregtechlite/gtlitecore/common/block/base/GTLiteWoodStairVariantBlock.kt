package gregtechlite.gtlitecore.common.block.base

import gregtechlite.gtlitecore.common.creativetabs.GTLiteCreativeTabs
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
        this.setCreativeTab(GTLiteCreativeTabs.TAB_DECORATION)
        this.useNeighborBrightness = true
    }

    override fun doesSideBlockChestOpening(blockState: IBlockState, world: IBlockAccess,
                                           pos: BlockPos, side: EnumFacing): Boolean = false

}