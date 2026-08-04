package gregtechlite.gtlitecore.mixins

import gregtechlite.gtlitecore.api.MOD_ID

interface CustomMixinLoader
{
    /**
     * Distributes the configuration files for mixins by single name.
     *
     * All mixin configurations has unique mod id, and will use single name
     * as secondary name, e.g. `mixins.gtlitecore.gregtech`.
     *
     * @param names All distributed names for mixin configurations.
     * @return      Returns all distributed names corresponding mixin configuration files.
     */
    fun createMixinConfigs(vararg names: String): List<String> = names.map { "mixins.$MOD_ID.$it.json" }.toList()
}