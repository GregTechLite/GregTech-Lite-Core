package magicbook.gtlitecore.common.block.blocks

import magicbook.gtlitecore.api.GTLiteAPI
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
        this.setCreativeTab(GTLiteAPI.TAB_GTLITE)
        this.setHarvestLevel("axe", 0)
    }

}