package magicbook.gtlitecore.common.metatileentity.multiblock;

import gregtech.api.block.VariantActiveBlock;
import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.MultiblockShapeInfo;
import gregtech.api.pattern.PatternMatchContext;
import gregtech.api.pattern.TraceabilityPredicate;
import gregtech.api.recipes.Recipe;
import gregtech.api.util.BlockInfo;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.utils.TooltipHelper;
import gregtech.common.ConfigHolder;
import gregtech.common.blocks.BlockFusionCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.core.sound.GTSoundEvents;
import lombok.Getter;
import magicbook.gtlitecore.api.GTLiteAPI;
import magicbook.gtlitecore.api.block.impl.WrappedIntTier;
import magicbook.gtlitecore.api.capability.GTLiteDataCodes;
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps;
import magicbook.gtlitecore.api.recipe.property.MinimumHeightProperty;
import magicbook.gtlitecore.api.unification.GTLiteMaterials;
import magicbook.gtlitecore.api.utils.stream.LazyStreams;
import magicbook.gtlitecore.client.renderer.texture.GTLiteTextures;
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks;
import magicbook.gtlitecore.common.block.blocks.BlockMetalCasing03;
import magicbook.gtlitecore.common.block.blocks.BlockMultiblockCasing01;
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import one.util.streamex.StreamEx;
import org.apache.commons.lang3.ArrayUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static gregtech.api.util.RelativeDirection.DOWN;
import static gregtech.api.util.RelativeDirection.FRONT;
import static gregtech.api.util.RelativeDirection.LEFT;
import static magicbook.gtlitecore.api.utils.GTLiteUtility.consistent;
import static magicbook.gtlitecore.api.utils.GTLiteUtility.getOrDefault;
import static magicbook.gtlitecore.api.utils.GTLiteUtility.maxLength;
import static magicbook.gtlitecore.api.utils.StructureUtility.emitterCasings;
import static magicbook.gtlitecore.api.utils.StructureUtility.fieldGenCasings;
import static magicbook.gtlitecore.api.utils.StructureUtility.processorCasings;
import static magicbook.gtlitecore.api.utils.StructureUtility.sensorCasings;

public class MetaTileEntityCosmicRayDetector extends RecipeMapMultiblockController
{

    @Getter
    private int emitterCasingTier;
    @Getter
    private int sensorCasingTier;
    @Getter
    private int fieldGenCasingTier;
    @Getter
    private int processorCasingTier;

    private static boolean hasRegistered = false;
    private static List<IBlockState> emitterCasings;
    private static List<IBlockState> sensorCasings;
    private static List<IBlockState> fieldGenCasings;
    private static List<IBlockState> processorCasings;

    private BlockPos topBlockPos = new BlockPos(0, -64, 0);

    // =================================================================================================================
    public MetaTileEntityCosmicRayDetector(ResourceLocation metaTileEntityId)
    {
        super(metaTileEntityId, GTLiteRecipeMaps.COSMIC_RAY_DETECTING_RECIPES());
        this.recipeMapWorkable = new CosmicRayDetectorWorkableHandler(this);
        this.registerCasingMaps();
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity)
    {
        return new MetaTileEntityCosmicRayDetector(metaTileEntityId);
    }

    private void registerCasingMaps()
    {
        if (hasRegistered) return;
        List<IBlockState> emitterCasing = LazyStreams.fastSortedByKey(GTLiteAPI.MAP_EMITTER_CASING);
        List<IBlockState> sensorCasing = LazyStreams.fastSortedByKey(GTLiteAPI.MAP_SENSOR_CASING);
        List<IBlockState> fieldGenCasing = LazyStreams.fastSortedByKey(GTLiteAPI.MAP_FIELD_GEN_CASING);
        List<IBlockState> processorCasing = LazyStreams.fastSortedByKey(GTLiteAPI.MAP_PROCESSOR_CASING);
        int maxLength = maxLength(new ArrayList<List<IBlockState>>() {{
            add(emitterCasing);
            add(sensorCasing);
            add(fieldGenCasing);
            add(processorCasing);
        }});
        emitterCasings = consistent(emitterCasing, maxLength);
        sensorCasings = consistent(sensorCasing, maxLength);
        fieldGenCasings = consistent(fieldGenCasing, maxLength);
        processorCasings = consistent(processorCasing, maxLength);
        hasRegistered = true;
    }

