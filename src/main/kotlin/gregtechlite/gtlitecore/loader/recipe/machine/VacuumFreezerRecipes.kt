package gregtechlite.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.ULV
import gregtech.api.GTValues.VA
import gregtech.api.fluids.store.FluidStorageKeys
import gregtech.api.recipes.RecipeMaps.VACUUM_RECIPES
import gregtech.api.unification.material.Materials.Ice
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Water
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.addRecipe
import gregtechlite.gtlitecore.api.extension.removeRecipe
import gregtechlite.gtlitecore.api.s
import gregtechlite.gtlitecore.api.t

internal object VacuumFreezerRecipes
{
    // @formatter:off

    fun init()
    {
        // Ice
        VACUUM_RECIPES.removeRecipe(Water.getFluid(1000))
        VACUUM_RECIPES.addRecipe {
            fluidInputs(Water.getFluid(1000))
            fluidOutputs(Ice.getFluid(1000))
            EUt(VA[ULV])
            duration(1.t)
        }

        // Liquid Oxygen
        VACUUM_RECIPES.removeRecipe(Oxygen.getFluid(1000))
        VACUUM_RECIPES.addRecipe {
            fluidInputs(Oxygen.getFluid(1000))
            fluidOutputs(Oxygen.getFluid(FluidStorageKeys.LIQUID, 1000))
            EUt(VA[EV])
            duration(2.s + 10.t)
        }
    }

    // @formatter:on
}