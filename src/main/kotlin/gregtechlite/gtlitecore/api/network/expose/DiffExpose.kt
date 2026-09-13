package gregtechlite.gtlitecore.api.network.expose

import gregtechlite.gtlitecore.api.data.Schema
import gregtechlite.gtlitecore.api.data.handler.DiffHandler
import gregtechlite.gtlitecore.api.data.handler.DiffObservable
import net.minecraft.network.PacketBuffer

class DiffExpose<T : DiffObservable<D>, D>(override val name: String,
                                           private val schema: Schema<T>,
                                           private val handler: DiffHandler<T, D>) : Expose
{
    override fun isChanged(): Boolean = handler.changed()

    override fun clearDirty() = handler.clearDirty()

    override fun writeValue(buf: PacketBuffer) = schema.dataWriter(buf, handler.value)

    override fun readValue(buf: PacketBuffer) { handler.apply(schema.dataReader(buf)) }

    override fun isDifferential(): Boolean = true

    override fun writeDifference(buf: PacketBuffer)
    {
        if (handler.dirty() || !handler.value.isChanged())
        {
            buf.writeBoolean(false)
            writeValue(buf)
        }
        else
        {
            buf.writeBoolean(true)
            handler.writeDifference(buf)
        }
    }

    override fun readDifference(buf: PacketBuffer)
    {
        if (buf.readBoolean())
        {
            handler.value.readDifference(buf)
            handler.apply(handler.value)
        }
        else
        {
            readValue(buf)
        }
    }
}
