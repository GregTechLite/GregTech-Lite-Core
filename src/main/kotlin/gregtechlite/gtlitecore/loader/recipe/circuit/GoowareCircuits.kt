package gregtechlite.gtlitecore.loader.recipe.circuit

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.VHA
import gregtech.api.GTValues.ZPM
import gregtech.api.metatileentity.multiblock.CleanroomType
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.recipes.RecipeMaps.ASSEMBLY_LINE_RECIPES
import gregtech.api.recipes.RecipeMaps.CANNER_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.CIRCUIT_ASSEMBLER_RECIPES
import gregtech.api.unification.material.Materials.Aluminium
import gregtech.api.unification.material.Materials.Americium
import gregtech.api.unification.material.Materials.Barium
import gregtech.api.unification.material.Materials.Copper
import gregtech.api.unification.material.Materials.Darmstadtium
import gregtech.api.unification.material.Materials.Europium
import gregtech.api.unification.material.Materials.Gallium
import gregtech.api.unification.material.Materials.Glycerol
import gregtech.api.unification.material.Materials.HydrochloricAcid
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.Indium
import gregtech.api.unification.material.Materials.Iridium
import gregtech.api.unification.material.Materials.Naquadah
import gregtech.api.unification.material.Materials.Neutronium
import gregtech.api.unification.material.Materials.Nitrobenzene
import gregtech.api.unification.material.Materials.Osmiridium
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Ruridit
import gregtech.api.unification.material.Materials.RutheniumTriniumAmericiumNeutronate
import gregtech.api.unification.material.Materials.SamariumMagnetic
import gregtech.api.unification.material.Materials.Selenium
import gregtech.api.unification.material.Materials.SolderingAlloy
import gregtech.api.unification.material.Materials.Steam
import gregtech.api.unification.material.Materials.TitaniumTetrachloride
import gregtech.api.unification.material.Materials.Trinium
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.material.Materials.YttriumBariumCuprate
import gregtech.api.unification.ore.OrePrefix.bolt
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.foil
import gregtech.api.unification.ore.OrePrefix.frameGt
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.ring
import gregtech.api.unification.ore.OrePrefix.rotor
import gregtech.api.unification.ore.OrePrefix.stick
import gregtech.api.unification.ore.OrePrefix.wireFine
import gregtech.api.unification.ore.OrePrefix.wireGtDouble
import gregtech.common.items.MetaItems.CRYSTAL_CENTRAL_PROCESSING_UNIT
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_LuV
import gregtech.common.items.MetaItems.FLUID_CELL_LARGE_STAINLESS_STEEL
import gregtech.common.items.MetaItems.NOR_MEMORY_CHIP
import gregtechlite.gtlitecore.api.MINUTE
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.SU
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.BURNER_REACTOR_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CRYOGENIC_REACTOR_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CVD_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.ROASTER_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Aminophenol
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BZMedium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BariumHydroxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BariumTitanate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CopperGalliumIndiumSelenide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CubicZirconia
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.EthylenediaminePyrocatechol
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.FluorinatedEthylenePropylene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HydroselenicAcid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Hydroxyquinoline
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HydroxyquinolineAluminium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.KaptonE
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.KaptonK
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PedotPSS
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PedotTMA
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SamariumCobalt
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SelenousAcid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TetramethylammoniumHydroxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ZBLANGlass
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ADVANCED_RAM_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.BZ_REACTION_CHAMBER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.COSMIC_SMD_CAPACITOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.COSMIC_SMD_DIODE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.COSMIC_SMD_INDUCTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.COSMIC_SMD_RESISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.COSMIC_SMD_TRANSISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GOOWARE_ASSEMBLY_UV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GOOWARE_BOARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GOOWARE_COMPUTER_UHV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GOOWARE_MAINFRAME_UEV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GOOWARE_PROCESSOR_ZPM
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GOOWARE_SMD_CAPACITOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GOOWARE_SMD_DIODE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GOOWARE_SMD_INDUCTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GOOWARE_SMD_RESISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GOOWARE_SMD_TRANSISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.NONLINEAR_CHEMICAL_OSCILLATOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.OPTICAL_SMD_CAPACITOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.OPTICAL_SMD_DIODE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.OPTICAL_SMD_INDUCTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.OPTICAL_SMD_RESISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.OPTICAL_SMD_TRANSISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SPINTRONIC_SMD_CAPACITOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SPINTRONIC_SMD_DIODE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SPINTRONIC_SMD_INDUCTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SPINTRONIC_SMD_RESISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SPINTRONIC_SMD_TRANSISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ULTIMATE_CIRCUIT_BOARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ULTRA_HIGHLY_ADVANCED_SOC_CHIP

