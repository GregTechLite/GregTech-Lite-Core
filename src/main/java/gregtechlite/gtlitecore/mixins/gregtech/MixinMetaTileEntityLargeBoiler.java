package gregtechlite.gtlitecore.mixins.gregtech;

import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.MultiblockWithDisplayBase;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.common.metatileentities.multi.BoilerType;
import gregtech.common.metatileentities.multi.MetaTileEntityLargeBoiler;
import gregtechlite.gtlitecore.mixins.Implemented;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

/**
 * @deprecated
 */
@Deprecated
@Implemented(at = "https://github.com/GregTechCEu/GregTech/pull/2873")
@Mixin(value = MetaTileEntityLargeBoiler.class, remap = false)
public abstract class MixinMetaTileEntityLargeBoiler extends MultiblockWithDisplayBase
{

    @Shadow
    public @Final BoilerType boilerType;

    public MixinMetaTileEntityLargeBoiler(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId);
    }

    /**
     * In original code, the import items and fluids not constrict its count and preview count:
     * <pre>{@code
     *      .or(abilities(MultiblockAbility.IMPORT_FLUIDS)
     *          .setMinGlobalLimited(1))
     *      .or(abilities(MultiblockAbility.IMPORT_ITEMS)
     *          .setMaxGlobalLimited(1))
     * }</pre>
     * This mixins change the maximum global limit of import items to <tt>2</tt>, and constrict it preview count:
     * <pre>{@code
     *      .or(abilities(MultiblockAbility.IMPORT_FLUIDS)
     *          .setMinGlobalLimited(1, 1))
     *      .or(abilities(MultiblockAbility.IMPORT_ITEMS)
     *          .setMaxGlobalLimited(2, 1))
     * }</pre>
     * Now the large boiler can preview in JEI page with correct structure normally.
     *
     * @author Magic_Sweepy
     * @reason Fix incorrect structure ability of firebox casing part.
     */
    @Overwrite
    @Override
    protected @NotNull BlockPattern createStructurePattern()
    {
        return FactoryBlockPattern.start()
                .aisle("XXX", "CCC", "CCC", "CCC")
                .aisle("XXX", "CPC", "CPC", "CCC")
                .aisle("XXX", "CSC", "CCC", "CCC")
                .where('S', selfPredicate())
                .where('P', states(boilerType.pipeState))
                .where('X', states(boilerType.fireboxState)
                        .setMinGlobalLimited(4)
                        .or(abilities(MultiblockAbility.IMPORT_FLUIDS)
                                .setMinGlobalLimited(1, 1))
                        .or(abilities(MultiblockAbility.IMPORT_ITEMS)
                                .setMaxGlobalLimited(2, 1))
                        .or(autoAbilities()))
                .where('C', states(boilerType.casingState)
                        .setMinGlobalLimited(20)
                        .or(abilities(MultiblockAbility.EXPORT_FLUIDS)
                                .setMinGlobalLimited(1)))
                .build();
    }

}
