package gregtechlite.gtlitecore.mixins.hooks

/**
 * Mark a mixins is an adhoc resolve which is implemented from upstream repositories or
 * unrelease contents in our mods, or just referenced some external contents.
 *
 * @param at A source for the implemented contents. Here are some allowed source format:
 *           1. URL format link, e.g. GitHub Issue/Pull Request link.
 *           2. Reference with correct format, like paper reference format.
 *           3. Blog link with its author name (or id)
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class Implemented(val at: Array<String> = [""])