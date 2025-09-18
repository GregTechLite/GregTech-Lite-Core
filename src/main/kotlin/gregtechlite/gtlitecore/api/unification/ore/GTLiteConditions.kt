package gregtechlite.gtlitecore.api.unification.ore

import gregtech.api.unification.material.Material
import gregtech.api.unification.material.info.MaterialFlags
import gregtech.api.unification.material.properties.PropertyKey
import gregtechlite.gtlitecore.api.unification.material.info.GTLiteMaterialFlags

object GTLiteConditions
{

    // @formatter:off

    // region Frontend Keys

    private val hasFuelRodKeys: (Material) -> Boolean = { it.hasFlag(GTLiteMaterialFlags.GENERATE_FUEL_ROD) }
    private val hasCrystallizableKey: (Material) -> Boolean = {
        it.hasFlag(GTLiteMaterialFlags.GENERATE_BOULE)
            || (it.hasFlags(MaterialFlags.CRYSTALLIZABLE)
                && !it.hasFlags(GTLiteMaterialFlags.DISABLE_CRYSTALLIZATION))
    }

    // endregion

    // region Vanilla Properties Adapter

    @JvmField
    internal val hasOreProperty: (Material) -> Boolean = { it.hasProperty(PropertyKey.ORE) }
    @JvmField
    internal val hasDustProperty: (Material) -> Boolean = { it.hasProperty(PropertyKey.DUST) }
    @JvmField
    internal val hasGemProperty: (Material) -> Boolean = { it.hasProperty(PropertyKey.GEM) }

    // endregion

    // region Specific Properties

    @JvmField
    val hasFuelRodProperties: (Material) -> Boolean = { hasDustProperty(it) && hasFuelRodKeys(it) }
    @JvmField
    val hasCrystalProperties: (Material) -> Boolean = { hasGemProperty(it) && hasCrystallizableKey(it) }

    // endregion

    // @formatter:on

}