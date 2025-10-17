package gregtechlite.gtlitecore.loader.recipe.circuit

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.VA
import gregtech.api.metatileentity.multiblock.CleanroomType
import gregtech.api.recipes.GTRecipeHandler
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.recipes.RecipeMaps.ASSEMBLY_LINE_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.CIRCUIT_ASSEMBLER_RECIPES
import gregtech.api.recipes.RecipeMaps.RESEARCH_STATION_RECIPES
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.MarkerMaterials
import gregtech.api.unification.material.Materials.Bacteria
import gregtech.api.unification.material.Materials.BacterialSludge
import gregtech.api.unification.material.Materials.Electrum
import gregtech.api.unification.material.Materials.EnrichedNaquadahTriniumEuropiumDuranide
import gregtech.api.unification.material.Materials.Europium
import gregtech.api.unification.material.Materials.Germanium
import gregtech.api.unification.material.Materials.HSSE
import gregtech.api.unification.material.Materials.Iron3Chloride
import gregtech.api.unification.material.Materials.Naquadah
import gregtech.api.unification.material.Materials.NiobiumTitanium
import gregtech.api.unification.material.Materials.Osmiridium
import gregtech.api.unification.material.Materials.Polybenzimidazole
import gregtech.api.unification.material.Materials.SiliconeRubber
import gregtech.api.unification.material.Materials.SodiumPersulfate
import gregtech.api.unification.material.Materials.SolderingAlloy
import gregtech.api.unification.material.Materials.SterileGrowthMedium
import gregtech.api.unification.material.Materials.Tin
import gregtech.api.unification.material.Materials.Tritanium
import gregtech.api.unification.material.Materials.YttriumBariumCuprate
import gregtech.api.unification.ore.OrePrefix.bolt
import gregtech.api.unification.ore.OrePrefix.circuit
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.foil
import gregtech.api.unification.ore.OrePrefix.frameGt
import gregtech.api.unification.ore.OrePrefix.pipeSmallFluid
import gregtech.api.unification.ore.OrePrefix.pipeTinyFluid
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.wireFine
import gregtech.api.unification.ore.OrePrefix.wireGtDouble
import gregtech.api.unification.ore.OrePrefix.wireGtSingle
import gregtech.common.items.MetaItems.ADVANCED_SMD_CAPACITOR
import gregtech.common.items.MetaItems.ADVANCED_SMD_DIODE
import gregtech.common.items.MetaItems.ADVANCED_SMD_INDUCTOR
import gregtech.common.items.MetaItems.ADVANCED_SMD_RESISTOR
import gregtech.common.items.MetaItems.ADVANCED_SMD_TRANSISTOR
import gregtech.common.items.MetaItems.CRYSTAL_CENTRAL_PROCESSING_UNIT
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_IV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_LuV
import gregtech.common.items.MetaItems.HIGHLY_ADVANCED_SOC
import gregtech.common.items.MetaItems.MULTILAYER_FIBER_BOARD
import gregtech.common.items.MetaItems.NAND_MEMORY_CHIP
import gregtech.common.items.MetaItems.NANO_CENTRAL_PROCESSING_UNIT
import gregtech.common.items.MetaItems.NEURO_PROCESSOR
import gregtech.common.items.MetaItems.NOR_MEMORY_CHIP
import gregtech.common.items.MetaItems.PETRI_DISH
import gregtech.common.items.MetaItems.QUBIT_CENTRAL_PROCESSING_UNIT
import gregtech.common.items.MetaItems.RANDOM_ACCESS_MEMORY
import gregtech.common.items.MetaItems.SENSOR_IV
import gregtech.common.items.MetaItems.STEM_CELLS
import gregtech.common.items.MetaItems.TOOL_DATA_MODULE
import gregtech.common.items.MetaItems.WETWARE_BOARD
import gregtech.common.items.MetaItems.WETWARE_CIRCUIT_BOARD
import gregtech.common.items.MetaItems.WETWARE_MAINFRAME_UHV
import gregtech.common.items.MetaItems.WETWARE_PROCESSOR_ASSEMBLY_ZPM
import gregtech.common.items.MetaItems.WETWARE_PROCESSOR_LUV
import gregtech.common.items.MetaItems.WETWARE_SUPER_COMPUTER_UV
import gregtechlite.gtlitecore.api.MINUTE
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeHandler
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CRYOGENIC_REACTOR_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.EthylenediaminePyrocatechol
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.FreeElectronGas
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.KaptonK
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TetramethylammoniumHydroxide
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ADVANCED_RAM_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ELECTRIC_SIGNAL_PETRI_DISH
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GOOWARE_SMD_CAPACITOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GOOWARE_SMD_DIODE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GOOWARE_SMD_INDUCTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GOOWARE_SMD_RESISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GOOWARE_SMD_TRANSISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.OPTICAL_SMD_CAPACITOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.OPTICAL_SMD_DIODE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.OPTICAL_SMD_INDUCTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.OPTICAL_SMD_RESISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.OPTICAL_SMD_TRANSISTOR

