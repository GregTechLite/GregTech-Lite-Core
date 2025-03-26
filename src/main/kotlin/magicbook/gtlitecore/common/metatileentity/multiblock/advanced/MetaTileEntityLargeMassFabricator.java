package magicbook.gtlitecore.common.metatileentity.multiblock.advanced;

import gregtech.api.GTValues;
import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.MultiblockShapeInfo;
import gregtech.api.pattern.PatternMatchContext;
import gregtech.api.recipes.RecipeMaps;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.ConfigHolder;
import gregtech.common.blocks.BlockBoilerCasing;
import gregtech.common.blocks.BlockFusionCasing;
import gregtech.common.blocks.BlockMultiblockCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import lombok.Getter;
import magicbook.gtlitecore.api.GTLiteAPI;
import magicbook.gtlitecore.api.block.impl.WrappedIntTier;
import magicbook.gtlitecore.api.capability.GTLiteDataCodes;
import magicbook.gtlitecore.api.utils.stream.LazyStreams;
import magicbook.gtlitecore.client.renderer.texture.GTLiteTextures;
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks;
import magicbook.gtlitecore.common.block.blocks.BlockBoilerCasing01;
import magicbook.gtlitecore.common.block.blocks.BlockMetalCasing02;
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import one.util.streamex.StreamEx;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

public class MetaTileEntityLargeMassFabricator extends RecipeMapMultiblockController
{

    @Getter
    private int fieldGenCasingTier;
    @Getter
    private int emitterCasingTier;
    @Getter
    private int sensorCasingTier;
    @Getter
    private int processorCasingTier;

    private static boolean hasRegistered = false;
    private static List<IBlockState> fieldGenCasings;
    private static List<IBlockState> emitterCasings;
    private static List<IBlockState> sensorCasings;
    private static List<IBlockState> processorCasings;

    // =================================================================================================================
    public MetaTileEntityLargeMassFabricator(ResourceLocation metaTileEntityId)
    {
        super(metaTileEntityId, RecipeMaps.MASS_FABRICATOR_RECIPES);
        this.recipeMapWorkable = new LargeMassFabricatorRecipeLogic(this);
        this.registerCasingMaps();
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity)
    {
        return new MetaTileEntityLargeMassFabricator(metaTileEntityId);
    }

    private void registerCasingMaps()
    {
        if (hasRegistered) return;
        List<IBlockState> fieldGenCasing = LazyStreams.fastSortedByKey(GTLiteAPI.MAP_FIELD_GEN_CASING);
        List<IBlockState> emitterCasing = LazyStreams.fastSortedByKey(GTLiteAPI.MAP_EMITTER_CASING);
        List<IBlockState> sensorCasing = LazyStreams.fastSortedByKey(GTLiteAPI.MAP_SENSOR_CASING);
        List<IBlockState> processorCasing = LazyStreams.fastSortedByKey(GTLiteAPI.MAP_PROCESSOR_CASING);
        int maxLength = maxLength(new ArrayList<List<IBlockState>>() {{
            add(fieldGenCasing);
            add(emitterCasing);
            add(sensorCasing);
            add(processorCasing);
        }});
        fieldGenCasings = consistent(fieldGenCasing, maxLength);
        emitterCasings = consistent(emitterCasing, maxLength);
        sensorCasings = consistent(sensorCasing, maxLength);
        processorCasings = consistent(processorCasing, maxLength);
        hasRegistered = true;
    }

