package magicbook.gtlitecore.common.metatileentity.multiblock.advanced;

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
import gregtech.api.util.GTUtility;
import gregtech.api.util.TextComponentUtil;
import gregtech.api.util.TextFormattingUtil;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.common.ConfigHolder;
import gregtech.common.blocks.BlockBoilerCasing;
import gregtech.common.blocks.BlockWireCoil;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.core.sound.GTSoundEvents;
import lombok.Getter;
import magicbook.gtlitecore.api.GTLiteAPI;
import magicbook.gtlitecore.api.block.impl.WrappedIntTier;
import magicbook.gtlitecore.api.capability.GTLiteDataCodes;
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps;
import magicbook.gtlitecore.client.renderer.texture.GTLiteTextures;
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks;
import magicbook.gtlitecore.common.block.blocks.BlockActiveUniqueCasing01;
import magicbook.gtlitecore.common.block.blocks.BlockMetalCasing02;
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
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static gregtech.api.util.RelativeDirection.DOWN;
import static gregtech.api.util.RelativeDirection.FRONT;
import static gregtech.api.util.RelativeDirection.LEFT;
import static magicbook.gtlitecore.api.utils.GTLiteUtility.consistent;
import static magicbook.gtlitecore.api.utils.GTLiteUtility.getOrDefault;
import static magicbook.gtlitecore.api.utils.GTLiteUtility.maxLength;
import static magicbook.gtlitecore.api.utils.StructureUtility.pumpCasings;

public class MetaTileEntityAlloyBlastSmelter extends RecipeMapMultiblockController implements IHeatingCoil
{

    @Getter
    private int pumpCasingTier;
    @Getter
    private int coilTier;
    @Getter
    private int temperature;

    private static boolean hasRegistered = false;
    private static List<IBlockState> pumpCasings;
    private static List<IBlockState> heatingCoils;

    // =================================================================================================================
    public MetaTileEntityAlloyBlastSmelter(ResourceLocation metaTileEntityId)
    {
        super(metaTileEntityId, GTLiteRecipeMaps.ALLOY_BLAST_RECIPES());
        this.recipeMapWorkable = new AlloyBlastSmelterRecipeLogic(this);
        this.registerCasingMaps();
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity)
    {
        return new MetaTileEntityAlloyBlastSmelter(metaTileEntityId);
    }

    private void registerCasingMaps()
    {
        if (hasRegistered) return;
        List<IBlockState> pumpCasing = StreamEx.of(GTLiteAPI.MAP_PUMP_CASING.entrySet())
                .sortedByInt(entry -> ((WrappedIntTier) entry.getValue()).getIntTier())
                .map(Map.Entry::getKey)
                .toList();
        List<IBlockState> heatingCoil = StreamEx.of(GregTechAPI.HEATING_COILS.entrySet())
                .sortedByInt(entry -> entry.getValue().getTier())
                .map(Map.Entry::getKey)
                .toList();
        int maxLength = maxLength(new ArrayList<List<IBlockState>>() {{
            add(pumpCasing);
            add(heatingCoil);
        }});
        pumpCasings = consistent(pumpCasing, maxLength);
        heatingCoils = consistent(heatingCoil, maxLength);
        hasRegistered = true;
    }

    // =================================================================================================================
    @Override
    protected void formStructure(PatternMatchContext context)
    {
        super.formStructure(context);
        Object type1 = context.get("PumpCasingTieredStats");
        Object type2 = context.get("CoilType");
        this.pumpCasingTier = getOrDefault(
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
        this.pumpCasingTier = 0;
        this.temperature = 0;
    }

    @NotNull
    @Override
    protected BlockPattern createStructurePattern()
    {
        return FactoryBlockPattern.start()
                .aisle(" CCC ", " HHH ", " UUU ", " HHH ", " CCC ")
                .aisle("CCCCC", "H###H", "U###U", "H###H", "CCCCC")
                .aisle("CCCCC", "H#P#H", "U#Q#U", "H#P#H", "CCOCC")
                .aisle("CCCCC", "H###H", "U###U", "H###H", "CCCCC")
                .aisle(" CSC ", " HHH ", " UUU ", " HHH ", " CCC ")
                .where('S', selfPredicate())
                .where('C', states(getCasingState())
                        .setMinGlobalLimited(10)
                        .or(autoAbilities(true, true, true, false, true, true, false)))
                .where('Q', states(getPipeCasingState()))
                .where('U', states(getUniqueCasingState()))
                .where('O', abilities(MultiblockAbility.MUFFLER_HATCH))
                .where('H', heatingCoils())
                .where('P', pumpCasings())
                .where('#', air())
                .where(' ', any())
                .build();
    }

    private static IBlockState getCasingState()
    {
        return GTLiteMetaBlocks.METAL_CASING_02.getState(BlockMetalCasing02.MetalCasingType.ZIRCONIUM_CARBIDE);
    }

    private static IBlockState getPipeCasingState()
    {
        return MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.TUNGSTENSTEEL_PIPE);
    }

