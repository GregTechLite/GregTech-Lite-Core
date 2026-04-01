package gregtechlite.gtlitecore.mixins.gregtech;

import com.cleanroommc.modularui.api.ISyncedAction;
import com.cleanroommc.modularui.value.sync.PanelSyncManager;
import gregtech.common.covers.filter.BaseFilterContainer;
import gregtechlite.gtlitecore.mixins.Compat;
import net.minecraftforge.fml.relauncher.Side;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Compat(modId = { "gregtech", "modularui" })
@Mixin(value = BaseFilterContainer.class, remap = false)
public abstract class MixinBaseFilterContainer {

    @Redirect(method = "initUI",
              at = @At(value = "INVOKE",
                       target = "Lcom/cleanroommc/modularui/value/sync/PanelSyncManager;registerSyncedAction(Ljava/lang/String;Lcom/cleanroommc/modularui/api/ISyncedAction;)Lcom/cleanroommc/modularui/value/sync/PanelSyncManager;"))
    private PanelSyncManager redirectSyncedAction(PanelSyncManager manager, String mapKey, ISyncedAction syncedAction) {
        return manager.registerSyncedAction(mapKey, Side.CLIENT, syncedAction);
    }
}
