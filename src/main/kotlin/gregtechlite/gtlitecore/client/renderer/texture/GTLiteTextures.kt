package gregtechlite.gtlitecore.client.renderer.texture

import codechicken.lib.texture.TextureUtils
import gregtech.client.renderer.texture.custom.DrumRenderer
import gregtechlite.gtlitecore.GTLiteMod
import gregtechlite.gtlitecore.client.renderer.texture.custom.ExtenderRenderer
import gregtechlite.gtlitecore.client.util.orientedOverlay
import gregtechlite.gtlitecore.client.util.sidedCubeOverlay
import gregtechlite.gtlitecore.client.util.simpleOverlay
import gregtechlite.gtlitecore.client.util.simpleSidedCubeOverlay
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.client.renderer.texture.TextureMap

object GTLiteTextures
{

    // region Machine Overlay Textures
    @JvmField
    val POLISHER_OVERLAY = orientedOverlay("machines/polisher")
    @JvmField
    val SLICER_OVERLAY = orientedOverlay("machines/slicer")
    @JvmField
    val TOOL_CASTER_OVERLAY = orientedOverlay("machines/tool_caster")
    @JvmField
    val LOOM_OVERLAY = orientedOverlay("machines/loom")
    @JvmField
    val LAMINATOR_OVERLAY = orientedOverlay("machines/laminator")
    @JvmField
    val CHEMICAL_DEHYDRATOR_OVERLAY = orientedOverlay("machines/chemical_dehydrator")
    @JvmField
    val VULCANIZING_PRESS_OVERLAY = orientedOverlay("machines/vulcanizing_press")
    @JvmField
    val SAP_COLLECTOR_OVERLAY = orientedOverlay("machines/sap_collector")
    @JvmField
    val BIO_REACTOR_OVERLAY = orientedOverlay("machines/bio_reactor")
    @JvmField
    val ROASTER_OVERLAY = orientedOverlay("machines/roaster")
    @JvmField
    val BURNER_REACTOR_OVERLAY = orientedOverlay("machines/burner_reactor")
    @JvmField
    val BATH_CONDENSER_OVERLAY = orientedOverlay("machines/bath_condenser")
    @JvmField
    val CRYOGENIC_REACTOR_OVERLAY = orientedOverlay("machines/cryogenic_reactor")
    @JvmField
    val FOOD_PROCESSOR_OVERLAY = orientedOverlay("machines/food_processor")
    @JvmField
    val MULTICOOKER_OVERLAY = orientedOverlay("machines/multicooker")
    @JvmField
    val MOB_EXTRACTOR_OVERLAY = orientedOverlay("machines/mob_extractor")
    @JvmField
    val LARGE_BREWERY_OVERLAY = orientedOverlay("machines/multiblock/large_brewery")
    @JvmField
    val LARGE_AUTOCLAVE_OVERLAY = orientedOverlay("machines/multiblock/large_autoclave")
    @JvmField
    val LARGE_ARC_FURNACE_OVERLAY = orientedOverlay("machines/multiblock/large_arc_furnace")
    @JvmField
    val LARGE_MACERATOR_OVERLAY = orientedOverlay("machines/multiblock/large_macerator")
    @JvmField
    val LARGE_SIFTER_OVERLAY = orientedOverlay("machines/multiblock/large_sifter")
    @JvmField
    val LARGE_ELECTROLYZER_OVERLAY = orientedOverlay("machines/multiblock/large_electrolyzer")
    @JvmField
    val LARGE_ORE_WASHER_OVERLAY = orientedOverlay("machines/multiblock/large_ore_washer")
    @JvmField
    val LARGE_ELECTROMAGNET_OVERLAY = orientedOverlay("machines/multiblock/large_electromagnet")
    @JvmField
    val LARGE_BURNER_REACTOR_OVERLAY = orientedOverlay("machines/multiblock/large_burner_reactor")
    @JvmField
    val LARGE_CRYOGENIC_REACTOR_OVERLAY = orientedOverlay("machines/multiblock/large_cryogenic_reactor")
    @JvmField
    val ALLOY_BLAST_SMELTER_OVERLAY = orientedOverlay("machines/multiblock/alloy_blast_smelter")
    @JvmField
    val INDUSTRIAL_COKE_OVEN_OVERLAY = orientedOverlay("machines/multiblock/industrial_coke_oven")
    @JvmField
    val MINING_DRONE_AIRPORT_OVERLAY = orientedOverlay("machines/multiblock/mining_drone_airport")
    @JvmField
    val CVD_UNIT_OVERLAY = orientedOverlay("machines/multiblock/cvd_unit")
    @JvmField
    val CRYSTALLIZATION_CRUCIBLE_OVERLAY = orientedOverlay("machines/multiblock/crystallization_crucible")
    @JvmField
    val NANOSCALE_FABRICATOR_OVERLAY = orientedOverlay("machines/multiblock/nanoscale_fabricator")
    @JvmField
    val SONICATOR_OVERLAY = orientedOverlay("machines/multiblock/sonicator")
    @JvmField
    val ADVANCED_FUSION_REACTOR_OVERLAY = orientedOverlay("machines/multiblock/advanced_fusion_reactor")
    @JvmField
    val COSMIC_RAY_DETECTOR_OVERLAY = orientedOverlay("machines/multiblock/cosmic_ray_detector")
    @JvmField
    val STELLAR_FORGE_OVERLAY = orientedOverlay("machines/multiblock/stellar_forge")
    @JvmField
    val NUCLEAR_REACTOR_OVERLAY = orientedOverlay("machines/multiblock/nuclear_reactor")
    @JvmField
    val BIO_SIMULATOR_OVERLAY = orientedOverlay("machines/bio_simulator")
    @JvmField
    val LARGE_ROCKET_ENGINE_OVERLAY = orientedOverlay("machines/multiblock/large_rocket_engine")
    @JvmField
    val QUANTUM_FORCE_TRANSFORMER_OVERLAY = orientedOverlay("machines/multiblock/quantum_force_transformer")
    @JvmField
    val ANTIMATTER_FORGE_OVERLAY = orientedOverlay("machines/multiblock/antimatter_forge")
    @JvmField
    val SPACE_ELEVATOR_OVERLAY = orientedOverlay("machines/multiblock/space_elevator")
    @JvmField
    val ROCKET_ENGINE_OVERLAY = orientedOverlay("generators/rocket_engine")
    @JvmField
    val NAQUADAH_REACTOR_OVERLAY = orientedOverlay("generators/naquadah_reactor")
    @JvmField
    val SPACE_ASSEMBLER_OVERLAY = simpleOverlay("machines/multiblock/space_elevator/space_assembler/overlay_side")
    @JvmField
    val SPACE_PUMP_OVERLAY = simpleOverlay("machines/multiblock/space_elevator/space_pump/overlay_side")
    @JvmField
    val ENTRODYNAMICALLY_PHASE_CHANGER_OVERLAY = orientedOverlay("machines/multiblock/entrodynamically_phase_changer")

