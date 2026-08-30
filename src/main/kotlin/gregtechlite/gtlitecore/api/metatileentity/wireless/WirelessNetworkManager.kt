package gregtechlite.gtlitecore.api.metatileentity.wireless

import gregtechlite.gtlitecore.api.LOGGER
import java.util.concurrent.ConcurrentHashMap

object WirelessNetworkManager
{
    private val networks = ConcurrentHashMap<Int, MutableList<WirelessEnergyHolder>>()

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

    private fun transferChannel(holders: List<WirelessEnergyHolder>)
    {
        if (holders.isEmpty()) return

        val outputs = holders.filter { it.role == WirelessRole.OUTPUT }.byPriority()
        val inputs = holders.filter { it.role == WirelessRole.INPUT }.byPriority()
        val storages = holders.filter { it.role == WirelessRole.STORAGE }.byPriority()

        if (outputs.isNotEmpty() && inputs.isNotEmpty()) // OUTPUT -> INPUT
        {
            distributeOutputsToInputs(outputs, inputs)
        }

        if (inputs.isNotEmpty() && storages.isNotEmpty()) // STORAGE -> INPUT
        {
            fillInputsFromStorages(inputs, storages)
        }

        if (outputs.isNotEmpty() && storages.isNotEmpty()) // OUTPUT -> STORAGE
        {
            storeRemainingOutputs(outputs, storages)
        }
    }

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

    private fun List<WirelessEnergyHolder>.byPriority(): List<WirelessEnergyHolder> = sortedByDescending { it.priority }
}
