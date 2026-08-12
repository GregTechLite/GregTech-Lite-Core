package gregtechlite.gtlitecore.api.metatileentity

import com.morphismmc.morphismlib.util.Unchecks
import gregtech.api.metatileentity.MetaTileEntity
import gregtechlite.gtlitecore.api.LOGGER
import gregtechlite.gtlitecore.api.data.Schema
import gregtechlite.gtlitecore.api.data.handle.CheckStrategy
import gregtechlite.gtlitecore.api.data.handle.DiffHandle
import gregtechlite.gtlitecore.api.data.handle.DiffObservable
import gregtechlite.gtlitecore.api.data.handle.Handle
import gregtechlite.gtlitecore.api.data.handle.handleOf
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
    private val handles = hashMapOf<String, Handle<*>>()

    fun <T> synced(schema: Schema<T>): SyncedField<T> = SyncedField(this, schema)

    fun <T> serialize(schema: Schema<T>, handle: Handle<T>): Handle<T>
    {
        serializers.register(schema, handle)
        handles[schema.name] = handle
        return handle
    }

    fun <T> expose(schema: Schema<T>, handle: Handle<T>): Handle<T>
    {
        exposes.register(HandleExpose(schema.name, schema, handle))
        handle.onChange { _, _ -> markDirty() }
        handles[schema.name] = handle
        return handle
    }

    fun <T : DiffObservable<D>, D> syncedDiff(schema: Schema<T>, persist: Boolean = true): DiffHandle<T, D>
    {
        val handle = handleOf(schema.initial, CheckStrategy.AlwaysUpdate)
        if (persist) serializers.register(schema, handle)
        exposes.register(DiffExpose(schema.name, schema, DiffHandle(handle)))
        handle.onChange { _, _ -> markDirty() }
        handles[schema.name] = handle
        return DiffHandle(handle)
    }

    fun <T> handle(name: String): Handle<T> = Unchecks.cast(handles[name] ?: error("No handle declared with name '$name'"))

    internal fun <T> declare(schema: Schema<T>): Handle<T>
    {
        val handle = handleOf(schema.initial, schema.strategy)
        serialize(schema, handle)
        expose(schema, handle)
        return handle
    }

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
        operator fun provideDelegate(thisRef: Any?, property: KProperty<*>): Handle<T>
            = sync.declare(schema.copy(name = schema.name.ifEmpty { property.name }))
    }
}

fun MetaTileEntitySync.syncedInt(initial: Int = 0) = synced(Schema.int(initial = initial))
fun MetaTileEntitySync.syncedLong(initial: Long = 0L) = synced(Schema.long(initial = initial))
fun MetaTileEntitySync.syncedShort(initial: Short = 0) = synced(Schema.short(initial = initial))
fun MetaTileEntitySync.syncedByte(initial: Byte = 0) = synced(Schema.byte(initial = initial))
fun MetaTileEntitySync.syncedFloat(initial: Float = 0.0f) = synced(Schema.float(initial = initial))
fun MetaTileEntitySync.syncedDouble(initial: Double = 0.0) = synced(Schema.double(initial = initial))
fun MetaTileEntitySync.syncedBoolean(initial: Boolean = false) = synced(Schema.boolean(initial = initial))
fun MetaTileEntitySync.syncedString(initial: String = "") = synced(Schema.string(initial = initial))
fun MetaTileEntitySync.syncedNBT(initial: NBTTagCompound = NBTTagCompound()) = synced(Schema.nbt(initial = initial))