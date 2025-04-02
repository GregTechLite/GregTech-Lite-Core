package magicbook.gtlitecore.loader.recipe.oreprocessing

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
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.CHEMICAL_DEHYDRATOR_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.ROASTER_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TungstenTrioxide
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.MINUTE
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK

@Suppress("MISSING_DEPENDENCY_CLASS")
class TungstenProcessing
{

    companion object
    {

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
                .EUt(VH[LV].toLong())
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
                .EUt(VA[EV].toLong())
                .duration(2 * MINUTE)
                .buildAndRegister()

            //  WO3 + 6H -> W + 3H2O
            CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, TungstenTrioxide, 4)
                .fluidInputs(Hydrogen.getFluid(6000))
                .output(dust, Tungsten)
                .fluidOutputs(Water.getFluid(3000))
                .EUt(VHA[EV].toLong())
                .duration(10 * SECOND + 10 * TICK)
                .buildAndRegister()

            // 2WO3 + 3C -> 2W + 3CO2
            ROASTER_RECIPES.recipeBuilder()
                .input(dust, TungstenTrioxide, 8)
                .input(dust, Carbon, 3)
                .output(dust, Tungsten, 2)
                .fluidOutputs(CarbonDioxide.getFluid(3000))
                .EUt(VA[EV].toLong())
                .duration(30 * SECOND)
                .buildAndRegister()

        }

    }

}