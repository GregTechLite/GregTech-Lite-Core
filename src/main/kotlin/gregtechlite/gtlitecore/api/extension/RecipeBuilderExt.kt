package gregtechlite.gtlitecore.api.extension

import gregtech.api.recipes.RecipeBuilder
import gregtech.api.recipes.builders.ResearchRecipeBuilder

// Short-circuit number converts for RecipeBuilder#EUt.

@Suppress("FunctionName")
fun <T: RecipeBuilder<T>> RecipeBuilder<T>.EUt(eut: Int): T
{
    return EUt(eut.toLong())
}

@Suppress("FunctionName")
fun <T: ResearchRecipeBuilder<T>> ResearchRecipeBuilder<T>.EUt(eut: Int): T
{
    return EUt(eut.toLong())
}

fun <T: RecipeBuilder<T>> RecipeBuilder<T>.duration(duration: Long): T
{
    return duration(duration.toInt())
}

fun <T: RecipeBuilder<T>> RecipeBuilder<T>.duration(duration: Double): T
{
    return duration(duration.toInt())
}