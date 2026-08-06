package gregtechlite.gtlitecore.api.capability

import gregtech.api.capability.IEnergyContainer
import gregtech.api.capability.IOpticalComputationProvider

interface ModuleProvider
{
    val casingTier: Int

    val subEnergyContainer: IEnergyContainer?

    val computationProvider: IOpticalComputationProvider?

    fun isModule(receiver: ModuleReceiver): Boolean
}
