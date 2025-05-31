package magicbook.gtlitecore.api.capability.logic

import gregtech.api.capability.IEnergyContainer
import gregtech.api.capability.impl.RecipeLogicEnergy
import gregtech.api.recipes.Recipe
import gregtech.api.recipes.RecipeMap
import magicbook.gtlitecore.api.metatileentity.PseudoMultiMachineMetaTileEntity
import magicbook.gtlitecore.api.recipe.property.PseudoMultiProperty
import magicbook.gtlitecore.api.recipe.property.value.PseudoMultiPropertyValues
import java.util.function.Supplier

class PseudoMultiRecipeLogic(private val pseudoMetaTileEntity: PseudoMultiMachineMetaTileEntity, recipeMap: RecipeMap<*>?, energyContainer: Supplier<IEnergyContainer?>?) : RecipeLogicEnergy(pseudoMetaTileEntity, recipeMap, energyContainer)
{

    override fun checkRecipe(recipe: Recipe): Boolean
    {
        if (pseudoMetaTileEntity.targetBlockState == null)
            return false // If world was remote or null then return.

        // If no property was given don't check if state matches.
        return !recipe.hasProperty(PseudoMultiProperty.INSTANCE)
                || recipe.getProperty(PseudoMultiProperty.INSTANCE, null)!!
                        .getValidBlockStates().contains(pseudoMetaTileEntity.targetBlockState) && super.checkRecipe(recipe)
    }

    override fun canProgressRecipe(): Boolean
    {
        return previousRecipe == null || !previousRecipe.hasProperty(PseudoMultiProperty.INSTANCE)
                || previousRecipe.getProperty(PseudoMultiProperty.INSTANCE, null)!!
                    .getValidBlockStates().contains(pseudoMetaTileEntity.targetBlockState) && super.canProgressRecipe()
    }

}