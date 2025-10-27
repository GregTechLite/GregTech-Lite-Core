package gregtechlite.gtlitecore.loader.recipe.circuit

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.ZPM
import gregtech.api.metatileentity.multiblock.CleanroomType
import gregtech.api.recipes.GTRecipeHandler
import gregtech.api.recipes.RecipeMaps.ASSEMBLY_LINE_RECIPES
import gregtech.api.recipes.RecipeMaps.BLAST_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.CIRCUIT_ASSEMBLER_RECIPES
import gregtech.api.recipes.RecipeMaps.CUTTER_RECIPES
import gregtech.api.recipes.RecipeMaps.FORMING_PRESS_RECIPES
import gregtech.api.recipes.RecipeMaps.LASER_ENGRAVER_RECIPES
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.MarkerMaterials
import gregtech.api.unification.material.Materials.Europium
import gregtech.api.unification.material.Materials.HSSE
import gregtech.api.unification.material.Materials.Helium
import gregtech.api.unification.material.Materials.IndiumTinBariumTitaniumCuprate
import gregtech.api.unification.material.Materials.Iron3Chloride
import gregtech.api.unification.material.Materials.Lubricant
import gregtech.api.unification.material.Materials.NetherStar
import gregtech.api.unification.material.Materials.NiobiumTitanium
import gregtech.api.unification.material.Materials.Palladium
import gregtech.api.unification.material.Materials.Platinum
import gregtech.api.unification.material.Materials.PolyvinylButyral
import gregtech.api.unification.material.Materials.SodiumPersulfate
import gregtech.api.unification.material.Materials.SolderingAlloy
import gregtech.api.unification.material.Materials.Tin
import gregtech.api.unification.material.Materials.YttriumBariumCuprate
import gregtech.api.unification.ore.OrePrefix.bolt
import gregtech.api.unification.ore.OrePrefix.craftingLens
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.foil
import gregtech.api.unification.ore.OrePrefix.frameGt
import gregtech.api.unification.ore.OrePrefix.lens
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.wireFine
import gregtech.api.unification.ore.OrePrefix.wireGtSingle
import gregtech.common.items.MetaItems.ADVANCED_SMD_CAPACITOR
import gregtech.common.items.MetaItems.ADVANCED_SMD_DIODE
import gregtech.common.items.MetaItems.ADVANCED_SMD_INDUCTOR
import gregtech.common.items.MetaItems.ADVANCED_SMD_TRANSISTOR
import gregtech.common.items.MetaItems.CRYSTAL_ASSEMBLY_LUV
import gregtech.common.items.MetaItems.CRYSTAL_CENTRAL_PROCESSING_UNIT
import gregtech.common.items.MetaItems.CRYSTAL_COMPUTER_ZPM
import gregtech.common.items.MetaItems.CRYSTAL_MAINFRAME_UV
import gregtech.common.items.MetaItems.CRYSTAL_PROCESSOR_IV
import gregtech.common.items.MetaItems.CRYSTAL_SYSTEM_ON_CHIP
import gregtech.common.items.MetaItems.ELITE_CIRCUIT_BOARD
import gregtech.common.items.MetaItems.ENGRAVED_CRYSTAL_CHIP
import gregtech.common.items.MetaItems.HIGH_POWER_INTEGRATED_CIRCUIT
import gregtech.common.items.MetaItems.MULTILAYER_FIBER_BOARD
import gregtech.common.items.MetaItems.NAND_MEMORY_CHIP
import gregtech.common.items.MetaItems.NANO_CENTRAL_PROCESSING_UNIT
import gregtech.common.items.MetaItems.NOR_MEMORY_CHIP
import gregtech.common.items.MetaItems.PLASTIC_CIRCUIT_BOARD
import gregtech.common.items.MetaItems.RANDOM_ACCESS_MEMORY
import gregtech.common.items.MetaItems.RAW_CRYSTAL_CHIP
import gregtechlite.gtlitecore.api.MINUTE
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeHandler
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CRYSTALLIZATION_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.MOLECULAR_BEAM_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Aegirine
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CubicZirconia
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ErbiumDopedZBLANGlass
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.EthylenediaminePyrocatechol
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Forsterite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Jade
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PraseodymiumDopedZBLANGlass
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Prasiolite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TantalumPentoxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TetramethylammoniumHydroxide
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CRYSTAL_INTERFACE_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CRYSTAL_INTERFACE_WAFER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CRYSTAL_SOC_SOCKET
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.DIAMOND_MODULATOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.DIELECTRIC_MIRROR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ENGRAVED_DIAMOND_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ENGRAVED_RUBY_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ENGRAVED_SAPPHIRE_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.EUROPIUM_DOPED_CUBIC_ZIRCONIA_BOULE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.EUROPIUM_DOPED_CUBIC_ZIRCONIA_WAFER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GOOWARE_SMD_CAPACITOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GOOWARE_SMD_DIODE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GOOWARE_SMD_INDUCTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GOOWARE_SMD_TRANSISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.OPTICAL_SMD_CAPACITOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.OPTICAL_SMD_DIODE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.OPTICAL_SMD_INDUCTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.RUBY_MODULATOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SAPPHIRE_MODULATOR

