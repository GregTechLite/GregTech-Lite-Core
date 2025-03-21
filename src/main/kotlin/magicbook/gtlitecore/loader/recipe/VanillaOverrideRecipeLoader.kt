package magicbook.gtlitecore.loader.recipe

import gregtech.api.recipes.GTRecipeHandler
import gregtech.api.recipes.ModHandler
import gregtech.api.recipes.RecipeMaps.CHEMICAL_BATH_RECIPES
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Materials.Paper
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.common.ConfigHolder
import gregtech.common.items.ToolItems
import magicbook.gtlitecore.api.utils.Mods
import magicbook.gtlitecore.common.item.GTLiteToolItems
import net.minecraft.init.Items
import net.minecraft.item.ItemStack

@Suppress("MISSING_DEPENDENCY_CLASS")
class VanillaOverrideRecipeLoader
{

    companion object
    {

        fun init()
        {
            paperRecipes()
            sugarRecipes()
        }

        private fun paperRecipes()
        {
            // Allowed player used paper dust though the hard paper config (ConfigHolder.recipes.nerfPaperCrafting).
            if (!ConfigHolder.recipes.nerfPaperCrafting)
            {
                ModHandler.removeRecipeByName("${Mods.Minecraft.getId()}:paper")
                ModHandler.addShapelessRecipe("paper", ItemStack(Items.PAPER),
                    GTLiteToolItems.ROLLING_PIN, ItemStack(Items.REEDS, 1, 0))

                ModHandler.addShapelessRecipe("paper_dust", OreDictUnifier.get(dust, Paper),
                    ToolItems.MORTAR, ItemStack(Items.PAPER, 1))
            }

        }

        private fun sugarRecipes()
        {
            ModHandler.removeRecipeByName("${Mods.Minecraft.getId()}:sugar")
            ModHandler.addShapelessRecipe("sugar", ItemStack(Items.SUGAR, 1),
                ToolItems.MORTAR, ItemStack(Items.REEDS, 1))
        }

    }

}