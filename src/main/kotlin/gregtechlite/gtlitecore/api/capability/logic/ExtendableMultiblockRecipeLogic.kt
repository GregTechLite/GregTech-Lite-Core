package gregtechlite.gtlitecore.api.capability.logic

import gregtech.api.capability.impl.MultiblockRecipeLogic
import gregtech.api.recipes.Recipe
import gregtechlite.gtlitecore.api.metatileentity.multiblock.extendable.AdditionalStructureManager
import gregtechlite.gtlitecore.api.metatileentity.multiblock.extendable.RecipeMapExtendableMultiblock
import gregtechlite.gtlitecore.api.recipe.property.RequestAdditionalProperty

open class ExtendableMultiblockRecipeLogic<T: RecipeMapExtendableMultiblock<T>>(controller: RecipeMapExtendableMultiblock<T>,
                                                                                protected val manager: AdditionalStructureManager<T>)
    : MultiblockRecipeLogic(controller)
{
    override fun checkRecipe(recipe: Recipe): Boolean
        = super.checkRecipe(recipe) && checkAdditionalRequirement(recipe)

    override fun canProgressRecipe(): Boolean
        = super.canProgressRecipe() && (previousRecipe?.let { checkAdditionalRequirement(it) } ?: true)

    private fun checkAdditionalRequirement(recipe: Recipe): Boolean
    {
        if (!recipe.hasProperty(RequestAdditionalProperty)) return true
        val requiredId = recipe.getProperty(RequestAdditionalProperty, null) ?: return false
        return manager.get(requiredId).isNotEmpty()
    }
}
