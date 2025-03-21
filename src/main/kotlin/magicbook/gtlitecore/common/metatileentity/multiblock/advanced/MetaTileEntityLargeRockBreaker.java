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
import gregtech.common.metatileentities.MetaTileEntities;
import lombok.Getter;
import magicbook.gtlitecore.api.GTLiteAPI;
import magicbook.gtlitecore.api.block.impl.WrappedIntTier;
import magicbook.gtlitecore.api.capability.GTLiteDataCodes;
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
import static magicbook.gtlitecore.api.utils.StructureUtility.conveyorCasings;
import static magicbook.gtlitecore.api.utils.StructureUtility.pistonCasings;

public class MetaTileEntityLargeRockBreaker extends RecipeMapMultiblockController
{

    @Getter
    private int pistonCasingTier;
    @Getter
    private int conveyorCasingTier;

    private static boolean hasRegistered = false;
    private static List<IBlockState> pistonCasings;
    private static List<IBlockState> conveyorCasings;

    // =================================================================================================================
    public MetaTileEntityLargeRockBreaker(ResourceLocation metaTileEntityId)
    {
        super(metaTileEntityId, RecipeMaps.ROCK_BREAKER_RECIPES);
        this.recipeMapWorkable = new LargeRockBreakerRecipeLogic(this);
        this.registerCasingMaps();
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity)
    {
        return new MetaTileEntityLargeRockBreaker(metaTileEntityId);
    }

    private void registerCasingMaps()
    {
        if (hasRegistered) return;
        List<IBlockState> pistonCasing = StreamEx.of(GTLiteAPI.MAP_PISTON_CASING.entrySet())
                .sortedByInt(entry -> ((WrappedIntTier) entry.getValue()).getIntTier())
                .map(Map.Entry::getKey)
                .toList();
        List<IBlockState> conveyorCasing = StreamEx.of(GTLiteAPI.MAP_CONVEYOR_CASING.entrySet())
                .sortedByInt(entry -> ((WrappedIntTier) entry.getValue()).getIntTier())
                .map(Map.Entry::getKey)
                .toList();
        int maxLength = maxLength(new ArrayList<List<IBlockState>>() {{
            add(pistonCasing);
            add(conveyorCasing);
        }});
        pistonCasings = consistent(pistonCasing, maxLength);
        conveyorCasings = consistent(conveyorCasing, maxLength);
        hasRegistered = true;
    }

    // =================================================================================================================
    @Override
    protected void formStructure(PatternMatchContext context)
    {
        super.formStructure(context);
        Object type1 = context.get("PistonCasingTieredStats");
        Object type2 = context.get("ConveyorCasingTieredStats");
        this.pistonCasingTier = getOrDefault(
                () -> type1 instanceof WrappedIntTier,
                () -> ((WrappedIntTier) type1).getIntTier(), 0);
        this.conveyorCasingTier = getOrDefault(
                () -> type2 instanceof WrappedIntTier,
                () -> ((WrappedIntTier) type2).getIntTier(), 0);
    }

    @Override
    public void invalidateStructure()
    {
        super.invalidateStructure();
        this.pistonCasingTier = 0;
        this.conveyorCasingTier = 0;
    }

    @NotNull
    @Override
    protected BlockPattern createStructurePattern()
    {
        return FactoryBlockPattern.start()
                .aisle("CCC", "CCC", "CCC", "CCC")
                .aisle("CCC", "CMC", "CPC", "CCC")
                .aisle("CCC", "CSC", "CCC", "CCC")
                .where('S', selfPredicate())
                .where('C', states(getCasingState())
                        .setMinGlobalLimited(4)
                        .or(autoAbilities(true, true, true, true, false, false, false)))
                .where('M', conveyorCasings())
                .where('P', pistonCasings())
                .build();
    }

    private static IBlockState getCasingState() {
        return GTLiteMetaBlocks.METAL_CASING_02.getState(BlockMetalCasing02.MetalCasingType.BLACK_STEEL);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart)
    {
        return GTLiteTextures.BLACK_STEEL_CASING;
    }

    @SideOnly(Side.CLIENT)
    @NotNull
    @Override
    protected ICubeRenderer getFrontOverlay()
    {
        return Textures.ROCK_BREAKER_OVERLAY;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes()
    {
        List<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
        MultiblockShapeInfo.Builder builder = MultiblockShapeInfo.builder(LEFT, DOWN, FRONT)
                .aisle("CEC", "CCC", "CCC", "CCC")
                .aisle("CCC", "CMC", "CPC", "CCC")
                .aisle("CNC", "ISJ", "CCC", "CCC")
                .where('S', GTLiteMetaTileEntities.LARGE_ROCK_BREAKER, EnumFacing.SOUTH)
                .where('C', getCasingState())
                .where('N', () -> ConfigHolder.machines.enableMaintenance ? MetaTileEntities.MAINTENANCE_HATCH : getCasingState(), EnumFacing.SOUTH)
                .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[0], EnumFacing.NORTH)
                .where('I', MetaTileEntities.ITEM_IMPORT_BUS[0], EnumFacing.SOUTH)
                .where('J', MetaTileEntities.ITEM_EXPORT_BUS[0], EnumFacing.SOUTH);
        AtomicInteger count = new AtomicInteger();
        StreamEx.of(pistonCasings)
                .map(b -> {
                    if (builder != null)
                    {
                        builder.where('P', b);
                        builder.where('M', conveyorCasings.get(count.get()));
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
            if (this.conveyorCasingTier == 0)
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
            this.writeCustomData(GTLiteDataCodes.UPDATE_SUB_TIERED_MACHINE, b -> b.writeInt(this.conveyorCasingTier));
        if (dataId == GTLiteDataCodes.UPDATE_TIERED_MACHINE)
            this.pistonCasingTier = buf.readInt();
        if (dataId == GTLiteDataCodes.UPDATE_SUB_TIERED_MACHINE)
            this.conveyorCasingTier = buf.readInt();
    }

    // =================================================================================================================
    @Override
    public void addInformation(ItemStack stack,
                               @Nullable World player,
                               @NotNull List<String> tooltip,
                               boolean advanced)
    {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("gtlitecore.machine.large_rock_breaker.tooltip.1"));
        tooltip.add(I18n.format("gtlitecore.machine.large_rock_breaker.tooltip.2"));
        tooltip.add(I18n.format("gtlitecore.machine.large_rock_breaker.tooltip.3"));
        tooltip.add(I18n.format("gtlitecore.machine.large_rock_breaker.tooltip.4"));
    }

    // =================================================================================================================
    @Override
    public boolean canBeDistinct()
    {
        return false;
    }

    protected class LargeRockBreakerRecipeLogic extends MultiblockRecipeLogic
    {

        public LargeRockBreakerRecipeLogic(RecipeMapMultiblockController tileEntity)
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
            super.setMaxProgress((int) (Math.floor(maxProgress * Math.pow(0.8, getConveyorCasingTier()))));
        }

        @Override
        public int getParallelLimit()
        {
            return 16 * getPistonCasingTier();
        }

    }

}
