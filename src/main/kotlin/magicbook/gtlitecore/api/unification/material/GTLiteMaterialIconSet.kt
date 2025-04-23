package magicbook.gtlitecore.api.unification.material

import gregtech.api.unification.material.Materials.BismuthBronze
import gregtech.api.unification.material.Materials.Uranium238
import gregtech.api.unification.material.info.MaterialIconSet
import magicbook.gtlitecore.api.unification.material.infos.MaterialIconSetWithRenderer
import magicbook.gtlitecore.client.renderer.texture.GTLiteTextures
import magicbook.gtlitecore.common.item.behavior.HaloRenderItemBehavior
import java.util.function.Supplier

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
        }

    }

}