package gregtechlite.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.L
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UIV
import gregtech.api.GTValues.UXV
import gregtech.api.GTValues.VA
import gregtech.api.unification.material.Materials.Americium
import gregtech.api.unification.material.Materials.Bohrium
import gregtech.api.unification.material.Materials.Darmstadtium
import gregtech.api.unification.material.Materials.Dubnium
import gregtech.api.unification.material.Materials.EnrichedNaquadahTriniumEuropiumDuranide
import gregtech.api.unification.material.Materials.Europium
import gregtech.api.unification.material.Materials.HSSE
import gregtech.api.unification.material.Materials.IndiumTinBariumTitaniumCuprate
import gregtech.api.unification.material.Materials.Livermorium
import gregtech.api.unification.material.Materials.Meitnerium
import gregtech.api.unification.material.Materials.Neutronium
import gregtech.api.unification.material.Materials.Polybenzimidazole
import gregtech.api.unification.material.Materials.RutheniumTriniumAmericiumNeutronate
import gregtech.api.unification.material.Materials.SolderingAlloy
import gregtech.api.unification.material.Materials.Tritanium
import gregtech.api.unification.ore.OrePrefix.frameGt
import gregtech.api.unification.ore.OrePrefix.wireGtHex
import gregtech.api.unification.ore.OrePrefix.wireGtQuadruple
import gregtech.common.items.MetaItems.CRYSTAL_COMPUTER_ZPM
import gregtech.common.items.MetaItems.CRYSTAL_MAINFRAME_UV
import gregtech.common.items.MetaItems.WETWARE_MAINFRAME_UHV
import gregtech.common.items.MetaItems.WETWARE_SUPER_COMPUTER_UV
import gregtechlite.gtlitecore.api.MINUTE
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.SINGULARITY_ASSEMBLY_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Adamantium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BoronFranciumCarbideSuperconductor
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CarbonNanotube
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CosmicFabric
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CosmicNeutronium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Fullerene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.FullerenePolymerMatrix
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.FullereneSuperconductor
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Infinity
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.KaptonE
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.KaptonK
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MutatedLivingSolder
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Polyetheretherketone
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PolyphosphonitrileFluoroRubber
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PolytetramethyleneGlycolRubber
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.QuantumchromodynamicallyConfinedMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Shirabon
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Vibranium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.VibraniumTritaniumActiniumIronSuperhydride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Zylon
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.COSMIC_ASSEMBLY_UIV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.COSMIC_COMPUTER_UXV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.COSMIC_MAINFRAME_OpV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.COSMIC_PROCESSOR_UEV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GOOWARE_ASSEMBLY_UV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GOOWARE_COMPUTER_UHV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GOOWARE_MAINFRAME_UEV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.OPTICAL_ASSEMBLY_UHV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.OPTICAL_COMPUTER_UEV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.OPTICAL_FIBER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.OPTICAL_MAINFRAME_UIV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SPINTRONIC_ASSEMBLY_UEV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SPINTRONIC_COMPUTER_UIV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SPINTRONIC_MAINFRAME_UXV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SPINTRONIC_PROCESSOR_UHV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ACNOR_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ADDRAM_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ADVANCED_SMD_CAPACITOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ADVANCED_SMD_DIODE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ADVANCED_SMD_INDUCTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ADVANCED_SMD_RESISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ADVANCED_SMD_TRANSISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ARAM_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_COSMIC_INFORMATION_MODULE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_COSMIC_SMD_CAPACITOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_COSMIC_SMD_DIODE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_COSMIC_SMD_INDUCTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_COSMIC_SMD_RESISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_COSMIC_SMD_TRANSISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_EARCPU_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ESCLG_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_GOOWARE_SMD_CAPACITOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_GOOWARE_SMD_DIODE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_GOOWARE_SMD_INDUCTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_GOOWARE_SMD_RESISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_GOOWARE_SMD_TRANSISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_HPIC_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_INFINITE_CIRCUIT_BOARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_MINAND_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_NOR_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_OPTICAL_SMD_CAPACITOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_OPTICAL_SMD_DIODE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_OPTICAL_SMD_INDUCTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_OPTICAL_SMD_RESISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_OPTICAL_SMD_TRANSISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_PERFECT_CIRCUIT_BOARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_PRAM_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_RAM_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_SPINTRONIC_SMD_CAPACITOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_SPINTRONIC_SMD_DIODE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_SPINTRONIC_SMD_INDUCTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_SPINTRONIC_SMD_RESISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_SPINTRONIC_SMD_TRANSISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_STTRAM_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_SUPRACAUSAL_SMD_CAPACITOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_SUPRACAUSAL_SMD_DIODE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_SUPRACAUSAL_SMD_INDUCTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_SUPRACAUSAL_SMD_RESISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_SUPRACAUSAL_SMD_TRANSISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_UHASOC_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ULTIMATE_CIRCUIT_BOARD

// TODO: producer or recipe action.
object SingularityAssemblyUnitRecipes
{

    // @formatter:off

