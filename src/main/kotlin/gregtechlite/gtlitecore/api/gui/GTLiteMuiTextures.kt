package gregtechlite.gtlitecore.api.gui

import com.cleanroommc.modularui.drawable.ColorType
import com.cleanroommc.modularui.drawable.UITexture
import gregtechlite.gtlitecore.api.MOD_ID

@Suppress("SameParameterValue")
object GTLiteMuiTextures
{

    // region Slot Textures

    @JvmField
    val PRIMITIVE_FLUID_SLOT = texture("textures/gui/primitive/primitive_fluid_slot.png")

    // endregion

    // region Progress Bar Textures

    @JvmField
    val PRIMITIVE_BLAST_FURNACE_PROGRESS_BAR = texture("textures/gui/primitive/progress_bar_primitive_blast_furnace.png")

    /**
     * Multiblock Progress Bar for Large Naquadah Reactor (LNR).
     */
    @JvmField
    val PROGRESS_BAR_LNR_PLASMA_OXYGEN = progressBar("textures/gui/progress_bar/progress_bar_lnr_plasma_oxygen.png",
                                                     94, 14)

    /**
     * Multiblock Progress Bar for Large Rocket Engine (LRE).
     */
    @JvmField
    val PROGRESS_BAR_LRE_HYDROGEN = progressBar("textures/gui/progress_bar/progress_bar_lre_hydrogen.png",
                                                62, 14)

    /**
     * Multiblock Progress Bar for Large Rocket Engine (LRE).
     */
    @JvmField
    val PROGRESS_BAR_LRE_LIQUID_AIR = progressBar("textures/gui/progress_bar/progress_bar_lre_liquid_air.png",
                                                  62, 14)

    /**
     * Multiblock Progress Bar for Large Rocket Engine (LRE).
     */
    @JvmField
    val PROGRESS_BAR_LRE_CARBON_DIOXIDE = progressBar("textures/gui/progress_bar/progress_bar_lre_carbon_dioxide.png",
                                                      62, 14)

    // endregion

    // region Overlay Textures

    @JvmField
    val PRIMITIVE_INT_CIRCUIT_OVERLAY = texture("textures/gui/primitive/int_circuit_overlay_primitive.png")

    // endregion

    // region Widget Textures

    @JvmField
    val FUSION_REACTOR_MK4_TITLE = texture("textures/gui/widget/fusion_reactor_mk4_title")

    @JvmField
    val FUSION_REACTOR_MK5_TITLE = texture("textures/gui/widget/fusion_reactor_mk5_title")

    /**
     * Multiblock Button for Space Elevator to enabled/disabled extension structure.
     *
     * - 0: Disabled
     * - 1: Enabled
     */
    @JvmField
    val BUTTON_ELEVATOR_EXTENSION = texture("textures/gui/widget/button_elevator_extension.png",
                                            18, 36, 18, 18)

    /**
     * Multiblock Button for Space Elevator Modules to refreshed its structure pattern.
     */
    @JvmField
    val BUTTON_REFRESH_STRUCTURE_PATTERN = texture("textures/gui/widget/button_refresh_structure_pattern.png")

    /**
     * Multiblock Button for Space Elevator Modules to enable modules.
     *
     */
    @JvmField
    val BUTTON_ENABLE_MODULE = fullImage("textures/gui/widget/button_enable_module.png")

    /**
     * Multiblock Button for Space Elevator Modules to disable modules.
     *
     */
    @JvmField
    val BUTTON_DISABLE_MODULE = fullImage("textures/gui/widget/button_disable_module.png")

    /**
     * Space Elevator Logo
     *
     */
    @JvmField
    val SPACE_ELEVATOR_LOGO = fullImage("textures/gui/icon/space_elevator_logo.png")

    /**
     * Space Elevator Logo Dark
     */
    @JvmField
    val SPACE_ELEVATOR_LOGO_DARK = fullImage("textures/gui/icon/space_elevator_logo_dark.png")

    // endregion

    /**
     * Creates ui texture with its [path] and [colorType] as theme.
     *
     * @param path      The path in the mod specified namespace.
     * @param colorType The color type (theme) of the texture.
     */
    @JvmStatic
    fun texture(path: String, colorType: ColorType? = null): UITexture = UITexture.fullImage(MOD_ID, path, colorType)

    /**
     * Creates sliced ui texture with its [path] and [colorType] as theme.
     *
     * @param path      The path in the mod specified namespace.
     * @param width     The original width of the ui texture.
     * @param height    The original height of the ui texture.
     * @param newWidth  The sliced width for the ui texture, should satisfy [width] / [newWidth] % 0.
     * @param newHeight The sliced height for the ui texture, should satisfy [height] / [newHeight] % 0.
     * @param colorType The color type (theme) of the texture.
     */
    @JvmStatic
    fun texture(path: String, width: Int, height: Int, newWidth: Int, newHeight: Int,
                colorType: ColorType? = null): Array<UITexture>
    {
        require((width % newWidth == 0) || (height % newHeight == 0))

        val countX = width / newWidth
        val countY = height / newHeight
        val slices = arrayOfNulls<UITexture>(countX * countY)

        for (indexX in 0 ..< countX)
        {
            for (indexY in 0 ..< countY)
            {
                slices[(indexX * countX) + indexY] = ui {
                    location(MOD_ID, path)
                    colorType(colorType)
                    imageSize(width, height)
                    xy(indexX * newWidth, indexY * newHeight, newWidth, newHeight)
                }
            }
        }
        return slices.filterNotNull().toTypedArray()
    }

    /**
     * Creates a GT format progress bar ui texture.
     *
     * @param path      The path in the mod specified namespace.
     * @param width     The original width of the progress bar texture.
     * @param height    The original height of the progress bar texture.
     * @param colorType The color type (theme) of the texture.
     */
    @JvmStatic
    fun progressBar(path: String, width: Int = 20, height: Int = 40, colorType: ColorType? = null): UITexture = ui {
        location(MOD_ID, path)
        imageSize(width, height)
        colorType(colorType)
    }

    private fun ui(builder: UITexture.Builder.() -> Unit): UITexture = UITexture.builder().apply(builder).build()

}