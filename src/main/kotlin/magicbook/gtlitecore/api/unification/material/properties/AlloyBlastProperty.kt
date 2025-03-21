package magicbook.gtlitecore.api.unification.material.properties

import gregtech.api.unification.material.properties.IMaterialProperty
import gregtech.api.unification.material.properties.MaterialProperties
import gregtech.api.unification.material.properties.PropertyKey
import magicbook.gtlitecore.loader.recipe.producer.AlloyBlastRecipeProducer

class AlloyBlastProperty(private var temperature: Int) : IMaterialProperty
{

    var recipeProducer: AlloyBlastRecipeProducer = AlloyBlastRecipeProducer.DEFAULT_PRODUCER

    override fun verifyProperty(properties: MaterialProperties)
    {
        properties.ensureSet(PropertyKey.BLAST)
        properties.ensureSet(PropertyKey.FLUID)
        this.temperature = properties.getProperty(PropertyKey.BLAST).blastTemperature
    }

}