package gregtechlite.gtlitecore.api.network.sync

/**
 * Marks a property as part of managed persistence fields.
 *
 * Synced properties in `writeToNBT` and `readFromNBT`.
 *
 * Usage:
 * ```
 *     @field:Persisted
 *     val foo: Int = 0
 * ```
 */
@Target(AnnotationTarget.FIELD, AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
annotation class Persisted
