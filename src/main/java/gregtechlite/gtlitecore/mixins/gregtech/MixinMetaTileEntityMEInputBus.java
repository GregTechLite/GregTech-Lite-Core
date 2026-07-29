package gregtechlite.gtlitecore.mixins.gregtech;

import appeng.api.storage.IStorageChannel;
import appeng.api.storage.data.IAEItemStack;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.metatileentities.multi.multiblockpart.appeng.MetaTileEntityAEHostablePart;
import gregtech.common.metatileentities.multi.multiblockpart.appeng.MetaTileEntityMEInputBus;
import gregtechlite.gtlitecore.mixins.hooks.Implemented;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.ApiStatus.ScheduledForRemoval;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@ScheduledForRemoval(inVersion = "Change gregtech to our forked version")
@Deprecated
@Implemented(at = "https://github.com/GregTechCEu/GregTech/pull/2769")
@Mixin(value = MetaTileEntityMEInputBus.class, remap = false)
public abstract class MixinMetaTileEntityMEInputBus extends MetaTileEntityAEHostablePart<IAEItemStack>
{
    public MixinMetaTileEntityMEInputBus(ResourceLocation metaTileEntityId, int tier, boolean isExportHatch, Class<? extends IStorageChannel<IAEItemStack>> storageChannel)
    {
        super(metaTileEntityId, tier, isExportHatch, storageChannel);
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
