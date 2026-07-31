package gregtechlite.gtlitecore.common.item.behavior

import codechicken.lib.model.ModelRegistryHelper
import codechicken.lib.util.TransformUtils
import gregtechlite.gtlitecore.client.renderer.TranscendentRenderBehavior
import gregtechlite.gtlitecore.client.renderer.handler.item.TranscendentItemRenderer
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class TranscendentRenderItemBehavior(private val rotationSpeed: Float = 3.5f,
                                     private val rotationAxisX: Float = 0.3f,
                                     private val rotationAxisY: Float = 0.5f,
                                     private val rotationAxisZ: Float = 0.2f,
                                     private val floatingOffset: Float = 0.03125f) : TranscendentRenderBehavior
{
    override fun getRotationSpeed(): Float = rotationSpeed

    override fun getRotationAxisX(): Float = rotationAxisX

    override fun getRotationAxisY(): Float = rotationAxisY

    override fun getRotationAxisZ(): Float = rotationAxisZ

    override fun getFloatingOffset(): Float = floatingOffset

    @SideOnly(Side.CLIENT)
    override fun onRendererRegistry(location: ResourceLocation)
    {
        ModelRegistryHelper.register(ModelResourceLocation(location, "inventory"),
            TranscendentItemRenderer(TransformUtils.DEFAULT_ITEM) {
                it.getObject(ModelResourceLocation(location, "inventory"))
            })
    }
}