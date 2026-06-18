package gregtechlite.gtlitecore.api.recipe.builder

import gregtech.api.recipes.Recipe
import gregtech.api.recipes.RecipeBuilder
import gregtech.api.recipes.RecipeMap
import gregtechlite.gtlitecore.GTLiteMod
import gregtechlite.gtlitecore.api.extension.buildToString
import gregtechlite.gtlitecore.api.recipe.property.RequestAdditionalProperty
import gregtechlite.gtlitecore.api.recipe.property.value.RequestAdditionalPropertyValue
import net.minecraft.util.ResourceLocation

class PCBFactoryRecipeBuilder : RecipeBuilder<PCBFactoryRecipeBuilder>
{
    val requests
        get() = recipePropertyStorage?.let { recipePropertyStorage.get(RequestAdditionalProperty, null) }

    companion object
    {
        val nanoArray = GTLiteMod.id("nanolithography_array") // T2
        val circDetector = GTLiteMod.id("microscale_circuit_detector") // T3
        val bioChamber = GTLiteMod.id("bio_component_cultivation_chamber") // Bio Chamber Upgrade

        // TODO: Gooware: Nonlinear Thermodynamic Cycle Unit, Optical: Optoelectronic Carving Room,
        //       Spintronic: Electromagnetic Effect Generator
        //       These additional structures are some idea for high tier circuit boards, maybe add it in the future.
    }

    constructor()

    @Suppress("unused")
    constructor(recipe: Recipe, recipeMap: RecipeMap<PCBFactoryRecipeBuilder>) : super(recipe, recipeMap)

    constructor(recipeBuilder: PCBFactoryRecipeBuilder) : super(recipeBuilder)

    override fun copy(): PCBFactoryRecipeBuilder = PCBFactoryRecipeBuilder(this)

    fun requireStruct(vararg ids: ResourceLocation): PCBFactoryRecipeBuilder = apply {
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
