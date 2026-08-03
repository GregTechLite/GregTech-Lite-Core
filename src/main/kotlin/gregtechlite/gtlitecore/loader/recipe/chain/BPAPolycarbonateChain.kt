package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.unification.material.Materials.BisphenolA
import gregtech.api.unification.material.Materials.CarbonDioxide
import gregtech.api.unification.material.Materials.Methanol
import gregtech.api.unification.material.Materials.Phenol
import gregtech.api.unification.material.Materials.Water
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.addRecipe
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BPAPolycarbonate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.DimethylCarbonate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.DiphenylCarbonate

internal object BPAPolycarbonateChain
{
    // @formatter:off

    fun init()
    {
        // CO2 + 2CH4O -> C3H6O3 + H2O
        CHEMICAL_RECIPES.addRecipe {
            fluidInputs(CarbonDioxide.getFluid(1000))
            fluidInputs(Methanol.getFluid(2000))
            fluidOutputs(DimethylCarbonate.getFluid(1000))
            fluidOutputs(Water.getFluid(1000))
            EUt(VA[HV])
            duration(6 * SECOND)
        }

        // C3H6O3 + 2C6H6O -> C13H10O3 + 2CH4O (cycle)
        CHEMICAL_RECIPES.addRecipe {
            fluidInputs(DimethylCarbonate.getFluid(1000))
            fluidInputs(Phenol.getFluid(2000))
            fluidOutputs(DiphenylCarbonate.getFluid(1000))
            fluidOutputs(Methanol.getFluid(2000))
            EUt(VA[EV])
            duration(6 * SECOND)
        }

        // C13H10O3 + C15H16O2 -> BPA Polycarbonate + 2C6H6O (cycle)
        CHEMICAL_RECIPES.addRecipe {
            fluidInputs(DiphenylCarbonate.getFluid(1000))
            fluidInputs(BisphenolA.getFluid(1000))
            fluidOutputs(BPAPolycarbonate.getFluid(L))
            fluidOutputs(Phenol.getFluid(2000))
            EUt(VA[IV])
            duration(8 * SECOND)
        }
    }

    // @formatter:on
}