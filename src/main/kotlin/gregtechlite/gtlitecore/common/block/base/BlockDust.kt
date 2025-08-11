package gregtechlite.gtlitecore.common.block.base

import gregtechlite.gtlitecore.common.creativetabs.GTLiteCreativeTabs
import net.minecraft.block.BlockFalling
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material

class BlockDust : BlockFalling(Material.SAND)
{

    init
    {
        setCreativeTab(GTLiteCreativeTabs.TAB_MAIN)
        setHardness(0.4F)
        setResistance(0.4F)
        setSoundType(SoundType.SAND)
        setHarvestLevel("shovel", 0)
    }

}