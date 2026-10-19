package gregtechlite.gtlitecore.mixins.gregtech;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import gregtech.api.capability.IEnergyContainer;
import gregtech.api.capability.impl.EnergyContainerList;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.api.pattern.PatternMatchContext;
import gregtech.api.pattern.TraceabilityPredicate;
import gregtech.common.metatileentities.multi.electric.MetaTileEntityPowerSubstation;
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(value = MetaTileEntityPowerSubstation.class, remap = false)
public abstract class MixinMetaTileEntityPowerSubstation
{
    @Shadow
    private EnergyContainerList inputHatches;

    @Shadow
    private EnergyContainerList outputHatches;

    /**
     * @author rainy
     * @reason Add the Wireless Energy Storage ability to the input hatches of the Power Substation.
     */
    @ModifyExpressionValue(method = "createStructurePattern",
                           at = @At(value = "INVOKE",
                                    ordinal = 0,
                                    target = "Lgregtech/common/metatileentities/multi/electric/MetaTileEntityPowerSubstation;abilities([Lgregtech/api/metatileentity/multiblock/MultiblockAbility;)Lgregtech/api/pattern/TraceabilityPredicate;"))
    private TraceabilityPredicate gtlitecore$wirelessInputAbility(TraceabilityPredicate original)
    {
        return MultiblockControllerBase.abilities(
                MultiblockAbility.INPUT_ENERGY,
                MultiblockAbility.SUBSTATION_INPUT_ENERGY,
                MultiblockAbility.INPUT_LASER,
                TraceabilityPredicates.WIRELESS_ENERGY_STORAGE);
    }

    /**
     * @author rainy
     * @reason Add the Wireless Energy Storage ability to the output hatches of the Power Substation.
     */
    @ModifyExpressionValue(method = "createStructurePattern",
                           at = @At(value = "INVOKE",
                                    ordinal = 1,
                                    target = "Lgregtech/common/metatileentities/multi/electric/MetaTileEntityPowerSubstation;abilities([Lgregtech/api/metatileentity/multiblock/MultiblockAbility;)Lgregtech/api/pattern/TraceabilityPredicate;"))
    private TraceabilityPredicate gtlitecore$wirelessOutputAbility(TraceabilityPredicate original)
    {
        return MultiblockControllerBase.abilities(MultiblockAbility.OUTPUT_ENERGY,
                MultiblockAbility.SUBSTATION_OUTPUT_ENERGY,
                MultiblockAbility.OUTPUT_LASER,
                TraceabilityPredicates.WIRELESS_ENERGY_STORAGE);
    }

    @Inject(method = "formStructure",
            at = @At("TAIL"))
    private void collectWirelessContainers(PatternMatchContext context, CallbackInfo callbackInfo)
    {
        MultiblockControllerBase self = (MultiblockControllerBase) (Object) this;
        List<IEnergyContainer> inputs = new ArrayList<>();
        inputs.addAll(self.getAbilities(MultiblockAbility.INPUT_ENERGY));
        inputs.addAll(self.getAbilities(MultiblockAbility.SUBSTATION_INPUT_ENERGY));
        inputs.addAll(self.getAbilities(MultiblockAbility.INPUT_LASER));
        inputs.addAll(self.getAbilities(TraceabilityPredicates.WIRELESS_ENERGY_STORAGE));
        this.inputHatches = new EnergyContainerList(inputs);

        List<IEnergyContainer> outputs = new ArrayList<>();
        outputs.addAll(self.getAbilities(MultiblockAbility.OUTPUT_ENERGY));
        outputs.addAll(self.getAbilities(MultiblockAbility.SUBSTATION_OUTPUT_ENERGY));
        outputs.addAll(self.getAbilities(MultiblockAbility.OUTPUT_LASER));
        outputs.addAll(self.getAbilities(TraceabilityPredicates.WIRELESS_ENERGY_STORAGE));
        this.outputHatches = new EnergyContainerList(outputs);
    }
}
