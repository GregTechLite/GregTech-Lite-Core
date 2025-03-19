package magicbook.gtlitecore.common.metatileentity.multiblock.advanced;

import gregtech.api.GTValues;
import gregtech.api.capability.IDistillationTower;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.capability.impl.DistillationTowerLogicHandler;
import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiMapMultiblockController;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.PatternMatchContext;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.RecipeMaps;

import gregtech.api.util.GTTransferUtils;
import gregtech.api.util.GTUtility;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.client.utils.TooltipHelper;
import gregtech.common.blocks.BlockBoilerCasing;
import gregtech.common.blocks.MetaBlocks;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Function;

import static gregtech.api.util.RelativeDirection.*;
import static magicbook.gtlitecore.api.utils.GTLiteUtility.getOrDefault;
import static magicbook.gtlitecore.api.utils.StructureUtility.pumpCasings;

public class MetaTileEntityLargeDistillery extends MultiMapMultiblockController implements IDistillationTower
{

    protected DistillationTowerLogicHandler workableHandler;

    @Getter
    private int casingTier;

    // =================================================================================================================
    public MetaTileEntityLargeDistillery(ResourceLocation metaTileEntityId)
    {
        super(metaTileEntityId, new RecipeMap[] {
                RecipeMaps.DISTILLERY_RECIPES,
                RecipeMaps.DISTILLATION_RECIPES
        });
        this.recipeMapWorkable = new LargeDistilleryRecipeLogic(this);
        this.workableHandler = new DistillationTowerLogicHandler(this);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity)
    {
        return new MetaTileEntityLargeDistillery(metaTileEntityId);
    }

    // =================================================================================================================
    @Override
    protected void formStructure(PatternMatchContext context)
    {
        super.formStructure(context);
        if (this.structurePattern == null)
            return;
        if (usesAdvancedHatchLogic())
        {
            this.workableHandler.determineLayerCount(this.structurePattern);
            this.workableHandler.determineOrderedFluidOutputs();
        }
        Object type = context.get("PumpCasingTieredStats");
        this.casingTier = getOrDefault(
                () -> type instanceof WrappedIntTier,
                () -> ((WrappedIntTier) type).getIntTier(), 0);
    }

    @Override
    public void invalidateStructure()
    {
        super.invalidateStructure();
        if (this.workableHandler != null)
            this.workableHandler.invalidate();
        this.casingTier = 0;
    }

    @NotNull
    @Override
    protected BlockPattern createStructurePattern()
    {
        return FactoryBlockPattern.start(RIGHT, FRONT, UP)
                .aisle("DSD", "DQD", "DDD")
                .aisle("CCC", "CPC", "CCC").setRepeatable(1, 11)
                .aisle("CCC", "CCC", "CCC")
                .where('S', selfPredicate())
                .where('D', states(getCasingState())
                        .or(abilities(MultiblockAbility.EXPORT_ITEMS)
                                .setMaxGlobalLimited(1))
                        .or(abilities(MultiblockAbility.INPUT_ENERGY)
                                .setMinGlobalLimited(1)
                                .setMaxGlobalLimited(4))
                        .or(abilities(MultiblockAbility.IMPORT_FLUIDS)
                                .setExactLimit(1)))
                .where('C', states(getCasingState())
                        .or(abilities(MultiblockAbility.EXPORT_FLUIDS)
                                .setMaxLayerLimited(1, 1))
                        .or(autoAbilities(true, false)))
                .where('P', states(getPipeCasingState()))
                .where('Q', pumpCasings())
                .build();
    }

    private static IBlockState getCasingState()
    {
        return GTLiteMetaBlocks.METAL_CASING_01.getState(BlockMetalCasing01.MetalCasingType.SILICON_CARBIDE);
    }

