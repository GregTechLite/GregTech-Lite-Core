package gregtechlite.gtlitecore.common.block.base

import gregtechlite.gtlitecore.common.creativetabs.GTLiteCreativeTabs
import net.minecraft.block.BlockFenceGate
import net.minecraft.block.BlockPlanks
import net.minecraft.block.SoundType

class GTLiteWoodFenceGateVariantBlock : BlockFenceGate(BlockPlanks.EnumType.OAK)
{

    init
    {
        this.setHardness(2.0F)
        this.setResistance(5.0F)
        this.setSoundType(SoundType.WOOD)
        this.setCreativeTab(GTLiteCreativeTabs.TAB_DECORATION)
        this.setHarvestLevel("axe", 0)
    }

}