package gregtechlite.gtlitecore.loader.recipe.circuit

import gregtech.api.GTValues.IV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.VA
import gregtech.api.metatileentity.multiblock.CleanroomType
import gregtech.api.recipes.GTRecipeHandler
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.CIRCUIT_ASSEMBLER_RECIPES
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Materials.AnnealedCopper
import gregtech.api.unification.material.Materials.GalliumArsenide
import gregtech.api.unification.material.Materials.IndiumGalliumPhosphide
import gregtech.api.unification.material.Materials.NiobiumTitanium
import gregtech.api.unification.material.Materials.Platinum
import gregtech.api.unification.material.Materials.Radon
import gregtech.api.unification.material.Materials.SolderingAlloy
import gregtech.api.unification.material.Materials.Tin
import gregtech.api.unification.ore.OrePrefix.bolt
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.dustSmall
import gregtech.api.unification.ore.OrePrefix.dustTiny
import gregtech.api.unification.ore.OrePrefix.foil
import gregtech.api.unification.ore.OrePrefix.wireFine
import gregtech.common.items.MetaItems.ADVANCED_SMD_CAPACITOR
import gregtech.common.items.MetaItems.ADVANCED_SMD_DIODE
import gregtech.common.items.MetaItems.ADVANCED_SMD_INDUCTOR
import gregtech.common.items.MetaItems.ADVANCED_SMD_TRANSISTOR
import gregtech.common.items.MetaItems.ADVANCED_SYSTEM_ON_CHIP
import gregtech.common.items.MetaItems.EXTREME_CIRCUIT_BOARD
import gregtech.common.items.MetaItems.FIBER_BOARD
import gregtech.common.items.MetaItems.NANO_CENTRAL_PROCESSING_UNIT
import gregtech.common.items.MetaItems.NANO_CENTRAL_PROCESSING_UNIT_WAFER
import gregtech.common.items.MetaItems.NOR_MEMORY_CHIP
import gregtech.common.items.MetaItems.QUANTUM_ASSEMBLY_IV
import gregtech.common.items.MetaItems.QUANTUM_COMPUTER_LUV
import gregtech.common.items.MetaItems.QUANTUM_EYE
import gregtech.common.items.MetaItems.QUANTUM_PROCESSOR_EV
import gregtech.common.items.MetaItems.QUBIT_CENTRAL_PROCESSING_UNIT
import gregtech.common.items.MetaItems.QUBIT_CENTRAL_PROCESSING_UNIT_WAFER
import gregtech.common.items.MetaItems.RANDOM_ACCESS_MEMORY
import gregtech.common.items.MetaItems.SMD_CAPACITOR
import gregtech.common.items.MetaItems.SMD_DIODE
import gregtech.common.items.MetaItems.SMD_INDUCTOR
import gregtech.common.items.MetaItems.SMD_TRANSISTOR
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.VACUUM_CHAMBER_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CadmiumSelenide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.EthylenediaminePyrocatechol
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TetramethylammoniumHydroxide
import gregtechlite.gtlitecore.api.MINUTE
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeHandler

internal object QuantumCircuits
{

    // @formatter:off

    fun init()
    {
        circuitBoardRecipes()
        circuitComponentsRecipes()
        circuitRecipes()
    }

    private fun circuitBoardRecipes()
    {
        // Advanced etching liquids recipe addition.
        for (etchingLiquid in arrayOf(
            TetramethylammoniumHydroxide.getFluid(500),
            EthylenediaminePyrocatechol.getFluid(250)))
        {
            CHEMICAL_RECIPES.recipeBuilder()
                .input(FIBER_BOARD)
                .input(foil, AnnealedCopper, 12)
                .fluidInputs(etchingLiquid)
                .output(EXTREME_CIRCUIT_BOARD)
                .EUt(VA[LV])
                .duration(1 * MINUTE)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()
        }
    }

