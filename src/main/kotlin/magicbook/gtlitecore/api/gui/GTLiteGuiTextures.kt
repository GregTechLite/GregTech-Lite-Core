package magicbook.gtlitecore.api.gui

import gregtech.api.gui.resources.SteamTexture
import gregtech.api.gui.resources.TextureArea

class GTLiteGuiTextures
{

    companion object
    {
        // ---------------------------------------- Internal ModularUI Textures ----------------------------------------
        // Bases
        @JvmField /* GuiTextures#SLOT_STEAM for fluid slots in Steam Machines.  */
        val FLUID_SLOT_STEAM = SteamTexture.fullImage(
            "textures/gui/base/fluid_slot_%s.png")

        // Overlays
        @JvmField
        val CHOPPING_BLOCK_OVERLAY = TextureArea.fullImage(
            "textures/gui/overlay/chopping_block_overlay.png")

        @JvmField
        val SLICED_CONTAINER_OVERLAY = TextureArea.fullImage(
            "textures/gui/overlay/sliced_container_overlay.png")

        @JvmField
        val SLICED_MATTER_OVERLAY= TextureArea.fullImage(
            "textures/gui/overlay/sliced_matter_overlay.png")

        @JvmField /* GuiTextures#INT_CIRCUIT_OVERLAY for Steam Machines. */
        val INT_CIRCUIT_OVERLAY_STEAM = SteamTexture.fullImage(
            "textures/gui/overlay/int_circuit_overlay_%s.png")

        @JvmField /* GuiTextures#STRING_SLOT_OVERLAY for fluid slots. */
        val STRING_SLOT_OVERLAY_2: TextureArea = TextureArea.fullImage(
            "textures/gui/overlay/string_slot_overlay_2.png")

        // Progress Bars
        @JvmField
        val PROGRESS_BAR_SLICING = TextureArea.fullImage(
            "textures/gui/progress_bar/progress_bar_slicing.png")

        // Widgets
        @JvmField
        val BUTTON_INT_CIRCUIT_PLUS_STEAM = SteamTexture.fullImage(
            "textures/gui/widget/button_circuit_plus_%s.png")

        @JvmField
        val BUTTON_INT_CIRCUIT_MINUS_STEAM = SteamTexture.fullImage(
            "textures/gui/widget/button_circuit_minus_%s.png")
        // -------------------------------------------- ModularUI2 Textures --------------------------------------------

        // -------------------------------------------------------------------------------------------------------------
    }

}