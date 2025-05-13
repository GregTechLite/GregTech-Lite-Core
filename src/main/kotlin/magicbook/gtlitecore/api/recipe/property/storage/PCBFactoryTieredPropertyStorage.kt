package magicbook.gtlitecore.api.recipe.property.storage

import magicbook.gtlitecore.api.recipe.property.PCBFactoryAuxiliaryTieredProperty
import magicbook.gtlitecore.api.recipe.property.PCBFactoryTieredProperty

class PCBFactoryTieredPropertyStorage
{

    companion object
    {

        fun loadProperties()
        {
            PCBFactoryTieredProperty.register(1, "1")
            PCBFactoryTieredProperty.register(2, "2")
            PCBFactoryTieredProperty.register(3, "3")

            PCBFactoryAuxiliaryTieredProperty.register(1, "")
        }

    }

}