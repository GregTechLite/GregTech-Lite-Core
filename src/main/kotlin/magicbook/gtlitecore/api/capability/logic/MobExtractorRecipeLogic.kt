package magicbook.gtlitecore.api.capability.logic

import gregtech.api.capability.IEnergyContainer
import gregtech.api.capability.impl.RecipeLogicEnergy
import gregtech.api.metatileentity.MetaTileEntity
import gregtech.api.recipes.Recipe
import gregtech.api.recipes.RecipeMap
import magicbook.gtlitecore.common.metatileentity.single.MetaTileEntityMobExtractor
import java.util.function.Supplier

@Suppress("MISSING_DEPENDENCY_CLASS")
class MobExtractorRecipeLogic(metaTileEntity: MetaTileEntity, recipeMap: RecipeMap<*>, energyContainer: Supplier<IEnergyContainer>) : RecipeLogicEnergy(metaTileEntity, recipeMap, energyContainer)
{

    override fun checkPreviousRecipe() = super.checkPreviousRecipe() && this.checkRecipe(this.previousRecipe)

    override fun checkRecipe(recipe: Recipe) = (metaTileEntity as MetaTileEntityMobExtractor).checkRecipe(recipe)

    // TODO damageable?
    // override fun setupAndConsumeRecipeInputs(recipe: Recipe, importInventory: IItemHandlerModifiable): Recipe?
    // {
    //     return super.setupAndConsumeRecipeInputs(recipe, importInventory)
    // }

}