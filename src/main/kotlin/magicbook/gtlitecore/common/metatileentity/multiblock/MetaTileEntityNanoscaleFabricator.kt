package magicbook.gtlitecore.common.metatileentity.multiblock;

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
import gregtech.api.pattern.TraceabilityPredicate;
import gregtech.api.recipes.Recipe;
import gregtech.api.util.BlockInfo;
import gregtech.api.util.GTUtility;
import gregtech.api.util.TextComponentUtil;
import gregtech.api.util.TextFormattingUtil;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.utils.TooltipHelper;
import gregtech.common.ConfigHolder;
import gregtech.common.blocks.BlockGlassCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityItemBus;
import gregtech.core.sound.GTSoundEvents;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import lombok.Getter;
import magicbook.gtlitecore.api.GTLiteAPI;
import magicbook.gtlitecore.api.block.IBlockTier;
import magicbook.gtlitecore.api.block.impl.WrappedIntTier;
import magicbook.gtlitecore.api.capability.GTLiteDataCodes;
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps;
import magicbook.gtlitecore.api.recipe.property.NoCoilTemperatureProperty;
import magicbook.gtlitecore.api.utils.stream.LazyStreams;
import magicbook.gtlitecore.client.renderer.texture.GTLiteTextures;
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks;
import magicbook.gtlitecore.common.block.blocks.BlockCrucible;
import magicbook.gtlitecore.common.block.blocks.BlockMetalCasing02;
import magicbook.gtlitecore.common.block.blocks.BlockMultiblockCasing01;
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities;
import net.minecraft.block.Block;
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
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static gregtech.api.GTValues.ULV;
import static gregtech.api.util.RelativeDirection.DOWN;
import static gregtech.api.util.RelativeDirection.FRONT;
import static gregtech.api.util.RelativeDirection.LEFT;
import static magicbook.gtlitecore.api.utils.GTLiteUtility.consistent;
import static magicbook.gtlitecore.api.utils.GTLiteUtility.getOrDefault;
import static magicbook.gtlitecore.api.utils.GTLiteUtility.maxLength;
import static magicbook.gtlitecore.api.utils.StructureUtility.emitterCasings;
import static magicbook.gtlitecore.api.utils.StructureUtility.robotArmCasings;

public class MetaTileEntityNanoscaleFabricator extends RecipeMapMultiblockController
{
    @Getter
    private int emitterCasingTier;
    @Getter
    private int robotArmCasingTier;
    @Getter
    private int temperature;

    // Pseudo crucible hash map for decorative JEI page multiblock shape infos.
    // Do not merge it to GTLiteAPI maps because only this mte used this map.
    private static final Object2ObjectOpenHashMap<IBlockState, IBlockTier> CRUCIBLES = new Object2ObjectOpenHashMap<>();

    private static boolean hasRegistered = false;
    private static List<IBlockState> emitterCasings;
    private static List<IBlockState> robotArmCasings;
    private static List<IBlockState> crucibles;

    // =================================================================================================================
    public MetaTileEntityNanoscaleFabricator(ResourceLocation metaTileEntityId)
    {
        super(metaTileEntityId, GTLiteRecipeMaps.MOLECULAR_BEAM_RECIPES());
        this.recipeMapWorkable = new NanoscaleFabricatorRecipeLogic(this);
        this.registerCasingMaps();
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity)
    {
        return new MetaTileEntityNanoscaleFabricator(metaTileEntityId);
    }

    private void registerCasingMaps()
    {
        if (hasRegistered) return;
        // Locally initialized the decorative crucible map, this is not a tiered stats.
        for (BlockCrucible.CrucibleType tier : BlockCrucible.CrucibleType.values())
            CRUCIBLES.put(GTLiteMetaBlocks.CRUCIBLE.getState(tier),
                    new WrappedIntTier(tier, tier.ordinal() + 1));

        List<IBlockState> emitterCasing = LazyStreams.fastSortedByKey(GTLiteAPI.MAP_EMITTER_CASING);
        List<IBlockState> robotArmCasing = LazyStreams.fastSortedByKey(GTLiteAPI.MAP_ROBOT_ARM_CASING);
        List<IBlockState> crucible = LazyStreams.fastSortedByKey(CRUCIBLES);

        int maxLength = maxLength(new ArrayList<List<IBlockState>>() {{
            add(emitterCasing);
            add(robotArmCasing);
            add(crucible);
        }});
        emitterCasings = consistent(emitterCasing, maxLength);
        robotArmCasings = consistent(robotArmCasing, maxLength);
        crucibles = consistent(crucible, maxLength);
        hasRegistered = true;
    }

