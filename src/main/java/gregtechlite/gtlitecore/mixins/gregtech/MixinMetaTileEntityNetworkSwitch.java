package gregtechlite.gtlitecore.mixins.gregtech;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.common.metatileentities.multi.electric.MetaTileEntityNetworkSwitch;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = MetaTileEntityNetworkSwitch.class, remap = false)
public abstract class MixinMetaTileEntityNetworkSwitch
{
    /**
     * @author Magic_Sweepy
     * @reason Let the central layer of the Network Switch be repeatable (1-16 layers).
     */
    @ModifyExpressionValue(method = "createStructurePattern",
                           at = @At(value = "INVOKE",
                                    ordinal = 1,
                                    target = "Lgregtech/api/pattern/FactoryBlockPattern;aisle([Ljava/lang/String;)Lgregtech/api/pattern/FactoryBlockPattern;"))
    private FactoryBlockPattern gtlitecore$repeatableCentralLayer(FactoryBlockPattern original)
    {
        return original.setRepeatable(1, 16);
    }
}
