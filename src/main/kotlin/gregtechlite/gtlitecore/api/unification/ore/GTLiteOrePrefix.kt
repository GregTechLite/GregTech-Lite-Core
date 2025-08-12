package gregtechlite.gtlitecore.api.unification.ore

import gregtech.api.GTValues.M
import gregtech.api.unification.material.Materials
import gregtech.api.unification.material.info.MaterialFlags
import gregtech.api.unification.material.info.MaterialIconType
import gregtech.api.unification.ore.OrePrefix
import gregtech.api.unification.ore.OrePrefix.Conditions
import gregtech.api.unification.stack.MaterialStack
import gregtech.common.ConfigHolder
import gregtech.common.items.MetaItems
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials
import gregtechlite.gtlitecore.api.unification.material.builder.OrePrefixBuilder
import gregtechlite.gtlitecore.api.unification.material.info.GTLiteMaterialFlags
import gregtechlite.gtlitecore.api.unification.material.info.GTLiteMaterialIconType

object GTLiteOrePrefix
{

    // @formatter:off

    // region Material Prefixes

    @JvmField
    val gemSolitary: OrePrefix = OrePrefixBuilder.builder("gemSolitary")
        .materialAmount(M * 8)
        .iconType(GTLiteMaterialIconType.gemSolitary)
        .enableUnification()
        .condition(Conditions.hasGemProperty)
        .build()

    @JvmField
    val oreLimestone= OrePrefixBuilder.builder("oreLimestone")
        .materialAmount(-1)
        .iconType(MaterialIconType.ore)
        .enableUnification()
        .condition(Conditions.hasOreProperty)
        .build()

    @JvmField
    val oreKomatiite: OrePrefix = OrePrefixBuilder.builder("oreKomatiite")
        .materialAmount(-1)
        .iconType(MaterialIconType.ore)
        .enableUnification()
        .condition(Conditions.hasOreProperty)
        .build()

    @JvmField
    val oreGreenSchist: OrePrefix = OrePrefixBuilder.builder("oreGreenSchist")
        .materialAmount(-1)
        .iconType(MaterialIconType.ore)
        .enableUnification()
        .condition(Conditions.hasOreProperty)
        .build()

    @JvmField
    val oreBlueSchist: OrePrefix = OrePrefixBuilder.builder("oreBlueSchist")
        .materialAmount(-1)
        .iconType(MaterialIconType.ore)
        .enableUnification()
        .condition(Conditions.hasOreProperty)
        .build()

    @JvmField
    val oreKimberlite: OrePrefix = OrePrefixBuilder.builder("oreKimberlite")
        .materialAmount(-1)
        .iconType(MaterialIconType.ore)
        .enableUnification()
        .condition(Conditions.hasOreProperty)
        .build()

    @JvmField
    val oreQuartzite: OrePrefix = OrePrefixBuilder.builder("oreQuartzite")
        .materialAmount(-1)
        .iconType(MaterialIconType.ore)
        .enableUnification()
        .condition(Conditions.hasOreProperty)
        .build()

    @JvmField
    val oreSlate: OrePrefix = OrePrefixBuilder.builder("oreSlate")
        .materialAmount(-1)
        .iconType(MaterialIconType.ore)
        .enableUnification()
        .condition(Conditions.hasOreProperty)
        .build()

    @JvmField
    val oreShale: OrePrefix = OrePrefixBuilder.builder("oreShale")
        .materialAmount(-1)
        .iconType(MaterialIconType.ore)
        .enableUnification()
        .condition(Conditions.hasOreProperty)
        .build()

    @JvmField
    val sheetedFrame: OrePrefix = OrePrefixBuilder.builder("sheetedFrame")
        .materialAmount((M * 5) / 6)
        .iconType(GTLiteMaterialIconType.sheetedFrame)
        .enableUnification()
        .condition { mat -> mat.hasFlags(MaterialFlags.GENERATE_FRAME) }
        .build()

    @JvmField
    val wallGt: OrePrefix = OrePrefixBuilder.builder("wallGt")
        .materialAmount((M * 4 + (M / 9) * 4) / 3)
        .iconType(GTLiteMaterialIconType.wallGt)
        .enableUnification()
        .condition { mat -> mat.hasFlags(MaterialFlags.GENERATE_PLATE, MaterialFlags.GENERATE_BOLT_SCREW) }
        .build()

