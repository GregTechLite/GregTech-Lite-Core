package magicbook.gtlitecore.api.gui

import gregtech.api.gui.resources.SteamTexture
import gregtech.api.gui.resources.TextureArea

/**
 * Gui Textures for internal ModularUI contexts in gregtech.
 *
 * @see gregtech.api.gui.ModularUI
 * @see gregtech.api.gui.GuiTextures
 * @see gregtech.api.gui.resources.SteamTexture
 * @see gregtech.api.gui.resources.TextureArea
 */
@Suppress("unused")
class GTLiteGuiTextures
{

    companion object
    {
        // -------------------------------------------------------------------------------------------------------------
        // Base textures (slots and backgrounds)

        /**
         * Steam version of [gregtech.api.gui.GuiTextures.FLUID_SLOT].
         */
        @JvmField
        var FLUID_SLOT_STEAM: SteamTexture = SteamTexture.fullImage(
            "textures/gui/base/fluid_slot_%s.png")

        /**
         * Primitive version of [gregtech.api.gui.GuiTextures.PRIMITIVE_SLOT].
         */
        @JvmField
        val PRIMITIVE_FLUID_SLOT: TextureArea = TextureArea.fullImage(
            "textures/gui/primitive/primitive_fluid_slot.png")

        // -------------------------------------------------------------------------------------------------------------
        // Icon textures.
        @JvmField
        val SPACE_ELEVATOR_LOGO: TextureArea = TextureArea.fullImage(
            "textures/gui/icon/space_elevator_logo.png")

        @JvmField
        val SPACE_ELEVATOR_LOGO_DARK: TextureArea = TextureArea.fullImage(
            "textures/gui/icon/space_elevator_logo_dark.png")

        // -------------------------------------------------------------------------------------------------------------
        // Overlay textures.

        /**
         * Steam version of [gregtech.api.gui.GuiTextures.INT_CIRCUIT_OVERLAY].
         */
        @JvmField
        val INT_CIRCUIT_OVERLAY_STEAM: SteamTexture = SteamTexture.fullImage(
            "textures/gui/overlay/int_circuit_overlay_%s.png")

        /**
         * Fluid slot form of [gregtech.api.gui.GuiTextures.STRING_SLOT_OVERLAY].
         */
        @JvmField
        val STRING_SLOT_OVERLAY_2: TextureArea = TextureArea.fullImage(
            "textures/gui/overlay/string_slot_overlay_2.png")

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        @JvmField
        val CHOPPING_BLOCK_OVERLAY: TextureArea = TextureArea.fullImage(
            "textures/gui/overlay/chopping_block_overlay.png")

        @JvmField
        val SLICED_CONTAINER_OVERLAY: TextureArea = TextureArea.fullImage(
            "textures/gui/overlay/sliced_container_overlay.png")

        @JvmField
        val SLICED_MATTER_OVERLAY: TextureArea = TextureArea.fullImage(
            "textures/gui/overlay/sliced_matter_overlay.png")

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

        // -------------------------------------------------------------------------------------------------------------
        // Progress bar textures

        /**
         * Steam version of [gregtech.api.gui.GuiTextures.PROGRESS_BAR_ARC_FURNACE].
         *
         * @see magicbook.gtlitecore.api.gui.indicator.SteamProgressBarIndicators.ARC_FURNACE
         */
        val PROGRESS_BAR_ARC_FURNACE_STEAM: SteamTexture = SteamTexture.fullImage(
            "textures/gui/progress_bar/progress_bar_arc_furnace_%s.png")

        /**
         * Steam version of [gregtech.api.gui.GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE].
         *
         * @see magicbook.gtlitecore.api.gui.indicator.SteamProgressBarIndicators.ARROW_MULTIPLE
         */
        @JvmField
        val PROGRESS_BAR_ARROW_MULTIPLE_STEAM: SteamTexture = SteamTexture.fullImage(
            "textures/gui/progress_bar/progress_bar_arrow_multiple_%s.png")

        /**
         * Steam version of [gregtech.api.gui.GuiTextures.PROGRESS_BAR_ASSEMBLY_LINE].
         *
         * @see magicbook.gtlitecore.api.gui.indicator.SteamProgressBarIndicators.ASSEMBLY_LINE
         */
        @JvmField
        val PROGRESS_BAR_ASSEMBLY_LINE_STEAM: SteamTexture = SteamTexture.fullImage(
            "textures/gui/progress_bar/progress_bar_assembly_line_%s.png")

        /**
         * Steam version of [gregtech.api.gui.GuiTextures.PROGRESS_BAR_ASSEMBLY_LINE_ARROW].
         *
         * @see magicbook.gtlitecore.api.gui.indicator.SteamProgressBarIndicators.ASSEMBLY_LINE_ARROW
         */
        @JvmField
        val PROGRESS_BAR_ASSEMBLY_LINE_ARROW_STEAM: SteamTexture = SteamTexture.fullImage(
            "textures/gui/progress_bar/progress_bar_assembly_line_arrow_%s.png")

        /**
         * Steam version of [gregtech.api.gui.GuiTextures.PROGRESS_BAR_BATH].
         *
         * @see magicbook.gtlitecore.api.gui.indicator.SteamProgressBarIndicators.BATH
         */
        @JvmField
        val PROGRESS_BAR_BATH_STEAM: SteamTexture = SteamTexture.fullImage(
            "textures/gui/progress_bar/progress_bar_bath_%s.png")

        /**
         * Steam version of [gregtech.api.gui.GuiTextures.PROGRESS_BAR_BENDING].
         *
         * @see magicbook.gtlitecore.api.gui.indicator.SteamProgressBarIndicators.BENDING
         */
        @JvmField
        val PROGRESS_BAR_BENDING_STEAM: SteamTexture = SteamTexture.fullImage(
            "textures/gui/progress_bar/progress_bar_bending_%s.png")

        /**
         * Steam version of [gregtech.api.gui.GuiTextures.PROGRESS_BAR_CANNER].
         *
         * @see magicbook.gtlitecore.api.gui.indicator.SteamProgressBarIndicators.CANNER
         */
        @JvmField
        val PROGRESS_BAR_CANNER_STEAM: SteamTexture = SteamTexture.fullImage(
            "textures/gui/progress_bar/progress_bar_canner_%s.png")

        /**
         * Steam version of [gregtech.api.gui.GuiTextures.PROGRESS_BAR_CIRCUIT].
         *
         * @see magicbook.gtlitecore.api.gui.indicator.SteamProgressBarIndicators.CIRCUIT
         */
        @JvmField
        val PROGRESS_BAR_CIRCUIT_STEAM: SteamTexture = SteamTexture.fullImage(
            "textures/gui/progress_bar/progress_bar_circuit_%s.png")

        /**
         * Steam version of [gregtech.api.gui.GuiTextures.PROGRESS_BAR_CIRCUIT_ASSEMBLER].
         *
         * @see magicbook.gtlitecore.api.gui.indicator.SteamProgressBarIndicators.CIRCUIT_ASSEMBLER
         */
        @JvmField
        val PROGRESS_BAR_CIRCUIT_ASSEMBLER_STEAM: SteamTexture = SteamTexture.fullImage(
            "textures/gui/progress_bar/progress_bar_circuit_assembler_%s.png")

        /**
         * Steam version of [gregtech.api.gui.GuiTextures.PROGRESS_BAR_COKE_OVEN].
         *
         * @see magicbook.gtlitecore.api.gui.indicator.SteamProgressBarIndicators.COKE_OVEN
         */
        @JvmField
        val PROGRESS_BAR_COKE_OVEN_STEAM: SteamTexture = SteamTexture.fullImage(
            "textures/gui/progress_bar/progress_bar_coke_oven_%s.png")

        /**
         * Steam version of [gregtech.api.gui.GuiTextures.PROGRESS_BAR_CRACKING].
         *
         * @see magicbook.gtlitecore.api.gui.indicator.SteamProgressBarIndicators.CRACKING
         */
        @JvmField
        val PROGRESS_BAR_CRACKING_STEAM: SteamTexture = SteamTexture.fullImage(
            "textures/gui/progress_bar/progress_bar_cracking_%s.png")

        /**
         * Steam version of [gregtech.api.gui.GuiTextures.PROGRESS_BAR_CRACKING_INPUT].
         *
         * @see magicbook.gtlitecore.api.gui.indicator.SteamProgressBarIndicators.CRACKING_INPUT
         */
        @JvmField
        val PROGRESS_BAR_CRACKING_INPUT_STEAM: SteamTexture = SteamTexture.fullImage(
            "textures/gui/progress_bar/progress_bar_cracking_2_%s.png")

        /**
         * Steam version of [gregtech.api.gui.GuiTextures.PROGRESS_BAR_CRYSTALLIZATION].
         *
         * @see magicbook.gtlitecore.api.gui.indicator.SteamProgressBarIndicators.CRYSTALLIZATION
         */
        @JvmField
        val PROGRESS_BAR_CRYSTALLIZATION_STEAM: SteamTexture = SteamTexture.fullImage(
            "textures/gui/progress_bar/progress_bar_crystallization_%s.png")

        /**
         * Steam version of [gregtech.api.gui.GuiTextures.PROGRESS_BAR_DISTILLATION_TOWER].
         *
         * @see magicbook.gtlitecore.api.gui.indicator.SteamProgressBarIndicators.DISTILLATION_TOWER
         */
        @JvmField
        val PROGRESS_BAR_DISTILLATION_TOWER_STEAM: SteamTexture = SteamTexture.fullImage(
            "textures/gui/progress_bar/progress_bar_distillation_tower_%s.png")

        /**
         * Steam version of [gregtech.api.gui.GuiTextures.PROGRESS_BAR_EXTRUDER].
         *
         * @see magicbook.gtlitecore.api.gui.indicator.SteamProgressBarIndicators.EXTRUDER
         */
        @JvmField
        val PROGRESS_BAR_EXTRUDER_STEAM: SteamTexture = SteamTexture.fullImage(
            "textures/gui/progress_bar/progress_bar_extruder_%s.png")

        /**
         * Steam version of [gregtech.api.gui.GuiTextures.PROGRESS_BAR_FUSION].
         *
         * @see magicbook.gtlitecore.api.gui.indicator.SteamProgressBarIndicators.FUSION
         */
        @JvmField
        val PROGRESS_BAR_FUSION_STEAM: SteamTexture = SteamTexture.fullImage(
            "textures/gui/progress_bar/progress_bar_fusion_%s.png")

        /**
         * Steam version of [gregtech.api.gui.GuiTextures.PROGRESS_BAR_GAS_COLLECTOR].
         *
         * @see magicbook.gtlitecore.api.gui.indicator.SteamProgressBarIndicators.GAS_COLLECTOR
         */
        @JvmField
        val PROGRESS_BAR_GAS_COLLECTOR_STEAM: SteamTexture = SteamTexture.fullImage(
            "textures/gui/progress_bar/progress_bar_gas_collector_%s.png")

        // ... TODO

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        @JvmField
        val PROGRESS_BAR_SLICING: TextureArea = TextureArea.fullImage(
            "textures/gui/progress_bar/progress_bar_slicing.png")

        @JvmField
        val PROGRESS_BAR_EXTRACTION: TextureArea = TextureArea.fullImage(
            "textures/gui/progress_bar/progress_bar_extraction.png")

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

        @JvmField
        val PROGRESS_BAR_COMPONENT_ASSEMBLY_LINE_1: TextureArea = TextureArea.fullImage(
            "textures/gui/progress_bar/progress_bar_component_assembly_line_1.png")

        @JvmField
        val PROGRESS_BAR_COMPONENT_ASSEMBLY_LINE_2: TextureArea = TextureArea.fullImage(
            "textures/gui/progress_bar/progress_bar_component_assembly_line_2.png")

        @JvmField
        val PROGRESS_BAR_NOVA_EXPLOSION: TextureArea = TextureArea.fullImage(
            "textures/gui/progress_bar/progress_bar_nova_explosion.png")

        @JvmField
        val PROGRESS_BAR_SWORD: TextureArea = TextureArea.fullImage(
            "textures/gui/progress_bar/progress_bar_sword.png")


        @JvmField
        val PROGRESS_BAR_LRE_HYDROGEN: TextureArea = TextureArea.fullImage(
            "textures/gui/progress_bar/progress_bar_lre_hydrogen.png")

        @JvmField
        val PROGRESS_BAR_LRE_LIQUID_AIR: TextureArea = TextureArea.fullImage(
            "textures/gui/progress_bar/progress_bar_lre_liquid_air.png")

        @JvmField
        val PROGRESS_BAR_LRE_CARBON_DIOXIDE: TextureArea = TextureArea.fullImage(
            "textures/gui/progress_bar/progress_bar_lre_carbon_dioxide.png")

        @JvmField
        val PROGRESS_BAR_LNR_PLASMA_OXYGEN: TextureArea = TextureArea.fullImage(
            "textures/gui/progress_bar/progress_bar_lnr_plasma_oxygen.png")

        // -------------------------------------------------------------------------------------------------------------
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
        val BUTTON_ELEVATOR_EXTENSION: TextureArea = TextureArea.fullImage(
            "textures/gui/widget/button_elevator_extension.png")

        @JvmField
        val BUTTON_DISABLE_MODULE: TextureArea = TextureArea.fullImage(
            "textures/gui/widget/button_disable_module.png")

        @JvmField
        val BUTTON_ENABLE_MODULE: TextureArea = TextureArea.fullImage(
            "textures/gui/widget/button_enable_module.png")

        @JvmField
        val BUTTON_REFRESH_STRUCTURE_PATTERN: TextureArea = TextureArea.fullImage(
            "textures/gui/widget/button_refresh_structure_pattern.png")

        @JvmField
        val FUSION_REACTOR_MK4_TITLE: TextureArea = TextureArea.fullImage(
            "textures/gui/widget/fusion_reactor_mk4_title")

        @JvmField
        val FUSION_REACTOR_MK5_TITLE: TextureArea = TextureArea.fullImage(
            "textures/gui/widget/fusion_reactor_mk5_title")

    }

}