package gregtechlite.gtlitecore.api.gui

import gregtech.api.gui.resources.SteamTexture
import gregtech.api.gui.resources.TextureArea
import gregtechlite.gtlitecore.GTLiteMod
import gregtechlite.gtlitecore.api.GTLiteLog
import kotlin.reflect.jvm.isAccessible

/**
 * Gui Textures for internal ModularUI contexts in gregtech.
 *
 * @see gregtech.api.gui.ModularUI
 * @see gregtech.api.gui.GuiTextures
 * @see gregtech.api.gui.resources.SteamTexture
 * @see gregtech.api.gui.resources.TextureArea
 */
@Suppress("unused")
object GTLiteGuiTextures
{

    // region Base Textures

    /**
     * Steam version of [gregtech.api.gui.GuiTextures.FLUID_SLOT].
     */
    @JvmField
    var FLUID_SLOT_STEAM = steamTexture("textures/gui/base/fluid_slot_%s.png")

    /**
     * Primitive version of [gregtech.api.gui.GuiTextures.PRIMITIVE_SLOT].
     */
    @JvmField
    val PRIMITIVE_FLUID_SLOT = texture("textures/gui/primitive/primitive_fluid_slot.png")

    // endregion

    // region Icon Textures

    @JvmField
    val SPACE_ELEVATOR_LOGO = texture("textures/gui/icon/space_elevator_logo.png")

    @JvmField
    val SPACE_ELEVATOR_LOGO_DARK = texture("textures/gui/icon/space_elevator_logo_dark.png")

    // endregion

    // region Overlay Textures

    /**
     * Steam version of [gregtech.api.gui.GuiTextures.INT_CIRCUIT_OVERLAY].
     */
    @JvmField
    val INT_CIRCUIT_OVERLAY_STEAM = steamTexture("textures/gui/overlay/int_circuit_overlay_%s.png")

    /**
     * Fluid slot form of [gregtech.api.gui.GuiTextures.STRING_SLOT_OVERLAY].
     */
    @JvmField
    val STRING_SLOT_OVERLAY_2 = texture("textures/gui/overlay/string_slot_overlay_2.png")

    @JvmField
    val CHOPPING_BLOCK_OVERLAY = texture("textures/gui/overlay/chopping_block_overlay.png")

    @JvmField
    val SLICED_CONTAINER_OVERLAY = texture("textures/gui/overlay/sliced_container_overlay.png")

    @JvmField
    val SLICED_MATTER_OVERLAY = texture("textures/gui/overlay/sliced_matter_overlay.png")

    @JvmField
    val DISH_OVERLAY = texture("textures/gui/overlay/dish_overlay.png")

    @JvmField
    val PLATE_OVERLAY = texture("textures/gui/overlay/plate_overlay.png")

    @JvmField
    val DISK_OVERLAY = texture("textures/gui/overlay/disk_overlay.png")

    @JvmField
    val NANOSCALE_OVERLAY_1 = texture("textures/gui/overlay/nanoscale_overlay_1.png")

    @JvmField
    val NANOSCALE_OVERLAY_2 = texture("textures/gui/overlay/nanoscale_overlay_2.png")

    @JvmField
    val FOIL_OVERLAY = texture("textures/gui/overlay/foil_overlay.png")

    // endregion

    // region Progress Bar Textures

    /**
     * Steam version of [gregtech.api.gui.GuiTextures.PROGRESS_BAR_ARC_FURNACE].
     *
     * @see gregtechlite.gtlitecore.api.gui.indicator.SteamProgressBarIndicators.ARC_FURNACE
     */
    val PROGRESS_BAR_ARC_FURNACE_STEAM = steamTexture("textures/gui/progress_bar/progress_bar_arc_furnace_%s.png")

