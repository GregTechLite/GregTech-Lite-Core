package gregtechlite.gtlitecore.api.network.sync

/**
 * Declares a client-side callback that is invoked whenever a synced field's value arrives over
 * the network (both initial sync and runtime dirty-sync).
 *
 * Must use together with [DescSynced] to provide `writeInitialSyncData`, `receiveInitialSyncData`
 * and `writeCustomData`, `receiveCustomData` syncs.
 *
 * The referenced method ([name]) must live on the mte itself and like:
 * ```
 * fun onFooChanged(newVal: T, oldVal: T)
 * ```
 * where `T` is the field's type.
 *
 * Usage:
 * ```
 *     @field:UpdateListener(name = "onFooChanged")
 *     var foo: Int = 0
 * ```
 */
@Target(AnnotationTarget.FIELD, AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
annotation class UpdateListener(val name: String)
