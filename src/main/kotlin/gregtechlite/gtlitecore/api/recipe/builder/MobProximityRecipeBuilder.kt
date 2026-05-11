package gregtechlite.gtlitecore.api.recipe.builder

import gregtech.api.recipes.Recipe
import gregtech.api.recipes.RecipeBuilder
import gregtech.api.recipes.RecipeMap
import gregtechlite.gtlitecore.api.extension.buildToString
import gregtechlite.gtlitecore.api.recipe.property.MobOnTopProperty
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityList
import net.minecraft.util.ResourceLocation
import kotlin.reflect.KClass

class MobProximityRecipeBuilder : RecipeBuilder<MobProximityRecipeBuilder>
{
    val entityId
        get() = recipePropertyStorage?.let { recipePropertyStorage.get(MobOnTopProperty, ResourceLocation("lightning_bolt")) } ?: ResourceLocation("lightning_bolt")

    constructor()

    @Suppress("unused")
    constructor(recipe: Recipe, recipeMap: RecipeMap<MobProximityRecipeBuilder>) : super(recipe, recipeMap)

    constructor(recipeBuilder: RecipeBuilder<MobProximityRecipeBuilder>) : super(recipeBuilder)

    override fun copy(): MobProximityRecipeBuilder = MobProximityRecipeBuilder(this)

    override fun applyPropertyCT(key: String, value: Any): Boolean
    {
        if (key == MobOnTopProperty.key)
        {
            mob(value as ResourceLocation)
            return true
        }
        return true
    }

    fun mob(entityName: String): MobProximityRecipeBuilder = mob(ResourceLocation(entityName))

    fun mob(entityId: ResourceLocation): MobProximityRecipeBuilder = apply {
        applyProperty(MobOnTopProperty, entityId)
    }

    fun mob(entityClazz: Class<out Entity>): MobProximityRecipeBuilder = apply {
        applyProperty(MobOnTopProperty, EntityList.getKey(entityClazz)!!)
    }

    fun mob(entityClazz: KClass<out Entity>): MobProximityRecipeBuilder = mob(entityClazz.javaObjectType)

    override fun toString(): String = buildToString {
        appendSuper(super.toString())
        append(MobOnTopProperty.key, entityId)
    }
}