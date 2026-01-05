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
import gregtechlite.gtlitecore.mixins.Implemented;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.IFluidTank;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.util.ArrayList;
import java.util.List;

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
                            if (tank instanceof INotifiableHandler)
                            {
                                INotifiableHandler notifiableTank = (INotifiableHandler) tank;
                                notifiableHandlers.add(notifiableTank);
                            }
                        }
                    }
                    else if (handler instanceof INotifiableHandler)
                    {
                        INotifiableHandler notifiableHandler = (INotifiableHandler) handler;
                        notifiableHandlers.add(notifiableHandler);
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
                        if (fluidTank instanceof IMultipleTankHandler.ITankEntry)
                        {
                            IMultipleTankHandler.ITankEntry entry = (IMultipleTankHandler.ITankEntry) fluidTank;
                            fluidTank = entry.getDelegate();
                        }
                        if (fluidTank instanceof INotifiableHandler)
                        {
                            notifiableHandlers.add((INotifiableHandler) fluidTank);
                        }
                    }
                }
            }
        }

        return notifiableHandlers;
    }

    @Shadow
    protected abstract List<INotifiableHandler> getItemHandlers();

    @Shadow
    protected abstract FluidTankList getFluidHandlers();

}
