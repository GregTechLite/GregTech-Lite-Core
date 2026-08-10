package gregtechlite.gtlitecore.api.network.sync

import gregtech.api.metatileentity.MetaTileEntity
import gregtechlite.gtlitecore.api.LOGGER
import gregtechlite.gtlitecore.api.network.payload.TypedPayloadRegistry
import gregtechlite.gtlitecore.api.network.payload.createEmptyPayload
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.network.PacketBuffer
import java.lang.reflect.Method

class ManagedFieldCache(private val holder: ManagedFieldHolder, val mte: MetaTileEntity)
{
    private val refs: Array<ManagedRef> = holder.keys.map { ManagedRef(it, mte) }.toTypedArray()
    private val syncRefs: Array<ManagedRef> = holder.syncKeys.map { ManagedRef(it, mte) }.toTypedArray()
    private val persistRefs: Array<ManagedRef> = holder.persistKeys.map { ManagedRef(it, mte) }.toTypedArray()
    private val byName: Map<String, ManagedRef> = refs.associateBy { it.key.name }
    private val listeners: Map<String, List<(String, Any?, Any?) -> Unit>>

    init
    {
        syncRefs.forEach { it.markSyncClean() }
        listeners = buildListeners()

        LOGGER.debug("Storage managed initialized for {} @ {}: syncFields={} persistedFields={}",
            mte.javaClass.simpleName, mte.pos,
            syncRefs.joinToString(", ") { it.key.name },
            persistRefs.joinToString(", ") { it.key.name })
    }

    fun getRef(name: String): ManagedRef? = byName[name]

    // region NBT

    fun writeNBT(tag: NBTTagCompound)
    {
        val managedTag = NBTTagCompound()
        for (ref in persistRefs)
        {
            val nbt = ref.toPayload().serializeNBT()
            if (nbt != null) managedTag.setTag(ref.key.persistentKey, nbt)
        }
        tag.setTag(ManagedFields.MANAGED_NBT_KEY, managedTag)
        LOGGER.debug("Managed writeToNBT @ {}: {}", mte.pos, printFields(persistRefs))
    }

    fun readNBT(tag: NBTTagCompound)
    {
        if (tag.hasKey(ManagedFields.MANAGED_NBT_KEY))
        {
            val managedTag = tag.getCompoundTag(ManagedFields.MANAGED_NBT_KEY)
            for (ref in persistRefs)
            {
                if (managedTag.hasKey(ref.key.persistentKey))
                {
                    val payload = createEmptyPayload(ref.key.field.type)
                    if (payload != null)
                    {
                        payload.deserializeNBT(managedTag.getTag(ref.key.persistentKey))
                        ref.fromPayload(payload)
                        if (ref.key.isSync) ref.markSyncClean()
                    }
                }
            }
        }
        LOGGER.debug("Managed readFromNBT @ {}: {}", mte.pos, printFields(persistRefs))
    }

    // endregion

    // region Initial Data

    fun writeInitialSync(buf: PacketBuffer)
    {
        buf.writeVarInt(syncRefs.size)
        for (ref in syncRefs)
        {
            val payload = ref.toPayload()
            buf.writeByte(payload.type.toInt())
            payload.writePayload(buf)
        }
        LOGGER.debug("Managed writeInitialSync @ {}: {}", mte.pos, printFields(syncRefs))
    }

    fun readInitialSync(buf: PacketBuffer)
    {
        val count = buf.readVarInt()
        for (i in 0 until count)
        {
            if (i >= syncRefs.size)
            {
                TypedPayloadRegistry.create(buf.readByte())?.readPayload(buf)
                continue
            }
            val ref = syncRefs[i]
            val payload = TypedPayloadRegistry.create(buf.readByte())
            if (payload == null)
            {
                LOGGER.warn("Managed readInitialSync: unknown payload type @ {}", mte.pos)
                continue
            }
            payload.readPayload(buf)
            val old = ref.currentValue()
            ref.fromPayload(payload)
            ref.markSyncClean()
            notifyListeners(ref.key.name, old, ref.currentValue())
        }
        LOGGER.debug("Managed readInitialSync @ {}: {}", mte.pos, printFields(syncRefs))
    }

    // endregion

    // region Custom Data

    fun tickSyncServer()
    {
        val changed = syncRefs.filter { it.isSyncDirty() }
        if (changed.isEmpty()) return

        LOGGER.debug("Managed tickSync @ {}: changed=[{}], sending {} payloads",
            mte.pos, changed.joinToString(", ") { it.key.name }, changed.size)

        mte.writeCustomData(ManagedFields.MANAGED_SYNC_CODE) {
            it.writeVarInt(changed.size)
            for (ref in changed)
            {
                it.writeVarInt(syncRefs.indexOf(ref))
                val payload = ref.toPayload()
                it.writeByte(payload.type.toInt())
                payload.writePayload(it)
            }
        }
        changed.forEach { it.markSyncClean() }
    }

    fun handleCustomData(buf: PacketBuffer)
    {
        val count = buf.readVarInt()
        repeat (count)
        {
            val index = buf.readVarInt()
            val typeId = buf.readByte()
            if (index < 0 || index >= syncRefs.size)
            {
                TypedPayloadRegistry.create(typeId)?.readPayload(buf)
                return@repeat
            }
            val ref = syncRefs[index]
            val payload = TypedPayloadRegistry.create(typeId) ?: return@repeat
            payload.readPayload(buf)
            val old = ref.currentValue()
            ref.fromPayload(payload)
            ref.markSyncClean()
            notifyListeners(ref.key.name, old, ref.currentValue())
            LOGGER.debug("Managed handleCustomData @ {}: {} {}->{}", mte.pos, ref.key.name, old, ref.currentValue())
        }
    }

    // endregion

    // region Update Listener

    private fun buildListeners(): Map<String, List<(String, Any?, Any?) -> Unit>>
    {
        val map = hashMapOf<String, MutableList<(String, Any?, Any?) -> Unit>>()
        for (ref in syncRefs)
        {
            val methodName = ref.key.updateListenerMethod ?: continue
            val method = findListenerMethod(methodName)
            if (method == null)
            {
                LOGGER.warn("Managed @UpdateListener method '{}' not found on {}",
                             methodName, mte.javaClass.simpleName)
                continue
            }
            map.getOrPut(ref.key.name) { ArrayList() }.add { _, newVal, oldVal -> invokeListener(method, newVal, oldVal) }
        }
        return map
    }

    private fun findListenerMethod(methodName: String): Method? =
        mte.javaClass.declaredMethods.firstOrNull { it.name == methodName && it.parameterCount == 2 }

    private fun invokeListener(method: Method, newVal: Any?, oldVal: Any?)
    {
        try
        {
            method.invoke(mte, newVal, oldVal)
        }
        catch (e: Exception)
        {
            LOGGER.error("Managed @UpdateListener invocation failed: {}", e.message, e)
        }
    }

    private fun notifyListeners(name: String, old: Any?, new: Any?)
    {
        listeners[name]?.forEach { it(name, new, old) }
    }

    // endregion

    private fun printFields(refs: Array<ManagedRef>): String
        = refs.joinToString(", ") { "${it.key.name}=${it.currentValue()}" }
}