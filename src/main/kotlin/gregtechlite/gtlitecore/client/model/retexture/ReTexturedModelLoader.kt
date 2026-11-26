package gregtechlite.gtlitecore.client.model.retexture

import gregtechlite.gtlitecore.api.collection.openHashMapOf
import net.minecraft.block.state.IBlockState
import net.minecraft.client.resources.IResourceManager
import net.minecraft.util.ResourceLocation
import net.minecraftforge.client.model.ICustomModelLoader
import net.minecraftforge.client.model.IModel
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

@SideOnly(Side.CLIENT)
object ReTexturedModelLoader : ICustomModelLoader
{
    private val models = openHashMapOf<ResourceLocation, ReTexturedModel>()

    fun register(provider: ReTexturedModelProvider, state: IBlockState?, location: ResourceLocation)
    {
        val model = ReTexturedModel(provider.getModels(state))
        model.provider = provider
        model.variant = location
        models[location] = model
    }

    override fun accepts(modelLocation: ResourceLocation): Boolean = models.containsKey(modelLocation)

    override fun loadModel(modelLocation: ResourceLocation): IModel
        = models[modelLocation] ?: error("No retextured model registered for $modelLocation")

    override fun onResourceManagerReload(resourceManager: IResourceManager)
    {
        models.values.forEach(ReTexturedModel::reset)
    }
}
