package magicbook.gtlitecore.mixin.gregtech;

import gregtech.api.GregTechAPI;
import gregtech.api.util.CapesRegistry;
import gregtech.common.command.CommandHand;
import gregtech.common.command.CommandRecipeCheck;
import gregtech.common.command.CommandShaders;
import gregtech.common.command.worldgen.CommandWorldgen;
import gregtech.core.CoreModule;
import gregtech.core.command.internal.CommandManager;
import gregtech.datafix.command.CommandDataFix;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = CoreModule.class, remap = false)
public abstract class CoreModuleMixin
{
    /**
     * @author Magic_Sweepy
     * @reason Duplicated {@code BQuDataFixer} by default because it will cause NPE when BQu is loaded,
     *         seems BQu update break this DataFixer, so we disabled it by mixin.
     *          TODO removal it when GTCEu fix this DataFixer.
     */
    @Overwrite
    public void serverStarting(FMLServerStartingEvent event)
    {
        CommandManager commandManager = CommandManager.getInstance();
        GregTechAPI.commandManager = commandManager;
        commandManager.registerServerCommand(event);
        GregTechAPI.commandManager.addCommand(new CommandWorldgen());
        GregTechAPI.commandManager.addCommand(new CommandHand());
        GregTechAPI.commandManager.addCommand(new CommandRecipeCheck());
        GregTechAPI.commandManager.addCommand(new CommandShaders());
        GregTechAPI.commandManager.addCommand(new CommandDataFix());
        CapesRegistry.load();
    }

}
