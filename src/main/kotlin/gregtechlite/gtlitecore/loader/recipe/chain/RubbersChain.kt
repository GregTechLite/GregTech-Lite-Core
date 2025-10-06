package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.ULV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.ZPM
import gregtech.api.recipes.GTRecipeHandler
import gregtech.api.recipes.RecipeMaps.ALLOY_SMELTER_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.EXTRACTOR_RECIPES
import gregtech.api.recipes.RecipeMaps.LARGE_CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.MIXER_RECIPES
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Materials.AceticAcid
import gregtech.api.unification.material.Materials.AmmoniumChloride
import gregtech.api.unification.material.Materials.Benzene
import gregtech.api.unification.material.Materials.CalciumChloride
import gregtech.api.unification.material.Materials.Carbon
import gregtech.api.unification.material.Materials.CarbonMonoxide
import gregtech.api.unification.material.Materials.Chlorine
import gregtech.api.unification.material.Materials.DistilledWater
import gregtech.api.unification.material.Materials.Ethanol
import gregtech.api.unification.material.Materials.Fluorine
import gregtech.api.unification.material.Materials.HydrochloricAcid
import gregtech.api.unification.material.Materials.HydrofluoricAcid
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.Iron
import gregtech.api.unification.material.Materials.Magnesia
import gregtech.api.unification.material.Materials.NitricAcid
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Polydimethylsiloxane
import gregtech.api.unification.material.Materials.Propene
import gregtech.api.unification.material.Materials.RawRubber
import gregtech.api.unification.material.Materials.RawStyreneButadieneRubber
import gregtech.api.unification.material.Materials.Rubber
import gregtech.api.unification.material.Materials.SiliconeRubber
import gregtech.api.unification.material.Materials.Sodium
import gregtech.api.unification.material.Materials.SodiumBisulfate
import gregtech.api.unification.material.Materials.Steam
import gregtech.api.unification.material.Materials.StyreneButadieneRubber
import gregtech.api.unification.material.Materials.Sulfur
import gregtech.api.unification.material.Materials.SulfuricAcid
import gregtech.api.unification.material.Materials.TitaniumTetrachloride
import gregtech.api.unification.material.Materials.Toluene
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.material.Materials.Zincite
import gregtech.api.unification.ore.OrePrefix.bolt
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.foil
import gregtech.api.unification.ore.OrePrefix.gear
import gregtech.api.unification.ore.OrePrefix.ingot
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.ring
import gregtech.api.unification.ore.OrePrefix.stick
import gregtech.common.blocks.MetaBlocks
import gregtech.common.items.MetaItems.BLACKLIGHT
import gregtech.common.items.MetaItems.SHAPE_EXTRUDER_BOLT
import gregtech.common.items.MetaItems.SHAPE_EXTRUDER_FOIL
import gregtech.common.items.MetaItems.SHAPE_EXTRUDER_GEAR
import gregtech.common.items.MetaItems.SHAPE_EXTRUDER_INGOT
import gregtech.common.items.MetaItems.SHAPE_EXTRUDER_PLATE
import gregtech.common.items.MetaItems.SHAPE_EXTRUDER_RING
import gregtech.common.items.MetaItems.SHAPE_EXTRUDER_ROD
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.BURNER_REACTOR_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.COAGULATION_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CRYOGENIC_REACTOR_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.SAP_COLLECTOR_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.VULCANIZATION_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Butanediol
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Diaminotoluene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Dinitrotoluene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Fluorobenzene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Latex
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.OctafluoroPentanol
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Phosgene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PhosphonitrilicChlorideTrimer
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PhosphorylChloride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Polyisoprene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PolyphosphonitrileFluoroRubber
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Polytetrahydrofuran
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PolytetramethyleneGlycolRubber
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.RawPolyphosphonitrileFluoroRubber
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.RawPolytetramethyleneGlycolRubber
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SodiumFluoride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SodiumTrifluoroethanolate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Tetrahydrofuran
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TolueneDiisocyanate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TolueneTetramethylDiisocyanate
import gregtechlite.gtlitecore.api.MINUTE
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.SU
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeHandler
import net.minecraft.block.BlockLog

