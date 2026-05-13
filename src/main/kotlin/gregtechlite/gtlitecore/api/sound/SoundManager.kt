package gregtechlite.gtlitecore.api.sound

import net.minecraft.client.audio.ISound
import net.minecraft.util.ResourceLocation
import net.minecraft.util.SoundEvent
import net.minecraft.util.math.BlockPos
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

interface SoundManager
{

    /**
     * Register a [SoundEvent].

     * Default to using the current active module container id, must be registered in
     * the Pre-Initialization Event phase.
     *
     * @param soundName The name of the sound in the resources' directory.
     * @return          The created [SoundEvent].
     */
    fun registerSound(soundName: String): SoundEvent

    /**
     * Register a [SoundEvent] with custom [containerName].
     *
     * Custom [containerName] of the [soundName] but not module container.
     *
     * @param containerName The name of mod or module.
     * @param soundName     The name of the sound in the resources' directory.
     * @return              The created [SoundEvent].
     */
    fun registerSound(containerName: String, soundName: String): SoundEvent

    /**
     * Starts a positioned sound at a provided [BlockPos].
     *
     * @param soundName The name of the sound to play.
     * @param volume    The volume multiplier of the sound.
     * @param pos       The position to play the sound at.
     * @return          The sound that was played.
     */
    @SideOnly(Side.CLIENT)
    fun startTileSound(soundName: ResourceLocation, volume: Float, pos: BlockPos): ISound

    /**
     * Stops the positioned sound playing at a given [BlockPos] (if any).
     *
     * @param pos The position to play the sound at.
     */
    @SideOnly(Side.CLIENT)
    fun stopTileSound(pos: BlockPos)
}