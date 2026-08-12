package gregtechlite.gtlitecore.api.metatileentity

import com.morphismmc.morphismlib.util.Unchecks
import gregtech.api.metatileentity.MetaTileEntity
import gregtechlite.gtlitecore.api.LOGGER
import gregtechlite.gtlitecore.api.data.Schema
import gregtechlite.gtlitecore.api.data.handler.CheckStrategy
import gregtechlite.gtlitecore.api.data.handler.DiffHandler
import gregtechlite.gtlitecore.api.data.handler.DiffObservable
import gregtechlite.gtlitecore.api.data.handler.FlowHandler
import gregtechlite.gtlitecore.api.data.handler.handlerOf
import gregtechlite.gtlitecore.api.data.serialize.SerializerManagement
import gregtechlite.gtlitecore.api.network.expose.DiffExpose
import gregtechlite.gtlitecore.api.network.expose.ExposeManagement
import gregtechlite.gtlitecore.api.network.expose.HandleExpose
import kotlin.reflect.KProperty
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.network.PacketBuffer

class MetaTileEntitySync(private val mte: MetaTileEntity)
{
    companion object
    {
        const val SYNC_CODE: Int = 0x6D616E
        const val NBT_KEY: String = "GTSync"
    }

    private val serializers = SerializerManagement()
    private val exposes = ExposeManagement()
    private val handlers = hashMapOf<String, FlowHandler<*>>()

    // region Delegator

    fun <T> synced(schema: Schema<T>): SyncedField<T> = SyncedField(this, schema)

    fun <T> serialize(schema: Schema<T>, handler: FlowHandler<T>): FlowHandler<T>
    {
        serializers.register(schema, handler)
        handler.onChange { _, _ ->
            if (mte.world?.isRemote == false) mte.markDirty()
        }
        handlers[schema.name] = handler
        return handler
    }

    fun <T> expose(schema: Schema<T>, handler: FlowHandler<T>): FlowHandler<T>
    {
        exposes.register(HandleExpose(schema.name, schema, handler))
        handler.onChange { _, _ -> markDirty() }
        handlers[schema.name] = handler
        return handler
    }

    fun <T : DiffObservable<D>, D> syncedDiff(schema: Schema<T>, persist: Boolean = true): DiffHandler<T, D>
    {
        val handle = handlerOf(schema.initial, CheckStrategy.AlwaysUpdate)
        if (persist) serializers.register(schema, handle)
        exposes.register(DiffExpose(schema.name, schema, DiffHandler(handle)))
        handle.onChange { _, _ -> markDirty() }
        handlers[schema.name] = handle
        return DiffHandler(handle)
    }

    fun <T> handle(name: String): FlowHandler<T> = Unchecks.cast(handlers[name] ?: error("No handle declared with name '$name'"))

    internal fun <T> declare(schema: Schema<T>): FlowHandler<T>
    {
        val handle = handlerOf(schema.initial, schema.strategy)
        serialize(schema, handle)
        expose(schema, handle)
        return handle
    }

    // endregion

    // region Sync Shortcut

    fun syncedInt(initial: Int = 0) = synced(Schema.int(initial = initial))

    fun syncedLong(initial: Long = 0L) = synced(Schema.long(initial = initial))

    fun syncedShort(initial: Short = 0) = synced(Schema.short(initial = initial))

    fun syncedByte(initial: Byte = 0) = synced(Schema.byte(initial = initial))

    fun syncedFloat(initial: Float = 0.0f) = synced(Schema.float(initial = initial))

    fun syncedDouble(initial: Double = 0.0) = synced(Schema.double(initial = initial))

    fun syncedBoolean(initial: Boolean = false) = synced(Schema.boolean(initial = initial))

    fun syncedString(initial: String = "") = synced(Schema.string(initial = initial))

    fun syncedNBT(initial: NBTTagCompound = NBTTagCompound()) = synced(Schema.nbt(initial = initial))

    // endregion

    // region Operation

    fun flushChanges()
    {
        if (mte.world?.isRemote != false) return
        if (!exposes.hasChanges()) return
        mte.writeCustomData(SYNC_CODE) { exposes.writeChangesToClient(it) }
        exposes.clearAllDirty()
        LOGGER.info("Sent '${mte.metaTileEntityId}' sync custom data")
    }

    private fun markDirty()
    {
        val world = mte.world ?: return
        if (!world.isRemote) MetaTileEntitySyncBatcher.get(world.provider.dimension).markDirty(this)
    }

    fun writeInitialSync(buf: PacketBuffer) = exposes.writeAllToClient(buf)

    fun receiveInitialSync(buf: PacketBuffer)
    {
        exposes.receiveFromServer(buf)
        LOGGER.info("Received initial sync")
    }

    fun receiveCustomData(buf: PacketBuffer)
    {
        exposes.receiveFromServer(buf)
        LOGGER.info("Received custom data sync")
    }

    fun saveToNBT(tag: NBTTagCompound)
    {
        val sub = NBTTagCompound()
        serializers.saveAll(sub)
        tag.setTag(NBT_KEY, sub)
    }

    fun loadFromNBT(tag: NBTTagCompound)
    {
        if (tag.hasKey(NBT_KEY)) serializers.loadAll(tag.getCompoundTag(NBT_KEY))
    }

    class SyncedField<T> internal constructor(private val sync: MetaTileEntitySync, private val schema: Schema<T>)
    {
        operator fun provideDelegate(thisRef: Any?, property: KProperty<*>): FlowHandler<T>
            = sync.declare(schema.copy(name = schema.name.ifEmpty { property.name }))
    }

    // endregion
}