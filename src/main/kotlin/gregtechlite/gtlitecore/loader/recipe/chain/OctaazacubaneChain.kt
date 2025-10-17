package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.IV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.ZPM
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.unification.material.Materials.Ammonia
import gregtech.api.unification.material.Materials.DinitrogenTetroxide
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.Iron3Chloride
import gregtech.api.unification.material.Materials.NitrogenDioxide
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Sodium
import gregtech.api.unification.material.Materials.SodiumHydroxide
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.ore.OrePrefix.dust
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CRYOGENIC_REACTOR_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CVD_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Octaazacubane
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SodiumAzanide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SodiumAzide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SodiumOxide

internal object OctaazacubaneChain
{

    // @formatter:off

    fun init()
    {
        // Na + NH3 -> NaNH2 + H
        CRYOGENIC_REACTOR_RECIPES.recipeBuilder()
            .input(dust, Sodium)
            .fluidInputs(Ammonia.getFluid(1000))
            .notConsumable(Iron3Chloride.getFluid(1))
            .output(dust, SodiumAzanide, 4)
            .fluidOutputs(Hydrogen.getFluid(1000))
            .EUt(VA[IV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // 2NaNH2 + NO2 -> NaN3 + NaOH + H2O + H (drop)
        CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, SodiumAzanide, 8)
            .fluidInputs(NitrogenDioxide.getFluid(1000))
            .output(dust, SodiumAzide, 4)
            .output(dust, SodiumHydroxide, 3)
            .fluidOutputs(Water.getFluid(1000))
            .EUt(VA[ZPM])
            .duration(5 * SECOND)
            .buildAndRegister()

        // 2NaN3 + N2O4 -> N8 + Na2O + 3O
        CVD_RECIPES.recipeBuilder()
            .input(dust, SodiumAzide, 8)
            .fluidInputs(DinitrogenTetroxide.getFluid(1000))
            .output(dust, Octaazacubane, 8)
            .output(dust, SodiumOxide, 3)
            .fluidOutputs(Oxygen.getFluid(3000))
            .EUt(VA[UHV])
            .duration(10 * SECOND)
            .temperature(1442)
            .buildAndRegister()

    }

    // @formatter:on

}