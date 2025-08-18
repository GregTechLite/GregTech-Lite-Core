package gregtechlite.gtlitecore.client.renderer;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public interface EntityCallback
{

    void onEntityStuffs(EntityLivingBase entity, World world);

}