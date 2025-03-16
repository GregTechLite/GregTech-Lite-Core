package magicbook.gtlitecore.api.recipe.property

import gregtech.api.recipes.properties.RecipeProperty
import net.minecraft.client.Minecraft
import net.minecraft.client.resources.I18n
import net.minecraft.nbt.NBTBase
import net.minecraft.nbt.NBTTagInt

class NoCoilTemperatureProperty : RecipeProperty<Int>(KEY, Int::class.java)
{

    companion object
    {

        val KEY = "temperature"

        var INSTANCE = NoCoilTemperatureProperty()
    }

    override fun serialize(value: Any): NBTBase = NBTTagInt(castValue(value).toInt())

    override fun deserialize(nbt: NBTBase): Any = (nbt as? NBTTagInt)!!.int

    override fun drawInfo(mc: Minecraft, x: Int, y: Int, color: Int, value: Any)
    {
        mc.fontRenderer.drawString(I18n.format("gtlitecore.recipe.temperature", castValue(value)), x, y, color)
    }

}