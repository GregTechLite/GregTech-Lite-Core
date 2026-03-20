package gregtechlite.gtlitecore.integration.justenoughitems.category

import gregtech.api.gui.GuiTextures
import gregtech.api.util.GTStringUtils.drawCenteredStringWithCutoff
import gregtech.integration.jei.basic.BasicRecipeCategory
import gregtechlite.gtlitecore.api.MOD_ID
import gregtechlite.gtlitecore.integration.justenoughitems.info.SpacePumpRecipeWrapper
import mezz.jei.api.IGuiHelper
import mezz.jei.api.gui.IRecipeLayout
import mezz.jei.api.ingredients.IIngredients
import mezz.jei.api.recipe.IRecipeWrapper
import net.minecraft.client.Minecraft
import net.minecraft.client.resources.I18n
import net.minecraftforge.fluids.FluidStack

class SpacePumpRecipeCategory(guiHelper: IGuiHelper) : BasicRecipeCategory<SpacePumpRecipeWrapper, SpacePumpRecipeWrapper>(
    "space_pump_module", "gtlitecore.jei.space_pump_module.name",
    guiHelper.createBlankDrawable(176, 166 - 36), guiHelper)
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

    override fun setRecipe(recipeLayout: IRecipeLayout, recipeWrapper: SpacePumpRecipeWrapper, ingredients: IIngredients)
    {
        val fluidStackGroup = recipeLayout.fluidStacks
        fluidStackGroup.init(0, true, SLOT_CENTER, 19,
            16, 16, 1, false, null)
        fluidStackGroup.set(ingredients)
    }

    override fun drawExtras(mc: Minecraft)
    {
        slot.draw(mc, SLOT_CENTER - 1, 18)
    }

    override fun getTooltipStrings(mouseX: Int, mouseY: Int): MutableList<String?> = mutableListOf()

    override fun getRecipeWrapper(recipeWrapper: SpacePumpRecipeWrapper): IRecipeWrapper = recipeWrapper

    override fun getModName() = MOD_ID

}
