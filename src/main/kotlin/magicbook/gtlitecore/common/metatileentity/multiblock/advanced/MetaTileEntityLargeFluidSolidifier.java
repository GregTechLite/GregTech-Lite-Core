package magicbook.gtlitecore.common.metatileentity.multiblock.advanced;

import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiMapMultiblockController;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.PatternMatchContext;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.util.GTUtility;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.client.utils.TooltipHelper;
import gregtech.common.blocks.BlockBoilerCasing;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;
import lombok.Getter;
import magicbook.gtlitecore.api.block.impl.WrappedIntTier;
import magicbook.gtlitecore.api.capability.GTLiteDataCodes;
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps;
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
import static magicbook.gtlitecore.api.utils.StructureUtility.pumpCasings;

public class MetaTileEntityLargeFluidSolidifier extends MultiMapMultiblockController
{

    @Getter
    private int casingTier;

    // =================================================================================================================
    public MetaTileEntityLargeFluidSolidifier(ResourceLocation metaTileEntityId)
    {
        super(metaTileEntityId, new RecipeMap[] {
                RecipeMaps.FLUID_SOLIDFICATION_RECIPES,
                GTLiteRecipeMaps.TOOL_CASTER_RECIPES()
        });
        this.recipeMapWorkable = new LargeFluidSolidifierRecipeLogic(this);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity)
    {
        return new MetaTileEntityLargeFluidSolidifier(metaTileEntityId);
    }

    // =================================================================================================================
    @Override
    protected void formStructure(PatternMatchContext context)
    {
        super.formStructure(context);
        Object type = context.get("PumpCasingTieredStats");
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
                .aisle("CCC", "CCC", "CCC")
                .aisle("CCC", "CPC", "CCC")
                .aisle("CCC", "CPC", "CCC")
                .aisle("QQQ", "QSQ", "QQQ")
                .where('S', selfPredicate())
                .where('C', states(getCasingState())
                        .setMinGlobalLimited(2)
                        .or(autoAbilities(true, true, true, true, true, false, false)))
                .where('Q', states(getPipeCasingState()))
                .where('P', pumpCasings())
                .build();
    }

    private static IBlockState getCasingState()
    {
        return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STEEL_SOLID);
    }

    private static IBlockState getPipeCasingState()
    {
        return MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.STEEL_PIPE);
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
        return Textures.FLUID_SOLIDIFIER_OVERLAY;
    }

    // =================================================================================================================
    @Override
    public void update() {
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
        tooltip.add(I18n.format("gtlitecore.machine.large_fluid_solidifier.tooltip.1"));
        tooltip.add(TooltipHelper.RAINBOW_SLOW + I18n.format("gregtech.machine.perfect_oc"));
        tooltip.add(I18n.format("gtlitecore.machine.large_fluid_solidifier.tooltip.2"));
        tooltip.add(I18n.format("gtlitecore.machine.large_fluid_solidifier.tooltip.3"));
    }

    // =================================================================================================================
    @Override
    public boolean canBeDistinct()
    {
        return false;
    }

    protected class LargeFluidSolidifierRecipeLogic extends MultiblockRecipeLogic
    {

        public LargeFluidSolidifierRecipeLogic(RecipeMapMultiblockController tileEntity)
        {
            super(tileEntity, true);
        }

        @Override
        public void setMaxProgress(int maxProgress)
        {
            super.setMaxProgress((int) (Math.floor(maxProgress * Math.pow(0.8, getCasingTier()))));
        }

        @Override
        public int getParallelLimit()
        {
            return 4 * GTUtility.getTierByVoltage(this.getMaxVoltage());
        }

    }

}
