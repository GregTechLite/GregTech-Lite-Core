package gregtechlite.gtlitecore.api.recipe.property

import com.morphismmc.morphismlib.util.I18nUtil
import gregtech.api.recipes.properties.RecipeProperty
import net.minecraft.client.Minecraft
import net.minecraft.nbt.NBTBase
import net.minecraft.nbt.NBTTagByte
import net.minecraft.nbt.NBTTagInt
import net.minecraft.util.ResourceLocation
import kotlin.reflect.KClass

class RecipePropertyBuilder<T : Any>(val key: String, val type: KClass<T>)
{

    var serializer: ((T) -> NBTBase)? = null
    var deserializer: ((NBTBase) -> T)? = null

    var infoSupplier: (T) -> List<String> = { emptyList() }

    private var hidden: Boolean = false
    private var hideTotalEU: Boolean = false
    private var hideEUt: Boolean = false
    private var hideDuration: Boolean = false

    fun hide() = apply {
        this.hidden = true
    }

    fun hideTotalEU() = apply {
        this.hideTotalEU = true
    }

    fun hideEUt() = apply {
        this.hideEUt = true
    }

    fun hideDuration() = apply {
        this.hideDuration = true
    }

    fun buildAndRegister(): RecipeProperty<T>
    {
        checkNotNull(serializer) { "serializer unset" }
        checkNotNull(deserializer) { "deserializer unset" }

        return object : RecipeProperty<T>(key, type.javaObjectType)
        {
            override fun serialize(value: Any) = serializer!!(castValue(value))

            override fun deserialize(nbt: NBTBase) = deserializer!!(nbt)

            override fun drawInfo(mc: Minecraft, x: Int, y: Int, color: Int, value: Any)
            {
                infoSupplier(castValue(value)).forEachIndexed { index, info ->
                    mc.fontRenderer.drawString(info, x, y + index * 10, color)
                }
            }

            override fun getInfoHeight(value: Any) = infoSupplier(castValue(value)).size * 10

            override fun isHidden() = hidden

            override fun hideTotalEU() = hideTotalEU

            override fun hideEUt() = hideEUt

            override fun hideDuration() = hideDuration
        }
    }

    companion object
    {

        fun <T : Any> property(key: String, type: KClass<T>, init: RecipePropertyBuilder<T>.() -> Unit)
           = RecipePropertyBuilder(key, type).apply(init).buildAndRegister()

        fun intProperty(key: String, init: RecipePropertyBuilder<Int>.() -> Unit) = property(key, Int::class) {
            serializer = { NBTTagInt(it) }
            deserializer = { (it as NBTTagInt).int }
            init()
        }

        fun tierProperty(location: ResourceLocation) = intProperty(location.path) {
            infoSupplier = {
                val translationKey = "${location.namespace}.recipe.$key"
                listOf(I18nUtil.format(I18nUtil.format("$translationKey.$it", translationKey)))
            }
        }

        fun booleanProperty(key: String, init: RecipePropertyBuilder<Boolean>.() -> Unit) =
            property(key, Boolean::class) {
                serializer = { NBTTagByte((if (it) 1 else 0).toByte()) }
                deserializer = { (it as NBTTagByte).byte != 0.toByte() }
                init()
            }

    }

}