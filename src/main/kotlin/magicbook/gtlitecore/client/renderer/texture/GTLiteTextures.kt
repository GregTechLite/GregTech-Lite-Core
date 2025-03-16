package magicbook.gtlitecore.client.renderer.texture

import gregtech.client.renderer.texture.cube.OrientedOverlayRenderer
import gregtech.client.renderer.texture.cube.SidedCubeRenderer
import gregtech.client.renderer.texture.cube.SimpleSidedCubeRenderer
import gregtech.client.renderer.texture.custom.DrumRenderer

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

        // =============================================================================================================
        @JvmField
        val REINFORCED_TREATED_WOOD_WALL = SidedCubeRenderer("casings/reinforced_treated_wood_wall")

        // =============================================================================================================
        @JvmField
        val PLASTIC_CAN_OVERLAY = SimpleSidedCubeRenderer("storage/drums/plastic_can_top")

        // =============================================================================================================
        @JvmField
        val PLASTIC_CAN = DrumRenderer("storage/drums/plastic_can")

        // =============================================================================================================

    }

}