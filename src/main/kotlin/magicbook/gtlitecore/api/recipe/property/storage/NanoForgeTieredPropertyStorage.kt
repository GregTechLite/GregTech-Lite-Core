package magicbook.gtlitecore.api.recipe.property.storage

import magicbook.gtlitecore.api.recipe.property.NanoForgeTieredProperty

class NanoForgeTieredPropertyStorage
{

    companion object
    {

        fun loadProperties()
        {
            NanoForgeTieredProperty.register(1, "1")
            NanoForgeTieredProperty.register(2, "2")
            NanoForgeTieredProperty.register(3, "3")
        }

    }

}