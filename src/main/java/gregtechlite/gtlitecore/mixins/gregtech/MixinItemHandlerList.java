package gregtechlite.gtlitecore.mixins.gregtech;

import com.google.common.collect.ImmutableList;
import gregtech.api.capability.INotifiableHandler;
import gregtech.api.capability.impl.ItemHandlerList;
import gregtechlite.gtlitecore.api.capability.MultipleNotifiableHandler;
import gregtechlite.gtlitecore.mixins.hooks.Implemented;
import net.minecraftforge.items.IItemHandler;
import org.jetbrains.annotations.ApiStatus.ScheduledForRemoval;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.util.Collection;

@ScheduledForRemoval(inVersion = "Change gregtech to our forked version")
@Deprecated
@Implemented(at = "https://github.com/GregTechCEu/GregTech/pull/2769")
@Mixin(value = ItemHandlerList.class, remap = false)
public abstract class MixinItemHandlerList implements MultipleNotifiableHandler
{
    @Shadow
    public abstract @NotNull Collection<IItemHandler> getBackingHandlers();

    @SuppressWarnings("AddedMixinMembersNamePattern")
    @Unique
    @Override
    public @NotNull Collection<INotifiableHandler> getBackingNotifiers() {
        ImmutableList.Builder<INotifiableHandler> notifiableHandlers = ImmutableList.builder();

        for (IItemHandler handler : getBackingHandlers())
        {
            if (handler instanceof INotifiableHandler)
            {
                INotifiableHandler notifiableHandler = (INotifiableHandler) handler;
                notifiableHandlers.add(notifiableHandler);
            }
        }

        return notifiableHandlers.build();
    }
}