    private static IBlockState getPipeCasingState()
    {
        return MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.TUNGSTENSTEEL_PIPE);
    }

    /**
     * Used if MultiblockPart Abilities need to be sorted a certain way, like Distillation
     * Tower and Assembly Line. <br>
     * There will be <i>consequences</i> if this is changed. Make sure to set the logic
     * handler (workable handler) to one with a property overriden.
     *
     * @see DistillationTowerLogicHandler#determineOrderedFluidOutputs()
     */
    @Override
    protected Function<BlockPos, Integer> multiblockPartSorter()
    {
        return UP.getSorter(getFrontFacing(), getUpwardsFacing(), isFlipped());
    }

    /**
     * Whether this multiblock can be rotated or face upwards. <br>
     * There will be <i>consequences</i> if this is changed. Make sure to set the logic
     * handler (workable handler) to one with a property overriden.
     *
     * @see DistillationTowerLogicHandler#determineOrderedFluidOutputs()
     */
    @Override
    public boolean allowsExtendedFacing()
    {
        return false;
    }

    @Override
    public boolean allowSameFluidFillForOutputs()
    {
        return !usesAdvancedHatchLogic();
    }

    @Override
    public int getFluidOutputLimit()
    {
        if (this.workableHandler != null && usesAdvancedHatchLogic())
            return this.workableHandler.getLayerCount();
        else
            return super.getFluidOutputLimit();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart)
    {
        return GTLiteTextures.SILICON_CARBIDE_CASING;
    }

    @SideOnly(Side.CLIENT)
    @NotNull
    @Override
    protected ICubeRenderer getFrontOverlay()
    {
        return Textures.DISTILLATION_TOWER_OVERLAY;
    }

    protected boolean usesAdvancedHatchLogic()
    {
        return this.getCurrentRecipeMap() == RecipeMaps.DISTILLATION_RECIPES;
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
                               boolean advanced)
    {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("gtlitecore.machine.large_distillery.tooltip.1"));
        tooltip.add(I18n.format("gtlitecore.machine.large_distillery.tooltip.2") + TooltipHelper.RAINBOW_SLOW + I18n.format("gregtech.machine.perfect_oc"));
        tooltip.add(I18n.format("gtlitecore.machine.large_distillery.tooltip.3"));
        tooltip.add(I18n.format("gtlitecore.machine.large_distillery.tooltip.4"));
        tooltip.add(I18n.format("gtlitecore.machine.large_distillery.tooltip.5"));
    }

    // =================================================================================================================
    @Override
    public boolean canBeDistinct()
    {
        return false;
    }

    protected class LargeDistilleryRecipeLogic extends MultiblockRecipeLogic
    {

        public LargeDistilleryRecipeLogic(RecipeMapMultiblockController tileEntity)
        {
            super(tileEntity);
        }

        @Override
        protected void outputRecipeOutputs()
        {
            if (usesAdvancedHatchLogic())
            {
                GTTransferUtils.addItemsToItemHandler(getOutputInventory(), false, itemOutputs);
                workableHandler.applyFluidToOutputs(fluidOutputs, true);
            }
            else
                super.outputRecipeOutputs();
        }

        @Override
        protected boolean checkOutputSpaceFluids(@NotNull Recipe recipe,
                                                 @NotNull IMultipleTankHandler exportFluids)
        {
            if (usesAdvancedHatchLogic())
            {
                // We have already trimmed fluid outputs at this time.
                if (!metaTileEntity.canVoidRecipeFluidOutputs() &&
                        !workableHandler.applyFluidToOutputs(recipe.getAllFluidOutputs(), false)) {
                    this.isOutputsFull = true;
                    return false;
                }
                return true;
            }
            return super.checkOutputSpaceFluids(recipe, exportFluids);
        }

        @Override
        protected IMultipleTankHandler getOutputTank()
        {
            if (usesAdvancedHatchLogic())
            {
                return workableHandler.getFluidTanks();
            }
            return super.getOutputTank();
        }

        @Override
        protected double getOverclockingDurationFactor()
        {
            if ((getMaxVoltage() >= GTValues.V[GTValues.UV] && usesAdvancedHatchLogic())
                    || !usesAdvancedHatchLogic())
            {
                return 0.25;
            }
            else
            {
                return 0.5;
            }
        }

        @Override
        public void setMaxProgress(int maxProgress)
        {
            super.setMaxProgress((int) (Math.floor(maxProgress * Math.pow(0.5, GTUtility.getTierByVoltage(this.getMaxVoltage())))));
        }

        @Override
        public int getParallelLimit() {
            return 16 * getCasingTier();
        }

    }

}
