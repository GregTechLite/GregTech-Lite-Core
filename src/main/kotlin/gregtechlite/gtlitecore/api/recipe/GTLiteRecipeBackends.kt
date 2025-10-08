package gregtechlite.gtlitecore.api.recipe

import gregtechlite.gtlitecore.api.recipe.backend.BlastFurnaceRecipeBackend
import gregtechlite.gtlitecore.api.recipe.backend.CVDRecipeBackend
import gregtechlite.gtlitecore.api.recipe.backend.LargeMixerRecipeBackend

object GTLiteRecipeBackends
{

    @JvmStatic
    fun preInit()
    {
        LargeMixerRecipeBackend.init()
    }

    @JvmStatic
    fun init()
    {
        CVDRecipeBackend.init()
    }

    @JvmStatic
    fun postInit()
    {
        BlastFurnaceRecipeBackend.init()
    }

}
