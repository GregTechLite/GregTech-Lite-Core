package gregtechlite.gtlitecore.mixins.gregtech;

import gregtech.api.metatileentity.MetaTileEntity;
import gregtechlite.gtlitecore.api.network.sync.ManageableMachine;
import gregtechlite.gtlitecore.api.network.sync.ManagedFields;
import net.minecraft.nbt.NBTTagCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = MetaTileEntity.class, remap = false)
public abstract class MixinMetaTileEntity
{
    @Inject(method = "writeToNBT",
            at = @At("RETURN"))
    private void gtlitecore$managedWriteToNBT(NBTTagCompound data, CallbackInfoReturnable<NBTTagCompound> cir)
    {
        final MetaTileEntity self = (MetaTileEntity) (Object) this;
        if (self instanceof ManageableMachine)
        {
            ManagedFields.writeToNBT(self, data);
        }
    }

    @Inject(method = "readFromNBT",
            at = @At("HEAD"))
    private void gtlitecore$managedReadFromNBT(NBTTagCompound data, CallbackInfo ci)
    {
        final MetaTileEntity self = (MetaTileEntity) (Object) this;
        if (self instanceof ManageableMachine)
        {
            ManagedFields.readFromNBT(self, data);
        }
    }

    @Inject(method = "update",
            at = @At("RETURN"))
    private void gtlitecore$managedUpdate(CallbackInfo ci)
    {
        final MetaTileEntity self = (MetaTileEntity) (Object) this;
        if (self instanceof ManageableMachine && self.getWorld() != null && !self.getWorld().isRemote)
        {
            ManagedFields.tickSync(self);
        }
    }
}
