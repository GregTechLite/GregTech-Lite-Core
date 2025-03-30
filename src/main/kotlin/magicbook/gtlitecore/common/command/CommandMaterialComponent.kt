package magicbook.gtlitecore.common.command

import gregtech.api.unification.OreDictUnifier
import net.minecraft.command.CommandBase
import net.minecraft.command.CommandException
import net.minecraft.command.ICommandSender
import net.minecraft.entity.player.EntityPlayerMP
import net.minecraft.server.MinecraftServer
import net.minecraft.util.text.TextComponentTranslation
import one.util.streamex.StreamEx

@Suppress("MISSING_DEPENDENCY_CLASS")
class CommandMaterialComponent : CommandBase()
{

    override fun getName() = "component"

    override fun getUsage(sender: ICommandSender) = "gtlitecore.command.component.usage"

    override fun execute(server: MinecraftServer, sender: ICommandSender,
                         args: Array<out String>)
    {
        if (sender is EntityPlayerMP)
        {
            var stackInHand = sender.heldItemMainhand
            if (stackInHand.isEmpty)
            {
                stackInHand = sender.heldItemOffhand
                if (stackInHand.isEmpty)
                    throw CommandException("gtlitecore.command.component.no_item")
            }

            val materialStack = OreDictUnifier.getMaterial(stackInHand)
            if (materialStack?.material == null
                || materialStack.material.materialComponents == null
                || materialStack.material.materialComponents.isEmpty())
            {
                throw CommandException("gtlitecore.command.component.no_component")
            }

            // Format: [materialStack ('materialName') * n, ...]
            val components = StreamEx.of(materialStack.material.materialComponents)
                .map { mat ->
                    if (mat.amount > 1)
                        "materialStack ('${mat.material.localizedName}')×${mat.amount}"
                    else
                        "materialStack ('${mat.material.localizedName}')"
                }
                .joining(", ", "[", "]")
            sender.sendMessage(TextComponentTranslation("gtlitecore.command.component.print", components))
        }
        else
        {
            throw CommandException("gtlitecore.command.component.not_player")
        }

    }

}