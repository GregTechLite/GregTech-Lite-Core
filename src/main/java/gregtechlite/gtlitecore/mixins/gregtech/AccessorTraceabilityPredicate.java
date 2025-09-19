package gregtechlite.gtlitecore.mixins.gregtech;

import gregtech.api.pattern.TraceabilityPredicate;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = TraceabilityPredicate.class, remap = false)
public interface AccessorTraceabilityPredicate
{
    @Accessor(value = "isCenter")
    boolean isCenter();
}
