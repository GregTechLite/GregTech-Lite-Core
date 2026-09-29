package gregtechlite.gtlitecore.common.command

import net.minecraft.command.CommandException
import net.minecraft.command.ICommandSender
import net.minecraft.entity.player.EntityPlayerMP
import net.minecraft.item.ItemStack

val ICommandSender.stackInHand: ItemStack
    get()
    {
        if (this !is EntityPlayerMP) throw CommandException("gtlitecore.command.error.not_player")
        var stackInHand = heldItemMainhand
        if (stackInHand.isEmpty)
        {
            stackInHand = heldItemOffhand
            if (stackInHand.isEmpty)
            {
                throw CommandException("gtlitecore.command.error.no_item")
            }
        }
        return stackInHand
    }
