package gregtechlite.gtlitecore.api.unification.ore

import gregtech.api.GTValues.M
import gregtech.api.unification.material.Material
import gregtech.api.unification.material.Materials.Graphite
import gregtech.api.unification.material.Materials.Quartzite
import gregtech.api.unification.material.Materials.Steel
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_BOLT_SCREW
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_FRAME
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_PLATE
import gregtech.api.unification.ore.OrePrefix
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.dustSmall
import gregtech.api.unification.ore.OrePrefix.dustTiny
import gregtech.api.unification.ore.OrePrefix.ingotHot
import gregtech.api.unification.ore.OrePrefix.nugget
import gregtech.api.unification.ore.OrePrefix.toolHeadDrill
import gregtech.api.unification.ore.OrePrefix.turbineBlade
import gregtech.api.unification.stack.MaterialStack
import gregtech.common.ConfigHolder
import gregtech.common.items.MetaItems
import gregtechlite.gtlitecore.api.GTLiteLog
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BlazingPyrotheum
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BlueSchist
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Creon
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.GelidCryotheum
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.GreenSchist
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HalkoniteSteel
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HarmonicPhononMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Kimberlite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Komatiite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Limestone
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MagMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Magnetium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MagnetohydrodynamicallyConstrainedStarMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Mellion
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.RoastedSphalerite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Shale
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Slate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TectonicPetrotheum
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TranscendentMetal
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ZSM5
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ZephyreanAerotheum
import gregtechlite.gtlitecore.api.unification.material.info.GTLiteMaterialFlags.GENERATE_NANITE
import gregtechlite.gtlitecore.api.unification.ore.GTLiteConditions.hasCrystalProperties
import gregtechlite.gtlitecore.api.unification.ore.GTLiteConditions.hasFuelRodProperties
import gregtechlite.gtlitecore.api.unification.ore.GTLiteConditions.hasGemProperty
import gregtechlite.gtlitecore.api.unification.ore.GTLiteConditions.hasOreProperty
import gregtechlite.gtlitecore.api.unification.ore.OrePrefixBuilder.Companion.addOrePrefix
import gregtech.api.unification.material.info.MaterialIconType.ore as oreIcon
import gregtechlite.gtlitecore.api.unification.material.info.GTLiteMaterialIconType.boule as bouleIcon
import gregtechlite.gtlitecore.api.unification.material.info.GTLiteMaterialIconType.fuelRod as fuelRodIcon
import gregtechlite.gtlitecore.api.unification.material.info.GTLiteMaterialIconType.fuelRodDepleted as fuelRodDepletedIcon
import gregtechlite.gtlitecore.api.unification.material.info.GTLiteMaterialIconType.fuelRodEnriched as fuelRodEnrichedIcon
import gregtechlite.gtlitecore.api.unification.material.info.GTLiteMaterialIconType.fuelRodEnrichedDepleted as fuelRodEnrichedDepletedIcon
import gregtechlite.gtlitecore.api.unification.material.info.GTLiteMaterialIconType.fuelRodHighDensity as fuelRodHighDensityIcon
import gregtechlite.gtlitecore.api.unification.material.info.GTLiteMaterialIconType.fuelRodHighDensityDepleted as fuelRodHighDensityDepletedIcon
import gregtechlite.gtlitecore.api.unification.material.info.GTLiteMaterialIconType.gemSolitary as gemSolitaryIcon
import gregtechlite.gtlitecore.api.unification.material.info.GTLiteMaterialIconType.nanite as naniteIcon
import gregtechlite.gtlitecore.api.unification.material.info.GTLiteMaterialIconType.seedCrystal as seedCrystalIcon
import gregtechlite.gtlitecore.api.unification.material.info.GTLiteMaterialIconType.sheetedFrame as sheetedFrameIcon
import gregtechlite.gtlitecore.api.unification.material.info.GTLiteMaterialIconType.wallGt as wallGtIcon

object GTLiteOrePrefix
{

    // @formatter:off

    // region Material Prefixes

    @JvmField
    val gemSolitary = addOrePrefix("gemSolitary", true)
    {
        materialAmount = M * 8
        iconType = gemSolitaryIcon
        condition = hasGemProperty
    }

    @JvmField
    val oreLimestone = addOrePrefix("oreLimestone", true)
    {
        materialAmount = -1
        iconType = oreIcon
        condition = hasOreProperty
    }
    @JvmField
    val oreKomatiite = addOrePrefix("oreKomatiite", true)
    {
        materialAmount = -1
        iconType = oreIcon
        condition = hasOreProperty
    }

    @JvmField
    val oreGreenSchist = addOrePrefix("oreGreenSchist", true)
    {
        materialAmount = -1
        iconType = oreIcon
        condition = hasOreProperty
    }

