package gregtechlite.gtlitecore.core.command;

import gregtechlite.magicbook.collection.ListOps;
import lombok.NoArgsConstructor;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.server.command.CommandTreeBase;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

import static gregtechlite.gtlitecore.api.GTLiteValues.MOD_ID;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
@NoArgsConstructor
public class Commands extends CommandTreeBase
{

    @Override
    public List<String> getAliases()
    {
        return ListOps.of("gtlite");
    }

    @Override
    public String getUsage(ICommandSender sender)
    {
        return "gtlite.command.usage";
    }

    @Override
    public String getName()
    {
        return MOD_ID;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
    {
        super.execute(server, sender, args);
    }

}
