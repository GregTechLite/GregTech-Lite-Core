package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.GTRecipeHandler
import gregtech.api.recipes.RecipeMaps.ARC_FURNACE_RECIPES
import gregtech.api.recipes.RecipeMaps.ELECTROLYZER_RECIPES
import gregtech.api.recipes.ingredients.IntCircuitIngredient
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Materials.Air
import gregtech.api.unification.material.Materials.Carbon
import gregtech.api.unification.material.Materials.CarbonMonoxide
import gregtech.api.unification.material.Materials.CertusQuartz
import gregtech.api.unification.material.Materials.NetherQuartz
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Palladium
import gregtech.api.unification.material.Materials.Quartzite
import gregtech.api.unification.material.Materials.Rhodium
import gregtech.api.unification.material.Materials.RhodiumPlatedPalladium
import gregtech.api.unification.material.Materials.Silicon
import gregtech.api.unification.material.Materials.SiliconDioxide
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.ingotHot
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SiliconCarbide
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeHandler

internal object AlloysChain
{

    // @formatter:off

    fun init()
    {
        siliconCarbideProcess()
        rhodiumPlatedPalladiumProcess()
    }

    private fun siliconCarbideProcess()
    {
        // Add a simplified recipes for silicon dust (arc furnace -> dust, EBF -> ingot).
        GTRecipeHandler.removeRecipesByInputs(ELECTROLYZER_RECIPES,
            OreDictUnifier.get(dust, Silicon, 3))

        // SiO2 -> Si + 2O
        ARC_FURNACE_RECIPES.recipeBuilder()
            .circuitMeta(0)
            .input(dust, SiliconDioxide, 3)
            .notConsumable(Air.getFluid(1000))
            .output(dust, Silicon)
            .fluidOutputs(Oxygen.getFluid(2000))
            .EUt(VA[LV])
            .duration(3 * SECOND)
            .buildAndRegister()

        // Allowed to decompose quartzite, etc.
        ARC_FURNACE_RECIPES.recipeBuilder()
            .circuitMeta(0)
            .input(dust, Quartzite, 3)
            .notConsumable(Air.getFluid(1000))
            .output(dust, Silicon)
            .fluidOutputs(Oxygen.getFluid(2000))
            .EUt(VA[LV])
            .duration(3 * SECOND)
            .buildAndRegister()

        ARC_FURNACE_RECIPES.recipeBuilder()
            .circuitMeta(0)
            .input(dust, NetherQuartz, 3)
            .notConsumable(Air.getFluid(1000))
            .output(dust, Silicon)
            .fluidOutputs(Oxygen.getFluid(2000))
            .EUt(VA[LV])
            .duration(3 * SECOND)
            .buildAndRegister()

        ARC_FURNACE_RECIPES.recipeBuilder()
            .circuitMeta(0)
            .input(dust, CertusQuartz, 3)
            .notConsumable(Air.getFluid(1000))
            .output(dust, Silicon)
            .fluidOutputs(Oxygen.getFluid(2000))
            .EUt(VA[LV])
            .duration(3 * SECOND)
            .buildAndRegister()

        // SiO2 + 2C -> Si + 2CO
        ARC_FURNACE_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(dust, SiliconDioxide, 3)
            .input(dust, Carbon, 2)
            .output(dust, Silicon)
            .fluidOutputs(CarbonMonoxide.getFluid(2000))
            .EUt(VA[LV])
            .duration(15 * SECOND)
            .buildAndRegister()

        ARC_FURNACE_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(dust, Quartzite, 3)
            .input(dust, Carbon, 2)
            .output(dust, Silicon)
            .fluidOutputs(CarbonMonoxide.getFluid(2000))
            .EUt(VA[LV])
            .duration(15 * SECOND)
            .buildAndRegister()

        ARC_FURNACE_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(dust, CertusQuartz, 3)
            .input(dust, Carbon, 2)
            .output(dust, Silicon)
            .fluidOutputs(CarbonMonoxide.getFluid(2000))
            .EUt(VA[LV])
            .duration(15 * SECOND)
            .buildAndRegister()

        ARC_FURNACE_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(dust, NetherQuartz, 3)
            .input(dust, Carbon, 2)
            .output(dust, Silicon)
            .fluidOutputs(CarbonMonoxide.getFluid(2000))
            .EUt(VA[LV])
            .duration(15 * SECOND)
            .buildAndRegister()

        // SiO2 + 3C -> SiC + 2CO
        ARC_FURNACE_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(dust, SiliconDioxide, 3)
            .input(dust, Carbon, 3)
            .output(ingotHot, SiliconCarbide, 2)
            .fluidOutputs(CarbonMonoxide.getFluid(2000))
            .EUt(VA[EV])
            .duration(20 * SECOND)
            .buildAndRegister()

        ARC_FURNACE_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(dust, Quartzite, 3)
            .input(dust, Carbon, 3)
            .output(ingotHot, SiliconCarbide, 2)
            .fluidOutputs(CarbonMonoxide.getFluid(2000))
            .EUt(VA[EV])
            .duration(20 * SECOND)
            .buildAndRegister()

        ARC_FURNACE_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(dust, CertusQuartz, 3)
            .input(dust, Carbon, 3)
            .output(ingotHot, SiliconCarbide, 2)
            .fluidOutputs(CarbonMonoxide.getFluid(2000))
            .EUt(VA[EV])
            .duration(20 * SECOND)
            .buildAndRegister()

        ARC_FURNACE_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(dust, NetherQuartz, 3)
            .input(dust, Carbon, 3)
            .output(ingotHot, SiliconCarbide, 2)
            .fluidOutputs(CarbonMonoxide.getFluid(2000))
            .EUt(VA[EV])
            .duration(20 * SECOND)
            .buildAndRegister()

    }

    private fun rhodiumPlatedPalladiumProcess()
    {
        // Change rhodium plated palladium smelting recipes to arc furnace but not EBF,
        // player can get liquid by common mixer and ABS.
        GTLiteRecipeHandler.removeMixerRecipes(
            arrayOf(OreDictUnifier.get(dust, Palladium, 3),
                OreDictUnifier.get(dust, Rhodium),
                IntCircuitIngredient.getIntegratedCircuit(1)))

        // 3Pd + Rh -> Pd3Rh
        ARC_FURNACE_RECIPES.recipeBuilder()
            .input(dust, Palladium, 3)
            .fluidInputs(Rhodium.getFluid(L))
            .fluidInputs(Oxygen.getFluid(600))
            .output(ingotHot, RhodiumPlatedPalladium, 4)
            .EUt(VA[IV])
            .duration(30 * SECOND)
            .buildAndRegister()

    }

    // @formatter:on

}