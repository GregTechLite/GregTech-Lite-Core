package magicbook.gtlitecore.client

import gregtech.api.unification.OreDictUnifier
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
        if (stack.item !is SheetedFrameItemBlock)
            return

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