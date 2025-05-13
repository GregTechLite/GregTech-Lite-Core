package magicbook.gtlitecore.api.recipe.property.storage

class RecipePropertyStorages
{

    companion object
    {

        @JvmStatic
        fun loadPropertyStorage()
        {
            AdvancedFusionTieredPropertyStorage.loadProperties()
            ComponentAssemblyLineTieredPropertyStorage.loadProperties()
            CircuitPatternPropertyStorage.loadProperties()
            FusionEUToStartPropertyStorage.loadProperties()
            PCBFactoryTieredPropertyStorage.loadProperties()
        }

    }

}