    private fun circuitComponentsRecipes()
    {
        // QuBit Central Processing Unit (QuBit CPU)
        GTLiteRecipeHandler.removeChemicalRecipes(
            arrayOf(NANO_CENTRAL_PROCESSING_UNIT_WAFER.stackForm,
                QUANTUM_EYE.getStackForm(2)),
            arrayOf(GalliumArsenide.getFluid(L * 2)))

        GTLiteRecipeHandler.removeChemicalRecipes(
            arrayOf(NANO_CENTRAL_PROCESSING_UNIT_WAFER.stackForm,
                OreDictUnifier.get(dust, IndiumGalliumPhosphide)),
            arrayOf(Radon.getFluid(50)))

        VACUUM_CHAMBER_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(NANO_CENTRAL_PROCESSING_UNIT_WAFER)
            .input(dustTiny, CadmiumSelenide)
            .input(QUANTUM_EYE)
            .fluidInputs(GalliumArsenide.getFluid(L))
            .output(QUBIT_CENTRAL_PROCESSING_UNIT_WAFER, 2)
            .EUt(VA[IV])
            .duration(10 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        VACUUM_CHAMBER_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(NANO_CENTRAL_PROCESSING_UNIT_WAFER)
            .input(dustTiny, CadmiumSelenide)
            .input(dust, IndiumGalliumPhosphide)
            .fluidInputs(Radon.getFluid(50))
            .output(QUBIT_CENTRAL_PROCESSING_UNIT_WAFER, 2)
            .EUt(VA[IV])
            .duration(20 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        VACUUM_CHAMBER_RECIPES.recipeBuilder()
            .circuitMeta(4)
            .input(NANO_CENTRAL_PROCESSING_UNIT_WAFER, 4)
            .input(dustSmall, CadmiumSelenide)
            .input(QUANTUM_EYE, 4)
            .fluidInputs(GalliumArsenide.getFluid(L * 4))
            .output(QUBIT_CENTRAL_PROCESSING_UNIT_WAFER, 8)
            .EUt(VA[IV])
            .duration(15 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        VACUUM_CHAMBER_RECIPES.recipeBuilder()
            .circuitMeta(5)
            .input(NANO_CENTRAL_PROCESSING_UNIT_WAFER, 4)
            .input(dustSmall, CadmiumSelenide)
            .input(dust, IndiumGalliumPhosphide, 4)
            .fluidInputs(Radon.getFluid(200))
            .output(QUBIT_CENTRAL_PROCESSING_UNIT_WAFER, 8)
            .EUt(VA[IV])
            .duration(25 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        VACUUM_CHAMBER_RECIPES.recipeBuilder()
            .circuitMeta(9)
            .input(NANO_CENTRAL_PROCESSING_UNIT_WAFER, 9)
            .input(dust, CadmiumSelenide)
            .input(QUANTUM_EYE, 9)
            .fluidInputs(GalliumArsenide.getFluid(L * 9))
            .output(QUBIT_CENTRAL_PROCESSING_UNIT_WAFER, 18)
            .EUt(VA[IV])
            .duration(20 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        VACUUM_CHAMBER_RECIPES.recipeBuilder()
            .circuitMeta(10)
            .input(NANO_CENTRAL_PROCESSING_UNIT_WAFER, 9)
            .input(dust, CadmiumSelenide)
            .input(dust, IndiumGalliumPhosphide, 9)
            .fluidInputs(Radon.getFluid(450))
            .output(QUBIT_CENTRAL_PROCESSING_UNIT_WAFER, 18)
            .EUt(VA[IV])
            .duration(30 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()
    }

    private fun circuitRecipes()
    {
        // EV Quantum Processor
        GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
            arrayOf(EXTREME_CIRCUIT_BOARD.stackForm,
                QUBIT_CENTRAL_PROCESSING_UNIT.stackForm,
                NANO_CENTRAL_PROCESSING_UNIT.stackForm,
                SMD_CAPACITOR.getStackForm(12),
                SMD_TRANSISTOR.getStackForm(12),
                OreDictUnifier.get(wireFine, Platinum, 12)),
            arrayOf(SolderingAlloy.getFluid(L / 2)))

        GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
            arrayOf(EXTREME_CIRCUIT_BOARD.stackForm,
                QUBIT_CENTRAL_PROCESSING_UNIT.stackForm,
                NANO_CENTRAL_PROCESSING_UNIT.stackForm,
                SMD_CAPACITOR.getStackForm(12),
                SMD_TRANSISTOR.getStackForm(12),
                OreDictUnifier.get(wireFine, Platinum, 12)),
            arrayOf(Tin.getFluid(L)))

        GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
            arrayOf(EXTREME_CIRCUIT_BOARD.stackForm,
                QUBIT_CENTRAL_PROCESSING_UNIT.stackForm,
                NANO_CENTRAL_PROCESSING_UNIT.stackForm,
                ADVANCED_SMD_CAPACITOR.getStackForm(3),
                ADVANCED_SMD_TRANSISTOR.getStackForm(3),
                OreDictUnifier.get(wireFine, Platinum, 12)),
            arrayOf(SolderingAlloy.getFluid(L / 2)))

        GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
            arrayOf(EXTREME_CIRCUIT_BOARD.stackForm,
                QUBIT_CENTRAL_PROCESSING_UNIT.stackForm,
                NANO_CENTRAL_PROCESSING_UNIT.stackForm,
                ADVANCED_SMD_CAPACITOR.getStackForm(3),
                ADVANCED_SMD_TRANSISTOR.getStackForm(3),
                OreDictUnifier.get(wireFine, Platinum, 12)),
            arrayOf(Tin.getFluid(L)))

        GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
            arrayOf(EXTREME_CIRCUIT_BOARD.stackForm,
                ADVANCED_SYSTEM_ON_CHIP.stackForm,
                OreDictUnifier.get(wireFine, Platinum, 12),
                OreDictUnifier.get(bolt, NiobiumTitanium, 8)),
            arrayOf(SolderingAlloy.getFluid(L / 2)))

        GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
            arrayOf(EXTREME_CIRCUIT_BOARD.stackForm,
                ADVANCED_SYSTEM_ON_CHIP.stackForm,
                OreDictUnifier.get(wireFine, Platinum, 12),
                OreDictUnifier.get(bolt, NiobiumTitanium, 8)),
            arrayOf(Tin.getFluid(L)))

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
            .input(EXTREME_CIRCUIT_BOARD)
            .input(QUBIT_CENTRAL_PROCESSING_UNIT)
            .input(NANO_CENTRAL_PROCESSING_UNIT)
            .input(SMD_CAPACITOR, 12)
            .input(SMD_TRANSISTOR, 12)
            .input(wireFine, Platinum, 12)
            .output(QUANTUM_PROCESSOR_EV, 4)
            .EUt(2400) // IV
            .duration(10 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
            .input(EXTREME_CIRCUIT_BOARD)
            .input(QUBIT_CENTRAL_PROCESSING_UNIT)
            .input(NANO_CENTRAL_PROCESSING_UNIT)
            .input(ADVANCED_SMD_CAPACITOR, 3)
            .input(ADVANCED_SMD_TRANSISTOR, 3)
            .input(wireFine, Platinum, 12)
            .output(QUANTUM_PROCESSOR_EV, 4)
            .EUt(2400) // IV
            .duration(5 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
            .input(EXTREME_CIRCUIT_BOARD)
            .input(ADVANCED_SYSTEM_ON_CHIP)
            .input(wireFine, Platinum, 12)
            .input(bolt, NiobiumTitanium, 8)
            .output(QUANTUM_PROCESSOR_EV, 8)
            .EUt(38400) // ZPM
            .duration(2 * SECOND + 10 * TICK)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // IV Quantum Assembly
        GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
            arrayOf(EXTREME_CIRCUIT_BOARD.stackForm,
                QUANTUM_PROCESSOR_EV.getStackForm(2),
                SMD_INDUCTOR.getStackForm(8),
                SMD_CAPACITOR.getStackForm(16),
                RANDOM_ACCESS_MEMORY.getStackForm(4),
                OreDictUnifier.get(wireFine, Platinum, 16)),
            arrayOf(SolderingAlloy.getFluid(L)))

        GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
            arrayOf(EXTREME_CIRCUIT_BOARD.stackForm,
                QUANTUM_PROCESSOR_EV.getStackForm(2),
                SMD_INDUCTOR.getStackForm(8),
                SMD_CAPACITOR.getStackForm(16),
                RANDOM_ACCESS_MEMORY.getStackForm(4),
                OreDictUnifier.get(wireFine, Platinum, 16)),
            arrayOf(Tin.getFluid(L * 2)))

        GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
            arrayOf(EXTREME_CIRCUIT_BOARD.stackForm,
                QUANTUM_PROCESSOR_EV.getStackForm(2),
                ADVANCED_SMD_INDUCTOR.getStackForm(2),
                ADVANCED_SMD_CAPACITOR.getStackForm(4),
                RANDOM_ACCESS_MEMORY.getStackForm(4),
                OreDictUnifier.get(wireFine, Platinum, 16)),
            arrayOf(SolderingAlloy.getFluid(L)))

        GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
            arrayOf(EXTREME_CIRCUIT_BOARD.stackForm,
                QUANTUM_PROCESSOR_EV.getStackForm(2),
                ADVANCED_SMD_INDUCTOR.getStackForm(2),
                ADVANCED_SMD_CAPACITOR.getStackForm(4),
                RANDOM_ACCESS_MEMORY.getStackForm(4),
                OreDictUnifier.get(wireFine, Platinum, 16)),
            arrayOf(Tin.getFluid(L * 2)))

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
            .input(EXTREME_CIRCUIT_BOARD)
            .input(QUANTUM_PROCESSOR_EV, 4)
            .input(SMD_INDUCTOR, 8)
            .input(SMD_CAPACITOR, 16)
            .input(RANDOM_ACCESS_MEMORY, 4)
            .input(wireFine, Platinum, 16)
            .output(QUANTUM_ASSEMBLY_IV, 3)
            .EUt(2400) // IV
            .duration(20 * SECOND)
            .solderMultiplier(2)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
            .input(EXTREME_CIRCUIT_BOARD)
            .input(QUANTUM_PROCESSOR_EV, 4)
            .input(ADVANCED_SMD_INDUCTOR, 2)
            .input(ADVANCED_SMD_CAPACITOR, 4)
            .input(RANDOM_ACCESS_MEMORY, 4)
            .input(wireFine, Platinum, 16)
            .output(QUANTUM_ASSEMBLY_IV, 3)
            .EUt(2400) // IV
            .duration(10 * SECOND)
            .solderMultiplier(2)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // LuV Quantum Supercomputer
        GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
            arrayOf(EXTREME_CIRCUIT_BOARD.stackForm,
                QUANTUM_ASSEMBLY_IV.getStackForm(2),
                SMD_DIODE.getStackForm(8),
                NOR_MEMORY_CHIP.getStackForm(4),
                RANDOM_ACCESS_MEMORY.getStackForm(16),
                OreDictUnifier.get(wireFine, Platinum, 32)),
            arrayOf(SolderingAlloy.getFluid(L)))

        GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
            arrayOf(EXTREME_CIRCUIT_BOARD.stackForm,
                QUANTUM_ASSEMBLY_IV.getStackForm(2),
                SMD_DIODE.getStackForm(8),
                NOR_MEMORY_CHIP.getStackForm(4),
                RANDOM_ACCESS_MEMORY.getStackForm(16),
                OreDictUnifier.get(wireFine, Platinum, 32)),
            arrayOf(Tin.getFluid(L * 2)))

        GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
            arrayOf(EXTREME_CIRCUIT_BOARD.stackForm,
                QUANTUM_ASSEMBLY_IV.getStackForm(2),
                ADVANCED_SMD_DIODE.getStackForm(2),
                NOR_MEMORY_CHIP.getStackForm(4),
                RANDOM_ACCESS_MEMORY.getStackForm(16),
                OreDictUnifier.get(wireFine, Platinum, 32)),
            arrayOf(SolderingAlloy.getFluid(L)))

        GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
            arrayOf(EXTREME_CIRCUIT_BOARD.stackForm,
                QUANTUM_ASSEMBLY_IV.getStackForm(2),
                ADVANCED_SMD_DIODE.getStackForm(2),
                NOR_MEMORY_CHIP.getStackForm(4),
                RANDOM_ACCESS_MEMORY.getStackForm(16),
                OreDictUnifier.get(wireFine, Platinum, 32)),
            arrayOf(Tin.getFluid(L * 2)))

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
            .input(EXTREME_CIRCUIT_BOARD)
            .input(QUANTUM_ASSEMBLY_IV, 3)
            .input(SMD_DIODE, 8)
            .input(NOR_MEMORY_CHIP, 4)
            .input(RANDOM_ACCESS_MEMORY, 16)
            .input(wireFine, Platinum, 32)
            .output(QUANTUM_COMPUTER_LUV, 2)
            .EUt(2400) // IV
            .duration(20 * SECOND)
            .solderMultiplier(2)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
            .input(EXTREME_CIRCUIT_BOARD)
            .input(QUANTUM_ASSEMBLY_IV, 3)
            .input(ADVANCED_SMD_DIODE, 2)
            .input(NOR_MEMORY_CHIP, 4)
            .input(RANDOM_ACCESS_MEMORY, 16)
            .input(wireFine, Platinum, 32)
            .output(QUANTUM_COMPUTER_LUV, 2)
            .EUt(2400) // IV
            .duration(10 * SECOND)
            .solderMultiplier(2)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // ZPM Quantum Mainframe
    }

    // @formatter:on

}