package gregtechlite.gtlitecore.loader.recipe.producer

import gregtech.api.items.metaitem.MetaItem
import gregtech.api.recipes.Recipe
import gregtech.api.recipes.RecipeMaps.CIRCUIT_ASSEMBLER_RECIPES
import gregtech.api.recipes.ingredients.GTRecipeFluidInput
import gregtech.api.recipes.ingredients.GTRecipeInput
import gregtech.api.recipes.ingredients.GTRecipeItemInput
import gregtech.api.recipes.ingredients.GTRecipeOreInput
import gregtech.api.unification.FluidUnifier
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Material
import gregtech.api.unification.material.Materials.SolderingAlloy
import gregtech.api.unification.material.Materials.Tin
import gregtech.api.unification.ore.OrePrefix
import gregtech.api.unification.ore.OrePrefix.wireFine
import gregtech.api.unification.ore.OrePrefix.wireGtDouble
import gregtech.api.unification.ore.OrePrefix.wireGtHex
import gregtech.api.unification.ore.OrePrefix.wireGtOctal
import gregtech.api.unification.ore.OrePrefix.wireGtQuadruple
import gregtech.api.unification.ore.OrePrefix.wireGtSingle
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
import gregtech.common.items.MetaItems.HIGH_POWER_INTEGRATED_CIRCUIT
import gregtech.common.items.MetaItems.HIGHLY_ADVANCED_SOC
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
import gregtechlite.gtlitecore.api.LOGGER
import gregtechlite.gtlitecore.api.extension.addRecipe
import gregtechlite.gtlitecore.api.extension.copy
import gregtechlite.gtlitecore.api.extension.stack
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CIRCUIT_ASSEMBLY_LINE_RECIPES
import gregtechlite.gtlitecore.api.recipe.util.circuitInfo
import gregtechlite.gtlitecore.api.recipe.util.createCircuitPatternRecipe
import gregtechlite.gtlitecore.api.recipe.util.createWrapItemMap
import gregtechlite.gtlitecore.api.recipe.util.wrapItems
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ADVANCED_RAM_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ALL_OPTICAL_CASCADE_NOR_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.AMPLITUDE_DUALITY_DISTURBANCE_RAM_CHIP
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
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.EXCITATION_SPECTRUM_COMPOSITE_LOGICAL_GATE_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.EXCITED_EXOTIC_ATOM_RESTRICT_CPU_CHIP
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
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ADDRAM_CHIP
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
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_EARCPU_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ELITE_CIRCUIT_BOARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ENGRAVED_DIAMOND_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ENGRAVED_LAPOTRON_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ENGRAVED_RUBY_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ENGRAVED_SAPPHIRE_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_EPOXY_BOARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ESCLG_CHIP
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
import net.minecraft.item.Item
import net.minecraft.item.ItemStack

/**
 * Circuit Assembly Line (CAL) recipe producer.
 *
 * Every recipe of [CIRCUIT_ASSEMBLER_RECIPES] whose output is one of the [TARGETS] is wrapped
 * into a CAL recipe, so the CAL recipes always follow the actual circuit assembler recipes (including
 * all modifications done by the `gregtechlite.gtlitecore.loader.recipe.circuit` package).
 *
 * A wrapped recipe performs 16 circuit assembler crafts at once, which gives the following rules:
 *
 * - Items with a wrapped form ([wrapItems]): transformed into the wrapped item and keep their count
 *   (1 wrapped item = 16 original items).
 * - Wires: upgraded by 16 material amounts (`wireFine` -> `wireGtQuadruple`, `wireGtSingle` ->
 *   `wireGtHex`, ...) and keep their count; if no gauge fits, the heaviest gauge with a whole count
 *   is used.
 * - All other solid inputs: multiplied by 16 and capped at one stack (64).
 * - Fluid inputs: inherited unchanged, the soldering variant of the base recipe wins over the
 *   automatically generated tin variant.
 * - Outputs: multiplied by 16 and split into stacks of 64.
 * - EUt: inherited, duration: multiplied by 16.
 *
 * @author Magic_Sweepy
 */
