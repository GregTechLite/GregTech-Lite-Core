package gregtechlite.gtlitecore.integration.theoneprobe.provider

import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtechlite.gtlitecore.api.MOD_ID
import gregtechlite.gtlitecore.common.metatileentity.electric.MachineEnergyDistributor
import mcjty.theoneprobe.api.IProbeHitData
import mcjty.theoneprobe.api.IProbeInfo
import mcjty.theoneprobe.api.IProbeInfoProvider
import mcjty.theoneprobe.api.ProbeMode
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.world.World

class EnergyDistributorInfoProvider : IProbeInfoProvider {

    override fun addProbeInfo(mode: ProbeMode, info: IProbeInfo, player: EntityPlayer,
                              worldIn: World, state: IBlockState, data: IProbeHitData) {
        if (!state.block.hasTileEntity(state)) return

        val tile = worldIn.getTileEntity(data.pos) ?: return
        if (tile !is IGregTechTileEntity) return

        val mte = tile.metaTileEntity ?: return
        if (mte !is MachineEnergyDistributor) return

        val modeKey = if (mte.isDistributeMode)
            "gtlitecore.top.energy_distributor.mode_distribute"
        else
            "gtlitecore.top.energy_distributor.mode_combine"

        info.text("{*$modeKey*}")
    }

    override fun getID(): String = "${MOD_ID}:energy_distributor_provider"
}
