package gregtechlite.gtlitecore.api.wireless

import gregtechlite.gtlitecore.api.LOGGER
import java.util.concurrent.ConcurrentHashMap

/**
 * Manager for all wireless energy networks.
 *
 * Handles registration, unregistration, and energy transfer between channels.
 * Energy transfer occurs every 100t (5s at 20 tps).
 */
object WirelessNetworkManager
{
    private val networks: MutableMap<Int, MutableList<WirelessEnergyHolder>> = ConcurrentHashMap()

    fun register(holder: WirelessEnergyHolder)
    {
        networks.computeIfAbsent(holder.channel) { mutableListOf() }.add(holder)
        LOGGER.info("Wireless holder registered: channel=${holder.channel}, pos=${holder.pos}, role=${holder.role}")
    }

    fun unregister(holder: WirelessEnergyHolder)
    {
        networks[holder.channel]?.remove(holder)
        if (networks[holder.channel]?.isEmpty() == true)
        {
            networks.remove(holder.channel)
        }
        LOGGER.info("Wireless holder unregistered: channel=${holder.channel}, pos=${holder.pos}")
    }

    fun getConnectionCount(channel: Int): Int = networks[channel]?.size ?: 0

    fun update()
    {
        networks.values.forEach { transferChannel(it.toList()) }
    }

    // Sort by priority descending (higher priority = processed first).
    private fun List<WirelessEnergyHolder>.byPriority(): List<WirelessEnergyHolder> = sortedByDescending { it.priority }

    private fun transferChannel(holders: List<WirelessEnergyHolder>)
    {
        if (holders.isEmpty()) return

        val outputs = holders.filter { it.role == WirelessRole.OUTPUT }.byPriority()
        val inputs = holders.filter { it.role == WirelessRole.INPUT }.byPriority()
        val storages = holders.filter { it.role == WirelessRole.STORAGE }.byPriority()

        // Step 1: OUTPUT -> INPUT (drain outputs to fill inputs)
        if (outputs.isNotEmpty() && inputs.isNotEmpty())
        {
            distributeOutputsToInputs(outputs, inputs)
        }

        // Step 2: STORAGE -> INPUT (fill remaining input needs from storage)
        if (inputs.isNotEmpty() && storages.isNotEmpty())
        {
            fillInputsFromStorages(inputs, storages)
        }

        // Step 3: OUTPUT -> STORAGE (store any remaining output energy)
        if (outputs.isNotEmpty() && storages.isNotEmpty())
        {
            storeRemainingOutputs(outputs, storages)
        }
    }

    /**
     * Step 1: Drain outputs in priority order to fill inputs in priority order.
     *
     * Each output is drained exhaustively before moving to the next.
     */
    private fun distributeOutputsToInputs(outputs: List<WirelessEnergyHolder>, inputs: List<WirelessEnergyHolder>)
    {
        for (input in inputs)
        {
            val canAccept = input.capacity - input.buffer
            if (canAccept <= 0) continue

            var remaining = canAccept
            for (output in outputs)
            {
                if (remaining <= 0) break
                if (output.buffer <= 0) continue

                val taken = minOf(output.buffer, remaining)
                output.buffer -= taken
                input.buffer += taken
                remaining -= taken
            }
        }
    }

    /**
     * Step 2: Fill inputs from storages in priority order.
     *
     * Higher-priority inputs are filled first, from higher-priority storages.
     */
    private fun fillInputsFromStorages(inputs: List<WirelessEnergyHolder>, storages: List<WirelessEnergyHolder>)
    {
        for (input in inputs)
        {
            var canAccept = input.capacity - input.buffer
            if (canAccept <= 0) continue

            for (storage in storages)
            {
                if (canAccept <= 0) break
                if (storage.buffer <= 0) continue

                val taken = minOf(storage.buffer, canAccept)
                storage.buffer -= taken
                input.buffer += taken
                canAccept -= taken
            }
        }
    }

    /**
     * Step 3: Store remaining output energy to storages in priority order.
     *
     * Higher-priority outputs are drained first, higher-priority storages filled first.
     */
    private fun storeRemainingOutputs(outputs: List<WirelessEnergyHolder>, storages: List<WirelessEnergyHolder>)
    {
        for (output in outputs)
        {
            if (output.buffer <= 0) continue

            for (storage in storages)
            {
                if (output.buffer <= 0) break
                val canAccept = storage.capacity - storage.buffer
                if (canAccept <= 0) continue

                val taken = minOf(output.buffer, canAccept)
                output.buffer -= taken
                storage.buffer += taken
            }
        }
    }
}
