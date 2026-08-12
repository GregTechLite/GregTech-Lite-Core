package gregtechlite.gtlitecore.api.data.handler

import kotlinx.coroutines.ExperimentalForInheritanceCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlin.reflect.KProperty

@OptIn(ExperimentalForInheritanceCoroutinesApi::class)
interface FlowHandler<T> : StateFlow<T>
{
    fun set(value: T)

    fun apply(value: T)

    fun load(value: T)

    fun dirty(): Boolean

    fun clearDirty()

    fun onChange(listener: (T) -> Unit): Subscription

    fun onChange(listener: (old: T, new: T) -> Unit): Subscription

    fun readOnly(): ReadOnlyFlowHandler<T>

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T = value

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) { set(value) }
}

fun <T> handlerOf(initial: T, strategy: CheckStrategy<T> = CheckStrategy.Equals): FlowHandler<T>
    = BasicFlowHandler(initial, strategy)
