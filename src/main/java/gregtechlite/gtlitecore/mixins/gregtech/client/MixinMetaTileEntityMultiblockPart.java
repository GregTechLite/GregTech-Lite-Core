package gregtechlite.gtlitecore.mixins.gregtech.client;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockAbilityPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.util.GTUtility;
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityMultiblockPart;
import gregtechlite.gtlitecore.core.GTLiteConfigHolder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;

@Mixin(value = MetaTileEntityMultiblockPart.class, remap = false)
public abstract class MixinMetaTileEntityMultiblockPart
{
    @ModifyExpressionValue(method = "renderMetaTileEntity",
                           at = @At(value = "INVOKE",
                                    target = "Lgregtech/api/util/GTUtility;convertRGBtoOpaqueRGBA_CL(I)I"))
    private int gtlitecore$hideMultiblockPartBodyColor(int original)
    {
        MetaTileEntity self = (MetaTileEntity) (Object) this;
        if (!GTLiteConfigHolder.client.hideMultiblockPartBodyColor
                || !self.isPainted() || !gtlitecore$isColorChannelPart())
            return original;
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