    // endregion

    // region Machine Casing Textures
    @JvmField
    val REINFORCED_TREATED_WOOD_WALL = sidedCubeOverlay("casings/reinforced_treated_wood_wall")

    @JvmField
    val MARAGING_STEEL_250_CASING = simpleOverlay("casings/metal/maraging_steel_250")
    @JvmField
    val INCONEL_625_CASING = simpleOverlay("casings/metal/inconel_625")
    @JvmField
    val BLUE_STEEL_CASING = simpleOverlay("casings/metal/blue_steel")
    @JvmField
    val STABALLOY_CASING = simpleOverlay("casings/metal/staballoy")
    @JvmField
    val TALONITE_CASING = simpleOverlay("casings/metal/talonite")
    @JvmField
    val IRIDIUM_CASING = simpleOverlay("casings/metal/iridium")
    @JvmField
    val ZERON_100_CASING = simpleOverlay("casings/metal/zeron_100")
    @JvmField
    val WATERTIGHT_STEEL_CASING = simpleOverlay("casings/metal/watertight_steel")
    @JvmField
    val STELLITE_CASING = simpleOverlay("casings/metal/stellite")
    @JvmField
    val TUMBAGA_CASING = simpleOverlay("casings/metal/tumbaga")
    @JvmField
    val EGLIN_STEEL_CASING = simpleOverlay("casings/metal/eglin_steel")
    @JvmField
    val POTIN_CASING = simpleOverlay("casings/metal/potin")
    @JvmField
    val GRISIUM_CASING = simpleOverlay("casings/metal/grisium")
    @JvmField
    val BABBIT_ALLOY_CASING = simpleOverlay("casings/metal/babbit_alloy")
    @JvmField
    val SILICON_CARBIDE_CASING = simpleOverlay("casings/metal/silicon_carbide")
    @JvmField
    val RED_STEEL_CASING = simpleOverlay("casings/metal/red_steel")
    @JvmField
    val HSLA_STEEL_CASING = simpleOverlay("casings/metal/hsla_steel")
    @JvmField
    val KOVAR_CASING = simpleOverlay("casings/metal/kovar")
    @JvmField
    val BLACK_STEEL_CASING = simpleOverlay("casings/metal/black_steel")
    @JvmField
    val INCOLOY_MA813_CASING = simpleOverlay("casings/metal/incoloy_ma813")
    @JvmField
    val MONEL_500_CASING = simpleOverlay("casings/metal/monel_500")
    @JvmField
    val INCOLOY_MA956_CASING = simpleOverlay("casings/metal/incoloy_ma956")
    @JvmField
    val ZIRCONIUM_CARBIDE_CASING = simpleOverlay("casings/metal/zirconium_carbide")
    @JvmField
    val HASTELLOY_C276_CASING = simpleOverlay("casings/metal/hastelloy_c276")
    @JvmField
    val HASTELLOY_X_CASING = simpleOverlay("casings/metal/hastelloy_x")
    @JvmField
    val POLYBENZIMIDAZOLE_CASING = simpleOverlay("casings/metal/polybenzimidazole")
    @JvmField
    val ALUMINIUM_BRONZE_CASING = simpleOverlay("casings/metal/aluminium_bronze")
    @JvmField
    val HASTELLOY_N_CASING = simpleOverlay("casings/metal/hastelloy_n")
    @JvmField
    val RENE_N5_CASING = simpleOverlay("casings/metal/rene_n5")
    @JvmField
    val BISMUTH_BRONZE_CASING = simpleOverlay("casings/metal/bismuth_bronze")
    @JvmField
    val BRASS_CASING = simpleOverlay("casings/metal/brass")
    @JvmField
    val TITANIUM_TUNGSTEN_CARBIDE_CASING = simpleOverlay("casings/metal/titanium_tungsten_carbide")
    @JvmField
    val COBALT_BRASS_CASING = simpleOverlay("casings/metal/cobalt_brass")
    @JvmField
    val QUANTUM_ALLOY_CASING = simpleOverlay("casings/metal/quantum_alloy")
    @JvmField
    val INCONEL_718_CASING = simpleOverlay("casings/metal/inconel_718")
    @JvmField
    val RHODIUM_PLATED_PALLADIUM_CASING = simpleOverlay("casings/metal/rhodium_plated_palladium")
    @JvmField
    val TRINAQUADALLOY_CASING = simpleOverlay("casings/metal/trinaquadalloy")
    @JvmField
    val NITINOL_60_CASING = simpleOverlay("casings/metal/nitinol_60")
    @JvmField
    val NAQUADAH_ALLOY_CASING = simpleOverlay("casings/metal/naquadah_alloy")
    @JvmField
    val PARTICLE_CONTAINMENT_CASING = simpleOverlay("casings/quantum/particle_containment_casing")
    @JvmField
    val SPACE_ELEVATOR_BASE_CASING = simpleOverlay("casings/aerospace/elevator_base_casing")
    @JvmField
    val MOLECULAR_CASING = simpleOverlay("casings/science/molecular_casing")
    @JvmField
    val ANTIMATTER_FORGE_TEXTURE = simpleOverlay("machines/multiblock/antimatter_forge/base/antimatter")
    @JvmField
    val ANTIMATTER_FORGE_ACTIVE_TEXTURE = simpleOverlay("machines/multiblock/antimatter_forge/base/antimatter_active")
    @JvmField
    val LATTICE_QCD_THERMAL_SHIELDING_CASING = simpleOverlay("casings/entropy/lattice_qcd_thermal_shielding_casing")

