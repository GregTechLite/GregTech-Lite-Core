package magicbook.gtlitecore.api.gui

import gregtech.api.gui.resources.SteamTexture
import gregtech.api.gui.widgets.ProgressWidget

data class SteamProgressBarIndicator(val progressBarTexture: SteamTexture,
                                     val progressBarMoveType: ProgressWidget.MoveType,
                                     val width: Int, val height: Int)