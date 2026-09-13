package gregtechlite.gtlitecore.mixins.gregtech;

import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityFluidHatch;
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityMultiblockNotifiablePart;
import gregtechlite.gtlitecore.mixins.hooks.Implemented;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.ApiStatus.ScheduledForRemoval;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@ScheduledForRemoval(inVersion = "Change gregtech to our forked version")
@Deprecated
@Implemented(at = "https://github.com/GregTechCEu/GregTech/pull/2769")
@Mixin(value = MetaTileEntityFluidHatch.class, remap = false)
public abstract class MixinMetaTileEntityFluidHatch extends MetaTileEntityMultiblockNotifiablePart
{
    public MixinMetaTileEntityFluidHatch(ResourceLocation metaTileEntityId, int tier, boolean isExportHatch)
    {
        super(metaTileEntityId, tier, isExportHatch);
    }

    @Unique
    @Override
    public void addToMultiBlock(MultiblockControllerBase controllerBase)
    {
        super.addToMultiBlock(controllerBase);
    }

    @Unique
    @Override
    public void removeFromMultiBlock(MultiblockControllerBase controllerBase)
    {
        super.removeFromMultiBlock(controllerBase);
    }
}
