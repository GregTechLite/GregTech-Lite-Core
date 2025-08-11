package gregtechlite.gtlitecore.mixins.minecraft.client;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(value = ModelLoader.class, remap = false)
public interface AccessorModelLoader
{

    @Accessor
    Map<ModelResourceLocation, IModel> getStateModels();

}
