package magicbook.gtlitecore.core.sound;

import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import magicbook.gtlitecore.api.GTLiteAPI;
import magicbook.gtlitecore.api.sound.ISoundManager;
import magicbook.gtlitecore.api.utils.GTLiteValues;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public class SoundManager implements ISoundManager
{

    private static final SoundManager INSTANCE = new SoundManager();

    /**
     * Warning: This map cannot be marked with {@code SideOnly(Side.CLIENT)} because the server
     * side will report it as a missing field when {@code INSTANCE} param is instantiated on the
     * server side.
     */
    private final Object2ObjectMap<BlockPos, ISound> sounds = new Object2ObjectOpenHashMap<>();

    private SoundManager() {}

    public static SoundManager getInstance()
    {
        return INSTANCE;
    }

    @Override
    public SoundEvent registerSound(String containerName, String soundName)
    {
        ResourceLocation location = new ResourceLocation(containerName, soundName);
        SoundEvent event = new SoundEvent(location);
        event.setRegistryName(location);
        ForgeRegistries.SOUND_EVENTS.register(event);
        return event;
    }

    @Override
    public SoundEvent registerSound(String soundName)
    {
        String containerID = GTLiteAPI.moduleManager.getLoadedContainer().getId();
        if (containerID == null)
            containerID = GTLiteValues.MODID;
        return this.registerSound(containerID, soundName);
    }

    @Override
    public ISound startTileSound(ResourceLocation soundName, float volume, BlockPos pos)
    {
        ISound sound = sounds.get(pos);
        if (sound == null || !Minecraft.getMinecraft().getSoundHandler().isSoundPlaying(sound))
        {
            sound = new PositionedSoundRecord(soundName, SoundCategory.BLOCKS, volume, 1.0F,
                    true, 0, ISound.AttenuationType.LINEAR,
                    pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F);
            sounds.put(pos, sound);
            Minecraft.getMinecraft().getSoundHandler().playSound(sound);
        }
        return sound;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void stopTileSound(BlockPos pos)
    {
        ISound sound = sounds.get(pos);
        if (sound != null)
        {
            Minecraft.getMinecraft().getSoundHandler().stopSound(sound);
            sounds.remove(pos);
        }
    }

}
