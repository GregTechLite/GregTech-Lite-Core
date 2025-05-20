package magicbook.gtlitecore.api.capability;

import gregtech.api.capability.IEnergyContainer;
import gregtech.api.capability.IOpticalComputationProvider;

public interface IModuleProvider
{

    /**
     * Get the auxiliary tiered status tier.
     */
    int getCasingTier();

    /**
     * Get {@link IEnergyContainer} of all modules.
     */
    IEnergyContainer getSubEnergyContainer();

    /**
     * Get computation provider of all modules.
     */
    IOpticalComputationProvider getComputationProvider();

    /**
     * Check if mte which implement {@link IModuleReceiver} is a module.
     *
     * @param receiver The mte which implement {@link IModuleReceiver}.
     */
    boolean isModule(IModuleReceiver receiver);

}
