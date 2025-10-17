package gregtechlite.gtlitecore.api.integration

import gregtechlite.gtlitecore.api.mixins.MixinExtension
import mezz.jei.api.IModRegistry

@MixinExtension
interface InjectableModRegistry
{

    fun registerPostContext(registry: IModRegistry)

}