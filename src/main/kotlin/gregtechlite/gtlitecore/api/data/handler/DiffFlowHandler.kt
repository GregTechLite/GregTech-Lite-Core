package gregtechlite.gtlitecore.api.data.handler

import net.minecraft.network.PacketBuffer
import kotlin.reflect.KProperty

class DiffHandler<T : DiffObservable<D>, D>(private val delegate: FlowHandler<T>)
{
    val value: T
        get() = delegate.value

    fun set(value: T) = delegate.set(value)

    fun apply(value: T) = delegate.apply(value)

    fun load(value: T) = delegate.load(value)

    fun changed(): Boolean = delegate.dirty() || delegate.value.isChanged()

    fun dirty(): Boolean = delegate.dirty()

    fun clearDirty() = delegate.clearDirty()

    fun writeDifference(buf: PacketBuffer) = delegate.value.writeDifference(buf)

    fun onChange(listener: (T) -> Unit): Subscription = delegate.onChange(listener)

    fun onChange(listener: (old: T, new: T) -> Unit): Subscription = delegate.onChange(listener)

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T = value
}