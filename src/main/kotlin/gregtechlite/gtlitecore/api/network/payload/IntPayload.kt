package gregtechlite.gtlitecore.api.network.payload

import net.minecraft.nbt.NBTBase
import net.minecraft.nbt.NBTTagInt
import net.minecraft.network.PacketBuffer

class IntPayload(var value: Int = 0) : TypedPayload<Int>
{
    companion object
    {
        const val TYPE_ID: Byte = 1
    }

    override val type: Byte = TYPE_ID

    override val payload: Int = value

    override fun writePayload(buf: PacketBuffer)
    {
        buf.writeInt(value)
    }

    override fun readPayload(buf: PacketBuffer)
    {
        value = buf.readInt()
    }

    override fun serializeNBT(): NBTBase? = NBTTagInt(value)

    override fun deserializeNBT(tag: NBTBase)
    {
        value = (tag as NBTTagInt).int
    }
}
