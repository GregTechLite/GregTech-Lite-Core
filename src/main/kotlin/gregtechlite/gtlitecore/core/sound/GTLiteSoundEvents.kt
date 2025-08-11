package gregtechlite.gtlitecore.core.sound

import gregtechlite.gtlitecore.api.GTLiteAPI
import net.minecraft.util.SoundEvent

internal object GTLiteSoundEvents
{

    lateinit var STELLAR_FORGE: SoundEvent

    fun register()
    {
        STELLAR_FORGE = GTLiteAPI.soundManager.registerSound("tick.stellar_forge")
    }

}