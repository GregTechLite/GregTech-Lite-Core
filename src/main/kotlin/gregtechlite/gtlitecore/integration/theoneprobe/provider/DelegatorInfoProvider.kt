package gregtechlite.gtlitecore.integration.theoneprobe.provider

import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtechlite.gtlitecore.api.MOD_ID
import gregtechlite.gtlitecore.api.capability.Delegator
import mcjty.theoneprobe.api.IProbeHitData
import mcjty.theoneprobe.api.IProbeInfo
import mcjty.theoneprobe.api.IProbeInfoProvider
import mcjty.theoneprobe.api.ProbeMode
import net.minecraft.block.state.IBlockState
import net.minecraft.client.resources.I18n
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.world.World

class DelegatorInfoProvider : IProbeInfoProvider
{

    override fun addProbeInfo(mode: ProbeMode, info: IProbeInfo, player: EntityPlayer,
                              worldIn: World, state: IBlockState, data: IProbeHitData)
    {
        if (state.block.hasTileEntity(state))
        {
            val te = worldIn.getTileEntity(data.pos)
            if (te is IGregTechTileEntity)
            {
                val mte = te.metaTileEntity
                if (mte is Delegator)
                {
                    // Soft coded all branches which will faced to provide translation in I18n.
                    info.text(I18n.format("gtlitecore.machine.delegator.delegating_face.${mte.getDelegatingFacing(data.sideHit)}"))
                }
            }
        }
    }

    override fun getID() = "${MOD_ID}:delegator_info_provider"

}