    // =================================================================================================================
    @Override
    protected void formStructure(PatternMatchContext context)
    {
        super.formStructure(context);
        Object type1 = context.get("EmitterCasingTieredStats");
        Object type2 = context.get("RobotArmCasingTieredStats");
        this.emitterCasingTier = getOrDefault(
                () -> type1 instanceof WrappedIntTier,
                () -> ((WrappedIntTier) type1).getIntTier(), 0);
        this.robotArmCasingTier = getOrDefault(
                () -> type2 instanceof WrappedIntTier,
                () -> ((WrappedIntTier) type2).getIntTier(), 0);
        // Initialized crucible temperature and amount info.
        int crucibleAmount = context.getInt("CrucibleAmount");
        if (crucibleAmount != 0)
            this.temperature = context.getInt("Temperature") / crucibleAmount;
        else
            this.temperature = 0;
    }

    @Override
    public void invalidateStructure()
    {
        super.invalidateStructure();
        this.emitterCasingTier = 0;
        this.robotArmCasingTier = 0;
        this.temperature = 0;
    }

    @NotNull
    @Override
    protected BlockPattern createStructurePattern()
    {
         return FactoryBlockPattern.start()
                 .aisle("   TTT   ", "   TIT   ", "   TCT   ", "         ")
                 .aisle("  XXXXX  ", "  XXRXX  ", "  XXXXX  ", "  XXXXX  ")
                 .aisle(" XXXXXXX ", " X#####X ", " X#####X ", " XXGGGXX ")
                 .aisle("TXXTTTXXT", "TX##Z##XT", "TX#####XT", " XGGGGGX ")
                 .aisle("TXXTITXXT", "IR#ZAZ#RI", "CX#####XC", " XGGGGGX ")
                 .aisle("TXXTTTXXT", "TX##Z##XT", "TX#####XT", " XGGGGGX ")
                 .aisle(" XXXXXXX ", " X#####X ", " X#####X ", " XXGGGXX ")
                 .aisle("  XXXXX  ", "  XXRXX  ", "  XXXXX  ", "  XXSXX  ")
                 .aisle("   TTT   ", "   TIT   ", "   TCT   ", "         ")
                 .where('S', selfPredicate())
                 .where('X', states(getCasingState())
                         .setMinGlobalLimited(84)
                         .or(autoAbilities(true, true, false, true, true, false, false)))
                 .where('T', states(getSecondCasingState())
                         .setMinGlobalLimited(36))
                 .where('G', states(getGlassState()))
                 .where('I', metaTileEntities(MetaTileEntities.ITEM_IMPORT_BUS[ULV]) // TODO Allowed a Huge Item Import Bus which has Int.MAX_VALUE stack size?
                         .or(states(getSecondCasingState())))
                 .where('C', states(getSecondCasingState())
                         .or(cruciblePredicate()))
                 .where('A', states(getThirdCasingState()))
                 .where('Z', emitterCasings())
                 .where('R', robotArmCasings())
                 .where('#', air())
                 .where(' ', any())
                 .build();
    }

    private static IBlockState getCasingState()
    {
        return GTLiteMetaBlocks.METAL_CASING_02.getState(BlockMetalCasing02.MetalCasingType.TITANIUM_TUNGSTEN_CARBIDE);
    }

    private static IBlockState getSecondCasingState()
    {
        return GTLiteMetaBlocks.METAL_CASING_02.getState(BlockMetalCasing02.MetalCasingType.HSLA_STEEL);
    }

    private static IBlockState getThirdCasingState()
    {
        return GTLiteMetaBlocks.MULTIBLOCK_CASING_01.getState(BlockMultiblockCasing01.MultiblockCasingType.SUBSTRATE_CASING);
    }

    private static IBlockState getGlassState()
    {
        return MetaBlocks.TRANSPARENT_CASING.getState(BlockGlassCasing.CasingType.LAMINATED_GLASS);
    }

