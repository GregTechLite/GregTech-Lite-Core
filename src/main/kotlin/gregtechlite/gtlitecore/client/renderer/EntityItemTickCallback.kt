package gregtechlite.gtlitecore.client.renderer

import net.minecraft.entity.item.EntityItem

fun interface EntityItemTickCallback
{

    fun onEntityTick(item: EntityItem?)

}