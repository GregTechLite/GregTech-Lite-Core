package gregtechlite.gtlitecore.client.renderer

import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

interface TranscendentRenderBehavior : ItemRendererManager
{
    @SideOnly(Side.CLIENT)
    fun getRotationSpeed(): Float

    @SideOnly(Side.CLIENT)
    fun getRotationAxisX(): Float

    @SideOnly(Side.CLIENT)
    fun getRotationAxisY(): Float

    @SideOnly(Side.CLIENT)
    fun getRotationAxisZ(): Float

    @SideOnly(Side.CLIENT)
    fun getFloatingOffset(): Float

    @SideOnly(Side.CLIENT)
    fun getRotationAxis(): FloatArray = floatArrayOf(getRotationAxisX(), getRotationAxisY(), getRotationAxisZ())
}