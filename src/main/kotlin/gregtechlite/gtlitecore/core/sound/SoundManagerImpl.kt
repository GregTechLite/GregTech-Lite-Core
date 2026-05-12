package gregtechlite.gtlitecore.core.sound

import com.morphismmc.morphismlib.client.Games
import gregtechlite.gtlitecore.api.GTLiteAPI
import gregtechlite.gtlitecore.api.MOD_ID
import gregtechlite.gtlitecore.api.collection.openHashMapOf
import gregtechlite.gtlitecore.api.sound.SoundManager
import net.minecraft.client.audio.ISound
import net.minecraft.client.audio.PositionedSoundRecord
import net.minecraft.util.ResourceLocation
import net.minecraft.util.SoundCategory
import net.minecraft.util.SoundEvent
import net.minecraft.util.math.BlockPos
import net.minecraftforge.fml.common.registry.ForgeRegistries
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class SoundManagerImpl private constructor(): SoundManager
{
    companion object
    {
        private val instance = SoundManagerImpl()

        fun getInstance(): SoundManagerImpl = instance
    }

    /**
     * Warning: This map cannot be marked with `SideOnly(Side.CLIENT)` because the server
     * side will report it as a missing field when [instance] param is instantiated on the
     * server side.
     */
    private val sounds: MutableMap<BlockPos, ISound> = openHashMapOf()

    override fun registerSound(soundName: String): SoundEvent
    {
        var containerId = GTLiteAPI.moduleManager.loadedContainer.id
        if (containerId == null)
            containerId = MOD_ID
        return registerSound(containerId, soundName)
    }

    override fun registerSound(containerName: String, soundName: String): SoundEvent
    {
        val location = ResourceLocation(containerName, soundName)
        val event = SoundEvent(location)
        event.registryName = location
        ForgeRegistries.SOUND_EVENTS.register(event)
        return event
    }

    @SideOnly(Side.CLIENT)
    override fun startTileSound(soundName: ResourceLocation, volume: Float, pos: BlockPos): ISound
    {
        var sound = sounds[pos]
        if (sound == null || !Games.mc().soundHandler.isSoundPlaying(sound))
        {
            sound = PositionedSoundRecord(soundName, SoundCategory.BLOCKS, volume, 1.0F, true, 0,
                                          ISound.AttenuationType.LINEAR, pos.x + 0.5F, pos.y + 0.5F, pos.z + 0.5F)
            sounds[pos] = sound
            Games.mc().soundHandler.playSound(sound)
        }
        return sound
    }

    @SideOnly(Side.CLIENT)
    override fun stopTileSound(pos: BlockPos)
    {
        val sound = sounds[pos]
        sound?.let {
            Games.mc().soundHandler.stopSound(sound)
            sounds.remove(pos)
        }
    }
}