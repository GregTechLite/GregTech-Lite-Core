package magicbook.gtlitecore.api.metatileentity;

import codechicken.lib.raytracer.CuboidRayTraceResult;
import gregtech.api.capability.impl.AbstractRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.SimpleMachineMetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.recipes.RecipeMap;
import gregtech.client.renderer.ICubeRenderer;
import lombok.Getter;
import lombok.Setter;
import magicbook.gtlitecore.api.capability.logic.PseudoMultiRecipeLogic;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

@Getter
@Setter
public class PseudoMultiMachineMetaTileEntity extends SimpleMachineMetaTileEntity
{

    private IBlockState targetBlockState;


    public PseudoMultiMachineMetaTileEntity(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap,
                                            ICubeRenderer renderer, int tier,
                                            boolean hasFrontFacing,
                                            Function<Integer, Integer> tankScalingFunction)
    {
        super(metaTileEntityId, recipeMap, renderer, tier, hasFrontFacing, tankScalingFunction);
    }

    @NotNull
    @Override
    public MetaTileEntity createMetaTileEntity(@Nullable IGregTechTileEntity tileEntity)
    {
        return new PseudoMultiMachineMetaTileEntity(this.metaTileEntityId,
                this.workable.getRecipeMap(), this.renderer, this.getTier(),
                this.hasFrontFacing(), this.getTankScalingFunction());
    }

    @Override
    protected AbstractRecipeLogic createWorkable(RecipeMap<?> recipeMap)
    {
        return new PseudoMultiRecipeLogic(this, recipeMap,
                () -> this.energyContainer);
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
