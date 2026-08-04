package gregtechlite.gtlitecore.api.module

/**
 * Marks a class as a module container, discovered and registered by [ModuleManager].
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class ModuleContainer
