package gregtechlite.gtlitecore.api.data

import gregtechlite.gtlitecore.api.data.handle.CheckStrategy
import gregtechlite.gtlitecore.api.data.handle.DiffObservable
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.network.PacketBuffer

data class Schema<T>(val name: String, val initial: T, val strategy: CheckStrategy<T>,
                     val nbtWriter: (NBTTagCompound, String, T) -> Unit, val nbtReader: (NBTTagCompound, String) -> T,
                     val dataWriter: (PacketBuffer, T) -> Unit, val dataReader: (PacketBuffer) -> T)
{
    companion object
    {
        fun <T : DiffObservable<D>, D> diff(name: String = "", initial: T,
                                            dataWriter: (PacketBuffer, T) -> Unit,
                                            dataReader: (PacketBuffer) -> T): Schema<T>
            = Schema(name, initial, CheckStrategy.AlwaysUpdate,
                     { tag, key, _ -> tag.setFloat(key, 0f) }, { tag, key -> initial }, dataWriter, dataReader)

        // region Primitive

        fun int(name: String = "", initial: Int = 0): Schema<Int> = Schema(name, initial, CheckStrategy.Equals,
            { tag, key, value -> tag.setInteger(key, value) }, { tag, key -> tag.getInteger(key) },
            { buf, value -> buf.writeInt(value) }, { it.readInt() })

        fun long(name: String = "", initial: Long = 0L): Schema<Long> = Schema(name, initial, CheckStrategy.Equals,
            { tag, key, value -> tag.setLong(key, value) }, { tag, key -> tag.getLong(key) },
            { buf, value -> buf.writeLong(value) }, { it.readLong() })

        fun short(name: String = "", initial: Short = 0): Schema<Short> = Schema(name, initial, CheckStrategy.Equals,
            { tag, key, value -> tag.setShort(key, value) }, { tag, key -> tag.getShort(key) },
            { buf, value -> buf.writeShort(value.toInt()) }, { it.readShort() })

        fun byte(name: String = "", initial: Byte = 0): Schema<Byte> = Schema(name, initial, CheckStrategy.Equals,
            { tag, key, value -> tag.setByte(key, value) }, { tag, key -> tag.getByte(key) },
            { buf, value -> buf.writeByte(value.toInt()) }, { it.readByte() })

        fun float(name: String = "", initial: Float = 0f): Schema<Float> = Schema(name, initial, CheckStrategy.Equals,
            { tag, key, value -> tag.setFloat(key, value) }, { tag, key -> tag.getFloat(key) },
            { buf, value -> buf.writeFloat(value) }, { it.readFloat() })

        fun double(name: String = "", initial: Double = 0.0): Schema<Double> = Schema(name, initial, CheckStrategy.Equals,
            { tag, key, value -> tag.setDouble(key, value) }, { tag, key -> tag.getDouble(key) },
            { buf, value -> buf.writeDouble(value) }, { it.readDouble() })

        fun boolean(name: String = "", initial: Boolean = false): Schema<Boolean> = Schema(name, initial, CheckStrategy.Equals,
            { tag, key, value -> tag.setBoolean(key, value) }, { tag, key -> tag.getBoolean(key) },
            { buf, value -> buf.writeBoolean(value) }, { it.readBoolean() })

        fun string(name: String = "", initial: String = ""): Schema<String> = Schema(name, initial, CheckStrategy.Equals,
            { tag, key, value -> tag.setString(key, value) }, { tag, key -> tag.getString(key) },
            { buf, value -> buf.writeString(value) }, { it.readString(Short.MAX_VALUE.toInt()) }) // TODO: Configurable?

        // endregion

        fun nbt(name: String = "", initial: NBTTagCompound = NBTTagCompound()): Schema<NBTTagCompound> = Schema(name, initial, CheckStrategy.Equals,
            { tag, key, value -> tag.setTag(key, value.copy()) }, { tag, key -> tag.getCompoundTag(key) },
            { buf, value -> buf.writeCompoundTag(value) }, { it.readCompoundTag() ?: NBTTagCompound() })
    }
}