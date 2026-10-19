package gregtechlite.gtlitecore.mixins.gregtech;

import com.google.common.collect.ImmutableList;
import gregtech.api.capability.DualHandler;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.capability.INotifiableHandler;
import gregtech.api.capability.impl.ItemHandlerList;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtechlite.gtlitecore.api.capability.MultipleNotifiableHandler;
import gregtechlite.gtlitecore.mixins.hooks.Implemented;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import org.jetbrains.annotations.ApiStatus.ScheduledForRemoval;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Collection;
import java.util.List;

@ScheduledForRemoval(inVersion = "Change gregtech to our forked version")
@Deprecated
@Implemented(at = "https://github.com/GregTechCEu/GregTech/pull/2769")
@Mixin(value = DualHandler.class, remap = false)
public abstract class MixinDualHandler implements INotifiableHandler, MultipleNotifiableHandler
{
    @Shadow
    protected IItemHandlerModifiable itemDelegate;

    @Shadow
    protected IMultipleTankHandler fluidDelegate;

    @Shadow
    List<MetaTileEntity> notifiableEntities;

    /**
     * Whether the mte actually appended by upstream {@code addNotifiableMetaTileEntity} call,
     * so the tail injector can skip the early-return case via this param hint.
     */
    @Unique
    private boolean gtlitecore$cancelInjector;

    @Inject(method = "addNotifiableMetaTileEntity",
            at = @At("HEAD"))
    private void gtlitecore$markRequireCancel(@Nullable MetaTileEntity metaTileEntity, CallbackInfo callbackInfo)
    {
        gtlitecore$cancelInjector = metaTileEntity != null && !notifiableEntities.contains(metaTileEntity);
    }

    /**
     * @author Magic_Sweepy
     * @reason Rewrite the notifiable {@code metaTileEntity} logic with {@link MultipleNotifiableHandler}.
     */
    @Inject(method = "addNotifiableMetaTileEntity",
            at = @At("TAIL"))
    private void gtlitecore$addToBackingHandlers(@Nullable MetaTileEntity metaTileEntity, CallbackInfo callbackInfo)
    {
        if (!gtlitecore$cancelInjector) return;
        gtlitecore$forEachBackingHandler(metaTileEntity, true);
    }

    @Inject(method = "removeNotifiableMetaTileEntity", at = @At("TAIL"))
    private void gtlitecore$removeFromBackingHandlers(MetaTileEntity metaTileEntity, CallbackInfo callbackInfo)
    {
        gtlitecore$forEachBackingHandler(metaTileEntity, false);
    }

    @Unique
    private void gtlitecore$forEachBackingHandler(@Nullable MetaTileEntity metaTileEntity, boolean shouldAddToHandlers)
    {
        if (metaTileEntity == null) return;

        if (getItemDelegate() instanceof INotifiableHandler)
        {
            INotifiableHandler handler = (INotifiableHandler) getItemDelegate();
            if (shouldAddToHandlers)
                handler.addNotifiableMetaTileEntity(metaTileEntity);
            else
                handler.removeNotifiableMetaTileEntity(metaTileEntity);
        }
        else if (getItemDelegate() instanceof ItemHandlerList)
        {
            ItemHandlerList list = (ItemHandlerList) getItemDelegate();
            for (IItemHandler handler : list.getBackingHandlers())
            {
                if (handler instanceof INotifiableHandler)
                {
                    INotifiableHandler notifiableHandler = (INotifiableHandler) handler;
                    if (shouldAddToHandlers)
                        notifiableHandler.addNotifiableMetaTileEntity(metaTileEntity);
                    else
                        notifiableHandler.removeNotifiableMetaTileEntity(metaTileEntity);
                }
            }
        }

        for (IMultipleTankHandler.ITankEntry entry : getFluidDelegate())
        {
            if (entry.getDelegate() instanceof INotifiableHandler)
            {
                INotifiableHandler handler = (INotifiableHandler) entry.getDelegate();
                if (shouldAddToHandlers)
                    handler.addNotifiableMetaTileEntity(metaTileEntity);
                else
                    handler.removeNotifiableMetaTileEntity(metaTileEntity);
            }
        }
    }

    @SuppressWarnings("AddedMixinMembersNamePattern")
    @Unique
    @Override
    public Collection<INotifiableHandler> getBackingNotifiers()
    {
        ImmutableList.Builder<INotifiableHandler> handlerList = ImmutableList.builder();

        handlerList.add(this);

        if (itemDelegate instanceof MultipleNotifiableHandler)
        {
            MultipleNotifiableHandler multipleNotifiableHandler = (MultipleNotifiableHandler) itemDelegate;
            handlerList.addAll(multipleNotifiableHandler.getBackingNotifiers());
        }
        else if (itemDelegate instanceof INotifiableHandler)
        {
            INotifiableHandler notifiableHandler = (INotifiableHandler) itemDelegate;
            handlerList.add(notifiableHandler);
        }

        for (IMultipleTankHandler.ITankEntry tank : fluidDelegate)
        {
            if (tank instanceof INotifiableHandler)
            {
                INotifiableHandler notifiableHandler = (INotifiableHandler) tank;
                handlerList.add(notifiableHandler);
            }
        }

        return handlerList.build();
    }

    @Shadow
    public abstract IItemHandlerModifiable getItemDelegate();

    @Shadow
    public abstract IMultipleTankHandler getFluidDelegate();
}
