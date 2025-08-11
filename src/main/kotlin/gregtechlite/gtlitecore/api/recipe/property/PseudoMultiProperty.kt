package gregtechlite.gtlitecore.api.recipe.property

import gregtech.api.recipes.properties.RecipeProperty
import gregtechlite.gtlitecore.api.recipe.property.value.PseudoMultiPropertyValues
import net.minecraft.client.Minecraft
import net.minecraft.client.resources.I18n
import net.minecraft.nbt.NBTBase
import net.minecraft.nbt.NBTTagString
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

object PseudoMultiProperty : RecipeProperty<PseudoMultiPropertyValues>("blocks", PseudoMultiPropertyValues::class.java)
{

    override fun serialize(value: Any): NBTBase = NBTTagString(castValue(value).blockGroupName)

    override fun deserialize(nbt: NBTBase): Any = (nbt as? NBTTagString)!!.string

    @SideOnly(Side.CLIENT)
    override fun drawInfo(mc: Minecraft, x: Int, y: Int, color: Int, value: Any)
    {
        val localizedBlockGroupMembers = I18n.format("gtlitecore.recipe.block_group_members.${castValue(value).blockGroupName}.name")
        mc.fontRenderer.drawString(I18n.format("gtlitecore.recipe.blocks", localizedBlockGroupMembers), x, y, color)
    }

}