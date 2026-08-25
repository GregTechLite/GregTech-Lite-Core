package gregtechlite.gtlitecore.api.capability

import gregtech.api.capability.IRotorHolder

interface RotorHandler
{
    val mode: RotorMode

    val rotorHolders: List<IRotorHolder>?
}