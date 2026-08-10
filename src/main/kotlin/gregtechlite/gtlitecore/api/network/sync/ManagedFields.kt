package gregtechlite.gtlitecore.api.network.sync

import gregtech.api.metatileentity.MetaTileEntity
import gregtechlite.gtlitecore.api.collection.idHashMapOf
import gregtechlite.gtlitecore.api.network.payload.TypedPayloadRegistry
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.network.PacketBuffer

object ManagedFields
{
    const val MANAGED_SYNC_CODE: Int = 0x6D616E
    const val MANAGED_NBT_KEY: String = "Managed"

    private val storageRegistry = idHashMapOf<MetaTileEntity, ManagedFieldCache>()

    @JvmStatic
    fun init() // TODO: Now we init it before all MTE registration in its context, should we remove it to CoreModule class?
    {
        TypedPayloadRegistry.registerAll()
    }

    @JvmStatic
    fun getStorage(mte: MetaTileEntity): ManagedFieldCache
    {
        TypedPayloadRegistry.registerAll()
        synchronized(storageRegistry)
        {
            return storageRegistry.computeIfAbsent(mte) { ManagedFieldCache((it as ManageableMachine).managedHolder, it) }
        }
    }

    @JvmStatic
    fun writeToNBT(mte: MetaTileEntity, tag: NBTTagCompound)
    {
        if (mte is ManageableMachine) getStorage(mte).writeNBT(tag)
    }

    @JvmStatic
    fun readFromNBT(mte: MetaTileEntity, tag: NBTTagCompound)
    {
        if (mte is ManageableMachine) getStorage(mte).readNBT(tag)
    }

    @JvmStatic
    fun writeInitialSync(mte: MetaTileEntity, buf: PacketBuffer)
    {
        if (mte is ManageableMachine) getStorage(mte).writeInitialSync(buf)
    }

    @JvmStatic
    fun readInitialSync(mte: MetaTileEntity, buf: PacketBuffer)
    {
        if (mte is ManageableMachine) getStorage(mte).readInitialSync(buf)
    }

    @JvmStatic
    fun tickSync(mte: MetaTileEntity)
    {
        if (mte is ManageableMachine) getStorage(mte).tickSyncServer()
    }

    @JvmStatic
    fun handleCustomData(mte: MetaTileEntity, discriminator: Int, buf: PacketBuffer): Boolean
    {
        if (discriminator != MANAGED_SYNC_CODE) return false
        if (mte is ManageableMachine) getStorage(mte).handleCustomData(buf)
        return true
    }
}