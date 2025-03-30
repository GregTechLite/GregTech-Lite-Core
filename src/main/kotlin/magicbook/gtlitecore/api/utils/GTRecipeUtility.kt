package magicbook.gtlitecore.api.utils

import gregtech.api.recipes.GTRecipeHandler
import gregtech.api.recipes.RecipeMaps
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps
import net.minecraft.item.ItemStack
import net.minecraftforge.fluids.FluidStack

class GTRecipeUtility
{

    companion object
    {

        @JvmStatic
        fun removeChemicalRecipes(itemInputs: Array<ItemStack>, fluidInputs: Array<FluidStack>)
        {
            GTRecipeHandler.removeRecipesByInputs(RecipeMaps.CHEMICAL_RECIPES, itemInputs, fluidInputs)
            GTRecipeHandler.removeRecipesByInputs(RecipeMaps.LARGE_CHEMICAL_RECIPES, itemInputs, fluidInputs)
        }

        @JvmStatic
        fun removeChemicalRecipes(itemInputs: Array<ItemStack>)
        {
            GTRecipeHandler.removeRecipesByInputs(RecipeMaps.CHEMICAL_RECIPES, itemInputs, arrayOfNulls<FluidStack>(0))
            GTRecipeHandler.removeRecipesByInputs(RecipeMaps.LARGE_CHEMICAL_RECIPES, itemInputs, arrayOfNulls<FluidStack>(0))
        }

        @JvmStatic
        fun removeChemicalRecipes(fluidInputs: Array<FluidStack>)
        {
            GTRecipeHandler.removeRecipesByInputs(RecipeMaps.CHEMICAL_RECIPES, arrayOfNulls<ItemStack>(0), fluidInputs)
            GTRecipeHandler.removeRecipesByInputs(RecipeMaps.LARGE_CHEMICAL_RECIPES, arrayOfNulls<ItemStack>(0), fluidInputs)
        }

        @JvmStatic
        fun removeMixerRecipes(itemInputs: Array<ItemStack>, fluidInputs: Array<FluidStack>)
        {
            GTRecipeHandler.removeRecipesByInputs(RecipeMaps.MIXER_RECIPES, itemInputs, fluidInputs)
            GTRecipeHandler.removeRecipesByInputs(GTLiteRecipeMaps.LARGE_MIXER_RECIPES, itemInputs, fluidInputs)
        }

        @JvmStatic
        fun removeMixerRecipes(itemInputs: Array<ItemStack>)
        {
            GTRecipeHandler.removeRecipesByInputs(RecipeMaps.MIXER_RECIPES, itemInputs, arrayOfNulls<FluidStack>(0))
            GTRecipeHandler.removeRecipesByInputs(GTLiteRecipeMaps.LARGE_MIXER_RECIPES, itemInputs, arrayOfNulls<FluidStack>(0))
        }

        @JvmStatic
        fun removeMixerRecipes(fluidInputs: Array<FluidStack>)
        {
            GTRecipeHandler.removeRecipesByInputs(RecipeMaps.MIXER_RECIPES, arrayOfNulls<ItemStack>(0), fluidInputs)
            GTRecipeHandler.removeRecipesByInputs(GTLiteRecipeMaps.LARGE_MIXER_RECIPES, arrayOfNulls<ItemStack>(0), fluidInputs)
        }

    }

}