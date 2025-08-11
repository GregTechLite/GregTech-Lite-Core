package gregtechlite.gtlitecore.api.capability

import net.minecraft.util.EnumFacing

interface Delegator
{

    fun getDelegatingFacing(facing: EnumFacing?): EnumFacing?

}