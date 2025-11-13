package gregtechlite.gtlitecore.api.item

import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

interface DescriptiveStats
{

    @SideOnly(Side.CLIENT)
    fun getDescription(): Array<String>

}