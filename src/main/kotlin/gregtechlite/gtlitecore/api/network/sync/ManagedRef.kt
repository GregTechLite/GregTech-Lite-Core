package gregtechlite.gtlitecore.api.network.sync

import gregtechlite.gtlitecore.api.LOGGER
import gregtechlite.gtlitecore.api.network.payload.TypedPayload
import gregtechlite.gtlitecore.api.network.payload.fromPayload
import gregtechlite.gtlitecore.api.network.payload.toPayload
import java.util.Objects

class ManagedRef(val key: ManagedFieldKey, private val owner: Any)
{
    var lastSyncedValue: Any? = null
        private set

    init
    {
        key.field.isAccessible = true
    }

    fun currentValue(): Any? = try { key.field.get(owner) } catch (_: Exception) { null }

    fun setValue(value: Any?)
    {
        try
        {
            key.field.set(owner, value)
        }
        catch (e: Exception)
        {
            LOGGER.error("Managed failed to write field {}: {}", key.name, value, e)
        }
    }

    fun toPayload(): TypedPayload<*> = toPayload(key.field.type, currentValue())

    fun fromPayload(payload: TypedPayload<*>)
    {
        setValue(fromPayload(key.field.type, payload))
    }

    fun isSyncDirty(): Boolean = !Objects.equals(currentValue(), lastSyncedValue)

    fun markSyncClean()
    {
        lastSyncedValue = currentValue()
    }

    fun markSynced(value: Any?)
    {
        lastSyncedValue = value
    }
}