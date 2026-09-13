package gregtechlite.gtlitecore.api.gui.sync

import com.cleanroommc.modularui.network.NetworkUtils
import com.cleanroommc.modularui.value.sync.FluidSlotSyncHandler
import com.cleanroommc.modularui.value.sync.ValueSyncHandler
import net.minecraft.network.PacketBuffer
import net.minecraftforge.fluids.FluidStack

class FluidDisplaySyncHandler(val getter: (() -> FluidStack?)?,
                              val setter: ((FluidStack?) -> Unit)?) : ValueSyncHandler<FluidStack>()
{
    private var cache: FluidStack? = null

    constructor(getter: (() -> FluidStack?)?) : this(getter, null)

    override fun setValue(value: FluidStack?, setSource: Boolean, sync: Boolean)
    {
        cache = FluidSlotSyncHandler.copyFluid(value)
        if (setSource && setter != null)
        {
            setter.invoke(FluidSlotSyncHandler.copyFluid(value))
        }
        if (sync)
        {
            if (NetworkUtils.isClient()) syncToServer(0, ::write) else syncToClient(0, ::write)
        }
        onValueChanged()
    }

    fun needsSync(): Boolean
    {
        val current = getter?.invoke()
        if (current == cache) return false
        if (current == null || cache == null) return true
        return current.amount != cache!!.amount || !current.isFluidEqual(cache)
    }

    override fun updateCacheFromSource(isFirstSync: Boolean): Boolean
    {
        if (isFirstSync || needsSync())
        {
            setValue(getter?.invoke(), setSource = false, sync = false)
            return true
        }
        return false
    }

    override fun notifyUpdate()
    {
        setValue(getter?.invoke(), setSource = false, sync = true)
    }

    override fun write(buffer: PacketBuffer?)
    {
        NetworkUtils.writeFluidStack(buffer, cache)
    }

    override fun read(buffer: PacketBuffer?)
    {
        setValue(NetworkUtils.readFluidStack(buffer), setSource = true, sync = false)
    }

    override fun getValue(): FluidStack? = if (cache == null) null else FluidSlotSyncHandler.copyFluid(cache)

    override fun getValueType(): Class<FluidStack> = FluidStack::class.java
}