package magicbook.gtlitecore.api.recipe.property

import gregtech.api.recipes.properties.RecipeProperty
import net.minecraft.client.Minecraft
import net.minecraft.client.resources.I18n
import net.minecraft.nbt.NBTBase
import net.minecraft.nbt.NBTTagString

@Suppress("MISSING_DEPENDENCY_CLASS")
class PseudoMultiProperty private constructor() : RecipeProperty<PseudoMultiPropertyValues>(KEY, PseudoMultiPropertyValues::class.java)
{

    companion object
    {

        const val KEY = "blocks"

        @JvmField
        val INSTANCE = PseudoMultiProperty()

    }

    override fun serialize(value: Any): NBTBase = NBTTagString(castValue(value).blockGroupName)

    override fun deserialize(nbt: NBTBase): Any = (nbt as? NBTTagString)!!.string

    override fun drawInfo(mc: Minecraft, x: Int, y: Int, color: Int, value: Any)
    {
        val localizedBlockGroupMembers = I18n.format("gtlitecore.recipe.block_group_members.${castValue(value).blockGroupName}.name")
        mc.fontRenderer.drawString(I18n.format("gtlitecore.recipe.blocks", localizedBlockGroupMembers), x, y, color)
    }

}