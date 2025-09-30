package gregtechlite.gtlitecore.api.mixins

/**
 * Marked a mixins class is an adhoc resolve which implemented from GTCEu or other mods unrelease contents.
 *
 * @param at The source link for the implemented contents.
 *
 * @see gregtechlite.gtlitecore.mixins
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class Implemented(val at: Array<String>)