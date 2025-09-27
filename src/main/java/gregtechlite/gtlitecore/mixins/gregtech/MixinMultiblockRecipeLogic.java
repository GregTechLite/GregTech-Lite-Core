package gregtechlite.gtlitecore.mixins.gregtech;

import gregtech.api.capability.INotifiableHandler;
import gregtech.api.capability.impl.AbstractRecipeLogic;
import gregtech.api.capability.impl.ItemHandlerList;
import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.multiblock.MultiblockWithDisplayBase;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.recipes.RecipeMap;
import gregtechlite.gtlitecore.api.capability.MultipleNotifiableHandler;
import gregtechlite.gtlitecore.api.mixins.Implemented;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Implemented(at = "https://github.com/GregTechCEu/GregTech/pull/2769")
@Mixin(value = MultiblockRecipeLogic.class, remap = false)
public abstract class MixinMultiblockRecipeLogic extends AbstractRecipeLogic
{

    @Shadow
    protected List<IItemHandlerModifiable> invalidatedInputList;

    public MixinMultiblockRecipeLogic(MetaTileEntity tileEntity, RecipeMap<?> recipeMap)
    {
        super(tileEntity, recipeMap);
    }

    /**
     * @reason Allowed to check notified inputs list when check working for inputs in recipe logic.
     * @author Magic_Sweepy
     */
    @Overwrite
    @Override
    protected boolean canWorkWithInputs()
    {
        MultiblockWithDisplayBase controller = (MultiblockWithDisplayBase) metaTileEntity;
        if (controller instanceof RecipeMapMultiblockController)
        {
            RecipeMapMultiblockController distinctController = (RecipeMapMultiblockController) controller;

            if (distinctController.canBeDistinct() && distinctController.isDistinct()
                    && getInputInventory().getSlots() > 0)
            {
                boolean canWork = false;
                if (invalidatedInputList.isEmpty()) return true;

                if (!metaTileEntity.getNotifiedFluidInputList().isEmpty())
                {
                    canWork = true;
                    invalidatedInputList.clear();
                    metaTileEntity.getNotifiedFluidInputList().clear();
                    metaTileEntity.getNotifiedItemInputList().clear();
                }
                else
                {
                    Iterator<IItemHandlerModifiable> notifiedIter = metaTileEntity.getNotifiedItemInputList().iterator();
                    while (notifiedIter.hasNext())
                    {
                        IItemHandlerModifiable bus = notifiedIter.next();
                        Iterator<IItemHandlerModifiable> invalidatedIter = invalidatedInputList.iterator();
                        while (invalidatedIter.hasNext()) {
                            IItemHandler invalidatedHandler = invalidatedIter.next();
                            if (invalidatedHandler instanceof MultipleNotifiableHandler)
                            {
                                MultipleNotifiableHandler multipleNotifiableHandler = (MultipleNotifiableHandler) invalidatedHandler;
                                for (INotifiableHandler notifiableHandler : multipleNotifiableHandler.getBackingNotifiers())
                                {
                                    if (notifiableHandler == bus)
                                    {
                                        canWork = true;
                                        invalidatedIter.remove();
                                        break;
                                    }
                                }
                            }
                            else if (invalidatedHandler == bus)
                            {
                                canWork = true;
                                invalidatedIter.remove();
                            }
                        }
                        notifiedIter.remove();
                    }
                }
                ArrayList<IItemHandler> flattenedHandlers = new ArrayList<>();
                for (IItemHandler itemHandler : getInputBuses())
                {
                    if (itemHandler instanceof ItemHandlerList)
                    {
                        flattenedHandlers.addAll(((ItemHandlerList) itemHandler).getBackingHandlers());
                    }
                    flattenedHandlers.add(itemHandler);
                }

                // noinspection SlowListContainsAll, SuspiciousMethodCalls
                if (!invalidatedInputList.containsAll(flattenedHandlers))
                {
                    canWork = true;
                }

                return canWork;
            }
        }
        return super.canWorkWithInputs();
    }

    @Shadow
    protected abstract List<IItemHandlerModifiable> getInputBuses();

}
