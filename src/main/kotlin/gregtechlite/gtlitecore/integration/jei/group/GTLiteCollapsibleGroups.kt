package gregtechlite.gtlitecore.integration.jei.group

import gregtechlite.gtlitecore.api.extension.unzipSubBlocks
import gregtechlite.gtlitecore.api.extension.unzipSubVariants
import gregtechlite.gtlitecore.common.block.GTLiteBlocks
import mezz.jei.api.ICollapsibleGroupRegistry

object GTLiteCollapsibleGroups
{
    internal fun registerGroup(registry: ICollapsibleGroupRegistry)
    {
        registry.addGroup("sheeted_frame", GTLiteBlocks.SHEETED_FRAME_BLOCKS.unzipSubBlocks())
        registry.addGroup("metal_wall", GTLiteBlocks.METAL_WALL_BLOCKS.unzipSubVariants())
    }
}