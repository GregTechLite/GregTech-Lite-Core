package gregtechlite.gtlitecore.api.network.payload

import net.minecraft.nbt.NBTBase
import net.minecraft.nbt.NBTTagDouble
import net.minecraft.network.PacketBuffer

class DoublePayload(var value: Double = 0.0) : TypedPayload<Double>
{
    companion object
    {
        const val TYPE_ID: Byte = 4
    }

    override val type: Byte = TYPE_ID

    override val payload: Double = value

    override fun writePayload(buf: PacketBuffer)
    {
        buf.writeDouble(value)
    }

    override fun readPayload(buf: PacketBuffer)
    {
        value = buf.readDouble()
    }

    override fun serializeNBT(): NBTBase? = NBTTagDouble(value)

    override fun deserializeNBT(tag: NBTBase)
    {
        value = (tag as NBTTagDouble).double
    }
}