/**
 * @see [gregtechlite.gtlitecore.loader.recipe.chain.BZMediumChain]
 */
internal object GoowareCircuits
{

    // @formatter:off

    fun init()
    {
        circuitBoardRecipes()
        smdRecipes()
        circuitComponentsRecipes()
        circuitRecipes()
    }

    private fun circuitBoardRecipes()
    {
        // Gooware Board
        CVD_RECIPES.recipeBuilder()
            .input(plate, KaptonE)
            .input(foil, YttriumBariumCuprate, 24)
            .fluidInputs(FluorinatedEthylenePropylene.getFluid(L))
            .output(GOOWARE_BOARD)
            .EUt(VA[UV])
            .duration(2 * SECOND)
            .temperature(493)
            .buildAndRegister()

        // Gooware Circuit Board
        for (etchingLiquid in arrayOf(
            // SodiumPersulfate.getFluid(16000),
            // Iron3Chloride.getFluid(8000) // 4000, 2000
            TetramethylammoniumHydroxide.getFluid(16000),
            EthylenediaminePyrocatechol.getFluid(8000)))
        {
            CHEMICAL_RECIPES.recipeBuilder()
                .input(GOOWARE_BOARD)
                .input(foil, Europium, 32)
                .fluidInputs(etchingLiquid)
                .output(ULTIMATE_CIRCUIT_BOARD)
                .EUt(VA[EV])
                .duration(1 * MINUTE + 45 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()
        }
    }

    private fun smdRecipes()
    {
        // C6H4(OH)(NH2) + C3H8O3 + O -> C9H7NO + 4H2O
        CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, Aminophenol, 15)
            .notConsumable(Nitrobenzene.getFluid(1))
            .fluidInputs(Glycerol.getFluid(1000))
            .fluidInputs(Oxygen.getFluid(1000))
            .output(dust, Hydroxyquinoline, 18)
            .fluidOutputs(Water.getFluid(4000))
            .EUt(VA[IV])
            .duration(13 * SECOND)
            .buildAndRegister()

        // Al + C9H7NO -> Al(C9H7NO)
        CRYOGENIC_REACTOR_RECIPES.recipeBuilder()
            .input(dust, Aluminium, 1)
            .input(dust, Hydroxyquinoline, 18)
            .output(dust, HydroxyquinolineAluminium, 19)
            .EUt(VA[ZPM])
            .duration(8 * SECOND + 10 * TICK)
            .buildAndRegister()

        // Another recipe for H2SeO3 for these chemistry processing.
        // Se + H2O + 2O -> H2SeO3
        CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, Selenium)
            .fluidInputs(Water.getFluid(1000))
            .fluidInputs(Oxygen.getFluid(2000))
            .output(dust, SelenousAcid, 6)
            .EUt(VA[HV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // H2SeO3 + O -> H2SeO4
        ROASTER_RECIPES.recipeBuilder()
            .input(dust, SelenousAcid, 6)
            .fluidInputs(Oxygen.getFluid(1000))
            .fluidOutputs(HydroselenicAcid.getFluid(1000))
            .EUt(VA[MV])
            .duration(2 * SECOND)
            .buildAndRegister()

        // Cu + Ga + In + 2H2SeO4 -> CuGaInSe2 + 2H2O + 6O
        BURNER_REACTOR_RECIPES.recipeBuilder()
            .input(dust, Copper)
            .input(dust, Gallium)
            .input(dust, Indium)
            .fluidInputs(HydroselenicAcid.getFluid(2000))
            .output(dust, CopperGalliumIndiumSelenide, 5)
            .fluidOutputs(Oxygen.getFluid(6000))
            .fluidOutputs(Steam.getFluid(2 * SU))
            .EUt(VA[LuV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // Gooware SMD Transistor
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(5)
            .input(foil, HydroxyquinolineAluminium)
            .input(wireFine, CopperGalliumIndiumSelenide, 8)
            .fluidInputs(KaptonK.getFluid(L))
            .output(GOOWARE_SMD_TRANSISTOR, 16)
            .EUt(VA[ZPM])
            .duration(8 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Gooware SMD Resistor
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(5)
            .input(dust, ZBLANGlass)
            .input(wireFine, Osmiridium, 4)
            .fluidInputs(KaptonK.getFluid(L * 2))
            .output(GOOWARE_SMD_RESISTOR, 16)
            .EUt(VA[ZPM])
            .duration(8 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Ba + 2H2O -> Ba(OH)2 + 2H
        CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, Barium)
            .fluidInputs(Water.getFluid(2000))
            .output(dust, BariumHydroxide, 5)
            .fluidOutputs(Hydrogen.getFluid(2000))
            .EUt(VA[MV])
            .duration(4 * SECOND + 10 * TICK)
            .buildAndRegister()

        // Ba(OH)2 + TiCl4 + H2O -> BaTiO3 + 4HCl
        CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, BariumHydroxide, 5)
            .fluidInputs(TitaniumTetrachloride.getFluid(1000))
            .fluidInputs(Water.getFluid(1000))
            .output(dust, BariumTitanate, 5)
            .fluidOutputs(HydrochloricAcid.getFluid(4000))
            .EUt(VA[IV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // Gooware SMD Capacitor
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(5)
            .input(foil, PedotPSS)
            .input(foil, BariumTitanate)
            .fluidInputs(KaptonK.getFluid(L / 2))
            .output(GOOWARE_SMD_CAPACITOR, 16)
            .EUt(VA[ZPM])
            .duration(8 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Gooware SMD Diode
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(5)
            .input(dust, CubicZirconia)
            .input(wireFine, PedotTMA, 8)
            .fluidInputs(KaptonK.getFluid(L * 2))
            .output(GOOWARE_SMD_DIODE, 64)
            .EUt(VA[ZPM])
            .duration(8 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Gooware SMD Inductor
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(5)
            .input(ring, SamariumCobalt)
            .input(wireFine, Europium, 4)
            .fluidInputs(KaptonK.getFluid(L))
            .output(GOOWARE_SMD_INDUCTOR, 16)
            .EUt(VA[ZPM])
            .duration(8 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()
    }

    private fun circuitComponentsRecipes()
    {
        // BZ Reaction Chamber
        ASSEMBLER_RECIPES.recipeBuilder()
            .input(FLUID_CELL_LARGE_STAINLESS_STEEL)
            .input(plate, Naquadah, 4)
            .input(plate, Ruridit, 2)
            .input(bolt, Trinium, 12)
            .input(stick, SamariumMagnetic)
            .input(rotor, Iridium)
            .input(ELECTRIC_MOTOR_LuV)
            .fluidInputs(SolderingAlloy.getFluid(L))
            .output(BZ_REACTION_CHAMBER)
            .EUt(VA[UV])
            .duration(30 * SECOND)
            .buildAndRegister()

        // Non-linear Chemical Oscillator
        CANNER_RECIPES.recipeBuilder()
            .input(BZ_REACTION_CHAMBER)
            .fluidInputs(BZMedium.getFluid(500))
            .output(NONLINEAR_CHEMICAL_OSCILLATOR)
            .EUt(VA[IV])
            .duration(3 * SECOND)
            .buildAndRegister()
    }

    private fun circuitRecipes()
    {
        // Gooware Processor
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
            .input(ULTIMATE_CIRCUIT_BOARD)
            .input(NONLINEAR_CHEMICAL_OSCILLATOR)
            .input(CRYSTAL_CENTRAL_PROCESSING_UNIT)
            .input(GOOWARE_SMD_CAPACITOR, 16)
            .input(GOOWARE_SMD_TRANSISTOR, 16)
            .input(wireFine, Europium, 8)
            .output(GOOWARE_PROCESSOR_ZPM, 4)
            .EUt(VHA[UV])
            .duration(10 * SECOND)
            .solderMultiplier(1)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
            .input(ULTIMATE_CIRCUIT_BOARD)
            .input(NONLINEAR_CHEMICAL_OSCILLATOR)
            .input(CRYSTAL_CENTRAL_PROCESSING_UNIT)
            .input(OPTICAL_SMD_CAPACITOR, 4)
            .input(OPTICAL_SMD_TRANSISTOR, 4)
            .input(wireFine, Europium, 8)
            .output(GOOWARE_PROCESSOR_ZPM, 4)
            .EUt(VHA[UV])
            .duration(5 * SECOND)
            .solderMultiplier(1)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
            .input(ULTIMATE_CIRCUIT_BOARD)
            .input(NONLINEAR_CHEMICAL_OSCILLATOR)
            .input(CRYSTAL_CENTRAL_PROCESSING_UNIT)
            .input(SPINTRONIC_SMD_CAPACITOR)
            .input(SPINTRONIC_SMD_TRANSISTOR)
            .input(wireFine, Europium, 8)
            .output(GOOWARE_PROCESSOR_ZPM, 4)
            .EUt(VHA[UV])
            .duration(2 * SECOND + 10 * TICK)
            .solderMultiplier(1)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
            .input(ULTIMATE_CIRCUIT_BOARD)
            .input(NONLINEAR_CHEMICAL_OSCILLATOR)
            .input(ULTRA_HIGHLY_ADVANCED_SOC_CHIP)
            .input(wireFine, Europium, 8)
            .input(bolt, Neutronium, 8)
            .output(GOOWARE_PROCESSOR_ZPM, 8)
            .EUt(VHA[UHV])
            .duration(2 * SECOND + 10 * TICK)
            .solderMultiplier(1)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Gooware Assembly
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
            .input(ULTIMATE_CIRCUIT_BOARD)
            .input(GOOWARE_PROCESSOR_ZPM, 4)
            .input(GOOWARE_SMD_INDUCTOR, 16)
            .input(GOOWARE_SMD_CAPACITOR, 32)
            .input(ADVANCED_RAM_CHIP, 32)
            .input(wireFine, Europium, 16)
            .output(GOOWARE_ASSEMBLY_UV, 3)
            .EUt(VHA[UV])
            .duration(20 * SECOND)
            .solderMultiplier(2)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
            .input(ULTIMATE_CIRCUIT_BOARD)
            .input(GOOWARE_PROCESSOR_ZPM, 4)
            .input(OPTICAL_SMD_INDUCTOR, 4)
            .input(OPTICAL_SMD_CAPACITOR, 8)
            .input(ADVANCED_RAM_CHIP, 32)
            .input(wireFine, Europium, 16)
            .output(GOOWARE_ASSEMBLY_UV, 3)
            .EUt(VHA[UV])
            .duration(10 * SECOND)
            .solderMultiplier(2)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
            .input(ULTIMATE_CIRCUIT_BOARD)
            .input(GOOWARE_PROCESSOR_ZPM, 4)
            .input(SPINTRONIC_SMD_INDUCTOR)
            .input(SPINTRONIC_SMD_CAPACITOR, 2)
            .input(ADVANCED_RAM_CHIP, 32)
            .input(wireFine, Europium, 16)
            .output(GOOWARE_ASSEMBLY_UV, 3)
            .EUt(VHA[UV])
            .duration(5 * SECOND)
            .solderMultiplier(2)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Gooware Supercomputer
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(ULTIMATE_CIRCUIT_BOARD)
            .input(GOOWARE_ASSEMBLY_UV, 3)
            .input(GOOWARE_SMD_DIODE, 8)
            .input(NOR_MEMORY_CHIP, 16)
            .input(ADVANCED_RAM_CHIP, 32)
            .input(wireFine, Europium, 16)
            .input(foil, KaptonK, 32)
            .input(plate, Americium, 4)
            .fluidInputs(SolderingAlloy.getFluid(L * 16))
            .output(GOOWARE_COMPUTER_UHV, 2)
            .EUt(VA[UV])
            .duration(40 * SECOND)
            .stationResearch {
                it.researchStack(GOOWARE_ASSEMBLY_UV)
                    .EUt(VA[UV])
                    .CWUt(24)
            }
            .buildAndRegister()

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(ULTIMATE_CIRCUIT_BOARD)
            .input(GOOWARE_ASSEMBLY_UV, 3)
            .input(OPTICAL_SMD_DIODE, 2)
            .input(NOR_MEMORY_CHIP, 16)
            .input(ADVANCED_RAM_CHIP, 32)
            .input(wireFine, Europium, 16)
            .input(foil, KaptonK, 32)
            .input(plate, Americium, 4)
            .fluidInputs(SolderingAlloy.getFluid(L * 16))
            .output(GOOWARE_COMPUTER_UHV, 2)
            .EUt(VA[UV])
            .duration(20 * SECOND)
            .stationResearch {
                it.researchStack(GOOWARE_ASSEMBLY_UV)
                    .EUt(VA[UV])
                    .CWUt(24)
            }
            .buildAndRegister()

        // Gooware Mainframe
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, Darmstadtium)
            .input(GOOWARE_COMPUTER_UHV, 2)
            .input(GOOWARE_SMD_DIODE, 64)
            .input(GOOWARE_SMD_CAPACITOR, 64)
            .input(GOOWARE_SMD_TRANSISTOR, 64)
            .input(GOOWARE_SMD_RESISTOR, 64)
            .input(GOOWARE_SMD_INDUCTOR, 64)
            .input(foil, KaptonK, 64)
            .input(ADVANCED_RAM_CHIP, 32)
            .input(wireGtDouble, RutheniumTriniumAmericiumNeutronate, 16)
            .input(plate, Americium, 8)
            .fluidInputs(SolderingAlloy.getFluid(L * 40))
            .fluidInputs(KaptonE.getFluid(L * 16))
            .output(GOOWARE_MAINFRAME_UEV)
            .EUt(VA[UHV])
            .duration(1 * MINUTE + 30 * SECOND)
            .stationResearch {
                it.researchStack(GOOWARE_COMPUTER_UHV)
                    .EUt(VA[UHV])
                    .CWUt(48)
            }
            .buildAndRegister()

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, Darmstadtium)
            .input(GOOWARE_COMPUTER_UHV, 2)
            .input(OPTICAL_SMD_DIODE, 16)
            .input(OPTICAL_SMD_CAPACITOR, 16)
            .input(OPTICAL_SMD_TRANSISTOR, 16)
            .input(OPTICAL_SMD_RESISTOR, 16)
            .input(OPTICAL_SMD_INDUCTOR, 16)
            .input(foil, KaptonK, 64)
            .input(ADVANCED_RAM_CHIP, 32)
            .input(wireGtDouble, RutheniumTriniumAmericiumNeutronate, 16)
            .input(plate, Americium, 8)
            .fluidInputs(SolderingAlloy.getFluid(L * 40))
            .fluidInputs(KaptonE.getFluid(L * 16))
            .output(GOOWARE_MAINFRAME_UEV)
            .EUt(VA[UHV])
            .duration(45 * SECOND)
            .stationResearch {
                it.researchStack(GOOWARE_COMPUTER_UHV)
                    .EUt(VA[UHV])
                    .CWUt(48)
            }
            .buildAndRegister()

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, Darmstadtium)
            .input(GOOWARE_COMPUTER_UHV, 2)
            .input(SPINTRONIC_SMD_DIODE, 4)
            .input(SPINTRONIC_SMD_CAPACITOR, 4)
            .input(SPINTRONIC_SMD_TRANSISTOR, 4)
            .input(SPINTRONIC_SMD_RESISTOR, 4)
            .input(SPINTRONIC_SMD_INDUCTOR, 4)
            .input(foil, KaptonK, 64)
            .input(ADVANCED_RAM_CHIP, 32)
            .input(wireGtDouble, RutheniumTriniumAmericiumNeutronate, 16)
            .input(plate, Americium, 8)
            .fluidInputs(SolderingAlloy.getFluid(L * 40))
            .fluidInputs(KaptonE.getFluid(L * 16))
            .output(GOOWARE_MAINFRAME_UEV)
            .EUt(VA[UHV])
            .duration(25 * SECOND)
            .stationResearch {
                it.researchStack(GOOWARE_COMPUTER_UHV)
                    .EUt(VA[UHV])
                    .CWUt(48)
            }
            .buildAndRegister()

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, Darmstadtium)
            .input(GOOWARE_COMPUTER_UHV, 2)
            .input(COSMIC_SMD_DIODE)
            .input(COSMIC_SMD_CAPACITOR)
            .input(COSMIC_SMD_TRANSISTOR)
            .input(COSMIC_SMD_RESISTOR)
            .input(COSMIC_SMD_INDUCTOR)
            .input(foil, KaptonK, 64)
            .input(ADVANCED_RAM_CHIP, 32)
            .input(wireGtDouble, RutheniumTriniumAmericiumNeutronate, 16)
            .input(plate, Americium, 8)
            .fluidInputs(SolderingAlloy.getFluid(L * 40))
            .fluidInputs(KaptonE.getFluid(L * 16))
            .output(GOOWARE_MAINFRAME_UEV)
            .EUt(VA[UHV])
            .duration(15 * SECOND)
            .stationResearch {
                it.researchStack(GOOWARE_COMPUTER_UHV)
                    .EUt(VA[UHV])
                    .CWUt(48)
            }
            .buildAndRegister()

    }

    // @formatter:on

}