package magicbook.gtlitecore.integration.justenoughitems.info

import mezz.jei.api.ingredients.IIngredients
import mezz.jei.api.ingredients.VanillaTypes
import mezz.jei.api.recipe.IRecipeWrapper
import net.minecraftforge.fluids.FluidStack
import javax.annotation.ParametersAreNonnullByDefault

/**
 * @param planetId Planet Id in frontend.
 * @param fluidId  Fluid Id in frontend.
 * @param fluid    FluidStack in frontend.
 *
 * @see magicbook.gtlitecore.api.recipe.frontend.SpacePumpRecipeFrontend
 */
class SpacePumpRecipeWrapper(@JvmField val planetId: Int, @JvmField val fluidId: Int, @JvmField val fluid: FluidStack) : IRecipeWrapper
{

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

}
