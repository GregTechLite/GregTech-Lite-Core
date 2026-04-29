package gregtechlite.gtlitecore.mixins.gregtech.client;

import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.stack.MaterialStack;
import gregtech.client.utils.RenderUtil;
import gregtech.common.gui.widget.prospector.ProspectorMode;
import gregtech.common.gui.widget.prospector.ProspectingTexture;
import gregtech.core.network.packets.PacketProspecting;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.resources.IResourceManager;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.HashMap;
import java.util.Map;

@Deprecated
@Mixin(value = ProspectingTexture.class, remap = false)
public abstract class MixinProspectingTexture extends AbstractTexture
{

    @Shadow
    private @Final int radius;

    @Shadow
    private int playerXGui;
    @Shadow
    private int playerYGui;

    @Shadow
    private int imageWidth;
    @Shadow
    private int imageHeight;

    @Shadow
    private @Final ProspectorMode mode;
    @Shadow
    private boolean darkMode;

    @Shadow
    private String selected;

    @Shadow
    public static @Final String SELECTED_ALL;

    @Unique
    private Object[][] gtlitecore$map;
    @Unique
    private static Map<Byte, String> gtlitecore$emptyTag;

    @Inject(method = "<init>",
            at = @At("TAIL"))
    private void onConstructor(ProspectorMode mode, int radius, boolean darkMode, CallbackInfo ci)
    {
        gtlitecore$emptyTag = new HashMap<>();
        if (mode == ProspectorMode.FLUID)
        {
            gtlitecore$map = new HashMap[(radius * 2 - 1)][(radius * 2 - 1)];
        }
        else
        {
            gtlitecore$map = new HashMap[(radius * 2 - 1) * 16][(radius * 2 - 1) * 16];
        }
    }

    @Inject(method = "updateTexture",
            at = @At("HEAD"),
            cancellable = true)
    private void onUpdateTexture(PacketProspecting packet, CallbackInfo ci)
    {
        int playerChunkX = packet.playerChunkX;
        int playerChunkZ = packet.playerChunkZ;
        playerXGui = packet.posX - (playerChunkX - this.radius + 1) * 16 + (packet.posX > 0 ? 1 : 0);
        playerYGui = packet.posZ - (playerChunkZ - this.radius + 1) * 16 + (packet.posX > 0 ? 1 : 0);

        int ox;
        if ((packet.chunkX > 0 && playerChunkX > 0) || (packet.chunkX < 0 && playerChunkX < 0))
        {
            ox = Math.abs(Math.abs(packet.chunkX) - Math.abs(playerChunkX));
        }
        else
        {
            ox = Math.abs(playerChunkX) + Math.abs(packet.chunkX);
        }
        if (playerChunkX > packet.chunkX)
        {
            ox = -ox;
        }

        int oy;
        if ((packet.chunkZ > 0 && playerChunkZ > 0) || (packet.chunkZ < 0 && playerChunkZ < 0))
        {
            oy = Math.abs(Math.abs(packet.chunkZ) - Math.abs(playerChunkZ));
        }
        else
        {
            oy = Math.abs(playerChunkZ) + Math.abs(packet.chunkZ);
        }
        if (playerChunkZ > packet.chunkZ)
        {
            oy = -oy;
        }

        int currentColumn = (this.radius - 1) + ox;
        int currentRow = (this.radius - 1) + oy;
        if (currentRow < 0)
        {
            ci.cancel();
            return;
        }

        if (mode == ProspectorMode.FLUID)
        {
            // region patch
            gtlitecore$map[currentColumn][currentRow] = packet.map[0][0] == null
                    ? gtlitecore$emptyTag : packet.map[0][0];
            // endregion
        }
        else
        {
            for (int x = 0; x < 16; x++)
            {
                for (int z = 0; z < 16; z++)
                {
                    // region patch
                    gtlitecore$map[x + currentColumn * 16][z + currentRow * 16] = packet.map[x][z] == null
                            ? gtlitecore$emptyTag : packet.map[x][z];
                    // endregion
                }
            }
        }
        loadTexture(null);
        ci.cancel();
    }

