package magicbook.gtlitecore.api.recipe.property.storage

import magicbook.gtlitecore.api.recipe.property.ComponentAssemblyLineTieredProperty

class ComponentAssemblyLineTieredPropertyStorage
{

    companion object
    {

        fun loadProperties()
        {
            ComponentAssemblyLineTieredProperty.register(1, "LV")
            ComponentAssemblyLineTieredProperty.register(2, "MV")
            ComponentAssemblyLineTieredProperty.register(3, "HV")
            ComponentAssemblyLineTieredProperty.register(4, "EV")
            ComponentAssemblyLineTieredProperty.register(5, "IV")
            ComponentAssemblyLineTieredProperty.register(6, "LuV")
            ComponentAssemblyLineTieredProperty.register(7, "ZPM")
            ComponentAssemblyLineTieredProperty.register(8, "UV")
            ComponentAssemblyLineTieredProperty.register(9, "UHV")
            ComponentAssemblyLineTieredProperty.register(10, "UEV")
            ComponentAssemblyLineTieredProperty.register(11, "UIV")
            ComponentAssemblyLineTieredProperty.register(12, "UXV")
            ComponentAssemblyLineTieredProperty.register(13, "OpV")
            ComponentAssemblyLineTieredProperty.register(14, "MAX")
        }

    }

}