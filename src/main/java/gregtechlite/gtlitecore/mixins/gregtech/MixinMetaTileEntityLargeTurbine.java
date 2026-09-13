package gregtechlite.gtlitecore.mixins.gregtech;

import gregtech.api.GTValues;
import gregtech.api.metatileentity.ITieredMetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.multiblock.FuelMultiblockController;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.recipes.RecipeMap;
import gregtech.common.metatileentities.multi.electric.generator.MetaTileEntityLargeTurbine;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = MetaTileEntityLargeTurbine.class, remap = false)
public abstract class MixinMetaTileEntityLargeTurbine extends FuelMultiblockController
{
    @Shadow
    public @Final int tier;

    public MixinMetaTileEntityLargeTurbine(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap, int tier)
    {
        super(metaTileEntityId, recipeMap, tier);
    }

    /**
     * @author Magic_Sweepy
     * @reason Allow all Large Turbines use Item Import Buses for rotor autofill feature.
     */
    @Overwrite
    @Override
    protected @NotNull BlockPattern createStructurePattern()
    {
        return FactoryBlockPattern.start()
                .aisle("CCCC", "CHHC", "CCCC")
                .aisle("CHHC", "RGGR", "CHHC")
                .aisle("CCCC", "CSHC", "CCCC")
                .where('S', selfPredicate())
                .where('G', states(getGearBoxState()))
                .where('C', states(getCasingState()))
                .where('R', metaTileEntities(MultiblockAbility.REGISTRY.get(MultiblockAbility.ROTOR_HOLDER).stream()
                        .filter(mte -> (mte instanceof ITieredMetaTileEntity) &&
                                (((ITieredMetaTileEntity) mte).getTier() >= tier))
                        .toArray(MetaTileEntity[]::new))
                        .addTooltips("gregtech.multiblock.pattern.clear_amount_3")
                        .addTooltip("gregtech.multiblock.pattern.error.limited.1", GTValues.VN[tier])
                        .setExactLimit(1)
                        .or(abilities(MultiblockAbility.OUTPUT_ENERGY)).setExactLimit(1))
                .where('H', states(getCasingState())
                        .or(autoAbilities(false, true, false, false, true, true, true))
                        // region patch
                        .or(abilities(MultiblockAbility.IMPORT_ITEMS)
                                .setPreviewCount(0)))
                        // endregion
                .build();
    }

    @Shadow
    public abstract IBlockState getCasingState();

    @Shadow
    public abstract IBlockState getGearBoxState();
}
