package magicbook.gtlitecore.api.recipe.property

import gregtech.api.recipes.properties.RecipeProperty
import net.minecraft.client.Minecraft
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTBase
import net.minecraft.nbt.NBTTagString

class CircuitPatternProperty : RecipeProperty<ItemStack>(KEY, ItemStack::class.java)
{

    companion object
    {

        const val KEY = "circuit"

        val registeredCircuits = hashSetOf<ItemStack>()

        val INSTANCE = CircuitPatternProperty()

        fun register(stack: ItemStack)
        {
            registeredCircuits.add(stack)
        }

    }

    override fun serialize(value: Any): NBTBase = NBTTagString(castValue(value).displayName)

    override fun deserialize(nbt: NBTBase): Any = (nbt as? NBTTagString)!!.string

    override fun drawInfo(mc: Minecraft, x: Int, y: Int, color: Int, value: Any)
    {
        mc.fontRenderer.drawString(I18n.format("gtlitecore.recipe.circuit_pattern", castValue(value)), x, y, color)
    }

}