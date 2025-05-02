package magicbook.gtlitecore.api.gui

import com.cleanroommc.modularui.drawable.UITexture
import gregtech.api.GTValues

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

        /**
         * @see gregtech.api.gui.GuiTextures.PRIMITIVE_BLAST_FURNACE_PROGRESS_BAR
         */
        val PRIMITIVE_BLAST_FURNACE_PROGRESS_BAR: UITexture = UITexture.fullImage(GTValues.MODID,
            "textures/gui/primitive/progress_bar_primitive_blast_furnace.png")

        /**
         * @see magicbook.gtlitecore.api.gui.GTLiteGuiTextures.PRIMITIVE_FLUID_SLOT
         */
        val PRIMITIVE_FLUID_SLOT: UITexture = UITexture.fullImage(GTValues.MODID,
            "textures/gui/primitive/primitive_fluid_slot.png")

        /**
         * TODO Add GuiTexture supported of this sprite.
         */
        val PRIMITIVE_INT_CIRCUIT_OVERLAY: UITexture = UITexture.fullImage(GTValues.MODID,
            "textures/gui/primitive/int_circuit_overlay_primitive.png")

    }

}