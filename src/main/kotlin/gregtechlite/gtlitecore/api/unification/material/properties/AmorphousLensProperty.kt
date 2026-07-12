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
        // A amorphous lens must have plate and cannot have gem.
        if (!properties.material.hasFlag(GENERATE_PLATE))
        {
            throw IllegalStateException("Material ${properties.material} with AmorphousLensProperty must have GENERATE_PLATE flag.")
        }
        else if (properties.hasProperty(PropertyKey.GEM))
        {
            throw IllegalStateException("Material ${properties.material} with AmorphousLensProperty cannot have GEM property.")
        }
    }
}