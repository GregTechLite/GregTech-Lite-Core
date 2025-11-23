package gregtechlite.gtlitecore.client.renderer.texture

import gregtech.client.renderer.texture.custom.DrumRenderer
import gregtechlite.gtlitecore.client.renderer.texture.custom.ExtenderRenderer
import gregtechlite.gtlitecore.client.util.orientedOverlay
import gregtechlite.gtlitecore.client.util.simpleOverlay
import gregtechlite.gtlitecore.client.util.simpleSidedCubeOverlay

@Suppress("SameParameterValue")
object GTLiteOverlays
{

    // region Single Machine Overlay Textures

    @JvmField
    val POLISHER_OVERLAY = singleId("polisher")
    @JvmField
    val SLICER_OVERLAY = singleId("slicer")
    @JvmField
    val TOOL_CASTER_OVERLAY = singleId("tool_caster")
    @JvmField
    val LOOM_OVERLAY = singleId("loom")
    @JvmField
    val LAMINATOR_OVERLAY = singleId("laminator")
    @JvmField
    val CHEMICAL_DEHYDRATOR_OVERLAY = singleId("chemical_dehydrator")
    @JvmField
    val VULCANIZING_PRESS_OVERLAY = singleId("vulcanizing_press")
    @JvmField
    val SAP_COLLECTOR_OVERLAY = singleId("sap_collector")
    @JvmField
    val BIO_REACTOR_OVERLAY = singleId("bio_reactor")
    @JvmField
    val ROASTER_OVERLAY = singleId("roaster")
    @JvmField
    val BURNER_REACTOR_OVERLAY = singleId("burner_reactor")
    @JvmField
    val BATH_CONDENSER_OVERLAY = singleId("bath_condenser")
    @JvmField
    val CRYOGENIC_REACTOR_OVERLAY = singleId("cryogenic_reactor")
    @JvmField
    val FOOD_PROCESSOR_OVERLAY = singleId("food_processor")
    @JvmField
    val MULTICOOKER_OVERLAY = singleId("multicooker")
    @JvmField
    val MOB_EXTRACTOR_OVERLAY = singleId("mob_extractor")
    @JvmField
    val BIO_SIMULATOR_OVERLAY = singleId("bio_simulator")
    @JvmField
    val ROCKET_ENGINE_OVERLAY = singleId("rocket_engine")
    @JvmField
    val NAQUADAH_REACTOR_OVERLAY = singleId("naquadah_reactor")
    @JvmField
    val ACID_GENERATOR_OVERLAY = singleId("acid_generator")

    // endregion

    // region Multiblock Machine Overlay Textures

    @JvmField
    val ADVANCED_FUSION_REACTOR_OVERLAY = multiId("advanced_fusion_reactor")
    @JvmField
    val ALLOY_BLAST_SMELTER_OVERLAY = multiId("alloy_blast_smelter")
    @JvmField
    val ANTIMATTER_FORGE_OVERLAY = multiId("antimatter_forge")
    @JvmField
    val COAGULATION_TANK_OVERLAY = multiId("coagulation_tank")
    @JvmField
    val COSMIC_RAY_DETECTOR_OVERLAY = multiId("cosmic_ray_detector")
    @JvmField
    val CRYSTALLIZATION_CRUCIBLE_OVERLAY = multiId("crystallization_crucible")
    @JvmField
    val CVD_UNIT_OVERLAY = multiId("cvd_unit")
    @JvmField
    val ENTRODYNAMICALLY_PHASE_CHANGER_OVERLAY = multiId("entrodynamically_phase_changer")
    @JvmField
    val INDUSTRIAL_COKE_OVEN_OVERLAY = multiId("industrial_coke_oven")
    @JvmField
    val LARGE_ARC_FURNACE_OVERLAY = multiId("large_arc_furnace")
    @JvmField
    val LARGE_AUTOCLAVE_OVERLAY = multiId("large_autoclave")
    @JvmField
    val LARGE_BREWERY_OVERLAY = multiId("large_brewery")
    @JvmField
    val LARGE_BURNER_REACTOR_OVERLAY = multiId("large_burner_reactor")
    @JvmField
    val LARGE_CRYOGENIC_REACTOR_OVERLAY = multiId("large_cryogenic_reactor")
    @JvmField
    val LARGE_ELECTROLYZER_OVERLAY = multiId("large_electrolyzer")
    @JvmField
    val LARGE_ELECTROMAGNET_OVERLAY = multiId("large_electromagnet")
    @JvmField
    val LARGE_MACERATOR_OVERLAY = multiId("large_macerator")
    @JvmField
    val LARGE_ORE_WASHER_OVERLAY = multiId("large_ore_washer")
    @JvmField
    val LARGE_ROCKET_ENGINE_OVERLAY = multiId("large_rocket_engine")
    @JvmField
    val LARGE_SIFTER_OVERLAY = multiId("large_sifter")
    @JvmField
    val LARGE_STEAM_COMPRESSOR_OVERLAY = multiId("large_steam_compressor")
    @JvmField
    val MINING_DRONE_AIRPORT_OVERLAY = multiId("mining_drone_airport")
    @JvmField
    val NANOSCALE_FABRICATOR_OVERLAY = multiId("nanoscale_fabricator")
    @JvmField
    val NUCLEAR_REACTOR_OVERLAY = multiId("nuclear_reactor")
    @JvmField
    val QUANTUM_FORCE_TRANSFORMER_OVERLAY = multiId("quantum_force_transformer")
    @JvmField
    val SONICATOR_OVERLAY = multiId("sonicator")
    @JvmField
    val SPACE_ELEVATOR_OVERLAY = multiId("space_elevator")
    @JvmField
    val STELLAR_FORGE_OVERLAY = multiId("stellar_forge")
    @JvmField
    val PLASMA_ARC_TRANSMITTER_OVERLAY = multiId("plasma_arc_transmitter")
    @JvmField
    val EP_COUPLING_ACCELERATOR_OVERLAY = multiId("ep_coupling_accelerator")
    @JvmField
    val NANO_ASSEMBLY_COMPLEX_OVERLAY = multiId("nano_assembly_complex")

