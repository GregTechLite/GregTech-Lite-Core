package gregtechlite.gtlitecore.mixins.gregtech;

import com.cleanroommc.modularui.api.ISyncedAction;
import com.cleanroommc.modularui.value.sync.PanelSyncManager;
import gregtech.common.covers.filter.BaseFilterContainer;
import gregtechlite.gtlitecore.mixins.hooks.Compat;
import net.minecraftforge.fml.relauncher.Side;
import org.jetbrains.annotations.ApiStatus.ScheduledForRemoval;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@ScheduledForRemoval(inVersion = "Change gregtech to our forked version and update modular ui to latest version")
@Deprecated
@Compat(modId = { "gregtech", "modularui:3.0.6" })
@Mixin(value = BaseFilterContainer.class, remap = false)
public abstract class MixinBaseFilterContainer
{
    @Redirect(method = "initUI",
              at = @At(value = "INVOKE",
                       target = "Lcom/cleanroommc/modularui/value/sync/PanelSyncManager;registerSyncedAction(Ljava/lang/String;Lcom/cleanroommc/modularui/api/ISyncedAction;)Lcom/cleanroommc/modularui/value/sync/PanelSyncManager;"))
    private PanelSyncManager redirectSyncedAction(PanelSyncManager manager, String mapKey, ISyncedAction syncedAction)
    {
        return manager.registerSyncedAction(mapKey, Side.CLIENT, syncedAction);
    }
}
