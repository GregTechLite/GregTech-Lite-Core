package gregtechlite.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.CANNER_RECIPES
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.addRecipe
import gregtechlite.gtlitecore.api.extension.inputs
import gregtechlite.gtlitecore.api.extension.outputs
import net.minecraft.init.Blocks.END_PORTAL_FRAME
import net.minecraft.init.Blocks.END_STONE
import net.minecraft.init.Items.ENDER_EYE

internal object CannerRecipes
{

    // @formatter:off

    fun init()
    {
        // End Portal Frame.
        CANNER_RECIPES.addRecipe {
            inputs(END_STONE)
            inputs(ENDER_EYE)
            outputs(END_PORTAL_FRAME)
            EUt(VA[HV])
            duration(10 * SECOND)
        }
    }

    // @formatter:on

}