package gregtechlite.gtlitecore.api.wireless

import gregtech.api.capability.IEnergyContainer
import gregtech.api.metatileentity.multiblock.MultiblockAbility

object WirelessAbilities {

    @JvmField
    val WIRELESS_ENERGY_STORAGE = MultiblockAbility(
        "wireless_energy_storage", IEnergyContainer::class.java
    )

    init {
        MultiblockAbility.REGISTRY[WIRELESS_ENERGY_STORAGE] = ArrayList()
    }
}
