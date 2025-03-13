package magicbook.gtlitecore.client.renderer.texture.cube;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Cuboid6;
import codechicken.lib.vec.Matrix4;
import gregtech.api.gui.resources.ResourceHelper;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.cclop.LightMapOperation;
import gregtech.client.renderer.texture.Textures;
import gregtech.client.utils.BloomEffectUtil;
import gregtech.common.ConfigHolder;
import magicbook.gtlitecore.api.utils.GTLiteUtility;
import magicbook.gtlitecore.api.utils.GTLiteValues;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.ArrayUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GTLiteSimpleOverlayRenderer implements ICubeRenderer
{

    private final String path;
    @SideOnly(Side.CLIENT)
    private TextureAtlasSprite sprite;
    @SideOnly(Side.CLIENT)
    @Nullable
    private TextureAtlasSprite spriteEmissive;

    public GTLiteSimpleOverlayRenderer(String path)
    {
        this.path = path;
        Textures.CUBE_RENDERER_REGISTRY.put(path, this);
        Textures.iconRegisters.add(this);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(@NotNull TextureMap textureMap)
    {
        String modId = GTLiteValues.MODID;
        String path = this.path;
        String[] split = path.split(":");
        if (split.length == 2)
        {
            modId = split[0];
            path = split[1];
        }
        String pathEmissive = "blocks/" + path + "_emissive";
        this.sprite = textureMap.registerSprite(GTLiteUtility.getId(modId, "blocks/" + path));
        if (ResourceHelper.doResourcepacksHaveTexture(modId, pathEmissive, true))
            this.spriteEmissive = textureMap.registerSprite(GTLiteUtility.getId(modId, pathEmissive));
    }

    @SideOnly(Side.CLIENT)
    public void renderOrientedState(CCRenderState renderState,
                                    Matrix4 translation,
                                    IVertexOperation[] pipeline,
                                    Cuboid6 bounds,
                                    EnumFacing frontFacing,
                                    boolean isActive, boolean isWorkingEnabled)
    {
        Textures.renderFace(renderState, translation, pipeline, frontFacing, bounds, this.sprite, BlockRenderLayer.CUTOUT_MIPPED);
        if (this.spriteEmissive != null)
        {
            if (ConfigHolder.client.machinesEmissiveTextures)
            {
                IVertexOperation[] lightPipeline = ArrayUtils.add(pipeline, new LightMapOperation(240, 240));
                Textures.renderFace(renderState, translation, lightPipeline, frontFacing, bounds, this.spriteEmissive, BloomEffectUtil.getEffectiveBloomLayer());
            }
            else
            {
                Textures.renderFace(renderState, translation, pipeline, frontFacing, bounds, this.spriteEmissive, BlockRenderLayer.CUTOUT_MIPPED);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public TextureAtlasSprite getParticleSprite()
    {
        return this.sprite;
    }

}
