package gregtechlite.gtlitecore.api.data.handler

import net.minecraft.network.PacketBuffer

interface DiffObservable<D>
{
    fun isChanged(): Boolean

    fun writeDifference(buf: PacketBuffer)

    fun readDifference(buf: PacketBuffer)
}
