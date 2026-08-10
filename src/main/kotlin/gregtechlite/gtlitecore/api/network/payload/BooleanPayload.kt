package gregtechlite.gtlitecore.api.network.payload

import net.minecraft.nbt.NBTBase
import net.minecraft.nbt.NBTTagByte
import net.minecraft.network.PacketBuffer

class BooleanPayload(var value: Boolean = false) : TypedPayload<Boolean>
{
    companion object
    {
        const val TYPE_ID: Byte = 7
    }

    override val type: Byte = TYPE_ID

    override val payload: Boolean
        get() = value

    override fun writePayload(buf: PacketBuffer)
    {
        buf.writeBoolean(value)
    }

    override fun readPayload(buf: PacketBuffer)
    {
        value = buf.readBoolean()
    }

    override fun serializeNBT(): NBTBase? = NBTTagByte(if (value) 1 else 0)

    override fun deserializeNBT(tag: NBTBase)
    {
        value = (tag as NBTTagByte).byte != 0.toByte()
    }
}