    // endregion

    // region Multiblock Part Overlay Textures

    @JvmField
    val ANTIMATTER_FORGE_TEXTURE = partId("antimatter_overlay")
    @JvmField
    val ANTIMATTER_FORGE_ACTIVE_TEXTURE = partId("antimatter_overlay_active")
    @JvmField
    val AIR_INTAKE_HATCH_OVERLAY = partId("air_intake_hatch_overlay")
    @JvmField
    val DUAL_HATCH_INPUT_OVERLAY = partId("dual_hatch_input_overlay")
    @JvmField
    val DUAL_HATCH_OUTPUT_OVERLAY = partId("dual_hatch_output_overlay")
    @JvmField
    val SPACE_ASSEMBLER_OVERLAY = partId("space_assembler_overlay")
    @JvmField
    val SPACE_PUMP_OVERLAY = partId("space_pump_overlay")
    @JvmField
    val STERILE_CLEANING_MAINTENANCE_OVERLAY = partId("sterile_cleaning_maintenance_hatch_overlay")

    // endregion

    // region Machine Casing Textures

    @JvmField
    val REINFORCED_TREATED_WOOD_WALL = casingId("primitive/reinforced_treated_wood_wall")
    @JvmField
    val MARAGING_STEEL_250_CASING = casingId("metal/maraging_steel_250")
    @JvmField
    val INCONEL_625_CASING = casingId("metal/inconel_625")
    @JvmField
    val BLUE_STEEL_CASING = casingId("metal/blue_steel")
    @JvmField
    val STABALLOY_CASING = casingId("metal/staballoy")
    @JvmField
    val TALONITE_CASING = casingId("metal/talonite")
    @JvmField
    val IRIDIUM_CASING = casingId("metal/iridium")
    @JvmField
    val ZERON_100_CASING = casingId("metal/zeron_100")
    @JvmField
    val WATERTIGHT_STEEL_CASING = casingId("metal/watertight_steel")
    @JvmField
    val STELLITE_CASING = casingId("metal/stellite")
    @JvmField
    val TUMBAGA_CASING = casingId("metal/tumbaga")
    @JvmField
    val EGLIN_STEEL_CASING = casingId("metal/eglin_steel")
    @JvmField
    val POTIN_CASING = casingId("metal/potin")
    @JvmField
    val GRISIUM_CASING = casingId("metal/grisium")
    @JvmField
    val BABBIT_ALLOY_CASING = casingId("metal/babbit_alloy")
    @JvmField
    val SILICON_CARBIDE_CASING = casingId("metal/silicon_carbide")
    @JvmField
    val RED_STEEL_CASING = casingId("metal/red_steel")
    @JvmField
    val HSLA_STEEL_CASING = casingId("metal/hsla_steel")
    @JvmField
    val KOVAR_CASING = casingId("metal/kovar")
    @JvmField
    val BLACK_STEEL_CASING = casingId("metal/black_steel")
    @JvmField
    val INCOLOY_MA813_CASING = casingId("metal/incoloy_ma813")
    @JvmField
    val MONEL_500_CASING = casingId("metal/monel_500")
    @JvmField
    val INCOLOY_MA956_CASING = casingId("metal/incoloy_ma956")
    @JvmField
    val ZIRCONIUM_CARBIDE_CASING = casingId("metal/zirconium_carbide")
    @JvmField
    val HASTELLOY_C276_CASING = casingId("metal/hastelloy_c276")
    @JvmField
    val HASTELLOY_X_CASING = casingId("metal/hastelloy_x")
    @JvmField
    val POLYBENZIMIDAZOLE_CASING = casingId("metal/polybenzimidazole")
    @JvmField
    val ALUMINIUM_BRONZE_CASING = casingId("metal/aluminium_bronze")
    @JvmField
    val HASTELLOY_N_CASING = casingId("metal/hastelloy_n")
    @JvmField
    val RENE_N5_CASING = casingId("metal/rene_n5")
    @JvmField
    val BISMUTH_BRONZE_CASING = casingId("metal/bismuth_bronze")
    @JvmField
    val BRASS_CASING = casingId("metal/brass")
    @JvmField
    val TITANIUM_TUNGSTEN_CARBIDE_CASING = casingId("metal/titanium_tungsten_carbide")
    @JvmField
    val COBALT_BRASS_CASING = casingId("metal/cobalt_brass")
    @JvmField
    val QUANTUM_ALLOY_CASING = casingId("metal/quantum_alloy")
    @JvmField
    val INCONEL_718_CASING = casingId("metal/inconel_718")
    @JvmField
    val RHODIUM_PLATED_PALLADIUM_CASING = casingId("metal/rhodium_plated_palladium")
    @JvmField
    val TRINAQUADALLOY_CASING = casingId("metal/trinaquadalloy")
    @JvmField
    val NITINOL_60_CASING = casingId("metal/nitinol_60")
    @JvmField
    val NAQUADAH_ALLOY_CASING = casingId("metal/naquadah_alloy")
    @JvmField
    val PARTICLE_CONTAINMENT_CASING = casingId("quantum/particle_containment_casing")
    @JvmField
    val SPACE_ELEVATOR_BASE_CASING = casingId("aerospace/elevator_base_casing")
    @JvmField
    val MOLECULAR_CASING = casingId("science/molecular_casing")
    @JvmField
    val LATTICE_QCD_THERMAL_SHIELDING_CASING = casingId("entropy/lattice_qcd_thermal_shielding_casing")
    @JvmField
    val ULTIMATE_MOLECULAR_CASING = casingId("science/ultimate_molecular_casing")
    @JvmField
    val LAFIUM_CASING = casingId("metal/lafium")
    @JvmField
    val NEUTRONIUM_CASING = casingId("metal/neutronium")

