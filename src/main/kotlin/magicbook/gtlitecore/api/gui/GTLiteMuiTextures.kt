package magicbook.gtlitecore.api.gui

import com.cleanroommc.modularui.api.drawable.IDrawable
import com.cleanroommc.modularui.drawable.UITexture
import com.cleanroommc.modularui.screen.viewport.GuiContext
import com.cleanroommc.modularui.theme.WidgetTheme
import gregtech.api.GTValues
import magicbook.gtlitecore.api.gui.theme.DisplayGuiTheme
import kotlin.math.min


/**
 * Modular Gui Textures for ModularUI2 contexts.
 *
 * @see com.cleanroommc.modularui.ModularUI
 * @see gregtech.api.mui.GTGuiTextures
 */
@Suppress("UnstableApiUsage")
class GTLiteMuiTextures
{

    companion object
    {
        // Animated Textures

        /**
         * @see gregtech.api.gui.GuiTextures.GREGTECH_LOGO_BLINKING_YELLOW
         */
        @JvmField
        val GREGTECH_LOGO_BLINKING_YELLOW: IDrawable = animated(
            "textures/gui/icon/gregtech_logo_blinking_yellow.png",
            17, 34, false, 60)

        /**
         * @see gregtech.api.gui.GuiTextures.GREGTECH_LOGO_BLINKING_RED
         */
        @JvmField
        val GREGTECH_LOGO_BLINKING_RED: IDrawable = animated(
            "textures/gui/icon/gregtech_logo_blinking_red.png",
            17, 34, false, 36)

        // -------------------------------------------------------------------------------------------------------------
        // Base Textures

        @JvmField
        val DISPLAY: UITexture = UITexture.Builder()
            .location(GTValues.MODID, "textures/gui/base/display.png")
            .name(DisplayGuiTheme.ThemeIDs.DISPLAY)
            .imageSize(143, 75)
            .adaptable(2)
            .canApplyTheme()
            .build()

        @JvmField
        val DISPLAY_BRONZE: UITexture = UITexture.Builder()
            .location(GTValues.MODID, "textures/gui/base/display_bronze.png")
            .name(DisplayGuiTheme.ThemeIDs.DISPLAY_BRONZE)
            .imageSize(143, 75)
            .adaptable(2)
            .build()

        @JvmField
        val DISPLAY_STEEL: UITexture = UITexture.Builder()
            .location(GTValues.MODID, "textures/gui/base/display_steel.png")
            .name(DisplayGuiTheme.ThemeIDs.DISPLAY_STEEL)
            .imageSize(143, 75)
            .adaptable(2)
            .build()

        /**
         * @see magicbook.gtlitecore.api.gui.GTLiteGuiTextures.PRIMITIVE_FLUID_SLOT
         */
        @JvmField
        val PRIMITIVE_FLUID_SLOT: UITexture = fullImage(
            "textures/gui/primitive/primitive_fluid_slot.png")

        // -------------------------------------------------------------------------------------------------------------
        // Progress Bar Textures

        /**
         * @see gregtech.api.gui.GuiTextures.PRIMITIVE_BLAST_FURNACE_PROGRESS_BAR
         */
        @JvmField
        val PRIMITIVE_BLAST_FURNACE_PROGRESS_BAR: UITexture = fullImage(
            "textures/gui/primitive/progress_bar_primitive_blast_furnace.png")

        // -------------------------------------------------------------------------------------------------------------
        // Overlay Textures

        /**
         * @see gregtech.api.gui.GuiTextures.PRIMITIVE_INGOT_OVERLAY
         */
        @JvmField
        val PRIMITIVE_INGOT_OVERLAY: UITexture = fullImage(
            "textures/gui/primitive/overlay_primitive_ingot.png")

        /**
         * @see gregtech.api.gui.GuiTextures.PRIMITIVE_DUST_OVERLAY
         */
        @JvmField
        val PRIMITIVE_DUST_OVERLAY: UITexture = fullImage(
            "textures/gui/primitive/overlay_primitive_dust.png")

        /**
         * @see gregtech.api.gui.GuiTextures.PRIMITIVE_FURNACE_OVERLAY
         */
        @JvmField
        val PRIMITIVE_FURNACE_OVERLAY: UITexture = fullImage(
            "textures/gui/primitive/overlay_primitive_furnace.png")

        /**
         * TODO Add GuiTexture supported of this sprite.
         */
        @JvmField
        val PRIMITIVE_INT_CIRCUIT_OVERLAY: UITexture = fullImage(
            "textures/gui/primitive/int_circuit_overlay_primitive.png")

        // -------------------------------------------------------------------------------------------------------------
        // Widget Textures

        /**
         * @see gregtech.api.gui.GuiTextures.BUTTON_POWER_DETAIL
         */
        @JvmField
        val BUTTON_POWER_DETAIL: UITexture = fullImage(
            "textures/gui/widget/button_power_detail.png")

        /**
         * 0 = OFF <br />
         * 1 = ON <br />
         *
         * @see gregtech.api.gui.GuiTextures.BUTTON_POWER
         */
        @JvmField
        val BUTTON_POWER: Array<UITexture?> = slice("textures/gui/widget/button_power.png",
            18, 36, 18, 18, false)

        /**
         * 0 = DISABLED <br></br>
         * 1 = ITEM VOID <br></br>
         * 2 = FLUID VOID <br></br>
         * 3 = VOID BOTH <br></br>
         *
         * @see gregtech.api.gui.GuiTextures.BUTTON_VOID_MULTIBLOCK
         */
        @JvmField
        val MULTIBLOCK_VOID: Array<UITexture?> = slice("textures/gui/widget/button_void_multiblock.png",
            18, 72, 18, 18, false)

        /**
         * @see gregtech.api.gui.GuiTextures.BUTTON_VOID_NONE
         */
        @JvmField
        val OVERLAY_VOID_NONE: UITexture = fullImage(
            "textures/gui/widget/button_void_none.png")

        /**
         * 0 = DISABLED <br></br>
         * 1 = ENABLED <br></br>
         *
         * @see gregtech.api.gui.GuiTextures.BUTTON_DISTINCT_BUSES
         */
        @JvmField
        val OVERLAY_DISTINCT_BUSES: Array<UITexture?> = slice("textures/gui/widget/button_distinct_buses.png",
            18, 36, 18, 18, false)

        @JvmField
        val OVERLAY_NO_FLEX: UITexture = fullImage(
            "textures/gui/widget/button_no_flex.png", false)

        /**
         * false = DISABLED <br></br>
         * true = ENABLED <br></br>
         *
         * @see magicbook.gtlitecore.api.gui.GTLiteGuiTextures.BUTTON_ELEVATOR_EXTENSION
         */
        @JvmField
        val BUTTON_ELEVATOR_EXTENSION: Array<UITexture?> = slice("textures/gui/widget/button_elevator_extension.png",
            18, 36, 18, 18, false)

        // -------------------------------------------------------------------------------------------------------------
        private fun fullImage(path: String) = fullImage(path, false)

        private fun fullImage(path: String, canApplyTheme: Boolean) = UITexture.fullImage(GTValues.MODID, path, canApplyTheme)

        private fun slice(path: String,
                          imageWidth: Int, imageHeight: Int,
                          sliceWidth: Int, sliceHeight: Int,
                          canApplyTheme: Boolean): Array<UITexture?>
        {
            require(!(imageWidth % sliceWidth != 0 || imageHeight % sliceHeight != 0)) {
                "Slice height and slice width must divide the image evenly!"
            }

            val countX = imageWidth / sliceWidth
            val countY = imageHeight / sliceHeight
            val slices: Array<UITexture?> = arrayOfNulls(countX * countY)

            for (indexX in 0 until countX)
            {
                for (indexY in 0 until countY)
                {
                    slices[indexX * countX + indexY] = UITexture.builder()
                        .location(GTValues.MODID, path)
                        .canApplyTheme(canApplyTheme)
                        .imageSize(imageWidth, imageHeight)
                        .uv(indexX * sliceWidth, indexY * sliceHeight, sliceWidth, sliceHeight)
                        .build()
                }
            }
            return slices
        }

        private fun slice(path: String,
                          imageWidth: Int, imageHeight: Int,
                          canApplyTheme: Boolean): Array<UITexture?>
        {
            val sliceSize = min(imageWidth.toDouble(), imageHeight.toDouble()).toInt()
            return slice(path, imageWidth, imageHeight, sliceSize, sliceSize, canApplyTheme)
        }

        private fun animated(path: String,
                             imageWidth: Int, imageHeight: Int,
                             canApplyTheme: Boolean,
                             rate: Int): IDrawable
        {
            return dynamic(slice(path, imageWidth, imageHeight, canApplyTheme), rate)
        }

        private fun dynamic(textures: Array<UITexture?>, rate: Int): IDrawable
        {
            return object : IDrawable
            {
                var tick: Int = 0
                var index: Int = 0

                override fun draw(context: GuiContext?,
                                  x: Int, y: Int, width: Int, height: Int,
                                  widgetTheme: WidgetTheme?)
                {
                    val a = tick++ % rate // this makes rate per frame ?
                    if (a == 0)
                        index++
                    textures[index % textures.size]!!.draw(context, x, y, width, height, widgetTheme)
                }
            }
        }

    }

}