package magicbook.gtlitecore.api.gui

import gregtech.api.gui.resources.SteamTexture
import gregtech.api.gui.resources.TextureArea

/**
 * Internal ModularUI Textures
 *
 * @see gregtech.api.gui.ModularUI
 */
class GTLiteGuiTextures
{

    companion object
    {
        // Bases
        @JvmField /* GuiTextures#SLOT_STEAM for fluid slots in Steam Machines.  */
        val FLUID_SLOT_STEAM: SteamTexture = SteamTexture.fullImage(
            "textures/gui/base/fluid_slot_%s.png")

        @JvmField /* GuiTextures#PRIMITIVE_SLOT for fluid slots. */
        val PRIMITIVE_FLUID_SLOT: TextureArea = TextureArea.fullImage(
            "textures/gui/primitive/primitive_fluid_slot.png")

        // Overlays
        @JvmField
        val CHOPPING_BLOCK_OVERLAY: TextureArea = TextureArea.fullImage(
            "textures/gui/overlay/chopping_block_overlay.png")

        @JvmField
        val SLICED_CONTAINER_OVERLAY: TextureArea = TextureArea.fullImage(
            "textures/gui/overlay/sliced_container_overlay.png")

        @JvmField
        val SLICED_MATTER_OVERLAY: TextureArea = TextureArea.fullImage(
            "textures/gui/overlay/sliced_matter_overlay.png")

        @JvmField /* GuiTextures#INT_CIRCUIT_OVERLAY for Steam Machines. */
        val INT_CIRCUIT_OVERLAY_STEAM: SteamTexture = SteamTexture.fullImage(
            "textures/gui/overlay/int_circuit_overlay_%s.png")

        @JvmField /* GuiTextures#STRING_SLOT_OVERLAY for fluid slots. */
        val STRING_SLOT_OVERLAY_2: TextureArea = TextureArea.fullImage(
            "textures/gui/overlay/string_slot_overlay_2.png")

        @JvmField
        val DISH_OVERLAY: TextureArea = TextureArea.fullImage(
            "textures/gui/overlay/dish_overlay.png")

        @JvmField
        val PLATE_OVERLAY: TextureArea = TextureArea.fullImage(
            "textures/gui/overlay/plate_overlay.png")

        @JvmField
        val DISK_OVERLAY: TextureArea = TextureArea.fullImage(
            "textures/gui/overlay/disk_overlay.png")

        @JvmField
        val NANOSCALE_OVERLAY_1: TextureArea = TextureArea.fullImage(
            "textures/gui/overlay/nanoscale_overlay_1.png")

        @JvmField
        val NANOSCALE_OVERLAY_2: TextureArea = TextureArea.fullImage(
            "textures/gui/overlay/nanoscale_overlay_2.png")

        @JvmField
        val FOIL_OVERLAY: TextureArea = TextureArea.fullImage(
            "textures/gui/overlay/foil_overlay.png")

        // Progress Bars
        @JvmField
        val PROGRESS_BAR_SLICING: TextureArea = TextureArea.fullImage(
            "textures/gui/progress_bar/progress_bar_slicing.png")

        @JvmField
        val PROGRESS_BAR_EXTRACTION: TextureArea = TextureArea.fullImage(
            "textures/gui/progress_bar/progress_bar_extraction.png")

        @JvmField
        val PROGRESS_BAR_ARROW_MULTIPLE_STEAM: SteamTexture = SteamTexture.fullImage(
            "textures/gui/progress_bar/progress_bar_arrow_multiple_%s.png")

        @JvmField
        val PROGRESS_BAR_EXTRACTION_STEAM: SteamTexture = SteamTexture.fullImage(
            "textures/gui/progress_bar/progress_bar_extraction_%s.png")

        @JvmField
        val PROGRESS_BAR_REACTION: TextureArea = TextureArea.fullImage(
            "textures/gui/progress_bar/progress_bar_reaction.png")

        @JvmField
        val PROGRESS_BAR_FOOD_PROCESSING: TextureArea = TextureArea.fullImage(
            "textures/gui/progress_bar/progress_bar_food_processing.png")

        @JvmField
        val PROGRESS_BAR_MINING_DRONE: TextureArea = TextureArea.fullImage(
            "textures/gui/progress_bar/progress_bar_mining_drone.png")

        @JvmField
        val PROGRESS_BAR_NANOSCALE: TextureArea = TextureArea.fullImage(
            "textures/gui/progress_bar/progress_bar_nanoscale.png")

        // Widgets
        @JvmField
        val BUTTON_INT_CIRCUIT_PLUS_STEAM: SteamTexture = SteamTexture.fullImage(
            "textures/gui/widget/button_circuit_plus_%s.png")

        @JvmField
        val BUTTON_INT_CIRCUIT_MINUS_STEAM: SteamTexture = SteamTexture.fullImage(
            "textures/gui/widget/button_circuit_minus_%s.png")

        @JvmField
        val BUTTON_INT_CIRCUIT_PLUS_PRIMITIVE: TextureArea = TextureArea.fullImage(
            "textures/gui/primitive/button_circuit_plus_primitive.png")

        @JvmField
        val BUTTON_INT_CIRCUIT_MINUS_PRIMITIVE: TextureArea = TextureArea.fullImage(
            "textures/gui/primitive/button_circuit_minus_primitive.png")

        @JvmField
        val FUSION_REACTOR_MK4_TITLE: TextureArea = TextureArea.fullImage(
            "textures/gui/widget/fusion_reactor_mk4_title")

        @JvmField
        val FUSION_REACTOR_MK5_TITLE: TextureArea = TextureArea.fullImage(
            "textures/gui/widget/fusion_reactor_mk5_title")

    }

}