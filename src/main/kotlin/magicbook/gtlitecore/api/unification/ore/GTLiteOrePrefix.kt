package magicbook.gtlitecore.api.unification.ore

import gregtech.api.GTValues.M
import gregtech.api.unification.material.Materials
import gregtech.api.unification.material.info.MaterialFlags
import gregtech.api.unification.material.info.MaterialIconType
import gregtech.api.unification.ore.OrePrefix
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.dustSmall
import gregtech.api.unification.ore.OrePrefix.dustTiny
import gregtech.api.unification.stack.MaterialStack
import gregtech.common.ConfigHolder
import gregtech.common.items.MetaItems
import magicbook.gtlitecore.api.unification.GTLiteMaterials
import magicbook.gtlitecore.api.unification.material.GTLiteMaterialFlags
import magicbook.gtlitecore.api.unification.material.GTLiteMaterialIconType

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
            dust.setIgnored(GTLiteMaterials.ZSM5)
            dustSmall.setIgnored(GTLiteMaterials.ZSM5)
            dustTiny.setIgnored(GTLiteMaterials.ZSM5)

            // Deleted Blazing Pyrotheum dusts.
            dustSmall.setIgnored(GTLiteMaterials.BlazingPyrotheum)
            dustTiny.setIgnored(GTLiteMaterials.BlazingPyrotheum)

            // Deleted Gelid Cryotheum dusts.
            dustSmall.setIgnored(GTLiteMaterials.GelidCryotheum)
            dustTiny.setIgnored(GTLiteMaterials.GelidCryotheum)

            // Deleted Tectonic Petrotheum dusts.
            dustSmall.setIgnored(GTLiteMaterials.TectonicPetrotheum)
            dustTiny.setIgnored(GTLiteMaterials.TectonicPetrotheum)

            // Deleted Zephyrean Aerotheum dusts.
            dustSmall.setIgnored(GTLiteMaterials.ZephyreanAerotheum)
            dustTiny.setIgnored(GTLiteMaterials.ZephyreanAerotheum)

            // Deleted RoastedSphalerite dusts.
            dustSmall.setIgnored(GTLiteMaterials.RoastedSphalerite)
            dustTiny.setIgnored(GTLiteMaterials.RoastedSphalerite)
        }

        // Let these setters be later than setOrePrefixInfos() because recipe generated at high
        // priority is correct, we want it has same priority of RecipeManager (priority = late),
        // so we add a postSetter which will be loaded after RecipeManager initialized to ensure
        // that the recipe which be removed do not be generated.
        fun postSetOrePrefixInfos()
        {
            // Because we add Tenorite (CuO ore) to change CuO dust, so this material is deprecated.
            // This material is ensured to remove all related recipes yet. Ignored all ore prefixes of CuO ^^.
            dust.setIgnored(Materials.CupricOxide)
            dustSmall.setIgnored(Materials.CupricOxide)
            dustTiny.setIgnored(Materials.CupricOxide)

            // Because we add Baddeleyite (ZrO2 ore) to change ZrO2 dust, so this material is
            // deprecated. This material is ensured to remove all related recipes yet. Ignored all
            // ore prefixes of ZrO2 ^^.
            dust.setIgnored(Materials.Zirconia)
            dustSmall.setIgnored(Materials.Zirconia)
            dustTiny.setIgnored(Materials.Zirconia)
        }

        fun addToMetaItem()
        {
            MetaItems.addOrePrefix(gemSolitary)
            MetaItems.addOrePrefix(seedCrystal)
            MetaItems.addOrePrefix(boule)
        }

    }

}