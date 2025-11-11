package gregtechlite.gtlitecore.api.recipe

import gregtechlite.gtlitecore.api.recipe.backend.BlastFurnaceRecipeBackend
import gregtechlite.gtlitecore.api.recipe.backend.CVDRecipeBackend
import gregtechlite.gtlitecore.api.recipe.backend.LargeMixerRecipeBackend
import gregtechlite.gtlitecore.api.recipe.backend.NanoAssemblyMatrixRecipeBackend

object GTLiteRecipeBackends
{

    @JvmStatic
    fun init()
    {
        LargeMixerRecipeBackend.init()
        CVDRecipeBackend.init()
    }

    @JvmStatic
    fun postInit()
    {
        NanoAssemblyMatrixRecipeBackend.init()
        BlastFurnaceRecipeBackend.init()
    }

}
