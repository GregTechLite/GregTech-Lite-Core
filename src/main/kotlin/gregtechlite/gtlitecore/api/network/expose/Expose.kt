package gregtechlite.gtlitecore.api.network.expose

import net.minecraft.network.PacketBuffer

interface Expose
{
    val name: String

    fun isChanged(): Boolean

    fun clearDirty()

    fun writeValue(buf: PacketBuffer)

    fun readValue(buf: PacketBuffer)

    fun isDifferential(): Boolean

    fun writeDifference(buf: PacketBuffer)

    fun readDifference(buf: PacketBuffer)
}