internal object RubbersChain
{

    // @formatter:off

    fun init()
    {
        rubberProcess()
        syntheticRubberProcess()
        advancedSyntheticRubberProcess()
    }

    private fun rubberProcess()
    {
        // Extraction latex from a rubber planks, used sap collector with water.
        SAP_COLLECTOR_RECIPES.recipeBuilder()
            .notConsumable(Water.getFluid(10))
            .fluidOutputs(Latex.getFluid(100))
            .EUt(VA[ULV])
            .duration(1 * SECOND)
            .blockStates("rubber", arrayListOf((MetaBlocks.RUBBER_LOG as? BlockLog)!!.defaultState))
            .buildAndRegister()

        // Coagulation processing of liquid latex.
        GTRecipeHandler.removeRecipesByInputs(EXTRACTOR_RECIPES,
            OreDictUnifier.get(dust, Latex))

        COAGULATION_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .notConsumable(stick, Iron)
            .fluidInputs(Latex.getFluid(1000))
            .output(dust, Latex)
            .duration(5 * SECOND)
            .buildAndRegister()

        COAGULATION_RECIPES.recipeBuilder()
            .notConsumable(stick, Iron)
            .notConsumable(dust, CalciumChloride)
            .fluidInputs(Latex.getFluid(1000))
            .output(dust, Latex)
            .duration(2 * SECOND + 5 * TICK)
            .buildAndRegister()

        COAGULATION_RECIPES.recipeBuilder()
            .notConsumable(stick, Iron)
            .notConsumable(SulfuricAcid.getFluid(1))
            .fluidInputs(Latex.getFluid(1000))
            .output(dust, Latex)
            .duration(1 * SECOND)
            .buildAndRegister()

        COAGULATION_RECIPES.recipeBuilder()
            .notConsumable(stick, Iron)
            .notConsumable(AceticAcid.getFluid(1))
            .fluidInputs(Latex.getFluid(1000))
            .output(dust, Latex)
            .duration(5 * TICK)
            .buildAndRegister()

        // Disabled vanilla Alloy Smelter recipes of Rubber ingot, only allowed used
        // vulcanization processing to get rubbers, same as this, chemical reactor is
        // also not allowed yet.
        GTRecipeHandler.removeRecipesByInputs(ALLOY_SMELTER_RECIPES,
            OreDictUnifier.get(dust, Sulfur),
            OreDictUnifier.get(dust, RawRubber, 3))
        GTLiteRecipeHandler.removeChemicalRecipes(arrayOf(
            OreDictUnifier.get(dust, RawRubber, 9),
            OreDictUnifier.get(dust, Sulfur)))

        // Used shape extruders to confirm recipes clearly.

        // Add rubber component recipes.
        for (catalyst in arrayOf(Zincite, Magnesia))
        {
            // Rubber Ingot
            VULCANIZATION_RECIPES.recipeBuilder()
                .notConsumable(dust, catalyst)
                .notConsumable(SHAPE_EXTRUDER_INGOT)
                .input(dust, Latex, 4)
                .input(dust, Sulfur)
                .output(ingot, Rubber, 4)
                .EUt(VA[ULV])
                .duration(20 * SECOND)
                .buildAndRegister()

            VULCANIZATION_RECIPES.recipeBuilder()
                .notConsumable(dust, catalyst)
                .notConsumable(SHAPE_EXTRUDER_INGOT)
                .input(dust, Polyisoprene, 2)
                .input(dust, Sulfur)
                .output(ingot, Rubber, 8)
                .EUt(VA[ULV])
                .duration(10 * SECOND)
                .buildAndRegister()

            // Rubber Plate
            VULCANIZATION_RECIPES.recipeBuilder()
                .notConsumable(dust, catalyst)
                .notConsumable(SHAPE_EXTRUDER_PLATE)
                .input(dust, Latex, 4)
                .input(dust, Sulfur)
                .output(plate, Rubber, 4)
                .EUt(VA[ULV])
                .duration(20 * SECOND)
                .buildAndRegister()

            VULCANIZATION_RECIPES.recipeBuilder()
                .notConsumable(dust, catalyst)
                .notConsumable(SHAPE_EXTRUDER_PLATE)
                .input(dust, Polyisoprene, 2)
                .input(dust, Sulfur)
                .output(plate, Rubber, 8)
                .EUt(VA[ULV])
                .duration(10 * SECOND)
                .buildAndRegister()

            // Rubber Rod
            VULCANIZATION_RECIPES.recipeBuilder()
                .notConsumable(dust, catalyst)
                .notConsumable(SHAPE_EXTRUDER_ROD)
                .input(dust, Latex, 4)
                .input(dust, Sulfur)
                .output(stick, Rubber, 8)
                .EUt(VA[ULV])
                .duration(20 * SECOND)
                .buildAndRegister()

            VULCANIZATION_RECIPES.recipeBuilder()
                .notConsumable(dust, catalyst)
                .notConsumable(SHAPE_EXTRUDER_ROD)
                .input(dust, Polyisoprene, 2)
                .input(dust, Sulfur)
                .output(stick, Rubber, 16)
                .EUt(VA[ULV])
                .duration(10 * SECOND)
                .buildAndRegister()

            // Rubber Ring
            VULCANIZATION_RECIPES.recipeBuilder()
                .notConsumable(dust, catalyst)
                .notConsumable(SHAPE_EXTRUDER_RING)
                .input(dust, Latex, 4)
                .input(dust, Sulfur)
                .output(ring, Rubber, 16)
                .EUt(VA[ULV])
                .duration(20 * SECOND)
                .buildAndRegister()

            VULCANIZATION_RECIPES.recipeBuilder()
                .notConsumable(dust, catalyst)
                .notConsumable(SHAPE_EXTRUDER_RING)
                .input(dust, Polyisoprene, 2)
                .input(dust, Sulfur)
                .output(ring, Rubber, 32)
                .EUt(VA[ULV])
                .duration(10 * SECOND)
                .buildAndRegister()

            // Rubber Foil
            VULCANIZATION_RECIPES.recipeBuilder()
                .notConsumable(dust, catalyst)
                .notConsumable(SHAPE_EXTRUDER_FOIL)
                .input(dust, Latex, 4)
                .input(dust, Sulfur)
                .output(foil, Rubber, 16)
                .EUt(VA[ULV])
                .duration(20 * SECOND)
                .buildAndRegister()

            VULCANIZATION_RECIPES.recipeBuilder()
                .notConsumable(dust, catalyst)
                .notConsumable(SHAPE_EXTRUDER_FOIL)
                .input(dust, Polyisoprene, 2)
                .input(dust, Sulfur)
                .output(foil, Rubber, 32)
                .EUt(VA[ULV])
                .duration(10 * SECOND)
                .buildAndRegister()

            // Rubber Bolt
            VULCANIZATION_RECIPES.recipeBuilder()
                .notConsumable(dust, catalyst)
                .notConsumable(SHAPE_EXTRUDER_BOLT)
                .input(dust, Latex, 4)
                .input(dust, Sulfur)
                .output(bolt, Rubber, 32)
                .EUt(VA[ULV])
                .duration(20 * SECOND)
                .buildAndRegister()

            VULCANIZATION_RECIPES.recipeBuilder()
                .notConsumable(dust, catalyst)
                .notConsumable(SHAPE_EXTRUDER_BOLT)
                .input(dust, Polyisoprene, 2)
                .input(dust, Sulfur)
                .output(bolt, Rubber, 64)
                .EUt(VA[ULV])
                .duration(10 * SECOND)
                .buildAndRegister()

            // Rubber Gear
            VULCANIZATION_RECIPES.recipeBuilder()
                .notConsumable(dust, catalyst)
                .notConsumable(SHAPE_EXTRUDER_GEAR)
                .input(dust, Latex, 4)
                .input(dust, Sulfur)
                .output(gear, Rubber, 4)
                .EUt(VA[ULV])
                .duration(20 * SECOND)
                .buildAndRegister()

            VULCANIZATION_RECIPES.recipeBuilder()
                .notConsumable(dust, catalyst)
                .notConsumable(SHAPE_EXTRUDER_GEAR)
                .input(dust, Polyisoprene, 2)
                .input(dust, Sulfur)
                .output(gear, Rubber, 8)
                .EUt(VA[ULV])
                .duration(10 * SECOND)
                .buildAndRegister()
        }

    }

