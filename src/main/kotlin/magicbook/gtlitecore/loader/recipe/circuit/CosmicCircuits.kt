package magicbook.gtlitecore.loader.recipe.circuit

import gregtech.api.GTValues.IV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UIV
import gregtech.api.GTValues.UV
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
import gregtech.api.unification.material.Materials.Moscovium
import gregtech.api.unification.material.Materials.Nihonium
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Roentgenium
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
import gregtech.api.unification.ore.OrePrefix.plateDouble
import gregtech.api.unification.ore.OrePrefix.ring
import gregtech.api.unification.ore.OrePrefix.screw
import gregtech.api.unification.ore.OrePrefix.spring
import gregtech.api.unification.ore.OrePrefix.springSmall
import gregtech.api.unification.ore.OrePrefix.wireFine
import gregtech.common.items.MetaItems.CRYSTAL_SYSTEM_ON_CHIP
import gregtech.common.items.MetaItems.NAND_MEMORY_CHIP
import gregtech.common.items.MetaItems.NOR_MEMORY_CHIP
import gregtech.common.items.MetaItems.QUBIT_CENTRAL_PROCESSING_UNIT_WAFER
import gregtech.common.items.MetaItems.SENSOR_UHV
import gregtech.common.items.MetaItems.TOOL_DATA_MODULE
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.BURNER_REACTOR_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.CHEMICAL_PLANT_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.CIRCUIT_ASSEMBLY_LINE_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.MOLECULAR_BEAM_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.SONICATION_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.SPACE_ASSEMBLER_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Abyssalloy
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Acetylene
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ActiniumGroupAlloyA
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Aminophenol
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ArceusAlloy2B
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BismuthGermanate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BismuthTrioxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CadmiumSulfide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CadmiumTungstate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CaesiumIodide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CarbonNanotube
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CinobiteA243
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CosmicNeutronium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CubicSiliconNitride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.DegenerateRhenium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Ethylenediamine
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.EuropiumOxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Fullerene
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.FullerenePolymerMatrix
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.GermaniumDioxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Gluons
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HDCS
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HadronicResonantGas
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HalkoniteSteel
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HastelloyK243
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HastelloyX78
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HeavyQuarkDegenerateMatter
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HydromoscovicAcid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Hypogen
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Infinity
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Lafium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LanthanumFullereneNanotube
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LanthanumOxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LanthanumZirconate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LutetiumManganeseGermanium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Magnetium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MagnetoResonatic
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MetastableHassium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MetastableOganesson
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MoscoviumIridiate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MutatedLivingSolder
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.NaquadriaEnergetic
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.NeutronProtonFermiSuperfluid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PhthalicAnhydride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Polymethylmethacrylate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PotassiumHydroxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PotassiumPermanganate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PrHoYLF
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Protomatter
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.QuantumAlloy
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.QuantumchromodynamicallyConfinedMatter
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ResonantStrangeMeson
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.RhodamineB
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Rhugnor
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SeaborgiumDopedCarbonNanotube
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Shirabon
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SodiumOxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.StrontiumEuropiumNihonate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.StrontiumOxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SuccinicAcid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SuperheavyAlloyA
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Tetracene
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ThalliumRoentgeniumChloride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ThalliumThuliumDopedCaesiumIodide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Trinaquadalloy
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TungstenTrioxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Vibranium
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.ADVANCED_RAM_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.AMPLITUDE_DUALITY_DISTURBANCE_RAM_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.BOSE_EINSTEIN_CONDENSATE
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.CLOSED_TIMELIKE_CURVE_COMPUTATIONAL_UNIT
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.CLOSED_TIMELIKE_CURVE_GUIDANCE_UNIT
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.CONDENSATE_CONTAINMENT_UNIT
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.COSMIC_ASSEMBLY_UIV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.COSMIC_COMPUTER_UXV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.INFINITE_CIRCUIT_BOARD
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.MAGNETRON
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.MANIFOLD_OSCILLATORY_POWER_CELL
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.ND_YAG_LASER
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.NUCLEAR_CLOCK
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.OPTICAL_FIBER
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SCINTILLATOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SCINTILLATOR_CRYSTAL
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.COSMIC_INFORMATION_MODULE
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.COSMIC_PROCESSOR_UEV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.COSMIC_SMD_CAPACITOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.COSMIC_SMD_DIODE
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.COSMIC_SMD_INDUCTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.COSMIC_SMD_RESISTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.COSMIC_SMD_TRANSISTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.EXCITATION_SPECTRUM_COMPOSITE_LOGICAL_GATE_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.EXCITED_EXOTIC_ATOM_RESTRICT_CPU_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.EXCITED_EXOTIC_ATOM_RESTRICT_CPU_WAFER
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.EXOTIC_ATOM_RESTRICT_CPU_WAFER
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.HOLOGRAPHIC_INFORMATION_IMC
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.NANOSILICON_CATHODE
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.QUANTUM_ANOMALY
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.RYDBERG_SPINOR_ARRAY
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SPINTRONIC_SMD_CAPACITOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SPINTRONIC_SMD_INDUCTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SUPRACAUSAL_SMD_CAPACITOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SUPRACAUSAL_SMD_DIODE
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SUPRACAUSAL_SMD_INDUCTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SUPRACAUSAL_SMD_RESISTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SUPRACAUSAL_SMD_TRANSISTOR

