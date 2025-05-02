package magicbook.gtlitecore.api.metatileentity;

import codechicken.lib.raytracer.CuboidRayTraceResult;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.recipes.RecipeMap;
import gregtech.client.renderer.ICubeRenderer;
import lombok.Getter;
import lombok.Setter;
import magicbook.gtlitecore.api.capability.logic.PseudoMultiSteamRecipeLogic;
import magicbook.gtlitecore.api.gui.indicator.SteamProgressBarIndicator;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.Nullable;

@Getter
@Setter
public class PseudoMultiSteamMachineMetaTileEntity extends SimpleSteamMachineMetaTileEntity
{

    private IBlockState targetBlockState;

    public PseudoMultiSteamMachineMetaTileEntity(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap,
                                                 SteamProgressBarIndicator progressBarIndicator,
                                                 ICubeRenderer renderer, boolean isBrickedCasing,
                                                 boolean isHighPressure)
    {
        super(metaTileEntityId, recipeMap, progressBarIndicator, renderer, isBrickedCasing, isHighPressure);
        this.workableHandler = new PseudoMultiSteamRecipeLogic(this, recipeMap, isHighPressure, this.steamFluidTank, 1.0);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity)
    {
        return new PseudoMultiSteamMachineMetaTileEntity(this.metaTileEntityId,
                this.workableHandler.getRecipeMap(), this.progressBarIndicator,
                this.renderer, this.isBrickedCasing, this.isHighPressure);
    }

    @Override
    public void onLoad()
    {
        super.onLoad();
        this.checkAdjacentBlocks();
    }

    @Override
    public void onPlacement(@Nullable EntityLivingBase placer)
    {
        super.onPlacement(placer);
        this.checkAdjacentBlocks();
    }

    @Override
    public void onNeighborChanged()
    {
        super.onNeighborChanged();
        this.checkAdjacentBlocks();
    }

    @Override
    public boolean onWrenchClick(EntityPlayer playerIn,
                                 EnumHand hand, EnumFacing facing,
                                 CuboidRayTraceResult hitResult)
    {
        boolean wrenchClickSucceeded = super.onWrenchClick(playerIn, hand, facing, hitResult);
        if (wrenchClickSucceeded)
            this.checkAdjacentBlocks();
        return wrenchClickSucceeded;
    }

    @Override
    public boolean getIsWeatherOrTerrainResistant()
    {
        return true;
    }

    public void checkAdjacentBlocks()
    {
        if (this.getWorld() == null || this.getWorld().isRemote)
        {
            this.targetBlockState = null;
            return;
        }
        // The traditional "back" side of this type of MetaTileEntity is actually treated
        // as its front for recipe purposes, making wrench movement feel as though you are
        // holding onto or manipulating the back side to point the MetaTileEntity.
        this.targetBlockState = this.getWorld().getBlockState(
                this.getPos().offset(this.getFrontFacing().getOpposite()));
    }

}
