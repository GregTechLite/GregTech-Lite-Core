package gregtechlite.gtlitecore.integration.jei.category

import gregtech.api.gui.GuiTextures
import gregtech.integration.jei.basic.BasicRecipeCategory
import gregtechlite.gtlitecore.api.MOD_ID
import gregtechlite.gtlitecore.api.gui.GTLiteGuiTextures
import gregtechlite.gtlitecore.integration.jei.info.SpacePumpRecipeWrapper
import mezz.jei.api.IGuiHelper
import mezz.jei.api.gui.IRecipeLayout
import mezz.jei.api.ingredients.IIngredients
import mezz.jei.api.recipe.IRecipeWrapper
import net.minecraft.client.Minecraft

class SpacePumpRecipeCategory(guiHelper: IGuiHelper) : BasicRecipeCategory<SpacePumpRecipeWrapper, SpacePumpRecipeWrapper>(
    "space_pump_module", "gtlitecore.jei.space_pump_module.name",
    guiHelper.createBlankDrawable(176, 82), guiHelper)
{
    companion object
    {
        private const val SLOT_CENTER = 79

        @JvmField
        val UID = String.format("%s.space_pump_module", MOD_ID)
    }

    private val slot = guiHelper.drawableBuilder(GuiTextures.SLOT.imageLocation, 0, 0, 18, 18)
        .setTextureSize(18, 18)
        .build()

    private val logo = guiHelper.drawableBuilder(GTLiteGuiTextures.SPACE_ELEVATOR_LOGO.imageLocation, 0, 0, 18, 18)
        .setTextureSize(18, 18)
        .build()

    override fun setRecipe(recipeLayout: IRecipeLayout, recipeWrapper: SpacePumpRecipeWrapper, ingredients: IIngredients)
    {
        val fluidStackGroup = recipeLayout.fluidStacks
        fluidStackGroup.init(0, true, SLOT_CENTER, 19, 16, 16, 1, false, null)
        fluidStackGroup.set(ingredients)
    }

    override fun drawExtras(mc: Minecraft)
    {
        slot.draw(mc, SLOT_CENTER - 1, 18)
        logo.draw(mc, 147, 52)
    }

    override fun getTooltipStrings(mouseX: Int, mouseY: Int): List<String> = emptyList()

    override fun getRecipeWrapper(recipeWrapper: SpacePumpRecipeWrapper): IRecipeWrapper = recipeWrapper

    override fun getModName() = MOD_ID
}
