package gregtechlite.gtlitecore.mixins.modularui;

import com.cleanroommc.modularui.value.sync.ISyncRegistrar;
import com.cleanroommc.modularui.value.sync.PanelSyncManager;
import com.cleanroommc.modularui.value.sync.SyncHandler;
import com.cleanroommc.modularui.widgets.slot.SlotGroup;
import gregtechlite.gtlitecore.mixins.Compat;
import gregtechlite.gtlitecore.mixins.Implemented;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Implemented(at = "https://github.com/GregTechCEu/GregTech/pull/2900")
@Compat(modId = { "gregtech", "modularui" })
@Mixin(value = PanelSyncManager.class, remap = false)
public abstract class MixinPanelSyncManager implements ISyncRegistrar<PanelSyncManager>
{

    @Unique
    public PanelSyncManager syncValue(String name, SyncHandler syncHandler)
    {
        return syncValue(name, 0, syncHandler);
    }

    @Unique
    public PanelSyncManager registerSlotGroup(String name, int rowSize)
    {
        return registerSlotGroup(new SlotGroup(name, rowSize, 100, true));
    }

    @Shadow
    public abstract PanelSyncManager registerSlotGroup(SlotGroup slotGroup);

}
