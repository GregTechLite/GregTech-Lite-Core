package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.LV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.MIXER_RECIPES
import gregtech.api.unification.material.Materials.Beryllium
import gregtech.api.unification.material.Materials.Fluorine
import gregtech.api.unification.material.Materials.Lithium
import gregtech.api.unification.material.Materials.Potassium
import gregtech.api.unification.ore.OrePrefix.dust
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BerylliumDifluoride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LithiumBerylliumFluorides
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LithiumFluoride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LithiumSodiumPotassiumFluorides
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PotassiumFluoride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SodiumFluoride
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt

internal object ReactorModeratorChain
{

    // @formatter:off

    fun init()
    {
        // Li + F -> LiF
        CHEMICAL_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(dust, Lithium)
            .fluidInputs(Fluorine.getFluid(1000))
            .output(dust, LithiumFluoride, 2)
            .EUt(VA[LV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()

        // K + F -> KF
        CHEMICAL_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(dust, Potassium)
            .fluidInputs(Fluorine.getFluid(1000))
            .output(dust, PotassiumFluoride, 2)
            .EUt(VA[LV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()

        // LiF + NaF + KF -> F3LiNaK
        MIXER_RECIPES.recipeBuilder()
            .circuitMeta(3)
            .input(dust, LithiumFluoride, 2)
            .input(dust, SodiumFluoride, 2)
            .input(dust, PotassiumFluoride, 2)
            .output(dust, LithiumSodiumPotassiumFluorides, 6)
            .EUt(VA[MV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // Be + 2F -> BeF2
        CHEMICAL_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(dust, Beryllium)
            .fluidInputs(Fluorine.getFluid(2000))
            .output(dust, BerylliumDifluoride, 3)
            .EUt(VA[MV])
            .duration(1 * SECOND + 10 * TICK)
            .buildAndRegister()

        // LiF + BeF2 -> F3LiBe
        MIXER_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(dust, LithiumFluoride, 2)
            .input(dust, BerylliumDifluoride, 3)
            .output(dust, LithiumBerylliumFluorides, 5)
            .EUt(VA[MV])
            .duration(15 * SECOND)
            .buildAndRegister()

    }

    // @formatter:on

}