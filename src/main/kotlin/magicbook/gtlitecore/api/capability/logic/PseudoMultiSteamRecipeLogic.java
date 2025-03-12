package magicbook.gtlitecore.api.capability.logic;

import gregtech.api.capability.impl.RecipeLogicSteam;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMap;
import lombok.Getter;
import lombok.Setter;
import magicbook.gtlitecore.api.metatileentity.PseudoMultiSteamMachineMetaTileEntity;
import magicbook.gtlitecore.api.recipe.property.PseudoMultiProperty;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fluids.IFluidTank;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
public class PseudoMultiSteamRecipeLogic extends RecipeLogicSteam
{

    private final PseudoMultiSteamMachineMetaTileEntity pseudoMetaTileEntity;

    public PseudoMultiSteamRecipeLogic(PseudoMultiSteamMachineMetaTileEntity metaTileEntity, RecipeMap<?> recipeMap,
                                       boolean isHighPressure,
                                       IFluidTank steamFluidTank,
                                       double conversionRate)
    {
        super(metaTileEntity, recipeMap, isHighPressure, steamFluidTank, conversionRate);
        this.pseudoMetaTileEntity = metaTileEntity;
    }

    @Override
    public boolean checkRecipe(@NotNull Recipe recipe)
    {
        if (this.pseudoMetaTileEntity.getTargetBlockState() == null)
            return false; // If world was remote or null.
        // If no property was given don't check if state matches.
        return !recipe.hasProperty(PseudoMultiProperty.INSTANCE)
                || recipe.getProperty(PseudoMultiProperty.INSTANCE, null)
                        .getValidBlockStates().contains(this.pseudoMetaTileEntity.getTargetBlockState())
                                && super.checkRecipe(recipe);
    }

    @Override
    protected boolean canProgressRecipe()
    {
        // Recipe stalled due to valid block removal will complete on world reload.
        return this.previousRecipe == null
                || !this.previousRecipe.hasProperty(PseudoMultiProperty.INSTANCE)
                || this.previousRecipe.getProperty(PseudoMultiProperty.INSTANCE, null)
                        .getValidBlockStates().contains(this.pseudoMetaTileEntity.getTargetBlockState())
                            && super.canProgressRecipe();
    }

    @Override
    public void onFrontFacingSet(EnumFacing newFrontFacing)
    {
        super.onFrontFacingSet(newFrontFacing);
        if (this.getVentingSide() == this.pseudoMetaTileEntity.getFrontFacing()
                || this.getVentingSide() == this.pseudoMetaTileEntity.getFrontFacing().getOpposite())
            this.setVentingSide(newFrontFacing.rotateY());
    }

}