    private fun syntheticRubberProcess()
    {
        // Deleted raw styrene butadiene rubber to styrene butadiene rubber chemistry reaction recipes.
        GTLiteRecipeHandler.removeChemicalRecipes(
            arrayOf(OreDictUnifier.get(dust, RawStyreneButadieneRubber, 9),
                OreDictUnifier.get(dust, Sulfur)))

        // Add styrene butadiene rubber component recipes.
        for (catalyst in arrayOf(Zincite, Magnesia))
        {
            // Styrene Butadiene Rubber Ingot
            VULCANIZATION_RECIPES.recipeBuilder()
                .notConsumable(dust, catalyst)
                .notConsumable(SHAPE_EXTRUDER_INGOT)
                .input(dust, RawStyreneButadieneRubber, 4)
                .input(dust, Sulfur)
                .output(ingot, StyreneButadieneRubber, 4)
                .EUt(VA[LV])
                .duration(30 * SECOND)
                .buildAndRegister()

            // Styrene Butadiene Rubber Plate
            VULCANIZATION_RECIPES.recipeBuilder()
                .notConsumable(dust, catalyst)
                .notConsumable(SHAPE_EXTRUDER_PLATE)
                .input(dust, RawStyreneButadieneRubber, 4)
                .input(dust, Sulfur)
                .output(plate, StyreneButadieneRubber, 4)
                .EUt(VA[LV])
                .duration(30 * SECOND)
                .buildAndRegister()

            // Styrene Butadiene Rubber Rod
            VULCANIZATION_RECIPES.recipeBuilder()
                .notConsumable(dust, catalyst)
                .notConsumable(SHAPE_EXTRUDER_ROD)
                .input(dust, RawStyreneButadieneRubber, 4)
                .input(dust, Sulfur)
                .output(stick, StyreneButadieneRubber, 8)
                .EUt(VA[LV])
                .duration(30 * SECOND)
                .buildAndRegister()

            // Styrene Butadiene Rubber Ring
            VULCANIZATION_RECIPES.recipeBuilder()
                .notConsumable(dust, catalyst)
                .notConsumable(SHAPE_EXTRUDER_RING)
                .input(dust, RawStyreneButadieneRubber, 4)
                .input(dust, Sulfur)
                .output(ring, StyreneButadieneRubber, 16)
                .EUt(VA[LV])
                .duration(30 * SECOND)
                .buildAndRegister()

            // Styrene Butadiene Rubber Foil
            VULCANIZATION_RECIPES.recipeBuilder()
                .notConsumable(dust, catalyst)
                .notConsumable(SHAPE_EXTRUDER_FOIL)
                .input(dust, RawStyreneButadieneRubber, 4)
                .input(dust, Sulfur)
                .output(foil, StyreneButadieneRubber, 16)
                .EUt(VA[LV])
                .duration(30 * SECOND)
                .buildAndRegister()
        }

        // Deleted raw silicone rubber (polydimethylsiloxane) to silicone rubber chemistry reaction recipes.
        GTLiteRecipeHandler.removeChemicalRecipes(
            arrayOf(OreDictUnifier.get(dust, Polydimethylsiloxane, 9),
                OreDictUnifier.get(dust, Sulfur)))

        // Add silicone rubber component recipes.
        for (catalyst in arrayOf(Zincite, Magnesia))
        {
            // Silicone Rubber Ingot
            VULCANIZATION_RECIPES.recipeBuilder()
                .notConsumable(dust, catalyst)
                .notConsumable(SHAPE_EXTRUDER_INGOT)
                .input(dust, Polydimethylsiloxane, 4)
                .input(dust, Sulfur)
                .output(ingot, SiliconeRubber, 4)
                .EUt(VA[LV])
                .duration(30 * SECOND)
                .buildAndRegister()

            // Silicone Rubber Plate
            VULCANIZATION_RECIPES.recipeBuilder()
                .notConsumable(dust, catalyst)
                .notConsumable(SHAPE_EXTRUDER_PLATE)
                .input(dust, Polydimethylsiloxane, 4)
                .input(dust, Sulfur)
                .output(plate, SiliconeRubber, 4)
                .EUt(VA[LV])
                .duration(30 * SECOND)
                .buildAndRegister()

            // Silicone Rubber Rod
            VULCANIZATION_RECIPES.recipeBuilder()
                .notConsumable(dust, catalyst)
                .notConsumable(SHAPE_EXTRUDER_ROD)
                .input(dust, Polydimethylsiloxane, 4)
                .input(dust, Sulfur)
                .output(stick, SiliconeRubber, 8)
                .EUt(VA[LV])
                .duration(30 * SECOND)
                .buildAndRegister()

            // Silicone Rubber Ring
            VULCANIZATION_RECIPES.recipeBuilder()
                .notConsumable(dust, catalyst)
                .notConsumable(SHAPE_EXTRUDER_RING)
                .input(dust, Polydimethylsiloxane, 4)
                .input(dust, Sulfur)
                .output(ring, SiliconeRubber, 16)
                .EUt(VA[LV])
                .duration(30 * SECOND)
                .buildAndRegister()

            // Silicone Rubber Foil
            VULCANIZATION_RECIPES.recipeBuilder()
                .notConsumable(dust, catalyst)
                .notConsumable(SHAPE_EXTRUDER_FOIL)
                .input(dust, Polydimethylsiloxane, 4)
                .input(dust, Sulfur)
                .output(foil, SiliconeRubber, 16)
                .EUt(VA[LV])
                .duration(30 * SECOND)
                .buildAndRegister()

            // Silicone Rubber Gear
            VULCANIZATION_RECIPES.recipeBuilder()
                .notConsumable(dust, catalyst)
                .notConsumable(SHAPE_EXTRUDER_GEAR)
                .input(dust, Polydimethylsiloxane, 4)
                .input(dust, Sulfur)
                .output(gear, SiliconeRubber, 4)
                .EUt(VA[LV])
                .duration(30 * SECOND)
                .buildAndRegister()
        }

    }

