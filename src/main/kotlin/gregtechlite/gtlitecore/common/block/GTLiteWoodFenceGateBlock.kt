package gregtechlite.gtlitecore.common.block

import gregtechlite.gtlitecore.common.creativetabs.GTLiteCreativeTabs
import net.minecraft.block.BlockFenceGate
import net.minecraft.block.BlockPlanks
import net.minecraft.block.SoundType

class GTLiteWoodFenceGateBlock : BlockFenceGate(BlockPlanks.EnumType.OAK)
{

    init
    {
        setHardness(2.0F)
        setResistance(5.0F)
        setSoundType(SoundType.WOOD)
        setCreativeTab(GTLiteCreativeTabs.TAB_DECORATION)
        setHarvestLevel("axe", 0)
    }

}