    @Inject(method = "getImage",
            at = @At("HEAD"),
            cancellable = true)
    private void onGetImage(CallbackInfoReturnable<BufferedImage> cir)
    {
        int wh = (radius * 2 - 1) * 16;
        BufferedImage image = new BufferedImage(wh, wh, BufferedImage.TYPE_INT_ARGB);
        WritableRaster raster = image.getRaster();

        for (int i = 0; i < wh; i++)
        {
            for (int j = 0; j < wh; j++)
            {
                // region patch
                // noinspection unchecked
                Map<Byte, String> data = (Map<Byte, String>) gtlitecore$map[mode == ProspectorMode.ORE ? i : i / 16][
                        mode == ProspectorMode.ORE ? j : j / 16];
                // endregion

                // draw bg
                image.setRGB(i, j, ((data == null) ^ darkMode) ? Color.darkGray.getRGB() : Color.WHITE.getRGB());

                // draw ore
                if (mode == ProspectorMode.ORE && data != null)
                {
                    for (String orePrefix : data.values())
                    {
                        if (!selected.equals(SELECTED_ALL) && !selected.equals(orePrefix)) continue;
                        MaterialStack ms = OreDictUnifier.getMaterial(OreDictUnifier.get(orePrefix));
                        image.setRGB(i, j, ms == null ? orePrefix.hashCode() :
                                ms.material.getMaterialRGB() | 0XFF000000);
                        break;
                    }
                }

                // draw grid
                if ((i) % 16 == 0 || (j) % 16 == 0)
                {
                    raster.setSample(i, j, 0, raster.getSample(i, j, 0) / 2);
                    raster.setSample(i, j, 1, raster.getSample(i, j, 1) / 2);
                    raster.setSample(i, j, 2, raster.getSample(i, j, 2) / 2);
                }
            }
        }
        cir.setReturnValue(image);
    }

    @Inject(method = "draw",
            at = @At("HEAD"),
            cancellable = true)
    private void onDraw(int x, int y, CallbackInfo ci)
    {
        if (glTextureId < 0)
        {
            ci.cancel();
            return;
        }

        GlStateManager.bindTexture(glTextureId);
        Gui.drawModalRectWithCustomSizedTexture(x, y, 0, 0, imageWidth, imageHeight, imageWidth, imageHeight);
        if (mode == ProspectorMode.FLUID)
        {
            for (int cx = 0; cx < radius * 2 - 1; cx++)
            {
                for (int cz = 0; cz < radius * 2 - 1; cz++)
                {
                    // region patch
                    // noinspection unchecked
                    Map<Byte, String> data = (Map<Byte, String>) gtlitecore$map[cx][cz];
                    // endregion
                    if (data != null && !data.isEmpty())
                    {
                        Fluid fluid = FluidRegistry.getFluid(data.get((byte) 1));
                        if (selected.equals(SELECTED_ALL) || selected.equals(fluid.getName()))
                        {
                            RenderUtil.drawFluidForGui(new FluidStack(fluid, 1), 1,
                                    x + cx * 16 + 1, y + cz * 16 + 1, 16, 16);
                        }
                    }
                }
            }
        }

        // draw red vertical line
        if (playerXGui % 16 > 7 || playerXGui % 16 == 0)
        {
            Gui.drawRect(x + playerXGui - 1, y, x + playerXGui, y + imageHeight, Color.RED.getRGB());
        }
        else
        {
            Gui.drawRect(x + playerXGui, y, x + playerXGui + 1, y + imageHeight, Color.RED.getRGB());
        }

        // draw red horizontal line
        if (playerYGui % 16 > 7 || playerYGui % 16 == 0)
        {
            Gui.drawRect(x, y + playerYGui - 1, x + imageWidth, y + playerYGui, Color.RED.getRGB());
        }
        else
        {
            Gui.drawRect(x, y + playerYGui, x + imageWidth, y + playerYGui + 1, Color.RED.getRGB());
        }
        ci.cancel();
    }

    @Shadow
    public abstract void loadTexture(@Nullable IResourceManager resourceManager);

}
