package gregtechlite.gtlitecore.api.unification.ore

import gregtech.api.GTValues.M
import gregtech.api.unification.material.Materials
import gregtech.api.unification.material.info.MaterialFlags
import gregtech.api.unification.ore.OrePrefix
import gregtech.api.unification.stack.MaterialStack
import gregtech.common.ConfigHolder
import gregtech.common.items.MetaItems
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials
import gregtechlite.gtlitecore.api.unification.material.info.GTLiteMaterialFlags
import gregtechlite.gtlitecore.api.unification.material.info.GTLiteMaterialIconType
import gregtechlite.gtlitecore.api.unification.ore.OrePrefixBuilder.Companion.addPrefix
import gregtechlite.gtlitecore.api.unification.ore.OrePrefixBuilder.Companion.addOrePrefix

object GTLiteOrePrefix
{

    // @formatter:off

    // region Material Prefixes

    @JvmField
    val gemSolitary = addPrefix("gemSolitary", true)
    {
        materialAmount = M * 8
        iconType = GTLiteMaterialIconType.gemSolitary
        condition = GTLiteConditions.hasGemProperty
    }

    @JvmField
    val oreLimestone = addOrePrefix("oreLimestone")
    @JvmField
    val oreKomatiite = addOrePrefix("oreKomatiite")
    @JvmField
    val oreGreenSchist = addOrePrefix("oreGreenSchist")
    @JvmField
    val oreBlueSchist = addOrePrefix("oreBlueSchist")
    @JvmField
    val oreKimberlite = addOrePrefix("oreKimberlite")
    @JvmField
    val oreQuartzite = addOrePrefix("oreQuartzite")
    @JvmField
    val oreSlate = addOrePrefix("oreSlate")
    @JvmField
    val oreShale = addOrePrefix("oreShale")

    @JvmField
    val sheetedFrame = addPrefix("sheetedFrame", true)
    {
        materialAmount = (M * 5) / 6
        iconType = GTLiteMaterialIconType.sheetedFrame
        condition = { it.hasFlag(MaterialFlags.GENERATE_FRAME) }
    }

    @JvmField
    val wallGt = addPrefix("wallGt", true)
    {
        materialAmount = (M * 4 + (M / 9) * 4) / 3
        iconType = GTLiteMaterialIconType.wallGt
        condition = { it.hasFlags(MaterialFlags.GENERATE_PLATE, MaterialFlags.GENERATE_BOLT_SCREW) }
    }

    @JvmField
    val seedCrystal = addPrefix("seedCrystal", true)
    {
        materialAmount = M / 9
        iconType = GTLiteMaterialIconType.seedCrystal
        condition = GTLiteConditions.hasCrystalProperties
    }

    @JvmField
    val boule = addPrefix("boule", true)
    {
        materialAmount = M * 4
        iconType = GTLiteMaterialIconType.boule
        condition = GTLiteConditions.hasCrystalProperties
    }

    @JvmField
    val fuelRod = addPrefix("fuelRod", true)
    {
        materialAmount = M * 2
        iconType = GTLiteMaterialIconType.fuelRod
        condition = GTLiteConditions.hasFuelRodProperties
    }

    @JvmField
    val fuelRodEnriched = addPrefix("fuelRodEnriched", true)
    {
        materialAmount = M * 4
        iconType = GTLiteMaterialIconType.fuelRodEnriched
        condition = GTLiteConditions.hasFuelRodProperties
    }

    @JvmField
    val fuelRodHighDensity = addPrefix("fuelRodHighDensity", true)
    {
        materialAmount = M * 8
        iconType = GTLiteMaterialIconType.fuelRodHighDensity
        condition = GTLiteConditions.hasFuelRodProperties
    }

    @JvmField
    val fuelRodDepleted = addPrefix("fuelRodDepleted", true)
    {
        materialAmount = M * 2
        iconType = GTLiteMaterialIconType.fuelRodDepleted
        condition = GTLiteConditions.hasFuelRodProperties
    }

    @JvmField
    val fuelRodEnrichedDepleted = addPrefix("fuelRodEnrichedDepleted", true)
    {
        materialAmount = M * 4
        iconType = GTLiteMaterialIconType.fuelRodEnrichedDepleted
        condition = GTLiteConditions.hasFuelRodProperties
    }

    @JvmField
    val fuelRodHighDensityDepleted = addPrefix("fuelRodHighDensityDepleted", true)
    {
        materialAmount = M * 8
        iconType = GTLiteMaterialIconType.fuelRodHighDensityDepleted
        condition = GTLiteConditions.hasFuelRodProperties
    }

    @JvmField
    val nanite = addPrefix("nanite", true)
    {
        materialAmount = -1
        iconType = GTLiteMaterialIconType.nanite
        condition = { it.hasFlag(GTLiteMaterialFlags.GENERATE_NANITE) }
    }

    // endregion

    // region Ore Dictionary Prefixes

    @JvmField
    val glass = addPrefix("glass", true)
    {
        materialAmount = -1
    }

    // endregion

