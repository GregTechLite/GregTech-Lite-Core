package gregtechlite.gtlitecore.mixins.gregtech;

import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtechlite.gtlitecore.api.metatileentity.sync.MetaTileEntitySyncer;
import gregtechlite.gtlitecore.api.metatileentity.sync.SyncedMetaTileEntity;
import net.minecraft.network.PacketBuffer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = MetaTileEntityHolder.class, remap = false)
public abstract class MixinMetaTileEntityHolder
{
    @Shadow
    MetaTileEntity metaTileEntity;

    @Inject(method = "writeInitialSyncData", at = @At("RETURN"))
    private void gtlitecore$syncWriteInitialSyncData(PacketBuffer buf, CallbackInfo ci)
    {
        if (metaTileEntity instanceof SyncedMetaTileEntity)
        {
            ((SyncedMetaTileEntity) metaTileEntity).getSyncer().writeInitialSync(buf);
        }
    }

    @Inject(method = "receiveInitialSyncData", at = @At("RETURN"))
    private void gtlitecore$syncReceiveInitialSyncData(PacketBuffer buf, CallbackInfo ci)
    {
        if (metaTileEntity instanceof SyncedMetaTileEntity)
        {
            ((SyncedMetaTileEntity) metaTileEntity).getSyncer().receiveInitialSync(buf);
        }
    }

    @Inject(method = "receiveCustomData", at = @At("HEAD"), cancellable = true)
    private void gtlitecore$syncReceiveCustomData(int discriminator, PacketBuffer buffer, CallbackInfo ci)
    {
        if (metaTileEntity instanceof SyncedMetaTileEntity && discriminator == MetaTileEntitySyncer.SYNC_CODE)
        {
            ((SyncedMetaTileEntity) metaTileEntity).getSyncer().receiveCustomData(buffer);
            ci.cancel();
        }
    }
}
