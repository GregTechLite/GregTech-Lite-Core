package magicbook.gtlitecore.common.metatileentity.multiblock.advanced;

import gregtech.api.GTValues;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockDisplayText;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.MultiblockShapeInfo;
import gregtech.api.pattern.PatternMatchContext;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.util.GTUtility;
import gregtech.api.util.TextComponentUtil;
import gregtech.api.util.TextFormattingUtil;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.ConfigHolder;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.core.sound.GTSoundEvents;
import lombok.Getter;
import magicbook.gtlitecore.api.GTLiteAPI;
import magicbook.gtlitecore.api.block.impl.WrappedIntTier;
import magicbook.gtlitecore.api.capability.GTLiteDataCodes;
import magicbook.gtlitecore.api.unification.GTLiteMaterials;
import magicbook.gtlitecore.api.utils.stream.LazyStreams;
import magicbook.gtlitecore.client.renderer.texture.GTLiteTextures;
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks;
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
import net.minecraftforge.fluids.FluidStack;
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
import static magicbook.gtlitecore.api.utils.StructureUtility.pumpCasings;

public class MetaTileEntityCryogenicFreezer extends RecipeMapMultiblockController
{

    @Getter
    private int pumpCasingTier;
    @Getter
    private int motorCasingTier;

    private static boolean hasRegistered = false;
    private static List<IBlockState> pumpCasings;
    private static List<IBlockState> motorCasings;

    // =================================================================================================================
    public MetaTileEntityCryogenicFreezer(ResourceLocation metaTileEntityId)
    {
        super(metaTileEntityId, RecipeMaps.VACUUM_RECIPES);
        this.recipeMapWorkable = new CryogenicFreezerRecipeLogic(this);
        this.registerCasingMaps();
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity)
    {
        return new MetaTileEntityCryogenicFreezer(metaTileEntityId);
    }

    private void registerCasingMaps()
    {
        if (hasRegistered) return;
        List<IBlockState> pumpCasing = LazyStreams.fastSortedByKey(GTLiteAPI.MAP_PUMP_CASING);
        List<IBlockState> motorCasing = LazyStreams.fastSortedByKey(GTLiteAPI.MAP_MOTOR_CASING);
        int maxLength = maxLength(new ArrayList<List<IBlockState>>() {{
            add(pumpCasing);
            add(motorCasing);
        }});
        pumpCasings = consistent(pumpCasing, maxLength);
        motorCasings = consistent(motorCasing, maxLength);
        hasRegistered = true;
    }

    // =================================================================================================================
    @Override
    protected void formStructure(PatternMatchContext context)
    {
        super.formStructure(context);
        Object type1 = context.get("PumpCasingTieredStats");
        Object type2 = context.get("MotorCasingTieredStats");
        this.pumpCasingTier = getOrDefault(
                () -> type1 instanceof WrappedIntTier,
                () -> ((WrappedIntTier) type1).getIntTier(), 0);
        this.motorCasingTier = getOrDefault(
                () -> type2 instanceof WrappedIntTier,
                () -> ((WrappedIntTier) type2).getIntTier(), 0);
    }

    @Override
    public void invalidateStructure()
    {
        super.invalidateStructure();
        this.pumpCasingTier = 0;
        this.motorCasingTier = 0;
    }

    @NotNull
    @Override
    protected BlockPattern createStructurePattern()
    {
        return FactoryBlockPattern.start()
                .aisle("CCC", "CCC", "CCC")
                .aisle("CMC", "CPC", "CCC")
                .aisle("CCC", "CSC", "CCC")
                .where('S', selfPredicate())
                .where('C', states(getCasingState())
                        .setMinGlobalLimited(4)
                        .or(autoAbilities(true, true, true, true, true, true, false)))
                .where('P', pumpCasings())
                .where('M', motorCasings())
                .build();
    }

