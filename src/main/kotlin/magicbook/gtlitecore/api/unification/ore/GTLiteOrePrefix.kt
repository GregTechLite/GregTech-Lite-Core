package magicbook.gtlitecore.api.unification.ore

import gregtech.api.GTValues.M
import gregtech.api.unification.material.Materials
import gregtech.api.unification.material.info.MaterialIconType
import gregtech.api.unification.ore.OrePrefix
import gregtech.api.unification.stack.MaterialStack
import gregtech.common.ConfigHolder
import gregtech.common.items.MetaItems
import magicbook.gtlitecore.api.unification.GTLiteMaterials
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

        }

        fun addToMetaItem()
        {
            MetaItems.addOrePrefix(gemSolitary)
        }

    }

}