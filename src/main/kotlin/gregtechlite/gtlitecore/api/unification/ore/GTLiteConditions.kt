package gregtechlite.gtlitecore.api.unification.ore

import gregtech.api.unification.material.Material
import gregtech.api.unification.material.info.MaterialFlags
import gregtech.api.unification.ore.OrePrefix.Conditions
import gregtechlite.gtlitecore.api.unification.material.info.GTLiteMaterialFlags
import java.util.function.Predicate

object GTLiteConditions
{

    @JvmField
    val hasFuelRodProperties: Predicate<Material>? = Conditions.hasDustProperty.and { mat -> mat.hasFlags(GTLiteMaterialFlags.GENERATE_FUEL_ROD) }

    @JvmField
    val hasCrystalProperties: Predicate<Material>? = Conditions.hasGemProperty.and { mat -> mat.hasFlags(GTLiteMaterialFlags.GENERATE_BOULE)
            || (mat.hasFlags(MaterialFlags.CRYSTALLIZABLE) && !mat.hasFlags(GTLiteMaterialFlags.DISABLE_CRYSTALLIZATION)) }

}