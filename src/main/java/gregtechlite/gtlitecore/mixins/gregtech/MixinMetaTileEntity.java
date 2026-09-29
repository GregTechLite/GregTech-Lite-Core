package gregtechlite.gtlitecore.mixins.gregtech;

import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityMultiblockPart;
import gregtechlite.gtlitecore.api.metatileentity.sync.SyncedMetaTileEntity;
import net.minecraft.nbt.NBTTagCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = MetaTileEntity.class, remap = false)
public abstract class MixinMetaTileEntity
{
    // region Synced MetaTileEntity
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

    // endregion

    // region Color-Based Distinct

    @Inject(method = "writeItemStackData", at = @At("RETURN"))
    private void gtlitecore$writePaintingColorToItem(NBTTagCompound itemStack, CallbackInfo ci)
    {
        final MetaTileEntity self = (MetaTileEntity) (Object) this;
        if (self instanceof MetaTileEntityMultiblockPart && self.isPainted())
        {
            itemStack.setInteger(MetaTileEntity.TAG_KEY_PAINTING_COLOR, self.getPaintingColor());
        }
    }

    @Inject(method = "initFromItemStackData", at = @At("HEAD"))
    private void gtlitecore$readPaintingColorFromItem(NBTTagCompound itemStack, CallbackInfo ci)
    {
        final MetaTileEntity self = (MetaTileEntity) (Object) this;
        if (!(self instanceof MetaTileEntityMultiblockPart))
            return;
        if (itemStack.hasKey(MetaTileEntity.TAG_KEY_PAINTING_COLOR))
        {
            self.setPaintingColor(itemStack.getInteger(MetaTileEntity.TAG_KEY_PAINTING_COLOR));
        }
    }

    // endregion
}
