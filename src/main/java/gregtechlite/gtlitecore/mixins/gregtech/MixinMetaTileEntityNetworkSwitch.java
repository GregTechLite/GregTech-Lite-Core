package gregtechlite.gtlitecore.mixins.gregtech;

import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.common.blocks.BlockComputerCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.multi.electric.MetaTileEntityDataBank;
import gregtech.common.metatileentities.multi.electric.MetaTileEntityNetworkSwitch;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Unique;

@Mixin(value = MetaTileEntityNetworkSwitch.class, remap = false)
public abstract class MixinMetaTileEntityNetworkSwitch extends MetaTileEntityDataBank
{

    public MixinMetaTileEntityNetworkSwitch(ResourceLocation metaTileEntityId)
    {
        super(metaTileEntityId);
    }

    /**
     * @author Magic_Sweepy
     * @reason Let QoS Network switch be scaleable.
     */
    @Overwrite
    @NotNull
    @Override
    protected BlockPattern createStructurePattern()
    {
        return FactoryBlockPattern.start()
                .aisle("XXX", "XXX", "XXX")
                .aisle("XXX", "XAX", "XXX").setRepeatable(1, 16)
                .aisle("XXX", "XSX", "XXX")
                .where('S', selfPredicate())
                .where('A', states(gtlitecore$getAdvancedState()))
                .where('X', states(gtlitecore$getCasingState())
                        .setMinGlobalLimited(7)
                        .or(abilities(MultiblockAbility.INPUT_ENERGY)
                                .setMinGlobalLimited(1, 1))
                        .or(maintenancePredicate())
                        .or(abilities(MultiblockAbility.COMPUTATION_DATA_RECEPTION)
                                .setMinGlobalLimited(1, 2))
                        .or(abilities(MultiblockAbility.COMPUTATION_DATA_TRANSMISSION)
                                .setMinGlobalLimited(1, 1)))
                .build();
    }

    @Unique
    @NotNull
    private static IBlockState gtlitecore$getCasingState()
    {
        return MetaBlocks.COMPUTER_CASING.getState(BlockComputerCasing.CasingType.COMPUTER_CASING);
    }

    @Unique
    @NotNull
    private static IBlockState gtlitecore$getAdvancedState()
    {
        return MetaBlocks.COMPUTER_CASING.getState(BlockComputerCasing.CasingType.ADVANCED_COMPUTER_CASING);
    }

}
