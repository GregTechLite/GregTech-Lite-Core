package gregtechlite.gtlitecore.integration.top.provider

import gregtech.api.metatileentity.MetaTileEntity
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockAbilityPart
import gregtech.api.metatileentity.multiblock.MultiblockAbility
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityMultiblockPart
import gregtechlite.gtlitecore.api.MOD_ID
import gregtechlite.gtlitecore.api.metatileentity.multiblock.ColorChannel
import gregtechlite.gtlitecore.api.metatileentity.multiblock.colorChannel
import mcjty.theoneprobe.api.IProbeHitData
import mcjty.theoneprobe.api.IProbeInfo
import mcjty.theoneprobe.api.IProbeInfoProvider
import mcjty.theoneprobe.api.ProbeMode
import mcjty.theoneprobe.api.TextStyleClass
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.EnumDyeColor
import net.minecraft.util.text.TextFormatting
import net.minecraft.world.World

class ColorChannelInfoProvider : IProbeInfoProvider
{
    companion object
    {
        private val CHAT_COLORS = arrayOf(
            TextFormatting.BLACK,        // 0  BLACK
            TextFormatting.DARK_RED,     // 1  RED
            TextFormatting.DARK_GREEN,   // 2  GREEN
            TextFormatting.GOLD,         // 3  BROWN
            TextFormatting.DARK_BLUE,    // 4  BLUE
            TextFormatting.DARK_PURPLE,  // 5  PURPLE
            TextFormatting.DARK_AQUA,    // 6  CYAN
            TextFormatting.GRAY,         // 7  SILVER
            TextFormatting.DARK_GRAY,    // 8  GRAY
            TextFormatting.LIGHT_PURPLE, // 9  PINK
            TextFormatting.GREEN,        // 10 LIME
            TextFormatting.YELLOW,       // 11 YELLOW
            TextFormatting.BLUE,         // 12 LIGHT_BLUE
            TextFormatting.AQUA,         // 13 MAGENTA
            TextFormatting.GOLD,         // 14 ORANGE
            TextFormatting.WHITE         // 15 WHITE
        )
    }

    override fun addProbeInfo(mode: ProbeMode, info: IProbeInfo, player: EntityPlayer, worldIn: World,
                              state: IBlockState, data: IProbeHitData)
    {
        if (!state.block.hasTileEntity(state)) return

        val tile = worldIn.getTileEntity(data.pos) ?: return
        if (tile !is IGregTechTileEntity) return

        val mte = tile.metaTileEntity ?: return
        when (mte)
        {
            is MetaTileEntityMultiblockPart  -> addPartChannel(info, mte)
            is RecipeMapMultiblockController -> addControllerChannels(info, mte)
        }
    }

    private fun addPartChannel(info: IProbeInfo, part: MetaTileEntityMultiblockPart)
    {
        if (!isImportPart(part)) return

        val channel = part.colorChannel
        if (channel == ColorChannel.NONE) return

        info.text(TextStyleClass.LABEL.toString() + loc("gtlitecore.top.color_channel.part") + " " + channelName(channel))
    }

    private fun addControllerChannels(info: IProbeInfo, controller: RecipeMapMultiblockController)
    {
        val channels = sortedSetOf<Int>()
        var shared = false

        for (part in controller.multiblockParts)
        {
            if (part !is MetaTileEntity) continue
            if (!isImportPart(part)) continue

            val channel = part.colorChannel
            if (channel == ColorChannel.NONE)
                shared = true
            else
                channels.add(channel)
        }

        if (channels.isEmpty()) return

        val entries = mutableListOf<String>()
        for (channel in channels)
            entries.add(channelName(channel))
        if (shared)
            entries.add(TextFormatting.GRAY.toString() + loc("gtlitecore.top.color_channel.shared"))

        info.text(TextStyleClass.LABEL.toString() + loc("gtlitecore.top.color_channel.controller") + " "
                + entries.joinToString(TextFormatting.GRAY.toString() + ", "))
    }

    private fun isImportPart(part: MetaTileEntity): Boolean
    {
        if (part !is IMultiblockAbilityPart<*>) return false
        return part.abilities.contains(MultiblockAbility.IMPORT_ITEMS)
                || part.abilities.contains(MultiblockAbility.IMPORT_FLUIDS)
    }

    private fun channelName(channel: Int): String = CHAT_COLORS[channel].toString() +
            loc("item.fireworksCharge." + EnumDyeColor.byDyeDamage(channel).translationKey)

    private fun loc(key: String) = IProbeInfo.STARTLOC + key + IProbeInfo.ENDLOC

    override fun getID(): String = "${MOD_ID}:color_channel_provider"
}
