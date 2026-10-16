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
    fun createMixinConfigs(vararg names: String): List<String> = names.map { mixinName(it) }.toList()

    /**
     * The standard mixin file name with default location.
     *
     * @param modId The distributed name for mixin file.
     * @return      Returns the standard mixin file name, format: `mixins.gtlitecore.<modId>.json`.
     */
    fun mixinName(modId: String): String = "mixins.$MOD_ID.$modId.json"
}