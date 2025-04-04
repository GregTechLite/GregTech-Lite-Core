package magicbook.gtlitecore.common.metatileentity.multiblock;

import gregtech.api.GTValues;
import gregtech.api.GregTechAPI;
import gregtech.api.block.IHeatingCoilBlockStats;
import gregtech.api.capability.IHeatingCoil;
import gregtech.api.capability.impl.HeatingCoilRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.MultiblockDisplayText;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.MultiblockShapeInfo;
import gregtech.api.pattern.PatternMatchContext;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.properties.impl.TemperatureProperty;
import gregtech.api.unification.material.Materials;
import gregtech.api.util.GTUtility;
import gregtech.api.util.TextComponentUtil;
import gregtech.api.util.TextFormattingUtil;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.ConfigHolder;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.BlockWireCoil;
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
import magicbook.gtlitecore.common.block.blocks.BlockActiveUniqueCasing01;
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
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
import static magicbook.gtlitecore.api.utils.StructureUtility.motorCasings;

public class MetaTileEntityCrystallizationCrucible extends RecipeMapMultiblockController implements IHeatingCoil
{

    @Getter
    private int motorCasingTier;
    @Getter
    private int coilTier;
    @Getter
    private int temperature;

    private static boolean hasRegistered = false;
    private static List<IBlockState> motorCasings;
    private static List<IBlockState> heatingCoils;

    // =================================================================================================================
    public MetaTileEntityCrystallizationCrucible(ResourceLocation metaTileEntityId)
    {
        super(metaTileEntityId, GTLiteRecipeMaps.CRYSTALLIZATION_RECIPES());
        this.recipeMapWorkable = new CrystallizationCrucibleRecipeLogic(this);
        this.registerCasingMaps();
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity)
    {
        return new MetaTileEntityCrystallizationCrucible(metaTileEntityId);
    }

    private void registerCasingMaps()
    {
        if (hasRegistered) return;
        List<IBlockState> motorCasing = LazyStreams.fastSortedByKey(GTLiteAPI.MAP_MOTOR_CASING);
        List<IBlockState> heatingCoil = LazyStreams.fastSortedByKey(GregTechAPI.HEATING_COILS);
        int maxLength = maxLength(new ArrayList<List<IBlockState>>() {{
            add(motorCasing);
            add(heatingCoil);
        }});
        motorCasings = consistent(motorCasing, maxLength);
        heatingCoils = consistent(heatingCoil, maxLength);
        hasRegistered = true;
    }

    // =================================================================================================================
    @Override
    protected void formStructure(PatternMatchContext context)
    {
        super.formStructure(context);
        Object type1 = context.get("MotorCasingTieredStats");
        Object type2 = context.get("CoilType");
        this.motorCasingTier = getOrDefault(
                () -> type1 instanceof WrappedIntTier,
                () -> ((WrappedIntTier) type1).getIntTier(), 0);
        if (type2 instanceof IHeatingCoilBlockStats)
        {
            this.temperature = ((IHeatingCoilBlockStats) type2).getCoilTemperature();
            this.coilTier = ((IHeatingCoilBlockStats) type2).getTier();
        }
        else
        {
            this.temperature = BlockWireCoil.CoilType.CUPRONICKEL.getCoilTemperature();
            this.coilTier = BlockWireCoil.CoilType.CUPRONICKEL.getTier();
        }
        this.temperature += 100 * Math.max(0, GTUtility.getTierByVoltage(
                getEnergyContainer().getInputVoltage()) - GTValues.MV);
    }

    @Override
    public void invalidateStructure()
    {
        super.invalidateStructure();
        this.motorCasingTier = 0;
        this.temperature = 0;
    }

    @NotNull
    @Override
    protected BlockPattern createStructurePattern()
    {
        return FactoryBlockPattern.start()
                .aisle("CCCCC", "F   F", "F   F", "CCCCC")
                .aisle("CCCCC", " UHU ", " UHU ", "CCCCC")
                .aisle("CCCCC", " HMH ", " HMH ", "CCOCC")
                .aisle("CCCCC", " UHU ", " UHU ", "CCCCC")
                .aisle("CCSCC", "F   F", "F   F", "CCCCC")
                .where('S', selfPredicate())
                .where('C', states(getCasingState())
                        .setMinGlobalLimited(32)
                        .or(autoAbilities(true, true, true, true, true, false, false)))
                .where('U', states(getUniqueCasingState()))
                .where('O', abilities(MultiblockAbility.MUFFLER_HATCH))
                .where('F', frames(Materials.Titanium))
                .where('M', motorCasings())
                .where('H', heatingCoils())
                .where(' ', any())
                .build();
    }

