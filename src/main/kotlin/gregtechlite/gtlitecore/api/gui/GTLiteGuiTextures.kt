package gregtechlite.gtlitecore.api.gui

import gregtech.api.gui.resources.SteamTexture
import gregtech.api.gui.resources.TextureArea
import gregtechlite.gtlitecore.GTLiteMod
import gregtechlite.gtlitecore.api.LOGGER
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

    @JvmField
    val BLACKHOLE_SHAPING_ICON = texture("textures/gui/icon/blackhole_shaping_category.png")

    @JvmField
    val BLACKHOLE_STAMPING_ICON = texture("textures/gui/icon/blackhole_stamping_category.png")

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
     * Steam version of [gregtech.api.gui.GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE].
     *
     * @see gregtechlite.gtlitecore.api.gui.indicator.SteamProgressBarIndicators.ARROW_MULTIPLE
     */
    @JvmField
    val PROGRESS_BAR_ARROW_MULTIPLE_STEAM = steamTexture("textures/gui/progress_bar/progress_bar_arrow_multiple_%s.png")

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

    @JvmField
    val PROGRESS_BAR_BLACKHOLE_FORMING = texture("textures/gui/progress_bar/progress_bar_blackhole_forming.png")

    @JvmField
    val PROGRESS_BAR_COMPLEX_PYROLYSIS = texture("textures/gui/progress_bar/progress_bar_complex_pyrolysis.png")

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

    /**
     * Creates texture area with its [path].
     *
     * This method create internal Modular UI format [TextureArea] for legacy GTCEu API, if you want to create an ui
     * texture for Modular UI 2 format, please use methods in [GTLiteMuiTextures] class.
     *
     * @param path The path in the mod specified namespace.
     *
     * @see GTLiteMuiTextures.texture
     */
    @JvmStatic
    fun texture(path: String): TextureArea = TextureArea(GTLiteMod.id(path), 0.0, 0.0, 1.0, 1.0)

    /**
     * Creates steam textures with its [path].
     *
     * This method create internal Modular UI format [TextureArea] for legacy GTCEu API, if you want to create an ui
     * texture for Modular UI 2 format, please use methods in [GTLiteMuiTextures] class.
     *
     * @param path The path in the mod specified namespace.
     *
     * @see GTLiteMuiTextures.texture
     * @see gregtech.api.mui.GTGuiTheme.BRONZE
     * @see gregtech.api.mui.GTGuiTheme.STEEL
     */
    @JvmStatic
    fun steamTexture(path: String): SteamTexture = runCatching {
        val constructor = SteamTexture::class.constructors
            .first { it.parameters.size == 2 }
            .also { it.isAccessible = true }

        val bronzeTexture = TextureArea(GTLiteMod.id(path.format("bronze")), 0.0, 0.0, 1.0, 1.0)
        val steelTexture = TextureArea(GTLiteMod.id(path.format("steel")), 0.0, 0.0, 1.0, 1.0)

        return@runCatching constructor.call(bronzeTexture, steelTexture)
    }
    .getOrElse {
        LOGGER.warn("Cannot invoke constructor of SteamTexture, moving search namespace of the texture in path '$path' to the mod 'gregtech'")
        return@getOrElse SteamTexture.fullImage(path)
    }
}
