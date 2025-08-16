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
        this.setHardness(2.0F)
        this.setResistance(5.0F)
        this.setSoundType(SoundType.WOOD)
        this.setCreativeTab(GTLiteCreativeTabs.TAB_DECORATION)
        this.setHarvestLevel("axe", 0)
    }

}