    /**
     * Steam version of [gregtech.api.gui.GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE].
     *
     * @see gregtechlite.gtlitecore.api.gui.indicator.SteamProgressBarIndicators.ARROW_MULTIPLE
     */
    @JvmField
    val PROGRESS_BAR_ARROW_MULTIPLE_STEAM = steamTexture("textures/gui/progress_bar/progress_bar_arrow_multiple_%s.png")

    /**
     * Steam version of [gregtech.api.gui.GuiTextures.PROGRESS_BAR_ASSEMBLY_LINE].
     *
     * @see gregtechlite.gtlitecore.api.gui.indicator.SteamProgressBarIndicators.ASSEMBLY_LINE
     */
    @JvmField
    val PROGRESS_BAR_ASSEMBLY_LINE_STEAM = steamTexture("textures/gui/progress_bar/progress_bar_assembly_line_%s.png")

    /**
     * Steam version of [gregtech.api.gui.GuiTextures.PROGRESS_BAR_ASSEMBLY_LINE_ARROW].
     *
     * @see gregtechlite.gtlitecore.api.gui.indicator.SteamProgressBarIndicators.ASSEMBLY_LINE_ARROW
     */
    @JvmField
    val PROGRESS_BAR_ASSEMBLY_LINE_ARROW_STEAM = steamTexture("textures/gui/progress_bar/progress_bar_assembly_line_arrow_%s.png")

    /**
     * Steam version of [gregtech.api.gui.GuiTextures.PROGRESS_BAR_BATH].
     *
     * @see gregtechlite.gtlitecore.api.gui.indicator.SteamProgressBarIndicators.BATH
     */
    @JvmField
    val PROGRESS_BAR_BATH_STEAM = steamTexture("textures/gui/progress_bar/progress_bar_bath_%s.png")

    /**
     * Steam version of [gregtech.api.gui.GuiTextures.PROGRESS_BAR_BENDING].
     *
     * @see gregtechlite.gtlitecore.api.gui.indicator.SteamProgressBarIndicators.BENDING
     */
    @JvmField
    val PROGRESS_BAR_BENDING_STEAM = steamTexture("textures/gui/progress_bar/progress_bar_bending_%s.png")

    /**
     * Steam version of [gregtech.api.gui.GuiTextures.PROGRESS_BAR_CANNER].
     *
     * @see gregtechlite.gtlitecore.api.gui.indicator.SteamProgressBarIndicators.CANNER
     */
    @JvmField
    val PROGRESS_BAR_CANNER_STEAM = steamTexture("textures/gui/progress_bar/progress_bar_canner_%s.png")

    /**
     * Steam version of [gregtech.api.gui.GuiTextures.PROGRESS_BAR_CIRCUIT].
     *
     * @see gregtechlite.gtlitecore.api.gui.indicator.SteamProgressBarIndicators.CIRCUIT
     */
    @JvmField
    val PROGRESS_BAR_CIRCUIT_STEAM = steamTexture("textures/gui/progress_bar/progress_bar_circuit_%s.png")

    /**
     * Steam version of [gregtech.api.gui.GuiTextures.PROGRESS_BAR_CIRCUIT_ASSEMBLER].
     *
     * @see gregtechlite.gtlitecore.api.gui.indicator.SteamProgressBarIndicators.CIRCUIT_ASSEMBLER
     */
    @JvmField
    val PROGRESS_BAR_CIRCUIT_ASSEMBLER_STEAM = steamTexture("textures/gui/progress_bar/progress_bar_circuit_assembler_%s.png")

    /**
     * Steam version of [gregtech.api.gui.GuiTextures.PROGRESS_BAR_COKE_OVEN].
     *
     * @see gregtechlite.gtlitecore.api.gui.indicator.SteamProgressBarIndicators.COKE_OVEN
     */
    @JvmField
    val PROGRESS_BAR_COKE_OVEN_STEAM = steamTexture("textures/gui/progress_bar/progress_bar_coke_oven_%s.png")

