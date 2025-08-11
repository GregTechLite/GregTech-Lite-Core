package gregtechlite.gtlitecore.api.gui.indicator

import gregtech.api.gui.GuiTextures
import gregtech.api.gui.widgets.ProgressWidget
import gregtechlite.gtlitecore.api.gui.GTLiteGuiTextures

@Suppress("unused")
object SteamProgressBarIndicators
{

    // @formatter:off

    // region Wrapped GTCEu Steam Textures

    @JvmField
    val ARC_FURNACE = SteamProgressBarIndicator(GTLiteGuiTextures.PROGRESS_BAR_ARC_FURNACE_STEAM,
        ProgressWidget.MoveType.HORIZONTAL, 20, 20)

    @JvmField
    val ARROW = SteamProgressBarIndicator(GuiTextures.PROGRESS_BAR_ARROW_STEAM,
        ProgressWidget.MoveType.HORIZONTAL, 20, 16)

    @JvmField
    val ARROW_MULTIPLE = SteamProgressBarIndicator(GTLiteGuiTextures.PROGRESS_BAR_ARROW_MULTIPLE_STEAM,
        ProgressWidget.MoveType.HORIZONTAL, 20, 20)

    @JvmField
    val ASSEMBLY_LINE = SteamProgressBarIndicator(GTLiteGuiTextures.PROGRESS_BAR_ASSEMBLY_LINE_STEAM,
        ProgressWidget.MoveType.HORIZONTAL, 54, 72)

    @JvmField
    val ASSEMBLY_LINE_ARROW = SteamProgressBarIndicator(GTLiteGuiTextures.PROGRESS_BAR_ASSEMBLY_LINE_ARROW_STEAM,
        ProgressWidget.MoveType.VERTICAL, 10, 18)

    @JvmField
    val BATH = SteamProgressBarIndicator(GTLiteGuiTextures.PROGRESS_BAR_BATH_STEAM,
        ProgressWidget.MoveType.CIRCULAR, 20, 20)

    @JvmField
    val BENDING = SteamProgressBarIndicator(GTLiteGuiTextures.PROGRESS_BAR_BENDING_STEAM,
        ProgressWidget.MoveType.HORIZONTAL, 20, 20)

    @JvmField
    val CANNER = SteamProgressBarIndicator(GTLiteGuiTextures.PROGRESS_BAR_CANNER_STEAM,
        ProgressWidget.MoveType.HORIZONTAL, 20, 20)

    @JvmField
    val CIRCUIT = SteamProgressBarIndicator(GTLiteGuiTextures.PROGRESS_BAR_CIRCUIT_STEAM,
        ProgressWidget.MoveType.HORIZONTAL, 20, 20)

    @JvmField
    val CIRCUIT_ASSEMBLER = SteamProgressBarIndicator(GTLiteGuiTextures.PROGRESS_BAR_CIRCUIT_ASSEMBLER_STEAM,
        ProgressWidget.MoveType.HORIZONTAL, 20, 20)

    @JvmField
    val COKE_OVEN = SteamProgressBarIndicator(GTLiteGuiTextures.PROGRESS_BAR_COKE_OVEN_STEAM,
        ProgressWidget.MoveType.HORIZONTAL, 36, 18)

    @JvmField
    val COMPRESS = SteamProgressBarIndicator(GuiTextures.PROGRESS_BAR_COMPRESS_STEAM,
        ProgressWidget.MoveType.HORIZONTAL, 20, 16)

    @JvmField
    val CRACKING = SteamProgressBarIndicator(GTLiteGuiTextures.PROGRESS_BAR_CRACKING_STEAM,
        ProgressWidget.MoveType.HORIZONTAL, 20, 20)

    @JvmField
    val CRACKING_INPUT = SteamProgressBarIndicator(GTLiteGuiTextures.PROGRESS_BAR_CRACKING_INPUT_STEAM,
        ProgressWidget.MoveType.HORIZONTAL, 21, 19)

    @JvmField
    val CRYSTALLIZATION = SteamProgressBarIndicator(GTLiteGuiTextures.PROGRESS_BAR_CRYSTALLIZATION_STEAM,
        ProgressWidget.MoveType.HORIZONTAL, 20, 20)

    @JvmField
    val DISTILLATION_TOWER = SteamProgressBarIndicator(GTLiteGuiTextures.PROGRESS_BAR_DISTILLATION_TOWER_STEAM,
        ProgressWidget.MoveType.HORIZONTAL, 66, 58)

    @JvmField
    val EXTRACT = SteamProgressBarIndicator(GuiTextures.PROGRESS_BAR_EXTRACT_STEAM,
        ProgressWidget.MoveType.HORIZONTAL, 20, 20)

    @JvmField
    val EXTRUDER = SteamProgressBarIndicator(GTLiteGuiTextures.PROGRESS_BAR_EXTRUDER_STEAM,
        ProgressWidget.MoveType.HORIZONTAL, 20, 20)

    @JvmField
    val FUSION = SteamProgressBarIndicator(GTLiteGuiTextures.PROGRESS_BAR_FUSION_STEAM,
        ProgressWidget.MoveType.HORIZONTAL, 20, 20)

    @JvmField
    val GAS_COLLECTOR = SteamProgressBarIndicator(GTLiteGuiTextures.PROGRESS_BAR_GAS_COLLECTOR_STEAM,
        ProgressWidget.MoveType.HORIZONTAL, 20, 20)

    // endregion

    // region Special Steam Textures

    @JvmField
    val EXTRACTION = SteamProgressBarIndicator(GTLiteGuiTextures.PROGRESS_BAR_EXTRACTION_STEAM,
        ProgressWidget.MoveType.VERTICAL_DOWNWARDS, 20, 20)

    // endregion

    // @formatter:on

}