package magicbook.gtlitecore.common.block.blocks

import magicbook.gtlitecore.api.GTLiteAPI
import net.minecraft.block.BlockFence
import net.minecraft.block.SoundType
import net.minecraft.block.material.MapColor
import net.minecraft.block.material.Material

class GTLiteWoodFenceVariantBlock() : BlockFence(Material.WOOD, MapColor.WOOD)
{

    init
    {
        this.setHardness(2.0F)
        this.setResistance(5.0F)
        this.setSoundType(SoundType.WOOD)
        this.setCreativeTab(GTLiteAPI.TAB_GTLITE)
        this.setHarvestLevel("axe", 0)
    }

}