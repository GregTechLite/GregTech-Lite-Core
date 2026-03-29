@file:Suppress("FunctionName")
package gregtechlite.gtlitecore.api.extension

import gregtech.api.metatileentity.multiblock.CleanroomType
import gregtech.api.recipes.GTRecipeHandler
import gregtech.api.recipes.RecipeBuilder
import gregtech.api.recipes.RecipeMap
import gregtech.api.recipes.builders.ResearchRecipeBuilder
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraftforge.fluids.FluidStack

// region DSL Context

/**
 * Registers a recipe for the [RecipeMap].
 *
 * This method is Kotlin style DSL for GTCEu [RecipeBuilder] for recipe registries.
 *
 * @param builder The corresponding [RecipeBuilder] of the [RecipeMap].
 */
fun <T: RecipeBuilder<T>> RecipeMap<T>.addRecipe(builder: T.() -> Unit)
    = recipeBuilder().apply(builder).buildAndRegister()

// endregion

// region Recipe Input Shortcut

fun <T: RecipeBuilder<T>> RecipeBuilder<T>.inputs(item: Item, amount: Int = 1, metadata: Int = 0): T
    = inputs(ItemStack(item, amount, metadata))

fun <T: RecipeBuilder<T>> RecipeBuilder<T>.inputs(block: Block, amount: Int = 1): T
    = inputs(ItemStack(block, amount))

// endregion

// region Recipe Output Shortcut

fun <T: RecipeBuilder<T>> RecipeBuilder<T>.outputs(item: Item, amount: Int = 1, metadata: Int = 0): T
    = outputs(ItemStack(item, amount, metadata))

fun <T: RecipeBuilder<T>> RecipeBuilder<T>.outputs(block: Block, amount: Int = 1): T
    = outputs(ItemStack(block, amount))

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

// region Recipe Condition Shortcut

fun <T: RecipeBuilder<T>> RecipeBuilder<T>.cleanroom(): T
    = cleanroom(CleanroomType.CLEANROOM)

fun <T: RecipeBuilder<T>> RecipeBuilder<T>.sterileCleanroom(): T
    = cleanroom(CleanroomType.STERILE_CLEANROOM)

// endregion

// region Recipe Removal Shortcut

fun <T: RecipeBuilder<T>> RecipeMap<T>.removeRecipe(vararg itemInputs: ItemStack)
    = GTRecipeHandler.removeRecipesByInputs(this, *itemInputs)

fun <T: RecipeBuilder<T>> RecipeMap<T>.removeRecipe(vararg fluidInputs: FluidStack)
    = GTRecipeHandler.removeRecipesByInputs(this, *fluidInputs)

fun <T: RecipeBuilder<T>> RecipeMap<T>.removeRecipe(itemInputs: Array<ItemStack>, fluidInputs: Array<FluidStack>)
    = GTRecipeHandler.removeRecipesByInputs(this, itemInputs, fluidInputs)

// endregion