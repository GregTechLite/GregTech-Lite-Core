package magicbook.gtlitecore.api.capability

import net.minecraft.util.EnumFacing

interface IDelegator
{

    fun getDelegatingFacing(facing: EnumFacing?): EnumFacing?

}