    // endregion

    // region Cover Textures
    @JvmField
    val COVER_DRAIN = simpleOverlay("covers/cover_drain")

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

    // region Atlas Sprite Textures
    lateinit var HALO: TextureAtlasSprite
    lateinit var HALO_NOISE: TextureAtlasSprite
    lateinit var COSMIC: Array<TextureAtlasSprite>
    lateinit var COSMIC_0: TextureAtlasSprite
    lateinit var COSMIC_1: TextureAtlasSprite
    lateinit var COSMIC_2: TextureAtlasSprite
    lateinit var COSMIC_3: TextureAtlasSprite
    lateinit var COSMIC_4: TextureAtlasSprite
    lateinit var COSMIC_5: TextureAtlasSprite
    lateinit var COSMIC_6: TextureAtlasSprite
    lateinit var COSMIC_7: TextureAtlasSprite
    lateinit var COSMIC_8: TextureAtlasSprite
    lateinit var COSMIC_9: TextureAtlasSprite

    lateinit var FORCE_FIELD: TextureAtlasSprite

    // endregion

    fun register(textureMap: TextureMap)
    {
        HALO = textureMap.registerSprite(GTLiteMod.id("shaders/halo"))
        HALO_NOISE = textureMap.registerSprite(GTLiteMod.id("shaders/halo_noise"))
        COSMIC_0 = textureMap.registerSprite(GTLiteMod.id("shaders/cosmic_0"))
        COSMIC_1 = textureMap.registerSprite(GTLiteMod.id("shaders/cosmic_1"))
        COSMIC_2 = textureMap.registerSprite(GTLiteMod.id("shaders/cosmic_2"))
        COSMIC_3 = textureMap.registerSprite(GTLiteMod.id("shaders/cosmic_3"))
        COSMIC_4 = textureMap.registerSprite(GTLiteMod.id("shaders/cosmic_4"))
        COSMIC_5 = textureMap.registerSprite(GTLiteMod.id("shaders/cosmic_5"))
        COSMIC_6 = textureMap.registerSprite(GTLiteMod.id("shaders/cosmic_6"))
        COSMIC_7 = textureMap.registerSprite(GTLiteMod.id("shaders/cosmic_7"))
        COSMIC_8 = textureMap.registerSprite(GTLiteMod.id("shaders/cosmic_8"))
        COSMIC_9 = textureMap.registerSprite(GTLiteMod.id("shaders/cosmic_9"))
        COSMIC = arrayOf(COSMIC_0, COSMIC_1, COSMIC_2, COSMIC_3, COSMIC_4, COSMIC_5, COSMIC_6, COSMIC_7, COSMIC_8,
                         COSMIC_9)

        FORCE_FIELD = textureMap.registerSprite(GTLiteMod.id("shaders/force_field"))
    }

    @JvmStatic
    fun preInit()
    {
        TextureUtils.addIconRegister(GTLiteTextures::register)
    }

}