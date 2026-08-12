package gregtechlite.gtlitecore.api.network.expose

import gregtechlite.gtlitecore.api.data.handler.FlowHandler
import gregtechlite.gtlitecore.api.data.Schema
import net.minecraft.network.PacketBuffer

class HandleExpose<T>(override val name: String,
                      private val schema: Schema<T>,
                      private val handler: FlowHandler<T>) : Expose
{
    override fun isChanged(): Boolean = handler.dirty()

    override fun clearDirty() = handler.clearDirty()

    override fun writeValue(buf: PacketBuffer) = schema.dataWriter(buf, handler.value)

    override fun readValue(buf: PacketBuffer)
    {
        handler.apply(schema.dataReader(buf))
    }

    override fun isDifferential(): Boolean = false

    override fun writeDifference(buf: PacketBuffer) {}

    override fun readDifference(buf: PacketBuffer) {}
}
