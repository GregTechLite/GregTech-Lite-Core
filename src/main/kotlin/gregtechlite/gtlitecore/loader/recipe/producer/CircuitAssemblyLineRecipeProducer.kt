package gregtechlite.gtlitecore.loader.recipe.producer

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
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
import gregtech.api.GTValues.VH
import gregtech.api.GTValues.VHA
import gregtech.api.unification.material.MarkerMaterials.Tier
import gregtech.api.unification.material.Materials.Aluminium
import gregtech.api.unification.material.Materials.AnnealedCopper
import gregtech.api.unification.material.Materials.BlueAlloy
import gregtech.api.unification.material.Materials.Copper
import gregtech.api.unification.material.Materials.Electrum
import gregtech.api.unification.material.Materials.Europium
import gregtech.api.unification.material.Materials.Gold
import gregtech.api.unification.material.Materials.HSSE
import gregtech.api.unification.material.Materials.HSSG
import gregtech.api.unification.material.Materials.Naquadah
import gregtech.api.unification.material.Materials.Neutronium
import gregtech.api.unification.material.Materials.NiobiumTitanium
import gregtech.api.unification.material.Materials.Palladium
import gregtech.api.unification.material.Materials.Platinum
import gregtech.api.unification.material.Materials.Polybenzimidazole
import gregtech.api.unification.material.Materials.RedAlloy
import gregtech.api.unification.material.Materials.SiliconeRubber
import gregtech.api.unification.material.Materials.Silver
import gregtech.api.unification.material.Materials.SolderingAlloy
import gregtech.api.unification.material.Materials.SterileGrowthMedium
import gregtech.api.unification.material.Materials.Tin
import gregtech.api.unification.material.Materials.VanadiumSteel
import gregtech.api.unification.material.Materials.YttriumBariumCuprate
import gregtech.api.unification.ore.OrePrefix.bolt
import gregtech.api.unification.ore.OrePrefix.circuit
import gregtech.api.unification.ore.OrePrefix.foil
import gregtech.api.unification.ore.OrePrefix.frameGt
import gregtech.api.unification.ore.OrePrefix.pipeTinyFluid
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.wireFine
import gregtech.api.unification.ore.OrePrefix.wireGtHex
import gregtech.api.unification.ore.OrePrefix.wireGtQuadruple
import gregtech.common.items.MetaItems.ADVANCED_CIRCUIT_BOARD
import gregtech.common.items.MetaItems.ADVANCED_SMD_CAPACITOR
import gregtech.common.items.MetaItems.ADVANCED_SMD_DIODE
import gregtech.common.items.MetaItems.ADVANCED_SMD_INDUCTOR
import gregtech.common.items.MetaItems.ADVANCED_SMD_RESISTOR
import gregtech.common.items.MetaItems.ADVANCED_SMD_TRANSISTOR
import gregtech.common.items.MetaItems.ADVANCED_SYSTEM_ON_CHIP
import gregtech.common.items.MetaItems.BASIC_CIRCUIT_BOARD
import gregtech.common.items.MetaItems.CENTRAL_PROCESSING_UNIT
import gregtech.common.items.MetaItems.COATED_BOARD
import gregtech.common.items.MetaItems.CRYSTAL_ASSEMBLY_LUV
import gregtech.common.items.MetaItems.CRYSTAL_CENTRAL_PROCESSING_UNIT
import gregtech.common.items.MetaItems.CRYSTAL_COMPUTER_ZPM
import gregtech.common.items.MetaItems.CRYSTAL_MAINFRAME_UV
import gregtech.common.items.MetaItems.CRYSTAL_PROCESSOR_IV
import gregtech.common.items.MetaItems.CRYSTAL_SYSTEM_ON_CHIP
import gregtech.common.items.MetaItems.ELECTRONIC_CIRCUIT_LV
import gregtech.common.items.MetaItems.ELECTRONIC_CIRCUIT_MV
import gregtech.common.items.MetaItems.ELITE_CIRCUIT_BOARD
import gregtech.common.items.MetaItems.ENERGY_LAPOTRONIC_ORB
import gregtech.common.items.MetaItems.ENGRAVED_LAPOTRON_CHIP
import gregtech.common.items.MetaItems.EPOXY_BOARD
import gregtech.common.items.MetaItems.EXTREME_CIRCUIT_BOARD
import gregtech.common.items.MetaItems.FIBER_BOARD
import gregtech.common.items.MetaItems.GOOD_CIRCUIT_BOARD
import gregtech.common.items.MetaItems.HIGHLY_ADVANCED_SOC
import gregtech.common.items.MetaItems.HIGH_POWER_INTEGRATED_CIRCUIT
import gregtech.common.items.MetaItems.INTEGRATED_CIRCUIT_HV
import gregtech.common.items.MetaItems.INTEGRATED_CIRCUIT_LV
import gregtech.common.items.MetaItems.INTEGRATED_CIRCUIT_MV
import gregtech.common.items.MetaItems.INTEGRATED_LOGIC_CIRCUIT
import gregtech.common.items.MetaItems.LOW_POWER_INTEGRATED_CIRCUIT
import gregtech.common.items.MetaItems.MAINFRAME_IV
import gregtech.common.items.MetaItems.MICROPROCESSOR_LV
import gregtech.common.items.MetaItems.MULTILAYER_FIBER_BOARD
import gregtech.common.items.MetaItems.NAND_CHIP_ULV
import gregtech.common.items.MetaItems.NAND_MEMORY_CHIP
import gregtech.common.items.MetaItems.NANO_CENTRAL_PROCESSING_UNIT
import gregtech.common.items.MetaItems.NANO_COMPUTER_IV
import gregtech.common.items.MetaItems.NANO_MAINFRAME_LUV
import gregtech.common.items.MetaItems.NANO_PROCESSOR_ASSEMBLY_EV
import gregtech.common.items.MetaItems.NANO_PROCESSOR_HV
import gregtech.common.items.MetaItems.NEURO_PROCESSOR
import gregtech.common.items.MetaItems.NOR_MEMORY_CHIP
import gregtech.common.items.MetaItems.PHENOLIC_BOARD
import gregtech.common.items.MetaItems.PLASTIC_BOARD
import gregtech.common.items.MetaItems.PLASTIC_CIRCUIT_BOARD
import gregtech.common.items.MetaItems.POWER_INTEGRATED_CIRCUIT
import gregtech.common.items.MetaItems.PROCESSOR_ASSEMBLY_HV
import gregtech.common.items.MetaItems.PROCESSOR_MV
import gregtech.common.items.MetaItems.QUANTUM_ASSEMBLY_IV
import gregtech.common.items.MetaItems.QUANTUM_COMPUTER_LUV
import gregtech.common.items.MetaItems.QUANTUM_MAINFRAME_ZPM
import gregtech.common.items.MetaItems.QUANTUM_PROCESSOR_EV
import gregtech.common.items.MetaItems.QUBIT_CENTRAL_PROCESSING_UNIT
import gregtech.common.items.MetaItems.RANDOM_ACCESS_MEMORY
import gregtech.common.items.MetaItems.SIMPLE_SYSTEM_ON_CHIP
import gregtech.common.items.MetaItems.SMD_CAPACITOR
import gregtech.common.items.MetaItems.SMD_DIODE
import gregtech.common.items.MetaItems.SMD_INDUCTOR
import gregtech.common.items.MetaItems.SMD_RESISTOR
import gregtech.common.items.MetaItems.SMD_TRANSISTOR
import gregtech.common.items.MetaItems.STEM_CELLS
import gregtech.common.items.MetaItems.SYSTEM_ON_CHIP
import gregtech.common.items.MetaItems.TOOL_DATA_MODULE
import gregtech.common.items.MetaItems.TOOL_DATA_ORB
import gregtech.common.items.MetaItems.TOOL_DATA_STICK
import gregtech.common.items.MetaItems.ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT
import gregtech.common.items.MetaItems.ULTRA_LOW_POWER_INTEGRATED_CIRCUIT
import gregtech.common.items.MetaItems.VACUUM_TUBE
import gregtech.common.items.MetaItems.WETWARE_BOARD
import gregtech.common.items.MetaItems.WETWARE_CIRCUIT_BOARD
import gregtech.common.items.MetaItems.WETWARE_MAINFRAME_UHV
import gregtech.common.items.MetaItems.WETWARE_PROCESSOR_ASSEMBLY_ZPM
import gregtech.common.items.MetaItems.WETWARE_PROCESSOR_LUV
import gregtech.common.items.MetaItems.WETWARE_SUPER_COMPUTER_UV
import gregtech.common.items.MetaItems.WORKSTATION_EV
import gregtechlite.gtlitecore.api.MINUTE
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CIRCUIT_ASSEMBLY_LINE_RECIPES
import gregtechlite.gtlitecore.api.recipe.util.circuitInfo
import gregtechlite.gtlitecore.api.recipe.util.createCircuitPatternRecipe
import gregtechlite.gtlitecore.api.recipe.util.wrapItems
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Bedrockium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CarbonNanotube
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Infinity
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MutatedLivingSolder
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Vibranium
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ADVANCED_RAM_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ALL_OPTICAL_CASCADE_NOR_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ATTO_PIC_CHIP
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
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CRYSTAL_INTERFACE_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CRYSTAL_SOC_SOCKET
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.DIAMOND_MODULATOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ENGRAVED_DIAMOND_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ENGRAVED_RUBY_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ENGRAVED_SAPPHIRE_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ESR_COMPUTATION_UNIT
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.EXOTIC_SYSTEM_ON_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.FEMTO_PIC_CHIP
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
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.HOLOGRAPHIC_INFORMATION_IMC
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.INFINITE_CIRCUIT_BOARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MAGNETIC_DOMAIN_WALL_INVERSION_NAND_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.NANO_PIC_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.NONLINEAR_CHEMICAL_OSCILLATOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.OPTICAL_ASSEMBLY_UHV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.OPTICAL_BOARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.OPTICAL_COMPUTER_UEV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.OPTICAL_FIBER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.OPTICAL_LASER_CONTROL_UNIT
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.OPTICAL_MAINFRAME_UIV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.OPTICAL_PROCESSOR_UV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.OPTICAL_SMD_CAPACITOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.OPTICAL_SMD_DIODE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.OPTICAL_SMD_INDUCTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.OPTICAL_SMD_RESISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.OPTICAL_SMD_TRANSISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.OPTOELECTRONIC_SYSTEM_ON_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.PERFECT_CIRCUIT_BOARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.PHASE_CHANGE_RAM_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.PICO_PIC_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.RUBY_MODULATOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SAPPHIRE_MODULATOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SPINTRONIC_ASSEMBLY_UEV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SPINTRONIC_BOARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SPINTRONIC_COMPUTER_UIV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SPINTRONIC_MAINFRAME_UXV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SPINTRONIC_PROCESSOR_UHV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SPINTRONIC_SMD_CAPACITOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SPINTRONIC_SMD_DIODE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SPINTRONIC_SMD_INDUCTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SPINTRONIC_SMD_RESISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SPINTRONIC_SMD_TRANSISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SPIN_TRANSFER_TORQUE_RAM_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SUPRACAUSAL_ASSEMBLY_UXV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SUPRACAUSAL_COMPUTER_OpV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SUPRACAUSAL_MAINFRAME_MAX
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SUPRACAUSAL_PROCESSOR_UIV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SUPRACAUSAL_SMD_CAPACITOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SUPRACAUSAL_SMD_DIODE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SUPRACAUSAL_SMD_INDUCTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SUPRACAUSAL_SMD_RESISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SUPRACAUSAL_SMD_TRANSISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ULTIMATE_CIRCUIT_BOARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ULTRA_HIGHLY_ADVANCED_SOC_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ACNOR_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ADVANCED_CIRCUIT_BOARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ADVANCED_SMD_CAPACITOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ADVANCED_SMD_DIODE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ADVANCED_SMD_INDUCTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ADVANCED_SMD_RESISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ADVANCED_SMD_TRANSISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ADVANCED_SOC_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_APIC_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ARAM_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_BASIC_CIRCUIT_BOARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_COATED_BOARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_COSMIC_INFORMATION_MODULE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_COSMIC_SMD_CAPACITOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_COSMIC_SMD_DIODE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_COSMIC_SMD_INDUCTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_COSMIC_SMD_RESISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_COSMIC_SMD_TRANSISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_CPU_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_CRYSTAL_CPU
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_CRYSTAL_INTERFACE_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_CRYSTAL_SOC
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ELITE_CIRCUIT_BOARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ENGRAVED_DIAMOND_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ENGRAVED_LAPOTRON_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ENGRAVED_RUBY_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ENGRAVED_SAPPHIRE_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_EPOXY_BOARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ESR_COMPUTATION_UNIT
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_EXOTIC_SYSTEM_ON_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_EXTREME_CIRCUIT_BOARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_FIBER_BOARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_FPIC_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_GOOD_CIRCUIT_BOARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_GOOWARE_BOARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_GOOWARE_SMD_CAPACITOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_GOOWARE_SMD_DIODE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_GOOWARE_SMD_INDUCTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_GOOWARE_SMD_RESISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_GOOWARE_SMD_TRANSISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_HIGHLY_ADVANCED_SOC_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_HOLOGRAPHIC_INFORMATION_IMC
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_HPIC_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ILC_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_INFINITE_CIRCUIT_BOARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_LPIC_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_MINAND_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_MULTILAYER_FIBER_BOARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_NAND_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_NANO_CPU_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_NEURO_PROCESSOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_NONLINEAR_CHEMICAL_OSCILLATOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_NOR_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_NPIC_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_OPTICAL_BOARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_OPTICAL_LASER_CONTROL_UNIT
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_OPTICAL_SMD_CAPACITOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_OPTICAL_SMD_DIODE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_OPTICAL_SMD_INDUCTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_OPTICAL_SMD_RESISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_OPTICAL_SMD_TRANSISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_OPTOELECTRONIC_SYSTEM_ON_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_PERFECT_CIRCUIT_BOARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_PHENOLIC_BOARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_PIC_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_PLASTIC_BOARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_PLASTIC_CIRCUIT_BOARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_PPIC_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_PRAM_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_QUBIT_CPU_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_RAM_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_SIMPLE_SOC_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_SMD_CAPACITOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_SMD_DIODE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_SMD_INDUCTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_SMD_RESISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_SMD_TRANSISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_SOC_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_SPINTRONIC_BOARD
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
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_UHPIC_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ULPIC_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ULTIMATE_CIRCUIT_BOARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_WETWARE_BOARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_WETWARE_CIRCUIT_BOARD
import kotlin.collections.set

