package gregtechlite.gtlitecore.api.network.expose

import gregtechlite.gtlitecore.api.LOGGER
import net.minecraft.network.PacketBuffer

class ExposeManagement
{
    private val exposes = arrayListOf<Expose>()

    fun register(expose: Expose)
    {
        exposes.add(expose)
    }

    fun writeAllToClient(buf: PacketBuffer)
    {
        buf.writeBoolean(true)
        buf.writeVarInt(exposes.size)
        exposes.forEachIndexed { id, expose ->
            buf.writeShort(id)
            expose.writeValue(buf)
        }
    }

    fun writeChangesToClient(buf: PacketBuffer)
    {
        val changed = exposes.indices.filter { exposes[it].isChanged() }
        buf.writeBoolean(false)
        buf.writeVarInt(changed.size)
        for (id in changed)
        {
            buf.writeShort(id)
            val expose = exposes[id]
            if (expose.isDifferential())
            {
                expose.writeDifference(buf)
            }
            else
            {
                expose.writeValue(buf)
            }
        }
    }

    fun receiveFromServer(buf: PacketBuffer)
    {
        val isFull = buf.readBoolean()
        val count = buf.readVarInt()
        repeat(count)
        {
            val id = buf.readShort().toInt()
            val expose = exposes.getOrNull(id)
            if (expose == null)
            {
                LOGGER.error("Received expose id '$id' but this machine has only ${exposes.size} exposes")
                return@repeat
            }
            when
            {
                isFull                  -> expose.readValue(buf)
                expose.isDifferential() -> expose.readDifference(buf)
                else                    -> expose.readValue(buf)
            }
        }
    }

    fun hasChanges(): Boolean = exposes.any { it.isChanged() }

    fun clearAllDirty() = exposes.forEach { it.clearDirty() }
}
