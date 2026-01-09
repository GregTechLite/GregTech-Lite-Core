package gregtechlite.gtlitecore.api.gui

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
    val BUTTON_ELEVATOR_EXTENSION = slice("textures/gui/widget/button_elevator_extension.png",
                                          18, 36, 18, 18, false)

    /**
     * Multiblock Button for Space Elevator Modules to refreshed its structure pattern.
     */
    @JvmField
    val BUTTON_REFRESH_STRUCTURE_PATTERN = fullImage("textures/gui/widget/button_refresh_structure_pattern.png")

    // endregion

    // region Helper Methods

    private fun fullImage(path: String): UITexture
    {
        return fullImage(path, false)
    }

    private fun fullImage(path: String, canApplyTheme: Boolean): UITexture
    {
        return UITexture.fullImage(MOD_ID, path, canApplyTheme)
    }

    private fun progressBar(path: String): UITexture
    {
        return progressBar(path, 20, 40)
    }

    private fun progressBar(path: String, canApplyTheme: Boolean): UITexture
    {
        return progressBar(path, 20, 40, false)
    }

    private fun progressBar(path: String, width: Int, height: Int): UITexture
    {
        return progressBar(path, width, height, false)
    }

    private fun progressBar(path: String, size: Size): UITexture
    {
        return progressBar(path, size, false)
    }

    private fun progressBar(path: String, width: Int, height: Int, canApplyTheme: Boolean): UITexture
    {
        val builder = UITexture.Builder()
            .location(MOD_ID, path)
            .imageSize(width, height)
        if (canApplyTheme) builder.canApplyTheme()
        return builder.build()
    }

    private fun progressBar(path: String, size: Size, canApplyTheme: Boolean): UITexture
    {
        return progressBar(path, size.width, size.height, canApplyTheme)
    }

    private fun slice(path: String, width: Int, height: Int, sliceWidth: Int, sliceHeight: Int,
                      canApplyTheme: Boolean): Array<UITexture>
    {
        require(!(width % sliceWidth != 0 || height % sliceHeight != 0)) {
            "Slice height and slice width must divide the image evenly!"
        }
        val countX = width / sliceWidth
        val countY = height / sliceHeight

        val slices = mutableListOf<UITexture>()
        for (indexX in 0 ..< countX)
        {
            for (indexY in 0 ..< countY)
            {
                slices.add(UITexture.builder()
                               .location(MOD_ID, path)
                               .canApplyTheme(canApplyTheme)
                               .imageSize(width, height)
                               .uv(indexX * sliceWidth, indexY * sliceHeight, sliceWidth, sliceHeight)
                               .build())
            }
        }
        return slices.toTypedArray()
    }

    private fun slice(path: String, size: Size, sliceSize: Size, canApplyTheme: Boolean): Array<UITexture>
    {
        return slice(path, size.width, size.height,
                     sliceSize.width, sliceSize.height, canApplyTheme)
    }

    // endregion

}