    private fun advancedSyntheticRubberProcess()
    {
        // PPF Rubber chain.

        // 6C + 3POCl3 + 3NH4Cl -> Cl6N3P3 + 6HCl + 3H2O
        BURNER_REACTOR_RECIPES.recipeBuilder()
            .input(dust, Carbon, 6)
            .input(dust, AmmoniumChloride, 6) // Special chemistry which with x2 as 1x amount, not 6 as 1x amount.
            .fluidInputs(PhosphorylChloride.getFluid(3000))
            .fluidOutputs(PhosphonitrilicChlorideTrimer.getFluid(1000))
            .fluidOutputs(HydrochloricAcid.getFluid(6000))
            .fluidOutputs(Steam.getFluid(3 * SU))
            .EUt(VA[EV])
            .duration(4 * SECOND)
            .buildAndRegister()

        // Na + F -> NaF
        CHEMICAL_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(dust, Sodium)
            .fluidInputs(Fluorine.getFluid(1000))
            .output(dust, SodiumFluoride, 2)
            .EUt(VA[LV])
            .duration(4 * SECOND)
            .buildAndRegister()

        // NaF + C2H6O + 2F -> NaC2H4OF3 + 2H
        CRYOGENIC_REACTOR_RECIPES.recipeBuilder()
            .input(dust, SodiumFluoride, 2)
            .fluidInputs(Ethanol.getFluid(1000))
            .fluidInputs(Fluorine.getFluid(2000))
            .output(dust, SodiumTrifluoroethanolate, 11)
            .fluidOutputs(Hydrogen.getFluid(2000))
            .EUt(VA[EV])
            .duration(8 * SECOND)
            .buildAndRegister()

        // C6H6 + 2F -> C6H5F + HF
        CHEMICAL_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .fluidInputs(Benzene.getFluid(1000))
            .fluidInputs(Fluorine.getFluid(2000))
            .fluidOutputs(Fluorobenzene.getFluid(1000))
            .fluidOutputs(HydrofluoricAcid.getFluid(1000))
            .EUt(VA[HV])
            .duration(3 * SECOND)
            .buildAndRegister()

        // 2C6H5F + 6HF + O -> C5H4F8O + 2C3H6
        CHEMICAL_RECIPES.recipeBuilder()
            .notConsumable(BLACKLIGHT)
            .fluidInputs(Fluorobenzene.getFluid(2000))
            .fluidInputs(HydrofluoricAcid.getFluid(6000))
            .fluidInputs(Oxygen.getFluid(1000))
            .fluidOutputs(OctafluoroPentanol.getFluid(1000))
            .fluidOutputs(Propene.getFluid(2000))
            .EUt(VA[ZPM])
            .duration(15 * SECOND)
            .buildAndRegister()

        // NaC2H4OF3 + Cl6N3P3 + 4C5H4F8O -> (CH2CF3)6(CH2C3F7)2(C2F4)2(NPO)4O4 + NaF (cycle) + 3POCl3 (cycle)
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, SodiumTrifluoroethanolate, 11)
            .fluidInputs(PhosphonitrilicChlorideTrimer.getFluid(1000))
            .fluidInputs(OctafluoroPentanol.getFluid(4000))
            .output(dust, RawPolyphosphonitrileFluoroRubber, 64)
            .output(dust, RawPolyphosphonitrileFluoroRubber, 32)
            .output(dust, SodiumFluoride, 2)
            .fluidOutputs(PhosphorylChloride.getFluid(3000))
            .EUt(VA[UV])
            .duration(20 * SECOND)
            .buildAndRegister()

