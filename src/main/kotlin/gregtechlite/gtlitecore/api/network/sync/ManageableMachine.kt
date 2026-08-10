package gregtechlite.gtlitecore.api.network.sync

import gregtech.api.metatileentity.MetaTileEntity

interface ManageableMachine
{
    val managedHolder: ManagedFieldHolder
        get() = ManagedFieldHolder.get(this.javaClass)

    val managedCache: ManagedFieldCache
        get() = ManagedFields.getStorage(this as MetaTileEntity)
}