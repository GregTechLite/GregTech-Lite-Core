package gregtechlite.gtlitecore.mixins.gregtech;

import com.google.common.collect.ImmutableList;
import gregtech.api.capability.INotifiableHandler;
import gregtech.api.capability.impl.ItemHandlerList;
import gregtechlite.gtlitecore.api.capability.MultipleNotifiableHandler;
import gregtechlite.gtlitecore.mixins.Implemented;
import net.minecraftforge.items.IItemHandler;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Collection;

@Implemented(at = "https://github.com/GregTechCEu/GregTech/pull/2769")
@Mixin(value = ItemHandlerList.class, remap = false)
public abstract class MixinItemHandlerList implements MultipleNotifiableHandler
{

    @Shadow
    @NotNull
    public abstract Collection<IItemHandler> getBackingHandlers();

    @SuppressWarnings("AddedMixinMembersNamePattern")
    @NotNull
    @Override
    public Collection<INotifiableHandler> getBackingNotifiers() {
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
