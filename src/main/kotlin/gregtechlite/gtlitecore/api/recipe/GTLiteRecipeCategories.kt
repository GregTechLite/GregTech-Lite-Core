package gregtechlite.gtlitecore.api.recipe

import gregtech.api.recipes.RecipeMap
import gregtech.api.recipes.category.GTRecipeCategory
import gregtechlite.gtlitecore.api.MOD_ID
import gregtechlite.gtlitecore.api.gui.GTLiteGuiTextures

object GTLiteRecipeCategories
{
    @JvmField
    val BLACKHOLE_SHAPING = category("blackhole_shaping", GTLiteRecipeMaps.BLACKHOLE_FORMING_RECIPES)
        .jeiIcon(GTLiteGuiTextures.BLACKHOLE_SHAPING_ICON)

    @JvmField
    val BLACKHOLE_STAMPING = category("blackhole_stamping", GTLiteRecipeMaps.BLACKHOLE_FORMING_RECIPES)
        .jeiIcon(GTLiteGuiTextures.BLACKHOLE_STAMPING_ICON)
}

private fun category(name: String, recipeMap: RecipeMap<*>) : GTRecipeCategory
    = GTRecipeCategory.create(MOD_ID, name, "${MOD_ID}.recipe.category.$name", recipeMap)