package magicbook.gtlitecore.client.renderer.texture

import gregtech.client.renderer.texture.cube.OrientedOverlayRenderer
import gregtech.client.renderer.texture.cube.SidedCubeRenderer
import gregtech.client.renderer.texture.cube.SimpleSidedCubeRenderer
import gregtech.client.renderer.texture.custom.DrumRenderer
import magicbook.gtlitecore.client.renderer.texture.cube.GTLiteSimpleOverlayRenderer

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

        // =============================================================================================================
        @JvmField
        val PLASTIC_CAN_OVERLAY = SimpleSidedCubeRenderer("storage/drums/plastic_can_top")

        // =============================================================================================================
        @JvmField
        val PLASTIC_CAN = DrumRenderer("storage/drums/plastic_can")

        // =============================================================================================================

    }

}