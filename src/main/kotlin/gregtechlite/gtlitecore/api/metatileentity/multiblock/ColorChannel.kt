package gregtechlite.gtlitecore.api.metatileentity.multiblock

import gregtech.api.metatileentity.MetaTileEntity
import gregtechlite.gtlitecore.api.collection.int2ByteHashMapOf
import net.minecraft.item.EnumDyeColor
import org.jetbrains.annotations.Range

object ColorChannel
{
    const val NONE = -1
    const val COUNT = 16

    private val channelByColorValue = int2ByteHashMapOf()

    private val colorValueByChannel = IntArray(COUNT)

    init
    {
        for (dye in EnumDyeColor.entries)
        {
            channelByColorValue[dye.colorValue] = dye.dyeDamage.toByte()
            colorValueByChannel[dye.dyeDamage] = dye.colorValue
        }
    }

    @JvmStatic
    fun ofPaintingColor(paintingColor: Int): Int
    {
        if (paintingColor == NONE)
            return NONE
        val channel = channelByColorValue.getOrDefault(paintingColor, NONE.toByte()).toInt()
        return if (channel in 0 until COUNT) channel else NONE
    }

    @JvmStatic
    fun colorValueOf(channel: @Range(from = 0L, to = COUNT.toLong()) Int): Int
        = if (channel in 0 until COUNT) colorValueByChannel[channel] else NONE
}

val MetaTileEntity.colorChannel: Int
    get() = ColorChannel.ofPaintingColor(paintingColor)