    // =================================================================================================================
    @Override
    protected void formStructure(PatternMatchContext context)
    {
        super.formStructure(context);
        Object type1 = context.get("FieldGenCasingTieredStats");
        Object type2 = context.get("EmitterCasingTieredStats");
        Object type3 = context.get("SensorCasingTieredStats");
        Object type4 = context.get("ProcessorCasingTieredStats");
        this.fieldGenCasingTier = getOrDefault(
                () -> type1 instanceof WrappedIntTier,
                () -> ((WrappedIntTier) type1).getIntTier(), 0);
        this.emitterCasingTier = getOrDefault(
                () -> type2 instanceof WrappedIntTier,
                () -> ((WrappedIntTier) type2).getIntTier(), 0);
        this.sensorCasingTier = getOrDefault(
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
        this.fieldGenCasingTier = 0;
        this.emitterCasingTier = 0;
        this.sensorCasingTier = 0;
        this.processorCasingTier = 0;
    }

    @NotNull
    @Override
    protected BlockPattern createStructurePattern()
    {
        return FactoryBlockPattern.start()
                .aisle("CCCCC", " P P ", " P P ", " P P ", "CCCCC")
                .aisle("CCCCC", "PCDCP", "PCDCP", "PCDCP", "CCCCC")
                .aisle("CCCCC", " QUQ ", " QOQ ", " QUQ ", "CDDDC")
                .aisle("CCCCC", " QRQ ", " FEF ", " QRQ ", "CDDDC")
                .aisle("CCCCC", " QRQ ", " FEF ", " QRQ ", "CDDDC")
                .aisle("CCCCC", " QUQ ", " QOQ ", " QUQ ", "CDDDC")
                .aisle("CCCCC", "PCDCP", "PCDCP", "PCDCP", "CCCCC")
                .aisle("CCSCC", " P P ", " P P ", " P P ", "CCCCC")
                .where('S', selfPredicate())
                .where('C', states(getCasingState())
                        .setMinGlobalLimited(20)
                        .or(autoAbilities(true, true, true, false, true, true, false)))
                .where('D', states(getSecondCasingState()))
                .where('P', states(getPipeCasingState()))
                .where('Q', states(getSecondPipeCasingState()))
                .where('R', states(getCoilState()))
                .where('F', fieldGenCasings())
                .where('E', emitterCasings())
                .where('O', sensorCasings())
                .where('U', processorCasings())
                .where(' ', any())
                .build();
    }

    private static IBlockState getCasingState()
    {
        return GTLiteMetaBlocks.METAL_CASING_02.getState(BlockMetalCasing02.MetalCasingType.HASTELLOY_N);
    }

    private static IBlockState getSecondCasingState()
    {
        return MetaBlocks.MULTIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.GRATE_CASING);
    }

    private static IBlockState getPipeCasingState()
    {
        return GTLiteMetaBlocks.BOILER_CASING_01.getState(BlockBoilerCasing01.BoilerCasingType.POLYBENZIMIDAZOLE);
    }