internal object CircuitAssemblyLineRecipeProducer
{
    private const val WRAP_FACTOR = 16

    private const val MAX_ITEM_INPUTS = 6
    private const val MAX_FLUID_INPUTS = 1
    private const val MAX_OUTPUT_STACKS = 6

    private const val MAX_STACK_SIZE = 64
    private const val DURATION_MULTIPLIER = 16

    private val WIRE_PREFIXES = listOf(wireGtHex, wireGtOctal, wireGtQuadruple, wireGtDouble, wireGtSingle, wireFine)

    // @formatter:off

    // Target output item for CAL recipes.
    private val TARGETS: List<MetaItem<*>.MetaValueItem> = listOf(
        /* ULV */ VACUUM_TUBE, NAND_CHIP_ULV,
        /* LV  */ ELECTRONIC_CIRCUIT_LV, INTEGRATED_CIRCUIT_LV, MICROPROCESSOR_LV,
        /* MV  */ ELECTRONIC_CIRCUIT_MV, INTEGRATED_CIRCUIT_MV, PROCESSOR_MV,
        /* HV  */ INTEGRATED_CIRCUIT_HV, PROCESSOR_ASSEMBLY_HV, NANO_PROCESSOR_HV,
        /* EV  */ WORKSTATION_EV, NANO_PROCESSOR_ASSEMBLY_EV, QUANTUM_PROCESSOR_EV,
        /* IV  */ MAINFRAME_IV, NANO_COMPUTER_IV, QUANTUM_ASSEMBLY_IV, CRYSTAL_PROCESSOR_IV,
        /* LuV */ NANO_MAINFRAME_LUV, QUANTUM_COMPUTER_LUV, CRYSTAL_ASSEMBLY_LUV, WETWARE_PROCESSOR_LUV,
        /* ZPM */ QUANTUM_MAINFRAME_ZPM, CRYSTAL_COMPUTER_ZPM, WETWARE_PROCESSOR_ASSEMBLY_ZPM, GOOWARE_PROCESSOR_ZPM,
        /* UV  */ CRYSTAL_MAINFRAME_UV, WETWARE_SUPER_COMPUTER_UV, GOOWARE_ASSEMBLY_UV, OPTICAL_PROCESSOR_UV,
        /* UHV */ WETWARE_MAINFRAME_UHV, GOOWARE_COMPUTER_UHV, OPTICAL_ASSEMBLY_UHV, SPINTRONIC_PROCESSOR_UHV,
        /* UEV */ GOOWARE_MAINFRAME_UEV, OPTICAL_COMPUTER_UEV, SPINTRONIC_ASSEMBLY_UEV, COSMIC_PROCESSOR_UEV,
        /* UIV */ OPTICAL_MAINFRAME_UIV, SPINTRONIC_COMPUTER_UIV, COSMIC_ASSEMBLY_UIV, SUPRACAUSAL_PROCESSOR_UIV,
        /* UXV */ SPINTRONIC_MAINFRAME_UXV, COSMIC_COMPUTER_UXV, SUPRACAUSAL_ASSEMBLY_UXV,
        /* OpV */ COSMIC_MAINFRAME_OpV, SUPRACAUSAL_COMPUTER_OpV,
        /* MAX */ SUPRACAUSAL_MAINFRAME_MAX,
        ENERGY_LAPOTRONIC_ORB, DIAMOND_MODULATOR, RUBY_MODULATOR, SAPPHIRE_MODULATOR,
        CRYSTAL_SOC_SOCKET, NEURO_PROCESSOR, TOOL_DATA_STICK, TOOL_DATA_ORB, TOOL_DATA_MODULE)

    // @formatter:on

