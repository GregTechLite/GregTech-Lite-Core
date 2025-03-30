package magicbook.gtlitecore.core.command;

import magicbook.gtlitecore.api.command.ICommandManager;
import net.minecraft.command.ICommand;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.server.command.CommandTreeBase;
import org.jetbrains.annotations.NotNull;

public class CommandManager implements ICommandManager
{

    private static final CommandManager INSTANCE = new CommandManager();
    private final CommandTreeBase command = new Commands();

    private CommandManager() {}

    public static CommandManager getInstance()
    {
        return INSTANCE;
    }

    @Override
    public void addCommand(ICommand command)
    {
        this.command.addSubcommand(command);
    }

    public void registerServerCommand(FMLServerStartingEvent event)
    {
        event.registerServerCommand(this.command);
    }

}