    /**
     * Steam version of [gregtech.api.gui.GuiTextures.PROGRESS_BAR_CRACKING].
     *
     * @see gregtechlite.gtlitecore.api.gui.indicator.SteamProgressBarIndicators.CRACKING
     */
    @JvmField
    val PROGRESS_BAR_CRACKING_STEAM = steamTexture("textures/gui/progress_bar/progress_bar_cracking_%s.png")

    /**
     * Steam version of [gregtech.api.gui.GuiTextures.PROGRESS_BAR_CRACKING_INPUT].
     *
     * @see gregtechlite.gtlitecore.api.gui.indicator.SteamProgressBarIndicators.CRACKING_INPUT
     */
    @JvmField
    val PROGRESS_BAR_CRACKING_INPUT_STEAM = steamTexture("textures/gui/progress_bar/progress_bar_cracking_2_%s.png")

    /**
     * Steam version of [gregtech.api.gui.GuiTextures.PROGRESS_BAR_CRYSTALLIZATION].
     *
     * @see gregtechlite.gtlitecore.api.gui.indicator.SteamProgressBarIndicators.CRYSTALLIZATION
     */
    @JvmField
    val PROGRESS_BAR_CRYSTALLIZATION_STEAM = steamTexture("textures/gui/progress_bar/progress_bar_crystallization_%s.png")

    /**
     * Steam version of [gregtech.api.gui.GuiTextures.PROGRESS_BAR_DISTILLATION_TOWER].
     *
     * @see gregtechlite.gtlitecore.api.gui.indicator.SteamProgressBarIndicators.DISTILLATION_TOWER
     */
    @JvmField
    val PROGRESS_BAR_DISTILLATION_TOWER_STEAM = steamTexture("textures/gui/progress_bar/progress_bar_distillation_tower_%s.png")

    /**
     * Steam version of [gregtech.api.gui.GuiTextures.PROGRESS_BAR_EXTRUDER].
     *
     * @see gregtechlite.gtlitecore.api.gui.indicator.SteamProgressBarIndicators.EXTRUDER
     */
    @JvmField
    val PROGRESS_BAR_EXTRUDER_STEAM = steamTexture("textures/gui/progress_bar/progress_bar_extruder_%s.png")

    /**
     * Steam version of [gregtech.api.gui.GuiTextures.PROGRESS_BAR_FUSION].
     *
     * @see gregtechlite.gtlitecore.api.gui.indicator.SteamProgressBarIndicators.FUSION
     */
    @JvmField
    val PROGRESS_BAR_FUSION_STEAM = steamTexture("textures/gui/progress_bar/progress_bar_fusion_%s.png")

    /**
     * Steam version of [gregtech.api.gui.GuiTextures.PROGRESS_BAR_GAS_COLLECTOR].
     *
     * @see gregtechlite.gtlitecore.api.gui.indicator.SteamProgressBarIndicators.GAS_COLLECTOR
     */
    @JvmField
    val PROGRESS_BAR_GAS_COLLECTOR_STEAM = steamTexture("textures/gui/progress_bar/progress_bar_gas_collector_%s.png")

    // TODO Other textures.

    @JvmField
    val PROGRESS_BAR_SLICING = texture("textures/gui/progress_bar/progress_bar_slicing.png")

    @JvmField
    val PROGRESS_BAR_EXTRACTION = texture("textures/gui/progress_bar/progress_bar_extraction.png")

    @JvmField
    val PROGRESS_BAR_EXTRACTION_STEAM = steamTexture("textures/gui/progress_bar/progress_bar_extraction_%s.png")

    @JvmField
    val PROGRESS_BAR_REACTION = texture("textures/gui/progress_bar/progress_bar_reaction.png")

    @JvmField
    val PROGRESS_BAR_FOOD_PROCESSING = texture("textures/gui/progress_bar/progress_bar_food_processing.png")

    @JvmField
    val PROGRESS_BAR_MINING_DRONE = texture("textures/gui/progress_bar/progress_bar_mining_drone.png")

