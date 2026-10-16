package gregtechlite.gtlitecore.mixins.hooks

import appeng.api.networking.IGrid
import appeng.helpers.IInterfaceHost
import gregtech.api.metatileentity.MetaTileEntityHolder
import gregtechlite.gtlitecore.api.collection.openArrayListOf
import gregtechlite.gtlitecore.common.metatileentity.part.PartMachineMECraftingPatternInputHatch

@Hook
object MachineHostHooks
{
    @JvmStatic
    fun collectHostByGrid(grid: IGrid?): List<IInterfaceHost>
    {
        val hosts = openArrayListOf<IInterfaceHost>()
        if (grid == null)
            return hosts

        for (node in grid.nodes)
        {
            if (!node.isActive) continue

            val machine = node.machine
            if (machine !is MetaTileEntityHolder) continue

            val mte = machine.metaTileEntity
            if (mte is PartMachineMECraftingPatternInputHatch)
                hosts.add(mte.patternInterfaceHost)
        }
        return hosts
    }

    @JvmStatic
    fun areHostsEqual(a: List<IInterfaceHost>?, b: List<IInterfaceHost>?): Boolean
    {
        if (a == b)
            return true
        if (a == null || b == null || a.size != b.size)
            return false

        for (i in 0 until a.size)
        {
            if (a[i] != b[i])
                return false
        }
        return true
    }
}