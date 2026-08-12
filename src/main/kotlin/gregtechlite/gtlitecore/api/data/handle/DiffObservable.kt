package gregtechlite.gtlitecore.api.data.handle

import net.minecraft.network.PacketBuffer

interface DiffObservable<D>
{
    fun isChanged(): Boolean

    fun writeDifference(buf: PacketBuffer)

    fun readDifference(buf: PacketBuffer)
}