    private static IBlockState getUniqueCasingState()
    {
        return GTLiteMetaBlocks.ACTIVE_UNIQUE_CASING_01.getState(BlockActiveUniqueCasing01.UniqueCasingType.HEAT_VENT);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart)
    {
        return GTLiteTextures.ZIRCONIUM_CARBIDE_CASING;
    }

    @SideOnly(Side.CLIENT)
    @NotNull
    @Override
    protected ICubeRenderer getFrontOverlay()
    {
        return GTLiteTextures.ALLOY_BLAST_SMELTER_OVERLAY;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes()
    {
        List<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
        MultiblockShapeInfo.Builder builder = MultiblockShapeInfo.builder(LEFT, DOWN, FRONT)
                .aisle(" CEC ", " HHH ", " UUU ", " HHH ", " CCC ")
                .aisle("CCCCC", "H   H", "U   U", "H   H", "CCCCC")
                .aisle("CCCCC", "H P H", "U Q U", "H P H", "CCMCC")
                .aisle("KCCCK", "H   H", "U   U", "H   H", "CCCCC")
                .aisle(" ISL ", " HHH ", " UUU ", " HHH ", " CNC ")
                .where('S', GTLiteMetaTileEntities.ALLOY_BLAST_SMELTER, EnumFacing.SOUTH)
                .where('C', getCasingState())
                .where('Q', getPipeCasingState())
                .where('U', getUniqueCasingState())
                .where('M', MetaTileEntities.MUFFLER_HATCH[1], EnumFacing.UP)
                .where('N', () -> ConfigHolder.machines.enableMaintenance ? MetaTileEntities.MAINTENANCE_HATCH : getCasingState(), EnumFacing.SOUTH)
                .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[0], EnumFacing.NORTH)
                .where('I', MetaTileEntities.ITEM_IMPORT_BUS[0], EnumFacing.SOUTH)
                .where('K', MetaTileEntities.FLUID_IMPORT_HATCH[0], EnumFacing.SOUTH)
                .where('L', MetaTileEntities.FLUID_EXPORT_HATCH[0], EnumFacing.SOUTH);
        AtomicInteger count = new AtomicInteger();
        StreamEx.of(pumpCasings)
                .map(b -> {
                    if (builder != null)
                    {
                        builder.where('P', b);
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
            if (this.pumpCasingTier == 0)
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
            this.writeCustomData(GTLiteDataCodes.UPDATE_TIERED_MACHINE, b -> b.writeInt(this.pumpCasingTier));
        if (dataId == GTLiteDataCodes.INITIALIZE_SUB_TIERED_MACHINE)
            this.writeCustomData(GTLiteDataCodes.UPDATE_SUB_TIERED_MACHINE, b -> b.writeInt(this.coilTier));
        if (dataId == GTLiteDataCodes.UPDATE_TIERED_MACHINE)
            this.pumpCasingTier = buf.readInt();
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
        tooltip.add(I18n.format("gtlitecore.machine.alloy_blast_smelter.tooltip.1"));
        tooltip.add(I18n.format("gtlitecore.machine.alloy_blast_smelter.tooltip.2"));
        tooltip.add(I18n.format("gtlitecore.machine.alloy_blast_smelter.tooltip.3"));
        tooltip.add(I18n.format("gtlitecore.machine.alloy_blast_smelter.tooltip.4"));
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
    public  List<ITextComponent> getDataInfo()
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

    protected class AlloyBlastSmelterRecipeLogic extends HeatingCoilRecipeLogic
    {

        private final MetaTileEntityAlloyBlastSmelter metaTileEntity;

        public AlloyBlastSmelterRecipeLogic(RecipeMapMultiblockController tileEntity)
        {
            super(tileEntity);
            this.metaTileEntity = (MetaTileEntityAlloyBlastSmelter) tileEntity;
        }

        @Override
        protected double getOverclockingDurationFactor()
        {
            return getMaxVoltage() >= GTValues.V[GTValues.UV] ? 0.25 : 0.5;
        }

        @Override
        public void setMaxProgress(int maxProgress)
        {
            super.setMaxProgress((int) (Math.floor(maxProgress * Math.pow(0.8, getCoilTier()))));
        }

        @Override
        public int getParallelLimit()
        {
            return 8 * getPumpCasingTier();
        }

    }

}
