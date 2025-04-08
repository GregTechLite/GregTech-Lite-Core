package magicbook.gtlitecore.common.metatileentity.multiblock;

import gregtech.api.GTValues;
import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiMapMultiblockController;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.MultiblockShapeInfo;
import gregtech.api.pattern.PatternMatchContext;
import gregtech.api.recipes.RecipeMap;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.common.ConfigHolder;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.core.sound.GTSoundEvents;
import lombok.Getter;
import magicbook.gtlitecore.api.GTLiteAPI;
import magicbook.gtlitecore.api.block.impl.WrappedIntTier;
import magicbook.gtlitecore.api.capability.GTLiteDataCodes;
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps;
import magicbook.gtlitecore.api.unification.GTLiteMaterials;
import magicbook.gtlitecore.api.utils.stream.LazyStreams;
import magicbook.gtlitecore.client.renderer.texture.GTLiteTextures;
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks;
import magicbook.gtlitecore.common.block.blocks.BlockGlassCasing01;
import magicbook.gtlitecore.common.block.blocks.BlockMetalCasing02;
import magicbook.gtlitecore.common.block.blocks.BlockMultiblockCasing01;
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import one.util.streamex.StreamEx;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static gregtech.api.util.RelativeDirection.DOWN;
import static gregtech.api.util.RelativeDirection.FRONT;
import static gregtech.api.util.RelativeDirection.LEFT;
import static magicbook.gtlitecore.api.utils.GTLiteUtility.consistent;
import static magicbook.gtlitecore.api.utils.GTLiteUtility.getOrDefault;
import static magicbook.gtlitecore.api.utils.GTLiteUtility.maxLength;
import static magicbook.gtlitecore.api.utils.StructureUtility.emitterCasings;
import static magicbook.gtlitecore.api.utils.StructureUtility.pumpCasings;
import static magicbook.gtlitecore.api.utils.StructureUtility.sensorCasings;

@Getter
public class MetaTileEntityLaserInducedCVDUnit extends MultiMapMultiblockController
{

    private int emitterCasingTier;
    private int pumpCasingTier;
    private int sensorCasingTier;

    private static boolean hasRegistered = false;
    private static List<IBlockState> emitterCasings;
    private static List<IBlockState> pumpCasings;
    private static List<IBlockState> sensorCasings;

    public MetaTileEntityLaserInducedCVDUnit(ResourceLocation metaTileEntityId)
    {
        super(metaTileEntityId, new RecipeMap[] {
                GTLiteRecipeMaps.LASER_CVD_RECIPES(),
                GTLiteRecipeMaps.CRYSTALLIZATION_RECIPES(),
                GTLiteRecipeMaps.MOLECULAR_BEAM_RECIPES(),
                GTLiteRecipeMaps.SONICATION_RECIPES()
        });
        this.recipeMapWorkable = new LaserInducedCVDRecipeLogic(this);
        this.registerCasingMaps();
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity)
    {
        return new MetaTileEntityLaserInducedCVDUnit(metaTileEntityId);
    }

    private void registerCasingMaps()
    {
        if (hasRegistered) return;
        List<IBlockState> emitterCasing = LazyStreams.fastSortedByKey(GTLiteAPI.MAP_EMITTER_CASING);
        List<IBlockState> pumpCasing = LazyStreams.fastSortedByKey(GTLiteAPI.MAP_PUMP_CASING);
        List<IBlockState> sensorCasing = LazyStreams.fastSortedByKey(GTLiteAPI.MAP_SENSOR_CASING);
        int maxLength = maxLength(new ArrayList<List<IBlockState>>() {{
            add(emitterCasing);
            add(pumpCasing);
            add(sensorCasing);
        }});
        emitterCasings = consistent(emitterCasing, maxLength);
        pumpCasings = consistent(pumpCasing, maxLength);
        sensorCasings = consistent(sensorCasing, maxLength);
        hasRegistered = true;
    }

    // =================================================================================================================
    @Override
    protected void formStructure(PatternMatchContext context)
    {
        super.formStructure(context);
        Object type1 = context.get("EmitterCasingTieredStats");
        Object type2 = context.get("PumpCasingTieredStats");
        Object type3 = context.get("SensorCasingTieredStats");
        this.emitterCasingTier = getOrDefault(
                () -> type1 instanceof WrappedIntTier,
                () -> ((WrappedIntTier) type1).getIntTier(), 0);
        this.pumpCasingTier = getOrDefault(
                () -> type2 instanceof WrappedIntTier,
                () -> ((WrappedIntTier) type2).getIntTier(), 0);
        this.sensorCasingTier = getOrDefault(
                () -> type3 instanceof WrappedIntTier,
                () -> ((WrappedIntTier) type3).getIntTier(), 0);
    }

