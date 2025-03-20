package magicbook.gtlitecore.common.metatileentity.multiblock.advanced;

import gregtech.api.GTValues;
import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiMapMultiblockController;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.MultiblockShapeInfo;
import gregtech.api.pattern.PatternMatchContext;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.util.GTUtility;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.common.ConfigHolder;
import gregtech.common.blocks.BlockBoilerCasing;
import gregtech.common.blocks.BlockFireboxCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import lombok.Getter;
import magicbook.gtlitecore.api.GTLiteAPI;
import magicbook.gtlitecore.api.block.impl.WrappedIntTier;
import magicbook.gtlitecore.api.capability.GTLiteDataCodes;
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps;
import magicbook.gtlitecore.api.unification.GTLiteMaterials;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import one.util.streamex.StreamEx;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static gregtech.api.util.RelativeDirection.DOWN;
import static gregtech.api.util.RelativeDirection.FRONT;
import static gregtech.api.util.RelativeDirection.LEFT;
import static magicbook.gtlitecore.api.utils.GTLiteUtility.getOrDefault;
import static magicbook.gtlitecore.api.utils.StructureUtility.motorCasings;

public class MetaTileEntityLargeBurnerReactor extends MultiMapMultiblockController
{

    @Getter
    private int casingTier;

    // =================================================================================================================
    public MetaTileEntityLargeBurnerReactor(ResourceLocation metaTileEntityId)
    {
        super(metaTileEntityId, new RecipeMap[] {
                GTLiteRecipeMaps.BURNER_REACTOR_RECIPES(),
                GTLiteRecipeMaps.ROASTER_RECIPES()
        });
        this.recipeMapWorkable = new LargeBurnerReactorRecipeLogic(this);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity)
    {
        return new MetaTileEntityLargeBurnerReactor(metaTileEntityId);
    }

    // =================================================================================================================
    @Override
    protected void formStructure(PatternMatchContext context)
    {
        super.formStructure(context);
        Object type = context.get("MotorCasingTieredStats");
        this.casingTier = getOrDefault(
                () -> type instanceof WrappedIntTier,
                () -> ((WrappedIntTier) type).getIntTier(), 0);
    }

    @Override
    public void invalidateStructure()
    {
        super.invalidateStructure();
        this.casingTier = 0;
        this.replaceFireboxAsActive(false);
    }

    @NotNull
    @Override
    protected BlockPattern createStructurePattern()
    {
        return FactoryBlockPattern.start()
                .aisle("     ", "     ", " P P ", " P P ", " P P ")
                .aisle("F   F", "FBBBF", "XPXPX", "XXXXX", " P P ")
                .aisle("     ", "XBBBX", "XPNPX", "XPMPX", " P P ")
                .aisle("F   F", "FBBBF", "XXSXX", "XXXXX", "     ")
                .where('S', selfPredicate())
                .where('X', states(getCasingState())
                        .setMinGlobalLimited(14)
                        .or(autoAbilities(true, true, true, true, true, true, false)))
                .where('P', states(getPipeCasingState()))
                .where('B', states(getFireboxCasingState()))
                .where('F', frames(GTLiteMaterials.IncoloyMA813))
                .where('M', abilities(MultiblockAbility.MUFFLER_HATCH))
                .where('N', motorCasings())
                .where(' ', any())
                .build();
    }

    private static IBlockState getCasingState()
    {
        return GTLiteMetaBlocks.METAL_CASING_02.getState(BlockMetalCasing02.MetalCasingType.INCOLOY_MA813);
    }

