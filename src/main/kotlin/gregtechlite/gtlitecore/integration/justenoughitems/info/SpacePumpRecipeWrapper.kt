package gregtechlite.gtlitecore.integration.justenoughitems.info

import gregtech.api.util.GTStringUtils.drawCenteredStringWithCutoff
import mezz.jei.api.ingredients.IIngredients
import mezz.jei.api.ingredients.VanillaTypes
import mezz.jei.api.recipe.IRecipeWrapper
import net.minecraft.client.Minecraft
import net.minecraft.client.resources.I18n
import net.minecraftforge.fluids.FluidStack

/**
 * @param planetId Planet Id in frontend.
 * @param fluidId  Fluid Id in frontend.
 * @param fluid    FluidStack in frontend.
 *
 * @see gregtechlite.gtlitecore.api.recipe.frontend.SpacePumpRecipeFrontend
 */
class SpacePumpRecipeWrapper(@JvmField val planetId: Int, @JvmField val fluidId: Int, @JvmField val fluid: FluidStack) : IRecipeWrapper
{

    private val textStartX = 5
    private val startPosY = 40
    private val fontHeight = Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT

    val fluids = ArrayList<MutableList<FluidStack>>()

    init
    {
        fluids.add(mutableListOf<FluidStack>(fluid))
    }

    override fun getIngredients(ingredients: IIngredients)
    {
        ingredients.setInputLists(VanillaTypes.FLUID, fluids)
        ingredients.setOutputLists(VanillaTypes.FLUID, fluids)
    }

     override fun drawInfo(
         minecraft: Minecraft,
         recipeWidth: Int,
         recipeHeight: Int,
         mouseX: Int,
         mouseY: Int
    ) {
        val font = minecraft.fontRenderer
         font.getWordWrappedHeight(fluid.localizedName, recipeWidth)

        drawCenteredStringWithCutoff(
            fluid.localizedName,
            font,
            recipeWidth
        )

        val planetIdName = I18n.format("gtlitecore.jei.space_pump_module.planet_id", planetId)
        font.drawString(planetIdName, textStartX, startPosY, 0x111111)

        val fluidIdName = I18n.format("gtlitecore.jei.space_pump_module.fluid_id", fluidId)
        font.drawString(fluidIdName, textStartX, startPosY + fontHeight + 1, 0x111111)

        val fluidAmountName = I18n.format("gtlitecore.jei.space_pump_module.fluid_amount", fluid.amount)
        font.drawString(fluidAmountName, textStartX, startPosY + fontHeight * 2 + 1, 0x111111)
    }

}
