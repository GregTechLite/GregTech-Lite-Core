package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.unification.material.Materials.Ammonia
import gregtech.api.unification.material.Materials.Water
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.addRecipe
import gregtechlite.gtlitecore.api.s
import gregtechlite.gtlitecore.api.t
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Formaldehyde
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Hydrazine
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HydrogenPeroxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Methylhydrazine

internal object HydrazineChain
{

    // @formatter:off

    fun init()
    {
        // 2NH3 + H2O2 -> N2H4 + 2H2O
        CHEMICAL_RECIPES.addRecipe {
            fluidInputs(Ammonia.getFluid(2000))
            fluidInputs(HydrogenPeroxide.getFluid(1000))
            fluidOutputs(Hydrazine.getFluid(1000))
            fluidOutputs(Water.getFluid(2000))
            EUt(VA[HV])
            duration(4.s)
        }

        // N2H4 + CH2O -> CH6N2 + O (drop)
        CHEMICAL_RECIPES.addRecipe {
            fluidInputs(Hydrazine.getFluid(1000))
            fluidInputs(Formaldehyde.getFluid(1000))
            fluidOutputs(Methylhydrazine.getFluid(1000))
            EUt(VA[HV])
            duration(2.s + 10.t)
        }
    }

    // @formatter:on

}