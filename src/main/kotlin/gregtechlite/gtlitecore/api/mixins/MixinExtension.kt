package gregtechlite.gtlitecore.api.mixins

/**
 * Marked an interface is extension for mixins class or only used for mixins class (means it as the hook for the
 * specific mixins class).
 *
 * @see gregtechlite.gtlitecore.mixins
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class MixinExtension()
