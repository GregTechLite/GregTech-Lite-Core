package gregtechlite.gtlitecore.client.renderer;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface UniversiumRenderBehavior extends ItemRendererManager
{

    float getCosmicOpacity();

    @SideOnly(Side.CLIENT)
    default float getCosmicOpacity(ItemStack stack, Object... args)
    {
        return getCosmicOpacity();
    }

}
