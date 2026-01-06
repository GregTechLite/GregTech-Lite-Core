package gregtechlite.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.IV
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.VA
import gregtech.api.metatileentity.multiblock.CleanroomType
import gregtech.api.recipes.GTRecipeHandler
import gregtech.api.recipes.RecipeMaps.CUTTER_RECIPES
import gregtech.api.unification.material.Materials.DistilledWater
import gregtech.api.unification.material.Materials.Lubricant
import gregtech.api.unification.material.Materials.Water
import gregtech.common.items.MetaItems.NEUTRONIUM_BOULE
import gregtech.common.items.MetaItems.NEUTRONIUM_WAFER
import gregtechlite.gtlitecore.api.MINUTE
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.HASSIUM_BOULE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.HASSIUM_WAFER

internal object CutterRecipes
{

    // @formatter:off
    fun init()
    {
        // Buff the neutronium-doped boule cutting.
        GTRecipeHandler.removeRecipesByInputs(CUTTER_RECIPES,
            arrayOf(NEUTRONIUM_BOULE.stackForm),
            arrayOf(Water.getFluid(1000)))

        GTRecipeHandler.removeRecipesByInputs(CUTTER_RECIPES,
            arrayOf(NEUTRONIUM_BOULE.stackForm),
            arrayOf(DistilledWater.getFluid(750)))

        GTRecipeHandler.removeRecipesByInputs(CUTTER_RECIPES,
            arrayOf(NEUTRONIUM_BOULE.stackForm),
            arrayOf(Lubricant.getFluid(250)))

        CUTTER_RECIPES.recipeBuilder()
            .input(NEUTRONIUM_BOULE)
            .output(NEUTRONIUM_WAFER, 64)
            .output(NEUTRONIUM_WAFER, 64)
            .EUt(VA[IV])
            .duration(2 * MINUTE)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Hassium-doped Wafer
        CUTTER_RECIPES.recipeBuilder()
            .input(HASSIUM_BOULE)
            .output(HASSIUM_WAFER, 64)
            .output(HASSIUM_WAFER, 64)
            .output(HASSIUM_WAFER, 64)
            .output(HASSIUM_WAFER, 64)
            .EUt(VA[LuV])
            .duration(2 * MINUTE + 40 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()
    }

    // @formatter:on

}