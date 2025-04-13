package magicbook.gtlitecore.common.metatileentity.multiblock;

import com.google.common.base.Preconditions;
import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.capability.impl.NotifiableItemStackHandler;
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
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.client.utils.TooltipHelper;
import gregtech.common.ConfigHolder;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.BlockMultiblockCasing;
import gregtech.common.blocks.BlockTurbineCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.core.sound.GTSoundEvents;
import lombok.Getter;
import magicbook.gtlitecore.api.GTLiteAPI;
import magicbook.gtlitecore.api.block.impl.WrappedIntTier;
import magicbook.gtlitecore.api.capability.GTLiteDataCodes;
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps;
import magicbook.gtlitecore.api.unification.GTLiteMaterials;
import magicbook.gtlitecore.api.utils.GTLiteUtility;
import magicbook.gtlitecore.api.utils.stream.LazyStreams;
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks;
import magicbook.gtlitecore.common.block.blocks.BlockMetalCasing03;
import magicbook.gtlitecore.common.block.blocks.BlockMultiblockCasing01;
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.IItemHandlerModifiable;
import one.util.streamex.StreamEx;
import org.jetbrains.annotations.ApiStatus;
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
import static magicbook.gtlitecore.api.utils.StructureUtility.pistonCasings;

public class MetaTileEntityBedrockDrillingRig extends RecipeMapMultiblockController
{

    @Getter
    private int pistonCasingTier;
    @Getter
    private int motorCasingTier;

    private static boolean hasRegistered = false;
    private static List<IBlockState> pistonCasings;
    private static List<IBlockState> motorCasings;

    // Target block at drill head block in multiblock structure bottom.
    protected BlockPos targetBlock = null;

    // =================================================================================================================
    public MetaTileEntityBedrockDrillingRig(ResourceLocation metaTileEntityId)
    {
        super(metaTileEntityId, GTLiteRecipeMaps.DRILLING_RECIPES());
        this.recipeMapWorkable = new BedrockDrillingRigWorkableHandler(this);
        this.registerCasingMaps();
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity)
    {
        return new MetaTileEntityBedrockDrillingRig(metaTileEntityId);
    }

    private void registerCasingMaps()
    {
        if (hasRegistered) return;
        List<IBlockState> pistonCasing = LazyStreams.fastSortedByKey(GTLiteAPI.MAP_PISTON_CASING);
        List<IBlockState> motorCasing = LazyStreams.fastSortedByKey(GTLiteAPI.MAP_MOTOR_CASING);
        int maxLength = maxLength(new ArrayList<List<IBlockState>>() {{
            add(pistonCasing);
            add(motorCasing);
        }});
        pistonCasings = consistent(pistonCasing, maxLength);
        motorCasings = consistent(motorCasing, maxLength);
        hasRegistered = true;
    }

    // =================================================================================================================
    @Override
    protected void formStructure(PatternMatchContext context)
    {
        super.formStructure(context);
        Object type1 = context.get("PistonCasingTieredStats");
        Object type2 = context.get("MotorCasingTieredStats");
        this.pistonCasingTier = getOrDefault(
                () -> type1 instanceof WrappedIntTier,
                () -> ((WrappedIntTier) type1).getIntTier(), 0);
        this.motorCasingTier = getOrDefault(
                () -> type2 instanceof WrappedIntTier,
                () -> ((WrappedIntTier) type2).getIntTier(), 0);
        // Transformed targetBlock from blockPos in inputInventory.
        if (this.targetBlock != null)
        {
            this.inputInventory.setStackInSlot(0, GTLiteUtility.toItem(this.getWorld()
                    .getBlockState(this.targetBlock)));
        }
    }

    @Override
    public void invalidateStructure()
    {
        super.invalidateStructure();
        this.pistonCasingTier = 0;
        this.motorCasingTier = 0;
        this.targetBlock = null;
        this.inputInventory.setStackInSlot(0, ItemStack.EMPTY);
    }

    @Override
    protected void initializeAbilities()
    {
        super.initializeAbilities();
        this.inputInventory = new NotifiableItemStackHandler(this, 1, this, false);
    }

