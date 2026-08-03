package gregtechlite.gtlitecore.api.integration

import gregtechlite.gtlitecore.mixins.hooks.Extension
import mezz.jei.api.IModRegistry

@Extension
interface InjectableModRegistry
{

    fun registerPostContext(registry: IModRegistry)

}