        // Add polyphosphonitrile fluoro rubber component recipes.
        for (catalyst in arrayOf(Zincite, Magnesia))
        {
            // Polyphosphonitrile Fluoro Rubber Ingot
            VULCANIZATION_RECIPES.recipeBuilder()
                .notConsumable(dust, catalyst)
                .notConsumable(SHAPE_EXTRUDER_INGOT)
                .input(dust, RawPolyphosphonitrileFluoroRubber, 4)
                .input(dust, Sulfur)
                .output(ingot, PolyphosphonitrileFluoroRubber, 4)
                .EUt(VA[HV])
                .duration(40 * SECOND)
                .buildAndRegister()

            // Polyphosphonitrile Fluoro Rubber Plate
            VULCANIZATION_RECIPES.recipeBuilder()
                .notConsumable(dust, catalyst)
                .notConsumable(SHAPE_EXTRUDER_PLATE)
                .input(dust, RawPolyphosphonitrileFluoroRubber, 4)
                .input(dust, Sulfur)
                .output(plate, PolyphosphonitrileFluoroRubber, 4)
                .EUt(VA[HV])
                .duration(40 * SECOND)
                .buildAndRegister()

            // Polyphosphonitrile Fluoro Rubber Rod
            VULCANIZATION_RECIPES.recipeBuilder()
                .notConsumable(dust, catalyst)
                .notConsumable(SHAPE_EXTRUDER_ROD)
                .input(dust, RawPolyphosphonitrileFluoroRubber, 4)
                .input(dust, Sulfur)
                .output(stick, PolyphosphonitrileFluoroRubber, 8)
                .EUt(VA[HV])
                .duration(40 * SECOND)
                .buildAndRegister()

            // Polyphosphonitrile Fluoro Rubber Ring
            VULCANIZATION_RECIPES.recipeBuilder()
                .notConsumable(dust, catalyst)
                .notConsumable(SHAPE_EXTRUDER_RING)
                .input(dust, RawPolyphosphonitrileFluoroRubber, 4)
                .input(dust, Sulfur)
                .output(ring, PolyphosphonitrileFluoroRubber, 16)
                .EUt(VA[HV])
                .duration(40 * SECOND)
                .buildAndRegister()

            // Polyphosphonitrile Fluoro Rubber Foil
            VULCANIZATION_RECIPES.recipeBuilder()
                .notConsumable(dust, catalyst)
                .notConsumable(SHAPE_EXTRUDER_FOIL)
                .input(dust, RawPolyphosphonitrileFluoroRubber, 4)
                .input(dust, Sulfur)
                .output(foil, PolyphosphonitrileFluoroRubber, 16)
                .EUt(VA[HV])
                .duration(40 * SECOND)
                .buildAndRegister()
        }

