package gregtechlite.gtlitecore.api.recipe

import gregtech.api.GregTechAPI
import gregtech.api.recipes.properties.RecipeProperty
import gregtech.api.recipes.properties.impl.FusionEUToStartProperty
import gregtech.common.items.MetaItems
import gregtechlite.gtlitecore.GTLiteMod
import gregtechlite.gtlitecore.api.recipe.util.createDataItemWithTag
import gregtechlite.gtlitecore.api.recipe.property.CircuitPatternProperty
import gregtechlite.gtlitecore.api.recipe.property.RecipePropertyBuilder
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems
import net.minecraft.client.resources.I18n

object GTLiteRecipeProperties
{

    lateinit var ACCELERATION_TRACK_TIER: RecipeProperty<Int>
    lateinit var ADVANCED_FUSION_TIER: RecipeProperty<Int>
    lateinit var COMPONENT_ASSEMBLY_LINE_TIER: RecipeProperty<Int>
    lateinit var MINIMUM_HEIGHT: RecipeProperty<Int>
    lateinit var NANO_FORGE_TIER: RecipeProperty<Int>
    lateinit var NO_COIL_TEMPERATURE: RecipeProperty<Int>
    lateinit var PCB_FACTORY_BIO_CHAMBER_UPGRADE: RecipeProperty<Int>
    lateinit var PCB_FACTORY_TIER: RecipeProperty<Int>
    lateinit var QUANTUM_FORCE_TRANSFORMER_TIER: RecipeProperty<Int>

