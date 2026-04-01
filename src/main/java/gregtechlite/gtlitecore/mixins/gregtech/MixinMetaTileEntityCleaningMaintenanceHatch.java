package gregtechlite.gtlitecore.mixins.gregtech;

import gregtech.api.GTValues;
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityAutoMaintenanceHatch;
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityCleaningMaintenanceHatch;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = MetaTileEntityCleaningMaintenanceHatch.class, remap = false)
public abstract class MixinMetaTileEntityCleaningMaintenanceHatch extends MetaTileEntityAutoMaintenanceHatch
{

    public MixinMetaTileEntityCleaningMaintenanceHatch(ResourceLocation metaTileEntityId)
    {
        super(metaTileEntityId);
    }

    /**
     * Because we enabled the cleaning multiblock config, we think the Cleaning Maintenance Hatch is too expensive
     * than its efficiency, so we want to down-tier it to a cheaper voltage requirement.
     *
     * @author Magic_Sweepy
     * @reason Down-tier the Cleaning Maintenance Hatch from UV to IV.
     */
    @Overwrite
    @Override
    public int getTier()
    {
        return GTValues.IV;
    }

}
