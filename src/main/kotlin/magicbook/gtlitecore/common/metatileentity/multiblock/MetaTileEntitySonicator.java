package magicbook.gtlitecore.common.metatileentity.multiblock;

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
import gregtech.api.util.GTUtility;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.common.ConfigHolder;
import gregtech.common.blocks.BlockBoilerCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.core.sound.GTSoundEvents;
import lombok.Getter;
import magicbook.gtlitecore.api.GTLiteAPI;
import magicbook.gtlitecore.api.block.impl.WrappedIntTier;
import magicbook.gtlitecore.api.capability.GTLiteDataCodes;
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps;
import magicbook.gtlitecore.api.utils.stream.LazyStreams;
import magicbook.gtlitecore.client.renderer.texture.GTLiteTextures;
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks;
import magicbook.gtlitecore.common.block.blocks.BlockMetalCasing03;
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
import static magicbook.gtlitecore.api.utils.StructureUtility.borosilicateGlasses;
import static magicbook.gtlitecore.api.utils.StructureUtility.motorCasings;
import static magicbook.gtlitecore.api.utils.StructureUtility.pumpCasings;

public class MetaTileEntitySonicator extends RecipeMapMultiblockController
{

    @Getter
    private int motorCasingTier;
    @Getter
    private int pumpCasingTier;
    @Getter
    private int glassTier;

    private static boolean hasRegistered = false;
    private static List<IBlockState> motorCasings;
    private static List<IBlockState> pumpCasings;
    private static List<IBlockState> borosilicateGlasses;

    // =================================================================================================================
    public MetaTileEntitySonicator(ResourceLocation metaTileEntityId)
    {
        super(metaTileEntityId, GTLiteRecipeMaps.SONICATION_RECIPES());
        this.recipeMapWorkable = new SonicatorRecipeLogic(this);
        this.registerCasingMaps();
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity)
    {
        return new MetaTileEntitySonicator(metaTileEntityId);
    }

    private void registerCasingMaps()
    {
        if (hasRegistered) return;
        List<IBlockState> motorCasing = LazyStreams.fastSortedByKey(GTLiteAPI.MAP_MOTOR_CASING);
        List<IBlockState> pumpCasing = LazyStreams.fastSortedByKey(GTLiteAPI.MAP_PUMP_CASING);
        List<IBlockState> borosilicateGlass = LazyStreams.fastSortedByKey(GTLiteAPI.MAP_BOROSILICATE_GLASS);
        int maxLength = maxLength(new ArrayList<List<IBlockState>>() {{
            add(motorCasing);
            add(pumpCasing);
            add(borosilicateGlass);
        }});
        motorCasings = consistent(motorCasing, maxLength);
        pumpCasings = consistent(pumpCasing, maxLength);
        borosilicateGlasses = consistent(borosilicateGlass, maxLength);
        hasRegistered = true;
    }

    // =================================================================================================================
    @Override
    protected void formStructure(PatternMatchContext context)
    {
        super.formStructure(context);
        Object type1 = context.get("MotorCasingTieredStats");
        Object type2 = context.get("PumpCasingTieredStats");
        Object type3 = context.get("BorosilicateGlassTieredStats");
        this.motorCasingTier = getOrDefault(
                () -> type1 instanceof WrappedIntTier,
                () -> ((WrappedIntTier) type1).getIntTier(), 0);
        this.pumpCasingTier = getOrDefault(
                () -> type2 instanceof WrappedIntTier,
                () -> ((WrappedIntTier) type2).getIntTier(), 0);
        this.glassTier = getOrDefault(
                () -> type3 instanceof WrappedIntTier,
                () -> ((WrappedIntTier) type3).getIntTier(), 0);
    }

    @Override
    public void invalidateStructure()
    {
        super.invalidateStructure();
        this.motorCasingTier = 0;
        this.pumpCasingTier = 0;
        this.glassTier = 0;
    }

    @NotNull
    @Override
    protected BlockPattern createStructurePattern()
    {
        return FactoryBlockPattern.start()
                .aisle("CCCCC", "CCCCC", "CCCCC", "     ")
                .aisle("CCCCC", "C###C", "CGGGC", "     ")
                .aisle("CCCCC", "C#M#C", "CGQGC", "  P  ")
                .aisle("CCCCC", "C###C", "CGGGC", "  P  ")
                .aisle("CCCCC", "CCCCC", "CCCCC", "  P  ")
                .aisle(" CCC ", " CPC ", " CQC ", "  P  ")
                .aisle(" CCC ", " CSC ", " CCC ", "     ")
                .where('S', selfPredicate())
                .where('C', states(getCasingState())
                        .setMinGlobalLimited(46)
                        .or(autoAbilities()))
                .where('P', states(getPipeCasingState()))
                .where('M', motorCasings())
                .where('Q', pumpCasings())
                .where('G', borosilicateGlasses())
                .where('#', air())
                .where(' ', any())
                .build();
    }

    private static IBlockState getCasingState()
    {
        return GTLiteMetaBlocks.METAL_CASING_03.getState(BlockMetalCasing03.MetalCasingType.COBALT_BRASS);
    }

