package gregtechlite.gtlitecore.api.capability.logic

import gregtech.api.capability.IEnergyContainer
import gregtech.api.capability.impl.RecipeLogicEnergy
import gregtech.api.metatileentity.MetaTileEntity
import gregtech.api.recipes.Recipe
import gregtech.api.recipes.RecipeMap
import gregtechlite.gtlitecore.common.metatileentity.single.MachineMobExtractor
import java.util.function.Supplier

class MobExtractorRecipeLogic(metaTileEntity: MetaTileEntity, recipeMap: RecipeMap<*>, energyContainer: Supplier<IEnergyContainer>) : RecipeLogicEnergy(metaTileEntity, recipeMap, energyContainer)
{

    override fun checkPreviousRecipe() = super.checkPreviousRecipe() && this.checkRecipe(this.previousRecipe)

    override fun checkRecipe(recipe: Recipe) = (metaTileEntity as MachineMobExtractor).checkRecipe(recipe)

}