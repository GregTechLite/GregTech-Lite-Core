package magicbook.gtlitecore.loader.recipe.circuit

import gregtech.api.GTValues.L
import gregtech.api.metatileentity.multiblock.CleanroomType
import gregtech.api.recipes.GTRecipeHandler
import gregtech.api.recipes.RecipeMaps.CIRCUIT_ASSEMBLER_RECIPES
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Materials.Electrum
import gregtech.api.unification.material.Materials.Platinum
import gregtech.api.unification.material.Materials.SolderingAlloy
import gregtech.api.unification.material.Materials.Tin
import gregtech.api.unification.ore.OrePrefix.bolt
import gregtech.api.unification.ore.OrePrefix.wireFine
import gregtech.common.items.MetaItems.ADVANCED_CIRCUIT_BOARD
import gregtech.common.items.MetaItems.ADVANCED_SMD_CAPACITOR
import gregtech.common.items.MetaItems.ADVANCED_SMD_DIODE
import gregtech.common.items.MetaItems.ADVANCED_SMD_INDUCTOR
import gregtech.common.items.MetaItems.ADVANCED_SMD_RESISTOR
import gregtech.common.items.MetaItems.ADVANCED_SMD_TRANSISTOR
import gregtech.common.items.MetaItems.ADVANCED_SYSTEM_ON_CHIP
import gregtech.common.items.MetaItems.NANO_CENTRAL_PROCESSING_UNIT
import gregtech.common.items.MetaItems.NANO_COMPUTER_IV
import gregtech.common.items.MetaItems.NANO_PROCESSOR_ASSEMBLY_EV
import gregtech.common.items.MetaItems.NANO_PROCESSOR_HV
import gregtech.common.items.MetaItems.NOR_MEMORY_CHIP
import gregtech.common.items.MetaItems.RANDOM_ACCESS_MEMORY
import gregtech.common.items.MetaItems.SMD_CAPACITOR
import gregtech.common.items.MetaItems.SMD_DIODE
import gregtech.common.items.MetaItems.SMD_INDUCTOR
import gregtech.common.items.MetaItems.SMD_RESISTOR
import gregtech.common.items.MetaItems.SMD_TRANSISTOR
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK

@Suppress("MISSING_DEPENDENCY_CLASS")
class NanoCircuits
{

    companion object
    {

        fun init()
        {

            // HV Nano Processor
            GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
                arrayOf(ADVANCED_CIRCUIT_BOARD.stackForm,
                    NANO_CENTRAL_PROCESSING_UNIT.stackForm,
                    SMD_RESISTOR.getStackForm(8),
                    SMD_CAPACITOR.getStackForm(8),
                    SMD_TRANSISTOR.getStackForm(8),
                    OreDictUnifier.get(wireFine, Electrum, 8)),
                arrayOf(SolderingAlloy.getFluid(L / 2)))

            GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
                arrayOf(ADVANCED_CIRCUIT_BOARD.stackForm,
                    NANO_CENTRAL_PROCESSING_UNIT.stackForm,
                    SMD_RESISTOR.getStackForm(8),
                    SMD_CAPACITOR.getStackForm(8),
                    SMD_TRANSISTOR.getStackForm(8),
                    OreDictUnifier.get(wireFine, Electrum, 8)),
                arrayOf(Tin.getFluid(L)))

            GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
                arrayOf(ADVANCED_CIRCUIT_BOARD.stackForm,
                    NANO_CENTRAL_PROCESSING_UNIT.stackForm,
                    ADVANCED_SMD_RESISTOR.getStackForm(2),
                    ADVANCED_SMD_CAPACITOR.getStackForm(2),
                    ADVANCED_SMD_TRANSISTOR.getStackForm(2),
                    OreDictUnifier.get(wireFine, Electrum, 8)),
                arrayOf(SolderingAlloy.getFluid(L / 2)))

            GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
                arrayOf(ADVANCED_CIRCUIT_BOARD.stackForm,
                    NANO_CENTRAL_PROCESSING_UNIT.stackForm,
                    ADVANCED_SMD_RESISTOR.getStackForm(2),
                    ADVANCED_SMD_CAPACITOR.getStackForm(2),
                    ADVANCED_SMD_TRANSISTOR.getStackForm(2),
                    OreDictUnifier.get(wireFine, Electrum, 8)),
                arrayOf(Tin.getFluid(L)))

            GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
                arrayOf(ADVANCED_CIRCUIT_BOARD.stackForm,
                    ADVANCED_SYSTEM_ON_CHIP.stackForm,
                    OreDictUnifier.get(wireFine, Electrum, 4),
                    OreDictUnifier.get(bolt, Platinum, 4)),
                arrayOf(SolderingAlloy.getFluid(L / 2)))

            GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
                arrayOf(ADVANCED_CIRCUIT_BOARD.stackForm,
                    ADVANCED_SYSTEM_ON_CHIP.stackForm,
                    OreDictUnifier.get(wireFine, Electrum, 4),
                    OreDictUnifier.get(bolt, Platinum, 4)),
                arrayOf(Tin.getFluid(L)))

            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
                .input(ADVANCED_CIRCUIT_BOARD)
                .input(NANO_CENTRAL_PROCESSING_UNIT)
                .input(SMD_RESISTOR, 8)
                .input(SMD_CAPACITOR, 8)
                .input(SMD_TRANSISTOR, 8)
                .input(wireFine, Electrum, 8)
                .output(NANO_PROCESSOR_HV, 4)
                .EUt(600) // EV
                .duration(10 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
                .input(ADVANCED_CIRCUIT_BOARD)
                .input(NANO_CENTRAL_PROCESSING_UNIT)
                .input(ADVANCED_SMD_RESISTOR, 2)
                .input(ADVANCED_SMD_CAPACITOR, 2)
                .input(ADVANCED_SMD_TRANSISTOR, 2)
                .input(wireFine, Electrum, 8)
                .output(NANO_PROCESSOR_HV, 4)
                .EUt(600) // EV
                .duration(5 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
                .input(ADVANCED_CIRCUIT_BOARD)
                .input(ADVANCED_SYSTEM_ON_CHIP)
                .input(wireFine, Electrum, 4)
                .input(bolt, Platinum, 4)
                .output(NANO_PROCESSOR_HV, 8)
                .EUt(9600) // LuV
                .duration(2 * SECOND + 10 * TICK)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // EV Nano Assembly
            GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
                arrayOf(ADVANCED_CIRCUIT_BOARD.stackForm,
                    NANO_PROCESSOR_HV.getStackForm(2),
                    SMD_INDUCTOR.getStackForm(4),
                    SMD_CAPACITOR.getStackForm(8),
                    RANDOM_ACCESS_MEMORY.getStackForm(8),
                    OreDictUnifier.get(wireFine, Electrum, 16)),
                arrayOf(SolderingAlloy.getFluid(L)))

            GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
                arrayOf(ADVANCED_CIRCUIT_BOARD.stackForm,
                    NANO_PROCESSOR_HV.getStackForm(2),
                    SMD_INDUCTOR.getStackForm(4),
                    SMD_CAPACITOR.getStackForm(8),
                    RANDOM_ACCESS_MEMORY.getStackForm(8),
                    OreDictUnifier.get(wireFine, Electrum, 16)),
                arrayOf(Tin.getFluid(L * 2)))

            GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
                arrayOf(ADVANCED_CIRCUIT_BOARD.stackForm,
                    NANO_PROCESSOR_HV.getStackForm(2),
                    ADVANCED_SMD_INDUCTOR.stackForm,
                    ADVANCED_SMD_CAPACITOR.getStackForm(2),
                    RANDOM_ACCESS_MEMORY.getStackForm(8),
                    OreDictUnifier.get(wireFine, Electrum, 16)),
                arrayOf(SolderingAlloy.getFluid(L)))

            GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
                arrayOf(ADVANCED_CIRCUIT_BOARD.stackForm,
                    NANO_PROCESSOR_HV.getStackForm(2),
                    ADVANCED_SMD_INDUCTOR.stackForm,
                    ADVANCED_SMD_CAPACITOR.getStackForm(2),
                    RANDOM_ACCESS_MEMORY.getStackForm(8),
                    OreDictUnifier.get(wireFine, Electrum, 16)),
                arrayOf(Tin.getFluid(L * 2)))

            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
                .input(ADVANCED_CIRCUIT_BOARD)
                .input(NANO_PROCESSOR_HV, 4)
                .input(SMD_INDUCTOR, 4)
                .input(SMD_CAPACITOR, 8)
                .input(RANDOM_ACCESS_MEMORY, 8)
                .input(wireFine, Electrum, 16)
                .output(NANO_PROCESSOR_ASSEMBLY_EV, 3)
                .EUt(600) // EV
                .duration(20 * SECOND)
                .solderMultiplier(2)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
                .input(ADVANCED_CIRCUIT_BOARD)
                .input(NANO_PROCESSOR_HV, 4)
                .input(ADVANCED_SMD_INDUCTOR)
                .input(ADVANCED_SMD_CAPACITOR, 2)
                .input(RANDOM_ACCESS_MEMORY, 8)
                .input(wireFine, Electrum, 16)
                .output(NANO_PROCESSOR_ASSEMBLY_EV, 3)
                .EUt(600) // EV
                .duration(10 * SECOND)
                .solderMultiplier(2)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // IV Nano Supercomputer
            GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
                arrayOf(ADVANCED_CIRCUIT_BOARD.stackForm,
                    NANO_PROCESSOR_ASSEMBLY_EV.getStackForm(2),
                    SMD_DIODE.getStackForm(8),
                    NOR_MEMORY_CHIP.getStackForm(4),
                    RANDOM_ACCESS_MEMORY.getStackForm(16),
                    OreDictUnifier.get(wireFine, Electrum, 16)),
                arrayOf(SolderingAlloy.getFluid(L)))

            GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
                arrayOf(ADVANCED_CIRCUIT_BOARD.stackForm,
                    NANO_PROCESSOR_ASSEMBLY_EV.getStackForm(2),
                    SMD_DIODE.getStackForm(8),
                    NOR_MEMORY_CHIP.getStackForm(4),
                    RANDOM_ACCESS_MEMORY.getStackForm(16),
                    OreDictUnifier.get(wireFine, Electrum, 16)),
                arrayOf(Tin.getFluid(L * 2)))

            GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
                arrayOf(ADVANCED_CIRCUIT_BOARD.stackForm,
                    NANO_PROCESSOR_ASSEMBLY_EV.getStackForm(2),
                    ADVANCED_SMD_DIODE.getStackForm(2),
                    NOR_MEMORY_CHIP.getStackForm(4),
                    RANDOM_ACCESS_MEMORY.getStackForm(16),
                    OreDictUnifier.get(wireFine, Electrum, 16)),
                arrayOf(SolderingAlloy.getFluid(L)))

            GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
                arrayOf(ADVANCED_CIRCUIT_BOARD.stackForm,
                    NANO_PROCESSOR_ASSEMBLY_EV.getStackForm(2),
                    ADVANCED_SMD_DIODE.getStackForm(2),
                    NOR_MEMORY_CHIP.getStackForm(4),
                    RANDOM_ACCESS_MEMORY.getStackForm(16),
                    OreDictUnifier.get(wireFine, Electrum, 16)),
                arrayOf(Tin.getFluid(L * 2)))

            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
                .input(ADVANCED_CIRCUIT_BOARD)
                .input(NANO_PROCESSOR_ASSEMBLY_EV, 3)
                .input(SMD_DIODE, 8)
                .input(NOR_MEMORY_CHIP, 4)
                .input(RANDOM_ACCESS_MEMORY, 16)
                .input(wireFine, Electrum, 16)
                .output(NANO_COMPUTER_IV, 2)
                .EUt(600) // EV
                .duration(20 * SECOND)
                .solderMultiplier(2)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
                .input(ADVANCED_CIRCUIT_BOARD)
                .input(NANO_PROCESSOR_ASSEMBLY_EV, 3)
                .input(ADVANCED_SMD_DIODE, 2)
                .input(NOR_MEMORY_CHIP, 4)
                .input(RANDOM_ACCESS_MEMORY, 16)
                .input(wireFine, Electrum, 16)
                .output(NANO_COMPUTER_IV, 2)
                .EUt(600) // EV
                .duration(10 * SECOND)
                .solderMultiplier(2)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // LuV Nano Mainframe
            // GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
            //     arrayOf(OreDictUnifier.get(frameGt, Aluminium, 2),
            //         NANO_COMPUTER_IV.getStackForm(2),
            //         SMD_INDUCTOR.getStackForm(16),
            //         SMD_CAPACITOR.getStackForm(32),
            //         RANDOM_ACCESS_MEMORY.getStackForm(16),
            //         OreDictUnifier.get(wireGtSingle, AnnealedCopper, 32)),
            //     arrayOf(SolderingAlloy.getFluid(L * 2)))

            // GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
            //     arrayOf(OreDictUnifier.get(frameGt, Aluminium, 2),
            //         NANO_COMPUTER_IV.getStackForm(2),
            //         SMD_INDUCTOR.getStackForm(16),
            //         SMD_CAPACITOR.getStackForm(32),
            //         RANDOM_ACCESS_MEMORY.getStackForm(16),
            //         OreDictUnifier.get(wireGtSingle, AnnealedCopper, 32)),
            //     arrayOf(Tin.getFluid(L * 4)))

            // GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
            //     arrayOf(OreDictUnifier.get(frameGt, Aluminium, 2),
            //         NANO_COMPUTER_IV.getStackForm(2),
            //         ADVANCED_SMD_INDUCTOR.getStackForm(4),
            //         ADVANCED_SMD_CAPACITOR.getStackForm(8),
            //         RANDOM_ACCESS_MEMORY.getStackForm(16),
            //         OreDictUnifier.get(wireGtSingle, AnnealedCopper, 32)),
            //     arrayOf(SolderingAlloy.getFluid(L * 2)))

            // GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
            //     arrayOf(OreDictUnifier.get(frameGt, Aluminium, 2),
            //         NANO_COMPUTER_IV.getStackForm(2),
            //         ADVANCED_SMD_INDUCTOR.getStackForm(4),
            //         ADVANCED_SMD_CAPACITOR.getStackForm(8),
            //         RANDOM_ACCESS_MEMORY.getStackForm(16),
            //         OreDictUnifier.get(wireGtSingle, AnnealedCopper, 32)),
            //     arrayOf(Tin.getFluid(L * 4)))

            // CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
            //     .input(frameGt, Aluminium, 2)
            //     .input(NANO_COMPUTER_IV, 2)
            //     .input(SMD_INDUCTOR, 16)
            //     .input(SMD_CAPACITOR, 32)
            //     .input(RANDOM_ACCESS_MEMORY, 16)
            //     .input(wireGtSingle, AnnealedCopper, 32)
            //     .output(NANO_MAINFRAME_LUV)
            //     .EUt(VA[EV].toLong())
            //     .duration(40 * SECOND)
            //     .solderMultiplier(4)
            //     .cleanroom(CleanroomType.CLEANROOM)
            //     .buildAndRegister()

            // CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
            //     .input(frameGt, Aluminium, 2)
            //     .input(NANO_COMPUTER_IV, 2)
            //     .input(ADVANCED_SMD_INDUCTOR, 4)
            //     .input(ADVANCED_SMD_CAPACITOR, 8)
            //     .input(RANDOM_ACCESS_MEMORY, 16)
            //     .input(wireGtSingle, AnnealedCopper, 32)
            //     .output(NANO_MAINFRAME_LUV)
            //     .EUt(VA[EV].toLong())
            //     .duration(20 * SECOND)
            //     .solderMultiplier(4)
            //     .cleanroom(CleanroomType.CLEANROOM)
            //     .buildAndRegister()

        }

    }

}