    internal fun init()
    {

        RecipePropertyBuilder.Companion.apply {

            ACCELERATION_TRACK_TIER = tierProperty(GTLiteMod.id("acceleration_track_tier"))
            COMPONENT_ASSEMBLY_LINE_TIER = tierProperty(GTLiteMod.id("component_assembly_line_tier"))
            QUANTUM_FORCE_TRANSFORMER_TIER = tierProperty(GTLiteMod.id("quantum_force_transformer_tier"))
            NANO_FORGE_TIER = tierProperty(GTLiteMod.id("nano_forge_tier"))
            ADVANCED_FUSION_TIER = tierProperty(GTLiteMod.id("advanced_fusion_tier"))

            MINIMUM_HEIGHT = intProperty("min_height") {
                infoSupplier = {
                    listOf(I18n.format("gtlitecore.recipe.min_height", it))
                }
            }

            NO_COIL_TEMPERATURE = intProperty("no_coil_temperature") {
                infoSupplier = {
                    listOf(I18n.format("gtlitecore.recipe.temperature", it))
                }
            }

            PCB_FACTORY_TIER = tierProperty(GTLiteMod.id("pcb_factory_tier"))
            PCB_FACTORY_BIO_CHAMBER_UPGRADE = tierProperty(GTLiteMod.id("pcb_factory_auxiliary_tier"))
        }

        FusionEUToStartProperty.getInstance().apply {
            FusionEUToStartProperty.registerFusionTier(9, "(MK4)")
            FusionEUToStartProperty.registerFusionTier(10, "(MK5)")
        }

        register(CircuitPatternProperty) {
            // T1: Electronic
            CircuitPatternProperty.registerCircuit(createDataItemWithTag(MetaItems.ELECTRONIC_CIRCUIT_LV))
            CircuitPatternProperty.registerCircuit(createDataItemWithTag(MetaItems.ELECTRONIC_CIRCUIT_MV))

            // T2: Integrated
            CircuitPatternProperty.registerCircuit(createDataItemWithTag(MetaItems.INTEGRATED_CIRCUIT_LV))
            CircuitPatternProperty.registerCircuit(createDataItemWithTag(MetaItems.INTEGRATED_CIRCUIT_MV))
            CircuitPatternProperty.registerCircuit(createDataItemWithTag(MetaItems.INTEGRATED_CIRCUIT_HV))

            // T3: Processor
            CircuitPatternProperty.registerCircuit(createDataItemWithTag(MetaItems.NAND_CHIP_ULV))
            CircuitPatternProperty.registerCircuit(createDataItemWithTag(MetaItems.MICROPROCESSOR_LV))
            CircuitPatternProperty.registerCircuit(createDataItemWithTag(MetaItems.PROCESSOR_MV))
            CircuitPatternProperty.registerCircuit(createDataItemWithTag(MetaItems.PROCESSOR_ASSEMBLY_HV))
            CircuitPatternProperty.registerCircuit(createDataItemWithTag(MetaItems.WORKSTATION_EV))
            CircuitPatternProperty.registerCircuit(createDataItemWithTag(MetaItems.MAINFRAME_IV))

            // T4: Nano
            CircuitPatternProperty.registerCircuit(createDataItemWithTag(MetaItems.NANO_PROCESSOR_HV))
            CircuitPatternProperty.registerCircuit(createDataItemWithTag(MetaItems.NANO_PROCESSOR_ASSEMBLY_EV))
            CircuitPatternProperty.registerCircuit(createDataItemWithTag(MetaItems.NANO_COMPUTER_IV))
            CircuitPatternProperty.registerCircuit(createDataItemWithTag(MetaItems.NANO_MAINFRAME_LUV))

            // T5: Quantum
            CircuitPatternProperty.registerCircuit(createDataItemWithTag(MetaItems.QUANTUM_PROCESSOR_EV))
            CircuitPatternProperty.registerCircuit(createDataItemWithTag(MetaItems.QUANTUM_ASSEMBLY_IV))
            CircuitPatternProperty.registerCircuit(createDataItemWithTag(MetaItems.QUANTUM_COMPUTER_LUV))
            CircuitPatternProperty.registerCircuit(createDataItemWithTag(MetaItems.QUANTUM_MAINFRAME_ZPM))

            // T6: Crystal
            CircuitPatternProperty.registerCircuit(createDataItemWithTag(MetaItems.CRYSTAL_PROCESSOR_IV))
            CircuitPatternProperty.registerCircuit(createDataItemWithTag(MetaItems.CRYSTAL_ASSEMBLY_LUV))
            CircuitPatternProperty.registerCircuit(createDataItemWithTag(MetaItems.CRYSTAL_COMPUTER_ZPM))
            CircuitPatternProperty.registerCircuit(createDataItemWithTag(MetaItems.CRYSTAL_MAINFRAME_UV))

            // T7: Wetware
            CircuitPatternProperty.registerCircuit(createDataItemWithTag(MetaItems.WETWARE_PROCESSOR_LUV))
            CircuitPatternProperty.registerCircuit(createDataItemWithTag(MetaItems.WETWARE_PROCESSOR_ASSEMBLY_ZPM))
            CircuitPatternProperty.registerCircuit(createDataItemWithTag(MetaItems.WETWARE_SUPER_COMPUTER_UV))
            CircuitPatternProperty.registerCircuit(createDataItemWithTag(MetaItems.WETWARE_MAINFRAME_UHV))

            // T8: Gooware
            CircuitPatternProperty.registerCircuit(createDataItemWithTag(GTLiteMetaItems.GOOWARE_PROCESSOR_ZPM))
            CircuitPatternProperty.registerCircuit(createDataItemWithTag(GTLiteMetaItems.GOOWARE_ASSEMBLY_UV))
            CircuitPatternProperty.registerCircuit(createDataItemWithTag(GTLiteMetaItems.GOOWARE_COMPUTER_UHV))
            CircuitPatternProperty.registerCircuit(createDataItemWithTag(GTLiteMetaItems.GOOWARE_MAINFRAME_UEV))

            // T9: Optical
            CircuitPatternProperty.registerCircuit(createDataItemWithTag(GTLiteMetaItems.OPTICAL_PROCESSOR_UV))
            CircuitPatternProperty.registerCircuit(createDataItemWithTag(GTLiteMetaItems.OPTICAL_ASSEMBLY_UHV))
            CircuitPatternProperty.registerCircuit(createDataItemWithTag(GTLiteMetaItems.OPTICAL_COMPUTER_UEV))
            CircuitPatternProperty.registerCircuit(createDataItemWithTag(GTLiteMetaItems.OPTICAL_MAINFRAME_UIV))

            // T10: Spintronic
            CircuitPatternProperty.registerCircuit(createDataItemWithTag(GTLiteMetaItems.SPINTRONIC_PROCESSOR_UHV))
            CircuitPatternProperty.registerCircuit(createDataItemWithTag(GTLiteMetaItems.SPINTRONIC_ASSEMBLY_UEV))
            CircuitPatternProperty.registerCircuit(createDataItemWithTag(GTLiteMetaItems.SPINTRONIC_COMPUTER_UIV))
            CircuitPatternProperty.registerCircuit(createDataItemWithTag(GTLiteMetaItems.SPINTRONIC_MAINFRAME_UXV))

            // T11: Cosmic
            CircuitPatternProperty.registerCircuit(createDataItemWithTag(GTLiteMetaItems.COSMIC_PROCESSOR_UEV))
            CircuitPatternProperty.registerCircuit(createDataItemWithTag(GTLiteMetaItems.COSMIC_ASSEMBLY_UIV))
            CircuitPatternProperty.registerCircuit(createDataItemWithTag(GTLiteMetaItems.COSMIC_COMPUTER_UXV))
            CircuitPatternProperty.registerCircuit(createDataItemWithTag(GTLiteMetaItems.COSMIC_MAINFRAME_OpV))

            // T12: Supracausal
            CircuitPatternProperty.registerCircuit(createDataItemWithTag(GTLiteMetaItems.SUPRACAUSAL_PROCESSOR_UIV))
            CircuitPatternProperty.registerCircuit(createDataItemWithTag(GTLiteMetaItems.SUPRACAUSAL_ASSEMBLY_UXV))
            CircuitPatternProperty.registerCircuit(createDataItemWithTag(GTLiteMetaItems.SUPRACAUSAL_COMPUTER_OpV))
            CircuitPatternProperty.registerCircuit(createDataItemWithTag(GTLiteMetaItems.SUPRACAUSAL_MAINFRAME_MAX))

            // Other Component Circuits
            CircuitPatternProperty.registerCircuit(createDataItemWithTag(MetaItems.ENERGY_LAPOTRONIC_ORB))
            CircuitPatternProperty.registerCircuit(createDataItemWithTag(GTLiteMetaItems.DIAMOND_MODULATOR))
            CircuitPatternProperty.registerCircuit(createDataItemWithTag(GTLiteMetaItems.RUBY_MODULATOR))
            CircuitPatternProperty.registerCircuit(createDataItemWithTag(GTLiteMetaItems.SAPPHIRE_MODULATOR))
            CircuitPatternProperty.registerCircuit(createDataItemWithTag(GTLiteMetaItems.CRYSTAL_SOC_SOCKET))
            CircuitPatternProperty.registerCircuit(createDataItemWithTag(MetaItems.NEURO_PROCESSOR))
        }

    }

    private fun <T : RecipeProperty<*>> register(property: T, configuration: T.() -> Unit)
    {
        GregTechAPI.RECIPE_PROPERTIES.register(property.key, property)
        configuration(property)
    }

}