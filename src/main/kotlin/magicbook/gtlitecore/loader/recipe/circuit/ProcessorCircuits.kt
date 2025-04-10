package magicbook.gtlitecore.loader.recipe.circuit

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.VHA
import gregtech.api.metatileentity.multiblock.CleanroomType
import gregtech.api.recipes.GTRecipeHandler
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.CIRCUIT_ASSEMBLER_RECIPES
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.MarkerMaterials.Component.Capacitor
import gregtech.api.unification.material.MarkerMaterials.Component.Diode
import gregtech.api.unification.material.MarkerMaterials.Component.Inductor
import gregtech.api.unification.material.MarkerMaterials.Component.Resistor
import gregtech.api.unification.material.MarkerMaterials.Component.Transistor
import gregtech.api.unification.material.Materials.Aluminium
import gregtech.api.unification.material.Materials.AnnealedCopper
import gregtech.api.unification.material.Materials.BlueAlloy
import gregtech.api.unification.material.Materials.Carbon
import gregtech.api.unification.material.Materials.Cobalt
import gregtech.api.unification.material.Materials.Copper
import gregtech.api.unification.material.Materials.Cupronickel
import gregtech.api.unification.material.Materials.Electrum
import gregtech.api.unification.material.Materials.Gallium
import gregtech.api.unification.material.Materials.GalliumArsenide
import gregtech.api.unification.material.Materials.Hafnium
import gregtech.api.unification.material.Materials.NickelZincFerrite
import gregtech.api.unification.material.Materials.Niobium
import gregtech.api.unification.material.Materials.Platinum
import gregtech.api.unification.material.Materials.Polyethylene
import gregtech.api.unification.material.Materials.PolyvinylChloride
import gregtech.api.unification.material.Materials.RedAlloy
import gregtech.api.unification.material.Materials.Ruthenium
import gregtech.api.unification.material.Materials.SiliconeRubber
import gregtech.api.unification.material.Materials.SolderingAlloy
import gregtech.api.unification.material.Materials.Tantalum
import gregtech.api.unification.material.Materials.Tin
import gregtech.api.unification.material.Materials.Titanium
import gregtech.api.unification.material.Materials.Tungsten
import gregtech.api.unification.material.Materials.Zirconium
import gregtech.api.unification.ore.OrePrefix.bolt
import gregtech.api.unification.ore.OrePrefix.component
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.foil
import gregtech.api.unification.ore.OrePrefix.ring
import gregtech.api.unification.ore.OrePrefix.wireFine
import gregtech.common.items.MetaItems.CENTRAL_PROCESSING_UNIT
import gregtech.common.items.MetaItems.GOOD_CIRCUIT_BOARD
import gregtech.common.items.MetaItems.MICROPROCESSOR_LV
import gregtech.common.items.MetaItems.NAND_CHIP_ULV
import gregtech.common.items.MetaItems.PLASTIC_BOARD
import gregtech.common.items.MetaItems.PLASTIC_CIRCUIT_BOARD
import gregtech.common.items.MetaItems.PROCESSOR_ASSEMBLY_HV
import gregtech.common.items.MetaItems.PROCESSOR_MV
import gregtech.common.items.MetaItems.RANDOM_ACCESS_MEMORY
import gregtech.common.items.MetaItems.SIMPLE_SYSTEM_ON_CHIP
import gregtech.common.items.MetaItems.SMD_CAPACITOR
import gregtech.common.items.MetaItems.SMD_DIODE
import gregtech.common.items.MetaItems.SMD_INDUCTOR
import gregtech.common.items.MetaItems.SMD_RESISTOR
import gregtech.common.items.MetaItems.SMD_TRANSISTOR
import gregtech.common.items.MetaItems.SYSTEM_ON_CHIP
import gregtech.common.items.MetaItems.WORKSTATION_EV
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.EthylenediaminePyrocatechol
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TetramethylammoniumHydroxide
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK

@Suppress("MISSING_DEPENDENCY_CLASS")
class ProcessorCircuits
{

