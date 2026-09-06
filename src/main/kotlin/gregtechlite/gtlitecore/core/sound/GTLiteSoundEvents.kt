package gregtechlite.gtlitecore.core.sound

import gregtechlite.gtlitecore.api.GTLiteAPI
import net.minecraft.util.SoundEvent

internal object GTLiteSoundEvents
{
    lateinit var STELLAR_FORGE: SoundEvent
    lateinit var QUANTUM: SoundEvent
    lateinit var BLACKHOLE: SoundEvent
    lateinit var PCB_FACTORY: SoundEvent
    lateinit var SPACE_ASSEMBLER: SoundEvent

    fun register()
    {
        STELLAR_FORGE = GTLiteAPI.soundManager.registerSound("tick.stellar_forge")
        QUANTUM = GTLiteAPI.soundManager.registerSound("tick.quantum")
        BLACKHOLE = GTLiteAPI.soundManager.registerSound("tick.blackhole")
        PCB_FACTORY = GTLiteAPI.soundManager.registerSound("tick.pcb_factory")
        SPACE_ASSEMBLER = GTLiteAPI.soundManager.registerSound("tick.space_assembler")
    }
}