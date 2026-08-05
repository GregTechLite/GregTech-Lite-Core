package gregtechlite.gtlitecore.mixins.hooks

/**
 * Mark a class (maybe final or abstract) is a hook for mixins.
 *
 * Generally, we not recommend to use those classes with this annotation in non mixins
 * situations. This annotation is not like [Extension], this annotation only
 * use for some utility class or hooks which mixin used only.
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class Hook