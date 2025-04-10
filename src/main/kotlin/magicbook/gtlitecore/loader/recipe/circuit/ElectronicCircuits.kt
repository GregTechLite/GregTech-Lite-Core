package magicbook.gtlitecore.loader.recipe.circuit

import gregtech.api.GTValues
import gregtech.api.GTValues.L
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.ULV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.VH
import gregtech.api.recipes.GTRecipeHandler
import gregtech.api.recipes.ModHandler
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.CIRCUIT_ASSEMBLER_RECIPES
import gregtech.api.recipes.RecipeMaps.EXTRACTOR_RECIPES
import gregtech.api.recipes.ingredients.IntCircuitIngredient
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.MarkerMaterials
import gregtech.api.unification.material.Materials.AceticAcid
import gregtech.api.unification.material.Materials.AnnealedCopper
import gregtech.api.unification.material.Materials.CalciumChloride
import gregtech.api.unification.material.Materials.Copper
import gregtech.api.unification.material.Materials.Cupronickel
import gregtech.api.unification.material.Materials.Epoxy
import gregtech.api.unification.material.Materials.Glue
import gregtech.api.unification.material.Materials.Iron
import gregtech.api.unification.material.Materials.Molybdenum
import gregtech.api.unification.material.Materials.Nickel
import gregtech.api.unification.material.Materials.Quicklime
import gregtech.api.unification.material.Materials.RedAlloy
import gregtech.api.unification.material.Materials.ReinforcedEpoxyResin
import gregtech.api.unification.material.Materials.Silver
import gregtech.api.unification.material.Materials.SolderingAlloy
import gregtech.api.unification.material.Materials.Steel
import gregtech.api.unification.material.Materials.SulfuricAcid
import gregtech.api.unification.material.Materials.Tin
import gregtech.api.unification.material.Materials.Wood
import gregtech.api.unification.ore.OrePrefix.bolt
import gregtech.api.unification.ore.OrePrefix.cableGtSingle
import gregtech.api.unification.ore.OrePrefix.circuit
import gregtech.api.unification.ore.OrePrefix.component
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.dustSmall
import gregtech.api.unification.ore.OrePrefix.foil
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.ring
import gregtech.api.unification.ore.OrePrefix.stick
import gregtech.api.unification.ore.OrePrefix.wireFine
import gregtech.api.unification.ore.OrePrefix.wireGtSingle
import gregtech.api.unification.stack.UnificationEntry
import gregtech.common.items.MetaItems.BASIC_CIRCUIT_BOARD
import gregtech.common.items.MetaItems.DIODE
import gregtech.common.items.MetaItems.ELECTRONIC_CIRCUIT_LV
import gregtech.common.items.MetaItems.ELECTRONIC_CIRCUIT_MV
import gregtech.common.items.MetaItems.GLASS_TUBE
import gregtech.common.items.MetaItems.GOOD_CIRCUIT_BOARD
import gregtech.common.items.MetaItems.PHENOLIC_BOARD
import gregtech.common.items.MetaItems.RESISTOR
import gregtech.common.items.MetaItems.VACUUM_TUBE
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.COAGULATION_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.VACUUM_CHAMBER_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.EthylenediaminePyrocatechol
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Kovar
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Resin
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TetramethylammoniumHydroxide
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.VACUUM_TUBE_COMPONENT

@Suppress("MISSING_DEPENDENCY_CLASS")
class ElectronicCircuits
{