    @ApiStatus.Internal
    @NotNull
    private TraceabilityPredicate cruciblePredicate()
    {
        return new TraceabilityPredicate(blockWorldState -> {
            IBlockState state = blockWorldState.getBlockState();
            Block block = state.getBlock();
            if (block instanceof BlockCrucible)
            {
                int storedTemperature = blockWorldState.getMatchContext().getOrPut("Temperature", 0);
                blockWorldState.getMatchContext().set("Temperature", ((BlockCrucible) block).getState(state).getTemperature() + storedTemperature);
                int storedCrucibleAmount = blockWorldState.getMatchContext().getOrPut("CrucibleAmount", 0);
                blockWorldState.getMatchContext().set("CrucibleAmount", 1 + storedCrucibleAmount);
                return true;
            }
            return false;
        }, () -> StreamEx.of(BlockCrucible.CrucibleType.values())
                .sortedByInt(BlockCrucible.CrucibleType::getTemperature)
                .map(type -> new BlockInfo(GTLiteMetaBlocks.CRUCIBLE.getState(type), null))
                .toArray(BlockInfo[]::new));
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart)
    {
        if (sourcePart instanceof MetaTileEntityItemBus
                && ((MetaTileEntityItemBus) sourcePart).getExportItems().getSlots() == 0)
            return GTLiteTextures.HSLA_STEEL_CASING;
        return GTLiteTextures.TITANIUM_TUNGSTEN_CARBIDE_CASING;
    }

