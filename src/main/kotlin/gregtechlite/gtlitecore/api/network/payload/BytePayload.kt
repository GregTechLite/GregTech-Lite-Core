package gregtechlite.gtlitecore.api.network.payload

import net.minecraft.nbt.NBTBase
import net.minecraft.nbt.NBTTagByte
import net.minecraft.network.PacketBuffer

class BytePayload(var value: Byte = 0) : TypedPayload<Byte>
{
    companion object
    {
        const val TYPE_ID: Byte = 5
    }

    override val type: Byte = TYPE_ID

    override val payload: Byte
        get() = value

    override fun writePayload(buf: PacketBuffer)
    {
        buf.writeByte(value.toInt())
    }

    override fun readPayload(buf: PacketBuffer)
    {
        value = buf.readByte()
    }

    override fun serializeNBT(): NBTBase? = NBTTagByte(value)

    override fun deserializeNBT(tag: NBTBase)
    {
        value = (tag as NBTTagByte).byte
    }
}
