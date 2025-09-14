package gregtechlite.gtlitecore.api.unification.material.info

import gregtech.api.unification.material.Materials.BismuthBronze
import gregtech.api.unification.material.Materials.Lutetium
import gregtech.api.unification.material.Materials.Nobelium
import gregtech.api.unification.material.Materials.Promethium
import gregtech.api.unification.material.Materials.Terbium
import gregtech.api.unification.material.Materials.Thulium
import gregtech.api.unification.material.Materials.Uranium238
import gregtech.api.unification.material.info.MaterialIconSet
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteTextures
import gregtechlite.gtlitecore.common.item.behavior.HaloRenderItemBehavior

object GTLiteMaterialIconSet
{

    // @formatter:off

    // region Single Texture IconSets

    @JvmField
    val PYROTHEUM = iconSet("pyrotheum")

    @JvmField
    val CRYOTHEUM = iconSet("cryotheum")

    @JvmField
    val PETROTHEUM = iconSet("petrotheum")

    @JvmField
    val AEROTHEUM = iconSet("aerotheum")

    @JvmField
    val MAGNETO = iconSet("magneto")

    @JvmField
    val NANOPARTICLES = iconSet("nanoparticles")

    @JvmField
    val ROASTED = iconSet("roasted")

    @JvmField
    val SUPERCRITICAL = iconSet("supercritical")

    @JvmField
    val DEGENERATE = iconSet("degenerate")

    // endregion

    // region Completed Texture IconSets

    @JvmField
    val BEDROCKIUM = iconSet("bedrockium")

    @JvmField
    val ENRICHED = iconSet("enriched")

    @JvmField
    val ORGANIC = iconSet("organic")

    @JvmField
    val SPACETIME = iconSet("spacetime")

    @JvmField
    val MHDCSM = iconSet("mhdcsm")

    @JvmField
    val WHITE_DWARF = iconSet("white_dwarf")

    @JvmField
    val UNIVERSIUM = iconSet("universium")

    @JvmField
    val CHROMATIC = iconSet("chromatic")

    @JvmField
    val OMNIUM = iconSet("omnium")

    @JvmField
    val MAGNETIUM = iconSet("magnetium")

    @JvmStatic
    val WAX = iconSet("wax")

    @JvmStatic
    val DARKMATTER = iconSet("darkmatter")

    @JvmStatic
    val REDMATTER = iconSet("redmatter")

    @JvmStatic
    val QUANTUM = iconSet("quantum")

    // endregion

    // region Renderer Suitable Texture IconSets

    @JvmField
    val COSMIC = MaterialIconSetWithRenderer("cosmic", null, true,
        HaloRenderItemBehavior(10, 0x33FFFFFF, { GTLiteTextures.HALO_NOISE },true))

    @JvmField
    val INFINITY = MaterialIconSetWithRenderer("infinity", null, true,
        HaloRenderItemBehavior(10, 0xFF000000.toInt(), { GTLiteTextures.HALO }, true))

    @JvmField
    val HALKONITE = MaterialIconSetWithRenderer("halkonite", null, true,
        HaloRenderItemBehavior(10, 0xFF000000.toInt(), { GTLiteTextures.HALO }, true))

    @JvmField
    val MAGMATTER = MaterialIconSetWithRenderer("magmatter", null, true,
        HaloRenderItemBehavior(10, 0x33FFFFFF, { GTLiteTextures.HALO_NOISE }, true))

    @JvmField
    val GLITCH = MaterialIconSetWithRenderer("glitch", null, true,
        HaloRenderItemBehavior(10, 0xFF000000.toInt(), { GTLiteTextures.HALO }, true))

    @JvmField
    val ETERNITY = MaterialIconSetWithRenderer("eternity", null, true,
        HaloRenderItemBehavior(10, 0xFF000000.toInt(), {GTLiteTextures.HALO}, true))

    // endregion

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

    private fun iconSet(name: String) = MaterialIconSet(name, null, true)

    // @formatter:on

}