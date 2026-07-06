package gregtechlite.gtlitecore.mixins.gregtech;

import gregtech.api.capability.IEnergyContainer;
import gregtech.api.capability.impl.EnergyContainerList;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.PatternMatchContext;
import gregtech.api.pattern.TraceabilityPredicate;
import gregtech.common.metatileentities.multi.electric.MetaTileEntityPowerSubstation;
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates;
import net.minecraft.block.state.IBlockState;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static gregtech.api.util.RelativeDirection.*;

@Mixin(value = MetaTileEntityPowerSubstation.class, remap = false)
public abstract class MixinMetaTileEntityPowerSubstation {

    @Shadow
    private EnergyContainerList inputHatches;

    @Shadow
    private EnergyContainerList outputHatches;

    @Shadow
    protected abstract IBlockState getCasingState();

    @Shadow
    protected abstract IBlockState getGlassState();

    @Accessor("BATTERY_PREDICATE")
    @SuppressWarnings("rawtypes")
    abstract Supplier getBatteryPredicate();

    /**
     * @author rainy
     * @reason Add wireless energy storage ability to PowerSubstation structure pattern
     */
    @NotNull
    @Overwrite
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start(RIGHT, FRONT, UP)
                .aisle("XXSXX", "XXXXX", "XXXXX", "XXXXX", "XXXXX")
                .aisle("XXXXX", "XCCCX", "XCCCX", "XCCCX", "XXXXX")
                .aisle("GGGGG", "GBBBG", "GBBBG", "GBBBG", "GGGGG")
                .setRepeatable(1, MetaTileEntityPowerSubstation.MAX_BATTERY_LAYERS)
                .aisle("GGGGG", "GGGGG", "GGGGG", "GGGGG", "GGGGG")
                .where('S', MultiblockControllerBase.metaTileEntities((MultiblockControllerBase) (Object) this).setCenter())
                .where('C', MultiblockControllerBase.states(getCasingState()))
                .where('X', MultiblockControllerBase.states(getCasingState()).setMinGlobalLimited(14)
                        .or(((AccessorMultiblockWithDisplayBase) this).invokeMaintenancePredicate())
                        .or(MultiblockControllerBase.abilities(
                                MultiblockAbility.INPUT_ENERGY,
                                MultiblockAbility.SUBSTATION_INPUT_ENERGY,
                                MultiblockAbility.INPUT_LASER,
                                TraceabilityPredicates.WIRELESS_ENERGY_STORAGE)
                                .setMinGlobalLimited(1))
                        .or(MultiblockControllerBase.abilities(
                                MultiblockAbility.OUTPUT_ENERGY,
                                MultiblockAbility.SUBSTATION_OUTPUT_ENERGY,
                                MultiblockAbility.OUTPUT_LASER,
                                TraceabilityPredicates.WIRELESS_ENERGY_STORAGE)
                                .setMinGlobalLimited(1)))
                .where('G', MultiblockControllerBase.states(getGlassState()))
                .where('B', ((TraceabilityPredicate) getBatteryPredicate().get()))
                .build();
    }

    @Inject(method = "formStructure", at = @At("TAIL"))
    private void collectWirelessContainers(PatternMatchContext context, CallbackInfo ci) {
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
