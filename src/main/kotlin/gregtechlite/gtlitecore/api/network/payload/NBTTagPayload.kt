package gregtechlite.gtlitecore.api.network.payload

import net.minecraft.nbt.NBTBase
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.network.PacketBuffer

class NBTTagPayload(var value: NBTBase? = null) : TypedPayload<NBTBase?>
{
    companion object
    {
        const val TYPE_ID: Byte = 10
    }

    override val type: Byte = TYPE_ID

    override val payload: NBTBase?
        get() = value

    override fun writePayload(buf: PacketBuffer)
    {
        buf.writeCompoundTag(value as? NBTTagCompound)
    }

    override fun readPayload(buf: PacketBuffer)
    {
        value = buf.readCompoundTag()
    }

    override fun serializeNBT(): NBTBase? = value

    override fun deserializeNBT(tag: NBTBase)
    {
        value = tag
    }
}
