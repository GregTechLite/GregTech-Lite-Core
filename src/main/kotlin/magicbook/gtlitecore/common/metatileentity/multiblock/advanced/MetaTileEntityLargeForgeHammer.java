package magicbook.gtlitecore.common.metatileentity.multiblock.advanced;

import gregtech.api.GTValues;
import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiMapMultiblockController;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.MultiblockShapeInfo;
import gregtech.api.pattern.PatternMatchContext;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.RecipeMaps;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.ConfigHolder;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import lombok.Getter;
import magicbook.gtlitecore.api.GTLiteAPI;
import magicbook.gtlitecore.api.block.impl.WrappedIntTier;
import magicbook.gtlitecore.api.capability.GTLiteDataCodes;
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
import static magicbook.gtlitecore.api.utils.StructureUtility.pistonCasings;

public class MetaTileEntityLargeForgeHammer extends MultiMapMultiblockController
{

    @Getter
    private int casingTier;

    /* ------------------------------- MetaTileEntity constructors ------------------------------- */
    public MetaTileEntityLargeForgeHammer(ResourceLocation metaTileEntityId)
    {
        super(metaTileEntityId, new RecipeMap[] {
                RecipeMaps.FORGE_HAMMER_RECIPES,
                RecipeMaps.COMPRESSOR_RECIPES
        });
        this.recipeMapWorkable = new LargeForgeHammerRecipeLogic(this);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity)
    {
        return new MetaTileEntityLargeForgeHammer(metaTileEntityId);
    }

    /* ----------------------------- Create MetaTileEntity structure ----------------------------- */
    @Override
    protected void formStructure(PatternMatchContext context)
    {
        super.formStructure(context);
        Object type = context.get("PistonCasingTieredStats");
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
                .aisle("SCC", "C#C", "CPC", "CCC")
                .where('S', selfPredicate())
                .where('C', states(getCasingState())
                        .setMinGlobalLimited(2)
                        .or(autoAbilities(true, true, true, true, true, true, false)))
                .where('P', pistonCasings())
                .where('#', air())
                .build();
    }

    private static IBlockState getCasingState()
    {
        return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STEEL_SOLID);
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
        return Textures.FORGE_HAMMER_OVERLAY;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes()
    {
        List<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
        MultiblockShapeInfo.Builder builder = MultiblockShapeInfo.builder(LEFT, DOWN, FRONT)
                .aisle("SME", "I J", "KPL", "CCC")
                .where('S', GTLiteMetaTileEntities.LARGE_FORGE_HAMMER, EnumFacing.SOUTH)
                .where('C', getCasingState())
                .where('M', () -> ConfigHolder.machines.enableMaintenance ? MetaTileEntities.MAINTENANCE_HATCH : getCasingState(), EnumFacing.SOUTH)
                .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[0], EnumFacing.SOUTH)
                .where('I', MetaTileEntities.ITEM_IMPORT_BUS[0], EnumFacing.SOUTH)
                .where('J', MetaTileEntities.ITEM_EXPORT_BUS[0], EnumFacing.SOUTH)
                .where('K', MetaTileEntities.FLUID_IMPORT_HATCH[0], EnumFacing.SOUTH)
                .where('L', MetaTileEntities.FLUID_EXPORT_HATCH[0], EnumFacing.SOUTH);
        StreamEx.of(GTLiteAPI.MAP_PISTON_CASING.entrySet())
                .sortedByInt(entry -> ((WrappedIntTier) entry.getValue()).getIntTier())
                .forEach(entry -> shapeInfo.add(builder.where('P', entry.getKey()).build()));
        return shapeInfo;
    }

    /* -------------------------------- Sync MetaTileEntity datas -------------------------------- */
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

    /* ------------------------------- MetaTileEntity Descriptions ------------------------------- */
    @Override
    public void addInformation(ItemStack stack,
                               @Nullable World player,
                               @NotNull List<String> tooltip,
                               boolean advanced)
    {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("gtlitecore.machine.large_forge_hammer.tooltip.1"));
        tooltip.add(I18n.format("gtlitecore.machine.large_forge_hammer.tooltip.2"));
        tooltip.add(I18n.format("gtlitecore.machine.large_forge_hammer.tooltip.3"));
        tooltip.add(I18n.format("gtlitecore.machine.large_forge_hammer.tooltip.4"));
    }

    /* ---------------------------------- MetaTileEntity Logics ---------------------------------- */
    @Override
    public boolean canBeDistinct()
    {
        return true;
    }

    protected class LargeForgeHammerRecipeLogic extends MultiblockRecipeLogic
    {

        public LargeForgeHammerRecipeLogic(RecipeMapMultiblockController tileEntity) {
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
            super.setMaxProgress((int) (Math.floor(maxProgress * Math.pow(0.8, getCasingTier()))));
        }

        @Override
        public int getParallelLimit()
        {
            return 4 * getCasingTier();
        }

    }

}
