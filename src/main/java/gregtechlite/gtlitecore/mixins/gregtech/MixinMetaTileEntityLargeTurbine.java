package gregtechlite.gtlitecore.mixins.gregtech;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.api.pattern.TraceabilityPredicate;
import gregtech.common.metatileentities.multi.electric.generator.MetaTileEntityLargeTurbine;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = MetaTileEntityLargeTurbine.class, remap = false)
public abstract class MixinMetaTileEntityLargeTurbine
{
    /**
     * @author Magic_Sweepy
     * @reason Allow all Large Turbines to use Item Import Buses for the rotor autofill feature.
     */
    @ModifyExpressionValue(method = "createStructurePattern", 
                           at = @At(value = "INVOKE",
                                    target = "Lgregtech/common/metatileentities/multi/electric/generator/MetaTileEntityLargeTurbine;autoAbilities(ZZZZZZZ)Lgregtech/api/pattern/TraceabilityPredicate;"))
    private TraceabilityPredicate gtlitecore$allowItemImportBus(TraceabilityPredicate original)
    {
        return original.or(MultiblockControllerBase.abilities(MultiblockAbility.IMPORT_ITEMS).setPreviewCount(0));
    }
}
