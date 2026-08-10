package gregtechlite.gtlitecore.api.network.payload

import net.minecraft.nbt.NBTBase
import net.minecraft.nbt.NBTTagString
import net.minecraft.network.PacketBuffer

class StringPayload(var value: String = "") : TypedPayload<String>
{
    companion object
    {
        const val TYPE_ID: Byte = 9
    }

    override val type: Byte = TYPE_ID

    override val payload: String = value

    override fun writePayload(buf: PacketBuffer)
    {
        buf.writeString(value)
    }

    override fun readPayload(buf: PacketBuffer)
    {
        value = buf.readString(Short.MAX_VALUE.toInt())
    }

    override fun serializeNBT(): NBTBase? = NBTTagString(value)

    override fun deserializeNBT(tag: NBTBase)
    {
        value = (tag as NBTTagString).string
    }
}
