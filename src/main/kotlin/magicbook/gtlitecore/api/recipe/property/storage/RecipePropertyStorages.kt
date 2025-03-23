package magicbook.gtlitecore.api.recipe.property.storage

class RecipePropertyStorages
{

    companion object
    {

        @JvmStatic
        fun loadPropertyStorage()
        {
            CircuitPatternPropertyStorage.loadProperties()
        }

    }

}