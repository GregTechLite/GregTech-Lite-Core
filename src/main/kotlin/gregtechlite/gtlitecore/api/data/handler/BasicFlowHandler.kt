package gregtechlite.gtlitecore.api.data.handler

import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow

internal class BasicFlowHandler<T>(initial: T, private val strategy: CheckStrategy<T>) : FlowHandler<T>
{
    private val state = MutableStateFlow(initial)

    private var dirty: Boolean = false

    private val listeners = arrayListOf<(T, T) -> Unit>()

    override val value: T
        get() = state.value

    override val replayCache: List<T>
        get() = state.replayCache

    override suspend fun collect(collector: FlowCollector<T>): Nothing
    {
        state.collect(collector)
    }

    override fun set(value: T)
    {
        val old = state.value
        if (strategy.matches(old, value)) return
        state.value = value
        dirty = true
        fire(old, value)
    }

    override fun apply(value: T)
    {
        val old = state.value
        if (strategy.matches(old, value)) return
        state.value = value
        fire(old, value)
    }

    override fun load(value: T)
    {
        state.value = value
    }

    override fun dirty(): Boolean = dirty

    override fun clearDirty()
    {
        dirty = false
    }

    override fun onChange(listener: (T) -> Unit): Subscription
        = onChange { _, new -> listener(new) }

    override fun onChange(listener: (old: T, new: T) -> Unit): Subscription
    {
        listeners.add(listener)
        return Subscription { listeners.remove(listener) }
    }

    override fun readOnly(): ReadOnlyFlowHandler<T> = ReadOnlyFlowHandler(this)

    private fun fire(old: T, new: T)
    {
        for (listener in listeners.toList())
            listener(old, new)
    }
}
