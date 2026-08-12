package gregtechlite.gtlitecore.api.data.handle

import net.minecraft.network.PacketBuffer
import kotlin.reflect.KProperty

class DiffHandle<T : DiffObservable<D>, D>(private val delegate: Handle<T>)
{
    val current: T get() = delegate.current

    fun set(value: T) = delegate.set(value)

    fun apply(value: T) = delegate.apply(value)

    fun load(value: T) = delegate.load(value)

    fun changed(): Boolean = delegate.dirty() || delegate.current.isChanged()

    fun dirty(): Boolean = delegate.dirty()

    fun clearDirty() = delegate.clearDirty()

    fun writeDifference(buf: PacketBuffer) = delegate.current.writeDifference(buf)

    fun onChange(listener: (T) -> Unit): Subscription = delegate.onChange(listener)

    fun onChange(listener: (old: T, new: T) -> Unit): Subscription = delegate.onChange(listener)
}

operator fun <T : DiffObservable<D>, D> DiffHandle<T, D>.getValue(thisRef: Any?, property: KProperty<*>): T = current