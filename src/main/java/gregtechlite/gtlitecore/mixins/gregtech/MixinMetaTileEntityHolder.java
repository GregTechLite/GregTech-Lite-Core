package gregtechlite.gtlitecore.mixins.gregtech;

import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtechlite.gtlitecore.api.network.sync.ManageableMachine;
import gregtechlite.gtlitecore.api.network.sync.ManagedFields;
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

    @Inject(method = "writeInitialSyncData",
            at = @At("RETURN"))
    private void gtlitecore$managedWriteInitialSyncData(PacketBuffer buf, CallbackInfo ci)
    {
        if (metaTileEntity instanceof ManageableMachine)
        {
            ManagedFields.writeInitialSync(metaTileEntity, buf);
        }
    }

    @Inject(method = "receiveInitialSyncData",
            at = @At("RETURN"))
    private void gtlitecore$managedReceiveInitialSyncData(PacketBuffer buf, CallbackInfo ci)
    {
        if (metaTileEntity instanceof ManageableMachine)
        {
            ManagedFields.readInitialSync(metaTileEntity, buf);
        }
    }

    @Inject(method = "receiveCustomData",
            at = @At("HEAD"),
            cancellable = true)
    private void gtlitecore$managedReceiveCustomData(int discriminator, PacketBuffer buffer, CallbackInfo ci)
    {
        if (metaTileEntity instanceof ManageableMachine
                && ManagedFields.handleCustomData(metaTileEntity, discriminator, buffer))
        {
            ci.cancel();
        }
    }
}
