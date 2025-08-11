package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.VH
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.FERMENTING_RECIPES
import gregtech.api.recipes.RecipeMaps.SIFTER_RECIPES
import gregtech.api.unification.material.Materials.Biomass
import gregtech.api.unification.material.Materials.CalciumChloride
import gregtech.api.unification.material.Materials.CarbonDioxide
import gregtech.api.unification.material.Materials.Diatomite
import gregtech.api.unification.material.Materials.DistilledWater
import gregtech.api.unification.material.Materials.Methane
import gregtech.api.unification.material.Materials.Salt
import gregtech.api.unification.material.Materials.SaltWater
import gregtech.api.unification.material.Materials.SulfuricAcid
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.common.items.MetaItems.CARBON_MESH
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.BIO_REACTOR_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.BURNER_REACTOR_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.AlgaeMixture
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BrownAlgae
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CalciumAlginate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Cellulose
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.GreenAlgae
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LithiumCarbonate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.RedAlgae
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SodiumAlginate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SodiumCarbonate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Xylose
import gregtechlite.gtlitecore.api.MINUTE
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt

internal object AlgaeChain
{

    // @formatter:off

    fun init()
    {
        BIO_REACTOR_RECIPES.recipeBuilder()
            .circuitMeta(4)
            .fluidInputs(Biomass.getFluid(1000))
            .fluidInputs(SaltWater.getFluid(1000))
            .fluidOutputs(AlgaeMixture.getFluid(1000))
            .EUt(VA[LV])
            .duration(10 * SECOND)
            .buildAndRegister()

        SIFTER_RECIPES.recipeBuilder()
            .notConsumable(CARBON_MESH)
            .fluidInputs(AlgaeMixture.getFluid(1000))
            .chancedOutput(dust, GreenAlgae, 6500, 250)
            .chancedOutput(dust, BrownAlgae, 5500, 500)
            .chancedOutput(dust, RedAlgae, 4500, 750)
            .fluidOutputs(SaltWater.getFluid(1000))
            .EUt(VA[LV])
            .duration(20 * SECOND)
            .buildAndRegister()

        // 6(Na2CO3)(H2O)-> 2C5H7O4COONa + C5H10O5 + C6H10O5
        BURNER_REACTOR_RECIPES.recipeBuilder()
            .notConsumable(dust, Diatomite)
            .input(dust, BrownAlgae, 10)
            .fluidInputs(SodiumCarbonate.getFluid(6000))
            .output(dust, SodiumAlginate, 40)
            .output(dust, Xylose, 20)
            .output(dust, Cellulose, 42)
            .fluidOutputs(CarbonDioxide.getFluid(6000))
            .fluidOutputs(Water.getFluid(6000))
            .EUt(VA[EV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // 2C5H7O4COONa + CaCl2 -> (C5H7O4COO)2Ca + 2NaCl
        CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, SodiumAlginate, 40)
            .input(dust, CalciumChloride, 3)
            .output(dust, CalciumAlginate, 39)
            .output(dust, Salt, 4)
            .EUt(VA[IV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // Another usages for algae.

        // Green Algae + H2O -> CH4
        FERMENTING_RECIPES.recipeBuilder()
            .input(dust, GreenAlgae, 10)
            .fluidInputs(DistilledWater.getFluid(500))
            .fluidOutputs(Methane.getFluid(500))
            .EUt(VH[MV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // Green Algae + Brown Algae + 5H2O -> 5H2SO4
        CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, GreenAlgae, 10)
            .input(dust, BrownAlgae, 5)
            .fluidInputs(DistilledWater.getFluid(5000))
            .fluidOutputs(SulfuricAcid.getFluid(5000))
            .EUt(VA[HV])
            .duration(2 * MINUTE + 30 * SECOND)
            .buildAndRegister()

        // Brown Algae + 2H2O -> 3Li2CO3
        FERMENTING_RECIPES.recipeBuilder()
            .input(dust, BrownAlgae, 40)
            .fluidInputs(DistilledWater.getFluid(2000))
            .output(dust, LithiumCarbonate, 18)
            .EUt(VA[MV])
            .duration(1 * MINUTE + 30 * SECOND)
            .buildAndRegister()

    }

    // @formatter:on

}