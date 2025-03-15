package magicbook.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.CANNER_RECIPES
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraft.item.ItemStack

@Suppress("MISSING_DEPENDENCY_CLASS")
class CannerRecipes
{

    companion object
    {

        fun init()
        {
            // End Portal Frame.
            CANNER_RECIPES.recipeBuilder()
                .inputs(ItemStack(Blocks.END_STONE))
                .inputs(ItemStack(Items.ENDER_EYE))
                .outputs(ItemStack(Blocks.END_PORTAL_FRAME))
                .EUt(VA[HV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()
        }

    }

}