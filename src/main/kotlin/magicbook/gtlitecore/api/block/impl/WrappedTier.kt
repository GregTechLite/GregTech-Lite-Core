package magicbook.gtlitecore.api.block.impl

import magicbook.gtlitecore.api.block.IBlockTier
import net.minecraft.util.IStringSerializable

open class WrappedTier(private val inner: IStringSerializable) : IBlockTier
{

    override fun getName(): String = this.inner.name

}