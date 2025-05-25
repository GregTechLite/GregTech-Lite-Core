package magicbook.gtlitecore.common.metatileentity.multiblock.module;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gregtech.api.capability.impl.EnergyContainerList;
import gregtech.api.capability.impl.FluidTankList;
import gregtech.api.capability.impl.ItemHandlerList;
import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.recipes.Recipe;
import gregtech.client.renderer.ICubeRenderer;
import magicbook.gtlitecore.api.metatileentity.multiblock.RecipeMapModuleMultiblockController;
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps;
import magicbook.gtlitecore.api.recipe.property.AccelerationOrbitTieredProperty;
import magicbook.gtlitecore.client.renderer.texture.GTLiteTextures;
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks;
import magicbook.gtlitecore.common.block.blocks.BlockSpaceElevatorCasing;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static gregtech.api.GTValues.VNF;

public class MetaTileEntitySpaceAssembler extends RecipeMapModuleMultiblockController
{

    public MetaTileEntitySpaceAssembler(ResourceLocation metaTileEntityId, int tier, int moduleTier, int minCasingTier)
    {
        super(metaTileEntityId, GTLiteRecipeMaps.SPACE_ASSEMBLER_RECIPES(), tier, moduleTier, minCasingTier);
        this.recipeMapWorkable = new SpaceAssemblerRecipeLogic(this);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity)
    {
        return new MetaTileEntitySpaceAssembler(metaTileEntityId, tier, moduleTier, minCasingTier);
    }

    @Override
    protected void initializeAbilities()
    {
        this.inputInventory = new ItemHandlerList(getAbilities(MultiblockAbility.IMPORT_ITEMS));
        this.inputFluidInventory = new FluidTankList(allowSameFluidFillForOutputs(), getAbilities(MultiblockAbility.IMPORT_FLUIDS));
        this.outputInventory = new ItemHandlerList(getAbilities(MultiblockAbility.EXPORT_ITEMS));
    }

    @NotNull
    @Override
    protected BlockPattern createStructurePattern()
    {
        return FactoryBlockPattern.start()
                .aisle("C","C","C","C","C")
                .aisle("C","C","C","S","C")
                .where('S', selfPredicate())
                .where('C', states(getCasingState())
                        .or(abilities(MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.EXPORT_ITEMS, MultiblockAbility.IMPORT_FLUIDS)
                                .setPreviewCount(0)))
                .build();
    }

    private static IBlockState getCasingState()
    {
        return GTLiteMetaBlocks.SPACE_ELEVATOR_CASING.getState(BlockSpaceElevatorCasing.CasingType.BASE_CASING);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart)
    {
        return GTLiteTextures.SPACE_ELEVATOR_BASE_CASING;
    }

    @SideOnly(Side.CLIENT)
    @NotNull
    @Override
    protected ICubeRenderer getFrontOverlay()
    {
        return GTLiteTextures.SPACE_ELEVATOR_OVERLAY;
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState,
                                     Matrix4 translation,
                                     IVertexOperation[] pipeline)
    {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        for (EnumFacing renderSide : EnumFacing.HORIZONTALS)
        {
            if (renderSide == getFrontFacing())
            {
                getFrontOverlay().renderOrientedState(renderState, translation, pipeline,
                        getFrontFacing(), isActive(), true);
            }
            else
            {
                GTLiteTextures.SPACE_ASSEMBLER_OVERLAY.renderSided(renderSide, renderState, translation, pipeline);
            }
        }
    }

    @Override
    public void addInformation(ItemStack stack,
                               @Nullable World world,
                               @NotNull List<String> tooltip,
                               boolean advanced)
    {
        super.addInformation(stack, world, tooltip, advanced);
        tooltip.add(I18n.format("gtlitecore.machine.space_assembler_module.tooltip"));
        if (moduleTier == 1)
            tooltip.add(I18n.format("gtlitecore.machine.space_assembler_module.mk1.tooltip.1"));
        else if (moduleTier == 2)
            tooltip.add(I18n.format("gtlitecore.machine.space_assembler_module.mk2.tooltip.1"));
        else
            tooltip.add(I18n.format("gtlitecore.machine.space_assembler_module.mk3.tooltip.1"));

        tooltip.add(I18n.format("gtlitecore.machine.space_assembler_module.max_parallel", VNF[tier], this.recipeMapWorkable.getParallelLimit()));
        tooltip.add(I18n.format("gtlitecore.machine.space_assembler_module.orbit_tier", getOrbitTier(moduleTier)));
    }

    protected String getOrbitTier(int moduleTier)
    {
        if (moduleTier == 1) return "MK1";
        else if (moduleTier == 2) return "MK2";
        else return "MK3";
    }

    protected class SpaceAssemblerRecipeLogic extends MultiblockRecipeLogic
    {

        public SpaceAssemblerRecipeLogic(RecipeMapModuleMultiblockController metaTileEntity)
        {
            super(metaTileEntity);
        }

        @Override
        public boolean checkRecipe(@NotNull Recipe recipe)
        {
            if (getModuleProvider() != null)
            {
                return super.checkRecipe(recipe)
                        && recipe.getProperty(AccelerationOrbitTieredProperty.getInstance(), 0) <= getModuleProvider().getCasingTier();
            }
            return false;
        }

        @Override
        public int getParallelLimit()
        {
            if (moduleTier == 1) return 4;
            else if (moduleTier == 2) return 16;
            else return 64;
        }

    }

}
