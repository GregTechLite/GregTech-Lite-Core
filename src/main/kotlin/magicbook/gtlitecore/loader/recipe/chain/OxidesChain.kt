package magicbook.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.ULV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.VH
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
import gregtech.api.unification.material.Materials.BandedIron
import gregtech.api.unification.material.Materials.Beryllium
import gregtech.api.unification.material.Materials.Bismuth
import gregtech.api.unification.material.Materials.Carbon
import gregtech.api.unification.material.Materials.CarbonDioxide
import gregtech.api.unification.material.Materials.CarbonMonoxide
import gregtech.api.unification.material.Materials.Chalcopyrite
import gregtech.api.unification.material.Materials.Charcoal
import gregtech.api.unification.material.Materials.Chlorine
import gregtech.api.unification.material.Materials.Chrome
import gregtech.api.unification.material.Materials.ChromiumTrioxide
import gregtech.api.unification.material.Materials.Coal
import gregtech.api.unification.material.Materials.CobaltOxide
import gregtech.api.unification.material.Materials.Cobaltite
import gregtech.api.unification.material.Materials.Copper
import gregtech.api.unification.material.Materials.CupricOxide
import gregtech.api.unification.material.Materials.Ferrosilite
import gregtech.api.unification.material.Materials.Galena
import gregtech.api.unification.material.Materials.Gallium
import gregtech.api.unification.material.Materials.Garnierite
import gregtech.api.unification.material.Materials.Hafnia
import gregtech.api.unification.material.Materials.Lanthanum
import gregtech.api.unification.material.Materials.Lithium
import gregtech.api.unification.material.Materials.Magnesia
import gregtech.api.unification.material.Materials.Magnesite
import gregtech.api.unification.material.Materials.Massicot
import gregtech.api.unification.material.Materials.Niobium
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Pentlandite
import gregtech.api.unification.material.Materials.Phosphorus
import gregtech.api.unification.material.Materials.PhosphorusPentoxide
import gregtech.api.unification.material.Materials.Pyrite
import gregtech.api.unification.material.Materials.Scandium
import gregtech.api.unification.material.Materials.Silicon
import gregtech.api.unification.material.Materials.SiliconDioxide
import gregtech.api.unification.material.Materials.Silver
import gregtech.api.unification.material.Materials.Sodium
import gregtech.api.unification.material.Materials.Sphalerite
import gregtech.api.unification.material.Materials.Stibnite
import gregtech.api.unification.material.Materials.Sulfur
import gregtech.api.unification.material.Materials.SulfurDioxide
import gregtech.api.unification.material.Materials.SulfurTrioxide
import gregtech.api.unification.material.Materials.Tantalum
import gregtech.api.unification.material.Materials.Tetrahedrite
import gregtech.api.unification.material.Materials.Yttrium
import gregtech.api.unification.material.Materials.Zincite
import gregtech.api.unification.material.Materials.Zircon
import gregtech.api.unification.material.Materials.Zirconia
import gregtech.api.unification.material.Materials.ZirconiumTetrachloride
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.dustTiny
import gregtech.api.unification.ore.OrePrefix.gem
import gregtech.api.unification.ore.OrePrefix.ingot
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.ROASTER_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Baddeleyite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BerylliumOxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BismuthTrioxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.GalliumTrioxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Iron3Sulfate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LanthanumOxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Lignite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LithiumOxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.NiobiumPentoxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ScandiumOxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SodiumOxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TantalumPentoxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Tenorite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.YttriumOxide
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
                .output(dust, Baddeleyite, 3)
                .output(dust, SiliconDioxide, 3)
                .chancedOutput(dust, Hafnia, 3333, 0)
                .EUt(VA[MV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            ROASTER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(dust, Zircon, 6)
                .fluidInputs(Oxygen.getFluid(2000))
                .output(dust, Baddeleyite, 3)
                .output(dust, Hafnia, 3)
                .output(dust, SiliconDioxide, 3)
                .EUt(VA[HV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // Hint: we removed Zirconia (ZrO2) and add an ore to simplified processing,
            // so we should remove all related recipes.
            GTRecipeUtility.removeChemicalRecipes(
                arrayOf(OreDictUnifier.get(dust, Zirconia, 3),
                    OreDictUnifier.get(dust, Carbon)),
                arrayOf(Chlorine.getFluid(4000)))

            CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Baddeleyite, 3)
                .input(dust, Carbon)
                .fluidInputs(Chlorine.getFluid(4000))
                .output(dust, ZirconiumTetrachloride, 5)
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .EUt(VA[HV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // ZnS -> ZnO
            GTRecipeHandler.removeRecipesByInputs(BLAST_RECIPES,
                arrayOf(OreDictUnifier.get(dust, Sphalerite)),
                arrayOf(Oxygen.getFluid(3000)))

            ROASTER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(dust, Sphalerite)
                .fluidInputs(Oxygen.getFluid(3000))
                .output(dust, Zincite)
                .chancedOutput(dust, Ash, 1000, 0)
                .fluidOutputs(SulfurDioxide.getFluid(1000))
                .EUt(VA[MV].toLong())
                .duration(6 * SECOND)
                .buildAndRegister()

            // Fe2(SO4)3 -> Fe2O3 + 3SO3
            // This is another choice in early game for provide raw material to H2SO4.
            ROASTER_RECIPES.recipeBuilder()
                .input(dust, Iron3Sulfate)
                .output(dust, BandedIron)
                .fluidOutputs(SulfurTrioxide.getFluid(3000))
                .EUt(VA[ULV].toLong())
                .duration(8 * SECOND)
                .buildAndRegister()

            // Ni9S8
            GTRecipeHandler.removeRecipesByInputs(BLAST_RECIPES,
                arrayOf(OreDictUnifier.get(dust, Pentlandite)),
                arrayOf(Oxygen.getFluid(3000)))

            ROASTER_RECIPES.recipeBuilder()
                .input(dust, Pentlandite)
                .fluidInputs(Oxygen.getFluid(3000))
                .output(dust, Garnierite)
                .chancedOutput(dust, Ash, 1000, 0)
                .fluidOutputs(SulfurDioxide.getFluid(1000))
                .EUt(VA[MV].toLong())
                .duration(6 * SECOND)
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

            // 2Li + O -> Li2O
            ROASTER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(dust, Lithium, 2)
                .fluidInputs(Oxygen.getFluid(1000))
                .output(dust, LithiumOxide, 3)
                .EUt(VH[LV].toLong())
                .duration(3 * SECOND)
                .buildAndRegister()

            // 2Nb + 5O -> Nb2O5
            ROASTER_RECIPES.recipeBuilder()
                .circuitMeta(5)
                .input(dust, Niobium, 2)
                .fluidInputs(Oxygen.getFluid(5000))
                .output(dust, NiobiumPentoxide, 7)
                .EUt(VA[MV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // 2Ta + 5O -> Ta2O5
            ROASTER_RECIPES.recipeBuilder()
                .circuitMeta(5)
                .input(dust, Tantalum, 2)
                .fluidInputs(Oxygen.getFluid(5000))
                .output(dust, TantalumPentoxide, 7)
                .EUt(VA[MV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // Be + O -> BeO
            ROASTER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(dust, Beryllium)
                .fluidInputs(Oxygen.getFluid(1000))
                .output(ingot, BerylliumOxide, 2)
                .EUt(VA[MV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // 2Bi + 3O -> Bi2O3
            ROASTER_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .input(dust, Bismuth, 2)
                .fluidInputs(Oxygen.getFluid(3000))
                .output(dust, BismuthTrioxide, 5)
                .EUt(VA[LV].toLong())
                .duration(4 * SECOND + 5 * TICK)
                .buildAndRegister()

            // Cu + O -> CuO
            ROASTER_RECIPES.recipeBuilder() // TODO Oxidification for other copper ores?
                .circuitMeta(1)
                .input(dust, Copper)
                .fluidInputs(Oxygen.getFluid(1000))
                .output(dust, Tenorite, 2)
                .EUt(VA[LV].toLong())
                .duration(15 * TICK)
                .buildAndRegister()

            // 2Na + O -> Na2O
            ROASTER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(dust, Sodium, 2)
                .fluidInputs(Oxygen.getFluid(1000))
                .output(dust, SodiumOxide, 3)
                .EUt(VA[LV].toLong())
                .duration(2 * SECOND)
                .buildAndRegister()

            // Add another choice of La2O3, do not push player used REE distillation.
            // 2La + 3O -> La2O3
            ROASTER_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .input(dust, Lanthanum, 2)
                .fluidInputs(Oxygen.getFluid(3000))
                .output(dust, LanthanumOxide, 5)
                .EUt(VA[MV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // Add another choice of Y2O3, do not push player used REE distillation.
            // 2Y + 3O -> Y2O3
            ROASTER_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .input(dust, Yttrium, 2)
                .fluidInputs(Oxygen.getFluid(3000))
                .output(dust, YttriumOxide, 5)
                .EUt(VA[MV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // Add another choice of Sc2O3, do not push player used REE distillation.
            // 2Sc + 3O -> Sc2O3
            ROASTER_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .input(dust, Scandium, 2)
                .fluidInputs(Oxygen.getFluid(3000))
                .output(dust, ScandiumOxide, 5)
                .EUt(VA[MV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // Add another choice of GaO3, do not push player used Naquadah processing.
            // Ga + 3O -> GaO3
            ROASTER_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .input(dust, Gallium)
                .fluidInputs(Oxygen.getFluid(3000))
                .output(dust, GalliumTrioxide, 4)
                .EUt(VA[MV].toLong())
                .duration(2 * SECOND + 10 * TICK)
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