        // PTMEG rubber chain.

        // C7H8 + 2HNO3 -> C7H6N2O4
        CHEMICAL_RECIPES.recipeBuilder()
            .fluidInputs(Toluene.getFluid(1000))
            .fluidInputs(NitricAcid.getFluid(2000))
            .fluidOutputs(Dinitrotoluene.getFluid(1000))
            .EUt(VA[HV])
            .duration(25 * SECOND)
            .buildAndRegister()

        // C7H6N2O4 + 4H -> C6H3(NH2)2CH3
        MIXER_RECIPES.recipeBuilder()
            .fluidInputs(Dinitrotoluene.getFluid(1000))
            .fluidInputs(Hydrogen.getFluid(4000))
            .fluidOutputs(Diaminotoluene.getFluid(1000))
            .EUt(VA[EV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // CO + 2Cl -> COCl2
        MIXER_RECIPES.recipeBuilder()
            .fluidInputs(CarbonMonoxide.getFluid(1000))
            .fluidInputs(Chlorine.getFluid(2000))
            .fluidOutputs(Phosgene.getFluid(1000))
            .EUt(VA[HV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()

        // C6H3(NH2)2CH3 + 2COCl2 -> CH3C6H3(NCO)2
        BURNER_REACTOR_RECIPES.recipeBuilder()
            .fluidInputs(Diaminotoluene.getFluid(1000))
            .fluidInputs(Phosgene.getFluid(2000))
            .fluidOutputs(TolueneDiisocyanate.getFluid(1000))
            .fluidOutputs(HydrochloricAcid.getFluid(4000))
            .EUt(VA[HV])
            .duration(45 * SECOND)
            .buildAndRegister()

        // (CH2)4O + H2O -> (C4H8O)OH2
        CHEMICAL_RECIPES.recipeBuilder()
            .notConsumable(dust, SodiumBisulfate) // as initiator.
            .fluidInputs(Tetrahydrofuran.getFluid(L))
            .fluidInputs(Water.getFluid(1000))
            .fluidOutputs(Polytetrahydrofuran.getFluid(L))
            .EUt(VA[MV])
            .duration(16 * SECOND)
            .buildAndRegister()

        CHEMICAL_RECIPES.recipeBuilder()
            .notConsumable(dust, SodiumBisulfate) // as initiator.
            .fluidInputs(Tetrahydrofuran.getFluid(L))
            .fluidInputs(DistilledWater.getFluid(1000))
            .fluidOutputs(Polytetrahydrofuran.getFluid(216)) // 1.5L
            .EUt(VA[MV])
            .duration(16 * SECOND)
            .buildAndRegister()

        LARGE_CHEMICAL_RECIPES.recipeBuilder()
            .circuitMeta(24)
            .notConsumable(dust, SodiumBisulfate)
            .fluidInputs(Tetrahydrofuran.getFluid(2160))
            .fluidInputs(Water.getFluid(7500))
            .fluidInputs(TitaniumTetrachloride.getFluid(100))
            .fluidOutputs(Polytetrahydrofuran.getFluid(3240))
            .EUt(VA[MV])
            .duration(1 * MINUTE + 20 * SECOND)
            .buildAndRegister()

        LARGE_CHEMICAL_RECIPES.recipeBuilder()
            .circuitMeta(24)
            .notConsumable(dust, SodiumBisulfate)
            .fluidInputs(Tetrahydrofuran.getFluid(2160))
            .fluidInputs(DistilledWater.getFluid(7500))
            .fluidInputs(TitaniumTetrachloride.getFluid(100))
            .fluidOutputs(Polytetrahydrofuran.getFluid(4320))
            .EUt(VA[MV])
            .duration(1 * MINUTE + 20 * SECOND)
            .buildAndRegister()

        // (C4H8O)OH2 + 3CH3C6H3(NCO)2 + 2H -> (CONH)2(C6H4)2CH2(C4O)
        CHEMICAL_RECIPES.recipeBuilder()
            .fluidInputs(Polytetrahydrofuran.getFluid(1000))
            .fluidInputs(TolueneDiisocyanate.getFluid(3000))
            .fluidInputs(Hydrogen.getFluid(2000))
            .fluidOutputs(TolueneTetramethylDiisocyanate.getFluid(2000))
            .EUt(VA[IV])
            .duration(1 * MINUTE)
            .buildAndRegister()

        // (CONH)2(C6H4)2CH2(C4O) + C4H8(OH)2 -> (CONH)2(C6H4)2CH2(C4O)HO(CH2)4OH
        CHEMICAL_RECIPES.recipeBuilder()
            .fluidInputs(TolueneTetramethylDiisocyanate.getFluid(4000))
            .fluidInputs(Butanediol.getFluid(1000))
            .output(dust, RawPolytetramethyleneGlycolRubber, 53)
            .EUt(VA[UV])
            .duration(30 * SECOND)
            .buildAndRegister()

        // Add polytetramethylene glycol rubber component recipes.
        for (catalyst in arrayOf(Zincite, Magnesia))
        {
            // Polytetramethylene Glycol Rubber Ingot
            VULCANIZATION_RECIPES.recipeBuilder()
                .notConsumable(dust, catalyst)
                .notConsumable(SHAPE_EXTRUDER_INGOT)
                .input(dust, RawPolytetramethyleneGlycolRubber, 4)
                .input(dust, Sulfur)
                .output(ingot, PolytetramethyleneGlycolRubber, 4)
                .EUt(VA[HV])
                .duration(40 * SECOND)
                .buildAndRegister()

            // Polytetramethylene Glycol Rubber Plate
            VULCANIZATION_RECIPES.recipeBuilder()
                .notConsumable(dust, catalyst)
                .notConsumable(SHAPE_EXTRUDER_PLATE)
                .input(dust, RawPolytetramethyleneGlycolRubber, 4)
                .input(dust, Sulfur)
                .output(plate, PolytetramethyleneGlycolRubber, 4)
                .EUt(VA[HV])
                .duration(40 * SECOND)
                .buildAndRegister()

            // Polytetramethylene Glycol Rubber Rod
            VULCANIZATION_RECIPES.recipeBuilder()
                .notConsumable(dust, catalyst)
                .notConsumable(SHAPE_EXTRUDER_ROD)
                .input(dust, RawPolytetramethyleneGlycolRubber, 4)
                .input(dust, Sulfur)
                .output(stick, PolytetramethyleneGlycolRubber, 8)
                .EUt(VA[HV])
                .duration(40 * SECOND)
                .buildAndRegister()

            // Polytetramethylene Glycol Rubber Ring
            VULCANIZATION_RECIPES.recipeBuilder()
                .notConsumable(dust, catalyst)
                .notConsumable(SHAPE_EXTRUDER_RING)
                .input(dust, RawPolytetramethyleneGlycolRubber, 4)
                .input(dust, Sulfur)
                .output(ring, PolytetramethyleneGlycolRubber, 16)
                .EUt(VA[HV])
                .duration(40 * SECOND)
                .buildAndRegister()

            // Polytetramethylene Glycol Rubber Foil
            VULCANIZATION_RECIPES.recipeBuilder()
                .notConsumable(dust, catalyst)
                .notConsumable(SHAPE_EXTRUDER_FOIL)
                .input(dust, RawPolytetramethyleneGlycolRubber, 4)
                .input(dust, Sulfur)
                .output(foil, PolytetramethyleneGlycolRubber, 16)
                .EUt(VA[HV])
                .duration(40 * SECOND)
                .buildAndRegister()

        }

    }

    // @formatter:on

}