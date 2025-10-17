package gregtechlite.gtlitecore.api.recipe.map

import gregtech.api.recipes.RecipeBuilder
import gregtech.api.recipes.RecipeMap
import gregtech.api.recipes.ui.RecipeMapUI

class PseudoGroupRecipeMapBuilder<B : RecipeBuilder<B>>(private val unlocalizedName: String,
                                                        private val defaultRecipeBuilder: B)
{

    private var recipeMaps: Array<RecipeMap<B>> = arrayOf()

    private var itemInputs = 0
    private var itemOutputs = 0
    private var fluidInputs = 0
    private var fluidOutputs = 0

    fun group(recipeMaps: Array<RecipeMap<B>>) = apply { this.recipeMaps = recipeMaps }

    @JvmName("groupKt")
    fun group(vararg recipeMaps: RecipeMap<B>) = apply { this.recipeMaps = recipeMaps.toList().toTypedArray() }

    fun itemInputs(itemInputs: Int) = apply { this.itemInputs = itemInputs }

    fun itemOutputs(itemOutputs: Int) = apply { this.itemOutputs = itemOutputs }

    fun fluidInputs(fluidInputs: Int) = apply { this.fluidInputs = fluidInputs }

    fun fluidOutputs(fluidOutputs: Int) = apply { this.fluidOutputs = fluidOutputs }

    fun build(): RecipeMap<B> = when (recipeMaps.size)
    {
        2 -> PseudoPairRecipeMap(unlocalizedName, defaultRecipeBuilder, ::buildUI,
                                 itemInputs, itemOutputs, fluidInputs, fluidOutputs,
                                 recipeMaps[0], recipeMaps[1])
        3 -> PseudoTripleRecipeMap(unlocalizedName, defaultRecipeBuilder, ::buildUI,
                                   itemInputs, itemOutputs, fluidInputs, fluidOutputs,
                                   recipeMaps[0], recipeMaps[1], recipeMaps[2])
        4 -> PseudoQuadrupleRecipeMap(unlocalizedName, defaultRecipeBuilder, ::buildUI,
                                      itemInputs, itemOutputs, fluidInputs, fluidOutputs,
                                      recipeMaps[0], recipeMaps[1], recipeMaps[2], recipeMaps[3])
        else -> throw IllegalArgumentException("Too many recipe map which pseudo group contained")
    }

    @Suppress("UnstableApiUsage")
    private fun buildUI(recipeMap: RecipeMap<*>): RecipeMapUI<*>
    {
        // Pseudo group recipe map do not generate correspondenced ui page.
        val ui = RecipeMapUI(recipeMap, false, false, false, false, false)
        return ui
    }

}