package magicbook.gtlitecore.api.recipe.property.storage

import gregtech.common.items.MetaItems.ELECTRONIC_CIRCUIT_LV
import gregtech.common.items.MetaItems.ELECTRONIC_CIRCUIT_MV
import gregtech.common.items.MetaItems.INTEGRATED_CIRCUIT_HV
import gregtech.common.items.MetaItems.INTEGRATED_CIRCUIT_LV
import gregtech.common.items.MetaItems.INTEGRATED_CIRCUIT_MV
import magicbook.gtlitecore.api.recipe.property.CircuitPatternProperty
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

            // T4: Nano

            // T5: Quantum

            // T6: Crystal

            // T7: Wetware

            // T8: Gooware

            // T9: Optical

            // T10: Spintronic

            // T11: Cosmic

            // T12: Supracausal

            // Applied Energistics 2 Processors

            // Open Computers Circuits

            // Other Component Circuits
        }

    }

}