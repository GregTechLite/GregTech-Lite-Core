package gregtechlite.gtlitecore.loader.recipe.circuit

import gregtech.api.GTValues.IV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UIV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.UXV
import gregtech.api.GTValues.VA
import gregtech.api.metatileentity.multiblock.CleanroomType
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.recipes.RecipeMaps.ASSEMBLY_LINE_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_BATH_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.CIRCUIT_ASSEMBLER_RECIPES
import gregtech.api.recipes.RecipeMaps.CUTTER_RECIPES
import gregtech.api.recipes.RecipeMaps.FORMING_PRESS_RECIPES
import gregtech.api.recipes.RecipeMaps.LASER_ENGRAVER_RECIPES
import gregtech.api.recipes.RecipeMaps.MIXER_RECIPES
import gregtech.api.recipes.RecipeMaps.POLARIZER_RECIPES
import gregtech.api.unification.material.Materials.Cadmium
import gregtech.api.unification.material.Materials.Caesium
import gregtech.api.unification.material.Materials.Carbon
import gregtech.api.unification.material.Materials.CarbonMonoxide
import gregtech.api.unification.material.Materials.Chlorine
import gregtech.api.unification.material.Materials.Copernicium
import gregtech.api.unification.material.Materials.DinitrogenTetroxide
import gregtech.api.unification.material.Materials.HydrochloricAcid
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.Iodine
import gregtech.api.unification.material.Materials.Iridium
import gregtech.api.unification.material.Materials.IridiumChloride
import gregtech.api.unification.material.Materials.Livermorium
import gregtech.api.unification.material.Materials.Manganese
import gregtech.api.unification.material.Materials.Meitnerium
import gregtech.api.unification.material.Materials.Moscovium
import gregtech.api.unification.material.Materials.Nihonium
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Rubidium
import gregtech.api.unification.material.Materials.Salt
import gregtech.api.unification.material.Materials.Sodium
import gregtech.api.unification.material.Materials.Steam
import gregtech.api.unification.material.Materials.SulfurDioxide
import gregtech.api.unification.material.Materials.Thallium
import gregtech.api.unification.material.Materials.Thorium
import gregtech.api.unification.material.Materials.Thulium
import gregtech.api.unification.material.Materials.UUMatter
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.material.Materials.ZirconiumTetrachloride
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.foil
import gregtech.api.unification.ore.OrePrefix.frameGt
import gregtech.api.unification.ore.OrePrefix.lens
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.ring
import gregtech.api.unification.ore.OrePrefix.screw
import gregtech.api.unification.ore.OrePrefix.spring
import gregtech.api.unification.ore.OrePrefix.springSmall
import gregtech.api.unification.ore.OrePrefix.wireFine
import gregtech.api.unification.ore.OrePrefix.wireGtDouble
import gregtech.common.items.MetaItems.CRYSTAL_SYSTEM_ON_CHIP
import gregtech.common.items.MetaItems.NAND_MEMORY_CHIP
import gregtech.common.items.MetaItems.NOR_MEMORY_CHIP
import gregtech.common.items.MetaItems.QUBIT_CENTRAL_PROCESSING_UNIT_WAFER
import gregtech.common.items.MetaItems.SENSOR_UHV
import gregtech.common.items.MetaItems.TOOL_DATA_MODULE
import gregtechlite.gtlitecore.api.MINUTE
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.SU
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.BURNER_REACTOR_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CHEMICAL_PLANT_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.MOLECULAR_BEAM_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.SONICATION_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.SPACE_ASSEMBLER_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Abyssalloy
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Acetylene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ActiniumGroupAlloyA
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Aminophenol
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ArceusAlloy2B
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BismuthGermanate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BismuthTrioxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BoronFranciumCarbideSuperconductor
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CadmiumSulfide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CadmiumTungstate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CaesiumIodide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CarbonNanotube
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CinobiteA243
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CosmicFabric
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CosmicNeutronium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CubicSiliconNitride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.DegenerateRhenium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Ethylenediamine
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.EuropiumOxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Fullerene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.FullerenePolymerMatrix
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.GermaniumDioxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Gluons
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HDCS
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HadronicResonantGas
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HalkoniteSteel
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HastelloyK243
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HastelloyX78
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HeavyQuarkDegenerateMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HydromoscovicAcid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Infinity
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Lafium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LanthanumFullereneNanotube
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LanthanumOxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LanthanumZirconate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LutetiumManganeseGermanium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Magnetium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MagnetoResonatic
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MetastableHassium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MetastableOganesson
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MoscoviumIridiate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MutatedLivingSolder
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.NaquadriaEnergetic
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.NeutronProtonFermiSuperfluid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PhthalicAnhydride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Polymethylmethacrylate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PotassiumHydroxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PotassiumPermanganate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PrHoYLF
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Protomatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.QuantumchromodynamicallyConfinedMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ResonantStrangeMeson
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.RhodamineB
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Rhugnor
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SeaborgiumDopedCarbonNanotube
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Shirabon
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SodiumOxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.StrontiumEuropiumNihonate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.StrontiumOxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SuccinicAcid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SuperheavyAlloyA
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Tetracene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ThalliumRoentgeniumChloride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ThalliumThuliumDopedCaesiumIodide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Trinaquadalloy
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TungstenTrioxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Vibranium
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ADVANCED_RAM_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.AMPLITUDE_DUALITY_DISTURBANCE_RAM_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.BOSE_EINSTEIN_CONDENSATE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CLOSED_TIMELIKE_CURVE_COMPUTATIONAL_UNIT
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CLOSED_TIMELIKE_CURVE_GUIDANCE_UNIT
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CONDENSATE_CONTAINMENT_UNIT
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.COSMIC_ASSEMBLY_UIV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.COSMIC_COMPUTER_UXV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.COSMIC_INFORMATION_MODULE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.COSMIC_MAINFRAME_OpV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.COSMIC_PROCESSOR_UEV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.COSMIC_SMD_CAPACITOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.COSMIC_SMD_DIODE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.COSMIC_SMD_INDUCTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.COSMIC_SMD_RESISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.COSMIC_SMD_TRANSISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.EXCITATION_SPECTRUM_COMPOSITE_LOGICAL_GATE_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.EXCITED_EXOTIC_ATOM_RESTRICT_CPU_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.EXCITED_EXOTIC_ATOM_RESTRICT_CPU_WAFER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.EXOTIC_ATOM_RESTRICT_CPU_WAFER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.HOLOGRAPHIC_INFORMATION_IMC
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.INFINITE_CIRCUIT_BOARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MAGNETRON
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MANIFOLD_OSCILLATORY_POWER_CELL
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.NANOSILICON_CATHODE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ND_YAG_LASER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.NUCLEAR_CLOCK
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.OPTICAL_FIBER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.QUANTUM_ANOMALY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.RYDBERG_SPINOR_ARRAY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SCINTILLATOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SCINTILLATOR_CRYSTAL
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SUPRACAUSAL_SMD_CAPACITOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SUPRACAUSAL_SMD_DIODE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SUPRACAUSAL_SMD_INDUCTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SUPRACAUSAL_SMD_RESISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SUPRACAUSAL_SMD_TRANSISTOR

