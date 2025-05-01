package magicbook.gtlitecore.loader.recipe.chain.food

import gregtech.api.GTValues.LV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.ModHandler
import gregtech.api.recipes.RecipeMaps.CHEMICAL_BATH_RECIPES
import gregtech.api.unification.material.Materials.Clay
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.ore.OrePrefix.ingot
import gregtech.api.unification.stack.UnificationEntry
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.CERAMIC_BOWL
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.CLAY_BOWL
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.DIRTY_CERAMIC_BOWL
import net.minecraft.init.Items
import net.minecraft.item.ItemStack

@Suppress("MISSING_DEPENDENCY_CLASS")
class UtensilsChain
{

    companion object
    {

        fun init()
        {
            // Clay Bowl
            ModHandler.addShapedRecipe(true, "clay_bowl", CLAY_BOWL.stackForm,
                "k p", "C C", "CCC",
                'C', ItemStack(Items.CLAY_BALL))

            // Ceramic Bowl
            ModHandler.addSmeltingRecipe(CLAY_BOWL.stackForm, CERAMIC_BOWL.stackForm)

            // Dirty Ceramic Bowl -> Ceramic Bowl
            CHEMICAL_BATH_RECIPES.recipeBuilder()
                .input(DIRTY_CERAMIC_BOWL)
                .fluidInputs(Water.getFluid(100))
                .output(CERAMIC_BOWL)
                .EUt(VA[LV].toLong())
                .duration(1 * SECOND + 5 * TICK)
                .buildAndRegister()
        }

    }

}