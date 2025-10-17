package gregtechlite.gtlitecore.loader.recipe.oreprocessing

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.VH
import gregtech.api.GTValues.VHA
import gregtech.api.recipes.GTRecipeHandler
import gregtech.api.recipes.RecipeMaps.BLAST_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.ELECTROLYZER_RECIPES
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Materials.Carbon
import gregtech.api.unification.material.Materials.CarbonDioxide
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.Tungsten
import gregtech.api.unification.material.Materials.TungsticAcid
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.ingotHot
import gregtechlite.gtlitecore.api.MINUTE
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.ROASTER_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TungstenTrioxide

internal object TungstenProcessing
{

    // @formatter:off

    fun init()
    {
        // Remove original recipes of H2WO4.
        GTRecipeHandler.removeRecipesByInputs(ELECTROLYZER_RECIPES,
            OreDictUnifier.get(dust, TungsticAcid, 7))

        // H2WO4/(WO3)·H2O -> WO3 + H2O
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
            .input(dust, TungsticAcid, 7)
            .output(dust, TungstenTrioxide, 4)
            .fluidOutputs(Water.getFluid(1000))
            .EUt(VH[LV])
            .duration(8 * SECOND)
            .buildAndRegister()

        // Choices of WO3 -> W process:
        // i)   Blasting it with Carbon dust and get Tungsten hot ingot.
        // ii)  Roasting it with Carbon dust and get Tungsten dust.
        // iii) Chemistry processing it with Liquid Hydrogen and get Tungsten dust.

        // 2WO3 + 3C -> 2W + 3CO2
        BLAST_RECIPES.recipeBuilder()
            .input(dust, TungstenTrioxide, 8)
            .input(dust, Carbon, 3)
            .output(ingotHot, Tungsten, 2)
            .fluidOutputs(CarbonDioxide.getFluid(3000))
            .blastFurnaceTemp(Tungsten.blastTemperature)
            .EUt(VA[EV])
            .duration(2 * MINUTE)
            .buildAndRegister()

        //  WO3 + 6H -> W + 3H2O
        CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, TungstenTrioxide, 4)
            .fluidInputs(Hydrogen.getFluid(6000))
            .output(dust, Tungsten)
            .fluidOutputs(Water.getFluid(3000))
            .EUt(VHA[EV])
            .duration(10 * SECOND + 10 * TICK)
            .buildAndRegister()

        // 2WO3 + 3C -> 2W + 3CO2
        ROASTER_RECIPES.recipeBuilder()
            .input(dust, TungstenTrioxide, 8)
            .input(dust, Carbon, 3)
            .output(dust, Tungsten, 2)
            .fluidOutputs(CarbonDioxide.getFluid(3000))
            .EUt(VA[EV])
            .duration(30 * SECOND)
            .buildAndRegister()

    }

    // @formatter:on

}