    // =================================================================================================================
    @Override
    protected void formStructure(PatternMatchContext context)
    {
        super.formStructure(context);
        Object type1 = context.get("EmitterCasingTieredStats");
        Object type2 = context.get("SensorCasingTieredStats");
        Object type3 = context.get("FieldGenCasingTieredStats");
        Object type4 = context.get("ProcessorCasingTieredStats");
        this.emitterCasingTier = getOrDefault(
                () -> type1 instanceof WrappedIntTier,
                () -> ((WrappedIntTier) type1).getIntTier(), 0);
        this.sensorCasingTier = getOrDefault(
                () -> type2 instanceof WrappedIntTier,
                () -> ((WrappedIntTier) type2).getIntTier(), 0);
        this.fieldGenCasingTier = getOrDefault(
                () -> type3 instanceof WrappedIntTier,
                () -> ((WrappedIntTier) type3).getIntTier(), 0);
        this.processorCasingTier = getOrDefault(
                () -> type4 instanceof WrappedIntTier,
                () -> ((WrappedIntTier) type4).getIntTier(), 0);
    }

    @Override
    public void invalidateStructure()
    {
        super.invalidateStructure();
        this.emitterCasingTier = 0;
        this.sensorCasingTier = 0;
        this.fieldGenCasingTier = 0;
        this.processorCasingTier = 0;
    }