    companion object
    {

        fun init()
        {
            circuitBoardRecipes()
            smdRecipes()
            circuitRecipes()
        }

        private fun circuitBoardRecipes()
        {
            // Advanced etching liquids recipe addition.
            for (etchingLiquid in arrayOf(
                TetramethylammoniumHydroxide.getFluid(125),
                EthylenediaminePyrocatechol.getFluid(50)))
            {
                CHEMICAL_RECIPES.recipeBuilder()
                    .input(PLASTIC_BOARD)
                    .input(foil, Copper, 6)
                    .fluidInputs(etchingLiquid)
                    .output(PLASTIC_CIRCUIT_BOARD)
                    .EUt(VA[LV].toLong())
                    .duration(30 * SECOND)
                    .buildAndRegister()
            }
        }

        private fun smdRecipes()
        {
            // Add more advanced recipes for common SMDs.

            // SMD Transistor
            GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
                arrayOf(OreDictUnifier.get(foil, Gallium),
                    OreDictUnifier.get(wireFine, AnnealedCopper, 8)),
                arrayOf(Polyethylene.getFluid(L)))

            GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
                arrayOf(OreDictUnifier.get(foil, Gallium),
                    OreDictUnifier.get(wireFine, Tantalum, 8)),
                arrayOf(Polyethylene.getFluid(L)))

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .input(foil, Gallium)
                .input(wireFine, AnnealedCopper, 8)
                .fluidInputs(Polyethylene.getFluid(L))
                .output(SMD_TRANSISTOR, 16)
                .EUt(VA[HV].toLong())
                .duration(8 * SECOND)
                .buildAndRegister()

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .input(foil, Gallium)
                .input(wireFine, Tantalum, 4)
                .fluidInputs(Polyethylene.getFluid(L))
                .output(SMD_TRANSISTOR, 32)
                .EUt(VA[HV].toLong())
                .duration(4 * SECOND)
                .buildAndRegister()

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .input(foil, Gallium)
                .input(wireFine, Titanium, 2)
                .fluidInputs(Polyethylene.getFluid(L))
                .output(SMD_TRANSISTOR, 64)
                .EUt(VA[HV].toLong())
                .duration(2 * SECOND)
                .buildAndRegister()