    private static IBlockState getPipeCasingState()
    {
        return MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.TITANIUM_PIPE);
    }

    private static IBlockState getFireboxCasingState()
    {
        return MetaBlocks.BOILER_FIREBOX_CASING.getState(BlockFireboxCasing.FireboxCasingType.TITANIUM_FIREBOX);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart)
    {
        return GTLiteTextures.INCOLOY_MA813_CASING;
    }

    @SideOnly(Side.CLIENT)
    @NotNull
    @Override
    protected ICubeRenderer getFrontOverlay()
    {
        return GTLiteTextures.LARGE_BURNER_REACTOR_OVERLAY;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes()
    {
        List<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
        MultiblockShapeInfo.Builder builder = MultiblockShapeInfo.builder(LEFT, DOWN, FRONT)
                .aisle("     ", "     ", " P P ", " P P ", " P P ")
                .aisle("F   F", "FBBBF", "XPEPX", "XXXXX", " P P ")
                .aisle("     ", "XBBBX", "XPNPX", "XPOPX", " P P ")
                .aisle("F   F", "FBBBF", "XISJX", "XKMLX", "     ")
                .where('S', GTLiteMetaTileEntities.LARGE_BURNER_REACTOR, EnumFacing.SOUTH)
                .where('X', getCasingState())
                .where('P', getPipeCasingState())
                .where('B', getFireboxCasingState())
                .where('F', MetaBlocks.FRAMES.get(GTLiteMaterials.IncoloyMA813).getBlock(GTLiteMaterials.IncoloyMA813))
                .where('O', MetaTileEntities.MUFFLER_HATCH[1], EnumFacing.UP)
                .where('M', () -> ConfigHolder.machines.enableMaintenance ? MetaTileEntities.MAINTENANCE_HATCH : getCasingState(), EnumFacing.SOUTH)
                .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[0], EnumFacing.SOUTH)
                .where('I', MetaTileEntities.ITEM_IMPORT_BUS[0], EnumFacing.SOUTH)
                .where('J', MetaTileEntities.ITEM_EXPORT_BUS[0], EnumFacing.SOUTH)
                .where('K', MetaTileEntities.FLUID_IMPORT_HATCH[0], EnumFacing.SOUTH)
                .where('L', MetaTileEntities.FLUID_EXPORT_HATCH[0], EnumFacing.SOUTH);
        StreamEx.of(GTLiteAPI.MAP_MOTOR_CASING.entrySet())
                .sortedByInt(entry -> ((WrappedIntTier) entry.getValue()).getIntTier())
                .forEach(entry -> shapeInfo.add(builder.where('N', entry.getKey()).build()));
        return shapeInfo;
    }

    // =================================================================================================================
    @Override
    public void update()
    {
        super.update();
        if (this.getWorld().isRemote && this.casingTier == 0)
            this.writeCustomData(GTLiteDataCodes.INITIALIZE_TIERED_MACHINE, buf -> {});
    }

    @Override
    public void receiveCustomData(int dataId, PacketBuffer buf)
    {
        super.receiveCustomData(dataId, buf);
        if (dataId == GTLiteDataCodes.INITIALIZE_TIERED_MACHINE)
            this.writeCustomData(GTLiteDataCodes.UPDATE_TIERED_MACHINE, b -> b.writeInt(this.casingTier));
        if (dataId == GTLiteDataCodes.UPDATE_TIERED_MACHINE)
            this.casingTier = buf.readInt();
    }

    @Override
    public void writeInitialSyncData(PacketBuffer buf)
    {
        super.writeInitialSyncData(buf);
        buf.writeInt(this.casingTier);
    }

    @Override
    public void receiveInitialSyncData(PacketBuffer buf)
    {
        super.receiveInitialSyncData(buf);
        this.casingTier = buf.readInt();
    }

    @Override
    public void onRemoval()
    {
        super.onRemoval();
        if (!this.getWorld().isRemote && this.isStructureFormed())
        {
            // Replaced Firebox Casing textures as active textures.
            this.replaceFireboxAsActive(false);
        }
    }

    public void replaceFireboxAsActive(boolean isActive)
    {
        BlockPos centerPos = this.getPos().offset(this.getFrontFacing().getOpposite()).down();
        for (int x = -1; x <= 1; x++)
        {
            for (int z = -1; z <= 1; z++)
            {
                BlockPos blockPos = centerPos.add(x, 0, z);
                IBlockState blockState = this.getWorld().getBlockState(blockPos);
                if (blockState.getBlock() instanceof BlockFireboxCasing)
                {
                    blockState = ((IExtendedBlockState) blockState).withProperty(BlockFireboxCasing.ACTIVE, isActive);
                    this.getWorld().setBlockState(blockPos, blockState);
                }
            }
        }
    }

    // =================================================================================================================
    @Override
    public void addInformation(ItemStack stack,
                               @Nullable World player,
                               @NotNull List<String> tooltip,
                               boolean advanced)
    {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("gtlitecore.machine.large_burner_reactor.tooltip.1"));
        tooltip.add(I18n.format("gtlitecore.machine.large_burner_reactor.tooltip.2"));
        tooltip.add(I18n.format("gtlitecore.machine.large_burner_reactor.tooltip.3"));
        tooltip.add(I18n.format("gtlitecore.machine.large_burner_reactor.tooltip.4"));
    }

    // =================================================================================================================
    @Override
    public boolean canBeDistinct()
    {
        return true;
    }

    protected class LargeBurnerReactorRecipeLogic extends MultiblockRecipeLogic
    {

        public LargeBurnerReactorRecipeLogic(RecipeMapMultiblockController tileEntity)
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
            super.setMaxProgress((int) (Math.floor(maxProgress * Math.pow(0.5, GTUtility.getTierByVoltage(getMaxVoltage())))));
        }

        @Override
        public int getParallelLimit()
        {
            return 16 * getCasingTier();
        }

    }

}
