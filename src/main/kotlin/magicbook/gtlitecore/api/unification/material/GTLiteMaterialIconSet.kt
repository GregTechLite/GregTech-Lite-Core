package magicbook.gtlitecore.api.unification.material

import gregtech.api.unification.material.Materials.BismuthBronze
import gregtech.api.unification.material.Materials.Uranium238
import gregtech.api.unification.material.info.MaterialIconSet

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