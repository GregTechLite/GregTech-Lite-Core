package gregtechlite.gtlitecore.api.data.serialize

import gregtechlite.gtlitecore.api.data.handle.Handle
import gregtechlite.gtlitecore.api.data.Schema
import net.minecraft.nbt.NBTTagCompound

class SerializerManagement
{
    private val serializers = linkedMapOf<String, Serialize<*>>()

    fun <T> register(schema: Schema<T>, handle: Handle<T>)
    {
        require(serializers.putIfAbsent(schema.name, Serialize(schema, handle)) == null) {
            "Duplicate serializer '${schema.name}'"
        }
    }

    fun saveAll(tag: NBTTagCompound)
    {
        for ((name, serialize) in serializers)
            serialize.save(tag, name)
    }

    fun loadAll(tag: NBTTagCompound)
    {
        for ((name, serialize) in serializers)
            serialize.load(tag, name)
    }

    private class Serialize<T>(private val schema: Schema<T>, private val handle: Handle<T>)
    {
        fun save(tag: NBTTagCompound, name: String) = schema.nbtWriter(tag, name, handle.current)

        fun load(tag: NBTTagCompound, name: String)
        {
            if (tag.hasKey(name))
                handle.load(schema.nbtReader(tag, name))
        }
    }
}
