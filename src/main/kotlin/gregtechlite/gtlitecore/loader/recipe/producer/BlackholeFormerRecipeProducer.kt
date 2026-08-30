package gregtechlite.gtlitecore.loader.recipe.producer

import gregtech.api.recipes.RecipeMaps
import gregtech.api.recipes.ingredients.GTRecipeInput
import gregtech.common.items.MetaItems.SHAPE_EXTRUDER_BLOCK
import gregtech.common.items.MetaItems.SHAPE_EXTRUDER_BOLT
import gregtech.common.items.MetaItems.SHAPE_EXTRUDER_BOTTLE
import gregtech.common.items.MetaItems.SHAPE_EXTRUDER_CELL
import gregtech.common.items.MetaItems.SHAPE_EXTRUDER_FOIL
import gregtech.common.items.MetaItems.SHAPE_EXTRUDER_GEAR
import gregtech.common.items.MetaItems.SHAPE_EXTRUDER_GEAR_SMALL
import gregtech.common.items.MetaItems.SHAPE_EXTRUDER_INGOT
import gregtech.common.items.MetaItems.SHAPE_EXTRUDER_PIPE_HUGE
import gregtech.common.items.MetaItems.SHAPE_EXTRUDER_PIPE_LARGE
import gregtech.common.items.MetaItems.SHAPE_EXTRUDER_PIPE_NORMAL
import gregtech.common.items.MetaItems.SHAPE_EXTRUDER_PIPE_SMALL
import gregtech.common.items.MetaItems.SHAPE_EXTRUDER_PIPE_TINY
import gregtech.common.items.MetaItems.SHAPE_EXTRUDER_PLATE
import gregtech.common.items.MetaItems.SHAPE_EXTRUDER_RING
import gregtech.common.items.MetaItems.SHAPE_EXTRUDER_ROD
import gregtech.common.items.MetaItems.SHAPE_EXTRUDER_ROD_LONG
import gregtech.common.items.MetaItems.SHAPE_EXTRUDER_ROTOR
import gregtech.common.items.MetaItems.SHAPE_EXTRUDER_WIRE
import gregtechlite.gtlitecore.api.extension.addRecipe
import gregtechlite.gtlitecore.api.extension.stack
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_EXTRUDER_DRILL_HEAD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_EXTRUDER_ROUND
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_EXTRUDER_TURBINE_BLADE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_FIELD_BLOCK
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_FIELD_BOLT
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_FIELD_BOTTLE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_FIELD_CELL
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_FIELD_DRILL_HEAD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_FIELD_FOIL
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_FIELD_GEAR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_FIELD_GEAR_SMALL
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_FIELD_INGOT
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_FIELD_PIPE_HUGE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_FIELD_PIPE_LARGE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_FIELD_PIPE_NORMAL
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_FIELD_PIPE_SMALL
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_FIELD_PIPE_TINY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_FIELD_PLATE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_FIELD_RING
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_FIELD_ROD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_FIELD_ROD_LONG
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_FIELD_ROTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_FIELD_ROUND
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_FIELD_TURBINE_BLADE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_FIELD_WIRE
import net.minecraft.item.ItemStack

internal object BlackholeFormerRecipeProducer
{
    // @formatter:off

    private val extruder2FieldStack = mapOf(
        SHAPE_EXTRUDER_PLATE.stack()         to SHAPE_FIELD_PLATE.stack(),
        SHAPE_EXTRUDER_ROD.stack()           to SHAPE_FIELD_ROD.stack(),
        SHAPE_EXTRUDER_BOLT.stack()          to SHAPE_FIELD_BOLT.stack(),
        SHAPE_EXTRUDER_RING.stack()          to SHAPE_FIELD_RING.stack(),
        SHAPE_EXTRUDER_CELL.stack()          to SHAPE_FIELD_CELL.stack(),
        SHAPE_EXTRUDER_INGOT.stack()         to SHAPE_FIELD_INGOT.stack(),
        SHAPE_EXTRUDER_WIRE.stack()          to SHAPE_FIELD_WIRE.stack(),
        SHAPE_EXTRUDER_PIPE_TINY.stack()     to SHAPE_FIELD_PIPE_TINY.stack(),
        SHAPE_EXTRUDER_PIPE_SMALL.stack()    to SHAPE_FIELD_PIPE_SMALL.stack(),
        SHAPE_EXTRUDER_PIPE_NORMAL.stack()   to SHAPE_FIELD_PIPE_NORMAL.stack(),
        SHAPE_EXTRUDER_PIPE_LARGE.stack()    to SHAPE_FIELD_PIPE_LARGE.stack(),
        SHAPE_EXTRUDER_PIPE_HUGE.stack()     to SHAPE_FIELD_PIPE_HUGE.stack(),
        SHAPE_EXTRUDER_BLOCK.stack()         to SHAPE_FIELD_BLOCK.stack(),
        SHAPE_EXTRUDER_GEAR.stack()          to SHAPE_FIELD_GEAR.stack(),
        SHAPE_EXTRUDER_BOTTLE.stack()        to SHAPE_FIELD_BOTTLE.stack(),
        SHAPE_EXTRUDER_FOIL.stack()          to SHAPE_FIELD_FOIL.stack(),
        SHAPE_EXTRUDER_GEAR_SMALL.stack()    to SHAPE_FIELD_GEAR_SMALL.stack(),
        SHAPE_EXTRUDER_ROD_LONG.stack()      to SHAPE_FIELD_ROD_LONG.stack(),
        SHAPE_EXTRUDER_ROTOR.stack()         to SHAPE_FIELD_ROTOR.stack(),
        SHAPE_EXTRUDER_ROUND.stack()         to SHAPE_FIELD_ROUND.stack(),
        SHAPE_EXTRUDER_TURBINE_BLADE.stack() to SHAPE_FIELD_TURBINE_BLADE.stack(),
        SHAPE_EXTRUDER_DRILL_HEAD.stack()    to SHAPE_FIELD_DRILL_HEAD.stack())

    fun produce()
    {
        RecipeMaps.EXTRUDER_RECIPES.recipeList.forEach { recipe ->
            val (shapeInput, shapeField) = recipe.inputs.firstNotNullOfOrNull{ input ->
                input.shapeFieldStack()?.let { input to it }
            } ?: return@forEach

            GTLiteRecipeMaps.BLACKHOLE_FORMING_RECIPES.addRecipe {
                notConsumable(shapeField)
                inputIngredients(recipe.inputs.filterNot { it === shapeInput })
                outputs(recipe.outputs)
                EUt(recipe.eUt)
                duration(recipe.duration)
            }
        }
    }

    private fun GTRecipeInput.shapeFieldStack(): ItemStack?
        = inputStacks?.firstNotNullOfOrNull {
            extruder2FieldStack.entries.firstOrNull { (extruder, _) -> extruder.isItemEqual(it) }?.value
        }



    // @formatter: on
}