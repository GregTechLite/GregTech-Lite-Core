package gregtechlite.gtlitecore.api.recipe.builder

import gregtech.api.recipes.Recipe
import gregtech.api.recipes.RecipeBuilder
import gregtech.api.recipes.RecipeMap
import gregtechlite.gtlitecore.GTLiteMod
import gregtechlite.gtlitecore.api.extension.buildToString
import gregtechlite.gtlitecore.api.recipe.property.RequestAdditionalProperty
import gregtechlite.gtlitecore.api.recipe.property.value.RequestAdditionalPropertyValue
import net.minecraft.util.ResourceLocation

class NanoForgeRecipeBuilder : RecipeBuilder<NanoForgeRecipeBuilder>
{
    val requests
        get() = recipePropertyStorage?.let { recipePropertyStorage.get(RequestAdditionalProperty, null) }

    companion object
    {
        val structT2 = GTLiteMod.id("consciousness_storage_center")
        val structT3 = GTLiteMod.id("nanite_replication_unrestricor")
        val structT4 = GTLiteMod.id("virtual_gestalt_computing_uplink")
    }

    constructor()

    @Suppress("unused")
    constructor(recipe: Recipe, recipeMap: RecipeMap<NanoForgeRecipeBuilder>) : super(recipe, recipeMap)

    constructor(recipeBuilder: RecipeBuilder<NanoForgeRecipeBuilder>) : super(recipeBuilder)

    override fun copy(): NanoForgeRecipeBuilder = NanoForgeRecipeBuilder(this)

    fun requireStruct(vararg ids: ResourceLocation): NanoForgeRecipeBuilder = apply {
        recipePropertyStorage?.get(RequestAdditionalProperty, null)?.additionalStructures?.addAll(ids)
            ?: applyProperty(RequestAdditionalProperty, RequestAdditionalPropertyValue(mutableListOf(*ids)))
    }

    override fun applyPropertyCT(key: String, value: Any): Boolean
    {
        if (key == RequestAdditionalProperty.key)
        {
            (value as RequestAdditionalPropertyValue).additionalStructures.forEach {
                requireStruct(ResourceLocation(it.toString()))
            }
            return true
        }
        return super.applyPropertyCT(key, value)
    }

    override fun toString(): String = buildToString {
        appendSuper(super.toString())
        append(RequestAdditionalProperty.key, requests?.additionalStructures?.joinToString())
    }
}
