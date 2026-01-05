/**
 * Copyright (c) 2022 GTNH Team, glee8e, Code Chicken
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of
 * the GNU Lesser General Public License as published by the Free Software
 * Foundation, either version 2.1 of the License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public License along with this program.
 * If not, see <http://www.gnu.org/licenses/>.
 */
package gregtechlite.gtlitecore.client.event

import com.morphismmc.morphismlib.util.I18nUtil
import net.minecraft.util.text.TextFormatting
import org.jetbrains.annotations.Range

object TextAnimations
{
    val BLACK = TextFormatting.BLACK.toString()
    val DARK_BLUE = TextFormatting.DARK_BLUE.toString()
    val DARK_GREEN = TextFormatting.DARK_GREEN.toString()
    val DARK_AQUA = TextFormatting.DARK_AQUA.toString()
    val DARK_RED = TextFormatting.DARK_RED.toString()
    val DARK_PURPLE = TextFormatting.DARK_PURPLE.toString()
    val GOLD = TextFormatting.GOLD.toString()
    val GRAY = TextFormatting.GRAY.toString()
    val DARK_GRAY = TextFormatting.DARK_GRAY.toString()
    val BLUE = TextFormatting.BLUE.toString()
    val GREEN = TextFormatting.GREEN.toString()
    val AQUA = TextFormatting.AQUA.toString()
    val RED = TextFormatting.RED.toString()
    val LIGHT_PURPLE = TextFormatting.LIGHT_PURPLE.toString()
    val YELLOW = TextFormatting.YELLOW.toString()
    val WHITE = TextFormatting.WHITE.toString()
    val OBFUSCATED = TextFormatting.OBFUSCATED.toString()
    val BOLD = TextFormatting.BOLD.toString()
    val STRIKETHROUGH = TextFormatting.STRIKETHROUGH.toString()
    val UNDERLINE = TextFormatting.UNDERLINE.toString()
    val ITALIC = TextFormatting.ITALIC.toString()
    val RESET = TextFormatting.RESET.toString()

    val GRADIENT_PURPLE_RED: GradientAnimation = GradientAnimation(1, 100, DARK_PURPLE, DARK_RED)

    val GRADIENT_RAINBOW: GradientAnimation = GradientAnimation(1, 100,
                                                                GOLD, YELLOW, GREEN, AQUA, BLUE, LIGHT_PURPLE)

    val GRADIENT_RAINBOW_BOLD: GradientAnimation = GRADIENT_RAINBOW.with(BOLD)
}

/**
 * Create a formatted and animated text, this method taken and adapted from GT:NH modpack
 * team's fork mod [Avaritia](https://github.com/GTNewHorizons/Avaritia),
 * please see line 19 at `fox/spiteful/avaritia/LudicrousText.java`.
 *
 * @param step    The steps `formats` is shifted each `delay`.
 * @param delay   The milliseconds which between each shift of `formats`.
 * @param formats An array of formatting codes. Each char of `text` will be
 *                prefixed by one entry, depending on `step` and `delay`.
 *                Wraps around, if shorter than `formats`.
 * @return        A formatted and animated text with properties.
 *
 * @author TTFTCUTS, glowredman
 */
class GradientAnimation (val step: @Range(from = 0, to = Int.MAX_VALUE.toLong()) Int,
                         val delay: @Range(from = 1, to = Int.MAX_VALUE.toLong()) Int,
                         vararg formats: String)
{
    private val formats = formats.toList()

    init
    {
        require(step >= 0)
        require(delay >= 1)
    }

    fun with(vararg additions: String): GradientAnimation
    {
        val newFormats = formats.map { it + additions.joinToString("") }.toTypedArray()
        return GradientAnimation(step, delay, *newFormats)
    }

    operator fun invoke(text: String, vararg args: Any) : String
    {
        if (text.isBlank() || formats.isEmpty()) return text
        val translated = I18nUtil.format(text, *args)
        val stringBuilder = StringBuilder(translated.length * 3)
        val offset = ((System.currentTimeMillis() / delay) % formats.size).toInt()
        for (i in 0..<translated.length)
        {
            val c = translated[i]
            val indexColorArray = (i * step + formats.size - offset) % formats.size
            stringBuilder.append(formats[indexColorArray])
            stringBuilder.append(c)
        }
        return stringBuilder.toString()
    }

}