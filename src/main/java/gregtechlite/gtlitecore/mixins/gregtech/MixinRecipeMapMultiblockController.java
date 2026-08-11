package gregtechlite.gtlitecore.mixins.gregtech;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtechlite.gtlitecore.mixins.hooks.Implemented;
import org.jetbrains.annotations.ApiStatus.ScheduledForRemoval;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;

@ScheduledForRemoval(inVersion = "Change gregtech to our forked version")
@Deprecated
@Implemented(at = "https://github.com/GregTechCEu/GregTech/pull/2769")
@Mixin(value = RecipeMapMultiblockController.class, remap = false)
public abstract class MixinRecipeMapMultiblockController
{
    @WrapOperation(method = "extendedImportFluidList(Lgregtech/api/capability/IMultipleTankHandler;)Lgregtech/api/capability/IMultipleTankHandler;",
                   at = @At(value = "INVOKE",
                            target = "Ljava/util/List;contains(Ljava/lang/Object;)Z"))
    private <E> boolean containsOrWrapped(List<E> tanks, E candidate, Operation<Boolean> original)
    {
        if (candidate instanceof IMultipleTankHandler.ITankEntry
                && gtlitecore$isTankAlreadyPresent(tanks, (IMultipleTankHandler.ITankEntry) candidate))
        {
            return true;
        }
        return original.call(tanks, candidate);
    }

    @Unique
    private static <E> boolean gtlitecore$isTankAlreadyPresent(List<E> tanks, IMultipleTankHandler.ITankEntry candidate)
    {
        for (E existingTank : tanks)
        {
            if (existingTank instanceof IMultipleTankHandler.ITankEntry)
            {
                IMultipleTankHandler.ITankEntry existing = (IMultipleTankHandler.ITankEntry) existingTank;
                if (existing.getDelegate() == candidate) return true;
            }
        }
        return false;
    }
}
