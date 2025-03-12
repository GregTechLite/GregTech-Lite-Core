package magicbook.gtlitecore.client

import gregtech.client.renderer.texture.cube.OrientedOverlayRenderer
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

        // =============================================================================================================
        @JvmField
        val PLASTIC_CAN_OVERLAY = SimpleSidedCubeRenderer("storage/drums/plastic_can_top")

        // =============================================================================================================
        @JvmField
        val PLASTIC_CAN = DrumRenderer("storage/drums/plastic_can")
    }

}