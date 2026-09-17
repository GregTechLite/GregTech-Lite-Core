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
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.spring
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.addRecipe
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.BURNER_REACTOR_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.VACUUM_CHAMBER_RECIPES
import gregtechlite.gtlitecore.api.s
import gregtechlite.gtlitecore.api.t
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Acetaldehyde
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.EthyleneGlycol
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.EthyleneOxide

internal object EthyleneGlycolChain
{

    // @formatter:off

    fun init()
    {
        // 7C2H4 + 12O -> 6C2H4O (ethylene oxide) + 2CO2 + 2H2O
        BURNER_REACTOR_RECIPES.addRecipe {
            circuitMeta(1)
            notConsumable(dust, Silver)
            fluidInputs(Ethylene.getFluid(7000))
            fluidInputs(Oxygen.getFluid(12000))
            fluidOutputs(EthyleneOxide.getFluid(6000))
            fluidOutputs(CarbonDioxide.getFluid(2000))
            fluidOutputs(Water.getFluid(2000))
            EUt(VA[HV])
            duration(7.s + 10.t)
        }

        // 7C2H4 + 12O -> 6C2H4O (acetaldehyde) + 2CO2 + 2H2O
        BURNER_REACTOR_RECIPES.addRecipe {
            circuitMeta(2)
            notConsumable(dust, Palladium)
            fluidInputs(Ethylene.getFluid(7000))
            fluidInputs(Oxygen.getFluid(12000))
            fluidOutputs(Acetaldehyde.getFluid(6000))
            fluidOutputs(CarbonDioxide.getFluid(2000))
            fluidOutputs(Water.getFluid(2000))
            EUt(VA[EV])
            duration(15.s)
        }

        // C2H4O (ethylene oxide) -> C2H4O (acetaldehyde)
        VACUUM_CHAMBER_RECIPES.addRecipe {
            notConsumable(spring, Magnesium)
            fluidInputs(EthyleneOxide.getFluid(1000))
            fluidOutputs(Acetaldehyde.getFluid(1000))
            EUt(VA[MV])
            duration(10.t)
        }

        // C2H4O + H2O -> C2H6O2
        LARGE_CHEMICAL_RECIPES.addRecipe {
            fluidInputs(EthyleneOxide.getFluid(1000))
            fluidInputs(DistilledWater.getFluid(1000))
            fluidInputs(CarbonDioxide.getFluid(100)) // catalyst
            fluidOutputs(EthyleneGlycol.getFluid(1000))
            EUt(VA[HV])
            duration(20.s)
        }
    }

    // @formatter:on

}