package gregtechlite.gtlitecore.common.command

import net.minecraft.command.ICommandSender
import net.minecraftforge.server.command.CommandTreeBase

class CommandTreeMaterial : CommandTreeBase()
{
    init
    {
        addSubcommand(CommandMaterialInfo())
        addSubcommand(CommandMaterialComponent())
    }

    override fun getName(): String = "material"

    override fun getUsage(sender: ICommandSender): String = "gtlitecore.command.material.usage"
}