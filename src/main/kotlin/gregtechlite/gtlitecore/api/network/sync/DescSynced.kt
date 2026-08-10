package gregtechlite.gtlitecore.api.network.sync

/**
 * Marks a property (field) as part of managed sync fields.
 *
 * Synced properties both in `writeInitialSyncData`, `receiveInitialSyncData`
 * and `writeCustomData`, `receiveCustomData`.
 *
 * Usage:
 * ```
 *     @field:DescSynced
 *     val foo: Int = 0
 * ```
 */
@Target(AnnotationTarget.FIELD, AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
annotation class DescSynced