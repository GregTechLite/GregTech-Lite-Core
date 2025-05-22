package magicbook.gtlitecore.api.unification.material.infos

import gregtech.api.unification.material.Materials.BismuthBronze
import gregtech.api.unification.material.Materials.Lutetium
import gregtech.api.unification.material.Materials.Nobelium
import gregtech.api.unification.material.Materials.Promethium
import gregtech.api.unification.material.Materials.Terbium
import gregtech.api.unification.material.Materials.Thulium
import gregtech.api.unification.material.Materials.Uranium238
import gregtech.api.unification.material.info.MaterialIconSet
import magicbook.gtlitecore.client.renderer.texture.GTLiteTextures
import magicbook.gtlitecore.common.item.behavior.HaloRenderItemBehavior

class GTLiteMaterialIconSet
{

    companion object
    {
        // ======================================== Generalized MaterialIconSets =======================================
        @JvmField
        val NANOPARTICLES = MaterialIconSet("nanoparticles", null, true)

        @JvmField
        val ROASTED = MaterialIconSet("roasted", null, true)

        @JvmField
        val BEDROCKIUM = MaterialIconSet("bedrockium", null, true)

        @JvmField
        val SUPERCRITICAL = MaterialIconSet("supercritical", null, true)

        @JvmField
        val COSMIC = MaterialIconSetWithRenderer("cosmic", null, true,
            HaloRenderItemBehavior(10, 0x33FFFFFF, { GTLiteTextures.HALO_NOISE }, true))

        @JvmField
        val INFINITY = MaterialIconSetWithRenderer("infinity", null, true,
            HaloRenderItemBehavior(10, 0xFF000000.toInt(), { GTLiteTextures.HALO }, true))

        @JvmField
        val HALKONITE = MaterialIconSetWithRenderer("halkonite", null, true,
            HaloRenderItemBehavior(10, 0xFF000000.toInt(), { GTLiteTextures.HALO }, true))

        @JvmField
        val DEGENERATE = MaterialIconSet("degenerate", null, true)

        @JvmField
        val MAGNETIUM = MaterialIconSetWithRenderer("magnetium", null, true,
            HaloRenderItemBehavior(10, 0xF8F8D500.toInt(), { GTLiteTextures.HALO }, true))

        @JvmField
        val ENRICHED = MaterialIconSet("enriched", null, true)

        @JvmField
        val ORGANIC = MaterialIconSet("organic", null, true)

        @JvmField
        val SPACETIME = MaterialIconSet("spacetime", null, true)

        @JvmField
        val MAGMATTER = MaterialIconSetWithRenderer("magmatter", null, true,
            HaloRenderItemBehavior(10, 0x33FFFFFF, { GTLiteTextures.HALO_NOISE }, true))

        @JvmField
        val GLITCH = MaterialIconSetWithRenderer("glitch", null, true,
            HaloRenderItemBehavior(10, 0xFF000000.toInt(), { GTLiteTextures.HALO }, true))

        // ========================================== Unified MaterialIconSets =========================================
        @JvmField
        val PYROTHEUM = MaterialIconSet("pyrotheum", null, true)

        @JvmField
        val CRYOTHEUM = MaterialIconSet("cryotheum", null, true)

        @JvmField
        val PETROTHEUM = MaterialIconSet("petrotheum", null, true)

        @JvmField
        val AEROTHEUM = MaterialIconSet("aerotheum", null, true)

        @JvmField
        val MAGNETO = MaterialIconSet("magneto", null, true)

        // =============================================================================================================
        @JvmStatic
        fun setMaterialIconSets()
        {
            BismuthBronze.materialIconSet = MaterialIconSet.METALLIC
            Uranium238.materialIconSet = MaterialIconSet.METALLIC
            Terbium.materialIconSet = MaterialIconSet.SHINY
            Promethium.materialIconSet = MaterialIconSet.SHINY
            Thulium.materialIconSet = MaterialIconSet.SHINY
            Lutetium.materialIconSet = MaterialIconSet.SHINY
            Nobelium.materialIconSet = MaterialIconSet.SHINY
        }

    }

}