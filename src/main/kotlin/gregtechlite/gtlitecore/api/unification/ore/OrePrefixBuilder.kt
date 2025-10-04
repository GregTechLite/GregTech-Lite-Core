package gregtechlite.gtlitecore.api.unification.ore

import gregtech.api.unification.material.Material
import gregtech.api.unification.material.info.MaterialIconType
import gregtech.api.unification.ore.OrePrefix

class OrePrefixBuilder(private val name: String)
{

    companion object
    {

        @JvmStatic
        fun addPrefix(name: String, dsl: OrePrefixBuilder.() -> Unit): OrePrefix
        {
            return OrePrefixBuilder(name).apply(dsl).build()
        }

        @JvmStatic
        fun addPrefix(name: String, canUnified: Boolean, dsl: OrePrefixBuilder.() -> Unit): OrePrefix
        {
            return OrePrefixBuilder(name).apply{ if (canUnified) enableUnification() }.apply(dsl).build()
        }

        @JvmStatic
        fun addOrePrefix(name: String): OrePrefix
        {
            return OrePrefixBuilder(name).apply {
                enableUnification()
                materialAmount = -1
                iconType = MaterialIconType.ore
                condition = GTLiteConditions.hasOreProperty
            }.build()
        }

    }

    // Used Java functional interface to contain backwards compatibility with GTCEu.
    var materialAmount: Long = -1
    var material: Material? = null
    var iconType: MaterialIconType? = null
    var flags: Long = 0
    var condition: ((Material) -> Boolean)? = null
    var tooltips: ((Material) -> List<String>)? = null

    fun enableUnification()
    {
        flags = OrePrefix.Flags.ENABLE_UNIFICATION
    }

    fun selfReferencing()
    {
        flags = OrePrefix.Flags.SELF_REFERENCING
    }

    fun build(): OrePrefix
    {
        return if (tooltips != null)
        {
            OrePrefix(name, materialAmount, material, iconType, flags, condition, tooltips)
        }
        else
        {
            OrePrefix(name, materialAmount, material, iconType, flags, condition)
        }
    }

}
