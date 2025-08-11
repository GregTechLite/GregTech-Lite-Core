package gregtechlite.gtlitecore.loader.recipe.oreprocessing

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.unification.material.Materials.Aluminium
import gregtech.api.unification.material.Materials.BandedIron
import gregtech.api.unification.material.Materials.Carbon
import gregtech.api.unification.material.Materials.CarbonDioxide
import gregtech.api.unification.material.Materials.CarbonMonoxide
import gregtech.api.unification.material.Materials.Iron
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Strontium
import gregtech.api.unification.material.Materials.SulfurDioxide
import gregtech.api.unification.ore.OrePrefix.dust
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.BURNER_REACTOR_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.ROASTER_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Alumina
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Celestine
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Strontianite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.StrontiumOxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.StrontiumSulfide
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt

internal object StrontiumProcessing
{

    // @formatter:off

    fun init()
    {
        strontianiteProcess()
        celestineProcess()
    }

    private fun strontianiteProcess()
    {
        // SrCO3 -> SrO + CO2
        ROASTER_RECIPES.recipeBuilder()
            .input(dust, Strontianite, 5)
            .output(dust, StrontiumOxide, 2)
            .fluidOutputs(CarbonDioxide.getFluid(1000))
            .EUt(VA[HV])
            .duration(10 * TICK)
            .buildAndRegister()

        // 3SrO + 2Fe -> 3Sr + Fe2O3
        BURNER_REACTOR_RECIPES.recipeBuilder()
            .input(dust, StrontiumOxide, 6)
            .input(dust, Iron, 2)
            .output(dust, Strontium, 3)
            .output(dust, BandedIron, 5)
            .EUt(VA[MV])
            .duration(20 * SECOND)
            .buildAndRegister()

        // 3SrO + 2Al -> 3Sr + Al2O3
        BURNER_REACTOR_RECIPES.recipeBuilder()
            .input(dust, StrontiumOxide, 6)
            .input(dust, Aluminium, 2)
            .output(dust, Strontium, 3)
            .output(dust, Alumina, 5)
            .EUt(VA[MV])
            .duration(5 * SECOND)
            .buildAndRegister()
    }

    private fun celestineProcess()
    {
        // SrSO4 + 4C -> SrS + 4CO
        ROASTER_RECIPES.recipeBuilder()
            .input(dust, Celestine, 6)
            .input(dust, Carbon, 4)
            .output(dust, StrontiumSulfide, 2)
            .fluidOutputs(CarbonMonoxide.getFluid(4000))
            .EUt(VA[HV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // SrS + 2O -> Sr + SO2
        ROASTER_RECIPES.recipeBuilder()
            .input(dust, StrontiumSulfide, 2)
            .fluidInputs(Oxygen.getFluid(2000))
            .output(dust, Strontium)
            .fluidOutputs(SulfurDioxide.getFluid(1000))
            .EUt(VA[MV])
            .duration(5 * SECOND)
            .buildAndRegister()

    }

    // @formatter:on

}