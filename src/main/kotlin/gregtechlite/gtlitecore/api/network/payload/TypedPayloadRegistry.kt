package gregtechlite.gtlitecore.api.network.payload

import gregtechlite.gtlitecore.api.collection.byteVOpenHashMapOf
import gregtechlite.gtlitecore.api.collection.intOpenHashMapOf

object TypedPayloadRegistry
{
    private val factories = intOpenHashMapOf<() -> TypedPayload<*>>()
    private val ids = byteVOpenHashMapOf<Class<*>>()

    private var registered = false

    @JvmStatic
    fun register(clazz: Class<*>, id: Byte, factory: () -> TypedPayload<*>)
    {
        factories[id.toInt()] = factory
        ids[clazz] = id
    }

    @JvmStatic
    fun create(id: Byte): TypedPayload<*>? = factories[id.toInt()]?.invoke()

    @JvmStatic
    fun getId(clazz: Class<*>): Byte = ids[clazz] ?: (-1).toByte()

    @JvmStatic
    fun registerAll()
    {
        if (registered) return
        registered = true
        register(IntPayload::class.java, IntPayload.TYPE_ID) { IntPayload() }
        register(LongPayload::class.java, LongPayload.TYPE_ID) { LongPayload() }
        register(FloatPayload::class.java, FloatPayload.TYPE_ID) { FloatPayload() }
        register(DoublePayload::class.java, DoublePayload.TYPE_ID) { DoublePayload() }
        register(BytePayload::class.java, BytePayload.TYPE_ID) { BytePayload() }
        register(ShortPayload::class.java, ShortPayload.TYPE_ID) { ShortPayload() }
        register(BooleanPayload::class.java, BooleanPayload.TYPE_ID) { BooleanPayload() }
        register(CharPayload::class.java, CharPayload.TYPE_ID) { CharPayload() }
        register(StringPayload::class.java, StringPayload.TYPE_ID) { StringPayload() }
        register(NBTTagPayload::class.java, NBTTagPayload.TYPE_ID) { NBTTagPayload() }
        register(EnumFacingPayload::class.java, EnumFacingPayload.TYPE_ID) { EnumFacingPayload() }
        register(EmptyPayload::class.java, EmptyPayload.TYPE_ID) { EmptyPayload }
    }
}
