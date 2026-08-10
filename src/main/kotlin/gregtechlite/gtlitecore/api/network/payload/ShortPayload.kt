package gregtechlite.gtlitecore.api.network.payload

import net.minecraft.nbt.NBTBase
import net.minecraft.nbt.NBTTagShort
import net.minecraft.network.PacketBuffer

class ShortPayload(var value: Short = 0) : TypedPayload<Short>
{
    companion object
    {
        const val TYPE_ID: Byte = 6
    }

    override val type: Byte = TYPE_ID

    override val payload: Short
        get() = value

    override fun writePayload(buf: PacketBuffer)
    {
        buf.writeShort(value.toInt())
    }

    override fun readPayload(buf: PacketBuffer)
    {
        value = buf.readShort()
    }

    override fun serializeNBT(): NBTBase? = NBTTagShort(value)

    override fun deserializeNBT(tag: NBTBase)
    {
        value = (tag as NBTTagShort).short
    }
}
