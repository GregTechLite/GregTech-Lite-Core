package magicbook.gtlitecore.api.block

import net.minecraft.util.IStringSerializable

interface IBlockTier : IStringSerializable
{

    fun getInfo(): Any? = null

    fun getTier(): Any? = 0

}