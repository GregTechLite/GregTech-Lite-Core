package gregtechlite.gtlitecore.loader.recipe.oreprocessing

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.CHEMICAL_BATH_RECIPES
import gregtech.api.unification.material.Materials.HydrofluoricAcid
import gregtech.api.unification.material.Materials.Pyrochlore
import gregtech.api.unification.material.Materials.Tantalite
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.ore.OrePrefix.dust
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CalciumDifluoride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ManganeseDifluoride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.NiobiumPentoxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TantalumPentoxide

/**
 * Produces Niobium and Tantalum from Tantalite and Pyrochlore.
 * - Main Products: Niobium, Tantalum.
 * - Side Products: None.
 *
 * A raw product estimations for these Ore Processing:
 * - 11 Pyrochlore -> 2 Niobium + 2/7 Tantalum</li>
 * - 9 Tantalite -> 2 Tantalum + 2/7 Niobium</li>
 *
 * This Ore Processing merged from <a href="https://github.com/GregTechCEu/gregicality-science">Gregicality Science</a>.
 */
internal object NiobiumTantalumProcessing
{

    // @formatter:off

    fun init()
    {
        // Changed original eletrolyzer recipes to chemical bath recipes.
        niobiumProcess()
        tantalumProcess()
    }

    private fun niobiumProcess()
    {
        // Ca2Nb2O7 + 4HF -> Nb2O5 + 2CaF2 + 2H2O
        CHEMICAL_BATH_RECIPES.recipeBuilder()
            .input(dust, Pyrochlore, 11)
            .fluidInputs(HydrofluoricAcid.getFluid(4000))
            .output(dust, NiobiumPentoxide, 7)
            .output(dust, TantalumPentoxide, 1)
            .output(dust, CalciumDifluoride, 6)
            .fluidOutputs(Water.getFluid(2000))
            .EUt(VA[HV])
            .duration(10 * SECOND)
            .buildAndRegister()
    }

    private fun tantalumProcess()
    {
        // MnTa2O6 + 2HF -> Ta2O5 + MnF2 + H2O
        CHEMICAL_BATH_RECIPES.recipeBuilder()
            .input(dust, Tantalite, 9)
            .fluidInputs(HydrofluoricAcid.getFluid(2000))
            .output(dust, TantalumPentoxide, 7)
            .output(dust, NiobiumPentoxide, 1)
            .output(dust, ManganeseDifluoride, 3)
            .fluidOutputs(Water.getFluid(1000))
            .EUt(VA[HV])
            .duration(10 * SECOND)
            .buildAndRegister()
    }

    // @formatter:on

}