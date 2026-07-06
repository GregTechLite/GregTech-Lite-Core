package gregtechlite.gtlitecore.mixins.gregtech;

import gregtech.api.metatileentity.multiblock.MultiblockWithDisplayBase;
import gregtech.api.pattern.TraceabilityPredicate;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value = MultiblockWithDisplayBase.class, remap = false)
public interface AccessorMultiblockWithDisplayBase {

    @Invoker("maintenancePredicate")
    TraceabilityPredicate invokeMaintenancePredicate();
}
