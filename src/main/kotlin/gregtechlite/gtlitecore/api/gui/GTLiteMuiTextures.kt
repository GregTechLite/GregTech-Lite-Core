package gregtechlite.gtlitecore.api.gui

import com.cleanroommc.modularui.drawable.ColorType
import com.cleanroommc.modularui.drawable.UITexture
import gregtech.api.util.Size
import gregtechlite.gtlitecore.api.MOD_ID

@Suppress("SameParameterValue")
object GTLiteMuiTextures
{

    // region Slot Textures

    @JvmField
    val PRIMITIVE_FLUID_SLOT = fullImage("textures/gui/primitive/primitive_fluid_slot.png")

    // endregion

    // region Progress Bar Textures

    @JvmField
    val PRIMITIVE_BLAST_FURNACE_PROGRESS_BAR = fullImage("textures/gui/primitive/progress_bar_primitive_blast_furnace.png")

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
    val PRIMITIVE_INT_CIRCUIT_OVERLAY = fullImage("textures/gui/primitive/int_circuit_overlay_primitive.png")

    // endregion

    // region Widget Textures
    @JvmField
    val FUSION_REACTOR_MK4_TITLE = fullImage("textures/gui/widget/fusion_reactor_mk4_title")

    @JvmField
    val FUSION_REACTOR_MK5_TITLE = fullImage("textures/gui/widget/fusion_reactor_mk5_title")

    /**
     * Multiblock Button for Space Elevator to enabled/disabled extension structure.
     *
     * - 0: Disabled
     * - 1: Enabled
     */
    @JvmField
    val BUTTON_ELEVATOR_EXTENSION = sliceImage("textures/gui/widget/button_elevator_extension.png",
                                          18, 36, 18, 18)

    /**
     * Multiblock Button for Space Elevator Modules to refreshed its structure pattern.
     */
    @JvmField
    val BUTTON_REFRESH_STRUCTURE_PATTERN = fullImage("textures/gui/widget/button_refresh_structure_pattern.png")

    // endregion

    // region Helper Methods

    private fun fullImage(path: String, colorType: ColorType? = null): UITexture = UITexture.fullImage(MOD_ID, path, colorType)

    @Suppress("SameParameterValue")
    private fun sliceImage(path: String, width: Int, height: Int, newWidth: Int, newHeight: Int, colorType: ColorType? = null): Array<UITexture?>
    {
        require((width % newWidth == 0) || (height % newHeight == 0))

        val countX= width / newWidth
        val countY= height / newHeight
        val slices = arrayOfNulls<UITexture>(countX * countY)

        for (indexX in 0 ..< countX)
        {
            for (indexY in 0 ..< countY)
            {
                slices[(indexX * countX) + indexY] = uiTexture{
                    location(MOD_ID, path)
                    colorType(colorType)
                    imageSize(width, height)
                    xy(indexX * newWidth, indexY * newHeight, newWidth, newHeight)
                }
            }
        }
        return slices
    }

    private fun progressBar(path: String, width: Int = 20, height: Int = 40, colorType: ColorType? = null) = uiTexture {
        location(MOD_ID, path)
        imageSize(width, height)
        colorType(colorType)
    }

    private fun uiTexture(builder: UITexture.Builder.() -> Unit) = UITexture.builder().apply(builder).build()

    // endregion

}