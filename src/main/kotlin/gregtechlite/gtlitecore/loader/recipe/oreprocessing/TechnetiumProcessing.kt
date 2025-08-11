package gregtechlite.gtlitecore.loader.recipe.oreprocessing

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.CHEMICAL_BATH_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.MIXER_RECIPES
import gregtech.api.unification.material.Materials.Ammonia
import gregtech.api.unification.material.Materials.NitricAcid
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.ore.OrePrefix.dust
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.AmmoniumNitrate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.AmmoniumPertechnetate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Pertechnetate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TechnetiumDioxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TechnetiumHeptaoxide
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt

internal object TechnetiumProcessing
{

    // @formatter:off

    fun init()
    {
        // Tc2O7 + H2O -> HTcO4
        MIXER_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(dust, TechnetiumHeptaoxide, 9)
            .fluidInputs(Water.getFluid(1000))
            .fluidOutputs(Pertechnetate.getFluid(1000))
            .EUt(VA[HV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // NH3 + HNO3 -> NH4NO3
        CHEMICAL_RECIPES.recipeBuilder()
            .fluidInputs(Ammonia.getFluid(1000))
            .fluidInputs(NitricAcid.getFluid(1000))
            .output(dust, AmmoniumNitrate, 2) // Special count like NH4Cl.
            .EUt(VA[LV])
            .duration(3 * SECOND)
            .buildAndRegister()

        // HTcO4 + NH4NO3 -> NH4TcO4 + HNO3
        CHEMICAL_BATH_RECIPES.recipeBuilder()
            .input(dust, AmmoniumNitrate, 2)
            .fluidInputs(Pertechnetate.getFluid(1000))
            .output(dust, AmmoniumPertechnetate, 10)
            .fluidOutputs(NitricAcid.getFluid(1000))
            .EUt(VA[EV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // 2NH4TcO4 -> 2TcO2 + 4H2O + 2N (drop)
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
            .input(dust, AmmoniumPertechnetate, 20)
            .output(dust, TechnetiumDioxide, 6)
            .fluidOutputs(Water.getFluid(4000))
            .EUt(VA[IV])
            .duration(15 * SECOND)
            .buildAndRegister()

        // TcO2 -> Tc + 2O by electrolysis decomposition.

    }

    // @formatter:on

}