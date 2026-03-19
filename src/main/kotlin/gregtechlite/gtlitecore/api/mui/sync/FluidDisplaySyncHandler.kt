package gregtechlite.gtlitecore.api.mui.sync

import com.cleanroommc.modularui.network.NetworkUtils
import com.cleanroommc.modularui.value.sync.FluidSlotSyncHandler.copyFluid
import com.cleanroommc.modularui.value.sync.ValueSyncHandler
import net.minecraft.network.PacketBuffer
import net.minecraftforge.fluids.FluidStack
import java.util.function.Consumer
import java.util.function.Supplier

class FluidDisplaySyncHandler(
    val getter: Supplier<FluidStack?>?,
    val setter: Consumer<FluidStack?>?) : ValueSyncHandler<FluidStack>()
{
    private var cache: FluidStack? = null

    constructor(getter: Supplier<FluidStack?>?) : this(getter, null)

    override fun setValue(value: FluidStack?, setSource: Boolean, sync: Boolean)
    {
        this.cache = copyFluid(value)
        if (setSource && this.setter != null)
        {
            this.setter.accept(copyFluid(value))
        }
        if (sync)
        {
            if (NetworkUtils.isClient())
            {
                syncToServer(0, this::write)
            } else
            {
                syncToClient(0, this::write)
            }
        }
        onValueChanged()
    }

    fun needsSync(): Boolean
    {
        val current: FluidStack? = this.getter?.get()
        if (current == this.cache) return false
        if (current == null || this.cache == null) return true
        return current.amount != this.cache!!.amount || !current.isFluidEqual(this.cache)
    }

    override fun updateCacheFromSource(isFirstSync: Boolean): Boolean
    {
        if (isFirstSync || needsSync())
        {
            setValue(this.getter?.get(), setSource = false, sync = false)
            return true
        }
        return false
    }

    override fun notifyUpdate()
    {
        setValue(getter?.get(), setSource = false, sync = true)
    }

    override fun write(buffer: PacketBuffer?)
    {
        NetworkUtils.writeFluidStack(buffer, this.cache)
    }

    override fun read(buffer: PacketBuffer?)
    {
        setValue(NetworkUtils.readFluidStack(buffer), true, false)
    }

    override fun getValue(): FluidStack?
    {
        return if (this.cache == null) null else copyFluid(this.cache)
    }

    override fun getValueType(): Class<FluidStack>
    {
        return FluidStack::class.java
    }
}