@Suppress("MISSING_DEPENDENCY_CLASS")
class CosmicCircuits
{

    companion object
    {

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
                .EUt(VA[UHV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .tier(3)
                .buildAndRegister()

            // Cs + I -> CsI
            CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Caesium)
                .input(dust, Iodine)
                .output(dust, CaesiumIodide, 2)
                .EUt(VA[MV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // CsI + Tl + Tm -> Tl/Tm:CsI
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .input(dust, CaesiumIodide, 2)
                .input(dust, Thallium)
                .input(dust, Thulium)
                .output(dust, ThalliumThuliumDopedCaesiumIodide, 4)
                .EUt(VA[IV].toLong())
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
                .fluidOutputs(Steam.getFluid(10000))
                .EUt(VA[UV].toLong())
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
                .EUt(VA[LuV].toLong())
                .duration(6 * SECOND)
                .buildAndRegister()

            // 3GeO2 + 2Bi2O3 -> Bi4Ge3O12
            CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, GermaniumDioxide, 9)
                .input(dust, BismuthTrioxide, 10)
                .output(dust, BismuthGermanate, 19)
                .EUt(VA[LuV].toLong())
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
                .EUt(VA[UHV].toLong())
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
                .EUt(VA[UHV].toLong())
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
                .EUt(VA[UEV].toLong())
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
                .EUt(VA[UEV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .stationResearch { r ->
                    r.researchStack(ND_YAG_LASER.stackForm)
                        .EUt(VA[UEV].toLong())
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
                .EUt(VA[UEV].toLong())
                .duration(5 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // Manifold Oscillatory Power Cell
            SONICATION_RECIPES.recipeBuilder()
                .input(CONDENSATE_CONTAINMENT_UNIT)
                .fluidInputs(QuantumchromodynamicallyConfinedMatter.getFluid(L * 4))
                .fluidInputs(HadronicResonantGas.getFluid(2000))
                .output(MANIFOLD_OSCILLATORY_POWER_CELL)
                .EUt(VA[UEV].toLong())
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
                .EUt(VA[UIV].toLong())
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
                .EUt(VA[UEV].toLong())
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
                .EUt(VA[UHV].toLong())
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
                .EUt(VA[UEV].toLong())
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
                .EUt(VA[UV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // Ir + 3Cl -> IrCl3
            CHEMICAL_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .input(dust, Iridium)
                .fluidInputs(Chlorine.getFluid(3000))
                .output(dust, IridiumChloride, 4)
                .EUt(VA[LV].toLong())
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
                .EUt(VA[UEV].toLong())
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
                .EUt(VA[UEV].toLong())
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
                .EUt(VA[UEV].toLong())
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
                .EUt(VA[UEV].toLong())
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
                .EUt(VA[UEV].toLong())
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
                .EUt(VA[UEV].toLong())
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
                .EUt(VA[UIV].toLong())
                .duration(5 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // Excited Exotic Atom Restrict Central Processing Unit
            POLARIZER_RECIPES.recipeBuilder()
                .input(EXOTIC_ATOM_RESTRICT_CPU_WAFER)
                .fluidInputs(NaquadriaEnergetic.getFluid(1000))
                .output(EXCITED_EXOTIC_ATOM_RESTRICT_CPU_WAFER)
                .EUt(VA[UIV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // Excited Exotic Atom Restrict Central Processing Unit Chip
            CUTTER_RECIPES.recipeBuilder()
                .input(EXCITED_EXOTIC_ATOM_RESTRICT_CPU_WAFER)
                .output(EXCITED_EXOTIC_ATOM_RESTRICT_CPU_CHIP, 2)
                .EUt(VA[UIV].toLong())
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
                .EUt(VA[UIV].toLong())
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
                .EUt(VA[UIV].toLong())
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
                .EUt(VA[UIV].toLong())
                .duration(10 * SECOND)
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
                .EUt(VA[UIV].toLong())
                .duration(5 * SECOND)
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
                .EUt(VA[UIV].toLong())
                .duration(20 * SECOND)
                .stationResearch { r ->
                    r.researchStack(COSMIC_PROCESSOR_UEV.stackForm)
                        .EUt(VA[UIV].toLong())
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
                .EUt(VA[UIV].toLong())
                .duration(10 * SECOND)
                .stationResearch { r ->
                    r.researchStack(COSMIC_PROCESSOR_UEV.stackForm)
                        .EUt(VA[UIV].toLong())
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
                .EUt(VA[UIV].toLong())
                .duration(40 * SECOND)
                .stationResearch { r ->
                    r.researchStack(COSMIC_ASSEMBLY_UIV.stackForm)
                        .EUt(VA[UIV].toLong())
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
                .EUt(VA[UIV].toLong())
                .duration(20 * SECOND)
                .stationResearch { r ->
                    r.researchStack(COSMIC_ASSEMBLY_UIV.stackForm)
                        .EUt(VA[UIV].toLong())
                        .CWUt(64)
                }
                .buildAndRegister()

            // TODO Cosmic Mainframe

        }

    }

}