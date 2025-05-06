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
         * @see gregtech.api.gui.GuiTextures.PRIMITIVE_INGOT_OVERLAY
         */
        val PRIMITIVE_INGOT_OVERLAY: UITexture = UITexture.fullImage(GTValues.MODID,
            "textures/gui/primitive/overlay_primitive_ingot.png")

        /**
         * @see gregtech.api.gui.GuiTextures.PRIMITIVE_DUST_OVERLAY
         */
        val PRIMITIVE_DUST_OVERLAY: UITexture = UITexture.fullImage(GTValues.MODID,
            "textures/gui/primitive/overlay_primitive_dust.png")

        /**
         * @see gregtech.api.gui.GuiTextures.PRIMITIVE_FURNACE_OVERLAY
         */
        val PRIMITIVE_FURNACE_OVERLAY: UITexture = UITexture.fullImage(GTValues.MODID,
            "textures/gui/primitive/overlay_primitive_furnace.png")

        /**
         * TODO Add GuiTexture supported of this sprite.
         */
        val PRIMITIVE_INT_CIRCUIT_OVERLAY: UITexture = UITexture.fullImage(GTValues.MODID,
            "textures/gui/primitive/int_circuit_overlay_primitive.png")

    }

}