    @JvmField
    val oreBlueSchist = addOrePrefix("oreBlueSchist", true)
    {
        materialAmount = -1
        iconType = oreIcon
        condition = hasOreProperty
    }

    @JvmField
    val oreKimberlite = addOrePrefix("oreKimberlite", true)
    {
        materialAmount = -1
        iconType = oreIcon
        condition = hasOreProperty
    }

    @JvmField
    val oreQuartzite = addOrePrefix("oreQuartzite", true)
    {
        materialAmount = -1
        iconType = oreIcon
        condition = hasOreProperty
    }

    @JvmField
    val oreSlate = addOrePrefix("oreSlate", true)
    {
        materialAmount = -1
        iconType = oreIcon
        condition = hasOreProperty
    }

    @JvmField
    val oreShale = addOrePrefix("oreShale", true)
    {
        materialAmount = -1
        iconType = oreIcon
        condition = hasOreProperty
    }

    @JvmField
    val sheetedFrame = addOrePrefix("sheetedFrame", true)
    {
        materialAmount = (M * 5) / 6
        iconType = sheetedFrameIcon
        condition = { it.hasFlag(GENERATE_FRAME) }
    }

    @JvmField
    val wallGt = addOrePrefix("wallGt", true)
    {
        materialAmount = (M * 4 + (M / 9) * 4) / 3
        iconType = wallGtIcon
        condition = { it.hasFlags(GENERATE_PLATE, GENERATE_BOLT_SCREW) }
    }

    @JvmField
    val seedCrystal = addOrePrefix("seedCrystal", true)
    {
        materialAmount = M / 9
        iconType = seedCrystalIcon
        condition = hasCrystalProperties
    }

    @JvmField
    val boule = addOrePrefix("boule", true)
    {
        materialAmount = M * 4
        iconType = bouleIcon
        condition = hasCrystalProperties
    }

    @JvmField
    val fuelRod = addOrePrefix("fuelRod", true)
    {
        materialAmount = M * 2
        iconType = fuelRodIcon
        condition = hasFuelRodProperties
    }

    @JvmField
    val fuelRodEnriched = addOrePrefix("fuelRodEnriched", true)
    {
        materialAmount = M * 4
        iconType = fuelRodEnrichedIcon
        condition = hasFuelRodProperties
    }

    @JvmField
    val fuelRodHighDensity = addOrePrefix("fuelRodHighDensity", true)
    {
        materialAmount = M * 8
        iconType = fuelRodHighDensityIcon
        condition = hasFuelRodProperties
    }

    @JvmField
    val fuelRodDepleted = addOrePrefix("fuelRodDepleted", true)
    {
        materialAmount = M * 2
        iconType = fuelRodDepletedIcon
        condition = hasFuelRodProperties
    }

    @JvmField
    val fuelRodEnrichedDepleted = addOrePrefix("fuelRodEnrichedDepleted", true)
    {
        materialAmount = M * 4
        iconType = fuelRodEnrichedDepletedIcon
        condition = hasFuelRodProperties
    }

    @JvmField
    val fuelRodHighDensityDepleted = addOrePrefix("fuelRodHighDensityDepleted", true)
    {
        materialAmount = M * 8
        iconType = fuelRodHighDensityDepletedIcon
        condition = hasFuelRodProperties
    }

    @JvmField
    val nanite = addOrePrefix("nanite", true)
    {
        materialAmount = -1
        iconType = naniteIcon
        condition = { it.hasFlag(GENERATE_NANITE) }
    }

    // endregion

    // region Ore Dictionary Prefixes

    @JvmField
    val glass = addOrePrefix("glass", true)
    {
        materialAmount = -1
    }

    // endregion

