package gregtechlite.gtlitecore.api.network.payload

import net.minecraft.nbt.NBTBase
import net.minecraft.nbt.NBTTagInt
import net.minecraft.network.PacketBuffer

class CharPayload(var value: Char = '\u0000') : TypedPayload<Char>
{
    companion object
    {
        const val TYPE_ID: Byte = 8
    }

    override val type: Byte = TYPE_ID

    override val payload: Char = value

    override fun writePayload(buf: PacketBuffer)
    {
        buf.writeChar(value.code)
    }

    override fun readPayload(buf: PacketBuffer)
    {
        value = buf.readChar()
    }

    override fun serializeNBT(): NBTBase? = NBTTagInt(value.code)

    override fun deserializeNBT(tag: NBTBase)
    {
        value = (tag as NBTTagInt).int.toChar()
    }
}
