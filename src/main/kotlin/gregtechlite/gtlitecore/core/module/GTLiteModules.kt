package gregtechlite.gtlitecore.core.module

import gregtechlite.gtlitecore.api.MOD_ID
import gregtechlite.gtlitecore.api.module.IModuleContainer
import gregtechlite.gtlitecore.api.module.ModuleContainer

@ModuleContainer
internal class GTLiteModules : IModuleContainer
{

    companion object
    {

        const val MODULE_CORE: String = "core"
        const val MODULE_INTEGRATION: String = "integration"
        const val MODULE_WORLDGEN: String = "worldgen"
        const val MODULE_JEI: String = "jei"
        const val MODULE_TOP: String = "top"
        const val MODULE_AE2: String = "appeng"

    }

    override fun getId(): String = MOD_ID

}
