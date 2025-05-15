package magicbook.gtlitecore.api.recipe.property.storage

import magicbook.gtlitecore.api.recipe.property.QuantumForceTransformerTieredProperty

class QuantumForceTransformerTieredPropertyStorage
{

    companion object
    {

        fun loadProperties()
        {
            QuantumForceTransformerTieredProperty.register(1, "1")
            QuantumForceTransformerTieredProperty.register(2, "2")
            QuantumForceTransformerTieredProperty.register(3, "3")
            QuantumForceTransformerTieredProperty.register(4, "4")
        }

    }

}