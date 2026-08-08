package gregtechlite.gtlitecore.integration.jei.group

import gregtechlite.gtlitecore.api.extension.stack
import gregtechlite.gtlitecore.api.extension.unzipSubBlocks
import gregtechlite.gtlitecore.api.extension.unzipSubVariants
import gregtechlite.gtlitecore.common.block.GTLiteBlocks
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities
import mezz.jei.api.ICollapsibleGroupRegistry

object GTLiteCollapsibleGroups
{
    internal fun registerGroup(registry: ICollapsibleGroupRegistry)
    {
        buildStorageGroups(registry)
        registry.addGroup("sheeted_frame", GTLiteBlocks.SHEETED_FRAME_BLOCKS.unzipSubBlocks())
        registry.addGroup("metal_wall", GTLiteBlocks.METAL_WALL_BLOCKS.unzipSubVariants())
    }

    private fun buildStorageGroups(registry: ICollapsibleGroupRegistry)
    {
        registry.addGroup("additional_drum", arrayOf(GTLiteMetaTileEntities.IRON_DRUM, GTLiteMetaTileEntities.COPPER_DRUM,
            GTLiteMetaTileEntities.LEAD_DRUM, GTLiteMetaTileEntities.CHROME_DRUM, GTLiteMetaTileEntities.TUNGSTEN_DRUM,
            GTLiteMetaTileEntities.IRIDIUM_DRUM, GTLiteMetaTileEntities.PE_CAN, GTLiteMetaTileEntities.PTFE_CAN,
            GTLiteMetaTileEntities.PBI_CAN, GTLiteMetaTileEntities.KEVLAR_CAN).map { it.stack() })
        registry.addGroup("additional_crate", arrayOf(GTLiteMetaTileEntities.IRON_CRATE, GTLiteMetaTileEntities.COPPER_CRATE,
            GTLiteMetaTileEntities.SILVER_CRATE, GTLiteMetaTileEntities.GOLD_CRATE, GTLiteMetaTileEntities.DIAMOND_CRATE)
            .map { it.stack() })
    }
}