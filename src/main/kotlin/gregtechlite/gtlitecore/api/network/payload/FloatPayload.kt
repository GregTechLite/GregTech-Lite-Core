package gregtechlite.gtlitecore.api.network.payload

import net.minecraft.nbt.NBTBase
import net.minecraft.nbt.NBTTagFloat
import net.minecraft.network.PacketBuffer

class FloatPayload(var value: Float = 0f) : TypedPayload<Float>
{
    companion object
    {
        const val TYPE_ID: Byte = 3
    }

    override val type: Byte = TYPE_ID

    override val payload: Float
        get() = value

    override fun writePayload(buf: PacketBuffer) { buf.writeFloat(value) }

    override fun readPayload(buf: PacketBuffer) { value = buf.readFloat() }

    override fun serializeNBT(): NBTBase? = NBTTagFloat(value)

    override fun deserializeNBT(tag: NBTBase) { value = (tag as NBTTagFloat).float }
}
