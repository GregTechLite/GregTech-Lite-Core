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
import org.jetbrains.annotations.ApiStatus;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

/**
 * Bug Fix: Disabled loading incorrect {@code BQuDataFixer} class in {@code CoreModule}.
 *
 * @apiNote The incorrect class {@code BQuDataFixer} is initialized in {@code CoreModule}, this
 *          data fixer will cause {@code NullPointerException} when the save data has Gregtech mod,
 *          and will throw this exception when player open its save. This bug maybe caused by
 *          update of BQu, because GTCEu do not provide disabled of this data fixer, so we fix
 *          this bug by disabled it initialization in {@code CoreModule}.
 *
 * @deprecated When GTCEu update and fix this problem.
 */
@ApiStatus.ScheduledForRemoval(inVersion = "Next GTCEu Update")
@Deprecated
@Mixin(value = CoreModule.class, remap = false)
public abstract class CoreModuleMixin
{

    /**
     * @deprecated When GTCEu update and fix this problem.
     *
     * @author Magic_Sweepy
     * @reason Duplicated Gregtech {@code CommandManager} initialization and disabled
     *         incorrect {@code BQuDataFixer} initialization.
     */
    @ApiStatus.ScheduledForRemoval(inVersion = "Next GTCEu Update")
    @Deprecated
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