    private static IBlockState getCasingState()
    {
        return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.TITANIUM_STABLE);
    }

    private static IBlockState getUniqueCasingState()
    {
        return GTLiteMetaBlocks.ACTIVE_UNIQUE_CASING_01.getState(BlockActiveUniqueCasing01.UniqueCasingType.HEAT_VENT);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart)
    {
        return Textures.STABLE_TITANIUM_CASING;
    }

    @SideOnly(Side.CLIENT)
    @NotNull
    @Override
    protected ICubeRenderer getFrontOverlay()
    {
        return GTLiteTextures.CRYSTALLIZATION_CRUCIBLE_OVERLAY;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes()
    {
        List<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
        MultiblockShapeInfo.Builder builder = MultiblockShapeInfo.builder(LEFT, DOWN, FRONT)
                .aisle("ECCCC", "F   F", "F   F", "CCCCC")
                .aisle("CCCCC", " UHU ", " UHU ", "CCCCC")
                .aisle("CCCCC", " HMH ", " HMH ", "CCOCC")
                .aisle("CCCCC", " UHU ", " UHU ", "CCCCC")
                .aisle("IJSNK", "F   F", "F   F", "CCCCC")
                .where('S', GTLiteMetaTileEntities.CRYSTALLIZATION_CRUCIBLE, EnumFacing.SOUTH)
                .where('C', getCasingState())
                .where('F', MetaBlocks.FRAMES.get(Materials.Titanium).getBlock(Materials.Titanium))
                .where('U', getUniqueCasingState())
                .where('O', MetaTileEntities.MUFFLER_HATCH[1], EnumFacing.UP)
                .where('N', () -> ConfigHolder.machines.enableMaintenance ? MetaTileEntities.MAINTENANCE_HATCH : getCasingState(), EnumFacing.SOUTH)
                .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[0], EnumFacing.NORTH)
                .where('I', MetaTileEntities.ITEM_IMPORT_BUS[0], EnumFacing.SOUTH)
                .where('J', MetaTileEntities.ITEM_EXPORT_BUS[0], EnumFacing.SOUTH)
                .where('K', MetaTileEntities.FLUID_IMPORT_HATCH[0], EnumFacing.SOUTH);
        AtomicInteger count = new AtomicInteger();
        StreamEx.of(motorCasings)
                .map(b -> {
                    if (builder != null)
                    {
                        builder.where('M', b);
                        builder.where('H', heatingCoils.get(count.get()));
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
            if (this.coilTier == 0)
                this.writeCustomData(GTLiteDataCodes.INITIALIZE_SUB_TIERED_MACHINE, buf -> {});
        }
    }

    @Override
    public void receiveCustomData(int dataId, PacketBuffer buf)
    {
        super.receiveCustomData(dataId, buf);
        if (dataId == GTLiteDataCodes.INITIALIZE_TIERED_MACHINE)
            this.writeCustomData(GTLiteDataCodes.UPDATE_TIERED_MACHINE, b -> b.writeInt(this.motorCasingTier));
        if (dataId == GTLiteDataCodes.INITIALIZE_SUB_TIERED_MACHINE)
            this.writeCustomData(GTLiteDataCodes.UPDATE_SUB_TIERED_MACHINE, b -> b.writeInt(this.coilTier));
        if (dataId == GTLiteDataCodes.UPDATE_TIERED_MACHINE)
            this.motorCasingTier = buf.readInt();
        if (dataId == GTLiteDataCodes.UPDATE_SUB_TIERED_MACHINE)
            this.coilTier = buf.readInt();
    }

    // =================================================================================================================
    @Override
    public void addInformation(ItemStack stack,
                               @Nullable World world,
                               @NotNull List<String> tooltip,
                               boolean advanced)
    {
        super.addInformation(stack, world, tooltip, advanced);
        tooltip.add(I18n.format("gtlitecore.machine.crystallization_crucible.tooltip.1"));
        tooltip.add(I18n.format("gtlitecore.machine.crystallization_crucible.tooltip.2"));
        tooltip.add(I18n.format("gtlitecore.machine.crystallization_crucible.tooltip.3"));
        tooltip.add(I18n.format("gtlitecore.machine.crystallization_crucible.tooltip.4"));
        tooltip.add(I18n.format("gtlitecore.machine.crystallization_crucible.tooltip.5"));
        tooltip.add(I18n.format("gregtech.machine.electric_blast_furnace.tooltip.1"));
        tooltip.add(I18n.format("gregtech.machine.electric_blast_furnace.tooltip.2"));
        tooltip.add(I18n.format("gregtech.machine.electric_blast_furnace.tooltip.3"));
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList)
    {
        MultiblockDisplayText.builder(textList, this.isStructureFormed())
                .setWorkingStatus(
                        this.recipeMapWorkable.isWorkingEnabled(),
                        this.recipeMapWorkable.isActive())
                .addEnergyUsageLine(this.energyContainer)
                .addEnergyTierLine(GTUtility.getTierByVoltage(
                        this.recipeMapWorkable.getMaxVoltage()
                ))
                .addCustom(tl -> {
                    if (this.isStructureFormed()) {
                        ITextComponent temperatureInfo = TextComponentUtil.stringWithColor(
                                TextFormatting.RED,
                                TextFormattingUtil.formatNumbers(this.temperature)
                        );
                        tl.add(TextComponentUtil.translationWithColor(
                                TextFormatting.GRAY,
                                "gregtech.multiblock.blast_furnace.max_temperature",
                                temperatureInfo
                        ));
                    }
                })
                .addParallelsLine(this.recipeMapWorkable.getParallelLimit())
                .addWorkingStatusLine()
                .addProgressLine(this.recipeMapWorkable.getProgressPercent());
    }

    @NotNull
    @Override
    public List<ITextComponent> getDataInfo()
    {
        List<ITextComponent> textList = super.getDataInfo();
        ITextComponent temperatureInfo = TextComponentUtil.translationWithColor(
                TextFormatting.RED,
                TextFormattingUtil.formatNumbers(this.temperature) + " K"
        );
        textList.add(TextComponentUtil.translationWithColor(
                TextFormatting.GRAY,
                "gregtech.multiblock.blast_furnace.max_temperature",
                temperatureInfo
        ));
        return textList;
    }

    // =================================================================================================================
    @Override
    public boolean canBeDistinct()
    {
        return true;
    }

    @Override
    public boolean checkRecipe(@NotNull Recipe recipe, boolean consumeIfSuccess)
    {
        return this.temperature >= recipe.getProperty(TemperatureProperty.getInstance(), 0);
    }

    @Override
    public boolean hasMufflerMechanics()
    {
        return true;
    }

    @Override
    public SoundEvent getBreakdownSound()
    {
        return GTSoundEvents.BREAKDOWN_ELECTRICAL;
    }

    @Override
    public int getCurrentTemperature()
    {
        return this.temperature;
    }

    protected class CrystallizationCrucibleRecipeLogic extends HeatingCoilRecipeLogic
    {

        private final MetaTileEntityCrystallizationCrucible metaTileEntity;

        public CrystallizationCrucibleRecipeLogic(RecipeMapMultiblockController tileEntity)
        {
            super(tileEntity);
            this.metaTileEntity = (MetaTileEntityCrystallizationCrucible) tileEntity;
        }

        @Override
        protected double getOverclockingDurationFactor()
        {
            return getMaxVoltage() >= GTValues.V[GTValues.UV] ? 0.25 : 0.5;
        }

        @Override
        public void setMaxProgress(int maxProgress)
        {
            super.setMaxProgress((int) (Math.floor(maxProgress * Math.pow(0.8, getMotorCasingTier()))));
        }

        @Override
        public int getParallelLimit()
        {
            return 8 * getCoilTier();
        }

    }


}