    fun init()
    {
        // region T6: Crystal

        // UV Crystal Mainframe
        SINGULARITY_ASSEMBLY_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(frameGt, HSSE, 16)
            .input(CRYSTAL_COMPUTER_ZPM, 32)
            .input(WRAP_ADVANCED_SMD_INDUCTOR, 16)
            .input(WRAP_ADVANCED_SMD_CAPACITOR, 16)
            .input(WRAP_ADVANCED_SMD_DIODE, 16)
            .input(WRAP_HPIC_CHIP, 2)
            .input(WRAP_RAM_CHIP, 32)
            .input(wireGtHex, IndiumTinBariumTitaniumCuprate, 8)
            .fluidInputs(SolderingAlloy.getFluid(L * 10))
            .output(CRYSTAL_MAINFRAME_UV, 16)
            .EUt(VA[LuV])
            .duration(8 * MINUTE) // Original: 40s, Wrapped: 40s * 16 = 640s
            .buildAndRegister()

        SINGULARITY_ASSEMBLY_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(frameGt, HSSE, 16 * 64)
            .input(CRYSTAL_COMPUTER_ZPM, 32 * 64)
            .input(WRAP_ADVANCED_SMD_INDUCTOR, 16 * 64)
            .input(WRAP_ADVANCED_SMD_CAPACITOR, 16 * 64)
            .input(WRAP_ADVANCED_SMD_DIODE, 16 * 64)
            .input(WRAP_HPIC_CHIP, 2 * 64)
            .input(WRAP_RAM_CHIP, 32 * 64)
            .input(wireGtHex, IndiumTinBariumTitaniumCuprate, 8 * 64)
            .fluidInputs(SolderingAlloy.getFluid(L * 10 * 64))
            .output(CRYSTAL_MAINFRAME_UV, 16 * 64)
            .EUt(VA[LuV])
            .duration(8 * MINUTE * 64) // Original: 40s, Wrapped: 40s * 16 = 640s
            .buildAndRegister()

        SINGULARITY_ASSEMBLY_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(frameGt, HSSE, 16)
            .input(CRYSTAL_COMPUTER_ZPM, 32)
            .input(WRAP_GOOWARE_SMD_INDUCTOR, 4)
            .input(WRAP_GOOWARE_SMD_CAPACITOR, 4)
            .input(WRAP_GOOWARE_SMD_DIODE, 4)
            .input(WRAP_HPIC_CHIP, 2)
            .input(WRAP_RAM_CHIP, 32)
            .input(wireGtHex, IndiumTinBariumTitaniumCuprate, 8)
            .fluidInputs(SolderingAlloy.getFluid(L * 10))
            .output(CRYSTAL_MAINFRAME_UV, 16)
            .EUt(VA[LuV])
            .duration(4 * MINUTE) // Original: 20s, Wrapped: 20s * 16 = 320s
            .buildAndRegister()

        SINGULARITY_ASSEMBLY_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(frameGt, HSSE, 16 * 64)
            .input(CRYSTAL_COMPUTER_ZPM, 32 * 64)
            .input(WRAP_GOOWARE_SMD_INDUCTOR, 4 * 64)
            .input(WRAP_GOOWARE_SMD_CAPACITOR, 4 * 64)
            .input(WRAP_GOOWARE_SMD_DIODE, 4 * 64)
            .input(WRAP_HPIC_CHIP, 2 * 64)
            .input(WRAP_RAM_CHIP, 32 * 64)
            .input(wireGtHex, IndiumTinBariumTitaniumCuprate, 8 * 64)
            .fluidInputs(SolderingAlloy.getFluid(L * 10 * 64))
            .output(CRYSTAL_MAINFRAME_UV, 16 * 64)
            .EUt(VA[LuV])
            .duration(4 * MINUTE * 64) // Original: 20s, Wrapped: 20s * 16 = 320s
            .buildAndRegister()

        SINGULARITY_ASSEMBLY_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(frameGt, HSSE, 16)
            .input(CRYSTAL_COMPUTER_ZPM, 32)
            .input(WRAP_OPTICAL_SMD_INDUCTOR)
            .input(WRAP_OPTICAL_SMD_CAPACITOR)
            .input(WRAP_OPTICAL_SMD_DIODE)
            .input(WRAP_HPIC_CHIP, 2)
            .input(WRAP_RAM_CHIP, 32)
            .input(wireGtHex, IndiumTinBariumTitaniumCuprate, 8)
            .fluidInputs(SolderingAlloy.getFluid(L * 10))
            .output(CRYSTAL_MAINFRAME_UV, 16)
            .EUt(VA[LuV])
            .duration(2 * MINUTE) // Original: 10s, Wrapped: 10s * 16 = 160s
            .buildAndRegister()

        SINGULARITY_ASSEMBLY_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(frameGt, HSSE, 16 * 64)
            .input(CRYSTAL_COMPUTER_ZPM, 32 * 64)
            .input(WRAP_OPTICAL_SMD_INDUCTOR, 64)
            .input(WRAP_OPTICAL_SMD_CAPACITOR, 64)
            .input(WRAP_OPTICAL_SMD_DIODE, 64)
            .input(WRAP_HPIC_CHIP, 2 * 64)
            .input(WRAP_RAM_CHIP, 32 * 64)
            .input(wireGtHex, IndiumTinBariumTitaniumCuprate, 8 * 64)
            .fluidInputs(SolderingAlloy.getFluid(L * 10 * 64))
            .output(CRYSTAL_MAINFRAME_UV, 16 * 64)
            .EUt(VA[LuV])
            .duration(2 * MINUTE * 64) // Original: 10s, Wrapped: 10s * 16 = 160s
            .buildAndRegister()

        // endregion

        // region T7: Wetware

        // UHV Wetware Mainframe
        SINGULARITY_ASSEMBLY_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(frameGt, Tritanium, 16)
            .input(WETWARE_SUPER_COMPUTER_UV, 32)
            .input(WRAP_ADVANCED_SMD_DIODE, 32)
            .input(WRAP_ADVANCED_SMD_CAPACITOR, 32)
            .input(WRAP_ADVANCED_SMD_TRANSISTOR, 32)
            .input(WRAP_ADVANCED_SMD_RESISTOR, 32)
            .input(WRAP_ADVANCED_SMD_INDUCTOR, 32)
            .input(WRAP_ARAM_CHIP, 32)
            .input(wireGtHex, EnrichedNaquadahTriniumEuropiumDuranide, 16)
            .fluidInputs(SolderingAlloy.getFluid(L * 20))
            .fluidInputs(Polybenzimidazole.getFluid(36 * 64 * 16)) // foil (36) * 64 * 16
            .fluidInputs(Europium.getFluid(L * 4 * 64)) // plate (144) * 4 * 64
            .output(WETWARE_MAINFRAME_UHV, 16)
            .EUt(300_000) // UV
            .duration(12 * MINUTE) // Original: 60s, Wrapped: 60s * 16 = 960s
            .buildAndRegister()

        SINGULARITY_ASSEMBLY_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(frameGt, Tritanium, 16 * 64)
            .input(WETWARE_SUPER_COMPUTER_UV, 32 * 64)
            .input(WRAP_ADVANCED_SMD_DIODE, 32 * 64)
            .input(WRAP_ADVANCED_SMD_CAPACITOR, 32 * 64)
            .input(WRAP_ADVANCED_SMD_TRANSISTOR, 32 * 64)
            .input(WRAP_ADVANCED_SMD_RESISTOR, 32 * 64)
            .input(WRAP_ADVANCED_SMD_INDUCTOR, 32 * 64)
            .input(WRAP_ARAM_CHIP, 32 * 64)
            .input(wireGtHex, EnrichedNaquadahTriniumEuropiumDuranide, 16 * 64)
            .fluidInputs(SolderingAlloy.getFluid(L * 20 * 64))
            .fluidInputs(Polybenzimidazole.getFluid(36 * 64 * 16 * 64)) // foil (36) * 64 * 16
            .fluidInputs(Europium.getFluid(L * 4 * 64 * 64)) // plate (144) * 4 * 64
            .output(WETWARE_MAINFRAME_UHV, 16 * 64)
            .EUt(300_000) // UV
            .duration(12 * MINUTE * 64) // Original: 60s, Wrapped: 60s * 16 = 960s
            .buildAndRegister()

        SINGULARITY_ASSEMBLY_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(frameGt, Tritanium, 16)
            .input(WETWARE_SUPER_COMPUTER_UV, 32)
            .input(WRAP_GOOWARE_SMD_DIODE, 8)
            .input(WRAP_GOOWARE_SMD_CAPACITOR, 8)
            .input(WRAP_GOOWARE_SMD_TRANSISTOR, 8)
            .input(WRAP_GOOWARE_SMD_RESISTOR, 8)
            .input(WRAP_GOOWARE_SMD_INDUCTOR, 8)
            .input(WRAP_ARAM_CHIP, 32)
            .input(wireGtHex, EnrichedNaquadahTriniumEuropiumDuranide, 16)
            .fluidInputs(SolderingAlloy.getFluid(L * 20))
            .fluidInputs(Polybenzimidazole.getFluid(36 * 64 * 16)) // foil (36) * 64 * 16
            .fluidInputs(Europium.getFluid(L * 4 * 64)) // plate (144) * 4 * 64
            .output(WETWARE_MAINFRAME_UHV, 16)
            .EUt(300_000) // UV
            .duration(6 * MINUTE) // Original: 30s, Wrapped: 30s * 16 = 480s
            .buildAndRegister()

        SINGULARITY_ASSEMBLY_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(frameGt, Tritanium, 16 * 64)
            .input(WETWARE_SUPER_COMPUTER_UV, 32 * 64)
            .input(WRAP_GOOWARE_SMD_DIODE, 8 * 64)
            .input(WRAP_GOOWARE_SMD_CAPACITOR, 8 * 64)
            .input(WRAP_GOOWARE_SMD_TRANSISTOR, 8 * 64)
            .input(WRAP_GOOWARE_SMD_RESISTOR, 8 * 64)
            .input(WRAP_GOOWARE_SMD_INDUCTOR, 8 * 64)
            .input(WRAP_ARAM_CHIP, 32 * 64)
            .input(wireGtHex, EnrichedNaquadahTriniumEuropiumDuranide, 16 * 64)
            .fluidInputs(SolderingAlloy.getFluid(L * 20 * 64))
            .fluidInputs(Polybenzimidazole.getFluid(36 * 64 * 16 * 64)) // foil (36) * 64 * 16
            .fluidInputs(Europium.getFluid(L * 4 * 64 * 64)) // plate (144) * 4 * 64
            .output(WETWARE_MAINFRAME_UHV, 16 * 64)
            .EUt(300_000) // UV
            .duration(6 * MINUTE * 64) // Original: 30s, Wrapped: 30s * 16 = 480s
            .buildAndRegister()

        SINGULARITY_ASSEMBLY_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(frameGt, Tritanium, 16)
            .input(WETWARE_SUPER_COMPUTER_UV, 32)
            .input(WRAP_OPTICAL_SMD_DIODE, 2)
            .input(WRAP_OPTICAL_SMD_CAPACITOR, 2)
            .input(WRAP_OPTICAL_SMD_TRANSISTOR, 2)
            .input(WRAP_OPTICAL_SMD_RESISTOR, 2)
            .input(WRAP_OPTICAL_SMD_INDUCTOR, 2)
            .input(WRAP_ARAM_CHIP, 32)
            .input(wireGtHex, EnrichedNaquadahTriniumEuropiumDuranide, 16)
            .fluidInputs(SolderingAlloy.getFluid(L * 20))
            .fluidInputs(Polybenzimidazole.getFluid(36 * 64 * 16)) // foil (36) * 64 * 16
            .fluidInputs(Europium.getFluid(L * 4 * 64)) // plate (144) * 4 * 64
            .output(WETWARE_MAINFRAME_UHV, 16)
            .EUt(300_000) // UV
            .duration(3 * MINUTE) // Original: 15s, Wrapped: 15s * 16 = 240s
            .buildAndRegister()

        SINGULARITY_ASSEMBLY_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(frameGt, Tritanium, 16 * 64)
            .input(WETWARE_SUPER_COMPUTER_UV, 32 * 64)
            .input(WRAP_OPTICAL_SMD_DIODE, 2 * 64)
            .input(WRAP_OPTICAL_SMD_CAPACITOR, 2 * 64)
            .input(WRAP_OPTICAL_SMD_TRANSISTOR, 2 * 64)
            .input(WRAP_OPTICAL_SMD_RESISTOR, 2 * 64)
            .input(WRAP_OPTICAL_SMD_INDUCTOR, 2 * 64)
            .input(WRAP_ARAM_CHIP, 32 * 64)
            .input(wireGtHex, EnrichedNaquadahTriniumEuropiumDuranide, 16 * 64)
            .fluidInputs(SolderingAlloy.getFluid(L * 20 * 64))
            .fluidInputs(Polybenzimidazole.getFluid(36 * 64 * 16 * 64)) // foil (36) * 64 * 16
            .fluidInputs(Europium.getFluid(L * 4 * 64 * 64)) // plate (144) * 4 * 64
            .output(WETWARE_MAINFRAME_UHV, 16 * 64)
            .EUt(300_000) // UV
            .duration(3 * MINUTE * 64) // Original: 15s, Wrapped: 15s * 16 = 240s
            .buildAndRegister()

        // endregion

        // region T8: Gooware

        // UHV Gooware Computer
        SINGULARITY_ASSEMBLY_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(WRAP_ULTIMATE_CIRCUIT_BOARD)
            .input(GOOWARE_ASSEMBLY_UV, 48)
            .input(WRAP_GOOWARE_SMD_DIODE, 8)
            .input(WRAP_NOR_CHIP, 16)
            .input(WRAP_ARAM_CHIP, 32)
            .input(wireGtQuadruple, Europium, 16)
            .fluidInputs(SolderingAlloy.getFluid(L * 16))
            .output(GOOWARE_COMPUTER_UHV, 32)
            .EUt(VA[UHV])
            .duration(8 * MINUTE) // Original: 40s, Wrapped: 40s * 16 = 640s
            .buildAndRegister()

        SINGULARITY_ASSEMBLY_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(WRAP_ULTIMATE_CIRCUIT_BOARD, 64)
            .input(GOOWARE_ASSEMBLY_UV, 48 * 64)
            .input(WRAP_GOOWARE_SMD_DIODE, 8 * 64)
            .input(WRAP_NOR_CHIP, 16 * 64)
            .input(WRAP_ARAM_CHIP, 32 * 64)
            .input(wireGtQuadruple, Europium, 16 * 64)
            .fluidInputs(SolderingAlloy.getFluid(L * 16 * 64))
            .output(GOOWARE_COMPUTER_UHV, 32 * 64)
            .EUt(VA[UHV])
            .duration(8 * MINUTE * 64) // Original: 40s, Wrapped: 40s * 16 = 640s
            .buildAndRegister()

        SINGULARITY_ASSEMBLY_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(WRAP_ULTIMATE_CIRCUIT_BOARD)
            .input(GOOWARE_ASSEMBLY_UV, 48)
            .input(WRAP_OPTICAL_SMD_DIODE, 2)
            .input(WRAP_NOR_CHIP, 16)
            .input(WRAP_ARAM_CHIP, 32)
            .input(wireGtQuadruple, Europium, 16)
            .fluidInputs(SolderingAlloy.getFluid(L * 16))
            .output(GOOWARE_COMPUTER_UHV, 32)
            .EUt(VA[UHV])
            .duration(4 * MINUTE) // Original: 20s, Wrapped: 20s * 16 = 320s
            .buildAndRegister()

        SINGULARITY_ASSEMBLY_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(WRAP_ULTIMATE_CIRCUIT_BOARD, 64)
            .input(GOOWARE_ASSEMBLY_UV, 48 * 64)
            .input(WRAP_OPTICAL_SMD_DIODE, 2 * 64)
            .input(WRAP_NOR_CHIP, 16 * 64)
            .input(WRAP_ARAM_CHIP, 32 * 64)
            .input(wireGtQuadruple, Europium, 16 * 64)
            .fluidInputs(SolderingAlloy.getFluid(L * 16 * 64))
            .output(GOOWARE_COMPUTER_UHV, 32 * 64)
            .EUt(VA[UHV])
            .duration(4 * MINUTE * 64) // Original: 20s, Wrapped: 20s * 16 = 320s
            .buildAndRegister()

        // UEV Gooware Mainframe
        SINGULARITY_ASSEMBLY_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(frameGt, Darmstadtium, 64)
            .input(GOOWARE_COMPUTER_UHV, 48)
            .input(WRAP_GOOWARE_SMD_DIODE, 64)
            .input(WRAP_GOOWARE_SMD_CAPACITOR, 64)
            .input(WRAP_GOOWARE_SMD_TRANSISTOR, 64)
            .input(WRAP_GOOWARE_SMD_RESISTOR, 64)
            .input(WRAP_GOOWARE_SMD_INDUCTOR, 64)
            .input(WRAP_ARAM_CHIP, 32)
            .input(wireGtHex, RutheniumTriniumAmericiumNeutronate, 128)
            .fluidInputs(SolderingAlloy.getFluid(L * 40))
            .fluidInputs(KaptonE.getFluid(L * 16))
            .fluidInputs(KaptonK.getFluid(36 * 64 * 16)) // foil (36) * 64 * 16
            .fluidInputs(Americium.getFluid(L * 8 * 16)) // plate (144) * 8 * 16
            .output(GOOWARE_MAINFRAME_UEV, 16)
            .EUt(VA[UHV])
            .duration(20 * MINUTE) // Original: 90s, Wrapped: 90s * 16 = 1440s
            .buildAndRegister()

        SINGULARITY_ASSEMBLY_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(frameGt, Darmstadtium, 64 * 64)
            .input(GOOWARE_COMPUTER_UHV, 48 * 64)
            .input(WRAP_GOOWARE_SMD_DIODE, 64 * 64)
            .input(WRAP_GOOWARE_SMD_CAPACITOR, 64 * 64)
            .input(WRAP_GOOWARE_SMD_TRANSISTOR, 64 * 64)
            .input(WRAP_GOOWARE_SMD_RESISTOR, 64 * 64)
            .input(WRAP_GOOWARE_SMD_INDUCTOR, 64 * 64)
            .input(WRAP_ARAM_CHIP, 32 * 64)
            .input(wireGtHex, RutheniumTriniumAmericiumNeutronate, 128 * 64)
            .fluidInputs(SolderingAlloy.getFluid(L * 40 * 64))
            .fluidInputs(KaptonE.getFluid(L * 16 * 64))
            .fluidInputs(KaptonK.getFluid(36 * 64 * 16 * 64)) // foil (36) * 64 * 16
            .fluidInputs(Americium.getFluid(L * 8 * 16 * 64)) // plate (144) * 8 * 16
            .output(GOOWARE_MAINFRAME_UEV, 16 * 64)
            .EUt(VA[UHV])
            .duration(20 * MINUTE * 64) // Original: 90s, Wrapped: 90s * 16 = 1440s
            .buildAndRegister()

        SINGULARITY_ASSEMBLY_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(frameGt, Darmstadtium, 64)
            .input(GOOWARE_COMPUTER_UHV, 48)
            .input(WRAP_OPTICAL_SMD_DIODE, 16)
            .input(WRAP_OPTICAL_SMD_CAPACITOR, 16)
            .input(WRAP_OPTICAL_SMD_TRANSISTOR, 16)
            .input(WRAP_OPTICAL_SMD_RESISTOR, 16)
            .input(WRAP_OPTICAL_SMD_INDUCTOR, 16)
            .input(WRAP_ARAM_CHIP, 32)
            .input(wireGtHex, RutheniumTriniumAmericiumNeutronate, 128)
            .fluidInputs(SolderingAlloy.getFluid(L * 40))
            .fluidInputs(KaptonE.getFluid(L * 16))
            .fluidInputs(KaptonK.getFluid(36 * 64 * 16)) // foil (36) * 64 * 16
            .fluidInputs(Americium.getFluid(L * 8 * 16)) // plate (144) * 8 * 16
            .output(GOOWARE_MAINFRAME_UEV, 16)
            .EUt(VA[UHV])
            .duration(10 * MINUTE) // Original: 45s, Wrapped: 45s * 16 = 720s
            .buildAndRegister()

        SINGULARITY_ASSEMBLY_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(frameGt, Darmstadtium, 64 * 64)
            .input(GOOWARE_COMPUTER_UHV, 48 * 64)
            .input(WRAP_OPTICAL_SMD_DIODE, 16 * 64)
            .input(WRAP_OPTICAL_SMD_CAPACITOR, 16 * 64)
            .input(WRAP_OPTICAL_SMD_TRANSISTOR, 16 * 64)
            .input(WRAP_OPTICAL_SMD_RESISTOR, 16 * 64)
            .input(WRAP_OPTICAL_SMD_INDUCTOR, 16 * 64)
            .input(WRAP_ARAM_CHIP, 32 * 64)
            .input(wireGtHex, RutheniumTriniumAmericiumNeutronate, 128 * 64)
            .fluidInputs(SolderingAlloy.getFluid(L * 40 * 64))
            .fluidInputs(KaptonE.getFluid(L * 16 * 64))
            .fluidInputs(KaptonK.getFluid(36 * 64 * 16 * 64)) // foil (36) * 64 * 16
            .fluidInputs(Americium.getFluid(L * 8 * 16 * 64)) // plate (144) * 8 * 16
            .output(GOOWARE_MAINFRAME_UEV, 16 * 64)
            .EUt(VA[UHV])
            .duration(10 * MINUTE * 64) // Original: 45s, Wrapped: 45s * 16 = 720s
            .buildAndRegister()

        SINGULARITY_ASSEMBLY_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(frameGt, Darmstadtium, 64)
            .input(GOOWARE_COMPUTER_UHV, 48)
            .input(WRAP_SPINTRONIC_SMD_DIODE, 4)
            .input(WRAP_SPINTRONIC_SMD_CAPACITOR, 4)
            .input(WRAP_SPINTRONIC_SMD_TRANSISTOR, 4)
            .input(WRAP_SPINTRONIC_SMD_RESISTOR, 4)
            .input(WRAP_SPINTRONIC_SMD_INDUCTOR, 4)
            .input(WRAP_ARAM_CHIP, 32)
            .input(wireGtHex, RutheniumTriniumAmericiumNeutronate, 128)
            .fluidInputs(SolderingAlloy.getFluid(L * 40))
            .fluidInputs(KaptonE.getFluid(L * 16))
            .fluidInputs(KaptonK.getFluid(36 * 64 * 16)) // foil (36) * 64 * 16
            .fluidInputs(Americium.getFluid(L * 8 * 16)) // plate (144) * 8 * 16
            .output(GOOWARE_MAINFRAME_UEV, 16)
            .EUt(VA[UHV])
            .duration(5 * MINUTE) // Original: 22.5s, Wrapped: 22.5s * 16 = 360s
            .buildAndRegister()

        SINGULARITY_ASSEMBLY_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(frameGt, Darmstadtium, 64 * 64)
            .input(GOOWARE_COMPUTER_UHV, 48 * 64)
            .input(WRAP_SPINTRONIC_SMD_DIODE, 4 * 64)
            .input(WRAP_SPINTRONIC_SMD_CAPACITOR, 4 * 64)
            .input(WRAP_SPINTRONIC_SMD_TRANSISTOR, 4 * 64)
            .input(WRAP_SPINTRONIC_SMD_RESISTOR, 4 * 64)
            .input(WRAP_SPINTRONIC_SMD_INDUCTOR, 4 * 64)
            .input(WRAP_ARAM_CHIP, 32 * 64)
            .input(wireGtHex, RutheniumTriniumAmericiumNeutronate, 128 * 64)
            .fluidInputs(SolderingAlloy.getFluid(L * 40 * 64))
            .fluidInputs(KaptonE.getFluid(L * 16 * 64))
            .fluidInputs(KaptonK.getFluid(36 * 64 * 16 * 64)) // foil (36) * 64 * 16
            .fluidInputs(Americium.getFluid(L * 8 * 16 * 64)) // plate (144) * 8 * 16
            .output(GOOWARE_MAINFRAME_UEV, 16 * 64)
            .EUt(VA[UHV])
            .duration(5 * MINUTE * 64) // Original: 22.5s, Wrapped: 22.5s * 16 = 360s
            .buildAndRegister()

        SINGULARITY_ASSEMBLY_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(frameGt, Darmstadtium, 64)
            .input(GOOWARE_COMPUTER_UHV, 48)
            .input(WRAP_COSMIC_SMD_DIODE)
            .input(WRAP_COSMIC_SMD_CAPACITOR)
            .input(WRAP_COSMIC_SMD_TRANSISTOR)
            .input(WRAP_COSMIC_SMD_RESISTOR)
            .input(WRAP_COSMIC_SMD_INDUCTOR)
            .input(WRAP_ARAM_CHIP, 32)
            .input(wireGtHex, RutheniumTriniumAmericiumNeutronate, 128)
            .fluidInputs(SolderingAlloy.getFluid(L * 40))
            .fluidInputs(KaptonE.getFluid(L * 16))
            .fluidInputs(KaptonK.getFluid(36 * 64 * 16)) // foil (36) * 64 * 16
            .fluidInputs(Americium.getFluid(L * 8 * 16)) // plate (144) * 8 * 16
            .output(GOOWARE_MAINFRAME_UEV, 16)
            .EUt(VA[UHV])
            .duration(2 * MINUTE + 30 * SECOND) // Original: 11.25s, Wrapped: 1.25s * 16 = 180s
            .buildAndRegister()

        SINGULARITY_ASSEMBLY_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(frameGt, Darmstadtium, 64 * 64)
            .input(GOOWARE_COMPUTER_UHV, 48 * 64)
            .input(WRAP_COSMIC_SMD_DIODE, 64)
            .input(WRAP_COSMIC_SMD_CAPACITOR, 64)
            .input(WRAP_COSMIC_SMD_TRANSISTOR, 64)
            .input(WRAP_COSMIC_SMD_RESISTOR, 64)
            .input(WRAP_COSMIC_SMD_INDUCTOR, 64)
            .input(WRAP_ARAM_CHIP, 32 * 64)
            .input(wireGtHex, RutheniumTriniumAmericiumNeutronate, 128 * 64)
            .fluidInputs(SolderingAlloy.getFluid(L * 40 * 64))
            .fluidInputs(KaptonE.getFluid(L * 16 * 64))
            .fluidInputs(KaptonK.getFluid(36 * 64 * 16 * 64)) // foil (36) * 64 * 16
            .fluidInputs(Americium.getFluid(L * 8 * 16 * 64)) // plate (144) * 8 * 16
            .output(GOOWARE_MAINFRAME_UEV, 16 * 64)
            .EUt(VA[UHV])
            .duration((2 * MINUTE + 30 * SECOND) * 64) // Original: 11.25s, Wrapped: 1.25s * 16 = 180s
            .buildAndRegister()

        // endregion

        // region T9: Optical

        // UEV Optical Supercomputer
        SINGULARITY_ASSEMBLY_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(WRAP_PERFECT_CIRCUIT_BOARD)
            .input(OPTICAL_ASSEMBLY_UHV, 48)
            .input(WRAP_OPTICAL_SMD_DIODE, 8)
            .input(WRAP_ACNOR_CHIP, 16)
            .input(WRAP_PRAM_CHIP, 32)
            .input(OPTICAL_FIBER, 64)
            .fluidInputs(SolderingAlloy.getFluid(L * 16))
            .fluidInputs(Polyetheretherketone.getFluid(L * 4))
            .fluidInputs(KaptonE.getFluid(36 * 32 * 16)) // foil (36) * 32 * 16
            .fluidInputs(Dubnium.getFluid(L * 4 * 16)) // plate (144) * 4 * 16
            .output(OPTICAL_COMPUTER_UEV, 32)
            .EUt(VA[UHV])
            .duration(8 * MINUTE) // Original: 40s, Wrapped: 40s * 16 = 640s
            .buildAndRegister()

        SINGULARITY_ASSEMBLY_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(WRAP_PERFECT_CIRCUIT_BOARD, 64)
            .input(OPTICAL_ASSEMBLY_UHV, 48 * 64)
            .input(WRAP_OPTICAL_SMD_DIODE, 8 * 64)
            .input(WRAP_ACNOR_CHIP, 16 * 64)
            .input(WRAP_PRAM_CHIP, 32 * 64)
            .input(OPTICAL_FIBER, 64 * 64)
            .fluidInputs(SolderingAlloy.getFluid(L * 16 * 64))
            .fluidInputs(Polyetheretherketone.getFluid(L * 4 * 64))
            .fluidInputs(KaptonE.getFluid(36 * 32 * 16 * 64)) // foil (36) * 32 * 16
            .fluidInputs(Dubnium.getFluid(L * 4 * 16 * 64)) // plate (144) * 4 * 16
            .output(OPTICAL_COMPUTER_UEV, 32 * 64)
            .EUt(VA[UHV])
            .duration(8 * MINUTE * 64) // Original: 40s, Wrapped: 40s * 16 = 640s
            .buildAndRegister()

        SINGULARITY_ASSEMBLY_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(WRAP_PERFECT_CIRCUIT_BOARD)
            .input(OPTICAL_ASSEMBLY_UHV, 48)
            .input(WRAP_SPINTRONIC_SMD_DIODE, 2)
            .input(WRAP_ACNOR_CHIP, 16)
            .input(WRAP_PRAM_CHIP, 32)
            .input(OPTICAL_FIBER, 64)
            .fluidInputs(SolderingAlloy.getFluid(L * 16))
            .fluidInputs(Polyetheretherketone.getFluid(L * 4))
            .fluidInputs(KaptonE.getFluid(36 * 32 * 16)) // foil (36) * 32 * 16
            .fluidInputs(Dubnium.getFluid(L * 4 * 16)) // plate (144) * 4 * 16
            .output(OPTICAL_COMPUTER_UEV, 32)
            .EUt(VA[UHV])
            .duration(4 * MINUTE) // Original: 20s, Wrapped: 20s * 16 = 320s
            .buildAndRegister()

        SINGULARITY_ASSEMBLY_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(WRAP_PERFECT_CIRCUIT_BOARD, 64)
            .input(OPTICAL_ASSEMBLY_UHV, 48 * 64)
            .input(WRAP_SPINTRONIC_SMD_DIODE, 2 * 64)
            .input(WRAP_ACNOR_CHIP, 16 * 64)
            .input(WRAP_PRAM_CHIP, 32 * 64)
            .input(OPTICAL_FIBER, 64 * 64)
            .fluidInputs(SolderingAlloy.getFluid(L * 16 * 64))
            .fluidInputs(Polyetheretherketone.getFluid(L * 4 * 64))
            .fluidInputs(KaptonE.getFluid(36 * 32 * 16 * 64)) // foil (36) * 32 * 16
            .fluidInputs(Dubnium.getFluid(L * 4 * 16 * 64)) // plate (144) * 4 * 16
            .output(OPTICAL_COMPUTER_UEV, 32 * 64)
            .EUt(VA[UHV])
            .duration(4 * MINUTE * 64) // Original: 20s, Wrapped: 20s * 16 = 320s
            .buildAndRegister()

        // UIV Optical Mainframe
        SINGULARITY_ASSEMBLY_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(frameGt, Neutronium, 16)
            .input(OPTICAL_COMPUTER_UEV, 32)
            .input(WRAP_OPTICAL_SMD_DIODE, 64)
            .input(WRAP_OPTICAL_SMD_CAPACITOR, 64)
            .input(WRAP_OPTICAL_SMD_TRANSISTOR, 64)
            .input(WRAP_OPTICAL_SMD_DIODE, 64)
            .input(WRAP_OPTICAL_SMD_INDUCTOR, 64)
            .input(WRAP_PRAM_CHIP, 32)
            .input(wireGtHex, VibraniumTritaniumActiniumIronSuperhydride, 128)
            .fluidInputs(SolderingAlloy.getFluid(L * 80 * 16))
            .fluidInputs(KaptonE.getFluid(L * 32 * 16 + 32 * 64 * 16)) // foil (36) * 64 * 16
            .fluidInputs(Polyetheretherketone.getFluid(L * 8 * 16))
            .fluidInputs(Dubnium.getFluid(L * 8 * 16)) // plate (144) * 8 * 16
            .output(OPTICAL_MAINFRAME_UIV, 16)
            .EUt(VA[UEV])
            .duration(20 * MINUTE) // Original: 90s, Wrapped: 90s * 16 = 1440s
            .buildAndRegister()

        SINGULARITY_ASSEMBLY_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(frameGt, Neutronium, 16 * 64)
            .input(OPTICAL_COMPUTER_UEV, 32 * 64)
            .input(WRAP_OPTICAL_SMD_DIODE, 64 * 64)
            .input(WRAP_OPTICAL_SMD_CAPACITOR, 64 * 64)
            .input(WRAP_OPTICAL_SMD_TRANSISTOR, 64 * 64)
            .input(WRAP_OPTICAL_SMD_DIODE, 64 * 64)
            .input(WRAP_OPTICAL_SMD_INDUCTOR, 64 * 64)
            .input(WRAP_PRAM_CHIP, 32 * 64)
            .input(wireGtHex, VibraniumTritaniumActiniumIronSuperhydride, 128 * 64)
            .fluidInputs(SolderingAlloy.getFluid(L * 80 * 16 * 64))
            .fluidInputs(KaptonE.getFluid((L * 32 * 16 + 32 * 64 * 16) * 64)) // foil (36) * 64 * 16
            .fluidInputs(Polyetheretherketone.getFluid(L * 8 * 16 * 64))
            .fluidInputs(Dubnium.getFluid(L * 8 * 16 * 64)) // plate (144) * 8 * 16
            .output(OPTICAL_MAINFRAME_UIV, 16 * 64)
            .EUt(VA[UEV])
            .duration(20 * MINUTE * 64) // Original: 90s, Wrapped: 90s * 16 = 1440s
            .buildAndRegister()

        SINGULARITY_ASSEMBLY_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(frameGt, Neutronium, 16)
            .input(OPTICAL_COMPUTER_UEV, 32)
            .input(WRAP_SPINTRONIC_SMD_DIODE, 16)
            .input(WRAP_SPINTRONIC_SMD_CAPACITOR, 16)
            .input(WRAP_SPINTRONIC_SMD_TRANSISTOR, 16)
            .input(WRAP_SPINTRONIC_SMD_DIODE, 16)
            .input(WRAP_SPINTRONIC_SMD_INDUCTOR, 16)
            .input(WRAP_PRAM_CHIP, 32)
            .input(wireGtHex, VibraniumTritaniumActiniumIronSuperhydride, 128)
            .fluidInputs(SolderingAlloy.getFluid(L * 80 * 16))
            .fluidInputs(KaptonE.getFluid(L * 32 * 16 + 32 * 64 * 16)) // foil (36) * 64 * 16
            .fluidInputs(Polyetheretherketone.getFluid(L * 8 * 16))
            .fluidInputs(Dubnium.getFluid(L * 8 * 16)) // plate (144) * 8 * 16
            .output(OPTICAL_MAINFRAME_UIV, 16)
            .EUt(VA[UEV])
            .duration(10 * MINUTE) // Original: 45s, Wrapped: 45s * 16 = 720s
            .buildAndRegister()

        SINGULARITY_ASSEMBLY_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(frameGt, Neutronium, 16 * 64)
            .input(OPTICAL_COMPUTER_UEV, 32 * 64)
            .input(WRAP_SPINTRONIC_SMD_DIODE, 16 * 64)
            .input(WRAP_SPINTRONIC_SMD_CAPACITOR, 16 * 64)
            .input(WRAP_SPINTRONIC_SMD_TRANSISTOR, 16 * 64)
            .input(WRAP_SPINTRONIC_SMD_DIODE, 16 * 64)
            .input(WRAP_SPINTRONIC_SMD_INDUCTOR, 16 * 64)
            .input(WRAP_PRAM_CHIP, 32 * 64)
            .input(wireGtHex, VibraniumTritaniumActiniumIronSuperhydride, 128 * 64)
            .fluidInputs(SolderingAlloy.getFluid(L * 80 * 16 * 64))
            .fluidInputs(KaptonE.getFluid((L * 32 * 16 + 32 * 64 * 16) * 64)) // foil (36) * 64 * 16
            .fluidInputs(Polyetheretherketone.getFluid(L * 8 * 16 * 64))
            .fluidInputs(Dubnium.getFluid(L * 8 * 16 * 64)) // plate (144) * 8 * 16
            .output(OPTICAL_MAINFRAME_UIV, 16 * 64)
            .EUt(VA[UEV])
            .duration(10 * MINUTE * 64) // Original: 45s, Wrapped: 45s * 16 = 720s
            .buildAndRegister()

        SINGULARITY_ASSEMBLY_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(frameGt, Neutronium, 16)
            .input(OPTICAL_COMPUTER_UEV, 32)
            .input(WRAP_COSMIC_SMD_DIODE, 4)
            .input(WRAP_COSMIC_SMD_CAPACITOR, 4)
            .input(WRAP_COSMIC_SMD_TRANSISTOR, 4)
            .input(WRAP_COSMIC_SMD_DIODE, 4)
            .input(WRAP_COSMIC_SMD_INDUCTOR, 4)
            .input(WRAP_PRAM_CHIP, 32)
            .input(wireGtHex, VibraniumTritaniumActiniumIronSuperhydride, 128)
            .fluidInputs(SolderingAlloy.getFluid(L * 80 * 16))
            .fluidInputs(KaptonE.getFluid(L * 32 * 16 + 32 * 64 * 16)) // foil (36) * 64 * 16
            .fluidInputs(Polyetheretherketone.getFluid(L * 8 * 16))
            .fluidInputs(Dubnium.getFluid(L * 8 * 16)) // plate (144) * 8 * 16
            .output(OPTICAL_MAINFRAME_UIV, 16)
            .EUt(VA[UEV])
            .duration(5 * MINUTE) // Original: 22.5s, Wrapped: 22.5s * 16 = 360s
            .buildAndRegister()

        SINGULARITY_ASSEMBLY_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(frameGt, Neutronium, 16 * 64)
            .input(OPTICAL_COMPUTER_UEV, 32 * 64)
            .input(WRAP_COSMIC_SMD_DIODE, 4 * 64)
            .input(WRAP_COSMIC_SMD_CAPACITOR, 4 * 64)
            .input(WRAP_COSMIC_SMD_TRANSISTOR, 4 * 64)
            .input(WRAP_COSMIC_SMD_DIODE, 4 * 64)
            .input(WRAP_COSMIC_SMD_INDUCTOR, 4 * 64)
            .input(WRAP_PRAM_CHIP, 32 * 64)
            .input(wireGtHex, VibraniumTritaniumActiniumIronSuperhydride, 128 * 64)
            .fluidInputs(SolderingAlloy.getFluid(L * 80 * 16 * 64))
            .fluidInputs(KaptonE.getFluid((L * 32 * 16 + 32 * 64 * 16) * 64)) // foil (36) * 64 * 16
            .fluidInputs(Polyetheretherketone.getFluid(L * 8 * 16 * 64))
            .fluidInputs(Dubnium.getFluid(L * 8 * 16 * 64)) // plate (144) * 8 * 16
            .output(OPTICAL_MAINFRAME_UIV, 16 * 64)
            .EUt(VA[UEV])
            .duration(5 * MINUTE * 64) // Original: 22.5s, Wrapped: 22.5s * 16 = 360s
            .buildAndRegister()

        SINGULARITY_ASSEMBLY_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(frameGt, Neutronium, 16)
            .input(OPTICAL_COMPUTER_UEV, 32)
            .input(WRAP_SUPRACAUSAL_SMD_DIODE)
            .input(WRAP_SUPRACAUSAL_SMD_CAPACITOR)
            .input(WRAP_SUPRACAUSAL_SMD_TRANSISTOR)
            .input(WRAP_SUPRACAUSAL_SMD_DIODE)
            .input(WRAP_SUPRACAUSAL_SMD_INDUCTOR)
            .input(WRAP_PRAM_CHIP, 32)
            .input(wireGtHex, VibraniumTritaniumActiniumIronSuperhydride, 128)
            .fluidInputs(SolderingAlloy.getFluid(L * 80 * 16))
            .fluidInputs(KaptonE.getFluid(L * 32 * 16 + 32 * 64 * 16)) // foil (36) * 64 * 16
            .fluidInputs(Polyetheretherketone.getFluid(L * 8 * 16))
            .fluidInputs(Dubnium.getFluid(L * 8 * 16)) // plate (144) * 8 * 16
            .output(OPTICAL_MAINFRAME_UIV, 16)
            .EUt(VA[UEV])
            .duration(2 * MINUTE + 30 * SECOND) // Original: 11.25s, Wrapped: 11.25s * 16 = 180s
            .buildAndRegister()

        SINGULARITY_ASSEMBLY_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(frameGt, Neutronium, 16 * 64)
            .input(OPTICAL_COMPUTER_UEV, 32 * 64)
            .input(WRAP_SUPRACAUSAL_SMD_DIODE, 64)
            .input(WRAP_SUPRACAUSAL_SMD_CAPACITOR, 64)
            .input(WRAP_SUPRACAUSAL_SMD_TRANSISTOR, 64)
            .input(WRAP_SUPRACAUSAL_SMD_DIODE, 64)
            .input(WRAP_SUPRACAUSAL_SMD_INDUCTOR, 64)
            .input(WRAP_PRAM_CHIP, 32 * 64)
            .input(wireGtHex, VibraniumTritaniumActiniumIronSuperhydride, 128 * 64)
            .fluidInputs(SolderingAlloy.getFluid(L * 80 * 16 * 64))
            .fluidInputs(KaptonE.getFluid((L * 32 * 16 + 32 * 64 * 16) * 64)) // foil (36) * 64 * 16
            .fluidInputs(Polyetheretherketone.getFluid(L * 8 * 16 * 64))
            .fluidInputs(Dubnium.getFluid(L * 8 * 16 * 64)) // plate (144) * 8 * 16
            .output(OPTICAL_MAINFRAME_UIV, 16 * 64)
            .EUt(VA[UEV])
            .duration((2 * MINUTE + 30 * SECOND) * 64) // Original: 11.25s, Wrapped: 11.25s * 16 = 180s
            .buildAndRegister()

        // endregion

        // region T10: Spintronic

        // UEV Spintronic Processor Assembly
        for (rubber in arrayOf(
            PolyphosphonitrileFluoroRubber.getFluid(36 * 32 * 16), // foil (36) * 32 * 16
            PolytetramethyleneGlycolRubber.getFluid(36 * 32 * 16)))
        {
            SINGULARITY_ASSEMBLY_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(WRAP_INFINITE_CIRCUIT_BOARD)
                .input(SPINTRONIC_PROCESSOR_UHV, 64)
                .input(WRAP_SPINTRONIC_SMD_CAPACITOR, 16)
                .input(WRAP_SPINTRONIC_SMD_INDUCTOR, 16)
                .input(WRAP_UHASOC_CHIP, 32)
                .input(wireGtQuadruple, CarbonNanotube, 16)
                .fluidInputs(MutatedLivingSolder.getFluid(L * 4 * 16))
                .fluidInputs(rubber)
                .output(SPINTRONIC_ASSEMBLY_UEV, 48)
                .EUt(VA[UEV])
                .duration(4 * MINUTE) // Original: 20s, Wrapped: 20s * 16 = 320s
                .buildAndRegister()
        }

        for (rubber in arrayOf(
            PolyphosphonitrileFluoroRubber.getFluid(36 * 32 * 16 * 64), // foil (36) * 32 * 16
            PolytetramethyleneGlycolRubber.getFluid(36 * 32 * 16 * 64)))
        {
            SINGULARITY_ASSEMBLY_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(WRAP_INFINITE_CIRCUIT_BOARD, 64)
                .input(SPINTRONIC_PROCESSOR_UHV, 64 * 64)
                .input(WRAP_SPINTRONIC_SMD_CAPACITOR, 16 * 64)
                .input(WRAP_SPINTRONIC_SMD_INDUCTOR, 16 * 64)
                .input(WRAP_UHASOC_CHIP, 32 * 64)
                .input(wireGtQuadruple, CarbonNanotube, 16 * 64)
                .fluidInputs(MutatedLivingSolder.getFluid(L * 4 * 16 * 64))
                .fluidInputs(rubber)
                .output(SPINTRONIC_ASSEMBLY_UEV, 48 * 64)
                .EUt(VA[UEV])
                .duration(4 * MINUTE * 64) // Original: 20s, Wrapped: 20s * 16 = 320s
                .buildAndRegister()
        }

        for (rubber in arrayOf(
            PolyphosphonitrileFluoroRubber.getFluid(36 * 32 * 16), // foil (36) * 32 * 16
            PolytetramethyleneGlycolRubber.getFluid(36 * 32 * 16)))
        {
            SINGULARITY_ASSEMBLY_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(WRAP_INFINITE_CIRCUIT_BOARD)
                .input(SPINTRONIC_PROCESSOR_UHV, 64)
                .input(WRAP_COSMIC_SMD_CAPACITOR, 4)
                .input(WRAP_COSMIC_SMD_INDUCTOR, 4)
                .input(WRAP_UHASOC_CHIP, 32)
                .input(wireGtQuadruple, CarbonNanotube, 16)
                .fluidInputs(MutatedLivingSolder.getFluid(L * 4 * 16))
                .fluidInputs(rubber)
                .output(SPINTRONIC_ASSEMBLY_UEV, 48)
                .EUt(VA[UEV])
                .duration(2 * MINUTE) // Original: 10s, Wrapped: 10s * 16 = 160s
                .buildAndRegister()
        }

        for (rubber in arrayOf(
            PolyphosphonitrileFluoroRubber.getFluid(36 * 32 * 16 * 64), // foil (36) * 32 * 16
            PolytetramethyleneGlycolRubber.getFluid(36 * 32 * 16 * 64)))
        {
            SINGULARITY_ASSEMBLY_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(WRAP_INFINITE_CIRCUIT_BOARD, 64)
                .input(SPINTRONIC_PROCESSOR_UHV, 64 * 64)
                .input(WRAP_COSMIC_SMD_CAPACITOR, 4 * 64)
                .input(WRAP_COSMIC_SMD_INDUCTOR, 4 * 64)
                .input(WRAP_UHASOC_CHIP, 32 * 64)
                .input(wireGtQuadruple, CarbonNanotube, 16 * 64)
                .fluidInputs(MutatedLivingSolder.getFluid(L * 4 * 16 * 64))
                .fluidInputs(rubber)
                .output(SPINTRONIC_ASSEMBLY_UEV, 48 * 64)
                .EUt(VA[UEV])
                .duration(2 * MINUTE * 64) // Original: 10s, Wrapped: 10s * 16 = 160s
                .buildAndRegister()
        }

        for (rubber in arrayOf(
            PolyphosphonitrileFluoroRubber.getFluid(36 * 32 * 16), // foil (36) * 32 * 16
            PolytetramethyleneGlycolRubber.getFluid(36 * 32 * 16)))
        {
            SINGULARITY_ASSEMBLY_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(WRAP_INFINITE_CIRCUIT_BOARD)
                .input(SPINTRONIC_PROCESSOR_UHV, 64)
                .input(WRAP_SUPRACAUSAL_SMD_CAPACITOR)
                .input(WRAP_SUPRACAUSAL_SMD_INDUCTOR)
                .input(WRAP_UHASOC_CHIP, 32)
                .input(wireGtQuadruple, CarbonNanotube, 16)
                .fluidInputs(MutatedLivingSolder.getFluid(L * 4 * 16))
                .fluidInputs(rubber)
                .output(SPINTRONIC_ASSEMBLY_UEV, 48)
                .EUt(VA[UEV])
                .duration(1 * MINUTE) // Original: 5s, Wrapped: 5s * 16 = 80s
                .buildAndRegister()
        }

        for (rubber in arrayOf(
            PolyphosphonitrileFluoroRubber.getFluid(36 * 32 * 16 * 64), // foil (36) * 32 * 16
            PolytetramethyleneGlycolRubber.getFluid(36 * 32 * 16 * 64)))
        {
            SINGULARITY_ASSEMBLY_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(WRAP_INFINITE_CIRCUIT_BOARD, 64)
                .input(SPINTRONIC_PROCESSOR_UHV, 64 * 64)
                .input(WRAP_SUPRACAUSAL_SMD_CAPACITOR, 64)
                .input(WRAP_SUPRACAUSAL_SMD_INDUCTOR, 64)
                .input(WRAP_UHASOC_CHIP, 32 * 64)
                .input(wireGtQuadruple, CarbonNanotube, 16 * 64)
                .fluidInputs(MutatedLivingSolder.getFluid(L * 4 * 16 * 64))
                .fluidInputs(rubber)
                .output(SPINTRONIC_ASSEMBLY_UEV, 48 * 64)
                .EUt(VA[UEV])
                .duration(1 * MINUTE * 64) // Original: 5s, Wrapped: 5s * 16 = 80s
                .buildAndRegister()
        }

        // UIV Spintronic Computer
        SINGULARITY_ASSEMBLY_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(WRAP_INFINITE_CIRCUIT_BOARD)
            .input(SPINTRONIC_ASSEMBLY_UEV, 48)
            .input(WRAP_SPINTRONIC_SMD_DIODE, 16)
            .input(WRAP_MINAND_CHIP, 32)
            .input(WRAP_STTRAM_CHIP, 64)
            .input(wireGtQuadruple, CarbonNanotube, 32)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 16 * 16))
            .fluidInputs(Zylon.getFluid(L * 8 * 16))
            .fluidInputs(Adamantium.getFluid(L * 4 * 16))
            .fluidInputs(Fullerene.getFluid(36 * 48 * 16)) // foil (36) * 48 * 16
            .fluidInputs(Bohrium.getFluid(L * 8 * 16)) // plate (144) * 8 * 16
            .output(SPINTRONIC_COMPUTER_UIV, 32)
            .EUt(VA[UEV])
            .duration(8 * MINUTE) // Original: 40s, Wrapped: 40s * 16 = 640s
            .buildAndRegister()

        SINGULARITY_ASSEMBLY_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(WRAP_INFINITE_CIRCUIT_BOARD, 64)
            .input(SPINTRONIC_ASSEMBLY_UEV, 48 * 64)
            .input(WRAP_SPINTRONIC_SMD_DIODE, 16 * 64)
            .input(WRAP_MINAND_CHIP, 32 * 64)
            .input(WRAP_STTRAM_CHIP, 64 * 64)
            .input(wireGtQuadruple, CarbonNanotube, 32 * 64)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 16 * 16 * 64))
            .fluidInputs(Zylon.getFluid(L * 8 * 16 * 64))
            .fluidInputs(Adamantium.getFluid(L * 4 * 16 * 64))
            .fluidInputs(Fullerene.getFluid(36 * 48 * 16 * 64)) // foil (36) * 48 * 16
            .fluidInputs(Bohrium.getFluid(L * 8 * 16 * 64)) // plate (144) * 8 * 16
            .output(SPINTRONIC_COMPUTER_UIV, 32 * 64)
            .EUt(VA[UEV])
            .duration(8 * MINUTE * 64) // Original: 40s, Wrapped: 40s * 16 = 640s
            .buildAndRegister()

        SINGULARITY_ASSEMBLY_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(WRAP_INFINITE_CIRCUIT_BOARD)
            .input(SPINTRONIC_ASSEMBLY_UEV, 48)
            .input(WRAP_COSMIC_SMD_DIODE, 4)
            .input(WRAP_MINAND_CHIP, 32)
            .input(WRAP_STTRAM_CHIP, 64)
            .input(wireGtQuadruple, CarbonNanotube, 32)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 16 * 16))
            .fluidInputs(Zylon.getFluid(L * 8 * 16))
            .fluidInputs(Adamantium.getFluid(L * 4 * 16))
            .fluidInputs(Fullerene.getFluid(36 * 48 * 16)) // foil (36) * 48 * 16
            .fluidInputs(Bohrium.getFluid(L * 8 * 16)) // plate (144) * 8 * 16
            .output(SPINTRONIC_COMPUTER_UIV, 32)
            .EUt(VA[UEV])
            .duration(4 * MINUTE) // Original: 20s, Wrapped: 20s * 16 = 320s
            .buildAndRegister()

        SINGULARITY_ASSEMBLY_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(WRAP_INFINITE_CIRCUIT_BOARD, 64)
            .input(SPINTRONIC_ASSEMBLY_UEV, 48 * 64)
            .input(WRAP_COSMIC_SMD_DIODE, 4 * 64)
            .input(WRAP_MINAND_CHIP, 32 * 64)
            .input(WRAP_STTRAM_CHIP, 64 * 64)
            .input(wireGtQuadruple, CarbonNanotube, 32 * 64)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 16 * 16 * 64))
            .fluidInputs(Zylon.getFluid(L * 8 * 16 * 64))
            .fluidInputs(Adamantium.getFluid(L * 4 * 16 * 64))
            .fluidInputs(Fullerene.getFluid(36 * 48 * 16 * 64)) // foil (36) * 48 * 16
            .fluidInputs(Bohrium.getFluid(L * 8 * 16 * 64)) // plate (144) * 8 * 16
            .output(SPINTRONIC_COMPUTER_UIV, 32 * 64)
            .EUt(VA[UEV])
            .duration(4 * MINUTE * 64) // Original: 20s, Wrapped: 20s * 16 = 320s
            .buildAndRegister()

        SINGULARITY_ASSEMBLY_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(WRAP_INFINITE_CIRCUIT_BOARD)
            .input(SPINTRONIC_ASSEMBLY_UEV, 48)
            .input(WRAP_SUPRACAUSAL_SMD_DIODE)
            .input(WRAP_MINAND_CHIP, 32)
            .input(WRAP_STTRAM_CHIP, 64)
            .input(wireGtQuadruple, CarbonNanotube, 32)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 16 * 16))
            .fluidInputs(Zylon.getFluid(L * 8 * 16))
            .fluidInputs(Adamantium.getFluid(L * 4 * 16))
            .fluidInputs(Fullerene.getFluid(36 * 48 * 16)) // foil (36) * 48 * 16
            .fluidInputs(Bohrium.getFluid(L * 8 * 16)) // plate (144) * 8 * 16
            .output(SPINTRONIC_COMPUTER_UIV, 32)
            .EUt(VA[UEV])
            .duration(2 * MINUTE) // Original: 10s, Wrapped: 10s * 16 = 160s
            .buildAndRegister()

        SINGULARITY_ASSEMBLY_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(WRAP_INFINITE_CIRCUIT_BOARD, 64)
            .input(SPINTRONIC_ASSEMBLY_UEV, 48 * 64)
            .input(WRAP_SUPRACAUSAL_SMD_DIODE, 64)
            .input(WRAP_MINAND_CHIP, 32 * 64)
            .input(WRAP_STTRAM_CHIP, 64 * 64)
            .input(wireGtQuadruple, CarbonNanotube, 32 * 64)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 16 * 16 * 64))
            .fluidInputs(Zylon.getFluid(L * 8 * 16 * 64))
            .fluidInputs(Adamantium.getFluid(L * 4 * 16 * 64))
            .fluidInputs(Fullerene.getFluid(36 * 48 * 16 * 64)) // foil (36) * 48 * 16
            .fluidInputs(Bohrium.getFluid(L * 8 * 16 * 64)) // plate (144) * 8 * 16
            .output(SPINTRONIC_COMPUTER_UIV, 32 * 64)
            .EUt(VA[UEV])
            .duration(2 * MINUTE * 64) // Original: 10s, Wrapped: 10s * 16 = 160s
            .buildAndRegister()

        // UXV Spintronic Mainframe
        SINGULARITY_ASSEMBLY_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(frameGt, Vibranium, 16)
            .input(SPINTRONIC_COMPUTER_UIV, 32)
            .input(WRAP_SPINTRONIC_SMD_DIODE, 64)
            .input(WRAP_SPINTRONIC_SMD_CAPACITOR, 64)
            .input(WRAP_SPINTRONIC_SMD_TRANSISTOR, 64)
            .input(WRAP_SPINTRONIC_SMD_RESISTOR, 64)
            .input(WRAP_SPINTRONIC_SMD_INDUCTOR, 64)
            .input(WRAP_STTRAM_CHIP, 48)
            .input(wireGtHex, FullereneSuperconductor, 128)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 80 * 16))
            .fluidInputs(Fullerene.getFluid(L * 32 * 16 + 36 * 64 * 16)) // foil (36) * 64 * 16
            .fluidInputs(Zylon.getFluid(L * 16 * 16))
            .fluidInputs(Adamantium.getFluid(L * 8 * 16))
            .fluidInputs(Bohrium.getFluid(L * 16 * 16)) // plate (144) * 16 * 16
            .output(SPINTRONIC_MAINFRAME_UXV, 16)
            .EUt(VA[UIV])
            .duration(20 * MINUTE) // Original: 90s, Wrapped: 90s * 16 = 1440s
            .buildAndRegister()

        SINGULARITY_ASSEMBLY_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(frameGt, Vibranium, 16 * 64)
            .input(SPINTRONIC_COMPUTER_UIV, 32 * 64)
            .input(WRAP_SPINTRONIC_SMD_DIODE, 64 * 64)
            .input(WRAP_SPINTRONIC_SMD_CAPACITOR, 64 * 64)
            .input(WRAP_SPINTRONIC_SMD_TRANSISTOR, 64 * 64)
            .input(WRAP_SPINTRONIC_SMD_RESISTOR, 64 * 64)
            .input(WRAP_SPINTRONIC_SMD_INDUCTOR, 64 * 64)
            .input(WRAP_STTRAM_CHIP, 48 * 64)
            .input(wireGtHex, FullereneSuperconductor, 128 * 64)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 80 * 16 * 64))
            .fluidInputs(Fullerene.getFluid((L * 32 * 16 + 36 * 64 * 16) * 64)) // foil (36) * 64 * 16
            .fluidInputs(Zylon.getFluid(L * 16 * 16 * 64))
            .fluidInputs(Adamantium.getFluid(L * 8 * 16 * 64))
            .fluidInputs(Bohrium.getFluid(L * 16 * 16 * 64)) // plate (144) * 16 * 16
            .output(SPINTRONIC_MAINFRAME_UXV, 16 * 64)
            .EUt(VA[UIV])
            .duration(20 * MINUTE * 64) // Original: 90s, Wrapped: 90s * 16 = 1440s
            .buildAndRegister()

        SINGULARITY_ASSEMBLY_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(frameGt, Vibranium, 16)
            .input(SPINTRONIC_COMPUTER_UIV, 32)
            .input(WRAP_COSMIC_SMD_DIODE, 16)
            .input(WRAP_COSMIC_SMD_CAPACITOR, 16)
            .input(WRAP_COSMIC_SMD_TRANSISTOR, 16)
            .input(WRAP_COSMIC_SMD_RESISTOR, 16)
            .input(WRAP_COSMIC_SMD_INDUCTOR, 16)
            .input(WRAP_STTRAM_CHIP, 48)
            .input(wireGtHex, FullereneSuperconductor, 128)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 80 * 16))
            .fluidInputs(Fullerene.getFluid(L * 32 * 16 + 36 * 64 * 16)) // foil (36) * 64 * 16
            .fluidInputs(Zylon.getFluid(L * 16 * 16))
            .fluidInputs(Adamantium.getFluid(L * 8 * 16))
            .fluidInputs(Bohrium.getFluid(L * 16 * 16)) // plate (144) * 16 * 16
            .output(SPINTRONIC_MAINFRAME_UXV, 16)
            .EUt(VA[UIV])
            .duration(10 * MINUTE) // Original: 45s, Wrapped: 45s * 16 = 720s
            .buildAndRegister()

        SINGULARITY_ASSEMBLY_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(frameGt, Vibranium, 16 * 64)
            .input(SPINTRONIC_COMPUTER_UIV, 32 * 64)
            .input(WRAP_COSMIC_SMD_DIODE, 16 * 64)
            .input(WRAP_COSMIC_SMD_CAPACITOR, 16 * 64)
            .input(WRAP_COSMIC_SMD_TRANSISTOR, 16 * 64)
            .input(WRAP_COSMIC_SMD_RESISTOR, 16 * 64)
            .input(WRAP_COSMIC_SMD_INDUCTOR, 16 * 64)
            .input(WRAP_STTRAM_CHIP, 48 * 64)
            .input(wireGtHex, FullereneSuperconductor, 128 * 64)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 80 * 16 * 64))
            .fluidInputs(Fullerene.getFluid((L * 32 * 16 + 36 * 64 * 16) * 64)) // foil (36) * 64 * 16
            .fluidInputs(Zylon.getFluid(L * 16 * 16 * 64))
            .fluidInputs(Adamantium.getFluid(L * 8 * 16 * 64))
            .fluidInputs(Bohrium.getFluid(L * 16 * 16 * 64)) // plate (144) * 16 * 16
            .output(SPINTRONIC_MAINFRAME_UXV, 16 * 64)
            .EUt(VA[UIV])
            .duration(10 * MINUTE * 64) // Original: 45s, Wrapped: 45s * 16 = 720s
            .buildAndRegister()

        SINGULARITY_ASSEMBLY_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(frameGt, Vibranium, 16)
            .input(SPINTRONIC_COMPUTER_UIV, 32)
            .input(WRAP_SUPRACAUSAL_SMD_DIODE, 4)
            .input(WRAP_SUPRACAUSAL_SMD_CAPACITOR, 4)
            .input(WRAP_SUPRACAUSAL_SMD_TRANSISTOR, 4)
            .input(WRAP_SUPRACAUSAL_SMD_RESISTOR, 4)
            .input(WRAP_SUPRACAUSAL_SMD_INDUCTOR, 4)
            .input(WRAP_STTRAM_CHIP, 48)
            .input(wireGtHex, FullereneSuperconductor, 128)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 80 * 16))
            .fluidInputs(Fullerene.getFluid(L * 32 * 16 + 36 * 64 * 16)) // foil (36) * 64 * 16
            .fluidInputs(Zylon.getFluid(L * 16 * 16))
            .fluidInputs(Adamantium.getFluid(L * 8 * 16))
            .fluidInputs(Bohrium.getFluid(L * 16 * 16)) // plate (144) * 16 * 16
            .output(SPINTRONIC_MAINFRAME_UXV, 16)
            .EUt(VA[UIV])
            .duration(5 * MINUTE) // Original: 22.5s, Wrapped: 22.5s * 16 = 360s
            .buildAndRegister()

        SINGULARITY_ASSEMBLY_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(frameGt, Vibranium, 16 * 64)
            .input(SPINTRONIC_COMPUTER_UIV, 32 * 64)
            .input(WRAP_SUPRACAUSAL_SMD_DIODE, 4 * 64)
            .input(WRAP_SUPRACAUSAL_SMD_CAPACITOR, 4 * 64)
            .input(WRAP_SUPRACAUSAL_SMD_TRANSISTOR, 4 * 64)
            .input(WRAP_SUPRACAUSAL_SMD_RESISTOR, 4 * 64)
            .input(WRAP_SUPRACAUSAL_SMD_INDUCTOR, 4 * 64)
            .input(WRAP_STTRAM_CHIP, 48 * 64)
            .input(wireGtHex, FullereneSuperconductor, 128 * 64)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 80 * 16 * 64))
            .fluidInputs(Fullerene.getFluid((L * 32 * 16 + 36 * 64 * 16) * 64)) // foil (36) * 64 * 16
            .fluidInputs(Zylon.getFluid(L * 16 * 16 * 64))
            .fluidInputs(Adamantium.getFluid(L * 8 * 16 * 64))
            .fluidInputs(Bohrium.getFluid(L * 16 * 16 * 64)) // plate (144) * 16 * 16
            .output(SPINTRONIC_MAINFRAME_UXV, 16 * 64)
            .EUt(VA[UIV])
            .duration(5 * MINUTE * 64) // Original: 22.5s, Wrapped: 22.5s * 16 = 360s
            .buildAndRegister()

        // endregion

        // region T11: Cosmic

        // UIV Cosmic Processor Assembly
        for (rubber in arrayOf(
            PolyphosphonitrileFluoroRubber.getFluid(36 * 64 * 16), // foil (36) * 64 * 16
            PolytetramethyleneGlycolRubber.getFluid(36 * 64 * 16)))
        {
            SINGULARITY_ASSEMBLY_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(WRAP_COSMIC_INFORMATION_MODULE)
                .input(COSMIC_PROCESSOR_UEV, 64)
                .input(WRAP_COSMIC_SMD_CAPACITOR, 32)
                .input(WRAP_COSMIC_SMD_INDUCTOR, 32)
                .input(WRAP_EARCPU_CHIP, 16)
                .input(wireGtQuadruple, Infinity, 16)
                .fluidInputs(MutatedLivingSolder.getFluid(L * 4 * 16))
                .fluidInputs(rubber)
                .output(COSMIC_ASSEMBLY_UIV, 48)
                .EUt(VA[UIV])
                .duration(4 * MINUTE) // Original: 20s, Wrapped: 20s * 16 = 320s
                .buildAndRegister()
        }

        for (rubber in arrayOf(
            PolyphosphonitrileFluoroRubber.getFluid(36 * 64 * 16 * 64), // foil (36) * 64 * 16
            PolytetramethyleneGlycolRubber.getFluid(36 * 64 * 16 * 64)))
        {
            SINGULARITY_ASSEMBLY_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(WRAP_COSMIC_INFORMATION_MODULE, 64)
                .input(COSMIC_PROCESSOR_UEV, 64 * 64)
                .input(WRAP_COSMIC_SMD_CAPACITOR, 32 * 64)
                .input(WRAP_COSMIC_SMD_INDUCTOR, 32 * 64)
                .input(WRAP_EARCPU_CHIP, 16 * 64)
                .input(wireGtQuadruple, Infinity, 16 * 64)
                .fluidInputs(MutatedLivingSolder.getFluid(L * 4 * 16 * 64))
                .fluidInputs(rubber)
                .output(COSMIC_ASSEMBLY_UIV, 48 * 64)
                .EUt(VA[UIV])
                .duration(4 * MINUTE * 64) // Original: 20s, Wrapped: 20s * 16 = 320s
                .buildAndRegister()
        }

        for (rubber in arrayOf(
            PolyphosphonitrileFluoroRubber.getFluid(36 * 64 * 16), // foil (36) * 64 * 16
            PolytetramethyleneGlycolRubber.getFluid(36 * 64 * 16)))
        {
            SINGULARITY_ASSEMBLY_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(WRAP_COSMIC_INFORMATION_MODULE)
                .input(COSMIC_PROCESSOR_UEV, 64)
                .input(WRAP_SPINTRONIC_SMD_CAPACITOR, 8)
                .input(WRAP_SPINTRONIC_SMD_INDUCTOR, 8)
                .input(WRAP_EARCPU_CHIP, 16)
                .input(wireGtQuadruple, Infinity, 16)
                .fluidInputs(MutatedLivingSolder.getFluid(L * 4 * 16))
                .fluidInputs(rubber)
                .output(COSMIC_ASSEMBLY_UIV, 48)
                .EUt(VA[UIV])
                .duration(2 * MINUTE) // Original: 10s, Wrapped: 10s * 16 = 160s
                .buildAndRegister()
        }

        for (rubber in arrayOf(
            PolyphosphonitrileFluoroRubber.getFluid(36 * 64 * 16 * 64), // foil (36) * 64 * 16
            PolytetramethyleneGlycolRubber.getFluid(36 * 64 * 16 * 64)))
        {
            SINGULARITY_ASSEMBLY_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(WRAP_COSMIC_INFORMATION_MODULE, 64)
                .input(COSMIC_PROCESSOR_UEV, 64 * 64)
                .input(WRAP_SPINTRONIC_SMD_CAPACITOR, 8 * 64)
                .input(WRAP_SPINTRONIC_SMD_INDUCTOR, 8 * 64)
                .input(WRAP_EARCPU_CHIP, 16 * 64)
                .input(wireGtQuadruple, Infinity, 16 * 64)
                .fluidInputs(MutatedLivingSolder.getFluid(L * 4 * 16 * 64))
                .fluidInputs(rubber)
                .output(COSMIC_ASSEMBLY_UIV, 48 * 64)
                .EUt(VA[UIV])
                .duration(2 * MINUTE * 64) // Original: 10s, Wrapped: 10s * 16 = 160s
                .buildAndRegister()
        }

        // UXV Cosmic Computer
        SINGULARITY_ASSEMBLY_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(WRAP_COSMIC_INFORMATION_MODULE)
            .input(COSMIC_ASSEMBLY_UIV, 48)
            .input(WRAP_COSMIC_SMD_DIODE, 16)
            .input(WRAP_ESCLG_CHIP, 32)
            .input(WRAP_ADDRAM_CHIP, 64)
            .input(wireGtQuadruple, Infinity, 32)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 16 * 16))
            .fluidInputs(Fullerene.getFluid(L * 12 * 16))
            .fluidInputs(CosmicNeutronium.getFluid(L * 8))
            .fluidInputs(FullerenePolymerMatrix.getFluid(36 * 64 * 16)) // foil (36) * 64 * 16
            .fluidInputs(Livermorium.getFluid(L * 8 * 16))
            .output(COSMIC_COMPUTER_UXV, 32)
            .EUt(VA[UIV])
            .duration(8 * MINUTE) // Original: 40s, Wrapped: 40s * 16 = 640s
            .buildAndRegister()

        SINGULARITY_ASSEMBLY_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(WRAP_COSMIC_INFORMATION_MODULE, 64)
            .input(COSMIC_ASSEMBLY_UIV, 48 * 64)
            .input(WRAP_COSMIC_SMD_DIODE, 16 * 64)
            .input(WRAP_ESCLG_CHIP, 32 * 64)
            .input(WRAP_ADDRAM_CHIP, 64 * 64)
            .input(wireGtQuadruple, Infinity, 32 * 64)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 16 * 16 * 64))
            .fluidInputs(Fullerene.getFluid(L * 12 * 16 * 64))
            .fluidInputs(CosmicNeutronium.getFluid(L * 8 * 64))
            .fluidInputs(FullerenePolymerMatrix.getFluid(36 * 64 * 16 * 64)) // foil (36) * 64 * 16
            .fluidInputs(Livermorium.getFluid(L * 8 * 16 * 64))
            .output(COSMIC_COMPUTER_UXV, 32 * 64)
            .EUt(VA[UIV])
            .duration(8 * MINUTE * 64) // Original: 40s, Wrapped: 40s * 16 = 640s
            .buildAndRegister()

        SINGULARITY_ASSEMBLY_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(WRAP_COSMIC_INFORMATION_MODULE)
            .input(COSMIC_ASSEMBLY_UIV, 48)
            .input(WRAP_SUPRACAUSAL_SMD_DIODE, 4)
            .input(WRAP_ESCLG_CHIP, 32)
            .input(WRAP_ADDRAM_CHIP, 64)
            .input(wireGtQuadruple, Infinity, 32)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 16 * 16))
            .fluidInputs(Fullerene.getFluid(L * 12 * 16))
            .fluidInputs(CosmicNeutronium.getFluid(L * 8))
            .fluidInputs(FullerenePolymerMatrix.getFluid(36 * 64 * 16)) // foil (36) * 64 * 16
            .fluidInputs(Livermorium.getFluid(L * 8 * 16))
            .output(COSMIC_COMPUTER_UXV, 32)
            .EUt(VA[UIV])
            .duration(4 * MINUTE) // Original: 20s, Wrapped: 20s * 16 = 320s
            .buildAndRegister()

        SINGULARITY_ASSEMBLY_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(WRAP_COSMIC_INFORMATION_MODULE, 64)
            .input(COSMIC_ASSEMBLY_UIV, 48 * 64)
            .input(WRAP_SUPRACAUSAL_SMD_DIODE, 4 * 64)
            .input(WRAP_ESCLG_CHIP, 32 * 64)
            .input(WRAP_ADDRAM_CHIP, 64 * 64)
            .input(wireGtQuadruple, Infinity, 32 * 64)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 16 * 16 * 64))
            .fluidInputs(Fullerene.getFluid(L * 12 * 16 * 64))
            .fluidInputs(CosmicNeutronium.getFluid(L * 8 * 64))
            .fluidInputs(FullerenePolymerMatrix.getFluid(36 * 64 * 16 * 64)) // foil (36) * 64 * 16
            .fluidInputs(Livermorium.getFluid(L * 8 * 16 * 64))
            .output(COSMIC_COMPUTER_UXV, 32 * 64)
            .EUt(VA[UIV])
            .duration(4 * MINUTE * 64) // Original: 20s, Wrapped: 20s * 16 = 320s
            .buildAndRegister()

        // OpV Cosmic Mainframe
        SINGULARITY_ASSEMBLY_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(frameGt, Shirabon, 16)
            .input(COSMIC_COMPUTER_UXV, 32)
            .input(WRAP_COSMIC_SMD_DIODE, 64)
            .input(WRAP_COSMIC_SMD_CAPACITOR, 64)
            .input(WRAP_COSMIC_SMD_TRANSISTOR, 64)
            .input(WRAP_COSMIC_SMD_RESISTOR, 64)
            .input(WRAP_COSMIC_SMD_INDUCTOR, 64)
            .input(WRAP_ADDRAM_CHIP, 64)
            .input(wireGtHex, BoronFranciumCarbideSuperconductor, 128)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 80 * 16))
            .fluidInputs(CosmicFabric.getFluid(L * 32 * 16))
            .fluidInputs(QuantumchromodynamicallyConfinedMatter.getFluid(L * 16 * 16))
            .fluidInputs(Vibranium.getFluid(L * 8 * 16))
            .fluidInputs(FullerenePolymerMatrix.getFluid(36 * 64 * 16)) // foil (36) * 64 * 16
            .fluidInputs(Meitnerium.getFluid(L * 16 * 16)) // plate (144) * 16 * 16
            .output(COSMIC_MAINFRAME_OpV, 16)
            .EUt(VA[UXV])
            .duration(20 * MINUTE) // Original: 90s, Wrapped: 90s * 16 = 1440s
            .buildAndRegister()

        SINGULARITY_ASSEMBLY_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(frameGt, Shirabon, 16 * 64)
            .input(COSMIC_COMPUTER_UXV, 32 * 64)
            .input(WRAP_COSMIC_SMD_DIODE, 64 * 64)
            .input(WRAP_COSMIC_SMD_CAPACITOR, 64 * 64)
            .input(WRAP_COSMIC_SMD_TRANSISTOR, 64 * 64)
            .input(WRAP_COSMIC_SMD_RESISTOR, 64 * 64)
            .input(WRAP_COSMIC_SMD_INDUCTOR, 64 * 64)
            .input(WRAP_ADDRAM_CHIP, 64 * 64)
            .input(wireGtHex, BoronFranciumCarbideSuperconductor, 128 * 64)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 80 * 16 * 64))
            .fluidInputs(CosmicFabric.getFluid(L * 32 * 16 * 64))
            .fluidInputs(QuantumchromodynamicallyConfinedMatter.getFluid(L * 16 * 16 * 64))
            .fluidInputs(Vibranium.getFluid(L * 8 * 16 * 64))
            .fluidInputs(FullerenePolymerMatrix.getFluid(36 * 64 * 16 * 64)) // foil (36) * 64 * 16
            .fluidInputs(Meitnerium.getFluid(L * 16 * 16 * 64)) // plate (144) * 16 * 16
            .output(COSMIC_MAINFRAME_OpV, 16 * 64)
            .EUt(VA[UXV])
            .duration(20 * MINUTE * 64) // Original: 90s, Wrapped: 90s * 16 = 1440s
            .buildAndRegister()

        SINGULARITY_ASSEMBLY_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(frameGt, Shirabon, 16)
            .input(COSMIC_COMPUTER_UXV, 32)
            .input(WRAP_SUPRACAUSAL_SMD_DIODE, 16)
            .input(WRAP_SUPRACAUSAL_SMD_CAPACITOR, 16)
            .input(WRAP_SUPRACAUSAL_SMD_TRANSISTOR, 16)
            .input(WRAP_SUPRACAUSAL_SMD_RESISTOR, 16)
            .input(WRAP_SUPRACAUSAL_SMD_INDUCTOR, 16)
            .input(WRAP_ADDRAM_CHIP, 64)
            .input(wireGtHex, BoronFranciumCarbideSuperconductor, 128)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 80 * 16))
            .fluidInputs(CosmicFabric.getFluid(L * 32 * 16))
            .fluidInputs(QuantumchromodynamicallyConfinedMatter.getFluid(L * 16 * 16))
            .fluidInputs(Vibranium.getFluid(L * 8 * 16))
            .fluidInputs(FullerenePolymerMatrix.getFluid(36 * 64 * 16)) // foil (36) * 64 * 16
            .fluidInputs(Meitnerium.getFluid(L * 16 * 16)) // plate (144) * 16 * 16
            .output(COSMIC_MAINFRAME_OpV, 16)
            .EUt(VA[UXV])
            .duration(10 * MINUTE) // Original: 45s, Wrapped: 45s * 16 = 720s
            .buildAndRegister()

        SINGULARITY_ASSEMBLY_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(frameGt, Shirabon, 16 * 64)
            .input(COSMIC_COMPUTER_UXV, 32 * 64)
            .input(WRAP_SUPRACAUSAL_SMD_DIODE, 16 * 64)
            .input(WRAP_SUPRACAUSAL_SMD_CAPACITOR, 16 * 64)
            .input(WRAP_SUPRACAUSAL_SMD_TRANSISTOR, 16 * 64)
            .input(WRAP_SUPRACAUSAL_SMD_RESISTOR, 16 * 64)
            .input(WRAP_SUPRACAUSAL_SMD_INDUCTOR, 16 * 64)
            .input(WRAP_ADDRAM_CHIP, 64 * 64)
            .input(wireGtHex, BoronFranciumCarbideSuperconductor, 128 * 64)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 80 * 16 * 64))
            .fluidInputs(CosmicFabric.getFluid(L * 32 * 16 * 64))
            .fluidInputs(QuantumchromodynamicallyConfinedMatter.getFluid(L * 16 * 16 * 64))
            .fluidInputs(Vibranium.getFluid(L * 8 * 16 * 64))
            .fluidInputs(FullerenePolymerMatrix.getFluid(36 * 64 * 16 * 64)) // foil (36) * 64 * 16
            .fluidInputs(Meitnerium.getFluid(L * 16 * 16 * 64)) // plate (144) * 16 * 16
            .output(COSMIC_MAINFRAME_OpV, 16 * 64)
            .EUt(VA[UXV])
            .duration(10 * MINUTE * 64) // Original: 45s, Wrapped: 45s * 16 = 720s
            .buildAndRegister()

        // endregion

        // region T12: Supracausal

        // endregion

    }

    // @formatter:on

}