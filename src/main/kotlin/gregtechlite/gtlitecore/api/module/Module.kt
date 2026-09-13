package gregtechlite.gtlitecore.api.module

/**
 * Marks a class as a module, discovered and configured by [ModuleManager].
 *
 * @param moduleId        The id of the module, must be unique within its container (`containerId`).
 * @param containerId     The id of the container to associate the module with.
 * @param name            The human-readable name of the module, different with `moduleId` format.
 * @param modDependencies All mod id that the module depends on. If any mods specified are not present,
 *                        the module will not load.
 * @param isCore          Whether the module is core for its container. Each container must have exactly
 *                        one core module which will be loaded before all other modules in the container.
 *                        Core modules should not have mod dependencies because it used to loaded mod
 *                        structure from API to mod main.
 * @param author          The author of the module.
 * @param version         The version of the module.
 * @param descriptions    The description of the module in the module configuration file.
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class Module(val moduleId: String,
                        val containerId: String,
                        val name: String,
                        val modDependencies: Array<String> = [],
                        val isCore: Boolean = false,
                        val author: String = "",
                        val version: String = "",
                        val descriptions: String = "")
