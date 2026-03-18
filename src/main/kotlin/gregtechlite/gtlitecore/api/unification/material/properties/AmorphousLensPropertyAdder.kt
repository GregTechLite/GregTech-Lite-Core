package gregtechlite.gtlitecore.api.unification.material.properties

import gregtech.api.GregTechAPI
import gregtech.api.unification.material.Material
import gregtech.api.unification.material.info.MaterialFlags
import gregtech.api.unification.material.properties.PropertyKey.GEM

object AmorphousLensPropertyAdder
{

    // add property for those require lens but not have gem property,
    // this is for some special materials like Plastic Lens.
    fun preInitAmorphousLensProperties()
    {
        GregTechAPI.materialManager.registeredMaterials
                .filter { it.hasFlag(MaterialFlags.GENERATE_LENS) && !it.hasProperty(GEM) }
                .forEach(::addLensNoGemProperty)
    }

    private fun addLensNoGemProperty(material: Material)
    {
        material.setProperty(GTLitePropertyKey.AMORPHOUS_LENS, AmorphousLensProperty())
    }
}