internal object CrystalCircuits
{

    // @formatter:off

    fun init()
    {
        circuitBoardRecipes()
        circuitComponentsRecipes()
        systemOnChipRecipes()
        circuitRecipes()
    }

    private fun circuitBoardRecipes()
    {
        // Redo foil consumed amounts from 8 to 16 to ensure it has priority with
        // other circuit boards.
        GTLiteRecipeHandler.removeChemicalRecipes(
            arrayOf(MULTILAYER_FIBER_BOARD.stackForm,
                OreDictUnifier.get(foil, Platinum, 8)),
            arrayOf(SodiumPersulfate.getFluid(4000)))

        GTLiteRecipeHandler.removeChemicalRecipes(
            arrayOf(MULTILAYER_FIBER_BOARD.stackForm,
                OreDictUnifier.get(foil, Platinum, 8)),
            arrayOf(Iron3Chloride.getFluid(2000)))

        // Advanced etching liquids recipe addition.
        for (etchingLiquid in arrayOf(
            SodiumPersulfate.getFluid(4000),
            Iron3Chloride.getFluid(2000),
            TetramethylammoniumHydroxide.getFluid(1000),
            EthylenediaminePyrocatechol.getFluid(500)))
        {
            CHEMICAL_RECIPES.recipeBuilder()
                .input(MULTILAYER_FIBER_BOARD)
                .input(foil, Platinum, 16)
                .fluidInputs(etchingLiquid)
                .output(ELITE_CIRCUIT_BOARD)
                .EUt(VA[MV])
                .duration(1 * MINUTE + 15 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()
        }
    }

    private fun circuitComponentsRecipes()
    {
        // Gem addition of engraved crystal chip.
        BLAST_RECIPES.recipeBuilder()
            .input(plate, Forsterite)
            .input(RAW_CRYSTAL_CHIP)
            .fluidInputs(Helium.getFluid(1000))
            .output(ENGRAVED_CRYSTAL_CHIP)
            .EUt(VA[HV])
            .duration(45 * SECOND)
            .blastFurnaceTemp(5000) // HSSG
            .buildAndRegister()

        BLAST_RECIPES.recipeBuilder()
            .input(plate, Aegirine)
            .input(RAW_CRYSTAL_CHIP)
            .fluidInputs(Helium.getFluid(1000))
            .output(ENGRAVED_CRYSTAL_CHIP)
            .EUt(VA[HV])
            .duration(45 * SECOND)
            .blastFurnaceTemp(5000) // HSSG
            .buildAndRegister()

        BLAST_RECIPES.recipeBuilder()
            .input(plate, Jade)
            .input(RAW_CRYSTAL_CHIP)
            .fluidInputs(Helium.getFluid(1000))
            .output(ENGRAVED_CRYSTAL_CHIP)
            .EUt(VA[HV])
            .duration(45 * SECOND)
            .blastFurnaceTemp(5000) // HSSG
            .buildAndRegister()

        BLAST_RECIPES.recipeBuilder()
            .input(plate, Prasiolite)
            .input(RAW_CRYSTAL_CHIP)
            .fluidInputs(Helium.getFluid(1000))
            .output(ENGRAVED_CRYSTAL_CHIP)
            .EUt(VA[HV])
            .duration(45 * SECOND)
            .blastFurnaceTemp(5000) // HSSG
            .buildAndRegister()

        // Dielectric Mirror
        MOLECULAR_BEAM_RECIPES.recipeBuilder()
            .input(foil, PolyvinylButyral)
            .input(dust, ErbiumDopedZBLANGlass, 2)
            .input(dust, PraseodymiumDopedZBLANGlass, 2)
            .input(dust, TantalumPentoxide, 7)
            .output(DIELECTRIC_MIRROR)
            .EUt(VA[LuV])
            .duration(30 * SECOND)
            .temperature(2820)
            .buildAndRegister()

        // Crystal Central Processing Unit (Crystal CPU)
        GTRecipeHandler.removeRecipesByInputs(LASER_ENGRAVER_RECIPES,
            OreDictUnifier.get(craftingLens, MarkerMaterials.Color.Lime),
            ENGRAVED_CRYSTAL_CHIP.stackForm)

        LASER_ENGRAVER_RECIPES.recipeBuilder()
            .notConsumable(DIELECTRIC_MIRROR)
            .notConsumable(craftingLens, MarkerMaterials.Color.Lime)
            .input(ENGRAVED_CRYSTAL_CHIP)
            .output(CRYSTAL_CENTRAL_PROCESSING_UNIT)
            .EUt(10000) // LuV
            .duration(5 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()
    }

    private fun systemOnChipRecipes()
    {
        // Deleted original SoC recipes.
        GTRecipeHandler.removeRecipesByInputs(LASER_ENGRAVER_RECIPES,
            CRYSTAL_CENTRAL_PROCESSING_UNIT.stackForm,
            OreDictUnifier.get(craftingLens, MarkerMaterials.Color.Blue))

        // c-ZrO2 + Eu -> Eu-doped c-ZrO2 Boule
        CRYSTALLIZATION_RECIPES.recipeBuilder()
            .input(dust, CubicZirconia, 64)
            .input(dust, Europium, 8)
            .output(EUROPIUM_DOPED_CUBIC_ZIRCONIA_BOULE)
            .EUt(VA[MV])
            .duration(6 * SECOND)
            .blastFurnaceTemp(3000) // Nichrome
            .buildAndRegister()

        // Eu-doped c-ZrO2 Boule -> Eu-doped c-ZrO2 Wafer
        CUTTER_RECIPES.recipeBuilder()
            .input(EUROPIUM_DOPED_CUBIC_ZIRCONIA_BOULE)
            .fluidInputs(Lubricant.getFluid(100))
            .output(EUROPIUM_DOPED_CUBIC_ZIRCONIA_WAFER, 8)
            .EUt(VA[HV])
            .duration(5 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Eu-doped c-ZrO2 Wafer -> Crystal Interface Wafer
        LASER_ENGRAVER_RECIPES.recipeBuilder()
            .notConsumable(lens, NetherStar)
            .input(EUROPIUM_DOPED_CUBIC_ZIRCONIA_WAFER)
            .output(CRYSTAL_INTERFACE_WAFER)
            .EUt(VA[LuV])
            .duration(1 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Crystal Interface Wafer -> Crystal Interface Chip
        CUTTER_RECIPES.recipeBuilder()
            .input(CRYSTAL_INTERFACE_WAFER)
            .fluidInputs(Lubricant.getFluid(100))
            .output(CRYSTAL_INTERFACE_CHIP, 8)
            .EUt(VA[HV])
            .duration(5 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Diamond Modulator
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
            .input(ENGRAVED_DIAMOND_CHIP)
            .input(PLASTIC_CIRCUIT_BOARD)
            .input(wireFine, Palladium, 8)
            .input(bolt, Platinum, 4)
            .output(DIAMOND_MODULATOR, 8)
            .EUt(VA[IV])
            .duration(10 * SECOND)
            .solderMultiplier(1)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Ruby Modulator
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
            .input(ENGRAVED_RUBY_CHIP)
            .input(PLASTIC_CIRCUIT_BOARD)
            .input(wireFine, Palladium, 8)
            .input(bolt, Platinum, 4)
            .output(RUBY_MODULATOR, 8)
            .EUt(VA[IV])
            .duration(10 * SECOND)
            .solderMultiplier(1)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Sapphire Modulator
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
            .input(ENGRAVED_SAPPHIRE_CHIP)
            .input(PLASTIC_CIRCUIT_BOARD)
            .input(wireFine, Palladium, 8)
            .input(bolt, Platinum, 4)
            .output(SAPPHIRE_MODULATOR, 8)
            .EUt(VA[IV])
            .duration(10 * SECOND)
            .solderMultiplier(1)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Crystal SoC Socket
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
            .input(CRYSTAL_INTERFACE_CHIP)
            .input(DIAMOND_MODULATOR)
            .input(RUBY_MODULATOR)
            .input(SAPPHIRE_MODULATOR)
            .input(wireFine, Europium, 4)
            .output(CRYSTAL_SOC_SOCKET)
            .EUt(VA[LuV])
            .duration(5 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Crystal SoC
        FORMING_PRESS_RECIPES.recipeBuilder()
            .input(CRYSTAL_SOC_SOCKET)
            .input(CRYSTAL_CENTRAL_PROCESSING_UNIT)
            .output(CRYSTAL_SYSTEM_ON_CHIP)
            .EUt(VA[ZPM])
            .duration(5 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

    }

    private fun circuitRecipes()
    {

        // IV Crystal Processor
        GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
            arrayOf(ELITE_CIRCUIT_BOARD.stackForm,
                CRYSTAL_CENTRAL_PROCESSING_UNIT.stackForm,
                NANO_CENTRAL_PROCESSING_UNIT.getStackForm(2),
                ADVANCED_SMD_CAPACITOR.getStackForm(6),
                ADVANCED_SMD_TRANSISTOR.getStackForm(6),
                OreDictUnifier.get(wireFine, NiobiumTitanium, 8)),
            arrayOf(SolderingAlloy.getFluid(L / 2)))

        GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
            arrayOf(ELITE_CIRCUIT_BOARD.stackForm,
                CRYSTAL_CENTRAL_PROCESSING_UNIT.stackForm,
                NANO_CENTRAL_PROCESSING_UNIT.getStackForm(2),
                ADVANCED_SMD_CAPACITOR.getStackForm(6),
                ADVANCED_SMD_TRANSISTOR.getStackForm(6),
                OreDictUnifier.get(wireFine, NiobiumTitanium, 8)),
            arrayOf(Tin.getFluid(L)))

        GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
            arrayOf(ELITE_CIRCUIT_BOARD.stackForm,
                CRYSTAL_SYSTEM_ON_CHIP.stackForm,
                OreDictUnifier.get(wireFine, NiobiumTitanium, 8),
                OreDictUnifier.get(bolt, YttriumBariumCuprate, 8)),
            arrayOf(SolderingAlloy.getFluid(L / 2)))

        GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
            arrayOf(ELITE_CIRCUIT_BOARD.stackForm,
                CRYSTAL_SYSTEM_ON_CHIP.stackForm,
                OreDictUnifier.get(wireFine, NiobiumTitanium, 8),
                OreDictUnifier.get(bolt, YttriumBariumCuprate, 8)),
            arrayOf(Tin.getFluid(L)))

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
            .input(ELITE_CIRCUIT_BOARD)
            .input(CRYSTAL_CENTRAL_PROCESSING_UNIT)
            .input(NANO_CENTRAL_PROCESSING_UNIT, 2)
            .input(ADVANCED_SMD_CAPACITOR, 4)
            .input(ADVANCED_SMD_TRANSISTOR, 4)
            .input(wireFine, NiobiumTitanium, 8)
            .output(CRYSTAL_PROCESSOR_IV, 4)
            .EUt(9600) // LuV
            .duration(10 * SECOND)
            .solderMultiplier(1)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
            .input(ELITE_CIRCUIT_BOARD)
            .input(CRYSTAL_CENTRAL_PROCESSING_UNIT)
            .input(NANO_CENTRAL_PROCESSING_UNIT, 2)
            .input(GOOWARE_SMD_CAPACITOR)
            .input(GOOWARE_SMD_TRANSISTOR)
            .input(wireFine, NiobiumTitanium, 8)
            .output(CRYSTAL_PROCESSOR_IV, 4)
            .EUt(9600) // LuV
            .duration(5 * SECOND)
            .solderMultiplier(1)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
            .input(ELITE_CIRCUIT_BOARD)
            .input(CRYSTAL_SYSTEM_ON_CHIP)
            .input(wireFine, NiobiumTitanium, 8)
            .input(bolt, YttriumBariumCuprate, 8)
            .output(CRYSTAL_PROCESSOR_IV, 8)
            .EUt(86000) // ZPM
            .duration(2 * SECOND + 10 * TICK)
            .solderMultiplier(1)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // LuV Crystal Processor Assembly
        GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
            arrayOf(ELITE_CIRCUIT_BOARD.stackForm,
                CRYSTAL_PROCESSOR_IV.getStackForm(2),
                ADVANCED_SMD_INDUCTOR.getStackForm(4),
                ADVANCED_SMD_CAPACITOR.getStackForm(8),
                RANDOM_ACCESS_MEMORY.getStackForm(24),
                OreDictUnifier.get(wireFine, NiobiumTitanium, 16)),
            arrayOf(SolderingAlloy.getFluid(L)))

        GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
            arrayOf(ELITE_CIRCUIT_BOARD.stackForm,
                CRYSTAL_PROCESSOR_IV.getStackForm(2),
                ADVANCED_SMD_INDUCTOR.getStackForm(4),
                ADVANCED_SMD_CAPACITOR.getStackForm(8),
                RANDOM_ACCESS_MEMORY.getStackForm(24),
                OreDictUnifier.get(wireFine, NiobiumTitanium, 16)),
            arrayOf(Tin.getFluid(L * 2)))

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
            .input(ELITE_CIRCUIT_BOARD)
            .input(CRYSTAL_PROCESSOR_IV, 4)
            .input(ADVANCED_SMD_INDUCTOR, 4)
            .input(ADVANCED_SMD_CAPACITOR, 8)
            .input(RANDOM_ACCESS_MEMORY, 24)
            .input(wireFine, NiobiumTitanium, 16)
            .output(CRYSTAL_ASSEMBLY_LUV, 3)
            .EUt(9600) // LuV
            .duration(20 * SECOND)
            .solderMultiplier(2)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
            .input(ELITE_CIRCUIT_BOARD)
            .input(CRYSTAL_PROCESSOR_IV, 4)
            .input(GOOWARE_SMD_INDUCTOR)
            .input(GOOWARE_SMD_CAPACITOR, 2)
            .input(RANDOM_ACCESS_MEMORY, 24)
            .input(wireFine, NiobiumTitanium, 16)
            .output(CRYSTAL_ASSEMBLY_LUV, 3)
            .EUt(9600) // LuV
            .duration(10 * SECOND)
            .solderMultiplier(2)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // ZPM Crystal Supercomputer
        GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
            arrayOf(ELITE_CIRCUIT_BOARD.stackForm,
                CRYSTAL_ASSEMBLY_LUV.getStackForm(2),
                RANDOM_ACCESS_MEMORY.getStackForm(4),
                NOR_MEMORY_CHIP.getStackForm(32),
                NAND_MEMORY_CHIP.getStackForm(64),
                OreDictUnifier.get(wireFine, NiobiumTitanium, 32)),
            arrayOf(SolderingAlloy.getFluid(L)))

        GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
            arrayOf(ELITE_CIRCUIT_BOARD.stackForm,
                CRYSTAL_ASSEMBLY_LUV.getStackForm(2),
                RANDOM_ACCESS_MEMORY.getStackForm(4),
                NOR_MEMORY_CHIP.getStackForm(32),
                NAND_MEMORY_CHIP.getStackForm(64),
                OreDictUnifier.get(wireFine, NiobiumTitanium, 32)),
            arrayOf(Tin.getFluid(L * 2)))

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
            .input(ELITE_CIRCUIT_BOARD)
            .input(CRYSTAL_ASSEMBLY_LUV, 3)
            .input(RANDOM_ACCESS_MEMORY, 4)
            .input(NOR_MEMORY_CHIP, 32)
            .input(NAND_MEMORY_CHIP, 64)
            .input(wireFine, NiobiumTitanium, 32)
            .output(CRYSTAL_COMPUTER_ZPM, 2)
            .EUt(9600) // LuV
            .duration(20 * SECOND)
            .solderMultiplier(2)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // UV Crystal Mainframe
        GTRecipeHandler.removeRecipesByInputs(ASSEMBLY_LINE_RECIPES,
            arrayOf(OreDictUnifier.get(frameGt, HSSE, 2),
                CRYSTAL_COMPUTER_ZPM.getStackForm(2),
                RANDOM_ACCESS_MEMORY.getStackForm(32),
                HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(2),
                OreDictUnifier.get(wireGtSingle, NiobiumTitanium, 8),
                ADVANCED_SMD_INDUCTOR.getStackForm(8),
                ADVANCED_SMD_CAPACITOR.getStackForm(16),
                ADVANCED_SMD_DIODE.getStackForm(8)),
            arrayOf(SolderingAlloy.getFluid(L * 10)))

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, HSSE)
            .input(CRYSTAL_COMPUTER_ZPM, 2)
            .input(ADVANCED_SMD_INDUCTOR, 16)
            .input(ADVANCED_SMD_CAPACITOR, 16)
            .input(ADVANCED_SMD_DIODE, 16)
            .input(HIGH_POWER_INTEGRATED_CIRCUIT, 2)
            .input(RANDOM_ACCESS_MEMORY, 32)
            .input(wireGtSingle, IndiumTinBariumTitaniumCuprate, 8)
            .fluidInputs(SolderingAlloy.getFluid(L * 10))
            .output(CRYSTAL_MAINFRAME_UV)
            .EUt(VA[LuV])
            .duration(40 * SECOND)
            .stationResearch {
                it.researchStack(CRYSTAL_COMPUTER_ZPM)
                    .EUt(VA[LuV])
                    .CWUt(16)
            }
            .buildAndRegister()

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, HSSE)
            .input(CRYSTAL_COMPUTER_ZPM, 2)
            .input(GOOWARE_SMD_INDUCTOR, 4)
            .input(GOOWARE_SMD_CAPACITOR, 4)
            .input(GOOWARE_SMD_DIODE, 4)
            .input(HIGH_POWER_INTEGRATED_CIRCUIT, 2)
            .input(RANDOM_ACCESS_MEMORY, 32)
            .input(wireGtSingle, IndiumTinBariumTitaniumCuprate, 8)
            .fluidInputs(SolderingAlloy.getFluid(L * 10))
            .output(CRYSTAL_MAINFRAME_UV)
            .EUt(VA[LuV])
            .duration(20 * SECOND)
            .stationResearch {
                it.researchStack(CRYSTAL_COMPUTER_ZPM)
                    .EUt(VA[LuV])
                    .CWUt(16)
            }
            .buildAndRegister()

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, HSSE)
            .input(CRYSTAL_COMPUTER_ZPM, 2)
            .input(OPTICAL_SMD_INDUCTOR)
            .input(OPTICAL_SMD_CAPACITOR)
            .input(OPTICAL_SMD_DIODE)
            .input(HIGH_POWER_INTEGRATED_CIRCUIT, 2)
            .input(RANDOM_ACCESS_MEMORY, 32)
            .input(wireGtSingle, IndiumTinBariumTitaniumCuprate, 8)
            .fluidInputs(SolderingAlloy.getFluid(L * 10))
            .output(CRYSTAL_MAINFRAME_UV)
            .EUt(VA[LuV])
            .duration(10 * SECOND)
            .stationResearch {
                it.researchStack(CRYSTAL_COMPUTER_ZPM)
                    .EUt(VA[LuV])
                    .CWUt(16)
            }
            .buildAndRegister()

    }

    // @formatter:on

}