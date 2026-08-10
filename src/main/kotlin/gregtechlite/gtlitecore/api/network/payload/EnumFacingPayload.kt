package gregtechlite.gtlitecore.api.network.payload

import net.minecraft.nbt.NBTBase
import net.minecraft.nbt.NBTTagInt
import net.minecraft.network.PacketBuffer
import net.minecraft.util.EnumFacing

class EnumFacingPayload(var value: EnumFacing = EnumFacing.DOWN) : TypedPayload<EnumFacing>
{
    companion object
    {
        const val TYPE_ID: Byte = 11
    }

    override val type: Byte = TYPE_ID

    override val payload: EnumFacing
        get() = value

    override fun writePayload(buf: PacketBuffer)
    {
        buf.writeByte(value.index)
    }

    override fun readPayload(buf: PacketBuffer)
    {
        value = EnumFacing.byIndex(buf.readByte().toInt())
    }

    override fun serializeNBT(): NBTBase? = NBTTagInt(value.index)

    override fun deserializeNBT(tag: NBTBase)
    {
        value = EnumFacing.byIndex((tag as NBTTagInt).int)
    }
}
