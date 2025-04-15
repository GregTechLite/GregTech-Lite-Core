package magicbook.gtlitecore.common.item.behavior;

import codechicken.lib.model.ModelRegistryHelper;
import codechicken.lib.util.TransformUtils;
import magicbook.gtlitecore.client.renderer.ICosmicRenderBehavior;
import magicbook.gtlitecore.client.renderer.handler.CosmicItemRenderer;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class CosmicRenderItemBehavior implements ICosmicRenderBehavior
{

    private final Supplier<?> supplier;
    private final int maskOpacity;

    public CosmicRenderItemBehavior(Supplier<TextureAtlasSprite> supplier,
                                    int maskOpacity)
    {
        this.supplier = supplier;
        this.maskOpacity = maskOpacity;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public TextureAtlasSprite getMaskTexture(ItemStack stack,
                                             @Nullable EntityLivingBase player)
    {
        return (TextureAtlasSprite) this.supplier.get();
    }

    @Override
    public float getMaskOpacity(ItemStack stack,
                                @Nullable EntityLivingBase player)
    {
        return this.maskOpacity;
    }

    @Override
    public void onRendererRegistry(ResourceLocation location)
    {
        ModelRegistryHelper.register(new ModelResourceLocation(location, "inventory"),
                new CosmicItemRenderer(TransformUtils.DEFAULT_ITEM, modelRegistry
                        -> modelRegistry.getObject(new ModelResourceLocation(location, "inventory"))));
    }

}