package gregtechlite.gtlitecore.common.item

import gregtech.api.items.toolitem.IGTTool
import gregtech.api.items.toolitem.IGTToolDefinition
import gregtech.api.items.toolitem.ItemGTTool
import gregtech.api.items.toolitem.ToolBuilder
import gregtech.common.items.ToolItems
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.util.ActionResult
import net.minecraft.util.EnumActionResult
import net.minecraft.util.EnumHand
import net.minecraft.util.SoundEvent
import net.minecraft.world.World
import java.util.function.Supplier


class ItemMultiTool(
    domain: String,
    id: String,
    tier: Int,
    toolStats: IGTToolDefinition,
    sound: SoundEvent?,
    playSoundOnBlockDestroy: Boolean,
    toolClasses: MutableSet<String>,
    oreDict: String?,
    secondaryOreDicts: MutableList<String>,
    markerItem: Supplier<ItemStack>?,
) : ItemGTTool(
    domain,
    id,
    tier,
    toolStats,
    sound,
    playSoundOnBlockDestroy,
    toolClasses,
    oreDict,
    secondaryOreDicts,
    markerItem
) {
    companion object{
        private val MULTI_TOOLS = ArrayList<IGTTool>()

        fun register( builder: ToolBuilder<ItemMultiTool>): IGTTool
        {
            val tool = ToolItems.register(builder)
            MULTI_TOOLS.add(tool)
            return tool
        }
    }

    override fun `definition$onItemRightClick`(
        world: World,
        player: EntityPlayer,
        hand: EnumHand
    ): ActionResult<ItemStack?>? {
        val stack = player.getHeldItem(hand)
        if((stack.item is ItemMultiTool) and (player.isSneaking))
        {
            if(!world.isRemote){
                val nextTool = getNextTool().get(this.getToolMaterial(stack))
                nextTool.itemDamage = stack.itemDamage
                player.setHeldItem(hand,nextTool)
            }
            return ActionResult.newResult<ItemStack?>(EnumActionResult.SUCCESS,stack)
        }
        return ActionResult.newResult<ItemStack?>(EnumActionResult.PASS, stack)
    }

    private fun getNextTool(): IGTTool
    {
        val index = (MULTI_TOOLS.indexOf(this)+1)%MULTI_TOOLS.size
        return MULTI_TOOLS[index]
    }


    class Builder(domain: String, id: String) : ToolBuilder<ItemMultiTool>(domain, id) {
        companion object {
            fun of(domain: String, id: String): Builder {
                return Builder(domain, id)
            }
        }

        override fun supply(): Supplier<ItemMultiTool?> = Supplier {
            ItemMultiTool(
                domain,
                id,
                tier,
                toolStats,
                sound,
                playSoundOnBlockDestroy,
                toolClasses,
                oreDict,
                secondaryOreDicts,
                markerItem
            )
        }
    }
}