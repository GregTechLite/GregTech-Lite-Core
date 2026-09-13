package gregtechlite.gtlitecore.api.data.handler

import kotlinx.coroutines.ExperimentalForInheritanceCoroutinesApi
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalForInheritanceCoroutinesApi::class)
class ReadOnlyFlowHandler<T>(private val delegate: FlowHandler<T>) : StateFlow<T>
{
    override val value: T
        get() = delegate.value

    override val replayCache: List<T>
        get() = delegate.replayCache

    override suspend fun collect(collector: FlowCollector<T>) = delegate.collect(collector)

    fun dirty(): Boolean = delegate.dirty()

    fun onChange(listener: (T) -> Unit): Subscription = delegate.onChange(listener)

    fun onChange(listener: (old: T, new: T) -> Unit): Subscription = delegate.onChange(listener)
}
