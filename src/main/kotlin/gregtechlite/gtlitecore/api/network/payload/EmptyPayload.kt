package gregtechlite.gtlitecore.api.network.payload

import net.minecraft.nbt.NBTBase
import net.minecraft.network.PacketBuffer

object EmptyPayload : TypedPayload<Nothing?>
{
    const val TYPE_ID: Byte = 12

    override val type: Byte = TYPE_ID

    override val payload: Nothing? = null

    override fun writePayload(buf: PacketBuffer) {}

    override fun readPayload(buf: PacketBuffer) {}

    override fun serializeNBT(): NBTBase? = null

    override fun deserializeNBT(tag: NBTBase) {}
}
