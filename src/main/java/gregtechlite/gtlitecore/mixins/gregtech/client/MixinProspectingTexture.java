package gregtechlite.gtlitecore.mixins.gregtech.client;

import gregtech.common.gui.widget.prospector.ProspectorMode;
import gregtech.common.gui.widget.prospector.ProspectingTexture;
import gregtech.core.network.packets.PacketProspecting;
import it.unimi.dsi.fastutil.bytes.Byte2ObjectOpenHashMap;
import net.minecraft.client.resources.IResourceManager;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(value = ProspectingTexture.class, remap = false)
public abstract class MixinProspectingTexture
{
    @Shadow
    @Final
    public Map<Byte, String>[][] map;

    @Shadow
    @Final
    private int radius;

    @Shadow
    private int playerXGui;

    @Shadow
    private int playerYGui;

    @Shadow
    @Final
    private ProspectorMode mode;

    /**
     * @author Magic_Sweepy
     * @reason Store {@link Byte2ObjectOpenHashMap} copies instead of the packet's {@code HashMap} cells.
     */
    @Inject(method = "updateTexture",
            at = @At("HEAD"),
            cancellable = true)
    private void gtlitecore$updateTexture(PacketProspecting packet, CallbackInfo callbackInfo)
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
            callbackInfo.cancel();
            return;
        }

        if (mode == ProspectorMode.FLUID)
        {
            map[currentColumn][currentRow] = gtlitecore$copyCell(packet.map[0][0]);
        }
        else
        {
            for (int x = 0; x < 16; x++)
            {
                for (int z = 0; z < 16; z++)
                {
                    map[x + currentColumn * 16][z + currentRow * 16] = gtlitecore$copyCell(packet.map[x][z]);
                }
            }
        }
        loadTexture(null);
        callbackInfo.cancel();
    }

    @Unique
    private static Map<Byte, String> gtlitecore$copyCell(@Nullable Map<Byte, String> cell)
    {
        return new Byte2ObjectOpenHashMap<>(cell == null ? ProspectingTexture.emptyTag : cell);
    }

    @Shadow
    public abstract void loadTexture(@Nullable IResourceManager resourceManager);
}
