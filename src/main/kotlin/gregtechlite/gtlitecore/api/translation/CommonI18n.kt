package gregtechlite.gtlitecore.api.translation

import gregtechlite.magicbook.util.LogicalSides
import net.minecraft.client.resources.I18n

object CommonI18n
{

    /**
     * Get a formated text [translationKey] or [defaultValue].
     *
     * @author vfyjxf
     */
    @JvmStatic
    fun format(translationKey: String, defaultValue: String = translationKey) =
        if (LogicalSides.isClient()) format(translationKey) else defaultValue

    /**
     * Get a formated text [translationKey] or [defaultValue].
     *
     * @author vfyjxf
     */
    @JvmStatic
    fun format(translationKey: String, vararg params: Any, defaultValue: String = translationKey) =
        if (LogicalSides.isClient()) format(translationKey, params) else defaultValue

    /**
     * Transformed method for [net.minecraft.client.resources.I18n.format], this is a safety ensure method
     * for two sides import.
     *
     * @author vfyjxf
     */
    private fun format(translationKey: String): String = I18n.format(translationKey)

    /**
     * Transformed method for [I18n.format], this is a safety ensure method
     * for two sides import.
     *
     * @author vfyjxf
     */
    private fun format(translationKey: String, vararg params: Any): String = I18n.format(translationKey, params)

}