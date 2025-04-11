package magicbook.gtlitecore.loader.recipe.producer

import com.google.common.collect.HashBiMap
import gregtech.api.GTValues.L
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.VA
import gregtech.api.items.metaitem.MetaItem
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.unification.material.Materials.Polyethylene
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
import gregtech.common.items.MetaItems.CRYSTAL_CENTRAL_PROCESSING_UNIT
import gregtech.common.items.MetaItems.CRYSTAL_SYSTEM_ON_CHIP
import gregtech.common.items.MetaItems.ELITE_CIRCUIT_BOARD
import gregtech.common.items.MetaItems.ENGRAVED_LAPOTRON_CHIP
import gregtech.common.items.MetaItems.EPOXY_BOARD
import gregtech.common.items.MetaItems.EXTREME_CIRCUIT_BOARD
import gregtech.common.items.MetaItems.FIBER_BOARD
import gregtech.common.items.MetaItems.GOOD_CIRCUIT_BOARD
import gregtech.common.items.MetaItems.HIGHLY_ADVANCED_SOC
import gregtech.common.items.MetaItems.HIGH_POWER_INTEGRATED_CIRCUIT
import gregtech.common.items.MetaItems.INTEGRATED_LOGIC_CIRCUIT
import gregtech.common.items.MetaItems.LOW_POWER_INTEGRATED_CIRCUIT
import gregtech.common.items.MetaItems.MULTILAYER_FIBER_BOARD
import gregtech.common.items.MetaItems.NAND_MEMORY_CHIP
import gregtech.common.items.MetaItems.NANO_CENTRAL_PROCESSING_UNIT
import gregtech.common.items.MetaItems.NEURO_PROCESSOR
import gregtech.common.items.MetaItems.NOR_MEMORY_CHIP
import gregtech.common.items.MetaItems.PHENOLIC_BOARD
import gregtech.common.items.MetaItems.PLASTIC_BOARD
import gregtech.common.items.MetaItems.PLASTIC_CIRCUIT_BOARD
import gregtech.common.items.MetaItems.POWER_INTEGRATED_CIRCUIT
import gregtech.common.items.MetaItems.QUBIT_CENTRAL_PROCESSING_UNIT
import gregtech.common.items.MetaItems.RANDOM_ACCESS_MEMORY
import gregtech.common.items.MetaItems.SIMPLE_SYSTEM_ON_CHIP
import gregtech.common.items.MetaItems.SMD_CAPACITOR
import gregtech.common.items.MetaItems.SMD_DIODE
import gregtech.common.items.MetaItems.SMD_INDUCTOR
import gregtech.common.items.MetaItems.SMD_RESISTOR
import gregtech.common.items.MetaItems.SMD_TRANSISTOR
import gregtech.common.items.MetaItems.SYSTEM_ON_CHIP
import gregtech.common.items.MetaItems.ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT
import gregtech.common.items.MetaItems.ULTRA_LOW_POWER_INTEGRATED_CIRCUIT
import gregtech.common.items.MetaItems.WETWARE_BOARD
import gregtech.common.items.MetaItems.WETWARE_CIRCUIT_BOARD
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.ADVANCED_RAM_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.ATTO_PIC_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.COSMIC_SMD_CAPACITOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.COSMIC_SMD_DIODE
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.COSMIC_SMD_INDUCTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.COSMIC_SMD_RESISTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.COSMIC_SMD_TRANSISTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.CRYSTAL_INTERFACE_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.ENGRAVED_DIAMOND_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.ENGRAVED_RUBY_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.ENGRAVED_SAPPHIRE_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.FEMTO_PIC_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.GOOWARE_BOARD
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.GOOWARE_SMD_CAPACITOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.GOOWARE_SMD_DIODE
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.GOOWARE_SMD_INDUCTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.GOOWARE_SMD_RESISTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.GOOWARE_SMD_TRANSISTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.INFINITE_CIRCUIT_BOARD
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.NANO_PIC_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.NONLINEAR_CHEMICAL_OSCILLATOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.OPTICAL_BOARD
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.OPTICAL_SMD_CAPACITOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.OPTICAL_SMD_DIODE
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.OPTICAL_SMD_INDUCTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.OPTICAL_SMD_RESISTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.OPTICAL_SMD_TRANSISTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.PERFECT_CIRCUIT_BOARD
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.PICO_PIC_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SPINTRONIC_BOARD
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SPINTRONIC_SMD_CAPACITOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SPINTRONIC_SMD_DIODE
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SPINTRONIC_SMD_INDUCTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SPINTRONIC_SMD_RESISTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SPINTRONIC_SMD_TRANSISTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SUPRACAUSAL_SMD_CAPACITOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SUPRACAUSAL_SMD_DIODE
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SUPRACAUSAL_SMD_INDUCTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SUPRACAUSAL_SMD_RESISTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SUPRACAUSAL_SMD_TRANSISTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.ULTIMATE_CIRCUIT_BOARD
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.ULTRA_HIGHLY_ADVANCED_SOC_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_ADVANCED_CIRCUIT_BOARD
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_ADVANCED_SMD_CAPACITOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_ADVANCED_SMD_DIODE
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_ADVANCED_SMD_INDUCTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_ADVANCED_SMD_RESISTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_ADVANCED_SMD_TRANSISTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_ADVANCED_SOC_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_APIC_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_ARAM_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_BASIC_CIRCUIT_BOARD
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_COATED_BOARD
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_COSMIC_SMD_CAPACITOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_COSMIC_SMD_DIODE
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_COSMIC_SMD_INDUCTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_COSMIC_SMD_RESISTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_COSMIC_SMD_TRANSISTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_CPU_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_CRYSTAL_CPU
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_CRYSTAL_INTERFACE_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_CRYSTAL_SOC
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_ELITE_CIRCUIT_BOARD
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_ENGRAVED_DIAMOND_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_ENGRAVED_LAPOTRON_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_ENGRAVED_RUBY_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_ENGRAVED_SAPPHIRE_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_EPOXY_BOARD
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_EXTREME_CIRCUIT_BOARD
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_FIBER_BOARD
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_FPIC_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_GOOD_CIRCUIT_BOARD
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_GOOWARE_BOARD
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_GOOWARE_SMD_CAPACITOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_GOOWARE_SMD_DIODE
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_GOOWARE_SMD_INDUCTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_GOOWARE_SMD_RESISTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_GOOWARE_SMD_TRANSISTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_HIGHLY_ADVANCED_SOC_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_HPIC_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_ILC_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_INFINITE_CIRCUIT_BOARD
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_LPIC_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_MULTILAYER_FIBER_BOARD
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_NAND_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_NANO_CPU_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_NEURO_PROCESSOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_NONLINEAR_CHEMICAL_OSCILLATOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_NOR_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_NPIC_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_OPTICAL_BOARD
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_OPTICAL_SMD_CAPACITOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_OPTICAL_SMD_DIODE
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_OPTICAL_SMD_INDUCTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_OPTICAL_SMD_RESISTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_OPTICAL_SMD_TRANSISTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_PERFECT_CIRCUIT_BOARD
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_PHENOLIC_BOARD
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_PIC_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_PLASTIC_BOARD
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_PLASTIC_CIRCUIT_BOARD
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_PPIC_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_QUBIT_CPU_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_RAM_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_SIMPLE_SOC_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_SMD_CAPACITOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_SMD_DIODE
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_SMD_INDUCTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_SMD_RESISTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_SMD_TRANSISTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_SOC_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_SPINTRONIC_BOARD
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_SPINTRONIC_SMD_CAPACITOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_SPINTRONIC_SMD_DIODE
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_SPINTRONIC_SMD_INDUCTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_SPINTRONIC_SMD_RESISTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_SPINTRONIC_SMD_TRANSISTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_SUPRACAUSAL_SMD_CAPACITOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_SUPRACAUSAL_SMD_DIODE
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_SUPRACAUSAL_SMD_INDUCTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_SUPRACAUSAL_SMD_RESISTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_SUPRACAUSAL_SMD_TRANSISTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_UHASOC_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_UHPIC_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_ULPIC_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_ULTIMATE_CIRCUIT_BOARD
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_WETWARE_BOARD
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_WETWARE_CIRCUIT_BOARD


@Suppress("MISSING_DEPENDENCY_CLASS")
class WrapItemRecipeProducer
{

    companion object
    {

        private val wrapItems: HashBiMap<MetaItem<*>.MetaValueItem, MetaItem<*>.MetaValueItem> = HashBiMap.create()

        fun produce()
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

            // Add all Wrap Items.
            wrapItems.forEach { (originalItem, wrapItem) -> addRecipe(originalItem, wrapItem) }
        }

        private fun addRecipe(originalItem: MetaItem<*>.MetaValueItem, wrappedItem: MetaItem<*>.MetaValueItem)
        {
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(16)
                .input(originalItem, 16)
                .fluidInputs(Polyethylene.getFluid(L / 2))
                .output(wrappedItem)
                .EUt(VA[LV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()
        }

    }

}