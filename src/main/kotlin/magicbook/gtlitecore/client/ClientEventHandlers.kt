package magicbook.gtlitecore.client

import gregtech.api.unification.OreDictUnifier
import gregtech.common.blocks.MaterialItemBlock
import magicbook.gtlitecore.common.block.itemblocks.SheetedFrameItemBlock
import net.minecraftforge.event.entity.player.ItemTooltipEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

@Suppress("MISSING_DEPENDENCY_CLASS")
@SideOnly(Side.CLIENT)
class ClientEventHandlers
{

    @SubscribeEvent
    fun addMaterialFormula(event: ItemTooltipEvent)
    {
        val stack = event.itemStack
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
        else if (stack.item is MaterialItemBlock
            && stack.item.registryName!!.path.startsWith("meta_block_wall_gt"))
        {
            val tUnificationEntry = OreDictUnifier.getUnificationEntry(stack)
            if (tUnificationEntry?.material != null)
            {
                if (tUnificationEntry.material?.chemicalFormula != null
                    && tUnificationEntry.material?.chemicalFormula!!.isNotEmpty())
                {
                    event.toolTip.add("§e" + tUnificationEntry.material!!.chemicalFormula)
                }
            }
        }

    }

}