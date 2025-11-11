package gregtechlite.gtlitecore.client.model

import net.minecraft.client.renderer.block.model.IBakedModel
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.util.registry.IRegistry

fun interface WrappedModelGetter
{

    /**
     * A callback from the `ModelLoadEvent` to grad the wrapped model.
     *
     * @param modelRegistry The model registry `(ModelResourceLocation, IBakedModel)`.
     * @return              The wrapped model.
     */
    fun getWrappedModel(modelRegistry: IRegistry<ModelResourceLocation, IBakedModel>): IBakedModel?

}