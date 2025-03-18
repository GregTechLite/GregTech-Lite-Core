package magicbook.gtlitecore.common.metatileentity.multiblock.advanced;

import gregtech.api.GTValues;
import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.PatternMatchContext;
import gregtech.api.recipes.RecipeMaps;
import gregtech.client.renderer.ICubeRenderer;
import lombok.Getter;
import magicbook.gtlitecore.api.block.impl.WrappedIntTier;
import magicbook.gtlitecore.api.capability.GTLiteDataCodes;
import magicbook.gtlitecore.client.renderer.texture.GTLiteTextures;
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks;
import magicbook.gtlitecore.common.block.blocks.BlockMetalCasing01;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static magicbook.gtlitecore.api.utils.GTLiteUtility.getOrDefault;
import static magicbook.gtlitecore.api.utils.StructureUtility.motorCasings;
import static magicbook.gtlitecore.api.utils.StructureUtility.pistonCasings;

public class MetaTileEntityLargeMacerator extends RecipeMapMultiblockController
{

    @Getter
    private int pistonCasingTier;
    @Getter
    private int motorCasingTier;

    // =================================================================================================================
    public MetaTileEntityLargeMacerator(ResourceLocation metaTileEntityId)
    {
        super(metaTileEntityId, RecipeMaps.MACERATOR_RECIPES);
        this.recipeMapWorkable = new LargeMaceratorRecipeLogic(this);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity)
    {
        return new MetaTileEntityLargeMacerator(metaTileEntityId);
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
    }

    @Override
    public void invalidateStructure()
    {
        super.invalidateStructure();
        this.pistonCasingTier = 0;
        this.motorCasingTier = 0;
    }

    @NotNull
    @Override
    protected BlockPattern createStructurePattern()
    {
        return FactoryBlockPattern.start()
                .aisle("CCC", "CCC", "CCC", "CCC", "CCC", "CCC")
                .aisle("CCC", "CMC", "C#C", "CPC", "C#C", "CCC")
                .aisle("CSC", "CCC", "CCC", "CCC", "CCC", "CCC")
                .where('S', selfPredicate())
                .where('C', states(getCasingState())
                        .setMinGlobalLimited(10)
                        .or(autoAbilities(true, true, true, true, false, false, false)))
                .where('P', pistonCasings())
                .where('M', motorCasings())
                .where('#', air())
                .build();
    }

    private static IBlockState getCasingState() {
        return GTLiteMetaBlocks.METAL_CASING_01.getState(BlockMetalCasing01.MetalCasingType.STELLITE);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart)
    {
        return GTLiteTextures.STELLITE_CASING;
    }

    @SideOnly(Side.CLIENT)
    @NotNull
    @Override
    protected ICubeRenderer getFrontOverlay()
    {
        return GTLiteTextures.LARGE_MACERATOR_OVERLAY;
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
                               @Nullable World player,
                               @NotNull List<String> tooltip,
                               boolean advanced)
    {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("gtlitecore.machine.large_macerator.tooltip.1"));
        tooltip.add(I18n.format("gtlitecore.machine.large_macerator.tooltip.2"));
        tooltip.add(I18n.format("gtlitecore.machine.large_macerator.tooltip.3"));
        tooltip.add(I18n.format("gtlitecore.machine.large_macerator.tooltip.4"));
    }

    // =================================================================================================================
    @Override
    public boolean canBeDistinct()
    {
        return true;
    }

    protected class LargeMaceratorRecipeLogic extends MultiblockRecipeLogic
    {

        public LargeMaceratorRecipeLogic(RecipeMapMultiblockController tileEntity)
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
            super.setMaxProgress((int) (Math.floor(maxProgress * Math.pow(0.5, getMotorCasingTier()))));
        }

        @Override
        public int getParallelLimit()
        {
            return 8 * getPistonCasingTier();
        }

    }

}