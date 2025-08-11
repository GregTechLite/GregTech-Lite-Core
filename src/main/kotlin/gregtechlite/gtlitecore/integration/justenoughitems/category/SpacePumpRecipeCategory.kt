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
        private const val TEXT_START_X = 5
        private const val START_POS_Y = 40

        @JvmField
        val UID = String.format("%s.space_pump_module", MOD_ID)

    }

    private var planetId = 0
    private var fluidId = 0
    private var fluid: FluidStack? = null
    private var fluidAmount = 0

    private val slot = guiHelper.drawableBuilder(GuiTextures.SLOT.imageLocation, 0, 0, 18, 18)
        .setTextureSize(18, 18)
        .build()

    private var planetIdLength = 0
    private var fluidIdLength = 0
    private var fluidAmountLength = 0

    override fun setRecipe(recipeLayout: IRecipeLayout, recipeWrapper: SpacePumpRecipeWrapper, ingredients: IIngredients)
    {
        val fluidStackGroup = recipeLayout.fluidStacks
        fluidStackGroup.init(0, true, SLOT_CENTER, 19,
            16, 16, 1, false, null)
        fluidStackGroup.set(ingredients)

        this.planetId = recipeWrapper.planetId
        this.fluidId = recipeWrapper.fluidId
        this.fluid = recipeWrapper.fluid
        this.fluidAmount = recipeWrapper.fluid.amount
    }

    override fun drawExtras(mc: Minecraft)
    {
        // Fluid name.
        drawCenteredStringWithCutoff(fluid?.localizedName, mc.fontRenderer, 176)
        slot.draw(mc, SLOT_CENTER - 1, 18)

        // Planet Id name.
        val planetIdName = I18n.format("gtlitecore.jei.space_pump_module.planet_id", planetId)
        planetIdLength = mc.fontRenderer.getStringWidth(planetIdName)
        mc.fontRenderer.drawString(planetIdName, TEXT_START_X, START_POS_Y, 0x111111)

        // Fluid Id name.
        val fluidIdName = I18n.format("gtlitecore.jei.space_pump_module.fluid_id", fluidId)
        this.fluidIdLength = mc.fontRenderer.getStringWidth(fluidIdName)
        mc.fontRenderer.drawString(fluidIdName, TEXT_START_X, START_POS_Y + FONT_HEIGHT + 1, 0x111111)

        // Fluid amount name.
        val fluidAmountName = I18n.format("gtlitecore.jei.space_pump_module.fluid_amount", fluidAmount)
        fluidAmountLength = mc.fontRenderer.getStringWidth(fluidAmountName)
        mc.fontRenderer.drawString(fluidAmountName, TEXT_START_X, START_POS_Y + FONT_HEIGHT * 2 + 1, 0x111111)
    }

    override fun getTooltipStrings(mouseX: Int, mouseY: Int): MutableList<String?> = mutableListOf()

    override fun getRecipeWrapper(recipeWrapper: SpacePumpRecipeWrapper): IRecipeWrapper = recipeWrapper

    override fun getModName() = MOD_ID

}
