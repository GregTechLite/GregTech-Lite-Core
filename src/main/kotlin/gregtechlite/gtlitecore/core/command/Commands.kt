package gregtechlite.gtlitecore.core.command

import gregtechlite.gtlitecore.api.MOD_ID
import net.minecraft.command.ICommandSender
import net.minecraft.server.MinecraftServer
import net.minecraftforge.server.command.CommandTreeBase

class Commands : CommandTreeBase()
{

    override fun getAliases(): List<String> = listOf("gtlite")

    override fun getUsage(sender: ICommandSender): String = "gtlite.command.usage"

    override fun getName(): String = MOD_ID

    override fun execute(server: MinecraftServer, sender: ICommandSender, args: Array<out String>)
    {
        super.execute(server, sender, args)
    }
}