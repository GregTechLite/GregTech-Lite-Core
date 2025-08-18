package gregtechlite.gtlitecore.client.model;

import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.util.registry.IRegistry;

public interface WrappedModelGetter
{

    /**
     * A callback from the {@code ModelLoadEvent} to grad the wrapped model.
     *
     * @param modelRegistry Model registry (ModelResourceLocation, IBakedModel).
     * @return              The wrapped model.
     */
    IBakedModel getWrappedModel(IRegistry<ModelResourceLocation, IBakedModel> modelRegistry);

}
