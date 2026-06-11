package gregtechlite.gtlitecore.client.renderer;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface TranscendentRenderBehavior extends ItemRendererManager
{

    float getRotationSpeed();

    float getRotationAxisX();

    float getRotationAxisY();

    float getRotationAxisZ();

    float getFloatingOffset();

    @SideOnly(Side.CLIENT)
    default float[] getRotationAxis()
    {
        return new float[] { getRotationAxisX(), getRotationAxisY(), getRotationAxisZ() };
    }

}