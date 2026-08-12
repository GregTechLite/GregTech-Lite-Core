package gregtechlite.gtlitecore.api.data.handle

class ReadOnlyHandle<T>(private val delegate: Handle<T>)
{
    val current: T get() = delegate.current

    fun dirty(): Boolean = delegate.dirty()

    fun onChange(listener: (T) -> Unit): Subscription = delegate.onChange(listener)

    fun onChange(listener: (old: T, new: T) -> Unit): Subscription = delegate.onChange(listener)
}
