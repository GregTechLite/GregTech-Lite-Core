package gregtechlite.gtlitecore.api.metatileentity.wireless

import net.minecraft.util.math.BlockPos
import java.util.*

enum class WirelessRole
{
    INPUT,    // Receives energy from network to machine.
    OUTPUT,   // Sends energy from machine to network.
    STORAGE   // Bidirectional buffer pool.
}

class WirelessEnergyHolder(val channel: Int,
                           var buffer: Long,
                           var capacity: Long,
                           val role: WirelessRole,
                           val pos: BlockPos,
                           val priority: Int)
{
    fun addEnergy(amount: Long): Long
    {
        val actualAdded = minOf(amount, capacity - buffer)
        buffer += actualAdded
        return actualAdded
    }

    fun removeEnergy(amount: Long): Long
    {
        val actualRemoved = minOf(amount, buffer)
        buffer -= actualRemoved
        return actualRemoved
    }

    override fun equals(other: Any?): Boolean
    {
        if (this === other) return true
        if (other !is WirelessEnergyHolder) return false
        return channel == other.channel && pos == other.pos
    }

    override fun hashCode(): Int = Objects.hash(channel, pos)
}
