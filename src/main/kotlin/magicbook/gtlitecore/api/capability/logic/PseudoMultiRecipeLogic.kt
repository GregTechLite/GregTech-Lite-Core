package magicbook.gtlitecore.api.capability.logic;

import gregtech.api.capability.IEnergyContainer;
import gregtech.api.capability.impl.RecipeLogicEnergy;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMap;
import magicbook.gtlitecore.api.metatileentity.PseudoMultiMachineMetaTileEntity;
import magicbook.gtlitecore.api.recipe.property.PseudoMultiProperty;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class PseudoMultiRecipeLogic extends RecipeLogicEnergy
{

    private final PseudoMultiMachineMetaTileEntity pseudoMetaTileEntity;

    public PseudoMultiRecipeLogic(PseudoMultiMachineMetaTileEntity metaTileEntity,
                                  RecipeMap<?> recipeMap,
                                  Supplier<IEnergyContainer> energyContainer)
    {
        super(metaTileEntity, recipeMap, energyContainer);
        this.pseudoMetaTileEntity = metaTileEntity;
    }

    @Override
    public boolean checkRecipe(@NotNull Recipe recipe)
    {
        if (this.pseudoMetaTileEntity.getTargetBlockState() == null)
            return false; // If world was remote or null then return.
        // If no property was given don't check if state matches.
        return !recipe.hasProperty(PseudoMultiProperty.INSTANCE)
                || recipe.getProperty(PseudoMultiProperty.INSTANCE, null)
                        .getValidBlockStates().contains(this.pseudoMetaTileEntity.getTargetBlockState())
                && super.checkRecipe(recipe);
    }

    @Override
    protected boolean canProgressRecipe()
    {
        return this.previousRecipe == null
                || !this.previousRecipe.hasProperty(PseudoMultiProperty.INSTANCE)
                || this.previousRecipe.getProperty(PseudoMultiProperty.INSTANCE, null).getValidBlockStates()
                        .contains(this.pseudoMetaTileEntity.getTargetBlockState()) && super.canProgressRecipe();
    }

}