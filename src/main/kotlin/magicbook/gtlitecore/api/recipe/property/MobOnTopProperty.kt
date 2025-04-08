package magicbook.gtlitecore.api.recipe.property

import gregtech.api.recipes.properties.RecipeProperty
import net.minecraft.client.Minecraft
import net.minecraft.client.resources.I18n
import net.minecraft.entity.EntityList
import net.minecraft.nbt.NBTBase
import net.minecraft.nbt.NBTTagString
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

@Suppress("MISSING_DEPENDENCY_CLASS")
class MobOnTopProperty private constructor() : RecipeProperty<ResourceLocation>(KEY, ResourceLocation::class.java)
{

    companion object
    {

        const val KEY = "mob_on_top"

        val playerEntity =  ResourceLocation("player")

        @JvmField
        val INSTANCE = MobOnTopProperty()

    }

    override fun serialize(value: Any): NBTBase = NBTTagString(castValue(value).toString())

    override fun deserialize(nbt: NBTBase): Any = (nbt as? NBTTagString)!!.string

    override fun drawInfo(mc: Minecraft, x: Int, y: Int, color: Int, value: Any)
    {
        mc.fontRenderer.drawString(I18n.format("gtlitecore.recipe.accepted_entity",
            getTranslationName(castValue(value))), x, y, color)
    }

    @SideOnly(Side.CLIENT)
    private fun getTranslationName(location: ResourceLocation)
        = if (location == playerEntity) I18n.format("gtlitecore.recipe.entity_name.player") else I18n.format("entity.${EntityList.getTranslationName(location)}.name")

}