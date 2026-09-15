package gregtechlite.gtlitecore.common.command

import gregtech.api.unification.OreDictUnifier
import net.minecraft.command.CommandBase
import net.minecraft.command.ICommandSender
import net.minecraft.server.MinecraftServer
import net.minecraft.util.text.TextComponentTranslation
import net.minecraft.util.text.TextFormatting

class CommandMaterialInfo : CommandBase()
{
    override fun execute(server: MinecraftServer, sender: ICommandSender, args: Array<out String?>)
    {
        val materialStack = OreDictUnifier.getMaterial(sender.stackInHand)
        materialStack.let { it?.material.let { material ->
            val name = TextFormatting.GREEN.toString() + material?.localizedName + TextFormatting.WHITE.toString()
            val modId = TextFormatting.YELLOW.toString() + material?.resourceLocation?.namespace
            sender.sendMessage(TextComponentTranslation("gtlitecore.command.material.info.name", name, modId))

            val color = TextFormatting.BLUE.toString() + material?.materialRGB!!.toString(16).uppercase().padStart(6, '0')
            val iconSet = TextFormatting.GRAY.toString() + material.materialIconSet!!.name
            sender.sendMessage(TextComponentTranslation("gtlitecore.command.material.info.color", color, iconSet))
        } }
    }

    override fun getName(): String = "info"

    override fun getUsage(sender: ICommandSender): String = "gtlitecore.command.material.info.usage"
}