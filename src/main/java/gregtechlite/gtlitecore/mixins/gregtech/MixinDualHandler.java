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
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.util.Collection;
import java.util.List;

@ScheduledForRemoval(inVersion = "Change gregtech to our forked version")
@Deprecated
@Implemented(at = "https://github.com/GregTechCEu/GregTech/pull/2769")
@Mixin(value = DualHandler.class, remap = false)
public abstract class MixinDualHandler implements INotifiableHandler, MultipleNotifiableHandler
{
    @Shadow
    protected @NotNull IItemHandlerModifiable itemDelegate;

    @Shadow
    protected @NotNull IMultipleTankHandler fluidDelegate;

    @Shadow
    List<MetaTileEntity> notifiableEntities;

    /**
     * @reason Rewrite the notifiable {@code metaTileEntity} logic with {@code MultipleNotifiableHandler}.
     * @author Magic_Sweepy
     */
    @Overwrite
    @Override
    public void addNotifiableMetaTileEntity(MetaTileEntity metaTileEntity)
    {
        if (metaTileEntity == null || this.notifiableEntities.contains(metaTileEntity)) return;
        this.notifiableEntities.add(metaTileEntity);

        if (getItemDelegate() instanceof INotifiableHandler)
        {
            INotifiableHandler handler = (INotifiableHandler) getItemDelegate();
            handler.addNotifiableMetaTileEntity(metaTileEntity);
        }
        else if (getItemDelegate() instanceof ItemHandlerList)
        {
            ItemHandlerList list = (ItemHandlerList) getItemDelegate();
            for (IItemHandler handler : list.getBackingHandlers())
            {
                if (handler instanceof INotifiableHandler)
                {
                    INotifiableHandler notifiableHandler = (INotifiableHandler) handler;
                    notifiableHandler.addNotifiableMetaTileEntity(metaTileEntity);
                }
            }
        }

        for (IMultipleTankHandler.ITankEntry entry : getFluidDelegate())
        {
            if (entry.getDelegate() instanceof INotifiableHandler)
            {
                INotifiableHandler handler = (INotifiableHandler) entry.getDelegate();
                handler.addNotifiableMetaTileEntity(metaTileEntity);
            }
        }
    }

    /**
     * @reason Rewrite the notifiable {@code metaTileEntity} logic with {@code MultipleNotifiableHandler}.
     * @author Magic_Sweepy
     */
    @Overwrite
    @Override
    public void removeNotifiableMetaTileEntity(MetaTileEntity metaTileEntity)
    {
        this.notifiableEntities.remove(metaTileEntity);

        if (getItemDelegate() instanceof INotifiableHandler)
        {
            INotifiableHandler handler = (INotifiableHandler) getItemDelegate();
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
                    notifiableHandler.removeNotifiableMetaTileEntity(metaTileEntity);
                }
            }
        }

        for (IMultipleTankHandler.ITankEntry entry : getFluidDelegate())
        {
            if (entry.getDelegate() instanceof INotifiableHandler)
            {
                INotifiableHandler handler = (INotifiableHandler) entry.getDelegate();
                handler.removeNotifiableMetaTileEntity(metaTileEntity);
            }
        }
    }

    @SuppressWarnings("AddedMixinMembersNamePattern")
    @Unique
    @Override
    public @NotNull Collection<INotifiableHandler> getBackingNotifiers()
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
    public abstract @NotNull IItemHandlerModifiable getItemDelegate();

    @Shadow
    public abstract @NotNull IMultipleTankHandler getFluidDelegate();
}