    private static IBlockState getPipeCasingState()
    {
        return MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.TITANIUM_PIPE);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart)
    {
        return GTLiteTextures.COBALT_BRASS_CASING;
    }

    @SideOnly(Side.CLIENT)
    @NotNull
    @Override
    protected ICubeRenderer getFrontOverlay()
    {
        return GTLiteTextures.SONICATOR_OVERLAY;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes()
    {
        List<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
        MultiblockShapeInfo.Builder builder = MultiblockShapeInfo.builder(LEFT, DOWN, FRONT)
                .aisle("ECCCC", "CCCCC", "CCCCC", "     ")
                .aisle("CCCCC", "C   C", "CGGGC", "     ")
                .aisle("CCCCC", "C M C", "CGQGC", "  P  ")
                .aisle("CCCCC", "C   C", "CGGGC", "  P  ")
                .aisle("CCCCC", "CCCCC", "CCCCC", "  P  ")
                .aisle(" CCC ", " CPC ", " CQC ", "  P  ")
                .aisle(" KNL ", " ISJ ", " CCC ", "     ")
                .where('S', GTLiteMetaTileEntities.SONICATOR, EnumFacing.SOUTH)
                .where('C', getCasingState())
                .where('P', getPipeCasingState())
                .where('N', () -> ConfigHolder.machines.enableMaintenance ? MetaTileEntities.MAINTENANCE_HATCH : getCasingState(), EnumFacing.SOUTH)
                .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[0], EnumFacing.NORTH)
                .where('I', MetaTileEntities.ITEM_IMPORT_BUS[0], EnumFacing.SOUTH)
                .where('J', MetaTileEntities.ITEM_EXPORT_BUS[0], EnumFacing.SOUTH)
                .where('K', MetaTileEntities.FLUID_IMPORT_HATCH[0], EnumFacing.SOUTH)
                .where('L', MetaTileEntities.FLUID_EXPORT_HATCH[0], EnumFacing.SOUTH);
        AtomicInteger count = new AtomicInteger();
        StreamEx.of(motorCasings)
                .map(b -> {
                    if (builder != null)
                    {
                        builder.where('M', b);
                        builder.where('Q', pumpCasings.get(count.get()));
                        builder.where('G', borosilicateGlasses.get(count.get()));
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
            if (this.motorCasingTier == 0)
                this.writeCustomData(GTLiteDataCodes.INITIALIZE_TIERED_MACHINE, buf -> {});
            if (this.pumpCasingTier == 0)
                this.writeCustomData(GTLiteDataCodes.INITIALIZE_SUB_TIERED_MACHINE, buf -> {});
            if (this.glassTier == 0)
                this.writeCustomData(GTLiteDataCodes.INITIALIZE_MINOR_TIERED_MACHINE, buf -> {});
        }
    }

    @Override
    public void receiveCustomData(int dataId, PacketBuffer buf)
    {
        super.receiveCustomData(dataId, buf);
        if (dataId ==  GTLiteDataCodes.INITIALIZE_TIERED_MACHINE)
            this.writeCustomData(GTLiteDataCodes.UPDATE_TIERED_MACHINE, b -> b.writeInt(this.motorCasingTier));
        if (dataId == GTLiteDataCodes.INITIALIZE_SUB_TIERED_MACHINE)
            this.writeCustomData(GTLiteDataCodes.UPDATE_SUB_TIERED_MACHINE, b -> b.writeInt(this.pumpCasingTier));
        if (dataId == GTLiteDataCodes.INITIALIZE_MINOR_TIERED_MACHINE)
            this.writeCustomData(GTLiteDataCodes.UPDATE_MINOR_TIERED_MACHINE, b -> b.writeInt(this.glassTier));
        if (dataId == GTLiteDataCodes.UPDATE_TIERED_MACHINE)
            this.motorCasingTier = buf.readInt();
        if (dataId == GTLiteDataCodes.UPDATE_SUB_TIERED_MACHINE)
            this.pumpCasingTier = buf.readInt();
        if (dataId == GTLiteDataCodes.UPDATE_MINOR_TIERED_MACHINE)
            this.glassTier = buf.readInt();
    }

    // =================================================================================================================
    @Override
    public void addInformation(ItemStack stack,
                               @Nullable World world,
                               @NotNull List<String> tooltip,
                               boolean advanced)
    {
        super.addInformation(stack, world, tooltip, advanced);
        tooltip.add(I18n.format("gtlitecore.machine.sonicator.tooltip.1"));
        tooltip.add(I18n.format("gtlitecore.machine.sonicator.tooltip.2"));
        tooltip.add(I18n.format("gtlitecore.machine.sonicator.tooltip.3"));
        tooltip.add(I18n.format("gtlitecore.machine.sonicator.tooltip.4"));
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

    protected class SonicatorRecipeLogic extends MultiblockRecipeLogic
    {

        public SonicatorRecipeLogic(RecipeMapMultiblockController tileEntity)
        {
            super(tileEntity);
        }

        @Override
        protected double getOverclockingDurationFactor()
        {
            return getGlassTier() >= GTValues.UV - 3 ? 0.25 : 0.5;
        }

        @Override
        public void setMaxProgress(int maxProgress)
        {
            super.setMaxProgress((int) (Math.floor(maxProgress * Math.pow(0.8, Math.min(getMotorCasingTier(), getPumpCasingTier())))));
        }

        @Override
        public int getParallelLimit()
        {
            return 4 * GTUtility.getTierByVoltage(getMaxVoltage());
        }

    }

}