    private val wrappedItems = hashMapOf<Pair<Item, Int>, ItemStack>()

    private var isWrapItemsInitialized = false

    private data class RecipeSignature(val inputs: List<String>, val outputs: List<String>, val duration: Int, val eut: Long)

    fun init()
    {
        if (isWrapItemsInitialized) return
        isWrapItemsInitialized = true
        createWrapItemMaps()
    }

    fun produce()
    {
        init()
        createCircuitPatternRecipes()

        val targets = hashMapOf<Pair<Item, Int>, MetaItem<*>.MetaValueItem>()
        TARGETS.forEach { targets[it.stack().toMetadataKey()] = it }

        val baseRecipes = linkedMapOf<RecipeSignature, MutableList<Recipe>>()
        for (base in CIRCUIT_ASSEMBLER_RECIPES.recipeList)
        {
            if (base.isHidden) continue
            if (base.outputs.none { it.toMetadataKey() in targets }) continue
            baseRecipes.getOrPut(createRecipeSignature(base)) { mutableListOf() }.add(base)
        }

        val recipesByTarget = linkedMapOf<MetaItem<*>.MetaValueItem, MutableList<Recipe>>()
        for (candidates in baseRecipes.values)
        {
            // Prefer the soldering alloy recipe, the tin recipe is its cheaper fallback.
            val base = candidates.firstOrNull { it.hasFluid(SolderingAlloy) }
                ?: candidates.firstOrNull { !it.hasFluid(Tin) }
                ?: candidates.first()
            val target = base.outputs.firstNotNullOfOrNull { stack -> targets[stack.toMetadataKey()] } ?: continue
            recipesByTarget.getOrPut(target) { mutableListOf() }.add(base)
        }

        var droppedRecipe = 0
        for (target in TARGETS)
        {
            val candidates = recipesByTarget[target] ?: continue
            for (candidate in candidates.sortedWith(compareBy({ it.duration }, { it.eUt })))
            {
                if (!generateCALRecipe(candidate, target))
                    droppedRecipe++
            }
        }
        if (droppedRecipe > 0)
            LOGGER.warn("Dropped {} CAL recipe(s), see the messages above for the affected circuits", droppedRecipe)
    }

    private fun generateCALRecipe(base: Recipe, target: MetaItem<*>.MetaValueItem): Boolean
    {
        val ingredients = base.inputs.asSequence()
            .filterNot { it.isNonConsumable }
            .mapNotNull { transformInput(it) }
            .toList()

        val fluids = base.fluidInputs.mapNotNull { (it as? GTRecipeFluidInput)?.inputFluidStack }
        val results = splitOutputs(base.outputs)

        if (ingredients.size > MAX_ITEM_INPUTS || fluids.size > MAX_FLUID_INPUTS
            || results.size > MAX_OUTPUT_STACKS)
        {
            LOGGER.warn("Skipped CAL recipe for {}: {} item inputs (max {}), {} fluid inputs (max {}), " +
                "{} output stacks (max {})", target.stack().displayName, ingredients.size, MAX_ITEM_INPUTS,
                fluids.size, MAX_FLUID_INPUTS, results.size, MAX_OUTPUT_STACKS)
            return false
        }

        CIRCUIT_ASSEMBLY_LINE_RECIPES.addRecipe {
            inputIngredients(ingredients)
            fluids.forEach { fluidInputs(it.copy()) }
            outputs(results)
            EUt(base.eUt)
            duration(base.duration * DURATION_MULTIPLIER)
            circuitInfo(target)
        }
        return true
    }

    private fun transformInput(input: GTRecipeInput): GTRecipeInput? = when (input)
    {
        is GTRecipeOreInput  -> transformOreInput(input)
        is GTRecipeItemInput -> transformItemInput(input)
        else                 -> input
    }

