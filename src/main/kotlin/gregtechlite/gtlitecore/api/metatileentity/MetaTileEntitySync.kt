package gregtechlite.gtlitecore.api.metatileentity

import gregtech.api.metatileentity.MetaTileEntity
import gregtechlite.gtlitecore.api.LOGGER
import gregtechlite.gtlitecore.api.data.handle.CheckStrategy
import gregtechlite.gtlitecore.api.data.handle.DiffHandle
import gregtechlite.gtlitecore.api.data.handle.DiffObservable
import gregtechlite.gtlitecore.api.data.handle.Handle
import gregtechlite.gtlitecore.api.data.handle.handleOf
import gregtechlite.gtlitecore.api.data.serialize.SerializerManagement
import gregtechlite.gtlitecore.api.network.expose.DiffExpose
import gregtechlite.gtlitecore.api.network.expose.ExposeManagement
import gregtechlite.gtlitecore.api.network.expose.HandleExpose
import gregtechlite.gtlitecore.api.data.Schema
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

    fun <T> synced(schema: Schema<T>, persist: Boolean = true): Handle<T>
    {
        val handle = handleOf(schema.initial, schema.strategy)
        if (persist) serializers.register(schema, handle)
        exposes.register(HandleExpose(schema.name, schema, handle))
        handle.onChange { _, _ -> markDirty() }
        return handle
    }

    fun <T> persistedOnly(schema: Schema<T>): Handle<T>
    {
        val handle = handleOf(schema.initial, schema.strategy)
        serializers.register(schema, handle)
        return handle
    }

    fun <T : DiffObservable<D>, D> syncedDiff(schema: Schema<T>, persist: Boolean = true): DiffHandle<T, D>
    {
        val handle = handleOf(schema.initial, CheckStrategy.ALWAYS_UPDATE)
        if (persist) serializers.register(schema, handle)
        exposes.register(DiffExpose(schema.name, schema, DiffHandle(handle)))
        handle.onChange { _, _ -> markDirty() }
        return DiffHandle(handle)
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
}