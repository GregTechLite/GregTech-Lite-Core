package gregtechlite.gtlitecore.mixins.gregtech;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import gregtech.api.pattern.TraceabilityPredicate;
import gregtech.common.metatileentities.multi.MetaTileEntityLargeBoiler;
import gregtechlite.gtlitecore.mixins.hooks.Implemented;
import org.jetbrains.annotations.ApiStatus.ScheduledForRemoval;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@ScheduledForRemoval(inVersion = "Change gregtech to our forked version")
@Deprecated
@Implemented(at = "https://github.com/GregTechCEu/GregTech/pull/2873")
@Mixin(value = MetaTileEntityLargeBoiler.class, remap = false)
public abstract class MixinMetaTileEntityLargeBoiler
{
    /**
     * @author Magic_Sweepy
     * @reason Upstream calls {@code setMinGlobalLimited(1)} without a preview count.
     */
    @ModifyExpressionValue(method = "createStructurePattern",
                           at = @At(value = "INVOKE",
                                    ordinal = 1,
                                    target = "Lgregtech/api/pattern/TraceabilityPredicate;setMinGlobalLimited(I)Lgregtech/api/pattern/TraceabilityPredicate;"))
    private TraceabilityPredicate gtlitecore$fluidImportPreviewCount(TraceabilityPredicate original)
    {
        return original.setPreviewCount(1);
    }

    /**
     * @author Magic_Sweepy
     * @reason Upstream only allows one item import bus and has no preview count.
     */
    @ModifyExpressionValue(
            method = "createStructurePattern",
            at = @At(value = "INVOKE",
                     target = "Lgregtech/api/pattern/TraceabilityPredicate;setMaxGlobalLimited(I)Lgregtech/api/pattern/TraceabilityPredicate;"))
    private TraceabilityPredicate gtlitecore$itemImportBusLimit(TraceabilityPredicate original)
    {
        return original.setMaxGlobalLimited(2).setPreviewCount(1);
    }
}
