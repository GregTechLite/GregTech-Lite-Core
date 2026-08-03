package gregtechlite.gtlitecore.api.recipe.property

import gregtech.api.recipes.properties.RecipeProperty
import gregtechlite.gtlitecore.api.recipe.property.value.RequestAdditionalPropertyValue
import net.minecraft.client.Minecraft
import net.minecraft.client.resources.I18n
import net.minecraft.nbt.NBTBase
import net.minecraft.nbt.NBTTagList
import net.minecraft.nbt.NBTTagString
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

object RequestAdditionalProperty: RecipeProperty<RequestAdditionalPropertyValue>(
    "request_additional", RequestAdditionalPropertyValue::class.java)
{
    override fun serialize(value: Any): NBTBase
    {
        val tags = NBTTagList()
        castValue(value).additionalStructures.forEach {
            tags.appendTag(NBTTagString(it.toString()))
        }
        return tags
    }

    override fun deserialize(nbt: NBTBase): Any
    {
        val preparedValue = RequestAdditionalPropertyValue()
        if (nbt is NBTTagList)
        {
            nbt.forEach { preparedValue.additionalStructures.add(ResourceLocation((it as NBTTagString).string)) }
        }
        return preparedValue
    }

    @SideOnly(Side.CLIENT)
    override fun drawInfo(mc: Minecraft, x: Int, y: Int, color: Int, value: Any)
    {
        val names = castValue(value).additionalStructures.joinToString(", ") { id ->
            val split = id.toString().split(":")
            I18n.format("${split[0]}.machine.${split[1]}.name")
        }
        mc.fontRenderer.drawString(I18n.format("gtlitecore.recipe.request_additional_structures", names), x, y, color)
    }
}