    companion object
    {

        fun init()
        {
            circuitBoardRecipes()
            vacuumTubeRecipes()
            circuitRecipes()
        }

        private fun circuitBoardRecipes()
        {
            // Deleted vanilla recipes of Phenolic board.
            GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
                arrayOf(IntCircuitIngredient.getIntegratedCircuit(1),
                        OreDictUnifier.get(dust, Wood)),
                arrayOf(Glue.getFluid(50)))

            GTRecipeHandler.removeRecipesByInputs(EXTRACTOR_RECIPES,
                OreDictUnifier.get(dust, Resin))

            // Coagulate resin liquid to dust.
            COAGULATION_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .notConsumable(stick, Iron)
                .fluidInputs(Resin.getFluid(1000))
                .output(dust, Resin)
                .duration(5 * SECOND)
                .buildAndRegister()

            COAGULATION_RECIPES.recipeBuilder()
                .notConsumable(stick, Iron)
                .notConsumable(dust, CalciumChloride)
                .fluidInputs(Resin.getFluid(1000))
                .output(dust, Resin)
                .duration(2 * SECOND + 5 * TICK)
                .buildAndRegister()

            COAGULATION_RECIPES.recipeBuilder()
                .notConsumable(stick, Iron)
                .notConsumable(SulfuricAcid.getFluid(1))
                .fluidInputs(Resin.getFluid(1000))
                .output(dust, Resin)
                .duration(1 * SECOND)
                .buildAndRegister()

            COAGULATION_RECIPES.recipeBuilder()
                .notConsumable(stick, Iron)
                .notConsumable(AceticAcid.getFluid(1))
                .fluidInputs(Resin.getFluid(1000))
                .output(dust, Resin)
                .duration(5 * TICK)
                .buildAndRegister()

            // New phenolic board recipes.
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(dust, Resin)
                .fluidInputs(Glue.getFluid(50))
                .output(PHENOLIC_BOARD)
                .EUt(VA[LV].toLong())
                .duration(7 * SECOND + 10 * TICK)
                .buildAndRegister()

            // Add advanced recipes of phenolic board.
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(dust, Epoxy)
                .fluidInputs(Glue.getFluid(50))
                .output(PHENOLIC_BOARD, 8)
                .EUt(VA[LV].toLong())
                .duration(7 * SECOND + 10 * TICK)
                .buildAndRegister()

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(dust, ReinforcedEpoxyResin)
                .fluidInputs(Glue.getFluid(50))
                .output(PHENOLIC_BOARD, 16)
                .EUt(VA[LV].toLong())
                .duration(7 * SECOND + 10 * TICK)
                .buildAndRegister()

            // Advanced recipes of basic circuit board.
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(foil, AnnealedCopper, 4)
                .input(plate, Wood)
                .fluidInputs(Glue.getFluid(100))
                .output(BASIC_CIRCUIT_BOARD, 4)
                .EUt(7) // ULV
                .duration(10 * SECOND)
                .buildAndRegister()

            ASSEMBLER_RECIPES.recipeBuilder()
                .input(foil, Cupronickel, 4)
                .input(plate, Wood)
                .fluidInputs(Glue.getFluid(100))
                .output(BASIC_CIRCUIT_BOARD, 16)
                .EUt(7) // ULV
                .duration(10 * SECOND)
                .buildAndRegister()

            // Advanced etching liquids recipe addition.
            for (etchingLiquid in arrayOf(
                TetramethylammoniumHydroxide.getFluid(50),
                EthylenediaminePyrocatechol.getFluid(25)))
            {
                CHEMICAL_RECIPES.recipeBuilder()
                    .input(foil, Silver, 4)
                    .input(PHENOLIC_BOARD)
                    .fluidInputs(etchingLiquid)
                    .output(GOOD_CIRCUIT_BOARD)
                    .EUt(VA[LV].toLong())
                    .duration(15 * SECOND)
                    .buildAndRegister()
            }

        }

