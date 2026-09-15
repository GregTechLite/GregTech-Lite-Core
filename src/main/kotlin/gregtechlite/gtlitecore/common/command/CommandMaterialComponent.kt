package gregtechlite.gtlitecore.common.command

import gregtech.api.unification.OreDictUnifier
import net.minecraft.command.CommandBase
import net.minecraft.command.CommandException
import net.minecraft.command.ICommandSender
import net.minecraft.server.MinecraftServer
import net.minecraft.util.text.TextComponentTranslation
import net.minecraft.util.text.TextFormatting

class CommandMaterialComponent : CommandBase()
{
    override fun execute(server: MinecraftServer, sender: ICommandSender, args: Array<out String?>)
    {
        val materialStack = OreDictUnifier.getMaterial(sender.stackInHand)
        if (materialStack?.material == null
            || materialStack.material.materialComponents == null
            || materialStack.material.materialComponents.isEmpty())
        {
            throw CommandException("gtlitecore.command.error.no_component")
        }

        materialStack.material.chemicalFormula?.let {
            sender.sendMessage(TextComponentTranslation("gtlitecore.command.material.component.formula",
                                                        TextFormatting.YELLOW.toString() + it))
        }

        val components = materialStack.material.materialComponents.joinToString(", ") {
            if (it.amount > 1)
                TextFormatting.GREEN.toString() + "${it.amount}x " + TextFormatting.GOLD.toString() + "${it.material.localizedName}"
            else
                TextFormatting.GOLD.toString() + "${it.material.localizedName}"
        }
        sender.sendMessage(TextComponentTranslation("gtlitecore.command.material.component.components", components))
    }

    override fun getName(): String = "component"

    override fun getUsage(sender: ICommandSender): String = "gtlitecore.command.material.component.usage"
}