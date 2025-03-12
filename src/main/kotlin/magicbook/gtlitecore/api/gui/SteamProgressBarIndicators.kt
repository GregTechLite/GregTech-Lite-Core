package magicbook.gtlitecore.api.gui

import gregtech.api.gui.GuiTextures
import gregtech.api.gui.widgets.ProgressWidget

class SteamProgressBarIndicators
{

    companion object
    {

        @JvmField
        val ARROW = SteamProgressBarIndicator(GuiTextures.PROGRESS_BAR_ARROW_STEAM,
            ProgressWidget.MoveType.HORIZONTAL, 20, 16)

        @JvmField
        val ARROW_MULTIPLE = SteamProgressBarIndicator(GTLiteGuiTextures.PROGRESS_BAR_ARROW_MULTIPLE_STEAM,
            ProgressWidget.MoveType.HORIZONTAL, 20, 20)

        @JvmField
        val COMPRESS = SteamProgressBarIndicator(GuiTextures.PROGRESS_BAR_COMPRESS_STEAM,
            ProgressWidget.MoveType.HORIZONTAL, 20, 16)

        @JvmField
        val EXTRACTION = SteamProgressBarIndicator(GTLiteGuiTextures.PROGRESS_BAR_EXTRACTION_STEAM,
            ProgressWidget.MoveType.VERTICAL_DOWNWARDS, 20, 20)

    }

}