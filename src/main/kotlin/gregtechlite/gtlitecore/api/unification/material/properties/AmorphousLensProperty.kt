package gregtechlite.gtlitecore.api.unification.material.properties

import gregtech.api.unification.material.info.MaterialFlags.GENERATE_PLATE
import gregtech.api.unification.material.properties.IMaterialProperty
import gregtech.api.unification.material.properties.MaterialProperties
import gregtech.api.unification.material.properties.PropertyKey

class AmorphousLensProperty : IMaterialProperty
{
    override fun verifyProperty(properties: MaterialProperties)
    {
        properties.ensureSet(PropertyKey.DUST, true)

        // Lens must have PLATE and cannot have GEM
        if (!properties.material.hasFlag(GENERATE_PLATE))
        {
            throw IllegalStateException("Material ${properties.material} with AmorphousLensProperty must have either PLATE flag or GEM property.")
        } else if (properties.hasProperty(PropertyKey.GEM))
        {
            throw IllegalStateException("Material ${properties.material} with AmorphousLensProperty cannot have GEM property.")
        }
    }
}