            // SMD Resistor
            GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
                arrayOf(OreDictUnifier.get(dust, Carbon),
                    OreDictUnifier.get(wireFine, Electrum, 4)),
                arrayOf(Polyethylene.getFluid(L * 2)))

            GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
                arrayOf(OreDictUnifier.get(dust, Carbon),
                    OreDictUnifier.get(wireFine, Tantalum, 4)),
                arrayOf(Polyethylene.getFluid(L * 2)))

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .input(dust, Carbon)
                .input(wireFine, Electrum, 4)
                .fluidInputs(Polyethylene.getFluid(L * 2))
                .output(SMD_RESISTOR, 16)
                .EUt(VA[HV].toLong())
                .duration(8 * SECOND)
                .buildAndRegister()

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .input(dust, Carbon)
                .input(wireFine, Niobium, 4)
                .fluidInputs(Polyethylene.getFluid(L * 2))
                .output(SMD_RESISTOR, 32)
                .EUt(VA[HV].toLong())
                .duration(4 * SECOND)
                .buildAndRegister()

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .input(dust, Carbon)
                .input(wireFine, Tungsten, 4)
                .fluidInputs(Polyethylene.getFluid(L * 2))
                .output(SMD_RESISTOR, 64)
                .EUt(VA[HV].toLong())
                .duration(2 * SECOND)
                .buildAndRegister()

            // SMD Capacitor
            GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
                arrayOf(OreDictUnifier.get(foil, SiliconeRubber),
                    OreDictUnifier.get(foil, Aluminium)),
                arrayOf(Polyethylene.getFluid(L / 2)))

            GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
                arrayOf(OreDictUnifier.get(foil, PolyvinylChloride, 2),
                    OreDictUnifier.get(foil, Aluminium)),
                arrayOf(Polyethylene.getFluid(L / 2)))

            GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
                arrayOf(OreDictUnifier.get(foil, SiliconeRubber),
                    OreDictUnifier.get(foil, Tantalum)),
                arrayOf(Polyethylene.getFluid(L / 2)))

            GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
                arrayOf(OreDictUnifier.get(foil, PolyvinylChloride, 2),
                    OreDictUnifier.get(foil, Tantalum)),
                arrayOf(Polyethylene.getFluid(L / 2)))

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .input(foil, PolyvinylChloride)
                .input(foil, Aluminium)
                .fluidInputs(Polyethylene.getFluid(L / 2))
                .output(SMD_CAPACITOR, 16)
                .EUt(VA[HV].toLong())
                .duration(8 * SECOND)
                .buildAndRegister()

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .input(foil, PolyvinylChloride)
                .input(foil, Tantalum)
                .fluidInputs(Polyethylene.getFluid(L / 2))
                .output(SMD_CAPACITOR, 32)
                .EUt(VA[HV].toLong())
                .duration(4 * SECOND)
                .buildAndRegister()

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .input(foil, PolyvinylChloride)
                .input(foil, Zirconium)
                .fluidInputs(Polyethylene.getFluid(L / 2))
                .output(SMD_CAPACITOR, 64)
                .EUt(VA[HV].toLong())
                .duration(2 * SECOND)
                .buildAndRegister()

            // SMD Diode
            GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
                arrayOf(OreDictUnifier.get(dust, GalliumArsenide),
                    OreDictUnifier.get(wireFine, Platinum, 8)),
                arrayOf(Polyethylene.getFluid(L * 2)))

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .input(dust, GalliumArsenide)
                .input(wireFine, Platinum, 8)
                .fluidInputs(Polyethylene.getFluid(L * 2))
                .output(SMD_DIODE, 32)
                .EUt(VA[HV].toLong())
                .duration(8 * SECOND)
                .buildAndRegister()

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .input(dust, GalliumArsenide)
                .input(wireFine, Ruthenium, 8)
                .fluidInputs(Polyethylene.getFluid(L * 2))
                .output(SMD_DIODE, 64)
                .EUt(VA[HV].toLong())
                .duration(4 * SECOND)
                .buildAndRegister()

            // SMD Inductor
            GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
                arrayOf(OreDictUnifier.get(ring, NickelZincFerrite),
                    OreDictUnifier.get(wireFine, Cupronickel, 4)),
                arrayOf(Polyethylene.getFluid(L)))

            GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
                arrayOf(OreDictUnifier.get(ring, NickelZincFerrite),
                    OreDictUnifier.get(wireFine, Tantalum, 4)),
                arrayOf(Polyethylene.getFluid(L)))

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .input(ring, NickelZincFerrite)
                .input(wireFine, Cobalt, 4)
                .fluidInputs(Polyethylene.getFluid(L))
                .output(SMD_INDUCTOR, 16)
                .EUt(VA[HV].toLong())
                .duration(8 * SECOND)
                .buildAndRegister()

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .input(ring, NickelZincFerrite)
                .input(wireFine, Cupronickel, 4)
                .fluidInputs(Polyethylene.getFluid(L))
                .output(SMD_INDUCTOR, 32)
                .EUt(VA[HV].toLong())
                .duration(4 * SECOND)
                .buildAndRegister()

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .input(ring, NickelZincFerrite)
                .input(wireFine, Hafnium, 4)
                .fluidInputs(Polyethylene.getFluid(L))
                .output(SMD_INDUCTOR, 64)
                .EUt(VA[HV].toLong())
                .duration(2 * SECOND)
                .buildAndRegister()

        }

        private fun circuitRecipes()
        {
            // ULV NAND Chip
            GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
                arrayOf(GOOD_CIRCUIT_BOARD.stackForm,
                    SIMPLE_SYSTEM_ON_CHIP.stackForm,
                    OreDictUnifier.get(bolt, RedAlloy, 2),
                    OreDictUnifier.get(wireFine, Tin, 2)),
                arrayOf(SolderingAlloy.getFluid(L / 2)))

            GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
                arrayOf(GOOD_CIRCUIT_BOARD.stackForm,
                    SIMPLE_SYSTEM_ON_CHIP.stackForm,
                    OreDictUnifier.get(bolt, RedAlloy, 2),
                    OreDictUnifier.get(wireFine, Tin, 2)),
                arrayOf(Tin.getFluid(L)))

            GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
                arrayOf(PLASTIC_CIRCUIT_BOARD.stackForm,
                    SIMPLE_SYSTEM_ON_CHIP.stackForm,
                    OreDictUnifier.get(bolt, RedAlloy, 2),
                    OreDictUnifier.get(wireFine, Tin, 2)),
                arrayOf(SolderingAlloy.getFluid(L / 2)))

            GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
                arrayOf(PLASTIC_CIRCUIT_BOARD.stackForm,
                    SIMPLE_SYSTEM_ON_CHIP.stackForm,
                    OreDictUnifier.get(bolt, RedAlloy, 2),
                    OreDictUnifier.get(wireFine, Tin, 2)),
                arrayOf(Tin.getFluid(L)))

            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
                .input(GOOD_CIRCUIT_BOARD)
                .input(SIMPLE_SYSTEM_ON_CHIP)
                .input(bolt, RedAlloy, 2)
                .input(wireFine, Tin, 2)
                .output(NAND_CHIP_ULV, 8)
                .EUt(VA[MV].toLong())
                .duration(15 * SECOND)
                .buildAndRegister()

            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
                .input(PLASTIC_CIRCUIT_BOARD)
                .input(SIMPLE_SYSTEM_ON_CHIP)
                .input(bolt, RedAlloy, 2)
                .input(wireFine, Tin, 2)
                .output(NAND_CHIP_ULV, 16)
                .EUt(VA[MV].toLong())
                .duration(15 * SECOND)
                .buildAndRegister()

            // LV Microprocessor
            GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
                arrayOf(PLASTIC_CIRCUIT_BOARD.stackForm,
                    CENTRAL_PROCESSING_UNIT.stackForm,
                    OreDictUnifier.get(component, Resistor, 2),
                    OreDictUnifier.get(component, Capacitor, 2),
                    OreDictUnifier.get(component, Transistor, 2),
                    OreDictUnifier.get(wireFine, Copper, 2)),
                arrayOf(SolderingAlloy.getFluid(L / 2)))

            GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
                arrayOf(PLASTIC_CIRCUIT_BOARD.stackForm,
                    CENTRAL_PROCESSING_UNIT.stackForm,
                    OreDictUnifier.get(component, Resistor, 2),
                    OreDictUnifier.get(component, Capacitor, 2),
                    OreDictUnifier.get(component, Transistor, 2),
                    OreDictUnifier.get(wireFine, Copper, 2)),
                arrayOf(Tin.getFluid(L)))

            GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
                arrayOf(PLASTIC_CIRCUIT_BOARD.stackForm,
                    SYSTEM_ON_CHIP.stackForm,
                    OreDictUnifier.get(wireFine, Copper, 2),
                    OreDictUnifier.get(bolt, Tin, 2)),
                arrayOf(SolderingAlloy.getFluid(L / 2)))

            GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
                arrayOf(PLASTIC_CIRCUIT_BOARD.stackForm,
                    SYSTEM_ON_CHIP.stackForm,
                    OreDictUnifier.get(wireFine, Copper, 2),
                    OreDictUnifier.get(bolt, Tin, 2)),
                arrayOf(Tin.getFluid(L)))

            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
                .input(PLASTIC_CIRCUIT_BOARD)
                .input(CENTRAL_PROCESSING_UNIT)
                .input(component, Resistor, 2)
                .input(component, Capacitor, 2)
                .input(component, Transistor, 2)
                .input(wireFine, Copper)
                .output(MICROPROCESSOR_LV, 4)
                .EUt(VHA[MV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
                .input(PLASTIC_CIRCUIT_BOARD)
                .input(SYSTEM_ON_CHIP)
                .input(wireFine, Copper, 2)
                .input(bolt, Tin, 2)
                .output(MICROPROCESSOR_LV, 8)
                .EUt(600) // EV
                .duration(2 * SECOND + 10 * TICK)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // MV Processor
            GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
                arrayOf(PLASTIC_CIRCUIT_BOARD.stackForm,
                    CENTRAL_PROCESSING_UNIT.stackForm,
                    OreDictUnifier.get(component, Resistor, 4),
                    OreDictUnifier.get(component, Capacitor, 4),
                    OreDictUnifier.get(component, Transistor, 4),
                    OreDictUnifier.get(wireFine, RedAlloy, 4)),
                arrayOf(SolderingAlloy.getFluid(L / 2)))

            GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
                arrayOf(PLASTIC_CIRCUIT_BOARD.stackForm,
                    CENTRAL_PROCESSING_UNIT.stackForm,
                    OreDictUnifier.get(component, Resistor, 4),
                    OreDictUnifier.get(component, Capacitor, 4),
                    OreDictUnifier.get(component, Transistor, 4),
                    OreDictUnifier.get(wireFine, RedAlloy, 4)),
                arrayOf(Tin.getFluid(L)))

            GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
                arrayOf(PLASTIC_CIRCUIT_BOARD.stackForm,
                    SYSTEM_ON_CHIP.stackForm,
                    OreDictUnifier.get(wireFine, RedAlloy, 4),
                    OreDictUnifier.get(bolt, AnnealedCopper, 4)),
                arrayOf(SolderingAlloy.getFluid(L / 2)))

            GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
                arrayOf(PLASTIC_CIRCUIT_BOARD.stackForm,
                    SYSTEM_ON_CHIP.stackForm,
                    OreDictUnifier.get(wireFine, RedAlloy, 4),
                    OreDictUnifier.get(bolt, AnnealedCopper, 4)),
                arrayOf(Tin.getFluid(L)))

            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
                .input(PLASTIC_CIRCUIT_BOARD)
                .input(CENTRAL_PROCESSING_UNIT)
                .input(component, Resistor, 4)
                .input(component, Capacitor, 4)
                .input(component, Transistor, 4)
                .input(wireFine, RedAlloy, 4)
                .output(PROCESSOR_MV, 4)
                .EUt(VHA[MV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
                .input(PLASTIC_CIRCUIT_BOARD)
                .input(SYSTEM_ON_CHIP)
                .input(wireFine, RedAlloy, 4)
                .input(bolt, AnnealedCopper, 4)
                .output(PROCESSOR_MV, 8)
                .EUt(2400) // IV
                .duration(2 * SECOND + 10 * TICK)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // HV Processor Assembly
            GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
                arrayOf(PLASTIC_CIRCUIT_BOARD.stackForm,
                    PROCESSOR_MV.getStackForm(2),
                    OreDictUnifier.get(component, Inductor, 4),
                    OreDictUnifier.get(component, Capacitor, 8),
                    RANDOM_ACCESS_MEMORY.getStackForm(4),
                    OreDictUnifier.get(wireFine, RedAlloy, 8)),
                arrayOf(SolderingAlloy.getFluid(L)))

            GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
                arrayOf(PLASTIC_CIRCUIT_BOARD.stackForm,
                    PROCESSOR_MV.getStackForm(2),
                    OreDictUnifier.get(component, Inductor, 4),
                    OreDictUnifier.get(component, Capacitor, 8),
                    RANDOM_ACCESS_MEMORY.getStackForm(4),
                    OreDictUnifier.get(wireFine, RedAlloy, 8)),
                arrayOf(Tin.getFluid(L * 2)))

            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
                .input(PLASTIC_CIRCUIT_BOARD)
                .input(PROCESSOR_MV, 4)
                .input(component, Inductor, 4)
                .input(component, Capacitor, 8)
                .input(RANDOM_ACCESS_MEMORY, 4)
                .input(wireFine, RedAlloy, 8)
                .output(PROCESSOR_ASSEMBLY_HV, 3)
                .EUt(90) // MV
                .duration(20 * SECOND)
                .solderMultiplier(2)
                .buildAndRegister()

            // EV Workstation
            GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
                arrayOf(PLASTIC_CIRCUIT_BOARD.stackForm,
                    PROCESSOR_ASSEMBLY_HV.getStackForm(2),
                    OreDictUnifier.get(component, Diode, 4),
                    RANDOM_ACCESS_MEMORY.getStackForm(4),
                    OreDictUnifier.get(wireFine, Electrum, 16),
                    OreDictUnifier.get(bolt, BlueAlloy, 16)),
                arrayOf(SolderingAlloy.getFluid(L)))

            GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
                arrayOf(PLASTIC_CIRCUIT_BOARD.stackForm,
                    PROCESSOR_ASSEMBLY_HV.getStackForm(2),
                    OreDictUnifier.get(component, Diode, 4),
                    RANDOM_ACCESS_MEMORY.getStackForm(4),
                    OreDictUnifier.get(wireFine, Electrum, 16),
                    OreDictUnifier.get(bolt, BlueAlloy, 16)),
                arrayOf(Tin.getFluid(L * 2)))

            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
                .input(PLASTIC_CIRCUIT_BOARD)
                .input(PROCESSOR_ASSEMBLY_HV, 3)
                .input(component, Diode, 4)
                .input(RANDOM_ACCESS_MEMORY, 4)
                .input(wireFine, Electrum, 16)
                .input(bolt, BlueAlloy, 16)
                .output(WORKSTATION_EV, 2)
                .EUt(VA[MV].toLong())
                .duration(20 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .solderMultiplier(2)
                .buildAndRegister()

        }

    }

}