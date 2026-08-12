package gregtechlite.gtlitecore.api.network.expose

import gregtechlite.gtlitecore.api.data.handle.DiffHandle
import gregtechlite.gtlitecore.api.data.handle.DiffObservable
import gregtechlite.gtlitecore.api.data.Schema
import net.minecraft.network.PacketBuffer

class DiffExpose<T : DiffObservable<D>, D>(override val name: String,
                                           private val schema: Schema<T>,
                                           private val handle: DiffHandle<T, D>) : Expose
{
    override fun isChanged(): Boolean = handle.changed()

    override fun clearDirty() = handle.clearDirty()

    override fun writeValue(buf: PacketBuffer) = schema.dataWriter(buf, handle.current)

    override fun readValue(buf: PacketBuffer) { handle.apply(schema.dataReader(buf)) }

    override fun isDifferential(): Boolean = true

    override fun writeDifference(buf: PacketBuffer)
    {
        if (handle.dirty() || !handle.current.isChanged())
        {
            buf.writeBoolean(false)
            writeValue(buf)
        }
        else
        {
            buf.writeBoolean(true)
            handle.writeDifference(buf)
        }
    }

    override fun readDifference(buf: PacketBuffer)
    {
        if (buf.readBoolean())
        {
            handle.current.readDifference(buf)
            handle.apply(handle.current)
        }
        else
        {
            readValue(buf)
        }
    }
}
