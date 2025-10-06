package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.LARGE_CHEMICAL_RECIPES
import gregtech.api.unification.material.Materials.CarbonDioxide
import gregtech.api.unification.material.Materials.DistilledWater
import gregtech.api.unification.material.Materials.Ethylene
import gregtech.api.unification.material.Materials.Magnesium
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Palladium
import gregtech.api.unification.material.Materials.Silver
import gregtech.api.unification.material.Materials.Steam
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.spring
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.BURNER_REACTOR_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.VACUUM_CHAMBER_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Acetaldehyde
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.EthyleneGlycol
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.EthyleneOxide
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.SU
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt

internal object EthyleneGlycolChain
{

    // @formatter:off

    fun init()
    {
        // 7C2H4 + 12O -> 6C2H4O (ethylene oxide) + 2CO2 + 2H2O
        BURNER_REACTOR_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .notConsumable(dust, Silver)
            .fluidInputs(Ethylene.getFluid(7000))
            .fluidInputs(Oxygen.getFluid(12000))
            .fluidOutputs(EthyleneOxide.getFluid(6000))
            .fluidOutputs(CarbonDioxide.getFluid(2000))
            .fluidOutputs(Steam.getFluid(2 * SU))
            .EUt(VA[HV])
            .duration(7 * SECOND + 10 * TICK)
            .buildAndRegister()

        // 7C2H4 + 12O -> 6C2H4O (acetaldehyde) + 2CO2 + 2H2O
        BURNER_REACTOR_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .notConsumable(dust, Palladium)
            .fluidInputs(Ethylene.getFluid(7000))
            .fluidInputs(Oxygen.getFluid(12000))
            .fluidOutputs(Acetaldehyde.getFluid(6000))
            .fluidOutputs(CarbonDioxide.getFluid(2000))
            .fluidOutputs(Steam.getFluid(2 * SU))
            .EUt(VA[EV])
            .duration(15 * SECOND)
            .buildAndRegister()

        // C2H4O converts.
        VACUUM_CHAMBER_RECIPES.recipeBuilder()
            .notConsumable(spring, Magnesium)
            .fluidInputs(EthyleneOxide.getFluid(1000))
            .fluidOutputs(Acetaldehyde.getFluid(1000))
            .EUt(VA[MV])
            .duration(10 * TICK)
            .buildAndRegister()

        // C2H4O + H2O -> C2H6O2
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
            .fluidInputs(EthyleneOxide.getFluid(1000))
            .fluidInputs(DistilledWater.getFluid(1000))
            .fluidInputs(CarbonDioxide.getFluid(100)) // as catalyst
            .fluidOutputs(EthyleneGlycol.getFluid(1000))
            .EUt(VA[HV])
            .duration(20 * SECOND)
            .buildAndRegister()
    }

    // @formatter:on

}