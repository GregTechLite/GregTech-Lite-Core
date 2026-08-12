package gregtechlite.gtlitecore.mixins.gregtech;

import gregtech.api.metatileentity.MetaTileEntity;
import gregtechlite.gtlitecore.api.metatileentity.SyncedMetaTileEntity;
import net.minecraft.nbt.NBTTagCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = MetaTileEntity.class, remap = false)
public abstract class MixinMetaTileEntity
{
    @Inject(method = "writeToNBT", at = @At("RETURN"))
    private void gtlitecore$syncWriteToNBT(NBTTagCompound data, CallbackInfoReturnable<NBTTagCompound> cir)
    {
        final MetaTileEntity self = (MetaTileEntity) (Object) this;
        if (self instanceof SyncedMetaTileEntity)
        {
            ((SyncedMetaTileEntity) self).getSyncer().saveToNBT(data);
        }
    }

    @Inject(method = "readFromNBT", at = @At("RETURN"))
    private void gtlitecore$syncReadFromNBT(NBTTagCompound data, CallbackInfo ci)
    {
        final MetaTileEntity self = (MetaTileEntity) (Object) this;
        if (self instanceof SyncedMetaTileEntity)
        {
            ((SyncedMetaTileEntity) self).getSyncer().loadFromNBT(data);
        }
    }
}