    private static IBlockState getCasingState()
    {
        return GTLiteMetaBlocks.METAL_CASING_02.getState(BlockMetalCasing02.MetalCasingType.HASTELLOY_X);
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
        return Textures.VACUUM_FREEZER_OVERLAY;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes()
    {
        List<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
        MultiblockShapeInfo.Builder builder = MultiblockShapeInfo.builder(LEFT, DOWN, FRONT)
                .aisle("CEC", "CCC", "CCC")
                .aisle("CMC", "CPC", "CCC")
                .aisle("KNL", "ISJ", "CCC")
                .where('S', GTLiteMetaTileEntities.CRYOGENIC_FREEZER, EnumFacing.SOUTH)
                .where('C', getCasingState())
                .where('N', () -> ConfigHolder.machines.enableMaintenance ? MetaTileEntities.MAINTENANCE_HATCH : getCasingState(), EnumFacing.SOUTH)
                .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[0], EnumFacing.NORTH)
                .where('I', MetaTileEntities.ITEM_IMPORT_BUS[0], EnumFacing.SOUTH)
                .where('J', MetaTileEntities.ITEM_EXPORT_BUS[0], EnumFacing.SOUTH)
                .where('K', MetaTileEntities.FLUID_IMPORT_HATCH[0], EnumFacing.SOUTH)
                .where('L', MetaTileEntities.FLUID_EXPORT_HATCH[0], EnumFacing.SOUTH);
        AtomicInteger count = new AtomicInteger();
        StreamEx.of(pumpCasings)
                .map(b -> {
                    if (builder != null)
                    {
                        builder.where('P', b);
                        builder.where('M', motorCasings.get(count.get()));
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
            if (this.motorCasingTier == 0)
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
            this.writeCustomData(GTLiteDataCodes.UPDATE_SUB_TIERED_MACHINE, b -> b.writeInt(this.motorCasingTier));
        if (dataId == GTLiteDataCodes.UPDATE_TIERED_MACHINE)
            this.pumpCasingTier = buf.readInt();
        if (dataId == GTLiteDataCodes.UPDATE_SUB_TIERED_MACHINE)
            this.motorCasingTier = buf.readInt();
    }

    // =================================================================================================================
    @Override
    public void addInformation(ItemStack stack,
                               @Nullable World world,
                               @NotNull List<String> tooltip,
                               boolean advanced)
    {
        super.addInformation(stack, world, tooltip, advanced);
        tooltip.add(I18n.format("gtlitecore.machine.cryogenic_freezer.tooltip.1"));
        tooltip.add(I18n.format("gtlitecore.machine.cryogenic_freezer.tooltip.2"));
        tooltip.add(I18n.format("gtlitecore.machine.cryogenic_freezer.tooltip.3"));
        tooltip.add(I18n.format("gtlitecore.machine.cryogenic_freezer.tooltip.4"));
        tooltip.add(I18n.format("gtlitecore.machine.cryogenic_freezer.tooltip.5"));
        tooltip.add(I18n.format("gtlitecore.machine.cryogenic_freezer.tooltip.6"));
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
                        this.recipeMapWorkable.getMaxVoltage()))
                .addCustom(tl -> {
                    if (this.isStructureFormed())
                    {
                        if (this.getInputFluidInventory() != null)
                        {
                            FluidStack cryotheumStack = this.getInputFluidInventory()
                                    .drain(GTLiteMaterials.GelidCryotheum.getFluid(Integer.MAX_VALUE), false);
                            int cryotheumAmount = cryotheumStack == null ? 0 : cryotheumStack.amount;
                            ITextComponent amountInfo = TextComponentUtil.stringWithColor(
                                    TextFormatting.GREEN,
                                    TextFormattingUtil.formatNumbers(cryotheumAmount) + " L");
                            tl.add(TextComponentUtil.translationWithColor(
                                    TextFormatting.GRAY,
                                    "gtlitecore.machine.cryogenic_freezer.cryotheum_amount",
                                    amountInfo)); // TODO Impl of IProgressBarMultiblock to replaced it.
                        }
                    }
                })
                .addParallelsLine(this.recipeMapWorkable.getParallelLimit())
                .addWorkingStatusLine()
                .addProgressLine(this.recipeMapWorkable.getProgressPercent());
    }

    @Override
    protected void addWarningText(List<ITextComponent> textList)
    {
        MultiblockDisplayText.builder(textList, this.isStructureFormed(), false)
                .addCustom(tl -> {
                    if (this.isStructureFormed())
                    {
                        FluidStack cryotheumStack = this.getInputFluidInventory()
                                .drain(GTLiteMaterials.GelidCryotheum.getFluid(Integer.MAX_VALUE), false);
                        if (cryotheumStack == null || cryotheumStack.amount == 0)
                        {
                            ITextComponent warnInfo = TextComponentUtil.translationWithColor(
                                    TextFormatting.YELLOW,
                                    "gtlitecore.machine.cryogenic_freezer_cryotheum_warning");
                            tl.add(warnInfo);
                        }
                    }
                });
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

    protected class CryogenicFreezerRecipeLogic extends MultiblockRecipeLogic
    {

        private final MetaTileEntityCryogenicFreezer cryogenicFreezer;

        public CryogenicFreezerRecipeLogic(RecipeMapMultiblockController tileEntity)
        {
            super(tileEntity);
            this.cryogenicFreezer = (MetaTileEntityCryogenicFreezer) tileEntity;
        }

        @Override
        protected void updateRecipeProgress()
        {
            if (this.canRecipeProgress && this.drawEnergy(this.recipeEUt, true))
            {
                IMultipleTankHandler inputTank = cryogenicFreezer.getInputFluidInventory();
                FluidStack cryotheumStack = GTLiteMaterials.GelidCryotheum.getFluid(2);
                if (cryotheumStack.isFluidStackIdentical(inputTank.drain(cryotheumStack, false)))
                {
                    inputTank.drain(cryotheumStack, true);
                    if (++this.progressTime > this.maxProgressTime)
                        this.completeRecipe();
                }
                else
                {
                    return;
                }
                this.drawEnergy(this.recipeEUt, false);
            }
        }

        @Override
        protected double getOverclockingDurationFactor()
        {
            return getMaxVoltage() >= GTValues.V[GTValues.UV] ? 0.25 : 0.5;
        }

        @Override
        public void setMaxProgress(int maxProgress)
        {
            super.setMaxProgress((int) (Math.floor(maxProgress * Math.pow(0.5, getPumpCasingTier()))));
        }

        @Override
        public int getParallelLimit() {
            return 16 * getMotorCasingTier();
        }

    }

}
