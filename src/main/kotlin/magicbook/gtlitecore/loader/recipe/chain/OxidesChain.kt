package magicbook.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.ULV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.VHA
import gregtech.api.recipes.GTRecipeHandler
import gregtech.api.recipes.RecipeMaps.BLAST_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.ELECTROLYZER_RECIPES
import gregtech.api.recipes.ingredients.IntCircuitIngredient
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Materials.Antimony
import gregtech.api.unification.material.Materials.AntimonyTrioxide
import gregtech.api.unification.material.Materials.ArsenicTrioxide
import gregtech.api.unification.material.Materials.Ash
import gregtech.api.unification.material.Materials.Carbon
import gregtech.api.unification.material.Materials.CarbonDioxide
import gregtech.api.unification.material.Materials.CarbonMonoxide
import gregtech.api.unification.material.Materials.Chalcopyrite
import gregtech.api.unification.material.Materials.Charcoal
import gregtech.api.unification.material.Materials.Chrome
import gregtech.api.unification.material.Materials.ChromiumTrioxide
import gregtech.api.unification.material.Materials.Coal
import gregtech.api.unification.material.Materials.CobaltOxide
import gregtech.api.unification.material.Materials.Cobaltite
import gregtech.api.unification.material.Materials.CupricOxide
import gregtech.api.unification.material.Materials.Ferrosilite
import gregtech.api.unification.material.Materials.Hafnia
import gregtech.api.unification.material.Materials.Magnesia
import gregtech.api.unification.material.Materials.Magnesite
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Phosphorus
import gregtech.api.unification.material.Materials.PhosphorusPentoxide
import gregtech.api.unification.material.Materials.Silicon
import gregtech.api.unification.material.Materials.SiliconDioxide
import gregtech.api.unification.material.Materials.Stibnite
import gregtech.api.unification.material.Materials.SulfurDioxide
import gregtech.api.unification.material.Materials.Tetrahedrite
import gregtech.api.unification.material.Materials.Zircon
import gregtech.api.unification.material.Materials.Zirconia
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.dustTiny
import gregtech.api.unification.ore.OrePrefix.gem
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.ROASTER_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Lignite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Tenorite
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK
import magicbook.gtlitecore.api.utils.GTRecipeUtility
import net.minecraft.init.Items
import net.minecraft.item.ItemStack

@Suppress("MISSING_DEPENDENCY_CLASS")
class OxidesChain
{

