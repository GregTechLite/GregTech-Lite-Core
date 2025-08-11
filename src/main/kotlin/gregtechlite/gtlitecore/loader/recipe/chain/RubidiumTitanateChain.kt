package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.unification.material.Materials.Chlorine
import gregtech.api.unification.material.Materials.Rubidium
import gregtech.api.unification.material.Materials.Rutile
import gregtech.api.unification.material.Materials.Salt
import gregtech.api.unification.material.Materials.SodiumHydroxide
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.ore.OrePrefix.dust
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CRYOGENIC_REACTOR_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.RubidiumChloride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.RubidiumTitanate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SodiumTitanate
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt

internal object RubidiumTitanateChain
{

    // @formatter:off

    fun init()
    {
        // Rb + Cl -> RbCl
        CHEMICAL_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(dust, Rubidium)
            .fluidInputs(Chlorine.getFluid(1000))
            .output(dust, RubidiumChloride, 2)
            .EUt(VA[LV])
            .duration(2 * SECOND)
            .buildAndRegister()

        // 2NaOH + TiO2 -> Na2TiO3 + H2O
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
            .input(dust, Rutile, 3)
            .input(dust, SodiumHydroxide, 6)
            .output(dust, SodiumTitanate, 6)
            .fluidOutputs(Water.getFluid(1000))
            .EUt(VA[EV])
            .duration(16 * TICK)
            .buildAndRegister()

        // 2Na2TiO3 + 4RbCl -> Rb2TiO3 + 4NaCl
        CRYOGENIC_REACTOR_RECIPES.recipeBuilder()
            .input(dust, SodiumTitanate, 12)
            .input(dust, RubidiumChloride, 8)
            .output(dust, RubidiumTitanate, 6)
            .output(dust, Salt, 8)
            .EUt(VA[LuV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()

    }

    // @formatter:on

}