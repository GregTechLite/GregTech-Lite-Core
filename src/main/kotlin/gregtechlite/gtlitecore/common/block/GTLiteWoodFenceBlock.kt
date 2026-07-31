package gregtechlite.gtlitecore.common.block

import gregtechlite.gtlitecore.common.creativetabs.GTLiteCreativeTabs
import net.minecraft.block.BlockFence
import net.minecraft.block.SoundType
import net.minecraft.block.material.MapColor
import net.minecraft.block.material.Material

class GTLiteWoodFenceBlock() : BlockFence(Material.WOOD, MapColor.WOOD)
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