internal object CosmicCircuits
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
        // Cosmic Information Module
        SPACE_ASSEMBLER_RECIPES.recipeBuilder()
            .input(INFINITE_CIRCUIT_BOARD)
            .input(plate, DegenerateRhenium, 2)
            .input(plate, HeavyQuarkDegenerateMatter, 4)
            .input(wireFine, Abyssalloy, 8)
            .fluidInputs(UUMatter.getFluid(4000))
            .output(COSMIC_INFORMATION_MODULE)
            .EUt(VA[UHV])
            .duration(2 * SECOND + 10 * TICK)
            .tier(3)
            .buildAndRegister()

        // Cs + I -> CsI
        CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, Caesium)
            .input(dust, Iodine)
            .output(dust, CaesiumIodide, 2)
            .EUt(VA[MV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()

        // CsI + Tl + Tm -> Tl/Tm:CsI
        MIXER_RECIPES.recipeBuilder()
            .circuitMeta(3)
            .input(dust, CaesiumIodide, 2)
            .input(dust, Thallium)
            .input(dust, Thulium)
            .output(dust, ThalliumThuliumDopedCaesiumIodide, 4)
            .EUt(VA[IV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // C4H6O4 + 2C6H4(CO)2O + 20H -> C18H12 + C2H2 + 10H2O
        LASER_ENGRAVER_RECIPES.recipeBuilder()
            .notConsumable(lens, MagnetoResonatic)
            .input(dust, SuccinicAcid, 14)
            .input(dust, PhthalicAnhydride, 30)
            .fluidInputs(Hydrogen.getFluid(20000))
            .output(dust, Tetracene, 30)
            .fluidOutputs(Acetylene.getFluid(1000))
            .fluidOutputs(Steam.getFluid(10 * SU))
            .EUt(VA[UV])
            .duration(10 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // CdS + WO3 + 3O -> CdWO4 + SO2
        BURNER_REACTOR_RECIPES.recipeBuilder()
            .input(dust, CadmiumSulfide, 2)
            .input(dust, TungstenTrioxide, 4)
            .fluidOutputs(Oxygen.getFluid(3000))
            .output(dust, CadmiumTungstate, 6)
            .fluidOutputs(SulfurDioxide.getFluid(1000))
            .EUt(VA[LuV])
            .duration(6 * SECOND)
            .buildAndRegister()

        // 3GeO2 + 2Bi2O3 -> Bi4Ge3O12
        CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, GermaniumDioxide, 9)
            .input(dust, BismuthTrioxide, 10)
            .output(dust, BismuthGermanate, 19)
            .EUt(VA[LuV])
            .duration(4 * SECOND)
            .buildAndRegister()

        // Scintillator Crystal
        FORMING_PRESS_RECIPES.recipeBuilder()
            .input(plate, Vibranium, 2)
            .input(dust, ThalliumThuliumDopedCaesiumIodide)
            .input(dust, Tetracene)
            .input(dust, CadmiumTungstate)
            .input(dust, BismuthGermanate)
            .output(SCINTILLATOR_CRYSTAL, 2)
            .EUt(VA[UHV])
            .duration(14 * SECOND)
            .buildAndRegister()

        // 2C6H4(CO)2O + 2C6H7NO + C2H4(NH2)2 + HCl -> C28H31ClN2O3 + N2O4 + CO + C
        CHEMICAL_PLANT_RECIPES.recipeBuilder()
            .input(dust, PhthalicAnhydride, 30)
            .input(dust, Aminophenol, 30)
            .fluidInputs(Ethylenediamine.getFluid(1000))
            .fluidInputs(HydrochloricAcid.getFluid(1000))
            .output(dust, RhodamineB, 64)
            .output(dust, RhodamineB)
            .output(dust, Carbon)
            .fluidOutputs(DinitrogenTetroxide.getFluid(1000))
            .fluidOutputs(CarbonMonoxide.getFluid(1000))
            .EUt(VA[UHV])
            .duration(4 * SECOND + 10 * TICK)
            .buildAndRegister()

        // Scintillator
        SPACE_ASSEMBLER_RECIPES.recipeBuilder()
            .input(plate, CinobiteA243, 4)
            .input(SCINTILLATOR_CRYSTAL)
            .input(dust, RhodamineB, 10)
            .input(MAGNETRON, 2)
            .input(spring, HDCS, 4)
            .input(foil, Lafium, 16)
            .input(screw, ArceusAlloy2B, 8)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 2))
            .fluidInputs(Trinaquadalloy.getFluid(L))
            .output(SCINTILLATOR)
            .EUt(VA[UEV])
            .duration(8 * SECOND)
            .tier(4)
            .buildAndRegister()

        // Nuclear Clock
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(SCINTILLATOR)
            .input(SENSOR_UHV)
            .input(ND_YAG_LASER, 2)
            .input(lens, PrHoYLF)
            .fluidInputs(Fullerene.getFluid(L * 4))
            .fluidInputs(Thorium.getPlasma(L))
            .output(NUCLEAR_CLOCK)
            .EUt(VA[UEV])
            .duration(2 * SECOND + 10 * TICK)
            .stationResearch {
                it.researchStack(ND_YAG_LASER)
                    .EUt(VA[UEV])
                    .CWUt(32)
            }
            .buildAndRegister()

        // Closed Timelike Curve Guidance Unit
        ASSEMBLER_RECIPES.recipeBuilder()
            .input(NUCLEAR_CLOCK)
            .input(TOOL_DATA_MODULE, 2)
            .input(plate, ActiniumGroupAlloyA, 4)
            .input(foil, QuantumchromodynamicallyConfinedMatter, 16)
            .input(screw, LanthanumFullereneNanotube, 8)
            .fluidInputs(CosmicNeutronium.getFluid(L * 4))
            .output(CLOSED_TIMELIKE_CURVE_GUIDANCE_UNIT)
            .EUt(VA[UEV])
            .duration(5 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Manifold Oscillatory Power Cell
        SONICATION_RECIPES.recipeBuilder()
            .input(CONDENSATE_CONTAINMENT_UNIT)
            .fluidInputs(QuantumchromodynamicallyConfinedMatter.getFluid(L * 4))
            .fluidInputs(HadronicResonantGas.getFluid(2000))
            .output(MANIFOLD_OSCILLATORY_POWER_CELL)
            .EUt(VA[UEV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // Closed Timelike Curve Computational Unit
        ASSEMBLER_RECIPES.recipeBuilder()
            .input(CLOSED_TIMELIKE_CURVE_GUIDANCE_UNIT)
            .input(MANIFOLD_OSCILLATORY_POWER_CELL, 4)
            .input(plate, SuperheavyAlloyA, 2)
            .input(wireFine, Infinity, 8)
            .fluidInputs(NeutronProtonFermiSuperfluid.getFluid(4000))
            .output(CLOSED_TIMELIKE_CURVE_COMPUTATIONAL_UNIT, 2)
            .EUt(VA[UIV])
            .duration(10 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Holographic Information IMC
        SPACE_ASSEMBLER_RECIPES.recipeBuilder()
            .input(frameGt, HastelloyK243)
            .input(COSMIC_INFORMATION_MODULE)
            .input(CLOSED_TIMELIKE_CURVE_COMPUTATIONAL_UNIT)
            .input(BOSE_EINSTEIN_CONDENSATE, 4)
            .input(OPTICAL_FIBER, 8)
            .input(foil, SeaborgiumDopedCarbonNanotube, 16)
            .fluidInputs(Gluons.getFluid(8000))
            .fluidInputs(Protomatter.getFluid(4000))
            .output(HOLOGRAPHIC_INFORMATION_IMC, 2)
            .EUt(VA[UEV])
            .duration(5 * SECOND)
            .tier(4)
            .buildAndRegister()

    }

    private fun smdRecipes()
    {
        // La2O3 + 2ZrCl4 + 4Na2O -> La2Zr2O7 + 8NaCl
        MOLECULAR_BEAM_RECIPES.recipeBuilder()
            .notConsumable(foil, Cadmium)
            .input(dust, LanthanumOxide, 5)
            .input(dust, ZirconiumTetrachloride, 10)
            .input(dust, SodiumOxide, 12)
            .output(dust, LanthanumZirconate, 11)
            .output(dust, Salt, 16)
            .EUt(VA[UHV])
            .duration(10 * SECOND)
            .temperature(5225)
            .buildAndRegister()

        // Cosmic SMD Transistor
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(8)
            .input(foil, MetastableHassium)
            .input(wireFine, LanthanumZirconate, 16)
            .input(wireFine, Rhugnor, 16)
            .fluidInputs(FullerenePolymerMatrix.getFluid(L))
            .output(COSMIC_SMD_TRANSISTOR, 64)
            .EUt(VA[UEV])
            .duration(8 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Mc + KMnO4 + H2O -> HMcO4 + KOH + Mn
        CHEMICAL_BATH_RECIPES.recipeBuilder()
            .input(dust, Moscovium)
            .input(dust, PotassiumPermanganate, 6)
            .fluidInputs(Water.getFluid(1000))
            .output(dust, PotassiumHydroxide, 3)
            .output(dust, Manganese)
            .fluidOutputs(HydromoscovicAcid.getFluid(1000))
            .EUt(VA[UV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // Ir + 3Cl -> IrCl3
        CHEMICAL_RECIPES.recipeBuilder()
            .circuitMeta(3)
            .input(dust, Iridium)
            .fluidInputs(Chlorine.getFluid(3000))
            .output(dust, IridiumChloride, 4)
            .EUt(VA[LV])
            .duration(8 * SECOND)
            .buildAndRegister()

        // 2HMcO4 + 2IrCl3 + 6Na -> Mc2Ir2O7 + 2HCl + 4NaCl + Na2O
        BURNER_REACTOR_RECIPES.recipeBuilder()
            .input(dust, IridiumChloride, 8)
            .input(dust, Sodium, 6)
            .fluidInputs(HydromoscovicAcid.getFluid(2000))
            .output(dust, MoscoviumIridiate, 11)
            .output(dust, Salt, 8)
            .output(dust, SodiumOxide, 3)
            .fluidOutputs(HydrochloricAcid.getFluid(2000))
            .EUt(VA[UEV])
            .duration(4 * SECOND + 10 * TICK)
            .buildAndRegister()

        // Cosmic SMD Resistor
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(8)
            .input(dust, Shirabon)
            .input(wireFine, MoscoviumIridiate, 8)
            .input(wireFine, MetastableOganesson, 8)
            .fluidInputs(FullerenePolymerMatrix.getFluid(L * 2))
            .output(COSMIC_SMD_RESISTOR, 64)
            .EUt(VA[UEV])
            .duration(8 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Cosmic SMD Capacitor
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(8)
            .input(NANOSILICON_CATHODE)
            .input(foil, HeavyQuarkDegenerateMatter, 4)
            .input(foil, Infinity, 4)
            .fluidInputs(FullerenePolymerMatrix.getFluid(L / 2))
            .output(COSMIC_SMD_CAPACITOR, 64)
            .EUt(VA[UEV])
            .duration(8 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // 2SrO + Eu2O3 + 4Nh + 3O -> 2SrEuNh2O4
        BURNER_REACTOR_RECIPES.recipeBuilder()
            .input(dust, StrontiumOxide, 4)
            .input(dust, EuropiumOxide, 5)
            .input(dust, Nihonium, 4)
            .fluidInputs(Oxygen.getFluid(3000))
            .output(dust, StrontiumEuropiumNihonate, 16)
            .EUt(VA[UEV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()

        // Cosmic SMD Diode
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(8)
            .input(dust, CubicSiliconNitride)
            .input(foil, StrontiumEuropiumNihonate, 8)
            .input(foil, HalkoniteSteel, 8)
            .fluidInputs(FullerenePolymerMatrix.getFluid(L * 2))
            .output(COSMIC_SMD_DIODE, 64)
            .EUt(VA[UEV])
            .duration(8 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Cosmic SMD Inductor
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(8)
            .input(ring, Magnetium)
            .input(wireFine, CosmicNeutronium, 8)
            .input(wireFine, SuperheavyAlloyA, 8)
            .fluidInputs(FullerenePolymerMatrix.getFluid(L * 2))
            .output(COSMIC_SMD_INDUCTOR, 64)
            .EUt(VA[UEV])
            .duration(8 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

    }

    private fun circuitComponentsRecipes()
    {
        // Exotic Atom Restrict Central Processing Unit
        LASER_ENGRAVER_RECIPES.recipeBuilder()
            .notConsumable(lens, PrHoYLF)
            .notConsumable(RYDBERG_SPINOR_ARRAY)
            .notConsumable(QUANTUM_ANOMALY)
            .input(QUBIT_CENTRAL_PROCESSING_UNIT_WAFER)
            .fluidInputs(ResonantStrangeMeson.getFluid(1000))
            .fluidInputs(Rubidium.getPlasma(L))
            .output(EXOTIC_ATOM_RESTRICT_CPU_WAFER)
            .EUt(VA[UIV])
            .duration(5 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Excited Exotic Atom Restrict Central Processing Unit
        POLARIZER_RECIPES.recipeBuilder()
            .input(EXOTIC_ATOM_RESTRICT_CPU_WAFER)
            .fluidInputs(NaquadriaEnergetic.getFluid(1000))
            .output(EXCITED_EXOTIC_ATOM_RESTRICT_CPU_WAFER)
            .EUt(VA[UIV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()

        // Excited Exotic Atom Restrict Central Processing Unit Chip
        CUTTER_RECIPES.recipeBuilder()
            .input(EXCITED_EXOTIC_ATOM_RESTRICT_CPU_WAFER)
            .output(EXCITED_EXOTIC_ATOM_RESTRICT_CPU_CHIP, 2)
            .EUt(VA[UIV])
            .duration(1 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Amplitude Duality Disturbance RAM
        FORMING_PRESS_RECIPES.recipeBuilder()
            .input(ADVANCED_RAM_CHIP)
            .input(plate, LanthanumFullereneNanotube, 4)
            .input(plate, Copernicium, 4)
            .input(foil, ThalliumRoentgeniumChloride, 8)
            .input(springSmall, CarbonNanotube, 2)
            .input(wireFine, HeavyQuarkDegenerateMatter, 16)
            .output(AMPLITUDE_DUALITY_DISTURBANCE_RAM_CHIP, 4)
            .EUt(VA[UIV])
            .duration(10 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Excitation Spectrum Composite Logical Gate
        FORMING_PRESS_RECIPES.recipeBuilder()
            .input(NOR_MEMORY_CHIP)
            .input(NAND_MEMORY_CHIP)
            .input(foil, Polymethylmethacrylate, 16)
            .input(foil, SuperheavyAlloyA, 16)
            .input(springSmall, LutetiumManganeseGermanium, 4)
            .input(wireFine, HastelloyX78, 24)
            .output(EXCITATION_SPECTRUM_COMPOSITE_LOGICAL_GATE_CHIP, 4)
            .EUt(VA[UIV])
            .duration(10 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

    }

    private fun circuitRecipes()
    {
        // Cosmic Processor
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
            .input(HOLOGRAPHIC_INFORMATION_IMC)
            .input(CRYSTAL_SYSTEM_ON_CHIP)
            .input(COSMIC_SMD_RESISTOR, 32)
            .input(COSMIC_SMD_CAPACITOR, 32)
            .input(COSMIC_SMD_TRANSISTOR, 32)
            .input(wireFine, Infinity, 8)
            .fluidInputs(MutatedLivingSolder.getFluid(L / 2))
            .output(COSMIC_PROCESSOR_UEV, 4)
            .EUt(VA[UIV])
            .duration(10 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
            .input(HOLOGRAPHIC_INFORMATION_IMC)
            .input(CRYSTAL_SYSTEM_ON_CHIP)
            .input(SUPRACAUSAL_SMD_RESISTOR, 8)
            .input(SUPRACAUSAL_SMD_CAPACITOR, 8)
            .input(SUPRACAUSAL_SMD_TRANSISTOR, 8)
            .input(wireFine, Infinity, 8)
            .fluidInputs(MutatedLivingSolder.getFluid(L / 2))
            .output(COSMIC_PROCESSOR_UEV, 4)
            .EUt(VA[UIV])
            .duration(5 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Cosmic Assembly
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(COSMIC_INFORMATION_MODULE)
            .input(COSMIC_PROCESSOR_UEV, 4)
            .input(COSMIC_SMD_CAPACITOR, 32)
            .input(COSMIC_SMD_INDUCTOR, 32)
            .input(EXCITED_EXOTIC_ATOM_RESTRICT_CPU_CHIP, 16)
            .input(wireFine, Infinity, 16)
            .input("foilAnyAdvancedSyntheticRubber", 64)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 4))
            .output(COSMIC_ASSEMBLY_UIV, 3)
            .EUt(VA[UIV])
            .duration(20 * SECOND)
            .stationResearch {
                it.researchStack(COSMIC_PROCESSOR_UEV)
                    .EUt(VA[UIV])
                    .CWUt(48)
            }
            .buildAndRegister()

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(COSMIC_INFORMATION_MODULE)
            .input(COSMIC_PROCESSOR_UEV, 4)
            .input(SUPRACAUSAL_SMD_CAPACITOR, 8)
            .input(SUPRACAUSAL_SMD_INDUCTOR, 8)
            .input(EXCITED_EXOTIC_ATOM_RESTRICT_CPU_CHIP, 16)
            .input(wireFine, Infinity, 16)
            .input("foilAnyAdvancedSyntheticRubber", 64)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 4))
            .output(COSMIC_ASSEMBLY_UIV, 3)
            .EUt(VA[UIV])
            .duration(10 * SECOND)
            .stationResearch {
                it.researchStack(COSMIC_PROCESSOR_UEV)
                    .EUt(VA[UIV])
                    .CWUt(48)
            }
            .buildAndRegister()

        // Cosmic Computer
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(COSMIC_INFORMATION_MODULE)
            .input(COSMIC_ASSEMBLY_UIV, 3)
            .input(COSMIC_SMD_DIODE, 16)
            .input(EXCITATION_SPECTRUM_COMPOSITE_LOGICAL_GATE_CHIP, 32)
            .input(AMPLITUDE_DUALITY_DISTURBANCE_RAM_CHIP, 64)
            .input(wireFine, Infinity, 32)
            .input(foil, FullerenePolymerMatrix, 64)
            .input(plate, Livermorium, 8)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 16))
            .fluidInputs(Fullerene.getFluid(L * 12))
            .fluidInputs(CosmicNeutronium.getFluid(L * 8))
            .output(COSMIC_COMPUTER_UXV, 2)
            .EUt(VA[UIV])
            .duration(40 * SECOND)
            .stationResearch {
                it.researchStack(COSMIC_ASSEMBLY_UIV)
                    .EUt(VA[UIV])
                    .CWUt(64)
            }
            .buildAndRegister()

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(COSMIC_INFORMATION_MODULE)
            .input(COSMIC_ASSEMBLY_UIV, 3)
            .input(SUPRACAUSAL_SMD_DIODE, 4)
            .input(EXCITATION_SPECTRUM_COMPOSITE_LOGICAL_GATE_CHIP, 32)
            .input(AMPLITUDE_DUALITY_DISTURBANCE_RAM_CHIP, 64)
            .input(wireFine, Infinity, 32)
            .input(foil, FullerenePolymerMatrix, 64)
            .input(plate, Livermorium, 8)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 16))
            .fluidInputs(Fullerene.getFluid(L * 12))
            .fluidInputs(CosmicNeutronium.getFluid(L * 8))
            .output(COSMIC_COMPUTER_UXV, 2)
            .EUt(VA[UIV])
            .duration(20 * SECOND)
            .stationResearch {
                it.researchStack(COSMIC_ASSEMBLY_UIV)
                    .EUt(VA[UIV])
                    .CWUt(64)
            }
            .buildAndRegister()

        // Cosmic Mainframe
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, Shirabon)
            .input(COSMIC_COMPUTER_UXV, 2)
            .input(COSMIC_SMD_DIODE, 64)
            .input(COSMIC_SMD_CAPACITOR, 64)
            .input(COSMIC_SMD_TRANSISTOR, 64)
            .input(COSMIC_SMD_RESISTOR, 64)
            .input(COSMIC_SMD_INDUCTOR, 64)
            .input(foil, FullerenePolymerMatrix, 64)
            .input(AMPLITUDE_DUALITY_DISTURBANCE_RAM_CHIP, 64)
            .input(wireGtDouble, BoronFranciumCarbideSuperconductor, 16)
            .input(plate, Meitnerium, 16)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 80))
            .fluidInputs(CosmicFabric.getFluid(L * 32))
            .fluidInputs(QuantumchromodynamicallyConfinedMatter.getFluid(L * 16))
            .fluidInputs(Vibranium.getFluid(L * 8))
            .output(COSMIC_MAINFRAME_OpV)
            .EUt(VA[UXV])
            .duration(1 * MINUTE + 30 * SECOND)
            .stationResearch {
                it.researchStack(COSMIC_COMPUTER_UXV)
                    .EUt(VA[UXV])
                    .CWUt(128)
            }
            .buildAndRegister()

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, Shirabon)
            .input(COSMIC_COMPUTER_UXV, 2)
            .input(SUPRACAUSAL_SMD_DIODE, 16)
            .input(SUPRACAUSAL_SMD_CAPACITOR, 16)
            .input(SUPRACAUSAL_SMD_TRANSISTOR, 16)
            .input(SUPRACAUSAL_SMD_RESISTOR, 16)
            .input(SUPRACAUSAL_SMD_INDUCTOR, 16)
            .input(foil, FullerenePolymerMatrix, 64)
            .input(AMPLITUDE_DUALITY_DISTURBANCE_RAM_CHIP, 64)
            .input(wireGtDouble, BoronFranciumCarbideSuperconductor, 16)
            .input(plate, Meitnerium, 16)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 80))
            .fluidInputs(CosmicFabric.getFluid(L * 32))
            .fluidInputs(QuantumchromodynamicallyConfinedMatter.getFluid(L * 16))
            .fluidInputs(Vibranium.getFluid(L * 8))
            .output(COSMIC_MAINFRAME_OpV)
            .EUt(VA[UXV])
            .duration(1 * MINUTE + 30 * SECOND)
            .stationResearch {
                it.researchStack(COSMIC_COMPUTER_UXV)
                    .EUt(VA[UXV])
                    .CWUt(128)
            }
            .buildAndRegister()

    }

    // @formatter:on

}