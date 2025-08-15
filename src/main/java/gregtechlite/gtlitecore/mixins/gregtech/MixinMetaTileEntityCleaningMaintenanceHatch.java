package gregtechlite.gtlitecore.mixins.gregtech;

import gregtech.api.GTValues;
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityAutoMaintenanceHatch;
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityCleaningMaintenanceHatch;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = MetaTileEntityCleaningMaintenanceHatch.class, remap = false)
public abstract class MixinMetaTileEntityCleaningMaintenanceHatch extends MetaTileEntityAutoMaintenanceHatch
{

    public MixinMetaTileEntityCleaningMaintenanceHatch(ResourceLocation metaTileEntityId)
    {
        super(metaTileEntityId);
    }

    @Override
    public int getTier()
    {
        return GTValues.IV;
    }

}
