package magicbook.gtlitecore.api.recipe.property.storage

import gregtech.common.items.MetaItems.CRYSTAL_ASSEMBLY_LUV
import gregtech.common.items.MetaItems.CRYSTAL_COMPUTER_ZPM
import gregtech.common.items.MetaItems.CRYSTAL_MAINFRAME_UV
import gregtech.common.items.MetaItems.CRYSTAL_PROCESSOR_IV
import gregtech.common.items.MetaItems.ELECTRONIC_CIRCUIT_LV
import gregtech.common.items.MetaItems.ELECTRONIC_CIRCUIT_MV
import gregtech.common.items.MetaItems.ENERGY_LAPOTRONIC_ORB
import gregtech.common.items.MetaItems.INTEGRATED_CIRCUIT_HV
import gregtech.common.items.MetaItems.INTEGRATED_CIRCUIT_LV
import gregtech.common.items.MetaItems.INTEGRATED_CIRCUIT_MV
import gregtech.common.items.MetaItems.MAINFRAME_IV
import gregtech.common.items.MetaItems.MICROPROCESSOR_LV
import gregtech.common.items.MetaItems.NAND_CHIP_ULV
import gregtech.common.items.MetaItems.NANO_COMPUTER_IV
import gregtech.common.items.MetaItems.NANO_MAINFRAME_LUV
import gregtech.common.items.MetaItems.NANO_PROCESSOR_ASSEMBLY_EV
import gregtech.common.items.MetaItems.NANO_PROCESSOR_HV
import gregtech.common.items.MetaItems.PROCESSOR_ASSEMBLY_HV
import gregtech.common.items.MetaItems.PROCESSOR_MV
import gregtech.common.items.MetaItems.QUANTUM_ASSEMBLY_IV
import gregtech.common.items.MetaItems.QUANTUM_COMPUTER_LUV
import gregtech.common.items.MetaItems.QUANTUM_MAINFRAME_ZPM
import gregtech.common.items.MetaItems.QUANTUM_PROCESSOR_EV
import gregtech.common.items.MetaItems.WORKSTATION_EV
import magicbook.gtlitecore.api.recipe.property.CircuitPatternProperty
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.CRYSTAL_SOC_SOCKET
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.DIAMOND_MODULATOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.RUBY_MODULATOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SAPPHIRE_MODULATOR
import magicbook.gtlitecore.loader.recipe.producer.CircuitAssemblyLineRecipeProducer.Companion.getCircuit

class CircuitPatternPropertyStorage
{

    companion object
    {

        fun loadProperties()
        {
            // T1: Electronic
            CircuitPatternProperty.register(getCircuit(ELECTRONIC_CIRCUIT_LV))
            CircuitPatternProperty.register(getCircuit(ELECTRONIC_CIRCUIT_MV))

            // T2: Integrated
            CircuitPatternProperty.register(getCircuit(INTEGRATED_CIRCUIT_LV))
            CircuitPatternProperty.register(getCircuit(INTEGRATED_CIRCUIT_MV))
            CircuitPatternProperty.register(getCircuit(INTEGRATED_CIRCUIT_HV))

            // T3: Processor
            CircuitPatternProperty.register(getCircuit(NAND_CHIP_ULV))
            CircuitPatternProperty.register(getCircuit(MICROPROCESSOR_LV))
            CircuitPatternProperty.register(getCircuit(PROCESSOR_MV))
            CircuitPatternProperty.register(getCircuit(PROCESSOR_ASSEMBLY_HV))
            CircuitPatternProperty.register(getCircuit(WORKSTATION_EV))
            CircuitPatternProperty.register(getCircuit(MAINFRAME_IV))

            // T4: Nano
            CircuitPatternProperty.register(getCircuit(NANO_PROCESSOR_HV))
            CircuitPatternProperty.register(getCircuit(NANO_PROCESSOR_ASSEMBLY_EV))
            CircuitPatternProperty.register(getCircuit(NANO_COMPUTER_IV))
            CircuitPatternProperty.register(getCircuit(NANO_MAINFRAME_LUV))

            // T5: Quantum
            CircuitPatternProperty.register(getCircuit(QUANTUM_PROCESSOR_EV))
            CircuitPatternProperty.register(getCircuit(QUANTUM_ASSEMBLY_IV))
            CircuitPatternProperty.register(getCircuit(QUANTUM_COMPUTER_LUV))
            CircuitPatternProperty.register(getCircuit(QUANTUM_MAINFRAME_ZPM))

            // T6: Crystal
            CircuitPatternProperty.register(getCircuit(CRYSTAL_PROCESSOR_IV))
            CircuitPatternProperty.register(getCircuit(CRYSTAL_ASSEMBLY_LUV))
            CircuitPatternProperty.register(getCircuit(CRYSTAL_COMPUTER_ZPM))
            CircuitPatternProperty.register(getCircuit(CRYSTAL_MAINFRAME_UV))

            // T7: Wetware

            // T8: Gooware

            // T9: Optical

            // T10: Spintronic

            // T11: Cosmic

            // T12: Supracausal

            // Applied Energistics 2 Processors

            // Open Computers Circuits

            // Other Component Circuits
            CircuitPatternProperty.register(getCircuit(ENERGY_LAPOTRONIC_ORB))
            CircuitPatternProperty.register(getCircuit(DIAMOND_MODULATOR))
            CircuitPatternProperty.register(getCircuit(RUBY_MODULATOR))
            CircuitPatternProperty.register(getCircuit(SAPPHIRE_MODULATOR))
            CircuitPatternProperty.register(getCircuit(CRYSTAL_SOC_SOCKET))

        }

    }

}