package gregtechlite.gtlitecore.mixins.gregtech;

import appeng.api.storage.IStorageChannel;
import appeng.api.storage.data.IAEItemStack;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.metatileentities.multi.multiblockpart.appeng.MetaTileEntityAEHostablePart;
import gregtech.common.metatileentities.multi.multiblockpart.appeng.MetaTileEntityMEInputBus;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;

// TODO Remove it when GTCEu PR#2769 merged.
@Mixin(value = MetaTileEntityMEInputBus.class, remap = false)
public abstract class MixinMetaTileEntityMEInputBus extends MetaTileEntityAEHostablePart<IAEItemStack>
{


    public MixinMetaTileEntityMEInputBus(ResourceLocation metaTileEntityId, int tier, boolean isExportHatch, Class<? extends IStorageChannel<IAEItemStack>> storageChannel)
    {
        super(metaTileEntityId, tier, isExportHatch, storageChannel);
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
