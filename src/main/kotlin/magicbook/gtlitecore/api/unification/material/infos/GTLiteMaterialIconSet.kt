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
        val PYROTHEUM = MaterialIconSet("pyrotheum", null, true)
        val CRYOTHEUM = MaterialIconSet("cryotheum", null, true)
        val PETROTHEUM = MaterialIconSet("petrotheum", null, true)
        val AEROTHEUM = MaterialIconSet("aerotheum", null, true)
        val MAGNETO = MaterialIconSet("magneto", null, true)
        val NANOPARTICLES = MaterialIconSet("nanoparticles", null, true)
        val ROASTED = MaterialIconSet("roasted", null, true)
        val BEDROCKIUM = MaterialIconSet("bedrockium", null, true)
        val SUPERCRITICAL = MaterialIconSet("supercritical", null, true)
        val DEGENERATE = MaterialIconSet("degenerate", null, true)
        val ENRICHED = MaterialIconSet("enriched", null, true)
        val ORGANIC = MaterialIconSet("organic", null, true)
        val SPACETIME = MaterialIconSet("spacetime", null, true)
        val MHDCSM = MaterialIconSet("mhdcsm", null, true)
        val UNIVERSIUM = MaterialIconSet("universium", null, true)

        val COSMIC = MaterialIconSetWithRenderer("cosmic", null, true,
            HaloRenderItemBehavior(10, 0x33FFFFFF, { GTLiteTextures.HALO_NOISE }, true))

        val INFINITY = MaterialIconSetWithRenderer("infinity", null, true,
            HaloRenderItemBehavior(10, 0xFF000000.toInt(), { GTLiteTextures.HALO }, true))

        val HALKONITE = MaterialIconSetWithRenderer("halkonite", null, true,
            HaloRenderItemBehavior(10, 0xFF000000.toInt(), { GTLiteTextures.HALO }, true))

        val MAGNETIUM = MaterialIconSetWithRenderer("magnetium", null, true,
            HaloRenderItemBehavior(10, 0xF8F8D500.toInt(), { GTLiteTextures.HALO }, true))

        val MAGMATTER = MaterialIconSetWithRenderer("magmatter", null, true,
            HaloRenderItemBehavior(10, 0x33FFFFFF, { GTLiteTextures.HALO_NOISE }, true))

        val GLITCH = MaterialIconSetWithRenderer("glitch", null, true,
            HaloRenderItemBehavior(10, 0xFF000000.toInt(), { GTLiteTextures.HALO }, true))

        val BLACK_DWARF = MaterialIconSetWithRenderer("black_dwarf", null, true,
            HaloRenderItemBehavior(10, 0xFF000000.toInt(), { GTLiteTextures.HALO }, true))

        val WHITE_DWARF = MaterialIconSetWithRenderer("white_dwarf", null, true,
            HaloRenderItemBehavior(10, 0xFFFFFFFF.toInt(), { GTLiteTextures.HALO }, true))

        val ETERNITY = MaterialIconSetWithRenderer("eternity", null, true,
            HaloRenderItemBehavior(10, 0xFF000000.toInt(), { GTLiteTextures.HALO }, true))

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