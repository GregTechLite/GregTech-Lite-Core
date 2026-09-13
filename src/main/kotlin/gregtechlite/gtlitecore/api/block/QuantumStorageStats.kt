package gregtechlite.gtlitecore.api.block

import java.math.BigInteger

interface QuantumStorageStats
{
    val distinctSlots: Int

    val totalCapacity: BigInteger
}