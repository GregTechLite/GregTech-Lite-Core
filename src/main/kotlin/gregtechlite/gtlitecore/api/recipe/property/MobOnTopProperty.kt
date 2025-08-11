package gregtechlite.gtlitecore.api.recipe.property

import gregtech.api.recipes.properties.RecipeProperty
import net.minecraft.client.Minecraft
import net.minecraft.client.resources.I18n
import net.minecraft.entity.EntityList
import net.minecraft.nbt.NBTBase
import net.minecraft.nbt.NBTTagString
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

object MobOnTopProperty : RecipeProperty<ResourceLocation>("mob_on_top", ResourceLocation::class.java)
{

    val playerEntity =  ResourceLocation("player")

    override fun serialize(value: Any) = NBTTagString(castValue(value).toString())

    override fun deserialize(nbt: NBTBase) = ResourceLocation((nbt as NBTTagString).string)

    @SideOnly(Side.CLIENT)
    override fun drawInfo(mc: Minecraft, x: Int, y: Int, color: Int, value: Any)
    {
        mc.fontRenderer.drawString(I18n.format("gtlitecore.recipe.accepted_entity",
            getTranslationName(castValue(value))), x, y, color)

    }

    @SideOnly(Side.CLIENT)
    private fun getTranslationName(location: ResourceLocation): String
    {
        return if (location == playerEntity)
            I18n.format("gtlitecore.recipe.entity_name.player")
        else
            I18n.format("entity.${EntityList.getTranslationName(location)}.name")
    }

}