    fun setOrePrefixInfos()
    {
        // region Material Amount

        setMaterialAmount(turbineBlade, M * 6)

        // endregion

        // region Stack Size

        gemSolitary.maxStackSize = 8
        fuelRodEnriched.maxStackSize = 32
        fuelRodHighDensity.maxStackSize = 16
        fuelRodEnrichedDepleted.maxStackSize = 32
        fuelRodHighDensityDepleted.maxStackSize = 16

        // endregion

        // region Secondary Materials

        if (ConfigHolder.worldgen.allUniqueStoneTypes)
        {
            oreLimestone.addSecondaryMaterial(Limestone, M)
            oreKomatiite.addSecondaryMaterial(Komatiite, M)
            oreGreenSchist.addSecondaryMaterial(GreenSchist, M)
            oreBlueSchist.addSecondaryMaterial(BlueSchist, M)
            oreKimberlite.addSecondaryMaterial(Kimberlite, M)
            oreQuartzite.addSecondaryMaterial(Quartzite, M)
            oreSlate.addSecondaryMaterial(Slate, M)
            oreShale.addSecondaryMaterial(Shale, M)
        }

        toolHeadDrill.removeSecondaryMaterial(Steel, M * 4)

        // endregion

        // region Ignored Prefix

        dust.setIgnored(ZSM5)
        dust.setIgnored(HalkoniteSteel)
        dust.setIgnored(Magnetium)
        dust.setIgnored(MagMatter)
        dust.setIgnored(MagnetohydrodynamicallyConstrainedStarMatter)

        dustSmall.setIgnored(ZSM5)
        dustSmall.setIgnored(BlazingPyrotheum)
        dustSmall.setIgnored(GelidCryotheum)
        dustSmall.setIgnored(TectonicPetrotheum)
        dustSmall.setIgnored(ZephyreanAerotheum)
        dustSmall.setIgnored(RoastedSphalerite)
        dustSmall.setIgnored(HalkoniteSteel)
        dustSmall.setIgnored(Magnetium)
        dustSmall.setIgnored(MagMatter)
        dustSmall.setIgnored(MagnetohydrodynamicallyConstrainedStarMatter)

        dustTiny.setIgnored(ZSM5)
        dustTiny.setIgnored(BlazingPyrotheum)
        dustTiny.setIgnored(GelidCryotheum)
        dustTiny.setIgnored(TectonicPetrotheum)
        dustTiny.setIgnored(ZephyreanAerotheum)
        dustTiny.setIgnored(RoastedSphalerite)
        dustTiny.setIgnored(HalkoniteSteel)
        dustTiny.setIgnored(Magnetium)
        dustTiny.setIgnored(MagMatter)
        dustTiny.setIgnored(MagnetohydrodynamicallyConstrainedStarMatter)

        nugget.setIgnored(HalkoniteSteel)
        nugget.setIgnored(Magnetium)
        nugget.setIgnored(MagMatter)
        nugget.setIgnored(MagnetohydrodynamicallyConstrainedStarMatter)

        ingotHot.setIgnored(TranscendentMetal)
        ingotHot.setIgnored(HalkoniteSteel)
        ingotHot.setIgnored(Magnetium)
        ingotHot.setIgnored(Mellion)
        ingotHot.setIgnored(Creon)
        ingotHot.setIgnored(HarmonicPhononMatter)

        fuelRodDepleted.setIgnored(Graphite)

        fuelRodEnrichedDepleted.setIgnored(Graphite)

        fuelRodHighDensityDepleted.setIgnored(Graphite)

        // endregion
    }

    fun addToMetaItem()
    {
        MetaItems.addOrePrefix(gemSolitary)
        MetaItems.addOrePrefix(seedCrystal)
        MetaItems.addOrePrefix(boule)
        MetaItems.addOrePrefix(fuelRod)
        MetaItems.addOrePrefix(fuelRodEnriched)
        MetaItems.addOrePrefix(fuelRodHighDensity)
        MetaItems.addOrePrefix(fuelRodDepleted)
        MetaItems.addOrePrefix(fuelRodEnrichedDepleted)
        MetaItems.addOrePrefix(fuelRodHighDensityDepleted)
        MetaItems.addOrePrefix(nanite)
    }

}

/**
 * Adds secondary materials for the prefix.
 *
 * @param material The material of the material stack for the secondary material.
 * @param amount   The material amount of the material stack for the secondary material, use magic number.
 */
private fun OrePrefix.addSecondaryMaterial(material: Material, amount: Long)
{
    val ms = MaterialStack(material, amount)
    if (!secondaryMaterials.contains(ms))
        secondaryMaterials.add(ms)
}

/**
 * Removes existed secondary material for the prefix.
 *
 * @param material The material of the material stack in the secondary material.
 * @param amount   The material amount of the material stack in the secondary material, use magic number.
 */
private fun OrePrefix.removeSecondaryMaterial(material: Material, amount: Long)
{
    val ms = MaterialStack(material, amount)
    if (secondaryMaterials.contains(ms)) {
        secondaryMaterials.remove(ms)
    }
}

/**
 * Modifies `materialAmount` field of the `OrePrefix`.
 *
 * This method is used to adjust recycling recipe outputs for specific prefixes.
 *
 * @param prefix    The prefix to modified.
 * @param newAmount The new material amount value.
 */
@Suppress("SameParameterValue")
private fun setMaterialAmount(prefix: OrePrefix, newAmount: Long) = runCatching {
    OrePrefix::class.java.getDeclaredField("materialAmount").apply {
        isAccessible = true
        setLong(prefix, newAmount)
    }
}.onFailure { GTLiteLog.logger.error("Cannot set materialAmount for the OrePrefix $prefix with new amount $newAmount") }
