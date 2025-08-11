package gregtechlite.gtlitecore.api.capability.logic

import gregtech.api.capability.impl.RecipeLogicSteam
import gregtech.api.recipes.Recipe
import gregtech.api.recipes.RecipeMap
import gregtechlite.gtlitecore.api.metatileentity.PseudoMultiSteamMachineMetaTileEntity
import gregtechlite.gtlitecore.api.recipe.property.PseudoMultiProperty
import net.minecraft.util.EnumFacing
import net.minecraftforge.fluids.IFluidTank

class PseudoMultiSteamRecipeLogic(val pseudoMetaTileEntity: PseudoMultiSteamMachineMetaTileEntity, recipeMap: RecipeMap<*>?, isHighPressure: Boolean, steamFluidTank: IFluidTank?, conversionRate: Double) : RecipeLogicSteam(pseudoMetaTileEntity, recipeMap, isHighPressure, steamFluidTank, conversionRate)
{

    override fun checkRecipe(recipe: Recipe): Boolean
    {
        if (pseudoMetaTileEntity.targetBlockState == null)
            return false // If world was remote or null.

        // If no property was given don't check if state matches.
        return !recipe.hasProperty(PseudoMultiProperty)
                || recipe.getProperty(PseudoMultiProperty, null)!!
                    .validBlockStates.contains(pseudoMetaTileEntity.targetBlockState) && super.checkRecipe(recipe)
    }

    override fun canProgressRecipe(): Boolean
    {
        // Recipe stalled due to valid block removal will complete on world reload.
        return previousRecipe == null || !previousRecipe.hasProperty(PseudoMultiProperty)
                || previousRecipe.getProperty(PseudoMultiProperty, null)!!
                    .validBlockStates.contains(pseudoMetaTileEntity.targetBlockState) && super.canProgressRecipe()
    }

    override fun onFrontFacingSet(newFrontFacing: EnumFacing)
    {
        super.onFrontFacingSet(newFrontFacing)
        if (ventingSide == pseudoMetaTileEntity.getFrontFacing()
            || ventingSide == pseudoMetaTileEntity.getFrontFacing().opposite)
            ventingSide = newFrontFacing.rotateY()
    }

}
