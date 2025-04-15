package magicbook.gtlitecore.client.shader;

import magicbook.gtlitecore.client.renderer.texture.GTLiteTextures;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.ARBShaderObjects;

import java.nio.FloatBuffer;

public class CosmicShaderHelper
{

    public static final ShaderCallback shaderCallback;

    public static float[] lightlevel = new float[3];

    public static boolean inventoryRender = false;
    public static float cosmicOpacity = 1.0f;
    public static FloatBuffer cosmicUVs = BufferUtils.createFloatBuffer(4 * 10);

    static
    {
        shaderCallback = new ShaderCallback()
        {
            @Override
            public void call(int shader)
            {
                Minecraft mc = Minecraft.getMinecraft();

                float yaw = 0;
                float pitch = 0;
                float scale = 1.0f;

                if (!inventoryRender)
                {
                    yaw = (float) ((mc.player.rotationYaw * 2 * Math.PI) / 360.0);
                    pitch = -(float) ((mc.player.rotationPitch * 2 * Math.PI) / 360.0);
                }
                else
                {
                    scale = 25.0f;
                }

                int x = ARBShaderObjects.glGetUniformLocationARB(shader, "yaw");
                ARBShaderObjects.glUniform1fARB(x, yaw);

                int z = ARBShaderObjects.glGetUniformLocationARB(shader, "pitch");
                ARBShaderObjects.glUniform1fARB(z, pitch);

                int l = ARBShaderObjects.glGetUniformLocationARB(shader, "lightlevel");
                ARBShaderObjects.glUniform3fARB(l, lightlevel[0], lightlevel[1], lightlevel[2]);

                int lightmix = ARBShaderObjects.glGetUniformLocationARB(shader, "lightmix");
                ARBShaderObjects.glUniform1fARB(lightmix, 0.2f);

                int uvs = ARBShaderObjects.glGetUniformLocationARB(shader, "cosmicuvs");
                ARBShaderObjects.glUniformMatrix2ARB(uvs, false, cosmicUVs);

                int s = ARBShaderObjects.glGetUniformLocationARB(shader, "externalScale");
                ARBShaderObjects.glUniform1fARB(s, scale);

                int o = ARBShaderObjects.glGetUniformLocationARB(shader, "opacity");
                ARBShaderObjects.glUniform1fARB(o, cosmicOpacity);
            }
        };
    }

    @SubscribeEvent
    public void onRenderTick(TickEvent.RenderTickEvent event)
    {
        if (event.phase == TickEvent.Phase.START)
        {
            cosmicUVs = BufferUtils.createFloatBuffer(4 * GTLiteTextures.COSMIC.length);
            TextureAtlasSprite icon;
            for (TextureAtlasSprite cosmicIcon : GTLiteTextures.COSMIC)
            {
                icon = cosmicIcon;
                cosmicUVs.put(icon.getMinU());
                cosmicUVs.put(icon.getMinV());
                cosmicUVs.put(icon.getMaxU());
                cosmicUVs.put(icon.getMaxV());
            }
            cosmicUVs.flip();
        }
    }

    @SubscribeEvent
    public void onDrawScreenPre(GuiScreenEvent.DrawScreenEvent.Pre event)
    {
        // Initialized CosmicShaderHelper renderer in Item Inventory.
        inventoryRender = true;
    }

    @SubscribeEvent
    public void drawScreenPost(GuiScreenEvent.DrawScreenEvent.Post event)
    {
        // Disabled non-Item Inventory CosmicShaderHelper renderer.
        inventoryRender = false;
    }

    public static void useShader()
    {
        ShaderUtils.useShader(ShaderUtils.cosmicShader, shaderCallback);
    }

    public static void releaseShader()
    {
        ShaderUtils.releaseShader();
    }

    public static void setLightFromLocation(World world, BlockPos pos)
    {
        if (world == null)
        {
            setLightLevel(1.0f);
            return;
        }

        int coord = world.getCombinedLight(pos, 0);

        // entityRenderer.lightmapColors is private, but we used AT at there.
        int[] map = Minecraft.getMinecraft().entityRenderer.lightmapColors;
        if (map == null)
        {
            setLightLevel(1.0f);
            return;
        }

        int mx = (coord % 65536) / 16;
        int my = (coord / 65536) / 16;

        int lightcolour = map[my * 16 + mx];

        setLightLevel(((lightcolour >> 16) & 0xFF) / 256.0f, ((lightcolour >> 8) & 0xFF) / 256.0f, ((lightcolour) & 0xFF) / 256.0f);
    }

    public static void setLightLevel(float level)
    {
        setLightLevel(level, level, level);
    }

    public static void setLightLevel(float r, float g, float b)
    {
        lightlevel[0] = Math.max(0.0f, Math.min(1.0f, r));
        lightlevel[1] = Math.max(0.0f, Math.min(1.0f, g));
        lightlevel[2] = Math.max(0.0f, Math.min(1.0f, b));
    }

}