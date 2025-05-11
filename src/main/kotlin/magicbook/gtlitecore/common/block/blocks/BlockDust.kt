package magicbook.gtlitecore.common.block.blocks

import magicbook.gtlitecore.api.GTLiteAPI
import net.minecraft.block.BlockFalling
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material

class BlockDust : BlockFalling(Material.SAND)
{

    init
    {
        setCreativeTab(GTLiteAPI.TAB_GTLITE)
        setHardness(0.4F)
        setResistance(0.4F)
        setSoundType(SoundType.SAND)
        setHarvestLevel("shovel", 0)
    }

}