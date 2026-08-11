package gregtechlite.gtlitecore.common.metatileentity

import gregtech.api.metatileentity.MetaTileEntity
import gregtechlite.gtlitecore.api.LOGGER
import gregtechlite.gtlitecore.api.network.sync.DescSynced
import gregtechlite.gtlitecore.api.network.sync.ManageableMachine
import gregtechlite.gtlitecore.api.network.sync.Persisted
import gregtechlite.gtlitecore.api.network.sync.UpdateListener
import net.minecraft.util.ResourceLocation

abstract class MetaTileEntityTestManagedBase(id: ResourceLocation) : MetaTileEntity(id), ManageableMachine
{
    @field:Persisted
    @field:DescSynced
    var inheritedInt: Int = 0

    @field:DescSynced
    @field:UpdateListener(name = "onInheritedModeChanged")
    var inheritedMode: Int = 0

    open fun onInheritedModeChanged(newVal: Int, oldVal: Int)
    {
        LOGGER.debug("Managed onInheritedModeChanged @ {}: {}->{}", pos, oldVal, newVal)
    }
}
