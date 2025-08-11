package gregtechlite.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.CANNER_RECIPES
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraft.item.ItemStack

internal object CannerRecipes
{

    // @formatter:off

    fun init()
    {
        // End Portal Frame.
        CANNER_RECIPES.recipeBuilder()
            .inputs(ItemStack(Blocks.END_STONE))
            .inputs(ItemStack(Items.ENDER_EYE))
            .outputs(ItemStack(Blocks.END_PORTAL_FRAME))
            .EUt(VA[HV])
            .duration(10 * SECOND)
            .buildAndRegister()
    }

    // @formatter:on

}