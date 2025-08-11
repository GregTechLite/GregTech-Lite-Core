package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.ZPM
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.unification.material.Materials.Bromine
import gregtech.api.unification.material.Materials.CarbonMonoxide
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.Salt
import gregtech.api.unification.material.Materials.Selenium
import gregtech.api.unification.material.Materials.Sodium
import gregtech.api.unification.material.Materials.SodiumBisulfate
import gregtech.api.unification.material.Materials.SodiumHydroxide
import gregtech.api.unification.material.Materials.SulfuricAcid
import gregtech.api.unification.material.Materials.Tetrafluoroethylene
import gregtech.api.unification.material.Materials.TitaniumTetrachloride
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.ore.OrePrefix.dust
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.ROASTER_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.AmmoniumPerrhenate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BETS
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BETSPerrhenate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Bromobutane
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Bromodihydrothiine
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Butyllithium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Dibromoacrolein
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Dichloroethane
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.FormicAcid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LithiumFluoride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LithiumThiinediselenide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SodiumFormate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SodiumThiosulfate
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt

internal object BETSPerrhenateChain
{

    // @formatter:off

    fun init()
    {
        // NaOH + CO -> HCOONa
        ROASTER_RECIPES.recipeBuilder()
            .input(dust, SodiumHydroxide, 3)
            .fluidInputs(CarbonMonoxide.getFluid(1000))
            .output(dust, SodiumFormate, 5)
            .EUt(VA[LV])
            .duration(4 * SECOND + 10 * TICK)
            .buildAndRegister()

        // HCOONa + H2SO4 -> Na2S2O3 + 2HCOOH (cycle)
        CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, SodiumFormate, 5)
            .fluidInputs(SulfuricAcid.getFluid(1000))
            .output(dust, SodiumThiosulfate, 7)
            .fluidOutputs(FormicAcid.getFluid(1000))
            .EUt(VA[LV])
            .duration(6 * SECOND)
            .buildAndRegister()

        // 4Na + 2HCOOH + 2Br + 2H2O -> 4NaOH + C2H2Br2O2 + 2H
        CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, Sodium, 4)
            .fluidInputs(FormicAcid.getFluid(2000))
            .fluidInputs(Bromine.getFluid(2000))
            .fluidInputs(Water.getFluid(2000))
            .output(dust, SodiumHydroxide, 12)
            .fluidOutputs(Dibromoacrolein.getFluid(1000))
            .fluidOutputs(Hydrogen.getFluid(2000))
            .EUt(VA[EV])
            .duration(18 * SECOND)
            .buildAndRegister()

        // 2Na2S2O3 + C2H2Br2O2 + C2H4Cl2 -> 2NaCl + 2NaHSO4 + C4H4S2Br2
        CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, SodiumThiosulfate, 14)
            .fluidInputs(Dibromoacrolein.getFluid(1000))
            .fluidInputs(Dichloroethane.getFluid(1000))
            .output(dust, Salt, 4)
            .output(dust, SodiumBisulfate, 14)
            .fluidOutputs(Bromodihydrothiine.getFluid(1000))
            .EUt(VA[IV])
            .duration(16 * SECOND)
            .buildAndRegister()

        // 2Se + C4H4S2Br2 + 2C4H9Li -> C4H4S2Li2Se2 + 2C4H9Br
        CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, Selenium, 2)
            .fluidInputs(Bromodihydrothiine.getFluid(1000))
            .fluidInputs(Butyllithium.getFluid(2000))
            .output(dust, LithiumThiinediselenide, 14)
            .fluidOutputs(Bromobutane.getFluid(2000))
            .EUt(VA[LuV])
            .duration(17 * SECOND)
            .buildAndRegister()

        // 2C4H4S2Li2Se2 + C2F4 -> C10H8S4Se4 + 4LiF
        CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, LithiumThiinediselenide, 28)
            .fluidInputs(Tetrafluoroethylene.getFluid(1000))
            .notConsumable(TitaniumTetrachloride.getFluid(1))
            .output(dust, BETS, 26)
            .output(dust, LithiumFluoride, 8)
            .EUt(VA[UHV])
            .duration(40 * SECOND)
            .buildAndRegister()

        // C10H8S4Se4 + NH4ReO4 -> (C10H8S4Se4)ReO4
        ROASTER_RECIPES.recipeBuilder()
            .input(dust, BETS, 26)
            .input(dust, AmmoniumPerrhenate, 10)
            .output(dust, BETSPerrhenate, 31)
            .EUt(VA[ZPM])
            .duration(5 * SECOND)
            .buildAndRegister()
    }

    // @formatter:on

}