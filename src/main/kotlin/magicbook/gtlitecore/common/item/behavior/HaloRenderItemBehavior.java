package magicbook.gtlitecore.common.item.behavior;

import codechicken.lib.model.ModelRegistryHelper;
import codechicken.lib.util.TransformUtils;
import magicbook.gtlitecore.client.renderer.IHaloRenderBehavior;
import magicbook.gtlitecore.client.renderer.handler.HaloItemRenderer;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.function.Supplier;

public class HaloRenderItemBehavior implements IHaloRenderBehavior
{

    private final int haloSize;
    private final int haloColor;
    private final Supplier<?> supplier;
    private final boolean drawPulse;

    public HaloRenderItemBehavior(int haloSize, int haloColor,
                                  Supplier<?> supplier, boolean drawPulse)
    {
        this.haloSize = haloSize;
        this.haloColor = haloColor;
        this.supplier = supplier;
        this.drawPulse = drawPulse;
    }

    @Override
    public boolean shouldDrawHalo()
    {
        return true;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public TextureAtlasSprite getHaloTexture()
    {
        return (TextureAtlasSprite) this.supplier.get();
    }

    @Override
    public int getHaloColor()
    {
        return this.haloColor;
    }

    @Override
    public int getHaloSize()
    {
        return this.haloSize;
    }

    @Override
    public boolean shouldDrawPulse()
    {
        return this.drawPulse;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void onRendererRegistry(ResourceLocation location)
    {
        ModelRegistryHelper.register(new ModelResourceLocation(location, "inventory"),
                new HaloItemRenderer(TransformUtils.DEFAULT_ITEM, modelRegistry
                        -> modelRegistry.getObject(new ModelResourceLocation(location, "inventory"))));
    }

}