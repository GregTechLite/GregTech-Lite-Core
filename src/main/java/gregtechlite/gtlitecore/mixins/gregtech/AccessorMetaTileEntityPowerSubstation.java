package gregtechlite.gtlitecore.mixins.gregtech;

import gregtech.common.metatileentities.multi.electric.MetaTileEntityPowerSubstation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.function.Supplier;

@Mixin(value = MetaTileEntityPowerSubstation.class, remap = false)
public interface AccessorMetaTileEntityPowerSubstation
{
    @Accessor("BATTERY_PREDICATE")
    @SuppressWarnings("rawtypes")
    Supplier getBatteryPredicate();
}
