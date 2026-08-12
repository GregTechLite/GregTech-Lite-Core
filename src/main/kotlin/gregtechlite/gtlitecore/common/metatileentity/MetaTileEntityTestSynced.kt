package gregtechlite.gtlitecore.common.metatileentity

import gregtech.api.metatileentity.MetaTileEntity
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtechlite.gtlitecore.api.LOGGER
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.data.handle.getValue
import gregtechlite.gtlitecore.api.data.handle.setValue
import gregtechlite.gtlitecore.api.metatileentity.MetaTileEntitySync
import gregtechlite.gtlitecore.api.data.Schema
import gregtechlite.gtlitecore.api.data.handle.DiffObservable
import gregtechlite.gtlitecore.api.metatileentity.SyncedMetaTileEntity
import net.minecraft.network.PacketBuffer
import net.minecraft.util.ResourceLocation
import org.jetbrains.annotations.TestOnly

@TestOnly
class MetaTileEntityTestSynced(id: ResourceLocation) : MetaTileEntity(id), SyncedMetaTileEntity
{
    override val sync = MetaTileEntitySync(this)

    private val countHandle = sync.synced(Schema.int("count", 0))
    private val labelHandle = sync.synced(Schema.string("label", ""))
    private val diskOnlyHandle = sync.persistedOnly(Schema.string("disk_only", ""))
    private val progressHandle = sync.syncedDiff(Schema.diff("progress", FloatProgressValue(0f),
        { buf, value -> buf.writeFloat(value.value) },
        { buf -> FloatProgressValue(buf.readFloat()) }), persist = false)

    var count: Int by countHandle
    var label: String by labelHandle
    var diskOnly: String by diskOnlyHandle
    val progress: Float
        get() = progressHandle.current.value

    init
    {
        countHandle.onChange { newVal, oldVal ->
            if (world != null && world.isRemote)
            {
                LOGGER.info("Client count synced @ $pos: $oldVal -> $newVal")
            }
        }
    }

    override fun createMetaTileEntity(te: IGregTechTileEntity): MetaTileEntity
        = MetaTileEntityTestSynced(metaTileEntityId)

    override fun update()
    {
        super.update()
        if (world != null && !world.isRemote)
        {
            if (offsetTimer % SECOND == 0L)
            {
                count += 1
                label = "tick-$offsetTimer"
                progressHandle.current.value = ((offsetTimer / SECOND) % 100f)
                LOGGER.info("Server update @ $pos: count=$count label=$label progress=$progress")
            }
        }
    }

    private inner class FloatProgressValue(initial: Float) : DiffObservable<Float>
    {
        var value: Float = initial
            set(newVal) { field = newVal; changed = true }

        private var lastSent: Float = initial
        private var changed: Boolean = false

        override fun isChanged(): Boolean = changed

        override fun writeDifference(buf: PacketBuffer)
        {
            buf.writeFloat(value - lastSent)
            lastSent = value
            changed = false
        }

        override fun readDifference(buf: PacketBuffer)
        {
            val delta = buf.readFloat()
            value = value + delta
            changed = false
        }
    }
}
