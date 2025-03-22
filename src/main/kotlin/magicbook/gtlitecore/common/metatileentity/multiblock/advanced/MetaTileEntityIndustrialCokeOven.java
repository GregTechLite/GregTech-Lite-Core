package magicbook.gtlitecore.common.metatileentity.multiblock.advanced;

import gregtech.api.GregTechAPI;
import gregtech.api.block.IHeatingCoilBlockStats;
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
import gregtech.common.blocks.BlockWireCoil;
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

import static gregtech.api.util.RelativeDirection.DOWN;
import static gregtech.api.util.RelativeDirection.FRONT;
import static gregtech.api.util.RelativeDirection.LEFT;

public class MetaTileEntityIndustrialCokeOven extends RecipeMapMultiblockController
{

    @Getter
    private int coilTier;

    // =================================================================================================================
    public MetaTileEntityIndustrialCokeOven(ResourceLocation metaTileEntityId)
    {
        super(metaTileEntityId, RecipeMaps.PYROLYSE_RECIPES);
        this.recipeMapWorkable = new IndustrialCokeOvenRecipeLogic(this);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity)
    {
        return new MetaTileEntityIndustrialCokeOven(metaTileEntityId);
    }

    // =================================================================================================================
    @Override
    protected void formStructure(PatternMatchContext context)
    {
        super.formStructure(context);
        Object type = context.get("CoilType");
        if (type instanceof IHeatingCoilBlockStats)
            this.coilTier = ((IHeatingCoilBlockStats) type).getTier();
        else
            this.coilTier = BlockWireCoil.CoilType.CUPRONICKEL.getTier();
    }

    @Override
    public void invalidateStructure()
    {
        super.invalidateStructure();
        this.coilTier = 0;
    }

    @NotNull
    @Override
    protected BlockPattern createStructurePattern()
    {
        return FactoryBlockPattern.start()
                .aisle("CCCCC", "CCCCC")
                .aisle("CCCCC", "CHCHC")
                .aisle("CCCCC", "CHCHC")
                .aisle("CCSCC", "CCCCC")
                .where('S', selfPredicate())
                .where('C', states(getCasingState())
                        .setMinGlobalLimited(12)
                        .or(autoAbilities(true, true, true, true, true, true, true)))
                .where('H', heatingCoils())
                .build();
    }

    private static IBlockState getCasingState()
    {
        return GTLiteMetaBlocks.METAL_CASING_02.getState(BlockMetalCasing02.MetalCasingType.ALUMINIUM_BRONZE);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart)
    {
        return GTLiteTextures.ALUMINIUM_BRONZE_CASING;
    }

    @SideOnly(Side.CLIENT)
    @NotNull
    @Override
    protected ICubeRenderer getFrontOverlay()
    {
        return GTLiteTextures.INDUSTRIAL_COKE_OVEN_OVERLAY;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes()
    {
        List<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
        MultiblockShapeInfo.Builder builder = MultiblockShapeInfo.builder(LEFT, DOWN, FRONT)
                .aisle("CCECC", "CCOCC")
                .aisle("CCCCC", "CHCHC")
                .aisle("CCCCC", "CHCHC")
                .aisle("CISJC", "CKNLC")
                .where('S', GTLiteMetaTileEntities.INDUSTRIAL_COKE_OVEN, EnumFacing.SOUTH)
                .where('C', getCasingState())
                .where('O', MetaTileEntities.MUFFLER_HATCH[1], EnumFacing.UP)
                .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[0], EnumFacing.NORTH)
                .where('N', () -> ConfigHolder.machines.enableMaintenance ? MetaTileEntities.MAINTENANCE_HATCH : getCasingState(), EnumFacing.SOUTH)
                .where('I', MetaTileEntities.ITEM_IMPORT_BUS[0], EnumFacing.SOUTH)
                .where('J', MetaTileEntities.ITEM_EXPORT_BUS[0], EnumFacing.SOUTH)
                .where('K', MetaTileEntities.FLUID_IMPORT_HATCH[0], EnumFacing.SOUTH)
                .where('L', MetaTileEntities.FLUID_EXPORT_HATCH[0], EnumFacing.SOUTH);
        StreamEx.of(GregTechAPI.HEATING_COILS.entrySet())
                .sortedByInt(entry -> entry.getValue().getTier())
                .forEach(entry -> shapeInfo.add(builder.where('H', entry.getKey()).build()));
        return shapeInfo;
    }

    // =================================================================================================================
    @Override
    public void update()
    {
        super.update();
        if (this.getWorld().isRemote && this.coilTier == 0)
            this.writeCustomData(GTLiteDataCodes.INITIALIZE_TIERED_MACHINE, buf -> {});
    }

    @Override
    public void receiveCustomData(int dataId, PacketBuffer buf)
    {
        super.receiveCustomData(dataId, buf);
        if (dataId == GTLiteDataCodes.INITIALIZE_TIERED_MACHINE)
            this.writeCustomData(GTLiteDataCodes.UPDATE_TIERED_MACHINE, b -> b.writeInt(this.coilTier));
        if (dataId == GTLiteDataCodes.UPDATE_TIERED_MACHINE)
            this.coilTier = buf.readInt();
    }

    @Override
    public void writeInitialSyncData(PacketBuffer buf)
    {
        super.writeInitialSyncData(buf);
        buf.writeInt(this.coilTier);
    }

    @Override
    public void receiveInitialSyncData(PacketBuffer buf)
    {
        super.receiveInitialSyncData(buf);
        this.coilTier = buf.readInt();
    }

    // =================================================================================================================
    @Override
    public void addInformation(ItemStack stack,
                               @Nullable World player,
                               @NotNull List<String> tooltip,
                               boolean advanced)
    {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("gtlitecore.machine.industrial_coke_oven.tooltip.1"));
        tooltip.add(I18n.format(TooltipHelper.RAINBOW_SLOW + I18n.format("gregtech.machine.perfect_oc")));
        tooltip.add(I18n.format("gtlitecore.machine.industrial_coke_oven.tooltip.2"));
        tooltip.add(I18n.format("gtlitecore.machine.industrial_coke_oven.tooltip.3"));
    }

    // =================================================================================================================
    @Override
    public boolean canBeDistinct()
    {
        return true;
    }

    @Override
    public boolean hasMufflerMechanics()
    {
        return true;
    }

    protected class IndustrialCokeOvenRecipeLogic extends MultiblockRecipeLogic
    {

        public IndustrialCokeOvenRecipeLogic(RecipeMapMultiblockController tileEntity)
        {
            super(tileEntity, true);
        }

        @Override
        public void setMaxProgress(int maxProgress)
        {
            super.setMaxProgress((int) (Math.floor(maxProgress * Math.pow(0.8, getCoilTier()))));
        }

        @Override
        public int getParallelLimit()
        {
            return 8 * GTUtility.getTierByVoltage(getMaxVoltage());
        }

    }

}
