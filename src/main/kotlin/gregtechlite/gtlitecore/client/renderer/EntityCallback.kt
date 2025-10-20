package gregtechlite.gtlitecore.client.renderer

import net.minecraft.entity.EntityLivingBase
import net.minecraft.world.World

fun interface EntityCallback
{

    fun onEntityStuffs(entity: EntityLivingBase?, world: World?)

}