package magicbook.gtlitecore.client.renderer.texture

import codechicken.lib.texture.TextureUtils
import gregtech.client.renderer.texture.cube.OrientedOverlayRenderer
import gregtech.client.renderer.texture.cube.SidedCubeRenderer
import gregtech.client.renderer.texture.cube.SimpleOverlayRenderer
import gregtech.client.renderer.texture.cube.SimpleSidedCubeRenderer
import gregtech.client.renderer.texture.custom.DrumRenderer
import magicbook.gtlitecore.api.utils.GTLiteUtility
import magicbook.gtlitecore.client.renderer.texture.cube.GTLiteSimpleOverlayRenderer
import magicbook.gtlitecore.client.renderer.texture.custom.ExtenderRenderer
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.client.renderer.texture.TextureMap

class GTLiteTextures
{

    companion object
    {

        @JvmField
        val POLISHER_OVERLAY = OrientedOverlayRenderer("machines/polisher")

        @JvmField
        val SLICER_OVERLAY = OrientedOverlayRenderer("machines/slicer")

        @JvmField
        val TOOL_CASTER_OVERLAY = OrientedOverlayRenderer("machines/tool_caster")

        @JvmField
        val LOOM_OVERLAY = OrientedOverlayRenderer("machines/loom")

        @JvmField
        val LAMINATOR_OVERLAY = OrientedOverlayRenderer("machines/laminator")

        @JvmField
        val CHEMICAL_DEHYDRATOR_OVERLAY = OrientedOverlayRenderer("machines/chemical_dehydrator")

        @JvmField
        val VULCANIZING_PRESS_OVERLAY = OrientedOverlayRenderer("machines/vulcanizing_press")

        @JvmField
        val SAP_COLLECTOR_OVERLAY = OrientedOverlayRenderer("machines/sap_collector")

        @JvmField
        val BIO_REACTOR_OVERLAY = OrientedOverlayRenderer("machines/bio_reactor")

        @JvmField
        val ROASTER_OVERLAY = OrientedOverlayRenderer("machines/roaster")

        @JvmField
        val BURNER_REACTOR_OVERLAY = OrientedOverlayRenderer("machines/burner_reactor")

        @JvmField
        val BATH_CONDENSER_OVERLAY = OrientedOverlayRenderer("machines/bath_condenser")

        @JvmField
        val CRYOGENIC_REACTOR_OVERLAY = OrientedOverlayRenderer("machines/cryogenic_reactor")

        @JvmField
        val FOOD_PROCESSOR_OVERLAY = OrientedOverlayRenderer("machines/food_processor")

        @JvmField
        val MULTICOOKER_OVERLAY = OrientedOverlayRenderer("machines/multicooker")

        @JvmField
        val MOB_EXTRACTOR_OVERLAY = OrientedOverlayRenderer("machines/mob_extractor")

        @JvmField
        val LARGE_BREWERY_OVERLAY = OrientedOverlayRenderer("machines/multiblock/large_brewery")

        @JvmField
        val LARGE_AUTOCLAVE_OVERLAY = OrientedOverlayRenderer("machines/multiblock/large_autoclave")

        @JvmField
        val LARGE_ARC_FURNACE_OVERLAY = OrientedOverlayRenderer("machines/multiblock/large_arc_furnace")

        @JvmField
        val LARGE_MACERATOR_OVERLAY = OrientedOverlayRenderer("machines/multiblock/large_macerator")

        @JvmField
        val LARGE_SIFTER_OVERLAY = OrientedOverlayRenderer("machines/multiblock/large_sifter")

        @JvmField
        val LARGE_ELECTROLYZER_OVERLAY = OrientedOverlayRenderer("machines/multiblock/large_electrolyzer")

        @JvmField
        val LARGE_ORE_WASHER_OVERLAY = OrientedOverlayRenderer("machines/multiblock/large_ore_washer")

        @JvmField
        val LARGE_ELECTROMAGNET_OVERLAY = OrientedOverlayRenderer("machines/multiblock/large_electromagnet")

        @JvmField
        val LARGE_BURNER_REACTOR_OVERLAY = OrientedOverlayRenderer("machines/multiblock/large_burner_reactor")

        @JvmField
        val LARGE_CRYOGENIC_REACTOR_OVERLAY = OrientedOverlayRenderer("machines/multiblock/large_cryogenic_reactor")

        @JvmField
        val ALLOY_BLAST_SMELTER_OVERLAY = OrientedOverlayRenderer("machines/multiblock/alloy_blast_smelter")

        @JvmField
        val INDUSTRIAL_COKE_OVEN_OVERLAY = OrientedOverlayRenderer("machines/multiblock/industrial_coke_oven")

        @JvmField
        val MINING_DRONE_AIRPORT_OVERLAY = OrientedOverlayRenderer("machines/multiblock/mining_drone_airport")

        @JvmField
        val CVD_UNIT_OVERLAY = OrientedOverlayRenderer("machines/multiblock/cvd_unit")

        @JvmField
        val CRYSTALLIZATION_CRUCIBLE_OVERLAY = OrientedOverlayRenderer("machines/multiblock/crystallization_crucible")

        @JvmField
        val NANOSCALE_FABRICATOR_OVERLAY = OrientedOverlayRenderer("machines/multiblock/nanoscale_fabricator")

        @JvmField
        val SONICATOR_OVERLAY = OrientedOverlayRenderer("machines/multiblock/sonicator")

        @JvmField
        val ADVANCED_FUSION_REACTOR_OVERLAY = OrientedOverlayRenderer("machines/multiblock/advanced_fusion_reactor")

        @JvmField
        val COSMIC_RAY_DETECTOR_OVERLAY = OrientedOverlayRenderer("machines/multiblock/cosmic_ray_detector")

        @JvmField
        val STELLAR_FORGE_OVERLAY = OrientedOverlayRenderer("machines/multiblock/stellar_forge")

        @JvmField
        val NUCLEAR_REACTOR_OVERLAY = OrientedOverlayRenderer("machines/multiblock/nuclear_reactor")

        @JvmField
        val BIO_SIMULATOR_OVERLAY = OrientedOverlayRenderer("machines/bio_simulator")

        @JvmField
        val LARGE_ROCKET_ENGINE_OVERLAY = OrientedOverlayRenderer("machines/multiblock/large_rocket_engine")

        @JvmField
        val QUANTUM_FORCE_TRANSFORMER_OVERLAY = OrientedOverlayRenderer("machines/multiblock/quantum_force_transformer")

        @JvmField
        val ANTIMATTER_FORGE_OVERLAY = OrientedOverlayRenderer("machines/multiblock/antimatter_forge")

        @JvmField
        val SPACE_ELEVATOR_OVERLAY = OrientedOverlayRenderer("machines/multiblock/space_elevator")

        @JvmField
        val SPACE_ASSEMBLER_OVERLAY = SimpleOverlayRenderer("machines/multiblock/space_elevator/space_assembler/overlay_side")

        @JvmField
        val SPACE_PUMP_OVERLAY = SimpleOverlayRenderer("machines/multiblock/space_elevator/space_pump/overlay_side")

        @JvmField
        val ROCKET_ENGINE_OVERLAY = OrientedOverlayRenderer("generators/rocket_engine")

        @JvmField
        val NAQUADAH_REACTOR_OVERLAY = OrientedOverlayRenderer("generators/naquadah_reactor")
        // =============================================================================================================
        @JvmField
        val REINFORCED_TREATED_WOOD_WALL = SidedCubeRenderer("casings/reinforced_treated_wood_wall")

        @JvmField
        val MARAGING_STEEL_250_CASING = GTLiteSimpleOverlayRenderer("casings/metal/maraging_steel_250")

        @JvmField
        val INCONEL_625_CASING = GTLiteSimpleOverlayRenderer("casings/metal/inconel_625")

        @JvmField
        val BLUE_STEEL_CASING = GTLiteSimpleOverlayRenderer("casings/metal/blue_steel")

        @JvmField
        val STABALLOY_CASING = GTLiteSimpleOverlayRenderer("casings/metal/staballoy")

        @JvmField
        val TALONITE_CASING = GTLiteSimpleOverlayRenderer("casings/metal/talonite")

        @JvmField
        val IRIDIUM_CASING = GTLiteSimpleOverlayRenderer("casings/metal/iridium")

        @JvmField
        val ZERON_100_CASING = GTLiteSimpleOverlayRenderer("casings/metal/zeron_100")

        @JvmField
        val WATERTIGHT_STEEL_CASING = GTLiteSimpleOverlayRenderer("casings/metal/watertight_steel")

        @JvmField
        val STELLITE_CASING = GTLiteSimpleOverlayRenderer("casings/metal/stellite")

        @JvmField
        val TUMBAGA_CASING = GTLiteSimpleOverlayRenderer("casings/metal/tumbaga")

        @JvmField
        val EGLIN_STEEL_CASING = GTLiteSimpleOverlayRenderer("casings/metal/eglin_steel")

        @JvmField
        val POTIN_CASING = GTLiteSimpleOverlayRenderer("casings/metal/potin")

        @JvmField
        val GRISIUM_CASING = GTLiteSimpleOverlayRenderer("casings/metal/grisium")

        @JvmField
        val BABBIT_ALLOY_CASING = GTLiteSimpleOverlayRenderer("casings/metal/babbit_alloy")

        @JvmField
        val SILICON_CARBIDE_CASING = GTLiteSimpleOverlayRenderer("casings/metal/silicon_carbide")

        @JvmField
        val RED_STEEL_CASING = GTLiteSimpleOverlayRenderer("casings/metal/red_steel")

        @JvmField
        val HSLA_STEEL_CASING = GTLiteSimpleOverlayRenderer("casings/metal/hsla_steel")

        @JvmField
        val KOVAR_CASING = GTLiteSimpleOverlayRenderer("casings/metal/kovar")

        @JvmField
        val BLACK_STEEL_CASING = GTLiteSimpleOverlayRenderer("casings/metal/black_steel")

        @JvmField
        val INCOLOY_MA813_CASING = GTLiteSimpleOverlayRenderer("casings/metal/incoloy_ma813")

        @JvmField
        val MONEL_500_CASING = GTLiteSimpleOverlayRenderer("casings/metal/monel_500")

        @JvmField
        val INCOLOY_MA956_CASING = GTLiteSimpleOverlayRenderer("casings/metal/incoloy_ma956")

        @JvmField
        val ZIRCONIUM_CARBIDE_CASING = GTLiteSimpleOverlayRenderer("casings/metal/zirconium_carbide")

        @JvmField
        val HASTELLOY_C276_CASING = GTLiteSimpleOverlayRenderer("casings/metal/hastelloy_c276")

        @JvmField
        val HASTELLOY_X_CASING = GTLiteSimpleOverlayRenderer("casings/metal/hastelloy_x")

        @JvmField
        val POLYBENZIMIDAZOLE_CASING = GTLiteSimpleOverlayRenderer("casings/metal/polybenzimidazole")

        @JvmField
        val ALUMINIUM_BRONZE_CASING = GTLiteSimpleOverlayRenderer("casings/metal/aluminium_bronze")

        @JvmField
        val HASTELLOY_N_CASING = GTLiteSimpleOverlayRenderer("casings/metal/hastelloy_n")

        @JvmField
        val RENE_N5_CASING = GTLiteSimpleOverlayRenderer("casings/metal/rene_n5")

        @JvmField
        val BISMUTH_BRONZE_CASING = GTLiteSimpleOverlayRenderer("casings/metal/bismuth_bronze")

        @JvmField
        val BRASS_CASING = GTLiteSimpleOverlayRenderer("casings/metal/brass")

        @JvmField
        val TITANIUM_TUNGSTEN_CARBIDE_CASING = GTLiteSimpleOverlayRenderer("casings/metal/titanium_tungsten_carbide")

        @JvmField
        val COBALT_BRASS_CASING = GTLiteSimpleOverlayRenderer("casings/metal/cobalt_brass")

        @JvmField
        val QUANTUM_ALLOY_CASING = GTLiteSimpleOverlayRenderer("casings/metal/quantum_alloy")

        @JvmField
        val INCONEL_718_CASING = GTLiteSimpleOverlayRenderer("casings/metal/inconel_718")

        @JvmField
        val RHODIUM_PLATED_PALLADIUM_CASING = GTLiteSimpleOverlayRenderer("casings/metal/rhodium_plated_palladium")

        @JvmField
        val TRINAQUADALLOY_CASING = GTLiteSimpleOverlayRenderer("casings/metal/trinaquadalloy")

        @JvmField
        val NITINOL_60_CASING = GTLiteSimpleOverlayRenderer("casings/metal/nitinol_60")

        @JvmField
        val NAQUADAH_ALLOY_CASING = GTLiteSimpleOverlayRenderer("casings/metal/naquadah_alloy")

        @JvmField
        val PARTICLE_CONTAINMENT_CASING = GTLiteSimpleOverlayRenderer("casings/special/particle_containment_casing")

        @JvmField
        val SPACE_ELEVATOR_BASE_CASING = GTLiteSimpleOverlayRenderer("casings/space/elevator_base_casing")

        @JvmField
        val MOLECULAR_CASING = GTLiteSimpleOverlayRenderer("casings/special/molecular_casing")

        @JvmField
        val ANTIMATTER_FORGE_TEXTURE = SimpleOverlayRenderer("machines/multiblock/antimatter_forge/base/antimatter")

        @JvmField
        val ANTIMATTER_FORGE_ACTIVE_TEXTURE = SimpleOverlayRenderer("machines/multiblock/antimatter_forge/base/antimatter_active")
        // =============================================================================================================
        @JvmField
        val PLASTIC_CAN_OVERLAY = SimpleSidedCubeRenderer("storage/drums/plastic_can_top")

        @JvmField
        val PLASTIC_CAN = DrumRenderer("storage/drums/plastic_can")

        @JvmField
        val INVENTORY_BRIDGE = SimpleSidedCubeRenderer("storage/bridges/inventory")

        @JvmField
        val TANK_BRIDGE = SimpleSidedCubeRenderer("storage/bridges/tank")

        @JvmField
        val INVENTORY_TANK_BRIDGE = SimpleSidedCubeRenderer("storage/bridges/inventory_tank")

        @JvmField
        val UNIVERSAL_BRIDGE = SimpleSidedCubeRenderer("storage/bridges/universal")

        @JvmField
        val INVENTORY_EXTENDER = ExtenderRenderer("storage/extenders/inventory")

        @JvmField
        val TANK_EXTENDER = ExtenderRenderer("storage/extenders/tank")

        @JvmField
        val INVENTORY_TANK_EXTENDER = ExtenderRenderer("storage/extenders/inventory_tank")

        @JvmField
        val UNIVERSAL_EXTENDER = ExtenderRenderer("storage/extenders/universal")

        // =============================================================================================================
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

        @JvmStatic
        fun register(textureMap: TextureMap)
        {
            HALO = textureMap.registerSprite(GTLiteUtility.gtliteId("shaders/halo"))
            HALO_NOISE = textureMap.registerSprite(GTLiteUtility.gtliteId("shaders/halo_noise"))
            COSMIC_0 = textureMap.registerSprite(GTLiteUtility.gtliteId("shaders/cosmic_0"))
            COSMIC_1 = textureMap.registerSprite(GTLiteUtility.gtliteId("shaders/cosmic_1"))
            COSMIC_2 = textureMap.registerSprite(GTLiteUtility.gtliteId("shaders/cosmic_2"))
            COSMIC_3 = textureMap.registerSprite(GTLiteUtility.gtliteId("shaders/cosmic_3"))
            COSMIC_4 = textureMap.registerSprite(GTLiteUtility.gtliteId("shaders/cosmic_4"))
            COSMIC_5 = textureMap.registerSprite(GTLiteUtility.gtliteId("shaders/cosmic_5"))
            COSMIC_6 = textureMap.registerSprite(GTLiteUtility.gtliteId("shaders/cosmic_6"))
            COSMIC_7 = textureMap.registerSprite(GTLiteUtility.gtliteId("shaders/cosmic_7"))
            COSMIC_8 = textureMap.registerSprite(GTLiteUtility.gtliteId("shaders/cosmic_8"))
            COSMIC_9 = textureMap.registerSprite(GTLiteUtility.gtliteId("shaders/cosmic_9"))
            COSMIC = arrayOf(COSMIC_0, COSMIC_1, COSMIC_2, COSMIC_3, COSMIC_4, COSMIC_5, COSMIC_6,
                COSMIC_7, COSMIC_8, COSMIC_9)

            FORCE_FIELD = textureMap.registerSprite(GTLiteUtility.gtliteId("shaders/force_field"))
        }

        @JvmStatic
        fun preInit()
        {
            TextureUtils.addIconRegister(GTLiteTextures::register)
        }

    }

}