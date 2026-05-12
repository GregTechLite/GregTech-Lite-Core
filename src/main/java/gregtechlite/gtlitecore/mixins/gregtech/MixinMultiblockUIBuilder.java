package gregtechlite.gtlitecore.mixins.gregtech;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import gregtech.api.metatileentity.multiblock.ui.MultiblockUIBuilder;
import gregtechlite.gtlitecore.mixins.Implemented;
import net.minecraftforge.fluids.FluidStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

/**
 * @deprecated
 */
@Deprecated
@Implemented(at = "https://github.com/GregTechCEu/GregTech/pull/2913")
@Mixin(value = MultiblockUIBuilder.class, remap = false)
public abstract class MixinMultiblockUIBuilder
{

    /**
     * Adds fluid output display with parallel number count.
     * <p>
     * This method captures the local parameter {@code p} (the parallel number from the ui syncer) and add it to fluid
     * output line method call, now the fluid output display consistent with the rest of the output display logic by
     * scaling it with the performed parallel count as well, i.e.
     * <pre>{@code
     *     // Original:
     *     addFluidOutputLine(stack, fluidMap.getInt(stack), maxProgress);
     *     // Now:
     *     addFluidOutputLine(stack, (long) fluidMap.getInt(stack) * p, maxProgress);
     * }</pre>
     *
     * @param instance     The target class which be mixined.
     * @param stack        A {@link FluidStack} to display on the ui of the machine.
     * @param count        The count of the {@code stack}.
     * @param recipeLength The recipe length of the current recipe (on ticks).
     * @param methodOp     The original method {@code addFluidOutputLine} call in {@code addRecipeOutputLine} method.
     * @param p            The parallel number of the machine from the {@code InternalSyncer}.
     */
    @WrapOperation(method = "addRecipeOutputLine(Lgregtech/api/capability/impl/AbstractRecipeLogic;I)Lgregtech/api/metatileentity/multiblock/ui/MultiblockUIBuilder;",
                   at = @At(value = "INVOKE",
                            target = "Lgregtech/api/metatileentity/multiblock/ui/MultiblockUIBuilder;addFluidOutputLine(Lnet/minecraftforge/fluids/FluidStack;JI)V"))
    private void makeFluidOutputParallel(MultiblockUIBuilder instance,
                                         FluidStack stack,
                                         long count,
                                         int recipeLength,
                                         Operation<Void> methodOp,
                                         @Local(index = 6) int p)
    {
        long newCount = count * p;
        methodOp.call(instance, stack, newCount, recipeLength);
    }

}