internal object CircuitAssemblyLineRecipeProducer
{

    // @formatter:off

    fun produce()
    {
        createWrapItemMaps()
        createCircuitPatternRecipes()

        // region T1: Electronic

        // LV Electronic Circuit
        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_BASIC_CIRCUIT_BOARD)
            .input(WRAP_SMD_RESISTOR, 2)
            .input(VACUUM_TUBE, 32)
            .input(wireGtHex, RedAlloy, 2)
            .fluidInputs(SolderingAlloy.getFluid(L / 2))
            .output(ELECTRONIC_CIRCUIT_LV, 64)
            .EUt(VH[LV])
            .duration(1 * MINUTE) // Original: 10s, Wrapped: 10s * 16 = 160s
            .circuitInfo(ELECTRONIC_CIRCUIT_LV)
            .buildAndRegister()

        // MV Good Electronic Circuit
        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_GOOD_CIRCUIT_BOARD)
            .input(ELECTRONIC_CIRCUIT_LV, 32)
            .input(WRAP_SMD_DIODE, 2)
            .input(wireGtHex, Copper, 2)
            .fluidInputs(SolderingAlloy.getFluid(L / 2))
            .output(ELECTRONIC_CIRCUIT_MV, 32)
            .EUt(VH[LV])
            .duration(1 * MINUTE + 20 * SECOND) // Original: 15s, Wrapped: 15s * 16 = 240s
            .circuitInfo(ELECTRONIC_CIRCUIT_MV)
            .buildAndRegister()

        // endregion

        // region T2: Integrated

        // LV Integrated Circuit
        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_BASIC_CIRCUIT_BOARD)
            .input(WRAP_ILC_CHIP)
            .input(WRAP_SMD_RESISTOR, 2)
            .input(WRAP_SMD_RESISTOR, 2)
            .input(wireGtQuadruple, Copper, 2)
            .input(bolt, Tin, 32)
            .fluidInputs(SolderingAlloy.getFluid(L / 2))
            .output(INTEGRATED_CIRCUIT_LV, 64)
            .EUt(VH[LV])
            .duration(2 * MINUTE) // Original: 10s, Wrapped: 10s * 16 = 160s
            .circuitInfo(INTEGRATED_CIRCUIT_LV)
            .buildAndRegister()

        // MV Good Integrated Circuit
        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_GOOD_CIRCUIT_BOARD)
            .input(INTEGRATED_CIRCUIT_LV, 64)
            .input(WRAP_SMD_RESISTOR, 2)
            .input(WRAP_SMD_DIODE, 2)
            .input(wireGtQuadruple, Gold, 4)
            .input(bolt, Silver, 64)
            .fluidInputs(SolderingAlloy.getFluid(L / 2))
            .output(INTEGRATED_CIRCUIT_MV, 48)
            .EUt(24) // LV
            .duration(4 * MINUTE) // Original: 20s, Wrapped: 20s * 16 = 320s
            .circuitInfo(INTEGRATED_CIRCUIT_MV)
            .buildAndRegister()

        // HV Advanced Integrated Circuit
        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(INTEGRATED_CIRCUIT_MV, 48)
            .input(WRAP_ILC_CHIP, 2)
            .input(WRAP_RAM_CHIP, 2)
            .input(WRAP_SMD_TRANSISTOR, 4)
            .input(wireGtQuadruple, Electrum, 8)
            .input(bolt, AnnealedCopper, 64)
            .fluidInputs(SolderingAlloy.getFluid(L))
            .output(INTEGRATED_CIRCUIT_HV, 32)
            .EUt(VA[LV])
            .duration(8 * MINUTE) // Original: 40s, Wrapped: 40s * 16 = 640s
            .circuitInfo(INTEGRATED_CIRCUIT_HV)
            .buildAndRegister()

        // endregion

        // region T3: Processor

        // ULV NAND Chip
        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_GOOD_CIRCUIT_BOARD)
            .input(WRAP_SIMPLE_SOC_CHIP)
            .input(bolt, RedAlloy, 32)
            .input(wireGtQuadruple, Tin, 2)
            .fluidInputs(SolderingAlloy.getFluid(L / 2))
            .output(NAND_CHIP_ULV, 64)
            .output(NAND_CHIP_ULV, 64)
            .EUt(VA[MV])
            .duration(3 * MINUTE) // Original: 15s, Wrapped: 15s * 16 = 240s
            .circuitInfo(NAND_CHIP_ULV)
            .buildAndRegister()

        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_PLASTIC_CIRCUIT_BOARD)
            .input(WRAP_SIMPLE_SOC_CHIP)
            .input(bolt, RedAlloy, 32)
            .input(wireGtQuadruple, Tin, 2)
            .fluidInputs(SolderingAlloy.getFluid(L / 2))
            .output(NAND_CHIP_ULV, 64)
            .output(NAND_CHIP_ULV, 64)
            .output(NAND_CHIP_ULV, 64)
            .output(NAND_CHIP_ULV, 64)
            .EUt(VA[MV])
            .duration(3 * MINUTE) // Original: 15s, Wrapped: 15s * 16 = 240s
            .circuitInfo(NAND_CHIP_ULV)
            .buildAndRegister()

        // LV Microprocessor
        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_PLASTIC_CIRCUIT_BOARD)
            .input(WRAP_CPU_CHIP)
            .input(WRAP_SMD_RESISTOR, 2)
            .input(WRAP_SMD_CAPACITOR, 2)
            .input(WRAP_SMD_TRANSISTOR, 2)
            .input(wireGtQuadruple, Copper)
            .fluidInputs(SolderingAlloy.getFluid(L / 2))
            .output(MICROPROCESSOR_LV, 64)
            .EUt(VHA[MV])
            .duration(2 * MINUTE) // Original: 10s, Wrapped: 10s * 16 = 160s
            .circuitInfo(MICROPROCESSOR_LV)
            .buildAndRegister()

        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_PLASTIC_CIRCUIT_BOARD)
            .input(WRAP_SOC_CHIP)
            .input(wireGtQuadruple, Copper, 2)
            .input(bolt, Tin, 32)
            .fluidInputs(SolderingAlloy.getFluid(L / 2))
            .output(MICROPROCESSOR_LV, 64)
            .output(MICROPROCESSOR_LV, 64)
            .EUt(600) // EV
            .duration(30 * SECOND) // Original: 2.5s, Wrapped: 2.5s * 16 = 40s
            .circuitInfo(MICROPROCESSOR_LV)
            .buildAndRegister()

        // MV Processor
        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_PLASTIC_CIRCUIT_BOARD)
            .input(WRAP_CPU_CHIP)
            .input(WRAP_SMD_RESISTOR, 4)
            .input(WRAP_SMD_CAPACITOR, 4)
            .input(WRAP_SMD_TRANSISTOR, 4)
            .input(wireGtQuadruple, RedAlloy, 4)
            .fluidInputs(SolderingAlloy.getFluid(L / 2))
            .output(PROCESSOR_MV, 64)
            .EUt(VHA[MV])
            .duration(2 * MINUTE) // Original: 10s, Wrapped: 10s * 16 = 160s
            .circuitInfo(PROCESSOR_MV)
            .buildAndRegister()

        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_PLASTIC_CIRCUIT_BOARD)
            .input(WRAP_SOC_CHIP)
            .input(wireGtQuadruple, RedAlloy, 4)
            .input(bolt, AnnealedCopper, 64)
            .fluidInputs(SolderingAlloy.getFluid(L / 2))
            .output(PROCESSOR_MV, 64)
            .output(PROCESSOR_MV, 64)
            .EUt(2400) // IV
            .duration(30 * SECOND) // Original: 2.5s, Wrapped: 2.5s * 16 = 40s
            .circuitInfo(PROCESSOR_MV)
            .buildAndRegister()

        // HV Processor Assembly
        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_PLASTIC_CIRCUIT_BOARD)
            .input(PROCESSOR_MV, 64)
            .input(WRAP_SMD_INDUCTOR, 4)
            .input(WRAP_SMD_CAPACITOR, 8)
            .input(WRAP_RAM_CHIP, 4)
            .input(wireGtQuadruple, RedAlloy, 8)
            .fluidInputs(SolderingAlloy.getFluid(L))
            .output(PROCESSOR_ASSEMBLY_HV, 48)
            .EUt(90) // MV
            .duration(4 * MINUTE) // Original: 20s, Wrapped: 20s * 16 = 320s
            .circuitInfo(PROCESSOR_ASSEMBLY_HV)
            .buildAndRegister()

        // EV Workstation
        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_PLASTIC_CIRCUIT_BOARD)
            .input(PROCESSOR_ASSEMBLY_HV, 48)
            .input(WRAP_SMD_DIODE, 4)
            .input(WRAP_RAM_CHIP, 4)
            .input(wireGtQuadruple, Electrum, 16)
            .input(bolt, BlueAlloy, 64)
            .fluidInputs(SolderingAlloy.getFluid(L))
            .output(WORKSTATION_EV, 32)
            .EUt(VA[MV])
            .duration(5 * MINUTE) // Original: 20s, Wrapped: 20ss * 16 = 320s
            .circuitInfo(WORKSTATION_EV)
            .buildAndRegister()

        // IV Mainframe
        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, Aluminium, 32)
            .input(WORKSTATION_EV, 32)
            .input(WRAP_SMD_INDUCTOR, 8)
            .input(WRAP_SMD_CAPACITOR, 16)
            .input(WRAP_RAM_CHIP, 16)
            .input(wireGtHex, AnnealedCopper, 16)
            .fluidInputs(SolderingAlloy.getFluid(L * 2))
            .output(MAINFRAME_IV, 16)
            .EUt(VA[HV])
            .duration(8 * MINUTE) // Original: 40s, Wrapped: 40s * 16 = 640s
            .circuitInfo(MAINFRAME_IV)
            .buildAndRegister()

        // endregion

        // region T4: Nano

        // HV Nano Processor
        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_ADVANCED_CIRCUIT_BOARD)
            .input(WRAP_NANO_CPU_CHIP)
            .input(WRAP_SMD_RESISTOR, 8)
            .input(WRAP_SMD_CAPACITOR, 8)
            .input(WRAP_SMD_TRANSISTOR, 8)
            .input(wireGtQuadruple, Electrum, 8)
            .fluidInputs(SolderingAlloy.getFluid(L / 2))
            .output(NANO_PROCESSOR_HV, 64)
            .EUt(600) // EV
            .duration(2 * MINUTE) // Original: 10s, Wrapped: 10s * 16 = 160s
            .circuitInfo(NANO_PROCESSOR_HV)
            .buildAndRegister()

        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_ADVANCED_CIRCUIT_BOARD)
            .input(WRAP_NANO_CPU_CHIP)
            .input(WRAP_ADVANCED_SMD_RESISTOR, 2)
            .input(WRAP_ADVANCED_SMD_CAPACITOR, 2)
            .input(WRAP_ADVANCED_SMD_TRANSISTOR, 2)
            .input(wireGtQuadruple, Electrum, 8)
            .fluidInputs(SolderingAlloy.getFluid(L / 2))
            .output(NANO_PROCESSOR_HV, 64)
            .EUt(600) // EV
            .duration(1 * MINUTE) // Original: 5s, Wrapped: 5s * 16 = 80s
            .circuitInfo(NANO_PROCESSOR_HV)
            .buildAndRegister()

        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_ADVANCED_CIRCUIT_BOARD)
            .input(WRAP_ADVANCED_SOC_CHIP)
            .input(wireGtQuadruple, Electrum, 4)
            .input(bolt, Platinum, 64)
            .fluidInputs(SolderingAlloy.getFluid(L / 2))
            .output(NANO_PROCESSOR_HV, 64)
            .output(NANO_PROCESSOR_HV, 64)
            .EUt(9600) // LuV
            .duration(30 * SECOND) // Original: 2.5s, Wrapped: 2.5s * 16 = 40s
            .circuitInfo(NANO_PROCESSOR_HV)
            .buildAndRegister()

        // EV Nano Processor Assembly
        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_ADVANCED_CIRCUIT_BOARD)
            .input(NANO_PROCESSOR_HV, 64)
            .input(WRAP_SMD_INDUCTOR, 4)
            .input(WRAP_SMD_CAPACITOR, 8)
            .input(WRAP_RAM_CHIP, 8)
            .input(wireGtQuadruple, Electrum, 16)
            .fluidInputs(SolderingAlloy.getFluid(L))
            .output(NANO_PROCESSOR_ASSEMBLY_EV, 48)
            .EUt(600) // EV
            .duration(4 * MINUTE) // Original: 20s, Wrapped: 20s * 16 = 320s
            .circuitInfo(NANO_PROCESSOR_ASSEMBLY_EV)
            .buildAndRegister()

        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_ADVANCED_CIRCUIT_BOARD)
            .input(NANO_PROCESSOR_HV, 64)
            .input(WRAP_ADVANCED_SMD_INDUCTOR)
            .input(WRAP_ADVANCED_SMD_CAPACITOR, 2)
            .input(WRAP_RAM_CHIP, 8)
            .input(wireGtQuadruple, Electrum, 16)
            .fluidInputs(SolderingAlloy.getFluid(L))
            .output(NANO_PROCESSOR_ASSEMBLY_EV, 48)
            .EUt(600) // EV
            .duration(2 * MINUTE) // Original: 10s, Wrapped: 10s * 16 = 160s
            .circuitInfo(NANO_PROCESSOR_ASSEMBLY_EV)
            .buildAndRegister()

        // IV Nano Computer
        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_ADVANCED_CIRCUIT_BOARD)
            .input(NANO_PROCESSOR_ASSEMBLY_EV, 48)
            .input(WRAP_SMD_DIODE, 8)
            .input(WRAP_NOR_CHIP, 4)
            .input(WRAP_RAM_CHIP, 16)
            .input(wireGtQuadruple, Electrum, 16)
            .fluidInputs(SolderingAlloy.getFluid(L))
            .output(NANO_COMPUTER_IV, 32)
            .EUt(600) // EV
            .duration(5 * MINUTE) // Original: 20s, Wrapped: 20s * 16 = 320s
            .circuitInfo(NANO_COMPUTER_IV)
            .buildAndRegister()

        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_ADVANCED_CIRCUIT_BOARD)
            .input(NANO_PROCESSOR_ASSEMBLY_EV, 48)
            .input(WRAP_ADVANCED_SMD_DIODE, 2)
            .input(WRAP_NOR_CHIP, 4)
            .input(WRAP_RAM_CHIP, 16)
            .input(wireGtQuadruple, Electrum, 16)
            .fluidInputs(SolderingAlloy.getFluid(L))
            .output(NANO_COMPUTER_IV, 32)
            .EUt(600) // EV
            .duration(2 * MINUTE + 30 * SECOND) // Original: 10s, Wrapped: 10s * 16 = 160s
            .circuitInfo(NANO_COMPUTER_IV)
            .buildAndRegister()

        // LuV Nano Mainframe
        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, VanadiumSteel, 32)
            .input(NANO_COMPUTER_IV, 32)
            .input(WRAP_SMD_INDUCTOR, 16)
            .input(WRAP_SMD_CAPACITOR, 32)
            .input(WRAP_RAM_CHIP, 16)
            .input(wireGtHex, AnnealedCopper, 32)
            .fluidInputs(SolderingAlloy.getFluid(L * 2))
            .output(NANO_MAINFRAME_LUV, 16)
            .EUt(VA[EV])
            .duration(8 * MINUTE) // Original: 40s, Wrapped: 40s * 16 = 640s
            .circuitInfo(NANO_MAINFRAME_LUV)
            .buildAndRegister()

        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, VanadiumSteel, 32)
            .input(NANO_COMPUTER_IV, 32)
            .input(WRAP_ADVANCED_SMD_INDUCTOR, 4)
            .input(WRAP_ADVANCED_SMD_CAPACITOR, 8)
            .input(WRAP_RAM_CHIP, 16)
            .input(wireGtHex, AnnealedCopper, 32)
            .fluidInputs(SolderingAlloy.getFluid(L * 2))
            .output(NANO_MAINFRAME_LUV, 16)
            .EUt(VA[EV])
            .duration(4 * MINUTE) // Original: 20s, Wrapped: 20s * 16 = 320s
            .circuitInfo(NANO_MAINFRAME_LUV)
            .buildAndRegister()

        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, VanadiumSteel, 32)
            .input(NANO_COMPUTER_IV, 32)
            .input(WRAP_GOOWARE_SMD_INDUCTOR)
            .input(WRAP_GOOWARE_SMD_CAPACITOR, 2)
            .input(WRAP_RAM_CHIP, 16)
            .input(wireGtHex, AnnealedCopper, 32)
            .fluidInputs(SolderingAlloy.getFluid(L * 2))
            .output(NANO_MAINFRAME_LUV, 16)
            .EUt(VA[EV])
            .duration(2 * MINUTE) // Original: 10s, Wrapped: 10s * 16 = 160s
            .circuitInfo(NANO_MAINFRAME_LUV)
            .buildAndRegister()

        // endregion

        // region T5: Quantum

        // EV Quantum Processor
        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_EXTREME_CIRCUIT_BOARD)
            .input(WRAP_QUBIT_CPU_CHIP)
            .input(WRAP_NANO_CPU_CHIP)
            .input(WRAP_SMD_CAPACITOR, 12)
            .input(WRAP_SMD_TRANSISTOR, 12)
            .input(wireGtQuadruple, Platinum, 12)
            .fluidInputs(SolderingAlloy.getFluid(L / 2))
            .output(QUANTUM_PROCESSOR_EV, 64)
            .EUt(2400) // IV
            .duration(2 * MINUTE) // Original: 10s, Wrapped: 10s * 16 = 160s
            .circuitInfo(QUANTUM_PROCESSOR_EV)
            .buildAndRegister()

        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_EXTREME_CIRCUIT_BOARD)
            .input(WRAP_QUBIT_CPU_CHIP)
            .input(WRAP_NANO_CPU_CHIP)
            .input(WRAP_ADVANCED_SMD_CAPACITOR, 3)
            .input(WRAP_ADVANCED_SMD_TRANSISTOR, 3)
            .input(wireGtQuadruple, Platinum, 12)
            .fluidInputs(SolderingAlloy.getFluid(L / 2))
            .output(QUANTUM_PROCESSOR_EV, 64)
            .EUt(2400) // IV
            .duration(1 * MINUTE) // Original: 5s, Wrapped: 5s * 16 = 80s
            .circuitInfo(QUANTUM_PROCESSOR_EV)
            .buildAndRegister()

        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_EXTREME_CIRCUIT_BOARD)
            .input(WRAP_ADVANCED_SOC_CHIP)
            .input(wireGtQuadruple, Platinum, 12)
            .input(bolt, NiobiumTitanium, 8)
            .fluidInputs(SolderingAlloy.getFluid(L / 2))
            .output(QUANTUM_PROCESSOR_EV, 64)
            .output(QUANTUM_PROCESSOR_EV, 64)
            .EUt(38400) // ZPM
            .duration(30 * SECOND) // Original: 2.5s, Wrapped: 2.5s * 16 = 40s
            .circuitInfo(QUANTUM_PROCESSOR_EV)
            .buildAndRegister()

        // IV Quantum Processor Assembly
        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_EXTREME_CIRCUIT_BOARD)
            .input(QUANTUM_PROCESSOR_EV, 64)
            .input(WRAP_SMD_INDUCTOR, 8)
            .input(WRAP_SMD_CAPACITOR, 16)
            .input(WRAP_RAM_CHIP, 4)
            .input(wireGtQuadruple, Platinum, 16)
            .fluidInputs(SolderingAlloy.getFluid(L))
            .output(QUANTUM_ASSEMBLY_IV, 48)
            .EUt(2400) // IV
            .duration(4 * MINUTE) // Original: 20s, Wrapped: 20s * 16 = 320s
            .circuitInfo(QUANTUM_ASSEMBLY_IV)
            .buildAndRegister()

        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_EXTREME_CIRCUIT_BOARD)
            .input(QUANTUM_PROCESSOR_EV, 64)
            .input(WRAP_ADVANCED_SMD_INDUCTOR, 2)
            .input(WRAP_ADVANCED_SMD_CAPACITOR, 4)
            .input(WRAP_RAM_CHIP, 4)
            .input(wireGtQuadruple, Platinum, 16)
            .fluidInputs(SolderingAlloy.getFluid(L))
            .output(QUANTUM_ASSEMBLY_IV, 48)
            .EUt(2400) // IV
            .duration(2 * MINUTE) // Original: 10s, Wrapped: 10s * 16 = 160s
            .circuitInfo(QUANTUM_ASSEMBLY_IV)
            .buildAndRegister()

        // LuV Quantum Supercomputer
        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_EXTREME_CIRCUIT_BOARD)
            .input(QUANTUM_ASSEMBLY_IV, 48)
            .input(WRAP_SMD_DIODE, 8)
            .input(WRAP_NOR_CHIP, 4)
            .input(WRAP_RAM_CHIP, 16)
            .input(wireGtQuadruple, Platinum, 32)
            .fluidInputs(SolderingAlloy.getFluid(L))
            .output(QUANTUM_COMPUTER_LUV, 32)
            .EUt(2400) // IV
            .duration(5 * MINUTE) // Original: 20s, Wrapped: 20s * 16 = 320s
            .circuitInfo(QUANTUM_COMPUTER_LUV)
            .buildAndRegister()

        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_EXTREME_CIRCUIT_BOARD)
            .input(QUANTUM_ASSEMBLY_IV, 48)
            .input(WRAP_ADVANCED_SMD_DIODE, 2)
            .input(WRAP_NOR_CHIP, 4)
            .input(WRAP_RAM_CHIP, 16)
            .input(wireGtQuadruple, Platinum, 32)
            .fluidInputs(SolderingAlloy.getFluid(L))
            .output(QUANTUM_COMPUTER_LUV, 32)
            .EUt(2400) // IV
            .duration(2 * MINUTE + 30 * SECOND) // Original: 10s, Wrapped: 10s * 16 = 160s
            .circuitInfo(QUANTUM_COMPUTER_LUV)
            .buildAndRegister()

        // ZPM Quantum Mainframe
        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, HSSG, 32)
            .input(QUANTUM_COMPUTER_LUV, 32)
            .input(WRAP_SMD_INDUCTOR, 24)
            .input(WRAP_SMD_CAPACITOR, 48)
            .input(WRAP_RAM_CHIP, 24)
            .input(wireGtHex, AnnealedCopper, 48)
            .fluidInputs(SolderingAlloy.getFluid(L * 2))
            .output(QUANTUM_MAINFRAME_ZPM, 16)
            .EUt(VA[IV])
            .duration(8 * MINUTE) // Original: 40s, Wrapped: 40s * 16 = 640s
            .circuitInfo(QUANTUM_MAINFRAME_ZPM)
            .buildAndRegister()

        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, HSSG, 32)
            .input(QUANTUM_COMPUTER_LUV, 32)
            .input(WRAP_ADVANCED_SMD_INDUCTOR, 6)
            .input(WRAP_ADVANCED_SMD_CAPACITOR, 12)
            .input(WRAP_RAM_CHIP, 24)
            .input(wireGtHex, AnnealedCopper, 48)
            .fluidInputs(SolderingAlloy.getFluid(L * 2))
            .output(QUANTUM_MAINFRAME_ZPM, 16)
            .EUt(VA[IV])
            .duration(4 * MINUTE) // Original: 20s, Wrapped: 20s * 16 = 320s
            .circuitInfo(QUANTUM_MAINFRAME_ZPM)
            .buildAndRegister()

        // endregion

        // region T6: Crystal

        // IV Crystal Processor
        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_ELITE_CIRCUIT_BOARD)
            .input(WRAP_CRYSTAL_CPU)
            .input(WRAP_NANO_CPU_CHIP, 2)
            .input(WRAP_ADVANCED_SMD_CAPACITOR, 4)
            .input(WRAP_ADVANCED_SMD_TRANSISTOR, 4)
            .input(wireGtQuadruple, NiobiumTitanium, 8)
            .fluidInputs(SolderingAlloy.getFluid(L / 2))
            .output(CRYSTAL_PROCESSOR_IV, 64)
            .EUt(9600) // LuV
            .duration(2 * MINUTE) // Original: 10s, Wrapped: 10s * 16 = 160s
            .circuitInfo(CRYSTAL_PROCESSOR_IV)
            .buildAndRegister()

        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_ELITE_CIRCUIT_BOARD)
            .input(WRAP_CRYSTAL_CPU)
            .input(WRAP_NANO_CPU_CHIP, 2)
            .input(WRAP_GOOWARE_SMD_CAPACITOR)
            .input(WRAP_GOOWARE_SMD_TRANSISTOR)
            .input(wireGtQuadruple, NiobiumTitanium, 8)
            .fluidInputs(SolderingAlloy.getFluid(L / 2))
            .output(CRYSTAL_PROCESSOR_IV, 64)
            .EUt(9600) // LuV
            .duration(1 * MINUTE) // Original: 5s, Wrapped: 5s * 16 = 80s
            .circuitInfo(CRYSTAL_PROCESSOR_IV)
            .buildAndRegister()

        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_ELITE_CIRCUIT_BOARD)
            .input(WRAP_CRYSTAL_SOC)
            .input(wireGtQuadruple, NiobiumTitanium, 8)
            .input(bolt, YttriumBariumCuprate, 64)
            .fluidInputs(SolderingAlloy.getFluid(L / 2))
            .output(CRYSTAL_PROCESSOR_IV, 64)
            .output(CRYSTAL_PROCESSOR_IV, 64)
            .EUt(86000) // ZPM
            .duration(30 * SECOND) // Original: 2.5s, Wrapped: 2.5s * 16 = 40s
            .circuitInfo(CRYSTAL_PROCESSOR_IV)
            .buildAndRegister()

        // LuV Crystal Processor Assembly
        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_ELITE_CIRCUIT_BOARD)
            .input(CRYSTAL_PROCESSOR_IV, 64)
            .input(WRAP_ADVANCED_SMD_INDUCTOR, 4)
            .input(WRAP_ADVANCED_SMD_CAPACITOR, 8)
            .input(WRAP_RAM_CHIP, 24)
            .input(wireGtQuadruple, NiobiumTitanium, 16)
            .fluidInputs(SolderingAlloy.getFluid(L))
            .output(CRYSTAL_ASSEMBLY_LUV, 48)
            .EUt(9600) // LuV
            .duration(4 * MINUTE) // Original: 20s, Wrapped: 20s * 16 = 320s
            .circuitInfo(CRYSTAL_ASSEMBLY_LUV)
            .buildAndRegister()

        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_ELITE_CIRCUIT_BOARD)
            .input(CRYSTAL_PROCESSOR_IV, 64)
            .input(WRAP_GOOWARE_SMD_INDUCTOR)
            .input(WRAP_GOOWARE_SMD_CAPACITOR, 2)
            .input(WRAP_RAM_CHIP, 24)
            .input(wireGtQuadruple, NiobiumTitanium, 16)
            .fluidInputs(SolderingAlloy.getFluid(L))
            .output(CRYSTAL_ASSEMBLY_LUV, 48)
            .EUt(9600) // LuV
            .duration(2 * MINUTE) // Original: 10s, Wrapped: 10s * 16 = 160s
            .circuitInfo(CRYSTAL_ASSEMBLY_LUV)
            .buildAndRegister()

        // ZPM Crystal Supercomputer
        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_ELITE_CIRCUIT_BOARD)
            .input(CRYSTAL_ASSEMBLY_LUV, 48)
            .input(WRAP_RAM_CHIP, 4)
            .input(WRAP_NOR_CHIP, 32)
            .input(WRAP_NAND_CHIP, 64)
            .input(wireGtQuadruple, NiobiumTitanium, 32)
            .fluidInputs(SolderingAlloy.getFluid(L))
            .output(CRYSTAL_COMPUTER_ZPM, 32)
            .EUt(9600) // LuV
            .duration(5 * MINUTE) // Original: 20s, Wrapped: 20s * 16 = 320s
            .circuitInfo(CRYSTAL_COMPUTER_ZPM)
            .buildAndRegister()

        // UV Crystal Mainframe (AssLine)

        // endregion

        // region T7: Wetware

        // LuV Wetware Processor
        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_NEURO_PROCESSOR)
            .input(WRAP_CRYSTAL_CPU)
            .input(WRAP_QUBIT_CPU_CHIP)
            .input(WRAP_ADVANCED_SMD_CAPACITOR, 8)
            .input(WRAP_ADVANCED_SMD_TRANSISTOR, 8)
            .input(wireGtQuadruple, YttriumBariumCuprate, 8)
            .fluidInputs(SolderingAlloy.getFluid(L / 2))
            .output(WETWARE_PROCESSOR_LUV, 64)
            .EUt(38400) // ZPM
            .duration(2 * MINUTE) // Original: 10s, Wrapped: 10s * 16 = 160s
            .circuitInfo(WETWARE_PROCESSOR_LUV)
            .buildAndRegister()

        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_NEURO_PROCESSOR)
            .input(WRAP_CRYSTAL_CPU)
            .input(WRAP_QUBIT_CPU_CHIP)
            .input(WRAP_GOOWARE_SMD_CAPACITOR, 2)
            .input(WRAP_GOOWARE_SMD_TRANSISTOR, 2)
            .input(wireGtQuadruple, YttriumBariumCuprate, 8)
            .fluidInputs(SolderingAlloy.getFluid(L / 2))
            .output(WETWARE_PROCESSOR_LUV, 64)
            .EUt(38400) // ZPM
            .duration(1 * MINUTE) // Original: 5s, Wrapped: 5s * 16 = 80s
            .circuitInfo(WETWARE_PROCESSOR_LUV)
            .buildAndRegister()

        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_NEURO_PROCESSOR)
            .input(WRAP_HIGHLY_ADVANCED_SOC_CHIP)
            .input(wireGtQuadruple, YttriumBariumCuprate, 8)
            .input(bolt, Naquadah, 8)
            .fluidInputs(SolderingAlloy.getFluid(L / 2))
            .output(WETWARE_PROCESSOR_LUV, 64)
            .output(WETWARE_PROCESSOR_LUV, 64)
            .EUt(150_000) // UV
            .duration(30 * SECOND) // Original: 2.5s, Wrapped: 2.5s * 16 = 40s
            .circuitInfo(WETWARE_PROCESSOR_LUV)
            .buildAndRegister()

        // ZPM Wetware Processor Assembly
        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_WETWARE_CIRCUIT_BOARD)
            .input(WETWARE_PROCESSOR_LUV, 64)
            .input(WRAP_ADVANCED_SMD_INDUCTOR, 8)
            .input(WRAP_ADVANCED_SMD_CAPACITOR, 16)
            .input(WRAP_ARAM_CHIP, 24)
            .input(wireGtQuadruple, YttriumBariumCuprate, 16)
            .fluidInputs(SolderingAlloy.getFluid(L))
            .output(WETWARE_PROCESSOR_ASSEMBLY_ZPM, 48)
            .EUt(38400) // ZPM
            .duration(4 * MINUTE) // Original: 20s, Wrapped: 20s * 16 = 320s
            .circuitInfo(WETWARE_PROCESSOR_ASSEMBLY_ZPM)
            .buildAndRegister()

        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_WETWARE_CIRCUIT_BOARD)
            .input(WETWARE_PROCESSOR_LUV, 64)
            .input(WRAP_GOOWARE_SMD_INDUCTOR, 2)
            .input(WRAP_GOOWARE_SMD_CAPACITOR, 4)
            .input(WRAP_ARAM_CHIP, 24)
            .input(wireGtQuadruple, YttriumBariumCuprate, 16)
            .fluidInputs(SolderingAlloy.getFluid(L))
            .output(WETWARE_PROCESSOR_ASSEMBLY_ZPM, 48)
            .EUt(38400) // ZPM
            .duration(2 * MINUTE) // Original: 10s, Wrapped: 10s * 16 = 160s
            .circuitInfo(WETWARE_PROCESSOR_ASSEMBLY_ZPM)
            .buildAndRegister()

        // UV Wetware Supercomputer
        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_WETWARE_CIRCUIT_BOARD)
            .input(WETWARE_PROCESSOR_ASSEMBLY_ZPM, 48)
            .input(WRAP_ARAM_CHIP, 8)
            .input(WRAP_NOR_CHIP, 64)
            .input(WRAP_NAND_CHIP, 64)
            .input(wireGtQuadruple, YttriumBariumCuprate, 32)
            .fluidInputs(SolderingAlloy.getFluid(L))
            .output(WETWARE_SUPER_COMPUTER_UV, 32)
            .EUt(38400) // ZPM
            .duration(5 * MINUTE) // Original: 20s, Wrapped: 20s * 16 = 320s
            .circuitInfo(WETWARE_SUPER_COMPUTER_UV)
            .buildAndRegister()

        // UHV Wetware Mainframe (AssLine)

        // endregion

        // region T8: Gooware

        // ZPM Gooware Processor
        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_ULTIMATE_CIRCUIT_BOARD)
            .input(WRAP_NONLINEAR_CHEMICAL_OSCILLATOR)
            .input(WRAP_CRYSTAL_CPU)
            .input(WRAP_GOOWARE_SMD_CAPACITOR, 16)
            .input(WRAP_GOOWARE_SMD_TRANSISTOR, 16)
            .input(wireGtQuadruple, Europium, 8)
            .fluidInputs(SolderingAlloy.getFluid(L / 2))
            .output(GOOWARE_PROCESSOR_ZPM, 64)
            .EUt(VHA[UV])
            .duration(2 * MINUTE) // Original: 10s, Wrapped: 10s * 16 = 160s
            .circuitInfo(GOOWARE_PROCESSOR_ZPM)
            .buildAndRegister()

        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_ULTIMATE_CIRCUIT_BOARD)
            .input(WRAP_NONLINEAR_CHEMICAL_OSCILLATOR)
            .input(WRAP_CRYSTAL_CPU)
            .input(WRAP_OPTICAL_SMD_CAPACITOR, 4)
            .input(WRAP_OPTICAL_SMD_TRANSISTOR, 4)
            .input(wireGtQuadruple, Europium, 8)
            .fluidInputs(SolderingAlloy.getFluid(L / 2))
            .output(GOOWARE_PROCESSOR_ZPM, 64)
            .EUt(VHA[UV])
            .duration(1 * MINUTE) // Original: 5s, Wrapped: 5s * 16 = 80s
            .circuitInfo(GOOWARE_PROCESSOR_ZPM)
            .buildAndRegister()

        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_ULTIMATE_CIRCUIT_BOARD)
            .input(WRAP_NONLINEAR_CHEMICAL_OSCILLATOR)
            .input(WRAP_CRYSTAL_CPU)
            .input(WRAP_SPINTRONIC_SMD_CAPACITOR)
            .input(WRAP_SPINTRONIC_SMD_TRANSISTOR)
            .input(wireGtQuadruple, Europium, 8)
            .fluidInputs(SolderingAlloy.getFluid(L / 2))
            .output(GOOWARE_PROCESSOR_ZPM, 64)
            .EUt(VHA[UV])
            .duration(30 * SECOND) // Original: 2.5s, Wrapped: 2.5s * 16 = 40s
            .circuitInfo(GOOWARE_PROCESSOR_ZPM)
            .buildAndRegister()

        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_ULTIMATE_CIRCUIT_BOARD)
            .input(WRAP_NONLINEAR_CHEMICAL_OSCILLATOR)
            .input(WRAP_UHASOC_CHIP)
            .input(wireGtQuadruple, Europium, 8)
            .input(bolt, Neutronium, 64)
            .fluidInputs(SolderingAlloy.getFluid(L / 2))
            .output(GOOWARE_PROCESSOR_ZPM, 64)
            .output(GOOWARE_PROCESSOR_ZPM, 64)
            .EUt(VHA[UHV])
            .duration(30 * SECOND) // Original: 2.5s, Wrapped: 2.5s * 16 = 40s
            .circuitInfo(GOOWARE_PROCESSOR_ZPM)
            .buildAndRegister()

        // UV Gooware Processor Assembly
        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_ULTIMATE_CIRCUIT_BOARD)
            .input(GOOWARE_PROCESSOR_ZPM, 64)
            .input(WRAP_GOOWARE_SMD_INDUCTOR, 16)
            .input(WRAP_GOOWARE_SMD_CAPACITOR, 32)
            .input(WRAP_ARAM_CHIP, 32)
            .input(wireGtQuadruple, Europium, 16)
            .fluidInputs(SolderingAlloy.getFluid(L))
            .output(GOOWARE_ASSEMBLY_UV, 48)
            .EUt(VHA[UV])
            .duration(4 * MINUTE) // Original: 20s, Wrapped: 20s * 16 = 320s
            .circuitInfo(GOOWARE_ASSEMBLY_UV)
            .buildAndRegister()

        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_ULTIMATE_CIRCUIT_BOARD)
            .input(GOOWARE_PROCESSOR_ZPM, 64)
            .input(WRAP_OPTICAL_SMD_INDUCTOR, 4)
            .input(WRAP_OPTICAL_SMD_CAPACITOR, 8)
            .input(WRAP_ARAM_CHIP, 32)
            .input(wireGtQuadruple, Europium, 16)
            .fluidInputs(SolderingAlloy.getFluid(L))
            .output(GOOWARE_ASSEMBLY_UV, 48)
            .EUt(VHA[UV])
            .duration(2 * MINUTE) // Original: 10s, Wrapped: 10s * 16 = 160s
            .circuitInfo(GOOWARE_ASSEMBLY_UV)
            .buildAndRegister()

        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_ULTIMATE_CIRCUIT_BOARD)
            .input(GOOWARE_PROCESSOR_ZPM, 64)
            .input(WRAP_SPINTRONIC_SMD_INDUCTOR)
            .input(WRAP_SPINTRONIC_SMD_CAPACITOR, 2)
            .input(WRAP_ARAM_CHIP, 32)
            .input(wireGtQuadruple, Europium, 16)
            .fluidInputs(SolderingAlloy.getFluid(L))
            .output(GOOWARE_ASSEMBLY_UV, 48)
            .EUt(VHA[UV])
            .duration(1 * MINUTE) // Original: 5s, Wrapped: 5s * 16 = 80s
            .circuitInfo(GOOWARE_ASSEMBLY_UV)
            .buildAndRegister()

        // UHV Gooware Supercomputer (AssLine)

        // UEV Gooware Mainframe (AssLine)

        // endregion

        // region T9: Optical

        // UV Optical Processor
        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_OPTICAL_LASER_CONTROL_UNIT)
            .input(WRAP_CRYSTAL_CPU)
            .input(WRAP_OPTICAL_SMD_RESISTOR, 16)
            .input(WRAP_OPTICAL_SMD_CAPACITOR, 16)
            .input(WRAP_OPTICAL_SMD_TRANSISTOR, 16)
            .input(OPTICAL_FIBER, 64)
            .fluidInputs(SolderingAlloy.getFluid(L / 2))
            .output(OPTICAL_PROCESSOR_UV, 64)
            .EUt(VA[UHV])
            .duration(2 * MINUTE) // Original: 10s, Wrapped: 10s * 16 = 160s
            .circuitInfo(OPTICAL_PROCESSOR_UV)
            .buildAndRegister()

        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_OPTICAL_LASER_CONTROL_UNIT)
            .input(WRAP_CRYSTAL_CPU)
            .input(WRAP_SPINTRONIC_SMD_RESISTOR, 4)
            .input(WRAP_SPINTRONIC_SMD_CAPACITOR, 4)
            .input(WRAP_SPINTRONIC_SMD_TRANSISTOR, 4)
            .input(OPTICAL_FIBER, 64)
            .fluidInputs(SolderingAlloy.getFluid(L / 2))
            .output(OPTICAL_PROCESSOR_UV, 64)
            .EUt(VA[UHV])
            .duration(1 * MINUTE) // Original: 5s, Wrapped: 5s * 16 = 80s
            .circuitInfo(OPTICAL_PROCESSOR_UV)
            .buildAndRegister()

        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_OPTICAL_LASER_CONTROL_UNIT)
            .input(WRAP_CRYSTAL_CPU)
            .input(WRAP_COSMIC_SMD_RESISTOR)
            .input(WRAP_COSMIC_SMD_CAPACITOR)
            .input(WRAP_COSMIC_SMD_TRANSISTOR)
            .input(OPTICAL_FIBER, 64)
            .fluidInputs(SolderingAlloy.getFluid(L / 2))
            .output(OPTICAL_PROCESSOR_UV, 64)
            .EUt(VA[UHV])
            .duration(30 * SECOND) // Original: 2.5s, Wrapped: 2.5s * 16 = 40s
            .circuitInfo(OPTICAL_PROCESSOR_UV)
            .buildAndRegister()

        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_OPTICAL_LASER_CONTROL_UNIT)
            .input(WRAP_OPTOELECTRONIC_SYSTEM_ON_CHIP)
            .input(OPTICAL_FIBER, 64)
            .input(bolt, Bedrockium, 64)
            .fluidInputs(SolderingAlloy.getFluid(L / 2))
            .output(OPTICAL_PROCESSOR_UV, 64)
            .output(OPTICAL_PROCESSOR_UV, 64)
            .EUt(VA[UEV])
            .duration(30 * SECOND) // Original: 2.5s, Wrapped: 2.5s * 16 = 40s
            .circuitInfo(OPTICAL_PROCESSOR_UV)
            .buildAndRegister()

        // UHV Optical Processor Assembly
        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_PERFECT_CIRCUIT_BOARD)
            .input(OPTICAL_PROCESSOR_UV, 64)
            .input(WRAP_OPTICAL_SMD_INDUCTOR, 16)
            .input(WRAP_OPTICAL_SMD_CAPACITOR, 32)
            .input(WRAP_HIGHLY_ADVANCED_SOC_CHIP, 32)
            .input(OPTICAL_FIBER, 64)
            .fluidInputs(SolderingAlloy.getFluid(L))
            .output(OPTICAL_ASSEMBLY_UHV, 48)
            .EUt(VA[UHV])
            .duration(4 * MINUTE) // Original: 20s, Wrapped: 20s * 16 = 320s
            .circuitInfo(OPTICAL_ASSEMBLY_UHV)
            .buildAndRegister()

        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_PERFECT_CIRCUIT_BOARD)
            .input(OPTICAL_PROCESSOR_UV, 64)
            .input(WRAP_SPINTRONIC_SMD_INDUCTOR, 4)
            .input(WRAP_SPINTRONIC_SMD_CAPACITOR, 8)
            .input(WRAP_HIGHLY_ADVANCED_SOC_CHIP, 32)
            .input(OPTICAL_FIBER, 64)
            .fluidInputs(SolderingAlloy.getFluid(L))
            .output(OPTICAL_ASSEMBLY_UHV, 48)
            .EUt(VA[UHV])
            .duration(2 * MINUTE) // Original: 10s, Wrapped: 10s * 16 = 160s
            .circuitInfo(OPTICAL_ASSEMBLY_UHV)
            .buildAndRegister()

        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_PERFECT_CIRCUIT_BOARD)
            .input(OPTICAL_PROCESSOR_UV, 64)
            .input(WRAP_COSMIC_SMD_INDUCTOR)
            .input(WRAP_COSMIC_SMD_CAPACITOR, 2)
            .input(WRAP_HIGHLY_ADVANCED_SOC_CHIP, 32)
            .input(OPTICAL_FIBER, 64)
            .fluidInputs(SolderingAlloy.getFluid(L))
            .output(OPTICAL_ASSEMBLY_UHV, 48)
            .EUt(VA[UHV])
            .duration(1 * MINUTE) // Original: 5s, Wrapped: 5s * 16 = 80s
            .circuitInfo(OPTICAL_ASSEMBLY_UHV)
            .buildAndRegister()

        // UEV Optical Computer (AssLine)

        // UIV Optical Mainframe (AssLine)

        // endregion

        // region T10: Spintronic

        // UHV Spintronic Processor
        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_ESR_COMPUTATION_UNIT)
            .input(WRAP_CRYSTAL_SOC)
            .input(WRAP_SPINTRONIC_SMD_RESISTOR, 16)
            .input(WRAP_SPINTRONIC_SMD_CAPACITOR, 16)
            .input(WRAP_SPINTRONIC_SMD_TRANSISTOR, 16)
            .input(wireGtQuadruple, CarbonNanotube, 8)
            .fluidInputs(MutatedLivingSolder.getFluid(L / 2))
            .output(SPINTRONIC_PROCESSOR_UHV, 64)
            .EUt(VA[UEV])
            .duration(2 * MINUTE) // Original: 10s, Wrapped: 10s * 16 = 160s
            .circuitInfo(SPINTRONIC_PROCESSOR_UHV)
            .buildAndRegister()

        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_ESR_COMPUTATION_UNIT)
            .input(WRAP_CRYSTAL_SOC)
            .input(WRAP_COSMIC_SMD_RESISTOR, 4)
            .input(WRAP_COSMIC_SMD_CAPACITOR, 4)
            .input(WRAP_COSMIC_SMD_TRANSISTOR, 4)
            .input(wireGtQuadruple, CarbonNanotube, 8)
            .fluidInputs(MutatedLivingSolder.getFluid(L / 2))
            .output(SPINTRONIC_PROCESSOR_UHV, 64)
            .EUt(VA[UEV])
            .duration(1 * MINUTE) // Original: 5s, Wrapped: 5s * 16 = 80s
            .circuitInfo(SPINTRONIC_PROCESSOR_UHV)
            .buildAndRegister()

        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_ESR_COMPUTATION_UNIT)
            .input(WRAP_CRYSTAL_SOC)
            .input(WRAP_SUPRACAUSAL_SMD_RESISTOR)
            .input(WRAP_SUPRACAUSAL_SMD_CAPACITOR)
            .input(WRAP_SUPRACAUSAL_SMD_TRANSISTOR)
            .input(wireGtQuadruple, CarbonNanotube, 8)
            .fluidInputs(MutatedLivingSolder.getFluid(L / 2))
            .output(SPINTRONIC_PROCESSOR_UHV, 64)
            .EUt(VA[UEV])
            .duration(30 * SECOND) // Original: 2.5s, Wrapped: 2.5s * 16 = 40s
            .circuitInfo(SPINTRONIC_PROCESSOR_UHV)
            .buildAndRegister()

        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_ESR_COMPUTATION_UNIT)
            .input(WRAP_EXOTIC_SYSTEM_ON_CHIP)
            .input(wireGtQuadruple, CarbonNanotube, 8)
            .input(bolt, Vibranium, 64)
            .fluidInputs(MutatedLivingSolder.getFluid(L / 2))
            .output(SPINTRONIC_PROCESSOR_UHV, 64)
            .output(SPINTRONIC_PROCESSOR_UHV, 64)
            .EUt(VA[UIV])
            .duration(30 * SECOND) // Original: 2.5s, Wrapped: 2.5s * 16 = 40s
            .circuitInfo(SPINTRONIC_PROCESSOR_UHV)
            .buildAndRegister()

        // UEV Spintronic Processor Assembly (AssLine)

        // UIV Spintronic Computer (AssLine)

        // UXV Spintronic Mainframe (AssLine)

        // endregion

        // region T11: Cosmic

        // UEV Cosmic Processor
        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_HOLOGRAPHIC_INFORMATION_IMC)
            .input(WRAP_CRYSTAL_SOC)
            .input(WRAP_COSMIC_SMD_RESISTOR, 32)
            .input(WRAP_COSMIC_SMD_CAPACITOR, 32)
            .input(WRAP_COSMIC_SMD_TRANSISTOR, 32)
            .input(wireGtQuadruple, Infinity, 8)
            .fluidInputs(MutatedLivingSolder.getFluid(L / 2))
            .output(COSMIC_PROCESSOR_UEV, 64)
            .EUt(VA[UIV])
            .duration(2 * MINUTE) // Original: 10s, Wrapped: 10s * 16 = 160s
            .circuitInfo(COSMIC_PROCESSOR_UEV)
            .buildAndRegister()

        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_HOLOGRAPHIC_INFORMATION_IMC)
            .input(WRAP_CRYSTAL_SOC)
            .input(WRAP_SUPRACAUSAL_SMD_RESISTOR, 8)
            .input(WRAP_SUPRACAUSAL_SMD_CAPACITOR, 8)
            .input(WRAP_SUPRACAUSAL_SMD_TRANSISTOR, 8)
            .input(wireGtQuadruple, Infinity, 8)
            .fluidInputs(MutatedLivingSolder.getFluid(L / 2))
            .output(COSMIC_PROCESSOR_UEV, 64)
            .EUt(VA[UIV])
            .duration(1 * MINUTE) // Original: 5s, Wrapped: 5s * 16 = 80s
            .circuitInfo(COSMIC_PROCESSOR_UEV)
            .buildAndRegister()

        // UIV Cosmic Processor Assembly (AssLine)

        // UXV Cosmic Supercomputer (AssLine)

        // OpV Cosmic Mainframe (AssLine)

        // endregion

        // region T12: Supracausal

        // UIV Supercausal Processor (NAC)

        // UXV Supracausal Processor Assembly (NAC)

        // OpV Supracausal Supercomputer (NAC)

        // MAX Supracausal Mainframe (NAC)

        // endregion

        // region Misc

        // Lapotronic Orb
        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_EXTREME_CIRCUIT_BOARD)
            .input(WRAP_PIC_CHIP, 4)
            .input(WRAP_ENGRAVED_LAPOTRON_CHIP, 24)
            .input(WRAP_NANO_CPU_CHIP, 2)
            .input(wireGtQuadruple, Platinum, 16)
            .input(plate, Platinum, 64)
            .fluidInputs(SolderingAlloy.getFluid(L))
            .output(ENERGY_LAPOTRONIC_ORB, 16)
            .EUt(VH[EV])
            .duration(6 * MINUTE) // Original: 25.6s, Wrapped: 25.6s * 16 = 409.6s
            .circuitInfo(ENERGY_LAPOTRONIC_ORB)
            .buildAndRegister()

        // Data Stick
        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_ADVANCED_CIRCUIT_BOARD)
            .input(circuit, Tier.HV, 32)
            .input(WRAP_RAM_CHIP, 4)
            .input(WRAP_NOR_CHIP, 16)
            .input(WRAP_NAND_CHIP, 32)
            .input(wireGtQuadruple, Platinum, 32)
            .fluidInputs(SolderingAlloy.getFluid(L))
            .output(TOOL_DATA_STICK, 16)
            .EUt(1200) // EV
            .duration(4 * MINUTE) // Original: 20s, Wrapped: 20s * 16 = 320s
            .buildAndRegister()

        // Data Orb
        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_EXTREME_CIRCUIT_BOARD)
            .input(circuit, Tier.IV, 32)
            .input(WRAP_RAM_CHIP, 8)
            .input(WRAP_NOR_CHIP, 32)
            .input(WRAP_NAND_CHIP, 48)
            .input(wireGtQuadruple, NiobiumTitanium, 32)
            .fluidInputs(SolderingAlloy.getFluid(L))
            .output(TOOL_DATA_ORB, 16)
            .EUt(9600) // LuV
            .duration(4 * MINUTE) // Original: 20s, Wrapped: 20s * 16 = 320s
            .buildAndRegister()

        // Data Module
        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_WETWARE_CIRCUIT_BOARD)
            .input(circuit, Tier.ZPM, 32)
            .input(WRAP_ARAM_CHIP, 32)
            .input(WRAP_NOR_CHIP, 64)
            .input(WRAP_NAND_CHIP, 64)
            .input(wireGtQuadruple, YttriumBariumCuprate, 32)
            .fluidInputs(SolderingAlloy.getFluid(L))
            .output(TOOL_DATA_MODULE, 16)
            .EUt(38400) // ZPM
            .duration(4 * MINUTE) // Original: 20s, Wrapped: 20s * 16 = 320s
            .buildAndRegister()

        // Diamond Modulator
        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_ENGRAVED_DIAMOND_CHIP)
            .input(WRAP_PLASTIC_CIRCUIT_BOARD)
            .input(wireFine, Palladium, 64)
            .input(bolt, Platinum, 64)
            .fluidInputs(SolderingAlloy.getFluid(L / 2))
            .output(DIAMOND_MODULATOR, 64)
            .output(DIAMOND_MODULATOR, 64)
            .EUt(VA[IV])
            .duration(2 * MINUTE) // Original: 10s, Wrapped: 10s * 16 = 160s
            .circuitInfo(DIAMOND_MODULATOR)
            .buildAndRegister()

        // Ruby Modulator
        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_ENGRAVED_RUBY_CHIP)
            .input(WRAP_PLASTIC_CIRCUIT_BOARD)
            .input(wireFine, Palladium, 64)
            .input(bolt, Platinum, 64)
            .fluidInputs(SolderingAlloy.getFluid(L / 2))
            .output(RUBY_MODULATOR, 64)
            .output(RUBY_MODULATOR, 64)
            .EUt(VA[IV])
            .duration(2 * MINUTE) // Original: 10s, Wrapped: 10s * 16 = 160s
            .circuitInfo(RUBY_MODULATOR)
            .buildAndRegister()

        // Sapphire Modulator
        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_ENGRAVED_SAPPHIRE_CHIP)
            .input(WRAP_PLASTIC_CIRCUIT_BOARD)
            .input(wireFine, Palladium, 64)
            .input(bolt, Platinum, 64)
            .fluidInputs(SolderingAlloy.getFluid(L / 2))
            .output(SAPPHIRE_MODULATOR, 64)
            .output(SAPPHIRE_MODULATOR, 64)
                .EUt(VA[IV])
                .duration(2 * MINUTE) // Original: 10s, Wrapped: 10s * 16 = 160s
                .circuitInfo(SAPPHIRE_MODULATOR)
                .buildAndRegister()

        // Crystal SoC Socket
        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_CRYSTAL_INTERFACE_CHIP)
            .input(DIAMOND_MODULATOR, 16)
            .input(RUBY_MODULATOR, 16)
            .input(SAPPHIRE_MODULATOR, 16)
            .input(wireGtQuadruple, Europium, 4)
            .fluidInputs(SolderingAlloy.getFluid(L / 2))
            .output(CRYSTAL_SOC_SOCKET, 16)
            .EUt(VA[LuV])
            .duration(20 * SECOND) // Original: 5s, Wrapped: 5s * 16 = 40s
            .circuitInfo(CRYSTAL_SOC_SOCKET)
            .buildAndRegister()

        // Neuro Processor
        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_WETWARE_CIRCUIT_BOARD)
            .input(STEM_CELLS, 64)
            .input(pipeTinyFluid, Polybenzimidazole, 32)
            .input(plate, Electrum, 64)
            .input(foil, SiliconeRubber, 64)
            .input(bolt, HSSE, 64)
            .fluidInputs(SterileGrowthMedium.getFluid(250))
            .output(NEURO_PROCESSOR, 32)
            .EUt(80000) // ZPM
            .duration(6 * MINUTE) // Original: 30s, Wrapped: 30s * 16 = 480s
            .circuitInfo(NEURO_PROCESSOR)
            .buildAndRegister()

        // endregion
    }

    private fun createCircuitPatternRecipes()
    {
        // ULV Tier
        createCircuitPatternRecipe(VACUUM_TUBE)
        createCircuitPatternRecipe(NAND_CHIP_ULV)

        // LV Tier
        createCircuitPatternRecipe(ELECTRONIC_CIRCUIT_LV)
        createCircuitPatternRecipe(INTEGRATED_CIRCUIT_LV)
        createCircuitPatternRecipe(MICROPROCESSOR_LV)

        // MV Tier
        createCircuitPatternRecipe(ELECTRONIC_CIRCUIT_MV)
        createCircuitPatternRecipe(INTEGRATED_CIRCUIT_MV)
        createCircuitPatternRecipe(PROCESSOR_MV)

        // HV Tier
        createCircuitPatternRecipe(INTEGRATED_CIRCUIT_HV)
        createCircuitPatternRecipe(PROCESSOR_ASSEMBLY_HV)
        createCircuitPatternRecipe(NANO_PROCESSOR_HV)

        // EV Tier
        createCircuitPatternRecipe(WORKSTATION_EV)
        createCircuitPatternRecipe(NANO_PROCESSOR_ASSEMBLY_EV)
        createCircuitPatternRecipe(QUANTUM_PROCESSOR_EV)

        // IV Tier
        createCircuitPatternRecipe(MAINFRAME_IV)
        createCircuitPatternRecipe(NANO_COMPUTER_IV)
        createCircuitPatternRecipe(QUANTUM_ASSEMBLY_IV)
        createCircuitPatternRecipe(CRYSTAL_PROCESSOR_IV)

        // LuV Tier
        createCircuitPatternRecipe(NANO_MAINFRAME_LUV)
        createCircuitPatternRecipe(QUANTUM_COMPUTER_LUV)
        createCircuitPatternRecipe(CRYSTAL_ASSEMBLY_LUV)
        createCircuitPatternRecipe(WETWARE_PROCESSOR_LUV)

        // ZPM Tier
        createCircuitPatternRecipe(QUANTUM_MAINFRAME_ZPM)
        createCircuitPatternRecipe(CRYSTAL_COMPUTER_ZPM)
        createCircuitPatternRecipe(WETWARE_PROCESSOR_ASSEMBLY_ZPM)
        createCircuitPatternRecipe(GOOWARE_PROCESSOR_ZPM)

        // UV Tier
        createCircuitPatternRecipe(CRYSTAL_MAINFRAME_UV)
        createCircuitPatternRecipe(WETWARE_SUPER_COMPUTER_UV)
        createCircuitPatternRecipe(GOOWARE_ASSEMBLY_UV)
        createCircuitPatternRecipe(OPTICAL_PROCESSOR_UV)

        // UHV Tier
        createCircuitPatternRecipe(WETWARE_MAINFRAME_UHV)
        createCircuitPatternRecipe(GOOWARE_COMPUTER_UHV)
        createCircuitPatternRecipe(OPTICAL_ASSEMBLY_UHV)
        createCircuitPatternRecipe(SPINTRONIC_PROCESSOR_UHV)

        // UEV Tier
        createCircuitPatternRecipe(GOOWARE_MAINFRAME_UEV)
        createCircuitPatternRecipe(OPTICAL_COMPUTER_UEV)
        createCircuitPatternRecipe(SPINTRONIC_ASSEMBLY_UEV)
        createCircuitPatternRecipe(COSMIC_PROCESSOR_UEV)

        // UIV Tier
        createCircuitPatternRecipe(OPTICAL_MAINFRAME_UIV)
        createCircuitPatternRecipe(SPINTRONIC_COMPUTER_UIV)
        createCircuitPatternRecipe(COSMIC_ASSEMBLY_UIV)
        createCircuitPatternRecipe(SUPRACAUSAL_PROCESSOR_UIV)

        // UXV Tier
        createCircuitPatternRecipe(SPINTRONIC_MAINFRAME_UXV)
        createCircuitPatternRecipe(COSMIC_COMPUTER_UXV)
        createCircuitPatternRecipe(SUPRACAUSAL_ASSEMBLY_UXV)

        // OpV Tier
        createCircuitPatternRecipe(COSMIC_MAINFRAME_OpV)
        createCircuitPatternRecipe(SUPRACAUSAL_COMPUTER_OpV)

        // MAX Tier
        createCircuitPatternRecipe(SUPRACAUSAL_MAINFRAME_MAX)

        // Misc
        createCircuitPatternRecipe(ENERGY_LAPOTRONIC_ORB)
        createCircuitPatternRecipe(DIAMOND_MODULATOR)
        createCircuitPatternRecipe(RUBY_MODULATOR)
        createCircuitPatternRecipe(SAPPHIRE_MODULATOR)
        createCircuitPatternRecipe(CRYSTAL_SOC_SOCKET)
    }

    private fun createWrapItemMaps()
    {
        // Boards
        wrapItems[COATED_BOARD] = WRAP_COATED_BOARD
        wrapItems[PHENOLIC_BOARD] = WRAP_PHENOLIC_BOARD
        wrapItems[PLASTIC_BOARD] = WRAP_PLASTIC_BOARD
        wrapItems[EPOXY_BOARD] = WRAP_EPOXY_BOARD
        wrapItems[FIBER_BOARD] = WRAP_FIBER_BOARD
        wrapItems[MULTILAYER_FIBER_BOARD] = WRAP_MULTILAYER_FIBER_BOARD
        wrapItems[WETWARE_BOARD] = WRAP_WETWARE_BOARD
        wrapItems[GOOWARE_BOARD] = WRAP_GOOWARE_BOARD
        wrapItems[OPTICAL_BOARD] = WRAP_OPTICAL_BOARD
        wrapItems[SPINTRONIC_BOARD] = WRAP_SPINTRONIC_BOARD

        // Circuit Boards
        wrapItems[BASIC_CIRCUIT_BOARD] = WRAP_BASIC_CIRCUIT_BOARD
        wrapItems[GOOD_CIRCUIT_BOARD] = WRAP_GOOD_CIRCUIT_BOARD
        wrapItems[PLASTIC_CIRCUIT_BOARD] = WRAP_PLASTIC_CIRCUIT_BOARD
        wrapItems[ADVANCED_CIRCUIT_BOARD] = WRAP_ADVANCED_CIRCUIT_BOARD
        wrapItems[EXTREME_CIRCUIT_BOARD] = WRAP_EXTREME_CIRCUIT_BOARD
        wrapItems[ELITE_CIRCUIT_BOARD] = WRAP_ELITE_CIRCUIT_BOARD
        wrapItems[WETWARE_CIRCUIT_BOARD] = WRAP_WETWARE_CIRCUIT_BOARD
        wrapItems[ULTIMATE_CIRCUIT_BOARD] = WRAP_ULTIMATE_CIRCUIT_BOARD
        wrapItems[PERFECT_CIRCUIT_BOARD] = WRAP_PERFECT_CIRCUIT_BOARD
        wrapItems[INFINITE_CIRCUIT_BOARD] = WRAP_INFINITE_CIRCUIT_BOARD

        // SMDs
        wrapItems[SMD_TRANSISTOR] = WRAP_SMD_TRANSISTOR
        wrapItems[SMD_RESISTOR] = WRAP_SMD_RESISTOR
        wrapItems[SMD_CAPACITOR] = WRAP_SMD_CAPACITOR
        wrapItems[SMD_DIODE] = WRAP_SMD_DIODE
        wrapItems[SMD_INDUCTOR] = WRAP_SMD_INDUCTOR
        wrapItems[ADVANCED_SMD_TRANSISTOR] = WRAP_ADVANCED_SMD_TRANSISTOR
        wrapItems[ADVANCED_SMD_RESISTOR] = WRAP_ADVANCED_SMD_RESISTOR
        wrapItems[ADVANCED_SMD_CAPACITOR] = WRAP_ADVANCED_SMD_CAPACITOR
        wrapItems[ADVANCED_SMD_DIODE] = WRAP_ADVANCED_SMD_DIODE
        wrapItems[ADVANCED_SMD_INDUCTOR] = WRAP_ADVANCED_SMD_INDUCTOR
        wrapItems[GOOWARE_SMD_TRANSISTOR] = WRAP_GOOWARE_SMD_TRANSISTOR
        wrapItems[GOOWARE_SMD_RESISTOR] = WRAP_GOOWARE_SMD_RESISTOR
        wrapItems[GOOWARE_SMD_CAPACITOR] = WRAP_GOOWARE_SMD_CAPACITOR
        wrapItems[GOOWARE_SMD_DIODE] = WRAP_GOOWARE_SMD_DIODE
        wrapItems[GOOWARE_SMD_INDUCTOR] = WRAP_GOOWARE_SMD_INDUCTOR
        wrapItems[OPTICAL_SMD_TRANSISTOR] = WRAP_OPTICAL_SMD_TRANSISTOR
        wrapItems[OPTICAL_SMD_RESISTOR] = WRAP_OPTICAL_SMD_RESISTOR
        wrapItems[OPTICAL_SMD_CAPACITOR] = WRAP_OPTICAL_SMD_CAPACITOR
        wrapItems[OPTICAL_SMD_DIODE] = WRAP_OPTICAL_SMD_DIODE
        wrapItems[OPTICAL_SMD_INDUCTOR] = WRAP_OPTICAL_SMD_INDUCTOR
        wrapItems[SPINTRONIC_SMD_TRANSISTOR] = WRAP_SPINTRONIC_SMD_TRANSISTOR
        wrapItems[SPINTRONIC_SMD_RESISTOR] = WRAP_SPINTRONIC_SMD_RESISTOR
        wrapItems[SPINTRONIC_SMD_CAPACITOR] = WRAP_SPINTRONIC_SMD_CAPACITOR
        wrapItems[SPINTRONIC_SMD_DIODE] = WRAP_SPINTRONIC_SMD_DIODE
        wrapItems[SPINTRONIC_SMD_INDUCTOR] = WRAP_SPINTRONIC_SMD_INDUCTOR
        wrapItems[COSMIC_SMD_TRANSISTOR] = WRAP_COSMIC_SMD_TRANSISTOR
        wrapItems[COSMIC_SMD_RESISTOR] = WRAP_COSMIC_SMD_RESISTOR
        wrapItems[COSMIC_SMD_CAPACITOR] = WRAP_COSMIC_SMD_CAPACITOR
        wrapItems[COSMIC_SMD_DIODE] = WRAP_COSMIC_SMD_DIODE
        wrapItems[COSMIC_SMD_INDUCTOR] = WRAP_COSMIC_SMD_INDUCTOR
        wrapItems[SUPRACAUSAL_SMD_TRANSISTOR] = WRAP_SUPRACAUSAL_SMD_TRANSISTOR
        wrapItems[SUPRACAUSAL_SMD_RESISTOR] = WRAP_SUPRACAUSAL_SMD_RESISTOR
        wrapItems[SUPRACAUSAL_SMD_CAPACITOR] = WRAP_SUPRACAUSAL_SMD_CAPACITOR
        wrapItems[SUPRACAUSAL_SMD_DIODE] = WRAP_SUPRACAUSAL_SMD_DIODE
        wrapItems[SUPRACAUSAL_SMD_INDUCTOR] = WRAP_SUPRACAUSAL_SMD_INDUCTOR

        // Chips
        wrapItems[CENTRAL_PROCESSING_UNIT] = WRAP_CPU_CHIP
        wrapItems[RANDOM_ACCESS_MEMORY] = WRAP_RAM_CHIP
        wrapItems[INTEGRATED_LOGIC_CIRCUIT] = WRAP_ILC_CHIP
        wrapItems[NANO_CENTRAL_PROCESSING_UNIT] = WRAP_NANO_CPU_CHIP
        wrapItems[QUBIT_CENTRAL_PROCESSING_UNIT] = WRAP_QUBIT_CPU_CHIP
        wrapItems[SIMPLE_SYSTEM_ON_CHIP] = WRAP_SIMPLE_SOC_CHIP
        wrapItems[SYSTEM_ON_CHIP] = WRAP_SOC_CHIP
        wrapItems[ADVANCED_SYSTEM_ON_CHIP] = WRAP_ADVANCED_SOC_CHIP
        wrapItems[HIGHLY_ADVANCED_SOC] = WRAP_HIGHLY_ADVANCED_SOC_CHIP
        wrapItems[NAND_MEMORY_CHIP] = WRAP_NAND_CHIP
        wrapItems[NOR_MEMORY_CHIP] = WRAP_NOR_CHIP
        wrapItems[ULTRA_LOW_POWER_INTEGRATED_CIRCUIT] = WRAP_ULPIC_CHIP
        wrapItems[LOW_POWER_INTEGRATED_CIRCUIT] = WRAP_LPIC_CHIP
        wrapItems[POWER_INTEGRATED_CIRCUIT] = WRAP_PIC_CHIP
        wrapItems[HIGH_POWER_INTEGRATED_CIRCUIT] = WRAP_HPIC_CHIP
        wrapItems[ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT] = WRAP_UHPIC_CHIP
        wrapItems[NANO_PIC_CHIP] = WRAP_NPIC_CHIP
        wrapItems[PICO_PIC_CHIP] = WRAP_PPIC_CHIP
        wrapItems[FEMTO_PIC_CHIP] = WRAP_FPIC_CHIP
        wrapItems[ATTO_PIC_CHIP] = WRAP_APIC_CHIP
        wrapItems[ADVANCED_RAM_CHIP] = WRAP_ARAM_CHIP
        wrapItems[ULTRA_HIGHLY_ADVANCED_SOC_CHIP] = WRAP_UHASOC_CHIP
        wrapItems[CRYSTAL_INTERFACE_CHIP] = WRAP_CRYSTAL_INTERFACE_CHIP
        wrapItems[PHASE_CHANGE_RAM_CHIP] = WRAP_PRAM_CHIP
        wrapItems[ALL_OPTICAL_CASCADE_NOR_CHIP] = WRAP_ACNOR_CHIP
        wrapItems[SPIN_TRANSFER_TORQUE_RAM_CHIP] = WRAP_STTRAM_CHIP
        wrapItems[MAGNETIC_DOMAIN_WALL_INVERSION_NAND_CHIP] = WRAP_MINAND_CHIP

        // Engraved Chips
        wrapItems[ENGRAVED_LAPOTRON_CHIP] = WRAP_ENGRAVED_LAPOTRON_CHIP
        wrapItems[ENGRAVED_DIAMOND_CHIP] = WRAP_ENGRAVED_DIAMOND_CHIP
        wrapItems[ENGRAVED_RUBY_CHIP] = WRAP_ENGRAVED_RUBY_CHIP
        wrapItems[ENGRAVED_SAPPHIRE_CHIP] = WRAP_ENGRAVED_SAPPHIRE_CHIP

        // Circuit Components
        wrapItems[CRYSTAL_CENTRAL_PROCESSING_UNIT] = WRAP_CRYSTAL_CPU
        wrapItems[CRYSTAL_SYSTEM_ON_CHIP] = WRAP_CRYSTAL_SOC
        wrapItems[NEURO_PROCESSOR] = WRAP_NEURO_PROCESSOR
        wrapItems[NONLINEAR_CHEMICAL_OSCILLATOR] = WRAP_NONLINEAR_CHEMICAL_OSCILLATOR
        wrapItems[OPTICAL_LASER_CONTROL_UNIT] = WRAP_OPTICAL_LASER_CONTROL_UNIT
        wrapItems[OPTOELECTRONIC_SYSTEM_ON_CHIP] = WRAP_OPTOELECTRONIC_SYSTEM_ON_CHIP
        wrapItems[ESR_COMPUTATION_UNIT] = WRAP_ESR_COMPUTATION_UNIT
        wrapItems[EXOTIC_SYSTEM_ON_CHIP] = WRAP_EXOTIC_SYSTEM_ON_CHIP
        wrapItems[COSMIC_INFORMATION_MODULE] = WRAP_COSMIC_INFORMATION_MODULE
        wrapItems[HOLOGRAPHIC_INFORMATION_IMC] = WRAP_HOLOGRAPHIC_INFORMATION_IMC
    }

    // @formatter:on

}