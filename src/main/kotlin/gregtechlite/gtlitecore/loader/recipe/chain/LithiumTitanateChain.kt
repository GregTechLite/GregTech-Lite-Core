package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.BLAST_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_BATH_RECIPES
import gregtech.api.unification.material.Materials.CarbonDioxide
import gregtech.api.unification.material.Materials.Chlorine
import gregtech.api.unification.material.Materials.DinitrogenTetroxide
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.NitricAcid
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.SodaAsh
import gregtech.api.unification.material.Materials.SodiumHydroxide
import gregtech.api.unification.material.Materials.Titanium
import gregtech.api.unification.material.Materials.TitaniumTetrachloride
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.ingotHot
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.BURNER_REACTOR_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LithiumCarbonate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LithiumOxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LithiumTitanate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TitaniumNitrate
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt

internal object LithiumTitanateChain
{

    // @formatter:off

    fun init()
    {
        // Li2O + CO2 -> Li2CO3
        CHEMICAL_BATH_RECIPES.recipeBuilder()
            .input(dust, LithiumOxide, 3)
            .fluidInputs(CarbonDioxide.getFluid(1000))
            .output(dust, LithiumCarbonate, 6)
            .EUt(VA[MV])
            .duration(7 * SECOND)
            .buildAndRegister()

        // TiCl4 + 2N2O4 + 2O -> Ti(NO3)4 + 4Cl
        BURNER_REACTOR_RECIPES.recipeBuilder()
            .fluidInputs(TitaniumTetrachloride.getFluid(1000))
            .fluidInputs(DinitrogenTetroxide.getFluid(2000))
            .fluidInputs(Oxygen.getFluid(2000))
            .output(dust, TitaniumNitrate, 17)
            .fluidOutputs(Chlorine.getFluid(4000))
            .EUt(VA[HV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // Ti + 4HNO3 -> Ti(NO3)4 + 4H (this recipe with Li2TiO3 reaction can
        // produce 4H each cycling, it is a higher buff recipe).
        CHEMICAL_BATH_RECIPES.recipeBuilder()
            .input(dust, Titanium)
            .fluidInputs(NitricAcid.getFluid(4000))
            .output(dust, TitaniumNitrate, 17)
            .fluidOutputs(Hydrogen.getFluid(4000))
            .EUt(VA[EV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()

        // Ti(NO3)4 + 2NaOH + Li2CO3 -> Li2TiO3 + Na2CO3 + 4HNO3 (cycle)
        BLAST_RECIPES.recipeBuilder()
            .input(dust, TitaniumNitrate, 17)
            .input(dust, SodiumHydroxide, 6)
            .input(dust, LithiumCarbonate, 6)
            .output(ingotHot, LithiumTitanate, 6)
            .output(dust, SodaAsh, 6)
            .fluidOutputs(NitricAcid.getFluid(4000))
            .EUt(VA[EV])
            .duration(16 * SECOND)
            .blastFurnaceTemp(3100)
            .buildAndRegister()
    }

    // @formatter:on

}