    @NotNull
    @Override
    protected BlockPattern createStructurePattern()
    {
        return FactoryBlockPattern.start()
                .aisle("       ", "XXXXXXX", "X     X", "X     X", "X     X", "X     X", "X     X", "XXXXXXX")
                .aisle("       ", "X     X", "       ", " F   F ", "       ", "       ", "       ", "X  F  X")
                .aisle("       ", "X     X", "   C   ", "  FCF  ", "   C   ", "  CVC  ", "  CVC  ", "X BBB X")
                .aisle("   R   ", "X  D  X", "  COC  ", "  CQC  ", "  CGC  ", "  VGV  ", "  VGV  ", "XFBBBFX")
                .aisle("       ", "X     X", "   C   ", "  FCF  ", "   C   ", "  CSC  ", "  CVC  ", "X BBB X")
                .aisle("       ", "X     X", "       ", " F   F ", "       ", "       ", "       ", "X  F  X")
                .aisle("       ", "XXXXXXX", "X     X", "X     X", "X     X", "X     X", "X     X", "XXXXXXX")
                .where('S', selfPredicate())
                .where('X', states(getCasingState()))
                .where('B', states(getSecondCasingState())
                        .setMinGlobalLimited(4)
                        .or(autoAbilities(true, true, false, true, false, true, true)))
                .where('C', states(getSecondCasingState()))
                .where('D', states(getThirdCasingState()))
                .where('F', frames(GTLiteMaterials.HSLASteel))
                .where('G', states(getGearboxCasingState()))
                .where('V', states(getFourthCasingState()))
                .where('O', pistonCasings())
                .where('Q', motorCasings())
                .where('R', blockPredicate())
                .where(' ', any())
                .build();
    }

    private static IBlockState getCasingState()
    {
        return GTLiteMetaBlocks.METAL_CASING_03.getState(BlockMetalCasing03.MetalCasingType.TRINAQUADALLOY);
    }

