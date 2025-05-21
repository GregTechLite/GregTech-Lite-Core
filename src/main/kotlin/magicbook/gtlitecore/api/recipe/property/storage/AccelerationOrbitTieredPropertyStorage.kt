package magicbook.gtlitecore.api.recipe.property.storage

import magicbook.gtlitecore.api.recipe.property.AccelerationOrbitTieredProperty

class AccelerationOrbitTieredPropertyStorage
{

    companion object
    {

        fun loadProperties()
        {
            AccelerationOrbitTieredProperty.register(1, "MK1")
            AccelerationOrbitTieredProperty.register(2, "MK2")
            AccelerationOrbitTieredProperty.register(3, "MK3")
            AccelerationOrbitTieredProperty.register(4, "MK4")
            AccelerationOrbitTieredProperty.register(5, "MK5")
        }

    }

}