    private fun transformOreInput(input: GTRecipeOreInput): GTRecipeInput
    {
        val stacks = input.inputStacks

        // Prefer a candidate with a wrapped form, e.g. the SMD variant of a component tag.
        stacks.firstNotNullOfOrNull { wrappedItems[it.toMetadataKey()] }?.let {
            return GTRecipeItemInput(it.copy(input.amount))
        }

        // Tags with several materials cannot be resolved to one of them, so they keep their tag.
        if (stacks.size == 1)
        {
            val stack = stacks.first()
            val prefix = OreDictUnifier.getPrefix(stack)
            val material = OreDictUnifier.getMaterial(stack)?.material
            if (prefix != null && material != null)
            {
                return if (prefix in WIRE_PREFIXES) transformWireInput(prefix, material, input.amount)
                else GTRecipeOreInput(prefix, material, scaleCount(input.amount))
            }
        }
        return input.copyWithAmount(scaleCount(input.amount))
    }

    private fun transformItemInput(input: GTRecipeItemInput): GTRecipeInput
    {
        val stacks = input.inputStacks
        if (stacks.size == 1)
        {
            wrappedItems[stacks.first().toMetadataKey()]?.let {
                return GTRecipeItemInput(it.copy(input.amount))
            }
        }
        return input.copyWithAmount(scaleCount(input.amount))
    }

    private fun transformWireInput(prefix: OrePrefix, material: Material, count: Int): GTRecipeInput
    {
        val amount = prefix.getMaterialAmount(material)
        if (amount <= 0) return GTRecipeOreInput(prefix, material, scaleCount(count))

        // Keep the count and upgrade the gauge by exactly 16 material amounts.
        WIRE_PREFIXES.firstOrNull { it.getMaterialAmount(material) == amount * WRAP_FACTOR }?.let {
            return GTRecipeOreInput(it, material, count)
        }

        val total = amount * count * WRAP_FACTOR
        WIRE_PREFIXES.firstOrNull { candidate ->
            val candidateAmount = candidate.getMaterialAmount(material)
            candidateAmount > 0 && total % candidateAmount == 0L
                && total / candidateAmount <= MAX_STACK_SIZE
        }?.let {
            return GTRecipeOreInput(it, material, (total / it.getMaterialAmount(material)).toInt())
        }

        return GTRecipeOreInput(prefix, material, scaleCount(count))
    }

    private fun splitOutputs(outputs: List<ItemStack>): List<ItemStack>
    {
        val stacks = mutableListOf<ItemStack>()
        for (output in outputs)
        {
            var remaining = output.count.toLong() * WRAP_FACTOR
            while (remaining > 0L)
            {
                val count = minOf(remaining, MAX_STACK_SIZE.toLong()).toInt()
                stacks.add(output.copy(count))
                remaining -= count
            }
        }
        return stacks
    }

    private fun scaleCount(count: Int): Int = minOf(count * WRAP_FACTOR, MAX_STACK_SIZE)

    private fun createRecipeSignature(base: Recipe) = RecipeSignature(
        base.inputs.map { it.toString() },
        base.outputs.map { "${it.item.registryName}@${it.metadata}x${it.count}" },
        base.duration, base.eUt)

    @Suppress("UnstableApiUsage")
    private fun Recipe.hasFluid(material: Material): Boolean = fluidInputs.any { input ->
        val stack = (input as? GTRecipeFluidInput)?.inputFluidStack ?: return@any false
        FluidUnifier.getMaterialFromFluid(stack.fluid) == material
    }

    private fun ItemStack.toMetadataKey(): Pair<Item, Int> = item to metadata

    private fun createCircuitPatternRecipes()
    {
        TARGETS.forEach { createCircuitPatternRecipe(it) }
    }

    private fun wrap(item: MetaItem<*>.MetaValueItem, wrapItem: MetaItem<*>.MetaValueItem)
    {
        createWrapItemMap(item, wrapItem)
        wrappedItems[item.stack().toMetadataKey()] = wrapItem.stack()
    }

