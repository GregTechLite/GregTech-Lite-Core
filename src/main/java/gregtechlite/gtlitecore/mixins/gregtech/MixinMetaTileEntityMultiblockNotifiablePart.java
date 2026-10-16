package gregtechlite.gtlitecore.mixins.gregtech;

import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.capability.INotifiableHandler;
import gregtech.api.capability.impl.FluidTankList;
import gregtech.api.metatileentity.multiblock.AbilityInstances;
import gregtech.api.metatileentity.multiblock.IMultiblockAbilityPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityMultiblockNotifiablePart;
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityMultiblockPart;
import gregtechlite.gtlitecore.api.capability.MultipleNotifiableHandler;
import gregtechlite.gtlitecore.mixins.hooks.Implemented;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.IFluidTank;
import org.jetbrains.annotations.ApiStatus.ScheduledForRemoval;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.util.ArrayList;
import java.util.List;

@ScheduledForRemoval(inVersion = "Change gregtech to our forked version")
@Deprecated
@Implemented(at = "https://github.com/GregTechCEu/GregTech/pull/2769")
@Mixin(value = MetaTileEntityMultiblockNotifiablePart.class, remap = false)
public abstract class MixinMetaTileEntityMultiblockNotifiablePart extends MetaTileEntityMultiblockPart
{
    @Shadow
    @Final
    protected boolean isExportHatch;

    public MixinMetaTileEntityMultiblockNotifiablePart(ResourceLocation metaTileEntityId, int tier)
    {
        super(metaTileEntityId, tier);
    }

    /**
     * @author Magic_Sweepy
     * @reason Let {@link #addToMultiBlock} method read {@link #gtlitecore$getPartHandlers} to process dual handlers.
     */
    @Overwrite
    @Override
    public void addToMultiBlock(MultiblockControllerBase controllerBase)
    {
        super.addToMultiBlock(controllerBase);
        List<INotifiableHandler> handlerList = gtlitecore$getPartHandlers();
        for (INotifiableHandler handler : handlerList)
        {
            handler.addNotifiableMetaTileEntity(controllerBase);
            handler.addToNotifiedList(this, handler, isExportHatch);
        }
    }

    /**
     * @author Magic_Sweepy
     * @reason Let {@link #removeFromMultiBlock} method read {@link #gtlitecore$getPartHandlers} to process dual handlers.
     */
    @Overwrite
    @Override
    public void removeFromMultiBlock(MultiblockControllerBase controllerBase)
    {
        super.removeFromMultiBlock(controllerBase);
        List<INotifiableHandler> handlerList = gtlitecore$getPartHandlers();
        for (INotifiableHandler handler : handlerList)
        {
            handler.removeNotifiableMetaTileEntity(controllerBase);
        }
    }

    @Unique
    private List<INotifiableHandler> gtlitecore$getPartHandlers()
    {
        List<INotifiableHandler> notifiableHandlers = new ArrayList<>();

        if (this instanceof IMultiblockAbilityPart<?>)
        {
            IMultiblockAbilityPart<?> abilityPart = (IMultiblockAbilityPart<?>) this;

            List<MultiblockAbility<?>> abilities = abilityPart.getAbilities();
            for (MultiblockAbility<?> ability : abilities)
            {
                AbilityInstances instances = new AbilityInstances(ability);
                abilityPart.registerAbilities(instances);
                for (Object handler : instances)
                {
                    if (handler instanceof MultipleNotifiableHandler)
                    {
                        MultipleNotifiableHandler multipleNotifiableHandler = (MultipleNotifiableHandler) handler;
                        notifiableHandlers.addAll(multipleNotifiableHandler.getBackingNotifiers());
                    }
                    else if (handler instanceof IMultipleTankHandler)
                    {
                        IMultipleTankHandler multipleTankHandler = (IMultipleTankHandler) handler;
                        for (IFluidTank tank : multipleTankHandler.getFluidTanks())
                        {
                            INotifiableHandler notifiableTank = gtlitecore$unwrapTank(tank);
                            if (notifiableTank != null)
                            {
                                notifiableHandlers.add(notifiableTank);
                            }
                        }
                    }
                    else
                    {
                        INotifiableHandler notifiableHandler = gtlitecore$unwrapTank(handler);
                        if (notifiableHandler != null)
                        {
                            notifiableHandlers.add(notifiableHandler);
                        }
                    }
                }
            }
        }
        else
        {
            for (INotifiableHandler notifier : getItemHandlers())
            {
                if (notifier.size() > 0)
                {
                    notifiableHandlers.add(notifier);
                }
            }

            if (this.fluidInventory.getTankProperties().length > 0)
            {
                FluidTankList fluidTankList = getFluidHandlers();
                if (fluidTankList != null)
                {
                    for (IFluidTank fluidTank : fluidTankList)
                    {
                        INotifiableHandler notifiableTank = gtlitecore$unwrapTank(fluidTank);
                        if (notifiableTank != null)
                        {
                            notifiableHandlers.add(notifiableTank);
                        }
                    }
                }
            }
        }

        return notifiableHandlers;
    }

    /**
     * Resolve the underlying notifiable handler from a tank entry, unwrapping
     * {@link IMultipleTankHandler.ITankEntry} wrappers (such as the ones registered by
     * {@code MetaTileEntityMultiFluidHatch}) back to their delegate tank.
     */
    @Unique
    private static INotifiableHandler gtlitecore$unwrapTank(Object handler)
    {
        if (handler instanceof IMultipleTankHandler.ITankEntry)
        {
            handler = ((IMultipleTankHandler.ITankEntry) handler).getDelegate();
        }
        return handler instanceof INotifiableHandler ? (INotifiableHandler) handler : null;
    }

    @Shadow
    protected abstract List<INotifiableHandler> getItemHandlers();

    @Shadow
    protected abstract FluidTankList getFluidHandlers();
}
