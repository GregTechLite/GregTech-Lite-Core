package gregtechlite.gtlitecore.mixins.gregtech;

import gregtech.api.capability.IDistinctBusController;
import gregtech.common.metatileentities.multi.electric.MetaTileEntityLargeChemicalReactor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(value = MetaTileEntityLargeChemicalReactor.class, remap = false)
public abstract class MixinMetaTileEntityLargeChemicalReactor implements IDistinctBusController
{
    /**
     * Make Large Chemical Reactor can use distinct mode because we have dual hatch now.
     *
     * @author Magic_Sweepy
     */
    @Unique
    @Override
    public boolean canBeDistinct()
    {
        return true;
    }
}