    // endregion

    // region Cover Textures
    @JvmField
    val COVER_DRAIN = coverId("cover_drain")

    // endregion

    // region Special Renderer Textures
    @JvmField
    val PLASTIC_CAN_OVERLAY = simpleSidedCubeOverlay("storage/drums/plastic_can_top")
    @JvmField
    val PLASTIC_CAN = DrumRenderer("storage/drums/plastic_can")
    @JvmField
    val INVENTORY_BRIDGE = simpleSidedCubeOverlay("storage/bridges/inventory")
    @JvmField
    val TANK_BRIDGE = simpleSidedCubeOverlay("storage/bridges/tank")
    @JvmField
    val INVENTORY_TANK_BRIDGE = simpleSidedCubeOverlay("storage/bridges/inventory_tank")
    @JvmField
    val UNIVERSAL_BRIDGE = simpleSidedCubeOverlay("storage/bridges/universal")
    @JvmField
    val INVENTORY_EXTENDER = ExtenderRenderer("storage/extenders/inventory")
    @JvmField
    val TANK_EXTENDER = ExtenderRenderer("storage/extenders/tank")
    @JvmField
    val INVENTORY_TANK_EXTENDER = ExtenderRenderer("storage/extenders/inventory_tank")
    @JvmField
    val UNIVERSAL_EXTENDER = ExtenderRenderer("storage/extenders/universal")

    // endregion

    // region Helper Methods

    @JvmStatic
    private fun singleId(path: String) = orientedOverlay("machines/$path")

    @JvmStatic
    private fun multiId(path: String) = orientedOverlay("machines/multiblock/$path")

    @JvmStatic
    private fun partId(path: String) = simpleOverlay("machines/part/$path")

    @JvmStatic
    private fun casingId(path: String) = simpleOverlay("casings/$path")

    @JvmStatic
    private fun coverId(path: String) = simpleOverlay("covers/$path")

    // endregion

}