package gregtechlite.gtlitecore.common.metatileentity

import gregtech.api.metatileentity.MetaTileEntity
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtechlite.gtlitecore.api.LOGGER
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.data.handler.handlerOf
import gregtechlite.gtlitecore.api.metatileentity.MetaTileEntitySync
import gregtechlite.gtlitecore.api.data.Schema
import gregtechlite.gtlitecore.api.data.handler.DiffObservable
import gregtechlite.gtlitecore.api.metatileentity.SyncedMetaTileEntity
import gregtechlite.gtlitecore.api.metatileentity.syncedInt
import gregtechlite.gtlitecore.api.metatileentity.syncedString
import net.minecraft.network.PacketBuffer
import net.minecraft.util.ResourceLocation
import org.jetbrains.annotations.TestOnly

@TestOnly
class MetaTileEntityTestSynced(id: ResourceLocation) : MetaTileEntity(id), SyncedMetaTileEntity
{
    override val sync = MetaTileEntitySync(this)

    var count by sync.syncedInt()
    var label by sync.syncedString()

    var diskOnly by sync.serialize(Schema.string("disk_only", ""), handlerOf(""))
    var runtimeOnly by sync.expose(Schema.int("runtime_only", 0), handlerOf(0))

    private val progressHandle = sync.syncedDiff(Schema.diff("progress", FloatProgressValue(0f),
        { buf, value -> buf.writeFloat(value.value) },
        { buf -> FloatProgressValue(buf.readFloat()) }), persist = false)

    val progress: Float
        get() = progressHandle.value.value

    init
    {
        sync.handle<Int>("count").onChange { newVal, oldVal ->
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
                runtimeOnly = count
                progressHandle.value.value = ((offsetTimer / SECOND) % 100f)
                LOGGER.info("Server update @ $pos: count=$count label=$label runtime=$runtimeOnly progress=$progress")
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