    private fun createWrapItemMaps()
    {
        // Boards
        wrap(COATED_BOARD, WRAP_COATED_BOARD)
        wrap(PHENOLIC_BOARD, WRAP_PHENOLIC_BOARD)
        wrap(PLASTIC_BOARD, WRAP_PLASTIC_BOARD)
        wrap(EPOXY_BOARD, WRAP_EPOXY_BOARD)
        wrap(FIBER_BOARD, WRAP_FIBER_BOARD)
        wrap(MULTILAYER_FIBER_BOARD, WRAP_MULTILAYER_FIBER_BOARD)
        wrap(WETWARE_BOARD, WRAP_WETWARE_BOARD)
        wrap(GOOWARE_BOARD, WRAP_GOOWARE_BOARD)
        wrap(OPTICAL_BOARD, WRAP_OPTICAL_BOARD)
        wrap(SPINTRONIC_BOARD, WRAP_SPINTRONIC_BOARD)

        // Circuit Boards
        wrap(BASIC_CIRCUIT_BOARD, WRAP_BASIC_CIRCUIT_BOARD)
        wrap(GOOD_CIRCUIT_BOARD, WRAP_GOOD_CIRCUIT_BOARD)
        wrap(PLASTIC_CIRCUIT_BOARD, WRAP_PLASTIC_CIRCUIT_BOARD)
        wrap(ADVANCED_CIRCUIT_BOARD, WRAP_ADVANCED_CIRCUIT_BOARD)
        wrap(EXTREME_CIRCUIT_BOARD, WRAP_EXTREME_CIRCUIT_BOARD)
        wrap(ELITE_CIRCUIT_BOARD, WRAP_ELITE_CIRCUIT_BOARD)
        wrap(WETWARE_CIRCUIT_BOARD, WRAP_WETWARE_CIRCUIT_BOARD)
        wrap(ULTIMATE_CIRCUIT_BOARD, WRAP_ULTIMATE_CIRCUIT_BOARD)
        wrap(PERFECT_CIRCUIT_BOARD, WRAP_PERFECT_CIRCUIT_BOARD)
        wrap(INFINITE_CIRCUIT_BOARD, WRAP_INFINITE_CIRCUIT_BOARD)

        // SMDs
        wrap(SMD_TRANSISTOR, WRAP_SMD_TRANSISTOR)
        wrap(SMD_RESISTOR, WRAP_SMD_RESISTOR)
        wrap(SMD_CAPACITOR, WRAP_SMD_CAPACITOR)
        wrap(SMD_DIODE, WRAP_SMD_DIODE)
        wrap(SMD_INDUCTOR, WRAP_SMD_INDUCTOR)
        wrap(ADVANCED_SMD_TRANSISTOR, WRAP_ADVANCED_SMD_TRANSISTOR)
        wrap(ADVANCED_SMD_RESISTOR, WRAP_ADVANCED_SMD_RESISTOR)
        wrap(ADVANCED_SMD_CAPACITOR, WRAP_ADVANCED_SMD_CAPACITOR)
        wrap(ADVANCED_SMD_DIODE, WRAP_ADVANCED_SMD_DIODE)
        wrap(ADVANCED_SMD_INDUCTOR, WRAP_ADVANCED_SMD_INDUCTOR)
        wrap(GOOWARE_SMD_TRANSISTOR, WRAP_GOOWARE_SMD_TRANSISTOR)
        wrap(GOOWARE_SMD_RESISTOR, WRAP_GOOWARE_SMD_RESISTOR)
        wrap(GOOWARE_SMD_CAPACITOR, WRAP_GOOWARE_SMD_CAPACITOR)
        wrap(GOOWARE_SMD_DIODE, WRAP_GOOWARE_SMD_DIODE)
        wrap(GOOWARE_SMD_INDUCTOR, WRAP_GOOWARE_SMD_INDUCTOR)
        wrap(OPTICAL_SMD_TRANSISTOR, WRAP_OPTICAL_SMD_TRANSISTOR)
        wrap(OPTICAL_SMD_RESISTOR, WRAP_OPTICAL_SMD_RESISTOR)
        wrap(OPTICAL_SMD_CAPACITOR, WRAP_OPTICAL_SMD_CAPACITOR)
        wrap(OPTICAL_SMD_DIODE, WRAP_OPTICAL_SMD_DIODE)
        wrap(OPTICAL_SMD_INDUCTOR, WRAP_OPTICAL_SMD_INDUCTOR)
        wrap(SPINTRONIC_SMD_TRANSISTOR, WRAP_SPINTRONIC_SMD_TRANSISTOR)
        wrap(SPINTRONIC_SMD_RESISTOR, WRAP_SPINTRONIC_SMD_RESISTOR)
        wrap(SPINTRONIC_SMD_CAPACITOR, WRAP_SPINTRONIC_SMD_CAPACITOR)
        wrap(SPINTRONIC_SMD_DIODE, WRAP_SPINTRONIC_SMD_DIODE)
        wrap(SPINTRONIC_SMD_INDUCTOR, WRAP_SPINTRONIC_SMD_INDUCTOR)
        wrap(COSMIC_SMD_TRANSISTOR, WRAP_COSMIC_SMD_TRANSISTOR)
        wrap(COSMIC_SMD_RESISTOR, WRAP_COSMIC_SMD_RESISTOR)
        wrap(COSMIC_SMD_CAPACITOR, WRAP_COSMIC_SMD_CAPACITOR)
        wrap(COSMIC_SMD_DIODE, WRAP_COSMIC_SMD_DIODE)
        wrap(COSMIC_SMD_INDUCTOR, WRAP_COSMIC_SMD_INDUCTOR)
        wrap(SUPRACAUSAL_SMD_TRANSISTOR, WRAP_SUPRACAUSAL_SMD_TRANSISTOR)
        wrap(SUPRACAUSAL_SMD_RESISTOR, WRAP_SUPRACAUSAL_SMD_RESISTOR)
        wrap(SUPRACAUSAL_SMD_CAPACITOR, WRAP_SUPRACAUSAL_SMD_CAPACITOR)
        wrap(SUPRACAUSAL_SMD_DIODE, WRAP_SUPRACAUSAL_SMD_DIODE)
        wrap(SUPRACAUSAL_SMD_INDUCTOR, WRAP_SUPRACAUSAL_SMD_INDUCTOR)

        // Chips
        wrap(CENTRAL_PROCESSING_UNIT, WRAP_CPU_CHIP)
        wrap(RANDOM_ACCESS_MEMORY, WRAP_RAM_CHIP)
        wrap(INTEGRATED_LOGIC_CIRCUIT, WRAP_ILC_CHIP)
        wrap(NANO_CENTRAL_PROCESSING_UNIT, WRAP_NANO_CPU_CHIP)
        wrap(QUBIT_CENTRAL_PROCESSING_UNIT, WRAP_QUBIT_CPU_CHIP)
        wrap(SIMPLE_SYSTEM_ON_CHIP, WRAP_SIMPLE_SOC_CHIP)
        wrap(SYSTEM_ON_CHIP, WRAP_SOC_CHIP)
        wrap(ADVANCED_SYSTEM_ON_CHIP, WRAP_ADVANCED_SOC_CHIP)
        wrap(HIGHLY_ADVANCED_SOC, WRAP_HIGHLY_ADVANCED_SOC_CHIP)
        wrap(NAND_MEMORY_CHIP, WRAP_NAND_CHIP)
        wrap(NOR_MEMORY_CHIP, WRAP_NOR_CHIP)
        wrap(ULTRA_LOW_POWER_INTEGRATED_CIRCUIT, WRAP_ULPIC_CHIP)
        wrap(LOW_POWER_INTEGRATED_CIRCUIT, WRAP_LPIC_CHIP)
        wrap(POWER_INTEGRATED_CIRCUIT, WRAP_PIC_CHIP)
        wrap(HIGH_POWER_INTEGRATED_CIRCUIT, WRAP_HPIC_CHIP)
        wrap(ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT, WRAP_UHPIC_CHIP)
        wrap(NANO_PIC_CHIP, WRAP_NPIC_CHIP)
        wrap(PICO_PIC_CHIP, WRAP_PPIC_CHIP)
        wrap(FEMTO_PIC_CHIP, WRAP_FPIC_CHIP)
        wrap(ATTO_PIC_CHIP, WRAP_APIC_CHIP)
        wrap(ADVANCED_RAM_CHIP, WRAP_ARAM_CHIP)
        wrap(ULTRA_HIGHLY_ADVANCED_SOC_CHIP, WRAP_UHASOC_CHIP)
        wrap(CRYSTAL_INTERFACE_CHIP, WRAP_CRYSTAL_INTERFACE_CHIP)
        wrap(PHASE_CHANGE_RAM_CHIP, WRAP_PRAM_CHIP)
        wrap(ALL_OPTICAL_CASCADE_NOR_CHIP, WRAP_ACNOR_CHIP)
        wrap(SPIN_TRANSFER_TORQUE_RAM_CHIP, WRAP_STTRAM_CHIP)
        wrap(MAGNETIC_DOMAIN_WALL_INVERSION_NAND_CHIP, WRAP_MINAND_CHIP)
        wrap(EXCITED_EXOTIC_ATOM_RESTRICT_CPU_CHIP, WRAP_EARCPU_CHIP)
        wrap(AMPLITUDE_DUALITY_DISTURBANCE_RAM_CHIP, WRAP_ADDRAM_CHIP)
        wrap(EXCITATION_SPECTRUM_COMPOSITE_LOGICAL_GATE_CHIP, WRAP_ESCLG_CHIP)

        // Engraved Chips
        wrap(ENGRAVED_LAPOTRON_CHIP, WRAP_ENGRAVED_LAPOTRON_CHIP)
        wrap(ENGRAVED_DIAMOND_CHIP, WRAP_ENGRAVED_DIAMOND_CHIP)
        wrap(ENGRAVED_RUBY_CHIP, WRAP_ENGRAVED_RUBY_CHIP)
        wrap(ENGRAVED_SAPPHIRE_CHIP, WRAP_ENGRAVED_SAPPHIRE_CHIP)

        // Circuit Components
        wrap(CRYSTAL_CENTRAL_PROCESSING_UNIT, WRAP_CRYSTAL_CPU)
        wrap(CRYSTAL_SYSTEM_ON_CHIP, WRAP_CRYSTAL_SOC)
        wrap(NEURO_PROCESSOR, WRAP_NEURO_PROCESSOR)
        wrap(NONLINEAR_CHEMICAL_OSCILLATOR, WRAP_NONLINEAR_CHEMICAL_OSCILLATOR)
        wrap(OPTICAL_LASER_CONTROL_UNIT, WRAP_OPTICAL_LASER_CONTROL_UNIT)
        wrap(OPTOELECTRONIC_SYSTEM_ON_CHIP, WRAP_OPTOELECTRONIC_SYSTEM_ON_CHIP)
        wrap(ESR_COMPUTATION_UNIT, WRAP_ESR_COMPUTATION_UNIT)
        wrap(EXOTIC_SYSTEM_ON_CHIP, WRAP_EXOTIC_SYSTEM_ON_CHIP)
        wrap(COSMIC_INFORMATION_MODULE, WRAP_COSMIC_INFORMATION_MODULE)
        wrap(HOLOGRAPHIC_INFORMATION_IMC, WRAP_HOLOGRAPHIC_INFORMATION_IMC)
    }
}