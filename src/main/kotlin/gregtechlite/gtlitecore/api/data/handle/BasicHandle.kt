package gregtechlite.gtlitecore.api.data.handle

internal class BasicHandle<T>(initial: T, private val strategy: CheckStrategy<T>) : Handle<T>
{
    private var value: T = initial
    private var dirty: Boolean = false

    private val listeners = arrayListOf<(T, T) -> Unit>()

    override val current: T
        get() = value

    override fun set(value: T)
    {
        if (strategy.matches(this.value, value)) return
        val old = this.value
        this.value = value
        dirty = true
        fire(old, value)
    }

    override fun apply(value: T)
    {
        if (strategy.matches(this.value, value)) return
        val old = this.value
        this.value = value
        fire(old, value)
    }

    override fun load(value: T)
    {
        this.value = value
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

    override fun readOnly(): ReadOnlyHandle<T> = ReadOnlyHandle(this)

    private fun fire(old: T, new: T)
    {
        for (listener in listeners.toList())
            listener(old, new)
    }
}
