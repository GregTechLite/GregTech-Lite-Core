package gregtechlite.gtlitecore.api.network.payload

import net.minecraft.nbt.NBTBase
import net.minecraft.nbt.NBTTagLong
import net.minecraft.network.PacketBuffer

class LongPayload(var value: Long = 0L) : TypedPayload<Long>
{
    companion object
    {
        const val TYPE_ID: Byte = 2
    }

    override val type: Byte = TYPE_ID

    override val payload: Long = value

    override fun writePayload(buf: PacketBuffer)
    {
        buf.writeLong(value)
    }

    override fun readPayload(buf: PacketBuffer)
    {
        value = buf.readLong()
    }

    override fun serializeNBT(): NBTBase? = NBTTagLong(value)

    override fun deserializeNBT(tag: NBTBase)
    {
        value = (tag as NBTTagLong).long
    }
}