        private fun vacuumTubeRecipes()
        {
            // Vacuum Tube Component
            ModHandler.addShapedRecipe(true, "vacuum_tube_component", VACUUM_TUBE_COMPONENT.getStackForm(4),
                "xW ", "PRF", " Bw",
                'W', UnificationEntry(wireFine, Cupronickel),
                'P', UnificationEntry(plate, Nickel),
                'R', UnificationEntry(ring, Steel),
                'F', UnificationEntry(foil, Nickel),
                'B', UnificationEntry(bolt, Steel))

            ModHandler.addShapedRecipe(true, "vacuum_tube_component_oxide_1", VACUUM_TUBE_COMPONENT.getStackForm(6),
                "xWD", "PRF", " Bw",
                'W', UnificationEntry(wireFine, Cupronickel),
                'P', UnificationEntry(plate, Nickel),
                'R', UnificationEntry(ring, Steel),
                'F', UnificationEntry(foil, Nickel),
                'B', UnificationEntry(bolt, Steel),
                'D', UnificationEntry(dustSmall, Quicklime))

            ModHandler.addShapedRecipe(true, "vacuum_tube_component_oxide_2", VACUUM_TUBE_COMPONENT.getStackForm(6),
                "xW ", "PRF", "DBw",
                'W', UnificationEntry(wireFine, Cupronickel),
                'P', UnificationEntry(plate, Nickel),
                'R', UnificationEntry(ring, Steel),
                'F', UnificationEntry(foil, Nickel),
                'B', UnificationEntry(bolt, Steel),
                'D', UnificationEntry(dustSmall, Quicklime))

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(wireFine, Cupronickel)
                .input(foil, Nickel, 2)
                .input(bolt, Steel, 4)
                .output(VACUUM_TUBE_COMPONENT, 6)
                .EUt(VA[LV].toLong())
                .duration(20 * SECOND)
                .buildAndRegister()

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(wireFine, Cupronickel)
                .input(foil, Molybdenum, 2)
                .input(bolt, Steel, 4)
                .output(VACUUM_TUBE_COMPONENT, 12)
                .EUt(VA[LV].toLong())
                .duration(20 * SECOND)
                .buildAndRegister()

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(wireFine, Cupronickel)
                .input(foil, Nickel, 2)
                .input(bolt, Steel, 4)
                .input(dustSmall, Quicklime)
                .output(VACUUM_TUBE_COMPONENT, 8)
                .EUt(VA[LV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(wireFine, Cupronickel)
                .input(foil, Molybdenum, 2)
                .input(bolt, Steel, 4)
                .input(dustSmall, Quicklime)
                .output(VACUUM_TUBE_COMPONENT, 16)
                .EUt(VA[LV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Vacuum Tube
            ModHandler.removeRecipeByName("${GTValues.MODID}:vacuum_tube")
            GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
                GLASS_TUBE.stackForm,
                OreDictUnifier.get(bolt, Steel, 2),
                OreDictUnifier.get(wireGtSingle, Copper, 2),
                IntCircuitIngredient.getIntegratedCircuit(1))

            GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
                arrayOf(GLASS_TUBE.stackForm,
                        OreDictUnifier.get(bolt, Steel, 2),
                        OreDictUnifier.get(wireGtSingle, Copper, 2)),
                arrayOf(RedAlloy.getFluid(L / 8)))

            GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
                arrayOf(GLASS_TUBE.stackForm,
                        OreDictUnifier.get(bolt, Steel, 2),
                        OreDictUnifier.get(wireGtSingle, AnnealedCopper, 2)),
                arrayOf(RedAlloy.getFluid(L / 8)))

            // Vacuum Tube
            VACUUM_CHAMBER_RECIPES.recipeBuilder()
                .input(GLASS_TUBE)
                .input(VACUUM_TUBE_COMPONENT)
                .input(ring, Kovar)
                .output(VACUUM_TUBE, 2)
                .EUt(VA[ULV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

        }

        private fun circuitRecipes()
        {
            // Deleted original recipes for Electronic Circuit LV (player do not
            // need steel plate to make it yet and x2 product).
            ModHandler.removeRecipeByName("${GTValues.MODID}:electronic_circuit_lv")
            ModHandler.addShapedRecipe(true, "electronic_circuit_lv", ELECTRONIC_CIRCUIT_LV.getStackForm(2),
                "RxR", "VCV", "WWW",
                'R', RESISTOR,
                'V', VACUUM_TUBE,
                'C', BASIC_CIRCUIT_BOARD,
                'W', UnificationEntry(cableGtSingle, RedAlloy))

            GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
                arrayOf(BASIC_CIRCUIT_BOARD.stackForm,
                        OreDictUnifier.get(component, MarkerMaterials.Component.Resistor, 2),
                        OreDictUnifier.get(wireGtSingle, RedAlloy, 2),
                        VACUUM_TUBE.getStackForm(2)),
                arrayOf(Tin.getFluid(L)))
            GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
                arrayOf(BASIC_CIRCUIT_BOARD.stackForm,
                    OreDictUnifier.get(component, MarkerMaterials.Component.Resistor, 2),
                    OreDictUnifier.get(wireGtSingle, RedAlloy, 2),
                    VACUUM_TUBE.getStackForm(2)),
                arrayOf(SolderingAlloy.getFluid(L / 2)))

            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
                .input(BASIC_CIRCUIT_BOARD)
                .input(component, MarkerMaterials.Component.Resistor, 2)
                .input(VACUUM_TUBE, 2)
                .input(wireGtSingle, RedAlloy, 2)
                .output(ELECTRONIC_CIRCUIT_LV, 4)
                .EUt(VH[LV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Deleted original recipes for Electronic Circuit MV (player do not
            // need steel plate to make it yet and x2 product).
            ModHandler.removeRecipeByName("${GTValues.MODID}:electronic_circuit_mv")
            ModHandler.addShapedRecipe(true, "electronic_circuit_mv", ELECTRONIC_CIRCUIT_MV.getStackForm(2),
                "DxD", "XCX", "WXW",
                'D', DIODE,
                'X', ELECTRONIC_CIRCUIT_LV,
                'C', GOOD_CIRCUIT_BOARD,
                'W', UnificationEntry(wireGtSingle, Copper))

            GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
                arrayOf(GOOD_CIRCUIT_BOARD.stackForm,
                        OreDictUnifier.get(circuit, MarkerMaterials.Tier.LV, 2),
                        OreDictUnifier.get(component, MarkerMaterials.Component.Diode, 2),
                        OreDictUnifier.get(wireGtSingle, Copper, 2)),
                arrayOf(Tin.getFluid(L)))
            GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
                arrayOf(GOOD_CIRCUIT_BOARD.stackForm,
                    OreDictUnifier.get(circuit, MarkerMaterials.Tier.LV, 2),
                    OreDictUnifier.get(component, MarkerMaterials.Component.Diode, 2),
                    OreDictUnifier.get(wireGtSingle, Copper, 2)),
                arrayOf(SolderingAlloy.getFluid(L / 2)))

            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
                .input(GOOD_CIRCUIT_BOARD)
                .input(ELECTRONIC_CIRCUIT_LV, 2) // Make sure this input circuit cannot be other circuits.
                .input(component, MarkerMaterials.Component.Diode, 2)
                .input(wireGtSingle, Copper, 2)
                .output(ELECTRONIC_CIRCUIT_MV, 2)
                .EUt(VH[LV].toLong())
                .duration(15 * SECOND)
                .buildAndRegister()

        }

    }

}