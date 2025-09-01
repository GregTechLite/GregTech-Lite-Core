package gregtechlite.gtlitecore.api.recipe

import gregtechlite.gtlitecore.api.recipe.backend.AdvancedFusionRecipeBackend
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
        AdvancedFusionRecipeBackend.init()
        CVDRecipeBackend.init()
    }

}