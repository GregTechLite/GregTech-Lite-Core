package magicbook.gtlitecore.core.sound;

import magicbook.gtlitecore.api.GTLiteAPI;
import net.minecraft.util.SoundEvent;

public class GTLiteSoundEvents
{

    // Machine Sounds.
    public static SoundEvent STELLAR_FORGE;

    public static void register()
    {
        STELLAR_FORGE = GTLiteAPI.soundManager.registerSound("tick.stellar_forge");
    }

}
