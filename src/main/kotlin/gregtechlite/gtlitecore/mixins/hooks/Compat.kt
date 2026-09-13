package gregtechlite.gtlitecore.mixins.hooks

/**
 * Mark a mixins is used to build compatibility with some mods.
 *
 * @param modId All compatible mods by this mixins doing. For notation convention,
 *              if you want to identify specific version of mod, please use a format
 *              like `modid:version` to hint a specific version compatibility.
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class Compat(val modId: Array<String> = [""])