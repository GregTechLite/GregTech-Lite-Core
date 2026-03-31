package gregtechlite.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.IV
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.CUTTER_RECIPES
import gregtech.api.unification.material.Materials.DistilledWater
import gregtech.api.unification.material.Materials.Lubricant
import gregtech.api.unification.material.Materials.Water
import gregtech.common.items.MetaItems.NEUTRONIUM_BOULE
import gregtech.common.items.MetaItems.NEUTRONIUM_WAFER
import gregtechlite.gtlitecore.api.MINUTE
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.addRecipe
import gregtechlite.gtlitecore.api.extension.cleanroom
import gregtechlite.gtlitecore.api.extension.removeRecipe
import gregtechlite.gtlitecore.api.extension.stack
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.HASSIUM_BOULE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.HASSIUM_WAFER

internal object CutterRecipes
{

    // @formatter:off
    fun init()
    {
        // Buff the neutronium-doped boule cutting.
        CUTTER_RECIPES.removeRecipe(
            arrayOf(NEUTRONIUM_BOULE.stack()),
            arrayOf(Water.getFluid(1000)))

        CUTTER_RECIPES.removeRecipe(
            arrayOf(NEUTRONIUM_BOULE.stack()),
            arrayOf(DistilledWater.getFluid(750)))

        CUTTER_RECIPES.removeRecipe(
            arrayOf(NEUTRONIUM_BOULE.stack()),
            arrayOf(Lubricant.getFluid(250)))

        CUTTER_RECIPES.addRecipe {
            input(NEUTRONIUM_BOULE)
            output(NEUTRONIUM_WAFER, 64)
            output(NEUTRONIUM_WAFER, 64)
            EUt(VA[IV])
            duration(2 * MINUTE)
            cleanroom()
        }

        // Hassium-doped Wafer
        CUTTER_RECIPES.addRecipe {
            input(HASSIUM_BOULE)
            output(HASSIUM_WAFER, 64)
            output(HASSIUM_WAFER, 64)
            output(HASSIUM_WAFER, 64)
            output(HASSIUM_WAFER, 64)
            EUt(VA[LuV])
            duration(2 * MINUTE + 40 * SECOND)
            cleanroom()
        }
    }

    // @formatter:on

}