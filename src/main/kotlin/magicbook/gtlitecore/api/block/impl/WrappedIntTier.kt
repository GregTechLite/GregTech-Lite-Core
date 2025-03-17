package magicbook.gtlitecore.api.block.impl

import net.minecraft.util.IStringSerializable

class WrappedIntTier(inner: IStringSerializable, private val tier: Int) : WrappedTier(inner)
{

    override fun getTier(): Int = this.tier

    fun getIntTier(): Int = this.tier

}