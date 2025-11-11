@file:JvmName("CircuitInfoManager")
package gregtechlite.gtlitecore.api.recipe.util

import gregtech.api.GTValues.LV
import gregtech.api.GTValues.VA
import gregtech.api.items.metaitem.MetaItem
import gregtech.api.recipes.RecipeMaps.SCANNER_RECIPES
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.collection.hashBiMapOf
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.stack
import gregtechlite.gtlitecore.api.recipe.builder.CircuitAssemblyLineRecipeBuilder
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CIRCUIT_PATTERN
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagString

const val CIRCUIT_INFO_NBT = "circuitInfo"

/**
 * The map with all item and its wrapped format.
 * Will be initialized when preloading Circuit Assembly Line recipes.
 */
val wrapItems: MutableMap<MetaItem<*>.MetaValueItem, MetaItem<*>.MetaValueItem> = hashBiMapOf()

/**
 * Create a scanning recipe for circuit info.
 *
 * @param circuitItem The item which will be scanned into the circuit pattern.
 * @param dataItem    The data container for the [circuitItem].
 */
fun createCircuitPatternRecipe(circuitItem: MetaItem<*>.MetaValueItem,
                               dataItem: MetaItem<*>.MetaValueItem = CIRCUIT_PATTERN)
{
    val dataStack = createDataItemWithTag(circuitItem, dataItem)
    SCANNER_RECIPES.recipeBuilder()
        .input(dataItem)
        .input(circuitItem)
        .outputs(dataStack)
        .EUt(VA[LV])
        .duration(2 * SECOND + 10 * TICK)
        .buildAndRegister()
}

/**
 * Make a circuit info to the recipe builder.
 *
 * @param circuitItem The item which will be scanned into the circuit pattern.
 * @param dataItem    The data container for the [circuitItem].
 */
fun CircuitAssemblyLineRecipeBuilder.circuitInfo(circuitItem: MetaItem<*>.MetaValueItem,
                                                 dataItem: MetaItem<*>.MetaValueItem = CIRCUIT_PATTERN): CircuitAssemblyLineRecipeBuilder
{
    circuit(createDataItemWithTag(circuitItem, dataItem))
    return this
}

/**
 * Create a [dataItem] with correspondenced NBT data from [circuitItem].
 *
 * @param circuitItem The item which will be scanned into the circuit pattern.
 * @param dataItem    The data container for the [circuitItem].
 */
fun createDataItemWithTag(circuitItem: MetaItem<*>.MetaValueItem,
                          dataItem: MetaItem<*>.MetaValueItem = CIRCUIT_PATTERN): ItemStack
{
    val nbtName = circuitItem.unlocalizedName

    val dataStack = dataItem.stack()
    dataStack.setTagInfo(CIRCUIT_INFO_NBT, NBTTagString(nbtName))

    return dataStack
}