    private static IBlockState getSecondPipeCasingState()
    {
        return MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.TUNGSTENSTEEL_PIPE);
    }

    private static IBlockState getCoilState()
    {
        return MetaBlocks.FUSION_CASING.getState(BlockFusionCasing.CasingType.SUPERCONDUCTOR_COIL);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart)
    {
        return GTLiteTextures.HASTELLOY_N_CASING;
    }

    @SideOnly(Side.CLIENT)
    @NotNull
    @Override
    protected ICubeRenderer getFrontOverlay()
    {
        return Textures.MASS_FABRICATOR_OVERLAY;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes()
    {
        List<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
        MultiblockShapeInfo.Builder builder = MultiblockShapeInfo.builder(LEFT, DOWN, FRONT)
                .aisle("CCXCC", " P P ", " P P ", " P P ", "CCNCC")
                .aisle("CCCCC", "PCDCP", "PCDCP", "PCDCP", "CCCCC")
                .aisle("CCCCC", " QUQ ", " QOQ ", " QUQ ", "CDDDC")
                .aisle("CCCCC", " QRQ ", " FEF ", " QRQ ", "CDDDC")
                .aisle("CCCCC", " QRQ ", " FEF ", " QRQ ", "CDDDC")
                .aisle("CCCCC", " QUQ ", " QOQ ", " QUQ ", "CDDDC")
                .aisle("CCCCC", "PCDCP", "PCDCP", "PCDCP", "CCCCC")
                .aisle("CKSLC", " P P ", " P P ", " P P ", "CCICC")
                .where('S', GTLiteMetaTileEntities.LARGE_MASS_FABRICATOR, EnumFacing.SOUTH)
                .where('C', getCasingState())
                .where('D', getSecondCasingState())
                .where('P', getPipeCasingState())
                .where('Q', getSecondPipeCasingState())
                .where('R', getCoilState())
                .where('N', () -> ConfigHolder.machines.enableMaintenance ? MetaTileEntities.MAINTENANCE_HATCH : getCasingState(), EnumFacing.NORTH)
                .where('X', MetaTileEntities.ENERGY_INPUT_HATCH[0], EnumFacing.NORTH)
                .where('I', MetaTileEntities.ITEM_IMPORT_BUS[0], EnumFacing.SOUTH)
                .where('K', MetaTileEntities.FLUID_IMPORT_HATCH[0], EnumFacing.SOUTH)
                .where('L', MetaTileEntities.FLUID_EXPORT_HATCH[0], EnumFacing.SOUTH);
        AtomicInteger count = new AtomicInteger();
        StreamEx.of(fieldGenCasings)
                .map(b -> {
                    if (builder != null)
                    {
                        builder.where('F', b);
                        builder.where('E', emitterCasings.get(count.get()));
                        builder.where('O', sensorCasings.get(count.get()));
                        builder.where('U', processorCasings.get(count.get()));
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
            if (this.fieldGenCasingTier == 0)
                this.writeCustomData(GTLiteDataCodes.INITIALIZE_TIERED_MACHINE, buf -> {});
            if (this.emitterCasingTier == 0)
                this.writeCustomData(GTLiteDataCodes.INITIALIZE_SUB_TIERED_MACHINE, buf -> {});
            if (this.sensorCasingTier == 0)
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
            this.writeCustomData(GTLiteDataCodes.UPDATE_TIERED_MACHINE, b -> b.writeInt(this.fieldGenCasingTier));
        if (dataId == GTLiteDataCodes.INITIALIZE_SUB_TIERED_MACHINE)
            this.writeCustomData(GTLiteDataCodes.UPDATE_SUB_TIERED_MACHINE, b -> b.writeInt(this.emitterCasingTier));
        if (dataId == GTLiteDataCodes.INITIALIZE_MINOR_TIERED_MACHINE)
            this.writeCustomData(GTLiteDataCodes.UPDATE_MINOR_TIERED_MACHINE, b -> b.writeInt(this.sensorCasingTier));
        if (dataId == GTLiteDataCodes.INITIALIZE_LAST_TIERED_MACHINE)
            this.writeCustomData(GTLiteDataCodes.UPDATE_LAST_TIERED_MACHINE, b -> b.writeInt(this.processorCasingTier));
        if (dataId == GTLiteDataCodes.UPDATE_TIERED_MACHINE)
            this.fieldGenCasingTier = buf.readInt();
        if (dataId == GTLiteDataCodes.UPDATE_SUB_TIERED_MACHINE)
            this.emitterCasingTier = buf.readInt();
        if (dataId == GTLiteDataCodes.UPDATE_MINOR_TIERED_MACHINE)
            this.sensorCasingTier = buf.readInt();
        if (dataId == GTLiteDataCodes.UPDATE_LAST_TIERED_MACHINE)
            this.processorCasingTier = buf.readInt();
    }

    // =================================================================================================================
    @Override
    public void addInformation(ItemStack stack,
                               @Nullable World world,
                               @NotNull List<String> tooltip,
                               boolean advanced)
    {
        super.addInformation(stack, world, tooltip, advanced);
        tooltip.add(I18n.format("gtlitecore.machine.large_mass_fabricator.tooltip.1"));
        tooltip.add(I18n.format("gtlitecore.machine.large_mass_fabricator.tooltip.2"));
        tooltip.add(I18n.format("gtlitecore.machine.large_mass_fabricator.tooltip.3"));
        tooltip.add(I18n.format("gtlitecore.machine.large_mass_fabricator.tooltip.4"));
    }

    // =================================================================================================================
    @Override
    public boolean canBeDistinct()
    {
        return false;
    }

    protected class LargeMassFabricatorRecipeLogic extends MultiblockRecipeLogic
    {

        public LargeMassFabricatorRecipeLogic(RecipeMapMultiblockController tileEntity)
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
            super.setMaxProgress((int) (Math.floor(maxProgress * Math.pow(0.5, Math.min(getFieldGenCasingTier(), getProcessorCasingTier())))));
        }

        @Override
        public int getParallelLimit()
        {
            return 4 * Math.min(getEmitterCasingTier(), getSensorCasingTier());
        }

    }

}