    fun setOrePrefixInfos()
    {
        gemSolitary.maxStackSize = 8
        fuelRodEnriched.maxStackSize = 32
        fuelRodHighDensity.maxStackSize = 16
        fuelRodEnrichedDepleted.maxStackSize = 32
        fuelRodHighDensityDepleted.maxStackSize = 16

        /* ---------------------------------------------------------------------------------------------------------- */

        if (ConfigHolder.worldgen.allUniqueStoneTypes)
        {
            oreLimestone.addSecondaryMaterial(MaterialStack(GTLiteMaterials.Limestone, M))
            oreKomatiite.addSecondaryMaterial(MaterialStack(GTLiteMaterials.Komatiite, M))
            oreGreenSchist.addSecondaryMaterial(MaterialStack(GTLiteMaterials.GreenSchist, M))
            oreBlueSchist.addSecondaryMaterial(MaterialStack(GTLiteMaterials.BlueSchist, M))
            oreKimberlite.addSecondaryMaterial(MaterialStack(GTLiteMaterials.Kimberlite, M))
            oreQuartzite.addSecondaryMaterial(MaterialStack(Materials.Quartzite, M))
            oreSlate.addSecondaryMaterial(MaterialStack(GTLiteMaterials.Slate, M))
            oreShale.addSecondaryMaterial(MaterialStack(GTLiteMaterials.Shale, M))
        }

        /* ---------------------------------------------------------------------------------------------------------- */

        OrePrefix.dust.setIgnored(GTLiteMaterials.ZSM5)
        OrePrefix.dust.setIgnored(GTLiteMaterials.HalkoniteSteel)
        OrePrefix.dust.setIgnored(GTLiteMaterials.Magnetium)
        OrePrefix.dust.setIgnored(GTLiteMaterials.MagMatter)
        OrePrefix.dust.setIgnored(GTLiteMaterials.MagnetohydrodynamicallyConstrainedStarMatter)

        OrePrefix.dustSmall.setIgnored(GTLiteMaterials.ZSM5)
        OrePrefix.dustSmall.setIgnored(GTLiteMaterials.BlazingPyrotheum)
        OrePrefix.dustSmall.setIgnored(GTLiteMaterials.GelidCryotheum)
        OrePrefix.dustSmall.setIgnored(GTLiteMaterials.TectonicPetrotheum)
        OrePrefix.dustSmall.setIgnored(GTLiteMaterials.ZephyreanAerotheum)
        OrePrefix.dustSmall.setIgnored(GTLiteMaterials.RoastedSphalerite)
        OrePrefix.dustSmall.setIgnored(GTLiteMaterials.HalkoniteSteel)
        OrePrefix.dustSmall.setIgnored(GTLiteMaterials.Magnetium)
        OrePrefix.dustSmall.setIgnored(GTLiteMaterials.MagMatter)
        OrePrefix.dustSmall.setIgnored(GTLiteMaterials.MagnetohydrodynamicallyConstrainedStarMatter)

        OrePrefix.dustTiny.setIgnored(GTLiteMaterials.ZSM5)
        OrePrefix.dustTiny.setIgnored(GTLiteMaterials.BlazingPyrotheum)
        OrePrefix.dustTiny.setIgnored(GTLiteMaterials.GelidCryotheum)
        OrePrefix.dustTiny.setIgnored(GTLiteMaterials.TectonicPetrotheum)
        OrePrefix.dustTiny.setIgnored(GTLiteMaterials.ZephyreanAerotheum)
        OrePrefix.dustTiny.setIgnored(GTLiteMaterials.RoastedSphalerite)
        OrePrefix.dustTiny.setIgnored(GTLiteMaterials.HalkoniteSteel)
        OrePrefix.dustTiny.setIgnored(GTLiteMaterials.Magnetium)
        OrePrefix.dustTiny.setIgnored(GTLiteMaterials.MagMatter)
        OrePrefix.dustTiny.setIgnored(GTLiteMaterials.MagnetohydrodynamicallyConstrainedStarMatter)

        OrePrefix.nugget.setIgnored(GTLiteMaterials.HalkoniteSteel)
        OrePrefix.nugget.setIgnored(GTLiteMaterials.Magnetium)
        OrePrefix.nugget.setIgnored(GTLiteMaterials.MagMatter)
        OrePrefix.nugget.setIgnored(GTLiteMaterials.MagnetohydrodynamicallyConstrainedStarMatter)

        OrePrefix.ingotHot.setIgnored(GTLiteMaterials.TranscendentMetal)
        OrePrefix.ingotHot.setIgnored(GTLiteMaterials.HalkoniteSteel)
        OrePrefix.ingotHot.setIgnored(GTLiteMaterials.Magnetium)
        OrePrefix.ingotHot.setIgnored(GTLiteMaterials.Mellion)
        OrePrefix.ingotHot.setIgnored(GTLiteMaterials.Creon)
        OrePrefix.ingotHot.setIgnored(GTLiteMaterials.HarmonicPhononMatter)

        fuelRodDepleted.setIgnored(Materials.Graphite)

        fuelRodEnrichedDepleted.setIgnored(Materials.Graphite)

        fuelRodHighDensityDepleted.setIgnored(Materials.Graphite)
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