    @Override
    protected @NotNull BlockPattern createStructurePattern()
    {
        assert Blocks.AIR != null;
        return FactoryBlockPattern.start()
                .aisle("               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "      DDD      ", "               ")
                .aisle("               ", "               ", "               ", "               ", "               ", "               ", "               ", "      DDD      ", "    DD   DD    ", "               ")
                .aisle("               ", "               ", "               ", "               ", "               ", "               ", "       D       ", "    DDD DDD    ", "   D       D   ", "               ")
                .aisle("      CCC      ", "      CCC      ", "      CCC      ", "               ", "               ", "       C       ", "     DDDDD     ", "   DD     DD   ", "  D         D  ", "               ")
                .aisle("     CCCCC     ", "     C###C     ", "     C###C     ", "      CCC      ", "      CCC      ", "     CCCCC     ", "    DDDDDDD    ", "  DD       DD  ", " D           D ", "               ")
                .aisle("    CCCCCCC    ", "    C#####C    ", "    C#####C    ", "     C###C     ", "     C###C     ", "    CCDDDCC    ", "   DDD   DDD   ", "  D         D  ", " D           D ", "               ")
                .aisle("   CCCCCCCCC   ", "   C###E###C   ", "   C#######C   ", "    C#####C    ", "    C##F##C    ", "    CDDDDDC    ", "   DD     DD   ", " DD         DD ", "D             D", "       *       ")
                .aisle("   CCCCCCCCC   ", "   C##ExE##C   ", "   C###c###C   ", "    C##c##C    ", "    C#FxF#C    ", "   CCDDEDDCC   ", "  DDD  f  DDD  ", " D     f     D ", "D      f      D", "      *s*      ")
                .aisle("   CCCCCCCCC   ", "   C###E###C   ", "   C#######C   ", "    C#####C    ", "    C##F##C    ", "    CDDDDDC    ", "   DD     DD   ", " DD         DD ", "D             D", "       *       ")
                .aisle("    CCCCCCC    ", "    C#####C    ", "    C#####C    ", "     C###C     ", "     C###C     ", "    CCDDDCC    ", "   DDD   DDD   ", "  D         D  ", " D           D ", "               ")
                .aisle("     CCCCC     ", "     C###C     ", "     C###C     ", "      CCC      ", "      CCC      ", "     CCCCC     ", "    DDDDDDD    ", "  DD       DD  ", " D           D ", "               ")
                .aisle("      CCC      ", "      CSC      ", "      CCC      ", "               ", "               ", "       C       ", "     DDDDD     ", "   DD     DD   ", "  D         D  ", "               ")
                .aisle("               ", "               ", "               ", "               ", "               ", "               ", "       D       ", "    DDD DDD    ", "   D       D   ", "               ")
                .aisle("               ", "               ", "               ", "               ", "               ", "               ", "               ", "      DDD      ", "    DD   DD    ", "               ")
                .aisle("               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "      DDD      ", "               ")
                .where('S', selfPredicate())
                .where('C', states(getCasingState())
                        .setMinGlobalLimited(148)
                        .or(autoAbilities()))
                .where('D', states(getSecondCasingState()))
                .where('c', states(getCoilState()))
                .where('f', frames(GTLiteMaterials.HDCS))
                .where('E', emitterCasings())
                .where('F', fieldGenCasings())
                .where('s', sensorCasings())
                .where('x', processorCasings())
                .where('*', minHeightPredicate(Blocks.AIR.getDefaultState()))
                .where('#', air())
                .where(' ', any())
                .build();
    }

    @NotNull
    protected TraceabilityPredicate minHeightPredicate(IBlockState... allowedStates)
    {
        return new TraceabilityPredicate(blockWorldState -> {
            this.topBlockPos = blockWorldState.getPos();
            IBlockState state = blockWorldState.getBlockState();
            if (state.getBlock() instanceof VariantActiveBlock)
                blockWorldState.getMatchContext().getOrPut("VABlock", new LinkedList<>())
                        .add(blockWorldState.getPos());
            return ArrayUtils.contains(allowedStates, state);
        }, () -> StreamEx.of(allowedStates)
                .map(state -> new BlockInfo(state, null))
                .toArray(BlockInfo[]::new));
    }

    private static IBlockState getCasingState()
    {
        return GTLiteMetaBlocks.METAL_CASING_03.getState(BlockMetalCasing03.MetalCasingType.QUANTUM_ALLOY);
    }

    private static IBlockState getSecondCasingState()
    {
        return GTLiteMetaBlocks.MULTIBLOCK_CASING_01.getState(BlockMultiblockCasing01.MultiblockCasingType.REFLECTIVE_SURFACE_CASING);
    }

    private static IBlockState getCoilState()
    {
        return MetaBlocks.FUSION_CASING.getState(BlockFusionCasing.CasingType.SUPERCONDUCTOR_COIL);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart)
    {
        return GTLiteTextures.QUANTUM_ALLOY_CASING;
    }

    @SideOnly(Side.CLIENT)
    @NotNull
    @Override
    protected ICubeRenderer getFrontOverlay()
    {
        return GTLiteTextures.COSMIC_RAY_DETECTOR_OVERLAY;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes()
    {
        List<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
        MultiblockShapeInfo.Builder builder = MultiblockShapeInfo.builder(LEFT, DOWN, FRONT)
                .aisle("               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "      DDD      ", "               ")
                .aisle("               ", "               ", "               ", "               ", "               ", "               ", "               ", "      DDD      ", "    DD   DD    ", "               ")
                .aisle("               ", "               ", "               ", "               ", "               ", "               ", "       D       ", "    DDD DDD    ", "   D       D   ", "               ")
                .aisle("      CeC      ", "      CCC      ", "      CCC      ", "               ", "               ", "       C       ", "     DDDDD     ", "   DD     DD   ", "  D         D  ", "               ")
                .aisle("     CCCCC     ", "     C   C     ", "     C   C     ", "      CCC      ", "      CCC      ", "     CCCCC     ", "    DDDDDDD    ", "  DD       DD  ", " D           D ", "               ")
                .aisle("    CCCCCCC    ", "    C     C    ", "    C     C    ", "     C   C     ", "     C   C     ", "    CCDDDCC    ", "   DDD   DDD   ", "  D         D  ", " D           D ", "               ")
                .aisle("   CCCCCCCCC   ", "   C   E   C   ", "   C       C   ", "    C     C    ", "    C  F  C    ", "    CDDDDDC    ", "   DD     DD   ", " DD         DD ", "D             D", "               ")
                .aisle("   CCCCCCCCC   ", "   C  ExE  C   ", "   C   c   C   ", "    C  c  C    ", "    C FxF C    ", "   CCDDEDDCC   ", "  DDD  f  DDD  ", " D     f     D ", "D      f      D", "       s       ")
                .aisle("   CCCCCCCCC   ", "   C   E   C   ", "   C       C   ", "    C     C    ", "    C  F  C    ", "    CDDDDDC    ", "   DD     DD   ", " DD         DD ", "D             D", "               ")
                .aisle("    CCCCCCC    ", "    C     C    ", "    C     C    ", "     C   C     ", "     C   C     ", "    CCDDDCC    ", "   DDD   DDD   ", "  D         D  ", " D           D ", "               ")
                .aisle("     CCCCC     ", "     C   C     ", "     C   C     ", "      CCC      ", "      CCC      ", "     CCCCC     ", "    DDDDDDD    ", "  DD       DD  ", " D           D ", "               ")
                .aisle("      CMC      ", "      ISJ      ", "      CCC      ", "               ", "               ", "       C       ", "     DDDDD     ", "   DD     DD   ", "  D         D  ", "               ")
                .aisle("               ", "               ", "               ", "               ", "               ", "               ", "       D       ", "    DDD DDD    ", "   D       D   ", "               ")
                .aisle("               ", "               ", "               ", "               ", "               ", "               ", "               ", "      DDD      ", "    DD   DD    ", "               ")
                .aisle("               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "      DDD      ", "               ")
                .where('S', GTLiteMetaTileEntities.COSMIC_RAY_DETECTOR, EnumFacing.SOUTH)
                .where('C', getCasingState())
                .where('D', getSecondCasingState())
                .where('c', getCoilState())
                .where('f', MetaBlocks.FRAMES.get(GTLiteMaterials.HDCS).getBlock(GTLiteMaterials.HDCS))
                .where('M', () -> ConfigHolder.machines.enableMaintenance ? MetaTileEntities.MAINTENANCE_HATCH : getCasingState(), EnumFacing.SOUTH)
                .where('I', MetaTileEntities.ITEM_IMPORT_BUS[0], EnumFacing.SOUTH)
                .where('J', MetaTileEntities.FLUID_EXPORT_HATCH[0], EnumFacing.SOUTH)
                .where('e', MetaTileEntities.ENERGY_INPUT_HATCH[0], EnumFacing.NORTH);
        AtomicInteger count = new AtomicInteger();
        StreamEx.of(emitterCasings)
                .map(b -> {
                    if (builder != null) {
                        builder.where('E', b);
                        builder.where('F', fieldGenCasings.get(count.get()));
                        builder.where('s', sensorCasings.get(count.get()));
                        builder.where('x', processorCasings.get(count.get()));
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
            if (this.sensorCasingTier == 0)
                this.writeCustomData(GTLiteDataCodes.INITIALIZE_SUB_TIERED_MACHINE, buf -> {});
            if (this.fieldGenCasingTier == 0)
                this.writeCustomData(GTLiteDataCodes.INITIALIZE_MINOR_TIERED_MACHINE, buf -> {});
            if (this.processorCasingTier == 0)
                this.writeCustomData(GTLiteDataCodes.INITIALIZE_LAST_TIERED_MACHINE, buf -> {});
        }
    }

    @Override
    public void receiveCustomData(int dataId, PacketBuffer buf)
    {
        super.receiveCustomData(dataId, buf);
        if (dataId == GTLiteDataCodes.INITIALIZE_TIERED_MACHINE)
            this.writeCustomData(GTLiteDataCodes.UPDATE_TIERED_MACHINE, b -> b.writeInt(this.emitterCasingTier));
        if (dataId == GTLiteDataCodes.INITIALIZE_SUB_TIERED_MACHINE)
            this.writeCustomData(GTLiteDataCodes.UPDATE_SUB_TIERED_MACHINE, b -> b.writeInt(this.sensorCasingTier));
        if (dataId == GTLiteDataCodes.INITIALIZE_MINOR_TIERED_MACHINE)
            this.writeCustomData(GTLiteDataCodes.UPDATE_MINOR_TIERED_MACHINE, b -> b.writeInt(this.fieldGenCasingTier));
        if (dataId == GTLiteDataCodes.INITIALIZE_LAST_TIERED_MACHINE)
            this.writeCustomData(GTLiteDataCodes.UPDATE_LAST_TIERED_MACHINE, b -> b.writeInt(this.processorCasingTier));
        if (dataId == GTLiteDataCodes.UPDATE_TIERED_MACHINE)
            this.emitterCasingTier = buf.readInt();
        if (dataId == GTLiteDataCodes.UPDATE_SUB_TIERED_MACHINE)
            this.sensorCasingTier = buf.readInt();
        if (dataId == GTLiteDataCodes.UPDATE_MINOR_TIERED_MACHINE)
            this.fieldGenCasingTier = buf.readInt();
        if (dataId == GTLiteDataCodes.UPDATE_LAST_TIERED_MACHINE)
            this.processorCasingTier = buf.readInt();
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data)
    {
        data.setIntArray("topBlockPos", new int[] { topBlockPos.getX(), topBlockPos.getY(), topBlockPos.getZ() });
        return super.writeToNBT(data);
    }

    @Override
    public void readFromNBT(NBTTagCompound data)
    {
        super.readFromNBT(data);
        int[] pos = data.getIntArray("topBlockPos");
        this.topBlockPos = new BlockPos(pos[0], pos[1], pos[2]);
    }

    // =================================================================================================================
    @Override
    public void addInformation(ItemStack stack,
                               @Nullable World world,
                               @NotNull List<String> tooltip,
                               boolean advanced)
    {
        super.addInformation(stack, world, tooltip, advanced);
        tooltip.add(TooltipHelper.RAINBOW_SLOW + I18n.format("gregtech.machine.perfect_oc"));
        tooltip.add(I18n.format("gtlitecore.machine.cosmic_ray_detector.tooltip.1"));
        tooltip.add(I18n.format("gtlitecore.machine.cosmic_ray_detector.tooltip.2"));
        tooltip.add(I18n.format("gtlitecore.machine.cosmic_ray_detector.tooltip.3"));
        tooltip.add(I18n.format("gtlitecore.machine.cosmic_ray_detector.tooltip.4"));
        tooltip.add(I18n.format("gtlitecore.machine.cosmic_ray_detector.tooltip.5"));
    }

    // =================================================================================================================
    @Override
    public boolean canBeDistinct()
    {
        return false;
    }

    @Override
    public SoundEvent getBreakdownSound()
    {
        return GTSoundEvents.BREAKDOWN_ELECTRICAL;
    }

    protected class CosmicRayDetectorWorkableHandler extends MultiblockRecipeLogic
    {

        public CosmicRayDetectorWorkableHandler(RecipeMapMultiblockController tileEntity)
        {
            super(tileEntity, true);
        }

        @Override
        public boolean checkRecipe(@NotNull Recipe recipe)
        {
            return super.checkRecipe(recipe) && recipe.getProperty(MinimumHeightProperty.getInstance(), -64) <= topBlockPos.getY();
        }

        @Override
        public void setMaxProgress(int maxProgress)
        {
            super.setMaxProgress((int) (Math.floor(maxProgress * Math.pow(0.5, Math.min(getSensorCasingTier(), getProcessorCasingTier())))));
        }

        @Override
        public int getParallelLimit()
        {
            return 32 * Math.min(getEmitterCasingTier(), getFieldGenCasingTier());
        }

    }

}
