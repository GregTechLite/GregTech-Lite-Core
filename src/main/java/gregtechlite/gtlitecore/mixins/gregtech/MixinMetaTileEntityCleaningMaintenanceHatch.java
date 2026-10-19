package gregtechlite.gtlitecore.mixins.gregtech;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import gregtech.api.GTValues;
import gregtech.common.ConfigHolder;
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityCleaningMaintenanceHatch;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = MetaTileEntityCleaningMaintenanceHatch.class, remap = false)
public abstract class MixinMetaTileEntityCleaningMaintenanceHatch
{
    /**
     * Because we enabled the cleaning multiblock config, the Cleaning Maintenance Hatch is too expensive
     * than its efficiency, so down-tier it to a cheaper voltage requirement.
     *
     * @author Magic_Sweepy
     */
    @ModifyReturnValue(method = "getTier()I", at = @At("RETURN"))
    private int gtlitecore$downTier(int original)
    {
        return ConfigHolder.machines.cleanMultiblocks ? GTValues.IV : original;
    }
}
