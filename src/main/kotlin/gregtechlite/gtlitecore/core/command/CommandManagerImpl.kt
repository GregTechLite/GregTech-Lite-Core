package gregtechlite.gtlitecore.core.command

import gregtechlite.gtlitecore.api.command.CommandManager
import net.minecraft.command.ICommand
import net.minecraftforge.fml.common.event.FMLServerStartingEvent
import net.minecraftforge.server.command.CommandTreeBase

class CommandManagerImpl : CommandManager
{
    private val command: CommandTreeBase = Commands()

    companion object
    {
        private val instance = CommandManagerImpl()

        fun getInstance(): CommandManagerImpl = instance
    }

    override fun addCommand(command: ICommand)
    {
        this.command.addSubcommand(command)
    }

    fun registerServerCommand(event: FMLServerStartingEvent)
    {
        event.registerServerCommand(command)
    }

}