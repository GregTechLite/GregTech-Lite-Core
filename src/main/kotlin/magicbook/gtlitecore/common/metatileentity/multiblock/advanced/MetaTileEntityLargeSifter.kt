package magicbook.gtlitecore.common.metatileentity.multiblock.advanced;

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
import gregtech.api.util.GTUtility;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.utils.TooltipHelper;
import gregtech.common.ConfigHolder;
import gregtech.common.blocks.BlockMultiblockCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import lombok.Getter;
import magicbook.gtlitecore.api.GTLiteAPI;
import magicbook.gtlitecore.api.block.impl.WrappedIntTier;
import magicbook.gtlitecore.api.capability.GTLiteDataCodes;
import magicbook.gtlitecore.client.renderer.texture.GTLiteTextures;
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks;
import magicbook.gtlitecore.common.block.blocks.BlockMetalCasing01;
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

import static gregtech.api.util.RelativeDirection.DOWN;
import static gregtech.api.util.RelativeDirection.FRONT;
import static gregtech.api.util.RelativeDirection.LEFT;
import static magicbook.gtlitecore.api.utils.GTLiteUtility.getOrDefault;
import static magicbook.gtlitecore.api.utils.StructureUtility.conveyorCasings;

public class MetaTileEntityLargeSifter extends RecipeMapMultiblockController
{

    @Getter
    private int casingTier;

    // =================================================================================================================
    public MetaTileEntityLargeSifter(ResourceLocation metaTileEntityId)
    {
        super(metaTileEntityId, RecipeMaps.SIFTER_RECIPES);
        this.recipeMapWorkable = new LargeSifterRecipeLogic(this);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity)
    {
        return new MetaTileEntityLargeSifter(metaTileEntityId);
    }

    // =================================================================================================================
    @Override
    protected void formStructure(PatternMatchContext context)
    {
        super.formStructure(context);
        Object type = context.get("ConveyorCasingTieredStats");
        this.casingTier = getOrDefault(
                () -> type instanceof WrappedIntTier,
                () -> ((WrappedIntTier) type).getIntTier(), 0);
    }

    @Override
    public void invalidateStructure()
    {
        super.invalidateStructure();
        this.casingTier = 0;
    }

    @NotNull
    @Override
    protected BlockPattern createStructurePattern()
    {
        return FactoryBlockPattern.start()
                .aisle("CCCCC", "MCCCM", "CCCCC")
                .aisle("CCCCC", "C###C", "CGGGC")
                .aisle("CCCCC", "C###C", "CGGGC")
                .aisle("CCCCC", "C###C", "CGGGC")
                .aisle("CCCCC", "MCSCM", "CCCCC")
                .where('S', selfPredicate())
                .where('C', states(getCasingState())
                        .setMinGlobalLimited(16)
                        .or(autoAbilities(true, true, true, true, true, true, false)))
                .where('G', states(getSecondCasingState()))
                .where('M', conveyorCasings())
                .where('#', air())
                .build();
    }

    private static IBlockState getCasingState()
    {
        return GTLiteMetaBlocks.METAL_CASING_01.getState(BlockMetalCasing01.MetalCasingType.EGLIN_STEEL);
    }

    private static IBlockState getSecondCasingState()
    {
        return MetaBlocks.MULTIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.GRATE_CASING);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart)
    {
        return GTLiteTextures.EGLIN_STEEL_CASING;
    }

    @SideOnly(Side.CLIENT)
    @NotNull
    @Override
    protected ICubeRenderer getFrontOverlay()
    {
        return GTLiteTextures.LARGE_SIFTER_OVERLAY;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes()
    {
        List<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
        MultiblockShapeInfo.Builder builder = MultiblockShapeInfo.builder(LEFT, DOWN, FRONT)
                .aisle("CCECC", "MCCCM", "CCCCC")
                .aisle("CCCCC", "C   C", "CGGGC")
                .aisle("CCCCC", "C   C", "CGGGC")
                .aisle("CCCCC", "C   C", "CGGGC")
                .aisle("CKNLC", "MISJM", "CCCCC")
                .where('S', GTLiteMetaTileEntities.LARGE_SIFTER, EnumFacing.SOUTH)
                .where('C', getCasingState())
                .where('G', getSecondCasingState())
                .where('N', () -> ConfigHolder.machines.enableMaintenance ? MetaTileEntities.MAINTENANCE_HATCH : getCasingState(), EnumFacing.SOUTH)
                .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[0], EnumFacing.SOUTH)
                .where('I', MetaTileEntities.ITEM_IMPORT_BUS[0], EnumFacing.SOUTH)
                .where('J', MetaTileEntities.ITEM_EXPORT_BUS[0], EnumFacing.SOUTH)
                .where('K', MetaTileEntities.FLUID_IMPORT_HATCH[0], EnumFacing.SOUTH)
                .where('L', MetaTileEntities.FLUID_EXPORT_HATCH[0], EnumFacing.SOUTH);
        StreamEx.of(GTLiteAPI.MAP_CONVEYOR_CASING.entrySet())
                .sortedByInt(entry -> ((WrappedIntTier) entry.getValue()).getIntTier())
                .forEach(entry -> shapeInfo.add(builder.where('M', entry.getKey()).build()));
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

    // =================================================================================================================
    @Override
    public void addInformation(ItemStack stack,
                               @Nullable World player,
                               @NotNull List<String> tooltip,
                               boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("gtlitecore.machine.large_sifter.tooltip.1"));
        tooltip.add(TooltipHelper.RAINBOW_SLOW + I18n.format("gregtech.machine.perfect_oc"));
        tooltip.add(I18n.format("gtlitecore.machine.large_sifter.tooltip.2"));
        tooltip.add(I18n.format("gtlitecore.machine.large_sifter.tooltip.3"));
    }

    // =================================================================================================================
    @Override
    public boolean canBeDistinct()
    {
        return true;
    }

    protected class LargeSifterRecipeLogic extends MultiblockRecipeLogic
    {

        public LargeSifterRecipeLogic(RecipeMapMultiblockController tileEntity)
        {
            super(tileEntity, true);
        }

        @Override
        public void setMaxProgress(int maxProgress)
        {
            super.setMaxProgress((int) (Math.floor(maxProgress * Math.pow(0.5, GTUtility.getTierByVoltage(this.getMaxVoltage())))));
        }

        @Override
        public int getParallelLimit()
        {
            return 8 * getCasingTier();
        }

    }

}
