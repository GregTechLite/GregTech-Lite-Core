package gregtechlite.gtlitecore.mixins.gregtech.client;

import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockAbilityPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.util.GTUtility;
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityMultiblockPart;
import gregtechlite.gtlitecore.core.GTLiteConfigHolder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;

@Mixin(value = MetaTileEntityMultiblockPart.class, remap = false)
public abstract class MixinMetaTileEntityMultiblockPart
{
    @Redirect(method = "renderMetaTileEntity",
            at = @At(value = "INVOKE",
                     target = "Lgregtech/api/util/GTUtility;convertRGBtoOpaqueRGBA_CL(I)I"),
            require = 1)
    private int gtlitecore$hideMultiblockPartBodyColor(int colorValue)
    {
        MetaTileEntity self = (MetaTileEntity) (Object) this;
        if (!GTLiteConfigHolder.client.hideMultiblockPartBodyColor
                || !self.isPainted()
                || !gtlitecore$isColorChannelPart())
            return GTUtility.convertRGBtoOpaqueRGBA_CL(colorValue);
        return GTUtility.convertRGBtoOpaqueRGBA_CL(self.getDefaultPaintingColor());
    }

    @Unique
    private boolean gtlitecore$isColorChannelPart()
    {
        if (!(this instanceof IMultiblockAbilityPart<?>))
            return false;
        List<MultiblockAbility<?>> abilities = ((IMultiblockAbilityPart<?>) this).getAbilities();
        return abilities.contains(MultiblockAbility.IMPORT_ITEMS)
                || abilities.contains(MultiblockAbility.IMPORT_FLUIDS);
    }
}
