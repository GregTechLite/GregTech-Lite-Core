package gregtechlite.gtlitecore.api.capability

import gregtech.api.capability.IEnergyContainer
import gregtech.api.capability.IOpticalComputationProvider

interface ModuleProvider
{
    /**
     * Get the auxiliary tiered status tier.
     */
    val casingTier: Int

    /**
     * Get [IEnergyContainer] of all modules.
     */
    val subEnergyContainer: IEnergyContainer?

    /**
     * Get computation provider of all modules.
     */
    val computationProvider: IOpticalComputationProvider?

    /**
     * Check if mte which implement [ModuleReceiver] is a module.
     *
     * @param receiver The mte which implement [ModuleReceiver].
     */
    fun isModule(receiver: ModuleReceiver): Boolean
}
