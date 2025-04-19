package magicbook.gtlitecore.api.sound;

import net.minecraft.client.audio.ISound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface ISoundManager
{

    /**
     * Register a {@code SoundEvent}.
     * <p>
     * Default to using the current active {@code ModuleContainer} id, must be registered in
     * the Pre-Initialization Event phase.
     *
     * @param soundName The name of the sound in the resources' directory.
     * @return          The created {@code SoundEvent}.
     */
    SoundEvent registerSound(String soundName);

    /**
     * Register a {@code SoundEvent} with custom {@code containerName}.
     * <p>
     * Custom {@code containerName} of {@code soundName} but not {@code ModuleContainer}.
     *
     * @param containerName The name of mod or module.
     * @param soundName     The name of the sound in the resources' directory.
     * @return              The created {@code SoundEvent}.
     */
    SoundEvent registerSound(String containerName, String soundName);

    /**
     * Starts a positioned sound at a provided {@code BlockPos}.
     *
     * @param soundName The name of the sound to play.
     * @param volume    The volume multiplier of the sound.
     * @param pos       The position to play the sound at.
     * @return          The sound that was played.
     */
    @SideOnly(Side.CLIENT)
    ISound startTileSound(ResourceLocation soundName, float volume, BlockPos pos);

    /**
     * Stops the positioned sound playing at a given {@code BlockPos} (if any).
     *
     * @param pos The position to play the sound at.
     */
    @SideOnly(Side.CLIENT)
    void stopTileSound(BlockPos pos);

}
