package magicbook.gtlitecore.api.unification.ore

import gregtech.api.GTValues.M
import gregtech.api.unification.ore.OrePrefix
import gregtech.common.items.MetaItems
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

        fun setOrePrefixInfos()
        {
            gemSolitary.maxStackSize = 8
        }

        fun addToMetaItem()
        {
            MetaItems.addOrePrefix(gemSolitary)
        }

    }

}