    private static IBlockState getSecondCasingState()
    {
        return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STEEL_SOLID);
    }

    private static IBlockState getGearboxCasingState()
    {
        return MetaBlocks.TURBINE_CASING.getState(BlockTurbineCasing.TurbineCasingType.TUNGSTENSTEEL_GEARBOX);
    }

    private static IBlockState getThirdCasingState()
    {
        return GTLiteMetaBlocks.MULTIBLOCK_CASING_01.getState(BlockMultiblockCasing01.MultiblockCasingType.DRILL_HEAD);
    }

    private static IBlockState getFourthCasingState()
    {
        return MetaBlocks.MULTIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.GRATE_CASING);
    }

    @ApiStatus.Internal
    @NotNull
    protected TraceabilityPredicate blockPredicate()
    {
        return new TraceabilityPredicate(blockWorldState -> {
            this.targetBlock = blockWorldState.getPos();
            if (this.isStructureFormed()) {
                this.inputInventory.setStackInSlot(0, GTLiteUtility.toItem(this.getWorld()
                        .getBlockState(this.targetBlock)));
            }
            return true;
        });
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart)
    {
        return Textures.SOLID_STEEL_CASING;
    }

    @SideOnly(Side.CLIENT)
    @NotNull
    @Override
    protected ICubeRenderer getFrontOverlay()
    {
        return Textures.PROCESSING_ARRAY_OVERLAY;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        List<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
        MultiblockShapeInfo.Builder builder = MultiblockShapeInfo.builder(LEFT, DOWN, FRONT)
                .aisle("       ", "XXXXXXX", "X     X", "X     X", "X     X", "X     X", "X     X", "XXXXXXX")
                .aisle("       ", "X     X", "       ", " F   F ", "       ", "       ", "       ", "X  F  X")
                .aisle("       ", "X     X", "   C   ", "  FCF  ", "   C   ", "  CVC  ", "  CVC  ", "X BMB X")
                .aisle("   R   ", "X  D  X", "  COC  ", "  CQC  ", "  CGC  ", "  VGV  ", "  VGV  ", "XFJNLFX")
                .aisle("       ", "X     X", "   C   ", "  FCF  ", "   C   ", "  CSC  ", "  CVC  ", "X BEB X")
                .aisle("       ", "X     X", "       ", " F   F ", "       ", "       ", "       ", "X  F  X")
                .aisle("       ", "XXXXXXX", "X     X", "X     X", "X     X", "X     X", "X     X", "XXXXXXX")
                .where('S', GTLiteMetaTileEntities.BEDROCK_DRILLING_RIG, EnumFacing.SOUTH)
                .where('X', getCasingState())
                .where('B', getSecondCasingState())
                .where('C', getSecondCasingState())
                .where('D', getThirdCasingState())
                .where('F', MetaBlocks.FRAMES.get(GTLiteMaterials.HSLASteel).getBlock(GTLiteMaterials.HSLASteel))
                .where('G', getGearboxCasingState())
                .where('V', getFourthCasingState())
                .where('M', MetaTileEntities.MUFFLER_HATCH[1], EnumFacing.UP)
                .where('N', () -> ConfigHolder.machines.enableMaintenance ? MetaTileEntities.MAINTENANCE_HATCH : getCasingState(), EnumFacing.UP)
                .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[0], EnumFacing.UP)
                .where('J', MetaTileEntities.ITEM_EXPORT_BUS[0], EnumFacing.UP)
                .where('L', MetaTileEntities.FLUID_EXPORT_HATCH[0], EnumFacing.UP);
        AtomicInteger count = new AtomicInteger();
        StreamEx.of(pistonCasings)
                .map(b -> {
                    if (builder != null) {
                        builder.where('O', b);
                        builder.where('Q', motorCasings.get(count.get()));
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
            if (this.pistonCasingTier == 0)
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
            this.writeCustomData(GTLiteDataCodes.UPDATE_TIERED_MACHINE, b -> b.writeInt(this.pistonCasingTier));
        if (dataId == GTLiteDataCodes.INITIALIZE_SUB_TIERED_MACHINE)
            this.writeCustomData(GTLiteDataCodes.UPDATE_SUB_TIERED_MACHINE, b -> b.writeInt(this.motorCasingTier));
        if (dataId == GTLiteDataCodes.UPDATE_TIERED_MACHINE)
            this.pistonCasingTier = buf.readInt();
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
        tooltip.add(TooltipHelper.RAINBOW_SLOW + I18n.format("gregtech.machine.perfect_oc"));
        tooltip.add(I18n.format("gtlitecore.machine.bedrock_drilling_rig.tooltip.1"));
        tooltip.add(I18n.format("gtlitecore.machine.bedrock_drilling_rig.tooltip.2"));
        tooltip.add(I18n.format("gtlitecore.machine.bedrock_drilling_rig.tooltip.3"));
        tooltip.add(I18n.format("gtlitecore.machine.bedrock_drilling_rig.tooltip.4"));
        tooltip.add(I18n.format("gtlitecore.machine.bedrock_drilling_rig.tooltip.5"));
        tooltip.add(I18n.format("gtlitecore.machine.bedrock_drilling_rig.tooltip.6"));
    }

    // =================================================================================================================
    @Override
    public boolean canBeDistinct()
    {
        return false;
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

    protected class BedrockDrillingRigWorkableHandler extends MultiblockRecipeLogic
    {

        public BedrockDrillingRigWorkableHandler(RecipeMapMultiblockController tileEntity)
        {
            super(tileEntity, true);
        }

        @NotNull
        @Override
        public MetaTileEntityBedrockDrillingRig getMetaTileEntity()
        {
            return (MetaTileEntityBedrockDrillingRig) (super.getMetaTileEntity());
        }

        @Nullable
        @Override
        protected Recipe setupAndConsumeRecipeInputs(@NotNull Recipe recipe,
                                                     @NotNull IItemHandlerModifiable importInventory)
        {
            if (!recipe.getInputs().get(0).isNonConsumable())
            {
                MetaTileEntityBedrockDrillingRig mte = getMetaTileEntity();
                Preconditions.checkNotNull(mte);
                mte.getWorld().destroyBlock(mte.targetBlock, false);
            }
            return super.setupAndConsumeRecipeInputs(recipe, importInventory);
        }


        @Override
        public void setMaxProgress(int maxProgress)
        {
            super.setMaxProgress((int) (Math.floor(maxProgress * Math.pow(0.8, getMotorCasingTier()))));
        }

        @Override
        public int getParallelLimit()
        {
            return 16 * getPistonCasingTier();
        }

    }

}
