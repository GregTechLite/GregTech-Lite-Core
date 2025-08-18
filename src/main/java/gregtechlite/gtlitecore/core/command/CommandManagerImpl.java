package gregtechlite.gtlitecore.core.command;

import gregtechlite.gtlitecore.api.command.CommandManager;
import net.minecraft.command.ICommand;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.server.command.CommandTreeBase;
import org.jetbrains.annotations.ApiStatus.Internal;

@Internal
public class CommandManagerImpl implements CommandManager
{

    private static final CommandManagerImpl INSTANCE = new CommandManagerImpl();
    private final CommandTreeBase command = new Commands();

    private CommandManagerImpl() {}

    public static CommandManagerImpl getInstance()
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
