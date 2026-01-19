package gregtechlite.gtlitecore.api.item

import gregtechlite.gtlitecore.client.event.TextAnimations
import net.minecraft.util.text.TextFormatting

enum class Contributors(val contributorName: String)
{

    MAGIC_SWEEPY(TextAnimations.GRADIENT_RAINBOW_BOLD("✧◇✧Magic_Sweepy✧◇✧")),
    LLAMA_WEI(TextFormatting.WHITE.toString() + TextFormatting.BOLD.toString() + "llama_wei");

}