    @Override
    public void invalidateStructure()
    {
        super.invalidateStructure();
        this.emitterCasingTier = 0;
        this.pumpCasingTier = 0;
        this.sensorCasingTier = 0;
    }

    @NotNull
    @Override
    protected BlockPattern createStructurePattern()
    {
        return FactoryBlockPattern.start()
                .aisle("   CCCCC", "   CGGGC", "   CGGGC", "   CGGGC", "    CCC ")
                .aisle("CCCCCCCC", "CCCFDDDF", "CCCF###F", "CCCF###F", "    FFF ")
                .aisle("CCCCCCCC", "CPCFDDDF", "CPRG###T", "CCCF###F", "    FFF ")
                .aisle("CCCCCCCC", "CCCFDDDF", "CSCF###F", "CCCF###F", "    FFF ")
                .aisle("   CCCCC", "   CGGGC", "   CGGGC", "   CGGGC", "    CCC ")
                .where('S', selfPredicate())
                .where('C', states(getCasingState())
                        .setMinGlobalLimited(60)
                        .or(autoAbilities()))
                .where('D', states(getSecondCasingState()))
                .where('G', states(getGlassState()))
                .where('F', frames(GTLiteMaterials.HastelloyX))
                .where('R', emitterCasings())
                .where('P', pumpCasings())
                .where('T', sensorCasings())
                .where('#', air())
                .where(' ', any())
                .build();
    }

    private static IBlockState getCasingState()
    {
        return GTLiteMetaBlocks.METAL_CASING_02.getState(BlockMetalCasing02.MetalCasingType.HASTELLOY_X);
    }

    private static IBlockState getSecondCasingState()
    {
        return GTLiteMetaBlocks.MULTIBLOCK_CASING_01.getState(BlockMultiblockCasing01.MultiblockCasingType.SUBSTRATE_CASING);
    }

    private static IBlockState getGlassState()
    {
        return GTLiteMetaBlocks.TRANSPARENT_CASING_01.getState(BlockGlassCasing01.GlassType.ZBLAN);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart)
    {
        return GTLiteTextures.HASTELLOY_X_CASING;
    }

    @SideOnly(Side.CLIENT)
    @NotNull
    @Override
    protected ICubeRenderer getFrontOverlay()
    {
        return GTLiteTextures.CVD_UNIT_OVERLAY;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes()
    {
        List<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
        MultiblockShapeInfo.Builder builder = MultiblockShapeInfo.builder(LEFT, DOWN, FRONT)
                .aisle("   CCCCC", "   CGGGC", "   CGGGC", "   CGGGC", "    CCC ")
                .aisle("CCCCCCCC", "CCCFDDDF", "CCCF   F", "CCCF   F", "    FFF ")
                .aisle("CCCCCCCC", "CPCFDDDF", "CPRG   T", "CCCF   F", "    FFF ")
                .aisle("CECCCCCC", "KNLFDDDF", "ISJF   F", "CCCF   F", "    FFF ")
                .aisle("   CCCCC", "   CGGGC", "   CGGGC", "   CGGGC", "    CCC ")
                .where('S', GTLiteMetaTileEntities.LASER_INDUCED_CVD_UNIT, EnumFacing.SOUTH)
                .where('C', getCasingState())
                .where('D', getSecondCasingState())
                .where('F', MetaBlocks.FRAMES.get(GTLiteMaterials.HastelloyX).getBlock(GTLiteMaterials.HastelloyX))
                .where('G', getGlassState())
                .where('N', () -> ConfigHolder.machines.enableMaintenance ? MetaTileEntities.MAINTENANCE_HATCH : getCasingState(), EnumFacing.SOUTH)
                .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[0], EnumFacing.SOUTH)
                .where('I', MetaTileEntities.ITEM_IMPORT_BUS[0], EnumFacing.SOUTH)
                .where('J', MetaTileEntities.ITEM_EXPORT_BUS[0], EnumFacing.SOUTH)
                .where('K', MetaTileEntities.FLUID_IMPORT_HATCH[0], EnumFacing.SOUTH)
                .where('L', MetaTileEntities.FLUID_EXPORT_HATCH[0], EnumFacing.SOUTH);
        AtomicInteger count = new AtomicInteger();
        StreamEx.of(emitterCasings)
                .map(b -> {
                    if (builder != null)
                    {
                        builder.where('R', b);
                        builder.where('P', pumpCasings.get(count.get()));
                        builder.where('T', sensorCasings.get(count.get()));
                        count.getAndIncrement();
                    }
                    return builder;
                })
                .nonNull()
                .forEach(b -> shapeInfo.add(b.build()));
        return shapeInfo;
    }

