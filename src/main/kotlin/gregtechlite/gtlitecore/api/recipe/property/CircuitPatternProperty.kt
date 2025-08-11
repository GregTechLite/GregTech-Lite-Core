package gregtechlite.gtlitecore.api.recipe.property

import gregtech.api.recipes.properties.RecipeProperty
import net.minecraft.client.Minecraft
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTBase
import net.minecraft.nbt.NBTTagString
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

object CircuitPatternProperty : RecipeProperty<ItemStack>("circuit", ItemStack::class.java)
{

    val registeredCircuits = hashSetOf<ItemStack>()

    override fun serialize(value: Any): NBTBase = NBTTagString(castValue(value).displayName)

    override fun deserialize(nbt: NBTBase): Any = (nbt as? NBTTagString)!!.string

    @SideOnly(Side.CLIENT)
    override fun drawInfo(mc: Minecraft, x: Int, y: Int, color: Int, value: Any)
    {
        mc.fontRenderer.drawString(I18n.format("gtlitecore.recipe.circuit_pattern", castValue(value)), x, y, color)
    }

    fun registerCircuit(stack: ItemStack)
    {
        registeredCircuits.add(stack)
    }

}