package gregtechlite.gtlitecore.mixins.gregtech;

import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityFluidHatch;
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityMultiblockNotifiablePart;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;

// TODO Remove it when GTCEu PR#2769 merged.
@Mixin(value = MetaTileEntityFluidHatch.class, remap = false)
public abstract class MixinMetaTileEntityFluidHatch extends MetaTileEntityMultiblockNotifiablePart
{

    public MixinMetaTileEntityFluidHatch(ResourceLocation metaTileEntityId, int tier, boolean isExportHatch)
    {
        super(metaTileEntityId, tier, isExportHatch);
    }

    @Override
    public void addToMultiBlock(MultiblockControllerBase controllerBase)
    {
        super.addToMultiBlock(controllerBase);
    }

    @Override
    public void removeFromMultiBlock(MultiblockControllerBase controllerBase)
    {
        super.removeFromMultiBlock(controllerBase);
    }

}