    companion object
    {

        fun init()
        {
            carbonMonoxideProcessing()
            carbonDioxideProcessing()
            // Deleted original MgCO3 -> MgO + CO2 recipes in chemical reactor.
            GTRecipeUtility.removeChemicalRecipes(
                arrayOf(OreDictUnifier.get(dust, Magnesite, 5)))

            ROASTER_RECIPES.recipeBuilder()
                .input(dust, Magnesite, 5)
                .output(dust, Magnesia, 2)
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .EUt(VA[LV].toLong())
                .duration(12 * SECOND)
                .buildAndRegister()

            // Deleted original stibnite processing in EBF and simple recipes in chemical reactor.
            GTRecipeHandler.removeRecipesByInputs(BLAST_RECIPES,
                arrayOf(OreDictUnifier.get(dust, Stibnite)),
                arrayOf(Oxygen.getFluid(3000)))

            ROASTER_RECIPES.recipeBuilder()
                .input(dust, Stibnite)
                .fluidInputs(Oxygen.getFluid(3000))
                .output(dust, AntimonyTrioxide)
                .chancedOutput(dust, Ash, 1000, 0)
                .fluidOutputs(SulfurDioxide.getFluid(1500))
                .EUt(VA[MV].toLong())
                .duration(6 * SECOND)
                .buildAndRegister()

            GTRecipeUtility.removeChemicalRecipes(
                arrayOf(OreDictUnifier.get(dust, Antimony, 2)),
                arrayOf(Oxygen.getFluid(3000)))

            ROASTER_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .input(dust, Antimony, 2)
                .fluidInputs(Oxygen.getFluid(3000))
                .output(dust, AntimonyTrioxide, 5)
                .EUt(VA[ULV].toLong())
                .duration(3 * SECOND)
                .buildAndRegister()

            // Rework Chalcopyrite and Tetrahedrite recipes.
            GTRecipeHandler.removeRecipesByInputs(BLAST_RECIPES,
                arrayOf(OreDictUnifier.get(dust, Chalcopyrite),
                    OreDictUnifier.get(dust, SiliconDioxide)),
                arrayOf(Oxygen.getFluid(3000)))

            GTRecipeHandler.removeRecipesByInputs(BLAST_RECIPES,
                arrayOf(OreDictUnifier.get(dust, Tetrahedrite)),
                arrayOf(Oxygen.getFluid(3000)))

            ROASTER_RECIPES.recipeBuilder()
                .input(dust, Chalcopyrite)
                .input(dust, SiliconDioxide)
                .fluidInputs(Oxygen.getFluid(3000))
                .output(dust, Tenorite)
                .output(dust, Ferrosilite)
                .fluidOutputs(SulfurDioxide.getFluid(2000))
                .EUt(VA[MV].toLong())
                .duration(6 * SECOND)
                .buildAndRegister()

            ROASTER_RECIPES.recipeBuilder()
                .input(dust, Tetrahedrite)
                .fluidInputs(Oxygen.getFluid(3000))
                .output(dust, Tenorite)
                .output(dustTiny, AntimonyTrioxide, 3)
                .fluidOutputs(SulfurDioxide.getFluid(2000))
                .EUt(VA[MV].toLong())
                .duration(6 * SECOND)
                .buildAndRegister()

            // see: JustEnoughItemsModel#onRuntimeAvailable(), we hidden CupricOxide (CuO)
            // in all materials and change it to CuO, so we remove all related recipes.
            GTRecipeHandler.removeRecipesByInputs(ELECTROLYZER_RECIPES,
                OreDictUnifier.get(dust, CupricOxide, 2))

            // Cobaltite processing.
            GTRecipeHandler.removeRecipesByInputs(BLAST_RECIPES,
                arrayOf(OreDictUnifier.get(dust, Cobaltite)),
                arrayOf(Oxygen.getFluid(3000)))

            ROASTER_RECIPES.recipeBuilder()
                .input(dust, Cobaltite)
                .fluidInputs(Oxygen.getFluid(3000))
                .output(dust, CobaltOxide)
                .output(dust, ArsenicTrioxide)
                .fluidOutputs(SulfurDioxide.getFluid(1000))
                .EUt(VA[MV].toLong())
                .duration(6 * SECOND)
                .buildAndRegister()

            // Remove ZrSiO4 blasting recipes.
            GTRecipeHandler.removeRecipesByInputs(BLAST_RECIPES,
                OreDictUnifier.get(dust, Zircon, 6))

            // Change ZrSiO4 ore processing from EBF to roaster.
            // ZrSiO4 -> ZrO2 + HfO2 + SiO2
            ROASTER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(dust, Zircon, 6)
                .output(dust, Zirconia, 3)
                .output(dust, SiliconDioxide, 3)
                .chancedOutput(dust, Hafnia, 3333, 0)
                .EUt(VA[MV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            ROASTER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(dust, Zircon, 6)
                .fluidInputs(Oxygen.getFluid(2000))
                .output(dust, Zirconia, 3)
                .output(dust, Hafnia, 3)
                .output(dust, SiliconDioxide, 3)
                .EUt(VA[HV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // Si + 2O -> SiO2
            ROASTER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(dust, Silicon)
                .fluidInputs(Oxygen.getFluid(2000))
                .output(dust, SiliconDioxide, 3)
                .EUt(VA[LV].toLong())
                .duration(10 * TICK)
                .buildAndRegister()

            // Deleted original P4O10 chemical reactor recipes.
            GTRecipeUtility.removeChemicalRecipes(
                arrayOf(OreDictUnifier.get(dust, Phosphorus, 4),
                    IntCircuitIngredient.getIntegratedCircuit(1)),
                arrayOf(Oxygen.getFluid(10000)))

            ROASTER_RECIPES.recipeBuilder()
                .circuitMeta(5)
                .input(dust, Phosphorus, 4)
                .fluidInputs(Oxygen.getFluid(10000))
                .output(dust, PhosphorusPentoxide, 14)
                .EUt(VA[LV].toLong())
                .duration(2 * SECOND)
                .buildAndRegister()

            // Deleted original CrO3 chemical reactor recipes.
            GTRecipeUtility.removeChemicalRecipes(
                arrayOf(OreDictUnifier.get(dust, Chrome, 1)),
                arrayOf(Oxygen.getFluid(3000)))

            ROASTER_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .input(dust, Chrome)
                .fluidInputs(Oxygen.getFluid(3000))
                .output(dust, ChromiumTrioxide, 4)
                .EUt(VHA[MV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

        }

        private fun carbonMonoxideProcessing()
        {
            // Remove vanilla C + O -> CO recipes in Chemical Reactor.
            GTRecipeUtility.removeChemicalRecipes(
                arrayOf(OreDictUnifier.get(dust, Carbon, 1),
                    IntCircuitIngredient.getIntegratedCircuit(1)),
                arrayOf(Oxygen.getFluid(1000)))

            GTRecipeUtility.removeChemicalRecipes(
                arrayOf(ItemStack(Items.COAL, 1, 0),
                    IntCircuitIngredient.getIntegratedCircuit(1)),
                arrayOf(Oxygen.getFluid(1000)))

            GTRecipeUtility.removeChemicalRecipes(
                arrayOf(ItemStack(Items.COAL, 1, 1),
                    IntCircuitIngredient.getIntegratedCircuit(1)),
                arrayOf(Oxygen.getFluid(1000)))

            GTRecipeUtility.removeChemicalRecipes(
                arrayOf(OreDictUnifier.get(dust, Coal, 1),
                    IntCircuitIngredient.getIntegratedCircuit(1)),
                arrayOf(Oxygen.getFluid(1000)))

            GTRecipeUtility.removeChemicalRecipes(
                arrayOf(OreDictUnifier.get(dust, Charcoal, 1),
                    IntCircuitIngredient.getIntegratedCircuit(1)),
                arrayOf(Oxygen.getFluid(1000)))

            // C + O -> CO
            ROASTER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(dust, Carbon, 1)
                .fluidInputs(Oxygen.getFluid(1000))
                .fluidOutputs(CarbonMonoxide.getFluid(1000))
                .EUt(VA[ULV].toLong())
                .duration(2 * SECOND)
                .buildAndRegister()

            // Vanilla Coals recipes for C + O -> CO, will output some Ash,
            // in these recipes, chances of Ash output will smaller (just a little)
            // than vanilla GregTech (11.11% -> 10%).
            ROASTER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .inputs(ItemStack(Items.COAL, 1, 0))
                .chancedOutput(dust, Ash, 1000, 0)
                .fluidInputs(Oxygen.getFluid(1000))
                .fluidOutputs(CarbonMonoxide.getFluid(1000))
                .EUt(VA[ULV].toLong())
                .duration(4 * SECOND)
                .buildAndRegister()

            ROASTER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .inputs(ItemStack(Items.COAL, 1, 1))
                .chancedOutput(dust, Ash, 1000, 0)
                .fluidInputs(Oxygen.getFluid(1000))
                .fluidOutputs(CarbonMonoxide.getFluid(1000))
                .EUt(VA[ULV].toLong())
                .duration(4 * SECOND)
                .buildAndRegister()

            // Add Compatibility of gtlitecore lignite to these recipes.
            ROASTER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(gem, Lignite)
                .chancedOutput(dust, Ash, 1000, 0)
                .fluidInputs(Oxygen.getFluid(1000))
                .fluidOutputs(CarbonMonoxide.getFluid(1000))
                .EUt(VA[ULV].toLong())
                .duration(4 * SECOND)
                .buildAndRegister()

            // Dust form of two vanilla coals and Lignite.
            ROASTER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(dust, Coal)
                .chancedOutput(dust, Ash, 1000, 0)
                .fluidInputs(Oxygen.getFluid(1000))
                .fluidOutputs(CarbonMonoxide.getFluid(1000))
                .EUt(VA[ULV].toLong())
                .duration(4 * SECOND)
                .buildAndRegister()

            ROASTER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(dust, Charcoal)
                .chancedOutput(dust, Ash, 1000, 0)
                .fluidInputs(Oxygen.getFluid(1000))
                .fluidOutputs(CarbonMonoxide.getFluid(1000))
                .EUt(VA[ULV].toLong())
                .duration(4 * SECOND)
                .buildAndRegister()

            ROASTER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(dust, Lignite)
                .chancedOutput(dust, Ash, 1000, 0)
                .fluidInputs(Oxygen.getFluid(1000))
                .fluidOutputs(CarbonMonoxide.getFluid(1000))
                .EUt(VA[ULV].toLong())
                .duration(4 * SECOND)
                .buildAndRegister()
        }

        private fun carbonDioxideProcessing()
        {

            // Remove vanilla C + 2O -> CO2 recipes in Chemical Reactor.
            GTRecipeUtility.removeChemicalRecipes(
                arrayOf(OreDictUnifier.get(dust, Carbon, 1),
                    IntCircuitIngredient.getIntegratedCircuit(2)),
                arrayOf(Oxygen.getFluid(2000)))

            GTRecipeUtility.removeChemicalRecipes(
                arrayOf(ItemStack(Items.COAL, 1, 0),
                    IntCircuitIngredient.getIntegratedCircuit(2)),
                arrayOf(Oxygen.getFluid(2000)))

            GTRecipeUtility.removeChemicalRecipes(
                arrayOf(ItemStack(Items.COAL, 1, 1),
                    IntCircuitIngredient.getIntegratedCircuit(2)),
                arrayOf(Oxygen.getFluid(2000)))

            GTRecipeUtility.removeChemicalRecipes(
                arrayOf(OreDictUnifier.get(dust, Coal, 1),
                    IntCircuitIngredient.getIntegratedCircuit(2)),
                arrayOf(Oxygen.getFluid(2000)))

            GTRecipeUtility.removeChemicalRecipes(
                arrayOf(OreDictUnifier.get(dust, Charcoal, 1),
                    IntCircuitIngredient.getIntegratedCircuit(2)),
                arrayOf(Oxygen.getFluid(2000)))

            // C + 2O -> CO2
            ROASTER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(dust, Carbon, 1)
                .fluidInputs(Oxygen.getFluid(2000))
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .EUt(VA[ULV].toLong())
                .duration(2 * SECOND)
                .buildAndRegister()

            // Vanilla Coals recipes for C + 2O -> CO2, will output some Ash,
            // in these recipes, chances of Ash output will smaller (just a little)
            // than vanilla GregTech (11.11% -> 10%).
            ROASTER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .inputs(ItemStack(Items.COAL, 1, 0))
                .chancedOutput(dust, Ash, 1000, 0)
                .fluidInputs(Oxygen.getFluid(2000))
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .EUt(VA[ULV].toLong())
                .duration(4 * SECOND)
                .buildAndRegister()

            ROASTER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .inputs(ItemStack(Items.COAL, 1, 1))
                .chancedOutput(dust, Ash, 1000, 0)
                .fluidInputs(Oxygen.getFluid(2000))
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .EUt(VA[ULV].toLong())
                .duration(4 * SECOND)
                .buildAndRegister()

            // Add Compatibility of gtlitecore lignite to these recipes.
            ROASTER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(gem, Lignite)
                .chancedOutput(dust, Ash, 1000, 0)
                .fluidInputs(Oxygen.getFluid(2000))
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .EUt(VA[ULV].toLong())
                .duration(4 * SECOND)
                .buildAndRegister()

            // Dust form of two vanilla coals and Lignite.
            ROASTER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(dust, Coal)
                .chancedOutput(dust, Ash, 1000, 0)
                .fluidInputs(Oxygen.getFluid(2000))
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .EUt(VA[ULV].toLong())
                .duration(4 * SECOND)
                .buildAndRegister()

            ROASTER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(dust, Charcoal)
                .chancedOutput(dust, Ash, 1000, 0)
                .fluidInputs(Oxygen.getFluid(2000))
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .EUt(VA[ULV].toLong())
                .duration(4 * SECOND)
                .buildAndRegister()

            ROASTER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(dust, Lignite)
                .chancedOutput(dust, Ash, 1000, 0)
                .fluidInputs(Oxygen.getFluid(2000))
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .EUt(VA[ULV].toLong())
                .duration(4 * SECOND)
                .buildAndRegister()

            // Delete vanilla CO2 + C -> CO recipe.
            GTRecipeUtility.removeChemicalRecipes(
                arrayOf(OreDictUnifier.get(dust, Carbon)),
                arrayOf(CarbonDioxide.getFluid(1000)))

            CHEMICAL_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(dust, Carbon)
                .fluidInputs(CarbonDioxide.getFluid(1000))
                .fluidOutputs(CarbonMonoxide.getFluid(2000))
                .EUt(VA[ULV].toLong())
                .duration(40 * SECOND)
                .buildAndRegister()
        }

    }

}