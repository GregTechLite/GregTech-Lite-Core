package magicbook.gtlitecore.integration.top.provider

import gregtech.api.metatileentity.MetaTileEntity
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import magicbook.gtlitecore.api.capability.IDelegator
import magicbook.gtlitecore.api.utils.GTLiteValues
import mcjty.theoneprobe.api.IProbeHitData
import mcjty.theoneprobe.api.IProbeInfo
import mcjty.theoneprobe.api.IProbeInfoProvider
import mcjty.theoneprobe.api.ProbeMode
import net.minecraft.block.state.IBlockState
import net.minecraft.client.resources.I18n
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.tileentity.TileEntity
import net.minecraft.world.World

@Suppress("MISSING_DEPENDENCY_CLASS")
class DelegatorInfoProvider : IProbeInfoProvider
{

    override fun addProbeInfo(mode: ProbeMode?, info: IProbeInfo?, player: EntityPlayer?,
                              worldIn: World?, state: IBlockState?, data: IProbeHitData?)
    {
        if (state!!.block.hasTileEntity(state))
        {
            val te: TileEntity? = worldIn?.getTileEntity(data!!.pos)
            if (te is IGregTechTileEntity)
            {
                val mte: MetaTileEntity = te.metaTileEntity
                if (mte is IDelegator)
                {
                    // Soft coded all branches which will faced to provide translation in I18n.
                    info!!.text(I18n.format("gtlitecore.machine.delegator.delegating_face.${mte.getDelegatingFacing(data!!.sideHit)}"))
                }
            }
        }
    }

    override fun getID() = "${GTLiteValues.MODID}:delegator_info_provider"

}