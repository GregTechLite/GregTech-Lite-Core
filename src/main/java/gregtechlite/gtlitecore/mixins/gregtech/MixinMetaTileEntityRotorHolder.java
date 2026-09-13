package gregtechlite.gtlitecore.mixins.gregtech;

import gregtech.api.capability.GregtechDataCodes;
import gregtech.api.capability.IRotorHolder;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.common.items.behaviors.TurbineRotorBehavior;
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityMultiblockNotifiablePart;
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityRotorHolder;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.items.IItemHandlerModifiable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = MetaTileEntityRotorHolder.class, remap = false)
public abstract class MixinMetaTileEntityRotorHolder extends MetaTileEntityMultiblockNotifiablePart implements IRotorHolder
{
    @Shadow
    private int rotorColor;

    public MixinMetaTileEntityRotorHolder(ResourceLocation metaTileEntityId, int tier, boolean isExportHatch)
    {
        super(metaTileEntityId, tier, isExportHatch);
    }

    @Inject(method = "update",
            at = @At("RETURN"))
    private void gtlitecore$rotorAutoFill(CallbackInfo ci)
    {
        if (getWorld().isRemote) return;
        if (hasRotor()) return;
        if (getOffsetTimer() % 20 != 0) return;

        final MetaTileEntity controller = getController();
        if (!(controller instanceof RecipeMapMultiblockController)) return;

        final RecipeMapMultiblockController mteController = (RecipeMapMultiblockController) controller;
        if (!mteController.isStructureFormed()) return;

        final IItemHandlerModifiable importItems = mteController.getInputInventory();
        if (importItems == null) return;

        for (int slot = 0; slot < importItems.getSlots(); slot++)
        {
            ItemStack currentStack = importItems.getStackInSlot(slot);
            if (TurbineRotorBehavior.getInstanceFor(currentStack) == null) continue;

            ItemStack extractedStack = importItems.extractItem(slot, 1, false);
            if (extractedStack.isEmpty()) return;

            getImportItems().setStackInSlot(0, extractedStack);
            // CEu not sync rotorColor globally so we resync it to refresh rotorColor.
            writeCustomData(GregtechDataCodes.UPDATE_ROTOR_COLOR, buf -> buf.writeInt(rotorColor));
            markDirty();
            return;
        }
    }

    @Inject(method = "receiveCustomData", at = @At("HEAD"))
    private void gtlitecore$receiveRotorColor(int dataId, PacketBuffer buf, CallbackInfo ci)
    {
        if (dataId != GregtechDataCodes.UPDATE_ROTOR_COLOR) return;
        rotorColor = buf.readInt(); // Receive resync rotorColor by writeCustomData.
        scheduleRenderUpdate();
    }
}
