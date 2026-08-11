package gregtechlite.gtlitecore.api.network.sync

import gregtech.api.metatileentity.MetaTileEntity
import gregtechlite.gtlitecore.api.collection.idHashMapOf
import gregtechlite.gtlitecore.api.network.payload.TypedPayloadRegistry
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.network.PacketBuffer

object ManagedFieldRegistry
{
    const val MANAGED_SYNC_CODE: Int = 0x6D616E
    const val MANAGED_NBT_KEY: String = "Managed"

    private val cacheRegistries = idHashMapOf<MetaTileEntity, ManagedFieldCache>()

    @JvmStatic
    fun init() // TODO: Now we init it before all MTE registration in its context, should we remove it to CoreModule class?
    {
        TypedPayloadRegistry.registerAll()
    }

    @JvmStatic
    fun getCache(mte: MetaTileEntity): ManagedFieldCache
    {
        TypedPayloadRegistry.registerAll()
        synchronized(cacheRegistries)
        {
            return cacheRegistries.computeIfAbsent(mte) { ManagedFieldCache((it as ManageableMachine).managedHolder, it) }
        }
    }

    @JvmStatic
    fun removeCache(mte: MetaTileEntity)
    {
        synchronized(cacheRegistries)
        {
            cacheRegistries.remove(mte)
        }
    }

    @JvmStatic
    fun writeToNBT(mte: MetaTileEntity, tag: NBTTagCompound)
    {
        if (mte is ManageableMachine) getCache(mte).writeNBT(tag)
    }

    @JvmStatic
    fun readFromNBT(mte: MetaTileEntity, tag: NBTTagCompound)
    {
        if (mte is ManageableMachine) getCache(mte).readNBT(tag)
    }

    @JvmStatic
    fun writeInitialSync(mte: MetaTileEntity, buf: PacketBuffer)
    {
        if (mte is ManageableMachine) getCache(mte).writeInitialSync(buf)
    }

    @JvmStatic
    fun readInitialSync(mte: MetaTileEntity, buf: PacketBuffer)
    {
        if (mte is ManageableMachine) getCache(mte).readInitialSync(buf)
    }

    @JvmStatic
    fun tickSync(mte: MetaTileEntity)
    {
        if (mte is ManageableMachine) getCache(mte).tickSyncServer()
    }

    @JvmStatic
    fun handleCustomData(mte: MetaTileEntity, discriminator: Int, buf: PacketBuffer): Boolean
    {
        if (discriminator != MANAGED_SYNC_CODE) return false
        if (mte is ManageableMachine) getCache(mte).handleCustomData(buf)
        return true
    }
}