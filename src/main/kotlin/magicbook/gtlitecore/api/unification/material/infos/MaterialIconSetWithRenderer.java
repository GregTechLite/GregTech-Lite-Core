package magicbook.gtlitecore.api.unification.material.infos;

import gregtech.api.unification.material.info.MaterialIconSet;
import magicbook.gtlitecore.client.renderer.IItemRenderer;
import magicbook.gtlitecore.client.renderer.IItemRendererManager;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MaterialIconSetWithRenderer extends MaterialIconSet implements IItemRenderer, IItemRendererManager
{

    private final IItemRendererManager rendererManager;

    public MaterialIconSetWithRenderer(@NotNull String name,
                                       IItemRendererManager rendererManager)
    {
        super(name);
        this.rendererManager = rendererManager;
    }

    public MaterialIconSetWithRenderer(@NotNull String name,
                                       @NotNull MaterialIconSet parentIconSet,
                                       IItemRendererManager rendererManager)
    {
        super(name, parentIconSet);
        this.rendererManager = rendererManager;
    }

    public MaterialIconSetWithRenderer(@NotNull String name,
                                       @Nullable MaterialIconSet parentIconSet,
                                       boolean isRootIconSet,
                                       IItemRendererManager rendererManager)
    {
        super(name, parentIconSet, isRootIconSet);
        this.rendererManager = rendererManager;
    }

    @Override
    public IItemRendererManager getRendererManager()
    {
        return this.rendererManager;
    }

    @Override
    public void onRendererRegistry(ResourceLocation location)
    {
        this.rendererManager.onRendererRegistry(location);
    }

}

