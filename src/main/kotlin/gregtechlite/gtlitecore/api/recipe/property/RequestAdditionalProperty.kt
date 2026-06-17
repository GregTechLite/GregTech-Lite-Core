package gregtechlite.gtlitecore.api.recipe.property

import gregtech.api.recipes.properties.RecipeProperty
import net.minecraft.client.Minecraft
import net.minecraft.client.resources.I18n
import net.minecraft.nbt.NBTBase
import net.minecraft.nbt.NBTTagString
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

object RequestAdditionalProperty: RecipeProperty<ResourceLocation>("request_additional", ResourceLocation::class.java)
{
    override fun serialize(value: Any): NBTBase = NBTTagString(value.toString())

    override fun deserialize(nbt: NBTBase): Any = (nbt as? NBTTagString)!!.string

    @SideOnly(Side.CLIENT)
    override fun drawInfo(mc: Minecraft, x: Int, y: Int, color: Int, value: Any)
    {
        val split = value.toString().split(":")
        mc.fontRenderer.drawString(
            I18n.format("gtlitecore.recipe.request_additional_structures", "${split[0]}.machine.${split[1]}.name")
            , x, y, color)
    }
}