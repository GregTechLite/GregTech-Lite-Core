package magicbook.gtlitecore.core.command;

import com.google.common.collect.Lists;
import lombok.NoArgsConstructor;
import magicbook.gtlitecore.api.utils.GTLiteValues;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.server.command.CommandTreeBase;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
@NoArgsConstructor
public class Commands extends CommandTreeBase
{

    @Override
    public List<String> getAliases()
    {
        return Lists.newArrayList("gtlite");
    }

    @Override
    public String getUsage(ICommandSender sender)
    {
        return "gtlite.command.usage"; // TODO translation.
    }

    @Override
    public String getName()
    {
        return GTLiteValues.MODID;
    }


    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
    {
        // TODO
        super.execute(server, sender, args);
    }

}