    @JvmField
    val PROGRESS_BAR_NANOSCALE = texture("textures/gui/progress_bar/progress_bar_nanoscale.png")

    @JvmField
    val PROGRESS_BAR_COMPONENT_ASSEMBLY_LINE_1 = texture("textures/gui/progress_bar/progress_bar_component_assembly_line_1.png")

    @JvmField
    val PROGRESS_BAR_COMPONENT_ASSEMBLY_LINE_2 = texture("textures/gui/progress_bar/progress_bar_component_assembly_line_2.png")

    @JvmField
    val PROGRESS_BAR_NOVA_EXPLOSION = texture("textures/gui/progress_bar/progress_bar_nova_explosion.png")

    @JvmField
    val PROGRESS_BAR_SWORD = texture("textures/gui/progress_bar/progress_bar_sword.png")

    @JvmField
    val PROGRESS_BAR_PHASE_CHANGE = texture("textures/gui/progress_bar/progress_bar_phase_change.png")

    @JvmField
    val PROGRESS_BAR_ANTI_GRAVITY_ASSEMBLING_1 = texture("textures/gui/progress_bar/progress_bar_anti_gravity_assembling_1.png")

    @JvmField
    val PROGRESS_BAR_ANTI_GRAVITY_ASSEMBLING_2 = texture("textures/gui/progress_bar/progress_bar_anti_gravity_assembling_2.png")

    // endregion

    // region Widget Textures

    @JvmField
    val BUTTON_INT_CIRCUIT_PLUS_STEAM = steamTexture("textures/gui/widget/button_circuit_plus_%s.png")

    @JvmField
    val BUTTON_INT_CIRCUIT_MINUS_STEAM = steamTexture("textures/gui/widget/button_circuit_minus_%s.png")

    @JvmField
    val BUTTON_INT_CIRCUIT_PLUS_PRIMITIVE = texture("textures/gui/primitive/button_circuit_plus_primitive.png")

    @JvmField
    val BUTTON_INT_CIRCUIT_MINUS_PRIMITIVE = texture("textures/gui/primitive/button_circuit_minus_primitive.png")

    @JvmField
    val BUTTON_ELEVATOR_EXTENSION = texture("textures/gui/widget/button_elevator_extension.png")

    @JvmField
    val BUTTON_DISABLE_MODULE = texture("textures/gui/widget/button_disable_module.png")

    @JvmField
    val BUTTON_ENABLE_MODULE = texture("textures/gui/widget/button_enable_module.png")

    @JvmField
    val BUTTON_REFRESH_STRUCTURE_PATTERN = texture("textures/gui/widget/button_refresh_structure_pattern.png")

    @JvmField
    val FUSION_REACTOR_MK4_TITLE = texture("textures/gui/widget/fusion_reactor_mk4_title")

    @JvmField
    val FUSION_REACTOR_MK5_TITLE = texture("textures/gui/widget/fusion_reactor_mk5_title")

    // endregion

    fun texture(path: String) = TextureArea(GTLiteMod.id(path), 0.0, 0.0, 1.0, 1.0)

    fun steamTexture(path: String): SteamTexture = runCatching {
        val constructor = SteamTexture::class.constructors
            .first { it.parameters.size == 2 }
            .also { it.isAccessible = true }

        val bronzeTexture = TextureArea(GTLiteMod.id(String.format(path, "bronze")),
                                        0.0, 0.0, 1.0, 1.0)

        val steelTexture = TextureArea(GTLiteMod.id(String.format(path, "steel")),
                                       0.0, 0.0, 1.0, 1.0)

        return@runCatching constructor.call(bronzeTexture, steelTexture)
    }
    .getOrElse {
        GTLiteLog.logger.warn("Cannot invoke constructor of SteamTexture, moving search namespace of the texture in path '$path' to the mod 'gregtech'")
        return@getOrElse SteamTexture.fullImage(path)
    }

}
