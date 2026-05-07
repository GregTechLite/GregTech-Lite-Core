package gregtechlite.gtlitecore.mixins.gregtech;

import gregtech.api.capability.IDistinctBusController;
import gregtech.common.metatileentities.multi.electric.MetaTileEntityLargeChemicalReactor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(value = MetaTileEntityLargeChemicalReactor.class, remap = false)
public abstract class MixinMetaTileEntityLargeChemicalReactor implements IDistinctBusController {

    @Unique
    @Override
    public boolean canBeDistinct() {
        return true;
    }
}
