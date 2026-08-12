package gregtechlite.gtlitecore.api.network.expose

import gregtechlite.gtlitecore.api.data.handle.Handle
import gregtechlite.gtlitecore.api.data.Schema
import net.minecraft.network.PacketBuffer

class HandleExpose<T>(override val name: String,
                      private val schema: Schema<T>,
                      private val handle: Handle<T>) : Expose
{
    override fun isChanged(): Boolean = handle.dirty()

    override fun clearDirty() = handle.clearDirty()

    override fun writeValue(buf: PacketBuffer) = schema.dataWriter(buf, handle.current)

    override fun readValue(buf: PacketBuffer)
    {
        handle.apply(schema.dataReader(buf))
    }

    override fun isDifferential(): Boolean = false

    override fun writeDifference(buf: PacketBuffer) {}

    override fun readDifference(buf: PacketBuffer) {}
}
