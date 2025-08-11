package gregtechlite.gtlitecore.api.recipe.builder

import gregtech.api.recipes.Recipe
import gregtech.api.recipes.RecipeBuilder
import gregtech.api.recipes.RecipeMap
import gregtechlite.gtlitecore.api.recipe.property.MobOnTopProperty
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityList
import net.minecraft.util.ResourceLocation
import org.apache.commons.lang3.builder.ToStringBuilder

class MobProximityRecipeBuilder : RecipeBuilder<MobProximityRecipeBuilder>
{

    constructor()

    @Suppress("unused")
    constructor(recipe: Recipe,
                recipeMap: RecipeMap<MobProximityRecipeBuilder>) : super(recipe, recipeMap)

    constructor(recipeBuilder: RecipeBuilder<MobProximityRecipeBuilder>) : super(recipeBuilder)

    override fun copy() = MobProximityRecipeBuilder(this)

    override fun applyPropertyCT(key: String, value: Any): Boolean
    {
        if (key == MobOnTopProperty.key)
        {
            this.mob(value as ResourceLocation)
            return true
        }
        return true
    }

    fun mob(entityId: ResourceLocation): MobProximityRecipeBuilder
    {
        this.applyProperty(MobOnTopProperty, entityId)
        return this
    }

    fun mob(entityClazz: Class<out Entity>): MobProximityRecipeBuilder
    {
        this.applyProperty(MobOnTopProperty, EntityList.getKey(entityClazz)!!)
        return this
    }

    override fun toString() = ToStringBuilder(this)
        .appendSuper(super.toString())
        .append(MobOnTopProperty.key, getEntityId())
        .toString()

    fun getEntityId(): ResourceLocation =
        if (this.recipePropertyStorage == null) ResourceLocation("lightning_bolt") else this.recipePropertyStorage.get(
            MobOnTopProperty,
            ResourceLocation("lightning_bolt")
        )!!

}