    // =================================================================================================================
    @Override
    public void update()
    {
        super.update();
        if (this.getWorld().isRemote)
        {
            if (this.emitterCasingTier == 0)
                this.writeCustomData(GTLiteDataCodes.INITIALIZE_TIERED_MACHINE, buf -> {});
            if (this.pumpCasingTier == 0)
                this.writeCustomData(GTLiteDataCodes.INITIALIZE_SUB_TIERED_MACHINE, buf -> {});
            if (this.sensorCasingTier == 0)
                this.writeCustomData(GTLiteDataCodes.INITIALIZE_MINOR_TIERED_MACHINE, buf -> {});
        }
    }

    @Override
    public void receiveCustomData(int dataId, PacketBuffer buf)
    {
        super.receiveCustomData(dataId, buf);
        if (dataId == GTLiteDataCodes.INITIALIZE_TIERED_MACHINE)
            this.writeCustomData(GTLiteDataCodes.UPDATE_TIERED_MACHINE, b -> b.writeInt(this.emitterCasingTier));
        if (dataId == GTLiteDataCodes.INITIALIZE_SUB_TIERED_MACHINE)
            this.writeCustomData(GTLiteDataCodes.UPDATE_SUB_TIERED_MACHINE, b -> b.writeInt(this.pumpCasingTier));
        if (dataId == GTLiteDataCodes.INITIALIZE_MINOR_TIERED_MACHINE)
            this.writeCustomData(GTLiteDataCodes.UPDATE_MINOR_TIERED_MACHINE, b -> b.writeInt(this.sensorCasingTier));
        if (dataId == GTLiteDataCodes.UPDATE_TIERED_MACHINE)
            this.emitterCasingTier = buf.readInt();
        if (dataId == GTLiteDataCodes.UPDATE_SUB_TIERED_MACHINE)
            this.pumpCasingTier = buf.readInt();
        if (dataId == GTLiteDataCodes.UPDATE_MINOR_TIERED_MACHINE)
            this.sensorCasingTier = buf.readInt();
    }

    // =================================================================================================================
    @Override
    public void addInformation(ItemStack stack,
                               @Nullable World world,
                               @NotNull List<String> tooltip,
                               boolean advanced)
    {
        super.addInformation(stack, world, tooltip, advanced);
        tooltip.add(I18n.format("gtlitecore.machine.laser_induced_cvd_unit.tooltip.1"));
        tooltip.add(I18n.format("gtlitecore.machine.laser_induced_cvd_unit.tooltip.2"));
        tooltip.add(I18n.format("gtlitecore.machine.laser_induced_cvd_unit.tooltip.3"));
        tooltip.add(I18n.format("gtlitecore.machine.laser_induced_cvd_unit.tooltip.4"));
        tooltip.add(I18n.format("gtlitecore.machine.laser_induced_cvd_unit.tooltip.5"));
        tooltip.add(I18n.format("gtlitecore.machine.laser_induced_cvd_unit.tooltip.6"));
    }

    // =================================================================================================================
    @Override
    public boolean canBeDistinct()
    {
        return true;
    }

    @Override
    public SoundEvent getBreakdownSound()
    {
        return GTSoundEvents.BREAKDOWN_ELECTRICAL;
    }

    protected class LaserInducedCVDRecipeLogic extends MultiblockRecipeLogic
    {

        public LaserInducedCVDRecipeLogic(RecipeMapMultiblockController tileEntity)
        {
            super(tileEntity);
        }

        @Override
        protected double getOverclockingDurationFactor()
        {
            return getMaxVoltage() >= GTValues.V[GTValues.UV] ? 0.25 : 0.5;
        }

        @Override
        public void setMaxProgress(int maxProgress)
        {
            super.setMaxProgress((int) (Math.floor(maxProgress * Math.pow(0.5, Math.min(getEmitterCasingTier(), getSensorCasingTier())))));
        }

        @Override
        public int getParallelLimit()
        {
            return 8 * getPumpCasingTier();
        }

    }

}
