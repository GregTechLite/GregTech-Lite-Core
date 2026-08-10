package gregtechlite.gtlitecore.api.network.payload

import net.minecraft.nbt.NBTBase
import net.minecraft.network.PacketBuffer

/**
 * | Payload Type     | Type Id |
 * |------------------|---------|
 * | [IntPayload]     | 1       |
 * | [LongPayload]    | 2       |
 * | [FloatPayload]   | 3       |
 * | [DoublePayload]  | 4       |
 * | [BytePayload]    | 5       |
 * | [ShortPayload]   | 6       |
 * | [BooleanPayload] | 7       |
 * | [CharPayload]    | 8       |
 * | [StringPayload]  | 9       |
 * | [NBTTagPayload]  | 10      |
 * | [EmptyPayload]   | 12      |
 */
interface TypedPayload<T>
{
    val type: Byte

    val payload: T

    fun writePayload(buf: PacketBuffer)

    fun readPayload(buf: PacketBuffer)

    fun serializeNBT(): NBTBase?

    fun deserializeNBT(tag: NBTBase)
}