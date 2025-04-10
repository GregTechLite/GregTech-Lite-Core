package magicbook.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.LARGE_CHEMICAL_RECIPES
import gregtech.api.unification.material.Materials.CarbonDioxide
import gregtech.api.unification.material.Materials.DistilledWater
import gregtech.api.unification.material.Materials.Ethylene
import gregtech.api.unification.material.Materials.Silver
import gregtech.api.unification.material.Materials.Steam
import gregtech.api.unification.ore.OrePrefix.dust
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.BURNER_REACTOR_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.EthyleneGlycol
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.EthyleneOxide
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK

@Suppress("MISSING_DEPENDENCY_CLASS")
class EthyleneGlycolChain
{

    companion object
    {

        fun init()
        {
            // 7C2H4 + 12O -> 6C2H4O + 2CO2 + 2H2O
            BURNER_REACTOR_RECIPES.recipeBuilder()
                .notConsumable(dust, Silver)
                .fluidInputs(Ethylene.getFluid(7000))
                .fluidOutputs(EthyleneOxide.getFluid(6000))
                .fluidOutputs(CarbonDioxide.getFluid(2000))
                .fluidOutputs(Steam.getFluid(2000))
                .EUt(VA[HV].toLong())
                .duration(7 * SECOND + 10 * TICK)
                .buildAndRegister()

            // C2H4O + H2O -> C2H6O2
            LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(EthyleneOxide.getFluid(1000))
                .fluidInputs(DistilledWater.getFluid(1000))
                .fluidInputs(CarbonDioxide.getFluid(100)) // as catalyst
                .fluidOutputs(EthyleneGlycol.getFluid(1000))
                .EUt(VA[HV].toLong())
                .duration(20 * SECOND)
                .buildAndRegister()
        }

    }

}