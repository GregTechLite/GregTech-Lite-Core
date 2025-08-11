package gregtechlite.gtlitecore.api.capability.logic

import gregtech.api.capability.IEnergyContainer
import gregtech.api.capability.impl.RecipeLogicEnergy
import gregtech.api.recipes.Recipe
import gregtech.api.recipes.RecipeMap
import gregtechlite.gtlitecore.api.metatileentity.PseudoMultiMachineMetaTileEntity
import gregtechlite.gtlitecore.api.recipe.property.PseudoMultiProperty
import java.util.function.Supplier

class PseudoMultiRecipeLogic(private val pseudoMetaTileEntity: PseudoMultiMachineMetaTileEntity, recipeMap: RecipeMap<*>?, energyContainer: Supplier<IEnergyContainer?>?) : RecipeLogicEnergy(pseudoMetaTileEntity, recipeMap, energyContainer)
{

    override fun checkRecipe(recipe: Recipe): Boolean
    {
        if (pseudoMetaTileEntity.targetBlockState == null)
            return false // If world was remote or null then return.

        // If no property was given don't check if state matches.
        return !recipe.hasProperty(PseudoMultiProperty)
                || recipe.getProperty(PseudoMultiProperty, null)!!
                        .validBlockStates.contains(pseudoMetaTileEntity.targetBlockState) && super.checkRecipe(recipe)
    }

    override fun canProgressRecipe(): Boolean
    {
        return previousRecipe == null || !previousRecipe.hasProperty(PseudoMultiProperty)
                || previousRecipe.getProperty(PseudoMultiProperty, null)!!
                        .validBlockStates.contains(pseudoMetaTileEntity.targetBlockState) && super.canProgressRecipe()
    }

}