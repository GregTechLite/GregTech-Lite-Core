package gregtechlite.gtlitecore.core.sound;

import com.morphismmc.morphismlib.client.Games;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import gregtechlite.gtlitecore.api.GTLiteAPI;
import gregtechlite.gtlitecore.api.sound.SoundManager;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.ApiStatus.Internal;

import static gregtechlite.gtlitecore.api.GTLiteValues.MOD_ID;

@Internal
public class SoundManagerImpl implements SoundManager
{

    private static final SoundManagerImpl INSTANCE = new SoundManagerImpl();

    /**
     * Warning: This map cannot be marked with {@code SideOnly(Side.CLIENT)} because the server
     * side will report it as a missing field when {@code INSTANCE} param is instantiated on the
     * server side.
     */
    private final Object2ObjectMap<BlockPos, ISound> sounds = new Object2ObjectOpenHashMap<>();

    private SoundManagerImpl() {}

    public static SoundManagerImpl getInstance()
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
        String containerId = GTLiteAPI.moduleManager.getLoadedContainer().getId();
        if (containerId == null)
        {
            containerId = MOD_ID;
        }
        return registerSound(containerId, soundName);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ISound startTileSound(ResourceLocation soundName, float volume, BlockPos pos)
    {
        ISound sound = sounds.get(pos);
        if (sound == null || !Games.mc().getSoundHandler().isSoundPlaying(sound))
        {
            sound = new PositionedSoundRecord(soundName, SoundCategory.BLOCKS, volume, 1.0F,
                    true, 0, ISound.AttenuationType.LINEAR,
                    pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F);
            sounds.put(pos, sound);
            Games.mc().getSoundHandler().playSound(sound);
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
            Games.mc().getSoundHandler().stopSound(sound);
            sounds.remove(pos);
        }
    }

}