    @JvmField
    val seedCrystal: OrePrefix = OrePrefixBuilder.builder("seedCrystal")
        .materialAmount(M / 9)
        .iconType(GTLiteMaterialIconType.seedCrystal)
        .enableUnification()
        .condition(GTLiteConditions.hasCrystalProperties)
        .build()

    @JvmField
    val boule: OrePrefix = OrePrefixBuilder.builder("boule")
        .materialAmount(M * 4)
        .iconType(GTLiteMaterialIconType.boule)
        .enableUnification()
        .condition(GTLiteConditions.hasCrystalProperties)
        .build()

    @JvmField
    val fuelRodSingle: OrePrefix = OrePrefixBuilder.builder("fuelRodSingle")
        .materialAmount(M * 2)
        .iconType(GTLiteMaterialIconType.fuelRodSingle)
        .enableUnification()
        .condition(GTLiteConditions.hasFuelRodProperties)
        .build()

    @JvmField
    val fuelRodDouble: OrePrefix = OrePrefixBuilder.builder("fuelRodDouble")
        .materialAmount(M * 4)
        .iconType(GTLiteMaterialIconType.fuelRodDouble)
        .enableUnification()
        .condition(GTLiteConditions.hasFuelRodProperties)
        .build()

    @JvmField
    val fuelRodQuadruple: OrePrefix = OrePrefixBuilder.builder("fuelRodQuadruple")
        .materialAmount(M * 8)
        .iconType(GTLiteMaterialIconType.fuelRodQuadruple)
        .enableUnification()
        .condition(GTLiteConditions.hasFuelRodProperties)
        .build()

    @JvmField
    val fuelRodDepletedSingle: OrePrefix = OrePrefixBuilder.builder("fuelRodDepletedSingle")
        .materialAmount(M * 2)
        .iconType(GTLiteMaterialIconType.fuelRodDepletedSingle)
        .enableUnification()
        .condition(GTLiteConditions.hasFuelRodProperties)
        .build()

    @JvmField
    val fuelRodDepletedDouble: OrePrefix = OrePrefixBuilder.builder("fuelRodDepletedDouble")
        .materialAmount(M * 4)
        .iconType(GTLiteMaterialIconType.fuelRodDouble)
        .enableUnification()
        .condition(GTLiteConditions.hasFuelRodProperties)
        .build()

    @JvmField
    val fuelRodDepletedQuadruple: OrePrefix = OrePrefixBuilder.builder("fuelRodDepletedQuadruple")
        .materialAmount(M * 8)
        .iconType(GTLiteMaterialIconType.fuelRodQuadruple)
        .enableUnification()
        .condition(GTLiteConditions.hasFuelRodProperties)
        .build()

    @JvmField
    val nanite: OrePrefix = OrePrefixBuilder.builder("nanite")
        .materialAmount(-1)
        .iconType(GTLiteMaterialIconType.nanite)
        .enableUnification()
        .condition { mat -> mat.hasFlag(GTLiteMaterialFlags.GENERATE_NANITE) }
        .build()

    // endregion

    // region Ore Dictionary Prefixes

    @JvmField
    val glass: OrePrefix = OrePrefixBuilder.builder("glass")
        .materialAmount(-1)
        .enableUnification()
        .build()

    // endregion

    fun setOrePrefixInfos()
    {
        gemSolitary.maxStackSize = 8
        fuelRodDouble.maxStackSize = 32
        fuelRodQuadruple.maxStackSize = 16
        fuelRodDepletedDouble.maxStackSize = 32
        fuelRodDepletedQuadruple.maxStackSize = 16

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

    }

    fun addToMetaItem()
    {
        MetaItems.addOrePrefix(gemSolitary)
        MetaItems.addOrePrefix(seedCrystal)
        MetaItems.addOrePrefix(boule)
        MetaItems.addOrePrefix(fuelRodSingle)
        MetaItems.addOrePrefix(fuelRodDouble)
        MetaItems.addOrePrefix(fuelRodQuadruple)
        MetaItems.addOrePrefix(fuelRodDepletedSingle)
        MetaItems.addOrePrefix(fuelRodDepletedDouble)
        MetaItems.addOrePrefix(fuelRodDepletedQuadruple)
        MetaItems.addOrePrefix(nanite)
    }

}