internal object WetwareCircuits
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
        // Wetware Circuit Board
        GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
            arrayOf(MULTILAYER_FIBER_BOARD.getStackForm(16),
                PETRI_DISH.stackForm,
                ELECTRIC_PUMP_LuV.stackForm,
                SENSOR_IV.stackForm,
                OreDictUnifier.get(circuit, MarkerMaterials.Tier.IV),
                OreDictUnifier.get(foil, NiobiumTitanium, 16)),
            arrayOf(SterileGrowthMedium.getFluid(4000)))

        // Electric Signal Petri Dish
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(PETRI_DISH)
            .input(wireFine, Germanium, 4)
            .fluidInputs(FreeElectronGas.getFluid(200))
            .output(ELECTRIC_SIGNAL_PETRI_DISH)
            .EUt(VA[IV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // Wetware Board
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
            .input(plate, KaptonK, 16)
            .input(ELECTRIC_SIGNAL_PETRI_DISH)
            .input(ELECTRIC_PUMP_IV)
            .input(SENSOR_IV)
            .input(circuit, MarkerMaterials.Tier.IV)
            .input(foil, NiobiumTitanium, 16)
            .fluidInputs(SterileGrowthMedium.getFluid(1000))
            .output(WETWARE_BOARD, 16)
            .EUt(VA[LuV])
            .duration(1 * MINUTE)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Down consumed of etching liquids and foils.
        GTLiteRecipeHandler.removeChemicalRecipes(
            arrayOf(WETWARE_BOARD.stackForm,
                OreDictUnifier.get(foil, NiobiumTitanium, 32)),
            arrayOf(SodiumPersulfate.getFluid(10000)))

        GTLiteRecipeHandler.removeChemicalRecipes(
            arrayOf(WETWARE_BOARD.stackForm,
                OreDictUnifier.get(foil, NiobiumTitanium, 32)),
            arrayOf(Iron3Chloride.getFluid(5000)))

        // Advanced etching liquids recipe addition.
        for (etchingLiquid in arrayOf(
            SodiumPersulfate.getFluid(8000),
            Iron3Chloride.getFluid(4000),
            TetramethylammoniumHydroxide.getFluid(2000),
            EthylenediaminePyrocatechol.getFluid(1000)
        ))
        {
            CHEMICAL_RECIPES.recipeBuilder()
                .input(WETWARE_BOARD)
                .input(foil, NiobiumTitanium, 24)
                .fluidInputs(etchingLiquid)
                .output(WETWARE_CIRCUIT_BOARD)
                .EUt(VA[HV])
                .duration(1 * MINUTE + 30 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()
        }

    }

    private fun circuitComponentsRecipes()
    {
        // Stem Cells
        GTLiteRecipeHandler.removeChemicalRecipes(
            arrayOf(OreDictUnifier.get(dust, Osmiridium)),
            arrayOf(SterileGrowthMedium.getFluid(500),
                Bacteria.getFluid(500)))

        CRYOGENIC_REACTOR_RECIPES.recipeBuilder()
            .input("dustBacterial")
            .fluidInputs(SterileGrowthMedium.getFluid(100))
            .fluidInputs(Bacteria.getFluid(500))
            .output(STEM_CELLS, 64)
            .fluidOutputs(BacterialSludge.getFluid(500))
            .EUt(VA[LuV])
            .duration(15 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Neuro Process
        GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
            arrayOf(WETWARE_CIRCUIT_BOARD.stackForm,
                STEM_CELLS.getStackForm(16),
                OreDictUnifier.get(pipeSmallFluid, Polybenzimidazole, 8),
                OreDictUnifier.get(plate, Electrum, 8),
                OreDictUnifier.get(foil, SiliconeRubber, 16),
                OreDictUnifier.get(bolt, HSSE, 8)),
            arrayOf(SterileGrowthMedium.getFluid(250)))

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
            .input(WETWARE_CIRCUIT_BOARD)
            .input(STEM_CELLS, 8)
            .input(pipeTinyFluid, Polybenzimidazole, 2)
            .input(plate, Electrum, 4)
            .input(foil, SiliconeRubber, 16)
            .input(bolt, HSSE, 8)
            .fluidInputs(SterileGrowthMedium.getFluid(250))
            .output(NEURO_PROCESSOR, 2)
            .EUt(80000) // ZPM
            .duration(30 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

    }

    private fun circuitRecipes()
    {
        // LuV Wetware Processor
        GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
            arrayOf(NEURO_PROCESSOR.stackForm,
                CRYSTAL_CENTRAL_PROCESSING_UNIT.stackForm,
                NANO_CENTRAL_PROCESSING_UNIT.stackForm,
                ADVANCED_SMD_CAPACITOR.getStackForm(8),
                ADVANCED_SMD_TRANSISTOR.getStackForm(8),
                OreDictUnifier.get(wireFine, YttriumBariumCuprate, 8)),
            arrayOf(SolderingAlloy.getFluid(L / 2)))

        GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
            arrayOf(NEURO_PROCESSOR.stackForm,
                CRYSTAL_CENTRAL_PROCESSING_UNIT.stackForm,
                NANO_CENTRAL_PROCESSING_UNIT.stackForm,
                ADVANCED_SMD_CAPACITOR.getStackForm(8),
                ADVANCED_SMD_TRANSISTOR.getStackForm(8),
                OreDictUnifier.get(wireFine, YttriumBariumCuprate, 8)),
            arrayOf(Tin.getFluid(L)))

        GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
            arrayOf(NEURO_PROCESSOR.stackForm,
                HIGHLY_ADVANCED_SOC.stackForm,
                OreDictUnifier.get(wireFine, YttriumBariumCuprate, 8),
                OreDictUnifier.get(bolt, Naquadah, 8)),
            arrayOf(SolderingAlloy.getFluid(L / 2)))

        GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
            arrayOf(NEURO_PROCESSOR.stackForm,
                HIGHLY_ADVANCED_SOC.stackForm,
                OreDictUnifier.get(wireFine, YttriumBariumCuprate, 8),
                OreDictUnifier.get(bolt, Naquadah, 8)),
            arrayOf(Tin.getFluid(L)))

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
            .input(NEURO_PROCESSOR)
            .input(CRYSTAL_CENTRAL_PROCESSING_UNIT)
            .input(QUBIT_CENTRAL_PROCESSING_UNIT)
            .input(ADVANCED_SMD_CAPACITOR, 8)
            .input(ADVANCED_SMD_TRANSISTOR, 8)
            .input(wireFine, YttriumBariumCuprate, 8)
            .output(WETWARE_PROCESSOR_LUV, 4)
            .EUt(38400) // ZPM
            .duration(10 * SECOND)
            .solderMultiplier(1)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
            .input(NEURO_PROCESSOR)
            .input(CRYSTAL_CENTRAL_PROCESSING_UNIT)
            .input(QUBIT_CENTRAL_PROCESSING_UNIT)
            .input(GOOWARE_SMD_CAPACITOR, 2)
            .input(GOOWARE_SMD_TRANSISTOR, 2)
            .input(wireFine, YttriumBariumCuprate, 8)
            .output(WETWARE_PROCESSOR_LUV, 4)
            .EUt(38400) // ZPM
            .duration(5 * SECOND)
            .solderMultiplier(1)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
            .input(NEURO_PROCESSOR)
            .input(HIGHLY_ADVANCED_SOC)
            .input(wireFine, YttriumBariumCuprate, 8)
            .input(bolt, Naquadah, 8)
            .output(WETWARE_PROCESSOR_LUV, 8)
            .EUt(150_000) // UV
            .duration(2 * SECOND + 10 * TICK)
            .solderMultiplier(1)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // ZPM Wetware Assembler
        GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
            arrayOf(WETWARE_CIRCUIT_BOARD.stackForm,
                WETWARE_PROCESSOR_LUV.getStackForm(2),
                ADVANCED_SMD_INDUCTOR.getStackForm(6),
                ADVANCED_SMD_CAPACITOR.getStackForm(12),
                RANDOM_ACCESS_MEMORY.getStackForm(24),
                OreDictUnifier.get(wireFine, YttriumBariumCuprate, 16)),
            arrayOf(SolderingAlloy.getFluid(L)))

        GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
            arrayOf(WETWARE_CIRCUIT_BOARD.stackForm,
                WETWARE_PROCESSOR_LUV.getStackForm(2),
                ADVANCED_SMD_INDUCTOR.getStackForm(6),
                ADVANCED_SMD_CAPACITOR.getStackForm(12),
                RANDOM_ACCESS_MEMORY.getStackForm(24),
                OreDictUnifier.get(wireFine, YttriumBariumCuprate, 16)),
            arrayOf(Tin.getFluid(L * 2)))

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
            .input(WETWARE_CIRCUIT_BOARD)
            .input(WETWARE_PROCESSOR_LUV, 4)
            .input(ADVANCED_SMD_INDUCTOR, 8)
            .input(ADVANCED_SMD_CAPACITOR, 16)
            .input(ADVANCED_RAM_CHIP, 24)
            .input(wireFine, YttriumBariumCuprate, 16)
            .output(WETWARE_PROCESSOR_ASSEMBLY_ZPM, 3)
            .EUt(38400) // ZPM
            .duration(20 * SECOND)
            .solderMultiplier(2)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
            .input(WETWARE_CIRCUIT_BOARD)
            .input(WETWARE_PROCESSOR_LUV, 4)
            .input(GOOWARE_SMD_INDUCTOR, 2)
            .input(GOOWARE_SMD_CAPACITOR, 4)
            .input(ADVANCED_RAM_CHIP, 24)
            .input(wireFine, YttriumBariumCuprate, 16)
            .output(WETWARE_PROCESSOR_ASSEMBLY_ZPM, 3)
            .EUt(38400) // ZPM
            .duration(10 * SECOND)
            .solderMultiplier(2)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // UV Wetware Computer
        GTRecipeHandler.removeRecipesByInputs(ASSEMBLY_LINE_RECIPES,
            arrayOf(WETWARE_CIRCUIT_BOARD.stackForm,
                WETWARE_PROCESSOR_ASSEMBLY_ZPM.getStackForm(2),
                ADVANCED_SMD_DIODE.getStackForm(8),
                NOR_MEMORY_CHIP.getStackForm(16),
                RANDOM_ACCESS_MEMORY.getStackForm(32),
                OreDictUnifier.get(wireFine, YttriumBariumCuprate, 24),
                OreDictUnifier.get(foil, Polybenzimidazole, 32),
                OreDictUnifier.get(plate, Europium, 4)),
            arrayOf(SolderingAlloy.getFluid(L * 8)))

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
            .input(WETWARE_CIRCUIT_BOARD)
            .input(WETWARE_PROCESSOR_ASSEMBLY_ZPM, 3)
            .input(ADVANCED_RAM_CHIP, 8)
            .input(NOR_MEMORY_CHIP, 64)
            .input(NAND_MEMORY_CHIP, 64)
            .input(wireFine, YttriumBariumCuprate, 32)
            .output(WETWARE_SUPER_COMPUTER_UV, 2)
            .EUt(38400) // ZPM
            .duration(20 * SECOND)
            .solderMultiplier(2)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // UHV Wetware Mainframe
        GTRecipeHandler.removeRecipesByInputs(RESEARCH_STATION_RECIPES,
            TOOL_DATA_MODULE.stackForm, WETWARE_SUPER_COMPUTER_UV.stackForm)

        GTRecipeHandler.removeRecipesByInputs(ASSEMBLY_LINE_RECIPES,
            arrayOf(OreDictUnifier.get(frameGt, Tritanium, 2),
                WETWARE_SUPER_COMPUTER_UV.getStackForm(2),
                ADVANCED_SMD_DIODE.getStackForm(32),
                ADVANCED_SMD_CAPACITOR.getStackForm(32),
                ADVANCED_SMD_TRANSISTOR.getStackForm(32),
                ADVANCED_SMD_RESISTOR.getStackForm(32),
                ADVANCED_SMD_INDUCTOR.getStackForm(32),
                OreDictUnifier.get(foil, Polybenzimidazole, 64),
                RANDOM_ACCESS_MEMORY.getStackForm(32),
                OreDictUnifier.get(wireGtDouble, EnrichedNaquadahTriniumEuropiumDuranide, 16),
                OreDictUnifier.get(plate, Europium, 8)),
            arrayOf(SolderingAlloy.getFluid(L * 20),
                Polybenzimidazole.getFluid(L * 8)))

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, Tritanium)
            .input(WETWARE_SUPER_COMPUTER_UV, 2)
            .input(ADVANCED_SMD_DIODE, 32)
            .input(ADVANCED_SMD_CAPACITOR, 32)
            .input(ADVANCED_SMD_TRANSISTOR, 32)
            .input(ADVANCED_SMD_RESISTOR, 32)
            .input(ADVANCED_SMD_INDUCTOR, 32)
            .input(foil, Polybenzimidazole, 64)
            .input(ADVANCED_RAM_CHIP, 32)
            .input(wireGtSingle, EnrichedNaquadahTriniumEuropiumDuranide, 16)
            .input(plate, Europium, 4)
            .fluidInputs(SolderingAlloy.getFluid(L * 20))
            .fluidInputs(Polybenzimidazole.getFluid(L * 8))
            .output(WETWARE_MAINFRAME_UHV)
            .EUt(300_000) // UV
            .duration(1 * MINUTE)
            .stationResearch { r ->
                r.researchStack(WETWARE_SUPER_COMPUTER_UV.stackForm)
                    .EUt(VA[UV])
                    .CWUt(32)
            }
            .buildAndRegister()

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, Tritanium)
            .input(WETWARE_SUPER_COMPUTER_UV, 2)
            .input(GOOWARE_SMD_DIODE, 8)
            .input(GOOWARE_SMD_CAPACITOR, 8)
            .input(GOOWARE_SMD_TRANSISTOR, 8)
            .input(GOOWARE_SMD_RESISTOR, 8)
            .input(GOOWARE_SMD_INDUCTOR, 8)
            .input(foil, Polybenzimidazole, 64)
            .input(ADVANCED_RAM_CHIP, 32)
            .input(wireGtSingle, EnrichedNaquadahTriniumEuropiumDuranide, 16)
            .input(plate, Europium, 4)
            .fluidInputs(SolderingAlloy.getFluid(L * 20))
            .fluidInputs(Polybenzimidazole.getFluid(L * 8))
            .output(WETWARE_MAINFRAME_UHV)
            .EUt(300_000) // UV
            .duration(30 * SECOND)
            .stationResearch { r ->
                r.researchStack(WETWARE_SUPER_COMPUTER_UV.stackForm)
                    .EUt(VA[UV])
                    .CWUt(32)
            }
            .buildAndRegister()

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, Tritanium)
            .input(WETWARE_SUPER_COMPUTER_UV, 2)
            .input(OPTICAL_SMD_DIODE, 2)
            .input(OPTICAL_SMD_CAPACITOR, 2)
            .input(OPTICAL_SMD_TRANSISTOR, 2)
            .input(OPTICAL_SMD_RESISTOR, 2)
            .input(OPTICAL_SMD_INDUCTOR, 2)
            .input(foil, Polybenzimidazole, 64)
            .input(ADVANCED_RAM_CHIP, 32)
            .input(wireGtSingle, EnrichedNaquadahTriniumEuropiumDuranide, 16)
            .input(plate, Europium, 4)
            .fluidInputs(SolderingAlloy.getFluid(L * 20))
            .fluidInputs(Polybenzimidazole.getFluid(L * 8))
            .output(WETWARE_MAINFRAME_UHV)
            .EUt(300_000) // UV
            .duration(15 * SECOND)
            .stationResearch { r ->
                r.researchStack(WETWARE_SUPER_COMPUTER_UV.stackForm)
                    .EUt(VA[UV])
                    .CWUt(32)
            }
            .buildAndRegister()

    }

    // @formatter:on

}