    @SideOnly(Side.CLIENT)
    @NotNull
    @Override
    protected ICubeRenderer getFrontOverlay()
    {
        return GTLiteTextures.NANOSCALE_FABRICATOR_OVERLAY;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes()
    {
        List<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
        MultiblockShapeInfo.Builder builder = MultiblockShapeInfo.builder(LEFT, DOWN, FRONT)
                .aisle("   TTT   ", "   T3T   ", "   TCT   ", "         ")
                .aisle("  XXXXX  ", "  XXRXX  ", "  XXXXX  ", "  XENEX  ")
                .aisle(" XXXXXXX ", " X     X ", " X     X ", " XXGGGXX ")
                .aisle("TXXTTTXXT", "TX  Z  XT", "TX     XT", " XGGGGGX ")
                .aisle("TXXTTTXXT", "1R ZAZ R2", "CX     XC", " XGGGGGX ")
                .aisle("TXXTTTXXT", "TX  Z  XT", "TX     XT", " XGGGGGX ")
                .aisle(" XXXXXXX ", " X     X ", " X     X ", " XXGGGXX ")
                .aisle("  XXXXX  ", "  XXRXX  ", "  XXXXX  ", "  XJSKX  ")
                .aisle("   TTT   ", "   T0T   ", "   TCT   ", "         ")
                .where('S', GTLiteMetaTileEntities.NANOSCALE_FABRICATOR, EnumFacing.SOUTH)
                .where('X', getCasingState())
                .where('T', getSecondCasingState())
                .where('G', getGlassState())
                .where('0', MetaTileEntities.ITEM_IMPORT_BUS[ULV], EnumFacing.SOUTH)
                .where('1', MetaTileEntities.ITEM_IMPORT_BUS[ULV], EnumFacing.WEST)
                .where('2', MetaTileEntities.ITEM_IMPORT_BUS[ULV], EnumFacing.EAST)
                .where('3', MetaTileEntities.ITEM_IMPORT_BUS[ULV], EnumFacing.NORTH)
                .where('A', getThirdCasingState())
                .where('N', () -> ConfigHolder.machines.enableMaintenance ? MetaTileEntities.MAINTENANCE_HATCH : getCasingState(), EnumFacing.NORTH)
                .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[0], EnumFacing.NORTH)
                .where('J', MetaTileEntities.ITEM_EXPORT_BUS[0], EnumFacing.SOUTH)
                .where('K', MetaTileEntities.FLUID_IMPORT_HATCH[0], EnumFacing.SOUTH);
        AtomicInteger count = new AtomicInteger();
        StreamEx.of(emitterCasings)
                .map(b -> {
                    if (builder != null)
                    {
                        builder.where('Z', b);
                        builder.where('R', robotArmCasings.get(count.get()));
                        builder.where('C', crucibles.get(count.get()));
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
    public void update() {
        super.update();
        if (this.getWorld().isRemote)
        {
            if (this.emitterCasingTier == 0)
                this.writeCustomData(GTLiteDataCodes.INITIALIZE_TIERED_MACHINE, buf -> {});
            if (this.robotArmCasingTier == 0)
                this.writeCustomData(GTLiteDataCodes.INITIALIZE_SUB_TIERED_MACHINE, buf -> {});
        }
    }

    @Override
    public void receiveCustomData(int dataId, PacketBuffer buf)
    {
        super.receiveCustomData(dataId, buf);
        if (dataId == GTLiteDataCodes.INITIALIZE_TIERED_MACHINE)
            this.writeCustomData(GTLiteDataCodes.UPDATE_TIERED_MACHINE, b -> b.writeInt(this.emitterCasingTier));
        if (dataId == GTLiteDataCodes.INITIALIZE_SUB_TIERED_MACHINE)
            this.writeCustomData(GTLiteDataCodes.UPDATE_SUB_TIERED_MACHINE, b -> b.writeInt(this.robotArmCasingTier));
        if (dataId == GTLiteDataCodes.UPDATE_TIERED_MACHINE)
            this.emitterCasingTier = buf.readInt();
        if (dataId == GTLiteDataCodes.UPDATE_SUB_TIERED_MACHINE)
            this.robotArmCasingTier = buf.readInt();
    }

    // =================================================================================================================
    @Override
    public void addInformation(ItemStack stack,
                               @Nullable World world,
                               @NotNull List<String> tooltip,
                               boolean advanced)
    {
        super.addInformation(stack, world, tooltip, advanced);
        tooltip.add(I18n.format("gtlitecore.machine.nanoscale_fabricator.tooltip.1"));
        tooltip.add(TooltipHelper.RAINBOW_SLOW + I18n.format("gregtech.machine.perfect_oc"));
        tooltip.add(I18n.format("gtlitecore.machine.nanoscale_fabricator.tooltip.2"));
        tooltip.add(I18n.format("gtlitecore.machine.nanoscale_fabricator.tooltip.3"));
        tooltip.add(I18n.format("gtlitecore.machine.nanoscale_fabricator.tooltip.4"));
        tooltip.add(I18n.format("gtlitecore.machine.nanoscale_fabricator.tooltip.5"));
        tooltip.add(I18n.format("gtlitecore.machine.nanoscale_fabricator.tooltip.6"));
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
                                TextFormattingUtil.formatNumbers(this.temperature) + "K"
                        );
                        tl.add(TextComponentUtil.translationWithColor(
                                TextFormatting.GRAY,
                                "gtlitecore.machine.nanoscale_fabricator.average_temperature",
                                temperatureInfo
                        ));
                        ITextComponent temperatureLowerInfo = TextComponentUtil.stringWithColor(
                                TextFormatting.RED,
                                TextFormattingUtil.formatNumbers(this.temperature - 250) + "K"
                        );
                        ITextComponent temperatureUpperInfo = TextComponentUtil.stringWithColor(
                                TextFormatting.RED,
                                TextFormattingUtil.formatNumbers(this.temperature + 250) + "K"
                        );
                        tl.add(TextComponentUtil.translationWithColor(
                                TextFormatting.GRAY,
                                "gtlitecore.machine.nanoscale_fabricator.temperature_range",
                                temperatureLowerInfo, temperatureUpperInfo
                        ));
                    }
                })
                .addParallelsLine(this.recipeMapWorkable.getParallelLimit())
                .addWorkingStatusLine()
                .addProgressLine(this.recipeMapWorkable.getProgressPercent());
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

    protected class NanoscaleFabricatorRecipeLogic extends MultiblockRecipeLogic
    {

        public NanoscaleFabricatorRecipeLogic(RecipeMapMultiblockController tileEntity) {
            super(tileEntity, true);
        }

        @Override
        public boolean checkRecipe(@NotNull Recipe recipe) {
            int delta = temperature - recipe.getProperty(NoCoilTemperatureProperty.INSTANCE, 0);
            return (delta > 0 && delta < 250);
        }

        @Override
        public void setMaxProgress(int maxProgress)
        {
            super.setMaxProgress((int) (Math.floor(maxProgress * Math.pow(0.8, getEmitterCasingTier()))));
        }

        @Override
        public int getParallelLimit()
        {
            return 4 * getRobotArmCasingTier();
        }

    }

}
