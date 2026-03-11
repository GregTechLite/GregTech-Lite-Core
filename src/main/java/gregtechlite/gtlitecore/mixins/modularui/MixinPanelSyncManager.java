package gregtechlite.gtlitecore.mixins.modularui;

import com.cleanroommc.modularui.value.sync.ISyncRegistrar;
import com.cleanroommc.modularui.value.sync.PanelSyncManager;
import com.cleanroommc.modularui.value.sync.SyncHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(value = PanelSyncManager.class, remap = false)
public abstract class MixinPanelSyncManager implements ISyncRegistrar<PanelSyncManager>
{

    @Unique
    public PanelSyncManager syncValue(String name, SyncHandler syncHandler)
    {
        return syncValue(name, 0, syncHandler);
    }

}
