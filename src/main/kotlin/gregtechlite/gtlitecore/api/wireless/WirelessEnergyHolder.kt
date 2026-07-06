package gregtechlite.gtlitecore.api.wireless

import net.minecraft.util.math.BlockPos
import java.util.Objects

/**
 * Wireless role for the energy network.
 * Determines how energy flows through this holder.
 */
enum class WirelessRole {
    INPUT,    // Energy Hatch - receives energy from network to machine
    OUTPUT,   // Dynamo Hatch - sends energy from machine to network
    STORAGE   // Storage Hatch - bidirectional buffer pool
}

/**
 * Represents a wireless energy connection holder.
 * Each wireless hatch (energy, dynamo, or storage) has one of these in the wireless network.
 */
class WirelessEnergyHolder(
    val channel: Int,
    var buffer: Long,
    var capacity: Long,
    val role: WirelessRole,
    val pos: BlockPos,
    val priority: Int
) {

    fun addEnergy(amount: Long): Long {
        val actualAdded = minOf(amount, capacity - buffer)
        buffer += actualAdded
        return actualAdded
    }

    fun removeEnergy(amount: Long): Long {
        val actualRemoved = minOf(amount, buffer)
        buffer -= actualRemoved
        return actualRemoved
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is WirelessEnergyHolder) return false
        return channel == other.channel && pos == other.pos
    }

    override fun hashCode(): Int = Objects.hash(channel, pos)
}
