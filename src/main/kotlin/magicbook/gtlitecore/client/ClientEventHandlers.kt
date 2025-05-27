package magicbook.gtlitecore.client

import gregtech.api.unification.OreDictUnifier
import gregtech.client.renderer.pipe.PipeRenderer
import gregtech.common.blocks.MaterialItemBlock
import magicbook.gtlitecore.common.block.itemblocks.SheetedFrameItemBlock
import net.minecraftforge.client.event.TextureStitchEvent
import net.minecraftforge.event.entity.player.ItemTooltipEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

@Suppress("MISSING_DEPENDENCY_CLASS")
@SideOnly(Side.CLIENT)
class ClientEventHandlers
{

    @SubscribeEvent
    fun textureStitchPre(event: TextureStitchEvent.Pre)
    {
        // Re-initialized textures (restrictors) in PipeRenderer to ensure it is loaded when a GT pipe
        // is rendering, this is a previous bug fix for PipeRenderer (though it is initialized in event
        // buses of GTCEu, but when CCL rendering the PipeRenderer, it crashed and throws NPE).
        val textureMap = event.map
        PipeRenderer.initializeRestrictor(textureMap)
    }

    companion object
    {

        @Suppress("KotlinConstantConditions")
        @SubscribeEvent
        fun addItemTooltips(event: ItemTooltipEvent)
        {
            val stack = event.itemStack
            // Add material formulas for sheetedFrameX.
            if (stack.item is SheetedFrameItemBlock)
            {
                val unificationEntry = OreDictUnifier.getUnificationEntry(stack)
                if (unificationEntry?.material != null)
                {
                    if (unificationEntry.material?.chemicalFormula != null
                        && unificationEntry.material?.chemicalFormula!!.isNotEmpty())
                    {
                        event.toolTip.add("§e" + unificationEntry.material!!.chemicalFormula)
                    }
                }
            }
            // Add material formulas for wallGtX
            else if (stack.item is MaterialItemBlock
                && stack.item.registryName!!.path.startsWith("meta_block_wall_gt"))
            {
                val unificationEntry = OreDictUnifier.getUnificationEntry(stack)
                if (unificationEntry?.material != null)
                {
                    if (unificationEntry.material?.chemicalFormula != null
                        && unificationEntry.material?.chemicalFormula!!.isNotEmpty())
                    {
                        event.toolTip.add("§e" + unificationEntry.material!!.chemicalFormula)
                    }
                }
            }
        }

    }

}