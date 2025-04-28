package magicbook.gtlitecore.api.unification.ore

import gregtech.api.GTValues.M
import gregtech.api.unification.material.Materials
import gregtech.api.unification.material.info.MaterialFlags
import gregtech.api.unification.material.info.MaterialIconType
import gregtech.api.unification.ore.OrePrefix
import gregtech.api.unification.stack.MaterialStack
import gregtech.common.ConfigHolder
import gregtech.common.items.MetaItems
import magicbook.gtlitecore.api.unification.GTLiteMaterials
import magicbook.gtlitecore.api.unification.material.infos.GTLiteMaterialFlags
import magicbook.gtlitecore.api.unification.material.infos.GTLiteMaterialIconType

@Suppress("MISSING_DEPENDENCY_CLASS")
class GTLiteOrePrefix
{

    companion object
    {

        @JvmField
        val gemSolitary = OrePrefix("gemSolitary", M * 8, null,
            GTLiteMaterialIconType.gemSolitary, OrePrefix.Flags.ENABLE_UNIFICATION,
            OrePrefix.Conditions.hasGemProperty)

        @JvmField
        val oreLimestone: OrePrefix = OrePrefix("oreLimestone", -1, null,
            MaterialIconType.ore, OrePrefix.Flags.ENABLE_UNIFICATION,
            OrePrefix.Conditions.hasOreProperty)

        @JvmField
        val oreKomatiite: OrePrefix = OrePrefix("oreKomatiite", -1, null,
            MaterialIconType.ore, OrePrefix.Flags.ENABLE_UNIFICATION,
            OrePrefix.Conditions.hasOreProperty)

        @JvmField
        val oreGreenSchist: OrePrefix = OrePrefix("oreGreenSchist", -1, null,
            MaterialIconType.ore, OrePrefix.Flags.ENABLE_UNIFICATION,
            OrePrefix.Conditions.hasOreProperty)

        @JvmField
        val oreBlueSchist: OrePrefix = OrePrefix("oreBlueSchist", -1, null,
            MaterialIconType.ore, OrePrefix.Flags.ENABLE_UNIFICATION,
            OrePrefix.Conditions.hasOreProperty)

        @JvmField
        val oreKimberlite: OrePrefix = OrePrefix("oreKimberlite", -1, null,
            MaterialIconType.ore, OrePrefix.Flags.ENABLE_UNIFICATION,
            OrePrefix.Conditions.hasOreProperty)

        @JvmField
        val oreQuartzite: OrePrefix = OrePrefix("oreQuartzite", -1, null,
            MaterialIconType.ore, OrePrefix.Flags.ENABLE_UNIFICATION,
            OrePrefix.Conditions.hasOreProperty)

        @JvmField
        val oreSlate: OrePrefix = OrePrefix("oreSlate", -1, null,
            MaterialIconType.ore, OrePrefix.Flags.ENABLE_UNIFICATION,
            OrePrefix.Conditions.hasOreProperty)

        @JvmField
        val oreShale: OrePrefix = OrePrefix("oreShale", -1, null,
            MaterialIconType.ore, OrePrefix.Flags.ENABLE_UNIFICATION,
            OrePrefix.Conditions.hasOreProperty)

        @JvmField
        val sheetedFrame: OrePrefix = OrePrefix("sheetedFrame", (M * 5) / 6, null,
            GTLiteMaterialIconType.sheetedFrame, OrePrefix.Flags.ENABLE_UNIFICATION) { m ->
            m.hasFlag(MaterialFlags.GENERATE_FRAME) }

        @JvmField
        val wallGt: OrePrefix = OrePrefix("wallGt", (M * 4 + (M / 9) * 4) / 3, null,
            GTLiteMaterialIconType.wallGt, OrePrefix.Flags.ENABLE_UNIFICATION) { m ->
            m.hasFlags(MaterialFlags.GENERATE_PLATE, MaterialFlags.GENERATE_BOLT_SCREW) }

        @JvmField
        val seedCrystal: OrePrefix = OrePrefix("seedCrystal", M / 9, null,
            GTLiteMaterialIconType.seedCrystal, OrePrefix.Flags.ENABLE_UNIFICATION,
            OrePrefix.Conditions.hasGemProperty.and { m -> m.hasFlags(GTLiteMaterialFlags.GENERATE_BOULE)
                    || (m.hasFlags(MaterialFlags.CRYSTALLIZABLE) && !m.hasFlags(GTLiteMaterialFlags.DISABLE_CRYSTALLIZATION))})

        @JvmField
        val boule: OrePrefix = OrePrefix("boule", M * 4, null,
            GTLiteMaterialIconType.boule, OrePrefix.Flags.ENABLE_UNIFICATION,
            OrePrefix.Conditions.hasGemProperty.and { m -> m.hasFlags(GTLiteMaterialFlags.GENERATE_BOULE)
                    || (m.hasFlags(MaterialFlags.CRYSTALLIZABLE) && !m.hasFlags(GTLiteMaterialFlags.DISABLE_CRYSTALLIZATION))})

        @JvmField
        val fuelRodSingle: OrePrefix = OrePrefix("fuelRodSingle", M * 2, null,
            GTLiteMaterialIconType.fuelRodSingle, OrePrefix.Flags.ENABLE_UNIFICATION,
            OrePrefix.Conditions.hasIngotProperty.and { m -> m.hasFlags(GTLiteMaterialFlags.GENERATE_FUEL_ROD) })

        @JvmField
        val fuelRodDouble: OrePrefix = OrePrefix("fuelRodDouble", M * 4, null,
            GTLiteMaterialIconType.fuelRodDouble, OrePrefix.Flags.ENABLE_UNIFICATION,
            OrePrefix.Conditions.hasIngotProperty.and { m -> m.hasFlags(GTLiteMaterialFlags.GENERATE_FUEL_ROD) })

        @JvmField
        val fuelRodQuadruple: OrePrefix = OrePrefix("fuelRodQuadruple", M * 8, null,
            GTLiteMaterialIconType.fuelRodQuadruple, OrePrefix.Flags.ENABLE_UNIFICATION,
            OrePrefix.Conditions.hasIngotProperty.and { m -> m.hasFlags(GTLiteMaterialFlags.GENERATE_FUEL_ROD) })

        @JvmField
        val fuelRodDepletedSingle: OrePrefix = OrePrefix("fuelRodDepletedSingle", M * 2, null,
            GTLiteMaterialIconType.fuelRodDepletedSingle, OrePrefix.Flags.ENABLE_UNIFICATION,
            OrePrefix.Conditions.hasIngotProperty.and { m -> m.hasFlags(GTLiteMaterialFlags.GENERATE_FUEL_ROD) })

        @JvmField
        val fuelRodDepletedDouble: OrePrefix = OrePrefix("fuelRodDepletedDouble", M * 4, null,
            GTLiteMaterialIconType.fuelRodDouble, OrePrefix.Flags.ENABLE_UNIFICATION,
            OrePrefix.Conditions.hasIngotProperty.and { m -> m.hasFlags(GTLiteMaterialFlags.GENERATE_FUEL_ROD) })

        @JvmField
        val fuelRodDepletedQuadruple: OrePrefix = OrePrefix("fuelRodDepletedQuadruple", M * 8, null,
            GTLiteMaterialIconType.fuelRodQuadruple, OrePrefix.Flags.ENABLE_UNIFICATION,
            OrePrefix.Conditions.hasIngotProperty.and { m -> m.hasFlags(GTLiteMaterialFlags.GENERATE_FUEL_ROD) })

        fun setOrePrefixInfos()
        {
            gemSolitary.maxStackSize = 8
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

            // Deleted ZSM-5 dusts.
            OrePrefix.dust.setIgnored(GTLiteMaterials.ZSM5)
            OrePrefix.dustSmall.setIgnored(GTLiteMaterials.ZSM5)
            OrePrefix.dustTiny.setIgnored(GTLiteMaterials.ZSM5)

            // Deleted Blazing Pyrotheum dusts.
            OrePrefix.dustSmall.setIgnored(GTLiteMaterials.BlazingPyrotheum)
            OrePrefix.dustTiny.setIgnored(GTLiteMaterials.BlazingPyrotheum)

            // Deleted Gelid Cryotheum dusts.
            OrePrefix.dustSmall.setIgnored(GTLiteMaterials.GelidCryotheum)
            OrePrefix.dustTiny.setIgnored(GTLiteMaterials.GelidCryotheum)

            // Deleted Tectonic Petrotheum dusts.
            OrePrefix.dustSmall.setIgnored(GTLiteMaterials.TectonicPetrotheum)
            OrePrefix.dustTiny.setIgnored(GTLiteMaterials.TectonicPetrotheum)

            // Deleted Zephyrean Aerotheum dusts.
            OrePrefix.dustSmall.setIgnored(GTLiteMaterials.ZephyreanAerotheum)
            OrePrefix.dustTiny.setIgnored(GTLiteMaterials.ZephyreanAerotheum)

            // Deleted RoastedSphalerite dusts.
            OrePrefix.dustSmall.setIgnored(GTLiteMaterials.RoastedSphalerite)
            OrePrefix.dustTiny.setIgnored(GTLiteMaterials.RoastedSphalerite)

            // Deleted HalkoniteSteel dusts, nugget and hot ingot.
            OrePrefix.dust.setIgnored(GTLiteMaterials.HalkoniteSteel)
            OrePrefix.dustSmall.setIgnored(GTLiteMaterials.HalkoniteSteel)
            OrePrefix.dustTiny.setIgnored(GTLiteMaterials.HalkoniteSteel)
            OrePrefix.nugget.setIgnored(GTLiteMaterials.HalkoniteSteel)
            OrePrefix.ingotHot.setIgnored(GTLiteMaterials.HalkoniteSteel)
        }

        // Let these setters be later than setOrePrefixInfos() because recipe generated at high
        // priority is correct, we want it has same priority of RecipeManager (priority = late),
        // so we add a postSetter which will be loaded after RecipeManager initialized to ensure
        // that the recipe which be removed do not be generated.
        fun postSetOrePrefixInfos()
        {
            // Because we add Tenorite (CuO ore) to change CuO dust, so this material is deprecated.
            // This material is ensured to remove all related recipes yet. Ignored all ore prefixes of CuO ^^.
            OrePrefix.dust.setIgnored(Materials.CupricOxide)
            OrePrefix.dustSmall.setIgnored(Materials.CupricOxide)
            OrePrefix.dustTiny.setIgnored(Materials.CupricOxide)

            // Because we add Baddeleyite (ZrO2 ore) to change ZrO2 dust, so this material is
            // deprecated. This material is ensured to remove all related recipes yet. Ignored all
            // ore prefixes of ZrO2 ^^.
            OrePrefix.dust.setIgnored(Materials.Zirconia)
            OrePrefix.dustSmall.setIgnored(Materials.Zirconia)
            OrePrefix.dustTiny.setIgnored(Materials.Zirconia)
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
        }

    }

}