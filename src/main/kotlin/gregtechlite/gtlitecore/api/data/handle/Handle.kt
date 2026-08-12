package gregtechlite.gtlitecore.api.data.handle

import kotlin.reflect.KProperty

interface Handle<T>
{
    val current: T

    fun set(value: T)

    fun apply(value: T)

    fun load(value: T)

    fun dirty(): Boolean

    fun clearDirty()

    fun onChange(listener: (T) -> Unit): Subscription

    fun onChange(listener: (old: T, new: T) -> Unit): Subscription

    fun readOnly(): ReadOnlyHandle<T>
}

fun <T> handleOf(initial: T, strategy: CheckStrategy<T> = CheckStrategy.EQUALS): Handle<T> = BasicHandle(initial, strategy)

operator fun <T> Handle<T>.getValue(thisRef: Any?, property: KProperty<*>): T = current

operator fun <T> Handle<T>.setValue(thisRef: Any?, property: KProperty<*>, value: T) { set(value) }
