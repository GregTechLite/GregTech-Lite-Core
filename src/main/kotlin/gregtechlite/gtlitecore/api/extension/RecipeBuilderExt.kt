@file:Suppress("FunctionName")
package gregtechlite.gtlitecore.api.extension

import gregtech.api.recipes.RecipeBuilder
import gregtech.api.recipes.RecipeMap
import gregtech.api.recipes.builders.ResearchRecipeBuilder
import net.minecraft.item.Item
import net.minecraft.item.ItemStack

// region DSL Context

/**
 * Registers a recipe for the [RecipeMap].
 *
 * This method is Kotlin style DSL for GTCEu [RecipeBuilder] for recipe registries.
 *
 * @param builder The corresponding [RecipeBuilder] of the [RecipeMap].
 */
fun <T: RecipeBuilder<T>> RecipeMap<T>.buildRecipe(builder: T.() -> Unit)
    = recipeBuilder().apply(builder).buildAndRegister()

// endregion

// region Recipe Input Shortcut

fun <T: RecipeBuilder<T>> RecipeBuilder<T>.inputs(item: Item): T
    = inputs(item, 1)

fun <T: RecipeBuilder<T>> RecipeBuilder<T>.inputs(item: Item, amount: Int): T
    = inputs(item, amount, 0)

fun <T: RecipeBuilder<T>> RecipeBuilder<T>.inputs(item: Item, amount: Int, metadata: Int): T
    = inputs(ItemStack(item, amount, metadata))

// endregion

// region Recipe EUt Shortcut

fun <T: RecipeBuilder<T>> RecipeBuilder<T>.EUt(eut: Int): T = EUt(eut.toLong())

fun <T: ResearchRecipeBuilder<T>> ResearchRecipeBuilder<T>.EUt(eut: Int): T = EUt(eut.toLong())

// endregion

// region Recipe Duration Shortcut

fun <T: RecipeBuilder<T>> RecipeBuilder<T>.duration(duration: Long): T
    = duration(duration.toInt())

fun <T: RecipeBuilder<T>> RecipeBuilder<T>.duration(duration: Double): T
    = duration(duration.toInt())

// endregion
